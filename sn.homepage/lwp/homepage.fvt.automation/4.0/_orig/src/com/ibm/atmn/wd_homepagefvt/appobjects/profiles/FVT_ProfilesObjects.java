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
public class FVT_ProfilesObjects {
	
	//Board
	public static final String ProfilesBoard = "css=textarea[title='Write a message on this profile']";
	public static final String PostStatus = "css=a:contains('Post')";
	public static final String StatusTextArea = "css=div form.lotusForm2 textarea.lotusText";
	public static final String AddResponse = "css=li.lotusFirst div a";
	public static final String ResponseTextArea = "css=textarea";
	public static final String PostResponse = "css=input[value='Post Comment']";
	public static final String PostMessage = "css=a:contains('Post').lotusAction";
	
	//Edit Profile button
	public static final String EditProfile = "css=input[id='editProfileButton']";
	//public static final String EditPhoto = "css=#aEditTabPhoto";
	public static final String EditPhoto = "css=img#imgProfilePhoto.lconnProfilePortrait";
	public static final String EnterPhoto = "css=input[id='photoUpload']";
	public static final String EditPhotoBrowse = "css=input[id='photoUpload']";
	public static final String REMOVE_PHOTO = "css=input[value='Remove Image']";
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
	
	//About
	public static final String AboutMe = "css=a#aEditTabAboutMe";
	public static final String AboutMeText = "";
	public static final String AboutCKEditor = "css=html.CSS1Compat body.cke_show_borders p";
	public static final String SaveandCloseAbout= "css=input[value='Save and Close']";
	
	//Edit Profile Header
	public static final String EditProfileHeader = "css=div[class='lotusHeader']";
	
	//Search for a user
	public static final String ProfilesSearchForUser = "css=input[title='Search']";
	public static final String ProfileSearch = "css=input[class='lotusSearchButton']";
	public static final String SelectProfile = "css=a.fn";
	public static final String FollowPerson= "css=input[value='Follow']";
	public static final String FollowLinkHidden= "css=input#inputProfileActionFollowing_follow.lotusFormButton.lotusHidden";
	
	
	//Invite User
	public static final String ViewBusinessCard = "css=div.javlinHover";
	public static final String BusinessCard_MoreActions = "css=ul.lotusInlinelist li a:contains('More Actions')";
	public static final String MoreActions_Invite = "css=li.lotusMenuSeparator a.email:contains('Invite to My Network')";
	public static final String Invite = "css=input#inputProfileActionAddColleague.lotusBtn";
	public static final String SendInvite = "css=input[value='Send invitation']";
	public static final String AcceptInvite = "css=li.lotusFirst a#accept_link_1";
	public static final String RejectInvite = "css=li a#reject_link_1";
	
	//Add a tag to the user
	//public static final String ProfilesTagTypeAhead = "css=input[id='socialTagName']";
	public static final String ProfilesTagTypeAhead = "css=input#socialTagName.lotusText";
	//public static final String ProfilesAddTag = "css=span.lotusBtnImg a#addTagButtonId.lotusAdd img";
	public static final String ProfilesAddTag = "//img[@alt='Add tag(s) to this profile']";
	public static final String ProfilesTagForFVT = "link="+FVT_ProfilesData.ProfilesTag;
	public static final String ProfilesTagForBVTForSearch = "link="+FVT_ProfilesData.ProfilesTagForSearch;
	
	//add link
	public static final String ProfilesAddLink = "css=input[value='Add Link']";
	public static final String ProfilesAddLinkName = "css=input[id='name']";
	public static final String ProfilesAddLinkLinkname = "css=input[id='url']";
	public static final String AddLinkSave = "css=td.lotusFormFooter > input.lotusFormButton";
	public static final String BrowserTitleName = "Help - IBM Connections";
	public static final String ProfilesHelpTitle = "css=h1.title.topictitle1";
	public static final String RemoveLink = "css=a.lotusDelete img";
	
	//Help
	public static final String HelpFrame = "Help - IBM Connections";
	public static final String HelpPageTitle = "//body/h1";
	
	//File Upload
	public static final String ProfilesFileUpload = "Choose File to Upload";
	
	
	
}
