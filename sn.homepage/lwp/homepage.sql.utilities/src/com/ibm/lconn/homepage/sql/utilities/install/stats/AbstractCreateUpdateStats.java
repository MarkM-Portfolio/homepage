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

package com.ibm.lconn.homepage.sql.utilities.install.stats;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.beans.Column;
import com.ibm.lconn.homepage.sql.utilities.beans.Table;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;

public abstract class AbstractCreateUpdateStats  implements IUpdateStats {

	private static String CLASS_NAME = AbstractCreateUpdateStats.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static String FILE_NAME = "updateStats.sql";
	
	public String getFilename() {
		return FILE_NAME;
	}
	
	public StringBuffer generateScript(Connection conn) throws Exception {
		StringBuffer sb = new StringBuffer();
				
		UtilitySQL.startScript(sb,getDbType());
		
		sb.append("\n");
		updateStats(conn, sb);
		sb.append("\n");
		
		if (!(this.getDbType().equals(IScriptGenerator.ORACLE)))		
			UtilitySQL.endScript(sb,getDbType());
		
		return sb;
	}
	
	public static boolean checkIfTableContainsOrganizationIdColumn(Table t) {
		ArrayList<Column> cols = t.getColumns();
		for (Column c: cols) {
			if (c.getColumnName().equals("ORGANIZATION_ID"))
				return true;
		}
		return false;
	}
	
	public abstract List<String> updateStats(Connection conn, StringBuffer sb) throws Exception;
	

	
}
