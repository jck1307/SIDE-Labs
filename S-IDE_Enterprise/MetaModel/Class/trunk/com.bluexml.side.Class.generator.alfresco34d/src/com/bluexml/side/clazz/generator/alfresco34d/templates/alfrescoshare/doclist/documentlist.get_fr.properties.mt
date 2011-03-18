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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/documentlibrary/documentlist.get_fr.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Buttons
button.folders.show=Afficher les dossiers
button.folders.hide=Cacher les dossiers
button.view.simple=Vue simplifi\u00e9e
button.view.detailed=Vue d\u00e9taill\u00e9e

## Drop-down Menus
menu.select=S\u00e9lectionner
menu.select.all=Tous
menu.select.none=Aucun
menu.select.invert=Inverser la s\u00e9lection
menu.select.folders=Dossiers
menu.select.documents=Documents

## Document Details
details.link-to=Lier \u00e0\u00a0: {0}
details.created.on=Cr\u00e9\u00e9 le\u00a0:
details.created.by=Cr\u00e9\u00e9 par\u00a0:
details.modified.on=Modifi\u00e9 le\u00a0:
details.modified.by=Modifi\u00e9 par\u00a0:
details.editing-started.on=\u00c9dition initialis\u00e9e le\u00a0:
details.editing-started.by=\u00c9dition initialis\u00e9e par\u00a0:
details.by=Par\u00a0:
details.version=Version\u00a0:
details.size=Taille\u00a0:
details.description=Description\u00a0:
details.description.none=(Aucun)
details.comments=Commentaires\u00a0:
details.tags=Tags : 
details.tags.none=(Aucun)

## Details Banners
details.banner.editing=Ce document est verrouill\u00e9 par vous pour une \u00e9dition hors ligne.
details.banner.lock-owner=Ce document est verrouill\u00e9 par vous.
details.banner.locked=Ce document est verrouill\u00e9 par {0} pour \u00e9dition.
details.banner.google-docs.link=Google Docs
details.banner.google-docs-owner=Ce document a \u00e9t\u00e9 ajout\u00e9 par vous \u00e0 {0} pour \u00e9dition.
details.banner.google-docs-locked=Ce document a \u00e9t\u00e9 ajout\u00e9 par {0} \u00e0 {1} pour \u00e9dition.

## Actions - moved to slingshot.properties
actions.more=Autres...

## Tips
tip.active-workflow=Associ\u00e9 \u00e0 {0} workflows en cours
tip.editing=Modifi\u00e9 par vous
tip.favourite-document.add=Ajouter le document aux favoris
tip.favourite-document.remove=Supprimer le document des favoris
tip.favourite-folder.add=Ajouter le dossier aux favoris
tip.favourite-folder.remove=Supprimer le dossier des favoris
tip.google-docs-owner=Verrouill\u00e9 par vous pour \u00e9dition \u00e0 l'aide de Google Docs&trade;
tip.google-docs-locked=Verrouill\u00e9 par {0} ({1}) pour \u00e9dition \u00e0 l''aide de Google Docs&trade;
tip.locked=Verrouill\u00e9 par {0} ({1})
tip.lock-owner=Verrouill\u00e9 par vous
tip.rules=Des r\u00e8gles sont appliqu\u00e9es au dossier
tip.simple-workflow=Approbation/Rejet simple du workflow appliqu\u00e9
tip.transferred-node=Transf\u00e9r\u00e9 depuis un autre entrep\u00f4t

## Pop-up Messages
message.confirm.delete.title=Supprimer le fichier
message.confirm.delete=\u00cates-vous s\u00fbr de vouloir supprimer ''{0}''\u00a0?
message.delete.success=''{0}'' a \u00e9t\u00e9 supprim\u00e9
message.delete.failure=Impossible de supprimer ''{0}''
message.details.success=Succ\u00e8s de la mise \u00e0 jour des d\u00e9tails
message.details.failure=\u00c9chec de la mise \u00e0 jour des d\u00e9tails
message.edit-offline.success=''{0}'' peut d\u00e9sormais \u00eatre modifi\u00e9
message.edit-offline.success.ie7=T\u00e9l\u00e9charger le document en cliquant sur le bouton ci-dessous.
message.edit-offline.failure=Vous ne pouvez pas modifier ''{0}''.
message.edit-cancel.success=L''\u00e9dition de ''{0}'' a \u00e9t\u00e9 annul\u00e9e
message.edit-cancel.failure=\u00c9chec de l''annulation de l''\u00e9dition de ''{0}''.
message.loading=Chargement de l'espace documentaire\u0085
message.error=\u00c9chec de l'acc\u00e8s \u00e0 l'espace documentaire
message.empty=Aucun \u00e9l\u00e9ment
message.empty.subfolders=Aucun \u00e9l\u00e9ment. Cliquer sur "{0}" pour voir {1}\u00a0sous-dossier(s) ici.
message.empty.subfolders.link=Afficher les dossiers
message.favourite.failure=\u00c9chec de la mise \u00e0 jour de la liste des favoris
message.simple-workflow.approved=\u00c9l\u00e9ment marqu\u00e9 comme approuv\u00e9
message.simple-workflow.rejected=\u00c9l\u00e9ment marqu\u00e9 comme rejet\u00e9
message.simple-workflow.failure=Echec de l'action du workflow.
message.checkout-google.inprogress=La copie de travail de ''{0}'' est en cours d'extraction vers Google Docs
message.checkout-google.success=La copie de travail de ''{0}'' a \u00e9t\u00e9 extraite dans Google Docs
message.checkout-google.failure=Impossible d''extraire une copie de travail de ''{0}'' vers Google Docs
message.checkout-google.failure=Impossible d''extraire une copie de travail de ''{0}'' vers Google Docs
message.checkin-google.inprogress=La copie de travail de ''{0}'' est en cours d''archivage depuis Google Docs
message.checkin-google.success=La copie de travail de ''{0}'' a \u00e9t\u00e9 archiv\u00e9e depuis Google Docs
message.checkin-google.failure=Impossible d''archiver la copie de travail de ''{0}'' depuis Google Docs

## File Upload (upload new version)
label.filter-description=Type identique \u00e0 {0}

## Edit Details Dialog
edit-details.title=D\u00e9tails pour {0}
edit-details.label.edit-metadata=Page d'\u00e9dition de l'ensemble des m\u00e9tadonn\u00e9es...

## Customize Dialog
customize.title=Personnaliser
customize.header.actions=Actions

# SIDE sort extension
label.sortby=Trier par\u00a0: {0}
search.sort.size=Taille
search.sort.mimetype=Type\u00a0MIME
search.sort.type=Type

## Custom Types
<%for (getAllAbstractClasses().nSort("name")){%>
### <%getFullName()%>
<%for (getSortedAttibutes()){%>
<%getQualifiedName%>=<%getLabel()%>
<%}%>
<%}%>
