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

package com.ibm.lconn.homepage.sql.utilities.install;

import java.sql.Connection;
import java.util.Properties;

import com.ibm.lconn.homepage.sql.utilities.UtilityIO;
import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.install.appgrants.CreateAppGrants;
import com.ibm.lconn.homepage.sql.utilities.install.postdbxfer.CreatePostdbxfer;
import com.ibm.lconn.homepage.sql.utilities.install.predbxfer.CreatePredbxfer;
import com.ibm.lconn.homepage.sql.utilities.install.reorg.Reorg;
import com.ibm.lconn.homepage.sql.utilities.install.setInteg.SetInteg;
import com.ibm.lconn.homepage.sql.utilities.install.stats.UpdateStats;

public class MainScriptsGenerator {

	
	/** Internal Main if we don't want to generate the full suit */
	public static void main (String args[]) throws Exception{
		Properties props = UtilityIO.loadProperties();
		Connection conn = UtilitySQL.getConnection(props);
		
		
		
		CreateAppGrants createAppGrants = new CreateAppGrants();
		CreatePostdbxfer createPostdbxfer = new CreatePostdbxfer();
		CreatePredbxfer createPredbxfer = new CreatePredbxfer();
		Reorg reorg = new Reorg();
		SetInteg setInteg = new SetInteg();
		UpdateStats updateStats = new UpdateStats();
		
		
		
		
		createAppGrants.generate(props, conn);
		createPostdbxfer.generate(props, conn);
		createPredbxfer.generate(props, conn);
		reorg.generate(props, conn);
		setInteg.generate(props, conn);
		updateStats.generate(props, conn);
	}
	
}
