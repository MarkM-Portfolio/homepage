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
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Photo_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	
	
	@Test
	public void testProfilesPhotoUpdated_Create () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_Create");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add tag
		uploadPhoto("Desert.jpg");		
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		//verifyFirstNewsStory("The following people updated their profile information: "+CommonData.IC_LDAP_Username_Fullname450,"Discover","Profiles", true);
		verifyNewsStory("The following people updated their profile information:","Discover","Profiles", true);

		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_Create");
	}
	
	//@Test (dependsOnMethods = { "testProfilesPhotoUpdated_Create" })
	public void testProfilesPhotoUpdated_UpdateExisting () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_UpdateExisting");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update photo
		uploadPhoto("Koala.jpg");
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyNewsStory("The following people updated their profile information:","Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_UpdateExisting");
	}
	
	//@Test (dependsOnMethods = { "testProfilesPhotoUpdated_UpdateExisting" })
	public void testProfilesPhotoUpdated_RemoveExisting () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_RemoveExisting");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Remove Photo
		removePhoto();		
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyFirstNewsStory("The following people updated their profile information:","Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_RemoveExisting");
	}
	
	
}
