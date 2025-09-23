/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services;

/**
 * Interface which classes implementing services to get Action Required information
 * must implement.
 * @author Jim Antill
 *
 */
public interface IHomepageActionRequiredService {
	/**
	 * Get the count of action required items for a given external person ID.
	 * @param personExtId External ID of person to get count for.
	 * @return long containing count of action required items.
	 */
	public long getActionRequiredTotalByExtId(String personExtId);
}
