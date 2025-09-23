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

package com.ibm.lconn.homepage.web.servlet;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenericRedirectServlet extends HttpServlet {

	private static final long serialVersionUID = -7136963173367633983L;
	
	private final static String CLASS_NAME = GenericRedirectServlet.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	private static final String PARAM_NAME = "redirectURL";
	private static final String DEFAULT_URL = "/homepage";
	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		setUrl(config.getInitParameter(PARAM_NAME)==null?DEFAULT_URL:config.getInitParameter(PARAM_NAME));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRedirect(req, resp);		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRedirect(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRedirect(req, resp);
	}
	
	private void handleRedirect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "redirectTo", new Object[] { resp, getUrl() });
		}

		if(!req.getQueryString().equalsIgnoreCase(""))
			resp.setHeader("location", getUrl() + "?" + req.getQueryString());
		else
			resp.setHeader("location", getUrl());
		
		resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "redirectTo");
		}
	}
	
}
