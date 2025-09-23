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

package com.ibm.lconn.homepage.model;

import java.util.Date;
import java.util.Map;


public class ServerMetricsData extends AbstractModelObject {
	
	private static final long serialVersionUID = -8823526569997031910L;
	

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		//must be "scalar" or "list"
		this.type = type;
	}
	public long getScalarData() {
		return scalarData;
	}
	public void setScalarData(long scalarData) {
		this.scalarData = scalarData;
	}
	public Map<String, Long> getTop5list() {
		return top5list;
	}
	public void setTop5list(Map<String, Long> top5list) {
		this.top5list = top5list;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public Date getRecordedOn() {
		return recordedOn;
	}
	public void setRecordedOn(Date recordedOn) {
		this.recordedOn = recordedOn;
	}



	public ServerMetricsData() {

	}
	
	private String key;
	private String type;// can be "scalar" or "list", 
	//if scalar, then use the scalar data if list use the top5list
	private long scalarData;
	private Map<String, Long> top5list;
	private long total; //this is to store the total value so we
						//can calculate the percentage of the statistic compared to the total i.e. percentage
	private Date recordedOn;



	public ServerMetricsData(String key, String type, long scalarData, long total, Date d) {
		super();
		this.key = key;
		this.type = type;
		this.scalarData = scalarData;
		this.total = total;
		this.recordedOn = d;
	}
	//this constructor is called when creating statistics which already store the total
	
	public ServerMetricsData(String key, String type, long scalarData, Date d) {
		super();
		this.key = key;
		this.type = type;
		this.scalarData = scalarData;
		this.total = scalarData;
		this.recordedOn = d;
	}
}