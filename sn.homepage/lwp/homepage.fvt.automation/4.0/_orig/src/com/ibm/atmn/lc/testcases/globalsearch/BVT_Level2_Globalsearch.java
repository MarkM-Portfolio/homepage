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

package com.ibm.atmn.lc.testcases.globalsearch;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.lc.appobjects.activities.*;
import com.ibm.atmn.lc.appobjects.blogs.*;
import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.communities.*;
import com.ibm.atmn.lc.appobjects.globalsearch.*;
import com.ibm.atmn.lc.appobjects.profiles.ProfilesData;
import com.ibm.atmn.lc.tasks.globalsearch.*;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Globalsearch extends GlobalSearchMethods {

	/*
	 * This is a functional test for the Global Search Component of IBM Connections Created By: Conor Pelly Date:
	 * 09/19/2011
	 */

	@Test
	public void testSearchForActivityTag() throws Exception {

		System.out.println("INFO: Start of Global Search Level 2 BVT Test 1");
		String ReturnedSearchCriteria1 = CommonData.IC_LDAP_Username;
		String ReturnedSearchCriteria2 = ActivitiesData.Start_An_Activity_InputText_Tags_Data_For_Search;
		String ReturnedSearchCriteria3 = ActivitiesData.Start_An_Activity_InputText_Name_Data_For_Search;
		String ReturnedSearchCriteria4 = ActivitiesData.Start_An_Activity_Textarea_Description_Data_For_Search;

		//Load the component
		LoadComponent(CommonObjects.ComponentGlobalSearch);

		//Login as a user (ie. Amy Jones)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Uncheck all checkboxes
		UnCheckAllCheckboxes();

		//Check the Activities checkbox and then enter the tag that was laydown earlier
		ComponentAndTagToSearchFor(SearchObjects.ActivitiesCheckbox, SearchData.ActivitiesSearchTag);

		//Check that there are no errors in the UI - this would mean that the indexer has not being run
		CheckForSearchError();

		//Check to see if the results are returned - this would mean that the indexer has not being run
		CheckForResults();

		//Verify the results
		VerifySearchResults(ReturnedSearchCriteria1, ReturnedSearchCriteria2, ReturnedSearchCriteria3, ReturnedSearchCriteria4);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Global Search Level 2 BVT Test 1");

	}

	@Test
	public void SearchForBlogs() throws Exception {

		System.out.println("INFO: Start of Global Search Level 2 BVT Test 2");
		String ReturnedSearchCriteria1 = CommonData.IC_LDAP_Username;
		String ReturnedSearchCriteria2 = BlogsData.BlogsTagForSearch;
		String ReturnedSearchCriteria3 = BlogsData.BlogsNameForSearch;
		String ReturnedSearchCriteria4 = BlogsData.BlogsDescriptionForSearch;

		//Load the component
		LoadComponent(CommonObjects.ComponentGlobalSearch);

		//Login as a user (ie. Amy Jones)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Uncheck all checkboxes
		UnCheckAllCheckboxes();

		//Check the Activities checkbox and then enter the tag that was laydown earlier
		ComponentAndTagToSearchFor(SearchObjects.BlogsCheckbox, SearchData.BlogsSearchTag);

		//Check that there are no errors in the UI - this would mean that the indexer has not being run
		CheckForSearchError();

		//Check to see if the results are returned - this would mean that the indexer has not being run
		CheckForResults();

		//Verify the results
		VerifySearchResults(ReturnedSearchCriteria1, ReturnedSearchCriteria2, ReturnedSearchCriteria3, ReturnedSearchCriteria4);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Global Search Level 2 BVT Test 2");

	}

	public void SearchForBookmarks() throws Exception {

		System.out.println("INFO: Start of Global Search Level 2 BVT Test 3");
		String ReturnedSearchCriteria1 = CommonData.IC_LDAP_Username;
		String ReturnedSearchCriteria2 = "BookmarkSearchTag";
		String ReturnedSearchCriteria3 = "IBM W3 Workplace";
		String ReturnedSearchCriteria4 = "This is a test bookmark for search bvt";

		//Load the component
		LoadComponent(CommonObjects.ComponentGlobalSearch);

		//Login as a user (ie. Amy Jones)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Uncheck all checkboxes
		UnCheckAllCheckboxes();

		//Check the Activities checkbox and then enter the tag that was laydown earlier
		ComponentAndTagToSearchFor(SearchObjects.BookmarkCheckbox, SearchData.BookmarksSearchTag);

		//Check that there are no errors in the UI - this would mean that the indexer has not being run
		CheckForSearchError();

		//Check to see if the results are returned - this would mean that the indexer has not being run
		CheckForResults();

		//Verify the results
		VerifySearchResults(ReturnedSearchCriteria1, ReturnedSearchCriteria2, ReturnedSearchCriteria3, ReturnedSearchCriteria4);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Global Search Level 2 BVT Test 3");

	}

	@Test
	public void testSearchForCommunity() throws Exception {

		System.out.println("INFO: Start of Global Search Level 2 BVT Test 4");
		String ReturnedSearchCriteria1 = CommonData.IC_LDAP_Username;
		String ReturnedSearchCriteria2 = CommunitiesData.CommunityTagForSearch;
		String ReturnedSearchCriteria3 = CommunitiesData.CommunityNameForSearch;
		String ReturnedSearchCriteria4 = CommunitiesData.CommunityDescriptionForSearch;

		//Load the component
		LoadComponent(CommonObjects.ComponentGlobalSearch);

		//Login as a user (ie. Amy Jones)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Uncheck all checkboxes
		UnCheckAllCheckboxes();

		//Check the Activities checkbox and then enter the tag that was laydown earlier
		ComponentAndTagToSearchFor(SearchObjects.CommunitiesCheckbox, SearchData.CommunitiesSearchTag);

		//Check that there are no errors in the UI - this would mean that the indexer has not being run
		CheckForSearchError();

		//Check to see if the results are returned - this would mean that the indexer has not being run
		CheckForResults();

		//Verify the results
		VerifySearchResults(ReturnedSearchCriteria1, ReturnedSearchCriteria2, ReturnedSearchCriteria3, ReturnedSearchCriteria4);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Global Search Level 2 BVT Test 4");

	}

	@Test
	public void testSearchForProfiles() throws Exception {

		System.out.println("INFO: Start of Global Search Level 2 BVT Test 5");
		String ReturnedSearchCriteria1 = CommonData.IC_LDAP_Username;
		String ReturnedSearchCriteria2 = ProfilesData.ProfilesTagForSearch;
		String ReturnedSearchCriteria3 = "";
		String ReturnedSearchCriteria4 = "";

		//Load the component
		LoadComponent(CommonObjects.ComponentGlobalSearch);

		//Login as a user (ie. Amy Jones)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Uncheck all checkboxes
		UnCheckAllCheckboxes();

		//Check the Activities checkbox and then enter the tag that was laydown earlier
		ComponentAndTagToSearchFor(SearchObjects.ProfilesCheckbox, SearchData.ProfilesSearchTag);

		//Check that there are no errors in the UI
		CheckForSearchError();

		//Check to see if the results are returned - this would mean that the indexer has not being run
		CheckForResults();

		//Verify the results
		VerifySearchResults(ReturnedSearchCriteria1, ReturnedSearchCriteria2, ReturnedSearchCriteria3, ReturnedSearchCriteria4);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Global Search Level 2 BVT Test 5");

	}

}
