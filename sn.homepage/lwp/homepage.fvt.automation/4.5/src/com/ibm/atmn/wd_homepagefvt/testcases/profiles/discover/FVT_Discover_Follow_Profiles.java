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

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Follow_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	User testUser3;

	
	@Test
	public void testFollowPerson () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testFollowPerson ");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
	
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
		testUser3 = userAllocator.getUser();
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//Follow Person
		FollowPerson(testUser2.getEmail());
		
		//Logout of profiles
		Logout();
		
		//verifyNewsStory("Amy Jones450 followed Amy Jones451.","Discover","People", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testFollowPerson ");
	}

	
}
