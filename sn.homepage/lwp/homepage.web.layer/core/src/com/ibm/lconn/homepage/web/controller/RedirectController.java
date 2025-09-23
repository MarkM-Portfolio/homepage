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
import static java.util.logging.Level.SEVERE;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.lconn.homepage.model.Component;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.utils.ResourceBundle;

@Controller
@RequestMapping(value = { "/web" })
public class RedirectController extends AbstractWebController {

    private static final long serialVersionUID = -3077391365144445509L;
    private final static String CLASS_NAME = RedirectController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);
    private static final String HOMEPAGE_COOKIE_NAME = "HomepageRedirect";

    @Autowired
    private ResourceBundle webResourceBundle;
    @Autowired
    private IConfigurationService configurationService;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public ResourceBundle getWebResourceBundle() {
        return webResourceBundle;
    }

    public void setWebResourceBundle(ResourceBundle webResourceBundle) {
        this.webResourceBundle = webResourceBundle;
    }

    @RequestMapping(value = { "/authredirect" }, method = RequestMethod.GET)
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

    /**
     * Send a 302 (moved temporarily) to redirect the user. Do not forward the request as we want a fresh request (one reason being the url in address bar)
     * 
     * @param response
     * @param location
     */
    private void redirectTo(String location, Cookie homepageCookie, HttpServletResponse response) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "redirectTo", new Object[] { response, location, homepageCookie });
        }

        response.setHeader("location", location);
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);

        // clear cookie (TODO: from Activity, check if really needed)
        if (homepageCookie != null) {
            homepageCookie.setMaxAge(0);
            homepageCookie.setPath("/");
            response.addCookie(homepageCookie);
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "redirectTo");
        }
    }

    private Cookie searchRedirectCookie(HttpServletRequest request) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "searchRedirectCookie", request);
        }

        Cookie homepageCookie = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(HOMEPAGE_COOKIE_NAME)) {
                    homepageCookie = cookie;
                    break;
                }
            }
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "searchRedirectCookie", homepageCookie);
        }

        return homepageCookie;
    }

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "handle", new Object[] { request, response });
        }

        Cookie homepageCookie = searchRedirectCookie(request);

        if (homepageCookie != null) {
            // Redirect to previous location set by the form based client side
            // utility
            // We cannot use the WAS cookie as it contains the URL that provoked
            // the
            // form based auth challenge (AJAX request, not url to the page)
            setUrl(homepageCookie.getValue());

            String msg = getWebResourceBundle().getString("info.redirect.form.auth", getUrl());
            logger.logp(FINER, CLASS_NAME, "handle", msg);

            redirectTo(getUrl(), homepageCookie, response);
        } else {
            // TODO: homepageCookie is null, redirect to context root from
            // Connections-Config.xml

            try {
                if (request.isSecure())
                    setUrl(getConfigurationService().getComponentUrl(Component.HOMEPAGE.getName(), true));
                else
                    setUrl(getConfigurationService().getComponentUrl(Component.HOMEPAGE.getName(), false));

                String msg = getWebResourceBundle().getString("info.redirect.nocookie", getUrl());
                logger.logp(FINER, CLASS_NAME, "handle", msg);
                redirectTo(getUrl(), null, response);

            } catch (ServiceException e) {
                String msg = getWebResourceBundle().getString("error.homepage.redirect.ventura");
                logger.logp(SEVERE, CLASS_NAME, "handle", msg);

                throw new ServletException(msg, e);
            }

        }

        return null;
    }

}
