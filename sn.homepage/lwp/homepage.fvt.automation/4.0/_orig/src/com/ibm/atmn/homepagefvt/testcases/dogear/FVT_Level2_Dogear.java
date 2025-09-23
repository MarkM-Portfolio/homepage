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

package com.ibm.atmn.homepagefvt.testcases.dogear;



import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.tasks.dogear.FVT_DogearMethods;

public class FVT_Level2_Dogear extends FVT_DogearMethods {
	
	private static String part1BookmarkPublicTitle = "";
	private static String part1BookmarkPrivateTitle = "";
	@SuppressWarnings("unused")
	private static String part1BookmarkModifiedPublicToPrivate = "";
	

	//NOTE: Only the ajones[number](Amy Jones[number]) class of users are valid here without further changes.
	private static String testUser1Username = CommonData.IC_LDAP_Username450;
	private static String testUser1Password = CommonData.IC_LDAP_Password450;
	private static String testUser2Username = CommonData.IC_LDAP_Username451;
	private static String testUser2Password = CommonData.IC_LDAP_Password451;
	private static String testUser3Username = CommonData.IC_LDAP_Username452;
	private static String testUser3Password = CommonData.IC_LDAP_Password452;
	
	
	private FormInputHandler typist = getFormInputHandler();
	

	
	
	@Test
	public void testPrivateBookmarkAdded() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones450)
		Login(testUser1Username, testUser1Password);

		//Add a private bookmark and verify
		part1BookmarkPrivateTitle = addBookmark(typist, true);
		//assertList = typist.getAssertList();
		//verifyBookmarkPresence(assertList, true, false);
		//typist.dumpList();

		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory(" created a new bookmark named "+part1BookmarkPrivateTitle+".","Discover","Bookmarks", false);

		System.out.println("INFO: End of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

	}

	@Test  (dependsOnMethods = { "testPrivateBookmarkAdded" })
	public void testPublicBookmarkAdded() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPublicBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones450)
		Login(testUser1Username, testUser1Password);

		//Add a public bookmark 
		part1BookmarkPublicTitle = addBookmark(typist);
		//ArrayList<String> part1BookmarkPublicList = typist.getListCopy();
		//assertList = typist.getAssertList();
		//verifyBookmarkPresence(assertList, true, true);
		//typist.dumpList();
		
		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory(" created a new bookmark named "+part1BookmarkPublicTitle+".","Discover","Bookmarks", true);		
		
		System.out.println("INFO: End of Dogear FVT Level 2 testPublicBookmarkAdded Test");


	}
	
	@Test (dependsOnMethods = { "testPublicBookmarkAdded" })
	public void testAddPersonToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddPersonToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones451)
		Login(testUser2Username, testUser2Password);

		//Add person to watchlist
		toggleAddPersonToWatchlist(testUser1Username);

		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory(" added  to their Bookmarks watchlist.","Discover","Bookmarks", true);		
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddPersonToWatchlist Test");

	}
	
	@Test (dependsOnMethods = { "testAddPersonToWatchlist" })
	public void testAddTagToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddTagToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones451)
		Login(testUser2Username, testUser2Password);

		//Add tag to watchlist
		toggleAddTagToWatchlist("fvttesttag");
		
		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory(" added the following tag to their Bookmarks watchlist: fvttesttag","Discover","Bookmarks", true);		
		
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddTagToWatchlist Test");

	}
	
	@Test (dependsOnMethods = { "testAddTagToWatchlist" })
	public void testAddPersonAndTagToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddPersonAndTagToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones452)
		Login(testUser3Username, testUser3Password);

		//Add person to watchlist
		toggleAddPersonToWatchlist(testUser1Username);
		
		//Add tag to watchlist 
		toggleAddTagToWatchlist("fvttesttag");
		
		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory_JO("FVTTestTag", "Bookmarks", "Public");
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddPersonAndTagToWatchlist Test");

	}
	
	
}
