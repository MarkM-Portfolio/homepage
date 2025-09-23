/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.web;

import org.junit.Before;

public abstract class AbstractLoggedInUserTest extends BaseStrutsSpringTestCase {

	@Before
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// CNXSERV-12778 - likely unnecessary now 
		// mockRequest.setRemoteUser("userLogin");
	}

}