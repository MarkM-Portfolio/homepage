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

package com.ibm.lconn.web.taglib.jsp;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.lconn.homepage.utils.ResourceBundle;

public class BuildNumberTag extends TagSupport {

	private static final long serialVersionUID = -2063590570573788511L;
	private static String CLASS_NAME = BuildNumberTag.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	private final static String BUILD_VERSION_STRING = "build.version";

	private boolean removeSpaces = false;

	public void setRemoveSpaces(boolean value) {
		removeSpaces = value;
	}

	public boolean getRemoveSpaces() {
		return removeSpaces;
	}

	protected ResourceBundle getCatalogServiceUIResourceBundle() {
		ServletContext sc = pageContext.getServletContext();

		return (ResourceBundle) WebApplicationContextUtils.getWebApplicationContext(sc).getBean("catalogServiceUIResourceBundle");
	}
	
	protected ResourceBundle getDatabaseResourceBundle() {
		ServletContext sc = pageContext.getServletContext();

		return (ResourceBundle) WebApplicationContextUtils.getWebApplicationContext(sc).getBean("databaseResourceBundle");
	}
	
	protected ResourceBundle getBuildStatsResourceBundle() {
		ServletContext sc = pageContext.getServletContext();

		return (ResourceBundle) WebApplicationContextUtils.getWebApplicationContext(sc).getBean("buildStatsResourceBundle");
	}
	
	public String getBuildString() {

		// Read string
		String num = getBuildStatsResourceBundle().getString(BUILD_VERSION_STRING);

		if (!getRemoveSpaces()) {
			// Uses localized string 'Release 2.0 ...'
			String message = getCatalogServiceUIResourceBundle().getString("build.about.release");
			String buildString = MessageFormat.format(message, num);

			return buildString;
		} else {
			return num.replace(' ', '_');
		}
	}

	@Override
	public int doStartTag() throws JspException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getBuildString", getBuildString());

		try {
			pageContext.getOut().print(getBuildString());
		}
		catch (IOException e) {
			if (logger.isLoggable(Level.SEVERE)) {
				logger.logp(Level.SEVERE, CLASS_NAME, "doStartTag",
						"Exception", e);
			}
		}

		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

}
