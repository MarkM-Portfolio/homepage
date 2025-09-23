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

package com.ibm.atmn.lc.tasks.dogear;

import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.lc.appobjects.dogear.DogearObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class DogearMethods extends CommonMethods {

	public String addBookmark(FormInputHandler typist) {

		return addBookmark(typist, false);
	}

	public String addBookmark(FormInputHandler typist, boolean makePrivate) {

		//Open popup window with form
		sel.click(DogearObjects.Nav_AddBookmark);
		return useBookmarkForm(typist, makePrivate);

	}

	public String editBookmark(FormInputHandler typist, boolean makePrivate) {

		return editBookmark(true, typist, makePrivate);
	}

	public String editBookmark(boolean modifyValues, FormInputHandler typist, boolean makePrivate) {

		clickLink(DogearObjects.BookmarksList_DetailsView);
		//Open popup window with form
		sel.click(DogearObjects.BookmarksList_EditOrAddToMyBookmarks1);
		return useBookmarkForm(modifyValues, typist, makePrivate);

	}

	public String useBookmarkForm() {

		return useBookmarkForm(false, null, false);
	}

	public String useBookmarkForm(FormInputHandler typist, boolean makePrivate) {

		return useBookmarkForm(true, typist, makePrivate);
	}

	public String useBookmarkForm(boolean modifyValues, FormInputHandler typist, boolean makePrivate) {

		sel.waitForPopUp("dw", "20000");
		sleep(1000);
		sel.selectWindow("var=dw");

		String bookmarkTitle = "";
		if (modifyValues == true) {
			bookmarkTitle = fillBookmarkForm(typist, makePrivate);
		}
		else {
			bookmarkTitle = sel.getValue(DogearObjects.Form_AddBookmark_Title);
			if (sel.getValue(DogearObjects.Form_AddBookmark_Tags).equalsIgnoreCase("") && sel.isElementPresent("css=div#popularTags a:nth(0)")) {
				System.out.println(sel.isElementPresent(DogearObjects.Form_AddBookmark_PopularTag1));
				cautiousClick(DogearObjects.Form_AddBookmark_PopularTag1);
			}
		}

		sel.click(DogearObjects.Form_AddBookmark_Save);
		sel.selectWindow(null);
		sleep(5000);

		return bookmarkTitle;
	}

	private String fillBookmarkForm(FormInputHandler typist, boolean makePrivate) {

		String rand = genDateBasedRandVal();
		String bookmarkTitle = "title" + rand;
		typist.type(DogearObjects.Form_AddBookmark_Title, bookmarkTitle);
		typist.type(DogearObjects.Form_AddBookmark_Url, "url" + rand, null);
		typist.type(DogearObjects.Form_AddBookmark_Tags, "tag" + rand, "tags");
		typist.type(DogearObjects.Form_AddBookmark_Description, "description" + rand);

		if (makePrivate) {
			sel.click(DogearObjects.Form_AddBookmark_Radio_Private);
		}
		else {
			sel.click(DogearObjects.Form_AddBookmark_Radio_Public);
		}

		return bookmarkTitle;
	}

	public void verifyMainPages() {

		//My updates
		clickLink(DogearObjects.Nav_MyUpdates);
		CheckForErrorsOnPage();
		clickLink(DogearObjects.MyUpdates_NotificationsSent);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Tags_Title));
		clickLink(DogearObjects.MyUpdates_NotificationsReceived);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Tags_Title));
		clickLink(DogearObjects.MyUpdates_MyWatchlist);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Tags_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Watchlist_Title));

		//Popular
		clickLink(DogearObjects.Nav_Popular);
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Tags_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_MostBookmarked_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_MostNotified_Title));

		//Public Bookmarks
		clickLink(DogearObjects.Nav_PublicBookmarks);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Tags_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_MostVisited_Title));

		//Bookmarklet page
		clickLink(DogearObjects.Nav_HowToBookmark);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(DogearObjects.Bookmarklet_Bookmarklet1));
		Assert.assertTrue(sel.isVisible(DogearObjects.Bookmarklet_Bookmarklet2));

		//Add bookmark using same-page link.	
		//TODO: The bookmarklet links can be clicked in firefox but not in IE
		/*
		 * clickLink(DogearObjects.Bookmarklet_Bookmarklet2);
		 * Assert.assertTrue(sel.isVisible(DogearObjects.Form_AddBookmark_Url +
		 * "[value*='/dogear/nav/toolbox?appName=dogear']")); clickLink(DogearObjects.Form_AddBookmark_Save); //Check
		 * that redirect was back to how-to bookmarklet page
		 * Assert.assertTrue(sel.isVisible(DogearObjects.Bookmarklet_Bookmarklet1));
		 */

		//My bookmarks
		clickLink(DogearObjects.Nav_MyBookmarks);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Notifications_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Tags_Title));
		Assert.assertTrue(sel.isVisible(DogearObjects.StreamWindows_Watchlist_Title));

		/*
		 * //Check bookmarklet bookmark was saved, appears in My Bookmarks, and directs to correct page
		 * clickLink("css=a:contains('Bookmarks Tools')");
		 * Assert.assertTrue(sel.isVisible(DogearObjects.Bookmarklet_Bookmarklet1));
		 * 
		 * //Go back to My Bookmarks clickLink(DogearObjects.Nav_MyBookmarks);
		 */
	}

	public void verifyBookmarkPresence(ArrayList<String> assertList, boolean inMyBookmarks, boolean inPublicBookmarks) {

		if (sel.isElementPresent(DogearObjects.MyBookmarks_Header) != true) {
			clickLink(DogearObjects.Nav_MyBookmarks);
		}

		if (inMyBookmarks) {
			//Expand all bookmarks that should be present
			clickLink(DogearObjects.BookmarksList_DetailsView);
			//Verify bookmark form entries were saved and are now displayed on My Bookmarks
			assertAllTextPresent(assertList);
		}
		else {
			assertAllTextNotPresent(assertList);
		}

		//Go to public bookmarks and verify bookmark form entries are now displayed if bookmark was not private
		clickLink(DogearObjects.Nav_PublicBookmarks);

		//TODO: This is to work around bookmarks bug in firefox3.6.22. Remove once test complete.
		//sel.refresh(); 
		//sel.waitForPageToLoad("10000");

		if (inPublicBookmarks) {
			//Expand all bookmarks
			clickLink(DogearObjects.BookmarksList_DetailsView);
			//Verify bookmark form entries were saved and are now displayed on My Bookmarks
			assertAllTextPresent(assertList);
		}
		else {
			assertAllTextNotPresent(assertList);
		}
	}

	public void toggleAddToOwnWatchlist() {

		if (sel.isElementPresent(DogearObjects.MyBookmarks_Header) != true) {
			clickLink(DogearObjects.Nav_MyBookmarks);
		}

		if (sel.isElementPresent(DogearObjects.MyBookmarks_AddToWatchlist)) {
			clickLink(DogearObjects.MyBookmarks_AddToWatchlist);
		}
		else {
			clickLink(DogearObjects.MyBookmarks_RemoveFromWatchlist);
		}

	}

	public void addToMyBookmarks() {

		editBookmark(false, null, false);
	}

	public void deleteBookmark() {

		sel.setSpeed("500");
		sel.click(DogearObjects.MyBookmarks_DeleteBookmark1);
		sel.click(DogearObjects.MyBookmarks_Delete);
		String message = sel.getConfirmation();
		AssertJUnit.assertTrue("FAIL: Delete confirmation message not as expected", message.endsWith("Are you sure you wish to delete it?"));
	}
}
