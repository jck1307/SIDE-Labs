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


package com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public abstract class AbstractRenamingStrategy {

	protected Logger logger = Logger.getLogger(getClass());
	private DataSource dataSource;
	private Integer maxTableNameLength = -1;
	private Integer maxColumnNameLength = -1;
	
	public abstract String renameTable(String tableName);
		
	private void checkMetaData() {
		logger.debug("Checking meta-data for retrieving max length of table names");
		DatabaseMetaData dmd = null;
		
		try {
			Connection connection = dataSource.getConnection();
			dmd = connection.getMetaData();			
			
			maxTableNameLength = dmd.getMaxTableNameLength();
			if (logger.isDebugEnabled())
				logger.debug("Support max table name length = " + maxTableNameLength);

			maxColumnNameLength = dmd.getMaxColumnNameLength();
			if (logger.isDebugEnabled())
				logger.debug("Support max column name length = " + maxColumnNameLength);
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Spring IOC/DI material
	 */
	
	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
		checkMetaData();
		if (maxTableNameLength <= 0) {
			logger.warn("Cannot set the max table name length from synchronization database meta-data, this value has to be set manually");
		}
		if (maxColumnNameLength <= 0) {
			logger.warn("Cannot set the max column name length from synchronization database meta-data, this value has to be set manually");
		}
	}
	
	public Integer getMaxTableNameLength() {
		return maxTableNameLength;
	}	
	public void setMaxTableNameLength(Integer length_) {
		maxTableNameLength = length_;
	}
	
	public Integer getMaxColumnNameLength() {
		return maxColumnNameLength;
	}
	public void setMaxColumnNameLength(Integer length_) {
		maxColumnNameLength = length_;
	}
}

