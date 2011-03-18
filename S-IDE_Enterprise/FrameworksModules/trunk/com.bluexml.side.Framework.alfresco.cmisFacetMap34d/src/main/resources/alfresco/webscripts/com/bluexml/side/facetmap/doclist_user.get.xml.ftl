<#--
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

-->


<#import "/org/alfresco/cmis/lib/ns.lib.atom.ftl" as nsLib/>
<#import "/org/alfresco/cmis/lib/links.lib.atom.ftl" as linksLib/>
<#import "/org/alfresco/cmis/lib/atomentry.lib.atom.ftl" as entryLib/>
<classes xmlns:cmis="<@nsLib.cmisNS/>" xmlns:alf="<@nsLib.alfNS/>" xmlns:app="<@nsLib.appNS/>" xmlns:cmisra="<@nsLib.cmisraNS/>">
<#list resultset as row>
<@entryLib.document row />
</#list>

</classes>
