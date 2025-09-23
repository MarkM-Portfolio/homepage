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

import com.ibm.lconn.hpnews.data.model.IModel;

import java.util.Date;

public class UI extends HPBaseModel implements IModel {
	
	private String uiId;
	private String personId;
	private String personLang;
	private Date lastVisit;
	private Date lastNotifyVisit;
	private boolean isWelcomeMode; // To check if the user is working in a welcome mode
	private boolean isWelcomeNote; // To check if the welcome note should be displayed
	private boolean showDisabledWidgets;
	private String organizationId;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonLang() {
		return personLang;
	}

	public void setPersonLang(String personLang) {
		this.personLang = personLang;
	}

	public String getUiId() {
		return uiId;
	}

	public void setUiId(String uiId) {
		this.uiId = uiId;
	}

	@Override
	public String getPrimaryKey() {
		return uiId;
	}

	@Override
	public void initPrimaryKey(String primaryKey) {
		this.uiId = primaryKey;
		
	}

	public boolean isWelcomeMode() {
		return isWelcomeMode;
	}

	public boolean getWelcomeMode() {
		return isWelcomeMode;
	}
	
	public void setWelcomeMode(boolean isWelcomeMode) {
		this.isWelcomeMode = isWelcomeMode;
	}
	
	public boolean isWelcomeNote() {
		return isWelcomeNote;
	}

	public boolean getWelcomeNote() {
		return isWelcomeNote;
	}
	
	public void setWelcomeNote(boolean isWelcomeNote) {
		this.isWelcomeNote = isWelcomeNote;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
	
	public Date getLastNotifyVisit() {
		return lastNotifyVisit;
	}

	public void setLastNotifyVisit(Date lastNotifyVisit) {
		this.lastNotifyVisit = lastNotifyVisit;
	}

	public boolean isShowDisabledWidgets() {
		return showDisabledWidgets;
	}

	public boolean getShowDisabledWidgets() {
		return showDisabledWidgets;
	}
	
	
	public void setShowDisabledWidgets(boolean showDisabledWidgets) {
		this.showDisabledWidgets = showDisabledWidgets;
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
