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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs;


import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;


public class TestOfFVT_Level2_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String StandaloneBlogName = "FVT Standalone Blog 1159153416";
	private static String StandaloneBlogEntry = "FVT Standalone Blog 1159153416 Blog Entry";
	private static String PublicBlogCommunity = "Public Level 2 FVT Community 1159161441";
	private static String PublicBlogEntry = " Public Level 2 FVT Community 1159161441 Blog Entry";
	
	
@Test 
public void testUpdateBlogEntry_PublicComm() throws Exception {
	
	
	System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");

	// Login to communities
	LoadComponent(CommonObjects.ComponentCommunities);
		
	Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
	assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
	
	//Open Community
	openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
	
	//Go to blog entry
	clickLink("link=" + PublicBlogEntry);
	
	//Update blog entry
	CreateAUpdatedBlogEntry();

	//Logout
	LogoutAndClose();
	
	//Replace news story string
	String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_BLOG_ENTRY, PublicBlogEntry, PublicBlogCommunity, CommonData.IC_LDAP_Username_Fullname450);
	
	verifyImFollowingNewsStory(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451,BlogNewsStory,FVT_NewsData.BLOGS, true);
	
	//verifyImFollowingNewsStory(" updated the "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Blogs", true);
	
	System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
	
}
}
