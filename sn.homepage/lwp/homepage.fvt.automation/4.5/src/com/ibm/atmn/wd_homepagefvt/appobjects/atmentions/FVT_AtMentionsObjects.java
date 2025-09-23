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
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;


@SuppressWarnings("unused")
public class FVT_AtMentionsObjects {
	
	//@mentions objects
	
	public static final String EmbeddedSharebox = "xpath=//*[@id='mentionstextAreaNode_0']";
	public static final String EmbeddedCommentBox = "xpath=//*[@id='mentionstextAreaNode_1']";
	public static final String Post = "link=Post";
	public static final String Comment = "css=div.lotusPostMore > ul.lotusInlinelist.lotusActions > li.lotusFirst > a:nth(0)";
		
}
