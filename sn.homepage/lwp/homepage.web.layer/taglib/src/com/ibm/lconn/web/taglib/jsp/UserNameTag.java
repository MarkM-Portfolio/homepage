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

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;

import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.hpnews.service.waltz.UserSyncException;
import com.ibm.lconn.web.taglib.AbstractHomepageTag;

public class UserNameTag extends AbstractHomepageTag {

	private static final long serialVersionUID = -2487052268233271359L;
	
	private static String CLASS_NAME = UserNameTag.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private String externalId;
	
	public String getExternalId() {
		return (externalId == null ? "" : externalId);
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	private String getUsernameByExtId(String extId) throws UserSyncException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getUsernameByExtId", extId);
		
		String displayName = "";
		
		if(getUserServices() != null) {
			try {
				User user = getUserServices().getUserByExternalId(extId);
				if(user!=null) {
					displayName = user.getDisplayname();
				}
			} catch (ServiceException e) {
				if (logger.isLoggable(Level.SEVERE)) {
					logger.logp(Level.SEVERE, CLASS_NAME, "getUsernameByExtId",
							"Exception obtaining user display name", e);
				}
			}
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getUsernameByExtId", displayName);
		
		return displayName;
	}
	
	@Override
	public int doStartTag() throws JspException {
		if(getExternalId() != "") {
			try {
				pageContext.getOut().print(getUsernameByExtId(getExternalId()));
			} catch (IOException e) {
					if (logger.isLoggable(Level.SEVERE)) {
						logger.logp(Level.SEVERE, CLASS_NAME, "doStartTag",
								"Exception", e);
					}
			} catch (UserSyncException e) {
				if (logger.isLoggable(Level.SEVERE)) {
					logger.logp(Level.SEVERE, CLASS_NAME, "doStartTag",
							"Exception", e);
				}
			}
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

}
