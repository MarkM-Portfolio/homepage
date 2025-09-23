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

package com.ibm.lconn.homepage.web.controller.start;

import static java.util.logging.Level.FINER;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.AbstractHomepageController;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

@Controller
@RequestMapping(value = { "/web" })
public class UpdateWelcomeModeController extends AbstractHomepageController {

    private final static String CLASS_NAME = UpdateWelcomeModeController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);
    private static final long serialVersionUID = 6078169325069978162L;

    private IUserServices userServices;

    private boolean checkboxValue;
    private JSONObject json;

    public IUserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    public boolean isCheckboxValue() {
        return checkboxValue;
    }

    public void setCheckboxValue(boolean checkboxValue) {
        this.checkboxValue = checkboxValue;
    }

    public void setJson(JSONObject jsonObject) {
        this.json = jsonObject;
    }

    public JSONObject getJson() {
        return json;
    }

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "executeInternal", checkboxValue);
        }

        if (!isCSRFSafe(request, response)) {
            return ViewConstants.VIEW_ERROR;
        }

        getUserServices().updateWelcome(getPersonInternalId(), !isCheckboxValue());
        if (!isCheckboxValue()) {
            auditEnabled();
        } else {
            auditDisabled();
        }
        json = new JSONObject();
        json.put("value", isCheckboxValue());

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "executeInternal", json);
        }
        addPagedAttributesToModel(model, request);

        // todo what's the return here?
        return "success";

    }

    protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {
        model.addAttribute("isPerson", com.ibm.lconn.core.web.auth.LCRestSecurityHelper.isUserInRole(request, "person"));

        super.addPagedAttributesToModel(model);
    }

    @RequestMapping(value = { "/updateWelcomeMode.action" }, method = RequestMethod.GET)
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

    private void auditEnabled() throws WebException, ServiceException {
        if (getEventUtils().isRequiredPost(IEventUtils.USER_ENABLE_GETTING_STARTED, Type.UPDATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.USER_ENABLE_GETTING_STARTED, Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), true);
            getEventUtils().sendPost(event);
        }
    }

    private void auditDisabled() throws WebException, ServiceException {
        if (getEventUtils().isRequiredPost(IEventUtils.USER_DISABLE_GETTING_STARTED, Type.UPDATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.USER_DISABLE_GETTING_STARTED, Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), true);
            getEventUtils().sendPost(event);
        }
    }

}
