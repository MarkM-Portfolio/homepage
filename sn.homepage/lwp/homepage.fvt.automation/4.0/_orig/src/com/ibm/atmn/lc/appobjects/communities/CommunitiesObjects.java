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

package com.ibm.atmn.lc.appobjects.communities;

public class CommunitiesObjects {

	//Community Views
	public static String MyCommunityView = "link=My Communities";
	public static String PublicCommunityView = "link=Public Communities";
	public static String CommunitiesHelp = "link=Help";

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
	public static String CommunityThemeGreen = "css=#greenCtlAddComm";
	public static String CommunityUploadImageLink = "css=#photoUploadLink]";
	public static String CommunityOverview = "css=h1[id='overview']";
	public static String CommunityOverviewDescription = "div.communityOverview p";
	public static String CommunityOverviewTag = "span.lotusTags span a";

	//Commnuity Actions menu items
	public static String Community_Actions_Button = "css=*[id='displayActionsBtn']";
	public static String Menu_Item_1 = "id=communityMenu_CREATESUB_text";
	public static String Menu_Item_2 = "id=communityMenu_EDIT_text";
	public static String Menu_Item_3 = "id=communityMenu_customize_text";
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
	public static String WidgetSectionClose = "css=img[title='Close palette']";

	//Community Left Nav options
	public static String LeftNavOption1 = "link=Overview";
	public static String LeftNavOption2 = "link=Members";
	public static String LeftNavOption3 = "link=Forums";
	public static String LeftNavOption4 = "link=Bookmarks";
	public static String LeftNavOption5 = "link=Files";
	public static String LeftNavOption6 = "link=Feeds";

	//Add members
	public static String AddMembersToExistingCommunity = "link=Add Members";
	public static String VerifyInviteMembers = "link=Invite Members";
	public static String RemoveMemberFromExistingCommunity = "";
	public static String VerifyMemberAsOwner = "css=input[value='owner']";
	public static String VerifyMemberAsMember = "css=input[value='member']";

	//Edit Community
	public static String ChangeCommmunityThemeLink = "css=#themeLink > a.action";
	public static String EditCommunityThemeSelected = "css=#greenCtlEditComm";
	public static String EditCommunityCancel = "link=Cancel";

	//Added objects for next LC Release
	/** Adding Members */
	public static String CommunityMembersDropdown = "css=select[id='addMembersSelect']";
	public static String CommunityMembersTypeAhead = "css=input[id='addComMembersWidgetPeopleTypeAhead']";
	public static String AddMembersToExistingTypeAhead = "css=input[id='addComMembersWidgetPeopleTypeAhead']";
	public static String AddMembersToExistingTypeAheadWithoutCom = "css=input[id='addMembersWidgetPeopleTypeAhead']";
	public static String fullSearchLink = "Person not listed? Use full search...";
	public static String fullUserSearchIdentifier = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";
	public static String fullUserSearchIdentifier1 = "css=*[id='addMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";
	public static String selectedUserIdentifier = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	public static String Add_To_ExistingMembers_Textfield_Typeahead = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	public static String Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom = "css=*[id='addMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";

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

	//Community buttons
	public static String FollowACommunity = "css=*[id='followDisplayActionsBtn']";
	public static String JoinACommunity = "css=*[id='joinDisplayActionsBtn']";
}
