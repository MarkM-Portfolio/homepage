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

package com.ibm.lconn.homepage.services.config.gettingstarted;

import static java.util.logging.Level.FINER;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.apache.commons.lang.text.StrLookup;

import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;

/**
 * 
 * Class which translates variables defined in the configuration file to the component URLs.
 * 
 * Variable in configuration file should be defined in the following two forms:
 *  ${lcurl:<componentName>.secure} - denotes secure URL of the corresponding LC component 
 *  ${lcurl:<componentName>.unsecure} - denotes unsecure URL of the corresponding LC component
 *  
 * If component name is not defined in LotusConnections-config or corresponding URL is not
 * enabled then empty string is returned and interpolation error is added to the list of
 * interpolation errors.
 * The list of interpolation errors is stored and should be reset for subsequent use of the 
 * same interpolator object for several configuration files.  
 *
 */
public class ComponentUrlInterpolator extends StrLookup {

	private static String CLASS_NAME = ComponentUrlInterpolator.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static final String LC_URL_PREFIX = "lcurl";
	
	private static final String SECURE = "secure";

	private static VenturaConfigurationHelper configHelper;
	
	private final List<String> interpolationErrors;
	
	private static final String PLACEHOLDER = "{%s}";
	
	public ComponentUrlInterpolator() {		
		interpolationErrors = new LinkedList<String>();		
	}	
	
	public List<String> getInterpolationErrors() {
		return interpolationErrors;
	}

	public void resetInterpolationErrors() {
		interpolationErrors.clear();
	}
	
	/**
	 * Provide a convenience method to take getting started url - ie homepage.unsecure
	 * split and render the links.
	 * 
	 * @param value
	 * @return
	 */
	public static String convertGettingStartConfigToUrl(String value){
		if ( logger.isLoggable(FINER) ) {
    		logger.entering(CLASS_NAME, "convertGettingStartConfigToUrl", value);
    	}
		
		if (configHelper == null){
			configHelper = VenturaConfigurationHelper.Factory.getInstance();	
		}	
		
		String result = value;
		if(value != null && value.startsWith("{")){
			int end = value.indexOf('}');
			String componentConfig = value.substring(1, end);
			String relPath = value.substring(end+1);
			
			StringTokenizer parts = new StringTokenizer(componentConfig.toLowerCase(),".");
		      if (parts.countTokens() == 2) {
		      	String componentName = parts.nextToken();
		      	if(configHelper.getInstalledComponents().contains(componentName)) {
		      		ComponentEntry component = configHelper.getComponentConfig(componentName);
			        if (component != null) {
			        	URL url = null;
						if (SECURE.equals(parts.nextToken())) {
							if (component.isSecureUrlEnabled())
								url = component.getSecureServiceUrl();
						} else {
							if (component.isUrlEnabled())
								url = component.getServiceUrl();
						}
						if (url != null) {							
							result = url.toExternalForm();
							result += relPath;
						}
			        }
		      	 }		      	
		      }
		}
	    
		if ( logger.isLoggable(FINER) ) {
    		logger.exiting(CLASS_NAME, "convertGettingStartConfigToUrl", result);
    	}
	    return result;
	}
	
	/**
	 *  Override and simple wrap the getting started url in braces {}
	 *  this used to cache the urls, we can't do this due to MT requirements
	 *  we need to build the Ventura host with a Request object and the correct
	 *  MT aware host is returned.
	 */
	public String lookup (String substitutionValue)
    {
		if(substitutionValue != null){
			substitutionValue = String.format(PLACEHOLDER, substitutionValue);
		}
        return substitutionValue;
    }
	
	public static void setConfigHelper(VenturaConfigurationHelper helper){
		configHelper = helper;
	}
}
