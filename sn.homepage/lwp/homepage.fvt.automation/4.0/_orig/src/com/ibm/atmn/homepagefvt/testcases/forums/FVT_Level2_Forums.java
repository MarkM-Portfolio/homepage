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

import org.junit.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.homepagefvt.tasks.forums.FVT_ForumsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Level2_Forums extends FVT_ForumsMethods {

	private static String part1ForumName = "";
	private static String part1TopicTitle = "";

	@Test
	public void testCreateStandaloneForum() throws Exception {

		System.out.println("INFO: Start of Forums Test testCreateStandaloneForum");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		// Start Forums FVT (Level 2) testCreateStandaloneForum
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);
		//clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Start a forum
		clickLink(FVT_ForumsObjects.Start_A_Forum);
		String forumRandomNumber = genDateBasedRandVal();
		part1ForumName = FVT_ForumsData.Start_A_Forum_InputText_Name_Data + forumRandomNumber;
		sel.type(FVT_ForumsObjects.Start_A_Forum_InputText_Name, part1ForumName);
		sel.type(FVT_ForumsObjects.Start_A_Forum_InputText_Tags, FVT_ForumsData.Start_A_Forum_InputText_Tags_Data
				+ forumRandomNumber);
		sel.type(FVT_ForumsObjects.Start_A_Forum_Textarea_Description, FVT_ForumsData.Start_A_Forum_Textarea_Description_Data);
		clickLink(CommonObjects.SaveButton);

		// For testing without using CK Editor window:
		/*
		 * if(sel.isElementPresent(Objects.Start_A_Topic_InputButton_ConfirmationDialogueOk )){
		 * clickLink(Objects.Start_A_Topic_InputButton_ConfirmationDialogueOk ); }
		 */

		// Go to main page of the forum created above
		//clickLink(FVT_ForumsObjects.My_Forums_Tab);
		clickLink("link=Forums");
		clickLink("link=I'm an Owner");
		clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		// Click link to the page of the forum created above
		clickLink("link=" + part1ForumName);

		// Verify 'add owners' link is present
		assertTrue(sel.isTextPresent("Add Owners"));

		// Logout of Connections
		Logout();
		
		//Verify that news story appears in  Activity Steam -> Discover
		
		VerifyNewsStory(" created the "+part1ForumName+" forum.","Discover","Forums", true);

		System.out.println("INFO: End of Forums Test testCreateStandaloneForum");

	}
	
	
	@Test (dependsOnMethods = { "testCreateStandaloneForum" })
	public void testUpdateStandaloneForum() throws Exception {

		System.out.println("INFO: Start of Forums Test testUpdateStandaloneForum");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		// Start Forums FVT (Level 2) testUpdateStandaloneForum
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);
		//clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		
		// Go to topic
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		clickLink("link=" + part1ForumName);
	
		// Update a forum
		clickLink(FVT_ForumsObjects.Forum_MoreActions);
		clickLink(FVT_ForumsObjects.Edit_Forum);
		
		sel.type(FVT_ForumsObjects.Start_A_Forum_Textarea_Description, FVT_ForumsData.Updated_Forum_Textarea_Description_Data);
		clickLink(CommonObjects.SaveButton);

		// For testing without using CK Editor window:
		/*
		 * if(sel.isElementPresent(Objects.Start_A_Topic_InputButton_ConfirmationDialogueOk )){
		 * clickLink(Objects.Start_A_Topic_InputButton_ConfirmationDialogueOk ); }
		 */

		// Go to main page of the forum created above
		//clickLink(FVT_ForumsObjects.My_Forums_Tab);
		clickLink("link=Forums");
		clickLink("link=I'm an Owner");
		clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		// Click link to the page of the forum created above
		clickLink("link=" + part1ForumName);

		// Verify 'add owners' link is present
		assertTrue(sel.isTextPresent("Add Owners"));

		// Logout of Connections
		Logout();
		
		VerifyNewsStory(" updated the "+part1ForumName+" forum.","Discover","Forums", true);

		System.out.println("INFO: End of Forums Test testUpdateStandaloneForum");

	}
	
	@Test (dependsOnMethods = { "testUpdateStandaloneForum" })
	public void testCreateStandaloneForumTopic() throws Exception {

		System.out.println("INFO: Start of Forums Test testCreateStandaloneForumTopic");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		// Start Forums FVT (Level 2) testCreateStandaloneForumTopic
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);
		//clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		//clickLink(FVT_ForumsObjects.ForumsTopTab);

		
		// Go to forum
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		clickLink("link=" + part1ForumName);
		
		// Start a topic
		clickLink(FVT_ForumsObjects.Start_A_Topic);
		String topicRandomNumber = genDateBasedRandVal();
		part1TopicTitle = FVT_ForumsData.Start_A_Topic_InputText_Title_Data + topicRandomNumber;
		sel.type(FVT_ForumsObjects.Start_A_Topic_InputText_Title, part1TopicTitle);
		if (Math.round(Math.random()) == 0) {
			sel.click(FVT_ForumsObjects.Start_A_Topic_InputCheckbox_MarkAsQuestion);
		}

		sel.type(FVT_ForumsObjects.Start_A_Topic_InputText_Tags, FVT_ForumsData.Start_A_Topic_InputText_Tags_Data
				+ topicRandomNumber);
		// type description
		typeNativeInCkEditor(FVT_ForumsData.CK_Editor_Textarea_RichText_Data + topicRandomNumber + ". \n\nIn forum... "
				+ part1ForumName);
		// Save form
		clickLink(CommonObjects.SaveButton);

		// For testing without using CK Editor window:
		/*
		 * if(sel.isElementPresent(Objects.Start_A_Topic_InputButton_ConfirmationDialogueOk )){
		 * clickLink(Objects.Start_A_Topic_InputButton_ConfirmationDialogueOk ); }
		 */

		// Go to main page of the forum created above
		//clickLink(FVT_ForumsObjects.My_Forums_Tab);
		clickLink("link=Forums");
		clickLink("link=I'm an Owner");
		clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		// Click link to the page of the forum created above
		clickLink("link=" + part1ForumName);

		// Verify 'add owners' link is present
		assertTrue(sel.isTextPresent("Add Owners"));

		// Logout of Connections
		Logout();

		VerifyNewsStory(" created a new topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.","Discover","Forums", true);
		
		System.out.println("INFO: End of Forums Test testCreateStandaloneForumTopic");

	}

	@Test (dependsOnMethods = { "testCreateStandaloneForumTopic" })
	public void teststandaloneForumReply() throws Exception {

		System.out.println("INFO: Start of Forums Test teststandaloneForumReply");

		//if (part1TopicTitle.length() == 0) {
		//	throw new Exception("TEST ERROR: FVT_Level2_Forums testPart1 must be run before testPart2");
		//}

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		// Start Forums FVT (Level 2) teststandaloneForumReply
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);

		// Go to topic
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		clickLink("link=" + part1ForumName);
		clickLink("link=" + part1TopicTitle);

		// Reply to topic
		clickLink(FVT_ForumsObjects.Reply_to_topic);
		// Type text using native keystrokes (typing will occur in focused
		// window)
		typeNativeInCkEditor("Reply to topic... " + part1TopicTitle + ", In forum... " + part1ForumName + ".");
		// Save form
		clickLink(CommonObjects.SaveButton);

		// Go to main page of forum created above
		clickLink("link=" + part1ForumName);

		// Verify that the count of replies to the topic created above is
		// exactly '1' (only the reply just created).
		Assert.assertTrue("FAIL: Number of replies not equal to '1'", sel.getText(
				FVT_ForumsObjects.First_Topic_Number_of_Replies).compareToIgnoreCase("1") == 0);

		// Logout of Connections
		Logout();
		
		VerifyNewsStory(" replied to their own Re: "+part1TopicTitle+" topic in the "+part1ForumName+" forum.","Discover","Forums", true);

		System.out.println("INFO: End of Forums Test teststandaloneForumReply");

	} 

}
