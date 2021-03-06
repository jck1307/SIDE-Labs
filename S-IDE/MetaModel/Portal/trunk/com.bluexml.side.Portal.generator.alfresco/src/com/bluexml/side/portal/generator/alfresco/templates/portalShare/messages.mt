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
metamodel http://www.kerblue.org/portal/1.0
%>

<%-- Templates creation --%>
<%script type="Portal" name="createMessages"%>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.core.messages")%>slingshot.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
## Slingshot Common Messages

## Buttons
button.ok=OK
button.cancel=Cancel
button.remove=Remove
button.browse=Browse
button.edit=Edit
button.save=Save
button.savechanges=Save Changes
button.upload=Upload
button.login=Login
button.yes=Yes
button.no=No
button.delete=Delete
button.close=Close
button.download=Download
button.add=Add
button.clear=Clear
button.search=Search
button.select=Select
button.update=Update
button.goback=Go Back

## File Size Format function
size.bytes=bytes
size.kilobytes=KB
size.megabytes=MB
size.gigabytes=GB

## Dates
days.initial=S,M,T,W,T,F,S
days.short=Su,Mo,Tu,We,Th,Fr,Sa
days.medium=Sun,Mon,Tue,Wed,Thu,Fri,Sat
days.long=Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday
months.short=Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec
months.long=January,February,March,April,May,June,July,August,September,October,November,December

## Date Formats
date-format.default=ddd d mmm yyyy HH:MM:ss
date-format.shortDate=d/m/yy
date-format.mediumDate=d mmm, yyyy
date-format.longDate=d mmmm, yyyy
date-format.fullDate=dddd, d mmmm, yyyy
date-format.shortTime=h:MM TT
date-format.mediumTime=h:MM:ss TT
date-format.longTime=h:MM:ss TT Z
date-format.am=am
date-format.pm=pm

# Messages
message.success=Success
message.failure=Failure
message.loginfailure=Failed to Login
message.loginautherror=The remote server may be unavailable or your authentication details have not been recognized.
message.please-wait=Please wait...

# Common Text labels
label.tags=Tags
label.none=(None)
label.about=About
label.username=User Name
label.password=Password
label.name=Name
label.title=Title
label.description=Description
label.firstname=First Name
label.lastname=Last Name
label.jobtitle=Job Title
label.location=Location
label.bio=Summary
label.photo=Photo
label.contactinfo=Contact Information
label.skype=Skype
label.im=IM
label.company=Company
label.companyinfo=Company Details
label.email=Email
label.telephone=Telephone
label.mobile=Mobile
label.fax=Fax
label.address=Address
label.postcode=Post Code
label.groups=Groups
label.yes=Yes
label.no=No
label.site=Site
label.path=Path

## Rich Text Editor
# i18n
tinymce_languages=en,de,es,fr,it,ja

# Rich Text Editor - Image Library
imagelib.title=Library Images
imagelib.tooltip=Insert Library Image

## Pagination
pagination.template={CurrentPageReport} <span class="separator">&nbsp;</span> &nbsp;&nbsp;{PreviousPageLink} {PageLinks} {NextPageLink}
pagination.template.page-report=Showing items {startRecord} - {endRecord} of {totalRecords}
pagination.previousPageLinkLabel=&lt;&lt; Previous
pagination.nextPageLinkLabel=Next &gt;&gt;

## Messages
message.minimum-length=Enter at least {0} characters

## Admin console tools
tool.users.label=Users
tool.users.description=User Management
tool.groups.label=Groups
tool.groups.description=Group Management

## Page titles
page.acceptInvite.title=Accept invite
page.acceptInvite.description=Accept invite page. Loading the page will trigger the site invitation to be accepted and enables the account of the user if still disabled
page.addGroups.title=Add Groups
page.addGroups.description=Add Groups
page.adminConsole.title=Admin Console
page.adminConsole.description=Administration and Management Console
page.blogCreateEdit.title=Blog: create or edit blog post
page.blogCreateEdit.description=Displays a form to create or edit a post
page.blogPostList.title=Blog
page.blogPostList.description=Displays the blog posts
page.blogPostView.title=Blog: display post detail view
page.blogPostView.description=Displays the post detail view
page.collaborationDetails.title=Collaboration document details page
page.collaborationDetails.description=Collaboration site's doclib page
page.calendar.title=Calendar
page.calendar.description=Site Calendar Component
page.changePassword.title=Change Password Page for a User
page.changePassword.description=Change Password section on Profile page for a User
page.contentViewer.title=Content Viewer
page.contentViewer.description=Page for viewing the content of a node ref
page.customiseSite.title=Customize Site
page.customiseSite.description=Add and remove pages from a site
page.customiseSiteDashboard.title=Customize Site Dashboard
page.customiseSiteDashboard.description=Customize dashboard layout and dashlets for a site dashboard
page.customiseUserDashboard.title=Customize User Dashboard
page.customiseUserDashboard.description=Customize dashboard layout and dashlets for a user dashboard
page.discussionsCreateTopic.title=Discussions: view create topic page
page.discussionsCreateTopic.description=Displays the form to create a topic
page.discussionsTopicList.title=Discussions
page.discussionsTopicList.description=Displays the forum topics
page.discussionsTopicView.title=Discussions: display topic detail view
page.discussionsTopicView.description=Displays the topic detail view
page.documentDetails.title=Document Details
page.documentDetails.description=Document details
page.documentLibrary.title=Document Library
page.documentLibrary.description=Document library with Tree view
page.editMetadata.title=Edit Metadata
page.editMetadata.description=Edit metadata of a node
page.folderDetails.title=Folder Details
page.folderDetails.description=Folder details
page.invite.title=Invite
page.invite.description=Invite
page.links.title=Links
page.links.description=Links
page.linkCreateEdit.title=Links: create or edit link
page.linkCreateEdit.description=Displays a form to create or edit link
page.linksView.title=Links: display links detail view
page.linksView.description=Displays the links detail view
page.login.title=Login
page.logout.title=Logout
page.pendingInvites.title=Pending Invites
page.pendingInvites.description=Pending Invites
page.peopleFinder.title=People Finder
page.peopleFinder.description=People finder page
page.profile.title=Profile Page for a User
page.profile.description=Profile page for a User
page.rejectInvite.title=Reject invite
page.rejectInvite.description=Reject invite page. Rejects an invite request
page.search.title=Search
page.search.description=Search view
page.siteFinder.title=Site Finder
page.siteFinder.description=Site finder page
page.siteIndex.title=Welcome
page.siteIndex.description=Landing page for users - will create user site dashboard as required and forward to it
page.siteGroups.title=Site Groups
page.siteGroups.description=Site Groups
page.siteMembers.title=Site Members
page.siteMembers.description=Site Members
page.wiki.title=Wiki
page.wiki.description=The landing page for the wiki
page.wikiCreate.title=Wiki Create Form
page.wikiCreate.description=Displays a form to create a new wiki page
page.wikiPage.title=Wiki
page.wikiPage.description=Displays a wiki page
page.rmDocumentLibrary.title=Fileplan
page.rmDocumentLibrary.description=Records Management Fileplan with Tree view
page.rmSearch.title=Records Search
page.rmSearch.description=Records Management Search page
page.rmReports.title=Records Report
page.rmReports.description=Records Management Report page
page.userContent.title=Last edited content list for a User
page.userContent.description=Lists content that a user has last edited
page.userSites.title=Sites list for a User
page.userSites.description=Lists sites that a user belongs to

## Dashboard page titles and descriptions
page.siteDashboard.title=Collaboration Site Dashboard
page.siteDashboard.description=Collaboration site's dashboard page
page.userDashboard.title=User Dashboard
page.userDashboard.description=Users dashboard page
page.rmSiteDashboard.title=Records Management Site Dashboard
page.rmSiteDashboard.description=Records Management site's dashboard page
page.workspace.title=Document Workspace Dashboard
page.workspace.description=Document Workspace dashboard page

## Roles
role.SiteManager=Manager
role.SiteCollaborator=Collaborator
role.SiteContributor=Contributor
role.SiteConsumer=Consumer

## Tag Library
taglibrary.tags=Tags
taglibrary.typetag=Type Tag(s)
taglibrary.populartagslink=Choose from popular tags in this site
taglibrary.msg.failedLoadTags=Cannot load tags from the server (connection down?)
taglibrary.msg.unableLoadTags=Unable to load tags: {0}
taglibrary.tip.removeTag=Remove tag

## YUI Editor keys
yuieditor.toolbar.group.font=Font Style
yuieditor.toolbar.group.lists=Lists
yuieditor.toolbar.group.link=Link
yuieditor.toolbar.item.createorderedlist=Create ordered list
yuieditor.toolbar.item.createunorderedlist=Create unordered list
yuieditor.toolbar.item.fontcolor=Font color
yuieditor.toolbar.item.backgroundcolor=Background color
yuieditor.toolbar.item.bold=Bold CTRL + SHIFT + B
yuieditor.toolbar.item.italic=Italic CTRL + SHIFT + I
yuieditor.toolbar.item.underline=Underline CTRL + SHIFT + U
yuieditor.toolbar.item.link=HTML Link CTRL + SHIFT + L

## BlueXML SIDE Custom pages keys
<%for (pageSet){%>
<%ID.toLowerCase().nPut("messages_name")%>
<%ID.nPut("title_name")%>
<%title.nPut("description_name")%>
<%if (current().generate){%>
page.<%nGet("messages_name")%>.title=<%nGet("title_name")%>
page.<%nGet("messages_name")%>.description=<%if (nGet("description_name") !=null){%><%nGet("description_name")%><%}else{%><%}%>
<%}%>

<%}%>
