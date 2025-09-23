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

package com.ibm.lconn.homepage.services;

import java.util.Date;

import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.model.UserResults;
import com.ibm.lconn.homepage.model.UserUI;
import com.ibm.lconn.hpnews.service.waltz.UserSyncException;

public interface IUserServices {
	
	/**
	 * 
	 * @param internalId
	 * @return
	 * @throws ServiceException
	 */
	public UserUI createUI(String internalId) throws ServiceException;
	
	/**
	 * 
	 * @param userInternalId
	 * @return
	 * @throws ServiceException
	 */
	public boolean isAdminTestModeActive(String userInternalId) throws ServiceException;
	
	/**
	 * 
	 * @param personId
	 * @return
	 * @throws ServiceException
	 */
	public UserUI getUserUIByPersonId(String personId) throws ServiceException;
	
	/**
	 * 
	 * @param userInternalId
	 * @throws ServiceException
	 */
	public void enableAdminTestMode(String userInternalId)	throws ServiceException;
	
	/**
	 * 
	 * @param userInternalId
	 * @throws ServiceException
	 */
	public void disableAdminTestMode(String userInternalId) throws ServiceException;
	

	/**
	 * 
	 * @param internalUserId
	 * @param lastVisit
	 */
	public void updateLastVisitForUser(String internalUserId);
	
	/**
	 * @param internalUserId
	 * @param language
	 */
	public void updateLastVisitAndLanguageForUser(String internalUserId, String language);
	
	/**
	 * 
	 * @param internalUserId
	 * @param language
	 */
	public void updateLanguageForUser(String internalUserId, String language);
	
	/**
	 * 
	 * @param userInternalId
	 * @param isWelcomeMode
	 */
	public void updateWelcome(String userInternalId, boolean isWelcomeMode);
	
	/**
	 * Update the welcome note
	 * 
	 * @param personId
	 * @return
	 * @throws ServiceException
	 */
	public void updateWelcomeNote(String userInternalId, boolean isWelcomeNote);
	
	/**
	 *  
	 * @param loginName
	 * @return
	 */
	public User getUserLoginName(String loginName) throws ServiceException;
	
	/**
	 * Using waltz
	 * 
	 * @param exId
	 * @return
	 * @throws UserSyncException 
	 */
	public User getUserByExternalId(String exId) throws ServiceException, UserSyncException;
	
	/**
	 * Check if the user is still in "welcome panel" mode.
	 * 
	 * @param personId
	 * @return
	 * @throws ServiceException
	 */
	public boolean isUserInWelcomeMode(String personId) throws ServiceException;
	
	/**
	 * Check if the user should still see the 'welcome note' on the Updates page
	 * 
	 * @param personId
	 * @return
	 * @throws ServiceException
	 */
	public boolean isWelcomeNote(String personId) throws ServiceException;
	
	/**
	 * 
	 * @param personId
	 * @param orgId
	 */
	public void fixupWidgetTabOrgIds(String personId, String orgId);
}
