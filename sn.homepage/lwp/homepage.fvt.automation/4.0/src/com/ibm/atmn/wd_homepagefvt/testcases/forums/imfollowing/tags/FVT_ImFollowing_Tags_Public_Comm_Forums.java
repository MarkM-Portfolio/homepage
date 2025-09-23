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
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Tags_Public_Comm_Forums extends FVT_ForumsMethods {

	private static String PublicForumCommunity = "";
	private static String PublicCommForum = "";
	private static String PublicCommTopic = "";
	
	User testUser1;
	User testUser2;
	User testUser3;
	
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
			
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Tags");
			
			clickLink(FVT_HomepageObjects.ManageTags);
			
			followTag(CommonData.AutomationTag);
			
			//Logout
			LogoutAndClose();
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowTag" })	
	public void testForumCreated_PublicComm() throws Exception {
			
			
			System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_PublicComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with Public access
	
			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a Public community
			PublicForumCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
					
			//Open Forums
			clickLink("link=Forums");
			
			//Forums tab
			clickLink(FVT_ForumsObjects.Forums_Tab);
			
			//Create forum name
			PublicCommForum = "FVT Public Community Forum "+genDateBasedRandVal();
			
			//Start a forum
			startAForum(PublicCommForum);
			
			//Logout
			LogoutAndClose();
			
			verifyImFollowingTagsNewsStory(testUser2, " created the "+PublicCommForum+" forum.","Forums","automation", true, true);

			System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_PublicComm");
			
		}
	
	
	@Test (dependsOnMethods = { "testForumCreated_PublicComm" })
	public void testForumUpdated_PublicComm() throws Exception {
		
		
			System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_PublicComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Click I'm an Owner in left nav
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Open moderated community
			clickLink("link=" + PublicForumCommunity);
					
			//Open Forums
			clickLink("link=Forums");
			
			//Forums tab
			clickLink(FVT_ForumsObjects.Forums_Tab);
			
			//Open forum
			clickLink("link=" + PublicCommForum);
			
			//Update the forum
			updateAForum();
			
			//Logout
			LogoutAndClose();
			
			//Verify news story appears in AS->Discover
			verifyImFollowingTagsNewsStory(testUser2, " updated the "+PublicCommForum+" forum.","Forums","automation", true, true);
			
			System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_PublicComm");
			
		}
	
	@Test (dependsOnMethods = { "testForumUpdated_PublicComm" })
	public void testForumTopicCreated_PublicComm() throws Exception {
		
		
			System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_PublicComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Click I'm an Owner in left nav
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Open moderated community
			clickLink("link=" + PublicForumCommunity);
					
			//Open Forums
			clickLink("link=Forums");
			
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
			
			//Verify news story appears in AS->Discover
			verifyImFollowingTagsNewsStory(testUser2, " created a topic named "+PublicCommTopic+" in the "+PublicCommForum+" forum.","Forums","automation", true, true);
			
			System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_PublicComm");
			
		}
		
	@Test (dependsOnMethods = { "testForumTopicCreated_PublicComm" })
	public void testForumTopicReply_PublicComm() throws Exception {
		
		
			System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_PublicComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Click I'm an Owner in left nav
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Open moderated community
			clickLink("link=" + PublicForumCommunity);
					
			//Open Forums
			clickLink("link=Forums");
			
			//Open topic
			clickLink("link=" + PublicCommTopic);
			
			//Reply to the topic
			replyToTopic(PublicCommTopic, PublicCommForum);
			
			//Logout
			LogoutAndClose();
			
			//Verify news story appears in AS->Discover
			verifyImFollowingTagsNewsStory(testUser2, "replied to the Re: "+PublicCommTopic+" topic thread in the "+PublicCommForum+" forum.","Forums","automation", true, true);	
			
			System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_PublicComm");
			
		}
	
	@Test (dependsOnMethods = { "testForumTopicReply_PublicComm" })
	public void testForumTopicUpdated_PublicComm() throws Exception {
		
		
			System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicUpdated_PublicComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Click I'm an Owner in left nav
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Open moderated community
			clickLink("link=" + PublicForumCommunity);
					
			//Open Forums
			clickLink("link=Forums");
			
			//Open topic
			clickLink("link=" + PublicCommTopic);
			
			//Update to the topic
			driver.getFirstElement(FVT_ForumsObjects.Edit_Topic).click();
			String topicRandomNumber = genDateBasedRandVal();
			typeNativeInCkEditor(" "+FVT_ForumsData.Edited_CK_Editor_Textarea_RichText_Data + topicRandomNumber + ". \n\nIn forum... "
					+ PublicCommForum);
			// Save form
			clickLink(CommonObjects.SaveButton);
			
			//Logout
			LogoutAndClose();
			
			//Verify news story appears in AS->Discover
			verifyImFollowingTagsNewsStory(testUser2, " updated the "+PublicCommTopic+" topic in the "+PublicCommForum+" forum.","Forums","automation", true, true);	
			
			System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicUpdated_PublicComm");
			
		}
	
	@Test (dependsOnMethods = { "testForumTopicUpdated_PublicComm" })
	public void testForumTopicReplyUpdated_PublicComm() throws Exception {
		
		
			System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReplyUpdated_PublicComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Click I'm an Owner in left nav
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Open moderated community
			clickLink("link=" + PublicForumCommunity);
					
			//Open Forums
			clickLink("link=Forums");
			
			//Open topic
			clickLink("link=" + PublicCommTopic);
			
			//Update to the topic
			driver.getFirstElement(FVT_ForumsObjects.Edit_Reply).click();
			typeNativeInCkEditor(" EDITED ");
			// Save form
			clickLink(CommonObjects.SaveButton);
			
			//Logout
			LogoutAndClose();
			
			//Verify news story appears in AS->Discover
			verifyImFollowingTagsNewsStory(testUser2, " updated the "+PublicCommTopic+" topic thread in the "+PublicCommForum+" forum.","Forums","automation", true, true);	
			
			System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReplyUpdated_PublicComm");
			
		}

}
