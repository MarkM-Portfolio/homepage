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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Tags_Public_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String PublicIdeablogCommunity = "";
	private static String PublicIdeablogEntry = "";
	
	@Test 
	public void testFollowTag() throws Exception {
			
			
		System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowTag");
			
		// Login to communities
		LoadComponent(CommonObjects.ComponentNews);
				
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
		//Follow tag	
		followTag(CommonData.AutomationTag);
			
		System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
	}
	
	
	@Test (dependsOnMethods = { "testFollowTag" })
	public void testCreatePublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreatePublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PublicIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		//Logout
		Logout();
				
		System.out.println("INFO: End of blogs FVT_Level_2 testCreatePublicComm");
		
	}
	

	@Test (dependsOnMethods = { "testCreatePublicComm" })
	public void testCreateIdeablog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PublicIdeablogCommunity);
			
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");

		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory(" created an Ideation Blog named "+PublicIdeablogCommunity+".","Blogs","automation",true, true);
				
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateIdeablog_PublicComm" })
	public void testCreateIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		// Created a new blog idea
		PublicIdeablogEntry = CreateANewIdea(PublicIdeablogCommunity + " Blog Idea");

		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory(" created the "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_PublicComm" })
	public void testUpdateIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory(" updated the "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_PublicComm" }, groups = { "broken" })
	public void testAddCommentToIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory("commented on their own "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_PublicComm" }, groups = { "broken" })
	public void testCreateATrackbackToIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory("left a trackback on their own RE: "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_PublicComm" }, groups = { "broken" })
	public void testIdeaVoted_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory(" voted for the "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_PublicComm" }, groups = { "broken" })
	public void testRecommendComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.Like);
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory("liked their own comment on "+PublicIdeablogEntry+".","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testRecommendComment_PublicComm" }, groups = { "broken" })
	public void testIdeaGraduated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaGraduated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		//sel.focus(FVT_BlogsObjects.GraduateOk);
		clickLink(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory("graduated their own "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Blogs","automation",true, true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaGraduated_PublicComm");
		
	}
	
	
}
