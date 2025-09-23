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

package com.ibm.atmn.homepagefvt.testcases.forums;


import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.homepagefvt.tasks.forums.FVT_ForumsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Level2_Public_Comm_Forums extends FVT_ForumsMethods {

	private static String PublicForumCommunity = "";
	private static String PublicCommForum = "";
	private static String PublicCommTopic = "";
	
	
@Test 	
public void testForumCreated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with Public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a Public community
		PublicForumCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
		//Open Forums
		clickLink("link=Forums");
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Create forum name
		PublicCommForum = "FVT Public Community Forum "+genDateBasedRandVal();
		
		//Start a forum
		StartAForum(PublicCommForum);
		
		//Logout
		Logout();
		
		//Verify news story appears in AS->Discover
		VerifyNewsStory(" created the "+PublicCommForum+" forum.","Discover","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_PublicComm");
		
	}
	
@Test (dependsOnMethods = { "testForumCreated_PublicComm" })
public void testForumUpdated_ModComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		//Click I'm an Owner in left nav
		clickLink("link=I'm an Owner");
		
		//Open moderated community
		clickLink("link=" + PublicForumCommunity);
				
		//Open Forums
		clickLink("link=Forums");
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Open forum
		clickLink("link=" + PublicCommForum);
		
		//Update the forum
		UpdateAForum();
		
		//Logout
		Logout();
		
		//Verify news story appears in AS->Discover
		VerifyNewsStory(" updated the "+PublicCommForum+" forum.","Discover","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_PublicComm");
		
	}

@Test (dependsOnMethods = { "testForumUpdated_ModComm" })
public void testForumTopicCreated_ModComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		//Click I'm an Owner in left nav
		clickLink("link=I'm an Owner");
		
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
		StartATopic(PublicCommTopic);
		
		//Logout
		Logout();
		
		//Verify news story appears in AS->Discover
		VerifyNewsStory(" created a new topic named "+PublicCommTopic+" in the "+PublicCommForum+" forum.","Discover","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_PublicComm");
		
	}
	
@Test (dependsOnMethods = { "testForumTopicCreated_ModComm" })
public void testForumTopicReply_ModComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		//Click I'm an Owner in left nav
		clickLink("link=I'm an Owner");
		
		//Open moderated community
		clickLink("link=" + PublicForumCommunity);
				
		//Open Forums
		clickLink("link=Forums");
		
		//Open topic
		clickLink("link=" + PublicCommTopic);
		
		//Reply to the topic
		ReplyToTopic(PublicCommTopic, PublicCommForum);
		
		//Logout
		Logout();
		
		//Verify news story appears in AS->Discover
		VerifyNewsStory(" replied to their own Re: "+PublicCommTopic+" topic in the "+PublicCommForum+" forum.","Discover","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_PublicComm");
		
	}

}
