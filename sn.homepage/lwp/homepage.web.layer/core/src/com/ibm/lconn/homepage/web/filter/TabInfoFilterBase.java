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
import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ibm.lotus.connections.dashboard.common.core.exceptions.DboardException;
import com.ibm.lotus.connections.dashboard.common.util.ResourceBundleHelper;
import com.ibm.lotus.connections.dashboard.nls.IWebResources;
import com.ibm.lotus.connections.dashboard.service.core.internal.impl.TabType;
import com.ibm.lotus.connections.dashboard.service.core.internal.impl.TabSupportServices;

public abstract class TabInfoFilterBase implements Filter {
	private final static String CLASS_NAME = TabInfoFilterBase.class.getName();

	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private static ResourceBundleHelper resourceBundleHelper = new ResourceBundleHelper(
			IWebResources.RESOURCE_BUNDLE);

	private TabSupportServices panelSvc;
	public static final String TAB_INSTANCE_ID = "tabInstanceId";

	protected void setTabInstanceIdRequestParam(HttpServletRequest request,
			TabType tabType) throws ServletException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "setTabInstanceIdRequestParam",
					new Object[] { request, tabType });
		}

		String tabId = null;

		// get internal user id first
		// important note: we are making the assumption that the filter is
		// registered AFTER the UserInfoFilter in web.xml
		String internalUserId = getInternalUserId(request);

		if (internalUserId == null) {
			String msg = resourceBundleHelper.getString(
					"error.filter.get.userId", internalUserId);
			ServletException servletEx = new ServletException(msg);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "setTabInstanceIdRequestParam",
						msg, servletEx);
			}
			throw servletEx;
		}

		try {

			// check if request param already set
			String tabInstanceParam = request.getParameter(TAB_INSTANCE_ID);

			if (tabInstanceParam == null) {
				tabId = panelSvc.getUserPanel(internalUserId, tabType);
				request.setAttribute(TAB_INSTANCE_ID, tabId);
			} else {
				request.setAttribute(TAB_INSTANCE_ID, tabInstanceParam);
			}

		} catch (DboardException e) {
			String msg = resourceBundleHelper.getString(
					"error.filter.get.tab.instance", internalUserId);
			ServletException servletEx = new ServletException(msg, e);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "setTabInstanceIdRequestParam",
						msg, servletEx);
			}
			throw servletEx;
		}

		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "setTabInstanceIdRequestParam");
		}
	}

	private String getInternalUserId(HttpServletRequest request) {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getInternalUserId",
					new Object[] { request });
		}

		String internalUserId = null;

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			HttpSession session = httpRequest.getSession();
			internalUserId = (String) session
					.getAttribute(UserInfoFilter.USER_INFO_INTERNAL_ID);
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "getInternalUserId", internalUserId);
		}

		return internalUserId;
	}

	public void init(FilterConfig config) throws ServletException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "init");
		}

		panelSvc = TabSupportServices.getInstance();

		if (panelSvc == null) {
			String error = resourceBundleHelper
					.getString("error.initializing.panel.service");
			ServletException servletEx = new ServletException(error);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "init", error, servletEx);
			}
			throw servletEx;
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "init");
		}
	}

}*/
