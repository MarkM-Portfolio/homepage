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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.controller.AbstractHomepageController;
import com.ibm.lconn.homepage.web.controller.ViewConstants;

@Controller
@RequestMapping(value = { "/web" })
public class ExploreStepPageController extends AbstractHomepageController {

    private static final long serialVersionUID = -3098360825410905488L;

    private static String CLASS_NAME = ExploreStepPageController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    // Defined in the order we want them to appear on the page
    private static String[] COMPONENT_NAMES = new String[] { "activities", "blogs", "dogear", "communities", "files", "forums", "profiles", "wikis" };

    // Used LinkedHashMap so that order we add items is order they are read
    private static Map<String, String> componentsConfig = new LinkedHashMap<String, String>();

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "executeInternal", new Object[] {});
        }

        initComponentsConfig(request);

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "executeInternal");
        }
        addPagedAttributesToModel(model, request);

        return ViewConstants.VIEW_STARTTAB_EXPLORESTEPPAGE;
    }

    protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {
        model.addAttribute("isPerson", com.ibm.lconn.core.web.auth.LCRestSecurityHelper.isUserInRole(request, "person"));
        model.addAttribute("componentsConfig", getComponentsConfig());

        super.addPagedAttributesToModel(model);
    }

    @RequestMapping(value = { "/exploreStepPage.action" }, method = RequestMethod.GET)
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

    private void initComponentsConfig(HttpServletRequest request) {
        // TODO: need to cache this once at startup in service layer

        for (String componentName : COMPONENT_NAMES) {

            if (getConfigurationService().isComponentInstalled(componentName)) {

                try {
                    if (getConfigurationService().isMultiTenantEnvironment() == true && (componentName.equals("blogs") || componentName.equals("forums") || componentName.equals("wikis"))) {
                        componentsConfig.put(componentName, getConfigurationService().getComponentUrl("communities", request.isSecure()));
                    } else {
                        componentsConfig.put(componentName, getConfigurationService().getComponentUrl(componentName, request.isSecure()));

                    }
                } catch (ServiceException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                }
            }
        }
    }

    public Map<String, String> getComponentsConfig() {
        return componentsConfig;
    }

}
