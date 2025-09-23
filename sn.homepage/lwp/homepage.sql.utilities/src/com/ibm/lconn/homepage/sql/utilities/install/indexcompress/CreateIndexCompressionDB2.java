/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2015                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.sql.utilities.install.indexcompress;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;

public class CreateIndexCompressionDB2 extends AbstractCreateIndexCompression implements ICreateIndexCompression   {

private static String CLASS_NAME = CreateIndexCompressionDB2.class.getName();
private static Logger logger = Logger.getLogger(CLASS_NAME);

public static String FILE_NAME = "indexCompression.sql";

	@Override
	public String getDbType() {
		return IScriptGenerator.DB2;
	}
	
	
	public List<String> compressionStmt(Connection conn, StringBuffer sb) throws Exception  {
		List<String> compressions = new ArrayList<String>();
		
		
		String schema = UtilitySQL.getSchema();
		
		Map<String,String> indexes = UtilitySQL.getIndexesToBeCompressed(conn);		
		Iterator<Entry<String, String>> it = indexes.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String,String> pairs = it.next();
	    	String statment = "ALTER INDEX "+schema+"."+pairs.getKey()+" COMPRESS YES@\nCOMMIT@\n";
			sb.append(statment);
			compressions.add(statment);
		}
	    
	    sb.append("\n\n\n-- -----------------------\n\n");
	    
		List<String> tables = UtilitySQL.getTablesToBeCompressed(conn);
		for (String t: tables) {
	    	String statment = "ALTER TABLE "+schema+"."+t+" COMPRESS YES@\nCOMMIT@\n";
			sb.append(statment);
			compressions.add(statment);
		}
		
//		reorg table HOMEPAGE.BOARD_ENTRIES LONGLOBDATA RESETDICTIONARY;
//		reorg indexes all for table HOMEPAGE.BOARD_ENTRIES;
//		runstats on table HOMEPAGE.BOARD_ENTRIES with distribution and detailed indexes all allow read access;
		
		sb.append("\n\n\n-- -----------------------\n\n");
		for (String t: tables) {
	    	String statment = "REORG TABLE "+schema+"."+t+" LONGLOBDATA RESETDICTIONARY@\nCOMMIT@\n";
			sb.append(statment);
			compressions.add(statment);
		}
		
		sb.append("\n\n\n-- -----------------------\n\n");
		for (String t: tables) {
	    	String statment = "REORG INDEXES ALL FOR TABLE  "+schema+"."+t+"@\nCOMMIT@\n";
			sb.append(statment);
			compressions.add(statment);
		}
		
		sb.append("\n\n\n-- -----------------------\n\n");
		for (String t: tables) {
	    	String statment = "RUNSTATS ON TABLE  "+schema+"."+t+" WITH DISTRIBUTION AND DETAILED INDEXES ALL ALLOW READ ACCESS@\nCOMMIT@\n";
			sb.append(statment);
			compressions.add(statment);
		}
	
		return compressions;		
	}
}
