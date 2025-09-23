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

import java.util.ArrayList;
import org.testng.annotations.Test;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.tasks.dogear.FVT_DogearMethods;

public class TestNGFVT_Level2_Dogear extends FVT_DogearMethods {
	
	private static String part1BookmarkPublicTitle = "";
	private static String part1BookmarkPrivateTitle = "";
	@SuppressWarnings("unused")
	private static String part1BookmarkModifiedPublicToPrivate = "";
	private static String part1BookmarkModifiedPrivateToPublic = "";

	//NOTE: Only the ajones[number](Amy Jones[number]) class of users are valid here without further changes.
	private static String testUser1Username = CommonData.IC_LDAP_Username111;
	private static String testUser1Password = CommonData.IC_LDAP_Password111;
	private static String testUser2Username = CommonData.IC_LDAP_Username222;
	private static String testUser2Password = CommonData.IC_LDAP_Password222;
	private static String testUser3Username = CommonData.IC_LDAP_Username333;
	private static String testUser3Password = CommonData.IC_LDAP_Password333;
	
	
	private FormInputHandler typist = getFormInputHandler();
	private static ArrayList<String> assertList;
	private static ArrayList<String> part1BookmarkModifiedPrivateToPublicList;

	
	
	@Test
	public void testPrivateBookmarkAdded() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones111)
		Login(testUser1Username, testUser1Password);

		//Add a private bookmark and verify
		part1BookmarkPrivateTitle = addBookmark(typist, true);
		assertList = typist.getAssertList();
		//verifyBookmarkPresence(assertList, true, false);
		typist.dumpList();

		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory_JO(part1BookmarkPrivateTitle, "Bookmarks", false);

		System.out.println("INFO: End of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

	}

	@Test
	public void testPublicBookmarkAdded() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPublicBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones111)
		Login(testUser1Username, testUser1Password);

		//Add a public bookmark 
		part1BookmarkPublicTitle = addBookmark(typist);
		ArrayList<String> part1BookmarkPublicList = typist.getListCopy();
		assertList = typist.getAssertList();
		verifyBookmarkPresence(assertList, true, true);
		typist.dumpList();
		
		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory_JO(part1BookmarkPublicTitle, "Bookmarks", true);
		
		System.out.println("INFO: End of Dogear FVT Level 2 testPublicBookmarkAdded Test");


	}
	
	@Test
	public void testAddPersonToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddPersonToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones222)
		Login(testUser2Username, testUser2Password);

		//Add person to watchlist
		toggleAddPersonToWatchlist(testUser1Username);
		
		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory_JO(testUser1Username, "Bookmarks", "Public");
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddPersonToWatchlist Test");

	}
	
	@Test
	public void testAddTagToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddTagToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones222)
		Login(testUser2Username, testUser2Password);

		//Add tag to watchlist
		toggleAddTagToWatchlist("fvttesttag");
		
		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory_JO("FVTTestTag", "Bookmarks", "Public");
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddTagToWatchlist Test");

	}
	
	@Test
	public void testAddPersonAndTagToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddPersonAndTagToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones333)
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
