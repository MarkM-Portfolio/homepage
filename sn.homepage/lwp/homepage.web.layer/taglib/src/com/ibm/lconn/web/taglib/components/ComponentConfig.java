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

package com.ibm.lconn.web.taglib.components;

import static java.util.logging.Level.FINER;

import java.util.logging.Logger;

import com.ibm.ventura.internal.config.exception.VenturaConfigHelperException;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.CustomAuthenticationSettings;

public class ComponentConfig {

	private final static String CLASS_NAME = ComponentConfig.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private static VenturaConfigurationHelper vch = VenturaConfigurationHelper.Factory
			.getInstance();

	/**
	 * Return whether a given Connections component is enabled or installed
	 * 
	 * @param componentName
	 * @return
	 */
	public static boolean isComponentEnabled(String componentName) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "isComponentEnabled", componentName);

		boolean result = false;

		if (componentName != null) {
			ComponentEntry entry = vch.getComponentConfig(componentName);

			result = false;

			if (entry != null) {
				result = entry.isUrlEnabled() || entry.isSecureUrlEnabled();
			} else {
				result = false;
			}
		} else {
			result = false;
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "isComponentEnabled", result);

		return result;
	}

	/**
	 * Obtain context root of a given Connections component. Handle secure
	 * (HTTPS) case. Return null if the component does not exist or not
	 * installed/enabled
	 * 
	 * @return
	 */
	public static String getComponentUrl(String componentName, boolean isSecure) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getComponentUrl", new Object[] {
					componentName, isSecure });

		String serviceUrl = null;

		if (isComponentEnabled(componentName)) {
			ComponentEntry entry = vch.getComponentConfig(componentName);

			if (isSecure) {
				if (entry.isSecureUrlEnabled()
						&& entry.getSecureServiceUrl() != null) {
					serviceUrl = entry.getSecureServiceUrl().toExternalForm();
				}

				else if (entry.isUrlEnabled() && entry.getServiceUrl() != null) {
					// SSL not enabled for component, so returns normal url as
					// last resort
					serviceUrl = entry.getServiceUrl().toExternalForm();
				}
			} else {
				if (entry.isUrlEnabled() && entry.getServiceUrl() != null) {
					serviceUrl = entry.getServiceUrl().toExternalForm();
				} else if (entry.isSecureUrlEnabled()
						&& entry.getSecureServiceUrl() != null) {
					// last resort: only SSL is enabled for the component
					// return SSL url even if not explicitly asked for secure
					// URL
					serviceUrl = entry.getSecureServiceUrl().toExternalForm();
				}
			}
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getComponentUrl", serviceUrl);

		return serviceUrl;
	}

	/**
	 * Obtain custom authentication settings or return null if invalid
	 * 
	 * @return
	 */
	public static CustomAuthenticationSettings getCustomAuthSettings() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getCustomAuthSettings");

		CustomAuthenticationSettings settings = null;
		

		try {
			settings = vch.getCustomAuthenticationSettings();
			
		} catch (VenturaConfigHelperException ex) {
			// should log here but we are after string freeze...

		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getCustomAuthSettings", settings);

		return settings;
	}
}
