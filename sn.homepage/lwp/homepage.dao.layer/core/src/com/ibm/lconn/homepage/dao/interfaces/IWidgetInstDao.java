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

package com.ibm.lconn.homepage.dao.interfaces;


import java.util.List;

import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;;



public interface IWidgetInstDao extends ICrud  {

	/**
	 * Find all the widget instances for a speciefed user linked to a panalName (DEFAULT)
	 * 
	 * @param personId
	 * @param defaultPanelName
	 * @return
	 * @throws DataAccessRetrieveException
	 */
	public List<WidgetInst> findWidgetsInstancesByPersonId(String personId, String defaultPanelName);

	/**
	 * Get the widget instances referenced by a widgetId for a speciefed user into the defaultPanel
	 * The same widget can have several instances. It exist just one widget instance for person with a widgetId
	 * 
	 * @param personId
	 * @param defaultPanel
	 * @param widgetId
	 * @return
	 * @throws DataAccessRetrieveException
	 */
	public List<WidgetInst> getWidgetInstancesByTabInstanceId(String tabInstanceId, String widgetId);
	
	
	/**
	 * Retrieve the personId by the widgetInstanceId
	 * 
	 * @param widgetInstanceId
	 * @return
	 * @throws DataAccessRetrieveException
	 */
	public String getPersonIdByWidgetInstanceId(String widgetInstanceId);
	
	
	/**
	 * Retrieve all the open widgets. Returns total number of widgets open (deployed on a page)
	 * 
	 * @return the number of open widgts
	 * @throws DataAccessRetrieveException
	 */
	public long getCountOpenWidgets();
	
	/**
	 * Delete all entries with a specific widget ID.
	 * 
	 * @param widgetId ID for a widget to remove entries
	 * 
	 * @throws DataAccessRetrieveException
	 */
	public int removeByWidgetId(String widgetId);
	
	/**
	 * Delete a set of widgets by IDs 
	 * @param instanceId
	 * @return
	 * 
	 * @throws DataAccessRetrieveException
	 */
	public int removeByInstanceIds(List<String> instanceId);
	
	
	/**
	 * 
	 * Retrieve all the open widgets that are releted to a tabInstId
	 * 
	 * @return the number of open widgts
	 * @throws DataAccessRetrieveException
	 */
	public List<WidgetInst> findWidgetsInstancesByTabInstance(String tabInstId);

	// Needed in interface to allow unit tests run
	public void filterDeleted(List<WidgetInst> widgetsInstances);
	
	public void setDeletedWidgetChecker(IDeletedWidgetChecker deletedWidgetChecker);
	
	public IDeletedWidgetChecker getDeletedWidgetChecker();
}
