/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.widget.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.SEVERE;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.lconn.core.services.cre.remote.widget.DuplicateGadgetUrlException;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetCategory;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetTab;
import com.ibm.ventura.internal.config.exception.VenturaConfigHelperException;
import com.ibm.lconn.homepage.dao.interfaces.IWidgetInstDao;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.model.Category;
import com.ibm.lconn.homepage.model.TabInstance;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.impl.AbstractService;
import com.ibm.lconn.homepage.services.utils.ModelConverter;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.lconn.homepage.utils.ResourceBundle;

public class WidgetServicesImpl extends AbstractService implements IWidgetServices {

	private static String CLASS_NAME = WidgetServicesImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Autowired
	private ResourceBundle catalogServiceUIResourceBundle;
	@Autowired
	private ResourceBundle webResourceBundle;
	
	@Autowired
	private ITabServices tabServices;

	@Autowired
	private IWidgetInstDao widgetInstDao;
	
	@Autowired
	@Qualifier("widgetCatalogServices")
	private WidgetCatalogServiceRemote catalogService;

	@Autowired
	private ModelConverter modelConverter;

	public ModelConverter getModelConverter() {
		return modelConverter;
	}

	public void setModelConverter(ModelConverter modelConverter) {
		this.modelConverter = modelConverter;
	}

	public IWidgetInstDao getWidgetInstDao() {
		return widgetInstDao;
	}

	public void setWidgetInstDao(IWidgetInstDao widgetInstDao) {
		this.widgetInstDao = widgetInstDao;
	}

	protected ResourceBundle getCatalogServiceUIResourceBundle() {
		return catalogServiceUIResourceBundle;
	}

	public void setCatalogServiceUIResourceBundle(ResourceBundle catalogServiceUIResourceBundle) {
		this.catalogServiceUIResourceBundle = catalogServiceUIResourceBundle;
	}

	protected ITabServices getTabServices() {
		return tabServices;
	}

	public void setTabServices(ITabServices tabServices) {
		this.tabServices = tabServices;
	}	

	protected void internalAfterPropertiesSet() throws Exception {
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<com.ibm.lconn.core.services.cre.remote.widget.model.Widget> getAvailableWidgetsByTabInstanceId(
			String tabInstanceId, boolean includeDisabledWidgets)
			throws ServiceException {
		return getAvailableWidgetsByTabInstanceId(tabInstanceId, Locale
				.getDefault(), includeDisabledWidgets);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<com.ibm.lconn.core.services.cre.remote.widget.model.Widget> getAvailableWidgetsByTabInstanceId(String tabInstanceId, Locale locale, boolean includeDisabledWidgets)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getAvailableWidgetBytabInstanceId",new Object[] { tabInstanceId, locale, includeDisabledWidgets });

		List<com.ibm.lconn.core.services.cre.remote.widget.model.Widget> widgetsMap = new ArrayList<com.ibm.lconn.core.services.cre.remote.widget.model.Widget>();

		try {
			// Get all enabled widgets from DAO

			TabInstance tabInst = tabServices.getTabInstance(tabInstanceId);
			
			if(tabInst == null){
				if (logger.isLoggable(SEVERE)){
					logger.logp(SEVERE, CLASS_NAME,"getAvailableWidgetBytabInstanceId", "Could not find TabInstance with tabInstId of {0}", tabInstanceId);
				}
				
				return widgetsMap;
			}
			
			if (logger.isLoggable(FINER))
				logger.logp(FINER, CLASS_NAME, "getAvailableWidgetBytabInstanceId", "TabInstance tabId: {0}, tabInstId: {1}", new Object[] {tabInst.getTabId(), tabInst.getTabInstId()});
			
			List<WidgetTab> widgetTabs = tabServices.getWidgetTabsByTabId(tabInst.getTabId());
			
			if (logger.isLoggable(FINER))
				logger.logp(FINER, CLASS_NAME, "getAvailableWidgetBytabInstanceId", "WidgetTabs: {0}, size: {1}", new Object[]{ widgetTabs, widgetTabs!=null?widgetTabs.size():0});
			
			for (WidgetTab widgetTab : widgetTabs) {
				String widgetId = widgetTab.getWidgetId();

				com.ibm.lconn.core.services.cre.remote.widget.model.Widget widget = getWidget(widgetId, locale, includeDisabledWidgets);
				
				if (widget != null) {
					if (logger.isLoggable(FINEST))
						logger.logp(FINEST, CLASS_NAME,"getAvailableWidgetsByTabInstanceId", "Adding widget: "+widget);
					widgetsMap.add(widget);
				}
			}
			

		} catch (VenturaConfigHelperException e) {
			String msg = getCatalogServiceUIResourceBundle().getString("error.service.config.helper");
			ServiceException t = new ServiceException(msg, e);
			if (logger.isLoggable(SEVERE))
				logger.logp(SEVERE, CLASS_NAME,"getAvailableWidgetBytabInstanceId", msg, t);
			throw t;
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getAvailableWidgetBytabInstanceId",widgetsMap);
		
		return widgetsMap;
	}

	/**
	 * Creates the default set of user specific widget instance for a new user.
	 * Currently this uses a very simple method to lay out all of the available,
	 * enabled widgets as all being open. In the future we should support the
	 * ability for the administrator to determine which widgets are open by
	 * default for new users and where they are on the page.
	 * 
	 * @return the list of widgets and their positioning
	 * 
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<WidgetInstance> createDefaultWidgetInstancesSetForTabInstanceId(String tabInstanceId, boolean includeDisabledWidgets)
			throws ServiceException {
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "createDefaultWidgetInstancesSetForTabInstanceId", new Object[] { tabInstanceId, includeDisabledWidgets });

		List<WidgetInstance> widgetInstances = new ArrayList<WidgetInstance>();
		List<Widget> availableWidgets = getAvailableWidgetsByTabInstanceId(tabInstanceId, includeDisabledWidgets);
		
		if (logger.isLoggable(FINEST))
			logger.logp(FINEST, CLASS_NAME,"createDefaultWidgetInstancesSetForTabInstanceId","This is the list of availableWidgets: "+availableWidgets);

		TabInstance tabInstance = getTabServices().getTabInstance(tabInstanceId);
		String tabOrgId = tabInstance.getOrganizationId();
		if (logger.isLoggable(FINEST))
			logger.logp(FINEST, CLASS_NAME,"createDefaultWidgetInstancesSetForTabInstanceId","This is the tab instance where we will display the widget: "+tabInstance);

		if (availableWidgets.size() > 0) {
			int orderNumber = 0;
			String containerId = com.ibm.lconn.homepage.model.Column.COL_1.getId();
			int column = 1;
			String defaultWidgetSetting = null;
			boolean defaultMaxMin = true;

			int numberOfCulumn = tabInstance.getNumColumns();

			for (Widget widget : availableWidgets) {
				if (widget.isDefaultOpened()) {

					String widgetId = widget.getWidgetId();

					String widgetInstanceId = createWidgetInstance(widgetId,tabInstanceId, orderNumber, containerId, defaultMaxMin, defaultWidgetSetting, null, tabOrgId);

					WidgetInstance instance = new WidgetInstance(widgetId,tabInstance.getTabInstId(), defaultWidgetSetting,orderNumber, containerId, defaultMaxMin, widgetInstanceId, tabOrgId);
					widgetInstances.add(instance);

					if (numberOfCulumn == 1) {
						orderNumber++;
						containerId = com.ibm.lconn.homepage.model.Column.COL_1.getId();
					} else {
						// TODO: hard-coded to 2 columns.
						// TODO: handle other cased (3 columns by default)
						if (column % 2 == 0) {
							orderNumber++;
							column = 1;
							containerId = com.ibm.lconn.homepage.model.Column.COL_1.getId();
						} else {
							column++;
							containerId = com.ibm.lconn.homepage.model.Column.COL_2.getId();
						}
					}

				}
			}

		} else {

			if (logger.isLoggable(FINEST)) {
				String info = getWebResourceBundle().getString("info.nowidget");
				logger.logp(FINEST, CLASS_NAME, "initDefaultBeans", info);
			}
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME,"createDefaultWidgetInstancesListForTabInstanceId", widgetInstances);

		return widgetInstances;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WidgetInstance> getWidgetInstancesByTabInstanceId(String tabInstanceId) throws ServiceException {

		List<WidgetInstance> widgetList = new ArrayList<WidgetInstance>();

		List<WidgetInst> widgetInstList = getWidgetInstDao().findWidgetsInstancesByTabInstance(tabInstanceId);

		if (widgetInstList != null) {
			for (WidgetInst widgetInst : widgetInstList) {
				WidgetInstance widget = new WidgetInstance(widgetInst.getWidgetId().trim(),
														   widgetInst.getTabInstanceId(),
														   widgetInst.getWidgetSetting(),
														   widgetInst.getOrderSequence(),
														   widgetInst.getContainer(),
														   widgetInst.getIsToggled(),
														   widgetInst.getWidgetInstId().trim(),
														   widgetInst.getOrganizationId());

				widgetList.add(widget);
			}
		}
		return widgetList;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, WidgetCategory> getAvailableWidgetsTree(
			String tabInstanceId, boolean includeDisabledWidgets)
			throws ServiceException {
		return getAvailableWidgetsTree(tabInstanceId, Locale.getDefault(),
				includeDisabledWidgets);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String createWidgetInstance(String widgetId, String tabInstanceId,
			int order, String containerId, boolean isToggled,
			String widgetSetting, String widgetInstanceId, String organizationId)
			throws ServiceException {

		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "createByInternalId", new Object[] {
					widgetId, tabInstanceId, order, containerId, isToggled,
					widgetSetting });

		String pk = null;

		WidgetInst widgetInst = new WidgetInst();
		widgetInst.setTabInstanceId(tabInstanceId);
		widgetInst.setWidgetId(widgetId);
		widgetInst.setOrderSequence(order);
		widgetInst.setContainer(containerId);
		widgetInst.setIsToggled(isToggled);
		widgetInst.setWidgetSetting(widgetSetting);
		widgetInst.setLastModified(new Date());
		widgetInst.setLastUpdated(new Date());
		widgetInst.setIsFixed(false);
		widgetInst.setWidgetInstId(widgetInstanceId);
		widgetInst.setOrganizationId(organizationId);

		pk = getWidgetInstDao().insert(widgetInst);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "createByInternalIdUI", pk);

		return pk;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, WidgetCategory> getAvailableWidgetsTree(
			String tabInstanceId, Locale locale, boolean includeDisabledWidgets)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getAvailableWidgetsTree");

		Map<String, WidgetCategory> availableWidgetsTree = new HashMap<String, WidgetCategory>();

		List<com.ibm.lconn.core.services.cre.remote.widget.model.Widget> widgets = this
				.getAvailableWidgetsByTabInstanceId(tabInstanceId, locale,
						includeDisabledWidgets);

		if (widgets != null) {

			for (com.ibm.lconn.core.services.cre.remote.widget.model.Widget widget : widgets) {
				if (!availableWidgetsTree.containsKey(widget.getCategoryName())) {
					WidgetCategory category = new WidgetCategory();
					if(widget.getCategoryName().isEmpty()){
						widget.setCategoryName("OTHER");
					}
					setCategoryFields(widget.getCategoryName(), category,
							locale);
					availableWidgetsTree
							.put(widget.getCategoryName(), category);
				}

				availableWidgetsTree.get(widget.getCategoryName()).addWidget(
						widget);
			}
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getAvailableWidgetsTree");

		return availableWidgetsTree;
	}

	/**
	 * In 2.5, the WIDGET table only contains a predefined id for the category
	 * This method set the category name (i18nized) and category CSS sprite
	 * based on the id stored in the DB Post 2.5, it might be worth adding a
	 * CATEGORY table to store that kind of information
	 */
	private void setCategoryFields(String categoryId, WidgetCategory category,
			Locale locale) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "setCategoryFields", new Object[] {
					categoryId, category });

		Category cat = Category.valueOf(categoryId);

		category.setName(cat.toLocalizedString(
				getCatalogServiceUIResourceBundle(), locale));
		// category.setCssClass(cat.getCSS());
		category.setId(categoryId);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "setCategoryFields");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public WidgetInstance getWidgetInstance(String widgetInstanceId)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getWidgetInstance", widgetInstanceId);

		WidgetInst widgetInst = (WidgetInst) getWidgetInstDao().getByPK(
				widgetInstanceId);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getWidgetInstance", widgetInst);

		return modelConverter.convertFromDaoWidgetInst(widgetInst);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WidgetInstance> getWidgetInstances(String widgetId,
			String tabInstanceId) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getWidgetInstances", new Object[] {
					widgetId, tabInstanceId });

		List<WidgetInst> widgetInstList = getWidgetInstDao()
				.getWidgetInstancesByTabInstanceId(tabInstanceId, widgetId);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getByInternalId", widgetInstList);

		return modelConverter.convertFromDaoWidgetInstList(widgetInstList);
	}

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
	 * 
	 * @throws DboardInfraException
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WidgetInstance> getWidgetsInstancesForTabInstance(
			String tabInstanceId) throws ServiceException {

		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getWidgetsInstancesForTabInstance",
					tabInstanceId);

		List<WidgetInst> widgetInstList = new ArrayList<WidgetInst>();	
		widgetInstList = getWidgetInstDao().findWidgetsInstancesByTabInstance(
				tabInstanceId);
	
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getWidgetsInstancesForTabInstance",
					widgetInstList);

		return modelConverter.convertFromDaoWidgetInstList(widgetInstList);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void changeWidgetSetting(String pk, String widgetSetting)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "changeWidgetSetting", new Object[] {
					pk, widgetSetting });

		WidgetInst widgetInst = (WidgetInst) getWidgetInstDao().getByPK(pk);
		widgetInst.setWidgetSetting(widgetSetting);
		getWidgetInstDao().update(widgetInst);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "changeWidgetSetting");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void changePosition(String pk, int row, int col, String pageID)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "changePosition", new Object[] { pk,
					row, col, pageID });

		WidgetInst widgetInst = (WidgetInst) getWidgetInstDao().getByPK(pk);
		widgetInst.setOrderSequence(row);

		// userWidgetPref.setColNum(col);
		widgetInst.setContainer(Integer.toString(col));

		// IT IS NOT POSSIBLE ANYMORE CHANGE THE PANEL ID
		// userWidgetPref.setPageID(pageID);

		getWidgetInstDao().update(widgetInst);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "changePosition");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteWidgetInstance(String pk) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "deleteWidgetInstance", pk);

		getWidgetInstDao().remove(pk);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "deleteWidgetInstance");
	}
	
	@Override
	public Widget getWidget(String widgetId, boolean checkDisabled)
			throws ServiceException 
	{
		try {
			// Implement locale behavior at client level
			return catalogService.getWidget(widgetId, Locale.getDefault(), checkDisabled);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public Widget getWidget(String widgetId, Locale locale, boolean checkDisabled)
			throws ServiceException {
		try {
			return catalogService.getWidget(widgetId, locale, checkDisabled);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}
	
	@Override
	public Widget getWidgetNonLocalized(String widgetId, boolean checkDisabled) 
		throws ServiceException
	{
		try {
			return catalogService.getWidgetNonLocalized(widgetId, checkDisabled);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public boolean checkFeatureInstalledByWidgetId(String widgetId)
			throws ServiceException {
		try {
			return catalogService.checkFeatureInstalledByWidgetId(widgetId);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public List<Widget> getAllWidgets(Locale locale) throws ServiceException {
		try {
			return catalogService.getAllWidgets(locale);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public void disableWidget(String widgetID) throws ServiceException {
		try {
			catalogService.disableWidget(widgetID);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public void enableWidget(String widgetID) throws ServiceException {
		try {
			catalogService.enableWidget(widgetID);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public void removeWidget(String widgetID) throws ServiceException {
		try {
			catalogService.removeWidget(widgetID);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public String addWidget(Widget widget) throws ServiceException {
		try {
			return catalogService.addWidget(widget);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public void updateWidget(Widget editedWidget) throws ServiceException {
		try {
			catalogService.updateWidget(editedWidget);
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public Map<String, Widget> getDisabledWidgetsForAdmin(Locale locale)
			throws ServiceException {
		final boolean FINER_P = logger.isLoggable(FINER);
		try {
			Map<String, Widget> ret = catalogService.getDisabledWidgets(locale);
			ArrayList<String> toRemove = new ArrayList();
			for (String key : ret.keySet()) {
				Widget wid = (Widget) ret.get(key);
				if (FINER_P) {
					Object[] s = wid.getBelongTabTypes().toArray();
					StringBuilder out = new StringBuilder();
					out.append( ((TabType) s[0]).getDBValue() );
					for ( int x=1; x < s.length; ++x ) out.append("::").append( ((TabType) s[x]).getDBValue() );
					
					logger.finer("key: " + key + 
							"\n  wid.tabTypes: " + out);
				}	
				if (wid.getBelongTabValue(TabType.IWIDGETS)) {
					// TabType.IWIDGETS are not Homepage widgets ...
					toRemove.add(key);
				}
			}
			for (int i=0; i<toRemove.size(); i++) {
				ret.remove(toRemove.get(i));
			}
			return ret;
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}

	@Override
	public Map<String, Widget> getEnabledWidgetsForAdminPage(Locale locale)
			throws ServiceException {
		final boolean FINER_P = logger.isLoggable(FINER);
		try {
			Map<String, Widget> ret = catalogService.getEnabledWidgets(locale);
			ArrayList<String> toRemove = new ArrayList();
			for (String key : ret.keySet()) {
				Widget wid = (Widget) ret.get(key);
				if (FINER_P) {
					Object[] s = wid.getBelongTabTypes().toArray();
					StringBuilder out = new StringBuilder();
					out.append( ((TabType) s[0]).getDBValue() );
					for ( int x=1; x < s.length; ++x ) out.append("::").append( ((TabType) s[x]).getDBValue() );
					
					logger.finer("key: " + key + 
							"\n  wid.tabTypes: " + out);
				}	
				if (wid.getBelongTabValue(TabType.IWIDGETS)) {
					// TabType.IWIDGETS are not Homepage widgets ...
					toRemove.add(key);
				}
			}
			for (int i=0; i<toRemove.size(); i++) {
				ret.remove(toRemove.get(i));
			}
			return ret;
		} catch (WidgetServiceException e) {
			throw convertCatalogException(e);
		}
	}
	
	/**
	 * Convert catalog exceptions for upstream
	 * @param ex
	 * @return
	 */
	private ServiceException convertCatalogException(WidgetServiceException e) {
		ServiceException ex = new ServiceException(e.getMessage(), e);
		if (e instanceof DuplicateGadgetUrlException) {
			ex.setDuplicateGadget(true);
			logger.log(Level.WARNING, "Duplicate gadget URLs are not permitted by the system", e);
		}
		return ex;
	}

	public void setWebResourceBundle(ResourceBundle webResourceBundle) {
		this.webResourceBundle = webResourceBundle;
	}

	public ResourceBundle getWebResourceBundle() {
		return webResourceBundle;
	}

	public WidgetCatalogServiceRemote getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(WidgetCatalogServiceRemote catalogService) {
		this.catalogService = catalogService;
	}

	@Override
	public List<Widget> getOrderedShareboxWidgets(Locale locale, boolean includeDisabledWidget) 
			throws ServiceException 
	{
		final boolean FINER_P = logger.isLoggable(FINER);
		final String METHOD = "getOrderedShareboxWidgets";
		List<Widget> widgets = null;
		
		if (FINER_P)
			logger.entering(CLASS_NAME, METHOD, new Object[] {locale, includeDisabledWidget});
		
		try {
			widgets = catalogService.getOrderedShareboxWidgets(locale, includeDisabledWidget);			
		} catch (WidgetServiceException e) {
			ServiceException ex = convertCatalogException(e);
			if (FINER_P)
				logger.throwing(CLASS_NAME, METHOD, ex);
			throw ex;
		}
		
		if (FINER_P)
			logger.exiting(CLASS_NAME, METHOD, widgets);
		
		return widgets;
	}

	@Override
	public void updateWidgetShareOrder(String widgetId, String orderAfterWidgetId)
		throws ServiceException 
	{
		final boolean FINER_P = logger.isLoggable(FINER);
		final String METHOD = "updateWidgetShareOrder";
		
		if (FINER_P)
			logger.entering(CLASS_NAME, METHOD, new Object[] {widgetId, orderAfterWidgetId});
		
		try {
			catalogService.updateWidgetShareOrder(widgetId, orderAfterWidgetId);
		} catch (WidgetServiceException e) {
			ServiceException ex = convertCatalogException(e);
			if (FINER_P)
				logger.throwing(CLASS_NAME, METHOD, ex);
			throw ex;
		}
		
		if (FINER_P)
			logger.exiting(CLASS_NAME, METHOD);		
	}

}
