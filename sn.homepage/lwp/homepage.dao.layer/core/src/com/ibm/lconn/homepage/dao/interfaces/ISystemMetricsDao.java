/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.dao.interfaces;

import static java.util.logging.Level.FINER;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ISystemMetricsDao {
	
	public long getCountHPPerson();
	
	public long getNumberUniqueVisit(Date begin, Date end);
	
	public long getCountAllWidgets();
	
	public long getCountEnabledWidgets();
	
	public List getWidgetsUsage();

	public long getCountOpenWidgets();
	
	public long getStoriesAdded();

	public long getStoriesAddedSince(Date begin, Date end);
	
	public long getStoriesSaved();
	
	public long getStoriesSavedSince(Date begin, Date end);
	
	public List getPopularWidgets();
	
	public long getCountCustomizedPages();
	
}
