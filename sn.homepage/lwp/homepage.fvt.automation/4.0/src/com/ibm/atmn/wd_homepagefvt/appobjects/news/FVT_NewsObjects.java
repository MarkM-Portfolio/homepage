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
public class FVT_NewsObjects {
    
    //Generate a random number from date & time
	public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }
    
	//5 Tab options
	
	public static final String Home = "link=Home";
	public static final String GettingStarted = "link=Getting Started";
	public static final String Updates= "link=Updates";
	public static final String ActivityStream = "link=Activity Stream";
	public static final String Widgets = "link=Widgets";
	public static final String Administration = "link=Administration";   
	public static final String AllUpdates = "link=I'm Following";
	public static final String ImFollowing = "link=I'm Following";
	public static final String StatusUpdates = "link=Status Updates";
	//public static final String ActionRequired = "link=Action Required";
	public static final String ActionRequired = "css=div.lotusMenuSection ul li a[title='View items that require your response']";
	public static final String Saved = "link=Saved";
	public static final String MyNotifications = "link=My Notifications";
	public static final String Discover = "link=Discover";
	public static final String MyPage = "link=My Page";
	
	//Updates
	public static final String LeftNavStatusUpdates = "//div[2]/div[2]/div/div/span[2]/span";
	public static final String LeftNavNewsFeed = "//div[2]/div[2]/div/span[2]/span";
	public static final String LeftNavNotifications = "//div[3]/div/span[2]/span";
	public static final String LeftNavDiscover = "//div[4]/div/span[2]/span";
	public static final String LeftNavSaved = "//div[5]/div/span[2]/span";
	public static final String NewsFeedLoadBlogs = "homepage/web/updates/#newsfeed/blogs";
	
	//Status Updates 
	public static final String ClickStatusUpdateLink = "css=*[class='lotusStatusInput']";
	public static final String EnterStatusUpdate = "css=textarea:contains('What are you working on right now?')";
	public static final String SaveStatusUpdate = "css=input[class='lotusFormButton']";
	public static final String PostStatus_Disabled = "css=div.lotusActions ul.lotusInlinelist li.lotusFirst a.lotusPersonInactive";
	public static final String PostStatus = "css=div.lotusActions ul.lotusInlinelist li.lotusFirst a:contains('Post')";
	public static final String PostStatusGlobal = "css=div ul.lotusInlinelist div input.lotusBtn[value='Post']";
	public static final String PostStatusDisabled = "css=a[aria-disabled='false']:contains('Post')";
	public static final String ResetStatus = "css=a:contains('Reset')";
	public static final String ClearStatus = "css=a:contains('Clear')";
	public static final String DeleteStatus = "css=div.lotusPostContent a.lotusDelete img";
	public static final String RemoveStatus = "css=input.lotusFormButton[value='Delete']";
	
	public static final String AttachAFile = "css=div.lotusActions ul.lotusInlinelist li.lotusFirst a:contains('Add a File')";
	public static final String MyFiles = "css=input#lconn_files_widget_FilePickerCompact_0_option_myfiles.lotusCheckbox";
	public static final String MyComputer = "css= input#lconn_files_widget_FilePickerCompact_0_option_mycomputer.lotusCheckbox";
	public static final String Ok = "css=input[value='OK']";
	public static final String RepostIcon = "css=span.repostIcon img";
	
	
	//Status Comment
	public static final String StatusComment = "css=li.lotusAddComment form.lotusForm2 div.lotusFieldEmphasis textarea.lotusText";
	public static final String PostComment = "css=li.lotusAddComment form.lotusForm2 div.lotusActions ul.lotusInlinelist li.lotusFirst a strong:contains('Post')";
	public static final String CancelComment = "link=Cancel";
	public static final String Comment = "css=div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:contains('Comment')";
	public static final String DeleteComment = "//li[2]/div/a/img";
	public static final String DeleteComment_Second = "//li[3]/div/a/img";
	//public static final String DeleteComment = "css=li#com_ibm_social_as_item_comment_InlineComment_1 > div.lotusPost > a.lotusDelete > img[alt='Delete comment']";
	public static final String RemoveComment = "css=input.lotusFormButton[value='Delete']";
	
	//EE
	public static final String EE_Close = "css=div.lotusFlyoutHeader span.lotusBtnImg input";
	public static final String EE_Frame = "id=__gadget_com.ibm.social.ee.controls.EEPreviewDialog_node_0";
	public static final String EE_Header = "css=div.lotusWrapper div.lotusFlyoutHeader";
	public static final String EE_Content = "css=div.lotusFlyoutContent div.lotusFlyoutContentArea";
	public static final String EE_StatusComment = "css=textarea#com_ibm_social_ee_widget_Comments_0addCommentBody.lotusText";
	public static final String EE_SaveComment = "css=div.lotusActions ul.lotusInlinelist li.lotusFirst a strong:contains('Post')";
	public static final String Repost = "css=button:contains('Repost')";
	
	//public static final String Like = "css=a[id^='TOGGLE_su_ee_']";
	public static final String Like = "link=Like";
	public static final String Likes = "css=a.lotusLikeCount div.lotusLikeText";
	public static final String Undo_Like = "link=Unlike";
	public static final String No_Likes = "css=a[title='0 people like this']";

	
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

	//Filter By
	
	public static final String FilterBy = "css=select";
	public static final String FilterAll = "All";
	public static final String FilterStatusUpdates = "Status Updates";
	public static final String FilterActivities = "Activities";
	public static final String FilterBlogs = "Blogs";
	public static final String FilterBookmarks = "Bookmarks";
	public static final String FilterCommunities = "Communities";
	public static final String FilterFiles = "Files";
	public static final String FilterForums = "Forums";
	public static final String FilterPeople = "People";
	public static final String FilterProfiles = "Profiles";
	public static final String FilterTags = "Tags";
	public static final String FilterWikis = "Wikis";
	
	//Filter By
	public static final String FilterByTag = "css=span[id^=lconn_homepage_as_tagmanager_TagFilterDijit_].activityStreamFilterMenu select";
	
	public static final String FilterByFor = "css=span[dojoattachpoint=secondFilterNode] span.asFilterMenu select";
	
	//Filter by - Status Updates
	
	public static final String PeopleIFollow = "People I Follow";
	public static final String CommunitiesIFollow = "Communities";
	public static final String MyNetwork = "My Network";
	public static final String MyNetworkAndPeopleIFollow = "My Network And People I Follow";
	public static final String MyUpdates = "My Updates";

	//Activities Identifiers
	
	public static String CreateAnActivity = "css=div#startActivity.lotusChunk span a";
	public static String ActivityName = "css=input#lconn_act_ActivityForm_0_titleInput.lotusText";
	public static String ActivityTag = "css=input#lconn_act_ActivityForm_0tagz.fieldNode";
	public static String ActivityGoal = "css=textarea#lconn_act_ActivityForm_0_descriptionInput.lotusText";
	
	//News item
	
	public static final String FocusOnComponent = "css=div.activityStreamNewsItemContainer div.lotusPostContent div.lotusPostAction";
	public static final String FirstNewsItem = "css= li#com_ibm_social_as_item_NewsItem_0 div.activityStreamNewsItemContainer div.lotusPostContent div";

	
	
	//Delete Saved
	
	public static final String DeleteSavedStory = "css=a[title='Remove from Saved'].lotusDelete img";
	public static final String RemoveSavedStory = "css=input.lotusFormButton[value='Remove']";

	//Action Required
	
	public static String ActionRequiredBadge = "css=a span.lotusUnreadBadge";
	public static String ActionRequired2= "css=a:contains('Action Required')";
	public static final String DeleteActionRequiredStory = "css=a[title='Remove from Action Required'].lotusDelete img";
	public static final String RemoveActionRequiredStory = "css=input[value='Remove']";
	
	//Share
	
	public static final String Share = "css=a.lotusBannerBtn";
	public static final String ShareSomething = "css=td.lotusNowrap a strong:nth-child(0)";
	
	//Roll Up
	
	public static String StatusNewsItem = "css=div[id^=com_ibm_social_as_item_StatusNewsItem].lotusPost";
	public static String RollUpNewsItem = "css=div[id^=com_ibm_social_as_item_RollupStatusNewsItem].lotusPost";
	
	//Community Status
	
	public static final String EnterStatusUpdateComm = "css=textarea:contains('Share a message with the community')";
	
}
