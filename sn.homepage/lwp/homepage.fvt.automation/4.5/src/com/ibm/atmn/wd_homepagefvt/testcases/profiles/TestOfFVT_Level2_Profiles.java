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





import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class TestOfFVT_Level2_Profiles extends FVT_ProfilesMethods{
	
	
	
	@Test
	public void testProfilesPersonSelftagged_Multiple () throws Exception {
		System.out.println("INFO: Start of News Level 2 testStatusUpdateMyNetwork_Part1");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login("jdoe5","doe5");
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Search for user
		SearchForUser("sadams8@ipdorn2007.mul.ie.ibm.com");
		//SearchForUser(CommonData.IC_LDAP_Username_Fullname451);
		
		//Invite User To You network
		boolean Pending = InviteToNetwork();		
		Reporter.log("Pending status = "+Pending);
		
		//Logout of profiles
		//LogoutAndClose();
		Logout();
		
		if (Pending==true)
		{
		
			LoadComponent(CommonObjects.ComponentProfiles);
			//Log in as other user
			Login("sadams8@ipdorn2007.mul.ie.ibm.com","adams8");
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			//Accept Invite
			clickLink("link=My Network");
			
			clickLink("link=Invitations");
			
			clickLink(FVT_ProfilesObjects.AcceptInvite);
			
			//Logout of Wiki
			Logout();
		
		}

		System.out.println("INFO: End of News Level 2 testStatusUpdateMyNetwork_Part1");
	}
	
	

}
