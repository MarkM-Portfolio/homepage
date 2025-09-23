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
import static java.util.logging.Level.FINEST;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;

public class GettingStartedInterceptor extends AbstractHomepageInterceptor {

    private static final long serialVersionUID = 7481740063683409202L;

    public static final String GETTING_STARTED_RESULT = "getting_started";

    private final static String CLASS_NAME = GettingStartedInterceptor.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private IUserServices userServices;

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

    protected boolean interceptInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "interceptInternal");
        }

        String result;

        String internalUserId = getInternalUserId(request);

        if (internalUserId != null) {

            boolean welcomeMode = userServices.isUserInWelcomeMode(internalUserId);

            boolean welcomeScreenNeedDisplay = request.getSession().getAttribute(GlobalSessionConstants.USER_IS_GETTING_STARTED_PAGE_SHOWN) == null;

            if (welcomeMode && welcomeScreenNeedDisplay) {
                result = GETTING_STARTED_RESULT;
            } else {
                result = null;
            }
        } else {
            if (logger.isLoggable(FINEST)) {
                logger.logp(FINEST, CLASS_NAME, "interceptInternal", "There is no user info in the session");
            }
            // redirect to the login page
            return false;

        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "interceptInternal", result);
        }

        return true;

    }

    private String getInternalUserId(HttpServletRequest request) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getInternalUserId");
        }

        String internalUserId = null;

        internalUserId = (String) request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID);

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "getInternalUserId", internalUserId);
        }

        return internalUserId;

    }

    public IUserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(IUserServices userSvc) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setUserSvc", userSvc);
        }

        this.userServices = userSvc;

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setUserSvc");
        }
    }

}
