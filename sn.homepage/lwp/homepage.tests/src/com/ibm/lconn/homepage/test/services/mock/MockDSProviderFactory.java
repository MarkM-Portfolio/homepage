/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2016                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services.mock;

import org.easymock.EasyMock;

import com.ibm.connections.directory.services.DSProvider;
import com.ibm.connections.directory.services.data.DSConstants;
import com.ibm.connections.directory.services.data.DSObject;
import com.ibm.connections.directory.services.exception.DSException;
import com.ibm.lconn.core.platform.ICPlatform;

public class MockDSProviderFactory {
	
	public static MockDSProviderFactory INSTANCE = new MockDSProviderFactory();
	
	public MockDSProviderFactory() {
    }
	
	private DSObject generateDSObjectVer1() {
		DSObject mockObj = new DSObject();
		mockObj.set_id("ZZZZZZZZ-2222-2222-2222-222222222222");
		mockObj.set_dn("mr.big.tester@ie.ibm.com");
		mockObj.set_email("mr.big.tester@ie.ibm.com");
		mockObj.set_name("Mr Big Tester");								 
		mockObj.addExtValue(DSConstants.ATTRIBUTE_TYPE_IBM_LIVE_ORG_ID, ICPlatform.ORG_ONPREM);
		return mockObj;
	}
	
	private DSObject generateDSObjectVer2() {
		DSObject mockObj = new DSObject();
		mockObj.set_id("22222222-2222-2222-2222-222222222222");
		mockObj.set_dn("vincent");
		mockObj.set_email("vincent@google.com");	
		mockObj.set_name("Mr Vincent Burckhardt");						 
		mockObj.addExtValue(DSConstants.ATTRIBUTE_TYPE_IBM_LIVE_ORG_ID, ICPlatform.ORG_ONPREM);
		return mockObj;
	}
	
	private DSProvider createMockProvider() throws DSException {
		DSProvider mock = EasyMock.createMock(DSProvider.class);
		//EasyMock.expect(mock.searchUserByExactIdMatch("ZZZZZZZZ-2222-2222-2222-222222222222")).andReturn(generateDSObjectVer1());
		//EasyMock.expect(mock.searchUserByExactIdMatch("ZZZZZZZZ-2222-2222-2222-222222222222")).andReturn(generateDSObjectVer1());
		
		EasyMock.expect(mock.findUserByPrincipal("vincent.burckhardt", null)).andReturn(generateDSObjectVer2()).anyTimes();
		EasyMock.replay(mock);
		return mock;
	}
	
    public DSProvider getProfileProvider() throws DSException {
    	return createMockProvider();
    }

}
