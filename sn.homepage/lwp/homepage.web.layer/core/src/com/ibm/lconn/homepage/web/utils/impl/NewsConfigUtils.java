/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2012, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.utils.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import org.apache.commons.configuration.XMLConfiguration;

import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.utils.INewsConfigUtil;
import com.ibm.ventura.internal.config.api.VenturaConfigurationProvider;
import com.ibm.ventura.internal.config.exception.VenturaConfigException;
import org.springframework.beans.factory.annotation.Autowired;

public class NewsConfigUtils implements INewsConfigUtil{

	private static String CLASS_NAME = NewsConfigUtils.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private final String STORY_LIFETIME = "databaseCleanup.storyLifetimeInDays";
	public final String NEWS_CONFIG_ID = "news";
	@Autowired
	private ResourceBundle webResourceBundle;
	private XMLConfiguration newsConfig; 
	
	
	public void onInit(){
		//to be called on start up,only read the config on startup
		newsConfig = getNewsConfig();
	}
	
	public int getStoryLifeTimeInDays(){
		// Database cleanup and synchronization strategies
		int storyLifetime = loadIntegerConfigValue(newsConfig, STORY_LIFETIME, 0);
		return storyLifetime;
		
	}
	/**
	 * 
	 * @param newsConfig
	 * @param configKey
	 * @param defaultValue
	 * @return An int configuration value
	 */
	private int loadIntegerConfigValue(XMLConfiguration newsConfig, String configKey, int defaultValue) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "loadIntegerConfigValue");
		
		String configStringValue = newsConfig.getString(configKey);

		if (logger.isLoggable(FINEST))
			logger.logp(FINEST, CLASS_NAME, "loadIntegerConfigValue", configKey + ": " + configStringValue);
		
		int retValue;

		try {
			retValue = Integer.parseInt(configStringValue);						
		} catch (NumberFormatException e) {
			retValue = defaultValue;
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "loadIntegerConfigValue", retValue);
		
		return retValue;
	}
	
	private XMLConfiguration getNewsConfig() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getNewsConfig", new Object[] {});
		XMLConfiguration config = null;

		try {
			config = (XMLConfiguration)VenturaConfigurationProvider.Factory.getInstance().getConfiguration(NEWS_CONFIG_ID);

			if (logger.isLoggable(FINEST))
				logger.logp(FINEST, CLASS_NAME, "getNewsConfig",  "loaded news-config.xml: {0}", config);
		} 
		catch ( VenturaConfigException vce) {
			String msg = webResourceBundle.getString("error.loading.news.config.settings");
			if (logger.isLoggable(SEVERE))
				logger.logp(SEVERE, CLASS_NAME, "getNewsConfig", msg, vce);
		}
	
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getNewsConfig", config);
	
		return config;
	}
	public ResourceBundle getWebResourceBundle() {
		return webResourceBundle;
	}
	public void setWebResourceBundle(ResourceBundle webResourceBundle) {
		this.webResourceBundle = webResourceBundle;
	}
	
	
}
