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

import org.junit.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.forums.FVT_ForumsMethods;


public class FVT_ImFollowing_People_Forums extends FVT_ForumsMethods {

	private static String part1ForumName = "";
	private static String part1TopicTitle = "";
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser1);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
				
		System.out.println("INFO: End of Forums FVT_Level_2 testLoginUsers");
		
	}
	
@Test (dependsOnMethods = { "testLoginUsers" })
	public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Forums FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
		
		//Login(testUser1);
		Login(testUser2);
		
		FollowPerson(testUser1.getEmail());
				
		System.out.println("INFO: End of Forums FVT_Level_2 testFollowUser");
		
	}
	
	
	@Test (dependsOnMethods = { "testFollowUser" })
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

		// Logout of Connections
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(testUser2," created the "+part1ForumName+" forum.","I'm Following","Forums","People", true);
	
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
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);
		//clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		clickLink(FVT_ForumsObjects.ForumsTopTab);

		// Go to forum
		openForum(part1ForumName);
	
		// Update a forum
		updateAForum();
		
		// Logout of Connections
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(testUser2," updated the "+part1ForumName+" forum.","I'm Following","Forums","People", true);

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

		// Go to forum
		openForum(part1ForumName);
		
		// Start a topic
		String topicRandomNumber = genDateBasedRandVal();
		part1TopicTitle = FVT_ForumsData.Start_A_Topic_InputText_Title_Data + topicRandomNumber;
		
		startATopic(part1TopicTitle);

		// Logout of Connections
		Logout();
		
		driver.close();

		verifyNewsStory_TwoFilters(testUser2," created a topic named "+part1TopicTitle+" in the "+part1ForumName+" forum.","I'm Following","Forums","People", true);
		
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

		// Go to topic
		openForum(part1ForumName);
		
		clickLink("link=" + part1TopicTitle);

		// Reply to topic
		replyToTopic(part1TopicTitle, part1ForumName);

		// Go to main page of forum created above
		clickLink("link=" + part1ForumName);

		// Verify that the count of replies to the topic created above is
		// exactly '1' (only the reply just created).
		Assert.assertTrue("FAIL: Number of replies not equal to '1'", driver.getSingleElement(FVT_ForumsObjects.First_Topic_Number_of_Replies).getText().compareToIgnoreCase("1") == 0);

		// Logout of Connections
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(testUser2,"replied to the Re: "+part1TopicTitle+" topic thread in the "+part1ForumName+" forum.","I'm Following","Forums","People", true);

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
		//clickBannerLink(CommonObjects.Banner_Apps_Forums);
		//clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		clickLink(FVT_ForumsObjects.ForumsTopTab);
		
		// Go to topic
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		clickLink("link=" + part1ForumName);
		
		clickLink("link=" + part1TopicTitle);
	
		// Update topic
		updateTopic(part1ForumName);

		// Logout of Connections
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(testUser2," updated the "+part1TopicTitle+" topic in the "+part1ForumName+" forum.","I'm Following","Forums","People", true);

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
		clickLink(FVT_ForumsObjects.ForumsTopTab);
		
		// Go to topic
		openForum(part1ForumName);
		clickLink("link=" + part1TopicTitle);
	
		// Update topic
		updateReply(part1ForumName);

		// Logout of Connections
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(testUser2," updated the "+part1TopicTitle+" topic thread in the "+part1ForumName+" forum.","I'm Following","Forums","People", true);

		System.out.println("INFO: End of Forums Test testUpdateStandaloneForumTopicReply");

	}


}
