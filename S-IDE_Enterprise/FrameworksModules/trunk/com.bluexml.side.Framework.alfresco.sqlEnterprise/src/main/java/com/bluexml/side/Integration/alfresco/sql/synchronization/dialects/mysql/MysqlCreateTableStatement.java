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


package com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.mysql;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.CreateTableStatement;

public class MysqlCreateTableStatement extends CreateTableStatement {

	public static class Builder extends CreateTableStatement.Builder {

		public Builder(String tableName_) {
			super(tableName_);
		}
		
		@Override
		public CreateTableStatement build() {
			return new MysqlCreateTableStatement(this);
		}
	}
	
	private MysqlCreateTableStatement(Builder builder_) {
		super(builder_);
	}
	
	@Override
	public String toSQLString() {
		return super.toSQLString() + " TYPE = InnoDB";
	}
}
