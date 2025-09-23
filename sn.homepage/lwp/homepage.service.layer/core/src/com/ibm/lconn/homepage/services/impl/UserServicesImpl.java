/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright  HCL Technologies Limited. 2010, 2021                   */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.lconn.core.platform.ICPlatform;
import com.ibm.lconn.homepage.dao.interfaces.ITabInstDao;
import com.ibm.lconn.homepage.dao.interfaces.IUIDao;
import com.ibm.lconn.homepage.dao.interfaces.IWidgetInstDao;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.dao.model.UI;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.model.UserUI;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.utils.ModelConverter;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.hpnews.data.dao.interfaces.IPersonDao;
import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import com.ibm.lconn.hpnews.service.interfaces.IPersonService;
import com.ibm.lconn.hpnews.service.waltz.IWaltzSyncService;
import com.ibm.lconn.hpnews.service.waltz.UserSyncException;

public class UserServicesImpl extends AbstractService implements IUserServices {

	private static String CLASS_NAME = UserServicesImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Autowired
	private ResourceBundle webResourceBundle;
	@Autowired
	private ResourceBundle serviceResourceBundle;
	@Autowired
	private ModelConverter modelConverter;
	@Autowired
	private IUIDao uiDao;
	@Autowired
	@Qualifier("HPNEWS-DATABASE:personDao")
	private IPersonDao personDao;
	@Autowired
	private IWaltzSyncService waltzSyncService;
	@Autowired
	@Qualifier("HPNEWS-SERVICE:personService")
	private IPersonService personService;
	@Autowired
	private IWidgetInstDao widgetInstDao;
	@Autowired
	private ITabInstDao tabInstDao;

	protected void internalAfterPropertiesSet() throws Exception {
	}

	public ModelConverter getModelConverter() {
		return modelConverter;
	}

	public void setModelConverter(ModelConverter modelConverter) {
		this.modelConverter = modelConverter;
	}

	public IWaltzSyncService getWaltzSyncService() {
		return waltzSyncService;
	}

	public void setWaltzSyncService(IWaltzSyncService waltzSyncService) {
		this.waltzSyncService = waltzSyncService;
	}

	public void setWebResourceBundle(ResourceBundle webResourceBundle) {
		this.webResourceBundle = webResourceBundle;
	}

	public ResourceBundle getWebResourceBundle() {
		return webResourceBundle;
	}

	public ResourceBundle getServiceResourceBundle() {
		return serviceResourceBundle;
	}

	public void setServiceResourceBundle(ResourceBundle serviceResourceBundle) {
		this.serviceResourceBundle = serviceResourceBundle;
	}

	public IUIDao getUiDao() {
		return uiDao;
	}

	public void setUiDao(IUIDao uiDao) {
		this.uiDao = uiDao;
	}

	public IPersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}		

	public UserUI getUserUIByPersonId(String personId) throws ServiceException {
		UI ui = getDaoUserUIByPersonId(personId);				
		return modelConverter.convertFromDaoUI(ui);
	}
	
	public IPersonService getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService personService) {
		this.personService = personService; 
	}

	public void setWidgetInstDao(IWidgetInstDao widgetInstDao) {
		this.widgetInstDao = widgetInstDao;
	}

	public void setTabInstDao(ITabInstDao tabInstDao) {
		this.tabInstDao = tabInstDao;
	}

	// TODO: VB: is the userservice really to correct location for this method??
	// test mode is a global settings, not user specific
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isAdminTestModeActive(String userInternalId)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger
					.entering(CLASS_NAME, "isAdminTestModeActive",
							userInternalId);

		boolean showDisabled = false;

		UserUI userUI = getUserUIByPersonId(userInternalId);
		if (userUI != null) {
			showDisabled = userUI.getShowDisabledWidgets();
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "isAdminTestModeActive", showDisabled);

		return showDisabled;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void disableAdminTestMode(String userInternalId)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "disableAdminTestMode", userInternalId);

		getUiDao().disableTestModeByUserID(userInternalId);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "disableAdminTestMode");
	}

	/**
	 * Enables administration test mode for a user.
	 * 
	 * @param userInternalId
	 *            user's id
	 * @throws IServiceException
	 *             if test mode could not be enabled
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void enableAdminTestMode(String userInternalId)
			throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "enableAdminTestMode", userInternalId);

		getUiDao().enableTestModeByUserID(userInternalId);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "enableAdminTestMode");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateLastVisitForUser(String internalUserId) {
		Date lastVisit = new Date();
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateLastVisitForUser", new Object[] {
					internalUserId, lastVisit });
			
			getUiDao().updateLastVisitByPersonId(internalUserId, lastVisit);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateLastVisitForUser");
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateLastVisitAndLanguageForUser(String internalUserId, String language) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateLasVisitAndLanguageForUser", new Object[] {
					internalUserId, language });
			Date lastVisit = new Date();
			if(language != null && language.length() <= 5) {
				getUiDao().updateLanguageAndLastVisitByPersonId(internalUserId, language, lastVisit);	
			}			

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateLasVisitAndLanguageForUser");
	}	

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateLanguageForUser(String internalUserId, String language) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateLanguageForUser", new Object[] {
					internalUserId, language });

		// The LC language selector sometimes seems to cause an invalid language
		// to be set, which is longer than 5 chars e.g. @@@lang@@@
		if(language != null && language.length() <= 5) {
			getUiDao().updateLanguageByPersonId(internalUserId, language);
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateLanguageForUser");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateWelcome(String userInternalId, boolean isWelcomeMode) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateWelcome", new Object[] {
					userInternalId, isWelcomeMode });

		com.ibm.lconn.homepage.dao.model.UI ui = null;
		try {
			ui = getDaoUserUIByPersonId(userInternalId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (ui != null) {
			ui.setWelcomeMode(isWelcomeMode);
			getUiDao().update(ui);
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateWelcome");
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateWelcomeNote(String userInternalId, boolean isWelcomeNote) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateWelcomeNote", new Object[] {
					userInternalId, isWelcomeNote });
		
		com.ibm.lconn.homepage.dao.model.UI ui = null;
		try {
			ui = getDaoUserUIByPersonId(userInternalId);
		} catch (ServiceException e) {
			String errMsg = 
				getServiceResourceBundle()
					.getString("error.UserServices.updateWelcomeNote", userInternalId);
			logger.logp(SEVERE, CLASS_NAME, "updateWelcomeNote", errMsg);
		}

		if (ui != null) {
			ui.setWelcomeNote(isWelcomeNote);
			getUiDao().update(ui);
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateWelcomeNote");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public UserUI createUI(String internalId) throws ServiceException {

		if (logger.isLoggable(FINER))
			logger
					.entering(CLASS_NAME, "createUI",
							new Object[] { internalId });

		UI ui = createUIInternal(internalId);
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "createUI", ui);

		return modelConverter.convertFromDaoUI(ui);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	private UI createUIInternal(String internalId) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger
					.entering(CLASS_NAME, "createUIInternal",
							new Object[] { internalId });

		com.ibm.lconn.homepage.dao.model.UI ui = new com.ibm.lconn.homepage.dao.model.UI();
		ui.setWelcomeMode(true);
		ui.setWelcomeNote(true);
		ui.setLastVisit(new Date());

		// default to English locale. This will be updated with the actual
		// locale later
		ui.setPersonLang(Locale.ENGLISH.getLanguage());
		// Perform database operations
		ui.setPersonId(internalId);
		
		String orgId = ApplicationContext.getOrganizationId();
		if(orgId == null){
			orgId = personService.getOrganizationForPerson(internalId);
		}
		ui.setOrganizationId(orgId);
		
		getUiDao().insert(ui);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "createUIInternal", ui);
		return ui;
	}

	private com.ibm.lconn.homepage.dao.model.UI getDaoUserUIByPersonId(
			String personId) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getUserUIByPersonId", personId);

		com.ibm.lconn.homepage.dao.model.UI ui = (com.ibm.lconn.homepage.dao.model.UI) this.uiDao
				.getUserUIByPersonId(personId);
		
		//check org id - fixup
		
		String orgId = ApplicationContext.getOrganizationId();
		if(ui != null && !orgId.equals(ui.getOrganizationId())){
			fixupWidgetTabUIOrgIds(personId, ui, orgId);
		}
		return ui;
	}

	/**
	 * Responsible for resynching an orgId change on a person across the hp_ui table and widget tabinst dependencies
	 */
	
	@Transactional(propagation = Propagation.REQUIRED)
	private void fixupWidgetTabUIOrgIds(String personId,	com.ibm.lconn.homepage.dao.model.UI ui, String orgId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "fixupWidgetTabOrgIds", new Object[]{personId, ui, orgId});
		
		ui.setOrganizationId(orgId);
		this.uiDao.update(ui);
		this.fixupWidgetTabOrgIds(personId, orgId);
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "fixupWidgetTabOrgIds", ui);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void fixupWidgetTabOrgIds(String personId, String orgId){
		try{
			ApplicationContext.setIgnoreOverride();
			List<TabInst> tabs = this.tabInstDao.getPersonUIPages(personId);
			for (TabInst tabInst : tabs) {
				tabInst.setOrganizationId(orgId);
				this.tabInstDao.update(tabInst);
				List<WidgetInst> widgetInstList = this.widgetInstDao.findWidgetsInstancesByTabInstance(tabInst.getTabInstId());
				for (WidgetInst widgetInst : widgetInstList) {
					widgetInst.setOrganizationId(orgId);
					this.widgetInstDao.update(widgetInst);
				}
			}
		}finally{
			ApplicationContext.unsetOrganizationIdOverride();
		}
	}

	public User getUserLoginName(String loginName) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getUserLoginName", loginName);

		// check to see if we have an OrgId on the request
		// If so, use in Waltz request along with loginname
		String orgId = ApplicationContext.getOrganizationId();
		
		Person person = getWaltzSyncService().retriveAndUpdatePersonByLoginName(loginName);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getUserLoginName", person);
		  
		  User user = null;

		  if(person != null){
			user = modelConverter.convertFromPerson(person);
			user.setExternalOrgId(orgId);			
		  }
		  else{
			  throw new ServiceException("Failed to get user details for: "+loginName+", orgId: "+orgId);
		  }
		  
		return user;
	}

	public User getUserByExternalId(String exId) throws UserSyncException, ServiceException  {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getUserByExternalId", exId);

		Person person = null;
		person = personService.getPersonByExternalId(exId);
		

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getUserByExternalId", person);
		User user = modelConverter.convertFromPerson(person);
		return user;
	}

	public boolean isUserInWelcomeMode(String personId) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "isUserInWelcomeMode", personId);

		boolean isInWelcomeMode = true;
		UserUI userUI = getUserUIByPersonId(personId);

		if (userUI == null) {
			try {
				userUI = createUI(personId);
			} catch (DataIntegrityViolationException ex) {
				// If we got a data integrity violation the row must be there so select it again.
 				userUI = getUserUIByPersonId(personId);
 			}
		}

		if (userUI != null)
			isInWelcomeMode = userUI.isWelcomeMode();

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "isUserInWelcomeMode", isInWelcomeMode);

		return isInWelcomeMode;
	}
	
	public boolean isWelcomeNote(String personId) throws ServiceException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "isWelcomeNote", personId);

		boolean isWelcomeNote = true;
		UserUI userUI = getUserUIByPersonId(personId);

		if (userUI == null) {
			try {
				userUI = createUI(personId);
			} catch (DataIntegrityViolationException ex) {
				// If we got a duplicate then reselect the user.
 				userUI = getUserUIByPersonId(personId);
 			}
		}

		if (userUI != null)
			isWelcomeNote = userUI.isWelcomeNote();

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "isWelcomeNote", isWelcomeNote);

		return isWelcomeNote;
	}

}
