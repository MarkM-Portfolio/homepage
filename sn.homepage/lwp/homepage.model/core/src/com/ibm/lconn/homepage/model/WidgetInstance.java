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

public class WidgetInstance extends AbstractModelObject {

	private static final long serialVersionUID = -7436237937463381045L;

	private String widgetId; // The ID of the widget
	private String widgetInstanceId;
	private String tabInstanceId;
	private String widgetSetting;
	private int orderSequence;
	private String container;
	private boolean isToggled;
	private boolean isFixed;
	private String organizationId;

	public WidgetInstance() {
	}

	/**
	 * Contructor, making this an immutable object.
	 * 
	 * @param widgetId
	 * @param tabInstanceId
	 * @param widgetSetting
	 * @param rowNum
	 * @param colNum
	 * @param maxOrMin
	 * @param widgetInstanceId
	 * 
	 */
	public WidgetInstance(String widgetId, String tabInstanceId,
			String widgetSetting, int orderNumber, String containerId,
			boolean isOpen, String widgetInstanceId, String organizationId) {
		this.widgetId = widgetId;
		this.tabInstanceId = tabInstanceId;
		this.widgetSetting = widgetSetting;
		this.orderSequence = orderNumber;
		this.container = containerId;
		this.isToggled = isOpen;
		this.widgetInstanceId = widgetInstanceId;
		this.organizationId = organizationId;
	}

	public String getContainer() {
		return container;
	}

	public boolean isToggled() {
		return isToggled;
	}

	public String getTabId() {
		return tabInstanceId;
	}

	public int getOrderSequence() {
		return orderSequence;
	}

	public String getWidgetId() {
		return widgetId;
	}

	public String getWidgetSetting() {
		return widgetSetting;
	}

	public String getWidgetInstanceId() {
		return widgetInstanceId;
	}

	public String getTabInstanceId() {
		return tabInstanceId;
	}

	public void setTabInstanceId(String tabInstanceId) {
		this.tabInstanceId = tabInstanceId;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public boolean setIsFixed() {
		return isFixed;
	}

	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	public void setWidgetInstanceId(String widgetInstanceId) {
		this.widgetInstanceId = widgetInstanceId;
	}

	public void setWidgetSetting(String widgetSetting) {
		this.widgetSetting = widgetSetting;
	}

	public void setOrderSequence(int orderSequence) {
		this.orderSequence = orderSequence;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public void setToggled(boolean isOpen) {
		this.isToggled = isOpen;
	}

	public void setIsToggled(boolean isOpen) {
		this.isToggled = isOpen;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!(o instanceof WidgetInstance)) {
			return false;
		} else if (this == o) {
			return true;
		} else {
			WidgetInstance other = (WidgetInstance) o;
			if (this.widgetInstanceId.equals(other.widgetInstanceId)
					&& this.tabInstanceId.equals(other.tabInstanceId)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public int hashCode() {

		if (widgetInstanceId != null && tabInstanceId != null) {
			return widgetInstanceId.hashCode() ^ tabInstanceId.hashCode();
		} else if (widgetInstanceId != null && tabInstanceId == null) {
			return widgetInstanceId.hashCode();
		} else if (widgetInstanceId == null && tabInstanceId == null) {
			return super.hashCode();
		} else {
			return super.hashCode();
		}
	}
}
