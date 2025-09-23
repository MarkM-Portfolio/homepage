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

package com.ibm.atmn.lc.appobjects.homepage;

public class HomepageObjects {

	//5 Tab options
	public static final String GettingStarted = "link=Getting Started";
	public static final String Updates = "link=Updates";
	public static final String ActivityStream = "link=Activity Stream";
	public static final String Widgets = "link=Widgets";
	public static final String Administration = "link=Administration";

	//Object needed to remove the existing widgets
	public static final String ActivitiesClickForActions = "css=a:contains(Activities) + div a.lotusIcon";
	public static final String BlogsClickForActions = "css=a:contains(Blogs) + div a.lotusIcon";
	public static final String BookmarksClickForActions = "css=a:contains(Bookmarks) + div a.lotusIcon";
	public static final String CommunitiesClickForActions = "css=a:contains(Communities) + div a.lotusIcon";
	public static final String ProfilesClickForActions = "css=a:contains(Profiles) + div a.lotusIcon";
	public static final String ClickForActions = "link=Click for actions";
	public static final String ClickForActionsHelp = "css=tr:contains(Help) + td class.dijitReset";
	public static final String ClickForActionsOption1 = "css=tbody[class='dijitReset'] tr:nth-child(1)";
	public static final String ClickForActionsOption2 = "css=tbody[class='dijitReset'] tr:nth-child(2)";
	public static final String ClickForActionsOption3 = "css=tbody[class='dijitReset'] tr:nth-child(3)";
	public static final String ClickForActionsOptionHelp = "css=tbody[class='dijitReset'] tr:nth-child(4)";
	public static final String ClickForActionsOption4 = "css=tbody[class='dijitReset'] tr:nth-child(4)";
	public static final String ClickForActionsOption5 = "css=tbody[class='dijitReset'] tr:nth-child(5)";
	public static final String ClickForActionsOptionRemove = "css=tbody[class='dijitReset'] tr:nth-child(6)";
	public static final String ClickForActionsOption = "css=tbody[class='dijitReset'] tr:contains('Help')";

	//Widgets
	public static final String CustomizeActivities = "css=li[link='Activities']";
	public static final String WidgetsCustomize = "id=paletteLink";
	public static final String AllWidgets = "link=All";
	public static final String ActivitiesWidget = "link=Activities";
	public static final String BlogsWidget = "link=Blogs";
	public static final String CommunitiesWidget = "link=Communities";
	public static final String BookmarksWidget = "link=Bookmarks";
	public static final String ProfilesWidget = "link=Profiles";
	public static final String MyFilesWidget = "link=My Files";
	public static final String LatestWikisWidget = "link=Latest Wikis";

	public static final String ActivityWidget = "link=Activities";//"css=h2[a='Activities']";

	//Activities widgets to Add
	public static final String AddActivityWidget = "css=a[title='Activities']";
	public static final String MyActivityWidget = "css=a[title='My Activities']";
	public static final String PublicActivityWidget = "css=a[title='Public Activities']";

	//Blogs widgets to Add
	public static final String AddBlogsWidget = "css=a[title='Blogs']";
	public static final String AddBookmarkWidget = "css=a[title='Bookmarks']";
	public static final String AddCommunityWidget = "css=a[title='Communities']";
	public static final String AddProfilesWidget = "css=a[title='Profiles']";

	//Homepage Help title
	public static final String HelpFrame = "Help - IBM Connections";
	public static final String HelpPageTitle = "//body/div[2]/h1";
	public static final String HomepageHelp = "css=h1[class='title topictitle1']";
	public static final String UsingWidgetText = "css=h1.title.topictitle1";
	public static final String UsingActivities = "link=Using the Activities widget";
	public static final String UsingBlogs = "link=Using the Blogs widget";
	public static final String UsingBookmarks = "link=Using the Bookmarks widget";
	public static final String UsingCommunities = "link=Using the Communities widget";
	public static final String UsingProfiles = "link=Using the Profiles widget";

}
