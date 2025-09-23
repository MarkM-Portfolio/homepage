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

package com.ibm.lconn.homepage.web.controller.activitystream;

import static java.util.logging.Level.FINER;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.controller.AbstractWidgetPageLayoutController;
import com.ibm.lconn.homepage.web.controller.ViewConstants;

@Controller
@RequestMapping(value = { "/web" })
public class ActivityStreamPageController extends AbstractWidgetPageLayoutController {

    private static final long serialVersionUID = 3587065299316204802L;
    private final static String CLASS_NAME = ActivityStreamPageController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private String welcomeNoteClass;

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "executeInternal");
        }

        setWelcomeNoteClass(getUserServices().isWelcomeNote(getPersonInternalId()) ? "" : "lotusHidden");

        setupWidgetInstances(request);
        setupActionRequiredTotal();
        setupASConfig(request);

        if (!getActivityStreamConfigService().checkConnectionsNewUIEnabled(request)) {
            setHomepageStateCookie("updates", request, response);
        }

        prepareWidgetPreferences();

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "executeInternal");
        }
        addPagedAttributesToModel(model, request);

        return ViewConstants.VIEW_HOMEPAGE_MAIN_ACTIVITYSTREAM;
    }

    @RequestMapping(value = { "/updates/" }, method = RequestMethod.GET)
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

    protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {
        // set pagination values
        model.addAttribute("welcomeNoteClass", getWelcomeNoteClass());
        model.addAttribute("isPerson", com.ibm.lconn.core.web.auth.LCRestSecurityHelper.isUserInRole(request, "person"));

        super.addPagedAttributesToModel(model);
    }

    public String getWelcomeNoteClass() {
        return welcomeNoteClass;
    }

    public void setWelcomeNoteClass(String welcomeNoteClass) {
        this.welcomeNoteClass = welcomeNoteClass;
    }

}
