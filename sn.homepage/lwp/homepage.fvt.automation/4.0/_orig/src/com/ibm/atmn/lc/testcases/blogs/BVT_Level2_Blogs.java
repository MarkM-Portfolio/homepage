/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential */
/*                                                                   */
/* OCO Source Materials */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013 */
/*                                                                   */
/* The source code for this program is not published or otherwise */
/* divested of its trade secrets, irrespective of what has been */
/* deposited with the U.S. Copyright Office. */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.testcases.blogs;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.base.SetUpMethods;
import com.ibm.atmn.lc.appobjects.blogs.BlogsData;
import com.ibm.atmn.lc.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.tasks.blogs.BlogsMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Blogs extends BlogsMethods {

	/*
	 * This is a functional test for the Blogs Component of IBM Connections Created By: Conor Pelly Date: 08/01/2011
	 */

	//Log into News and then logout
	@Test
	public void createANewBlogAsAdmin() throws Exception {

		System.out.println("INFO: Start of Blogs Level 2 BVT Test 1");
		String DateTime = SetUpMethods.genDateBasedRandVal();

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);

		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);

		//Add code for creating a blog
		CreateABlogAsAdmin(BlogsData.BlogsName1 + DateTime, BlogsData.BlogsAddress1 + DateTime);

		//Edit the admin settings
		ChangeAdminSettings();

		//Click on the Public Blogs tab
		clickLink(BlogsObjects.PublicBlogs);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Blogs Level 2 BVT Test 1");
	}

	@Test
	public void blogsAdminUserTest() throws Exception {

		System.out.println("INFO: Start of Blogs Level 2 BVT Test 2");
		String DateTime = SetUpMethods.genDateBasedRandVal();

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);

		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);

		//Add code for creating a blog
		CreateABlogAsAdmin(BlogsData.BlogsName2 + DateTime, BlogsData.BlogsAddress2 + DateTime);

		//add code to check that the blog was created
		BlogsVerifyUIMessage("New blog [" + BlogsData.BlogsName2 + DateTime + "] has been successfully created.");

		//Now set this blog as the primary blog
		SetBlogAsPrimary(BlogsData.BlogsName2 + DateTime);

		//Add an Entry
		CreateANewBlogEntry(BlogsData.BlogsName2 + DateTime, BlogsData.BlogsNewEntryTitle + DateTime);

		//Add a comment
		AddACommentToEntry();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Blogs Level 2 BVT Test 2");

	}

	@Test
	public void blogsStandardUserTest() throws Exception {

		System.out.println("INFO: Start of Blogs Level 2 BVT Test 3");
		String DateTime = SetUpMethods.genDateBasedRandVal();

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);

		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);

		//Add code for creating a blog
		CreateABlogAsStandardUser(BlogsData.BlogsName3 + DateTime, BlogsData.BlogsAddress3 + DateTime);

		//add code to check that the blog was created
		BlogsVerifyUIMessage("New blog [" + BlogsData.BlogsName3 + DateTime + "] has been successfully created.");

		//Add an Entry
		CreateANewBlogEntry(BlogsData.BlogsName3 + DateTime, BlogsData.BlogsNewEntryTitle + DateTime);

		//Add a comment
		AddACommentToEntry();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Blogs Level 2 BVT Test 3");

	}

	@Test
	public void blogUpdateSettings() throws Exception {

		System.out.println("INFO: Start of Blogs Level 2 BVT Test 4");
		String DateTime = SetUpMethods.genDateBasedRandVal();

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);

		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);

		//Add code for creating a blog
		CreateABlogAsAdmin(BlogsData.BlogsName4 + DateTime, BlogsData.BlogsAddress4 + DateTime);

		//Add an Entry
		CreateANewBlogEntry(BlogsData.BlogsName4 + DateTime, BlogsData.BlogsNewEntryTitle + DateTime);

		//Add a comment
		AddACommentToEntry();

		//Upload a file
		BlogsFileUpload("Desert.jpg");

		//Add a new entry with image that you just uploaded
		CreateANewBlogEntryWithImage(BlogsData.BlogsSecondEntryTitle + DateTime);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Blogs Level 2 BVT Test 4");

	}
}
