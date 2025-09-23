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

package com.ibm.atmn.wd_homepagefvt.appobjects.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Properties;

import com.ibm.atmn.wd_homepagefvt.appobjects.files.FilesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;


@SuppressWarnings("unused")
public class NewsStoryData extends Initialize{
	

	
	//Activities Strings
	public static String CREATE_PUBLIC_ACTIVTY = " created a public activity named PLACEHOLDER.";
	public static String MAKE_ACTIVTY_PUBLIC = "USER made the PLACEHOLDER activity public.";
	public static String CREATE_PRIVATE_ACTIVTY = " created a private activity named PLACEHOLDER.";
	public static String CREATE_ACTIVITY = "USER created an activity named PLACEHOLDER.";
	public static String CREATE_ACTIVITY_ENTRY = "USER created the PLACEHOLDER entry in the REPLACE_THIS activity.";
	public static String COMMENT_ON_ACTIVITY = "USER commented on the PLACEHOLDER entry thread in the REPLACE_THIS activity.";
	public static String CREATE_TODO_ITEM = "USER created a to-do item named PLACEHOLDER in the REPLACE_THIS activity.";
	public static String ASSIGNED_TODO_ITEM = "USER assigned themselves a to-do item named PLACEHOLDER in the REPLACE_THIS activity.";
	public static String ASSIGNED_YOU_TODO = "USER assigned you a to-do item named PLACEHOLDER in the REPLACE_THIS activity.";
	public static String COMPLETE_TODO_ITEM = "USER completed their own PLACEHOLDER to-do item in the REPLACE_THIS activity.";
	public static String COMPLETE_A_TODO_ITEM = "USER completed the PLACEHOLDER to-do item in the REPLACE_THIS activity.";
	public static String CREATE_SECTION = "USER created a section in the PLACEHOLDER.";
	public static String MAKE_ACTIVITY_PUBLIC = "USER made the PLACEHOLDER activity public.";
	public static String UPDATE_ACTIVITY_ENTRY = "USER updated the PLACEHOLDER entry thread in the REPLACE_THIS activity.";

	
	public static String MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FROM_ME = "You notified USER that they were added to the activity named PLACEHOLDER.";
	public static String MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FOR_ME = "USER notified you that you were added to the activity named PLACEHOLDER.";
	public static String MY_NOTIFICATIONS_ACTIVITY_NOTIFY_ENTRY_FROM_ME = "You notified USER and 1 others about the activity entry named PLACEHOLDER.";
	public static String MY_NOTIFICATIONS_ACTIVITY_NOTIFY_ENTRY_FOR_ME = "USER notified you and 1 others about the activity entry named PLACEHOLDER.";

	
	//Blogs Strings
	public static String MY_NOTIFICATIONS_BLOG_ENTRY_NOTIFY_FROM_ME = "You notified USER about the PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String MY_NOTIFICATIONS_BLOG_ENTRY_NOTIFY_FOR_ME = "USER notified you about the PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String MY_NOTIFICATIONS_BLOG_ADD_AUTHOR_FROM_ME = "You added USER as an author of the PLACEHOLDER blog.";
	public static String MY_NOTIFICATIONS_BLOG_ADD_AUTHOR_FOR_ME = "USER added you as an author of the PLACEHOLDER blog.";
	public static String MY_NOTIFICATIONS_BLOG_ADD_OWNER_FROM_ME = "You added USER as an owner of the PLACEHOLDER blog.";
	public static String MY_NOTIFICATIONS_BLOG_ADD_OWNER_FOR_ME = "USER added you as an owner of the PLACEHOLDER blog.";
	public static String MY_NOTIFICATIONS_BLOG_ADD_DRAFT_FROM_ME = "You added USER as a draft contributor of the PLACEHOLDER blog.";
	public static String MY_NOTIFICATIONS_BLOG_ADD_DRAFT_FOR_ME = "USER added you as a draft contributor of the PLACEHOLDER blog.";

	public static String CREATE_BLOG = "USER created a blog named PLACEHOLDER.";
	public static String CREATE_BLOG_ENTRY = "USER created a blog entry named PLACEHOLDER in the REPLACE_THIS blog.";
	public static String CREATE_IDEATION_BLOG = "USER created the PLACEHOLDER community Ideation Blog.";
	public static String CREATE_IDEATION_BLOG_ENTRY = "USER created the PLACEHOLDER entry in the REPLACE_THIS Ideation Blog.";
	public static String CREATE_IDEATION_BLOG_IDEA = "USER created the PLACEHOLDER idea in the REPLACE_THIS Ideation Blog.";
	public static String UPDATE_IDEATION_BLOG_IDEA = "USER updated the PLACEHOLDER idea in the REPLACE_THIS Ideation Blog.";
	public static String COMMENT_IDEATION_BLOG_IDEA = "USER commented on their own PLACEHOLDER idea in the REPLACE_THIS Ideation Blog.";
	public static String CREATE_TRACKBACK_IDEATION = "USER left a trackback on their own PLACEHOLDER idea in the REPLACE_THIS Ideation Blog.";
	public static String GRADUATE_IDEA = "USER graduated their own PLACEHOLDER idea in the REPLACE_THIS Ideation Blog.";
	public static String VOTE_FOR_BLOG = "USER voted for the PLACEHOLDER idea in the REPLACE_THIS Ideation Blog."; 
	public static String CREATE_DRAFT_BLOG_ENTRY = "USER created a draft blog entry named PLACEHOLDER in the REPLACE_THIS blog.";
	public static String UPDATE_BLOG_ENTRY = "USER updated the PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String ADD_COMMENT_BLOG_ENTRY = "USER commented on their own PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String ADD_TB_BLOG_ENTRY = "USER left a trackback on their own PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String LIKE_BLOG_ENTRY = "USER liked their own PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String LIKE_BLOG_COMMENT = "USER liked their own comment on PLACEHOLDER.";
	public static String VOTE_FOR_OWN_IDEA = "USER voted for their own PLACEHOLDER idea in the REPLACE_THIS Ideation Blog.";
	
	public static String CREATE_COMM_BLOG = "USER created the PLACEHOLDER community blog.";
	
	//Bookmarks
	public static String MY_NOTIFICATIONS_BOOKMARK_NOTIFY_FROM_ME = "You notified USER about the following bookmarks:";
	public static String MY_NOTIFICATIONS_BOOKMARK_NOTIFY_FOR_ME = "USER notified you about the following bookmarks:";
	public static String MY_NOTIFICATIONS_BOOKMARK_BROKEN_FROM_ME = "You notified USER that the URL for the bookmark named PLACEHOLDER is broken.";
	public static String MY_NOTIFICATIONS_BOOKMARK_BROKEN_FOR_ME = "USER notified you that the URL for the bookmark named PLACEHOLDER is broken.";
	
	//Communities
	public static String MY_NOTIFICATIONS_COMMUNITY_INVITE_FROM_ME = "You invited USER to join the PLACEHOLDER community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_INVITE_FOR_ME = "USER invited you to join the PLACEHOLDER community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_REQUEST_FROM_ME = "USER has requested to join the PLACEHOLDER community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_REQUEST_FOR_ME = "USER has requested to join your PLACEHOLDER community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_ADD_MEMBER_FROM_ME = "You added USER to the PLACEHOLDER community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_ADD_MEMBER_FOR_ME = "USER added you to the PLACEHOLDER community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_REMOVE_MEMBER_FROM_ME = "You removed USER from the PLACEHOLDER community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_REMOVE_MEMBER_FOR_ME = "USER removed you from the PLACEHOLDER community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_EVENT_INVITE_FROM_ME = "You notified USER about the community event PLACEHOLDER in the REPLACE_THIS community.";
	public static String MY_NOTIFICATIONS_COMMUNITY_EVENT_INVITE_FOR_ME = "USER notified you about community event PLACEHOLDER in the REPLACE_THIS community.";
	
	public static String CREATE_COMMUNITY = "USER created a community named PLACEHOLDER";
	public static String ADD_COMMUNITY_BOOKMARK = "USER added the PLACEHOLDER bookmark to the REPLACE_THIS community.";
	public static String ADD_COMMUNITY_FEED = "USER added the PLACEHOLDER feed to the REPLACE_THIS community.";
	public static String UPDATE_COMMUNITY_BOOKMARK = "USER updated the PLACEHOLDER bookmark in the REPLACE_THIS community.";
	
	//Community Calendar
	public static String CREATE_COMMUNITY_CALENDAR_EVENT = "USER created the event PLACEHOLDER in the REPLACE_THIS community";
	public static String UPDATE_COMMUNITY_CALENDAR_EVENT = "USER updated the event PLACEHOLDER in the REPLACE_THIS community.";
	public static String COMMENT_COMMUNITY_CALENDAR_EVENT = "USER commented on the event PLACEHOLDER in the REPLACE_THIS community.";
	public static String CREATE_COMMUNITY_CALENDAR_REPEATING_EVENT = "USER created a repeating event PLACEHOLDER in the REPLACE_THIS community";
	public static String UPDATE_COMMUNITY_CALENDAR_REPEATING_EVENT = "USER updated the repeating event PLACEHOLDER in the REPLACE_THIS community.";
	public static String UPDATE_COMMUNITY_CALENDAR_EVENT_INSTANCE = "USER updated an instance of the repeating event PLACEHOLDER in the REPLACE_THIS community.";
	
	//Dogear
	public static String CREATE_BOOKMARK = "USER created a bookmark named PLACEHOLDER.";
	public static String UPDATE_BOOKMARK = "USER updated the bookmark named PLACEHOLDER.";
	public static String ADD_BOOKMARK_WATCHLIST = "USER added to their Bookmarks watchlist.";
	public static String ADD_TAG = "USER added the following tag to their Bookmarks watchlist: PLACEHOLDER.";
	public static String TAG_NAME = "fvttesttag";

	
	//Forums Strings
	public static String CREATE_FORUM = "USER created the PLACEHOLDER forum.";
	public static String UPDATE_FORUM = "USER updated the PLACEHOLDER forum.";
	public static String CREATE_TOPIC = "USER created a topic named PLACEHOLDER in the REPLACE_THIS forum.";
	public static String UPDATE_TOPIC = " updated the PLACEHOLDER topic in the REPLACE_THIS forum.";
	public static String CREATE_REPLY = "USER replied to the PLACEHOLDER topic thread in the REPLACE_THIS forum.";
	public static String UPDATE_REPLY = " updated the PLACEHOLDER topic in the REPLACE_THIS forum.";
	public static String UPDATE_REPLY_THREAD = " updated the PLACEHOLDER topic thread in the REPLACE_THIS forum.";
	public static String UPDATE_REPLY_RE = " updated the Re: PLACEHOLDER topic in the REPLACE_THIS forum.";
	
	public static String MY_NOTIFICATIONS_CREATE_REPLY = " replied to the Re: PLACEHOLDER topic thread started by you in the REPLACE_THIS forum.";

	//Profiles
	public static String MY_NOTIFICATIONS_NETWORK_INVITE_FROM_ME = "You invited USER to join your network.";
	public static String MY_NOTIFICATIONS_NETWORK_INVITE_FOR_ME = "USER invited you to become a network contact.";
	public static String PROFILE_INFO_CHANGED = "USER's profile information changed.";
	public static String MULTI_PROFILE_CHANGE = "The following people updated their profile information:";
	
	//Wikis Strings
	public static String CREATE_WIKI = "USER created a wiki named PLACEHOLDER.";
	public static String CREATE_WIKI_PAGE = "USER created a wiki page named PLACEHOLDER in the REPLACE_THIS wiki.";
	public static String EDIT_WIKI_PAGE = "USER edited the wiki page PLACEHOLDER in the REPLACE_THIS wiki.";
	public static String TAG_WIKI_PAGE = "USER tagged the wiki page Welcome to PLACEHOLDER with REPLACE_THIS in the REPLACE_THIS_TOO wiki.";
	public static String LIKE_WIKI_PAGE = "USER liked the wiki page PLACEHOLDER in the REPLACE_THIS wiki.";
	public static String LIKE_WIKI_WELCOME = "USER liked the wiki page Welcome to PLACEHOLDER in the REPLACE_THIS wiki.";
	public static String COMMENT_WIKI_PAGE = "USER commented on the wiki page PLACEHOLDER in the REPLACE_THIS wiki.";
	public static String COMMENT_WIKI_WELCOME = "USER commented on the wiki page Welcome to PLACEHOLDER in the REPLACE_THIS wiki.";
	public static String CREATE_WIKI_COMMUNITY = "A PLACEHOLDER community wiki was created.";
	public static String UPDATE_WIKI = "USER updated the wiki page PLACEHOLDER in the REPLACE_THIS wiki.";
	public static String UPDATE_WIKI_PAGE = "USER updated the wiki page Re: Welcome to PLACEHOLDER in the REPLACE_THIS wiki.";
	public static String UPDATE_WIKI_PAGE_NO_WELCOME = "USER updated the wiki page Re: PLACEHOLDER in the REPLACE_THIS wiki.";
	
	
	
	public static String MY_NOTIFICATIONS_WIKI_MEMBER_ADDED_FROM_ME = "You notified USER that they have been added to the PLACEHOLDER wiki.";
	public static String MY_NOTIFICATIONS_WIKI_MEMBER_ADDED_FOR_ME = "USER notified you that you have been added to the PLACEHOLDER wiki.";
	
	//@Mentions
	public static String MENTIONED_YOU = "USER mentioned you in a message.";
	public static String MENTIONED_YOU_COMMENT = "USER mentioned you in a comment on USER's message."; 
	public static String MENTIONED_YOU_COMMENT_COMM = "USER mentioned you in a comment on USER's message posted to the PLACEHOLDER community."; 
	public static String MENTIONED_YOU_MESSAGE_BOARD = "USER mentioned you in a message posted to PLACEHOLDER.";
	public static String MENTIONED_YOU_COMMUNITY = "USER mentioned you in a message posted to the PLACEHOLDER community.";
	public static String MENTIONED_YOU_BOARD_MESSAGE_COMMENT = "USER mentioned you in a comment on USER's message posted to PLACEHOLDER";
	

	
}    
