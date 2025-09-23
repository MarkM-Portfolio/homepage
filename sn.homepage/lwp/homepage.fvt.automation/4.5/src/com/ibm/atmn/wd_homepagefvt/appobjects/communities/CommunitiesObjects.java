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

package com.ibm.atmn.wd_homepagefvt.appobjects.communities;

public class CommunitiesObjects {

	//Community Views
	public static String MyCommunityView = "link=My Communities";
	public static String PublicCommunityView = "link=Public Communities";
	public static String CommunitiesLink = "css=ul.lotusTabs li.lotusFirst a:contains('Communities')";
	//public static String CommunitiesHelp = "link=Help";
	public static String CommunitiesHelp = "css=a#headerHelpLink";
	public static String ImFollowing = "//a[contains(text(),'Following')]";
	public static String ImInvited = "//a[contains(text(),'Invited')]";

	//Object Identifiers for the Start a Community form
	public static String StartACommunity = "css=*[id='createAllBtn']";
	public static String CommunityName = "css=input[id='addCommunityName']";
	public static String CommunityTag = "css=input[id='autocompletetags']";
	public static String CommunityHandle = "css=input[id='autocompletehandle']";
	public static String CommunityAccessOption1 = "//*[@id='addPublicAccess']";
	public static String CommunityAccessOption2 = "//*[@id='addPublicInviteOnlyAccess']";
	public static String CommunityAccessOption3 = "//*[@id='addPrivateAccess']";
	public static String CKEditor_Toolbar_Bidi = "css=*[class='cke_off cke_button_bidirtl']";
	public static String CommunityDescription = "//body/p";
	public static String CommunityThemeLink = "css=#themeLinkButton";
	public static String CommunityThemeDefalut = "css=#defaultCtlAddComm";
	public static String CommunityThemeRed = "css=#redCtlAddComm";
	public static String CommunityThemeGreen = "css=#greenCtlAddComm";
	public static String CommunityThemeGold = "css=#goldCtlAddComm";
	public static String CommunityThemeSilver = "css=#silverCtlAddComm";
	public static String CommunityThemeBlue = "css=#blueCtlAddComm";
	public static String CommunityThemePink = "css=#pinkCtlAddComm";
	public static String CommunityThemeOnyx = "css=#onyxCtlAddComm";
	public static String CommunityThemePurple = "css=#purpleCtlAddComm";
	public static String CommunityThemeOrange = "css=#orangeCtlAddComm";
	public static String CommunityUploadImageLink = "css=#photoUploadLink]";
	public static String CommunityOverview = "css=h1[id='overview']";
	public static String CommunityOverviewDescription = "div.communityOverview p";
	public static String CommunityOverviewTag = "span.lotusTags span a";
	
	public static String SubcommunityAccessOption1 = "//*[@id='addPublicAccessCtl']";
	public static String SubcommunityAccessOption2 = "//*[@id='addSubPublicInviteOnlyAccess']";
	public static String SubcommunityAccessOption3 = "//*[@id='addSubPrivateAccess']";
	public static String SubcommunityAddAllMembers = "//*[@id='addAllMembers']";

	//Commnuity Actions menu items
	public static String Community_Actions_Button = "css=*[id='displayActionsBtn']";
	public static String Menu_Item_1 = "id=communityMenu_CREATESUB_text";
	public static String Menu_Item_2 = "id=communityMenu_EDIT_text";
	public static String Menu_Item_3 = "xpath=//*[@id='communityMenu_CUSTOMIZE']";
	//public static String Menu_Item_3 = "tr#communityMenu_customize.dijitReset";
	public static String Menu_Item_4 = "id=communityMenu_EMAIL_text";
	public static String Menu_Item_5 = "id=communityMenu_LEAVE_text";
	public static String Menu_Item_6 = "id=communityMenu_DELETE_text";
	public static String Menu_Item_Moderate = "id=communityMenu_MODERATE_text";

	//To Add Widgets to communities
	public static String WidgetBlog = "link=Blog";
	public static String WidgetIdeationBlog = "link=Ideation Blog";
	public static String WidgetActivities = "link=Activities";
	public static String WidgetWikis = "link=Wiki";
	public static String WidgetSubCommunities = "link=Subcommunities";
	public static String WidgetFeeds = "link=Feeds";
	public static String WidgetMediaGallery = "link=Media Gallery";
	public static String WidgetEvent = "link=Events";
	public static String WidgetSectionClose = "css=a#closePalette.lotusRight img.otherFramework16";

	//Community Left Nav options
	public static String LeftNavOption1 = "link=Overview";
	public static String LeftNavOption2 = "link=Members";
	public static String LeftNavOption3 = "link=Forums";
	public static String LeftNavOption4 = "link=Bookmarks";
	public static String LeftNavOption5 = "link=Files";
	public static String LeftNavOption6 = "link=Feeds";

	//Add members
	public static String AddMembersToExistingCommunity = "link=Add Members";
	public static String InviteMembers = "css=a[id='memberInviteButtonLink']";
	public static String VerifyInviteMembers = "link=Invite Members";
	public static String RemoveMemberFromExistingCommunity = "";
	public static String VerifyMemberAsOwner = "css=input[value='owner']";
	public static String VerifyMemberAsMember = "css=input[value='member']";

	public static String MemberBusCard = "css=div.javlinHover";
	
	//Edit Community
	public static String ChangeCommmunityThemeLink = "css=#themeLink > a.action";
	public static String EditCommunityThemeSelected = "css=#greenCtlEditComm";
	public static String EditCommunityCancel = "link=Cancel";

	//Added objects for next LC Release
	/** Adding Members */
	public static String CommunityMembersDropdown = "css=select[id='addMembersSelectBox']";
	public static String CommunityMembersTypeAhead = "css=input[id='addComMembersWidgetPeopleTypeAhead']";
	public static String AddMembersToExistingTypeAhead = "css=input[id='addComMembersWidgetPeopleTypeAhead']";
	public static String AddMembersToExistingTypeAheadWithoutCom = "css=input[id='addMembersWidgetPeopleTypeAhead']";
	public static String fullSearchLink = "Person not listed? Use full search...";
	public static String fullUserSearchIdentifier = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";
	public static String fullUserSearchIdentifier1 = "css=*[id='addMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";
	public static String selectedUserIdentifier = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	public static String Add_To_ExistingMembers_Textfield_Typeahead = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	public static String Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom = "css=*[id='addMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	
	public static String InviteMembersToExistingTypeAhead = "css=input[id='inviteMembersWidgetPeopleTypeAhead']";
	public static String Invite_To_ExistingMembers_Textfield_TypeaheadWithoutCom = "css=*[id='inviteMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	public static String fullInvitedUserSearchIdentifier = "css=*[id='inviteMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";

	//Generate a random number from date & time
	/*
	 * public static String genDateBasedRandVal(){ //Create format class SimpleDateFormat tmformat = new
	 * SimpleDateFormat("MMDDHHmmss");
	 * 
	 * return tmformat.format(new Date()); }
	 */

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
	public static String EditBookmarkURL = "css=textarea[id^='editBookmarkUrl']";
	public static String EnterBookmarkName = "css=#addBookmarkName";
	public static String EditBookmarkName = "css=#editBookmarkName.lotusText";
	public static String EnterBookmarkDescription = "css=#addBookmarkDescription";
	public static String EditBookmarkDescription = "css=textarea[id^='editBookmarkDescription']";
	public static String EnterBookmarkTag = "css=#autocompletetags2";
	public static String EditBookmarkTag = "css=#autocompletetags2";
	public static String AddAsImportant = "css=input[name='homepage']";
	public static String EditAddAsImportant = "css=input[id^='homepage']";

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
	public static String AddAFeed = "css=a.lotusAction:contains('Add Your First Feed')";
	//public static String AddAFeed = "//a[contains(text(),'Add Your First Feed')]";
	public static String TestFeedURL = "communities/service/atom/communities/my";
	public static String AddFeedFormFeed = "css=textarea[id='addFeedUrl']";
	public static String EditFeedFormFeed = "css=textarea[id='editFeedUrl']";
	public static String AddFeedFormTitle = "css=input[id='addFeedName']";
	public static String EditFeedFormTitle = "css=input[id='editFeedName']";
	public static String AddFeedFormDescription = "css=textarea[id='addFeedDescription']";
	public static String EditFeedFormDescription = "css=textarea[id='editFeedDescription']";
	public static String AddFeedFormTag = "css=input[id='autocompletetags2']";
	public static String EditFeedFormTag = "css=input[id='autocompletetags2']";

	//Help Window Identifiers
	public static final String HelpFrame = "Help - IBM Connections";
	public static final String HelpPageTitle = "css=h1.title";////body/h1

	//Community buttons
	public static String FollowACommunity = "css=*[id='followDisplayActionsBtn']";
	public static String JoinACommunity = "css=*[id='joinDisplayActionsBtn']";
	
	//Subcommunity Widget Identifiers
	public static String SubcommunityWidget = "//div[@id='subcommunityImages']";
	public static String PublicSubcommunity = "css=img[alt^='Public Subcommunity']";
	public static String ModeratedSubcommunity = "css=img[alt^='Moderated Subcommunity']";
	public static String RestrictedSubcommunity = "css=img[alt^='Restricted Subcommunity']";
	
	//Inviting Members
	public static String SendInvitesButton = "css=input[value='Send Invitations'][class='lotusFormButton']";
	public static String InvitationsTab = "link=Invitations";
	public static String ResendLink = "css=a[title='Resend']";
	public static String RevokeLink = "css=a[title='Revoke']";
	// Calendar Save Button
	public static String CalendarSave = "css=input[id='calendar_event_editor-submit']";
	
	//Delete Community
	
	public static String ConfirmCommName = "css=input#communityInputField.lotusText";
	public static String ConfirmUserName = "css=input#signature.lotusText";
	public static String ConfirmCheckbox = "css=input#checkbox.lotusCheckbox";
	public static String DeleteCommunityBtn = "css=div.lotusDialogFooter > button.lotusBtn";
}
