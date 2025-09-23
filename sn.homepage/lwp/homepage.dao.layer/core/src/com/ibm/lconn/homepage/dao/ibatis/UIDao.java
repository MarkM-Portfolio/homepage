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

package com.ibm.lconn.homepage.dao.ibatis;

import com.ibm.lconn.homepage.dao.interfaces.IUIDao;
import com.ibm.lconn.homepage.dao.model.UI;
import com.ibm.lconn.hpnews.data.dao.impl.ibatis.CrudDao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.logging.Level.FINER;


/**
 *
 * @author Lorenzo Notarfonzo
 *
 *
 */
public class UIDao extends CrudDao implements IUIDao {

	private static String CLASS_NAME = UIDao.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Override
	public String getSchema() {
		return "HOMEPAGE";
	}

	@Override
	public String getTableName() {
		return "HP_UI";
	}

	public void disableTestModeByUserID(String personId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "disableTestModeByUserID", personId);

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("personId", personId);
		map.put("schema", getSchema());
		sqlMapClientOperations.update("disableTestModeByUserID-" + getTableName(), map);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "disableTestModeByUserID");

	}

	public void enableTestModeByUserID(String personId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "enableTestModeByUserID", personId);

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("personId", personId);
		map.put("schema", getSchema());
		sqlMapClientOperations.update("enableTestModeByUserID-" + getTableName(), map);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "enableTestModeByUserID");

	}

	public UI getUserUIByPersonId(String personId) {
		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.entering(CLASS_NAME, "getUserUIByPersonId",
					new Object[] { personId });

		UI ui = null;

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("personId", personId);
		map.put("schema", getSchema());
		ui = (UI)sqlMapClientOperations.queryForObject("getUserUIByPersonId-" + getTableName(), map);


		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.exiting(CLASS_NAME, "getUserUIByPersonId", ui);
		return ui;
	}

	public int updateLanguageByPersonId(String personId, String locale) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateLanguage", new Object[]{personId, locale});

		Map<String, String> map = new HashMap<String, String>(3);
		map.put("personLang",locale);
		map.put("personId", personId);
		map.put("schema", getSchema());
		int count = sqlMapClientOperations.update("updateLanguageByPersonId-" + getTableName(), map);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateLanguage");

		return count;
	}

	public int updateLastVisitByPersonId(String personId, Date timestamp) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateLastVisitByPersonId", personId);

		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("personId",personId);
		map.put("lastVisit", timestamp);
		map.put("schema", getSchema());
		int count = sqlMapClientOperations.update("updateLastVisitByPersonId-" + getTableName(), map);


		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateLastVisitByPersonId");

		return count;
	}

	public int updateLanguageAndLastVisitByPersonId(String personId, String locale, Date timestamp) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateLanguageAndLastVisitByPersonId", new Object[]{ personId, locale, timestamp});

		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("personLang",locale);
		map.put("personId", personId);
		map.put("lastVisit", timestamp);
		map.put("schema", getSchema());
		int count = sqlMapClientOperations.update("updateLanguageAndLastVisitByPersonId-" + getTableName(), map);


		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateLanguageAndLastVisitByPersonId");

		return count;
	}

	public int updateLanguage(String locale, UI ui) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "updateLanguage", ui);

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("personLang",locale);
		map.put("uiId", ui.getUiId());
		map.put("schema", getSchema());
		int count = sqlMapClientOperations.update("updateLanguage-" + getTableName(), map);


		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "updateLanguage");

		return count;
	}


	public int countNumberOfUniqueVisitors() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "countNumberOfUniqueVisitors");

		int count = 0;

		Map<String, String> map = new HashMap<String, String>(1);
		map.put("schema", getSchema());
		count = (Integer) sqlMapClientOperations.queryForObject("countNumberOfUniqueVisitors-" + getTableName(), map);


		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "countNumberOfUniqueVisitors", count);

		return count;
	}

	public int resetWelcomeModeFlag(String personId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "resetWelcomeModeFlag", personId);

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("schema", getSchema());
		if (personId != null) {
			map.put("personId",personId);
		}

		int affectedRows = sqlMapClientOperations.update("resetWelcomeModeFlag-" + getTableName(), map);


		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "resetWelcomeModeFlag",affectedRows);

		return affectedRows;

	}



}
