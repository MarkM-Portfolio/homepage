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

package com.ibm.atmn.homepagefvt.appobjects.common;

import java.io.IOException;
import java.util.Properties;

import com.thoughtworks.selenium.Selenium;

public class CommonObjects {
	protected Selenium selenium;

	private static final String BUNDLE = "test.properties";
	private static final Properties properties;
	static {
		properties = new Properties();
		try {
			properties.load(CommonObjects.class.getResourceAsStream(BUNDLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//URL for testing 
	public static final String TestURL = properties.getProperty("test_url", "http://localhost:9080");
	public static final String TestServer = properties.getProperty("test_server");
	public static final String SeleniumPort = properties.getProperty("test_port");
	public static final String TestBrowser = properties.getProperty("test_browser");
	public static final String TestOS = properties.getProperty("test_os");

	public static final String ComponentActivities = properties.getProperty("test_component_activities");
	public static final String ComponentBlogs = properties.getProperty("test_component_blogs");
	public static final String ComponentCommunities = properties.getProperty("test_component_communities");
	public static final String ComponentDogear = properties.getProperty("test_component_dogear");
	public static final String ComponentFiles = properties.getProperty("test_component_files");
	public static final String ComponentForums = properties.getProperty("test_component_forums");
	public static final String ComponentHomepage = properties.getProperty("test_component_homepage");
	public static final String ComponentGlobalSearch = properties.getProperty("test_component_globalsearch");
	public static final String ComponentMobile = properties.getProperty("test_component_mobile");
	public static final String ComponentModeration = properties.getProperty("test_component_moderation");
	public static final String ComponentNews = properties.getProperty("test_component_news");
	public static final String ComponentProfiles = properties.getProperty("test_component_profiles");
	public static final String ComponentWikis = properties.getProperty("test_component_wikis");
	public static final String ComponentMetrics = properties.getProperty("test_component_metrics");

	//Browser timeout
	public static final String browserTimeout = properties.getProperty("test_timeout");
	public static final String browserSleep = properties.getProperty("test_sleep");

	//objects on the Login Page
	public static final String USERNAME_FIELD = "css=input[id='username']";
	public static final String Password_FIELD = "css=input[id='password']";
	public static final String Login_Button = "css=input[value='Log In']";
	public static final String Mobile_USERNAME_FIELD = "css=input[id='j_username']";
	public static final String Mobile_Password_FIELD = "css=input[id='j_password']";
	public static final String Mobile_Login_Button = "id=submitLink";
	public static final String Error_Message_Text = "class=errorMessage";
	public static final String Login_Link = "link=Log In";

	//Page Footer links
	public static final String PageFooterAbout = "link=About";

	//Logout
	public static final String Logout_Link = "link=Log Out";

	//Start Button for components
	public static final String StartButton = "css=*[id='createAllBtn']";

	//Submit button and Cancel Links
	public static String SaveButton = "css=input[value='Save']";
	public static String SaveButton2 = "css=span input.lotusFormButton";
	public static String OKButton = "css=input[value='OK']";
	public static String CancelLink = "css=*[title='Cancel']";

	//'About' Page
	public static String AboutPageLink = "link=About";
	public static String ReleaseVersion = "css=table.lotusLegal tbody td.lotusBuild";

	//Banner ('LotusBanner') links and trigger elements
	public static String Banner_Homepage_Base_List_Link = "css=#lotusBannerHomepage a";
	public static String Banner_Profiles_Base_List_Link = "css=#lotusBannerProfiles a";
	public static String Banner_Profiles_MyProfile = "css=#lotusBannerProfiles a[href$=profiles/html/myProfileView.do]";
	public static String Banner_Profiles_EditMyProfile = "css=#lotusBannerProfiles a[href$=profiles/html/editMyProfileView.do]";
	public static String Banner_Profiles_MyNetwork = "css=#lotusBannerProfiles a[href$=profiles/html/networkView.do?widgetId=friends&requireAuth=true]";
	public static String Banner_Profiles_StatusUpdates = "css=#lotusBannerProfiles a[href$=homepage/web/displayHomePage.action#status/follow-network]";
	public static String Banner_Profiles_Directory = "css=#lotusBannerProfiles a[href$=profiles/html/searchProfiles.do]";

	public static String Banner_Communities_Base_List_Link = "css=#lotusBannerCommunities a";
	public static String Banner_Communities_MyCommunities = "css=#lotusBannerCommunities a[href$=communities/service/html/mycommunities]";
	public static String Banner_Communities_PublicCommunities = "css=#lotusBannerCommunities a[href$=communities/service/html/allcommunities]";
	public static String Banner_Communities_ImFollowing = "css=#lotusBannerCommunities a[href$=communities/service/html/mycommunities?filterType=following]";

	public static String Banner_Apps_Base_List_Link = "css=#lotusBannerApps a";

	public static String Banner_Apps_Activities = "css=div#lconnheadermenu-apps a[href$=activities/]";
	public static String Banner_Apps_ToDoList = "css=div#lconnheadermenu-apps a[href$=activities/service/html/mainpage#todolist]";
	public static String Banner_Apps_HighPriorityActivities = "css=div#lconnheadermenu-apps a[href$=activities/service/html/mainpage#dashboard,highpriority]";

	public static String Banner_Apps_Blogs = "css=div#lconnheadermenu-apps a[href$=blogs]";
	public static String Banner_Apps_LatestEntries = "css=div#lconnheadermenu-apps a[href$=blogs/roller-ui/homepage]";
	public static String Banner_Apps_PublicBlogsListing = "css=div#lconnheadermenu-apps a[href$=blogs/roller-ui/allblogs]";

	public static String Banner_Apps_Bookmarks = "css=div#lconnheadermenu-apps a[href$=dogear]";
	public static String Banner_Apps_Popular = "css=div#lconnheadermenu-apps a[href$=dogear/html/popular]";
	public static String Banner_Apps_PublicBookmarks = "css=div#lconnheadermenu-apps a[href$=dogear/html]";

	public static String Banner_Apps_Files = "css=div#lconnheadermenu-apps a[href$=files]";
	public static String Banner_Apps_SharedWithMe = "css=div#lconnheadermenu-apps a[href$=files/app/shares]";
	public static String Banner_Apps_PinnedFolders = "css=div#lconnheadermenu-apps a[href$=files/app/folders/pinned]";

	public static String Banner_Apps_Forums = "css=div#lconnheadermenu-apps a[href$=forums/]";
	//public static String Banner_Apps_Forums = "css=strong:contains('Forums')";
	public static String Banner_Apps_ImAnOwnerForums = "css=div#lconnheadermenu-apps a[href$=forums/html/my?view=owner]";
	public static String Banner_Apps_PublicForums = "css=div#lconnheadermenu-apps a[href$=forums/html/public]";

	public static String Banner_Apps_Wikis = "css=div#lconnheadermenu-apps a[href$=wikis]";
	public static String Banner_Apps_ImAnOwnerWikis = "css=div#lconnheadermenu-apps a[href$=wikis/home/mywikis?role=owner]";
	public static String Banner_Apps_PublicWikis = "css=div#lconnheadermenu-apps a[href$=wikis/home/public]";

	//Centre content (.lotusContent) filter tabs (.lotusTabContainer). Select tabs (numbered from left to right, starting at Tab1
	public static String Centre_Content_Filter_Tabs_Tab1 = "css=div.lotusContent>div>div>div>div.lotusTabContainer>div>ul>li:nth(0) a";
	public static String Centre_Content_Filter_Tabs_Tab2 = "css=div.lotusContent>div>div>div>div.lotusTabContainer>div>ul>li:nth(1) a";
	public static String Centre_Content_Filter_Tabs_Tab3 = "css=div.lotusContent>div>div>div>div.lotusTabContainer>div>ul>li:nth(2) a";
	public static String Centre_Content_Filter_Tabs_Tab4 = "css=div.lotusContent>div>div>div>div.lotusTabContainer>div>ul>li:nth(3) a";
	public static String Centre_Content_Filter_Tabs_Tab5 = "css=div.lotusContent>div>div>div>div.lotusTabContainer>div>ul>li:nth(4) a";
	public static String Centre_Content_Filter_Tabs_Tab6 = "css=div.lotusContent>div>div>div>div.lotusTabContainer>div>ul>li:nth(5) a";
	
	//Rich text editor window objects (CK Editor)
	public static final String CKEditor_Activation_Area1 = "css=html";//"link=HTML Source";
	public static final String CKEditor_Activation_Area2 = "css=html>body";
	public static final String CKEditor_Textarea_RichText = "css=html>body>p";//"css=textarea.cke_source.cke_enable_context_menu";

	//test file directory
	public static final String DATA_FOLDER = "testfiles";
	
	//date picker
	//public static final String Date_Picker_NextMonth = "css=img[class$=dijitCalendarIncrease]";
	public static final String Date_Picker_NextMonth = "css=th[dojoattachpoint='incrementMonth']";
	public static final String Date_Picker_TopRightDate = "";
	public static final String Date_Picker_CurrentMonth_Dates = "css=td[class^=dijitCalendarCurrentMonth]";
	
	//
	public static final String ImAnOwner = "I'm an Owner";
	public static final String ImAMember = "I'm a Member";
	
}
