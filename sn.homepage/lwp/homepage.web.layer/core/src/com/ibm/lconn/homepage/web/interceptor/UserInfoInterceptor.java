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
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.connections.highway.client.api.HighwayAdminClient;
import com.ibm.connections.highway.client.api.HighwaySetup;
import com.ibm.connections.highway.common.api.HighwayUserSessionInfo;
import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.RuntimeWebException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;

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
public class UserInfoInterceptor extends AbstractHomepageInterceptor implements HandlerInterceptor {

    private static final String CAN_VIEW_ORGANIZATION_EVENTS = "news.allow.viewOrganizationEvents";

    private static final long serialVersionUID = -6993484475591597821L;

    @Autowired
    private IUserServices userServices;

    private boolean emailExposed = false;
    private boolean wpiEnabled = false;
    private static final long ONE_HOUR = 3600000L;

    @Autowired
    private IConfigurationService configurationService;

    private final static String CLASS_NAME = UserInfoInterceptor.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        init();

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

    /**
     * Obtain the user services service and also enquire as to whether WPI is enabled.
     * 
     **/
    public void init() throws RuntimeWebException {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "init");
        }

        emailExposed = getConfigurationService().getExposeEmail();

        try {
            wpiEnabled = getConfigurationService().getWpiEnabled();
            if (wpiEnabled) {
                if (logger.isLoggable(INFO)) {
                    logger.logp(INFO, CLASS_NAME, "init", getWebResourceBundle().getString("info.wpi.on"));
                }
            } else {
                if (logger.isLoggable(INFO)) {
                    logger.logp(INFO, CLASS_NAME, "init", getWebResourceBundle().getString("info.wpi.off"));
                }
            }
        } catch (ServiceException ex) {
            String msg = getWebResourceBundle().getString("error.serviceconfig");
            RuntimeWebException t = new RuntimeWebException(msg, ex);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "init", msg, t);
            }
            throw t;
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

        ApplicationContext.setOrganizationId();
        String requestOrgID = ApplicationContext.getOrganizationId();

        String loginId = (String) request.getRemoteUser();

        // Should not happen but just in case have a fall-back to EN
        String language = (request.getLocale() != null && request.getLocale().toString() != null) ? request.getLocale().toString().toLowerCase() : Locale.ENGLISH.getLanguage();
        boolean result = false;

        if (loginId != null) {
            if (!isUserInfoInSession(loginId, requestOrgID, request)) {
                if (logger.isLoggable(FINEST)) {
                    logger.logp(FINEST, CLASS_NAME, "interceptInternal", "created a new session for user with login ID: " + loginId);
                }

                // We will let the abstract class catch any exceptions
                cacheUserInfo(loginId, requestOrgID, language, request);

                // Used by admin JSP
                boolean isAdmin = request.isUserInRole("admin");
                request.getSession().setAttribute("isAdmin", isAdmin);
            } else {
                if (logger.isLoggable(FINEST)) {
                    logger.logp(FINEST, CLASS_NAME, "interceptInternal", "user: " + loginId + " has an exsiting session");
                }
                String userId = (String) request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID);

                // If language has changed update the db value
                String currentLang = (String) request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_LANG);
                if (currentLang != null && !language.equals(currentLang)) {
                    updateLanguageField(userId, language);
                    request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_LANG, language);
                }
                updateLastVisit(userId, request);

            }

            result = true;
            ApplicationContext.unset();

        } else {
            if (logger.isLoggable(FINEST)) {
                logger.logp(FINEST, CLASS_NAME, "interceptInternal", "The current user's login has expired");
            }

            // result = Action.LOGIN;
            // TODO Make sure it is ok to remove Action.LOGIN and replace it with Action.SUCCESS.
            // This was added to persist the hash url through logging. Removing it reverts to the
            // login page straight away.
            result = false;

            String redirectUrl = request.getContextPath() + "/login/";
            try {
                response.sendRedirect(redirectUrl);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "interceptInternal", result);
        }

        return result;
    }

    /**
     * Update the lastvisit time for the user if one hour has expired since last update
     * 
     * @param sessionAttr
     * @param userId
     */
    private void updateLastVisit(String userId, HttpServletRequest request) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "updateLastVisit");
        }
        Long lastUpdate = (Long) request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_TIMESTAMP);
        if (lastUpdate == null || (new Date().getTime() - lastUpdate) > ONE_HOUR) {
            updateLastVisitField(userId);
            request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_TIMESTAMP, new Date().getTime());
        }
        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "updateLastVisit");
        }
    }

    /**
     * Check that the user info from waltz are already stored as attributes of the session
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    private boolean isUserInfoInSession(String loginId, String orgId, HttpServletRequest request) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "isUserInfoInSession");
        }

        boolean result = false;

        String previousSessionOrgId = (String) request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_ORGID);
        if (previousSessionOrgId != null && orgId != null) {
            if (!previousSessionOrgId.equals(orgId)) {
                clearSessionInfo(request);
                return result;
            }

        }

        String sessionLoginId = (String) request.getSession().getAttribute(GlobalSessionConstants.USER_LOGIN_ID);
        if (sessionLoginId != null) {

            if (!loginId.equalsIgnoreCase(sessionLoginId)) {
                clearSessionInfo(request);
                result = false;
            } else {
                result = true;
            }

        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "isUserInfoInSession", result);
        }

        return result;
    }

    @SuppressWarnings("rawtypes")
    private void clearSessionInfo(HttpServletRequest request) {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "clearSessionInfo");
        }
        request.getSession().removeAttribute(GlobalSessionConstants.USER_INFO_EXTERNAL_ID);
        request.getSession().removeAttribute(GlobalSessionConstants.USER_INFO_NAME);
        request.getSession().removeAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID);
        request.getSession().removeAttribute(GlobalSessionConstants.USER_BOARD_ENABLED);
        request.getSession().removeAttribute(GlobalSessionConstants.USER_INFO_LANG);
        request.getSession().removeAttribute(GlobalSessionConstants.USER_INFO_TIMESTAMP);
        request.getSession().removeAttribute(GlobalSessionConstants.USER_IS_EXTERNAL);

        if (request.getSession().getAttribute("isAdmin") != null) {
            request.getSession().removeAttribute("isAdmin");
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "clearSessionInfo");
        }

    }

    /**
     * Use Waltz to retrieve the user info from LDAP or other sources And save it in session. If it is the first time for the user login it adds this info to the session
     * 
     * @param loginID
     *            The Login Id of current user
     * @param session
     *            The current session
     * 
     * @throws WebException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void cacheUserInfo(String loginID, String requestOrgId, String lang, HttpServletRequest request) throws WebException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "cacheUserInfo", new Object[] { loginID });
        }

        request.getSession().setAttribute(GlobalSessionConstants.USER_LOGIN_ID, loginID);

        try {
            // Retrieve the user details from Waltz
            User user = userServices.getUserLoginName(loginID);

            if (user != null) {

                if (emailExposed) {
                    if (logger.isLoggable(FINEST)) {
                        logger.logp(FINEST, CLASS_NAME, "cacheUserInfo", "Email exposure enabled. Setting email address in session");
                    }
                    request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_EMAIL, user.getMail());
                }
                request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_ORGID, requestOrgId);
                request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_EXTERNAL_ID, user.getExternalId());
                request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_NAME, user.getDisplayname());
                request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID, user.getId());
                request.getSession().setAttribute(GlobalSessionConstants.USER_BOARD_ENABLED, user.isBoardEnabled());
                request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_LANG, lang);
                request.getSession().setAttribute(GlobalSessionConstants.USER_INFO_TIMESTAMP, new Date().getTime());
                request.getSession().setAttribute(GlobalSessionConstants.USER_IS_EXTERNAL, user.getIsExternal());

                updateLastVisitAndLanguageField(user.getId(), lang);

                try {
                    HighwayUserSessionInfo userSession = HighwaySetup.createUserSessionInfo(user.getExternalId(), user.getOrgId());
                    HighwayAdminClient hc = HighwaySetup.getHighwayAdminClient("homepage");
                    if (hc.getSetting(userSession, CAN_VIEW_ORGANIZATION_EVENTS) != null) {
                        request.getSession().setAttribute(GlobalSessionConstants.HIGHWAY_USER_CAN_VIEW_ORG_EVENT, !Boolean.valueOf((String) hc.getSetting(userSession, CAN_VIEW_ORGANIZATION_EVENTS)));
                    }

                } catch (Exception e) {
                    logger.logp(SEVERE, CLASS_NAME, "isExternalUser", e.getLocalizedMessage(), e);
                }

            } else {

                if (logger.isLoggable(FINEST)) {
                    logger.logp(FINEST, CLASS_NAME, "cacheUserInfo", "User not found by UserServices");
                }

                String error = getUserRetrieveError(wpiEnabled, loginID);
                WebException t = new WebException(error);
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "cacheUserInfo", error, t);
                }
                throw t;
            }
        } catch (ServiceException e) {
            String error = getUserRetrieveError(wpiEnabled, loginID, e);
            WebException t = new WebException(error, e);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "cacheUserInfo", error, t);
            }
            throw t;
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "cacheUserInfo");
        }
    }

    /**
     * Updates the person table with the timestamp of this visit.
     * 
     * @param userInternalId
     * @throws ServletException
     */
    protected void updateLastVisitField(String userInternalId) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "updateLastVisitField", userInternalId);
        }

        getUserServices().updateLastVisitForUser(userInternalId);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "updateLastVisitField");
    }

    /**
     * Updates the HP_UI table with the timestamp and browser lang of this visit.
     * 
     * @param userInternalId
     * 
     * @throws ServletException
     */
    protected void updateLastVisitAndLanguageField(String userInternalId, String language) {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "updateLastVisitAndLanguageField", new Object[] { userInternalId, language });

        if (userInternalId != null) {
            getUserServices().updateLastVisitAndLanguageForUser(userInternalId, language);
        }
        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "updateLastVisitAndLanguageField");
    }

    /**
     * Updates the HP_UI table with the browser lang settings.
     * 
     * @param userInternalId
     * 
     * @throws ServletException
     */
    protected void updateLanguageField(String userInternalId, String language) {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "updateLanguageField", new Object[] { userInternalId, language });

        if (userInternalId != null) {
            getUserServices().updateLanguageForUser(userInternalId, language);
        }
        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "updateLanguageField");
    }

    private String getUserRetrieveError(boolean wpi, String loginId, Object... params) {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getUserRetrieveError", new Object[] { wpi, loginId, params });
        }
        // Find out if WPI is enabled - this would be useful to know for
        // the error message.
        String key = (wpi == true ? "error.retrieve.user.wpi" : "error.retrieve.user");
        String error = "";
        try {

            if (params != null && params.length > 0) {
                error = getWebResourceBundle().getString(key, loginId, params);
            } else {
                error = getWebResourceBundle().getString(key, loginId);
            }

            return error;
        } catch (Exception ex) {
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "getUserRetrieveError", "error retrieving resource [" + key + "]", ex);
            }
            error = "error retrieving resource [" + key + "]";
            return error;
        } finally {
            if (logger.isLoggable(FINER)) {
                logger.exiting(CLASS_NAME, "getUserRetrieveError", error);
            }
        }

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

    public void setConfigurationService(IConfigurationService configurationService) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setConfigurationService", configurationService);
        }

        this.configurationService = configurationService;

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setConfigurationService");
        }

    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }
}
