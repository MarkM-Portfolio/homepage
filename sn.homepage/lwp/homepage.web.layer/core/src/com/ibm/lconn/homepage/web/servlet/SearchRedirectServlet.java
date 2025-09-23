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

package com.ibm.lconn.homepage.web.servlet;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.model.Component;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.ServiceException;

public class SearchRedirectServlet extends HttpServlet {

	private final static String CLASS_NAME = SearchRedirectServlet.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private static final long serialVersionUID = 330590892028873710L;
	@Autowired
	private IConfigurationService configurationService;
	
	
	protected void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	protected IConfigurationService getConfigurationService() {
		return configurationService;
	}
	
	public void init() throws ServletException {
		super.init();

		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.entering(CLASS_NAME, "init");
		
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		setConfigurationService((IConfigurationService)context.getBean("configurationService"));

		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.exiting(CLASS_NAME, "init");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRedirect(req, resp);		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRedirect(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRedirect(req, resp);
	}
	
	private void handleRedirect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "handleRedirect", new Object[] { req, resp });
		}
		String redirectUrl = "";
		
		if(getConfigurationService()==null) {
			resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		} else {
			try {
				if(getConfigurationService().isComponentInstalled(Component.SEARCH.getName())) {
					String searchUrl = getConfigurationService().getComponentUrl(Component.SEARCH.getName(), req.isSecure());
					String uri = req.getRequestURI().replaceFirst(req.getContextPath(), "");
					if(req.getQueryString()!=null)
						redirectUrl = searchUrl + uri + "?" + req.getQueryString();
					else
						redirectUrl = searchUrl + uri;
					
					resp.setHeader("location", redirectUrl);
					resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				} else {
					resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
				}
				
			} catch (ServiceException e) {
				resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
			}
			
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "handleRedirect", redirectUrl);
		}
	}
	
}
