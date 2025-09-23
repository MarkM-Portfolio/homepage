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

package com.ibm.lconn.homepage.dao.interfaces;

import java.util.Date;

import com.ibm.lconn.homepage.dao.model.UI;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;


public interface IUIDao extends ICrud  {
	/**
	 * Updates the language for a UI.
	 *
	 * @param ui
	 *            UI bean
	 * @throws DataAccessUpdateException
	 *             if there is a problem updating external ID
	 */
	public int updateLanguage(String locale, UI ui);

	/**
	 * Retrieve all the UI person info using the personId.
	 * The UI info are stored in the UI table. There is a one to one reletionship between
	 * a Person and an UI record.
	 *
	 * @param ui
	 *            UI bean
	 * @throws DataAccessRetrieveException
	 *              if there is a problem during the retrieving of the UI setting
	 */
	public UI getUserUIByPersonId(String personId);

	/**
	 * Disables administration test mode for a user.
	 *
	 * @param userID
	 *            The ID of user to disable test mode for.
	 * @throws DataAccessUpdateException
	 *             if there is a problem updating test mode.
	 */
	public void disableTestModeByUserID(String userID);

	/**
	 * Update the language of hp_ui record by personId
	 *
	 * @param locale
	 * @param ui
	 */
	public int updateLanguageByPersonId(String personId, String locale);

	/**
	 * Update the LastVisit timestamp by personId
	 *
	 * @param personId
	 * @param timestamp
	 */
	public int updateLastVisitByPersonId(String personId, Date timestamp);

	/**
	 * Updates the Language and LastVisit timestamp by person id
	 *
	 * @param personId
	 * @param locale
	 * @param timestamp
	 */
	public int updateLanguageAndLastVisitByPersonId(String personId, String locale, Date timestamp);

	/**
	 * Enables administration test mode for a user.
	 *
	 * @param userID
	 *            The ID of user to enable test mode for.
	 * @throws DataAccessUpdateException
	 *             if there is a problem updating test mode.
	 */
	public void enableTestModeByUserID(String userID);

	/**
	 * To count the number of unique visitors for server metrics
	 *
	 */
	public int countNumberOfUniqueVisitors();

	/**
	 * Resets the WELCOM_MODE flag to default value
	 * for the person if personId is specified
	 * or for all persons
	 * @param personId internal person Id
	 * @returns the number of rows affected in this operation
	 *
	 */
	public int resetWelcomeModeFlag(String personId);

}
