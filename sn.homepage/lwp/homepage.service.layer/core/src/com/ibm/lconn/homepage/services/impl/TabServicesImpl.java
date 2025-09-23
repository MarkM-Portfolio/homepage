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

package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetLayoutServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.core.services.cre.remote.widget.model.Tab;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetTab;
import com.ibm.lconn.homepage.dao.interfaces.ITabInstDao;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.model.TabInstance;
import com.ibm.lconn.homepage.model.UserUI;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.utils.ModelConverter;
import com.ibm.lconn.homepage.utils.IUIServiceUtils;
import com.ibm.lconn.homepage.utils.ResourceBundle;

public class TabServicesImpl extends AbstractService implements ITabServices {

    public final static String CUSTOM_PANEL = TabType.CUSTOM.getDBValue();
    public final static String UPDATE_PANEL = TabType.UPDATE.getDBValue();
    public final static String WIDGET_PANEL = TabType.WIDGET.getDBValue();

    private static String CLASS_NAME = TabServicesImpl.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private ResourceBundle serviceResourceBundle;
    @Autowired
    private IUIServiceUtils uiServicesUtils;
    @Autowired
    private ITabInstDao tabInstDao;
    @Autowired
    private IUserServices userServices;
    @Autowired
    private WidgetLayoutServiceRemote widgetLayoutServices;
    @Autowired
    private ModelConverter modelConverter;

    public final static String[] DEFAULT_USER_PANELS = { TabType.UPDATE.getDBValue(), TabType.WIDGET.getDBValue() };

    protected void internalAfterPropertiesSet() throws Exception {
    }

    public ModelConverter getModelConverter() {
        return modelConverter;
    }

    public void setModelConverter(ModelConverter modelConverter) {
        this.modelConverter = modelConverter;
    }

    public ResourceBundle getServiceResourceBundle() {
        return serviceResourceBundle;
    }

    public void setServiceResourceBundle(ResourceBundle serviceResourceBundle) {
        this.serviceResourceBundle = serviceResourceBundle;
    }

    public ITabInstDao getTabInstDao() {
        return tabInstDao;
    }

    public void setTabInstDao(ITabInstDao tabInstDao) {
        this.tabInstDao = tabInstDao;
    }

    public IUserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    public IUIServiceUtils getUiServicesUtils() {
        return uiServicesUtils;
    }

    public void setUiServicesUtils(IUIServiceUtils uiServiceUtils) {
        this.uiServicesUtils = uiServiceUtils;
    }

    /**
     * 
     * Vincent: I'm changing this method from private to public temporarily, to help on the palette example
     * 
     * @param tabInstanceId
     * @return
     * @throws ServiceException
     */
    public TabInstance getTabInstance(String tabInstanceId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getTabInstance", new Object[] { tabInstanceId });

        TabInst tabInst = (TabInst) getTabInstDao().getByPK(tabInstanceId);
        TabInstance tabInstance = modelConverter.convertFromDaoTabInst(tabInst, tabInstanceId);

        if (tabInstance == null) {
            String msg = getServiceResourceBundle().getString("error.UIServices.get.tabInstance", tabInstanceId);
            throw new ServiceException(msg);
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getTabInstance", tabInstance);

        return tabInstance;
    }

    /**
     * Given an id for a person we have back the id panel that contains all the widgets. If any panel is defined the method will create a panel
     * 
     * @param personId
     * @param tabType
     * 
     * @return the panel id
     * 
     * @throws DataAccessRetrieveException
     * @throws DataAccessCreateException
     * @throws DboardInfraException
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public String getTabInstanceId(String personId, TabType tabType) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getUserPanel", new Object[] { personId, tabType });

        String tabInsId = null;

        TabInst tabInst = getTabInstDao().getPersonUIPageByDefaultName(personId, tabType.getDBValue());
        // If the user is accessing on the UI for the first time he doesn't have
        // any page. We need to insert into the db the standard users panels
        if (tabInst == null) {
            tabInsId = createTabInstance(tabType.getDBValue(), personId);
        } else
            tabInsId = tabInst.getTabInstId();

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getUserPanel", tabInsId);

        return tabInsId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String createTabInstance(String tabName, String personId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "createUserPanel", new Object[] { tabName, personId });

        String tabInstId = null;

        // Retrieving the UI of the user
        UserUI ui = getUserServices().getUserUIByPersonId(personId);

        if (ui == null) {
            try {
                ui = getUserServices().createUI(personId);
            } catch (DataIntegrityViolationException ex) {
                try {
                    /*
                     * If we got an error inserting the try to reselect the user. If the error was due to a duplicate row insertion then reselecting will get the user without exception.
                     */
                    ui = getUserServices().getUserUIByPersonId(personId);

                    // If we still don't have a user we can't go on. The error was due to something other
                    // than a duplicate insertion so throw the original error.
                    if (ui == null) {
                        throw new ServiceException(ex);
                    }
                } catch (DataIntegrityViolationException e) {
                    // An error occurred on the retrieval.
                    throw e;
                }
            }
        }

        boolean isACustomName = isACustomPanelName(tabName);

        // Adding the defualt panel or a customm panel to the user
        Tab tab = null;
        TabInst tabInst = new TabInst();
        if (isACustomName) {
            // TODO: provide a way to specify a different panel name
            tab = getTabByName(TabType.CUSTOM.getDBValue());
            tabInst.setTabName(tabName);
        } else {
            tab = getTabByName(tabName);
            tabInst.setTabName(tabName);
        }
        tabInst.setTabId(tab.getTabId());
        tabInst.setLastModified(new Date());
        tabInst.setNumColumns(tab.getNumColumns()); // TODO Maybe this value can
        // changed based on the type
        // of panel
        tabInst.setUiId(ui.getUiId());
        tabInst.setOrganizationId(ui.getOrganizationId());

        try {
            tabInstId = getTabInstDao().insert(tabInst);
        } catch (DataIntegrityViolationException ex) {
            // If we got a duplicate then reselect the tab.
            tabInst = getTabInstDao().getPersonUIPageByDefaultName(personId, tabName);

            if (tabInst != null) {
                tabInstId = tabInst.getTabInstId();
            } else {
                // The exception must have been for something other than a duplicate.
                throw new ServiceException(ex);
            }
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "createUserPanel", tabInstId);

        return tabInstId;
    }

    private Tab getTabByName(String tabName) throws ServiceException {
        try {
            return widgetLayoutServices.getTabByName(tabName);
        } catch (WidgetServiceException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update the numbercolumn field of a given tab instance
     * 
     * @param tabInstanceId
     * @param numberColumn
     * @throws DboardInfraException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateNumberColumn(String tabInstanceId, int numberColumn) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "updateNumberColumn", new Object[] { tabInstanceId, numberColumn });

        TabInst tabInst = (TabInst) getTabInstDao().getByPK(tabInstanceId);
        tabInst.setNumColumns(numberColumn);
        getTabInstDao().update(tabInst);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "updateNumberColumn");

    }

    /**
     * To create a custom panel for a person.
     * 
     * @param tabName
     *            the panel name must be different from the standards panel.
     * @param personId
     * 
     * @return the panel id created
     * 
     * @throws DboardInfraException
     * @throws DataAccessRetrieveException
     * @throws DataAccessCreateException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String createCustomTabInstance(String tabName, String personId) throws ServiceException {
        return createTabInstance(tabName, personId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getNumberOfColumns(String tabInstanceId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getNumberOfColumns", tabInstanceId);

        int numberOfColumns;

        TabInst tabInst = (TabInst) getTabInstDao().getByPK(tabInstanceId);
        numberOfColumns = tabInst.getNumColumns();

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getNumberOfColumns", numberOfColumns);

        return numberOfColumns;
    }

    /**
     * To retrieve all the panels that an user got. This method it is used to retrieve a list with all the panels. It will contains the standard panels plus the custom panels.
     * 
     * If this method is runned when the db is empty it will populate the db in an automated way creating the standard default panel for the users.
     * 
     * The method will give back the list of the panel alredy translated plus the panel added by the users.
     * 
     * @param personId
     * @return
     * @throws DboardInfraException
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<TabInstance> getAllTabInstancesForPerson(String personId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getAllTabInstancesForPerson", new Object[] { personId });

        List<TabInst> tabInsts = getAllTabInstances(personId);

        // Case that the user login into the system for the first time
        if (tabInsts == null || tabInsts.isEmpty()) {

            for (String standardName : DEFAULT_USER_PANELS) {
                createTabInstance(standardName, personId);
            }

            tabInsts = getAllTabInstances(personId);
        }

        // pages is never empty at this point

        for (TabInst tabInst : tabInsts) {
            String tabName = tabInst.getTabName();
            String transletedPanelName = null;
            if (tabName.equals(TabType.CUSTOM)) // don't translate
                transletedPanelName = tabName;
            else { // translate
                Locale locale = new Locale("en_EN"); // TODO
                transletedPanelName = uiServicesUtils.getGlobalizedMsgFromCatalogUI(tabName, locale);
            }

            tabInst.setTabName(transletedPanelName);

        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getAllTabInstancesForPerson", tabInsts);

        return modelConverter.convertFromDaoTabInstList(tabInsts, personId);

    }

    private List<TabInst> getAllTabInstances(String personId) {
        List<TabInst> tabInsts = getTabInstDao().getPersonUIPages(personId);
        return tabInsts;
    }

    private static final boolean isACustomPanelName(String panelName) {
        boolean isACustomName = true;
        for (String standardName : DEFAULT_USER_PANELS) {
            if (standardName.equals(panelName))
                return false;
        }
        return isACustomName;
    }

    public void disableTab(String tabName) throws ServiceException {
        try {
            widgetLayoutServices.disableTab(tabName);
        } catch (WidgetServiceException e) {
            logLayoutException(e, "disableTab");
            throw new ServiceException(e);
        }
    }

    public void enableTab(String tabName) throws ServiceException {
        try {
            widgetLayoutServices.enableTab(tabName);
        } catch (WidgetServiceException e) {
            logLayoutException(e, "enableTab");
            throw new ServiceException(e);
        }
    }

    public boolean isTabEnabled(String tabName) throws ServiceException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "isTabEnabled", tabName);
        }

        try {
            return widgetLayoutServices.isTabEnabled(tabName);
        } catch (Exception e) {
            logLayoutException(e, "isTabEnabled");
            if (e instanceof WidgetServiceException) {
                throw new ServiceException(e);
            } else if (e instanceof NullPointerException) {
                // if the ejb call throws a NullPointerException we will just return false instead of propagating the exception.
                // besides the severe log that is logged above, we will also add more logging info here.
                if (logger.isLoggable(Level.SEVERE)) {
                    logger.logp(Level.SEVERE, CLASS_NAME, "isTabEnabled", tabName);
                }
                return false;
            } else {
                throw new RuntimeException(e);
            }
        } finally {
            if (logger.isLoggable(FINER)) {
                logger.exiting(CLASS_NAME, "isTabEnabled", tabName);
            }
        }
    }

    public List<WidgetTab> getWidgetTabsByTabId(String tabId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getWidgetTabsByTabId", new Object[] { tabId });

        try {
            List<WidgetTab> widgetTabs = widgetLayoutServices.getWidgetTabsByTabId(tabId);

            if (logger.isLoggable(FINER))
                logger.exiting(CLASS_NAME, "getWidgetTabsByTabId", widgetTabs);

            return widgetTabs;
        } catch (WidgetServiceException e) {
            logLayoutException(e, "getWidgetTabsByTabId");
            throw new ServiceException(e);
        }
    }

    public List<WidgetTab> getWidgetTabsByWidgetId(String widgetId) throws ServiceException {
        try {
            return widgetLayoutServices.getWidgetTabsByWidgetId(widgetId);
        } catch (WidgetServiceException e) {
            logLayoutException(e, "getWidgetTabsByWidgetId");
            throw new ServiceException(e);
        }
    }

    /**
     * Log exception from layout service
     * 
     * @param e
     * @param funcName
     */
    private void logLayoutException(Exception e, String funcName) {
        if (logger.isLoggable(Level.SEVERE)) {
            logger.logp(Level.SEVERE, CLASS_NAME, funcName, e.getMessage());
        }
    }

    /**
     * @return the widgetLayoutServices
     */
    public WidgetLayoutServiceRemote getWidgetLayoutServices() {
        return widgetLayoutServices;
    }

    /**
     * @param widgetLayoutServices
     *            the widgetLayoutServices to set
     */
    public void setWidgetLayoutServices(WidgetLayoutServiceRemote layoutServices) {
        this.widgetLayoutServices = layoutServices;
    }

}
