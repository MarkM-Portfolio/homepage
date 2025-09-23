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


import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;

public class ProfilesData {
	
	
	
	public static final String FieldBuilding = "IBM DSL Building 6 " +CommonMethods.genDateBasedRandVal();
	public static final String FieldFloor = "DSL 1" +CommonMethods.genDateBasedRandVal();
	public static final String FieldOffice = "DSL 1 " +CommonMethods.genDateBasedRandVal();
	public static final String FieldOfficeNo = "+3531815400 " +CommonMethods.genDateBasedRandVal();
	
	public static final String ProfilesTag = "level2bvt" +CommonMethods.genDateBasedRandVal();
	public static final String ProfilesTagForSearch = "ProfilesSearchTag12345";
	
	public static final String ProfilesBoardEntry = "I am currently working on the selenium level 2 BVT testcases for Profiles" +CommonMethods.genDateBasedRandVal();
	public static final String ProfilesBoardEntryNewsBVT = "I am currently working on the selenium level 2 BVT testcases for News" +CommonMethods.genDateBasedRandVal();
	
	public static final String AddLinkName = "Connections Help "+CommonMethods.genDateBasedRandVal();
	public static final String AddLinkURL = CommonObjects.TestURL+"help/index.jsp?topic=/com.ibm.lotus.connections.profiles.help/pframe.html";
	public static final String WindowName = "Help - IBM Connections";

}
