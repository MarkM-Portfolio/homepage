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
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;


public class FVT_ImFollowing_Tags_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	

	//private static String GetConfirmation = "";
	
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
			
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Tags");
			
			clickLink(FVT_HomepageObjects.ManageTags);
			
			followTag(CommonData.AutomationTag);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowTag" })
	public void testCreateStandAloneBlog() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlog");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
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
		
		verifyImFollowingTagsNewsStory(testUser2, " created a blog named "+StandaloneBlogName+".","Blogs","automation", false, true);
			
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlog");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlog" })
	public void testCreateStandAloneBlogEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlogEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//open blog
		openBlog(StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry = CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");
		
		//Logout
		LogoutAndClose();
		
		//
		verifyImFollowingTagsNewsStory(testUser2, " created a blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.","Blogs","automation",false, true);
	  	
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlogEntry");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testUpdateBlogSettings() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testUpdateBlogSettings");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//open blog
		openBlog(StandaloneBlogName);
		
		//Update blogs settings 
		updateBlogSettings();

		//Logout
		LogoutAndClose();
	  	
		//
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+StandaloneBlogName+" blog.","Blogs","automation",false, true);
		
		System.out.println("INFO: End of Blogs Level 2 testUpdateBlogSettings");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateBlogSettings" })
	public void testUpdateStandAloneBlogEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testUpdateStandAloneBlogEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//open blog
		openBlog(StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
		
		//Logout
		LogoutAndClose();
		
		//
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.","Blogs","automation", false, true);
	  	
		System.out.println("INFO: End of Blogs Level 2 testUpdateStandAloneBlogEntry");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testAddCommentStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testAddCommentStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//open blog
		openBlog(StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
		
		//Logout
		LogoutAndClose();
		
		//
		verifyImFollowingTagsNewsStory(testUser2, "commented on their own "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.","Blogs","automation",false, true);
	  	
		System.out.println("INFO: End of Blogs Level 2 testAddCommentStandaloneEntry");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testCreateATrackbackToStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateATrackbackToStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//open blog
		openBlog(StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
		
		//Logout
		LogoutAndClose();
	  	
		//
		verifyImFollowingTagsNewsStory(testUser2, "left a trackback on their own "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.", "Blogs", "automation", false, true);
		
		System.out.println("INFO: End of Blogs Level 2 testCreateATrackbackToStandaloneEntry");
		
	}

	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
	public void testRecommendStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testRecommendStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//open blog
		openBlog(StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
		
		//Logout
		LogoutAndClose();
	  	
		//
		verifyImFollowingTagsNewsStory(testUser2, "liked their own "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.","Blogs", "automation", false, true);
		
		System.out.println("INFO: End of Blogs Level 2 testRecommendStandaloneEntry");
		
	}

	@Test (dependsOnMethods = { "testAddCommentStandaloneEntry" })
	public void testRecommendCommentStandaloneEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testRecommendCommentStandaloneEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//open blog
		openBlog(StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Recommend comment entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
		
		//Logout
		LogoutAndClose();
	  	
		//
		verifyImFollowingTagsNewsStory(testUser2, "liked their own comment on "+StandaloneBlogEntry+".","Discover", "automation", false, true);
		
		System.out.println("INFO: End of Blogs Level 2 testRecommendCommentStandaloneEntry");
		
	}


}
