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
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;

public abstract class AbstractCreateIndexCompression implements ICreateIndexCompression {

private static String CLASS_NAME = AbstractCreateIndexCompression.class.getName();
private static Logger logger = Logger.getLogger(CLASS_NAME);

public static String FILE_NAME = "indexCompression.sql";

	public String getFilename() {
		return FILE_NAME;
	}

	public StringBuffer generateScript(Connection conn) throws Exception {
		StringBuffer sb = new StringBuffer();
				
		UtilitySQL.startScript(sb,getDbType());
		
		sb.append("\n");
		compressionStmt(conn, sb);
		sb.append("\n");
		
		if (!(this.getDbType().equals(IScriptGenerator.ORACLE)))		
			UtilitySQL.endScript(sb,getDbType());
		
		return sb;
	}
	
	public  abstract List<String> compressionStmt(Connection conn, StringBuffer sb) throws Exception;
}
