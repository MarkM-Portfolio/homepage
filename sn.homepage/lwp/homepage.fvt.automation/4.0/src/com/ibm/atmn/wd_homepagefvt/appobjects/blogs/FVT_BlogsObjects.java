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

package com.ibm.atmn.wd_homepagefvt.appobjects.blogs;

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
public class FVT_BlogsObjects {
    
    
    //Generate a random number from date & time
	public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }
	
	//4 Tab options
	public static final String MyBlogs = "link=My Blogs";
	public static final String PublicBlogs= "link=Public Blogs";
	public static final String MyUpdates = "link=My Updates";
	public static final String Administration = "link=Administration";
	
	public static final String StartABlog = "link=Start a Blog";
	public static final String BlogsNewEntry = "link=New Entry";
	public static final String BlogsSettings = "css=span.lotusBtn a:contains('Settings')";
	public static final String BlogsManageBlog = "link=Manage Blog";
	
	//Public Blogs Nav Options
	public static final String BlogsPublicLatest = "link=Latest Blogs Entries";
	public static final String BlogsPublicListing = "link=Blogs Listing";
	public static final String BlogsPublicMyRecommendations = "link=My Recommendations/Votes";
	
	public static final String FollowBlog = "link=Follow this Blog";
	public static final String UnfollowBlog = "link=Stop Following this Blog";
	
	//Create a blog form
	public static final String BlogsNameField = "css=input#name.lotusText";
	public static final String BlogsAddress = "css=input[id='handle']";
	public static final String BlogsTags = "css=input[id='tagsAsString']";
	public static final String BlogsDescription = "css=textarea[id='description']";
	public static final String BlogsTimeZone = "css=select[id='timeZone']";
	public static final String BlogsTheme = "css=select[id='theme']";
	
	public static final String BlogsSetAsPrimaryBlog = "//div/div[4]/ul/li[4]/a";
	
	//Settings
	public static final String BlogsSettingsTitle = "css=input#name";
	public static final String BlogsSettingsDescription = BlogsDescription;
	public static final String BlogsSettingsTags = BlogsTags;
	public static final String BlogsSettingsActive = "css=input[id='active' and type='checkbox']";
	public static final String BlogsSettingsNoOfEntries = "css=input[id='entryDisplayCount' and type='text']";
	public static final String BlogsSettingsEmoticons = "css=input[id='Emoticons' and type='checkbox']";
	public static final String BlogsSettingsAllow = "css=input[id='allowCoEdit' and type='checkbox']";
	public static final String BlogsSettingsTimeZone = BlogsTimeZone;
	public static final String BlogsSettingsAllowComments = "css=input[id='allowComments' and type='checkbox']";
	public static final String BlogsSettingsModerateComments = "css=input[id='moderateComments' and type='checkbox']";
	public static final String BlogsSettingsDefaultComments = "css=input[id='defaultAllowComments' and type='checkbox']";
	public static final String BlogsSettingsDefaultTimeforComemnts = "css=select[id='defaultCommentDays']";
	public static final String BlogsSettingsApplyCommentDefaultsToExisting = "css=input[id='applyCommentDefaults' and type='checkbox']";
	public static final String BlogsSettingsEnableAPI = "css=input[id='enableBloggerApi' and type='checkbox']";
	public static final String BlogsSettingsUpdateSettings = "//input[@value='Update Blog Settings']";
	public static final String BlogsSettingsRemoveBlog = "input[class='lotusBtn' and type='button']";
	
	 
	//Communities Blog
	public static final String CreateYourFirstEntry = "css=a#blogsAddNewEntryLink.lotusAction";
	
	
	//New Entry
	public static final String BlogsNewEntryTitle = "css=input#title";
	public static final String BlogsNewEntryAddTags = "link=Add Tags";
	public static final String BlogsNewEntryAddRemoveTags = "Add or Remove Tags";
	public static final String BlogsNewEntryAddTagsOK = "css=input[value='OK']";
	public static final String BlogsNewEntryAddTagsCancel = "link=Cancel";
	public static final String BlogsNewEntryAddTagsTextfield = "css=input[id='addtagwidgetAddTagsTypeAhead']";
	public static final String BlogsNewEntryTagsRemove = "css=a[class='blogsRemoveIcon']";
	public static final String BlogsNewEntryHTML = "link=HTML Source";
	public static final String BlogsNewEntryRichText = "css=textarea.cke_source.cke_enable_context_menu";
	public static final String BlogsNewEntryEmoticons = BlogsSettingsEmoticons;
	public static final String BlogsNewEntryDelay = "css=input[id='delayedPub' and type='checkbox']";
	public static final String BlogsNewEntryHour = "css=select[title='Hours']";
	public static final String BlogsNewEntryMinutes = "css=select[title='Minutes']";
	public static final String BlogsNewEntryDate = "css=div[id='widget_publishDate']";
	public static final String BlogsNewEntryAllowComments = "css=input[id='allowCommentsCheck' and type='checkbox']";
	public static final String BlogsNewEntryAllowCommentsFor = "css=select[id='commentDays']";
	public static final String BlogsNewEntrySpecifyURL = "css=input[id='permlink_url' and type='text']";
	public static final String BlogsNewEntryIncludeURL = "css=input[id='att_mediacast_url' and type='text']";
	//public static final String BlogsNewEntryPost = "css=input[id='postEntryID'][value='Post']";
	public static final String BlogsNewEntryPost = "css=input#postEntryID.lotusFormButton";
	public static final String BlogsNewEntrySaveAsDraft = "css=input[name='draft']";
	public static final String BlogsNewEntryPreview = "css=input[class='lotusBtn' and name='fullPreview']";
	public static final String BlogsNewEntryCancel = "css=a.lotusAction:contains('Cancel')";
	
	public static final String Like = "link=Like";
	
	//Comments
	public static final String BlogsAddACommentLink = "link=Add a Comment";
	public static final String BlogsCommentTextArea = "css=textarea[id='comment_content']";
	public static final String BlogsCommentAddEntryToBlog = "css=input[id='postAsTrackback']";
	public static final String BlogsCommentSelectEntry = "css=select[title='Target']";
	public static final String BlogsCommentSubmit = "css=input[name='submitBtn']";
	public static final String BlogsCommentPreview = "css=input[class='lotusBtn' and name='fullPreview']";
	public static final String BlogsCommentCancel = "link=Cancel";
	public static final String BlogsEntryRecommend = "css=a[id^=TOGGLE_entry_rating_].lotusLikeAction";
	//public static final String BlogsCommentRecommend = "//span[4]/span/span/div/span/a";
	public static final String BlogsCommentRecommend = "css=a[id^=TOGGLE_comment_rating_]:nth-child(1)";
	
	//Edit
	public static final String BlogsEdit = "link=Edit";
	
	//File Uploads
	public static final String BlogsFileUploadsHelp = "css=span[class='blogsPaddingLeft']";
	public static final String BlogsFileUploadsField = "css=input[id^='uploadFile']";
	public static final String BlogsFileUploadsField1 = "css=input[class='lotusLTR lotusAlignLeft']";
	public static final String BlogsFileUploadsField2 = "css=input[id='uploadFile1']";
	public static final String BlogsFileUploadsField3 = "css=input[id='uploadFile2']";
	public static final String BlogsFileUploadsField4 = "css=input[id='uploadFile3']";
	public static final String BlogsFileUploadsField5 = "css=input[id='uploadFile4']";
	public static final String BlogsFileUploadButton = "css=input#uploadBtn.lotusBtn";//input[id='uploadBtn']";
	public static final String BlogsFileUploadNewDirectory = "css=input[id='newDir']";
	public static final String BlogsFileUploadNewDirectoryCreate = "input[id='createDirBtn' and value='Create']";
	//For File Uploads in Chrome and IE
	public static final String BlogsFileUploadHelpImage = "css=a[id=uploadFileHelpLink]";
	public static final String BlogsFileUploadChooseFile = "css=input[name='Choose File']";
	
	//Administration Config
	public static final String BlogsSiteSettingsSitename = "css=input[name='site.name']";
	public static final String BlogsSiteSettingsBlogTitle = "css=input[name='site.shortName']";
	public static final String BlogsSiteSettingsSiteDescription = "css=input[name='site.description']";
	public static final String BlogsSiteSettingsHandleOfBlog = "css=input[name='site.frontpage.weblog.handle']";
	public static final String BlogsSiteSettingsEnable = "css=input[title='Enable active content filtering:']";
	public static final String BlogsSiteSettingsAutomatic = "css=input[name='site.heartBeatTime']";
	public static final String BlogsSiteSettingsNoOfEntries = "css=input[name='configForm.homepageEntryLimit']";
	
	public static final String BlogsSiteSettingsSave = "css=input[value='Save']";
	
	//Insert Image
	public static final String BlogsCKEInsertImageButton = "css=a[title='Insert Image']";
	public static final String BlogsCKEInsertAddImage = "css=input[id='radio_upload']";	
	public static final String BlogsCKEChooseFiles = "css=*[id='uploadedFile0']";
	public static final String BlogsCKEInsertFromRecent = "css=input[id='radio_chooseFromPhoto']";
	public static final String BlogsCKEChoosePhoto = "//div[@id='uploadedImageDiv1']/img";
	public static final String BlogsCKEInsertButton = "css=input[id='insertButton']"; 
	
	
	
	//Ideablogs
	
	public static final String NewIdea = "link=New Idea";
	public static final String IdeablogNewEntryTitle = "css=input#title";
	public static final String IdeablogNewEntryAddTags = "link=Add Tags";
	public static final String IdeablogNewEntryAddTagsTextfield = "css=input[id='addtagwidgetAddTagsTypeAhead']";
	public static final String IdeablogNewEntryAddTagsOK = "css=input[value='OK']";
	public static final String IdeablogNewEntryHTML = "link=HTML Source";
	public static final String IdeablogNewEntryRichText = "css=textarea.cke_source.cke_enable_context_menu";
	public static final String IdeablogNewEntryPost = "css=input[value='Post']";
	public static final String Vote = "css=a.ideaVotingButton";
	public static final String Graduate = "css=a#graduateIdeaButton";
	public static final String GraduateOk = "css=input.lotusFormButton[value='OK']";
	
	//Notify 
	
	public static String MORE_ACTIONS = "css=a#focusMoreActionsLink.lotusAction";
	public static String NOTIFY_OTHER_PEOPLE = "css=td#focusAddNotificationLink_text.dijitReset";
	public static String NOTIFY_TYPEAHEAD = "css=input#notificationForm_typeahead.typeAheadPeople";
	public static String NOTIFY_TYPEAHEAD_SEARCH = "css=li#notificationForm_typeahead_popup_searchDir.dijitMenuItem";
	//public static String NOTIFY_TYPEAHEAD_IDENTIFIER = "css=*[id$='notificationForm_typeahead_popup'] li:nth(1)";
	public static String NOTIFY_TYPEAHEAD_IDENTIFIER = "css=*[id='notificationForm_typeahead_popup'] li";

	//Add Authors
	
	public static String AUTHOR_LINK = "link=Authors";
	public static String ADD_MEMBERS = "css=a#focusAddMembersLink";
	public static String MEMBER_ROLE = "css=select";
	public static String MEMBER_TYPEAHEAD = "css=input#lconn_core_PeopleTypeAhead_0.typeAhead";
	public static String MEMBER_TYPEAHEAD_SEARCH = "css=li#lconn_core_PeopleTypeAhead_0_popup_searchDir.dijitMenuItem";
	//public static String MEMBER_TYPEAHEAD_IDENTIFIER = "css=*[id$='lconn_core_PeopleTypeAhead_0_popup'] li:nth(1)";
	public static String MEMBER_TYPEAHEAD_IDENTIFIER = "css=*[id='lconn_core_PeopleTypeAhead_0_popup'] li";

	
	public static String OWNER_CHECK = "css=tr[class='rollertable_odd']:nth(2) input[title='Set as blog owner']";
	public static String AUTHOR_CHECK = "css=tr[class='rollertable_odd']:nth(2) input[title='Set as blog author']";
	public static String DRAFT_CHECK = "css=tr[class='rollertable_odd']:nth(2) input[title='Set as blog draft']";
	public static String REMOVE_CHECK = "css=tr[class='rollertable_odd']:nth(2) input[title='Remove from blog']";
	
	
}
