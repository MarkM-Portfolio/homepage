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

public class ForeignKey {

	private String foreignKey;
	private ArrayList<String> columns;
	private String tabName;
	private String constName;
	private String refTabName;
	private String fkColName;
	private String primKeyName;
	private String deleteRule;
	
	public String getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}
	public ArrayList<String> getColumns() {
		return columns;
	}
	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	
	public String getConstName() {
		return constName;
	}
	public void setConstName(String constName) {
		this.constName = constName;
	}
	public String getRefTabName() {
		return refTabName;
	}
	public void setRefTabName(String refTabName) {
		this.refTabName = refTabName;
	}
	
	public String getFKColName() {
		return fkColName;
	}
	public void setFKColName(String fkColName) {
		this.fkColName = fkColName;
	}
	
	public String getPrimKeyName() {
		return primKeyName;
	}
	public void setPrimKeyName(String primKeyName) {
		this.primKeyName = primKeyName;
	}
	public String getFkColName() {
		return fkColName;
	}
	public void setFkColName(String fkColName) {
		this.fkColName = fkColName;
	}
	public String getDeleteRule() {
		return deleteRule;
	}
	public void setDeleteRule(String deleteRule) {
		this.deleteRule = deleteRule;
	}
	@Override
	public String toString() {
		return "ForeignKey [foreignKey=" + foreignKey + ", columns=" + columns
				+ ", tabName=" + tabName + ", constName=" + constName
				+ ", refTabName=" + refTabName + ", fkColName=" + fkColName
				+ ", primKeyName=" + primKeyName + ", deleteRule=" + deleteRule
				+ "]";
	}
	

	
	
	
}
