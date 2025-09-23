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

package com.ibm.lconn.homepage.services.widget;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetCategory;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ServiceException;

public interface IWidgetServices {

	/**
	 * 
	 * @param widgetId
	 * @param checkDisabled
	 * @return
	 * @throws IServiceException
	 */
	public Widget getWidget(String widgetId, boolean checkDisabled)
			throws ServiceException;

	/**
	 * 
	 * @param widgetId
	 * @param locale
	 * @param isDisabled
	 * @return
	 * @throws IServiceException
	 */
	public Widget getWidget(String widgetId, Locale locale, boolean isDisabled)
			throws ServiceException;

	/**
	 * 
	 * @param widgetId
	 * @param checkDisabled
	 * @return
	 * @throws ServiceException
	 */
	public Widget getWidgetNonLocalized(String widgetId, boolean checkDisabled)
		throws ServiceException;
	
	/**
	 * 
	 * @param widgetId
	 * @return
	 * @throws IServiceException
	 */
	public boolean checkFeatureInstalledByWidgetId(String widgetId)
			throws ServiceException;

	/**
	 * 
	 * @param locale
	 * @return
	 * @throws ServiceException
	 */
	public List<Widget> getAllWidgets(Locale locale) throws ServiceException;
	
	/**
	 * 
	 * @param tabInstanceId
	 * @param locale
	 * @param checkDisabled
	 * @return
	 * @throws IServiceException
	 */
	public List<Widget> getAvailableWidgetsByTabInstanceId(
			String tabInstanceId, Locale locale, boolean includeDisabledWidgets)
			throws ServiceException;

	/**
	 * 
	 * @param tabInstanceId
	 * @param includeDisabledWidgets
	 * @return
	 * @throws IServiceException
	 */
	public List<Widget> getAvailableWidgetsByTabInstanceId(
			String tabInstanceId, boolean includeDisabledWidgets)
			throws ServiceException;

	/**
	 * Get an ordered list of all of the sharebox gadgets
	 * @param locale The locale to use for localizing the widgets
	 * @param includeDisabledWidget Indicate if all widgets are needed including disabled widgets
	 * @return
	 * @throws WidgetServiceException
	 */
	public List<Widget> getOrderedShareboxWidgets(Locale locale, boolean includeDisabledWidget) 
		throws ServiceException;
	
	/**
	 * 
	 * @param tabInstanceId
	 * @return
	 * @throws IServiceException
	 */
	public List<WidgetInstance> getWidgetInstancesByTabInstanceId(
			String tabInstanceId) throws ServiceException;

	/**
	 * 
	 * @return
	 * @throws IServiceException
	 */
	public Map<String, WidgetCategory> getAvailableWidgetsTree(
			String tabInstanceId, Locale locale, boolean includeDisabledWidgets)
			throws ServiceException;

	/**
	 * 
	 * @return
	 * @throws IServiceException
	 */
	public Map<String, WidgetCategory> getAvailableWidgetsTree(
			String tabInstanceId, boolean includeDisabledWidgets)
			throws ServiceException;
	
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
	public String createWidgetInstance(String widgetId, String tabInstanceId,
			int order, String containerId, boolean isToggled,
			String widgetSetting, String widgetInstanceId, String organizationId)
			throws ServiceException;

	/**
	 * 
	 * @param widgetInstanceId
	 * @return
	 * @throws ServiceException
	 */
	public WidgetInstance getWidgetInstance(String widgetInstanceId) throws ServiceException;
	
	/**
	 * 
	 * @param widgetId
	 * @param tabInstanceId
	 * @return
	 * @throws ServiceException
	 */
	public List<WidgetInstance> getWidgetInstances(String widgetId, String tabInstanceId) throws ServiceException;
	
	/**
	 * 
	 * @param tabInstanceId
	 * @param includeDisabledWidgets
	 * @return
	 * @throws ServiceException
	 */
	public List<WidgetInstance> createDefaultWidgetInstancesSetForTabInstanceId(
			String tabInstanceId, boolean includeDisabledWidgets)
			throws ServiceException;
	
	/**
	 * 
	 * @param tabInstanceId
	 * @return
	 * @throws ServiceException
	 */
	public List<WidgetInstance> getWidgetsInstancesForTabInstance(String tabInstanceId) throws ServiceException;
	
	/**
	 * 
	 * @param pk
	 * @param widgetSetting
	 * @throws ServiceException
	 */
	public void changeWidgetSetting(String pk, String widgetSetting) throws ServiceException;
	
	/**
	 * 
	 * @param pk
	 * @param row
	 * @param col
	 * @param pageID
	 * @throws ServiceException
	 */
	public void changePosition(String pk, int row, int col, String pageID) throws ServiceException;
	
	/**
	 * 
	 * @param pk
	 * @throws ServiceException
	 */
	public void deleteWidgetInstance(String pk) throws ServiceException;
	
	/**
	 * Disables a widget in the catalog. A disabled widget is stored and
	 * available for administrators, but it is not served by the
	 * ICatalogWidgetInfoService to end users.
	 * 
	 * @param widgetID
	 * @throws ServiceException
	 */
	public void disableWidget(String widgetID) throws ServiceException;
	
	/**
	 * Enables a widget in the catalog. An enabled widget is available to both
	 * administrators and end users.
	 * 
	 * @param widgetID
	 *            ID for widget to be enabled
	 * @throws ServiceException
	 *             if widget could not be enabled
	 */
	public void enableWidget(String widgetID) throws ServiceException;
	
	/**
	 * Removes an existing widget from the catalog. If it is a system widget, it
	 * cannot be removed.
	 * 
	 * @param widgetID
	 *            ID for widget to be removed
	 * @throws ServiceException
	 *             if widget could not be removed from the catalog
	 */
	public void removeWidget(String widgetID)throws ServiceException;
	
	/**
	 * Adds a new widget to the catalog. The caller has to fill an instance of
	 * the DashboardWidget class with all needed properties, except the ID.
	 * 
	 * @see DashboardWidget
	 * @param dashboardWidget
	 *            properties for the new widget
	 * @throws ServiceException
	 *             if the widget could not be added to the catalog
	 */

	public String addWidget(Widget widget) throws ServiceException;

	public void updateWidget(Widget editedWidget) throws ServiceException;
	
	/**
	 * Update a widget and place it after a particular widget
	 * @param editedWidget
	 * @param orderAfterShareWidgetId
	 * @throws ServiceException
	 */
	public void updateWidgetShareOrder(String widgetId, String orderAfterWidgetId) 
		throws ServiceException;

	/**
	 * Gets a map with all disabled widgets.
	 *
	 * @param locale User's locale for widget titles
	 * @return map with all disabled widgets
	 * @throws ServiceException
	 *             if map could not be retrieved
	 */
	public Map<String, Widget> getDisabledWidgetsForAdmin(Locale locale) throws ServiceException;

	/**
	 * Gets a map with all enabled widgets.
	 *
	 * @param locale User's locale for widget titles
	 * @return Map with enabled widgets
	 * @throws ServiceException
	 *             if map could not be retrieved
	 */
	public Map<String, Widget> getEnabledWidgetsForAdminPage(Locale locale) throws ServiceException;

}
