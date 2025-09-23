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

package com.ibm.lconn.homepage.services.configuration.caching;

import java.util.List;

import com.ibm.lconn.homepage.model.ComponentConfiguration;
import com.ibm.lconn.homepage.services.caching.IGenericCachingService;

public interface IComponentConfigurationCachingService extends IGenericCachingService<String, ComponentConfiguration> {
	
	public List<String> getInstalledComponents();

}
