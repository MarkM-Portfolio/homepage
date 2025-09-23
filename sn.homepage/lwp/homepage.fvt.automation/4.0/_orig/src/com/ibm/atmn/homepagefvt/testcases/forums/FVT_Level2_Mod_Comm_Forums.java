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

public class FVT_Level2_Mod_Comm_Forums extends FVT_ForumsMethods {

	private static String ModeratedForumCommunity = "";
	private static String ModeratedCommForum = "";
	private static String ModeratedCommTopic = "";
	
	
@Test 
public void testForumCreated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumCreated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedForumCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
		//Open Forums
		clickLink("link=Forums");
		
		//Forums tab
		clickLink(FVT_ForumsObjects.Forums_Tab);
		
		//Create forum name
		ModeratedCommForum = "FVT Moderated Community Forum "+genDateBasedRandVal();
		
		//Start a forum
		StartAForum(ModeratedCommForum);
		
		//Logout
		Logout();
		
		VerifyNewsStory(" created the "+ModeratedCommForum+" forum.","Discover","Forums", true);
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumCreated_ModComm");
		
	}
	
@Test (dependsOnMethods = { "testForumCreated_ModComm" })
public void testForumUpdated_ModComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumUpdated_ModComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
	assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
	
	//Click I'm an Owner in left nav
	clickLink("link=I'm an Owner");
	
	//Open moderated community
	clickLink("link=" + ModeratedForumCommunity);
			
	//Open Forums
	clickLink("link=Forums");
	
	//Forums tab
	clickLink(FVT_ForumsObjects.Forums_Tab);
	
	//Open forum
	clickLink("link=" + ModeratedCommForum);
	
	//Update the forum
	UpdateAForum();
	
	//Logout
	Logout();
	
	VerifyNewsStory(" updated the "+ModeratedCommForum+" forum.","Discover","Forums", true);
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumUpdated_ModComm");
	
}

@Test (dependsOnMethods = { "testForumUpdated_ModComm" })
public void testForumTopicCreated_ModComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicCreated_ModComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
	assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
	
	//Click I'm an Owner in left nav
	clickLink("link=I'm an Owner");
	
	//Open moderated community
	clickLink("link=" + ModeratedForumCommunity);
			
	//Open Forums
	clickLink("link=Forums");
	
	//Forums tab
	clickLink(FVT_ForumsObjects.Forums_Tab);
	
	//Open forum
	clickLink("link=" + ModeratedCommForum);
	
	//Create topic name
	ModeratedCommTopic = "FVT Moderated Community Forum Topic"+genDateBasedRandVal();
	
	//Start a topic
	StartATopic(ModeratedCommTopic);
	
	//Logout
	Logout();
	
	//Verify news story appears in AS->Discover
	VerifyNewsStory(" created a new topic named "+ModeratedCommTopic+" in the "+ModeratedCommForum+" forum.","Discover","Forums", true);	
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicCreated_ModComm");
	
}

@Test (dependsOnMethods = { "testForumTopicCreated_ModComm" })
public void testForumTopicReply_ModComm() throws Exception {
	
	
	System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_ModComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
	assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
	
	//Click I'm an Owner in left nav
	clickLink("link=I'm an Owner");
	
	//Open moderated community
	clickLink("link=" + ModeratedForumCommunity);
			
	//Open Forums
	clickLink("link=Forums");
	
	//Open topic
	clickLink("link=" + ModeratedCommTopic);
	
	//Reply to the topic
	ReplyToTopic(ModeratedCommTopic, ModeratedCommForum);
	
	//Logout
	Logout();
	
	VerifyNewsStory(" replied to their own Re: "+ModeratedCommTopic+" topic in the "+ModeratedCommForum+" forum.","Discover","Forums", true);
	
	System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_ModComm");
	
}

}
