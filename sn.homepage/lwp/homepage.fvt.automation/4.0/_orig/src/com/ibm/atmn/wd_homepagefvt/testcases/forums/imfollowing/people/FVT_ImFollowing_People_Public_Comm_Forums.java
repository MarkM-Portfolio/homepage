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

package com.ibm.atmn.wd_homepagefvt.testcases.forums.imfollowing.people;


import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_People_Public_Comm_Forums extends FVT_ForumsMethods {

	private static String PublicForumCommunity = "";
	private static String PublicCommForum = "";
	private static String PublicCommTopic = "";
	
	
@Test 	
public void testCreate_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with Public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a Public community
		PublicForumCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
		//Logout
		Logout();
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_PublicComm");
		
	}


@Test (dependsOnMethods = { "testForumCreated_PublicComm" })
public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		FollowPerson(CommonData.IC_LDAP_Username_Fullname450);
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testFollowUser");
		
	}
	
	
@Test (dependsOnMethods = { "testFollowUser" })
public void testForumCreated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(PublicForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Create forum name
		PublicCommForum = "FVT Public Community Forum "+genDateBasedRandVal();
		
		//Start a forum
		startAForum(PublicCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters(" created the "+PublicCommForum+" forum.","I'm Following","Communities","People","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_PublicComm");
		
	}


public void testForumUpdated_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyNewsStory_ThreeFilters(" updated the "+PublicCommForum+" forum.","I'm Following","Communities","People","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_PublicComm");
		
	}

@Test (dependsOnMethods = { "testForumUpdated_PublicComm" })
public void testForumTopicCreated_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyNewsStory_ThreeFilters(" created a topic named "+PublicCommTopic+" in the "+PublicCommForum+" forum.","I'm Following","Communities","People","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_PublicComm");
		
	}
	
@Test (dependsOnMethods = { "testForumTopicCreated_PublicComm" })
public void testForumTopicReply_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + PublicCommTopic);
		
		//Reply to the topic
		replyToTopic(PublicCommTopic, PublicCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyNewsStory_ThreeFilters("commented on the Re: "+PublicCommTopic+" topic thread in the "+PublicCommForum+" forum:","I'm Following","Communities","People","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_PublicComm");
		
	}

@Test (dependsOnMethods = { "testForumTopicReply_PublicComm" })
public void testForumTopicUpdated_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + PublicCommTopic);
		
		//Update to the topic
		updateTopic(PublicCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyNewsStory_ThreeFilters(" updated the "+PublicCommTopic+" topic in the "+PublicCommForum+" forum.","I'm Following","Communities","People","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicUpdated_PublicComm");
		
	}

@Test (dependsOnMethods = { "testForumTopicUpdated_PublicComm" })
public void testForumTopicReplyUpdated_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReplyUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + PublicCommTopic);
		
		//Update to the topic
		updateTopic(PublicCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyNewsStory_ThreeFilters(" updated the "+PublicCommTopic+" topic thread in the "+PublicCommForum+" forum.","I'm Following","Communities","People","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReplyUpdated_PublicComm");
		
	}

}
