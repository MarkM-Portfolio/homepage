package com.ibm.lconn.homepage.test.dao.util;

/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import javax.wsdl.Input;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.db2.Db2DataTypeFactory;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.dbunit.ext.oracle.OracleDataTypeFactory;

import com.ibm.lconn.homepage.test.TestHome;


/**
 * To populate the derby db using a datasat. The connection info are stored inside database.properties 
 * test/spring/news/database/database.properties
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public class DELETEDBUnitUtility {
	
	
//
//	
//	private JdbcProperties jdbcPorperties;
//	private IDatabaseTester databaseTester;
//	
//	static String CLASS_NAME = DBUnitUtility.class.getName();
//	static java.util.logging.Logger logger = java.util.logging.Logger
//			.getLogger(CLASS_NAME);
//	
//	private static final String JDBC_PROPERTIES = "test/spring/homepage/database/database.properties";
//	private static final String HOMEPAGE_DATASET = TestHome.getPJ_HOME()+"/derby_scripts/homepage_dataset.xml";
//
//	public DBUnitUtility(){
//		this("derby");
//	}
//	
//	//dbvendor: derby, db2, sqlserver, oracle
//	public DBUnitUtility(String dbvendor) {
//		Properties databaseProperties = getJdbcProperties();
//		jdbcPorperties = new JdbcProperties();
//		jdbcPorperties.setDriver(databaseProperties.getProperty(dbvendor+".driver"));
//		jdbcPorperties.setUrl(databaseProperties.getProperty(dbvendor+".url"));
//		jdbcPorperties.setUsername(databaseProperties.getProperty(dbvendor+".username"));
//		jdbcPorperties.setPassword(databaseProperties.getProperty(dbvendor+".password"));
//		jdbcPorperties.setSchema(databaseProperties.getProperty(dbvendor+".schema"));		
//	}
//	
//	private  Properties getJdbcProperties() {
//		Properties properties;
//		properties = new Properties();		
//		try {
//			InputStream is = this.getClass().getClassLoader().getResourceAsStream(JDBC_PROPERTIES);
//			properties.load(is);
//		} catch (Exception e) {
//			System.out.println("ERROR: Unable to load the file: "+JDBC_PROPERTIES);
//			e.printStackTrace();
//		}
//		return properties;
//	}
//	
//	
//	public void tearDownDBUnit() throws Exception {
//		try {
//			databaseTester.onTearDown();
//			databaseTester.getConnection().close();
//		}
//		catch (Exception e) {
//			System.out.println("Error: DBUNIT: Database NOT initialized with: "+HOMEPAGE_DATASET);
//			System.out.println("Error: "+getJDBCInfo());		
//			throw e;
//		}
//	}
//	
//	public void setupDBUnit() throws Exception {
//		try {
//			IDataSet dataSet = getDataSet();
//			initDatabaseTester();
//			System.out.println("");
//			databaseTester.setDataSet(dataSet);
//			databaseTester.onSetup();
//			System.out.println("Database initialized with: "+HOMEPAGE_DATASET);
//		}
//		catch (Exception e) {
//			System.out.println("Error: DBUNIT: Database NOT initialized with: "+HOMEPAGE_DATASET);
//			System.out.println("Error: "+getJDBCInfo());		
//			throw e;
//		}
//		
//	}
//	
//	private static void  initPropertyDatatypeFactory(String dbvendor) {
//		DatabaseConfig config = new DatabaseConfig(); 		
//		
//		if (dbvendor.equals(DERBY))
//			//config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
//
//		if (dbvendor.equals(DB2))
//			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Db2DataTypeFactory());
//
//		if (dbvendor.equals(ORACLE))
//			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
//
//		if (dbvendor.equals(SQLSERVER))
//			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MsSqlDataTypeFactory());
//
//	}
//	
//	private void initDatabaseTester() throws Exception {		
//		String driver = jdbcPorperties.getDriver();
//		String url = jdbcPorperties.getUrl();
//		String username = jdbcPorperties.getUsername();
//		String password = jdbcPorperties.getPassword();
//		String schema = jdbcPorperties.getSchema();
//		
//		databaseTester = new JdbcDatabaseTester(driver,url,username,password);
//		if( schema != null && schema.trim().length()>0)
//			databaseTester.setSchema(schema);
//		
//		if (logger.isLoggable(Level.INFO)) {
//			logger.logp(Level.INFO,CLASS_NAME,"getConnection","**** The test is running against: "+url+" using this driver: "+driver);
//			logger.logp(Level.INFO,CLASS_NAME,"getConnection","**** The test is running against: "+url+" for username: "+username+" password: "+password+" and using this schema: "+schema);
//		}
//
//	}
//	
//
//
//	
//	
//	
//	private  static IDataSet getDataSet() throws IOException,  DataSetException {
//		return new FlatXmlDataSet(new File(HOMEPAGE_DATASET));
//	}
//	
//	public String getJDBCInfo() {
//		String driver = jdbcPorperties.getDriver();
//		String url = jdbcPorperties.getUrl();
//		String username = jdbcPorperties.getUsername();
//		String password = jdbcPorperties.getPassword();
//		String schema = jdbcPorperties.getSchema();
//		
//		return "The user: "+username+" with this password: "+password+" and schema: "+schema+" use this jdbc connection url: "+url;
//	}

}
