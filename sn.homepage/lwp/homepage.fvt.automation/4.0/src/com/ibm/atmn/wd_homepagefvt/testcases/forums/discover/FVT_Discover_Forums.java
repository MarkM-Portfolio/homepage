/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential */
/*                                                                   */
/* OCO Source Materials */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013 */
/*                                                                   */
/* The source code for this program is not published or otherwise */
/* divested of its trade secrets, irrespective of what has been */
/* deposited with the U.S. Copyright Office. */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.forums.discover;


import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;


public class FVT_Discover_Forums extends FVT_ForumsMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String part1ForumName = "";
	private static String part1TopicTitle = "";

	@Test
	public void testCreateStandaloneForum() throws Exception {

		System.out.println("INFO: Start of Forums Test testCreateStandaloneForum");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		// Login as a user (ie. Amy Jones450)
		Login(testUser1);

		// Start Forums FVT (Level 2) testCreateStandaloneForum
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Start a forum
		String forumRandomNumber = genDateBasedRandVal();
		part1ForumName = FVT_ForumsData.Start_A_Forum_InputText_Name_Data + forumRandomNumber;
		
		startAForum(part1ForumName);

		// Logout of Connections
		LogoutAndClose();
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_FORUM, part1ForumName, null, null);
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);

		System.out.println("INFO: End of Forums Test testCreateStandaloneForum");

	}
	
	
	@Test (dependsOnMethods = { "testCreateStandaloneForum" })
	public void testUpdateStandaloneForum() throws Exception {

		System.out.println("INFO: Start of Forums Test testUpdateStandaloneForum");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(testUser1);

		// Start Forums FVT (Level 2) testUpdateStandaloneForum
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Go to forum
		openForum(part1ForumName);
	
		// Update a forum
		updateAForum();

		// Logout of Connections
		LogoutAndClose();
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.UPDATE_FORUM, part1ForumName, null, null);
		
		//Verify story in news feed
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);

		System.out.println("INFO: End of Forums Test testUpdateStandaloneForum");

	}
	
	@Test (dependsOnMethods = { "testUpdateStandaloneForum" })
	public void testCreateStandaloneForumTopic() throws Exception {

		System.out.println("INFO: Start of Forums Test testCreateStandaloneForumTopic");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(testUser1);

		// Go to forum
		openForum(part1ForumName);
		
		// Start a topic
		String topicRandomNumber = genDateBasedRandVal();
		
		part1TopicTitle = FVT_ForumsData.Start_A_Topic_InputText_Title_Data + topicRandomNumber;

		startATopic(part1TopicTitle);

		// Logout of Connections
		LogoutAndClose();
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_TOPIC, part1TopicTitle, part1ForumName, null);

		//Verify story in news feed
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);
		
		System.out.println("INFO: End of Forums Test testCreateStandaloneForumTopic");

	}

	@Test (dependsOnMethods = { "testCreateStandaloneForumTopic" })
	public void teststandaloneForumReply() throws Exception {

		System.out.println("INFO: Start of Forums Test teststandaloneForumReply");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(testUser1);

		// Go to topic
		openForum(part1ForumName);
		
		clickLink("link=" + part1TopicTitle);

		// Reply to topic
		replyToTopic(part1TopicTitle, part1ForumName);

		// Logout of Connections
		LogoutAndClose();
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_REPLY, part1TopicTitle, part1ForumName, null);
		
		//Verify story in news feed
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);

		System.out.println("INFO: End of Forums Test teststandaloneForumReply");

	} 

}
