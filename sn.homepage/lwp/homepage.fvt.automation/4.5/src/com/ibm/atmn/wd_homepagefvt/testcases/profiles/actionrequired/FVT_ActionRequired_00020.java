/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013, 2014                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.profiles.actionrequired;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_ActionRequired_00020 extends FVT_ProfilesMethods{
	
	User testUser1;
	User testUser2;
	
	
	@Test
	public void testInviteCollegue () throws Exception {
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of testInviteCollegue");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login
		Login(testUser2);
		
		//Search for user
		SearchForUserByName(testUser1.getDisplayName());
		
		//Invite User To You network
		InviteToNetwork();		
		
		//Logout of profiles
		LogoutAndClose();	
		
		//Load Component News
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login
		Login(testUser1);
		
		//Click on Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		//Verify Network Invite
		assertTrue("Fail network invite not visible", driver.isTextPresent(testUser2.getDisplayName()+ " invited you to become a network contact."));
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of testInviteCollegue");
	}

}
