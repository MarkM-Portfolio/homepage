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

public class WidgetInst extends HPBaseModel implements IModel {
	
//	CREATE TABLE HOMEPAGE.HP_WIDGET_INST  (
//			WIDGET_INST_ID VARCHAR(36) NOT NULL,
//			WIDGET_ID VARCHAR(36) NOT NULL,
//			TAB_INSTANCE_ID VARCHAR(36) NOT NULL,
//			WIDGET_SETTING VARCHAR(2048),
//			CONTAINER VARCHAR(36),
//			ORDER_SEQUENCE SMALLINT,
//			IS_FIXED SMALLINT,
//			IS_TOGGLED SMALLINT,
//			LAST_MODIFIED TIMESTAMP NOT NULL,
//			LAST_UPDATED TIMESTAMP
//		);


	private String widgetInstId;
	private String widgetId;
	private String tabInstanceId;
	private String widgetSetting;
	private String container;
	private int orderSequence;
	private boolean isFixed;
	private boolean isToggled;
	private Date lastModified;
	private Date lastUpdated;
	private String organizationId;
	
	
	@Override
	public String getPrimaryKey() {
		return widgetInstId;
		
	}
	@Override
	public void initPrimaryKey(String primaryKey) {
		this.widgetInstId = primaryKey;		
	}
	
	public String getContainer() {
		return container;
	}
	
	public void setContainer(String container) {
		this.container = container;
	}
	
	public boolean getIsFixed() {
		return isFixed;
	}
	
	public void setIsFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}
	
	public boolean getIsToggled() {
		return isToggled;
	}
	
	public void setIsToggled(boolean isToggled) {
		this.isToggled = isToggled;
	}
	
	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public int getOrderSequence() {
		return orderSequence;
	}
	
	public void setOrderSequence(int orderSequence) {
		this.orderSequence = orderSequence;
	}
	
	public String getTabInstanceId() {
		return tabInstanceId;
	}
	
	public void setTabInstanceId(String tabInstanceId) {
		this.tabInstanceId = tabInstanceId;
	}
	
	public String getWidgetId() {
		return widgetId;
	}
	
	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}
	
	public String getWidgetInstId() {
		return widgetInstId;
	}
	
	public void setWidgetInstId(String widgetInstId) {
		this.widgetInstId = widgetInstId;
	}
	
	public String getWidgetSetting() {
		return widgetSetting;
	}
	
	public void setWidgetSetting(String widgetSetting) {
		this.widgetSetting = widgetSetting;
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
