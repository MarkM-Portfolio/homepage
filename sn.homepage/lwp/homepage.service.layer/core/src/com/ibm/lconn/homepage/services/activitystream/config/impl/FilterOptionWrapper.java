/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.activitystream.config.impl;

import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.FilterOption;

/**
 * Helper class that wraps a filter option and holds references to the services
 * it relies on and the filter id that should be used in JSON to hold it.
 * 
 * @author Robert Campion
 */

public class FilterOptionWrapper {
	// Actual filter option
	private FilterOption filter;
	
	// Services needed by this filter in order to work
	private String[] servicesNeeded;
	
	// The JSON id that should be used to reference this object
	private String filterId;
	
	/**
	 * FilterOptionWrapper constructor that sets all three class variables.
	 * @param filter
	 * @param servicesNeeded
	 * @param filterId
	 */
	public FilterOptionWrapper(FilterOption filter, String[] servicesNeeded, String filterId){
		this.filter = filter;
		this.servicesNeeded = servicesNeeded;
		this.filterId = filterId;
	}
	
	/**
	 * Get the filter option associated with this wrapper.
	 * @return
	 */
	public FilterOption getFilter() {
		return filter;
	}

	/**
	 * Get the services needed for this filter option.
	 * @return
	 */
	public String[] getServicesNeeded() {
		return servicesNeeded;
	}

	/**
	 * Get the filter's reference id.
	 * @return
	 */
	public String getFilterId() {
		return filterId;
	}
}
