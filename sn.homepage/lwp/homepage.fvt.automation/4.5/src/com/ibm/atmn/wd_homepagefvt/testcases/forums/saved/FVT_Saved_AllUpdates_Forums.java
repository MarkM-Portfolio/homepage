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


import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;



import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;

/*  
 *  Note : If TTT is found for this test , please append to this comment and 
 *  verify that the tests are matching the scenarios correctly.
 *  
 *  ToDO :> Clean up code and Comment Code
 */

public class FVT_Saved_AllUpdates_Forums extends FVT_ForumsMethods {

	private User testUser1;
	private User testUser2;
	private User testUser3;
	private static String part1ForumName = "";
	private static String part1TopicTitle = "";
	
		
	@Test 
	public void testMarkingForumsStoriesAsSaved_AllUpdates() throws Exception {
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);	
		
		System.out.println("INFO: Start of Communities Level 2 testMarkingForumsStoriesAsSaved_AllUpdates");	
		
		//Create forum
		
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);

		// Start Forums FVT (Level 2) testCreateStandaloneForum
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);
		//clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Start a forum
		clickLink(FVT_ForumsObjects.Start_A_Forum);
		String forumRandomNumber = genDateBasedRandVal();
		part1ForumName = FVT_ForumsData.Start_A_Forum_InputText_Name_Data + forumRandomNumber;
		driver.getFirstElement("css=input#lconn_forums_ForumForm_0_name.lotusText").type(part1ForumName);
		driver.getFirstElement("css=input#lconn_forums_ForumForm_0_tag.lotusText").type(FVT_ForumsData.Start_A_Forum_InputText_Tags_Data+ forumRandomNumber);
		driver.getFirstElement("css=td textarea#lconn_forums_ForumForm_0_description.lotusText").type(FVT_ForumsData.Start_A_Forum_Textarea_Description_Data);
		//driver.type(FVT_ForumsObjects.Start_A_Forum_InputText_Name, part1ForumName);
		//driver.type(FVT_ForumsObjects.Start_A_Forum_InputText_Tags, FVT_ForumsData.Start_A_Forum_InputText_Tags_Data
			//	+ forumRandomNumber);
		//driver.type(FVT_ForumsObjects.Start_A_Forum_Textarea_Description, FVT_ForumsData.Start_A_Forum_Textarea_Description_Data);
		clickLink(CommonObjects.SaveButton);
				
		//Logout of Connections
		LogoutAndClose();
		
		Thread.sleep(3000);
		
		//Follow Forum
		
		LoadComponent(CommonObjects.ComponentForums);
		
		//Log in
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.ForumsTopTab);
		
		//Go to public Forums
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		
		//Go to public Forums
		clickLink("link="+part1ForumName);
		
		//Follow Forum
		clickLink(FVT_ForumsObjects.Follow);
		
		//Follow Drop down
		clickLink(FVT_ForumsObjects.FollowForum);
	
		//Logout
		LogoutAndClose();
			
		//Save forum news story
		
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("All Updates");
			
		//cautiousFocus("css=div.lotusPostContent:contains('created the "+part1ForumName+" forum.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the story is Saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		//cautiousFocus("css=div.lotusPostContent:contains('created the "+part1ForumName+" forum.')");
		
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		assertTrue("Fail: Link is present", driver.getSingleElement("css=div.lotusPostContent div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:nth(0)").isTextPresent("Saved"));	
		
		//Check that the story appears in Saved
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created the "+part1ForumName+" forum."));
			
		//Remove saved news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created the "+part1ForumName+" forum.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created the "+part1ForumName+" forum.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		LogoutAndClose();
					
		System.out.println("INFO: End of Forums FVT_Level_2 testMarkingForumsStoriesAsSaved_AllUpdates");
			
	}
	
	
	
	
	@Test (dependsOnMethods = { "testMarkingForumsStoriesAsSaved_AllUpdates" })
	public void testMarkingForumsStoriesAsSaved2_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testMarkingForumsStoriesAsSaved2_AllUpdates");
	
		//Start a topic 
		
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);

		// Start Forums FVT (Level 2) testCreateStandaloneForumTopic
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);
		//clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		//clickLink(FVT_ForumsObjects.ForumsTopTab);

		
		// Go to forum
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		clickLink("link=" + part1ForumName);
		
		// Start a topic
		clickLink(FVT_ForumsObjects.Start_A_Topic);
		String topicRandomNumber = genDateBasedRandVal();
		part1TopicTitle = FVT_ForumsData.Start_A_Topic_InputText_Title_Data + topicRandomNumber;
		driver.getFirstElement("css=input#lconn_forums_PostForm_0_postTitle.lotusText").type(part1TopicTitle);
		driver.getFirstElement("css=input#lconn_forums_PostForm_0_postTag.lotusText").type(FVT_ForumsData.Start_A_Forum_InputText_Tags_Data+ topicRandomNumber);
		driver.getFirstElement("css=div.lotusFormBody div.lotusFormField label.lotusCheckbox").click();
		//driver.type(FVT_ForumsObjects.Start_A_Topic_InputText_Title, part1TopicTitle);
		//if (Math.round(Math.random()) == 0) {
		//	driver.click(FVT_ForumsObjects.Start_A_Topic_InputCheckbox_MarkAsQuestion);
		//}

		//driver.type(FVT_ForumsObjects.Start_A_Topic_InputText_Tags, FVT_ForumsData.Start_A_Topic_InputText_Tags_Data
		//		+ topicRandomNumber);
		// type description
		//typeNativeInCkEditor(FVT_ForumsData.CK_Editor_Textarea_RichText_Data + topicRandomNumber + ". \n\nIn forum...	+ part1ForumName);
		// Save form

		clickLink(CommonObjects.SaveButton);
		driver.getFirstElement("css=input[value='OK'][role='button']").click();
		
		//Logout of Connections
		LogoutAndClose();	
		
		//Save the forum news story
		
		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('created a new topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the story is saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		//clickLink(FVT_HomepageObjects.ImFollowing);
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.')");
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		driver.getFirstElement("css= div.lotusPostMore ul.lotusInlinelist li.lotusFirst a").click();
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));	
		
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		assertTrue("Fail: Link is present", driver.getSingleElement("css=div.lotusPostContent div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:nth(0)").isTextPresent("Saved"));

		
		//Check that the story appears in Saved 
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a topic named "+part1TopicTitle+" in the "+part1ForumName+" forum."));
		//assertTrue("Fail: text is not present!!", driver.isTextPresent("created a topic named "+part1ForumName+" in the "+part1ForumName+" forum."));
		
		//Delete the news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.')");
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.')");
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		LogoutAndClose();
		
		System.out.println("INFO: End of Forums FVT_Level_2 testMarkingForumsStoriesAsSaved2_AllUpdates");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingForumsStoriesAsSaved2_AllUpdates" })
	public void testMarkingForumsStoriesAsSaved3_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testMarkingForumsStoriesAsSaved3_AllUpdates");
	
		//Create forum content
		
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones451)
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		// Start Forums FVT (Level 2) teststandaloneForumReply
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);

		// Go to topic
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		clickLink("link=" + part1ForumName);
		clickLink("link=" + part1TopicTitle);

		// Reply to topic
		clickLink(FVT_ForumsObjects.Reply_to_topic);
		// Type text using native keystrokes (typing will occur in focused
		// window)
		typeNativeInCkEditor("Reply to topic... " + part1TopicTitle + ", In forum... " + part1ForumName + ".");
		// Save form
		
		clickLink(CommonObjects.SaveButton);

		// Go to main page of forum created above
		clickLink("link=" + part1ForumName);

		// Verify that the count of replies to the topic created above is
		// exactly '1' (only the reply just created).
		//Assert.assertTrue("FAIL: Number of replies not equal to '1'", ((Object) driver).getText(
		//		FVT_ForumsObjects.First_Topic_Number_of_Replies).compareToIgnoreCase("1") == 0);

		// LogoutAndClose() of Connections
		LogoutAndClose();
		
		//Save the forum news story
		
		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains(' replied to you about the Re: "+part1TopicTitle+" topic in the "+part1ForumName+" forum.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.SaveThis);
	
		//Check that the news story saved
		
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.Home);
		
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.Discover);
		
		//cautiousFocus("css=div.lotusPostContent:contains('about the Re: "+part1TopicTitle+" topic in the "+part1ForumName+" forum.')");
	
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		assertTrue("Fail: Link is present", driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").isTextPresent("Saved"));
		
		//Check that the news story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		//assertTrue("Fail: text is not present!!", driver.isTextPresent("about the Re: "+part1TopicTitle+" topic in the "+part1ForumName+" forum."));
		assertTrue("Fail: text is not present!!", driver.isTextPresent("Reply to topic... " + part1TopicTitle + ", In forum... " + part1ForumName + "."));
		
		//Delete the forum news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('about the Re: "+part1TopicTitle+" topic in the "+part1ForumName+" forum.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('replied to you about the Re: "+part1TopicTitle+" topic in the "+part1ForumName+" forum.')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//LogoutAndClose()
		LogoutAndClose();
		
		
		System.out.println("INFO: End of Forums FVT_Level_2 testMarkingForumsStoriesAsSaved3_AllUpdates");
		
	}
	

}