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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.mynotifications;


import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

public class FVT_My_Notifications_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	
	@Test
	public void testNotifyUserOfBlogEntry() throws Exception{
		System.out.println("INFO: Start of Blogs testNotifyUserOfBlogEntry");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//add code to check that the blog was created
		driver.isTextPresent("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");
		
		//Add code for creating a blog entry
		StandaloneBlogEntry = CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");
		
		notifyAboutBlogEntry(testUser2);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ENTRY_NOTIFY_FROM_ME, StandaloneBlogEntry, StandaloneBlogName, testUser2.getDisplayName());
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ENTRY_NOTIFY_FOR_ME, StandaloneBlogEntry, StandaloneBlogName, testUser1.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BLOGS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BLOGS, true);

		
		System.out.println("INFO: End of Blogs testNotifyUserOfBlogEntry");
		
	}
	
	
	@Test 
	public void testNotifyUserOfAssignedAuthorRole() throws Exception{
		
		System.out.println("INFO: Start of Blogs testNotifyUserOfAssignedAuthorRole");			
		
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//add code to check that the blog was created
		driver.isTextPresent("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink(FVT_BlogsObjects.BlogsSettings);
		
		//Assign a blog user a role
		assignUserRole("Author", testUser2.getDisplayName(),testUser2.getLastName());
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_AUTHOR_FROM_ME, StandaloneBlogName, null, testUser2.getDisplayName() );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_AUTHOR_FOR_ME, StandaloneBlogName, null, testUser1.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BLOGS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs testNotifyUserOfAssignedAuthorRole");
		
	}
	
	@Test 
	public void testNotifyUserOfOwnerRole() throws Exception{
		
		System.out.println("INFO: Start of Blogs testNotifyUserOfOwnerRole");	
		
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//add code to check that the blog was created
		driver.isTextPresent("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink(FVT_BlogsObjects.BlogsSettings);
		
		//Assign a blog user a role
		assignUserRole("Owner", testUser2.getDisplayName(),testUser2.getLastName());
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_OWNER_FROM_ME, StandaloneBlogName, null, testUser2.getDisplayName() );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_OWNER_FOR_ME, StandaloneBlogName, null, testUser1.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BLOGS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs testNotifyUserOfOwnerRole");
		
	}
	
	@Test 
	public void testNotifyUserOfDraftRole() throws Exception{
		
		System.out.println("INFO: Start of Blogs testNotifyUserOfDraftRole");	
			
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//add code to check that the blog was created
		driver.isTextPresent("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink(FVT_BlogsObjects.BlogsSettings);
		
		//Assign a blog user a role
		assignUserRole("Draft", testUser2.getDisplayName(),testUser2.getLastName());
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_DRAFT_FROM_ME, StandaloneBlogName, null, testUser2.getDisplayName() );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_DRAFT_FOR_ME, StandaloneBlogName, null, testUser1.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BLOGS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs testNotifyUserOfDraftRole");
		
	}
	


}
