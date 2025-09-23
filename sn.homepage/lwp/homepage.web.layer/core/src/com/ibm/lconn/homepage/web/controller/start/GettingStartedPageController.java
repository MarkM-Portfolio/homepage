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

import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.lconn.core.util.localization.ResourceBundleCache;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.config.gettingstarted.ComponentUrlInterpolator;
import com.ibm.lconn.homepage.services.config.gettingstarted.GettingStartedBeanHelper;
import com.ibm.lconn.homepage.services.config.gettingstarted.GettingStartedItem;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.controller.AbstractWidgetPageLayoutController;
import com.ibm.lconn.homepage.web.controller.ViewConstants;

@Controller
@RequestMapping(value = { "/web" })
public class GettingStartedPageController extends AbstractWidgetPageLayoutController {

    private static final long serialVersionUID = 4413138114841318624L;

    private static String CLASS_NAME = GettingStartedPageController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private String jsonUrl;

    private String welcomeMode;

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "executeInternal");
        }

        boolean isSecure = request.isSecure();
        jsonUrl = getJSONString(GettingStartedBeanHelper.getStartConfigurationData(), isSecure, request);

        welcomeMode = getUserServices().isUserInWelcomeMode(getPersonInternalId()) ? "" : "checked";

        updateSession(GlobalSessionConstants.USER_IS_GETTING_STARTED_PAGE_SHOWN, Boolean.TRUE);

        // Init widgets start
        setupWidgetInstances(request);
        setupJavalinAttributes();
        setupActionRequiredTotal();
        setupASConfig(request);

        prepareWidgetPreferences();
        // Init widgets end

        if (!getActivityStreamConfigService().checkConnectionsNewUIEnabled(request)) {
            setHomepageStateCookie("gettingStarted", request, response);
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "OutputJsonUrl: ", new Object[] { jsonUrl });
        }
        setNonce(request, response);
        addPagedAttributesToModel(model, request);

        return ViewConstants.VIEW_HOMEPAGE_MAIN_GETTINGSTARTED;
    }

    protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {
        // set pagination values
        model.addAttribute("isPerson", com.ibm.lconn.core.web.auth.LCRestSecurityHelper.isUserInRole(request, "person"));
        model.addAttribute("jsonUrl", getJsonUrl());
        model.addAttribute("welcomeMode", getWelcomeMode());

        super.addPagedAttributesToModel(model);
    }

    @RequestMapping(value = { "/gettingStarted/" }, method = RequestMethod.GET)
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

    public String getJsonUrl() {
        return jsonUrl;
    }

    public String getWelcomeMode() {
        return welcomeMode;
    }

    /**
     * Get JSON string from getting started items
     * 
     * @return String JSON string
     */

    private String getJSONString(Set<GettingStartedItem> items, boolean isSecure, HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();
        sb.append("{tiles:[");

        if (items != null) {

            Iterator<GettingStartedItem> iterator = items.iterator();
            if (iterator.hasNext()) {
                GettingStartedItem item = iterator.next();
                while (item != null) {
                    String itemUrl = isSecure ? item.getSecureUrl() : item.getUrl();
                    itemUrl = ComponentUrlInterpolator.convertGettingStartConfigToUrl(itemUrl);
                    sb.append("{title:'").append(getTitleString(item.getTitle(), item.getBundleName(), request)).append("', url: '").append(itemUrl).append("'}");
                    if (iterator.hasNext()) {
                        sb.append(",");
                        item = iterator.next();
                    } else {
                        item = null;
                    }
                }
            }

        }

        sb.append("]}");
        return sb.toString();

    }

    private String getTitleString(String titleKey, String bundleName, HttpServletRequest request) {
        String result = null;
        try {
            ResourceBundle bundle = ResourceBundleCache.getBundle(bundleName, getLocale(request), null);
            result = bundle.getString(titleKey);
        } catch (Exception e) {
            String msg = getWebResourceBundle().getString("error.gettingstarted.title", titleKey, bundleName);
            logger.logp(FINER, CLASS_NAME, "getTitleString", msg);
        }

        return result != null ? result : titleKey;

    }

}
