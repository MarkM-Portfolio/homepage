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

import com.ibm.lconn.homepage.model.ServerMetricsData;
import com.ibm.lconn.homepage.services.caching.IGenericCachingService;

public interface IServerMetricsCachingService extends IGenericCachingService<String, ServerMetricsData>{

}
