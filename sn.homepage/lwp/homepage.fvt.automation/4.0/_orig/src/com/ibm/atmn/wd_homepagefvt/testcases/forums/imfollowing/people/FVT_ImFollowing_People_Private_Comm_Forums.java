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

import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;
import static org.testng.AssertJUnit.*;


public class FVT_ImFollowing_People_Private_Comm_Forums extends FVT_ForumsMethods {

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
			PrivateForumCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.fullUserSearchIdentifier);
					
			//Open Forums
			clickLink(FVT_CommunitiesObjects.LeftNavForums);
			
			//Forums tab
			clickLink(FVT_ForumsObjects.Forums_Tab);
			
			//Create forum name
			PrivateCommForum = "FVT Private Community Forum "+genDateBasedRandVal();
			
			//Start a forum
			startAForum(PrivateCommForum);
			
			//Logout
			Logout();
		
			System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_PrivateComm");
			
		}
	
	@Test (dependsOnMethods = { "testForumCreated_PrivateComm" })
	public void testFollowPrivateCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Communities FVT_Level_2 testFollowPrivateCommunity");
	
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.ImAMember);
			
			clickLink("link="+PrivateForumCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			clickLink(FVT_HomepageObjects.Home);
			
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created a community named "+PrivateForumCommunity+"."));
			
			filterBy("Forums");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",!driver.isTextPresent(" created a community named "+PrivateForumCommunity+"."));
			
			//Logout
			Logout();
			
			System.out.println("INFO: End of Communities FVT_Level_2 testFollowPrivateCommunity");
			
		}
		
	@Test (dependsOnMethods = { "testForumCreated_PrivateComm" })
	public void testForumUpdated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Open forum
		clickLink("link=" + PrivateCommForum);
		
		//Update the forum
		updateAForum();
		
		//Logout
		Logout();
		
		driver.close();
		
		//Verify news story does not appear in AS->Discover
		verifyImFollowingNewsStory(" updated the "+PrivateCommForum+" forum.","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumUpdated_PrivateComm" })
	public void testForumTopicCreated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
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
		Logout();
		
		driver.close();
		
		//Verify news story does not appear in AS->Discover
		verifyNewsStory(" created a topic named "+PrivateCommTopic+" in the "+PrivateCommForum+" forum.","I'm Following","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_PrivateComm");
		
	}
		
	@Test (dependsOnMethods = { "testForumTopicCreated_PrivateComm" })
	public void testForumTopicReply_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + PrivateCommTopic);
		
		//Reply to the topic
		replyToTopic(PrivateCommTopic, PrivateCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		//Verify news story does not appear in AS->Discover
		verifyNewsStory("commented on the Re: "+PrivateCommTopic+" topic thread in the "+PrivateCommForum+" forum.","I'm Following","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumTopicReply_PrivateComm" })
	public void testForumTopicUpdated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + PrivateCommTopic);
		
		//Update to the topic
		updateTopic(PrivateCommForum);
		//Logout
		Logout();
		
		driver.close();
		
		//Verify news story does not appear in AS->Discover
		verifyNewsStory(" updated the "+PrivateCommTopic+" topic in the "+PrivateCommForum+" forum.","I'm Following","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicUpdated_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumTopicReply_PrivateComm" })
	public void testForumReplyUpdated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumReplyUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateForumCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavForums);
		
		//Open topic
		clickLink("link=" + PrivateCommTopic);
		
		//Update to the reply
		updateReply(PrivateCommForum);
		
		//Logout
		Logout();
		
		driver.close();
		
		//Verify news story does not appear in AS->Discover
		verifyNewsStory(" updated the "+PrivateCommTopic+" topic thread in the "+PrivateCommForum+" forum.","I'm Following","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumReplyUpdated_PrivateComm");
		
	}

}
