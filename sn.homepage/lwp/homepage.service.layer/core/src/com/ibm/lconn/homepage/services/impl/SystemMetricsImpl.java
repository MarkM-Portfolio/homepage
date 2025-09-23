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

package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.services.IServerMetricsServices;
import com.ibm.lconn.homepage.services.ISystemMetrics;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;


public class SystemMetricsImpl implements ISystemMetrics {
	private static String CLASS_NAME = SystemMetricsImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Autowired
	private IServerMetricsServices serverMetricsServices = null;
	
	public IServerMetricsServices getServerMetricsServices() {
		return serverMetricsServices;
	}

	public void setServerMetricsServices(
			IServerMetricsServices serverMetricsServices) {
		this.serverMetricsServices = serverMetricsServices;
	}

	private long lastGetTime = 0;
	private long getTimeoutInterval = 3600000; // 1 hour
	private Map<String, Map<String, Object>> metricCache = new HashMap<String, Map<String, Object>>();
	
	private static String[] metricKeyNames =null;

	public Map<String, Object> fetchMetrics() {
		if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "fetchMetrics", ApplicationContext.getOrganizationId());
        }
        
        String lookUpOrgId = ApplicationContext.getOrganizationId();
		// check to see if > an hour has elapsed since last get of metrics.
		// if an hour has elapsed since the last get, re-get the metrics
        if ( metricCache.get(lookUpOrgId) == null )
        {
			if ( hourTimeoutElapsed() ) {
		        metricCache.clear();
			}
			
	        if(logger.isLoggable(Level.FINEST)){
	        	logger.logp(Level.FINEST, CLASS_NAME, "fetchMetrics", "Metrics for organization ID [" + lookUpOrgId + "] is not in cache. Retrieving...");
	        } 
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			Date now = c.getTime();
	        
	        metricCache.put(lookUpOrgId, serverMetricsServices.getMetricMap(now));
	        
	        metricKeyNames = getMetricKeyNameArray();
			// reset time of last fetch
			lastGetTime = System.currentTimeMillis();
	        if(logger.isLoggable(Level.FINEST)){
	        	logger.logp(Level.FINEST, CLASS_NAME, "fetchMetrics", "lastGetTime: {0}", lastGetTime);
	        	logger.logp(Level.FINEST, CLASS_NAME, "fetchMetrics", "lastGetTime date: {0}", new Date(lastGetTime));
	        }
        }
		
        final Map<String, Object> orgMetrics = metricCache.get(lookUpOrgId);
		int metricMapCount = orgMetrics.size();
		int metricKeyCount = metricKeyNames.length;
		if(logger.isLoggable(Level.FINEST)){
			Object[] params = {metricMapCount, metricKeyCount};
        	logger.logp(Level.FINEST, CLASS_NAME, "fetchMetrics", "metric map count is: {0} metric key count is:{1}", params);        	
        }
		HashMap<String, Object> clonedMap = new HashMap<String, Object>(orgMetrics);

        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "fetchMetrics", clonedMap);
        }
		return clonedMap;
	}

	public long getLastGetTime() {
        if(logger.isLoggable(Level.FINER))
        	logger.entering(CLASS_NAME, "getLastGetTime");

        if(logger.isLoggable(Level.FINEST))
        	logger.logp(Level.FINEST, CLASS_NAME, "getLastGetTime", "lastGetTime: {0}", lastGetTime);

		long retLastGetTime = lastGetTime;
		// if we haven't gotten metrics yet, return now assuming we are about to
		// get them
		if (retLastGetTime == 0){
	        if(logger.isLoggable(Level.FINEST)){
	        	logger.logp(Level.FINEST, CLASS_NAME, "getLastGetTime", "Will return current time");
	        }
			retLastGetTime = System.currentTimeMillis();
		}

		if(logger.isLoggable(Level.FINER))
        	logger.entering(CLASS_NAME, "getLastGetTime", retLastGetTime);
        
		
		return retLastGetTime;
	}

	public String getMetricKeyName(int ix) {
		return getMetricKeyName(null, ix);
	}
	
	public String getMetricKeyName(String organizationId, int ix) {
        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "getMetricKeyName", ix);
        }
		String retStr = null;
		if(metricKeyNames == null){
			metricKeyNames = getMetricKeyNameArray(organizationId);
		}
		if ((ix < 0) || (ix >= metricKeyNames.length))
			retStr = null;
		else
			retStr = metricKeyNames[ix];

        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "getMetricKeyName", retStr);
        }
		return retStr;
	}	

	public String[] getMetricKeyNameArray() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getMetricKeyNameArray",
					new Object[] {});
		if(metricKeyNames == null){
			List<String> filteredKeyNames = null;
			filteredKeyNames = serverMetricsServices.getMetricKeys(new Date(System.currentTimeMillis()));
			metricKeyNames = filteredKeyNames.toArray(new String[0]);
		}
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getMetricKeyNameArray", metricKeyNames);
		return metricKeyNames;
	}
	
	public String[] getMetricKeyNameArray(String organizationId) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getMetricKeyNameArray",
					new Object[] {});
		if(metricKeyNames == null){
			List<String> filteredKeyNames = null;
			filteredKeyNames = serverMetricsServices.getMetricKeys(new Date(System.currentTimeMillis()));				                                  
			metricKeyNames = filteredKeyNames.toArray(new String[0]);
		}
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getMetricKeyNameArray", metricKeyNames);
		return metricKeyNames;
	}	

	public boolean setCacheTimeoutMinutes(int newCacheTimeout) {
		if(logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "setCacheTimeoutMinutes", newCacheTimeout);
	        
		boolean result = false;
	        
		// check cache timeout reset (by qe)
		// don't reset if timeout is < 0 or > 1 day
		if (newCacheTimeout >= 0 && newCacheTimeout <= 1440) {
	        if(logger.isLoggable(Level.FINEST)) {
	        	logger.logp(Level.FINEST, CLASS_NAME, "setCacheTimeoutMinutes", "getTimeoutInterval: {0}", getTimeoutInterval);
	        }
			getTimeoutInterval = newCacheTimeout * 60000;
			
	        if(logger.isLoggable(Level.FINEST)) {
	        	logger.logp(Level.FINEST, CLASS_NAME, "setCacheTimeoutMinutes", "New TimeoutInterval: {0}", getTimeoutInterval);
	        }
			result = true;
		} 
		else {
			if(logger.isLoggable(Level.FINEST))
				logger.logp(Level.FINEST, CLASS_NAME, "setCacheTimeoutMinutes", "New cache timeout is greater than one day. Leaving setting as: {0}", getTimeoutInterval);
			
			result = false;
		}

        if(logger.isLoggable(Level.FINER))
        	logger.exiting(CLASS_NAME, "setCacheTimeoutMinutes", result);
        
		return result;
	}
	
	private boolean hourTimeoutElapsed() {
        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "hourTimeoutElapsed");
        }
        boolean result = false;

		// if an hour has elapsed since the last get, re-get the metrics       
		if ((System.currentTimeMillis() - lastGetTime) > getTimeoutInterval)
			result = true;
		else
			result = false;
		
        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "hourTimeoutElapsed", result);
        }
		return result;
	}
	
	public Object getMetricValueForKey(String key) {
        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "getMetricValueForKey", key);
        }
        
       Map<String, Object> cacheList =  metricCache.get(ApplicationContext.getOrganizationId());
       Object retObj = null;
       if(cacheList != null){
    	   retObj = ((Object) (cacheList.get(key)));
       }
  
        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "getMetricValueForKey", retObj);
        }
		return (retObj);
	}	

}
