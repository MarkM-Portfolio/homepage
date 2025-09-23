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
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;
import com.ibm.atmn.wdbase.core.data.User;

public class FVT_Saved_StatusUpdates extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StatusUpdate1 = "";
	private static String StatusUpdate2 = "";
	private static String StatusUpdate3 = "";
	private static String StatusUpdate4 = "";
	private static String StatusComment1 = "";
	private static String StatusComment2 = "";
	private static String StatusComment3 = "";

	
	@Test
	public void testSavedStatusUpdatesPart1 () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart1");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		testUser1 = userAllocator.getUser();
		Login(testUser1);
		
		//Update Status
		StatusUpdate1 = statusUpdate();
	
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Save the status update news story
		filterBy("Status Updates");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdate = "css=div.lotusPostContent:contains('"+StatusUpdate1+"')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdate).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the Save this button has disappeared from news story
		clickLink(FVT_HomepageObjects.Home);

		clickLink(FVT_HomepageObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+StatusUpdate1+"')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that news story appears in saved
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(StatusUpdate1));
		
		//Delete Story
		filterBy("Status Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate1+"')");
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdate).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that save this button is available again
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Status Updates");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+StatusUpdate1+"')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout of homepage
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart1");
	}
	
	@Test (dependsOnMethods = { "testSavedStatusUpdatesPart1" })
	public void testSavedStatusUpdatesPart2 () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart2");	
		
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
		Login(testUser1);
		
		//Go to Discover View
		//clickLink(FVT_HomepageObjects.Discover);
		
		//Update Status
		StatusUpdate2 = statusUpdate();
		
		//Logout of profiles
		Logout();
			
		//User 2 comment on status update 
		
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		clickLink("css=div.lotusPostContent:contains('"+StatusUpdate2+"')");
		
		StatusComment1 = CommentOnStatus_EE(StatusUpdate2);
		
		//Logout of profiles
		Logout();	
		
		//User 1 save the comment news story
		
		//Load News
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//Go To Home
		clickLink(FVT_HomepageObjects.Home);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		//Filter by People
		filterBy("Status Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('"+StatusComment1+"')");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusComment = "css=div.lotusPostContent:contains('"+StatusComment1+"')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getSingleElement(StatusComment).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that story was saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+StatusComment1+"')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that news story appears in saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(StatusUpdate2));
		
		//Delete Story
		
		filterBy("Status Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate2+"')");
		
		String StatusUpdate_2 = "css=div.lotusPostContent:contains('"+StatusUpdate2+"')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdate_2).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that save this link is available again in For Me
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Status Updates");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+StatusComment1+"')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart2");
	}
	
	//@Test 
	public void testSavedStatusUpdatesPart3 () throws Exception {
		
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart3");	
		
		
		//User 1 follow User 2
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
		Login(testUser1);
		
		//Follow Person
		FollowPerson(testUser2.getDisplayName());
		
		//Logout of profiles
		Logout();
		
		driver.close();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//User 2 update status
		
		//Login as a user (ie. Amy Jones451)
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Update Status
		StatusUpdate3 = statusUpdate();
		
		//Logout of profiles
		Logout();
		
		driver.close();
		
		LoadComponent(CommonObjects.ComponentNews);
		
		//Check that story appears in User 1's I'm following and click save
		
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		//Filter by People
		filterBy("Status Updates");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdate = "css=div.lotusPostContent:contains('"+StatusUpdate3+"')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdate).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that story is saved 
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+StatusUpdate3+"')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Go to Saved and check story appears there
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(StatusUpdate3));
		
		//Comment on status update
		
		StatusComment2 = CommentOnStatus(StatusUpdate3);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(StatusComment2));
		
		//Logout of profiles
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart3");
	}
	
	//@Test 
	public void testSavedStatusUpdatesPart4 () throws Exception {
		
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart4");	
		
		//User 1 follow User 2
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
		Login(testUser1);
		
		//Follow Person
		FollowPerson(testUser2.getDisplayName());
		
		//Logout of profiles
		Logout();
		
		//User 2 updates status
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login as a user (ie. Amy Jones452)
		//Login(CommonData.IC_LDAP_Username452, CommonData.IC_LDAP_Password452);
		Login(testUser2);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Update Status
		StatusUpdate4 = statusUpdate();
		
		//Logout of profiles
		Logout();
			
		//Save news story
		
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		//Filter by People
		filterBy("Status Updates");
		
		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that news story saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+StatusUpdate4+"')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that news story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(StatusUpdate4));
		
		//Comment on status update
		
		StatusComment3 = CancelComment(StatusUpdate4);
		
		assertTrue("Fail: Cancel did not work!!", !driver.isTextPresent(StatusComment3));
		
		//Logout of profiles
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart4");
	}
	
	
	@Test
	public void testCheckStatusUpdatesSaved () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart1");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		testUser1 = userAllocator.getUser();
		Login(testUser1);
		
		//Update Status
		StatusUpdate1 = statusUpdate();
	
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Save the status update news story
		filterBy("Status Updates");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdate = "css=div.lotusPostContent:contains('"+StatusUpdate1+"')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdate).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the Save this button has disappeared from news story
		clickLink(FVT_HomepageObjects.Home);

		clickLink(FVT_HomepageObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+StatusUpdate1+"')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that news story appears in saved
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(StatusUpdate1));
		
		//Logout of homepage
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart1");
	}
	
}
