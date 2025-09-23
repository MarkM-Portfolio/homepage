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

package com.ibm.lconn.homepage.services.utils;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import com.ibm.lconn.news.ejb.client.NewsStoryEJBBean;
import com.ibm.lconn.news.ejb.exceptions.NewsEJBConsumerException;

public class NewsBeanFactory {

	private final static String CLASS_NAME = NewsBeanFactory.class.getName();	
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public NewsBeanFactory() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, 
							"constructor");
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, 
							"constructor");
	}
	
	public NewsStoryEJBBean getNewsEjbInstance() {
	
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, 
							"getNewsEjbInstance");
		}
		
		NewsStoryEJBBean bean = null;
		try {
			bean = com.ibm.lconn.news.ejb.client.NewsStoryEJBUtil.getBean("homepage");
		}
		catch(NewsEJBConsumerException ne) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "getNewsEjbInstance", "error invoking com.ibm.lconn.news.ejb.client.NewsStoryEJBUtil.getBean('homepage')", ne);
			}
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getNewsEjbInstance", bean);
		
		return bean;
	}
}
