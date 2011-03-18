<%--
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

--%>


<%
metamodel http://www.kerblue.org/class/1.0

import templates.alfrescoshare.uploadForm.file-upload-js-get-lib
import templates.servicesTemplates.Common
%>

<%--
  -- This file is a patched copy of Alfresco Share's
  -- Contains additional message for custom Types 
  --%>
  
<%script type="clazz.ClassPackage" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/documentlibrary/documentlist.get.properties<%}%>

<%script type="clazz.ClassPackage" name="htmlUploadJs" file="<%fileName%>"%>
## Buttons
button.folders.show=Show Folders
button.folders.hide=Hide Folders
button.view.simple=Simple View
button.view.detailed=Detailed View

## Drop-down Menus
menu.select=Select
menu.select.all=All
menu.select.none=None
menu.select.invert=Invert Selection
menu.select.folders=Folders
menu.select.documents=Documents

## Document Details
details.link-to=Link to: {0}
details.created.on=Created on:
details.created.by=Created by:
details.modified.on=Modified on:
details.modified.by=Modified by:
details.checked-out.on=Checked out on:
details.checked-out.by=Checked out by:
details.by=By:
details.version=Version:
details.size=Size:
details.description=Description:
details.description.none=(None)
details.comments=Comments:
details.tags=Tags:
details.tags.none=(None)

## Actions
actions.description.empty=Empty action set
actions.description.document=Document default
actions.description.folder=Folder default
actions.description.locked=Locked by another user
actions.description.lockOwner=Locked by you
actions.description.workingCopyOwner=Checked-out by you
actions.description.link=Document or Folder link

actions.document.assign-workflow=Assign Workflow
actions.document.cancel-editing=Cancel Editing
actions.document.copy-to=Copy to...
actions.document.delete=Delete Document
actions.document.download=Download
actions.document.download-again=Download
actions.document.download-original=Download Original
actions.document.edit-metadata=Edit Metadata
actions.document.edit-offline=Edit Offline
actions.document.edit-online=Edit Online
actions.document.manage-aspects=Manage Aspects
actions.document.manage-permissions=Manage Permissions
actions.document.move-to=Move to...
actions.document.upload-new-version=Upload New Version
actions.link.delete=Delete Link
actions.folder.edit-metadata=Edit Metadata
actions.folder.view-metadata=View Details
actions.folder.copy-to=Copy to...
actions.folder.move-to=Move to...
actions.folder.delete=Delete Folder
actions.folder.manage-permissions=Manage Permissions
actions.folder.manage-aspects=Manage Aspects
actions.more=More...

## Tips
tip.locked=Locked by {0} ({1})
tip.editing=Being edited by you
tip.lock-owner=Locked by you
tip.active-workflow=Belongs to {0} active workflows
tip.favourite-document.add=Add to favorites
tip.favourite-document.remove=Remove from favorites

## Pop-up Messages
message.confirm.delete.title=Delete File
message.confirm.delete=Are you sure you want to delete '{0}'?
message.delete.success='{0}' was deleted
message.delete.failure=Could not delete '{0}'
message.edit-offline.success='{0}' can now be edited
message.edit-offline.success.ie7=Download the document using the button below.
message.edit-offline.failure=You cannot edit '{0}'.
message.edit-cancel.success=Editing '{0}' has been cancelled
message.edit-cancel.failure=Could not cancel editing '{0}'.
message.loading=Loading the Document Library...
message.error=Could not access the Document Library
message.empty=No items
message.empty.subfolders=No items. Click "{0}" to see {1} subfolder(s) here.
message.empty.subfolders.link=Show Folders
message.favourite.failure=Could not update favorites list

## File Upload (upload new version)
label.filter-description=Same type as {0}

## Customize Dialog
customize.title=Customize
customize.header.actions=Actions

## Custom Types
<%for (getAllAbstractClasses().nSort("name")){%>
### <%getFullName()%>
<%for (getSortedAttibutes()){%>
<%getQualifiedName%>=<%getLabel()%>
<%}%>
<%}%>
