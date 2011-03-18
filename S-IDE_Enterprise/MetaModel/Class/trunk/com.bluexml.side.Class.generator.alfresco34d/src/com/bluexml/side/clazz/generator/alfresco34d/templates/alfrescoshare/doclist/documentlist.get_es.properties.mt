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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/documentlibrary/documentlist.get_es.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Buttons
button.folders.show=Mostrar carpetas
button.folders.hide=Ocultar carpetas
button.view.simple=Vista sencilla
button.view.detailed=Vista detallada

## Drop-down Menus
menu.select=Seleccionar
menu.select.all=Todas
menu.select.none=Ninguna
menu.select.invert=Invertir la selecci\u00f3n
menu.select.folders=Carpetas
menu.select.documents=Documentos

## Document Details
details.link-to=Enlace a: {0}
details.created.on=Creado el:
details.created.by=Creado por:
details.modified.on=Modificado el:
details.modified.by=Modificado por:
details.editing-started.on=Inicio de edici\u00f3n el:
details.editing-started.by=Edici\u00f3n iniciada por:
details.by=Por:
details.version=Versi\u00f3n:
details.size=Tama\u00f1o:
details.description=Descripci\u00f3n:
details.description.none=(Ninguna)
details.comments=Comentarios:
details.tags=Etiquetas:
details.tags.none=(Ninguna)

## Details Banners
details.banner.editing=Este documento est\u00e1 bloqueado por usted para edici\u00f3n fuera de l\u00ednea.
details.banner.lock-owner=Este documento est\u00e1 bloqueado por usted.
details.banner.locked=Este documento est\u00e1 bloqueado por {0} para edici\u00f3n.
details.banner.google-docs.link=Google Docs
details.banner.google-docs-owner=Este documento ha sido subido por usted a {0} para su edici\u00f3n.
details.banner.google-docs-locked=Este documento ha sido subido por {0} a {1} para su edici\u00f3n.

## Actions - moved to slingshot.properties
actions.more=M\u00e1s...

## Tips
tip.active-workflow=Pertenece a {0} flujos de trabajo activos
tip.editing=Est\u00e1 siendo editado por usted
tip.favourite-document.add=A\u00f1adir documento a favoritos
tip.favourite-document.remove=Quitar documento de favoritos
tip.favourite-folder.add=A\u00f1adir carpeta a favoritos
tip.favourite-folder.remove=Quitar carpeta de favoritos
tip.google-docs-owner=Bloqueado por usted para editar con Google Docs&trade;
tip.google-docs-locked=Bloqueado por   {0} ({1})para editar con Google Docs&trade;
tip.locked=Bloqueado por {0} ({1})
tip.lock-owner=Bloqueado por usted
tip.rules=Carpetas con reglas aplicadas
tip.simple-workflow=Flujo de trabajo sencillo de Aprobaci\u00f3n/Rechazo aplicado
tip.transferred-node=Transferido desde otro Repositorio

## Pop-up Messages
message.confirm.delete.title=Eliminar fichero
message.confirm.delete=\u00bfEst\u00e1 seguro de que quiere eliminar '{0}'?
message.delete.success={0}' fue eliminado
message.delete.failure=No se puede eliminar '{0}'
message.details.success=Detalles actualizados satisfactoriamente
message.details.failure=No se pueden actualizar los detalles
message.edit-offline.success={0}' ahora puede ser editado
message.edit-offline.success.ie7=Descargar el documento usando el bot\u00f3n de abajo.
message.edit-offline.failure=Usted no puede editar '{0}'.
message.edit-cancel.success=La edici\u00f3n de '{0}' ha sido cancelada
message.edit-cancel.failure=No se puede cancelar la edici\u00f3n de '{0}'.
message.loading=Cargando biblioteca de documentos...
message.error=No se puede acceder a la biblioteca de documentos...
message.empty=No hay elementos
message.empty.subfolders=No hay elementos. Pulse "{0}" para ver {1} subcarpeta(s) aqu\u00ed.
message.empty.subfolders.link=Mostrar carpetas
message.favourite.failure=No se puede actualizar la lista de favoritos
message.simple-workflow.approved=Elemento marcado como aprobado
message.simple-workflow.rejected=Elemento marcado como rechazado
message.simple-workflow.failure=La acci\u00f3n del flujo de trabajo no se puede completar.
message.checkout-google.inprogress={0}' se est\u00e1 subiendo a Google Docs
message.checkout-google.success={0}' se ha subido a Google Docs
message.checkout-google.failure=No se pudo subir '{0}' a Google Docs
message.checkout-google.failure=No se pudo subir '{0}' a Google Docs
message.checkin-google.inprogress={0}' se est\u00e1 descargando de Google Docs
message.checkin-google.success={0}' se ha descargado de Google Docs
message.checkin-google.failure=No se puede descargar '{0}' de  Google Docs

## File Upload (upload new version)
label.filter-description=Mismo tipo que {0}

## Edit Details Dialog
edit-details.title=Detalles de {0}
edit-details.label.edit-metadata=P\u00e1gina de edici\u00f3n completa de metadatos...

## Customize Dialog
customize.title=Personalizar
customize.header.actions=Acciones

# SIDE sort extension
label.sortby=Clasificar por: {0}
search.sort.size=Tama\u00f1o
search.sort.mimetype=Tipo MIME
search.sort.type=Tipo

## Custom Types
<%for (getAllAbstractClasses().nSort("name")){%>
### <%getFullName()%>
<%for (getSortedAttibutes()){%>
<%getQualifiedName%>=<%getLabel()%>
<%}%>
<%}%>
