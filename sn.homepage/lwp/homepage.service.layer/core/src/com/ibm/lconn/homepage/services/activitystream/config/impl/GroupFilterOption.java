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
 * Filter option for group changes. Provides a shorter way to create filter
 * options.
 * 
 * @author Robert Campion
 */

public class GroupFilterOption extends FilterOption {

	/**
	 * Constructor for GroupFilterOption. Sets the label and paramValue to
	 * param name 'groupId'.
	 * @param label
	 * @param paramValue
	 */
	public GroupFilterOption(String label, String paramValue) {
		super(label);
		
		this.setGroupId(paramValue);
	}
}
