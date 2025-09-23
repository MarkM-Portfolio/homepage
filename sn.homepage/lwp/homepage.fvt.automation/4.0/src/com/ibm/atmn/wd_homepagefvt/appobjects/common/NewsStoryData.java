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
	public static String CREATE_PUBLIC_ACTIVTY = " created a public activty named PLACEHOLDER.";
	
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
	public static String CREATE_DRAFT_BLOG_ENTRY = "USER created a draft blog entry named PLACEHOLDER in the REPLACE_THIS blog.";
	public static String UPDATE_BLOG_ENTRY = "USER updated the PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String ADD_COMMENT_BLOG_ENTRY = "USER commented on their own PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String ADD_TB_BLOG_ENTRY = "USER left a trackback on their own PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String LIKE_BLOG_ENTRY = "USER liked their own PLACEHOLDER blog entry in the REPLACE_THIS blog.";
	public static String LIKE_BLOG_COMMENT = "USER liked their own comment on PLACEHOLDER.";
	
	
	public static String CREATE_COMM_BLOG = "USER added a blog to the PLACEHOLDER community.";
	
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
	
	//Forums Strings
	public static String CREATE_FORUM = " created the PLACEHOLDER forum.";
	public static String UPDATE_FORUM = " updated the PLACEHOLDER forum.";
	public static String CREATE_TOPIC = " created a topic named PLACEHOLDER in the REPLACE_THIS forum.";
	public static String UPDATE_TOPIC = " updated the PLACEHOLDER topic in the REPLACE_THIS forum.";
	public static String CREATE_REPLY = " replied to the Re: PLACEHOLDER topic thread in the REPLACE_THIS forum.";
	public static String UPDATE_REPLY = " updated the PLACEHOLDER topic thread in the REPLACE_THIS forum.";
	
	public static String MY_NOTIFICATIONS_CREATE_REPLY = " replied to the Re: PLACEHOLDER topic thread started by you in the REPLACE_THIS forum.";

	//Profiles
	public static String MY_NOTIFICATIONS_NETWORK_INVITE_FROM_ME = "You invited USER to join your network.";
	public static String MY_NOTIFICATIONS_NETWORK_INVITE_FOR_ME = "USER invited you to become a network contact.";
	
	//Wikis Strings
	public static String CREATE_WIKI = " created a wiki named PLACEHOLDER.";
	
	public static String MY_NOTIFICATIONS_WIKI_MEMBER_ADDED_FROM_ME = "You notified USER that they have been added to the PLACEHOLDER wiki.";
	public static String MY_NOTIFICATIONS_WIKI_MEMBER_ADDED_FOR_ME = "USER notified you that you have been added to the PLACEHOLDER wiki.";
	

	
}    
