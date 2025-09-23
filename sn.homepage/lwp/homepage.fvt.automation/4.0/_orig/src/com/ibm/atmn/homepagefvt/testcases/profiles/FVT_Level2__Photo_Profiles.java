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



import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Level2__Photo_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	
	
	
	public void testProfilesPhotoUpdated_Create () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_Create");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add tag
		UploadPhoto_JDO();		
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_Create");
	}
	
	/*
	public void testProfilesPhotoUpdated_UpdateExisting () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_UpdateExisting");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add tag
		//UploadPhoto_JO();		
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_UpdateExisting");
	}
	
	
	public void testProfilesPhotoUpdated_RemoveExisting () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_RemoveExisting");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add tag
		//UploadPhoto_JO();		
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_RemoveExisting");
	}
	
	*/
}
