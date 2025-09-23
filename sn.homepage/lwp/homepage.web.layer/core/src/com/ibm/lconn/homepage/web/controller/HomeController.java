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

package com.ibm.lconn.homepage.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.lconn.homepage.services.config.gettingstarted.GettingStartedBeanHelper;
import com.ibm.lconn.homepage.services.helper.DefaultHomepageTabHelper;
import com.ibm.lconn.homepage.services.helper.GettingStartedPreferenceHelper;
import com.ibm.lconn.homepage.utils.UrlDecoder;

@Controller
public class HomeController extends AbstractSessionAwareController {

    // Mappings of page names to actual URLs
    private static Map<String, String> pageUrls;

    static {
        pageUrls = new HashMap<String, String>();

        pageUrls.put("gettingStarted", "/web/gettingStarted/");
        pageUrls.put("updates", "/web/updates/");
        pageUrls.put("widgets", "/web/widgets/");
    }

    @RequestMapping(value = { "/login", "/login/" })
    public String handleLoginRequest(@RequestParam(value = "error", required = false) String error, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // LOGGER.debug(">");

        request.setAttribute("loginError", Boolean.valueOf(error));

        return ViewConstants.VIEW_LOGIN;
    }

    @RequestMapping(value = "/*")
    public String handleRequest(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (getActivityStreamConfigService().checkConnectionsNewUIEnabled(request)) {
            // do immediate redirection from this servlet
            String homepageState = getCookieValue(request.getCookies(), "ConnectionsHomepageState");
            if (StringUtils.isEmpty(homepageState)) {
                homepageState = "updates";
            }
 
            setPageUrl(getPageUrl(transformPage(request, homepageState)), request);
            return redirectToPage(transformPage(request, homepageState), request, response);
        }

        // use index.jsp for redirection logic in cnx7 case
        if (!DefaultHomepageTabHelper.isGettingStartedBypassed() && isGettingStartedDisplayEnabled(request) && !isGettingStartedUrlMode()) {
            // If the getting started page is enabled by the user
            return redirectToPage("gettingStarted", request, response);
        } else if (DefaultHomepageTabHelper.isWidgetsDefault()) {
            // Else if the widgets page is default
            return redirectToPage("widgets", request, response);
        } else {
            return ViewConstants.VIEW_INDEX;
        }
    }

    private String redirectToPage(String page, HttpServletRequest request, HttpServletResponse response) {
        String pageUrl = getPageUrl(page);

        if (StringUtils.equals(page, "updates")) {
            // add update page hash to show correct stream view
            System.out.println("show updates view " + getUpdatesPageHash(request));
            pageUrl += getUpdatesPageHash(request);
        }

        String redirectUrl = request.getContextPath() + pageUrl;
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    // ==============================================================================
    // GETTERS AND SETTERS FOR THE FIELDS
    // ==============================================================================

    private String getUpdatesPageHash(HttpServletRequest request) {
        String updatesPageHash = "";

        String asState = getCookieValue(request.getCookies(), "ConnectionsHomepageActivityStreamState");
        if (asState != null && asState.length() > 0) {
            updatesPageHash = "#" + asState;
        } else if (getActivityStreamConfigService().checkConnectionsNewUIEnabled(request)) {
            if (isOrientEnabled()) {
                updatesPageHash = "#topUpdates/topUpdates/all";
            } else {
                updatesPageHash = "#myStream/updates/all";
            }
        } else {
            updatesPageHash = "#myStream/imFollowing/all";
        }
        return updatesPageHash;
    }

    private String getPageUrl(String page) {
        // Get the available page URL
        String pageUrl = (String) pageUrls.get(page);

        // If it doesn't exist
        if (pageUrl == null) {
            // Default to the "updates" page
            pageUrl = (String) pageUrls.get("updates");
        }

        return pageUrl;
    }

    private void setPageUrl(String pageUrl, HttpServletRequest request) {
        if (pageUrl.equals((String) pageUrls.get("updates"))) {
            pageUrl += getUpdatesPageHash(request);
        }

        // Set the request attribute for this page URL
        request.setAttribute("ConnectionsHomepageState", pageUrl);
    }

    /**
     * Logic for transforming the page result under certain circumstances.
     * 
     * @param request
     * @param page
     * @return
     */
    private String transformPage(HttpServletRequest request, String page) {
        // If we're going to getting started but the checkbox is checked
        if (StringUtils.isNotEmpty(page) && page.equals("gettingStarted") && (DefaultHomepageTabHelper.isGettingStartedBypassed() || !isGettingStartedDisplayEnabled(request))) {
            // Ignore and redirect to the updates page instead
            return "updates";
        }

        return page;
    }

    /**
     * Is the getting started page enabled (i.e. checkbox not checked).
     * 
     * @param request
     * @return true if it is, false otherwise
     */
    private boolean isGettingStartedDisplayEnabled(HttpServletRequest request) {
        return GettingStartedPreferenceHelper.isGettingStartedDisplayEnabled((String) request.getSession().getAttribute("user.info.internal.id"), request.getRemoteUser());
    }

    private boolean isGettingStartedUrlMode() {
        return GettingStartedBeanHelper.isUrlMode();
    }
}