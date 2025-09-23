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

package com.ibm.lconn.homepage.services.mbean.admin.impl;

/**
 * Services available to wsadmin.
 * 
 * @author Lorenzo Notarfonzo
 * 
 */
public interface HomepagePersonServiceMBean {

	/**
	 * The command resets WELCOME_MODE flag for all users to its
	 * default value
	 *
	 * @returns informational message
	 */
	public String resetWelcomeFlagAllMembers();

	/**
	 * The command resets WELCOME_MODE flag for the specified user to its
	 * default value. The user is identified by email
	 * 
	 * @param email
	 *            E-mail for the user to reset welcome mode
	 * @returns informational message
	 */
	public String resetWelcomeFlagMemberByEmail(String email);

	/**
	 * The command resets WELCOME_MODE flag for the specified user to its
	 * default value. The user is identified by login name
	 * 
	 * @param loginName
	 *            Login name for the user to reset welcome mode
	 * @returns informational message
	 */
	public String resetWelcomeFlagMemberByLoginName(String loginName);

	
	/**
	 * The command resets WELCOME_MODE flag for the batch of users to its
	 * default value. The user emails are listed in a specified file
	 * 
	 * @param repositoryLocation
	 *            Name of the file with user emails (one email per line)
	 * 
	 * @returns informational message
	 */
	public String resetWelcomeFlagBatchMembersByEmail(String repositoryLocation);
	
	/**
	 * The command resets WELCOME_MODE flag for the batch of users to its
	 * default value. The user login names are listed in a specified file
	 * 
	 * @param repositoryLocation
	 *            Name of the file with user login names (one name per line)
	 * 
	 * @returns informational message
	 */
	public String resetWelcomeFlagBatchMembersByLoginName(String repositoryLocation);

}
