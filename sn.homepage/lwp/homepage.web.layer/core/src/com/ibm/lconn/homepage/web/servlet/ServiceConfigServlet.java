/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2008, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.web.servlet;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.core.web.serviceconfigs.ServiceConfigsApiImpl;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * * Simple servlet to wrap the service configs API to provide a feed of the *
 * installed Connections services. * *
 * 
 * @author aspender *
 */
public class ServiceConfigServlet extends HttpServlet {
	
	private final static String CLASS_NAME = ServiceConfigServlet.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	/** * */
	private static final long serialVersionUID = -7823694315334506639L;
	private static final String serviceName="homepage";
	
	@Autowired
	private ResourceBundle webResourceBundle;
	
	public void init() throws ServletException {
		super.init();

		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.entering(CLASS_NAME, "init");
		
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		webResourceBundle = (ResourceBundle)context.getBean("webResourceBundle");


		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.exiting(CLASS_NAME, "init");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handle(request, response);
	}

	private void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "handle", new Object[] { request,
					response });
		}
		ServiceConfigsApiImpl apiImpl = new ServiceConfigsApiImpl();
		try {
			response.setContentType("application/atom+xml");
			response.setCharacterEncoding("UTF-8");
			apiImpl.writeFeedServiceConfigs(serviceName, response
					.getOutputStream());
		} catch (Exception e) {
			String msg = webResourceBundle.getString("error.serviceconfig");
			if (logger.isLoggable(FINER)) {
				logger.log(FINER, msg, e);
			}
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "handle", msg);
			}
			throw new ServletException(msg);
		}
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "handle");
		}
	}
	
}
