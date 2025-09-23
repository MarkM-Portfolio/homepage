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


public class FVT_ImFollowing_Tags_Private_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PrivateIdeablogCommunity = "";
	private static String PrivateIdeablogEntry = "";
	
	
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
	public void testCreatePrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreatePrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of blogs FVT_Level_2 testCreatePrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testCreateIdeablog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PrivateIdeablogCommunity);
		
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");
		
		sleep(500);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " added an Ideation Blog to the "+PrivateIdeablogCommunity+" community.","Blogs","automation",true, false);
				
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdeablog_PrivateComm" })
	public void testCreateIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));
		
		//open community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		// Created a new blog idea
		PrivateIdeablogEntry = CreateANewIdea(PrivateIdeablogCommunity + " Blog Idea");

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " created the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_PrivateComm" })
	public void testUpdateIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_PrivateComm" })
	public void testAddCommentToIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "commented on their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_PrivateComm" })
	public void testCreateATrackbackToIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "left a trackback on their own RE: "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_PrivateComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_PrivateComm" })
	public void testIdeaVoted_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2," voted for the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_PrivateComm" }, groups = { "brokenIE"})
	public void testRecommendComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.Like);
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "liked their own comment on "+PrivateIdeablogEntry+".","Tags","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
		
	}
	
	//Delay in element appearing causing issues with test
	//@Test (dependsOnMethods = { "testRecommendComment_PrivateComm" })
	public void testIdeaGraduated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaGraduated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		//driver.focus(FVT_BlogsObjects.GraduateOk);
		clickLink(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		LogoutAndClose();
		
		
		//verifyImFollowingTagsNewsStory(testUser2, "graduated their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaGraduated_PrivateComm");
		
	}
	
	
}
