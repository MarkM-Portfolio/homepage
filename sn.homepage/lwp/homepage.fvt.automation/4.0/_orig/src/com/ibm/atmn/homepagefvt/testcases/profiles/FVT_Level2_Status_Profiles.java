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

package com.ibm.atmn.homepagefvt.testcases.profiles;


import org.junit.*;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageData;
import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.homepagefvt.appobjects.profiles.FVT_ProfilesData;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Level2_Status_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	
	public void testStatusUpdate_ExtendedCharacters () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_ExtendedCharacters");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_ProfilesData.ProfilesBoardEntry_ExtendedChars);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_ExtendedCharacters");
	}
	
	@Ignore 
	public void testStatusUpdate_FromHomepage () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_FromHomepage");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		clickLink("link=I'm following");
		
		sel.type(FVT_HomepageObjects.StatusUpdate, FVT_HomepageData.HomepageBoardEntry);
		
		clickLink(FVT_HomepageObjects.PostStatus);  //Button currently disabled
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_FromHomepage");
	}
	
	
	public void testStatusUpdate_LinkInStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_LinkInStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_ProfilesData.ProfilesBoardEntry_Link);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_LinkInStatus");
	}
	
	
	public void testStatusUpdate_LongStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_LongStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_ProfilesData.ProfilesBoardEntry_Long);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_LongStatus");
	}
	
}
