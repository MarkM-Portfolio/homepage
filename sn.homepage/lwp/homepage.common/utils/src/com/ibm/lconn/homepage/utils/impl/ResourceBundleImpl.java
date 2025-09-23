/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.utils.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;

import com.ibm.lconn.core.customization.ApplicationCustomization;
import com.ibm.lconn.homepage.utils.ResourceBundle;

public class ResourceBundleImpl implements InitializingBean, ResourceBundle {

	private final static String CLASS_NAME = ResourceBundleImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	private final static String KEY_CONCAT_SYMBOL = "#";

	private Map<String, java.util.ResourceBundle> bundles = new HashMap<String, java.util.ResourceBundle>();
	private String bundleName;

	private static final Set<String> nonUIBundleNames=new HashSet<String>();
	static {
		nonUIBundleNames.add("com.ibm.lconn.homepage.resources.nls.catalog.service.CatalogServicesLogMessages");
		nonUIBundleNames.add("com.ibm.lconn.homepage.resources.nls.database.DataLogMessages");
		nonUIBundleNames.add("com.ibm.lconn.homepage.resources.nls.web.WebLogMessages");
		nonUIBundleNames.add("com.ibm.lconn.homepage.resources.nls.mbean.MBeanResources");
		nonUIBundleNames.add("com.ibm.lconn.homepage.resources.nls.service.ServiceLogMessages");
		nonUIBundleNames.add("com.ibm.lconn.homepage.resources.nls.jfrost.JFrostLogMessages");
		nonUIBundleNames.add("com.ibm.lconn.homepage.resources.stats.BuildStats");
	}
	
	
	public String getBundleName() {
		return bundleName;		
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

	public String getString(String key, Object... params) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getString", new Object[] { bundleName,
					key, params });

		String message = null;

		java.util.ResourceBundle bundle = getBundle(Locale.getDefault());

		// Retrieving the message
		message = bundle.getString(key);

		if (params != null && params.length > 0)
			message = MessageFormat.format(message, params);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getString", message);

		return message;
	}

	public String getString(String key, Locale locale, Object... params) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getString", new Object[] { bundleName,
					key, locale, params });

		String message = null;
		java.util.ResourceBundle bundle = getBundle(locale);

		// Retrieving the message
		message = bundle.getString(key);

		if (params != null && params.length > 0)
			message = MessageFormat.format(message, params);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getString", message);

		return message;
	}

	private java.util.ResourceBundle getBundle(Locale locale) {
		String bundleKey = bundleName + KEY_CONCAT_SYMBOL + locale.toString();

		// Because there is a specified locale, the key is bundleName more the
		// specifice locale
		java.util.ResourceBundle bundle = bundles.get(bundleKey);

		if (bundle == null) {
			bundle = generateResourceBundle(bundleName, locale);
			// caching the bundle
			bundles.put(bundleKey, bundle);
		}
		return bundle;
	}

	public Enumeration<String> getKeys() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getKeys");

		Enumeration<String> keys = getBundle(Locale.getDefault()).getKeys();

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getKeys", keys);

		return keys;
	}

	public void afterPropertiesSet() throws Exception {
		bundles.put(bundleName, generateResourceBundle(bundleName, Locale
				.getDefault()));
	}

	private java.util.ResourceBundle generateResourceBundle(String bundleName,
			Locale locale) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getResourceBundle", new Object[] {
					bundleName, locale });

		java.util.ResourceBundle resourceBundle = null;

		try {
			if (nonUIBundleNames.contains(bundleName)) {
				resourceBundle = java.util.ResourceBundle.getBundle(bundleName, locale);			
			}
			else {
				resourceBundle = ApplicationCustomization.getInstance().getBundle(bundleName, locale);
			}
			
		} catch (MissingResourceException e) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "getResourceBundle", e
						.getMessage());
			}
		} catch (NullPointerException e) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "getResourceBundle", e
						.getMessage());
			}
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getString", resourceBundle);

		return resourceBundle;
	}

}
