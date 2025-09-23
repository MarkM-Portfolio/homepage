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

package com.ibm.lconn.homepage.dao.model;

import java.util.Date;

import com.ibm.lconn.hpnews.data.model.IModel;

/**
 * 
 * @author lorenzo
 *
 */
public class MetricStat extends HPBaseModel implements IModel {


	
/*	CREATE TABLE HOMEPAGE.MT_METRIC_STAT  (
		      METRIC_STAT_ID VARCHAR(36) NOT NULL,
		      RECORDED_ON TIMESTAMP NOT NULL,
		      METRIC_TYPE SMALLINT NOT NULL,
		      METRIC_DESC VARCHAR(36) NOT NULL,
		      RES_BUNDLE_KEY VARCHAR(144) NOT NULL,
		      COUNT_LAST_24_H BIGINT,
		      COUNT_LAST_7_D BIGINT,
		      COUNT_LAST_1_M BIGINT,
		      TOP_STATS VARCHAR(512),
		      TOT_STAT BIGINT,
		      AVG_TOT_STAT BIGINT
		)
		IN HOMEPAGETABSPACE;*/
	
	
	private String metricStatId;
	private Date recordedOn;
	private int metricType;
	private String metricDesc;
	private String resBundleKey;
	private Long countLast24h;
	private Long countLast7d;
	private Long countLast1m;
	private String topStats;
	private Long totStat;
	private Long avgTotStat;
	
	
	
	@Override
	public String getPrimaryKey() {
		return metricStatId;
	}

	@Override
	public void initPrimaryKey(String primaryKey) {
		this.metricStatId = primaryKey;		
	}

	public String getMetricStatId() {
		return metricStatId;
	}

	public void setMetricStatId(String metricStatId) {
		this.metricStatId = metricStatId;
	}

	public Date getRecordedOn() {
		return recordedOn;
	}

	public void setRecordedOn(Date recordedOn) {
		this.recordedOn = recordedOn;
	}

	public int getMetricType() {
		return metricType;
	}

	public void setMetricType(int metricType) {
		this.metricType = metricType;
	}

	public String getMetricDesc() {
		return metricDesc;
	}

	public void setMetricDesc(String metricDesc) {
		this.metricDesc = metricDesc;
	}

	public String getResBundleKey() {
		return resBundleKey;
	}

	public void setResBundleKey(String resBundleKey) {
		this.resBundleKey = resBundleKey;
	}

	public Long getCountLast24h() {
		return countLast24h;
	}

	public void setCountLast24h(Long countLast24h) {
		this.countLast24h = countLast24h;
	}

	public Long getCountLast7d() {
		return countLast7d;
	}

	public void setCountLast7d(Long countLast7d) {
		this.countLast7d = countLast7d;
	}

	public Long getCountLast1m() {
		return countLast1m;
	}

	public void setCountLast1m(Long countLast1m) {
		this.countLast1m = countLast1m;
	}

	public String getTopStats() {
		return topStats.replace(',', ':');
	}

	public void setTopStats(String topStats) {
		this.topStats = topStats;
	}

	public Long getTotStat() {
		return totStat;
	}

	public void setTotStat(Long totStat) {
		this.totStat = totStat;
	}

	public Long getAvgTotStat() {
		return avgTotStat;
	}

	public void setAvgTotStat(Long avgTotStat) {
		this.avgTotStat = avgTotStat;
	}

	@Override
	public IModel cloneModel() {
		// Intentionally left blank
		return null;
	}

	@Override
	public String getTABLENAME() {
		// Intentionally left blank
		return null;
	}

	@Override
	public String getDbVendor() {
		// Intentionally left blank
		return null;
	}

	@Override
	public void setDbVendor(String dbVendor) {
		// Intentionally left blank
		
	}

	
	
}
