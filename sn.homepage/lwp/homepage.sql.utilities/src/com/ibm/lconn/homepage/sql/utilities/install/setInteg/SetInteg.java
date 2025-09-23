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

package com.ibm.lconn.homepage.sql.utilities.install.setInteg;

import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilityIO;
import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;

public class SetInteg {

	private static String CLASS_NAME = SetInteg.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
		
	/** Internal Main if we don't want to generate the full suit */
	public static void main (String args[]) throws Exception{
		Properties propJDBC = UtilityIO.loadProperties();
		Connection conn = UtilitySQL.getConnection(propJDBC);
		
		SetInteg createPredbxfer = new SetInteg();
		createPredbxfer.generate(propJDBC, conn);	
	}
	
	/** Put here the obj to generate */
	public void generate(Properties props, Connection conn) throws Exception {
		UtilityIO.generate(props, conn, new SetIntegDB2());
	}
	
}

	