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

package com.ibm.lconn.homepage.web.controller.admin;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

@Controller
@RequestMapping(value = { "/admin" })
public class EnableTestModeController extends AbstractWidgetAdminController {

    private static final long serialVersionUID = -1003911934070021710L;

    private static String CLASS_NAME = EnableTestModeController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private String useDisabled;

    private void enableTestMode() throws WebException {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "enableTestMode");

        // Saves admin setting to see disabled widgets in home page
        String userInternalId = getPersonInternalId();

        try {
            boolean formSetting = getUseDisabled() != null && "true".equals(getUseDisabled());
            boolean currentSetting = getUserServices().isAdminTestModeActive(userInternalId);

            // Saves new setting only if different from current one
            if (formSetting != currentSetting) {
                if (formSetting) {
                    getUserServices().enableAdminTestMode(userInternalId);
                    auditEnabled();
                } else {
                    getUserServices().disableAdminTestMode(userInternalId);
                    auditDisabled();
                }
            }
        } catch (ServiceException ex) {
            String error = getWebResourceBundle().getString("error.admin.testmode", userInternalId, getUseDisabled());
            WebException e = new WebException(error, ex);
            if (logger.isLoggable(SEVERE))
                logger.logp(SEVERE, CLASS_NAME, "enableTestMode", error, e);
            throw e;
        }
    }

    public void setUseDisabled(String useDisabled) {
        this.useDisabled = useDisabled;
    }

    public String getUseDisabled() {
        return useDisabled;
    }

    @RequestMapping(value = { "/enableTestMode.action" }, method = RequestMethod.GET)
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        init(request);

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        try {
            return executeInternal(model, request, response);
        } catch (ServiceException se) {
            logger.log(Level.SEVERE, se.getMessage(), se);
            response.sendError(400, se.getErrorCode());
            return ViewConstants.VIEW_ERROR;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, t.getMessage(), t);
            response.sendError(400, t.getMessage());
            return ViewConstants.VIEW_ERROR;
        } finally {
            if (logger.isLoggable(FINER))
                logger.exiting(CLASS_NAME, "execute");
        }

    }

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        enableTestMode();

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "execute", ViewConstants.VIEW_HOMEPAGE_ADMIN_ADMINPAGE);

        addPagedAttributesToModel(model, request);

        return ViewConstants.VIEW_HOMEPAGE_ADMIN_ADMINPAGE;
    }

    private void auditEnabled() throws WebException, ServiceException {
        if (getEventUtils().isRequiredPost(IEventUtils.ADMIN_ENABLE_TEST_MODE, Type.UPDATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.ADMIN_ENABLE_TEST_MODE, Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), false);
            getEventUtils().sendPost(event);
        }
    }

    private void auditDisabled() throws WebException, ServiceException {
        if (getEventUtils().isRequiredPost(IEventUtils.ADMIN_DISABLE_TEST_MODE, Type.UPDATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.ADMIN_DISABLE_TEST_MODE, Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), false);
            getEventUtils().sendPost(event);
        }
    }

}
