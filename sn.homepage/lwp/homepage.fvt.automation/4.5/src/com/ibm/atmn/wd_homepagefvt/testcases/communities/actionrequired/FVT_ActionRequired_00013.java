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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class FVT_ActionRequired_00013 extends FVT_CommunitiesMethods{
	
	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	
	FormInputHandler typist;	
	private static String ModeratedCommunity = "";
	
	
	@Test
	public void testCreateModCommInvite() throws Exception {
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		testUser4 = userAllocator.getUser(this);
		
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
		
		//Click members
		clickLink(FVT_CommunitiesObjects.LeftNavMembers);
		
		//invite a member
		clickLink(FVT_CommunitiesObjects.InviteMembers);
		
		//Type member to invite
		driver.getFirstElement(FVT_CommunitiesObjects.InviteCommunityMembersTypeAhead).clear();
		driver.getFirstElement(FVT_CommunitiesObjects.InviteCommunityMembersTypeAhead).type(testUser2.getDisplayName());
		
		smartSleep(FVT_CommunitiesObjects.selectedUserIdentifier_Invite1);
		
		//Click on Selected user
		driver.getSingleElement(FVT_CommunitiesObjects.selectedUserIdentifier_Invite1).click();
		
		//Send Invite
		clickLink(FVT_CommunitiesObjects.InviteButton);
		
		//verify invite was sent successfully
		assertTrue("Fail - Invite was not successful",driver.isTextPresent("You have successfully invited the following people to this community: "+testUser2.getDisplayName()+"."));
		
		//Logout
		LogoutAndClose();
		
		
	    //Load Component News
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login
		Login(testUser2);
		
		//Click on Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		//Verify Network Invite
		assertTrue("Fail community add not visible", driver.isTextPresent(testUser1.getDisplayName()+ " invited you to join the "+ModeratedCommunity+" community."));
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of testCreateModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateModCommInvite" })
	public void testRequestAccess() throws Exception {
		
		System.out.println("INFO: Start of testRequestAccess");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser4);
		
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
		
		System.out.println(testUser4.getDisplayName()+ " has requested to join your "+ModeratedCommunity+" community.");
		
		//Verify Community request
		assertTrue("Fail - Community Request not visible", driver.isTextPresent(testUser4.getDisplayName()+ " has requested to join your "+ModeratedCommunity+" community."));
		
		
		System.out.println("INFO: End of testRequestAccess");
	}
	
	
	@Test (dependsOnMethods = { "testRequestAccess" })
	public void AcceptCommunityJoinRequest() throws Exception {
		
		System.out.println("INFO: Start of AcceptCommunityJoinRequest");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(testUser1);
		
		//Click Action Required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		Actions builder = new Actions(wd);
		
		//Find the Status Update and click the comment button +testUser4.getDisplayName()+" has requested to join your "  community.
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+ModeratedCommunity+")";
		
		//Click on comment button belonging to relevant Status Update
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) 
		driver.getFirstElement("css=div.lotusPostContent > div.lotusPostMore > ul.lotusInlinelist > li > a:nth(1)").getBackingObject()).click().build().perform();	
		
		//Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchToWindowByHandle(winHandle);
		}
		
		//Click Save button
		clickLink("css=input[value='Save']");

		//Close the new window
		driver.close();

		//Switch back to original browser (first window)
		driver.switchToWindowByHandle(winHandleBefore);

		//Click Action Required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);		
		
		//Verify Action required story is removed
		assertTrue("Fail invitation not removed", driver.isTextNotPresent(testUser4.getDisplayName()+" has requested to join your "+ModeratedCommunity+" community."));	
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of AcceptCommunityInvite");
		
	}
	
	
	@Test (dependsOnMethods = { "AcceptCommunityJoinRequest" })
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
		Login(testUser2);
		
		//Click on Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		//Verify Action required newsstory deleted
		assertTrue("Fail community not deleted", driver.isTextNotPresent(testUser1.getDisplayName()+ " invited you to join the "+ModeratedCommunity+" community."));
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of test Delete Mod Comm");
		
				
	}
		
}