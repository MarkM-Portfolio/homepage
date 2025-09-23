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

package com.ibm.atmn.lc.testcases.communities;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.base.SetUpMethods;
import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesData;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.lc.tasks.communities.CommunitiesMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Communities extends CommunitiesMethods {

	/*
	 * This is a functional test for the Communities Component of IBM Connections Created By: Adrian Strock Date:
	 * 08/19/2011
	 */

	//@Test
	public void aboutAndHelpForCommunities() throws Exception {

		System.out.println("INFO: Start of Communities BVT_Level_2 Test 1");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//assertTrue("Fail: Communities is not open", sel.isTextPresent("My Communities"));

		//Now verify the About page and version number and the Communities Help
		CommunitiesHelpAndAbout();

		//Logout of Communities
		Logout();

		System.out.println("INFO: End of Communities BVT_Level_2 Test 1");

	}

	@Test
	public void createPublicCommunity() throws Exception {

		System.out.println("INFO: Start of Communities BVT_Level_2 Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);
		
		// No longer valid in 4.0
		//assertTrue("Fail: Communities is not open", sel.isTextPresent("My Communities"));

		//Now Get the DateTime
		String DateTimeStamp = SetUpMethods.genDateBasedRandVal();

		//Create a public community
		CreateNewCommunity(CommunitiesData.CommunityName + DateTimeStamp, CommunitiesData.CommunityHandle + DateTimeStamp, CommunitiesObjects.CommunityAccessOption1, "Members",
				CommunitiesObjects.CommunityMembersTypeAhead, CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead + 100,
				CommunitiesObjects.fullUserSearchIdentifier);

		//Verify that community, description, image and theme are correct
		VerifyCommunityDetails("Public", CommunitiesData.CommunityName + DateTimeStamp, CommunitiesData.CommunityHandle + DateTimeStamp);

		//Now add a bookmark
		AddBookmarkInCommunity();

		//Verify that the bookmark has being created 
		VerifyBookmark("link=" + CommunitiesData.BookmarkName, CommunitiesData.BookmarkName);

		//Now Edit the bookmark
		EditBookmark();

		//Verify the edited bookmark
		VerifyBookmark("link=" + CommunitiesData.EditBookmarkName, CommunitiesData.EditBookmarkName);

		//Add a forum topic with description and tag
		AddForumEntry();

		//Add feeds to the overview page
		AddFeedsWidgetToOverview();

		//Add Files to the Community
		//Generate random file name
		String randomName = genDateBasedRandVal();

		//Upload community-owned file
		uploadFileToCommunity(randomName, ".jpg", "Desert.jpg");

		//Logout of Communities
		Logout();

		System.out.println("INFO: End of Communities BVT_Level_2 Test 2");
	}

	@Test
	public void createModeratedCommunity() throws Exception {

		System.out.println("INFO: Start of Communities BVT_Level_2 Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);
		
		// Not valid in 4.0. Old nav replaced by new catalog view
		//assertTrue("Fail: Communities is not open", sel.isTextPresent("My Communities"));

		//Now Get the DateTime
		String DateTimeStamp = SetUpMethods.genDateBasedRandVal();

		//Create a moderated community
		CreateNewCommunity(CommunitiesData.CommunityName + DateTimeStamp, CommunitiesData.CommunityHandle + DateTimeStamp, CommunitiesObjects.CommunityAccessOption2, "Members",
				CommunitiesObjects.CommunityMembersTypeAhead, CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead + 101,
				CommunitiesObjects.fullUserSearchIdentifier);

		//Verify that community, description, image and theme are correct
		VerifyCommunityDetails("Moderated", CommunitiesData.CommunityName + DateTimeStamp, CommunitiesData.CommunityHandle + DateTimeStamp);

		//Logout of Communities
		Logout();

		System.out.println("INFO: End of Communities BVT_Level_2 Test 3");
	}

	@Test
	public void createRestrictedCommunity() throws Exception {

		System.out.println("INFO: Start of Communities BVT_Level_2 Test 4");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//assertTrue("Fail: Communities is not open", sel.isTextPresent("My Communities"));

		//Now Get the DateTime
		String DateTimeStamp = SetUpMethods.genDateBasedRandVal();

		//Create a private community
		CreateNewCommunity(CommunitiesData.CommunityName + DateTimeStamp, CommunitiesData.CommunityHandle + DateTimeStamp, CommunitiesObjects.CommunityAccessOption3, "Members",
				CommunitiesObjects.CommunityMembersTypeAhead, CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead + 102,
				CommunitiesObjects.fullUserSearchIdentifier);

		//Verify that community, description, image and theme are correct
		VerifyCommunityDetails("Restricted", CommunitiesData.CommunityName + DateTimeStamp, CommunitiesData.CommunityHandle + DateTimeStamp);

		//Add new member to existing community
		AddMembersToExistingCommunity("Members", CommunitiesObjects.AddMembersToExistingTypeAheadWithoutCom, CommonData.LDAP_User_Typeahead + 109,
				CommunitiesObjects.Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom, CommunitiesObjects.fullUserSearchIdentifier1);
		//Add new member to existing community
		AddMembersToExistingCommunity("Members", CommunitiesObjects.AddMembersToExistingTypeAheadWithoutCom, CommonData.LDAP_User_Typeahead + 110,
				CommunitiesObjects.Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom, CommunitiesObjects.fullUserSearchIdentifier1);
		//Add new member to existing community
		AddMembersToExistingCommunity("Owners", CommunitiesObjects.AddMembersToExistingTypeAheadWithoutCom, CommonData.LDAP_User_Typeahead + 111,
				CommunitiesObjects.Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom, CommunitiesObjects.fullUserSearchIdentifier1);

		//Verify the New Members and their roles
		VerifyNewMembers();

		//Remove the member just added
		RemoveMembersToExistingCommunity();

		//Logout of Communities
		Logout();

		System.out.println("INFO: End of Communities BVT_Level_2 Test 4");
	}
}
