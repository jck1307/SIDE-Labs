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


//Fonction qui change le langage sur action à partir de la dashlet languagePicker
function loadShareDashboardWithLang(lang)
{	
	var params = window.location.search;
	var searchString = "";
	
	if (params.length > 0)
	{
		var position = params.indexOf("shareLang=", 0);
		if (position == -1)
			searchString = params + "&shareLang=" + lang
		else
		{
			var end = params.indexOf("&", position);
			
			if (end == -1)
				searchString = params.substring(0, position + 10) + lang;
			else
				searchString = params.substring(0, position + 10) + lang + params.substring (end);
		}
	}
	else
		searchString = "?shareLang=" + lang
	
	window.location.href = window.location.protocol + "//" + window.location.host + window.location.pathname + searchString;
}

function changeLanguage(lang) {
    var preferences = new Alfresco.service.Preferences();
    var responseConfig = 
         {
            failureCallback: {
            
               fn: function DL_oFD_failure(event, p_oRecord) {
                  Alfresco.util.PopupManager.displayPrompt(
                  {
                     title: "Error",
                     text: "Fail to change user language"
                  });
               },
               scope: this
            },
            successCallback : {
                fn: function (response, obj) {
                    loadShareDashboardWithLang(lang);
                },
                scope: this
            }
         };
    preferences.set("user_language", lang ,responseConfig);

}
