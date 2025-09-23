/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.appobjects.dogear;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;

public class FVT_DogearObjects extends CommonObjects {

	//Add bookmark
	public static String Nav_AddBookmark = "css=div#editActions.lotusActionBar span span.lotusBtn a:contains('Add a Bookmark')";
	public static String Form_AddBookmark_Title = "css=input#title";
	public static String Form_AddBookmark_Url = "css=input#urlField";
	public static String Form_AddBookmark_Tags = "css=input#ptags";
	public static String Form_AddBookmark_Description = "css=textarea#rteDiv";
	public static String Form_AddBookmark_Radio_Public = "css=input#publicDogear";
	public static String Form_AddBookmark_Radio_Private = "css=input#privateDogear";
	public static String Form_AddBookmark_Save = "css=input#submitBtn";
	public static String Form_AddBookmark_PopularTag1 = "css=div#popularTags a:nth(0)";
	
	//Import
	public static String Nav_ImportBookmarks = "css=a:contains('Import Bookmarks')";
	
	//Nav
	public static String Nav_MyBookmarks = "css=a:contains('My Bookmarks')";
	public static String Nav_PublicBookmarks = "css=a:contains('Public Bookmarks')";
	public static String Nav_Popular = "css=a:contains('Popular')";
	public static String Nav_MyUpdates = "css=a:contains('My Updates')";
	public static String Nav_BookmarksHome_ExpandDetails_Bookmark1 = "css=a#showMore_1";
	public static String Nav_BookmarksHome_HideDetails_Bookmark1= "css=a#hideMore_1";
	public static String Nav_HowToBookmark = "css=a:contains('How to Bookmark')";
	public static String Notify_People = "css=a[id^=Notify_].lotusAction";
	public static String Notify_Name = "css=input#notifyEmails.lotusText";
	
	//Bookmarket: Button page and extra bottom tabs not present on add bookmark form
	public static String Bookmarklet_Bookmarklet1 = "css=a[id$=bookmarklet1]";
	public static String Bookmarklet_Bookmarklet2 = "css=a[id$=bookmarklet2]";
	
	//Stream Windows
	public static String StreamWindows_Notifications_Title = "css=h2#notificationPanelTitle > span";
	public static String StreamWindows_Watchlist_Title = "css=span#dogearSubsTitle";
	public static String StreamWindows_Tags_Title = "css=span#dogearTagTitle";
	public static String StreamWindows_People_Title = "css=span#dogearPeopleTitle";
	public static String StreamWindows_People_Search = "css=a#peopleSearchLink";
	public static String StreamWindows_MostBookmarked_Title = "css=a#mbImage ~ span";
	public static String StreamWindows_MostNotified_Title = "css=span#mnTitle";
	public static String StreamWindows_MostVisited_Title = "css=span#mostVisitedLabel";
	
	//My Updates
	public static String MyUpdates_MyWatchlist = "css=a[href*='/dogear/html/inbox']";
	public static String MyUpdates_NotificationsReceived = "css=a[href*='/dogear/html/mynotifications']";
	public static String MyUpdates_NotificationsSent = "css=a[href*='/dogear/html/mysentnotifications']";
	
	//My Bookmarks
	public static String MyBookmarks_AddToWatchlist = "css=a#subscribe2";
	public static String MyBookmarks_RemoveFromWatchlist = "css=a#subscribe1";
	public static String MyBookmarks_Header = "css=h1:contains('My Bookmarks')";
	public static String MyBookmarks_DeleteBookmark1 = "css=input#t1";
	public static String MyBookmarks_DeleteBookmark2 = "css=input#t2";
	public static String MyBookmarks_Delete = "css=a:contains('Delete Selected')";
	
	//My bookmarks and Public Bookmarks
	public static String BookmarksList_DetailsView = "css=a#bkdetailsLink";
	public static String BookmarksList_EditOrAddToMyBookmarks1 = "css=a#link_1";
	public static String BookmarksList_EditOrAddToMyBookmarks2 = "css=a#link_2";
	
	
	public static String PublicBookmarks_AddToWatchlist = "css=a#subscribe2.lotusAction";
	//public static String PublicBookmarks_AddToWatchlist = "css=a:contains('Add To Watchlist')";
	public static String PublicBookmarks_RemoveFromWatchlist = "css=a#subscribe1.lotusAction";
	
	public static String StreamWindows_Focus_People_Search = "css=input#nameinput.lotusText";
	
	public static String StreamWindows_Open_Tag_Search = "css=a#dogearTagImage.lotusSprite";
	public static String StreamWindows_Focus_Tag_Search = "css=a.lotusBold";
	public static String StreamWindows_Type_Tag_Search = "css=input#lconnTagWidgetcommonTagsTypeAhead.lotusText";

	public static String NotifyDropDown = "css=li#notifyEmails_popup_searchDir.dijitMenuItem";
	public static String NotifyDropDownPerson = "css=li[id^='notifyEmails_popup]";
	public static String NotifyDropDownPerson1 = "css=*[id$='notifyEmails_popup'] li:nth(1)";
	
	public static String NotifyButton = "css=input[value='Notify']";
	
	//Edit
	
	public static String MoreButton = "css=a[id^=showMore]";
	public static String EditButton = "css=a[id^=link_]:contains('Edit')";
	
	public static String MORE_ACTIONS = "css=a[id^=MoreActions_]";
	public static String FLAG_BROKEN = "css=td:contains('Flag as Broken URL')";
	public static String BROKEN_SUBMIT = "css=input[id='brokenSubmit']";
	
}