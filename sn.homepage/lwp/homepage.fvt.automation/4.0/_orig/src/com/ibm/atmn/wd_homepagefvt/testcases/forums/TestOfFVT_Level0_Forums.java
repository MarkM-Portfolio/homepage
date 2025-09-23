/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.forums;



import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;


public class TestOfFVT_Level0_Forums extends FVT_ForumsMethods{
	
	private static String PublicForumCommunity = "Public Level 2 FVT Community 1117135921";
	private static String PublicCommForum = "FVT Public Community Forum 1117140050";
	private static String PublicCommTopic = "FVT Public Community Forum Topic1117140435";
	
	@Test
	public void testForumTopicReply_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReply_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyImFollowingNewsStory("The following people replied to their own Re: "+PublicCommTopic+" topic in the "+PublicCommForum+" forum:","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReply_PublicComm");
		
	}

@Test (dependsOnMethods = { "testForumTopicReply_PublicComm" })
public void testForumTopicUpdated_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyImFollowingNewsStory(" updated the "+PublicCommTopic+" topic in the "+PublicCommForum+" forum.","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicUpdated_PublicComm");
		
	}

@Test (dependsOnMethods = { "testForumTopicUpdated_PublicComm" })
public void testForumTopicReplyUpdated_PublicComm() throws Exception {
	
	
		System.out.println("INFO: Start of Forums FVT_Level_2 testForumTopicReplyUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		driver.close();
		
		//Verify news story appears in AS->Discover
		verifyImFollowingNewsStory(" updated the "+PublicCommTopic+" topic in the "+PublicCommForum+" forum.","Forums", true);	
		
		System.out.println("INFO: End of Forums FVT_Level_2 testForumTopicReplyUpdated_PublicComm");
		
	}
}