/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.dao.util;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.db2.Db2DataTypeFactory;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.dbunit.ext.oracle.OracleDataTypeFactory;

public class DELETEJdbcDatabaseTester { //extends org.dbunit.JdbcDatabaseTester {
	
//
//
//	
//	private String dbvendor;
//	
//	public JdbcDatabaseTester(String driverClass, String connectionUrl,	String username, String password) throws ClassNotFoundException {
//		super(driverClass, connectionUrl, username, password);
//	
//		dbvendor = JdbcProperties.getDbVendor(driverClass, connectionUrl);
//
//	}
//	
//	@Override
//	public IDatabaseConnection getConnection() throws Exception {
//		IDatabaseConnection dbC = super.getConnection();
//				
//		if (dbvendor.equals(DB2))
//			dbC.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Db2DataTypeFactory());
//
//		if (dbvendor.equals(ORACLE))
//			dbC.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
//
//		if (dbvendor.equals(SQLSERVER))
//			dbC.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MsSqlDataTypeFactory());
//		
//		return dbC;
//	}
	

}
