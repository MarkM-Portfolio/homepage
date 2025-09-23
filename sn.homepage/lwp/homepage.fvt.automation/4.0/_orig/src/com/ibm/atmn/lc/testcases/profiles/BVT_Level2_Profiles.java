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

package com.ibm.atmn.lc.testcases.profiles;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.profiles.ProfilesObjects;
import com.ibm.atmn.lc.tasks.profiles.ProfilesMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Profiles extends ProfilesMethods {

	/*
	 * This is a functional test for the Profiles Component of IBM Connections Created By: Conor Pelly Date: 08/29/2011
	 */

	//Log into Profiles, edit the profile and then save and close
	@Test
	public void profilesLevel2BVTPart1() throws Exception {

		System.out.println("INFO: Start of Profiles Level 2 BVT Test 1");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Add an entry to the board
		AddBoardEntry();

		//Click on the Edit Profile button
		clickLink(ProfilesObjects.EditProfile);

		//Update the Users Profile
		UpdateProfile();

		//Click on the Edit Profile button
		clickLink(ProfilesObjects.EditProfile);

		//Logout of Wiki
		Logout();

		//verify that the user is logged out
		//AssertJUnit.assertFalse(sel.isTextPresent(CommonData.IC_LDAP_Username_Fullname));

		System.out.println("INFO: End of Profiles Level 2 BVT Test 1");
	}

	@Test
	public void profilesLevel2BVTPart2() throws Exception {

		System.out.println("INFO: Start of Profiles Level 2 BVT Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Click on the Edit Profile button
		clickLink(ProfilesObjects.EditProfile);

		//Verify User
		VerifyUserProfile();

		//Search for the user
		SearchForUser();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Profiles Level 2 BVT Test 2");
	}

	@Test
	public void profilesLevel2BVTPart3() throws Exception {

		System.out.println("INFO: Start of Profiles Level 2 BVT Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Click on the Edit Profile button
		clickLink(ProfilesObjects.EditProfile);

		//add a tag
		ProfilesAddATag();

		//Search for the user
		SearchForUser();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Profiles Level 2 BVT Test 3");
	}

	@Test
	public void profilesLevel2BVTPart4() throws Exception {

		System.out.println("INFO: Start of Profiles Level 2 BVT Test 4");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Add link 
		ProfilesAddLink();

		//open link in a new window
		sel.click("link=" + AddLinkName);

		//Verify that clicked link is opened in a new window
		VerifyNewWindow();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Profiles Level 2 BVT Test 4");
	}

}
