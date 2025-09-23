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

package com.ibm.lconn.homepage.sql.utilities.install.predbxfer;

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

public class CreatePredbxferOracle extends AbstractCreatePredbxfer implements ICreatePredbxfer {
	
	private static String CLASS_NAME = CreatePredbxferDB2.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	 
	private static int step = 0;
	

	@Override
	public String getDbType() {
		return IScriptGenerator.ORACLE;
	}
	

	
	public List<String> dropFK(Connection conn, StringBuffer sb) throws Exception  {
		ArrayList<ForeignKey> fks = UtilitySQL.getFKColNames(conn);
		List<String> drops = new ArrayList<String>();
		
		for (ForeignKey fk : fks) {
			String statment = "ALTER TABLE "+UtilitySQL.getDatabaseName()+"."+fk.getTabName()+" DROP CONSTRAINT "+fk.getConstName()+";\nCOMMIT;\n";
			sb.append(statment);
		    drops.add(statment);	    
		}

		return drops;		
	}

	@Override
	public List<String> removeRecords(Connection conn, StringBuffer sb)
			throws Exception {
	
		ArrayList<Table> tables = UtilitySQL.getTables(conn, false);
		List<String> updates = new ArrayList<String>();
		
		for (Table t: tables) {
			String table = t.getTableName();
			String statment = "DELETE FROM "+UtilitySQL.getDatabaseName()+"."+table+";\nCOMMIT;\n";
			
			sb.append(statment);
		}
		
		//System.out.println(updates);
		return updates;
	}

}
