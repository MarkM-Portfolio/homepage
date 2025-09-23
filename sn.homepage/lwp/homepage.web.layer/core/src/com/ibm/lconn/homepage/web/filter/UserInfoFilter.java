// MOVED TO UserInfoInterceptor


/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

/*package com.ibm.lconn.homepage.web.filter;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.INFO;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;

import com.ibm.lotus.connections.dashboard.common.core.exceptions.service.DboardInfraException;
import com.ibm.lotus.connections.dashboard.common.util.ResourceBundleHelper;
import com.ibm.lotus.connections.dashboard.data.model.User;
import com.ibm.lotus.connections.dashboard.nls.IWebResources;
import com.ibm.lotus.connections.dashboard.service.core.internal.impl.UserServices;
import com.ibm.ventura.internal.config.api.VenturaConfigurationProvider;
import com.ibm.ventura.internal.config.exception.VenturaConfigException;
import com.ibm.ventura.internal.config.exception.VenturaConfigHelperException;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;

*//**
 * 
 * @author Liang Chen
 * 
 * UserInfoFilter checks the session of the current Login User Information. If
 * this session is an exsiting session -> Do nothing Else if the session is a
 * new session use Waltz to query the LDAP server and retrieve user infomation
 * and put them in session
 *//*
public class UserInfoFilter implements Filter {

	*//**
	 * Attributes names, will be saved in session
	 *//*
	public final static String USER_INFO_EMAIL = "user.info.email";

	public final static String USER_INFO_ID = "user.info.id";

	public final static String USER_INFO_NAME = "user.info.name";

	public final static String USER_INFO_INTERNAL_ID = "user.info.internal.id";

	private UserServices userService;

	
	private static final String DIRECTORY_SERVICE = "directory";
	private static final String WPI_ENABLED_ATTR = "[@profiles_directory_service_extension_enabled]";
	//private static final String WPI_ENABLED_ATTR = "[@waltz_profiles_integration_enabled]"; // 2.0.1 attribute name
	
	private boolean emailExposed = false;
	private boolean wpiEnabled = false;

	private final static String CLASS_NAME = UserInfoFilter.class.getName();

	private static ResourceBundleHelper resourceBundleHelper = new ResourceBundleHelper(
			IWebResources.RESOURCE_BUNDLE);

	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private FilterConfig filterConfig = null;
	
	*//**
	 * Obtain the user services service and also enquire as to whether 
	 * WPI is enabled.
	 * 
	 *//*
	public void init(FilterConfig config) throws ServletException {

		if(logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "init", config);
		}
		
		this.filterConfig = config;
		
		try {
			userService = UserServices.getInstance();
		} catch (DboardInfraException e) {
			// post 2.0 modification, reuse existing externalized string
			String error = resourceBundleHelper
					.getString("error.userpref.null.profileprovider");
			ServletException servletEx = new ServletException(error, e);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "init", error, servletEx);
			}
			throw servletEx;
		}

		VenturaConfigurationHelper helper;
		
		try {
			helper = VenturaConfigurationHelper.Factory.getInstance();
			emailExposed = helper.getExposeEmail();
		} catch (VenturaConfigHelperException e) {
			// post 2.0 modification, reuse existing externalized string
			String error = resourceBundleHelper
					.getString("error.serviceconfig");
			ServletException servletEx = new ServletException(error, e);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "init", error, servletEx);
			}
			throw servletEx;
		}
		
		// AWS - Should provide a way to get the state of WPI via VCH
		VenturaConfigurationProvider configurationProvider;
		try {
			configurationProvider = VenturaConfigurationProvider.Factory
					.getInstance();
			
			Configuration directoryConfig = configurationProvider.getServiceConfiguration("directory");
			String wpiEnabledStr = directoryConfig.getString(WPI_ENABLED_ATTR);
			if(wpiEnabledStr != null) {
				wpiEnabled = Boolean.parseBoolean(wpiEnabledStr);
				if(wpiEnabled) {
					if(logger.isLoggable(INFO)) {
						logger.logp(INFO, CLASS_NAME, "init", resourceBundleHelper.getString("info.wpi.on"));
					}
				} else {
					if(logger.isLoggable(INFO)) {
						logger.logp(INFO, CLASS_NAME, "init", resourceBundleHelper.getString("info.wpi.off"));
					}	
				}
			} else {
				logger.logp(FINER, CLASS_NAME, "init", "Could not retrieve enabled value for Profiles Directory Service Extenstion");
			}
		} catch (VenturaConfigException e) {
			String msg = resourceBundleHelper.getString("error.serviceconfig");
			ServletException t = new ServletException(
					msg, e);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "init", msg, t);
			}
			throw t;
		}
	
		if(logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "init");
		}

	}
	
	public void destroy() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "destroy");

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "destroy");
	}

	*//**
	 * Check whether the session is a new session or not. If it is new, this
	 * method will populate the session with user data, throwing the appropriate
	 * exceptions if there are problems obtaining that data.
	 * 
	 *//*
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "doFilter", new Object[] {
					servletRequest, servletResponse, filterChain });
		}

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String loginId = (String) request.getRemoteUser();

		if (loginId != null) {

			HttpSession session = request.getSession(true);

			if (session.isNew()) {
				if (logger.isLoggable(FINEST)) {
					logger.logp(FINEST, CLASS_NAME, "doFilter",
							"created a new session for user with login ID: " + loginId);
				}
				
				cacheUserInfo(loginId, session);
				
				// Used by admin JSP
				boolean isAdmin = request.isUserInRole("admin");
				session.setAttribute("isAdmin", isAdmin);
			} else {
				if (logger.isLoggable(FINEST)) {
					logger.logp(FINEST, CLASS_NAME, "doFilter", "user: "
							+ loginId + " has an exsiting session");
				}
			}
			
			if (logger.isLoggable(FINEST)) {
				logger.logp(FINEST, CLASS_NAME, "doFilter", "session ID is: "
						+ session.getId());
			}

			// redirect to next filter or servlet
			filterChain.doFilter(request, servletResponse);

		} else {
			if (logger.isLoggable(FINEST)) {
				logger.logp(FINEST, CLASS_NAME, "doFilter",
						"The current user's login has expired");
			}
			
			// redirect to the login page
			filterConfig.getServletContext().getRequestDispatcher("/auth/login.jsp").
		 	forward(servletRequest, servletResponse);
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "doFilter");
		}
	}


	*//**
	 * Use Waltz to retrieve the user info from LDAP or other sources And save
	 * it in session. If it is the first time for the user login it adds this
	 * info to the session
	 * 
	 * @param loginID The Login Id of current user
	 * @param session The current session
	 * 
	 * @throws ServletException
	 *//*
	private void cacheUserInfo(String loginID, HttpSession session)
			throws ServletException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "cacheUserInfo", new Object[] { loginID,
					session });
		}
		
		session.setAttribute(SessionValidationFilter.USER_LOGIN_ID, loginID);

		try {
			
			// Retrieve the user details from Waltz
			User user = userService.getUserByLoginName(loginID);
			
			if(user != null) {
				
				if(emailExposed) {
					if(logger.isLoggable(FINEST)) {
						logger.logp(FINEST, CLASS_NAME, "cacheUserInfo", "Email exposure enabled. Setting email address in session");
					}
					session.setAttribute(USER_INFO_EMAIL, user.getMail());
				}
	
				session.setAttribute(USER_INFO_ID, user.getExternalId());
				session.setAttribute(USER_INFO_NAME, user.getDisplayname());
				session.setAttribute(USER_INFO_INTERNAL_ID, user.getId());

			} else {
				
				// Find out if WPI is enabled - this would be useful to know for
				// the error message.
				String error;
				if(wpiEnabled) {
					error = resourceBundleHelper.getString(
						"error.retrieve.user.notvalid.wpi", loginID);
				} else {
					error = resourceBundleHelper.getString(
							"error.retrieve.user.notvalid", loginID);
				}
				
				ServletException servletEx = new ServletException(error);
				if (logger.isLoggable(SEVERE)) {
					logger.logp(SEVERE, CLASS_NAME, "doFilter", error, servletEx);
				}
				throw servletEx;		
			}
		} catch (DboardInfraException e) {
			String error;
			if(wpiEnabled) {
				error = resourceBundleHelper.getString(
						"error.retrieve.user.wpi", loginID, e.getMessage());
			} else {
				error = resourceBundleHelper.getString(
						"error.retrieve.user", loginID, e.getMessage());	
			}
			ServletException servletEx = new ServletException(error, e);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "doFilter", error, servletEx);
			}
			throw servletEx;
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "cacheUserInfo");
		}
	}

}*/
