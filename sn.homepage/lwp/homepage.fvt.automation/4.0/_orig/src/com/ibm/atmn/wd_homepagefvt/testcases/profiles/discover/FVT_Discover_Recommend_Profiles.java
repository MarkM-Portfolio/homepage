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



import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageData;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesData;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;



public class FVT_Discover_Recommend_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	


	public void testRecommendAdded_Profiles () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testRecommendAdded_Profiles ");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_ProfilesData.ProfilesBoardEntry);
		
		//Logout of profiles
		Logout();	
		
		
		
		//Login as a user (ie. Amy Jones451)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Recommend User Status
		RecommendStatus_Profiles(CommonData.IC_LDAP_Username_Fullname450);
		
		//Logout of profiles
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testRecommendAdded_Profiles ");
	}
	
	
	public void testRecommendAdded_Homepage () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testRecommendAdded_Homepage ");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink("link=Discover");
		
		driver.getSingleElement(FVT_HomepageObjects.StatusUpdate).type(FVT_HomepageData.HomepageBoardEntry);
		
		clickLink(FVT_HomepageObjects.PostStatus);  //Button currently disabled
		
		//Logout of profiles
		Logout();
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Update Status
		RecommendStatus_Homepage(CommonData.IC_LDAP_Username450);
		
		//Logout of profiles
		Logout();
		
		
	}
}
