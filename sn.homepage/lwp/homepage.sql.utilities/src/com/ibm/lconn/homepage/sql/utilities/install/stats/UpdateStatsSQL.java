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
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.beans.ForeignKey;
import com.ibm.lconn.homepage.sql.utilities.beans.Index;
import com.ibm.lconn.homepage.sql.utilities.beans.Table;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;

public class UpdateStatsSQL extends AbstractCreateUpdateStats implements IUpdateStats {
	
	private static String CLASS_NAME = UpdateStatsDB2.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	 
	private static int step = 0;
	

	@Override
	public String getDbType() {
		return IScriptGenerator.SQLSERVER;
	}

	public List<String> updateStats(Connection conn, StringBuffer sb) throws Exception  {
		List<String> tables = UtilitySQL.getAllNameTablesInOrder(conn);
		List<String> stats = new ArrayList<String>();

		
		for (String table: tables) {
			String statment = "UPDATE STATISTICS "+table+";\nGO\n";
			sb.append(statment);
			stats.add(statment);
	    
		}

		return stats;		
	}


	


}
