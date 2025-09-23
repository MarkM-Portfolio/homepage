/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.dao.ibatis;

import static java.util.logging.Level.FINER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.dao.interfaces.IDeletedWidgetChecker;
import com.ibm.lconn.homepage.dao.interfaces.IWidgetInstDao;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.hpnews.data.dao.impl.ibatis.CrudDao;

public class WidgetInstDao extends CrudDao implements IWidgetInstDao {

	private static String CLASS_NAME = WidgetInstDao.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Autowired
	private IDeletedWidgetChecker deletedWidgetChecker;

	@Override
	public String getSchema() {
		return "HOMEPAGE";
	}

	@Override
	public String getTableName() {
		return "HP_WIDGET_INST";
	}

	public List<WidgetInst> findWidgetsInstancesByPersonId(String personId, String defaultPanelName) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "findWidgetsInstancesByPersonId",
					new Object[] { personId, defaultPanelName });

		Map<String, String> params = new HashMap<String, String>(2);
		params.put("personId", personId);
		params.put("defaultPanelName", defaultPanelName);	
		params.put("schema", getSchema());
		@SuppressWarnings("unchecked")
		List<WidgetInst> widgetsInstances = (List<WidgetInst>) sqlMapClientOperations.queryForList("findWidgetsInstancesByPersonId-"+getTableName(), params);
		
		filterDeleted(widgetsInstances);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "findWidgetsInstancesByPersonId",widgetsInstances);
		
		return widgetsInstances;
	}

	public List<WidgetInst> getWidgetInstancesByTabInstanceId(String tabInstanceId, String widgetId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getWidgetInstancesByTabInstanceId",
					new Object[] { tabInstanceId, widgetId });
		
		Map<String, String> params = new HashMap<String, String>(2);
		//params.put("personId", personId);
		params.put("tabInstId", tabInstanceId);
		params.put("widgetId", widgetId);
		params.put("schema", getSchema());
		
		@SuppressWarnings("unchecked")
		List<WidgetInst> widgetsInstances = (List<WidgetInst>) sqlMapClientOperations.queryForList("getWidgetInstancesByTabInstanceId-"+getTableName(), params);

		filterDeleted(widgetsInstances);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getWidgetInstancesByTabInstanceId",	widgetsInstances);
		
		return widgetsInstances;
	}

	public String getPersonIdByWidgetInstanceId(String widgetInstanceId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getPersonIdByWidgetInstanceId",
					new Object[] { widgetInstanceId });

		String personId = null;
		Map<String, String> params = new HashMap<String, String>(2);
		//params.put("personId", personId);
		params.put("widgetInstId", widgetInstanceId);
		params.put("schema", getSchema());
		personId = (String)sqlMapClientOperations.queryForObject("getPersonIdByWidgetInstanceId-"+getTableName(), params);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getPersonIdByWidgetInstanceId",
					personId);
		
		return personId;
	}

	public long getCountOpenWidgets()  {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getCountOpenWidgets");
		
		long result = -1;
		
		Map<String, String> params = new HashMap<String, String>(2);
		params.put("schema", getSchema());
		result = (Long)sqlMapClientOperations.queryForObject("getCountOpenWidgets-"+getTableName(),params);
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getCountOpenWidgets", result);
		
		return result;
	}

	public int removeByWidgetId(String widgetId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "removeByWidgetId",
					new Object[] { widgetId });
		
		int rowsDeleted = 0;
		
		Map<String, String> params = new HashMap<String, String>(2);
		params.put("schema", getSchema());
		params.put("widgetId", widgetId);
		rowsDeleted = sqlMapClientOperations.delete("removeByWidgetId-" + getTableName(), params);
		
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "removeByWidgetId");
		
		return rowsDeleted;
	}

	public List<WidgetInst> findWidgetsInstancesByTabInstance(String tabInstId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "findWidgetsInstancesByTabInstance",
					new Object[] { tabInstId });
		
		Map<String, String> params = new HashMap<String, String>(2);
		params.put("schema", getSchema());
		params.put("tabInstId", tabInstId);
		
		@SuppressWarnings("unchecked")
		List<WidgetInst> widgetsInstances = (List<WidgetInst>) sqlMapClientOperations.queryForList("findWidgetsInstancesByTabInstance-"+getTableName(), params);
		
		filterDeleted(widgetsInstances);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "findWidgetsInstancesByPersonId",widgetsInstances);
		
		return widgetsInstances;
	}

	public int removeByInstanceIds(final List<String> instanceIds) {
		final boolean FINER_P = logger.isLoggable(FINER);
		
		if (FINER_P)
			logger.entering(CLASS_NAME, "removeByInstanceIds", instanceIds);
		
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("schema", getSchema());
		params.put("instanceIds", instanceIds);
		
		final int res = sqlMapClientOperations.delete("delete-HP_WIDGET_INST_MULTI", params);
		
		if (FINER_P)
			logger.exiting(CLASS_NAME, "removeByInstanceIds", instanceIds);
		
		return res;
	}

	@Override
	public WidgetInst getByPK(String key) {
		WidgetInst inst = (WidgetInst) super.getByPK(key);
		
		if (inst == null || isDeleted(inst)) {
			return null;
		}
		
		return inst;
	}

	/**
	 * Check if a single instance is deleted
	 * @param inst
	 * @return
	 */
	private boolean isDeleted(WidgetInst inst) {
		final boolean FINER = logger.isLoggable(Level.FINER);
		boolean result = false;
		
		if (FINER)
			logger.entering(CLASS_NAME, "isDeleted", inst);
		
		// build set to check
		final List<String> ids2Check = new ArrayList<String>(1);
		ids2Check.add(inst.getWidgetId());
		
		// remove dead instances and clean list
		try {
			final List<String> disabledList = deletedWidgetChecker.getDeletedList(ids2Check);
			if (disabledList !=null) {
				result = disabledList.size() == 1;
			}
			else result = false;
		} catch (Exception e) {
			if (FINER) {
				logger.throwing(CLASS_NAME, "isDeleted", e);
			}
			throw new RuntimeException("Error filtering single filter ID.", e);
		}
		
		if (FINER)
			logger.exiting(CLASS_NAME, "isDeleted", result);
		
		return result;
	}

	/**
	 * Hackish technique to filter. I would prefer to do this up at the Service
	 * code level, but the usage of this DAO is spread out all over the Homepage
	 * code. To limit the damage of the 'soft-delete' change I am filtering
	 * deleted widgets down in the DAO.
	 * 
	 * @param widgetsInstances
	 */
	public void filterDeleted(List<WidgetInst> widgetsInstances) {
		final boolean FINER = logger.isLoggable(Level.FINER);
		
		if (FINER)
			logger.entering(CLASS_NAME, "filterDeleted", widgetsInstances);
		
		// build set to check
		final List<String> ids2Check = new ArrayList<String>(widgetsInstances.size());
		for (WidgetInst inst : widgetsInstances) {
			ids2Check.add(inst.getWidgetId());
		}
		
		// remove dead instances and clean list
		try {
			final List<String> disabledList = deletedWidgetChecker.getDeletedList(ids2Check);
			
			if (disabledList != null && disabledList.size() > 0) {
				removeByInstanceIds(disabledList);
				
				final Set<String> disabledSet = new HashSet<String>(disabledList);
				
				Iterator<WidgetInst> instIt = widgetsInstances.iterator();
				while (instIt.hasNext()) {
					WidgetInst inst = instIt.next();
					if (disabledSet.contains(inst.getWidgetId())) {
						instIt.remove();
					}
				}
			}
		} catch (Exception e) {
			if (FINER) {
				logger.throwing(CLASS_NAME, "filterDeleted", e);
			}
			throw new RuntimeException("Error filtering deleted ID list.", e);
		}
		
		if (FINER)
			logger.exiting(CLASS_NAME, "filterDeleted", widgetsInstances);
	}
	
	/**
	 * @return the deletedWidgetChecker
	 */
	public IDeletedWidgetChecker getDeletedWidgetChecker() {
		return deletedWidgetChecker;
	}

	/**
	 * @param deletedWidgetChecker the deletedWidgetChecker to set
	 */
	public void setDeletedWidgetChecker(IDeletedWidgetChecker deletedWidgetChecker) {
		this.deletedWidgetChecker = deletedWidgetChecker;
	}


}
