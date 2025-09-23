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

package com.ibm.lconn.homepage.test.sandbox;

//TODO Use junit 4
//import org.junit.BeforeClass;

import com.ibm.lconn.core.platform.ICPlatform;
import com.ibm.lconn.homepage.test.TestHome;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;
import com.ibm.lconn.hpnews.data.model.IModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Base class to test the CRUD DAO
 *
 * @author Lorenzo Notarfonzo
 *
 */
public abstract class CrudDaoTest extends SpringContextAwareTestCase {

	private final static int N_RUN_CRUD_TEST = 1;

	public static final String PJ_HOME = TestHome.getPJ_HOME();
	public static final String LOGGING_CONFIG_FILE = PJ_HOME+"/src/logging.properties";
	protected static String ORG_ID_DEFAULT = ICPlatform.ORG_DEFAULT;
	protected static String ORG_ID_ONE = "11111111-1111-1111-1111-111111111111";


	@Test
	public void testGetDbVendor() {
		String dbVendorFromDriver = getCrudDAO().getDbVendor();
		String dbVendorSetting = TestHome.getDbVendor().toString();

		if (dbVendorSetting.equals("derby"))
			assertEquals(dbVendorFromDriver, ICrud.DERBY);
		else if (dbVendorSetting.equals("db2"))
			assertEquals(dbVendorFromDriver, ICrud.DB2);
		else if (dbVendorSetting.equals("oracle"))
			assertEquals(dbVendorFromDriver, ICrud.ORACLE);
		else if (dbVendorSetting.equals("sqlserver"))
			assertEquals(dbVendorFromDriver, ICrud.SQLSERVER);
	}

	protected abstract ICrud getCrudDAO();

	protected abstract IModel getBaseObjectToInsert();

	protected abstract IModel getBaseObjectToUpdate(IModel model);

	@Test
	public void testCRUD(){	 
		String result = "";
		long startTime;
		long endTime;
		
		ICrud crudDAO = getCrudDAO();
		    	
		// Test the selectAll
		for (int i=0; i<N_RUN_CRUD_TEST; i++) {
			long tot = 0;
			result += "\nSTART ITERATION "+i+"th <br/>";
			// System.out.println("\nSTART ITERATION "+i+"th");
			List<IModel> beforeRecords = null;
			try {
				beforeRecords = crudDAO.selectAll();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			startTime = System.currentTimeMillis();
			
			endTime = System.currentTimeMillis();
			
			result += "Select all performed into: "+(endTime-startTime)+"ms <br/>";
			// System.out.println("Select all performed into: "+(endTime-startTime)+"ms");
			tot += endTime-startTime;
			
			
			startTime = System.currentTimeMillis();
			String key = "";
			
			IModel modelToInsert = getBaseObjectToInsert();
			key = crudDAO.insert(modelToInsert);
			
			endTime = System.currentTimeMillis();
			result += "Inserted record into: "+(endTime-startTime)+"ms <br/> with this key: "+key;
			
			// System.out.println("Inserted record into: "+(endTime-startTime)+"ms");
			tot += endTime-startTime;
			
			// System.out.println("Inserted: "+getBaseObjectToInsert()+" with this key: "+key);
			
			//Test the findByPrimaryKey
			startTime = System.currentTimeMillis();
			IModel baseObjectInserted;
			
			baseObjectInserted = crudDAO.getByPK(key);
			
			endTime = System.currentTimeMillis();
			result += "Find by primary performed into: "+(endTime-startTime)+"ms <br/>";
			// System.out.println("Find by primary performed into: "+(endTime-startTime)+"ms");
			tot += endTime-startTime;
			
			// Test the update
			IModel baseObjectToUpdate = getBaseObjectToUpdate(baseObjectInserted);
			startTime = System.currentTimeMillis();
			crudDAO.update(baseObjectToUpdate);
			endTime = System.currentTimeMillis();
			result += "Updated performed into: "+(endTime-startTime)+"ms <br/>";
			// System.out.println("Updated performed into: "+(endTime-startTime)+"ms");
			tot += endTime-startTime;
			
			// Test the delete
			
			startTime = System.currentTimeMillis();
			crudDAO.remove(key);
			endTime = System.currentTimeMillis();
			result += "Delete performed into: "+(endTime-startTime)+"ms <br/>";
			// System.out.println("Delete performed into: "+(endTime-startTime)+"ms");
			tot += endTime-startTime;
			
			baseObjectInserted = crudDAO.getByPK(key);
			
			result += "END ITERATION "+i+"th into a total: "+tot+"ms <br/>";
			// System.out.println("END ITERATION "+i+"th into a total: "+tot+"ms");
		
			List<IModel> afterRecords = crudDAO.selectAll();
			
			assertEquals(beforeRecords.size(), afterRecords.size());
		}
	}

	/*
	public void testSafeInsert() {
		ICrud crudDAO = getCrudDAO();

		IModel modelToInsert = getBaseObjectToInsert();
		String key = crudDAO.safeInsert(modelToInsert);
		assertNotNull(key);
		System.out.println("Inserted successfully record with key: " + key);
		String pk = key;

		System.out.println("Re-inserting the same record");
		try {
			modelToInsert.initPrimaryKey(key);
			key = crudDAO.safeInsert(modelToInsert);
			assertNull(key);

		} catch (DataIntegrityViolationException e) {
			fail("Safe insert should catch DataIntegrityViolationException exception.");
		}
		// Clean up what we have inserted
		crudDAO.remove(pk);
	}
	*/

//	public void testInsertBatch() {
//		ICrud crudDAO = getCrudDAO();
//
//		int numModelsToInsert = 10;
//
//		List<IModel> modelsToInsert = new ArrayList<IModel>();
//		for(int i=0; i<numModelsToInsert; i++) {
//			IModel modelToInsert = getBaseObjectToInsert();
//			modelToInsert.initPrimaryKey(UUID.randomUUID().toString());
//			modelsToInsert.add(modelToInsert);
//		}
//
//		int modelsInserted = crudDAO.insertBatch(modelsToInsert);
//
//		assertEquals(modelsInserted, 10);
//
//		// cleanup
//		try {
//			crudDAO.removeAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
