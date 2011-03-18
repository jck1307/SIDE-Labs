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


<div class="dashlet">
  <div class="title">${msg("languagePicker.title")}</div>
  <div>
  	<a class="languagePickerLink" onclick="javascript:changeLanguage('fr_FR');">
  		<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/fr_FR.png"/>
			${msg("languagePicker.french")}
	  	</div>
  	</a>
  	<a class="languagePickerLink" onclick="javascript:changeLanguage('en_US');">
		<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/en_US.png"/>
			${msg("languagePicker.english")}
		</div>
	</a>
	<!--
	============= This is a language item ================
	<a class="languagePickerLink" onclick="javascript:changeLanguage('es_ES');">
	  	<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/es_ES.png"/>
			${msg("languagePicker.spanish")}
		</div>
	</a>	
	
	============= This is a language item ================
	<a class="languagePickerLink" onclick="javascript:changeLanguage('de_DE');">
	  	<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/de_DE.png"/>
			${msg("languagePicker.german")}
		</div>
	</a>
	-->
  </div>
</div>
