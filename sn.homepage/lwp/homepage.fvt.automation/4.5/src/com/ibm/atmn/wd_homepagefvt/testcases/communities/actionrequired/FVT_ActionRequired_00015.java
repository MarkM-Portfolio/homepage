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
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;

public class FVT_ActionRequired_00015 extends FVT_CommunitiesMethods{
	
	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	
	FormInputHandler typist;	
	private static String PublicCommunity = "";
	
	
	@Test
	public void AcceptCommunityInvite() throws Exception {
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		testUser4 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of AcceptCommunityInvite");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Click I'm an Owner
		clickLink(FVT_CommunitiesObjects.ImAnOwner);

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
		
		//Click members
		clickLink(FVT_CommunitiesObjects.LeftNavMembers);
		
		//click invite a member
		clickLink(FVT_CommunitiesObjects.InviteMembers);
		
		//Add users to invitation
		InviteUserToCommunity(testUser2);
		
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
		assertTrue("Fail community add not visible", driver.isTextPresent(testUser1.getDisplayName()+ " invited you to join the "+PublicCommunity+" community."));
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		Actions builder = new Actions(wd);
		
		//Find the Status Update and click the comment button
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+PublicCommunity+")";
		
		//Click on comment button belonging to relevant Status Update
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) 
		driver.getFirstElement("css=div.lotusPostContent > div.lotusPostMore > ul.lotusInlinelist > li > a:nth(1)").getBackingObject()).click().build().perform();	
		
		//Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchToWindowByHandle(winHandle);
		}
		
		assertTrue("Fail invitation not accepted", driver.isTextPresent("You have joined the community and can now post content. Follow the community to get updates about community content."));	

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
		assertTrue("Fail invitation not removed", driver.isTextNotPresent(testUser1.getDisplayName()+ " invited you to join the "+PublicCommunity+" community."));	
		
		System.out.println("INFO: End of AcceptCommunityInvite");
		
	}
		
}