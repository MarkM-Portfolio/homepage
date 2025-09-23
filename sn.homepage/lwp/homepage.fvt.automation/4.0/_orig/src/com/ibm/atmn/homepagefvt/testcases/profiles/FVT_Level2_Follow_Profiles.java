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




import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Level2_Follow_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	

	
	@Test
	public void testFollowPerson () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testFollowPerson ");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Follow Person
		FollowPerson(CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout of profiles
		Logout();
		
		//VerifyNewsStory("Amy Jones450 followed Amy Jones451.","Discover","People", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testFollowPerson ");
	}

	
}
