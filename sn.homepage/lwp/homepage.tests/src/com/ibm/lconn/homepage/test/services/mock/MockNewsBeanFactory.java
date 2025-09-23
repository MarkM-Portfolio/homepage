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

package com.ibm.lconn.homepage.test.services.mock;

import static java.util.logging.Level.FINER;

import java.util.logging.Logger;

import org.easymock.EasyMock;

import com.ibm.lconn.homepage.services.utils.NewsBeanFactory;
import com.ibm.lconn.news.ejb.client.NewsStoryEJBBean;

public class MockNewsBeanFactory extends NewsBeanFactory {

	private final static String CLASS_NAME = MockNewsBeanFactory.class.getName();	
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public MockNewsBeanFactory() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, 
							"constructor");
		
		
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, 
							"constructor");
	}
	
	@Override
	public NewsStoryEJBBean getNewsEjbInstance() {
		NewsStoryEJBBean bean = EasyMock.createMock(NewsStoryEJBBean.class);
		EasyMock.replay(bean);
		return bean;
	}
}
