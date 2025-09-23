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

package com.ibm.atmn.wd_homepagefvt.testcases.profiles;





import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class TestOfFVT_Level2_Profiles extends FVT_ProfilesMethods{
	
	
	
	@Test
	public void testProfilesPersonSelftagged_Multiple () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonSelftagged_Multiple");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
		//Add multiple tags
		ProfilesAddMultipleTags();			
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonSelftagged_Multiple");
	}
	
	

}
