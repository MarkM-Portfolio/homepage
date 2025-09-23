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

package com.ibm.lconn.homepage.services.mbean.admin.impl;

/**
 * Interface for MBean Facade. Facade Service will invoke particular Homepage
 * services using service name and method signature.
 * 
 */
public interface HomepageMBeanFacadeMBean {
	/**
	 * Invoke Service using the given parameters
	 * 
	 * @param service
	 *            Service to be invoked
	 * @param method
	 *            Service's method to be invoked
	 * @param args
	 *            Arguments to be passed into the service method
	 * @param argTypes
	 *            Corresponding data types for the arguments to be passed in
	 */
	public Object invoke(String service, String method, Object[] args,
			Class<?>[] argTypes);

	/**
	 * processes the organisation ID and then invokes Service using the given
	 * parameters.
	 * 
	 * @param service
	 *            Service to be invoked
	 * @param method
	 *            Service's method to be invoked
	 * @param args
	 *            Arguments to be passed into the service method
	 * @param argTypes
	 *            Corresponding data types for the arguments to be passed in
	 */
	public Object invoke(String service, String method, Object[] args,
			Class<?>[] argTypes, String orgId);
}
