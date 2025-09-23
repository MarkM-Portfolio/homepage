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

package com.ibm.lconn.homepage.model;

import java.util.List;



public class UserResults extends AbstractModelObject {
	
	private static final long serialVersionUID = 6786507347599623324L;

	private List<User> userList;

	// total number of match. The user list may contain only a subset of the
	// results
	private long totalNumber;

	public List<User> getUsers() {
		return userList;
	}

	public void setUsers(List<User> userList) {
		this.userList = userList;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}
}
