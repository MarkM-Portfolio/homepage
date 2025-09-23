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

import com.ibm.lconn.homepage.model.WidgetInstance;

public interface IUIServices {

	/**
	 * Important note: we let the calling code set the WidgetInstanceId here,
	 * which is the pk in the DB the widgetinstanceId. This is used to let the
	 * client-side code pick an UUID without have to send an ajax request. There
	 * is a very very small chance that the picked random id already exists in
	 * the DB. We let the insert fails here, in worse case, the new widget would
	 * no be persisted which is a small drawback compared to the performance
	 * gain of not used an ajax request to get an UUID from the server.
	 * 
	 * @param widgetId
	 * @param tabInstanceId
	 * @param order
	 * @param containerId
	 * @param isToggled
	 * @param widgetSetting
	 * @param widgetInstanceId
	 * @return
	 * @throws DboardInfraException
	 */
	public String createWidgetInstance(String widgetId,String tabInstanceId, int order, String containerId, boolean isToggled, String widgetSetting, String widgetInstanceId, String organizationId);
	
	// getUserWidgetInstance
	public WidgetInstance getWidgetInstance(String widgetInstanceId) throws ServiceException;

	public List<WidgetInstance> findWidgetInstances(String widgetId, String tabInstanceId) throws ServiceException;

	/**
	 * Returns the list of widget instance preferences for the given tab
	 * instance. Each object in the list is a <code>WidgetInst</code> which is a
	 * data model object containing details of the widget and it's positioning
	 * on the page for the user in question.
	 * 
	 * @param tabInstanceId
	 *            Tab instance id. A tab instance is user specific
	 * 
	 * @return the list of widget instances objects
	 * @throws ServiceException 
	 * 
	 * @throws DboardInfraException
	 */
	public List<WidgetInstance> findWidgetsInstancesForTabInstance(String tabInstanceId) throws ServiceException;

	public void changeWidgetSetting(String pk, String widgetSetting);

	public void changePosition(String pk, int row, int col, String pageID);

	public void deleteWidgetInstance(String pk);
}
