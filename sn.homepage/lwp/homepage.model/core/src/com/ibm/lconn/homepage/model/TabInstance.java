/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.model;

import java.util.Date;

public class TabInstance {	
	private String tabInstId;
	private String tabId;
	private String uiId;
	private String tabName;
	private int numColumns;
	private Date lastModified;
	private boolean enabled;
	private String organizationId;
	
	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int columns) {
		numColumns = columns;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String pageId) {
		this.tabId = pageId;
	}

	public String getUiId() {
		return uiId;
	}

	public void setUiId(String uiId) {
		this.uiId = uiId;
	}

	public String getTabInstId() {
		return tabInstId;
	}

	public void setTabInstId(String uiPageId) {
		this.tabInstId = uiPageId;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
}
