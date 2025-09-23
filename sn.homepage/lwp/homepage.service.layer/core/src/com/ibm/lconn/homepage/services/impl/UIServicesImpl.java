/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright  HCL Technologies Limited. 2008, 2021                   */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.ibm.connections.directory.services.data.DSObject;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.lconn.homepage.dao.interfaces.IWidgetInstDao;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.model.Component;
import com.ibm.lconn.homepage.model.ComponentConfiguration;
import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.IUIServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.utils.ModelConverter;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.hpnews.data.dao.interfaces.IPersonDao;

/**
 * To manage the user UI and how the widgets are displayed
 * 
 * @author Lorenzo Notarfonzo
 * 
 */
public class UIServicesImpl implements IUIServices {

	private static String CLASS_NAME = UIServicesImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Autowired
	private IWidgetInstDao widgetInstDao;
	@Autowired
	@Qualifier("HPNEWS-DATABASE:personDao")
	private IPersonDao personDao;

	@Autowired
	private ResourceBundle serviceResourceBundle;
	@Autowired
	private ModelConverter modelConverter;
	
	@Autowired
	private IConfigurationService configurationService;
	@Autowired
	private ResourceBundle catalogServiceUIResourceBundle;

	private static final String STATUS_UPDATES_ID = "status";
	private static final String NEWS_FEED_ID = "newsfeed";
	private static final String DISCOVER_ID = "discover";
	private static final String SAVED_ITEMS_ID = "saved";
	
	private static final String PREFIX = "/";
	
	private static final String TAGS_ID = "tags";
	private static final String RESPONSES_ID = "responses";
	
	private static final String MENU_STATUS_UPDATES = "main.menu.status.updates";
	private static final String MENU_NEWS_FEED = "main.menu.news.feed";
	private static final String MENU_DISCOVER = "main.menu.discover";
	private static final String MENU_SAVED_ITEMS = "main.menu.saved.items";
	
	private static final String CATEGORY_ACTIVITIES = "category.activities.name";
	private static final String CATEGORY_BLOGS = "category.blogs.name";
	private static final String CATEGORY_BOOKMARKS = "category.dogear.name";
	private static final String CATEGORY_COMMUNITIES = "category.communities.name";
	private static final String CATEGORY_FILES = "category.files.name";
	private static final String CATEGORY_FORUMS = "category.forums.name";
	private static final String CATEGORY_PEOPLE = "category.people.name";
	private static final String CATEGORY_WIKIS = "category.wiki.name";
	private static final String CATEGORY_TAGS = "category.tags.name";
	private static final String CATEGORY_RESPONSES = "category.responses.name";
	
	/** TODO: Should this configurable? */
	private static final int RESPONSES_POS = 1;
	private static final int PEOPLE_POS = 2;
	private static final int ACTIVITIES_POS = 3;
	private static final int BLOGS_POS = 4;
	private static final int BOOKMARKS_POS = 5;
	private static final int COMMUNITIES_POS = 6;
	private static final int FILES_POS = 7;
	private static final int FORUMS_POS = 8;	
	private static final int WIKIS_POS = 9;
	private static final int TAGS_POS = 10;
		
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

	@Transactional(propagation = Propagation.REQUIRED) 
	public String createWidgetInstance(String widgetId, String tabInstanceId,
			int order, String containerId, boolean isToggled,
			String widgetSetting, String widgetInstanceId, String organizationId) {

		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "createWidgetInstance", new Object[] {
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

		pk = widgetInstDao.insert(widgetInst);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "createWidgetInstance", pk);

		return pk;
	}

	// getUserWidgetInstance
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WidgetInstance getWidgetInstance(String widgetInstanceId)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getWidgetInstance", widgetInstanceId);

		WidgetInst widgetInst = null;
		WidgetInstance widgetInstance = null;

		widgetInst = (WidgetInst) widgetInstDao.getByPK(widgetInstanceId);

		widgetInstance = modelConverter.convertFromDaoWidgetInst(widgetInst);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getWidgetInstance", widgetInstance);

		return widgetInstance;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WidgetInstance> findWidgetInstances(String widgetId,
			String tabInstanceId) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getWidgetInstances", new Object[] {
					widgetId, tabInstanceId });

		List<WidgetInst> widgets = null;
		List<WidgetInstance> widgetsInstances = null;

		widgets = widgetInstDao.getWidgetInstancesByTabInstanceId(
				tabInstanceId, widgetId);

		widgetsInstances = modelConverter.convertFromDaoWidgetInstList(widgets);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getWidgetInstances", widgetsInstances);

		return widgetsInstances;
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
	 * @throws ServiceException
	 * 
	 * @throws DboardInfraException
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WidgetInstance> findWidgetsInstancesForTabInstance(
			String tabInstanceId) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getWidgetsInstancesForTabInstance",
					tabInstanceId);

		List<WidgetInst> widgetInstList = new ArrayList<WidgetInst>();
		List<WidgetInstance> widgetsInstances = null;
		// String panelName = PanelServices.DBOARD_1; //TODO REMOVE IT

		widgetInstList = (List<WidgetInst>) widgetInstDao
				.findWidgetsInstancesByTabInstance(tabInstanceId);

		widgetsInstances = modelConverter
				.convertFromDaoWidgetInstList(widgetInstList);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getWidgetsInstancesForTabInstance",
					widgetsInstances);

		return widgetsInstances;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void changeWidgetSetting(String pk, String widgetSetting) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "changeWidgetSetting", new Object[] {
					pk, widgetSetting });

		WidgetInst widgetInst;

		// factoryDAO.beginTransaction();

		widgetInst = (WidgetInst) widgetInstDao.getByPK(pk);
		widgetInst.setWidgetSetting(widgetSetting);
		widgetInstDao.update(widgetInst);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "changeWidgetSetting");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void changePosition(String pk, int row, int col, String pageID) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "changePosition", new Object[] { pk,
					row, col, pageID });

		WidgetInst widgetInst;

		widgetInst = (WidgetInst) widgetInstDao.getByPK(pk);

		widgetInst.setOrderSequence(row);

		widgetInst.setContainer("" + col);

		// IT IS NOT POSSIBLE ANYMORE CHANGE THE PANEL ID

		widgetInstDao.update(widgetInst);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "changePosition");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteWidgetInstance(String pk) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "deleteWidgetInstance", pk);

		widgetInstDao.remove(pk);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "deleteWidgetInstance");
	}

	protected User createUser(DSObject obj) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "createUser", obj);

		User user = new User();
		user.setDisplayname(obj.get_name());
		user.setExternalId(obj.get_id());
		user.setMail(obj.get_email());

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "createUser", user);

		return user;
	}

	public IWidgetInstDao getWidgetInstDao() {
		return widgetInstDao;
	}

	public void setWidgetInstDao(IWidgetInstDao widgetInstDao) {
		this.widgetInstDao = widgetInstDao;
	}

	public IPersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

	public ResourceBundle getServiceResourceBundle() {
		return serviceResourceBundle;
	}

	public void setServiceResourceBundle(ResourceBundle serviceResourceBundle) {
		this.serviceResourceBundle = serviceResourceBundle;
	}

	public ModelConverter getModelConverter() {
		return modelConverter;
	}

	public void setModelConverter(ModelConverter modelConverter) {
		this.modelConverter = modelConverter;
	}
	
	private JSONObject generateMyNetworkMenuItem(Locale locale) {
		JSONObject network = new JSONObject();
		network.put("id", STATUS_UPDATES_ID);
		network.put("name", getCatalogServiceUIResourceBundle().getString(MENU_STATUS_UPDATES, locale));
		network.put("type", "menuitem");
		return network;
	}
	
	private JSONArray generateComponentMenuItems(String prefix, Locale locale) {
		JSONArray items = new JSONArray();
		Map<Integer, JSONObject> componentMap = new HashMap<Integer, JSONObject>();
		
		List<String> components = getConfigurationService().getInstalledComponents();
		for(String component : components) {
			try {
				ComponentConfiguration comp = getConfigurationService().getComponentConfiguration(component);
				
				if(comp.isSecureUrlEnabled() && comp.isUrlEnabled()) {
					
					String label = "";
					int index=0;
					
					/**
					 * Check that if the menu is for following, only include communities & profiles.
					 */
					
					if(component.equalsIgnoreCase(Component.ACTIVITIES.getName())) {
						
						label = getCatalogServiceUIResourceBundle().getString(CATEGORY_ACTIVITIES, locale);
						index = ACTIVITIES_POS;
						
					} else if(component.equalsIgnoreCase(Component.BLOGS.getName())) {
						
						label = getCatalogServiceUIResourceBundle().getString(CATEGORY_BLOGS, locale);
						index = BLOGS_POS;
						
					} else if(component.equalsIgnoreCase(Component.BOOKMARKS.getName())
									&& !prefix.equalsIgnoreCase(NEWS_FEED_ID + PREFIX)){
						
						label = getCatalogServiceUIResourceBundle().getString(CATEGORY_BOOKMARKS, locale);						
						index = BOOKMARKS_POS;
						
					} else if(component.equalsIgnoreCase(Component.COMMUNITIES.getName())) {
						
						label = getCatalogServiceUIResourceBundle().getString(CATEGORY_COMMUNITIES, locale);
						index = COMMUNITIES_POS;
						
					} else if(component.equalsIgnoreCase(Component.FILES.getName())) {
						
						label = getCatalogServiceUIResourceBundle().getString(CATEGORY_FILES, locale);
						index = FILES_POS;
						
					} else if(component.equalsIgnoreCase(Component.FORUMS.getName())) {
						
						label = getCatalogServiceUIResourceBundle().getString(CATEGORY_FORUMS, locale);
						index = FORUMS_POS;
						
					} else if(component.equalsIgnoreCase(Component.PROFILES.getName())) {
						
						label = getCatalogServiceUIResourceBundle().getString(CATEGORY_PEOPLE, locale);
						index = PEOPLE_POS;
						
					} else if(component.equalsIgnoreCase(Component.WIKIS.getName())) {
						
						label = getCatalogServiceUIResourceBundle().getString(CATEGORY_WIKIS, locale);
						index = WIKIS_POS;
						
					}
					if(label!="") {
						JSONObject item = new JSONObject();
						item.put("id", prefix + component);
						item.put("name", label);
						item.put("type", "sub-menuitem");
						componentMap.put(index, item);				
					}
				}
			}
			catch(ServiceException ex) {
				logger.logp(Level.SEVERE, CLASS_NAME, "generateComponentMenuItems", ex.getMessage());
				//Keep iterating???
			}
		}
		
		/**
		 * Check that if the menu is for following add responses menu option
		 * and if search is enabled, include tags
		 * 
		 */
		
		if(prefix.equalsIgnoreCase(NEWS_FEED_ID + PREFIX)) {
			
			JSONObject responsesItem = new JSONObject();
			responsesItem.put("id", prefix + RESPONSES_ID);
			responsesItem.put("name", getCatalogServiceUIResourceBundle().getString(CATEGORY_RESPONSES, locale));
			responsesItem.put("type", "sub-menuitem");
			componentMap.put(RESPONSES_POS, responsesItem);
			
			try {
				ComponentConfiguration comp = getConfigurationService().getComponentConfiguration(Component.SEARCH.getName());
				//Watchlist functionality requires search, so if it is not enabled - disable tags.
				if(comp.isSecureUrlEnabled() && comp.isUrlEnabled()) {
					JSONObject tagsItem = new JSONObject();
					tagsItem.put("id", prefix + TAGS_ID);
					tagsItem.put("name", getCatalogServiceUIResourceBundle().getString(CATEGORY_TAGS, locale));
					tagsItem.put("type", "sub-menuitem");
					componentMap.put(TAGS_POS, tagsItem);
				}
			}
			catch(ServiceException ex) {
				logger.logp(Level.SEVERE, CLASS_NAME, "generateComponentMenuItems", ex.getMessage());
			}
				
		}
		
		Object[] keys = componentMap.keySet().toArray();
		Arrays.sort(keys);
		
		for(int i=0; i < keys.length; i++) {			
			items.add(componentMap.get(keys[i]));
		}
		return items;
	}
	
	private JSONObject generateFollowingMenuItem(Locale locale) {
		return generateComponentBasedMenu(NEWS_FEED_ID, getCatalogServiceUIResourceBundle().getString(MENU_NEWS_FEED, locale), NEWS_FEED_ID + PREFIX, locale);
	}
	
	private JSONObject generateDiscoverMenuItem(Locale locale) {
		return generateComponentBasedMenu(DISCOVER_ID, getCatalogServiceUIResourceBundle().getString(MENU_DISCOVER, locale), DISCOVER_ID + PREFIX, locale);		
	}
	
	private JSONObject generateSavedItemsMenuItem(Locale locale) {
		return generateComponentBasedMenu(SAVED_ITEMS_ID, getCatalogServiceUIResourceBundle().getString(MENU_SAVED_ITEMS, locale), SAVED_ITEMS_ID + PREFIX, locale);		
	}
	
	private JSONObject generateComponentBasedMenu(String parentId, String parentLabel, String parentPrefix, Locale locale) {
		JSONObject menu = new JSONObject();
		menu.put("id", parentId);		
		menu.put("name", parentLabel);
		menu.put("type", "menuitem");
		
		JSONArray items = generateComponentMenuItems(parentPrefix, locale);		
		if(items.size() > 0)
			menu.put("children", items);
		return menu;
	}
	
	public void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public IConfigurationService getConfigurationService() {
		return configurationService;
	}
	
	public void setCatalogServiceUIResourceBundle(
			ResourceBundle catalogServiceUIResourceBundle) {
		this.catalogServiceUIResourceBundle = catalogServiceUIResourceBundle;
	}

	public ResourceBundle getCatalogServiceUIResourceBundle() {
		return catalogServiceUIResourceBundle;
	}

}

