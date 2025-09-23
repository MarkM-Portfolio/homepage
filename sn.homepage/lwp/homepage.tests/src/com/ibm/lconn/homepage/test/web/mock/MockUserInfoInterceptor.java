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

package com.ibm.lconn.homepage.test.web.mock;

import com.ibm.lconn.homepage.web.RuntimeWebException;
import com.ibm.lconn.homepage.web.interceptor.UserInfoInterceptor;

public class MockUserInfoInterceptor extends UserInfoInterceptor {
	private static final long serialVersionUID = -1126004091307708108L;
	/*private String userLoginId = "loginId";
	private String email = "email";
	private String externalId = "externalId";
	private String internalId = "internalId";*/

	@Override
	public void init() throws RuntimeWebException {
		// do nothing
	}

	// CNXSERV-12778 with adjustment to spring, the intercept function doesn't exist anymore -> need to rearrange to interceptor.preHandle if necessary.
	// @Override
	public String intercept() throws Exception {

		// we're in our mock struts unit test context
		/*
		 * MockHttpServletRequest request = (MockHttpServletRequest) invocation
		 * .getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		 * 
		 * // set mock session attributes for user
		 * 
		 * 
		 * request.getSession().setAttribute(GlobalSessionConstants.USER_LOGIN_ID
		 * , userLoginId); request.getSession().setAttribute(
		 * GlobalSessionConstants.USER_INFO_EXTERNAL_ID, externalId);
		 * request.getSession().setAttribute(
		 * GlobalSessionConstants.USER_INFO_EMAIL, email);
		 * request.getSession().setAttribute(
		 * GlobalSessionConstants.USER_INFO_INTERNAL_ID, internalId);
		 */
		 

		// return invocation.invoke();
        return null;
	}

}