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

import static java.util.logging.Level.FINER;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.dao.interfaces.ITabInstDao;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.hpnews.data.dao.impl.ibatis.CrudDao;;

public class TabInstDao extends CrudDao implements ITabInstDao {

	private static String CLASS_NAME = TabInstDao.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	
	@Override
	public String getSchema() {
		return "HOMEPAGE";
	}

	@Override
	public String getTableName() {
		return "HP_TAB_INST";
	}

	public TabInst getPersonUIPageByDefaultName(String personId, String defaultName) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getPersonUIPageByDefaultName",
					new Object[] { personId, defaultName });
		
		TabInst pageUI = null;
		
		HashMap<String, String> params = new HashMap<String, String>(2);
		params.put("schema", getSchema());
		params.put("personId", personId);
		params.put("defaultName", defaultName);
		
		pageUI = (TabInst)sqlMapClientOperations.queryForObject("getPersonUIPageByDefaultName-"+getTableName(), params);
			
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getPersonUIPageByDefaultName",	pageUI);
		
		return pageUI;
	}

	public List<TabInst> getPersonUIPages(String personId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getPersonUIPages",
					new Object[] { personId });
		
		List<TabInst> pages = null;
		
		HashMap<String, String> params = new HashMap<String, String>(2);
		params.put("schema", getSchema());
		params.put("personId", personId);
		pages = (List<TabInst>)sqlMapClientOperations.queryForList("getPersonUIPages-"+getTableName(), params);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getPersonUIPages", pages);
				
		return pages;
	}


	
}
