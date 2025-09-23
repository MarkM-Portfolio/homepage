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
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Discover_Public_Comm_Forums extends FVT_ForumsMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PublicForumCommunity = "";
	private static String PublicCommForum = "";
	private static String PublicCommTopic = "";
	
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testLoginUsers");
		
	}
	
@Test (dependsOnMethods = { "testLoginUsers" })
public void testForumCreated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with Public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a Public community
		PublicForumCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead,testUser2.getEmail());
				
		//Open Forums
		clickLink(FVT_CommunitiesObjects.LeftNavForums);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Create forum name
		PublicCommForum = "FVT Public Community Forum "+genDateBasedRandVal();
		
		//Start a forum
		startAForum(PublicCommForum);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_FORUM, PublicCommForum, null, null);
		
		//Verify news story appears in AS->Discover
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_PublicComm");
		
	}
	
@Test (dependsOnMethods = { "testForumCreated_PublicComm" })
public void testForumUpdated_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Open forum
		clickLink("link=" + PublicCommForum);
		
		//Update the forum
		updateAForum();
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.UPDATE_FORUM, PublicCommForum, null, null);
			
		//Verify news story appears in AS->Discover
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_PublicComm");
		
	}

@Test (dependsOnMethods = { "testForumUpdated_PublicComm" })
public void testForumTopicCreated_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Open forum
		clickLink("link=" + PublicCommForum);
		
		//Create topic name
		PublicCommTopic = "FVT Public Community Forum Topic"+genDateBasedRandVal();
		
		//Start a topic
		startATopic(PublicCommTopic);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_TOPIC, PublicCommTopic, PublicCommForum, null);
		
		//Verify news story appears in AS->Discover
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_PublicComm");
		
	}
	
@Test (dependsOnMethods = { "testForumTopicCreated_PublicComm" })
public void testForumTopicReply_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + PublicCommTopic);
		
		//Reply to the topic
		replyToTopic(PublicCommTopic, PublicCommForum);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_REPLY, PublicCommTopic, PublicCommForum, null);
		
		//Verify news story appears in AS->Discover
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_PublicComm");
		
	}

}
