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

package com.ibm.atmn.homepagefvt.testcases.blogs;


import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.homepagefvt.tasks.blogs.FVT_BlogsMethods;

public class FVT_Level2_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	private static String StandaloneBlogEntryDraft = "";
	private static String EditedStandaloneBlogName = "";
	
	
	//private static String GetConfirmation = "";
	
	@Test
	public void testCreateStandAloneBlog() throws Exception{
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlog");	
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
		BlogsVerifyUIMessage("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");
		
		//Logout
		Logout();
		
		//
		VerifyNewsStory(" created a new blog named "+StandaloneBlogName+".","Discover","Blogs", true);
	  	
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlog");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlog" })
	public void testCreateStandAloneBlogEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlogEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry = CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");
		
		//Logout
		Logout();
		
		//
		VerifyNewsStory(" created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.","Discover","Blogs", true);
	  	
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlogEntry");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testCreateStandAloneBlogEntry_Draft() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlogEntry_Draft");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntryDraft = CreateANewBlogEntryDraft(StandaloneBlogName + " Blog Entry");

		//Logout
		Logout();
	  	
		//
		VerifyNewsStory(" created a new draft blog entry named "+StandaloneBlogEntryDraft+" in the "+StandaloneBlogName+" blog.","Discover","Blogs", false);
		
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlogEntry_Draft");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry_Draft" })
	public void testUpdateStandAloneBlogEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testUpdateStandAloneBlogEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
		
		//Logout
		Logout();
		
		//
		VerifyNewsStory(" updated the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.","Discover","Blogs", true);
	  	
		System.out.println("INFO: End of Blogs Level 2 testUpdateStandAloneBlogEntry");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateStandAloneBlogEntry" })
	public void testAddCommentStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testAddCommentStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
		
		//
		VerifyNewsStory(" commented on their own "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.","Discover","Blogs", true);
		
		//Logout
		Logout();
	  	
		System.out.println("INFO: End of Blogs Level 2 testAddCommentStandaloneEntry");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentStandaloneEntry" })
	public void testCreateATrackbackToStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateATrackbackToStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
		
		//Logout
		Logout();
	  	
		//
		VerifyNewsStory(" left a trackback on their own RE: "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs Level 2 testCreateATrackbackToStandaloneEntry");
		
	}

	@Test (dependsOnMethods = { "testCreateATrackbackToStandaloneEntry" })
	public void testRecommendStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testRecommendStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
		
		//Logout
		Logout();
	  	
		//
		VerifyNewsStory(" recommended their own "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs Level 2 testRecommendStandaloneEntry");
		
	}

	@Test (dependsOnMethods = { "testRecommendStandaloneEntry" })
	public void testRecommendCommentStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testRecommendCommentStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Recommend comment entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
		
		//Logout
		Logout();
	  	
		//
		VerifyNewsStory(" recommended their own comment on "+StandaloneBlogEntry+".","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs Level 2 testRecommendCommentStandaloneEntry");
		
	}

	@Test (dependsOnMethods = { "testRecommendCommentStandaloneEntry" })
	public void testBlogParentItemRename() throws Exception{
		
		EditedStandaloneBlogName = "Edited " + StandaloneBlogName;
		
		System.out.println("INFO: Start of Blogs Level 2 testBlogParentItemRename");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink(FVT_BlogsObjects.BlogsSettings);
		
		// Change Settings
		sel.focus(FVT_BlogsObjects.BlogsSettingsTitle);
		sel.type(FVT_BlogsObjects.BlogsSettingsTitle, "");
		sel.type(FVT_BlogsObjects.BlogsSettingsTitle, EditedStandaloneBlogName );
		
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
		Logout();
		
		//
		VerifyNewsStory(" updated the "+StandaloneBlogEntry+" blog entry in the "+EditedStandaloneBlogName+" blog.","Discover","Blogs", true);
	  	
		System.out.println("INFO: End of Blogs Level 2 testBlogParentItemRename");
		
	}

}
