/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.homepagefvt.appobjects.news;


import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;

public class FVT_NewsData {
	
	public static final String UpdateStatus = "This is the FVT Level 2 Status update message "+CommonMethods.genDateBasedRandVal();
	public static final String StatusComment = "This is the FVT Level 2 status comment "+CommonMethods.genDateBasedRandVal();

	
	
	//For Adding Blogs
	public static final String BlogNameForNewsFVT = "News Level 2 BVT "+CommonMethods.genDateBasedRandVal();
	public static final String BlogsAddressForNewsFVT = "NewsAddress"+CommonMethods.genDateBasedRandVal();
	public static final String BlogsTagForNewsFVT = "FVTLevel2News";
	public static final String BlogsDescriptionForNewsFVT = "This is a test description for the level 2 bvt for news";
	public static final String BlogsTimeZoneOptionForNewsFVT = "(GMT) Greenwich Mean Time : Dublin, Edinburgh, Lisbon, London";
	public static final String BlogsThemeOptionForNewsFVT = "Blog_with_Bookmarks";
	public static final String BlogsNewEntryTitleForNewsFVT = "Entry for Level 2 Test for news "+CommonMethods.genDateBasedRandVal();
	public static final String BlogsSecondEntryTitleForNewsFVT = "Second Entry for Level 2 Test for news "+CommonMethods.genDateBasedRandVal();
	public static final String BlogsNewEntryTagForNewsFVT = "TestTagForNews";
	public static final String BlogsNewEntryEntryForNewsFVT = "This is a test entry for the level 2 blogs for the news bvt";
	public static final String BlogsHandleOfBlog = "Handle"+CommonMethods.genDateBasedRandVal();
	public static final String BlogsCommentText = "This is the test for the comment to be added to the entry in blogs - part of the FVT level 2 test for news";
	
	//URL of People view
	public static final String LoadPeopleView = "homepage/web/updates/#discover/all";
	
	

}
