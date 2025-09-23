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

package com.ibm.lconn.homepage.web.interceptor;

import static java.util.logging.Level.FINER;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.RuntimeWebException;

/**
 * Based on old UserInfoFilter. Just changing the structure for now to Struts Interceptor.
 * 
 * 
 * TODO: We might want to review the logic here: - Place a Person bean in session instead of 4 different string attributes - Inject Person bean in associated action instead of using the session
 * (PersonAware)
 * 
 * @author vincent
 * 
 */
public class ExternalUserWidgetInfoInterceptor extends AbstractHomepageInterceptor implements HandlerInterceptor {

    private static final long serialVersionUID = -6993484475591597821L;

    private final static String CLASS_NAME = ExternalUserWidgetInfoInterceptor.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private IConfigurationService configurationService;

    protected IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Obtain the user services service and also enquire as to whether WPI is enabled.
     * 
     **/
    public void init() throws RuntimeWebException {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "init");
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "init");
        }

    }

    /**
     * Check whether the session is a new session or not. If it is new, this method will populate the session with user data, throwing the appropriate exceptions if there are problems obtaining that
     * data.
     * 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected boolean interceptInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "interceptInternal");
        }

        String result;
        Boolean isExternal = false;
        isExternal = (Boolean) request.getSession().getAttribute(GlobalSessionConstants.USER_IS_EXTERNAL);

        // Get the url of the referrer
        String referrer = request.getHeader("referer");
        if (referrer == null) {
            // if the referrer is null get the url from getRequestURL - for example when url typed in
            referrer = request.getRequestURL().toString();
        }

        boolean shouldHandle = true;

        // get homepage url from config for both secure and nonsecure
        String componentSecureURL = getConfigurationService().getComponentUrl("homepage", true);
        String componentURL = getConfigurationService().getComponentUrl("homepage", false);

        Boolean retVal;
        // Check if the referrer url ends with either homepage or homepage/
        retVal = referrer.equals(componentSecureURL) || referrer.equals(componentSecureURL + "/") || referrer.equals(componentURL) || referrer.equals(componentURL + "/");

        if (isExternal != null && isExternal.booleanValue() && retVal != null && retVal) {
            // Redirect to im following view
            response.sendRedirect(referrer + "web/updates/#myStream/imFollowing/all");
            shouldHandle = false;
        } else if (isExternal != null && isExternal.booleanValue() && !retVal) {
            // Show access denied message - for example when trying to hit /web/widgets/ directly
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            shouldHandle = false;
        } else {
            shouldHandle = true;
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "interceptInternal", shouldHandle);
        }
        return shouldHandle;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "intercept");

        try {
            return interceptInternal(request, response);
        } catch (Throwable ex) {

            if (logger.isLoggable(Level.SEVERE)) {
                String msg = getWebResourceBundle().getString("error.action.error.general");
                logger.log(Level.SEVERE, msg, ex);
            }

            return false;
        } finally {
            if (logger.isLoggable(FINER))
                logger.exiting(CLASS_NAME, "intercept");
        }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {
    }

}
