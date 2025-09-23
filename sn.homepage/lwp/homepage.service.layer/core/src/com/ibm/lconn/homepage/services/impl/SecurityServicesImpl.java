/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetTab;
import com.ibm.lconn.homepage.dao.interfaces.ITabInstDao;
import com.ibm.lconn.homepage.dao.interfaces.IWidgetInstDao;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.model.TabInstance;
import com.ibm.lconn.homepage.services.ISecurityServices;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.ServiceException;

public class SecurityServicesImpl implements ISecurityServices {

    private static String CLASS_NAME = SecurityServicesImpl.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private ITabInstDao tabInstDao;
    @Autowired
    private IWidgetInstDao widgetInstDao;
    @Autowired
    private ITabServices tabServices;

    public ITabInstDao getTabInstDao() {
        return tabInstDao;
    }

    public void setTabInstDao(ITabInstDao tabInstDao) {
        this.tabInstDao = tabInstDao;
    }

    public IWidgetInstDao getWidgetInstDao() {
        return widgetInstDao;
    }

    public void setWidgetInstDao(IWidgetInstDao widgetInstDao) {
        this.widgetInstDao = widgetInstDao;
    }

    public ITabServices getTabServices() {
        return tabServices;
    }

    public void setTabServices(ITabServices tabServices) {
        this.tabServices = tabServices;
    }

    public boolean checkTabInstanceOwnership(String personId, String tabInstanceId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "checkTabInstanceOwnership", new Object[] { personId, tabInstanceId });

        // naive implementation here based on a loop due to time contains.
        // optimizations might occur here if there is a real need.
        boolean owned = false;
        List<TabInstance> tabs = getTabServices().getAllTabInstancesForPerson(personId);

        for (TabInstance tab : tabs) {
            if (tab.getTabInstId().equals(tabInstanceId)) {
                owned = true;
                break;
            }
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "checkTabInstanceOwnership", owned);

        return owned;
    }

    public boolean checkWidgetAllowedOnWidgetTab(String widgetId, String tabInstanceId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "checkWidgetAllowedOnWidgetTab", new Object[] { widgetId, tabInstanceId });

        boolean result = false;
        List<WidgetTab> tabs = tabServices.getWidgetTabsByWidgetId(widgetId);
        TabInst tabInstance = (TabInst) getTabInstDao().getByPK(tabInstanceId);
        String tabId = tabInstance.getTabId();

        for (WidgetTab tab : tabs) {
            if (tab.getTabId().equals(tabId)) {
                result = true;
                break;
            }
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "checkWidgetAllowedOnWidgetTab", result);

        return result;
    }

    public boolean checkWidgetInstanceAllowedOnWidgetTab(String widgetInstanceId, String tabInstanceId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "checkWidgetInstanceAllowedOnWidgetTab", new Object[] { widgetInstanceId, tabInstanceId });

        boolean result = false;
        WidgetInst widgetInstance = (WidgetInst) getWidgetInstDao().getByPK(widgetInstanceId);
        result = checkWidgetAllowedOnWidgetTab(widgetInstance.getWidgetId(), tabInstanceId);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "checkWidgetInstanceAllowedOnWidgetTab", result);

        return result;
    }

    public boolean checkWidgetInstanceOwnership(String personId, String widgetInstanceId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "checkWidgetInstanceOwnership", new Object[] { personId, widgetInstanceId });

        boolean owned = false;

        WidgetInst widgetInstance = (WidgetInst) getWidgetInstDao().getByPK(widgetInstanceId);
        String tabInstanceId = widgetInstance.getTabInstanceId();
        owned = checkTabInstanceOwnership(personId, tabInstanceId);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "checkWidgetInstanceOwnership", owned);

        return owned;
    }

    public boolean checkWidgetInstanceType(String widgetInstanceId, String widgetId) throws ServiceException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "checkWidgetInstanceType", new Object[] { widgetInstanceId, widgetInstanceId });

        boolean result = false;

        WidgetInst widgetInstance = (WidgetInst) getWidgetInstDao().getByPK(widgetInstanceId);
        result = widgetInstance.getWidgetId().equals(widgetId);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "checkWidgetInstanceType", result);

        return result;
    }

}
