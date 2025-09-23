/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.impl;

import com.ibm.ventura.internal.config.api.VenturaConfigurationProvider;
import com.ibm.ventura.internal.config.exception.VenturaConfigException;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractVenturaService extends AbstractService {
	
	private VenturaConfigurationHelper venturaConfigurationHelper;
	private VenturaConfigurationProvider venturaConfigurationProvider;
	
	protected VenturaConfigurationHelper getVenturaConfigurationHelper() {
		if(venturaConfigurationHelper == null) {
			venturaConfigurationHelper = VenturaConfigurationHelper.Factory.getInstance();
		}
		return venturaConfigurationHelper;
	}
	
	protected VenturaConfigurationProvider getVenturaConfigurationProvider() throws VenturaConfigException {
		if(venturaConfigurationProvider == null) {
			venturaConfigurationProvider = VenturaConfigurationProvider.Factory.getInstance();
		}
		return venturaConfigurationProvider; 
	}	
}
