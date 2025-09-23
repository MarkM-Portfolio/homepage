/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.news;



import static org.testng.AssertJUnit.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.news.FVT_CommonNewsMethods;
import com.ibm.atmn.wdbase.core.data.User;



public class FVT_StatusUpdates_File extends FVT_CommonNewsMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StatusUpdate = "";
	
	@Test
	public void testStatusUpdate_File() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRollUp_FileComment");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		/* USER 1 Post Status Update*/
		
		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//StatusUpdate = FVT_NewsData.UpdateStatus_File+CommonMethods.genDateBasedRandVal();
		
		//Open Status Updates
		openStatusUpdates();
	
		StatusUpdate = statusUpdate_FileAttached(FVT_NewsData.UpdateStatus_File, "Desert.jpg");
		
		//Logout of Wiki
		Logout();
		
		
		System.out.println("INFO: End of News Level 2 testRollUp_FileComment");
	}
	
}
