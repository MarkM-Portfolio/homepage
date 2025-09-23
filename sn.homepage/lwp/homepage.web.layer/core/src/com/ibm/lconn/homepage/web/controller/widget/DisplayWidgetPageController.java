/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.web.controller.widget;

import static java.util.logging.Level.FINER;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.controller.AbstractWidgetPageLayoutController;
import com.ibm.lconn.homepage.web.controller.ViewConstants;

/**
 * This action is taken from the DisplayWidgetPageServlet TODO: refactoring candidate
 */
@Controller
@RequestMapping(value = { "/web" })
public class DisplayWidgetPageController extends AbstractWidgetPageLayoutController {

    private static final long serialVersionUID = -7138966399090823158L;
    private final static String CLASS_NAME = DisplayWidgetPageController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private ITabServices tabServices;

    public void setTabServices(ITabServices tabServices) {
        this.tabServices = tabServices;
    }

    public ITabServices getTabServices() {
        return tabServices;
    }

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "executeInternal");

        boolean welcomeMode = getUserServices().isUserInWelcomeMode(getPersonInternalId());
        // updateSession(GlobalSessionConstants.USER_IS_FIRST_LOGIN,
        // welcomeMode);

        setFirstTimeLogin(welcomeMode);
        setColumnNumber(getTabServices().getNumberOfColumns(getTabInstanceId()));
        setupWidgetInstances(request);
        setupJavalinAttributes();
        setupActionRequiredTotal();
        setupASConfig(request);
        prepareWidgetPreferences();

        if (!getActivityStreamConfigService().checkConnectionsNewUIEnabled(request)) {
            setHomepageStateCookie("widgets", request, response);
        }

        addPagedAttributesToModel(model, request);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "executeInternal");

        return ViewConstants.VIEW_HOMEPAGE_MAIN_WIDGETSPAGE;
    }

    @RequestMapping(value = { "/widgets/" }, method = RequestMethod.GET)
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "tabInstanceId", required = false) String tabInstanceId) throws Exception {
        init(request);
        if (tabInstanceId != null) {
            setTabInstanceId(tabInstanceId);
        }

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
        model.addAttribute("isPerson", com.ibm.lconn.core.web.auth.LCRestSecurityHelper.isUserInRole(request, "person"));
        model.addAttribute("actionRequiredTotal", getActionRequiredTotal());
        model.addAttribute("session", getSession());        
        model.addAttribute("activityStreamConfig", getActivityStreamConfig());
        model.addAttribute("nonce", getNonce());
        model.addAttribute("tabInstanceId", getTabInstanceId());
        model.addAttribute("widgetPreferences", getWidgetPreferences());
        model.addAttribute("activityStreamConfig", getActivityStreamConfig());
        

        super.addPagedAttributesToModel(model);

    }

    private void setupASConfig() {
        // TODO Auto-generated method stub

    }

    private void setHomepageStateCookie() {
        // TODO Auto-generated method stub

    }
}
