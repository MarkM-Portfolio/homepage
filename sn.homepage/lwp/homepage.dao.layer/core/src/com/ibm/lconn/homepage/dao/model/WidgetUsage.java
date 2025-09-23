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

package com.ibm.lconn.homepage.dao.model;

import com.ibm.lconn.hpnews.data.model.IModel;

public class WidgetUsage extends HPBaseModel implements IModel {
	
	private static final long serialVersionUID = -160400158241439163L;

	private String widgetId;

	private String title;

	private long nbUsers;

	public String getWidgetId() {
		return widgetId;
	}

	public void setWidgetId(String id) {
		this.widgetId = id;
	}

	public long getNbUsers() {
		return nbUsers;
	}

	public void setNbUsers(long nbUsers) {
		this.nbUsers = nbUsers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getPrimaryKey() {
		return widgetId;
	}

	@Override
	public void initPrimaryKey(String primaryKey) {
		widgetId = primaryKey;
		
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
