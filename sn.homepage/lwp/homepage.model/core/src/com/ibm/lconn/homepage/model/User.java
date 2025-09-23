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

public class User extends AbstractModelObject {

	private static final long serialVersionUID = 6618462622670235629L;

	private String id;
	private String orgId;
	private String extOrgId;
	private String mail;
	private String externalId;
	private String displayname;
	private String mailLower;
	private Boolean boardEnabled;
	private boolean isExternal;
	
	public String getMailLower() {
		return mailLower;
	}

	public void setMailLower(String mailLower) {
		this.mailLower = mailLower;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayName) {
		this.displayname = displayName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getOrgId() {
		return orgId;
	}
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getExternalOrgId() {
		return extOrgId;
	}
	
	public void setExternalOrgId(String extOrgId) {
		this.extOrgId = extOrgId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPrimaryKey() {
		return getId();
	}

	public void initPrimaryKey(String primaryKey) {
		this.id = primaryKey;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public boolean equals(Object o) {
		if(o ==null) {
			return false;
		}
		else if (! (o instanceof User)) {
			return false;
		} else if(this == o) {
			return true;
		} else {
			User other = (User) o;
			if(this.id.equals(other.id) &&
			   this.externalId.equals(other.externalId)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public int hashCode() {
		if(id!=null && externalId != null)
			return id.hashCode() ^ externalId.hashCode();
		else
			return super.hashCode();
	}

	public void setBoardEnabled(Boolean boardEnabled) {
		this.boardEnabled = boardEnabled;
	}

	public Boolean isBoardEnabled() {
		return boardEnabled;
	}

	public boolean getIsExternal() {
		return isExternal;
	}

	public void setIsExternal(boolean isExternal) {
		this.isExternal = isExternal;
	}
	
	
}
