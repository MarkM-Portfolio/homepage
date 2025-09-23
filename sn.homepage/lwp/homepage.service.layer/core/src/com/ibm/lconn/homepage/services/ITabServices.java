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

package com.ibm.lconn.homepage.services;

import java.util.List;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetTab;
import com.ibm.lconn.homepage.model.TabInstance;

public interface ITabServices {

	// public TabConfiguration getTabConfigurationById(String tabId) throws
	// ServiceException;
	// public TabConfiguration getTabConfigurationById(String tabId, Locale
	// locale, boolean checkDisabled) throws ServiceException;

	public TabInstance getTabInstance(String tabInstanceId) throws ServiceException;

	public String createTabInstance(String tabName, String personId) throws ServiceException;
	
	public String getTabInstanceId(String personId, TabType tabType) throws ServiceException;
	
	public void updateNumberColumn(String tabInstanceId, int numberColumn) throws ServiceException;
	
	public String createCustomTabInstance(String tabName, String personId) throws ServiceException;
	
	public int getNumberOfColumns(String tabInstanceId)	throws ServiceException;
	
	public List<TabInstance> getAllTabInstancesForPerson(String personId) throws ServiceException;
	
	public boolean isTabEnabled(String tabName) throws ServiceException;
	
	public void enableTab(String tabName) throws ServiceException;
	
	public void disableTab(String tabName) throws ServiceException;
	
	/**
	 * Get the widget to tab mapping for a particular tabId
	 * 
	 * @param tabId
	 * @return
	 * @throws WidgetServiceException 
	 */
	public List<WidgetTab> getWidgetTabsByTabId(String tabId) throws ServiceException;
	
	/**
	 * Get the set of widgets-to-tab mappings for a widget id.
	 * 
	 * @param widgetId
	 * @return
	 * @throws WidgetServiceException
	 */
	public List<WidgetTab> getWidgetTabsByWidgetId(String widgetId) throws ServiceException;
	
	
}
