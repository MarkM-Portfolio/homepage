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

package com.ibm.atmn.wd_homepagefvt.appobjects.profiles;


import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;

public class FVT_ProfilesData {
	
	
	
	public static final String FieldBuilding = "IBM DSL Building 6 " +CommonMethods.genDateBasedRandVal();
	public static final String FieldFloor = "DSL 1" +CommonMethods.genDateBasedRandVal();
	public static final String FieldOffice = "DSL 1 " +CommonMethods.genDateBasedRandVal();
	public static final String FieldOfficeNo = "+3531815400 " +CommonMethods.genDateBasedRandVal();
	
	public static final String ProfilesTag = "level2fvt" +CommonMethods.genDateBasedRandVal();
	public static final String ProfilesTags = "level2"+CommonMethods.genDateBasedRandVal()+" fvt"+CommonMethods.genDateBasedRandVal();
	public static final String ProfilesTagForSearch = "ProfilesSearchTag12345";
	
	
	public static final String ProfilesBoardEntry = "I am currently working on automating FVT testcases for Profiles " +CommonMethods.genDateBasedRandVal();
	public static final String ProfilesBoardEntry_Link = "www.skysports.com " +CommonMethods.genDateBasedRandVal();
	public static final String ProfilesBoardEntry_ExtendedChars = "~!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?1234567890 " +CommonMethods.genDateBasedRandVal();
	public static final String ProfilesBoardEntry_Long = "Many, perhaps most, software applications today are written as web-based applications to be run in an Internet browser. The effectiveness of testing these applications varies widely among companies and organizations. In an era of highly interactive and responsive software processes where many organizations are using some form of Agile methodology, test automation is frequently becoming a requirement for software projects. Test automation is often the answer. Test automation means using a software tool to run repeatable tests against the application to be tested. For regression testing this provides that responsiveness. There are many advantages to test automation. Most are related to the repeatability of the tests and the speed at which the tests can be executed. There are a number of commercial and open source tools available for assisting with the development of test automation. Selenium is possibly the most widely-used open source solution. This user’s guide will assist both new and experienced Selenium users in learning effective techniques in building test automation for web applications. " +CommonMethods.genDateBasedRandVal();
	public static final String ProfilesBoardEntryNewsBVT = "I am currently working on the selenium level 2 FVT testcases for News " +CommonMethods.genDateBasedRandVal();
	
	public static final String AddLinkName = "Connections Help "+CommonMethods.genDateBasedRandVal();
	public static final String AddLinkURL = CommonObjects.TestURL+"help/index.jsp?topic=/com.ibm.lotus.connections.profiles.help/pframe.html";
	public static final String WindowName = "Help - IBM Connections";
	
	public static final String OtherProfileBoardEntry = "FVT Board Entry " +CommonMethods.genDateBasedRandVal();
	
	//Following
	public static final String STOP_FOLLOWING = "Stop following";
	



}
