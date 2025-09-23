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

package com.ibm.atmn.wd_homepagefvt.testcases.forums.imfollowing.tags;


import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;
import static org.testng.AssertJUnit.*;


public class FVT_ImFollowing_Tags_Private_Comm_Forums extends FVT_ForumsMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PrivateForumCommunity = "";
	private static String PrivateCommForum = "";
	private static String PrivateCommTopic = "";

	
	@Test 
	public void testFollowTag() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowTag");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
				
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
			testUser3 = userAllocator.getUser(this);
			
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_HomepageObjects.Updates);
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Tags");
			
			clickLink(FVT_HomepageObjects.ManageTags);
			
			followTag(CommonData.AutomationTag);
			
			//Logout
			LogoutAndClose();
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
		}
	
	@Test (dependsOnMethods = { "testFollowTag" })
	public void testForumCreated_PrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_PrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
			
			// Created a new community with Private access
	
			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a Private community
			PrivateForumCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
					
			//Open Forums
			clickLink("link=Forums");
			
			//Forums tab
			clickLink(FVT_ForumsObjects.Forums_Tab);
			
			//Create forum name
			PrivateCommForum = "FVT Private Community Forum "+genDateBasedRandVal();
			
			//Start a forum
			startAForum(PrivateCommForum);
			
			//Logout
			LogoutAndClose();
			
			String ImFollowingNewsStory = replaceNewsStory(NewsStoryData.CREATE_FORUM, PrivateCommForum, null, testUser1.getDisplayName());
			
			verifyImFollowingTagsNewsStory(testUser2, ImFollowingNewsStory, FVT_NewsData.FORUMS, CommonData.AutomationTag, true, false);
			//verifyImFollowingTagsNewsStory(testUser2, " created the "+PrivateCommForum+" forum.","Forums","automation", true, false);

			System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_PrivateComm");
			
		}
	
	@Test (dependsOnMethods = { "testForumCreated_PrivateComm" })
	public void testForumUpdated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Click I'm an Owner in left nav
		clickLink("link=I'm an Owner");
		
		//Open Private community
		clickLink("link=" + PrivateForumCommunity);
				
		//Open Forums
		clickLink("link=Forums");
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Open forum
		clickLink("link=" + PrivateCommForum);
		
		//Update the forum
		updateAForum();
		
		//Logout
		LogoutAndClose();
		
		//Verify news story does not appear in AS->Discover
		
		String ImFollowingNewsStory = replaceNewsStory(NewsStoryData.UPDATE_FORUM, PrivateCommForum, null, testUser1.getDisplayName());
		
		verifyImFollowingTagsNewsStory(testUser2, ImFollowingNewsStory, FVT_NewsData.FORUMS, CommonData.AutomationTag, true, false);
		//verifyImFollowingTagsNewsStory(testUser2, " updated the "+PrivateCommForum+" forum.","Forums","automation", true, false);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumUpdated_PrivateComm" })
	public void testForumTopicCreated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
				
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Click I'm an Owner in left nav
		clickLink("link=I'm an Owner");
		
		//Open moderated community
		clickLink("link=" +PrivateForumCommunity);
				
		//Open Forums
		clickLink("link=Forums");
		
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
		
		//Verify news story does not appear in AS->Discover
		String ImFollowingNewsStory = replaceNewsStory(NewsStoryData.CREATE_FORUM, PrivateCommTopic, PrivateCommForum, testUser1.getDisplayName());
		
		verifyImFollowingTagsNewsStory(testUser2, ImFollowingNewsStory, FVT_NewsData.FORUMS, CommonData.AutomationTag, true, false);
		//verifyImFollowingTagsNewsStory(testUser2, " created a topic named "+PrivateCommTopic+" in the "+PrivateCommForum+" forum.","Forums","automation", true, false);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_PrivateComm");
		
	}
		
	@Test (dependsOnMethods = { "testForumTopicCreated_PrivateComm" })
	public void testForumTopicReply_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
				
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Click I'm an Owner in left nav
		clickLink("link=I'm an Owner");
		
		//Open Private community
		clickLink("link=" + PrivateForumCommunity);
				
		//Open Forums
		clickLink("link=Forums");
		
		//Open topic
		clickLink("link=" + PrivateCommTopic);
		
		//Reply to the topic
		replyToTopic(PrivateCommTopic, PrivateCommForum);
		
		//Logout
		LogoutAndClose();
		
		//Verify news story does not appear in AS->Discover
		
		String ImFollowingNewsStory = replaceNewsStory(NewsStoryData.CREATE_REPLY, PrivateCommTopic, PrivateCommForum, testUser1.getDisplayName());
		
		verifyImFollowingTagsNewsStory(testUser2, ImFollowingNewsStory, FVT_NewsData.FORUMS, CommonData.AutomationTag, true, false);
		//verifyImFollowingTagsNewsStory(testUser2, "replied to the Re: "+PrivateCommTopic+" topic thread in the "+PrivateCommForum+" forum.","Forums","automation", true, false);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumTopicReply_PrivateComm" })
	public void testForumTopicUpdated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
				
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Click I'm an Owner in left nav
		clickLink("link=I'm an Owner");
		
		//Open Private community
		clickLink("link=" + PrivateForumCommunity);
				
		//Open Forums
		clickLink("link=Forums");
		
		//Open topic
		clickLink("link=" + PrivateCommTopic);
		
		//Update to the topic
		driver.getFirstElement(FVT_ForumsObjects.Edit_Topic).click();
		String topicRandomNumber = genDateBasedRandVal();
		typeNativeInCkEditor(" "+FVT_ForumsData.Edited_CK_Editor_Textarea_RichText_Data + topicRandomNumber + ". \n\nIn forum... "
				+ PrivateCommForum);
		// Save form
		clickLink(CommonObjects.SaveButton);
		
		//Logout
		LogoutAndClose();
		
		//Verify news story does not appear in AS->Discover
		
		String ImFollowingNewsStory = replaceNewsStory(NewsStoryData.UPDATE_TOPIC, PrivateCommTopic, PrivateCommForum, testUser1.getDisplayName());
		
		verifyImFollowingTagsNewsStory(testUser2, ImFollowingNewsStory, FVT_NewsData.FORUMS, CommonData.AutomationTag, true, false);
		//verifyImFollowingTagsNewsStory(testUser2, " updated the "+PrivateCommTopic+" topic in the "+PrivateCommForum+" forum:","Forums","automation", true, false);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicUpdated_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testForumTopicReply_PrivateComm" })
	public void testForumReplyUpdated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumReplyUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
				
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Click I'm an Owner in left nav
		clickLink("link=I'm an Owner");
		
		//Open Private community
		clickLink("link=" + PrivateForumCommunity);
				
		//Open Forums
		clickLink("link=Forums");
		
		//Open topic
		clickLink("link=" + PrivateCommTopic);
		
		//Update to the reply
		driver.getFirstElement(FVT_ForumsObjects.Edit_Reply2).click();
		typeNativeInCkEditor(" EDITED ");
		// Save form
		clickLink(CommonObjects.SaveButton);
		
		//Logout
		LogoutAndClose();
		
		//Verify news story does not appear in AS->Discover
		String ImFollowingNewsStory = replaceNewsStory(NewsStoryData.UPDATE_TOPIC, PrivateCommTopic, PrivateCommForum, testUser1.getDisplayName());
		
		verifyImFollowingTagsNewsStory(testUser2, ImFollowingNewsStory, FVT_NewsData.FORUMS, CommonData.AutomationTag, true, false);
		//verifyImFollowingTagsNewsStory(testUser2, " updated the "+PrivateCommTopic+" topic thread in the "+PrivateCommForum+" forum:","Forums","automation", true, false);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumReplyUpdated_PrivateComm");
		
	}

}
