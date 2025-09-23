/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services;

public interface ISecurityServices {
	
	public boolean checkTabInstanceOwnership(String personId, String tabInstanceId) throws ServiceException; 
	public boolean checkWidgetInstanceOwnership(String personId, String widgetInstanceId) throws ServiceException;
	public boolean checkWidgetInstanceType(String widgetInstanceId,	String widgetId) throws ServiceException;
	public boolean checkWidgetInstanceAllowedOnWidgetTab(String widgetInstanceId, String tabInstanceId)	throws ServiceException;
	public boolean checkWidgetAllowedOnWidgetTab(String widgetId, String tabInstanceId) throws ServiceException; 
}
