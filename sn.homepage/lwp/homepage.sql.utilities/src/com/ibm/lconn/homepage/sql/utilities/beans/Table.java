/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.sql.utilities.beans;

import java.util.ArrayList;

public class Table {

	private String fullTableName;
	private ArrayList<Column> columns;
	private String pk;
	private String tableName;
	
	public String getPk() {
		return pk;
	}

	public String getFullTableName() {
		return fullTableName;
	}
	public void setFullTableName(String tableName) {
		this.fullTableName = tableName;
	}
	public ArrayList<Column> getColumns() {
		return columns;
	}
	public void setColumns(ArrayList<Column> columns) {
		this.columns = columns;
	}
	public void setPk(String columnName) {
		this.pk=columnName;		
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "Table [fullTableName=" + fullTableName + ", columns=" + columns
				+ ", pk=" + pk + ", tableName=" + tableName + "]";
	}
	
	
}
