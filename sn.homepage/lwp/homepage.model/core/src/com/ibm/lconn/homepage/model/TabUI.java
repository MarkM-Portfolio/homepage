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

import java.util.List;

public class TabUI {
	
	private String tabInstanceId;
	private int numberColumns;
	private List<WidgetInstance> widgetInstances;
	
	public int getNumberColumns() {
		return numberColumns;
	}

	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}

	public String getTabInstanceId() {
		return tabInstanceId;
	}

	public void setTabInstanceId(String tabId) {
		this.tabInstanceId = tabId;
	}

	public List<WidgetInstance> getWidgetInstances() {
		return widgetInstances;
	}

	public void setWidgetInstances(List<WidgetInstance> widgetInstances) {
		this.widgetInstances = widgetInstances;
	}

}
