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

package com.ibm.lconn.homepage.services.impl;

import org.springframework.beans.factory.InitializingBean;

import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;

public abstract class AbstractService implements InitializingBean {

	protected abstract void internalAfterPropertiesSet() throws Exception;
	
	
	public void afterPropertiesSet() throws Exception {
		internalAfterPropertiesSet();
	}
		
	
}
