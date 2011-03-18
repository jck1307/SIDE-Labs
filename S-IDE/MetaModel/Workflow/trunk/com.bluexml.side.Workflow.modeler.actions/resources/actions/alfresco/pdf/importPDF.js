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


parameter.action=IMPORT_FILLED_PDF;
parameter.pdf.path=/app:company_home/cm:pdf/cm:filled_pdf.pdf;
parameter.output.path=/app:company_home/sys:pdf/cm:mynewcontent;
parameter.output.type=bx:com_bluexml_side_example_infoperson;
field.person_name=name;
field.person_phone=phonenumber;
field.address_address1=address->address1;
field.address_address2=address->address2;
field.address_city=address->city->cityname;
field.address_zipCode=address->city->zip;
