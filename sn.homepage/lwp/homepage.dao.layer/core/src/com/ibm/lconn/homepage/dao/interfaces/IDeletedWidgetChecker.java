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
package com.ibm.lconn.homepage.dao.interfaces;

import java.util.List;

/**
 * @author Michael Ahern (michael.ahern@ie.ibm.com)
 *
 */
public interface IDeletedWidgetChecker {

	/**
	 * Takes a given set of input widgets and return a list of IDs that have been deleted
	 * @param widgetIds
	 * @return
	 */
	public List<String> getDeletedList(List<String> widgetIds) throws Exception;

}
