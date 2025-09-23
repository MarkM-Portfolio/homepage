/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2015                              */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.dao.interfaces;

/**
 * Interface implemented by Dao class providing services used on Homepage Startup.
 * 
 * @author Jim Antill
 *
 */
public interface IHomepageActionRequiredDao {

	/**
	 * Gets the initial total of ActionRequired items.
	 * @param String External ID of the person to retrieve the count of ActionRequired items for. 
	 * @return long Total number of ActionRequired items.
	 */
	public long getActionRequiredTotalByExtId(String personExtId);
}