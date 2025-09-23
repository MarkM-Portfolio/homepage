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

package com.ibm.atmn.wd_homepagefvt.appobjects.news;

import org.junit.Assert;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;


@SuppressWarnings("unused")
public class NewsObjects {
    
    //Generate a random number from date & time
	public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }
    
	//5 Tab options
	public static final String GettingStarted = "link=Getting Started";
	public static final String Updates= "link=Updates";
	public static final String ActivityStream = "link=Activity Stream";
	public static final String Widgets = "link=Widgets";
	public static final String Administration = "link=Administration";   
	
	//Updates
	public static final String LeftNavStatusUpdates = "//div[2]/div[2]/div/div/span[2]/span";
	public static final String LeftNavNewsFeed = "//div[2]/div[2]/div/span[2]/span";
	public static final String LeftNavNotifications = "//div[3]/div/span[2]/span";
	public static final String LeftNavDiscover = "//div[4]/div/span[2]/span";
	public static final String LeftNavSaved = "//div[5]/div/span[2]/span";
	public static final String NewsFeedLoadBlogs = "homepage/web/updates/#newsfeed/blogs";
	
	//Status Updates 
	public static final String ClickStatusUpdateLink = "css=*[class='lotusStatusInput']";
	public static final String EnterStatusUpdate = "css=textarea[id='updateStatusTextArea']";
	public static final String SaveStatusUpdate = "css=input[class='lotusFormButton']";
	
	
	//For Adding Blogs
	public static final String MyBlogs = "link=My Blogs";
		
	public static final String StartABlog = "link=Start a Blog";
	public static final String BlogsNewEntry = "link=New Entry";
	public static final String BlogsSettings = "css=span[class='LotusBtn' and a='Settings']";
	public static final String BlogsManageBlog = "link=Manage Blog";
	public static final String BlogsFollowThisBlog = "link=Follow this Blog";
	public static final String BlogsStopFollowingThisBlog = "link=Stop Following this Blog";

	public static final String BlogsNameField = "css=input#name.lotusText";
	public static final String BlogsAddress = "css=input[id='handle']";
	public static final String BlogsTags = "css=input[id='tagsAsString']";
	public static final String BlogsDescription = "css=textarea[id='description']";
	public static final String BlogsTimeZone = "css=select[id='timeZone']";
	public static final String BlogsTheme = "css=select[id='theme']";
	
	//New Entry
	public static final String BlogsNewEntryTitle = "css=input[id='title']";
	public static final String BlogsNewEntryAddTags = "link=Add Tags";
	public static final String BlogsNewEntryAddRemoveTags = "Add or Remove Tags";
	public static final String BlogsNewEntryAddTagsOK = "css=input[value='OK']";
	public static final String BlogsNewEntryAddTagsCancel = "link=Cancel";
	public static final String BlogsNewEntryAddTagsTextfield = "css=input[id='addtagwidgetAddTagsTypeAhead']";
	public static final String BlogsNewEntryTagsRemove = "css=a[class='blogsRemoveIcon']";
	public static final String BlogsNewEntryHTML = "link=HTML Source";
	public static final String BlogsNewEntryRichText = "css=textarea.cke_source.cke_enable_context_menu";
	public static final String BlogsNewEntryDelay = "css=input[id='delayedPub' and type='checkbox']";
	public static final String BlogsNewEntryHour = "css=select[title='Hours']";
	public static final String BlogsNewEntryMinutes = "css=select[title='Minutes']";
	public static final String BlogsNewEntryDate = "css=div[id='widget_publishDate']";
	public static final String BlogsNewEntryAllowComments = "css=input[id='allowCommentsCheck' and type='checkbox']";
	public static final String BlogsNewEntryAllowCommentsFor = "css=select[id='commentDays']";
	public static final String BlogsNewEntrySpecifyURL = "css=input[id='permlink_url' and type='text']";
	public static final String BlogsNewEntryIncludeURL = "css=input[id='att_mediacast_url' and type='text']";
	public static final String BlogsNewEntryPost = "css=input[value='Post']";
	public static final String BlogsNewEntrySaveAsDraft = "css=input[class='lotusBtn' and name='draft']";
	public static final String BlogsNewEntryPreview = "css=input[class='lotusBtn' and name='fullPreview']";
	public static final String BlogsNewEntryCancel = "link=Cancel";
	
	//Comments
	public static final String BlogsAddACommentLink = "link=Add a Comment";
	public static final String BlogsCommentTextArea = "css=textarea[id='comment_content']";
	public static final String BlogsCommentAddEntryToBlog = "css=input[id='postAsTrackback' and type='checkbox']";
	public static final String BlogsCommentSelectEntry = "css=select[title='Target']";
	public static final String BlogsCommentSubmit = "css=input[name='submitBtn']";
	public static final String BlogsCommentPreview = "css=input[class='lotusBtn' and name='fullPreview']";
	public static final String BlogsCommentCancel = "link=Cancel";
	
	//Add a Profile Board Entry
	public static final String ProfilesBoard = "css=#wallEntryFormInputMsg";
	public static final String PostStatus = "css=input[value='Post Status']";
	
	//Action Required
	
	public static String ActionRequiredBadge = "css=a span.lotusUnreadBadge";
	
	public static String ActionRequired2= "css=a:contains('Action Required')";
	
	

}
