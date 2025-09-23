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

package com.ibm.lconn.homepage.web.controller;

import static java.util.logging.Level.FINER;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.lconn.homepage.web.WebException;

public abstract class AbstractWidgetController extends AbstractTabInstanceAwareController {

    private final static String CLASS_NAME = AbstractWidgetController.class.getName();
    private final static Logger logger = Logger.getLogger(CLASS_NAME);

    private static final long serialVersionUID = -6165830128794206637L;

    @Autowired
    private IUserServices userServices;

    @Autowired
    private IWidgetServices widgetServices;

    /**
     * Show disabled widgets if, and only if, user is in admin role AND the "test admin mode" is enabled (checkbox in admin pages).
     * 
     * Most of the methods in the widget service ask for this boolean
     * 
     * TODO: move this to facade in service layer
     * 
     * @return
     */
    public boolean isShowDisabledWidgets() throws WebException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "isShowDisabledWidgets");
        }

        boolean showDisabledWidgets;

        if (isUserInAdminRole()) {
            try {
                showDisabledWidgets = userServices.isAdminTestModeActive(getPersonInternalId());
            } catch (ServiceException e) {
                throw new WebException(e);
            }
        } else {
            showDisabledWidgets = false;
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "isShowDisabledWidgets", showDisabledWidgets);
        }

        return showDisabledWidgets;
    }

    protected void addPagedAttributesToModel(Model model) {
        super.addPagedAttributesToModel(model);
    }

    public IUserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    public IWidgetServices getWidgetServices() {
        return widgetServices;
    }

    public void setWidgetServices(IWidgetServices widgetServices) {
        this.widgetServices = widgetServices;
    }

}
