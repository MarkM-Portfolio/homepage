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

package com.ibm.lconn.homepage.services;

import java.util.List;

import com.ibm.lconn.homepage.model.ComponentConfiguration;

public interface IConfigurationService {

	public String getComponentUrl(String componentName, boolean isSecureURL) throws ServiceException;
	public List<String> getInstalledComponents();
	public ComponentConfiguration getComponentConfiguration(String componentName) throws ServiceException;
	public boolean getExposeEmail();
	public boolean getWpiEnabled() throws ServiceException;
    public boolean isComponentInstalled(String componentName);
    public boolean isMultiTenantEnvironment();
}
