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

package com.ibm.atmn.homepagefvt.tasks.dogear;


import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.appobjects.dogear.FVT_DogearObjects;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import org.junit.*;

public class FVT_DogearMethods extends com.ibm.atmn.homepagefvt.tasks.news.FVT_CommonNewsMethods {

	public String addBookmark(FormInputHandler typist) {
		return addBookmark(typist, false);
	}

	public String addBookmark(FormInputHandler typist, boolean makePrivate) {

		//Open popup window with form
		sel.click(FVT_DogearObjects.Nav_AddBookmark);
		return useBookmarkForm(typist, makePrivate);

	}

	public String editBookmark(FormInputHandler typist, boolean makePrivate) {
		return editBookmark(true, typist, makePrivate);
	}

	public String editBookmark(boolean modifyValues, FormInputHandler typist, boolean makePrivate) {

		clickLink(FVT_DogearObjects.BookmarksList_DetailsView);
		//Open popup window with form
		sel.click(FVT_DogearObjects.BookmarksList_EditOrAddToMyBookmarks1);
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
		} else {
			bookmarkTitle = sel.getValue(FVT_DogearObjects.Form_AddBookmark_Title);
			if (sel.getValue(FVT_DogearObjects.Form_AddBookmark_Tags).equalsIgnoreCase("")
					&& sel.isElementPresent("css=div#popularTags a:nth(0)")) {
				System.out.println(sel.isElementPresent(FVT_DogearObjects.Form_AddBookmark_PopularTag1));
				cautiousClick(FVT_DogearObjects.Form_AddBookmark_PopularTag1);
			}
		}

		sel.click(FVT_DogearObjects.Form_AddBookmark_Save);
		sel.selectWindow(null);
		sleep(5000);

		return bookmarkTitle;
	}

	private String fillBookmarkForm(FormInputHandler typist, boolean makePrivate) {

		String rand = genDateBasedRandVal();
		String bookmarkTitle = "FVT Bookmark " + rand;
		typist.type(FVT_DogearObjects.Form_AddBookmark_Title, bookmarkTitle);
		typist.type(FVT_DogearObjects.Form_AddBookmark_Url, "url" + rand, null);
		typist.type(FVT_DogearObjects.Form_AddBookmark_Tags, "fvttesttag");
		typist.type(FVT_DogearObjects.Form_AddBookmark_Description, "description" + rand);

		if (makePrivate) {
			sel.click(FVT_DogearObjects.Form_AddBookmark_Radio_Private);
		} else {
			sel.click(FVT_DogearObjects.Form_AddBookmark_Radio_Public);
		}

		return bookmarkTitle;
	}

	public void verifyMainPages() {

		//My updates
		clickLink(FVT_DogearObjects.Nav_MyUpdates);
		CheckForErrorsOnPage();
		clickLink(FVT_DogearObjects.MyUpdates_NotificationsSent);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Tags_Title));
		clickLink(FVT_DogearObjects.MyUpdates_NotificationsReceived);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Tags_Title));
		clickLink(FVT_DogearObjects.MyUpdates_MyWatchlist);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Tags_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Watchlist_Title));

		//Popular
		clickLink(FVT_DogearObjects.Nav_Popular);
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Tags_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_MostBookmarked_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_MostNotified_Title));

		//Public Bookmarks
		clickLink(FVT_DogearObjects.Nav_PublicBookmarks);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Tags_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_MostVisited_Title));

		//Bookmarklet page
		clickLink(FVT_DogearObjects.Nav_HowToBookmark);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.Bookmarklet_Bookmarklet1));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.Bookmarklet_Bookmarklet2));

		//Add bookmark using same-page link.	
		//TODO: The bookmarklet links can be clicked in firefox but not in IE
		/*
		 * clickLink(FVT_DogearObjects.Bookmarklet_Bookmarklet2);
		 * Assert.assertTrue(sel.isVisible(FVT_DogearObjects.Form_AddBookmark_Url +
		 * "[value*='/dogear/nav/toolbox?appName=dogear']")); clickLink(FVT_DogearObjects.Form_AddBookmark_Save); //Check
		 * that redirect was back to how-to bookmarklet page
		 * Assert.assertTrue(sel.isVisible(FVT_DogearObjects.Bookmarklet_Bookmarklet1));
		 */

		//My bookmarks
		clickLink(FVT_DogearObjects.Nav_MyBookmarks);
		CheckForErrorsOnPage();
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Notifications_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_People_Search));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Tags_Title));
		Assert.assertTrue(sel.isVisible(FVT_DogearObjects.StreamWindows_Watchlist_Title));

		/*
		 * //Check bookmarklet bookmark was saved, appears in My Bookmarks, and directs to correct page
		 * clickLink("css=a:contains('Bookmarks Tools')");
		 * Assert.assertTrue(sel.isVisible(FVT_DogearObjects.Bookmarklet_Bookmarklet1));
		 * 
		 * //Go back to My Bookmarks clickLink(FVT_DogearObjects.Nav_MyBookmarks);
		 */
	}

	public void verifyBookmarkPresence(ArrayList<String> assertList, boolean inMyBookmarks, boolean inPublicBookmarks) {
		if (sel.isElementPresent(FVT_DogearObjects.MyBookmarks_Header) != true) {
			clickLink(FVT_DogearObjects.Nav_MyBookmarks);
		}

		if (inMyBookmarks) {
			//Expand all bookmarks that should be present
			clickLink(FVT_DogearObjects.BookmarksList_DetailsView);
			//Verify bookmark form entries were saved and are now displayed on My Bookmarks
			assertAllTextPresent(assertList);
		} else {
			assertAllTextNotPresent(assertList);
		}

		//Go to public bookmarks and verify bookmark form entries are now displayed if bookmark was not private
		clickLink(FVT_DogearObjects.Nav_PublicBookmarks);

		//TODO: This is to work around bookmarks bug in firefox3.6.22. Remove once test complete.
		//sel.refresh(); 
		//sel.waitForPageToLoad("10000");

		if (inPublicBookmarks) {
			//Expand all bookmar
			
			clickLink(FVT_DogearObjects.BookmarksList_DetailsView);
			//Verify bookmark form entries were saved and are now displayed on My Bookmarks
			assertAllTextPresent(assertList);
		} else {
			assertAllTextNotPresent(assertList);
		}
	}

	public void toggleAddToOwnWatchlist() {
		if (sel.isElementPresent(FVT_DogearObjects.MyBookmarks_Header) != true) {
			clickLink(FVT_DogearObjects.Nav_MyBookmarks);
		}

		if (sel.isElementPresent(FVT_DogearObjects.MyBookmarks_AddToWatchlist)) {
			clickLink(FVT_DogearObjects.MyBookmarks_AddToWatchlist);
		} else {
			clickLink(FVT_DogearObjects.MyBookmarks_RemoveFromWatchlist);
		}

	}

	public void toggleAddPersonToWatchlist(String personName) throws Exception {
		
		final FormInputHandler typist = getFormInputHandler();
		
		clickLink(FVT_DogearObjects.Nav_PublicBookmarks);
		
		clickLink(FVT_DogearObjects.StreamWindows_People_Search);
		
		
		typist.typeAndWait(FVT_DogearObjects.StreamWindows_Focus_People_Search,personName.replace(
				"ajones", "Amy Jones"), null);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_DOWN));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		
		Thread.sleep(2000);
		
		if (sel.isElementPresent(FVT_DogearObjects.PublicBookmarks_AddToWatchlist) == true) {
			cautiousClick(FVT_DogearObjects.PublicBookmarks_AddToWatchlist);

		} else if (sel.isElementPresent(FVT_DogearObjects.PublicBookmarks_AddToWatchlist) != true){
			System.out.println("Already added to watchlist!");
		}

		
	}

	public void toggleAddTagToWatchlist(String tagName) throws Exception {
		
		final FormInputHandler typist = getFormInputHandler();
		
		clickLink(FVT_DogearObjects.Nav_PublicBookmarks);
		
		if (sel.isElementPresent(FVT_DogearObjects.StreamWindows_Focus_Tag_Search)) {
			clickLink(FVT_DogearObjects.StreamWindows_Focus_Tag_Search);
		}else{
			clickLink(FVT_DogearObjects.StreamWindows_Open_Tag_Search);
			clickLink(FVT_DogearObjects.StreamWindows_Focus_Tag_Search);

		}
			
		typist.typeAndWait(FVT_DogearObjects.StreamWindows_Type_Tag_Search,tagName, null);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_DOWN));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		
		Thread.sleep(2000);
		
		if (sel.isElementPresent(FVT_DogearObjects.PublicBookmarks_AddToWatchlist) == true) {
			cautiousClick(FVT_DogearObjects.PublicBookmarks_AddToWatchlist);
			} else if (sel.isElementPresent(FVT_DogearObjects.PublicBookmarks_AddToWatchlist) != true) {
			System.out.println("Already added to watchlist!");
		}

		
	}
	
	
	public void addToMyBookmarks() {
		editBookmark(false, null, false);
	}

	public void deleteBookmark() {
		sel.setSpeed("500");
		sel.click(FVT_DogearObjects.MyBookmarks_DeleteBookmark1);
		sel.click(FVT_DogearObjects.MyBookmarks_Delete);
		String message = sel.getConfirmation();
		Assert.assertTrue("FAIL: Delete confirmation message not as expected", message
				.endsWith("Are you sure you wish to delete it?"));
	}
}
