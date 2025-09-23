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

public class FVT_ActionRequired_00011_00012_00014 extends FVT_CommunitiesMethods{
	
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
		ModeratedCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser4.getEmail());
		
		//Click members
		clickLink(FVT_CommunitiesObjects.LeftNavMembers);
		
		//click invite a member
		clickLink(FVT_CommunitiesObjects.InviteMembers);
		
		//Add users to invitation
		InviteUserToCommunity(testUser2);
		InviteUserToCommunity(testUser3);
		
		//Send Invite
		clickLink(FVT_CommunitiesObjects.InviteButton);		
		
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
	public void testDeclineModCommInvite() throws Exception {
		
		System.out.println("INFO: Start of testDeclineModCommInvite");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(testUser2);
		
		//Click on Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		Actions builder = new Actions(wd);
		
		//Find the Status Update and click the comment button
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+ModeratedCommunity+")";
		
		//Click on comment button belonging to relevant Status Update
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) 
		driver.getFirstElement("css=div.lotusPostContent > div.lotusPostMore > ul.lotusInlinelist > li > a:nth(2)").getBackingObject()).click().build().perform();	
		
		//Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchToWindowByHandle(winHandle);
		}
		
		assertTrue("Fail invitation not declined", driver.isTextPresent("Your invitation has been declined."));	

		//Close the new window
		driver.close();

		//Switch back to original browser (first window)
		driver.switchToWindowByHandle(winHandleBefore);

		//Logout
		LogoutAndClose();
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login
		Login(testUser2);
		
		//Click on Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);	
		
		//Verify Action required story is removed
		assertTrue("Fail invitation not removed", driver.isTextNotPresent(testUser1.getDisplayName()+ " invited you to join the "+ModeratedCommunity+" community."));	
		
		System.out.println("INFO: End of testDeclineModCommInvite");

	}
	
	@Test (dependsOnMethods = { "testCreateModCommInvite" })
	public void Member_Has_No_ActionRequired_Invite() throws Exception {
		
		System.out.println("INFO: Start of Member_Has_No_ActionRequired_Invite");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(testUser3);
		
		//Click on Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		//Verify Network Invite
		assertTrue("Fail - Invite is present for member", driver.isTextPresent(testUser1.getDisplayName()+ " invited you to join the "+ModeratedCommunity+" community."));
		
		//Logout
		LogoutAndClose();
		
		//Load Component
		LoadComponent(CommonObjects.ComponentCommunities);
		
		//Login
		Login(testUser1);
		
		//Open Community
		clickLink("link="+ModeratedCommunity);
		
		//Click members
		clickLink(FVT_CommunitiesObjects.LeftNavMembers);
		
		//Add Members
		clickLink(CommunitiesObjects.AddMembersToExistingCommunity);
		
		//Clear text field
		driver.getFirstElement(CommunitiesObjects.AddMembersToExistingTypeAheadWithoutCom).clear();

		//typre username
		driver.getFirstElement(CommunitiesObjects.AddMembersToExistingTypeAheadWithoutCom).type(testUser3.getDisplayName());
		
		smartSleep(FVT_CommunitiesObjects.Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom);
		
		//Click on Selected user
		driver.getSingleElement(FVT_CommunitiesObjects.Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom).click();
		
		//Click Save button
		clickLink("css=input[value='Save']");
		
		//Verify user has been added to community
		assertTrue("",driver.isTextPresent("You have successfully added the following members to this community: "+testUser3.getDisplayName()+"."));
		
		//Logout
		LogoutAndClose();
		
		//Load Component
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login
		Login(testUser3);
		
		//Open Action required
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		//Verify Actin required story has been removed
		assertTrue("Fail - Invite is present for member", driver.isTextNotPresent(testUser1.getDisplayName()+ " invited you to join the "+ModeratedCommunity+" community."));
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Member receives no Action required Invite");
	}

}
