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

package com.ibm.lconn.homepage.services.utils;
//package com.ibm.openactivities.admin.names;


/**
 * 
 * @author Lorenzo Notarfonzo
 * @author Guo Du
 *
 */
public interface WASAdminService {

	/**
	 * get WAS default domain
	 * @return
	 */
	public String getDefaultDomain();


	/**
	 * get WAS cell name
	 * @return
	 */
	public String getCellName();


	/**
	 * get WAS node name
	 * @return
	 */
	public String getNodeName();


	/**
	 * get WAS process name
	 * @return
	 */
	public String getProcessName();
}
