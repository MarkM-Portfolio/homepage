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

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_People_Mod_Comm_Forums extends FVT_ForumsMethods {

	private static String ModeratedForumCommunity = "";
	private static String ModeratedCommForum = "";
	private static String ModeratedCommTopic = "";
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testLoginUsers");

		LoadComponent(CommonObjects.ComponentProfiles);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser1);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();

		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser3);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Forums FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreate_ModComm() throws Exception {
			
			
			System.out.println("INFO: Start of Forums FVT_Level_2 testCreate_ModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with moderated access
	
			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a moderated community
			ModeratedForumCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
			
			//Logout
			Logout();
			
			System.out.println("INFO: End of Forums FVT_Level_2 testCreate_ModComm");
			
	}
	
	@Test (dependsOnMethods = { "testCreate_ModComm" })
	public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
		
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		FollowPerson(testUser1.getEmail());
				
		System.out.println("INFO: End of Forums FVT_Level_2 testFollowUser");
		
	}
	
	
	@Test (dependsOnMethods = { "testFollowUser" })
	public void testForumCreated_ModComm() throws Exception {
			
			
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_ModComm");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
			
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		openCommunity(ModeratedForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
			
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
			
		//Create forum name
		ModeratedCommForum = "FVT Moderated Community Forum "+genDateBasedRandVal();
			
		//Start a forum
		startAForum(ModeratedCommForum);
			
		//Logout
		Logout();
			
		driver.close();
			
		verifyNewsStory_ThreeFilters(testUser2," created the "+ModeratedCommForum+" forum.","I'm Following","Communities","People","Forums", true);
			
		System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_ModComm");
			
	}
	
	@Test (dependsOnMethods = { "testForumCreated_ModComm" })
	public void testForumUpdated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
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
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters(testUser2," updated the "+ModeratedCommForum+" forum.","I'm Following","Communities","People","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumUpdated_ModComm" })
	public void testForumTopicCreated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
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
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyNewsStory_ThreeFilters(testUser2," created a topic named "+ModeratedCommTopic+" in the "+ModeratedCommForum+" forum.","I'm Following","Communities","People","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumTopicCreated_ModComm" })
	public void testForumTopicReply_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + ModeratedCommTopic);
		
		//Reply to the topic
		replyToTopic(ModeratedCommTopic, ModeratedCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters(testUser2,"replied to the Re: "+ModeratedCommTopic+" topic thread in the "+ModeratedCommForum+" forum.","I'm Following","Communities","People","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_ModComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testForumTopicReply_ModComm" })
	public void testForumTopicUpdated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicUpdated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + ModeratedCommTopic);
		
		//Update to the topic
		updateTopic(ModeratedCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters(testUser2," updated the "+ModeratedCommTopic+" topic in the "+ModeratedCommForum+" forum.","I'm Following","Communities","People","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicUpdated_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumTopicUpdated_ModComm" })
	public void testForumTopicReplyUpdated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReplyUpdated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + ModeratedCommTopic);
		
		//Update to the reply
		updateReply2(ModeratedCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters(testUser2," updated the "+ModeratedCommTopic+" topic thread in the "+ModeratedCommForum+" forum.","I'm Following","Communities","People","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReplyUpdated_ModComm");
		
	}

}
