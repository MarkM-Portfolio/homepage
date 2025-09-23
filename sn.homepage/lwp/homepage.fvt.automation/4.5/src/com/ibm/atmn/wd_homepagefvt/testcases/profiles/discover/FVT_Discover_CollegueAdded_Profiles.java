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
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;



public class FVT_Discover_CollegueAdded_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	
	
	
	
	@Test
	public void testCollegueAdded_Accepted () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testCollegueAdded_Accepted");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Search for user
		SearchForUser(testUser1.getDisplayName());
		
		//Invite User To You network
		InviteToNetwork();		
		
		//Logout of profiles
		Logout();	
		
		//Log in as other user
		clickLink("css=a:contains('Log In')");
		Login(testUser2);
		
		//Accept Invite
		clickLink("link=My Network");
		
		clickLink("link=Invitations");
		
		if(driver.isElementPresent(FVT_ProfilesObjects.AcceptInvite)){
			clickLink(FVT_ProfilesObjects.AcceptInvite);
		}
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testCollegueAdded_Accepted");
	}
	
	@Test (dependsOnMethods = { "testCollegueAdded_Accepted" })
	public void testCollegueAdded_Rejected () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testCollegueAdded_Rejected");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Search for user
		SearchForUser(testUser2.getDisplayName());
		
		//Invite User To You network
		InviteToNetwork();		
		
		//Logout of profiles
		Logout();	
		
		//Log in as other user
		clickLink("css=a:contains('Log In')");
		Login(testUser2);
		
		//Reject Invite
		clickLink("link=My Network");
		
		clickLink("link=Invitations");
		
		if(driver.isElementPresent(FVT_ProfilesObjects.RejectInvite)){
		clickLink(FVT_ProfilesObjects.RejectInvite);
		}
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testCollegueAdded_Rejected");
	}
	
	
}
