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

package com.ibm.lconn.homepage.test.services.mock;

import org.easymock.EasyMock;

import com.ibm.ventura.internal.config.api.VenturaConfigurationProvider;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;

/**
 * Create mock VCH via EasyMock
 * 
 * @author vincent
 * 
 */
public class VenturaConfigurationServiceMockFactory {

	public static VenturaConfigurationHelper createVenturaConfigurationHelperMock() {
		VenturaConfigurationHelper mockHelper = EasyMock
				.createNiceMock(VenturaConfigurationHelper.class);
		
		EasyMock.replay(mockHelper);
		
		return mockHelper;
	}

	public static VenturaConfigurationProvider createVenturaConfigurationProviderMock() {
		VenturaConfigurationProvider mockProvider = EasyMock
				.createNiceMock(VenturaConfigurationProvider.class);

		//EasyMock.expect(mockProvider.get)
		
		EasyMock.replay(mockProvider);
		
		return mockProvider;
	}

}
