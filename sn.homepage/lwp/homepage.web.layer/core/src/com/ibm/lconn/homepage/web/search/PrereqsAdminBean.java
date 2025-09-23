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

package com.ibm.lconn.homepage.web.search;

import static java.util.logging.Level.FINER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import com.ibm.ventura.internal.config.exception.VenturaConfigHelperException;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;
import com.ibm.lconn.hpnews.common.util.Environment; 

// TODO: legacy bean from search that needs to be refactored
public class PrereqsAdminBean {

	private static VenturaConfigurationHelper configService = null;
	private final static String CLASS_NAME = PrereqsAdminBean.class.getName();

	private static Logger logger = Logger.getLogger(CLASS_NAME);
		
	public PrereqsAdminBean() throws VenturaConfigHelperException {
		if (configService == null)
			configService = VenturaConfigurationHelper.Factory.getInstance();
	}

	public Collection<String> getInstalledComponents()
			throws VenturaConfigHelperException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getInstalledComponents");
		}
		Collection<String> installedComponents = null;				
		installedComponents = new ArrayList<String>();

		Collection<String> prereqs = configService.getInstalledComponents();
		for (String prereq : prereqs) {
			ComponentEntry componentEntry = configService
					.getComponentConfig(prereq);
			if (componentEntry.isUrlEnabled()
					|| componentEntry.isSecureUrlEnabled()) {
				installedComponents.add(prereq);
			}
		}
		

		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getInstalledComponents",
					installedComponents);
		}

		return installedComponents;
	}

	public Collection<String> getSearchableComponents()
			throws VenturaConfigHelperException {
		
			Collection<String> searchableComponents = null;
			searchableComponents = new ArrayList<String>();
			Collection<String> prereqs = getInstalledComponents();
			if (prereqs.contains("microblogging")) {
				searchableComponents.add("microblogging");
			}
			if (prereqs.contains("activities")) {
				searchableComponents.add("activities");
			}
			if (prereqs.contains("blogs")) {
				searchableComponents.add("blogs");
			}
			if (prereqs.contains("dogear")) {
				searchableComponents.add("dogear");
			}
			if (prereqs.contains("communities")) {
				searchableComponents.add("communities");
			}
			if (prereqs.contains("files")) {
				searchableComponents.add("files");
			}
			if (prereqs.contains("forums")) {
				searchableComponents.add("forums");
			}
			if (prereqs.contains("profiles") && !Environment.SMART_CLOUD.isEnvironment()) {
				searchableComponents.add("profiles");
			}
			if (prereqs.contains("wikis")) {
				searchableComponents.add("wikis");
			}
		
		return searchableComponents;
	}

	public String getSearchURL() {
		String searchURL = null;
		ComponentEntry searchService = configService
				.getComponentConfig("search");
		if (searchService.isUrlEnabled()) {
			searchURL = searchService.getServiceUrl().toString();
		} else {
			searchURL = searchService.getSecureServiceUrl().toString();
		}	
		return searchURL;
	}
	
	public String getSecureSearchURL() {
		String secureSearchURL = null;
		ComponentEntry searchService = configService
				.getComponentConfig("search");
		if (searchService.isSecureUrlEnabled()) {
			secureSearchURL = searchService.getSecureServiceUrl().toString();
		} else {
			secureSearchURL = searchService.getServiceUrl().toString();
		}
		return secureSearchURL;
	}
}
