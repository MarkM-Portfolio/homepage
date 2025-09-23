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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;
import static org.testng.AssertJUnit.*;


public class FVT_Discover_Private_Comm_Forums extends FVT_ForumsMethods {

	private static String PrivateForumCommunity = "";
	private static String PrivateCommForum = "";
	private static String PrivateCommTopic = "";
	
	
@Test
public void testForumCreated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with Private access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a Private community
		PrivateForumCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
		//Open Forums
		clickLink(FVT_CommunitiesObjects.LeftNavForums);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Create forum name
		PrivateCommForum = "FVT Private Community Forum "+genDateBasedRandVal();
		
		//Start a forum
		startAForum(PrivateCommForum);
		
		//Logout
		LogoutAndClose();
	
		//Replace news story string
		String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_FORUM, PrivateCommForum, null, null);
		
		//Verify news story appears in AS->Discover
		verifyNewsStory(ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, false);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_PrivateComm");
		
	}
	
@Test (dependsOnMethods = { "testForumCreated_PrivateComm" })
public void testForumUpdated_PrivateComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_PrivateComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
	assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
	
	//Open forums in community
	openCommunity(PrivateForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
	
	//Forums tab
	clickLink(FVT_ForumsObjects.Forums_Tab);
	
	//Open forum
	clickLink("link=" + PrivateCommForum);
	
	//Update the forum
	updateAForum();
	
	//Logout
	LogoutAndClose();

	//Replace news story string
	String ForumNewsStory = replaceNewsStory(NewsStoryData.UPDATE_FORUM, PrivateCommForum, null, null);
	
	//Verify news story does not appear in AS->Discover
	verifyNewsStory(ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, false);
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_PrivateComm");
	
}

@Test (dependsOnMethods = { "testForumUpdated_PrivateComm" })
public void testForumTopicCreated_PrivateComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_PrivateComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
	assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
	
	//Open forums in community
	openCommunity(PrivateForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
	
	//Forums tab
	clickLink(FVT_ForumsObjects.Forums_Tab);
	
	//Open forum
	clickLink("link=" + PrivateCommForum);
	
	//Create topic name
	PrivateCommTopic = "FVT Public Community Forum Topic"+genDateBasedRandVal();
	
	//Start a topic
	startATopic(PrivateCommTopic);
	
	//Logout
	LogoutAndClose();

	//Replace news story string
	String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_TOPIC, PrivateCommTopic, PrivateCommForum, null);
	
	//Verify news story does not appear in AS->Discover
	verifyNewsStory(ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, false);	
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_PrivateComm");
	
}
	
@Test (dependsOnMethods = { "testForumTopicCreated_PrivateComm" })
public void testForumTopicReply_PrivateComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_PrivateComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
	assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
	
	//Open forums in community
	openCommunity(PrivateForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
	
	//Open topic
	clickLink("link=" + PrivateCommTopic);
	
	//Reply to the topic
	replyToTopic(PrivateCommTopic, PrivateCommForum);
	
	//Logout
	LogoutAndClose();
	
	//Replace news story string
	String ForumNewsStory = replaceNewsStory(NewsStoryData.CREATE_REPLY, PrivateCommTopic, PrivateCommForum, null);
	
	//Verify news story does not appear in AS->Discover
	verifyNewsStory(ForumNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.FORUMS, false);	
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_PrivateComm");
	
}

}
