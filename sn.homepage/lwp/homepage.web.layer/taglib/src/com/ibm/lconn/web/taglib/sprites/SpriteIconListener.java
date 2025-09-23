/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.web.taglib.sprites;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.lconn.homepage.utils.ResourceBundle;

/**
 * Load the paths of the icons from a file. The icons are used in the river of
 * news and injected into the page with IconTag
 * 
 * Note: Was initially a servlet in 2.5 (SpriteIconServlet)
 * 
 * @see IconTag
 * @author vincent
 * 
 */
public class SpriteIconListener implements ServletContextListener {
	private static final long serialVersionUID = -2154755951008271241L;

	private static final String PROPERTIES_FILE_ICONS_KEY = "iconMappingLocation";

	private final static String CLASS_NAME = SpriteIconListener.class.getName();

	public static Properties ICONS_MAPPING = new Properties();

	/*
	 * private static ResourceBundleHelper resourceBundleHelper = new
	 * ResourceBundleHelper( IWebResources.RESOURCE_BUNDLE);
	 */

	private static Logger logger = Logger.getLogger(CLASS_NAME);

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "contextInitialized");

		ICONS_MAPPING = new Properties();
		final ServletContext servletContext = servletContextEvent
				.getServletContext();

		try {

			String path = obtainPropertyFileReference(servletContext);

			ICONS_MAPPING.load(servletContext.getResourceAsStream(path));
		} catch (IOException e) {
			String msg = getWebResourceBundle(servletContext).getString(
					"error.sprite.icon.init");
			ServletException t = new ServletException(msg, e);
			if (logger.isLoggable(SEVERE))
				logger.logp(SEVERE, CLASS_NAME, "init", msg, t);
			// TODO: change this to a runtimeexception and throw
		//	throw t;
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "contextInitialized");

	}

	/**
	 * Get the reference to the property file configured in web.xml
	 * 
	 * @param sc
	 * @throws ServletException
	 */
	private String obtainPropertyFileReference(ServletContext sc)
			/*throws ServletException*/ {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "obtainPropertyFileReference");

		String propertyFilePath = sc
				.getInitParameter(PROPERTIES_FILE_ICONS_KEY);

		if ((propertyFilePath == null) || (propertyFilePath.length() == 0)) {
			String msg = getWebResourceBundle(sc).getString(
					"error.sprite.icon.undefined.propertyFile",
					PROPERTIES_FILE_ICONS_KEY);

			ServletException t = new ServletException(msg);

			if (logger.isLoggable(SEVERE))
				logger.logp(SEVERE, CLASS_NAME, "init", msg, t);

			//throw t;
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "obtainPropertyFileReference",
					propertyFilePath);

		return propertyFilePath;
	}

	public ResourceBundle getWebResourceBundle(ServletContext sc) {
		// listener are not managed by Spring so use direct reference to app
		// context
		// TODO: review, better solution

		return (ResourceBundle) WebApplicationContextUtils
				.getWebApplicationContext(sc).getBean("webResourceBundle");

	}

	/*
	 * public void setWebResourceBundle(ResourceBundle webResourceBundle) {
	 * this.webResourceBundle = webResourceBundle; }
	 */

}
