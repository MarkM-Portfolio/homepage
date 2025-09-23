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

package com.ibm.lconn.homepage.sql.utilities.orgid;

import java.sql.Connection;
import java.util.Properties;

import com.ibm.lconn.homepage.sql.utilities.UtilityIO;
import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;

public class SetNotNullOrgId {

	/** Internal Main if we don't want to generate the full suit */
	public static void main(String[] args) throws Exception {
		Properties propJDBC = UtilityIO.loadProperties();
		Connection conn = UtilitySQL.getConnection(propJDBC);
		
		SetNotNullOrgId setNotNullOrgId = new SetNotNullOrgId();
		setNotNullOrgId.generate(propJDBC, conn);	
		
	}
	
	/** Put here the obj to generate */
	public void generate(Properties props, Connection conn) throws Exception {
		UtilityIO.generate(props, conn, new SetNotNullOrgIdDB2());
		//UtilityIO.generate(props, conn, new SetNotNullOrgIdOracle());
		//UtilityIO.generate(props, conn, new SetNotNullOrgIdSQLServer());
	}


}
