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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibm.lconn.homepage.model.ServerMetricsData;


public interface IServerMetricsServices {
	

	public void setToday(Date now);
	
	public List<ServerMetricsData> getServerMetricsData(int type) throws ServiceException;
	
	public Map<String, Object> getMetricMap(Date now);
	public List<String> getMetricKeys(Date now);
}
