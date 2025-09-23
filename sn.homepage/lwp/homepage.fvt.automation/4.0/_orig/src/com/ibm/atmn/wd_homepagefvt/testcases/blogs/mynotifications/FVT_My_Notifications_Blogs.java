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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
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
	
	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	
	@Test
	public void testNotifyUserOfBlogEntry() throws Exception{
		System.out.println("INFO: Start of Blogs testNotifyUserOfBlogEntry");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		
		notifyAboutBlogEntry();
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ENTRY_NOTIFY_FROM_ME, StandaloneBlogEntry, StandaloneBlogName, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ENTRY_NOTIFY_FOR_ME, StandaloneBlogEntry, StandaloneBlogName, CommonData.IC_LDAP_Username_Fullname450 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BLOGS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BLOGS, true);

		
		System.out.println("INFO: End of Blogs testNotifyUserOfBlogEntry");
		
	}
	
	
	@Test 
	public void testNotifyUserOfAssignedAuthorRole() throws Exception{
		
		System.out.println("INFO: Start of Blogs testNotifyUserOfAssignedAuthorRole");			
		
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		assignUserRole("Author", CommonData.IC_LDAP_UserName451_Typeahead,CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_AUTHOR_FROM_ME, StandaloneBlogName, null, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_AUTHOR_FOR_ME, StandaloneBlogName, null, CommonData.IC_LDAP_Username_Fullname450 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BLOGS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs testNotifyUserOfAssignedAuthorRole");
		
	}
	
	@Test 
	public void testNotifyUserOfOwnerRole() throws Exception{
		
		System.out.println("INFO: Start of Blogs testNotifyUserOfOwnerRole");	
		
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		assignUserRole("Owner", CommonData.IC_LDAP_UserName451_Typeahead,CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_OWNER_FROM_ME, StandaloneBlogName, null, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_OWNER_FOR_ME, StandaloneBlogName, null, CommonData.IC_LDAP_Username_Fullname450 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BLOGS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs testNotifyUserOfOwnerRole");
		
	}
	
	@Test 
	public void testNotifyUserOfDraftRole() throws Exception{
		
		System.out.println("INFO: Start of Blogs testNotifyUserOfDraftRole");	
			
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		assignUserRole("Draft", CommonData.IC_LDAP_UserName451_Typeahead,CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_DRAFT_FROM_ME, StandaloneBlogName, null, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BLOG_ADD_DRAFT_FOR_ME, StandaloneBlogName, null, CommonData.IC_LDAP_Username_Fullname450 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BLOGS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs testNotifyUserOfDraftRole");
		
	}
	


}
