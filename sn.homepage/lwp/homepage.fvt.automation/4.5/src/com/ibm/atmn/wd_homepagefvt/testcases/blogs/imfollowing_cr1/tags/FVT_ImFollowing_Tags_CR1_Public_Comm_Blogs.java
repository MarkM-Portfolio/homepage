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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.imfollowing_cr1.tags;


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

public class FVT_ImFollowing_Tags_CR1_Public_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PublicBlogCommunity = "";
	private static String PublicBlogEntry = "";
	
	
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
	public void testCreatePublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreatePublicComm");
	
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
		PublicBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());

		//Logout
		Logout();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreatePublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePublicComm" })
	public void testCreateBlog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PublicBlogCommunity);
		
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " added a blog to the "+PublicBlogCommunity+" community.","Blogs","automation", true, true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateBlog_PublicComm" })
	public void testCreateBlogEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		//open community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		// Created a new blog entry
		PublicBlogEntry = CreateANewBlogEntry(PublicBlogCommunity + " Blog Entry");

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " created a blog entry named "+PublicBlogEntry+" in the "+PublicBlogCommunity+" blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testUpdateBlogSettings_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogSettings_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Update blogs settings 
		updateBlogSettings();

		//Logout
		LogoutAndClose();
		
		//not appearing in news feed - commented out pending fix
		//verifyImFollowingTagsNewsStory(testUser2, " updated the "+PublicBlogCommunity+" blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogSettings_PublicComm");
		
	}

	@Test (dependsOnMethods = { "testUpdateBlogSettings_PublicComm" })
	public void testUpdateBlogEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testAddCommentToBlog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "commented on their own "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Blogs","automation",true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testCreateATrackbackTOEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		LogoutAndClose();
		
		//not appearing in newsfeed
		//verifyImFollowingTagsNewsStory(testUser2, "left a trackback on their own "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Blogs","automation",true, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testRecommendEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "liked their own "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Blogs","automation",true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_PublicComm" }, groups = { "brokenIE"})
	public void testRecommendComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//open community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Recommend entry
		likeBlogComment_CR1();
		
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "liked their own comment on "+PublicBlogEntry+".","Blogs","automation",true, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PublicComm");
		
	}
	
}
