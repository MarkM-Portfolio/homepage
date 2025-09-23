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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.actionrequired;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;

public class FVT_ActionRequired_00023 extends FVT_CommunitiesMethods{
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	FormInputHandler typist;	
	private static String ModeratedCommunity = "";
	
	
	@Test
	public void testCreateModComm() throws Exception {
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of testCreateModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Click I'm an Owner
		clickLink(FVT_CommunitiesObjects.ImAnOwner);

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of testCreateModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateModComm" })
	public void testRequestAccess() throws Exception {
		
		System.out.println("INFO: Start of testRequestAccess");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Click on Public Communities
		clickLink(FVT_CommunitiesObjects.PublicCommunityView);
		
		//Open community
		clickLink("link="+ModeratedCommunity);
		
		//Request to Join Community
		clickLink(FVT_CommunitiesObjects.JOIN_COMMUNITY_REQUEST);
		
		//Send request
		clickLink(CommonObjects.SEND_BUTTON);
		
		//verify request was sent
		assertTrue("Fail - Join request not successful", driver.isTextPresent("Your membership request has been sent."));
		
		//logout
		LogoutAndClose();
		
		//Load Component
		LoadComponent(CommonObjects.ComponentNews);

		//Login
		Login(testUser1);
		
		//open Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		System.out.println(testUser2.getDisplayName()+ " has requested to join your "+ModeratedCommunity+" community.");
		
		//Verify Community request
		assertTrue("Fail - Community Request not visible", driver.isTextPresent(testUser2.getDisplayName()+ " has requested to join your "+ModeratedCommunity+" community."));
		
		
		System.out.println("INFO: End of testRequestAccess");
	}
	
	@Test (dependsOnMethods = { "testRequestAccess" })
	public void testDeleteCommunity() throws Exception {
		
		
		System.out.println("INFO: Start of test Delete Community");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Go to moderated community
		clickLink("link=" + ModeratedCommunity);
		
		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);
		smartSleep(CommunitiesObjects.Menu_Item_6);
		
		//Choose Delete
		driver.getSingleElement(CommunitiesObjects.Menu_Item_6).click();
		Thread.sleep(500);
		
		//Confirm community name
		driver.getFirstElement(CommunitiesObjects.ConfirmCommName).type(ModeratedCommunity);
		
		//Confirm User name
		driver.getFirstElement(CommunitiesObjects.ConfirmUserName).type(testUser1.getDisplayName());
		
		//Tick confirm delete CheckBox
		driver.getFirstElement(CommunitiesObjects.ConfirmCheckbox).click();
		
		//Press Delete Button
		driver.getFirstElement(CommunitiesObjects.DeleteCommunityBtn).click();
		
		//Logout
		LogoutAndClose();
				
		//Load Component Community
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login user2
		Login(testUser1);
		
		//Click on Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		//Verify Action required newsstory deleted
		assertTrue("Fail community not deleted", driver.isTextNotPresent(testUser2.getDisplayName()+ " has requested to join your "+ModeratedCommunity+" community."));
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of testDeleteCommunity");
		
				
	}
		
}