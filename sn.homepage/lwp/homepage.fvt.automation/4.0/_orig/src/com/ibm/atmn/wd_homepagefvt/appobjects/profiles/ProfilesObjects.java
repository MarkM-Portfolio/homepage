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
public class ProfilesObjects {
	
	//Board
	public static final String ProfilesBoard = "css=div form.lotusForm2 textarea.lotusText";
	public static final String PostStatus = "css=input[value='Post']";
   
	//Edit Profile button
	public static final String EditProfile = "css=input[id='editProfileButton']";
	public static final String EditPhoto = "css=#aEditTabPhoto";
	public static final String EnterPhoto = "css=input[id='photoUpload']";
	public static final String EditPhotoBrowse = "css=input[id='photoUpload']";
	public static final String SaveandClosePhoto = "css=#lconn_savePhotoButton";
	
	//Edit the users profile
	public static final String EditProfileName = "css=input[id='contactInformation.displayName']";
	public static final String EditProfileBuilding = "css=input[id='contactInformation.bldgId']";
	public static final String EditProfileFloor = "css=input[id='contactInformation.floor']";
	public static final String EditProfileOffice = "css=input[id='contactInformation.officeName']";
	public static final String EditProfileOfficeNo = "css=input[id='contactInformation.telephoneNumber']";
	public static final String EditProfileSave = "css=input[value='Save']";
	public static final String EditProfileSaveAndClose = "css=input[value='Save and Close']";
	public static final String EditProfileCancel = "link=Cancel";
	
	//Edit Profile Header
	public static final String EditProfileHeader = "css=div[class='lotusHeader']";
	
	//Search for a user
	public static final String ProfilesSearchForUser = "css=input[title='Search']";
	public static final String ProfileSearch = "css=input[class='lotusSearchButton']";
	
	//Add a tag to the user
	public static final String ProfilesTagTypeAhead = "css=input[id='socialTagName']";// 
	public static final String ProfilesAddTag = "css=span.lotusBtnImg a#addTagButtonId.lotusAdd img";
	public static final String ProfilesTagForBVT = "link="+ProfilesData.ProfilesTag;
	public static final String ProfilesTagForBVTForSearch = "link="+ProfilesData.ProfilesTagForSearch;
	
	//add link
	public static final String ProfilesAddLink = "css=input[value='Add Link']";
	public static final String ProfilesAddLinkName = "css=input[id='name']";
	public static final String ProfilesAddLinkLinkname = "css=input[id='url']";
	public static final String AddLinkSave = "css=td.lotusFormFooter > input.lotusFormButton";
	public static final String BrowserTitleName = "Help - IBM Connections";
	public static final String ProfilesHelpTitle = "css=h1.title.topictitle1";
	
	//Help
	public static final String HelpFrame = "Help - IBM Connections";
	public static final String HelpPageTitle = "//body/h1";
	
	//File Upload
	public static final String ProfilesFileUpload = "Choose File to Upload";
	
	
	
}
