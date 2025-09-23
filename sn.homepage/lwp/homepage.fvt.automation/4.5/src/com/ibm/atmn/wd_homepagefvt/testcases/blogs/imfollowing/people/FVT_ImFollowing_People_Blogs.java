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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.imfollowing.people;


import org.testng.annotations.Test;


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;


public class FVT_ImFollowing_People_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	private static String StandaloneBlogEntryDraft = "";
	private static String EditedStandaloneBlogName = "";
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	
	@Test
	public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		FollowPerson(testUser1.getEmail());
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testFollowUser");
		
	}
	
	
	@Test (dependsOnMethods = { "testFollowUser" })
	public void testCreateStandAloneBlog() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlog");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//add code to check that the blog was created
		driver.isTextPresent("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_BLOG, StandaloneBlogName, null, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam 		
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, true);
			
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlog");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlog" })
	public void testCreateStandAloneBlogEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlogEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry = CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_BLOG_ENTRY, StandaloneBlogEntry, StandaloneBlogName, testUser1.getDisplayName());
		
		//
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, true);	  	
		
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlogEntry");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testCreateStandAloneBlogEntry_Draft() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlogEntry_Draft");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntryDraft = CreateANewBlogEntryDraft(StandaloneBlogName + " Blog Entry");

		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_DRAFT_BLOG_ENTRY, StandaloneBlogEntryDraft, StandaloneBlogName, testUser1.getDisplayName());
	  	
		//
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, false);	  	
		
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlogEntry_Draft");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testUpdateStandAloneBlogEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testUpdateStandAloneBlogEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_BLOG_ENTRY, StandaloneBlogEntry, StandaloneBlogName, testUser1.getDisplayName());
		
		//
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, true);	  	
	  	
		System.out.println("INFO: End of Blogs Level 2 testUpdateStandAloneBlogEntry");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testAddCommentStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testAddCommentStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMENT_BLOG_ENTRY, StandaloneBlogEntry, StandaloneBlogName, testUser1.getDisplayName());
		
		//
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, true);	  	
	  	
		System.out.println("INFO: End of Blogs Level 2 testAddCommentStandaloneEntry");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testCreateATrackbackToStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateATrackbackToStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_TB_BLOG_ENTRY, StandaloneBlogEntry, StandaloneBlogName, testUser1.getDisplayName());
	  	
		//
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, true);	  	
		
		System.out.println("INFO: End of Blogs Level 2 testCreateATrackbackToStandaloneEntry");
		
	}

	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testRecommendStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testRecommendStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_ENTRY, StandaloneBlogEntry, StandaloneBlogName, testUser1.getDisplayName());
	  	
		//
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, true);	  	
		
		System.out.println("INFO: End of Blogs Level 2 testRecommendStandaloneEntry");
		
	}

	@Test (dependsOnMethods = { "testAddCommentStandaloneEntry" }, groups = { "brokenIE"})
	public void testRecommendCommentStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testRecommendCommentStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Recommend comment entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_COMMENT, StandaloneBlogEntry, StandaloneBlogName, testUser1.getDisplayName());
	  	
		//
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, true);	  	
		
		System.out.println("INFO: End of Blogs Level 2 testRecommendCommentStandaloneEntry");
		
	}

	//@Test (dependsOnMethods = { "testRecommendCommentStandaloneEntry" ,"testRecommendStandaloneEntry","testCreateATrackbackToStandaloneEntry","testUpdateStandAloneBlogEntry","testCreateStandAloneBlogEntry_Draft"})
	public void testBlogParentItemRename() throws Exception{
		
		EditedStandaloneBlogName = "Edited " + StandaloneBlogName;
		
		System.out.println("INFO: Start of Blogs Level 2 testBlogParentItemRename");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink(FVT_BlogsObjects.BlogsSettings);
		
		// Change Settings
		driver.getSingleElement(FVT_BlogsObjects.BlogsSettingsTitle).clear();
		driver.getSingleElement(FVT_BlogsObjects.BlogsSettingsTitle).type(EditedStandaloneBlogName);
		
		// Update Blog Settings
		clickLink(FVT_BlogsObjects.BlogsSettingsUpdateSettings);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + EditedStandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_BLOG_ENTRY, StandaloneBlogEntry, EditedStandaloneBlogName, testUser1.getDisplayName());
		
		//
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW,FVT_NewsData.BLOGS,FVT_NewsData.PEOPLE, true);	  	
	  	
		System.out.println("INFO: End of Blogs Level 2 testBlogParentItemRename");
		
	}

}
