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

package com.ibm.atmn.wd_homepagefvt.testcases.profiles.discover;




import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Status_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	@Test
	public void testStatusUpdate_ExtendedCharacters () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_ExtendedCharacters");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		statusUpdate(FVT_NewsData.UpdateStatus_Special);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
			
		verifyNewsStory(FVT_NewsData.UpdateStatus_Special,"Discover","Profiles", true);

		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_ExtendedCharacters");
	}
	
	@Test
	public void testStatusUpdate_FromHomepage () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_FromHomepage");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		//Update Status
		statusUpdate(FVT_NewsData.UpdateStatus);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyNewsStory(FVT_NewsData.UpdateStatus,"Discover","Profiles", true);

		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_FromHomepage");
	}
	
	@Test
	public void testStatusUpdate_LinkInStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_LinkInStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		statusUpdate(FVT_NewsData.UpdateStatus_Link);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyNewsStory(FVT_NewsData.UpdateStatus_Link,"Discover","Profiles", true);

		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_LinkInStatus");
	}
	
	@Test
	public void testStatusUpdate_LongStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_LongStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_NewsData.UpdateStatus_1000);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyNewsStory(FVT_NewsData.UpdateStatus_1000,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_LongStatus");
	}
	
}
