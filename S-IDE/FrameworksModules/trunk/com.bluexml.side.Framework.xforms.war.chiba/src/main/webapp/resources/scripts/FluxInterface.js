/*
    Copyright (C) 2007-2011  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/


/******************************************************************************
 GENERAL STUFF
 ******************************************************************************/
// todo: make configurable
var DATE_DISPLAY_FORMAT = "%d.%m.%Y";
var DATETIME_DISPLAY_FORMAT = "%d.%m.%Y %H:%M";
var keepAliveTimer;

// global isDirty flag signals that data have changed through user input
var isDirty = false;
// skip shutdown for load and submission actions (these do it themselves)
var skipShutdown = false;

// ***** Messages to the user - you may overwrite these in your forms with inline script blocks
var confirmMsg = "There are changed data. Really exit?";


/******************************************************************************
 PAGE init
 ******************************************************************************/
function initXForms(){
    dojo.event.connect("before",window,"onunload","close");
}

/******************************************************************************
 SESSION HANDLING AND PAGE UNLOADING
 ******************************************************************************/
window.onbeforeunload = function(e) {
    if (!e) e = event;
    return unload(e);
}

function getXFormsDOM() {
    Flux.getXFormsDOM(document.getElementById("chibaSessionKey").value,printXFormsDOM);

}

function setLocale(aLocale){
    dojo.debug("setLocale to " + aLocale);
    Flux.setLocale(aLocale,document.getElementById("chibaSessionKey").value,updateLocale);
}

function updateLocale(data) {
    activate(dojo.byId("refresh-value"));
//    alert("now do it") ;

    

}

function printXFormsDOM(data) {
    dojo.debug("XForms DOM:", data);
    dojo.byId("debug-section").appendChild(data.documentElement);
}
function unload(e) {
    if (isDirty) {
        var msg = confirmMsg;
        if (!e && window.event) {
            e = window.event;
        }
        e.returnValue = msg;
        return msg;
    }
}

function close(){
    dojo.debug("close called");
    if (!skipShutdown) closeSession();
}

//window.onunload = function() {
//    dojo.debug("unloading page");
//    if (!skipShutdown) closeSession();
//}

// Call this whenever we use the session, so we know not to call updateUI needlessly
var lastUpdateTime=0;
function localActivity() {
    lastUpdateTime=new Date().getTime();
}

// call processor to note user typing activity (not value change
// because user hasn't exited field yet), which extends session lifetime.
// Also note that navigating off is no longer OK since we've potentially
// modified something.
// noteActivity will call Flux.noteActivity
// if nothing has happened in the last minute.
function keepAlive() {
    isDirty = true;
    var x = (new Date().getTime());
    if ((x - lastUpdateTime) > (60000)) {
        localActivity();
        if(DWREngine){
            DWREngine.setErrorHandler(handleExceptions);
            DWREngine.setOrdered(true);
            var sessionKey = document.getElementById("chibaSessionKey").value;
            Flux.keepAlive(sessionKey);
        }
    }
    return false;
}

function closeSession() {
    var sessionKey = document.getElementById("chibaSessionKey").value;
    DWREngine.setErrorHandler(ignoreExceptions);
    DWREngine.setAsync(false);    
    Flux.close(sessionKey);
}

// this unfortunately does not prevent the 'DWREngine is not defined' messages when closing session
function ignoreExceptions(msg){
}

/******************************************************************************
 END OF SESSION HANDLING AND PAGE UNLOADING
 ******************************************************************************/

function useLoadingMessage() {
    DWREngine.setPreHook(function() {
        document.getElementById('indicator').className = 'enabled';
    });

    DWREngine.setPostHook(function() {
        document.getElementById('indicator').className = 'disabled';
    });
}

/*
just a starter.
*/
function handleExceptions(msg) {
    //    if(msg.indexOf(":") != -1){
    //        alert(msg.substring(msg.lastIndexOf(":") +1 ));
    //    }else{
    alert(msg);
    //    }
}
/*
This function is called whenever the user presses ENTER in an input or secret
or on a radiobutton or checkbox. Normally this should not result in a post request
in an AJAX environment. The current function simply does nothing. If something is
expected to happen on an ENTER it has to be handled here.
*/

function submitFunction(control) {
    return false;
}

// call processor to execute a trigger
function activate(target) {
    forceRepeatIndex(dojo.byId(target));

    // lookup value element
    while (target && ! _hasClass(target, "value")) {
        target = target.parentNode;
    }

    var id = target.id;
    if (id.substring(id.length - 6, id.length) == "-value") {
        // cut off "-value"
        id = id.substring(0, id.length - 6);
    }

    dojo.debug("Flux.activate: " + id);
    useLoadingMessage();
    DWREngine.setErrorHandler(handleExceptions);
    DWREngine.setOrdered(true);
    var sessionKey = document.getElementById("chibaSessionKey").value;
    Flux.fireAction(updateUI, id, sessionKey);
    return false;
}

// call processor to update a controls' value
function setXFormsValue(control, forceControl) {
    DWREngine.setErrorHandler(handleExceptions);
    var target;
    //forceControll is used to ignore the window.event
    // => set to true if you want to call this function on a control, other than the source of the event
    if (window.event && !forceControl) {
        target = window.event.srcElement;
    }
    else {
        target = control;
    }

    var id = target.id;
    if (id.substring(id.length - 6, id.length) == "-value") {
        // cut off "-value"
        id = id.substring(0, id.length - 6);
    }

    var value = "";
    if (target.value) {
        value = target.value;
    }

    switch (target.type) {
        case "radio":
        // get target id from parent control, since the id passed in is the item's id
            while (! _hasClass(target, "select1")) {
                target = target.parentNode;
            }
            id = target.id;
            break;
        case "checkbox":
        // keep name
            var name = target.name;

        // get target id from parent control, since the id passed in is the item's id
            while (! _hasClass(target, "select")) {
                target = target.parentNode;
            }
            id = target.id;

        // assemble value from selected checkboxes
            var elements = eval("document.chibaform.elements");
            var checkboxes = new Array();
            for (var i = 0; i < elements.length; i++) {
                if (elements[i].name == name && elements[i].type != "hidden" && elements[i].checked) {
                    checkboxes.push(elements[i].value);
                }
            }
            value = checkboxes.join(" ");
            break;
        case "select-multiple":
        // assemble value from selected options
            var options = target.options;
            var multiple = new Array();
            for (var i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    multiple.push(options[i].value);
                }
            }
            value = multiple.join(" ");
            break;
        default:
            break;
    }

    dojo.debug("Flux.setXFormsValue: " + id + "='" + value + "'");
    useLoadingMessage();

    DWREngine.setOrdered(true);
    DWREngine.setErrorHandler(handleExceptions);
    var sessionKey = document.getElementById("chibaSessionKey").value;
    Flux.setXFormsValue(updateUI, id, value, sessionKey);
    isDirty = true;                                    
}

/******************************************************************************
 CONTROL SPECIFIC FUNCTIONS
 ******************************************************************************/

function setRange(id, value) {
    dojo.debug("Flux.setRangeValue: " + id + "='" + value + "'");

    //todo: fix for IE
    //    var oldValue = document.getElementsByName(id + '-value')[0];
    var oldValue = document.getElementsByClassName('rangevalue', document.getElementById(id))[0];
    if (oldValue) {
        oldValue.className = "step";
        //        oldValue.removeAttribute("name");
    }

    var newValue = document.getElementById(id + value);
    newValue.className = newValue.className + " rangevalue";
    //    newValue.setAttribute("name", id + "-value");

    DWREngine.setErrorHandler(handleExceptions);
    var sessionKey = document.getElementById("chibaSessionKey").value;
    Flux.setXFormsValue(updateUI, id, value, sessionKey);
}


// call the processor to set a repeat's index
function setRepeatIndex(e) {
    // get event target
    var target = _getEventTarget(e);

    // lookup repeat item
    while (target && ! _hasClass(target, "repeat-item")) {
        target = target.parentNode;
    }

    // maybe the user clicked on a whitespace node between to items *or*
    // on an already selected item, so there is no item to select
    if ((!target) || _hasClass(target, "repeat-index")) {
        return;
    }

    chiba.setRepeatIndex(target);
}

/*
Unconditionally forces the repeat index if a trigger in a repeat-item is clicked. Called from activate()
*/
function forceRepeatIndex(control) {
    // get event target
    var target = control;

    // lookup repeat item
    while (target && ! _hasClass(target, "repeat-item")) {
        target = target.parentNode;
    }

    if(target == null){
        return;
    }

    chiba.setRepeatIndex(target);
}

function _getEventTarget(event) {
    var target;
    if (window.event) {
        target = window.event.srcElement;
    }
    else {
        target = event.target;
    }

    return target;
}

// callback for updating any control
function updateUI(data) {
    dojo.debug("updateUI: " + data);

    var eventLog = data.documentElement.childNodes;
    var selects = [];
    var tmpNr = 0;    
    for (var i = 0; i < eventLog.length; i++) {    
        var type = eventLog[i].getAttribute("type");
        var targetName = eventLog[i].getAttribute("targetName");
        if((targetName == "select1" || targetName == "select") && type=="chiba-state-changed" ){
            selects[tmpNr] = eventLog[i]; 
            data.documentElement.removeChild(eventLog[i]);
            tmpNr++;
        }
    }
    for (var i =0; i <selects.length; i++) {
            data.documentElement.appendChild(selects[i]); 
    }

    for (var i = 0; i < eventLog.length; i++) {
        var type = eventLog[i].getAttribute("type");
        var targetId = eventLog[i].getAttribute("targetId");
        var targetName = eventLog[i].getAttribute("targetName");
        var properties = new Array;
        var name;
        for (var j = 0; j < eventLog[i].childNodes.length; j++) {
            if (eventLog[i].childNodes[j].nodeName == "property") {
                name = eventLog[i].childNodes[j].getAttribute("name");
                if (eventLog[i].childNodes[j].childNodes.length > 0) {
                    properties[name] = eventLog[i].childNodes[j].childNodes[0].nodeValue;
                }
                else {
                    properties[name] = "";
                }
            }
        }

        var context = new PresentationContext();
        _handleServerEvent(context, type, targetId, targetName, properties);
    }
}


function _handleServerEvent(context, type, targetId, targetName, properties) {
    dojo.debug("handleServerEvent: type=" + type + " targetId=" + targetId);
    switch (type) {
        case "chiba-load-uri":
            isDirty = false;
            if (properties["show"] == "replace") {
              skipShutdown = true;
            }
            context.handleLoadURI(properties["uri"], properties["show"]);
            break;
        case "chiba-render-message":
            context.handleRenderMessage(properties["message"], properties["level"]);
            break;
        case "chiba-replace-all":
            isDirty = false;
            skipShutdown = true;
            context.handleReplaceAll(properties["webcontext"]);
            break;
        case "chiba-state-changed":
            if (properties["value"] != "") {
                isDirty = true;                                
            }
            // this is a bit clumsy but needed to distinguish between controls and helper elements
            if (properties["parentId"]) {
                context.handleHelperChanged(properties["parentId"], targetName, properties["value"]);
            }
            else {
                context.handleStateChanged(targetId, targetName, properties["valid"], properties["readonly"], properties["required"], properties["enabled"], properties["value"],properties["type"]);
            }
            break;
        case "chiba-prototype-cloned":
            context.handlePrototypeCloned(targetId, targetName, properties["originalId"], properties["prototypeId"]);
            break;
        case "chiba-id-generated":
            context.handleIdGenerated(targetId, properties["originalId"]);
            break;
        case "chiba-item-inserted":
            context.handleItemInserted(targetId, targetName, properties["originalId"], properties["position"]);
            break;
        case "chiba-item-deleted":
            context.handleItemDeleted(targetId, targetName, properties["originalId"], properties["position"]);
            break;
        case "chiba-index-changed":
            context.handleIndexChanged(targetId, properties["originalId"], properties["index"]);
            break;
        case "chiba-switch-toggled":
            context.handleSwitchToggled(properties["deselected"], properties["selected"]);
            break;
        case "upload-progress-event":
        //            _updateProgress(targetId,properties["progress"])
            var currentUpload = dojo.widget.byId(targetId + "-value");
            currentUpload.updateProgress(properties["progress"])
            break;
        case "xforms-submit-error":
            _highlightFailedRequired(properties);
            break;
        case "chiba-script-action":
            dojo.debug("handle Script action: " + properties["script"]);
            eval(properties["script"]);
            break;
        case "xforms-focus":
            context.handleFocus(targetId);
            break;
        default:
            dojo.debug("Event " + type + " unknown");
            break;
    }
}

function getXFormsControlLabel(controlName, hasInnerText) {
	var node = document.getElementById(controlName + "-label");
	
	if (node) {
		var initialText;
		var pos;
		if (hasInnerText) {
			initialText = node.innerText;
		} else {
			initialText = node.textContent;
		}
		pos = initialText.lastIndexOf("*");
		if (pos != -1) { // has required symbol
			initialText = initialText.substring(0, pos);
		}
		pos = initialText.lastIndexOf(" :");
		if (pos != -1) { 
			initialText = initialText.substring(0, pos);
		}
		return initialText;
	} else {
		return ""; // this should never happen... normally.
	}
}

var submissionErrors = 0;
function _highlightFailedRequired(properties) {

    if (properties["cause"]) {
        alert(properties["cause"]);
        submissionErrors = 0;
    }

    //$$
    // show an alert if the user repeatedly sends incomplete data
    if (submissionErrors >= 1) {
		//## Collect the names of required fields that have no value
	    var fields = [];
	    var labelnames = [];
	    var namelist = "";
	    var isFirst = true;
	    var hasInnerText = (document.getElementsByTagName("body")[0].innerText !== undefined) ? true : false;
	    
	    var required = document.getElementsByClassName("required", "chibaform");
	    
	    for (var ii = 0,len = required.length; ii < len; ii++) {
	    	var fieldname = required[ii].id;
	        var control = $(fieldname);
		    var value = getXFormsControlValue(control);
	        if (value === null || value === "") {
		        var label = getXFormsControlLabel(fieldname, hasInnerText);
		        if (label === null || label === "") {
		            // nothing to do
		        } else {
		            fields.push(label);
		            labelnames.push(fieldname + "-label");
		        }
		    }
	    }

	    //## 
	    if (fields.length > 0) {
	        namelist = fields.join(", ");
            alert("Please provide values for all required fields (" + namelist + ").");
        } else {
            alert("Please provide values for all required fields.");
        }
        submissionErrors = 0;
    }

	if (fields) {
		// we've already seen the labels to pulsate so don't lookup again
		for (var idx = 0; idx < fields.length; idx++) {
			new Effect.Pulsate($(labelnames[idx]));
		}
	} else {
	    //lookup all required fields and check if they contain a value
	    var foo = document.getElementsByClassName("required", "chibaform");
	    for (var i = 0,j = foo.length; i < j; i++) {
	        var control = $(foo[i].id);
	        var value = getXFormsControlValue(control);
	        if (value === null || value === "") {
	            new Effect.Pulsate($(foo[i].id + "-label"));
	        }
	    }
	}

    new Effect.Pulsate(document.getElementById("required-msg"));
    submissionErrors ++;
}

/* help function - still not ready */
function showHelp(helptext) {
    alert(helptext);
    var helpwnd = window.open('', '', 'scrollbars=no,menubar=no,height=400,width=400,resizable=yes,toolbar=no,location=no,status=no');
    helpwnd.document.getElementsByTagName("body")[0].innerHTML = helptext;

}
/******************************************************************************
 FUNCTIONS calling Chiba processor - should become an API one day
 ******************************************************************************/

var chiba  = function() {
};

chiba.setRepeatIndex = function(targetRepeatElement){
    var target = targetRepeatElement;
    target.setAttribute("selected", "true");

    var repeatItems = target.parentNode.childNodes;
    var currentPosition = 0;
    var targetPosition = 0;

    // lookup target to compute logical position
    for (var index = 0; index < repeatItems.length; index++) {
        if (repeatItems[index].nodeType == 1 && _hasClass(repeatItems[index], "repeat-item")) {
            currentPosition++;

            if (repeatItems[index].getAttribute("selected") == "true") {
                repeatItems[index].removeAttribute("selected");
                targetPosition = currentPosition;

                // optimistic update
                _addClass(repeatItems[index], "repeat-index-pre");
            }

            _removeClass(repeatItems[index], "repeat-index")
        }
    }

    // lookup repeat id
    while (! _hasClass(target, "repeat")) {
        target = target.parentNode;
    }
    var repeatId = target.id;

    dojo.debug("Flux.setRepeatIndex: " + repeatId + "='" + targetPosition + "'");
    useLoadingMessage();
    DWREngine.setErrorHandler(handleExceptions);
    DWREngine.setOrdered(true);
    var sessionKey = document.getElementById("chibaSessionKey").value;
    Flux.setRepeatIndex(updateUI, repeatId, targetPosition, sessionKey);
}
