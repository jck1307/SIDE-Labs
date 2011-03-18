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
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/com/bluexml/components/Xform/Xform.get.properties

<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
form.field.not.editable=This field is read-only as the system has prevented it from being edited.
form.field.ambiguous=is ambiguous, please contact your system administrator to remove this message.
form.field.incomplete=This field is mandatory but a value has not been provided.

form.button.submit.label=Submit
form.button.reset.label=Reset
form.button.cancel.label=Cancel
form.required.fields=Required Fields
form.required.fields.marker=*
form.jsonsubmit.failed=Failed to submit JSON data, see logs for details.
form.default.set.label=Default
form.notset=Not Set

## date picker control messages
form.control.date-picker.choose=Choose a date:

## Format used for date entry in date picker, 'entry' format is used by date parsing code,
## 'display' format is shown to the user under date entry field
form.control.date-picker.entry.date.format=d/M/yyyy
form.control.date-picker.display.date.format=DD/MM/YYYY

## Format used for time entry in datetime picker, 'entry' format is used by date parsing code,
## 'display' format is shown to the user under time entry field
form.control.date-picker.entry.time.format=HH:mm
form.control.date-picker.display.time.format=HH:MM (24 Hour)

## Format used for date and time when form is in 'view' mode
form.control.date-picker.view.date.format=EEE dd MMM yyyy
form.control.date-picker.view.time.format=EEE dd MMM yyyy HH:mm:ss

## selectone control messages
form.control.selectone.missing-options=&lt;No options available&gt;

## transient control messages
form.control.mimetype.label=Mimetype
form.control.mimetype.unknown=Unknown
form.control.encoding.label=Encoding
form.control.encoding.unknown=Unknown
form.control.size.label=Size

## period control messages
form.control.period.type=Period
form.control.period.expression=Expression

## object picker control messages
form.control.object-picker.header=Select...
form.control.object-picker.current.failure=Could not display current values.
form.control.object-picker.items-list.loading=Loading...
form.control.object-picker.items-list.empty=No items found
form.control.object-picker.selected-items.empty=No items selected
form.control.object-picker.add-item=Add
form.control.object-picker.remove-item=Remove

## association picker control messages
form.control.association.message.instructions=Enter a search term
form.control.association.message.empty=No items found
form.control.association.message.error=Unable to perform search
form.control.association.message.searching=Searching...
