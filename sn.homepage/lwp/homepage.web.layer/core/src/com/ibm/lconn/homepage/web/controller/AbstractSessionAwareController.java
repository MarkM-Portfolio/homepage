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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ibm.connections.highway.common.api.HighwayException;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.lconn.core.web.exception.AuthorizationException;
import com.ibm.lconn.core.web.secutil.CSRFHelper;
import com.ibm.lconn.core.web.util.CookieHelper;
import com.ibm.lconn.homepage.services.IHomepageActionRequiredService;
import com.ibm.lconn.homepage.services.activitystream.config.IConfigService;
import com.ibm.lconn.homepage.utils.UrlDecoder;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.UserInfo;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;

public abstract class AbstractSessionAwareController extends AbstractPrincipalAwareController {

    private static final String CONNECTIONS_HOMEPAGE_STATE = "ConnectionsHomepageState";
    private static final String CONNECTIONS_HOMEPAGE_AS_STATE = "ConnectionsHomepageActivityStreamState";
    private static final long serialVersionUID = -7791538887502810672L;
    private final static String CLASS_NAME = AbstractSessionAwareController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);
    @Autowired
    private IHomepageActionRequiredService homepageActionRequiredService;
    private String actionRequiredTotal;
    protected static final String UPDATE_NONCE_ATTRIBUTE = "X-Update-Nonce";

    private String activityStreamConfig;
    @Autowired
    private IConfigService activityStreamConfigService;
    private String nonce;
    private boolean cnxNewUiEnabled;
	
	private static ArrayList<String> allowedStartViews;
	private VenturaConfigurationHelper vch = VenturaConfigurationHelper.Factory.getInstance();

    protected String getPersonInternalId() throws WebException {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getPersonInternalId");

        String personId = (String) getSession().get(GlobalSessionConstants.USER_INFO_INTERNAL_ID);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getPersonInternalId", personId);

        if (personId == null)
            throw new WebException("personId not contained in session");

        return personId;
    }

    protected void addPagedAttributesToModel(Model model) {
        model.addAttribute("actionRequiredTotal", getActionRequiredTotal());
        model.addAttribute("session", getSession());
        model.addAttribute("activityStreamConfig", getActivityStreamConfig());
        model.addAttribute("nonce", getNonce());
        model.addAttribute("xUpdateNonce", getNonce());
        model.addAttribute("cnxNewUIEnabled", isCnxNewUIEnabled());

        super.addPagedAttributesToModel(model);
    }

    protected String getCookieValue(Cookie[] cookies, String cookieName) {
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];

                // Compare it to the cookie name passed
                if (cookie.getName().equals(cookieName)) {
                    // If found, return the value
                    return UrlDecoder.decode(cookie.getValue());
                }
            }
        }

        return null;
    }
	
	protected JSONObject getConfiguredStartViewJSONResponse(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        
        JSONArray allowedStartViews = new JSONArray();
        for (String view : getAllowedStartViews()) {
        	allowedStartViews.add(view);
        }
        jsonObject.put("views", allowedStartViews);
        
        String homepageState = getCookieValue(request.getCookies(), "ConnectionsHomepageState");
       
        if (StringUtils.equals(homepageState, "widgets")) {
        	jsonObject.put("configuredView", "myPage");
        	return jsonObject;
        }
        
        // check which exact updates view is selected via 2nd cookie
        String activityStreamState = getCookieValue(request.getCookies(), "ConnectionsHomepageActivityStreamState");
        if (StringUtils.contains(activityStreamState, "topUpdates/")) {
        	jsonObject.put("configuredView", "topUpdates");
        } else if (StringUtils.contains(activityStreamState, "myStream/")) {
        	jsonObject.put("configuredView", "latestUpdates");
        } else if (StringUtils.contains(activityStreamState, "discover/")) {
        	jsonObject.put("configuredView", "discover");
        } else {
        	// use default 
			if (isOrientEnabled()) {
	        	jsonObject.put("configuredView", "topUpdates");
			} else {
	        	jsonObject.put("configuredView", "latestUpdates");
			}
        }
        return jsonObject;
	}
	
	protected ArrayList<String> getAllowedStartViews() {
		if (allowedStartViews == null) {
			allowedStartViews = new ArrayList<String>();
			if (isOrientEnabled()) {
				allowedStartViews.add("topUpdates");
			}
			allowedStartViews.add("latestUpdates");
			allowedStartViews.add("discover");
			allowedStartViews.add("myPage");
			
		}
		return allowedStartViews;
	}
	
	protected boolean isOrientEnabled() {		
		return isServiceEnabled("orient");
	}
	
	protected boolean isServiceEnabled(String serviceName) {		
		ComponentEntry component = vch.getComponentConfig(serviceName);
		return ( component != null && ( component.isUrlEnabled() || component.isSecureUrlEnabled() ) );
	}
	
	protected boolean enabled(String appId) {
		return isServiceEnabled(appId);
	}

    protected String getPersonExternalId() throws WebException {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getPersonExternalId");

        String personId = (String) getSession().get(GlobalSessionConstants.USER_INFO_EXTERNAL_ID);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getPersonExternalId", personId);

        if (personId == null)
            throw new WebException("External personId not contained in session");

        return personId;
    }

    protected Boolean isPersonBoardEnabled() throws WebException {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "isPersonBoardEnabled");

        Boolean boardEnabled = (Boolean) getSession().get(GlobalSessionConstants.USER_BOARD_ENABLED);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "isPersonBoardEnabled", boardEnabled);

        if (boardEnabled == null)
            throw new WebException("isPersonBoardEnabled not contained in session");

        return boardEnabled;
    }

    protected String getPersonDisplayName() throws WebException {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getPersonDisplayName");

        String personName = (String) getSession().get(GlobalSessionConstants.USER_INFO_NAME);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getPersonDisplayName", personName);

        if (personName == null)
            throw new WebException("External personName not contained in session");
        return personName;
    }

    protected String getPersonExternalOrgId() throws WebException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getPersonOrgId");
        }

        String orgId = (String) getSession().get(GlobalSessionConstants.USER_INFO_ORGID);

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "getPersonOrgId", orgId);
        }

        if (orgId == null) {
            throw new WebException("Users organization id not contained in sesssion");
        }

        return orgId;
    }

    protected String getPersonEmail() throws WebException {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getPersonEMail");

        String personEMail = (String) getSession().get(GlobalSessionConstants.USER_INFO_EMAIL);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getPersonEMail", personEMail);

        return personEMail;
    }

    protected boolean isPersonExternal() throws WebException {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getPersonEMail");

        Boolean personIsExternal = (Boolean) getSession().get(GlobalSessionConstants.USER_IS_EXTERNAL);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getPersonEMail", personIsExternal);

        return personIsExternal;
    }

    @SuppressWarnings("unchecked")
    protected void updateSession(Object key, Object value) {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "updateSession", new Object[] { key, value });

        getSession().put((String) key, value);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "updateSession");
    }

    protected void setupActionRequiredTotal() {
        String personId = (String) getSession().get(GlobalSessionConstants.USER_INFO_EXTERNAL_ID);
        Long l = getHomepageActionRequiredService().getActionRequiredTotalByExtId(personId);
        setActionRequiredTotal(l.toString());
    }

    public IHomepageActionRequiredService getHomepageActionRequiredService() {
        return homepageActionRequiredService;
    }

    public void setHomepageActionRequiredService(IHomepageActionRequiredService homepageActionRequiredService) {
        this.homepageActionRequiredService = homepageActionRequiredService;
    }

    public String getActionRequiredTotal() {
        return actionRequiredTotal;
    }

    public void setActionRequiredTotal(String actionRequiredTotal) {
        this.actionRequiredTotal = actionRequiredTotal;
    }

    protected void setHomepageStateCookie(String pageName, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieHelper.addHttpOnlyCookie(request, response, CONNECTIONS_HOMEPAGE_STATE, pageName, request.getContextPath());
    }

    protected void setHomepageASStateCookie(String streamName, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieHelper.addCookie(request, response, CONNECTIONS_HOMEPAGE_AS_STATE, streamName, request.getContextPath());
    }

    protected void setupASConfig(HttpServletRequest request) throws WebException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setupASConfig");
        }

        // TODO remove or alter this code once we have a proper indicator whether the current user wants to use old or new ui
        cnxNewUiEnabled = getActivityStreamConfigService().checkConnectionsNewUIEnabled(request);

        UserInfo user = getUserInfo();
        // Get the config object from the service and set it to the local jsonObject
        activityStreamConfig = getActivityStreamConfigService().getConfigObject(getLocale(request), user).toString();

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setupASConfig");
        }
    }

    public Locale getLocale(HttpServletRequest request) {
        return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
    }

    /**
     * @return The UserInfo object for the current request user.
     * @throws WebException
     */
    protected UserInfo getUserInfo() throws WebException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getUserInfo");
        }

        UserInfo user = new UserInfo();
        user.setUserDisplayName(getPersonDisplayName());
        user.setUserId(getPersonExternalId());
        user.setUserOrg(getPersonExternalOrgId());
        user.setIsExternal(isExternalUser(user));

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "getUserInfo", user);
        }
        return user;
    }

    /**
     * Check highway policy canViewOrganizationEvents first if not in effect fallback check if the user is external
     * 
     * @return If user is external or not
     * @throws HighwayException
     */
    public Boolean isExternalUser(UserInfo user) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "isExternal", user);
        }

        boolean isExternal = false;
        try {

            if (getSession().containsKey(GlobalSessionConstants.HIGHWAY_USER_CAN_VIEW_ORG_EVENT)) {
                isExternal = (Boolean) getSession().get(GlobalSessionConstants.HIGHWAY_USER_CAN_VIEW_ORG_EVENT);
            }
            // user has highway policy to allow orgEvents - secondary check if they are external users
            if (!isExternal && isPersonExternal()) {
                isExternal = true;
            }
        } catch (Exception e) {
            logger.logp(SEVERE, CLASS_NAME, "isExternalUser", e.getLocalizedMessage(), e);
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "isExternal", isExternal);
        }
        return isExternal;
    }

    public String setNonce(HttpServletRequest request, HttpServletResponse response) {
        String nonce = UUID.randomUUID().toString();
        this.getSession().put(UPDATE_NONCE_ATTRIBUTE, nonce);
        Cookie cookie = CookieHelper.addCookie(request, response, UPDATE_NONCE_ATTRIBUTE, nonce);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        this.setNonce(nonce);
        return nonce;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * Anti Cross-site request forgery check from CSRFHelper
     *
     * @return
     */
    public boolean isCSRFSafe(HttpServletRequest request, HttpServletResponse response) {
        try {
            CSRFHelper.isSafe(request, response);
        } catch (AuthorizationException se) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.logp(Level.FINEST, CLASS_NAME, "executeInternal", "CSRF attack, the request no X-Update-Nonce in header");
            }
            return false;
        }

        return true;
    }

    public String getActivityStreamConfig() {
        return activityStreamConfig;
    }

    public void setActivityStreamConfig(String asConfig) {
        this.activityStreamConfig = asConfig;
    }

    public boolean isCnxNewUIEnabled() {
        return cnxNewUiEnabled;
    }

    public IConfigService getActivityStreamConfigService() {
        return this.activityStreamConfigService;
    }

    public void setActivityStreamConfigService(IConfigService activityStreamConfigService) {
        this.activityStreamConfigService = activityStreamConfigService;
    }

}
