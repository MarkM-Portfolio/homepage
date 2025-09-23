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

import java.util.ArrayList;
import java.util.List;

import com.ibm.lconn.hpnews.data.model.LoginName;

public class UserWithLoginNames extends User {

	private static final long serialVersionUID = 2305491393212761220L;
	
	private List<LoginName> loginNames;

	private synchronized void init() {
		if(loginNames==null)
			loginNames = new ArrayList<LoginName>();
	}
	public List<LoginName> getLoginNames() {
		return loginNames;
	}

	public void setLoginNames(List<LoginName> loginNames) {
		this.loginNames = loginNames;
	}
	
	public void addLoginName(LoginName name) {
		init();
		loginNames.add(name);
	}
}
