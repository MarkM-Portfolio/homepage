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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.imfollowing.tags;



import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Tags_Mod_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;

	
	private static String ModeratedIdeablogCommunity = "";
	private static String ModeratedIdeablogEntry = "";
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testFollowTag() throws Exception {
			
			
		System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowTag");
			
		// Login to communities
		LoadComponent(CommonObjects.ComponentNews);
				
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
		//Follow Tag
		followTag(CommonData.AutomationTag);
			
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
	}
	
	
	@Test (dependsOnMethods = { "testFollowTag" })
	public void testCreateModComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateModComm");
	
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
		ModeratedIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
	
		//Logout
		Logout();
				
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateModComm" })
	public void testCreateIdeablog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);	
		
		clickLink("link="+ModeratedIdeablogCommunity);
				
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");
		
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " added an Ideation Blog to the "+ModeratedIdeablogCommunity+" community.","Blogs","automation",true, true);
				
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_ModComm");
		
	}
	

	@Test (dependsOnMethods = { "testCreateIdeablog_ModComm" })
	public void testCreateIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
	
		//open community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		// Created a new blog idea
		ModeratedIdeablogEntry = CreateANewIdea(ModeratedIdeablogCommunity + " Blog Idea");

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " created the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_ModComm" },groups = {"notbvt3"})
	public void testUpdateIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_ModComm" },groups = {"notbvt3"})
	public void testAddCommentToIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Update blog entry
		AddACommentToIdea();
		
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "commented on their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_ModComm" },groups = {"notbvt3"})
	public void testCreateATrackbackToIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "left a trackback on their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_ModComm" },groups = {"notbvt3"})
	public void testIdeaVoted_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " voted for the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_ModComm" },groups = {"notbvt3"})
	public void testRecommendComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testRecommendComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.Like);
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "liked their own comment on "+ModeratedIdeablogEntry+".","Blogs","automation",true, true);

		System.out.println("INFO: End of Activities FVT_Level_2 testRecommendComment_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testRecommendComment_ModComm" },groups = {"notbvt3"})
	public void testIdeaGraduated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		//sel.focus(FVT_BlogsObjects.GraduateOk);
		clickLink(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "graduated their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_ModComm");
		
	}
	
	
}
