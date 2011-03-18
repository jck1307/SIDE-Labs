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


var results = {};
var result= new Array();
var resource = "" + url.extension;
var query  = args.q;
var status = "succeed";

switch (resource)
{
   case "google":

      var googleServiceUrl = "http://google.com/complete/search?output=toolbar&q="+query;
      var connector = remote.connect("http");
      var str = new String(connector.call(googleServiceUrl));

      //Javascript E4X module has problems with XML header
      if ( str.substr(0,5).indexOf("?xml") != -1 ) 
      {
         positionRootElement = str.indexOf("<", 10);//get first real tag
         str = str.substr( positionRootElement, str.length - 1 ); 
      }

      var suggestions = new XML(str);
      var suggestion;
      for each (suggestion in suggestions.CompleteSuggestion)
      {
         var resultItem = {};
         resultItem.name  = suggestion.suggestion.@data.toString();
         resultItem.value = suggestion.suggestion.@data.toString();
         result.push(resultItem);
      }
   
      break;
   
   case "yahoo":
   
      // get appid from configuration
      var s = new XML(config.script);
      var appid = s.yahoo.appid;

      var yahooServiceUrl = "http://search.yahooapis.com/WebSearchService/V1/relatedSuggestion?query="+query+"&appid="+appid+"&output=json";
      var connector = remote.connect("http");
      var jsonStr = eval("(" + connector.call(yahooServiceUrl) + ")");

      var suggestion;
      for each (suggestion in jsonStr.ResultSet.Result)
      {
         var resultItem = {};
         resultItem.name  = suggestion;
         resultItem.value = suggestion;
         result.push(resultItem);
      }
   
      break;
   
   default : 
      status="failed";
}

results.result = result;
model.result = jsonUtils.toJSONString(results);

