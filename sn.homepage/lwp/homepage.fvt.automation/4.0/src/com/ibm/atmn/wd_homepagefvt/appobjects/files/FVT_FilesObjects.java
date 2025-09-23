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

package com.ibm.atmn.wd_homepagefvt.appobjects.files;

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

//import com.ibm.lconn.automation.test.bvt.selenium.appobjects.files.*;

@SuppressWarnings("unused")
public class FVT_FilesObjects {
      
    //objects for file upload
    //public static final String UploadFiles_Button = "link=Upload Files";
    public static final String UploadFiles_Button = "css=button.lotusBtn:contains('Upload Files')";
    public static final String NewFolder_Button = "//button[@class='lotusBtn' and @title='Create a new folder to put files into']";
    
    public static final String MyFiles = "css=a[title='See your files'] > span";
    public static final String PublicFiles = "css=a[title='See public files'] > span";
    public static final String UploadNewVersion = "css=a:contains('Upload New Version')";
    public static final String NewVersion = "css=input#lconn_share_widget_Dialog_0_contents.lotusText";
    public static final String ChangeSummary = "css=textarea#lconn_share_widget_Dialog_0_changesummary.lotusText";
	public static final String UploadFiles_Tag = "css=input[id^='lconn_share_widget_TagTypeAhead_']";

    
    public static final String Upload_Button = "css=input[type='submit']";
    public static final String Rename_Link = "css=li.lotusFirst a.lotusAction";
    public static final String Replace_Link = "link=Replace"; 
    public static final String UploadFiles_Name = "//input[@class='lotusText lotusLtr']";
    public static final String FileUploadBrowse = "css=span.lconnUploadContainer > button.lotusBtn";
    public static final String FilesUploadedMessage = "css=div.lotusMessage span";
    
    //objects for multi-file upload 
    public static String FileName_FIELD1 = "lconn_core_upload_ui_FileField_0_lconnUpload_0";
    public static String FileName_FIELD2 = "lconn_core_upload_ui_FileField_0_lconnUpload_1";
    public static String FileName_FIELD3 = "lconn_core_upload_ui_FileField_0_lconnUpload_2";
	public static String[] FileName_FIELDS = {FileName_FIELD1, FileName_FIELD2, FileName_FIELD3};
    
	//FF-only
    public static final String Browse_Button = "css=input[type='file']";
    //public static final String UploadFiles_Name = "//input[@dojoattachpoint='nameInput']";
    
    //Access
    public static String AccessPublic = "css=input#lconn_share_widget_Dialog_0_visibility_public.lotusCheckbox";
    public static String AccessPeopleOrCommunities = "css=input#lconn_share_widget_Dialog_0_visibility_people.lotusCheckbox";
    
    //Create Folder form
    public static String CreateFolderName = "css=input[id$='name']";
    public static String CreateFolderDescription = "css=textarea[id$='description']";
    public static String CreateFolderPrivate = "css=input[id$='visibility_private']";
    public static String CreateFolderPeople = "css=input[id$='visibility_people']";
    public static String CreateFolderPublic = "css=input[id$='visibility_public']";
    public static String CreateFolderEveryoneContribute = "css=input[id$='shareCollectionPublicAuth']";
    
    /**Adding Members*/
	public static String CommunityMembersDropdown = "css=select[id='addMembersSelect']";
	public static String CommunityMembersTypeAhead = "css=input[id='addComMembersWidgetPeopleTypeAhead']";
	public static String AddMembersToExistingTypeAhead = "css=input[id='addMembersWidgetPeopleTypeAhead']";
	public static String fullSearchLink = "Person not listed? Use full search...";
	public static String fullUserSearchIdentifier = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";
	public static String fullUserSearchIdentifier1 = "css=*[id='addMembersWidgetPeopleTypeAhead_popup'] *[dojoattachpoint='searchButton']";
	public static String selectedUserIdentifier = "css=*[id='addComMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
	public static String Add_To_ExistingMembers_Textfield_Typeahead="css=*[id='addMembersWidgetPeopleTypeAhead_popup'] li:nth-child(2)";
    
    //Editing a File uploaded
	public static String VerifyFileName = "css=h1#scene-title a";
	public static String PinFile = "//img[@alt='Pin']";
	public static String RecommendFile = "css=span.lotusMeta";
	public static String AddATagToFile = "link=Add tags";
	public static String EnterTag = "id=fileTags_selectTag";
	public static String SaveTag = "css=input[type=\"submit\"]";
	public static String AddACommentLink = "css=#fileComments.lotusChunk div.lotusChunk a";
	public static String EnterTheComment ="id=addCommentBody";
	public static String Messagediv = "css=div.lotusMessage.lotusConfirm > span";
	public static String ClickForActions = "css=span.lotusBtn a:contains('More Actions')";
	public static String ClickForActionsOptionStopFollowing = "css=tbody[class='dijitReset'] tr:contains('Stop Following')";
	public static String ClickForActionsOptionFollow = "css=tbody[class='dijitReset'] tr:contains('Follow')";
	public static String ClickForActionsOptionMoveToTrash = "css=tbody[class='dijitReset'] tr:contains('Move to Trash')";
	public static String DownloadLink = "css=a.lconnDownloadable";
	
	
	//Move to Trash
	public static String FirstCheckbox = "css=input#list_0.lotusCheckbox";
	public static String MoveToTrashButton = "css=button.lotusBtn:contains('Move to Trash')";
	public static String FileMovedToTrashMessage = "css=div.lotusMessage span";
	public static String TrashLinkinNav = "css=div.lotusMenuSection ul li a:contains('Trash')";
	public static String FileNameInTrash = "css=span.entry-title";
	public static String EmptyTrash = "css=span.lotusBtn a:contains('Empty Trash')";
	public static String TrashEmptyMessage = "css=div.lconnEmpty";
	
	public static String MyFilesViewMoreLink = "css=td.lotusAlignRight a:nth-child(1)";
	public static String MyFilesInNav = "css=div.lotusMenuSection ul li a:contains('My Files')";
	public static String AddToFoldersLink = "link=Add to Folders";
	public static String ChooseFolder = "css=input[id$='recentFolders_0']";
	public static String AddtoFolderCheckbox = "name=_shareAsEditor";
	public static String CancelLink = "link=Cancel";
	public static String AddToFoldersButton = "css=input[value='Add to folders']";
	public static String VerifyFileInFolder = "css=td h4.lotusBreakWord a.entry-title";
	
	public static String MyFoldersInNav = "css=a#menu_1_label";
	public static String SelectAllCheckbox = "css=#list_selectall";
	public static String FoldersInMyFolders = "css=a.entry-title";
	public static String ClickForActionsOptionDelete = "css=tbody[class='dijitReset'] tr:contains('Delete')";
	public static String CheckForEntryInMyFolders = "css=a.entry-title";
	public static String MyFilesEmptyMessage = "css=div.lconnEmpty";
	public static String AddFiles = "css=span.lotusBtn a";
	//public static String CheckFilesAdd = "css=input#lconn_files_widget_FilePicker_0_myFiles_recentFiles_0";
	public static String CheckPrivateFileAdd = "css=td.lotusNowrap label[title*=Private]";
	public static String CheckPublicFileAdd = "css=td.lotusNowrap label[title*=Public]";
	public static String AddFileSubmit = "css=input.lotusFormButton[value=Add Files]";
	
	//Share Files
	public static final String ShareType = "css=select#lconn_share_widget_MemberInput_0_searchSourceDropdown";
	public static final String ShareRole = "css=select#lconn_share_widget_MemberInput_0_roleDropdown";
	public static final String ShareSave = "css=div.lotusDialogFooter input.lotusFormButton";
	public static final String FileMembersTypeAhead = "css=input#lconn_share_widget_MemberInput_0_people.lotusText";
	public static final String FileMembersTypeAhead_PromptList = "css=#lconn_share_widget_MemberInput_0_people_popup";
	//public static String fullUserSearchIdentifierFiles = FileMembersTypeAhead_PromptList + "_searchDir";
	public static String fullUserSearchIdentifierFiles = "css=ul#lconn_share_widget_MemberInput_0_people_popup.dijitReset li.dijitMenuItem[dojoattachpoint='searchButton']";
	//public static String selectedUserIdentifierFiles = "css=*[id$='#lconn_share_widget_MemberInput_0_people_popup'] li:nth-child(2)";
	public static String selectedUserIdentifierFiles = "css=#lconn_share_widget_MemberInput_0_people_popup0";
	public static String fullSearchLinkFiles = "Person not listed? Use full search...";
	
	//Communities Files
	public static final String ShareFiles = "link=Share Files";
	public static final String BrowseFiles = "css=input#lconn_share_widget_Dialog_0_contents_contents.lotusText";
	public static final String Upload_Button_Comm = "css=input[value='Upload']";
	public static final String SelectPublicFile = "css=label[title*='FVT Public File']";
	public static final String SelectModCommFile = "css=label[title*='FVT Moderated Community File']";
	public static final String SelectPubCommFile = "css=label[title*='FVT Public Community File']";
	public static final String SelectPrivCommFile = "css=label[title*='FVT Private Community File']";
	public static final String ShareFilesConfirm = "css=input[value='Share Files']";
	
	
    }
