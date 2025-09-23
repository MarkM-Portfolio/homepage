/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2006, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.web.taglib.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

abstract public class AbstractBodyTransformer extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4253863463315209218L;

	public int doAfterBody() throws JspException {
		try {
			BodyContent bc = getBodyContent();
			bc.getEnclosingWriter().print(transform(bc.getString()));
		} catch (Exception e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}

	abstract protected String transform(String s) throws JspException;

}
