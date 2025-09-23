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


import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;

public class FVT_ImFollowing_Tags_Private_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String PrivateBlogCommunity = "";
	private static String PrivateBlogEntry = "";
	
	
	@Test 
	public void testFollowTag() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowTag");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			//Follow Tag
			followTag(CommonData.AutomationTag);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
	}
	
	
	@Test
	public void testCreatePrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreatePrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		//Logout
		Logout();
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreatePrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testCreateBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PrivateBlogCommunity);
	
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		Logout();
		
		verifyImFollowingTagsNewsStory(" created a blog named "+PrivateBlogCommunity+".","Blogs","automation", true, false);

		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlog_PrivateComm" })
	public void testCreateBlogEntry_PrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//open community
			openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
			
			// Created a new blog entry
			PrivateBlogEntry = CreateANewBlogEntry(PrivateBlogCommunity + " Blog Entry");
	
			//Logout
			Logout();
			
			verifyImFollowingTagsNewsStory(" created a blog entry named "+PrivateBlogEntry+" in the "+PrivateBlogCommunity+" blog.","Blogs","automation", true, false);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
			
	}
	

	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testUpdateBlogSettings_PrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogSettings_PrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//open community
			openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
			
			//Update blogs settings 
			updateBlogSettings();
	
			//Logout
			Logout();
			
			verifyImFollowingTagsNewsStory(" updated the "+PrivateBlogCommunity+" blog.","Blogs","automation", true, false);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogSettings_PrivateComm");
			
	}
	
	@Test (dependsOnMethods = { "testUpdateBlogSettings_PrivateComm" })
	public void testUpdateBlogEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		Logout();
		
		verifyImFollowingTagsNewsStory(" updated the "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PrivateComm");
		
	}
	

	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testAddCommentToBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		Logout();
		
		verifyImFollowingTagsNewsStory("commented on their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testCreateATrackbackTOEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		Logout();
		
		verifyImFollowingTagsNewsStory("left a trackback on their own RE: "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testRecommendEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendOEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		Logout();
		
		verifyImFollowingTagsNewsStory("liked their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendOEntry_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_PrivateComm" })
	public void testRecommendComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		Logout();
		
		verifyImFollowingTagsNewsStory("liked their own comment on "+PrivateBlogEntry+".","Blogs","automation", true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
		
	}
	
}
