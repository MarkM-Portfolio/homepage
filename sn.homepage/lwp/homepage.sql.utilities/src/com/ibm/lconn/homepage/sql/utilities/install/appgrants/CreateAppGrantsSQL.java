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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;



public class CreateAppGrantsSQL extends AbstractCreateAppGrants implements ICreateAppGrants {
	
	private static String CLASS_NAME = CreateAppGrantsSQL.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	

	@Override
	public String getDbType() {
		return IScriptGenerator.SQLSERVER;
	}

	@Override
	public List<String> grants(Connection conn, StringBuffer sb)
			throws Exception {
		List<String> tables= UtilitySQL.getAllNameTablesViewInOrder(conn);
		List<String> drops = new ArrayList<String>();
		
		for (String tabName : tables) {
			String tableName = tabName;
			String statment = "GRANT DELETE,INSERT,SELECT,UPDATE ON " + tableName + " TO "+UtilitySQL.getDatabaseName()+"USER\nGO\n";	
			sb.append(statment);
		    drops.add(statment);		    
		}
		return drops;	
	}
}
