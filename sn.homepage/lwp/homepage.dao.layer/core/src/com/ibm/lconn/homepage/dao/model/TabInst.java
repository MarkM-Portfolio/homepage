/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.dao.model;

import java.util.Date;

import com.ibm.lconn.hpnews.data.model.IModel;

/**
 * CREATE TABLE HOMEPAGE.HP_TAB_INST  (
	TAB_INST_ID VARCHAR(36) NOT NULL,
	TAB_ID VARCHAR(36) NOT NULL,
	UI_ID VARCHAR(36) NOT NULL,
	TAB_NAME VARCHAR(36),
	N_COLUMNS SMALLINT,
	LAST_MODIFIED TIMESTAMP NOT NULL
);
 * @author Lorenzo Notarfonzo
 *
 */
public class TabInst extends HPBaseModel implements IModel {
	
	private String tabInstId;
	private String tabId;
	private String uiId;
	private String tabName;
	private int numColumns;
	private Date lastModified;
	/* Join with HP_TAB Table */
	private boolean enabled;
	private String organizationId;
	
	@Override
	public String getPrimaryKey() {
		return tabInstId;
	}
	
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

	@Override
	public void initPrimaryKey(String primaryKey) {
		tabInstId = primaryKey;		
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

	@Override
	public IModel cloneModel() {
		// Intentionally left blank
		return null;
	}

	@Override
	public String getTABLENAME() {
		// Intentionally left blank
		return null;
	}

	@Override
	public String getDbVendor() {
		// Intentionally left blank
		return null;
	}

	@Override
	public void setDbVendor(String dbVendor) {
		// Intentionally left blank
		
	}
	
}
