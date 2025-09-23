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

package com.ibm.lconn.homepage.web.filter;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;

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

/**
 * 
 * @author Liang Chen
 * 
 * This filter checks the validation of session
 * If the user is no longer login to homepage 
 * OR
 * If the login user doesn't match the user id in the session
 * Then the current session will be invalidated
 *
 */
public class SessionValidationFilter implements Filter {
	public final static String USER_LOGIN_ID = "user.login.id";
	
	private final static String CLASS_NAME = SessionValidationFilter.class.getName();
	private static Logger logger=Logger.getLogger(CLASS_NAME);
	
	public void destroy() {
		logger = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		String method = "doFilter";
		if (logger.isLoggable(FINER)){
			logger.entering(CLASS_NAME, method, new Object[]{request, response, filterChain});
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String loginUserID = httpRequest.getRemoteUser();
		HttpSession session = httpRequest.getSession(true);

		if(loginUserID!=null){
							
			String loginIDFromSession = (String)session.getAttribute(USER_LOGIN_ID);
			if((loginIDFromSession==null)||(!loginUserID.equals(loginIDFromSession))){
				if (logger.isLoggable(FINEST)){
					logger.logp(FINEST, CLASS_NAME, method, 
							"The current login user doesn't match the one in the session, " +
							"invalidate the session now...");
				}
				session.invalidate();
		
			}else{
				if (logger.isLoggable(FINEST)){
					logger.logp(FINEST, CLASS_NAME, method, "the user has previously logged in," +
							"and his info should already in the session");
				}
			}

		}else{
			
			if (logger.isLoggable(FINEST)){
				logger.logp(FINEST, CLASS_NAME, method, "The current user's login has expired, " +
						"invalidate the session now...");
			}
			session.invalidate();
		}
		
		//next filter
		filterChain.doFilter(request, response);
		
		if (logger.isLoggable(FINER)){
			logger.exiting(CLASS_NAME, method);
		}
	}

	public void init(FilterConfig arg0) throws ServletException{}

}
