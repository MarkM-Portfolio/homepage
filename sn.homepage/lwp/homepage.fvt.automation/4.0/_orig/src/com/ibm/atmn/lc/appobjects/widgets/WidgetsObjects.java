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

package com.ibm.atmn.lc.appobjects.widgets;

public class WidgetsObjects {

	//Object Identifiers for the Start a Community form
	public static String CommunityName = "css=input[id='addCommunityName']";
	public static String CommunityTag = "css=input[id='autocompletetags']";
	public static String CommunityAccessOption1 = "//*[@id='addPublicAccess']";
	public static String CommunityAccessOption2 = "//*[@id='addPublicInviteOnlyAccess']";
	public static String CommunityAccessOption3 = "//*[@id='addPrivateAccess']";
	public static String CommunityMembersDropdown = "css=*[@id='']";
	public static String CommunityMembersTypeAhead = "css=*[@id='']";

	//Commnuity Actions menu items
	public static final String Community_Actions_Button = "css=*[id='displayActionsBtn']";
	public static final String Menu_Item_1 = "id=communityMenu_CREATESUB_text";
	public static final String Menu_Item_2 = "id=communityMenu_EDIT_text";
	public static final String Menu_Item_3 = "id=communityMenu_customize_text";
	public static final String Menu_Item_4 = "id=communityMenu_EMAIL_text";
	public static final String Menu_Item_5 = "id=communityMenu_LEAVE_text";
	public static final String Menu_Item_6 = "id=communityMenu_DELETE_text";

	//To Add Widgets to communities
	public static String WidgetBlog = "link=Blog";
	public static String WidgetIdeationBlog = "link=Ideation Blog";
	public static String WidgetActivities = "link=Activities";
	public static String WidgetWikis = "link=Wiki";
	public static String WidgetSubCommunities = "link=Subcommunities";
	public static String WidgetFeeds = "link=Feeds";
	public static String WidgetMediaGallery = "link=Media Gallery";
	public static String WidgetEvent = "link=Calendar";
	public static String WidgetSectionClose = "css=img[title='Close palette']";

}
