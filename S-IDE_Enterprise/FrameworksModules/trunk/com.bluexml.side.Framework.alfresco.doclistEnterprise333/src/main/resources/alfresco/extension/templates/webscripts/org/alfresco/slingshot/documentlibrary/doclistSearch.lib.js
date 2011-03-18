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


const DEFAULT_MAX_RESULTS = 100;
const SITES_SPACE_QNAME_PATH = "/app:company_home/st:sites/";
/**
 * Return Search results with the given search terms
 * Terms are split on whitespace characters.
 * 
 * AND, OR and NOT are supported - as their Lucene equivalent.
 */
function getSearchQuery(term, maxResults, siteId, containerId)
{
   var path = SITES_SPACE_QNAME_PATH;
   if (siteId !== null && siteId.length > 0)
   {
      path += "cm:" + search.ISO9075Encode(siteId) + "/";
   }
   else
   {
      path += "*/";
   }
   if (containerId !== null && containerId.length > 0)
   {
      path += "cm:" + search.ISO9075Encode(containerId) + "/";
   }
   else
   {
      path += "*/";
   }
	
   var luceneQuery = "";
   if (term !== null && term.length !== 0)
   {
      // TODO: Perform smarter term processing. For now we simply split on whitespace
      //       which ignores quoted phrases that may be present.
      var terms = term.split(/\s/),
         i, j, t;
      
      for (i = 0, j = terms.length; i < j; i++)
      {
         t = terms[i];
         // remove quotes - TODO: add support for quoted terms later
         t = t.replace(/\"/g, "");
         if (t.length !== 0)
         {
            switch (t.toLowerCase())
            {
               case "and":
                  if (i < j - 1 && terms[i + 1].length !== 0)
                  {
                     luceneQuery += "AND ";
                  }
                  break;
               
               case "or":
                  break;
               
               case "not":
                  if (i < j - 1 && terms[i + 1].length !== 0)
                  {
                     luceneQuery += "NOT ";
                  }
                  break;
               
               default:
                  luceneQuery += "(TEXT:\"" + t + "\"" +        // full text
                                 " @cm\\:name:\"" + t + "\"" +  // name property
                                 " @lnk\\:title:\"" + t + "\"" +  // link title
                                 " @lnk\\:description:\"" + t + "\"" +  // link description
                                 " PATH:\"/cm:taggable/cm:" + search.ISO9075Encode(t) + "/member\"" + // tags
                                 ") ";
            }
         }
      }
   }
   
   
   
   // if we processed the search terms, then suffix the PATH query
   if (luceneQuery.length !== 0)
   {
      luceneQuery = "+PATH:\"" + path + "/*\" +(" + luceneQuery + ")";
      luceneQuery += " -TYPE:\"{http://www.alfresco.org/model/content/1.0}thumbnail\"";
      
   }
   else
   {
      return null;
   }
   
   return luceneQuery;
}
