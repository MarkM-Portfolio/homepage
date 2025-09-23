/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.spring.context;

import org.springframework.context.ApplicationContext;


/*
 * All your need after the ApplicationContextLoader was initialized. It there are two 
 * getBean helper methods to lookup beans from the ApplicationContext.
 * 
 */

public interface ApplicationContextLoader {

	public static final String DEFAULT_APPLICATION_CONTEXT_NAME="ApplicationContext";
	public static final String DEFAULT_APPLICATION_CONTEXT_FILES=DEFAULT_APPLICATION_CONTEXT_NAME+".xml";

	/*
	 * Return the ApplicationContext instance
	 */
	public ApplicationContext getApplicationContext();

	/*
	 * Lookup the bean by name from ApplicationContext
	 */
	public Object getBean(String beanName);

	/*
	 * Lookup the bean by name from ApplicationContext and cast to beanType
	 */
	public <T> T getBean(String beanName, Class<T> beanType);

	/*
	 * Destroy the ApplicationContext
	 */
	public void destory();

}
