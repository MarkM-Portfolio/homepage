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
package com.ibm.lconn.homepage.spring.context.config;

import java.util.Map;

/*
 * Configure the properties for the component. It will be global available. So it's better to have proper prefix.
 * 
 * The properties from locations have higer priority than properties map.
 * 
 */

public class ComponentProperties {
	protected static final String CLASS_NAME=ComponentProperties.class.getName();
	
	private String[] locations;
	private Map<String,String> properties;

	public String[] getLocations() {
		return locations;
	}
	public void setLocations(String[] locations) {
		this.locations = locations;
	}
	
	public Map<String,String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String,String> properties) {
		this.properties = properties;
	}
}
