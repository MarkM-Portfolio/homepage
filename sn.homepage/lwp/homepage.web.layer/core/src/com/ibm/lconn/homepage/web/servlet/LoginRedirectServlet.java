/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.servlet;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.ventura.internal.config.api.VenturaConfigurationProvider;
import com.ibm.ventura.internal.config.exception.VenturaConfigException;

/**
 * Acts as a protected resource to handle SPNEGO like systems.
 * Essentially, we need to redirect to this servlet from homepageAuth.jspf
 * in order for SPNEGO to work. Once the user has logged in, they'll be
 * redirected to this servlet, which will pass them on to the page they
 * originally requested.
 * @author Robert Campion
 */

public class LoginRedirectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4944768332523773819L;
	private final static String CLASS_NAME = LoginRedirectServlet.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private static final String PARAM_SERVICE_NAME = "service.name";
	private static final String REDIRECT_URL = "redirectUrl";

	protected String serviceName;

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

		// Set up the service name
		serviceName = config.getInitParameter(PARAM_SERVICE_NAME);
		if (serviceName == null)
			serviceName = config.getServletContext().getInitParameter(PARAM_SERVICE_NAME);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isLoggable(FINER)){
			logger.entering(CLASS_NAME, "doGet");
		}
		
		String redirectUrl = processRedirectUrl(request);

		// If the redirect URL is empty
		if(redirectUrl == null){
			try {
				VenturaConfigurationProvider provider = VenturaConfigurationProvider.Factory.getInstance();
				
				// Get the services URL
				URI uriBase = provider.getServiceURL(serviceName).toURI();
				// Redirect to the base (e.g. "/homepage")
				redirectUrl = uriBase.getPath();
			} catch (VenturaConfigException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			} catch (URISyntaxException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		if (logger.isLoggable(FINER)){
			logger.exiting(CLASS_NAME, "doGet", redirectUrl);
		}
		
		try {
			// Redirect to the redirect URL
			if ( logger.isLoggable(Level.FINE) ) {
				logger.logp(Level.FINE, CLASS_NAME, "doGet", "Redirecting to: " + redirectUrl);
			}
			response.sendRedirect(redirectUrl);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Process the redirectUrl to prevent malicious use.
	 * Need to ensure that the URL is:
	 * - within Homepage
	 * - not trying to get outside Homepage with use of ".."
	 * - not trying to redirect to the proxy
	 * @param request
	 * @return
	 */
	private String processRedirectUrl(HttpServletRequest request) {
		if ( logger.isLoggable(Level.FINER) ) {
			logger.entering(CLASS_NAME, "processRedirectUrl", request);
		}
		
		String redirectUrl = request.getParameter(REDIRECT_URL);
		
		// URLDecode the URL to catch obfuscated redirect urls
		String decodedUrl = null;
		try {
			decodedUrl = URLDecoder.decode(redirectUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// if it fails to decode, leave as null, and redirect to default
			if ( logger.isLoggable(Level.FINER) ) {
				logger.logp(Level.FINER, CLASS_NAME, "processRedirectUrl", "Error decoding url", e);
			}
		} 
		
		if ( decodedUrl == null || 
				decodedUrl.contains("..") ||
				decodedUrl.startsWith(request.getContextPath() + "/proxy") ||
				!decodedUrl.startsWith(request.getContextPath() + "/") ) {
			if ( logger.isLoggable(Level.FINE) ) {
				logger.logp(Level.FINE, CLASS_NAME, "processRedirectUrl", 
						"The requested redirect URL is not allowed: " + redirectUrl +
						", decoded Url: " + decodedUrl);
			}
			redirectUrl = null;
		}
		
		if ( logger.isLoggable(Level.FINER) ) {
			logger.exiting(CLASS_NAME, "processRedirectUrl", redirectUrl);
		}
		return redirectUrl;
	}
}
