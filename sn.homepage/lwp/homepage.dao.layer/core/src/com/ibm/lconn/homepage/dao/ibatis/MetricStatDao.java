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

package com.ibm.lconn.homepage.dao.ibatis;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.lconn.homepage.dao.interfaces.IMetricStatDao;
import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.hpnews.data.dao.impl.ibatis.CrudDao;
/**
 * 
 * @author lorenzo
 *
 */
public class MetricStatDao extends CrudDao implements IMetricStatDao {

	@Override
	public String getSchema() {
		return "HOMEPAGE";
	}

	@Override
	public String getTableName() {
		return "METRIC_STAT";
	}
	
	public List<IModel> selectByDate(Date now){
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		if(c.get(Calendar.HOUR_OF_DAY) < 2){
			//metrics are set at 2 so,if its between 12-2 am take the previous midnights stats
			c.add(Calendar.DAY_OF_YEAR, -2);
		}
		else{
			//else take todays stats from midnight
			c.add(Calendar.DAY_OF_YEAR, -1);
		}
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date midnightThisMorning = c.getTime();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schema",getSchema());
		params.put("begin", midnightThisMorning);
		params.put("end", now);
		List<IModel> todaysMetrics = sqlMapClientOperations.queryForList("selectByDate-"+getTableName(), params);
		return todaysMetrics;
	}

}
