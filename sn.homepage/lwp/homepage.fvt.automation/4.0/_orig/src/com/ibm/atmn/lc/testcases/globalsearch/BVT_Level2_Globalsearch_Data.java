/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.testcases.globalsearch;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.lc.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.tasks.globalsearch.GlobalSearchMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Globalsearch_Data extends GlobalSearchMethods {

	/*
	 * Created By: Conor Pelly Date: 09/09/2011 Comment: This classes will enter the data that is required for the
	 * Global Search testcases to run successfully. This will be the first Level 2 BVT called and the actually BVT for
	 * search will be the last to run so that the indexer will have run by then
	 */

	private FormInputHandler typist = getFormInputHandler();

	@Test
	public void testActivitiesSearchData() throws Exception {

		System.out.println("INFO: Entering Data needed for Global Search BVT for Activities");

		//Load the component
		LoadComponent(CommonObjects.ComponentActivities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Create an activity with a tag
		CreatAnActivityForSearch(typist, CommonData.IC_LDAP_Username77);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of entering Data for Global Search for Activities");

	}

	@Test
	public void testBlogsSearchData() throws Exception {

		System.out.println("INFO: Entering Data needed for Global Search BVT for Blogs");

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Create a blog with a tag
		clickLink(BlogsObjects.MyBlogs);

		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);

		//Add code for creating a blog
		AddBlogForSearch();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of entering Data for Global Search for Blogs");
	}

	public void BookmarkSearchData() throws Exception {

		System.out.println("INFO: Entering Data needed for Global Search BVT for Bookmarks");

		//Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Create a bookmark with a tag
		AddBookmarkForSearch("IBM W3 Workplace", "http://w3.ibm.com/w3odw/spg/index_default.html", "BookmarkSearchTag");

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of entering Data for Global Search for Bookmarks");
	}

	@Test
	public void testCommunitySearchData() throws Exception {

		System.out.println("INFO: Entering Data needed for Global Search BVT for Communities");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Create a community with a tag
		CreateACommunityForSearch();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of entering Data for Global Search for Communities");
	}

	@Test
	public void testProfileSearchData() throws Exception {

		System.out.println("INFO: Entering Data needed for Global Search BVT for Profiles");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Add a tag to the user's profile
		ProfilesAddATagForSearch();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of entering Data for Global Search for Profiles");

	}

}
