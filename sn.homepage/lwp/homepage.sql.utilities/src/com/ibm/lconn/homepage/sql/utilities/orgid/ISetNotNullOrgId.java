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
import java.util.ArrayList;
import java.util.List;

import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;

public interface ISetNotNullOrgId extends IScriptGenerator {
		
	public List<String> dropOrgFK(Connection conn, StringBuffer sb) throws Exception;
	
	public List<String> createOrgFK(Connection conn, StringBuffer sb) throws Exception;
	
	public List<String> dropExistingOrgIndexes(Connection conn, StringBuffer sb) throws Exception;
	
	public ArrayList<String> restoringExistingOrgIndexes(Connection conn, StringBuffer sb) throws Exception;
	
	public List<String> setNotNullOrgId(Connection conn, StringBuffer sb) throws Exception;
	
	
}
