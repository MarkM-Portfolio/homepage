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

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;

public abstract class AbstractMockSupportTest {
	private List<Object> mocks=new ArrayList<Object>();
	
	public void setupMocks() {
		TestConstants.reset();
		mocks=new ArrayList<Object>();
	}
	
    public <T> T createMock(Class<T> toMock) {
    	T mock=EasyMock.createMock(toMock);
    	mocks.add(mock);
        return mock;
    }
	
	public void replyMocks() {
		for(Object mock:mocks){
			EasyMock.replay(mock);
		}
	}
	
	@After
	public void verifyMocks() {
		for(Object mock:mocks){
			EasyMock.verify(mock);
		}
	}
}
