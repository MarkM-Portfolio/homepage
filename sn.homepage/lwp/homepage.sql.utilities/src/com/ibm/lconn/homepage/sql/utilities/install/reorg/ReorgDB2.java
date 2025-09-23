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

package com.ibm.lconn.homepage.sql.utilities.install.reorg;

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

public class ReorgDB2 extends AbstractReorg implements IReorg {
	
	private static String CLASS_NAME = ReorgDB2.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	 
	private static int step = 0;
	

	@Override
	public String getDbType() {
		return IScriptGenerator.DB2;
	}


	public List<String> reorg(Connection conn, StringBuffer sb) throws Exception  {
		List<String> tables = UtilitySQL.getAllNameTablesInOrder(conn);
		List<String> stats = new ArrayList<String>();

		
		sb.append("\n-- REORG INDEXES:\n");
		for (String table : tables) {
			if (!"HOMEPAGE.HOMEPAGE_SCHEMA".equals(table)) {
				String statment = "reorg indexes all for table " + table
						+ "@\nCOMMIT@\n";
				sb.append(statment);
				stats.add(statment);
			}
		}
		sb.append("\n-- REORG TABLES:\n");
		for (String table: tables) {
			String statment = "reorg table "+table+"@\nCOMMIT@\n";
			sb.append(statment);
			stats.add(statment);
	    
		}

		return stats;		
	}
	

	

}
