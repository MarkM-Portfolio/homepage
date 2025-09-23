/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.dao.ibatis;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.ibm.lconn.hpnews.data.dao.impl.ibatis.CrudDao;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;
import com.ibm.lconn.homepage.dao.interfaces.ISystemMetricsDao;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemMetricsDao extends CrudDao implements ISystemMetricsDao{
	
	private final static String CLASS_NAME = SystemMetricsDao.class.getName();
	
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private static String dbVendor = null;
	
	@Autowired
	private ResourceBundle databaseResourceBundle;
	private String WIDGET_INST_TABLE = "HP_WIDGET_INST";
	private String PERSON_TABLE = "PERSON";


	/* Person based metrics*/
	
	public long getNumberUniqueVisit(Date begin, Date end){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getNumberUniqueVisit");

		long numberPerson = 0;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schema",getSchema());
		params.put("begin", begin);
		params.put("end", end);
		numberPerson = (Long) sqlMapClientOperations.queryForObject("getNumberUniqueVisit-metrics", params);

		return numberPerson;
	}
	public long getCountHPPerson() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getCountHPPerson");

		long numberPerson = 0;
		HashMap<String, String> map = new HashMap<String, String>(1);
		map.put("schema",getSchema());
		numberPerson = (Long) sqlMapClientOperations.queryForObject("getCountHPPerson-" + getPersonTableName()+"-metrics", map);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getCountHPPerson", numberPerson);

		return numberPerson;
	}
	private String getPersonTableName() {
		return PERSON_TABLE;
	}

	/* widget based metrics
	 */
	public long getCountAllWidgets() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getCountAllWidgets");

		long numberWidget = 0;

		Map<String, String> map = new HashMap<String, String>(2);
		map.put("schema", getSchema());
		numberWidget = (Long) sqlMapClientOperations.queryForObject("getCountAllWidgets-metrics", map);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getCountAllWidgets", numberWidget);

		return numberWidget;
	}

	public long getCountEnabledWidgets() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getCountEnabledWidgets");

		long numberWidget = 0;
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("schema", getSchema());
		numberWidget = (Long) sqlMapClientOperations.queryForObject("getCountEnabledWidgets-metrics", map);


		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getCountEnabledWidgets", numberWidget);

		return numberWidget;
	}

	@SuppressWarnings("unchecked")
	public List getWidgetsUsage() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getWidgetsUsage");

		List widgetUsage = null;
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("schema", getSchema());
		widgetUsage = sqlMapClientOperations.queryForList("getWidgetsUsage-metrics", map);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getWidgetsUsage", widgetUsage);

		return widgetUsage;
	}
	/* widget instance metrics*/
	public long getCountOpenWidgets()  {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getCountOpenWidgets");
		
		long result = -1;
		
		Map<String, String> params = new HashMap<String, String>(2);
		params.put("schema", getSchema());
		result = (Long)sqlMapClientOperations.queryForObject("getCountOpenWidgets-"+getWidgetInstTableName()+"-metrics",params);
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getCountOpenWidgets", result);
		
		return result;
	}
/*stories metrics */
	public long getStoriesAddedSince(Date begin, Date end){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getStoriesAddedSince", new Object[] {
					begin, end });
		long result =-1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schema",getSchema());
		params.put("begin", begin);
		params.put("end", end);
		result = (Long) sqlMapClientOperations.queryForObject("getCountStoriesAddedSince", params);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getStoriesAddedSince", result);
		return result;
	}
	
	public long getStoriesAdded(){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getStoriesAdded");
		long result =-1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schema",getSchema());
		result = (Long) sqlMapClientOperations.queryForObject("getCountStoriesAdded", params);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getStoriesAdded", result);
		return result;
	}
	
	public long getStoriesSaved(){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getStoriesSaved");
		long result =-1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schema",getSchema());
		result = (Long) sqlMapClientOperations.queryForObject("getCountStoriesSaved", params);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getStoriesSaved", result);
		return result;
	}	

	public long getStoriesSavedSince(Date begin, Date end){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getStoriesSavedSince", new Object[] {
					begin, end });
		long result =-1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schema",getSchema());
		params.put("begin", begin);
		params.put("end", end);
		result = (Long) sqlMapClientOperations.queryForObject("getCountStoriesSavedSince", params);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getStoriesSavedSince", result);
		return result;
	}	
	
	public List getPopularWidgets(){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getPopularWidgets");
		List results =null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schema",getSchema());
		results = sqlMapClientOperations.queryForList("getCountPopularWidgets", params);
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getPopularWidgets");
		return results;
	}
	
	public long getCountCustomizedPages(){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getCountCustomizedPages");
		long result = -1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schema",getSchema());
		result = (Long) sqlMapClientOperations.queryForObject("getCountCustomizedPages", params);		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getCountCustomizedPages", result);
		return result; 
	}
	
	public ResourceBundle getDatabaseResourceBundle() {
		return databaseResourceBundle;
	}

	public void setDatabaseResourceBundle(ResourceBundle databaseResourceBundle) {
		this.databaseResourceBundle = databaseResourceBundle;
	}
	public String getWidgetInstTableName(){return WIDGET_INST_TABLE;}
	
	public String getSchema(){return "HOMEPAGE";}
	
	
	public String getDbVendor() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getDbVendor");
		
		if (dbVendor == null) {
			try {
				Connection conn = getSqlMapClientTemplate().getDataSource().getConnection();
				
				DatabaseMetaData meta = conn.getMetaData();
				String driverName = meta.getDriverName().toLowerCase();
				String productName = meta.getDatabaseProductName();
				String productVersion = meta.getDatabaseProductVersion();
				String driverVersion = meta.getDriverVersion();
				String url = meta.getURL();
				
				if (logger.isLoggable(FINER)) {
					logger.logp(FINER, CLASS_NAME, "getDbVendor", "This is the driver name used to run jdbc driverName: "+driverName);
					logger.logp(FINER, CLASS_NAME, "getDbVendor", "This is the productName name used to run jdbc productName: "+productName);
					logger.logp(FINER, CLASS_NAME, "getDbVendor", "This is the productVersion name used to run jdbc productVersion: "+productVersion);
					logger.logp(FINER, CLASS_NAME, "getDbVendor", "This is the driverVersion name used to run jdbc driverVersion: "+driverVersion);
					logger.logp(FINER, CLASS_NAME, "getDbVendor", "This is the url name used to run jdbc url: "+url);

				}
				
				if (driverName.toLowerCase().contains(ICrud.DERBY) || url.toLowerCase().contains(ICrud.DERBY))
					dbVendor = ICrud.DERBY;
	
				if (driverName.toLowerCase().contains(ICrud.DB2) || url.toLowerCase().contains(ICrud.DB2))
					dbVendor = ICrud.DB2;

				if (driverName.toLowerCase().contains("as/400"))
					dbVendor = ICrud.DB2;

				if (driverName.toLowerCase().contains(ICrud.ORACLE) || url.toLowerCase().contains(ICrud.ORACLE))
					dbVendor = ICrud.ORACLE;
	
				if (driverName.toLowerCase().contains(ICrud.SQLSERVER) || url.toLowerCase().contains(ICrud.SQLSERVER))
					dbVendor = ICrud.SQLSERVER;
				
				conn.close();
				
			} catch (Exception e) {
				
				String msg = getDatabaseResourceBundle().getString("error.dbvendor");
				if (logger.isLoggable(SEVERE)) {
					logger.logp(SEVERE, CLASS_NAME, "getDbVendor", msg, e);
				}
				e.printStackTrace();
			}
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getDbVendor", dbVendor);
		
		return dbVendor;
	}
	
	@Override
	public String getTableName() {
		// Intentionally left blank
		return null;
	}	
}
