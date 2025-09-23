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

package com.ibm.atmn.wd_homepagefvt.appobjects.news;


import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;

public class FVT_NewsData {
	
	public static final String UpdateStatus = "This is the FVT Level 2 Status update message "+CommonMethods.genDateBasedRandVal();
	public static final String UpdateStatus_File = "This is the FVT Level 2 Status update message with file attached "+CommonMethods.genDateBasedRandVal();
	public static final String UpdateStatus_Hashtag = "This is the FVT Level 2 Status update message #fvt ";
	public static final String UpdateStatus_Special = "This is the FVT Level 2 Status update message  ~!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?1234567890 "+CommonMethods.genDateBasedRandVal();
	public static final String UpdateStatus_Chinese = "This is the FVT Level 2 Status update message ";
	public static final String UpdateStatus_Link = "http://www.ibm.com";
	public static final String UpdateStatus_Security = "<script>alert('xss');</script>";
	public static final String UpdateStatus_1000 = "Many, perhaps most, software applications today are written as web-based applications to be run in an Internet browser. The effectiveness of testing these applications varies widely among companies and organizations. In an era of highly interactive and responsive software processes where many organizations are using some form of Agile methodology, test automation is frequently becoming a requirement for software projects. Test automation is often the answer. Test automation means using a software tool to run repeatable tests against the application to be tested. For regression testing this provides that responsiveness.There are many advantages to test automation. Most are related to the repeatability of the tests and the speed at which the tests can be executed. There are a number of commercial and open source tools available for assisting with the development of test automation. Selenium is possibly the most widely-used open source solution...........................................";
	public static final String UpdateStatus_Greater1000 = "Many, perhaps most, software applications today are written as web-based applications to be run in an Internet browser. The effectiveness of testing these applications varies widely among companies and organizations. In an era of highly interactive and responsive software processes where many organizations are using some form of Agile methodology, test automation is frequently becoming a requirement for software projects. Test automation is often the answer. Test automation means using a software tool to run repeatable tests against the application to be tested. For regression testing this provides that responsiveness.There are many advantages to test automation. Most are related to the repeatability of the tests and the speed at which the tests can be executed. There are a number of commercial and open source tools available for assisting with the development of test automation. Selenium is possibly the most widely-used open source solution...............................................................................................................";
	public static final String StatusComment = "This is the FVT Level 2 status comment "+CommonMethods.genDateBasedRandVal();
	public static final String StatusComment2 = "This is the FVT Level 2 second status comment "+CommonMethods.genDateBasedRandVal();
	public static final String StatusComment_Special = "~!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?1234567890 "+CommonMethods.genDateBasedRandVal();
	public static final String StatusComment_Link = "http://www.ibm.com";
	public static final String StatusComment_Long = "Many, perhaps most, software applications today are written as web-based applications to be run in an Internet browser. The effectiveness of testing these applications varies widely among companies and organizations. In an era of highly interactive and responsive software processes where many organizations are using some form of Agile methodology, test automation is frequently becoming a requirement for software projects. Test automation is often the answer. "+CommonMethods.genDateBasedRandVal();

	//Stop Following
	public static final String StopFollowingDialog = "css=div h3:contains('You are not currently following any items for this story.')";
	
	//For Adding Blogs
	public static final String BlogNameForNewsFVT = "News Level 2 BVT "+CommonMethods.genDateBasedRandVal();
	public static final String BlogsAddressForNewsFVT = "NewsAddress"+CommonMethods.genDateBasedRandVal();
	public static final String BlogsTagForNewsFVT = "FVTLevel2News";
	public static final String BlogsDescriptionForNewsFVT = "This is a test description for the level 2 bvt for news";
	public static final String BlogsTimeZoneOptionForNewsFVT = "(GMT) Greenwich Mean Time : Dublin, Edinburgh, Lisbon, London";
	public static final String BlogsThemeOptionForNewsFVT = "Blog_with_Bookmarks";
	public static final String BlogsNewEntryTitleForNewsFVT = "Entry for Level 2 Test for news "+CommonMethods.genDateBasedRandVal();
	public static final String BlogsSecondEntryTitleForNewsFVT = "Second Entry for Level 2 Test for news "+CommonMethods.genDateBasedRandVal();
	public static final String BlogsNewEntryTagForNewsFVT = "TestTagForNews";
	public static final String BlogsNewEntryEntryForNewsFVT = "This is a test entry for the level 2 blogs for the news bvt";
	public static final String BlogsHandleOfBlog = "Handle"+CommonMethods.genDateBasedRandVal();
	public static final String BlogsCommentText = "This is the test for the comment to be added to the entry in blogs - part of the FVT level 2 test for news";
	
	//URL of People view
	public static final String LoadPeopleView = "homepage/web/updates/#discover/all";
	
	//Views
	public static final String DISCOVER_VIEW = "Discover";
	public static final String IM_FOLLOWING_VIEW = "I'm Following";
	public static final String SAVED__VIEW = "Saved";
	public static final String MY_NOTIFICATIONS_VIEW = "My Notifcations";
	public static final String ACTION_REQUIRED_VIEW = "Action Required";
	public static final String STATUS_UPDATES_VIEW = "Status Updates";
	
	//Filter Options
	public static final String ALL = "All";
	public static final String ACTIVITIES= "Activities";
	public static final String BLOGS = "Blogs";
	public static final String BOOKMARKS = "Bookmarks";
	public static final String COMMUNITIES = "Communities";
	public static final String FILES = "Files";
	public static final String FORUMS = "Forums";
	public static final String PEOPLE = "People";
	public static final String PROFILES = "Profiles";
	public static final String STATUS_UPDATES = "Status Updates";
	public static final String TAGS = "Tags";
	public static final String WIKIS = "Wikis";
	
	public static final String FOR_ME = "For Me";
	public static final String FROM_ME = "From Me";
	
	
	
	

	
	
}
