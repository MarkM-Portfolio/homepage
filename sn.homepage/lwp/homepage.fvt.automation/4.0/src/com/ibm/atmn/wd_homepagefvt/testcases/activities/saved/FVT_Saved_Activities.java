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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.saved;




import java.util.ArrayList;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;


public class FVT_Saved_Activities extends FVT_ActivitiesMethods {

	User testUser1;
	User testUser2;
	
	FormInputHandler typist;
	private ArrayList<String> assertList;

	private static String publicActivityName = "";
	private static String publicActivityName2 = "";
	private static String publicActivityPublicEntryTitle = "";
	private static String publicActivityPublicEntryTitle2 = "";
	
		
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testMarkingActivitySaved_Part1() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivitySaved_Part1");
		
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser2.getEmail());
			
			//Go back to activities list
			clickLink(FVT_ActivitiesObjects.Activities_Tab);
	
			//Expand top activity (activity created above)
			clickLink(FVT_ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);
			
			//Check all 'start activity' form entries were saved and are now displayed
			assertList = typist.getAssertList();
			assertAllTextPresent(assertList);
			typist.dumpList();
			
			//Go back to the main page of the activity created above
			clickLink("link=" + publicActivityName);
			
			//expand activity description
			clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
			
			changeActivityAccess();
			
			Logout();
				
			System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivitySaved_Part1");
			
		}
	
	@Test (dependsOnMethods = { "testMarkingActivitySaved_Part1" })
	public void testMarkingActivitySaved_Part2() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivitySaved_Part2");
		
			typist = new FormInputHandler(driver);
				
			//Save activity news story
			
			LoadComponent(CommonObjects.ComponentNews);
			
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			clickLink(FVT_NewsObjects.Discover);
			
			filterBy("Activities");
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			String ActivitiesStory = "css=div.lotusPostContent:contains(' made the "+publicActivityName+" activity public.')";
			
			//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
			builder.moveToElement((WebElement) driver.getFirstElement(ActivitiesStory).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
			
			//JavascriptExecutor js = (JavascriptExecutor) wd;
			
			//js.executeScript("var as = dijit.byId('activityStream');var firstNewsItem = dojo.query('.lotusPost', as.newsFeedNode[0]);dojo.addClass(firstNewsItem, 'lotusPostHover');",(Object) null);
		
			//clickLink(FVT_HomepageObjects.SaveThis);
			
			//Check that saved link is gone
			
			clickLink(FVT_HomepageObjects.Refresh_Discover);
			
			driver.getSingleElement(ActivitiesStory).hover();			
		
			assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
			//Go to Saved and check that news story is appearing 
			
			clickLink(FVT_NewsObjects.Saved);
			
			assertTrue("Fail: text is not present!!", driver.isTextPresent(" made the "+publicActivityName+" activity public."));
			
			//Delete activities news story
			
			filterBy("Activities");
			
			//driver.getSingleElement("css=div.lotusPostContent:contains('created a public activity named "+publicActivityName+".')").hover();

			builder.moveToElement((WebElement) driver.getFirstElement(ActivitiesStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();
			
			//clickLink(FVT_NewsObjects.DeleteSavedStory);
			
			clickLink(FVT_NewsObjects.RemoveSavedStory);

			assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

			//Check that the Saver this link appears again
			
			clickLink(FVT_NewsObjects.Discover);
			
			filterBy("Activities");
			
			driver.getSingleElement(ActivitiesStory).hover();	
			
			assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));		
			
			//Logout
			Logout();
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivitySaved_Part2");
			
		}
	
	@Test (dependsOnMethods = { "testMarkingActivitySaved_Part2" })
	public void testMarkingActivityEntrySaved_Part1() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityEntrySaved_Part1");
	
		typist = new FormInputHandler(driver);
		
		//Follow Activity
		//followActivity(publicActivityName);
		LoadComponent(CommonObjects.ComponentActivities);
		
		//Log in
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		//driver.getSingleElement(CommonObjects.Banner_Apps_Activities);
		//cautiousClick(CommonObjects.Banner_Apps_Activities);
		
		//Go back to activities list
		clickLink(FVT_ActivitiesObjects.Activities_Tab);
		
		//Go back to the main page of the activity created above
		clickLink("link=" + publicActivityName);
		
		//Follow
		clickLink(FVT_ActivitiesObjects.FollowStandActivity);
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityEntrySaved_Part1");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingActivityEntrySaved_Part1" })
	public void testMarkingActivityEntrySaved_Part2() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityEntrySaved_Part2");
	
		typist = new FormInputHandler(driver);
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
		
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
		
		// Create a new entry within this activity
		publicActivityPublicEntryTitle = addEntryToActivity_Basic(publicActivityName+" Entry",typist, false);
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityEntrySaved_Part2");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingActivityEntrySaved_Part2" })
	public void testMarkingActivityEntrySaved_Part3() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityEntrySaved_Part3");
	
		typist = new FormInputHandler(driver);
	
		//Save news story
		
		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		//clickLink("link=Home");
		
		clickLink(FVT_NewsObjects.AllUpdates);

		filterBy("Activities");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		System.out.println(publicActivityPublicEntryTitle);
		
		Reporter.log(publicActivityPublicEntryTitle);

		String ActivityEntryStory = "css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(ActivityEntryStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//driver.getSingleElement("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')").hover();

		//clickLink("link=Save this");
		
		//Check story saved correctly 
		
		clickLink(FVT_NewsObjects.Home);
		
		clickLink(FVT_NewsObjects.AllUpdates);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check story apppears in Saved
		
		clickLink(FVT_NewsObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
		
		//Delete news story
		
		filterBy("Activities");
		
		//driver.getSingleElement("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')").hover();

		builder.moveToElement((WebElement) driver.getSingleElement(ActivityEntryStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Go to I'm following and save news story
		
		clickLink(FVT_NewsObjects.AllUpdates);
		
		filterBy("Activities");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));	
		
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityEntrySaved_Part3");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingActivityEntrySaved_Part3" })
	public void testMarkingActivityCommentSaved_Part1() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityCommentSaved_Part1");
	
		typist = new FormInputHandler(driver);
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
				
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		addCommentToActivityEntry(typist, false);
			
		//Logout
		Logout();
			
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityCommentSaved_Part1");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingActivityCommentSaved_Part1" })
	public void testMarkingActivityCommentSaved_Part2() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityCommentSaved_Part2");
		
		//Go to For Me and save activities news story
		
		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);

		clickLink(FVT_NewsObjects.MyNotifications);
		
		filterBy("Activities");
		
		//driver.getSingleElement("css=div.lotusPostContent:contains('commented on your "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')").hover();

		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);

		String ActivityCommentStory = "css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry thread added by you in the "+publicActivityName+" activity.')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getSingleElement(ActivityCommentStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//clickLink("link=Save this");
	
		//Check that story saved
		
		clickLink(FVT_NewsObjects.Home);
		
		clickLink(FVT_NewsObjects.MyNotifications);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry thread added by you in the "+publicActivityName+" activity.')").hover();
	
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Go to Saved and ensure story appears here
		
		clickLink(FVT_NewsObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" commented on the "+publicActivityPublicEntryTitle+" entry thread added by you in the "+publicActivityName+" activity."));
		
		//Delete news story
		
		filterBy("Activities");
	
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry thread added by you in the "+publicActivityName+" activity.')").hover();

		builder.moveToElement((WebElement) driver.getSingleElement(ActivityCommentStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that Save this link is visible again
		
		clickLink(FVT_NewsObjects.MyNotifications);
		
		filterBy("Activities");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry thread added by you in the "+publicActivityName+" activity.')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityCommentSaved_Part2");
		
	}
	
	
	//@Test (dependsOnMethods = { "testMarkingActivityCommentSaved_Part2" })
	public void testSavedActivities_CommentingAfterSaving_Part1() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part1");
		
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			
			publicActivityName2 = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser2.getDisplayName());
			
			//Go back to activities list
			clickLink(FVT_ActivitiesObjects.Activities_Tab);
	
			//Expand top activity (activity created above)
			clickLink(FVT_ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);
			
			//Check all 'start activity' form entries were saved and are now displayed
			assertList = typist.getAssertList();
			assertAllTextPresent(assertList);
			typist.dumpList();
			
			//Go back to the main page of the activity created above
			clickLink("link=" + publicActivityName2);
			
			//expand activity description
			clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
			
			changeActivityAccess();
		
			// Create a new entry within this activity
			publicActivityPublicEntryTitle2 = addEntryToActivity_Basic(publicActivityName2+" Entry",typist, false);
			
			Logout();
				
			System.out.println("INFO: End of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part1");
			
		}
	
	
	//@Test (dependsOnMethods = { "testSavedActivities_CommentingAfterSaving_Part1" })
	public void testSavedActivities_CommentingAfterSaving_Part2() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part2");
	
		typist = new FormInputHandler(driver);
	
		//Save news story
		
		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		//clickLink("link=Home");
		
		clickLink(FVT_NewsObjects.Discover);

		filterBy("Activities");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);

		String ActivityEntryStory = "css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle2+" entry in the "+publicActivityName2+" activity.')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(ActivityEntryStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//driver.getSingleElement("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')").hover();

		//clickLink("link=Save this");
		
		//Check story saved correctly 
		
		clickLink(FVT_NewsObjects.Home);
		
		clickLink(FVT_NewsObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle2+" entry in the "+publicActivityName2+" activity.')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check story apppears in Saved
		
		clickLink(FVT_NewsObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" created the "+publicActivityPublicEntryTitle2+" entry in the "+publicActivityName2+" activity."));
		
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part2");
		
	}
	
	//@Test (dependsOnMethods = { "testSavedActivities_CommentingAfterSaving_Part2" })
	public void testSavedActivities_CommentingAfterSaving_Part3() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part3");
	
		typist = new FormInputHandler(driver);
	
		//Save news story
		
		LoadComponent(CommonObjects.ComponentActivities);

		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
		
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName2);
				
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		addCommentToActivityEntry(typist, false);
		
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part3");
		
	}
	
	//@Test (dependsOnMethods = { "testSavedActivities_CommentingAfterSaving_Part3" })
	public void testSavedActivities_CommentingAfterSaving_Part4() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part4");
	
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		clickLink(FVT_NewsObjects.Discover);
		
		filterBy("Activities");
		
		sleep(2000);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" commented on the "+publicActivityPublicEntryTitle2+" entry thread in the "+publicActivityName2+" activity."));

		clickLink(FVT_NewsObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" created the "+publicActivityPublicEntryTitle2+" entry in the "+publicActivityName2+" activity."));

		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part4");
		
	}


}