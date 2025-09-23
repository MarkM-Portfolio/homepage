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

package com.ibm.lconn.web.taglib;

import javax.servlet.ServletContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.utils.ResourceBundle;

public abstract class AbstractHomepageTag extends TagSupport {

	private static final long serialVersionUID = -8055581834868802970L;
	
	protected ResourceBundle getWebLogResourceBundle() {
		ServletContext sc = pageContext.getServletContext();

		return (ResourceBundle) WebApplicationContextUtils
				.getWebApplicationContext(sc).getBean("webResourceBundle");
	}

	protected ResourceBundle getJSPResourceBundle() {

		ServletContext sc = pageContext.getServletContext();

		return (ResourceBundle) WebApplicationContextUtils
				.getWebApplicationContext(sc).getBean("jspResourceBundle");
	}
	
	protected IUserServices getUserServices() {

		ServletContext sc = pageContext.getServletContext();

		return (IUserServices) WebApplicationContextUtils
				.getWebApplicationContext(sc).getBean("userServices");
	}

}
