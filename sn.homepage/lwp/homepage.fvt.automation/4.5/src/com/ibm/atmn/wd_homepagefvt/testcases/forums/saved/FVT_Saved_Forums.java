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

package com.ibm.atmn.wd_homepagefvt.testcases.forums.saved;




import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;
import java.util.List;
import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;



public class FVT_Saved_Forums extends FVT_ForumsMethods {


	User testUser1;
	User testUser2;
	User testUser3;

	private static String part1ForumName = "";
	private static String part1TopicTitle = "";
	private List<Element> list = null;
	
	private static String ForumName2 = "";
	private static String TopicTitle2 = "";
	
		
	@Test (groups={"saveandremove"})
	public void testMarkingForumsStoriesAsSaved() throws Exception {
			
			
		System.out.println("INFO: Start of Communities Level 2 testMarkingForumsStoriesAsSaved");	
		
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		// Login as a user (ie. Amy Jones450)
		Login(testUser1);

		// Start Forums FVT (Level 2) testCreateStandaloneForum
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Start a forum
		String forumRandomNumber = genDateBasedRandVal();
		part1ForumName = FVT_ForumsData.Start_A_Forum_InputText_Name_Data + forumRandomNumber;
		Reporter.log("Test 1 - part1ForumName "+part1ForumName);
		startAForum(part1ForumName);
				
		//Logout of Connections
		LogoutAndClose();	
		
		//driver.close();
		
		Thread.sleep(3000);
		
		//Follow Forum
		
		LoadComponent(CommonObjects.ComponentForums);
		
		//Log in
		Login(testUser2);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.ForumsTopTab);
		
		Reporter.log("User 2 is following (User2 name)-> "+testUser2.getDisplayName());
		
		//Follow Forum
		followForum(part1ForumName);
		
		//Logout
		LogoutAndClose();
		
		////driver.close();
			
		//Save the forum news story in Discover
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(testUser1);
		
		Reporter.log("User 1 ->"+testUser1.getDisplayName());
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("Forums");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String ForumStory = "css=div.lotusPostContent:contains('created the "+part1ForumName+" forum.')";
		
		//builder.moveToElement((WebElement) driver.getFirstElement(ForumStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();	
		list = driver.getElements("css=div.activityStreamNewsItemContainer div.lotusPostContent");
		
		for(Element i:list){
			if (i.isTextPresent(ForumStory)) {
				i.hover();
				i.click();
				break;
			}
		}
		
		
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created the "+part1ForumName+" forum.')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']:nth(0)"));
			
		//Check that the saved news story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created the "+part1ForumName+" forum."));
			
		//Delete the news story 
		
		filterBy("Forums");
		
		builder.moveToElement((WebElement) driver.getFirstElement(ForumStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();	

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		//assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Forums");
		
		sleep(2000);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created the "+part1ForumName+" forum."));

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']:nth(0)"));
		
		//Logout
		LogoutAndClose();
			
		System.out.println("INFO: End of Forums FVT_Level_2 testMarkingForumsStoriesAsSaved");
			
	}
	
	
	
	
	@Test (dependsOnMethods = { "testMarkingForumsStoriesAsSaved" }, groups={"saveandremove"})
	public void testMarkingForumsStoriesAsSavedPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testMarkingForumsStoriesAsSavedPart2");
	
		
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(testUser1);

		// Start Forums FVT (Level 2) testCreateStandaloneForumTopic
		//clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Go to forum
		openForum(part1ForumName);
		
		Reporter.log("part1ForumName ::> "+part1ForumName);
		
		// Start a topic
		String topicRandomNumber = genDateBasedRandVal();
		part1TopicTitle = FVT_ForumsData.Start_A_Topic_InputText_Title_Data + topicRandomNumber;
		
		Reporter.log("part1TopicTitle "+part1TopicTitle);
		startATopic(part1TopicTitle);
		
		//Logout of Connections
		LogoutAndClose();	
		
		////driver.close();
		
		//Save the news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(testUser2);
		
		clickLink(FVT_HomepageObjects.GettingStarted);
		
		clickLink(FVT_HomepageObjects.AllUpdates);

		filterBy("Forums");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String ForumTopicStory = "css=div.lotusPostContent:contains('created a topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.')";
		
		Reporter.log(" ForumTopicStory  ->  "+ForumTopicStory);
		
		sleep(30); // testing
		//builder.moveToElement((WebElement) driver.getFirstElement(ForumTopicStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
	
		driver.getFirstElement(ForumTopicStory).hover();
		driver.getFirstElement(FVT_HomepageObjects.SaveThis).click();
		
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		filterBy("Forums");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.')").hover();
		
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']:nth(0)"));			
		assertTrue("Fail: Link still there!!", driver.getFirstElement("css=div.activityStreamNewsItemContainer").isTextPresent("Saved"));
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a topic named "+part1TopicTitle+" in the "+part1ForumName+" forum."));
		
		//Delete the news story
		
		filterBy("Forums");
		
		builder.moveToElement((WebElement) driver.getFirstElement(ForumTopicStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		filterBy("Forums");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']:nth(0)"));		
		LogoutAndClose();
		
		System.out.println("INFO: End of Forums FVT_Level_2 testMarkingForumsStoriesAsSavedPart2");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingForumsStoriesAsSavedPart2" }, groups={"saveandremove"} )
	public void testMarkingForumsStoriesAsSavedPart3() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testMarkingForumsStoriesAsSavedPart3");
	
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones451)
		Login(testUser2);

		// Start Forums FVT (Level 2) teststandaloneForumReply
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);

		// Go to topic
		openForum(part1ForumName);
		
		clickLink("link=" + part1TopicTitle);

		// Reply to topic
		replyToTopic(part1TopicTitle, part1ForumName);

		// Go to main page of forum created above
		clickLink("link=" + part1ForumName);

		// Verify that the count of replies to the topic created above is
		// exactly '1' (only the reply just created).
		Assert.assertTrue("FAIL: Number of replies not equal to '1'", driver.getSingleElement(FVT_ForumsObjects.First_Topic_Number_of_Replies).getText().compareToIgnoreCase("1") == 0);

		// Logout of Connections
		LogoutAndClose();
		
		////driver.close();
		
		//Save the forums news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(testUser1);

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Forums");
		
		//Replace news story string
		String MyNotForumNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_CREATE_REPLY, part1TopicTitle, part1ForumName, null);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String ForumTopicReplyStory = "css=div.lotusPostContent:contains("+MyNotForumNewsStory+")";
		
		builder.moveToElement((WebElement) driver.getFirstElement(ForumTopicReplyStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//Check that the story saved 
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_CREATE_REPLY, part1TopicTitle, part1ForumName, null); //changed text string 01/09/12
		
		String ForumTopicReplyStory2 = "css=div.lotusPostContent:contains("+ForumNewsStory+")";
		
		driver.getSingleElement("css=div.lotusPostContent:contains("+ForumNewsStory+")").hover();
	
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']:nth(0)"));
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(ForumNewsStory));
		
		//Check that the story deletes correctly
		
		filterBy("Forums");
		
		builder.moveToElement((WebElement) driver.getFirstElement(ForumTopicReplyStory2).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Forums");
		
		driver.getSingleElement(ForumTopicReplyStory).hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']:nth(0)"));
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Forums FVT_Level_2 testMarkingForumsStoriesAsSavedPart3");
		
	}
	
	@Test (groups={"replyafter"})
	public void testMarkingForumsStoriesAsSaved_ReplyAfter() throws Exception {
			
		testUser3 = userAllocator.getUser(this);

		System.out.println("INFO: Start of Communities Level 2 testMarkingForumsStoriesAsSaved_ReplyAfter");	
		
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(testUser3);

		// Start Forums FVT (Level 2) testCreateStandaloneForum
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Start a forum
		String forumRandomNumber = genDateBasedRandVal();
		ForumName2 = FVT_ForumsData.Start_A_Forum_InputText_Name_Data + forumRandomNumber;
		startAForum(ForumName2);
			
		// Go to forum
		//clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		//clickLink("link=" + ForumName2);
		
		// Start a topic
		
		String topicRandomNumber = genDateBasedRandVal();
		TopicTitle2 = FVT_ForumsData.Start_A_Topic_InputText_Title_Data + topicRandomNumber;
		startATopic(TopicTitle2);
		
		//
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);

		filterBy("Forums");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String ForumTopicStory = "css=div.lotusPostContent:contains('created a topic named "+TopicTitle2+" in the "+ForumName2+" forum.')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(ForumTopicStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();

		
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Forums");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a topic named "+TopicTitle2+" in the "+ForumName2+" forum.')").hover();
		
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']:nth(0)"));
		assertTrue("Fail: Link still there!!", driver.getFirstElement("css=div.activityStreamNewsItemContainer").isTextPresent("Saved"));		
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a topic named "+TopicTitle2+" in the "+ForumName2+" forum."));
		
		//Logout of Connections
		LogoutAndClose();	
	
			
		System.out.println("INFO: End of Forums FVT_Level_2 testMarkingForumsStoriesAsSaved_ReplyAfter");
			
	}
	
	
	@Test (dependsOnMethods = { "testMarkingForumsStoriesAsSaved_ReplyAfter" } , groups={"replyafter"})
	public void testMarkingForumsStoriesAsSaved_ReplyAfter2() throws Exception {
			
			
		System.out.println("INFO: Start of Communities Level 2 testMarkingForumsStoriesAsSaved_ReplyAfter2");	
		
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(testUser3);

		// Go to topic
		openForum(ForumName2);
		clickLink("link=" + TopicTitle2);

		// Reply to topic
		replyToTopic(TopicTitle2, ForumName2);

		// Go to main page of forum created above
		clickLink("link=" + ForumName2);

		// Verify that the count of replies to the topic created above is
		// exactly '1' (only the reply just created).
		Assert.assertTrue("FAIL: Number of replies not equal to '1'", driver.getSingleElement(FVT_ForumsObjects.First_Topic_Number_of_Replies).getText().compareToIgnoreCase("1") == 0);
		
		//Check that discover now contains reply story
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);

		filterBy("Forums");
		
		sleep(2000);
		
		//Replace news story string
		String ForumReplyNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_CREATE_REPLY, TopicTitle2, ForumName2 , null);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(ForumReplyNewsStory));

		//Check that the original story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a topic named "+TopicTitle2+" in the "+ForumName2+" forum."));
		
		//Logout of Connections
		LogoutAndClose();	
			
		System.out.println("INFO: End of Forums FVT_Level_2 testMarkingForumsStoriesAsSaved_ReplyAfter2");
			
	}

}