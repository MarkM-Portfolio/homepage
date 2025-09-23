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

package com.ibm.atmn.wd_homepagefvt.appobjects.communities;

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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;


@SuppressWarnings("unused")
public class FVT_CommunitiesObjects {
   
	private static final String BUNDLE = "data.properties";
    private static final Properties properties;
    static
    {
      properties = new Properties();
      try
      {
        properties.load(CommonObjects.class.getResourceAsStream(BUNDLE));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    
    //Community Views
    public static String MyCommunityView = "link=My Communities";
    public static String PublicCommunityView = "link=Public Communities";
    public static String ImAMember = "link=I'm a Member";
    public static String ImAnOwner = "link=I'm an Owner";
    public static String CommunitiesHelp = "link=Help";
    
    //Data
    public static final String NameOfCommunity = properties.getProperty("Community_Name");
    public static final String NameOfCommunityLevelTwo = properties.getProperty("Community_Name_Level_2");
    public static final String TagForCommunity = properties.getProperty("Community_Tag");
    
    //Object Identifiers for the Start a Community form
    public static String StartACommunity = "css=*[id='createAllBtn']";
	public static String CommunityName = "css=input[id='addCommunityName']";
	public static String CommunityTag = "css=input[id='autocompletetags']";
	public static String CommunityHandle = "css=input[id='autocompletehandle']";
	public static String CommunityAccessOption1 = "//*[@id='addPublicAccess']";
	public static String CommunityAccessOption2 = "//*[@id='addPublicInviteOnlyAccess']";
	public static String CommunityAccessOption3 = "//*[@id='addPrivateAccess']";
	public static String ChangeAccessOption3 = "//*[@id='privateAccess']";
	public static String CKEditor_Toolbar_Bidi = "css=*[class='cke_off cke_button_bidirtl']";
	public static String CommunityDescription = "//body/p";
	public static String CommunityThemeLink = "css=#themeLinkButton";
	public static String CommunityThemeGreen = "css=#greenCtlAddComm";
	public static String CommunityUploadImageLink = "css=#photoUploadLink]";
	public static String CommunityOverview = "css=h1[id='overview']";
	public static String CommunityOverviewDescription = "div.communityOverview p";
	public static String CommunityOverviewTag = "span.lotusTags span a";
	public static String CheckContentApproval = "css=input#contentApproval.lotusCheckbox";
	
	//Commnuity Actions menu items
	public static String Community_Actions_Button= "css=*[id='displayActionsBtn']";
	public static String Menu_Item_1 ="id=communityMenu_CREATESUB_text";
	public static String Menu_Item_2 ="id=communityMenu_EDITURL_text";
	public static String Menu_Item_3 ="id=communityMenu_customize_text";
	public static String Menu_Item_4 ="id=communityMenu_EMAIL_text";
	public static String Menu_Item_5 ="id=communityMenu_LEAVE_text";
	public static String Menu_Item_6 ="id=communityMenu_DELETE_text";
	
	//To Add Widgets to communities
	public static String WidgetBlog = "link=Blog";
	public static String WidgetIdeationBlog = "link=Ideation Blog";
	public static String WidgetActivities = "link=Activities";
	public static String WidgetWikis = "link=Wiki";
	public static String WidgetSubCommunities = "link=Subcommunities";
	public static String WidgetFeeds = "link=Feeds";
	public static String WidgetMediaGallery = "link=Media Gallery";
	public static String WidgetCalendar = "link=Calendar";
	public static String WidgetEvents = "link=Events";
	public static String WidgetSectionClose = "css=a#closePalette.lotusRight img.otherFramework16";
	
	//Community Left Nav options
	public static String LeftNavOption1 = "link=Overview";
	public static String LeftNavOption2 = "link=Members";
	public static String LeftNavOption3 = "link=Forums";
	public static String LeftNavOption4 = "link=Bookmarks";
	public static String LeftNavOption5 = "link=Files";
	public static String LeftNavOption6 = "link=Feeds";
	public static String LeftNavOption7 = "link=Activities";
	public static String LeftNavOption8 = "link=Blog";
	public static String LeftNavOption9 = "link=Calendar";
	public static String LeftNavOption10 = "link=Ideation Blog";
	
	
	public static String LeftNavOverview = "link=Overview";
	public static String LeftNavMembers = "link=Members";
	public static String LeftNavForums = "link=Forums";
	public static String LeftNavBookmarks = "link=Bookmarks";
	public static String LeftNavFiles = "link=Files";
	public static String LeftNavFeeds = "link=Feeds";
	public static String LeftNavActivities = "link=Activities";
	public static String LeftNavBlogs = "link=Blog";
	public static String LeftNavWiki = "link=Wiki";
	//public static String LeftNavWiki = "css=a[title='Wiki']";
	public static String LeftNavCalendar = "link=Calendar";
	public static String LeftNavEvents = "link=Events";
	public static String LeftNavIdeationBlog = "link=Ideation Blog";
	public static String RecentUpdates = "link=Recent Updates";
	public static String StatusUpdates = "link=Status Updates";
	
	//Add members
	public static String AddMembersToExistingCommunity = "link=Add Members";
	public static String VerifyInviteMembers = "link=Invite Members";
	public static String RemoveMemberFromExistingCommunity = "";
	public static String VerifyMemberAsOwner = "css=input[value='owner']";
	public static String VerifyMemberAsMember = "css=input[value='member']";
	public static String InviteButton = "css=input[value='Send Invitations']";
	public static String InviteMembers = "css=a#memberInviteButtonLink";
	
	//Edit Community
	public static String ChangeCommmunityThemeLink = "css=#themeLink > a.action"; 
	public static String EditCommunityThemeSelected = "css=#greenCtlEditComm";
	public static String EditCommunityCancel = "link=Cancel";
	
	
	
	//Added objects for next LC Release
	/**Adding Members*/
	public static String CommunityMembersDropdown = "css=select[id='addMembersSelect']";
	public static String CommunityMembersTypeAhead = "css=input[id='addComMembersWidgetPeopleTypeAhead']";
	public static String AddMembersToExistingTypeAhead = "css=input[id='addComMembersWidgetPeopleTypeAhead']";
	public static String AddMembersToExistingTypeAheadWithoutCom = "css=input[id='addMembersWidgetPeopleTypeAhead']";
	public static String fullSearchLink = "Person not listed? Use full search...";
	public static String fullUserSearchIdentifier = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup" + "_searchDir']";
	public static String fullUserSearchIdentifier1 = "css=*[id='addMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";
	public static String selectedUserIdentifier = "css=*[id$='addComMembersWidgetPeopleTypeAhead_popup'] li:nth(1)";
	public static String selectedUserIdentifier_test = "css=*[id$='addComMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	//public static String selectedUserIdentifier = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] li";
	public static String Add_To_ExistingMembers_Textfield_Typeahead="css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	public static String Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom="css=*[id='addMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	
	public static String InviteCommunityMembersTypeAhead = "css=input[id='inviteMembersWidgetPeopleTypeAhead']";
	public static String fullUserSearchIdentifier_Invite = "css=*[id='inviteMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";
	public static String selectedUserIdentifier_Invite = "css=*[id='inviteMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	public static String selectedUserIdentifier_Invite1 = "css=*[id$='inviteMembersWidgetPeopleTypeAhead_popup'] li:nth(1)";
    //Generate a random number from date & time
	/*public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }*/
	
	//Object Identifiers for Community Files
	public static String CommunityFilesSidebar = "link=Files";
	public static String CommunityShareFiles = "link=Share Files";
	public static String BrowseFilesOnMyComputer = "link=Browse files on my computer...";
	public static final String UploadFiles_Name = "//input[@class='lotusText lotusLtr']";
	public static final String Upload_Button = "//input[@aria-disabled='false']";
	public static String RecentUploadsCheckbox = "css=input[type='checkbox']";
	public static String BrowseComputerForFiles = "//a[contains(text(),'Browse files on my computer')]";
	
	//Bookmark identifiers
	public static String AddBookmarkButton = "css=#addBookmarkBtn";
	public static String EnterBookmarkURL = "css=#addBookmarkUrl";
	public static String EnterBookmarkName = "css=#addBookmarkName";
	public static String EnterBookmarkDescription = "css=#addBookmarkDescription";
	public static String EnterBookmarkTag = "css=#autocompletetags2";
	public static String AddAsImportant = "css=input[name='homepage']";
	
	public static String ImportantBookmarks = "css=a.action";
	public static String OverviewBookmarksLink = "css=h4 > a";
	public static String MoreLink = "//a[contains(text(),'More')]";
	public static String BookmarksEditLink = "css=li.lotusFirst > a.lotusAction";
	public static String EditLink = "//a[contains(text(),'Edit')]";
	
	//Forums identifiers
	public static String TopicTitle = "css=input[id$='postTitle']";
	public static String TopicTag = "css=input[id$='postTag']";
	public static String PostTitle = "css=h1 span.forumPostTitle";
	
	//Feeds identifiers
	public static String AddAFeed = "//a[contains(text(),'Add Your First Feed')]";
	public static String TestFeedURL = "communities/service/atom/communities/my";
	public static String AddFeedFormFeed = "css=textarea[id='addFeedUrl']";
	public static String AddFeedFormTitle = "css=input[id='addFeedName']";
	public static String AddFeedFormDescription = "css=textarea[id='addFeedDescription']";
	public static String AddFeedFormTag = "css=input[id='autocompletetags2']";
	
	//Help Window Identifiers
	public static final String HelpFrame = "Help - IBM Connections";
	public static final String HelpPageTitle = "//body/h1";
	
	
	//Create Calendar Event
	//public static String CreateCalendarEvent = "link=Create an Event";
	public static String CreateCalendarEvent = "css=div.lotusActionBar span.lotusBtn a";
	//public static String CreateCalendarEvent = "css=div.lotusActionBar span.lotusBtn a:contains('Create an Event')";
	public static String EventTitle = "css=input#calendar_event_editor-subject.dijit";
	public static String EventDiscription = "css=td#cke_contents_calendar_event_editor-description.cke_contents";
	public static final String NewEntryHTML = "link=HTML Source";
	public static String EventTags = "css=input#calendar_event_editor-tagsAsString.lotusText";
	public static final String EventAddACommentLink = "link=Add a comment...";
	public static final String EventCommentTextArea = "//textarea";
	public static final String SelectEventType = "css=select[id=calendar_event_editor_rec-rectype]";
	public static final String REPEATS = "css=a[id='calendar_event_editor_rec-recurrenceEnable_link']";
	public static final String RepeatDaily = "This entry repeats daily";
	public static final String EntireSeriesCheckbox = "id=calendar_event_editor-series";
	public static final String InstanceCheckbox = "id=calendar_event_editor-exception";
	public static final String DeleteComment = "css=div.lotusActions ul.lotusInlinelist li.lotusFirst a:contains('Delete')";
	//public static final String DeleteConfirm = "css=input#dialog_ok_btn.lotusFormButton";
	public static final String DeleteConfirm = "css=div.lotusDialogFooter input.lotusFormButton:nth(0)";
	public static final String MoreActions = "css=div.lotusActionBar span.lotusBtn a[title='More Actions']";
	public static final String DeleteEvent = "css=td#calendar_event_viewer-miDelete_text.dijitReset";
	//public static final String DeleteEventConfirm = "css=a#ddlg_submit";
	public static final String DeleteEventConfirm = "css=div.lotusDialogFooter input.lotusFormButton:nth(0)";
	public static final String DeleteInstanceCheckbox = "css=input#deleteInstance";
	public static final String DeleteSeriesCheckbox = "css=input#deleteAll";
	public static final String EventLocation = "css=input#calendar_event_editor-location.dijit";
	public static final String SaveEventEdit = "css=input#calendar_event_editor-submit.lotusFormButton";
	public static final String NOTIFY = "css=input[id='calendar_event_editor-notify'][type='checkbox']";
	public static final String NOTIFY_FILTER = "css=input[id^='lconn_calendar_FilteringCheckbox_'][id$='FilterTextbox']";
	public static final String NOTIFY_SELECTED = "css=input[id^='lconn_calendar_FilteringCheckbox_'][type='checkbox']";
	
	//Follow Community
	public static String FollowCommunity = "link=Follow this Community";
	//public static String FollowCommunity = "css=a#followDisplayActionsBtn";
	
	
	//Join Community
	public static String JoinCommunity = "link=Join this Community";
	public static String REQUEST_JOIN = "link=Request to Join this Community";
	public static String JOIN_COMMUNITY_REQUEST = "css=a#joinDisplayActionsBtn";
	
	
}
