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

package com.ibm.atmn.wd_homepagefvt.testcases.news;



import static org.testng.AssertJUnit.*;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.waffle.extensions.user.User;


public class FVT_StatusUpdates_Commenting extends FVT_CommunitiesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StatusUpdate = "";
	private static String PublicCommunity = "";
	
	@Test
	public void testVerifyStatusUpdateViewAdded() throws Exception {

		System.out.println("INFO: Start of News Level 2 testVerifyStatusUpdateViewAdded");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
	
		Login(testUser1);	
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//verify status updates views
		
		verifyStatusUpdatesViews();
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testVerifyStatusUpdateViewAdded");
	}

	@Test (dependsOnMethods = { "testVerifyStatusUpdateViewAdded" })
	public void testStatusUpdateAll() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateAll");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser1);
		
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Open status updates
		
		openStatusUpdates();
		
		//Update status
		
		StatusUpdate = statusUpdate(FVT_NewsData.UpdateStatus);
			
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		//builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(FVT_NewsData.StopFollowingDialog));
		
		//Logout of Homepage
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateAll");
	}
	
	
	@Test (dependsOnMethods = { "testStatusUpdateAll" })
	public void testStatusUpdatePeopleIFollow_Part1() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdatePeopleIFollow");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//FollowPerson(testUser2.getDisplayName());
		FollowPerson(testUser2.getEmail());
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdatePeopleIFollow");
	}
	
	
	@Test (dependsOnMethods = { "testStatusUpdatePeopleIFollow_Part1" })
	public void testStatusUpdatePeopleIFollow_Part2() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdatePeopleIFollow");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser2 = userAllocator.getUser();
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
	
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdatePeopleIFollow");
	}

	@Test (dependsOnMethods = { "testStatusUpdatePeopleIFollow_Part2" })
	public void testStatusUpdatePeopleIFollow_Part3() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdatePeopleIFollow_Part3");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("People I Follow");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		//builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(FVT_NewsData.StopFollowingDialog));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdatePeopleIFollow_Part3");
	}
	
	@Test (dependsOnMethods = { "testStatusUpdatePeopleIFollow_Part3" })
	public void testStatusUpdateCommunitiesIFollow_Part1() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateCommunitiesIFollow_Part1");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access
		
		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateCommunitiesIFollow_Part1");
	}
	
	
	@Test (dependsOnMethods = { "testStatusUpdateCommunitiesIFollow_Part1" })
	public void testStatusUpdateCommunitiesIFollow_Part2() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateCommunitiesIFollow_Part2");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		//testUser2 = userAllocator.getUser();
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink("link=Public Communities");
		
		clickLink("link="+PublicCommunity);
		
		clickLink(FVT_CommunitiesObjects.FollowCommunity);
	
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateCommunitiesIFollow_Part2");
	}
	
	
	@Test (dependsOnMethods = { "testStatusUpdateCommunitiesIFollow_Part2" })
	public void testStatusUpdateCommunitiesIFollow_Part3() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateCommunitiesIFollow_Part3");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PublicCommunity);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_CommunitiesObjects.StatusUpdates);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdateComm));
	
		StatusUpdate = updateStatusCommunity(StatusUpdate);
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateCommunitiesIFollow_Part3");
	}

	@Test (dependsOnMethods = { "testStatusUpdateCommunitiesIFollow_Part3" })
	public void testStatusUpdateCommunitiesIFollow_Part4() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateCommunitiesIFollow_Part4");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("Communities I Follow");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		//builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent("Stop following"));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateCommunitiesIFollow_Part4");
	}
	
	@Test (dependsOnMethods = { "testStatusUpdateCommunitiesIFollow_Part4" })
	public void testStatusUpdateMyNetwork_Part1() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateMyNetwork_Part1");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Search for user
		SearchForUser(testUser2.getEmail());
		//SearchForUser(CommonData.IC_LDAP_Username_Fullname451);
		
		//Invite User To You network
		boolean Pending = InviteToNetwork();		
		
		//Logout of profiles
		LogoutAndClose();
		
		if (Pending==true)
		{
		
			LoadComponent(CommonObjects.ComponentProfiles);
			//Log in as other user
			Login(testUser2);
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
	
	
	@Test (dependsOnMethods = { "testStatusUpdateMyNetwork_Part1" })
	public void testStatusUpdateMyNetwork_Part2() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateMyNetwork_Part2");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser2 = userAllocator.getUser();
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);

		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		Reporter.log(StatusUpdate);
	
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateMyNetwork_Part2");
	}

	@Test (dependsOnMethods = { "testStatusUpdateMyNetwork_Part2" })
	public void testStatusUpdateMyNetwork_Part3() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateMyNetwork_Part3");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
			
		filterBy_StatusUpdates("My Network");
		
		//Reporter.log(StatusUpdate);
		
		sleep(2000);
		
		clickLink(FVT_HomepageObjects.Refresh_StatusUpdates);
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
				
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateMyNetwork_Part3");
	}
	
	@Test (dependsOnMethods = { "testStatusUpdateMyNetwork_Part3" })
	public void testStatusUpdateMyNetworkAndPeopleIFollow() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateMyNetworkAndPeopleIFollow");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("My Network And People I Follow");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		//builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(FVT_NewsData.StopFollowingDialog));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateMyNetworkAndPeopleIFollow");
	}
	
	@Test (dependsOnMethods = { "testStatusUpdateMyNetworkAndPeopleIFollow" })
	public void testStatusUpdateMyUpdates() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateAll");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("My Updates");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		//builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(FVT_NewsData.StopFollowingDialog));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateAll");
	}
	
	
}
