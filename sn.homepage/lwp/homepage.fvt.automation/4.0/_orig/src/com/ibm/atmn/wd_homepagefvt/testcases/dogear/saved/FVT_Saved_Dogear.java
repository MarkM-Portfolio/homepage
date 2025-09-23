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

package com.ibm.atmn.wd_homepagefvt.testcases.dogear.saved;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.dogear.FVT_DogearMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearData;


public class FVT_Saved_Dogear extends FVT_DogearMethods {


	

	private static String PUBLIC_BOOKMARK_NAME = "";
	
	//Now Get the DateTime
	String DateTimeStamp = CommonMethods.genDateBasedRandVal();
	
	//NOTE: Only the ajones[number](Amy Jones[number]) class of users are valid here without further changes.
	private static String testUser1Username = CommonData.IC_LDAP_Username450;
	private static String testUser1Password = CommonData.IC_LDAP_Password450;

	
		
	@Test 
	public void testMarkingBookmarksStoriesAsSaved() throws Exception {
			
			
		System.out.println("INFO: Start of Bookmarks Level 2 testMarkingForumsStoriesAsSaved");	
		
		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones450)
		Login(testUser1Username, testUser1Password);

		//Add a public bookmark 
		
		PUBLIC_BOOKMARK_NAME = addPublicBookmark(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp, FVT_DogearData.PublicBookmarkURL+DateTimeStamp, FVT_DogearData.PublicBookmarkTag, FVT_DogearData.BookmarkDescription);

		// Logout of Connections
		Logout();
		
		driver.close();
		//
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("Bookmarks");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
			
		String DogearStory = "css=div.lotusPostContent:contains('created a bookmark named "+PUBLIC_BOOKMARK_NAME+".')";

		builder.moveToElement((WebElement) driver.getFirstElement(DogearStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		clickLink(FVT_HomepageObjects.Refresh_Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a bookmark named "+PUBLIC_BOOKMARK_NAME+".')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a bookmark named "+PUBLIC_BOOKMARK_NAME+"."));
	
		filterBy("Bookmarks");
		
		builder.moveToElement((WebElement) driver.getFirstElement(DogearStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();
				
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Bookmarks");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a bookmark named "+PUBLIC_BOOKMARK_NAME+".')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
				
		System.out.println("INFO: End of Bookmarks FVT_Level_2 testMarkingBookmarksStoriesAsSaved");
			
	}
	
	@Test (dependsOnMethods = { "testMarkingBookmarksStoriesAsSaved" })
	public void testMarkingBookmarksStoriesAsSavedPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Bookmarks FVT_Level_2 testMarkingBookmarksStoriesAsSavedPart2");
	
		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		notifyUserAboutBookmark(CommonData.IC_LDAP_UserName451_Typeahead);
		
		// Logout of Connections
		Logout();
				
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Bookmarks");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
			
		String DogearNotifStory = "css=div.lotusPostContent:contains('"+PUBLIC_BOOKMARK_NAME+"')";

		builder.moveToElement((WebElement) driver.getFirstElement(DogearNotifStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		clickLink(FVT_HomepageObjects.Refresh_MyNotifications);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+PUBLIC_BOOKMARK_NAME+"')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(PUBLIC_BOOKMARK_NAME));
				
		filterBy("Bookmarks");
		
		builder.moveToElement((WebElement) driver.getFirstElement(DogearNotifStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Bookmarks");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+PUBLIC_BOOKMARK_NAME+"')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Bookmarks FVT_Level_2 testMarkingBookmarksStoriesAsSavedPart2");
		
	}
	

}