/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services.utils;

import com.ibm.lconn.homepage.services.utils.WASAdminService;

/**
 * 
 * @author Guo Du
 *
 */
public class MockWasAdminServiceImpl implements WASAdminService {

	private String defaultDomain;
	private String cellName;
	private String processName;
	private String nodeName;
	
	public String getDefaultDomain() {
		return defaultDomain;
	}
	public void setDefaultDomain(String defaultDomain) {
		this.defaultDomain = defaultDomain;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

}
