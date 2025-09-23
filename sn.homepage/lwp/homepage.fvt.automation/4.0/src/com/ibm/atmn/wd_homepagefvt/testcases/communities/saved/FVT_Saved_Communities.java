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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.saved;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;



import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;



public class FVT_Saved_Communities extends FVT_CommunitiesMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	
	static String PublicCommunity = "";
	private static String PublicCalendarEntry = "";
	private static String Public_Comm_File = "";
	
		
	@Test 
	public void testMarkingCommunitiesStoriesAsSaved() throws Exception {
			
			
		System.out.println("INFO: Start of Communities Level 2 testMarkingCommunitiesStoriesAsSaved");	
		
		//Create community
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());

		//Add the blogs widget
		AddWidgetToOverview("Events");
		
		//Logout
		Logout();
					
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved");
			
	}
	
	
	@Test (dependsOnMethods = { "testMarkingCommunitiesStoriesAsSaved" })
	public void testMarkingCommunitiesStoriesAsSaved_Part1b() throws Exception {
			
			
		System.out.println("INFO: Start of Communities Level 2 testMarkingCommunitiesStoriesAsSaved_Part1b");	
		
		//Create community
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		//Log in
		Login(testUser2);
		
		clickLink(FVT_CommunitiesObjects.PublicCommunityView);
		
		//Go back to the main page of the Blog created above
		clickLink("link=" + PublicCommunity);
		
		//Follow
		clickLink(FVT_CommunitiesObjects.FollowCommunity);
		
		//Join
		clickLink(FVT_CommunitiesObjects.JoinCommunity);
		
		//Logout
		Logout();
		
		driver.close();
			
		//Save the communities news story
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(testUser1);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("Communities");
			
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String CommunitiesStory = "css=div.lotusPostContent:contains('created a community named "+PublicCommunity+".')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(CommunitiesStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis_2).getBackingObject()).click().build().perform();
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");

		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the news story is saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a community named "+PublicCommunity+".')").hover();
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the news story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" created a community named "+PublicCommunity+"."));
	
		//Delete the Saved news story
		
		filterBy("Communities");
		
		builder.moveToElement((WebElement) driver.getFirstElement(CommunitiesStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();

		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Communities");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a community named "+PublicCommunity+".')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
					
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved_Part1b");
			
	}
	
	
	
	@Test (dependsOnMethods = { "testMarkingCommunitiesStoriesAsSaved_Part1b" })
	public void testMarkingCommunitiesStoriesAsSavedPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSavedPart2");
	
		//
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + PublicCommunity);
	
		// Created a new calendar entry
		
		PublicCalendarEntry = "FVT Calendar Event " + genDateBasedRandVal();

		createCalendarEntry(PublicCalendarEntry, false, false, null);
		
		//Logout
		LogoutAndClose();
		
		//Saved the communities news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(testUser2);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.AllUpdates);

		filterBy("Communities");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String CommunitiesEventStory = "css=div.lotusPostContent:contains('created the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(CommunitiesEventStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();

		//cautiousFocus("css=div.lotusPostContent:contains('created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check the story is saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		filterBy("Communities");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')").hover();
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community."));
		
		//Delete the saved news story
		
		filterBy("Communities");
		
		builder.moveToElement((WebElement) driver.getFirstElement(CommunitiesEventStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		filterBy("Communities");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')").hover();

		assertTrue("Fail: Saved text still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		Logout();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSavedPart2");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingCommunitiesStoriesAsSavedPart2" })
	public void testMarkingCommunitiesStoriesAsSavedPart3() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSavedPart3");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login as a user (ie. Amy Jones451)
		Login(testUser2);
	
		//Open Calendar
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.PublicCommunityView, FVT_CommunitiesObjects.LeftNavEvents);
	
		//Click on the Blog entry
		clickLink("link=" + PublicCalendarEntry);
		
		//Update blog entry
		AddACommentToEventEntry();
			
		//Logout
		LogoutAndClose();
		
		//Save the communities news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(testUser1);

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Communities");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String CommunitiesEventCommentStory = "css=div.lotusPostContent:contains('commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(CommunitiesEventCommentStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		//clickLink(FVT_HomepageObjects.SaveThis);
	
		//Check that the story is saved 
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')").hover();
	
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community."));
		
		//Delete the saved news story
		
		filterBy("Communities");
		
		builder.moveToElement((WebElement) driver.getFirstElement(CommunitiesEventCommentStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link appears again

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Communities");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')").hover();

		assertTrue("Fail: Saved text still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSavedPart3");
		
	}
	
	//@Test //(dependsOnMethods = { "testMarkingCommunitiesStoriesAsSavedPart3" })
	public void testCommunityFileSaved() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCommunityFileSaved");
	
		//
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + PublicCommunity);
	
		//Generate random file name
		Public_Comm_File = "FVT Public Community File "+genDateBasedRandVal();
		
		//Upload private file
		uploadFileToCommunity(Public_Comm_File, ".jpg", "Desert.jpg");
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCommunityFileSaved");
		
	}
	

}