/* ***************************************************************** */
/*                                                                   */
/* HCl Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2011, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.services.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Used by index.jsp and redirectAction.jsp to allow retrieval of users setting
 * for display of Getting Started tab.
 * 
 * @author BrianOG
 *
 */
public class GettingStartedPreferenceHelper {
	private static final String CLASS_NAME = GettingStartedPreferenceHelper.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
	private static GettingStartedPreferenceHelper INSTANCE = null;
	
	@Autowired
	private IUserServices userServices;

	// only ever one instance created, by ourselves
	private GettingStartedPreferenceHelper() {
		if ( LOGGER.isLoggable(Level.FINER) ) {
			LOGGER.entering(CLASS_NAME, "<constructor>");
		}
		
		init();
		
		if ( LOGGER.isLoggable(Level.FINER) ) {
			LOGGER.exiting(CLASS_NAME, "<constructor>");
		}
	}
	
	private void init() {
		if ( INSTANCE == null ) {
			INSTANCE = this;
			if ( LOGGER.isLoggable(Level.FINER) ) {
				LOGGER.logp(Level.FINER, CLASS_NAME, "init", "Setting INSTANCE: " + this);
			}
		}
	}
	
	/**
	 * Checks the DB for the users preference/setting for the "Do not show..." 
	 * checkbox on the Getting Started tab.
	 * 
	 * @param personInternalId - users internal ID for homepage. They may be null
	 * @param loginId - users loginId (remoteUser from request). This will be used
	 * to look up the users internal ID if we did not get one.
	 * @return true if Getting Started should be displayed (or if there was error
	 * accessing information) - false only if we find user & preference and it's 
	 * set to falfe
	 */
	public static boolean isGettingStartedDisplayEnabled(String personInternalId, String loginId) {
		if ( LOGGER.isLoggable(Level.FINER) ) {
			LOGGER.entering(CLASS_NAME, "isGettingStartedDisplayEnabled", new Object[] { personInternalId, loginId });
		}
		
		// if we got the personInternalId, we can avoid a lookup on loginId
		if ( personInternalId == null && loginId != null ) {
			try {
				User user = INSTANCE.userServices.getUserLoginName(loginId);
				personInternalId = user.getId();
			} catch (ServiceException e) {
				if ( LOGGER.isLoggable(Level.FINE) ) {
					LOGGER.logp(Level.FINE, CLASS_NAME, "isGettingStartedDisplayEnabled", "Could not retrieve User for: " + loginId, e);
				}
			}
		}
		
		boolean enabled = true;

		if ( personInternalId != null ) {
			try {
				enabled = INSTANCE.userServices.isUserInWelcomeMode(personInternalId);
			} catch ( ServiceException e ) {
				if ( LOGGER.isLoggable(Level.FINE) ) {
					LOGGER.logp(Level.FINE, CLASS_NAME, "", "Could not retreive GettingStarted preference for user: " + personInternalId, e);
				}
			}
		}
		
		if ( LOGGER.isLoggable(Level.FINER) ) {
			LOGGER.exiting(CLASS_NAME, "isGettingStartedDisplayEnabled", new Boolean(enabled));
		}
		return enabled;
	}

	
	public IUserServices getUserServices() {
		return userServices;
	}

	
	public void setUserServices(IUserServices userServices) {
		if ( LOGGER.isLoggable(Level.FINER) ) {
			LOGGER.entering(CLASS_NAME, "setUserServices", userServices);
		}

		init();
		INSTANCE.userServices = userServices;
		
		if ( LOGGER.isLoggable(Level.FINER) ) {
			LOGGER.exiting(CLASS_NAME, "setUserServices");
		}
	}
}
