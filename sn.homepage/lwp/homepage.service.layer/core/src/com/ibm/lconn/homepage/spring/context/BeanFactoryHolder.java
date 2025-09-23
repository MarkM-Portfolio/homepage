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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
 * Provide static access to BeanFactory for none spring managed code.
 * 
 */

public class BeanFactoryHolder implements ApplicationContextAware,
		BeanFactoryAware, DisposableBean {

	private static BeanFactory beanFactory;
	private static ApplicationContext appContext;

	/*
	 * Set BeanFactory instance
	 */
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		BeanFactoryHolder.beanFactory = beanFactory;
	}

	/*
	 * unset the reference to BeanFactory instance
	 */
	public void destroy() throws Exception {
		BeanFactoryHolder.beanFactory = null;
	}

	/*
	 * Get the BeanFactory instance
	 */
	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static ApplicationContext getApplicationContext() {
		return appContext;
	}

	/*
	 * Lookup the bean by name from BeanFactory
	 */
	public static Object getBean(String beanName) {
		return beanFactory.getBean(beanName);
	}

	/*
	 * Lookup the bean by name from BeanFactory and cast to beanType
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName, Class<T> beanType) {
		return (T) beanFactory.getBean(beanName, beanType);
	}

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		BeanFactoryHolder.appContext = ctx;
	}
}
