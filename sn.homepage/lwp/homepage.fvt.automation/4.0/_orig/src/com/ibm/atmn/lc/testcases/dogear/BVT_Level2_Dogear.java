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

package com.ibm.atmn.lc.testcases.dogear;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.ArrayList;

import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.dogear.DogearObjects;
import com.ibm.atmn.lc.tasks.dogear.DogearMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Dogear extends DogearMethods {

	private static String part1BookmarkPublicTitle = "";
	private static String part1BookmarkPrivateTitle = "";
	@SuppressWarnings("unused")
	private static String part1BookmarkModifiedPublicToPrivate = "";
	private static String part1BookmarkModifiedPrivateToPublic = "";

	//NOTE: Only the ajones[number](Amy Jones[number]) class of users are valid here without further changes.
	private static String testUser1Username = CommonData.IC_LDAP_Username333;
	private static String testUser1Password = CommonData.IC_LDAP_Password333;
	private static String testUser2Username = CommonData.IC_LDAP_Username444;
	private static String testUser2Password = CommonData.IC_LDAP_Password444;

	private FormInputHandler typist = getFormInputHandler();
	private static ArrayList<String> assertList;
	private static ArrayList<String> part1BookmarkModifiedPrivateToPublicList;

	@Test
	public void part1() throws Exception {

		System.out.println("INFO: Start of Dogear BVT (Level 2) Part 1");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones333)
		Login(testUser1Username, testUser1Password);

		//verify main pages
		verifyMainPages();

		//Add a public bookmark and verify
		part1BookmarkPublicTitle = addBookmark(typist);
		ArrayList<String> part1BookmarkPublicList = typist.getListCopy();
		assertList = typist.getAssertList();
		verifyBookmarkPresence(assertList, true, true);
		typist.dumpList();

		//Add a private bookmark and verify
		part1BookmarkPrivateTitle = addBookmark(typist, true);
		assertList = typist.getAssertList();
		verifyBookmarkPresence(assertList, true, false);
		typist.dumpList();

		//Add to watchlist.
		toggleAddToOwnWatchlist();

		//Go to updates page
		clickLink(DogearObjects.Nav_MyUpdates);

		//Verify that public bookmark is shown and that private bookmark is not
		AssertJUnit.assertTrue(sel.isTextPresent(part1BookmarkPublicTitle));
		AssertJUnit.assertFalse("FAIL: Private bookmark appears on watchlist", sel.isTextPresent(part1BookmarkPrivateTitle));

		toggleAddToOwnWatchlist();

		//Go to updates page again
		clickLink(DogearObjects.Nav_MyUpdates);

		//Verify that own bookmarks are no longer shown on updates
		AssertJUnit.assertFalse("FAIL: Bookmark of unwatched appears on watchlist", sel.isTextPresent(part1BookmarkPublicTitle));
		AssertJUnit.assertFalse("FAIL: Private Bookmark of unwatched appears on watchlist", sel.isTextPresent(part1BookmarkPrivateTitle));

		//Go to public bookmarks
		clickLink(DogearObjects.Nav_PublicBookmarks);

		//Edit public bookmark: change all text and make private
		part1BookmarkModifiedPublicToPrivate = editBookmark(typist, true);
		//verifiy that it is now private and all text was saved correctly
		assertList = typist.getAssertList();
		verifyBookmarkPresence(assertList, true, false);
		typist.dumpList();

		//Check bookmark details from before edit are no longer present
		verifyBookmarkPresence(part1BookmarkPublicList, false, false);

		//Go back to my bookmarks
		clickLink(DogearObjects.Nav_MyBookmarks);

		//Edit private bookmark: change text and make public
		part1BookmarkModifiedPrivateToPublic = editBookmark(typist, false);
		part1BookmarkModifiedPrivateToPublicList = typist.getListCopy();

		//verifiy that it is now public and all text was saved correctly
		assertList = typist.getAssertList();
		verifyBookmarkPresence(assertList, true, true);
		typist.dumpList();

		// Logout of Connections
		Logout();

		System.out.println("INFO: End of Dogear BVT (Level 2) Part 1");

	}

	@Test
	public void part2() throws Exception {

		System.out.println("INFO: Start of Dogear BVT (Level 2) Part 2");

		if (part1BookmarkModifiedPrivateToPublic.length() == 0) {
			throw new Exception("TEST ERROR: BVT_Level2_Dogear testPart1 did not complete. testPart2 depends on testPart1.");
		}

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(testUser2Username, testUser2Password);

		clickLink(DogearObjects.Nav_PublicBookmarks);

		//add bookmark created by user1 to user2's bookmarks: should be titled part1BookmarkModifiedPrivateToPublic
		addToMyBookmarks();

		//veirfy that it is in user2's bookmarks
		verifyBookmarkPresence(part1BookmarkModifiedPrivateToPublicList, true, true);

		clickLink(DogearObjects.Nav_MyBookmarks);

		//delete the bookmark user2 added to MyBookmarks above
		deleteBookmark();

		//verify that it is gone from MyBookmarks, but still present in public bookmarks as it is still owned by user1
		verifyBookmarkPresence(part1BookmarkModifiedPrivateToPublicList, false, true);

		// Logout of Connections
		Logout();

		System.out.println("INFO: End of Dogear BVT (Level 2) Part 2");
	}
}
