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

/*package com.ibm.lconn.homepage.web.filter;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ibm.lotus.connections.dashboard.service.core.internal.impl.TabType;

public class TabInfoUpdatePageFilter extends TabInfoFilterBase {
	private final static String CLASS_NAME = TabInfoUpdatePageFilter.class
			.getName();

	private static Logger logger = Logger.getLogger(CLASS_NAME);

	public void destroy() {
		// do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "doFilter", new Object[] { request,
					response, chain });
		}

		setTabInstanceIdRequestParam((HttpServletRequest) request,
				TabType.UPDATE);
		
		chain.doFilter(request, response);

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "doFilter");
		}
	}
}
*/