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

import org.junit.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;




public class FVT_ImFollowing_Tags_Forums extends FVT_ForumsMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String part1ForumName = "";
	private static String part1TopicTitle = "";
	
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
			
			//Follow Tag
			followTag(CommonData.AutomationTag);
			
			//Logout
			LogoutAndClose();
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowTag" })
	public void testCreateStandaloneForum() throws Exception {

		System.out.println("INFO: Start of Forums Test testCreateStandaloneForum");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		// Start Forums FVT (Level 2) testCreateStandaloneForum
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Start a forum
		String forumRandomNumber = genDateBasedRandVal();
		part1ForumName = FVT_ForumsData.Start_A_Forum_InputText_Name_Data + forumRandomNumber;
		startAForum(part1ForumName);
		
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " created the "+part1ForumName+" forum.","Forums","automation",false, true);

		System.out.println("INFO: End of Forums Test testCreateStandaloneForum");

	}
	
	
	@Test (dependsOnMethods = { "testCreateStandaloneForum" })
	public void testUpdateStandaloneForum() throws Exception {

		System.out.println("INFO: Start of Forums Test testUpdateStandaloneForum");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		// Start Forums FVT (Level 2) testUpdateStandaloneForum
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Go to forum
		openForum(part1ForumName);
	
		// Update a forum
		updateAForum();
		
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+part1ForumName+" forum.","Forums","automation",false, true);

		System.out.println("INFO: End of Forums Test testUpdateStandaloneForum");

	}
	
	@Test (dependsOnMethods = { "testUpdateStandaloneForum" })
	public void testCreateStandaloneForumTopic() throws Exception {

		System.out.println("INFO: Start of Forums Test testCreateStandaloneForumTopic");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		// Start Forums FVT (Level 2) testCreateStandaloneForumTopic
		//clickLink(FVT_ForumsObjects.ForumsTopTab);
		
		// Go to forum
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		clickLink("link=" + part1ForumName);
		
		// Start a topic
		String topicRandomNumber = genDateBasedRandVal();
		part1TopicTitle = FVT_ForumsData.Start_A_Topic_InputText_Title_Data + topicRandomNumber;
		startATopic(part1TopicTitle);

		//Logout
		LogoutAndClose();

		verifyImFollowingTagsNewsStory(testUser2, " created a topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.","Forums","automation",false, true);
		
		System.out.println("INFO: End of Forums Test testCreateStandaloneForumTopic");

	}

	@Test (dependsOnMethods = { "testCreateStandaloneForumTopic" })
	public void teststandaloneForumReply() throws Exception {

		System.out.println("INFO: Start of Forums Test teststandaloneForumReply");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		// Start Forums FVT (Level 2) teststandaloneForumReply
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);

		// Go to forum
		openForum(part1ForumName);
		clickLink("link=" + part1TopicTitle);

		// Reply to topic
		replyToTopic(part1TopicTitle, part1ForumName);

		// Go to main page of forum created above
		clickLink("link=" + part1ForumName);

		// Verify that the count of replies to the topic created above is
		// exactly '1' (only the reply just created).
		Assert.assertTrue("FAIL: Number of replies not equal to '1'", driver.getSingleElement(FVT_ForumsObjects.First_Topic_Number_of_Replies).getText().compareToIgnoreCase("1") == 0);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "replied to the Re: "+part1TopicTitle+" topic thread in the "+part1ForumName+" forum.","Forums","automation",false, true);

		System.out.println("INFO: End of Forums Test teststandaloneForumReply");

	} 
	
	@Test (dependsOnMethods = { "teststandaloneForumReply" })
	public void testUpdateStandaloneForumTopic() throws Exception {

		System.out.println("INFO: Start of Forums Test testUpdateStandaloneForumTopic");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		// Start Forums FVT (Level 2) testUpdateStandaloneForum
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Go to topic
		openForum(part1ForumName);
		clickLink("link=" + part1TopicTitle);
	
		// Update topic
		updateTopic(part1ForumName);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+part1TopicTitle+" topic in the "+part1ForumName+" forum.","Forums","automation",false, true);

		System.out.println("INFO: End of Forums Test testUpdateStandaloneForumTopic");

	}
	
	@Test (dependsOnMethods = { "testUpdateStandaloneForumTopic" })
	public void testUpdateStandaloneForumTopicReply() throws Exception {

		System.out.println("INFO: Start of Forums Test testUpdateStandaloneForumTopicReply");

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		// Start Forums FVT (Level 2) testUpdateStandaloneForum
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);
		//clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		
		// Go to topic
		openForum(part1ForumName);
		clickLink("link=" + part1TopicTitle);
	
		// Update reply
		updateReply(part1ForumName);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+part1TopicTitle+" topic thread in the "+part1ForumName+" forum.","Forums","automation",false, true);

		System.out.println("INFO: End of Forums Test testUpdateStandaloneForumTopicReply");

	}


}
