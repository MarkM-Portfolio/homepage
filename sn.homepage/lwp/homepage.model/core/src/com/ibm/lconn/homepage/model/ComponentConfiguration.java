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

package com.ibm.lconn.homepage.model;

import java.util.HashMap;
import java.util.Map;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;


/**
 * This class is to reflect the configuration information returned from Ventura.
 * At this time these are the only details we use in homepage
 * 
 * @author paulo
 * 
 * @author scrawford - modified to remove cache and wrap the Ventura Config object
 * for MT environments
 *
 */
public class ComponentConfiguration extends AbstractModelObject {
	
	public static final String BOARD_MAX_LENGTH_PROPERTY_KEY = "boardMaxLength";

	private static final long serialVersionUID = -1905167289216230484L;
	
	private ComponentEntry componentEntry;
	private Map<String, String> properties;
	
	public ComponentConfiguration() {
		this.properties = new HashMap<String, String>();
	}
	
	public String getName() {
		String name = componentEntry.getName();
		return name;
	}
	
	public String getUrl() {
		String url = null;
		
		if (componentEntry.getServiceUrl() != null){
			url = componentEntry.getServiceUrl().toExternalForm();
		}
		
		return url;
	}
	
	public String getSecureUrl() {
		String secureUrl = null;
		
		if (componentEntry.getSecureServiceUrl() != null){
			secureUrl = componentEntry.getSecureServiceUrl().toExternalForm();
		}
		
		return secureUrl;
	}
	
	public boolean isUrlEnabled() {
		return componentEntry.isUrlEnabled();
	}

	public boolean isSecureUrlEnabled() {
		return componentEntry.isSecureUrlEnabled();
	}
	
	public boolean isEnabled() {
		return componentEntry.isUrlEnabled();
	}
	
	public ComponentEntry getComponentConfiguration() {
		return componentEntry;
	}

	public void setComponentConfiguration(ComponentEntry componentEntry) {
		this.componentEntry = componentEntry;
	}
	
	public void addProperty(String key, String val) {
		this.properties.put(key, val);
	}
	
	public String getProperty(String key) {
		if(this.properties.containsKey(key))
			return this.properties.get(key);
		else
			return "";
	}

}
