/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.utils.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.utils.IUIServiceUtils;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * To manage the ui utils like translation
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public class UIServicesUtilsImpl implements IUIServiceUtils {
	
	@Autowired
	private ResourceBundle catalogServiceUIResourceBundle;
	@Autowired
	private ResourceBundle catalogServiceResourceBundle;
	

	private static String CLASS_NAME = UIServicesUtilsImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	/**
	 * Checks if a string starts with a % indicating an externalized property.
	 * If so, it looks for that property in the corresponding resource bundle.
	 * If the string does not start with a %, the property key has a null or
	 * empty value, or the key doesn't exist in resource bundle, this method
	 * returns the sent string.
	 */
	public String getGlobalizedMsgFromCatalogUI(String originalMsg, Locale locale) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getGlobalizedMsgFromCatalogUI", new Object[]{originalMsg, locale});

		String globalizedMsg = originalMsg;

            // If originalMsg is empty, just return it
		if (originalMsg != null && originalMsg.length() > 1) {

			// A property starts with % if it has a corresponding key
			if (originalMsg.charAt(0) == '%') {
				String key = originalMsg.substring(1);

				// Check that key has a value
				if (key != null && key.length() > 0) {
					try {
						
						globalizedMsg = catalogServiceUIResourceBundle.getString(key, locale);
					} catch (MissingResourceException e) {
						// If property is not in bundle, just log it
						// Return value should be the same as received
						if (logger.isLoggable(SEVERE)) {
							String msg = catalogServiceResourceBundle.getString("error.service.info.property",locale);
							logger.logp(SEVERE, CLASS_NAME,	"getGlobalizedMsgFromCatalogUI", msg);
						}
					}
				}
			}
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getGlobalizedMsgFromCatalogUI", globalizedMsg);

		return globalizedMsg;
	}

	public ResourceBundle getCatalogServiceUIResourceBundle() {
		return catalogServiceUIResourceBundle;
	}

	public void setCatalogServiceUIResourceBundle(ResourceBundle catalogServiceUIResourceBundle) {
		this.catalogServiceUIResourceBundle = catalogServiceUIResourceBundle;
	}

	public ResourceBundle getCatalogServiceResourceBundle() {
		return catalogServiceResourceBundle;
	}

	public void setCatalogServiceResourceBundle(
			ResourceBundle catalogServiceResourceBundle) {
		this.catalogServiceResourceBundle = catalogServiceResourceBundle;
	}

	

}
