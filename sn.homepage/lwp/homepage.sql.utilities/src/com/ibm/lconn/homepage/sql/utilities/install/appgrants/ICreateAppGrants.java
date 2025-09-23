/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.sql.utilities.install.appgrants;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;

public interface ICreateAppGrants extends IScriptGenerator {
		
	public List<String> grants(Connection conn, StringBuffer sb) throws Exception;
	
	public void setProperties(Properties properties);

}
