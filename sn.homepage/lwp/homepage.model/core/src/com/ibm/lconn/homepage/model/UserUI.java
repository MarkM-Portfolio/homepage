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

package com.ibm.lconn.homepage.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class UserUI extends AbstractModelObject {
	
	private static final long serialVersionUID = -6320780985305533679L;
	private String uiId;
	private String personId;
	private String personLang;
	private Date lastVisit;
	private String organizationId;
	
	private boolean isWelcomeMode; // To check if the user is working in a welcome mode
	private boolean isWelcomeNote; // To check if the welcome note should be displayed
	private boolean showDisabledWidgets;
	
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

	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		else if( !(o instanceof UserUI) ) {
			return false;
		}
		else if(this == o) {
			return true;
		} else {
			UserUI other = (UserUI) o;
			if(this.uiId.equals(other.uiId) &&
			   this.personId.equals(other.personId)) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public int hashCode() {
		return StringUtils.defaultString(uiId).hashCode() ^ StringUtils.defaultString(personId).hashCode();
	}
	
}
