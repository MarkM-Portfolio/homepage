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

package com.ibm.lconn.homepage.test.services.mock;


import java.util.Date;
import java.util.List;

import com.ibm.lconn.hpnews.data.dao.impl.ibatis.CrudDao;
import com.ibm.lconn.homepage.dao.interfaces.IMetricStatDao;
import com.ibm.lconn.hpnews.data.model.IModel;
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
		return super.selectAll();

	}

}
