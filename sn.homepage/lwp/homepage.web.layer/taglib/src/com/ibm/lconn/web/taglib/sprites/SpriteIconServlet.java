// Moved to SpriteIconListener

/* ***************************************************************** 
                                                                   
 IBM Confidential                                                  
                                                                   
 OCO Source Materials                                              
                                                                   
 Copyright IBM Corp. 2009, 2015                                    
                                                                   
 The source code for this program is not published or otherwise    
 divested of its trade secrets, irrespective of what has been      
 deposited with the U.S. Copyright Office.                         
                                                                   
 ***************************************************************** 

package com.ibm.lconn.web.taglib.sprites;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.ibm.lotus.connections.dashboard.common.util.ResourceBundleHelper;
import com.ibm.lotus.connections.dashboard.nls.IWebResources;

*//**
 * Load the paths of the icons from a file.
 * The icons are used in the river of news and injected into the page with
 * IconTag
 * 
 * @see IconTag
 * @author vincent
 * 
 *//*
public class SpriteIconServlet extends HttpServlet {
	private static final long serialVersionUID = -2154755951008271241L;

	private static final String PROPERTIES_FILE_ICONS_KEY = "iconMappingLocation";

	private final static String CLASS_NAME = SpriteIconServlet.class.getName();

	public static Properties ICONS_MAPPING = new Properties();

	private static ResourceBundleHelper resourceBundleHelper = new ResourceBundleHelper(
			IWebResources.RESOURCE_BUNDLE);

	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Override
	public void init(ServletConfig config) throws ServletException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "init");

		ICONS_MAPPING = new Properties();
		try {

			String path = obtainPropertyFileReference(config);

			ICONS_MAPPING.load(config.getServletContext().getResourceAsStream(
					path));
		} catch (IOException e) {
			String msg = resourceBundleHelper
					.getString("error.sprite.icon.init");
			ServletException t = new ServletException(msg, e);
			if (logger.isLoggable(SEVERE))
				logger.logp(SEVERE, CLASS_NAME, "init", msg, t);
			throw t;
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "init");
	}

	*//**
	 * Get the reference to the property file configured in web.xml
	 * 
	 * @param config
	 * @throws ServletException
	 *//*
	private String obtainPropertyFileReference(ServletConfig config)
			throws ServletException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "obtainPropertyFileReference");

		String propertyFilePath = config
				.getInitParameter(PROPERTIES_FILE_ICONS_KEY);

		if ((propertyFilePath == null) || (propertyFilePath.length() == 0)) {
			String msg = resourceBundleHelper.getString(
					"error.sprite.icon.undefined.propertyFile",
					PROPERTIES_FILE_ICONS_KEY);

			ServletException t = new ServletException(msg);

			if (logger.isLoggable(SEVERE))
				logger.logp(SEVERE, CLASS_NAME, "init", msg, t);

			throw t;
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "obtainPropertyFileReference",
					propertyFilePath);

		return propertyFilePath;
	}
}
*/