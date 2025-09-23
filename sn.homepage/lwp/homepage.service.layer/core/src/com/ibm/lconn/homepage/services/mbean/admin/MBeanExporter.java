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

package com.ibm.lconn.homepage.services.mbean.admin;

import java.util.Map;

/**
 * Export regular java bean instance as mbean
 * 
 * @author Guo Du
 *
 */
public interface MBeanExporter {
	
	/**
	 * export a single java bean as mbean
	 * 
	 * @param beanName short bean name, will append prefix and suffix by implementation
	 * @param beanObject	java bean to export
	 */
	public void exportBean(String shortBeanName,Object beanObject) throws Exception;
	
	public Map<String, Object> getBeansToExport();
	
}
