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

import java.util.HashMap;
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
public class ShareStepPageController extends AbstractHomepageController {

    private static final long serialVersionUID = -7766040162331831199L;

    private static String CLASS_NAME = ShareStepPageController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    // In the share tab, there is a mapping between group of components.
    // We name the group by resource bundle key.
    // Map => { shareResourceKey, { componentName, URL} , ...}
    private Map<String, Map<String, String>> componentGroups;

    // Keys used to name the "group" of components in share tab
    // Important: group names are used in resource bundle:
    // jsp.start.share.component.headline.{groupName}
    // Use LinkedHashMap so that they are read in jsp in the order we add to map
    private static final Map<String, String> GROUP_STRUCTURE_MAPPING = new LinkedHashMap<String, String>();

    static {
        GROUP_STRUCTURE_MAPPING.put("profiles", "people");
        GROUP_STRUCTURE_MAPPING.put("communities", "collaborate");
        GROUP_STRUCTURE_MAPPING.put("activities", "documents");
        GROUP_STRUCTURE_MAPPING.put("blogs", "knowledge");
        GROUP_STRUCTURE_MAPPING.put("dogear", "links");
        GROUP_STRUCTURE_MAPPING.put("files", "documents");
        GROUP_STRUCTURE_MAPPING.put("forums", "ideas");
        GROUP_STRUCTURE_MAPPING.put("wikis", "ideas");
        GROUP_STRUCTURE_MAPPING.put("library", "library");
    }

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "executeInternal");
        }

        initComponentGroups(request);

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "executeInternal");
        }
        addPagedAttributesToModel(model, request);

        return ViewConstants.VIEW_STARTTAB_SHARESTEPPAGE;

    }

    protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {

        model.addAttribute("isPerson", com.ibm.lconn.core.web.auth.LCRestSecurityHelper.isUserInRole(request, "person"));
        model.addAttribute("componentGroups", getComponentGroups());

        super.addPagedAttributesToModel(model);
    }

    @RequestMapping(value = { "/shareStepPage.action" }, method = RequestMethod.GET)
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

    private void initComponentGroups(HttpServletRequest request) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "initComponentGroups");
        }

        // TODO: need to move this as static and cache once rather than for each
        // request
        componentGroups = new LinkedHashMap<String, Map<String, String>>();

        for (Map.Entry<String, String> bundleMapping : GROUP_STRUCTURE_MAPPING.entrySet()) {

            String componentName = bundleMapping.getKey();
            String groupName = bundleMapping.getValue();

            // we need two groups for Communities. One for Communities & one for libraries.
            // Because it's using map, cannot add two entries for Communities.
            // Use pseudo componentName of "library"
            if (componentName == "library") {
                componentName = "communities";
            }

            if (getConfigurationService().isComponentInstalled(componentName)) {
                try {
                    Map<String, String> groupConfig = componentGroups.get(groupName);

                    if (groupConfig == null) {
                        groupConfig = new HashMap<String, String>();
                        componentGroups.put(groupName, groupConfig);
                    }

                    groupConfig.put(componentName, getConfigurationService().getComponentUrl(componentName, request.isSecure()));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                }
            }
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "initComponentGroups");
        }
    }

    public Map<String, Map<String, String>> getComponentGroups() {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getComponentGroups");
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "getComponentGroups", componentGroups);
        }

        return componentGroups;
    }

}
