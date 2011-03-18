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


Ext.onReady(function(){

	function createPoint(lat,long) {
		var reg=new RegExp("(,)", "g");
		return new GLatLng(lat.replace(reg,"."), long.replace(reg,"."));
	}

	function load() {
	    var mapwin = new Ext.Panel({
	        title: 'GMap Window',
	        width: '100%',
	        height:600,
	        items: {
				xtype: 'gmappanel',
	            zoomLevel: 5,
	            gmapType: 'map',
	            id:'mygmap',
	            mapConfOpts: ['enableScrollWheelZoom','enableDoubleClickZoom','enableDragging'],
	            mapControls: ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
	            setCenter: {
	                geoCodeAddr: 'Kammerzell House, Strasbourg, France',
	                marker: {title: 'Paris',hide:true}
	            }
	        }
	    });
	    
	    mapwin.render('grid-example-gmap');
	
	    var defaultIcon = new GIcon(G_DEFAULT_ICON);
	    
	    var pictureIcon = new GIcon(G_DEFAULT_ICON);
		pictureIcon.image = "/library/css/images/picture.png";
		pictureIcon.iconSize = new GSize(32,32);
	
	    var videoIcon = new GIcon(G_DEFAULT_ICON);
		videoIcon.image = "/library/css/images/video.png";
		videoIcon.iconSize = new GSize(32,32);
	
	    var textIcon = new GIcon(G_DEFAULT_ICON);
		textIcon.image = "/library/css/images/text.png";
		textIcon.iconSize = new GSize(32,32);
	
	    
		Ext.Ajax.request({
	    	url:getDataSource('json',_TICKET),
	    	success: function ( result, request ) {
	    		var jsonData = Ext.util.JSON.decode(result.responseText);
	    		for (var i = 0; i < jsonData.records.length; i++) {
	    			var doc = jsonData.records[i];
	    			var icon = defaultIcon;
	    			if (doc._typeOfDocument == 'Picture')
	    				icon = pictureIcon;
	    			else if (doc._typeOfDocument == 'Video')
	    				icon = videoIcon;
	    			else if (doc._typeOfDocument == 'Textual document')
	    				icon = textIcon;
	    			Ext.getCmp('mygmap').addMarker(createPoint(doc._latitude, doc._longitude),{title:doc._name, icon:icon});
	    		}
	    	}
	    });
	}
	    
    loadWithAuthentication(load);
 });
