/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.appobjects.atmentions;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Properties;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;

@SuppressWarnings("unused")
public class FVT_AtMentionsData {
	
	//@Mentions
	
	public static final String MentionUserPost = "This is an at_Mentions FVT test post "+ CommonMethods.genDateBasedRandVal()+" @";
	

	
	public static final String StatusUpdate = "This is an at_Mentions FVT Status Update post "+ CommonMethods.genDateBasedRandVal()+" @";
	
	public static final String StatusUpdateComment = "This is an at_Mentions FVT Status Update comment "+ CommonMethods.genDateBasedRandVal()+" @";
	
	
	//General Data
	
	public static final String MentionUserPostPrivate = "This is an at_Mentions FVT test post "+ CommonMethods.genDateBasedRandVal()+" ";
	
	public static final String StatusUpdateCommentGeneral = "This is an at_Mentions FVT Status Update comment "+ CommonMethods.genDateBasedRandVal();

}
