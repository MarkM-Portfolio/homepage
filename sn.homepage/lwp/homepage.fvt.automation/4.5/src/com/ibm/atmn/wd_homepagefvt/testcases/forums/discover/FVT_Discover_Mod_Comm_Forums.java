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

public class FVT_Discover_Mod_Comm_Forums extends FVT_ForumsMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String ModeratedForumCommunity = "";
	private static String ModeratedCommForum = "";
	private static String ModeratedCommTopic = "";
	
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Forums FVT_Level_2 testLoginUsers");
		
	}
	
@Test (dependsOnMethods = { "testLoginUsers" })
public void testForumCreated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedForumCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
				
		//Open Forums
		clickLink(FVT_CommunitiesObjects.LeftNavForums);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Create forum name
		ModeratedCommForum = "FVT Moderated Community Forum "+genDateBasedRandVal();
		
		//Start a forum
		startAForum(ModeratedCommForum);
		
		//Logout
		LogoutAndClose();
	
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_FORUM, ModeratedCommForum, null, null);
		
		verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_ModComm");
		
	}
	
@Test (dependsOnMethods = { "testForumCreated_ModComm" })
public void testForumUpdated_ModComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_ModComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(testUser1);
	
	assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
	
	//Open Community
	openCommunity(ModeratedForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
	
	//Forums tab
	clickLink(FVT_ForumsObjects.Forums_Tab);
	
	//Open forum
	clickLink("link=" + ModeratedCommForum);
	
	//Update the forum
	updateAForum();
	
	//Logout
	LogoutAndClose();
	
	//Replace news story string
	String ForumNewsStory = replaceNewsStory(NewsStoryData.UPDATE_FORUM, ModeratedCommForum, null, null);
	
	verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_ModComm");
	
}

@Test (dependsOnMethods = { "testForumUpdated_ModComm" })
public void testForumTopicCreated_ModComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_ModComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(testUser1);
	
	assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
	
	//Open Community
	openCommunity(ModeratedForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
	
	//Forums tab
	clickLink(FVT_ForumsObjects.Forums_Tab);
	
	//Open forum
	clickLink("link=" + ModeratedCommForum);
	
	//Create topic name
	ModeratedCommTopic = "FVT Moderated Community Forum Topic"+genDateBasedRandVal();
	
	//Start a topic
	startATopic(ModeratedCommTopic);
	
	//Logout
	LogoutAndClose();
	
	//Replace news story string
	String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_TOPIC, ModeratedCommTopic, ModeratedCommForum, null);
	
	//Verify news story appears in AS->Discover
	verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);	
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_ModComm");
	
}

@Test (dependsOnMethods = { "testForumTopicCreated_ModComm" })
public void testForumTopicReply_ModComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_ModComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(testUser1);
	
	assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
	
	//Open Community
	openCommunity(ModeratedForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
	
	//Open topic
	clickLink("link=" + ModeratedCommTopic);
	
	//Reply to the topic
	replyToTopic(ModeratedCommTopic, ModeratedCommForum);
	
	//Logout
	LogoutAndClose();
	
	//Replace news story string
	String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_REPLY, ModeratedCommTopic, ModeratedCommForum, null);

	verifyNewsStory(testUser2, ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, true);
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_ModComm");
	
}

}
