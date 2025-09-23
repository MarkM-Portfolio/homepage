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

package com.ibm.lconn.homepage.test.web.mock;

import org.easymock.EasyMock;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Simple Spring factory delegating the creating of a mock object to EasyMock
 * Only work for interfaces (need OS for classextension if we want mocking
 * classes)
 * 
 * @author vincent
 * 
 * @param <T>
 */
public class GenericMockFactory extends AbstractFactoryBean {

	String interfaceName = "";

	protected Object createInstance() throws Exception {
		Object mock = EasyMock.createNiceMock(Class.forName(interfaceName));

		EasyMock.replay(mock);

		return mock;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class getObjectType() {
		Class clazz = null;

		try {
			clazz = Class.forName(interfaceName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return clazz;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

}