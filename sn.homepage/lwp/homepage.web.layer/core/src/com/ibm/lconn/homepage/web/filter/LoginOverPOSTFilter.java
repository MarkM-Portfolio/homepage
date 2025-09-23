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

package com.ibm.lconn.homepage.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/** 
 * This filter enforces the fact that users can only login via a POST
 * and not via GET which exposes credentials on the URL.
 * 
 * @author spendaw
 *
 */
public class LoginOverPOSTFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			
			// POST is ok, reject anything else
			if (httpRequest.getMethod().equalsIgnoreCase("POST")){
				chain.doFilter(request, response);
			} else {
				// At this point we should redirect to the login error page
				// to allow the user to login properly
				httpRequest.getRequestDispatcher("/login/?error=true").forward(request, response);
			}
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
