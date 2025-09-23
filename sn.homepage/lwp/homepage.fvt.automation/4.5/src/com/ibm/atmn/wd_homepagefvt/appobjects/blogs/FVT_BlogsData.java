/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.appobjects.blogs;

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
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;

@SuppressWarnings("unused")
public class FVT_BlogsData {
	
	//Generate a random number from date & time
	/*public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }*/
	
	//FVT Blog Names & Addresses
	
	public static final String ModeratedCommunityBlog = "FVT Moderated Community Blog ";
	public static final String PrivateCommunityBlog = "FVT Private Community Blog ";
	public static final String PublicCommunityBlog = "FVT Public Community Blog ";
	public static final String StandaloneBlog = "FVT Standalone Blog ";
	
	
	public static final String ModBlogsAddress= "ModBlogAddress";
	public static final String PrivateBlogsAddress= "PrivateBlogAddress";
	public static final String PublicBlogsAddress= "PublicBlogAddress";
	public static final String StandaloneBlogsAddress= "StandaloneBlogAddress";
	
	
	
	public static final String BlogsName1 = "First Blogs Level 2 test ";
	public static final String BlogsAddress1= "Test1Address";
	public static final String BlogsName2 = "Second Blogs Level 2 test ";
	public static final String BlogsAddress2= "Test2Address";
	public static final String BlogsName3 = "Third Blogs Level 2 test ";
	public static final String BlogsAddress3= "Test3Address";
	public static final String BlogsName4 = "Fourth Blogs Level 2 test ";
	public static final String BlogsAddress4= "Test4Address";
	public static final String BlogsTag = "automation";
	public static final String BlogsDescription = "This is a test description of this FVT Blog";
	public static final String BlogsTimeZoneOption = "(GMT) Greenwich Mean Time : Dublin, Edinburgh, Lisbon, London";
	public static final String BlogsThemeOption = "Blog_with_Bookmarks";
	
	//Data for Search
	public static final String BlogsNameForSearch = "Blog for Search FVT 12345";
	public static final String BlogsAddressForSearch = "SearchBlogAddress12345";
	public static final String BlogsDescriptionForSearch = "This is a blog created for the search bvt - for laying down the data";
	public static final String BlogsTagForSearch = "searchblogtag12345";
	
	public static final String BlogsNewEntryTitle = "Entry for Level 2 Test " + CommonMethods.genDateBasedRandVal();
	public static final String BlogsSecondEntryTitle = "Second Entry for Level 2 Test " + CommonMethods.genDateBasedRandVal();
	public static final String BlogsNewEntryTag = "automation";
	public static final String BlogsNewEntryEntry = "This is a test entry for the level 2 blogs";
	public static final String BlogsNewUpdatedEntry = "This is an updated test entry for the level 2 blogs";
	
	
	public static final String BlogsHandleOfBlog = "Handle" + CommonMethods.genDateBasedRandVal();
	
	public static final String BlogsCommentText = "This is the test for the comment to be added to the entry in blogs - part of the BVT level 2 test";
	
	public static final String BlogsComment1 = "This is the first blogs comment"+CommonMethods.genDateBasedRandVal();;
	public static final String BlogsComment2 = "This is the second blogs comment"+CommonMethods.genDateBasedRandVal();;
	
	public static final String BlogFileAttachment1 = "Connections_BVT/CommonTestObjects/Desert.jpg";
	
	
	// Ideablog
	public static final String IdeablogNewEntryEntry = "This is a test idea for the level 2 ideablog";
	public static final String IdeablogNewEntryUpdated = "This is an updated test idea for the level 2 ideablog";
	
	//Users
	public static final String Author = "Author";
	public static final String Owner = "Owner";
	public static final String Draft = "Draft";
	public static final String Remove = "Remove";
	
	
}
