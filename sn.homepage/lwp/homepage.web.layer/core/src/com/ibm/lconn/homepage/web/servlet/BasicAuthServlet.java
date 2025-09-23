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

package com.ibm.lconn.homepage.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.lconn.core.web.auth.LCRestSecurityHelper;
import com.ibm.lconn.core.web.exception.LConnException;

public class BasicAuthServlet extends HttpServlet {
	
	/**
	 *  Copied from the Dogear implementation
	 */
	private static final long serialVersionUID = 4944768333523773819L;

	protected void service(HttpServletRequest request, HttpServletResponse response) {
		if (!request.isSecure()) {
			try {
				response.setHeader("Location", LCRestSecurityHelper.getSslVersionOfCurrentUrl(request, "Homepage"));
				response.setStatus(HttpServletResponse.SC_FOUND);
			}catch(LConnException ex) {
				// do nothing
			}
		}else {
			String realmName = "Dashboard";
			response.addHeader("WWW-Authenticate", HttpServletRequest.BASIC_AUTH + " realm=\"" + realmName +  "\"");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

}
