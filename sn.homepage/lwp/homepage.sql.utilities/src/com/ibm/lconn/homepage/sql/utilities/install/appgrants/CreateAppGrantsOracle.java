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


public class CreateAppGrantsOracle extends AbstractCreateAppGrants implements ICreateAppGrants {
	
	private static String CLASS_NAME = CreateAppGrantsOracle.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	

	@Override
	public String getDbType() {
		return IScriptGenerator.ORACLE;
	}

	@Override
	public List<String> grants(Connection conn, StringBuffer sb)
			throws Exception {
		List<String> tables= UtilitySQL.getAllNameTablesViewInOrder(conn);
		List<String> drops = new ArrayList<String>();
		
		for (String tabName : tables) {
			String tableName = tabName;
			String statment = "GRANT DELETE,INSERT,SELECT,UPDATE ON " + tableName + " TO HOMEPAGEUSER_ROLE;\nCOMMIT;\n";	
			sb.append(statment);
		    drops.add(statment);		    
		}
		
		sb.append("\n\n-- Adding specific Oracle Grants");
		sb.append("\nGRANT ALTER SESSION TO HOMEPAGEUSER_ROLE;");
		sb.append("\nGRANT CREATE SESSION TO HOMEPAGEUSER_ROLE;");
		sb.append("\nGRANT CREATE SYNONYM TO HOMEPAGEUSER_ROLE;");
		sb.append("\nGRANT SELECT_CATALOG_ROLE, HOMEPAGEUSER_ROLE TO HOMEPAGEUSER;");
		
	
		
		
		return drops;	
	}	
}
