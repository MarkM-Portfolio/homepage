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

package com.ibm.lconn.homepage.sql.utilities.install.postdbxfer;

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

public class CreatePostdbxferDB2 extends AbstractCreatePostdbxfer implements ICreatePostdbxfer {
	
	private static String CLASS_NAME = CreatePostdbxferDB2.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	 
	private static int step = 0;
	

	@Override
	public String getDbType() {
		return IScriptGenerator.DB2;
	}

	public List<String> createFK(Connection conn, StringBuffer sb) throws Exception  {
		ArrayList<ForeignKey> foreignKeys = UtilitySQL.getFKColNames(conn);
		ArrayList<String> creates = new ArrayList<String>();	
	
		for (ForeignKey foreignKey : foreignKeys) {
			String statment = "ALTER TABLE "+UtilitySQL.getDatabaseName()+"."+foreignKey.getTabName()+" ADD CONSTRAINT "+foreignKey.getConstName()+" FOREIGN KEY ("+foreignKey.getFKColName().trim()+") REFERENCES "+UtilitySQL.getDatabaseName()+"."+foreignKey.getRefTabName()+" (" +foreignKey.getPrimKeyName().trim()+")"; 
					if (foreignKey.getDeleteRule().equals("C")) {
						statment =	statment +	" ON DELETE CASCADE";
					}
						statment =	statment +	"" +
					"@\nCOMMIT@\n";
			sb.append(statment);
		    creates.add(statment);	    
		}

		return creates; 

	}

	
}
