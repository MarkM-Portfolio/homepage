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

package com.ibm.lconn.homepage.dao.model;

public class PageInfo {
	
	private int pageNo;
	private int pageSize;
	private int totalRecords;
	
	public PageInfo(int totalRecords, int pageSize) {
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;
	}
	public int getPageCount() {
		if(totalRecords != 0) {
			return (totalRecords / pageSize) + (totalRecords % pageSize > 0 ? 1 : 0);
		} else
			return 0;
	}
	public int getPageSize() {
		return pageSize;
	}	
	public int getTotalRecords() {
		return totalRecords;
	}
	
	public void setPageNo(int no) {
		if(no <= getPageCount())
			this.pageNo = no;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public int getPageRecordStart() {
		if(pageNo!=0)
			return (pageNo==1 ? pageNo : ((pageNo - 1) * pageSize) + 1);
		else
			return 0;
	}
	public int getPageRecordEnd() {
		if(pageNo!=0) {
			if(pageNo==getPageCount())
				return getTotalRecords();
			else 
				return pageNo * pageSize;
		}
		else
			return 0;
	}
		
	public String toString() {	
		return "pageSize [" + getPageSize() + "], totalRecords [" + getTotalRecords() + "], getPageCount [" + getPageCount() + "]";
	}
	
}
