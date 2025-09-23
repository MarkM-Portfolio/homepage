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

package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.lconn.homepage.dao.interfaces.IMetricStatDao;
import com.ibm.lconn.homepage.dao.interfaces.ISystemMetricsDao;
import com.ibm.lconn.homepage.dao.model.MetricStat;
import com.ibm.lconn.homepage.model.Metric;
import com.ibm.lconn.homepage.model.ServerMetricsData;
import com.ibm.lconn.homepage.services.IServerMetricsCachingService;
import com.ibm.lconn.homepage.services.IServerMetricsServices;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;

/**
 * 
 * Implementation for server metrics services
 * 
 * @author Yishan Sun / Paul Barford / William Doran
 * adding 
 */
public class ServerMetricsServicesImpl implements IServerMetricsServices {

	private static String CLASS_NAME = ServerMetricsServicesImpl.class.getName();
	
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Autowired
	private ResourceBundle serviceResourceBundle;
	
	@Autowired
	@Qualifier("metricStatDao")
	private IMetricStatDao statisticsDao;
	
	@Autowired
	@Qualifier("systemMetricsDao")
	private ISystemMetricsDao systemMetrics;
	 
	public ISystemMetricsDao getSystemMetrics() {
		return systemMetrics;
	}

	public void setSystemMetrics(ISystemMetricsDao systemMetrics) {
		this.systemMetrics = systemMetrics;
	}

	public IMetricStatDao getStatisticsDao() {
		return statisticsDao;
	}

	public void setStatisticsDao(IMetricStatDao statisticsDao) {
		this.statisticsDao = statisticsDao;
		
	}
	
	private Date today;
	private Map <String,MetricCacheData> metricCacheList = new HashMap <String,MetricCacheData>();//stores last metrics received
	
	@Autowired
	private IServerMetricsCachingService serverMetricsCachingService;
	

	private List<String> metricKeys = null;
	
	public IServerMetricsCachingService getServerMetricsCachingService() {
		return serverMetricsCachingService;
	}

	public void setServerMetricsCachingService(IServerMetricsCachingService serverMetricsCachingService) {
		this.serverMetricsCachingService = serverMetricsCachingService;
	}

	public ResourceBundle getServiceResourceBundle() {
		return serviceResourceBundle;
	}

	public void setServiceResourceBundle(ResourceBundle serviceResourceBundle) {
		this.serviceResourceBundle = serviceResourceBundle;
	}

	public void setToday(Date now) {
		this.today = now;
	}
		
	public List<ServerMetricsData> getServerMetricsData(int type){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getServerMetricsData",
					new Object[] { type });
			long milli = System.currentTimeMillis();
			Date now = new Date(milli);
			List<IModel> metricList = getMetricsFromDB(now);
			List<ServerMetricsData> data =  new ArrayList<ServerMetricsData>();//will store the data items to send to JSP
			if( metricList != null){
				for(IModel metric1 : metricList){
					MetricStat metric = (MetricStat) metric1;
					if(metric.getMetricType() == type){
						seperateAndAddMetrics(data, metric);
					}
				}				
			}
			else{
				if (logger.isLoggable(FINEST))
					logger.logp(FINEST, CLASS_NAME, "getMetricMap", "Problem with metricStat Dao");
			}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getServerMetricsData", data);
		return data;
	}

	/**
	 * Each MetricStat object contains multiple individual statistics to be
	 * displayed, trend statistics hold a value for the last 24 hours, 7days and 
	 * 1 month. These must be seperated and added seperately with the proper
	 * resource string. The top5list statistics are stored in a string which itself
	 * is split. Scalar values are simply added as they are.
	 * @param data
	 * @param metric
	 */
	public void seperateAndAddMetrics(List<ServerMetricsData> data,
			MetricStat metric) {
		ServerMetricsData item = new ServerMetricsData();
		String key = metric.getResBundleKey();
		String metricDisplayType = evaluateMetricDisplayStyle(metric);
		if(metricDisplayType.equalsIgnoreCase("trend")){//trend over time
			data.add(new ServerMetricsData(key,"scalar", metric.getTotStat(), metric.getRecordedOn()));//total value
			data.add(new ServerMetricsData(key +".today", "scalar",metric.getCountLast24h(),metric.getTotStat(), metric.getRecordedOn()));//total over last day
			data.add(new ServerMetricsData(key+ ".week", "scalar", metric.getCountLast7d(),metric.getTotStat(), metric.getRecordedOn()));//total over last 7 days
			data.add(new ServerMetricsData(key +".month","scalar",metric.getCountLast1m(),metric.getTotStat(), metric.getRecordedOn()));//total over last month

		}else if(metricDisplayType.equalsIgnoreCase("list")){//top 5 list
			String listString = metric.getTopStats();
			Map<String, Long> top5List = splitTop5String(listString); 
			item.setTop5list(top5List);
			item.setKey(key);
			item.setType("list");
			item.setRecordedOn(metric.getRecordedOn());
			data.add(item);
		}
		else if(metricDisplayType.equalsIgnoreCase("scalar")){//scalar value
			data.add(new ServerMetricsData(key,"scalar", metric.getTotStat(), metric.getRecordedOn())); 
		}
		else{
			if (logger.isLoggable(FINEST))
				logger.logp(FINEST, CLASS_NAME, "getMetricMap",
						"Metric type not supported, only 0, 1,2: value="+metricDisplayType);
		}
	}

	public Map<String, Long> splitTop5String(String listString) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "splitTop5String",
					new Object[] { listString });
		Map<String, Long> top5List = new LinkedHashMap<String, Long>();
		Scanner scan1 = new Scanner(listString).useDelimiter(";");//name1:count1;name2:count2;
		Scanner scan2 = null;
		String pair, name = null;
		long count = 0;
		while(scan1.hasNext()){
			pair = scan1.next();
			scan2 = new Scanner(pair).useDelimiter(":");
			if(scan2.hasNext())
				name = scan2.next();
			if(scan2.hasNext())
				count = scan2.nextLong();
			top5List.put(name, count);
			if (logger.isLoggable(FINEST))
				logger.logp(FINEST, CLASS_NAME, "splitTop5String", "Adding:"+name+" count:"+count+"to top5list");
		}		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "splitTop5String", top5List);
		return top5List;
	}

	public List<String> getMetricKeys(Date now){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getMetricKeys");
		if(metricKeys == null){//shouldnt happen as keys should be set by get metric map
	
			List<IModel> metricList = getMetricsFromDB(now);
			metricKeys = new ArrayList<String>();//will store the list of metrics keys
			if( metricList != null){
				for(IModel metric1 : metricList){
					MetricStat metric = (MetricStat) metric1;
					String key = metric.getResBundleKey();
					metricKeys.add(key);
					if(evaluateMetricDisplayStyle(metric).equalsIgnoreCase("trend")){//trend over time
						metricKeys.add(key +".today");
						metricKeys.add(key +".week");
						metricKeys.add(key +".month");
						if(key.equalsIgnoreCase("homepage.metric.totals.visitor")){
							metricKeys.add(key +".hourly");
						}
					}
					else{
						if (logger.isLoggable(FINEST))
							logger.logp(FINEST, CLASS_NAME, "getMetricKeys",
									"Metric type not supported, only 0, 1,2: value="+evaluateMetricDisplayStyle(metric));
					}
				}
			}
			else{
				if (logger.isLoggable(FINEST))
					logger.logp(FINEST, CLASS_NAME, "getMetricKeys", "Problem with metricStat Dao");
			}
		}//else just return the metric keys
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getMetricKeys", metricKeys);
		return metricKeys;
	}
	
	/**
	 * This is to support compatability with the Mbean, returns a Map
	 * of the metrics and their values
	 */
	public Map<String, Object> getMetricMap(Date now) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getMetricMap");
		Map<String, Object> metricMap = new HashMap<String, Object>();
		List<IModel> metricList = getMetricsFromDB(now);
		metricKeys = getMetricKeys(now);//will store the list of metrics keys
		if( metricList != null){
			for(IModel metric1 : metricList){
				MetricStat metric = (MetricStat) metric1;
				String key = metric.getResBundleKey();
				String metricDisplayStyle = evaluateMetricDisplayStyle(metric);
				if(metricDisplayStyle.equalsIgnoreCase("trend")){//trend over time
					metricMap.put(key, metric.getTotStat());//total value
					metricMap.put(key +".today", metric.getCountLast24h());//total over last day
					metricMap.put(key+ ".week", metric.getCountLast7d());//total over last 7 days
					metricMap.put(key +".month",metric.getCountLast1m());//total over last month
					/*if(key.equalsIgnoreCase("homepage.metric.totals.visitor")){
						metricMap.put(key +".hourly",getHourlyTotal(metric));
					}*/					
				}else if(metricDisplayStyle.equalsIgnoreCase("list")){//top 5 list
					metricMap.put(key, metric.getTopStats());//list of values in a string
				}
				else if(metricDisplayStyle.equalsIgnoreCase("scalar")){//scalar value
					metricMap.put(key, metric.getTotStat()); 
				}
				else{
					if (logger.isLoggable(FINEST))
						logger.logp(FINEST, CLASS_NAME, "getMetricMap",
								"Metric type not supported, only 0, 1,2: value="+metricDisplayStyle);
				}
			}
		}
		else{
			if (logger.isLoggable(FINEST))
				logger.logp(FINEST, CLASS_NAME, "getMetricMap", "Problem with metricStat Dao");
		}
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getMetricMap", metricMap);
		return metricMap;
	}
	/**
	 * Preforms the DAO lookup of metrics if the timeout period of
	 * one day has expired, if 
	 */
	public List<IModel> getMetricsFromDB(Date now){
 	 if (logger.isLoggable(FINER))
		logger.entering(CLASS_NAME, "getMetricsFromDB");
	 	Calendar currentTime = Calendar.getInstance();
	 	currentTime.setTime(now);
	 	MetricCacheData cacheData = this.metricCacheList.get(ApplicationContext.getOrganizationId());
	 	if(cacheData == null){
	 		cacheData = new MetricCacheData(currentTime, new ArrayList<IModel>());
	 	}
	 	
	 	if(isUpdateRequired(currentTime, cacheData.getLastUpdate())){
	 		
	 		synchronized(this){
	 			List<IModel> metricListFromDb = statisticsDao.selectByDate(currentTime.getTime());
	 			cacheData.setMetricList(metricListFromDb);
	 			cacheData.setLastUpdate(currentTime);
	 		}
	 	}
	 	else{
	 		//last update was today,need to check if 
	 		//this is for the first time the system runs
	 		if(cacheData.getMetricList() == null || cacheData.getMetricList().size() ==0){
		 		synchronized(this){
		 			List<IModel> metricListFromDb  = statisticsDao.selectByDate(currentTime.getTime());
		 			cacheData.setMetricList(metricListFromDb);
		 			cacheData.setLastUpdate(currentTime);
		 		}	  			
	 		}
	 	}

	 if (logger.isLoggable(FINER))
		logger.exiting(CLASS_NAME, "getMetricsFromDB", cacheData.getMetricList());
	 return cacheData.getMetricList();
	}
	/**
	 * Function to figure out whether the last updated time is different
	 * from the current time, compares the current day and year. Returns true
	 *  
	 * @param currentTime
	 * @return
	 */
	public boolean isUpdateRequired(Calendar currentTime, Calendar lastUpdate){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "wasLastUpdateToday",
					new Object[] { currentTime, lastUpdate });
		boolean updateRequired = false;
		if((currentTime.get(Calendar.DAY_OF_YEAR) <= lastUpdate.get(Calendar.DAY_OF_YEAR))
		 		&& (currentTime.get(Calendar.YEAR) <= lastUpdate.get(Calendar.YEAR))){
			updateRequired = false;
		}
		else{
			updateRequired = true;
		}
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "wasLastUpdateToday", updateRequired);
		return updateRequired;
	}
	private String evaluateMetricDisplayStyle(MetricStat statistic){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "evaluateMetricDisplayStyle",
					new Object[] { statistic });
			String style = null;
			for(Metric metric : Metric.values()){
				if(metric.getType() == statistic.getMetricType()){
					style = metric.getStyle();
					break;
				}
			}
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "evaluateMetricDisplayStyle", style);
		return style;
	}
	
	private class MetricCacheData{
		private Calendar lastUpdate;
		private List<IModel> metricList;
		public Calendar getLastUpdate() {
			return lastUpdate;
		}
		public void setLastUpdate(Calendar lastUpdate) {
			this.lastUpdate = lastUpdate;
		}
		public void setMetricList(List<IModel> metricList){
			this.metricList = metricList;
		}
		public List<IModel> getMetricList(){
			return this.metricList;
		}
		MetricCacheData(Calendar d, List<IModel> metricList){
			lastUpdate = d;
			this.metricList = metricList;
		}
	}


}
