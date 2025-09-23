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

package com.ibm.atmn.wd_homepagefvt.appobjects.wikis;

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
public class WikisObjects {
    
	private static final String BUNDLE = "test.properties";
    private static final Properties properties;
    static
    {
      properties = new Properties();
      try
      {
        properties.load(WikisObjects.class.getResourceAsStream(BUNDLE));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    
    //Generate a random number from date & time
	public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }
    
	//URL for testing 
	public static final String TestURL = properties.getProperty("test_url", "http://localhost:9080");
	public static final String TestServer = properties.getProperty("test_server");
	public static final String SeleniumPort = properties.getProperty("test_port");
	public static final String TestBrowser = properties.getProperty("test_browser");
	public static final String ComponentName = properties.getProperty("test_component");
	
	public static final String browserTimeout = properties.getProperty("test_timeout"); 
	public static final String browserSleep = properties.getProperty("test_sleep");
	
	
	//objects on the Login Page
	public static final String WIKI_USERNAME_FIELD = "css=input[id='username']";
	public static final String WIKI_Password_FIELD = "css=input[id='password']";
    public static final String WIKI_Login_Button = "css=input[value='Log In']";
    public static final String Error_Message_Text="class=errorMessage";
    public static final String Login_Link="link=Log In";
   
    //Objects on the main service page
    public static final String Subscribe_To_Public_Wikis_Link = "link=Subscribe to public wikis";
    public static final String Subscribe_To_My_Wikis_Link = "link=Subscribe to wikis you own";
    public static final String Start_A_Wiki_Title = "Start a Wiki";
    public static final String Public_Wikis_Tab="css=div.lotusInner div ul li a:contains('Public Wikis')";
    public static final String Public_Wikis_List_Header="Public Wikis";
    public static final String Public_Wikis_List="id=list";
    public static final String No_Wikis_In_List="No items found";
    public static final String No_Edits_In_List="You have not edited any pages in this wiki.";
    public static final String Public_Wikis_Tab_Focus="class=lotusSelected";
    public static final String Recently_Updated_Top_Link = "//div[@id='updatedwikis']/div/table[@id='recent']/tbody/tr[2]/td/div/div[1]/h4/a";
    public static final String New_Wikis_Top_Link = "//div[@id='newwikis']/div/table[@id='recent']/tbody/tr[2]/td/div/div[1]/h4/a";  
    public static final String Start_New_Wiki_Button="link=Start a Wiki";
    public static final String Public_Wikis_Search_Dropdown="xpath=id('searchForm')/table/tbody/tr/td[1]/div/a";
    public static final String My_Wikis_And_This_Wiki_Search_Dropdown="xpath=id('searchForm')/table/tbody/tr/td[1]/div/a/span";
    public static final String Search_Textfield_Content="xpath=id('searchForm')/table/tbody/tr/td[1]/div/a/span";
    public static final String Tags_Tooltip_Text="Tags are keywords added to wikis to make them easier to find later. To show wikis with a particular tag, start typing the tag and then select it. Or click a tag in a list or cloud. Use the slider to expand the cloud. Bold tags are those most commonly used.";
    public static final String Tags_NoTags_Text="No tags yet";
    public static final String GoToWiki_Search_Dropdown="id=searchForm_wikis_dropdown";
    public static final String GoToWiki_Search_PopupItem="id=searchForm_wikis_popup";
    public static final String GoToWiki_Search_Textfield="xpath=id('searchForm_wikis')";
    public static final String Search_Icon="xpath=id('searchForm_submit')";
    public static final String GoToWiki_No_Results_Dropdown="class=dijitMenuItem resultsNode";
    
    public static final String  LoginStartContributing_Button= "link=Log In / Start Contributing!";
    
    //Objects for Pages section
    public static final String  Index_Link= "link=Index";
	public static final String  Pages_Section_Title= "css=[id='span_filter_wiki.filter.allpages']";
	public static final String  Go_To_Page_Link= "link=Go to Page...";
	public static final String  IEdited_Link= "link=I Edited";
	public static final String  EditedBy_Link= "link=Edited by...";
	public static final String  Search_Page_Field= "css=input[id='pagenavsearchbox']";
	public static final String  Search_Page_Button= "//ul[@id='allPagesList']/li[1]/div/form/span/input";
	public static final String  Page_Search_popup= "css=[id='pagenavsearchbox_popup']";
	public static final String  Search_EditedBy_Field= "css=[id='personalpagesearchbox']";
	public static final String  Search_EditedBy_Dropdown= "css=[id='personalpagesearchbox_dropdown']";
	public static final String  Search_EditedBy_Button= "//div[@id='allPagesList']/li[5]/div/form/span/input";
	public static final String  Edited_By_Search_Dropdown= "css=ul[id='personalpagesearchbox_popup']";
	public static final String  Edited_By_Search_First_Item= "css=[id='personalpagesearchbox_popup0']";

	//Objects for Trash
	public static final String Trash_Link= "link=Trash";
	public static final String Restore_Page_Button= "link=Restore";
	public static final String Delete_Page_Button= "link=Delete";
	public static final String Empty_Trash_Button= "link=Empty Trash";
	
	//Objects for Wiki Sizing feature
	public static final String  Wiki_Sizing_Text= "xpath=id('list')/div[2]/table/tbody/tr[1]/td[4]/span";
	
	//Objects for Members section
	public static final String  View_All_Link= "css=div[id^='lconn_share0_widget_MembersSummary_'] a[class='lotusAction']";
												
	//Objects for Site Tools section
	public static final String  SiteTools_Heading= "Site Tools";
	public static final String  Members_Link= "link=Members";
	public static final String  MembersandRoles_Text= "Member and Roles";
	public static final String  Manage_Access_Link= "link=Manage Access";
	public static final String  Manage_Access_Text= "Manage Access";
	public static final String  Edit_Wiki_Name_Textfield= "css=[id='editWiki_title']";
	public static final String  Edit_Wiki_Tags_Textfield= "css=[id='qkrTagTextbox']";
	public static final String  Edit_Wiki_Description_Textfield= "css=[id='editWiki_description']";
    public static final String 	Delete_Wiki = "//*[contains(@id,'dijit_MenuItem_')][contains(@id,'_text')]";
    public static final String 	Subscribe_To_This_Page_Link_Object = "//div[@id='wikiFooter']/div/a[1]";
    public static final String 	Subscribe_To_This_Page_Link = "link=Subscribe to this page";
    public static final String 	Subscribe_To_These_Comments_Link = "link=Subscribe to these comments";
    public static final String 	Subscribe_To_These_Page_Versions_Link = "link=Subscribe to these page versions";
    public static final String 	Subscribe_To_These_Attachments_Link = "link=Subscribe to these attachments";
    public static final String 	Subscribe_To_These_Trashed_Pages = "css=[class='lotusFeed lotusAction']";
    public static final String 	Username_on_PageView = "css=*[class='lotusPerson fn vcard hasHover']";
    public static final String  Wiki_Nav_Tree= "id=lconnWikisNavTree";
	public static final String  Wiki_Nav_Table= "id=sideNav";
	public static final String  Wiki_Nav_Table2= "class=lotusSelected";
	public static final String  Create_New_Page_Link= "link=New Page";
	public static final String  AlphabeticalOrder_Pages_Link= "Alphabetical";
	public static final String  RecentlyUpdated_Pages_Link= "Recently Updated";
	public static final String  MostActive_Pages_Link= "Most Active";
	public static final String  OwnedByMe_Pages_Link= "Owned By Me";
	public static final String  Add_Members_Link= "link=Add Members";
	public static final String 	Updated_By_Field="xpath=id('wikiFooter')/ul[1]/li";
	public static final String 	HomePage_Title_Class = "class=qkrWideInner";
	public static final String 	No_Results_Dropdown = "xpath=id('pagenavsearchbox_popup')/li[2]";
	public static final String 	Wiki_Title_In_Nav = "//*[contains(@id,'quickr_share_wiki_util__NavigationTreeNode_')]/div[2]/div";
	public static final String 	Cancel_Link_In_Popup = "css=div[class='lotusDialogFooter'] [title='Cancel']";
	public static final String 	OK_Button_In_Popup = "css=div[class='lotusDialogFooter'] [value='OK']";
	public static final String 	Delete_Button_In_Popup = "css=div[class='lotusDialogFooter'] [value='Delete']";
	public static final String  Permanently_Delete_This_Wiki = "css= input[value='Delete'].lotusFormButton";
	public static final String 	Delete_Check = "id=lconn_wikis_action_DeleteWiki_confirm";
	public static final String 	Delete_Signature = "id=lconn_wikis_action_DeleteWiki_signature";
	
	
	//Homepage inline tabs
	public static final String  Comments_Tab= "css=a[id='comments_link']";
	public static final String  Attachments_Tab= "css=a[id='attachments_link']";
	public static final String  About_Tab= "css=a[id='about_link']";
	public static final String  Versions_Tab= "css=a[id='versionHistory_link']";
	
	//Homepage Buttons
	public static final String  Edit_Button= "css=a[title='Click to edit this page']";
	public static final String  Page_Actions_Button= "css=a[title='Create a child page or peer page, print the page, move the page, download the page as an HTML file, or delete the page.']";
	public static final String  Wiki_Actions_Button= "css=a[id='wikiActionMenuLink']";
	public static final String 	Follow_Button = "css=a[id^='lconn_core_FollowMenu_']";
	public static final String 	Page_Actions_Button_For_Editors = "css=a[title='Create a child page or peer page, print the page, move the page, or download the page as an HTML file.']";
	public static final String 	Page_Actions_Button_For_Readers = "css=a[title='Print the page, or download the page as an HTML file.']";
	public static final String  Add_tags_Link= "link=Add tags";
	public static final String  Add_or_RemoveTags_Link= "link=Add or remove tags";
	public static final String  Add_or_RemoveTags_Text= "Add or remove tags";
	public static final String  Tag_Textbox= "css=input#lconn_share0_widget_Tagger_0_selectTag.lotusText";
	public static final String  Tag_Textbox_2="css=input#pageTags_selectTag.lotusText";
	public static final String  Tag_Textbox_3="css=input#lconn_share0_widget_TagTypeAhead_0.lotusText";
	public static final String  Edit_Link= "link=Edit";
	public static final String  DownLoad_Icon= "";
	public static final String  Print_Icon= "";
	public static final String  RSS_Icon= "";
	public static final String  Download_Page_Link= "link=Download Page";
	public static final String 	New_Wiki_Link = "link=Verify_PageViewUI_Wiki";
	public static final String  Breadcrumbs_Root_Page= "css=div[id^='lconn_share0_widget_Breadcrumbs_'] a:nth-child(1)";
	public static final String  breadcrumb= "wikiBreadcrumbs";
	public static final String  Recommend_a_Page_Text= "Recommend this page";
	public static final String  Create_This_Page_Button= "css=[value='Create This Page']";
	public static final String  Create_A_Page_Button= "css=[value='Create A Page']";
	public static final String  All_Page_Deletion_Warning= "css=td[class='lotusFormTitle']";
	public static final String  All_Breadcrumb_Text= "css=div[id^='lconn_share0_widget_Breadcrumbs_']";
	public static final String 	About_This_File_Updated_Field_Username="xpath=id('about')/div[1]/table/tbody/tr[1]/td/a";
	public static final String 	About_This_File_Updated_Field="xpath=id('about')/div[1]/table/tbody/tr[1]/td";
	public static final String 	About_This_File_Added_Field_Username="xpath=id('about')/div[1]/table/tbody/tr[2]/td/a";
	public static final String 	About_This_File_Added_Field="xpath=id('about')/div[1]/table/tbody/tr[2]/td";
	public static final String 	About_This_File_Size_Field="xpath=id('about')/div[1]/table/tbody/tr[3]/td/span";
	public static final String 	About_This_File_PageViews_Field="xpath=id('about')/div[1]/table/tbody/tr[4]/td";
	public static final String 	About_This_File_Page_Viewed_By_Field="xpath=//*[contains(@id,'quickr_widget_DownloadInfoStream')]/div[1]";
	public static final String 	About_This_File_Page_Viewed_By_Text="xpath=//*[contains(@id,'quickr_widget_DownloadInfoStream')]/div[1]/span[1]/a";
	public static final String 	Wiki_Navigation_Title="xpath=//*[contains(@id,'quickr_widget_DownloadInfoStream')]/div[1]/span[1]/a";
	public static final String 	Text_Content_In_Homepage="css=[id='wikiContentDiv']";
	public static final String 	Initial_Text_Content_In_Homepage_Header="xpath=id('pageDetails')/div[1]/div/h1";
	public static final String 	New_Text_Content_In_Homepage_Header="xpath=id('pageDetails')/div[1]/div";
	public static final String 	Back_To_Link="css=*[id='lotusContent'] *[class='lotusBreadcrumbs']";
	public static final String 	Site_Map_Title="xpath=id('lotusContent')/div[2]/h1";
	public static final String 	Pages_Index_Title="xpath=id('lotusContent')/div[3]/h1";
	public static final String 	Pages_Edited_By_Me_Title="xpath=id('lotusContent')/div[3]/h1";

	//General Tags section UI objects for both Public Tags & My Tags
    public static final String Link_for_Tag_List="link=List";
    public static final String Link_for_Tag_Cloud="link=Cloud";
    public static final String Link_for_Find_a_Tag="link=Find a Tag";
    public static final String Tag_Search_Textfield="xpath=id('lconnTagWidgetcommonTagsTypeAhead')";
	public static final String Wiki_Statics_bar="id=lotusColRight";
	public static final String Tag_Options_dropdownList_on_TagSearchField="id=tagSearchBox_popup";
	public static final String Tag_Search_Field_SubmitButton="//div[@id='lconnTagWidget']/div/div[5]/form/span/input";
    public static final String Tag_attheTop_of_OrderedList="xpath=//div[@id='userTagList']/div[2]/ul/li[1]/span/a[1]";
    public static final String No_oftimes_Tagused_attheTop_of_OrderedList="xpath=//div[@id='userTagList']/div[2]/ul/li[1]";
	public static final String Tags_Search_Icon="xpath=id('lotusColLeft')/div[2]/div/div[1]/form/span";
	public static final String Tags_View_As_List="xpath=id('userTagList')/div[3]/ul/li[1]";
	public static final String Tags_Cloud="xpath=id('userTagList')/p[1]/ul";
	public static final String Tags_Container="xpath=id('tagCloudContainer')";
	
	//Objects for Page View
	public static final String Members_Expand_Collapse_Arrow = "css=*[title='Expand and collapse Members section']";
	public static final String Pages_Expand_Collapse_Arrow = "css=*[title='Expand and collapse Pages section']";
	public static final String Date_Expand_Collapse_Arrow="css=*[title='Expand and collapse Date section']";
	public static final String Tags_Expand_Collapse_Arrow="css=*[title='Expand and collapse Tags section']";
	public static final String Tags_Expanded_Arrow="css=a[class='lotusSprite lotusArrow lotusTwistyOpen'][title='Expand and collapse Tags section']";
	public static final String Tags_Collapsed_Arrow="css=a[class='lotusSprite lotusArrow lotusTwistyClosed'][title='Expand and collapse Tags section']";
	                                                  
	public static final String Page_View_Tags_Title="css=[id='span_filter_tags.cloud']";
	public static final String Page_View_Tags_Search_Icon="xpath=id('lotusColLeft')/div[2]/div/div[1]/form/span/input";
	
	//Objects for Recommendations UI
	public static final String Recommendations_Info="css=a[id^=lconn_wikis_widget_RecommendInfo_]";
	public static final String Recommendations_Info_Alternate="css=a[id^=lconn_wikis_widget_RecommendInfo_][class='lotusRecommend']";
	public static final String Recommend_Image="css=img[class^='lconnSprite lconnSprite-iconRating16']";
	public static final String Recommend_Link="css=a[id^='lconn_wikis_widget_RecommendInfo_'] span[class='lotusMeta']";
	
	//Like
	public static final String LikeWikiPage="css=a[id*=TOGGLE_lconn_wikis_widget_WikiLike_].lotusLikeAction";

	
	//Objects for Public Tags
	public static final String Public_Tags_Search_Section="id=filter_Public tags";
	public static final String Public_Tag_Search_Field_SearchIcon="xpath=//div[@id='filter_Public tags']/div/div[1]/div/form/span/input";
	public static final String Tooltip_for_Public_Tags_Section="//div[@id='filter_tags.cloud']/h2/a[2]/img";
	public static final String Public_Tags_Expand_Collapse_Arrow="xpath=id('filter_Public tags')/h2/a[1]";
	public static final String Public_Tags_Title="xpath=id('lotusColLeft')/div[2]/h2/span";
	
	//Objects for My Tags
	public static final String My_Tags_Search_Section="id=filter_Tags";
	public static final String My_Tag_Search_Field_SearchIcon="xpath=id('filter_Tags')/div/div[1]/div/form/span/input";
	public static final String Tooltip_for_My_Tags_Section="//div[@id='filter_Tags']/h2/a[2]/img";
	public static final String My_Tags_Expand_Collapse_Arrow="xpath=id('filter_Tags')/h2/a[1]";
	
    //Objects in a Page View
    public static final String  Welcome_Homepage= "Welcome";
    public static final String  Selected_Tag_list= "xpath=id('userTagList')/div[3]/ul/li[1]/span";
	public static final String 	Date_Section_Title="css=[id='span_filter_files.filter.date']";
	public static final String 	Date_Links_Today="link=Today";                    		
	public static final String 	Date_Links_Last_Week="link=Last week";  
	public static final String 	Date_Links_Last_30_Days="link=Last 30 days";  
	public static final String 	Date_Links_Last_Year="link=Last year";
	public static final String 	Members_Section_Title="css=[id='span_filter_members.summary']";
	public static final String 	PressedTableIcon="class=lotusSprite lotusView lotusDetailsOn";
	public static final String 	UnPressedTableIcon="class=lotusSprite lotusView lotusDetailsOff";
	public static final String 	PressedListIcon="class=lotusSprite lotusView lotusSummaryOn";
	public static final String 	UnPressedListIcon="class=lotusSprite lotusView lotusSummaryOff";
	public static final String 	Page_Index_Top_Pagination="css=div[dojoattachpoint='topPageNode']";
	public static final String 	Page_Index_Bottom_Pagination="css=div[dojoattachpoint='bottomPageNode']";
	public static final String 	Page_Index_Text_In_Top_Pagination="xpath=id('list')/div[1]/div";
	public static final String 	Lightbox_Header="css=form[class='lotusDialog lotusForm'] h1[id^='lconn_share0_widget_Dialog']";
	public static final String 	Warning_Msg_Text="css=td[id$='_qkrDialogText']";
	public static final String 	Members_Header="css=[id='lotusContent'] *[class='lotusHeader']";
	public static final String 	Manage_Access_Header="xpath=//*[contains(@id,'quickr_widget_Dialog_')]/div[2]/div/form/h1";
	public static final String 	All_Authenticated_Users="xpath=//*[contains(@id,'dijit_Dialog')]/div[2]/div/form/div[1]/div/table/tbody/tr[2]/td[2]/ul/li[1]/a";
	public static final String 	Anonymous_Users_Field="xpath=//*[contains(@id,'dijit_Dialog')]/div[2]/div/form/div[1]/div/table/tbody/tr[2]/td[2]/ul/li[2]/a";
	public static final String 	Owner_Field="xpath=//*[contains(@id,'dijit_Dialog')]/div[2]/div/form/div[1]/div/table/tbody/tr[2]/td[2]/ul/li[3]/a/span";
	public static final String 	User_Group_Dropdown="xpath=//*[contains(@id,'dijit_Dialog')]/div[2]/div/form/div[1]/div/table/tbody/tr[2]/td[2]/table/tbody/tr/td[1]/select";
	public static final String 	User_Role_Dropdown="xpath=//*[contains(@id,'dijit_Dialog')]/div[2]/div/form/div[1]/div/table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]/select";
	public static final String 	Role_Or_Kind_Filter_Icon="css=a[class='lotusFilter']:nth-child(1)";
	public static final String 	Role_Or_Kind_Filter_Icon2="css=a[class='lotusFilter']:nth-child(2)";
	public static final String 	Anonymous_Users_Default_Avatar="xpath=id('find')/tbody/tr[3]/td[2]/ul/li[1]/a";
	public static final String 	Authenticated_Users_Default_Avatar="xpath=id('find')/tbody/tr[3]/td[2]/ul/li[2]/a";
	public static final String 	Person_Kind_Filter_Text="People";
	public static final String 	Groups_Kind_Filter_Text="Groups";
	public static final String 	Users_Role_Filter_Text="Users only";
	public static final String 	Groups_Role_Filter_Text="Groups only";
	public static final String 	Owner_Role_Filter_Text="Owners";
	public static final String 	Editor_Role_Filter_Text="Editors";
	public static final String 	Reader_Role_Filter_Text="Readers";
	public static final String 	Username_Textfield="xpath=id('membershipOptions_memberTextbox')";
	public static final String 	Members_And_Permissions_OK="xpath=//*[contains(@id,'quickr_widget_Dialog')]/div[2]/div/form/div[2]/input";
	public static final String 	Place_Bar_Title="css=[id='lotusPlaceBar'] *[class='lotusInner'] h2:nth-child(1)";
	public static final String  Save_Button= "css=input[value='Save']";
	public static final String  Comments_Save_Button= "//div[@id='wikiPageComments']/form/table/tbody/tr[2]/td/input";
	public static final String 	First_Search_Dropdown_Option_inPageView = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[1]/td[2]";
    public static final String 	Second_Search_Dropdown_Option_inPageView = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[2]/td[2]";
    public static final String 	Third_Search_Dropdown_Option_inPageView = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[3]/td[2]";
    public static final String 	Fourth_Search_Dropdown_Option_inPageView = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[4]/td[2]";
    public static final String 	Fifth_Search_Dropdown_Option_inPageView = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[5]/td[2]";
    public static final String 	Sixth_Search_Dropdown_Option_inPageView = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[7]/td[2]";

    public static final String First_Search_Dropdown_Option_inPageView_For_AnonymousUser = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[1]/td[2]";
    public static final String Second_Search_Dropdown_Option_inPageView_For_AnonymousUser = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[2]/td[2]";
    public static final String Third_Search_Dropdown_Option_inPageView_For_AnonymousUser = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[3]/td[2]";
    public static final String Fourth_Search_Dropdown_Option_inPageView_For_AnonymousUser = "xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[5]/td[2]";

    //Edit Attachments
    public static final String Filename_Textfield = "xpath=//*[contains(@id,'quickr_widget_Dialog_')]/div[2]/div/form/div[1]/div/table/tbody/tr[1]/td[2]/input";
    public static final String Description_Textfield =  "xpath=//*[contains(@id,'quickr_widget_Dialog_')]/div[2]/div/form/div[1]/div/table/tbody/tr[2]/td[2]/textarea";
    public static final String Replace_Contents_Textfield = "xpath=//*[contains(@id,'quickr_widget_Dialog_')]/div[2]/div/form/div[1]/div/table/tbody/tr[3]/td[2]/input";
	public static final String Attachment_Heading = "xpath=id('pageDetails')/div/h2/span";
	public static final String Link_For_2nd_Page = "link=2";
	public static final String Link_For_3rd_Page = "link=3";
	public static final String Link_For_4th_Page = "link=4";
	
    //objects on Tagging Editor
	public static final String  OK_Button= "css=input[value='OK']";
	public static final String  Tagging_Editor= "id=wikiPageTags_selectTag";
	public static final String  Delete_Tag_Icon= "xpath=id('lconn_share0_widget_Tagger_0')/a[4]/img";
	public static final String 	TagSelector1="xpath=//div[@id='lconn_share0_widget_Tagger_1']/a[2]/img";
	public static final String 	TagEditorTextFieldInput="css=input[id*=lconn_share0_widget_Tagger_].lotusText";
	public static final String 	TagEditorTextField="css=span[id^='lconn_share0_widget_Tagger_'] input[id$='_selectTag']";
	public static final String 	NoTags="css=span[id^='lconn_share0_widget_Tagger_'] [class='qkrEmpty']";

    public static final String  Remove_Icon= "css=a[class='qkrRemove']";
    public static final String  Tagged_With_Icon= "xpath=id('lotusContent')/div[2]/div/a";
    
	//objects on Page Edit
	//public static final String  Save_and_Close_Link= "xpath=//input[@value='Save and Close']";
	public static final String  Save_and_Close_Link= "css=input[value='Save and Close']";
	public static final String  Cancel_Link= "link=Cancel";
	public static final String  Save_Link= "link=Save";
	public static final String  Page_Name_Textfield_In_Editor= "css=*[id='title']";
	public static final String  Add_Tag_Link= "link=Add tags";
	public static final String  Rich_Text_Tab= "link=Rich Text";
	public static final String  Rich_Text_Tab_Label= "Rich Text";
	public static final String  HTML_Source_Tab= "link=HTML Source";
	public static final String  HTML_Source_Tab_Label= "HTML Source";
	public static final String  Wiki_Text_tab= "link=Wiki Text";
	public static final String  Wiki_Text_Tab_Label= "Wiki Text";
	public static final String  Preview_Tab= "link=Preview";
	public static final String  Preview_Tab_Label= "Preview";

	public static final String  bread_crumb= "class=lotusTiny";
	public static final String  Show_or_Hide_HelpGuide_Link= "id=qkrHideLink";
	public static final String  HideGuide_Link= "link=Hide Guide";
	public static final String  ShowGuide_Link= "link=Show Guide";
	public static final String  HTML_guide= "id=htmlReference";
	public static final String  Wiki_markup_guide= "id=wikiReference";
	public static final String 	New_Save_and_Close_Link = "xpath=//div[@id='lotusContent']/div[2]/div[2]/ul/li[1]/a";
	public static final String 	Edit_Page_Title = "xpath=id('lotusPlaceBar')/div/div/h2/b";
	public static final String 	WikiText_Editor = "//Body";
	public static final String 	WikiText_Editor_Frame = "//html/body/table/tbody/tr[2]/td/iframe";//*[@id='fck___Frame']
	public static final String  WikiText_Tab_TextArea= "//html/body[@id='body']/div[@id='lotusFrame']/div[@id='lotusMain']/div[@id='lotusContent']/div[@id='pageDetails']/textarea";
	public static final String 	New_Page_Title_Textfield = "css=input#name.lotusText";
	public static final String  Breadcrumbs_In_Edit_Mode= "xpath=//*[contains(@id,'quickr_widget_Breadcrumbs_')]";
	public static final String  Breadcrumbs_In_Edit_Mode_Root= "xpath=//*[contains(@id,'quickr_widget_Breadcrumbs_')]/span[3]/a";
	public static final String  CKEditor = "//html/body";
	public static final String  CKEditor_WikiTextTab = "css=*[id='wikiTextEditor']";
	
	public static final String  CKEditorFrame = "//*[@class='cke_contents']/iframe";
	public static final String  Duplicate_Page_Warning = "A page or file with the name you have specified already exists. Please choose a different name.";
	public static final String  Jump_To_Page = "css=input[id='qkrJumpToInput']";
	
	//objects on Comments Page
	public static final String  Add_Comment_Link= "link=Add a comment";
	public static final String  Add_Comment_Editor= "css=[id='addCommentBody']";
	public static final String  Edit_Comment_Editor= "css=form[class='lotusForm qkrAddComment'] textarea[id^='commentBody_']";
	public static final String 	Link_toShow_1stPageof_Comments="link=1";
	public static final String 	Link_toShow_2ndPageof_Comments="link=2";
	public static final String 	Link_toShow_3rdPageof_Comments="link=3";
	public static final String 	Cancel_Comments_Link = "css=form[class='qkrAddComment lotusForm'] *[dojoattachevent='onclick:cancelCreate']";
	public static final String 	CommentPagination_Activated1= "link=1";
	public static final String 	CommentPagination_Activated2= "link=2";
	public static final String 	CommentPaginationNavigationBar="class=lotusInlinelist";
	public static final String 	CommentPagination_Activated3= "link=3";
	public static final String 	CommentPagination_Deactivated="css=ul[class='lotusInlinelist'] span";
	public static final String 	CommentPagination_Activated4= "link=4";
	public static final String 	PagingDetailsInNavigationBar="css=div[class='lotusPaging'] div[class='lotusLeft']";
	                                                                                       
	//Objects on Members Page
	public static final String Remove_Members_Button="link=Remove Members";
	public static final String Add_Members_Button= "link=Add Members";
	public static final String Manage_Access_Button= "link=Manage Access";
	public static final String Link_toshow_10Members_perpage="link=16";
    public static final String Link_toshow_25Members_perpage="link=32";
    public static final String Link_toshow_50Members_perpage="link=64";
    public static final String PageID_Showing_10Members_perpage="css=*[id='s10']";
    public static final String PageID_Showing_25Members_perpage="css=*[id='s25']";
    public static final String PageID_Showing_50Members_perpage="css=*[id='s50']";
    public static final String PageID_Showing_16Members_perpage="link=16";
    public static final String PageID_Showing_32Members_perpage="link=32";
    public static final String PageID_Showing_64Members_perpage="link=64";
    public static final String Number_of_Members = "//div[@id='list']/div[@class='lotusPaging']/div[@class='lotusLeft']";
    public static final String Roles_Radio_Button_List="css=*[id='roles_list']";
    public static final String Edit_Member_Dialog="css=div[class='dijitDialogPaneContent']";
    public static final String MyFiles_Heading_Text = "My Files";
    public static final String PublicFiles_Heading_Text = "Public Files";
    public static final String PublicWikis_Heading_Text = "Public Wikis";
    
	//My Wikis 'Wikis I can' section objects
	public static final String MyWikis_Editor_Filter="link=I'm an Editor";
	public static final String MyWikis_Owner_Filter="link=I'm an Owner";
	public static final String MyWikis_Reader_Filter="link=I'm a Reader";
	public static final String MyWikis_Follow_Filter="link=I'm Following";
	public static final String MyWikis_Editor_Filter_Text="I'm an Editor";
	public static final String MyWikis_Owner_Filter_Text="I'm an Owner";
	public static final String MyWikis_Reader_Filter_Text="I'm a Reader";
	public static final String MyWikis_Following_Filter_Text="I'm following";

	public static final String MyWikis_Filter_Text="Filtered by:";
	public static final String MyWikis_Test_Wiki_SubText1="Test_Roles1_Wiki_";
	public static final String MyWikis_Test_Wiki_SubText2="Test_Roles2_Wiki_";
	public static final String Verify_Role_Filters_Wiki_Title_For_Concat = "Test_Roles1_Wiki_";
	public static final String Verify_Nav_Filters_Wiki_Title_For_Concat = "Test_Roles2_Wiki_";
	
	//My Wikis 'Tags' section objects
	public static final String Tags_Section_Title="xpath=id('label_Tags')";
	public static final String MyWikis_Tags_Collapsible_Icon="xpath=id('filter_Tags')/h2/a[1]";
	
    //Objects on subsection Member Role
    public static final String Member_Role_Heading= "Role";
    public static final String Members_Role_Expand_Collapse_Arrow = "css=*[title='Expand and collapse Role section']";
    public static final String Tooltip_For_MemberRole_Field="css=div[id='filter_members.filter.role'] a[id^='lconn_wikis_widget_HelpLauncher_']";
    public static final String Owner_Filter_Link="link=Owner";
    public static final String Editor_Filter_Link="link=Editor";
    public static final String Reader_Filter_Link="link=Reader";
    
    //Objects on subsection Member Kind
    public static final String Member_Kind_Heading= "Kind";
    public static final String Members_Kind_Expand_Collapse_Arrow= "css=*[title='Expand and collapse Kind section']";
    public static final String Tooltip_For_MemberKind_Field="css=div[id='filter_members.filter.type'] a[id^='lconn_wikis_widget_HelpLauncher_']";
    public static final String Person_Filter_Link="link=Person";
    public static final String Group_Filter_Link="link=Group";
    
    public static final String Link_toFilter_Members_bySpecialGroups="link=special groups";
    public static final String Sortby_Type_Option="link=Type";
    //Next & Previous inactive links
    public static final String Top_Next_Page_Link_InActive="css=*[dojoattachpoint='topPageNavigateNode'] li[dojoattachpoint='tNextTextNode']";
    public static final String Top_Previous_Page_Link_InActive="css=*[dojoattachpoint='topPageNavigateNode'] li[dojoattachpoint='tPreviousTextNode']";
    public static final String Bottom_Next_Page_Link_InActive="css=*[dojoattachpoint='bottomPageNavigateNode'] li[dojoattachpoint='bNextTextNode']";
    public static final String Bottom_Previous_Page_Link_InActive="css=*[dojoattachpoint='bottomPageNavigateNode'] li[dojoattachpoint='bPreviousTextNode']";
    //Next & Previous active links
    public static final String Top_Next_Page_Link_Active="css=ul[dojoattachpoint='topPageNavigateNode'] li[dojoattachpoint='tNextLinkNode']";
    public static final String Top_Previous_Page_Link_Active="css=*[dojoattachpoint='topPageNavigateNode'] li[dojoattachpoint='tPreviousLinkNode']";
    public static final String Bottom_Next_Page_Link_Active="css=*[dojoattachpoint='bottomPageNavigateNode'] li[dojoattachpoint='bNextLinkNode']";
    public static final String Bottom_Previous_Page_Link_Active="css=*[dojoattachpoint='bottomPageNavigateNode'] li[dojoattachpoint='bPreviousLinkNode']";
    
    public static final String Link_toshow_SecondPage_ofMembersList="link=2";
    public static final String Link_toshow_ThirdPage_ofMembersList="link=3";
    public static final String Reader_Role_Option_onEditMemberLightBox="css=input[id$='_role_reader']";
    public static final String Editor_Role_Option_onEditMemberLightBox="css=input[id$='_role_editor']";
    public static final String Owner_Role_Option_onEditMemberLightBox="css=input[id$='_role_manager']";
    
    //Objects on Delete Wiki Light Box	
    public static final String  Delete_Wiki_Option= "link=Delete wiki";
    
    //Objects on Delete Page Light Box	
    public static final String 	Delete_Button= "css=input[value='Delete']";
	
    //Autosave objects
    public static final String  Autosave_Indicator= "xpath=id('qkrAutosaveContainer')/span";
    public static final String  Draft_Message_Title= "xpath=id('lotusContent')/div[1]/div[1]/span/span[1]";
    public static final String 	Discard_Button= "xpath=//*[contains(@id,'quickr_widget_Dialog_')]/div[2]/div/form/div[2]/input";
   
    //Objects on Manage Access popup
    public static final String  Radio_Button1= "xpath=//*[contains(@id,'quickr_share_wiki_widget_WikiAccessOptions_')][contains(@id,'_publicViewingAllowed')]";
    public static final String  Radio_Button2= "xpath=//*[contains(@id,'quickr_share_wiki_widget_WikiAccessOptions_')][contains(@id,'_publicViewingDisabled')]";
    public static final String  Radio_Button3= "xpath=//*[contains(@id,'quickr_share_wiki_widget_WikiAccessOptions_')][contains(@id,'_authenticatedEditingAllowed')]";
    public static final String  Radio_Button4= "xpath=//*[contains(@id,'quickr_share_wiki_widget_WikiAccessOptions_')][contains(@id,'_authenticatedEditingDisabled')]";
   
    public static final String  Start_A_Wiki_Radio_Button_Text1= "xpath=//*[contains(@id,'quickr_share_wiki_widget_WikiAccessOptions_')]/td[2]/div[1]/label";
    public static final String  Start_A_Wiki_Radio_Button_Text2= "xpath=//*[contains(@id,'quickr_share_wiki_widget_WikiAccessOptions_')]/td[2]/div[2]/label";
    public static final String  Start_A_Wiki_Radio_Button_Text3= "xpath=id('lotusContent')/div/form/table/tbody/tr[5]/td[2]/div[1]/label";
    public static final String  Start_A_Wiki_Radio_Button_Text4= "xpath=id('lotusContent')/div/form/table/tbody/tr[5]/td[2]/div[2]/label";
    
    public static final String  Manage_Access_Radio_Button_Text1= "xpath=//div[@id='readAccessNode']/div[1]/label";
    public static final String  Manage_Access_Radio_Button_Text2= "xpath=//div[@id='readAccessNode']/div[2]/label";
    public static final String  Manage_Access_Radio_Button_Text3= "xpath=//div[@id='editAccessNode']/div[1]/label";
    public static final String  Manage_Access_Radio_Button_Text4= "xpath=//div[@id='editAccessNode']/div[2]/label";
  
    public static final String  Close_Icon= "xpath=//*[contains(@id,'quickr_widget_Dialog_')]/div[2]/div/form/h1/a/img";
    
    //Objects on About Page
    public static final String  AboutTabInline = "about_link";											
	public static final String  ChildPages_Heading= "xpath=id('about')/div[2]/h2/span";
	public static final String  Hierarchy_Heading= "xpath=id('about')/div[3]/h2/span";
	public static final String  AddanAttachment_Link= "link=Add an attachment";
	public static final String  Create_newChildPage_Link= "link=Create new child page";
	public static final String  Updated_Field= "Updated";
	public static final String  Added_Field= "Added";
	public static final String  Created_Field= "Created";
	public static final String  Size_Field= "Size";
	public static final String  PageViews_Field= "Page views";
	public static final String  PageViewedby_Field= "Page viewed by";
	public static final String  MovePage_Link= "link=Move Page";
	public static final String  Hierarchy_breadcrumb_RootPage= "xpath=id('hierarchyBreadcrumbs')/p/a[1]";
	public static final String  Hierarchy_breadcrumb_HomePage= "xpath=id('hierarchyBreadcrumbs')/p/a[2]";
	public static final String  Wiki_Page_Attachments= "xpath=id('wikiPageAttachments')/div[2]";
	public static final String  breadcrumb_PageInfoPage= "class=lotusLink";
	public static final String 	Print_Page_forReaders_andAnonymousUsers ="//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu lotusPlain']/tbody/tr[1]/td[2]";
	public static final String 	No_Attachments_Text = "xpath=id('wikiPageAttachments')/div[2]/div";
	public static final String 	Page_Placebar = "xpath=id('lotusPlaceBar')";
	
	//Items in Page Actions/Wiki Actions/Follow dropdowns
	public static final String 	Menu_Item_1 ="css=tbody[class='dijitReset'] tr:nth-child(1)";
	public static final String 	Menu_Item_2 ="css=tbody[class='dijitReset'] tr:nth-child(2)";
	public static final String 	Menu_Item_3 ="css=tbody[class='dijitReset'] tr:nth-child(3)";
	public static final String 	Menu_Item_4 ="css=tbody[class='dijitReset'] tr:nth-child(4)";
	public static final String 	Menu_Item_5 ="css=tbody[class='dijitReset'] tr:nth-child(5)";
	public static final String 	Menu_Item_6 ="css=tbody[class='dijitReset'] tr:nth-child(6)";
	public static final String 	Menu_Item_7 ="css=tbody[class='dijitReset'] tr:nth-child(7)";

	//Objects on the Main Service Page 
	public static final String Help_Link="link=Help";
    public static final String Logout_Link="link=Log Out";
    public static final String Home_Link="link=Home";
    public static final String Demo_Link="link=Demo";
    public static final String IBMLotusSupportForums_Link="link=IBM Lotus Support Forums";
    public static final String FAQ_Link="link=Frequently Asked Questions";
    public static final String DownloadConnectors_Link="link=Download Connectors";
    public static final String LearnMore_Link="link=Learn More";
    public static final String IBMLotusConnectionson_ibmcom_Link="link=IBM Connections on ibm.com";
    public static final String Howto_Bookmark_Link="link=How to Bookmark";
    public static final String Submit_Feedback_Link="link=Submit Feedback";
    public static final String Statistics_Link="link=Statistics";
    public static final String Quickron_ibm_Link="link=IBM Lotus Quickr on ibm.com";   
    public static final String Search_by_WikiName_Field="title";
    public static final String Files_Link="link=Files";
    public static final String Wikis_Link="link=Wikis";

 	//Objects used for Autosave automation
    public static final String Draft_Text="xpath=id('lotusContent')/div[1]/div[1]/span";
    public static final String Drafts_Edit_Link="xpath=id('lotusContent')/div[1]/div[1]/ul/li[1]/a";
    public static final String Drafts_Discard_Link="xpath=id('lotusContent')/div[1]/div[1]/ul/li[2]/a";
    public static final String Drafts_Homepage_Edit_Link="xpath=id('lotusContent')/div[1]/div[1]/div/ul/li[1]/a";
    public static final String Drafts_Homepage_Discard_Link="xpath=id('lotusContent')/div[1]/div[1]/div/ul/li[2]/a";
    public static final String Drafts_Discard_Text="Discard";
        
    public static final String Authenticated_Public_Wikis_Title="xpath=id('lotusContent')/div[3]/h1";
    public static final String Anonymous_Public_Wikis_Title="xpath=id('lotusContent')/div[2]/h1";
    public static final String Find_A_Wiki_Header="Find a Wiki";
    public static final String Public_Tags_Header="Public tags";
    public static final String Find_In_Wiki_List="wikiSearchBox";
    public static final String Find_In_Tags_List="xpath=id('lotusColLeft')/div[2]/div[1]/form/input[1]";
    
    public static final String Welcome_Section="id=welcome";
    public static final String Get_Started_Button="link=Get started";
    public static final String Learn_More_Button="link=Learn More";
    public static final String Watch_Demo_Button="xpath=id('welcome')/a[1]/img";
    
    public static final String Wiki_Name_in_PublicWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/h4";
    public static final String Tag_Name_in_PublicWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_PublicWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_PublicWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_PublicWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/div/a";
    public static final String more_Link_in_PublicWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[3]/a[1]";
    
    public static final String Wiki_Name_in_PublicWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/h4";
    public static final String Tag_Name_in_PublicWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_PublicWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_PublicWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_PublicWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/div/a[1]/span";
    public static final String more_Link_in_PublicWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[3]/a[1]";
    
    public static final String Wiki_Name_in_PublicWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/h4";
    public static final String Tag_Name_in_PublicWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_PublicWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_PublicWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_PublicWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/div/a[1]/span";
    public static final String more_Link_in_PublicWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[3]/a[1]";
    
    public static final String Wiki_Name_in_PublicWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/h4";
    public static final String Tag_Name_in_PublicWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_PublicWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_PublicWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_PublicWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/div/a[1]/span";
    public static final String more_Link_in_PublicWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[3]/a[1]";
    
    public static final String Wiki_Name_in_PublicWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/h4";
    public static final String Tag_Name_in_PublicWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_PublicWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_PublicWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_PublicWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/div/a[1]/span";
    public static final String more_Link_in_PublicWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[3]/a[1]";
    
    
    public static final String Wiki_Name_in_PublicWikisList_TopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/h4";
    public static final String Wiki_Name_in_PublicWikisList_SecondTopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/h4";
    public static final String Wiki_Name_in_PublicWikisList_ThirdTopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/h4";
    public static final String Wiki_Name_in_PublicWikisList_FourthTopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/h4";
    public static final String Wiki_Name_in_PublicWikisList_FifthTopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/h4";
    public static final String WikiCreated_UserName_in_PublicWikisList_TopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[5]/a";
    
    public static final String Wiki_Name_in_MyWikisList_TopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/h4";
    public static final String Wiki_Name_in_MyWikisList_SecondTopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/h4";
    public static final String Wiki_Name_in_MyWikisList_ThirdTopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/h4";
    public static final String Wiki_Name_in_MyWikisList_FourthTopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/h4";
    public static final String Wiki_Name_in_MyWikisList_FifthTopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/h4";
    public static final String WikiCreated_UserName_in_MyWikisList_TopCell_ListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[5]/a";
    
    public static final String Next_PageLink_atthe_Topof_PublicWikisList="xpath=//div[@id='list']/div[1]/ul/li[3]/a";
    public static final String Previous_PageLink_atthe_Topof_PublicWikisList="xpath=//div[@id='list']/div[1]/ul/li[1]/a";
    public static final String Next_PageLink_atthe_Bottomof_PublicWikisList="xpath=//div[@id='list']/div[3]/ul/li[3]/a";
    public static final String Previous_PageLink_atthe_Bottomof_PublicWikisList="xpath=//div[@id='list']/div[3]/ul/li[1]/a";
        
    public static final String Wiki_Name_in_MostVisitsList_TopCell="//div[@id='mostVisitedWikis']/div/div/ul/li[1]/a";
    public static final String NoofVisits_for_Wiki_in_MostVisitsList_TopCell="//div[@id='mostVisitedWikis']/div/div/ul/li[1]";
    public static final String Wiki_Name_in_MostVisitsList_SecondTopCell="//div[@id='mostVisitedWikis']/div/div/ul/li[2]/a";
    public static final String NoofVisits_for_Wiki_in_MostVisitsList_SecondTopCell="//div[@id='mostVisitedWikis']/div/div/ul/li[2]";
    
    public static final String Wiki_Name_in_MostCommentsList_TopCell="//div[@id='mostCommentedWikis']/div/div/ul/li[1]/a";
    public static final String NoofComments_for_Wiki_in_MostCommentsList_TopCell="//div[@id='mostCommentedWikis']/div/div/ul/li[1]";
    public static final String Wiki_Name_in_MostCommentsList_SecondTopCell="//div[@id='mostCommentedWikis']/div/div/ul/li[2]/a";
    public static final String NoofComments_for_Wiki_in_MostCommentsList_SecondTopCell="//div[@id='mostCommentedWikis']/div/div/ul/li[2]";
    
    public static final String Wiki_Name_in_MostRecommendedList_TopCell="xpath=id('mostRecommendedWikis')/div/div/ul/li/a";
    public static final String NoofRecommendations_for_Wiki_in_MostRecommendedList_TopCell="//div[@id='mostRecommendedWikis']/div/div/ul/li[1]";
    public static final String Wiki_Name_in_MostRecommendedList_SecondTopCell="xpath=//div[@id='mostRecommendedWikis']/div/div/ul/li[2]/a";
    public static final String NoofRecommendations_for_Wiki_in_MostRecommendedList_SecondTopCell="//div[@id='mostRecommendedWikis']/div/div/ul/li[2]";
      
    public static final String Personal_PlacesforQuickrNext_Heading="Personal Places for Quickr Next";
    public static final String IBM_Lotus_Connections_Heading="IBM Connections";
    public static final String Help_Heading="Help";
    public static final String Tools_Heading="Tools";
    public static final String About_Heading="About";
    public static final String Page_Header_Login_Name="id=headerUserName";
    public static final String Page_Header_Lotus_Quickr_Image="lotusLogo";
    public static final String Page_Header_Lotus_Connections_Image="lotusLogo";
    public static final String Page_Header_Files_Link="link=Files";
    public static final String Page_Header_Wikis_Link="link=Wikis";
    public static final String Find_A_Wiki_Search_Text="searchFor";
    public static final String Find_A_Wiki_Search_Submit_Button="simpleSearchSubmit";
    public static final String Public_Tags="Public tags";
    public static final String Most_Recommended="mostRecommendedWikis";
    public static final String Most_Commented="mostCommentedWikis";
    public static final String Most_Visited="mostVisitedWikis";
    public static final String Most_RecommendedWikiListHeading="Most Recommended";
    public static final String Most_CommentedWikiListHeading="Most Comments";
    public static final String Most_VisitedWikiListHeading="Most Visits";
    public static final String Wikis_Tab="xpath=id('lotusTitleBar')/div/div/ul/li[2]/a";
    
    //objects on the My Wikis Page
    public static final String My_Wikis_TopCell="xpath=id('list')/div[2]/table/tbody/tr[1]/td[2]/h4/a";
    public static final String TopCell_Username="xpath=id('list')/div[2]/table/tbody/tr[1]/td[2]/div/a";
    public static final String Public_Wikis_TopCell="xpath=id('list')/div[2]/table/tbody/tr[1]/td[2]/h4/a";
    public static final String PageDisplay_Choice_Option="//table[@id='qkrViewControl']";
    public static final String Table_Display_Text="Details";
    public static final String List_Display_Text="Summary";   
    public static final String Link_toshow_10Wikis_perpage="link=10";
    public static final String Link_toshow_25Wikis_perpage="link=25";
    public static final String Link_toshow_50Wikis_perpage="link=50";
    public static final String PageID_Showing_10Wikis_perpage="10";
    public static final String PageID_Showing_25Wikis_perpage="25";
    public static final String PageID_Showing_50Wikis_perpage="50";
    public static final String Sortby_Name_Option="css=[id='link_qkrSortname']";
    public static final String Sortby_Visits_Option="css=[id='link_qkrSortmostpopular']";
    public static final String Sortby_Recommendations_Option="css=[id='link_qkrSorthighestrated']";
    public static final String Sortby_Comments_Option="css=[id='link_qkrSortmostcommented']";
    public static final String Sortby_Created_Option="css=[id='link_qkrSortcreated']";
    public static final String Sortby_Page_Views_Option="css=[id='link_qkrSortmostpopular']";
    public static final String Sortby_Size_Option="css=[id='link_qkrSortsize']";
    public static final String Sortby_Updated_Option="css=[id='link_qkrSortupdated']"; 
    
    public static final String Wiki_Name_in_MyWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/h4";
    public static final String Tag_Name_in_MyWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_MyWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_MyWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_MyWikisList_TopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/div/a[1]";
    
    public static final String Wiki_Name_in_MyWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/h4";
    public static final String Tag_Name_in_MyWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_MyWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_MyWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_MyWikisList_SecondTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/div/a[1]";
    
    public static final String Wiki_Name_in_MyWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/h4";
    public static final String Tag_Name_in_MyWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_MyWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_MyWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_MyWikisList_ThirdTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/div/a[1]";
    
    public static final String Wiki_Name_in_MyWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/h4";
    public static final String Tag_Name_in_MyWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_MyWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_MyWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_MyWikisList_FourthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/div/a[1]";
    
    public static final String Wiki_Name_in_MyWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/h4";
    public static final String Tag_Name_in_MyWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/div/span[4]/a[1]";
    public static final String Wiki_Link_in_MyWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/h4/a[1]";
    public static final String Ratings_Value_in_MyWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[3]/span/span";
    public static final String WikiCreated_UserName_in_MyWikisList_FifthTopCell="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/div/a[1]";
    
    public static final String Wiki_Name_in_MyWikisList_TopCell_onListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/h4/a[1]";
    public static final String Wiki_Name_in_MyWikisList_SecondTopCell_onListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/h4/a[1]";
    public static final String Wiki_Name_in_MyWikisList_ThirdTopCell_onListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/h4/a[1]";
    public static final String Wiki_Name_in_MyWikisList_FourthTopCell_onListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/h4/a[1]";
    public static final String Wiki_Name_in_MyWikisList_FifthTopCell_onListView="xpath=//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/h4/a[1]";
    
    public static final String Link_toShow_2ndPageof_WikisList_onMyWikisPage="link=2";
	public static final String Link_toShow_3rdPageof_WikisList_onMyWikisPage="link=3";
	
    //Sorting on Pages Index page
    public static final String Pages_Index_Page_Name_TopCell="xpath=id('list')/div[2]/table/tbody/tr[1]/td[2]/h4/a";
    public static final String Pages_Index_Page_Name_SecondTopCell="xpath=id('list')/div[2]/table/tbody/tr[3]/td[2]/h4/a";
    public static final String Pages_Index_Page_Name_ThirdTopCell="xpath=id('list')/div[2]/table/tbody/tr[5]/td[2]/h4/a";
    public static final String Pages_Index_Page_Name_FourthTopCell="xpath=id('list')/div[2]/table/tbody/tr[7]/td[2]/h4/a";
    public static final String Pages_Index_Page_Name_FifthTopCell="xpath=id('list')/div[2]/table/tbody/tr[9]/td[2]/h4/a";
    
    public static final String Page_Icons = "css=img[class^='lconnSprite lconnSprite-iconWikisItem']";					
    public static final String Page_Links = "css=a[class='entry-title']";
    															
    public static final String PageIndex_MostVisitedIcon = "css=img[class^='lconnSprite lconnSprite-iconPageHits']";
    public static final String PageIndex_MostRecommendedIcon = "css=img[class^='lconnSprite lconnSprite-iconRating']";
    public static final String PageIndex_MostCommentedIcon = "css=img[class^='lconnSprite lconnSprite-iconComment']";
    public static final String PageIndex_NoCommentsIcon = "css=img[class^='lconnSprite lconnSprite-iconNoComments']";
    public static final String PageTitleField = "css=div[class='lotusHeader'] h1";
    public static final String More_Link = "link=More";
    public static final String Recommend_This_Page_Link = "";

    //Objects on Tag Filer Page
    public static final String Filter_with_TagName = "//div[@id='lotusContent']/div[2]/div/a";
    public static final String Link_toshow_10Wikis_perpage_TagFilterPage="link=10";
    public static final String Link_toshow_25Wikis_perpage_TagFilterPage="link=25";
    public static final String Link_toshow_50Wikis_perpage_TagFilterPage="link=50";
    public static final String PageID_Showing_10Wikis_perpage_on_TagFilterPage="10";
    public static final String PageID_Showing_25Wikis_perpage_on_TagFilterPage="25";
    public static final String PageID_Showing_50Wikis_perpage_on_TagFilterPage="50";
    public static final String Wiki_Name_in_TagFilterWikisList_TopCell="//div[@id='list']/div[2]/table/tbody/tr[1]/td[2]/h4/a";
    public static final String Wiki_Name_in_TagFilterWikisList_SecondTopCell="//div[@id='list']/div[2]/table/tbody/tr[3]/td[2]/h4/a";
    public static final String Wiki_Name_in_TagFilterWikisList_ThirdTopCell="//div[@id='list']/div[2]/table/tbody/tr[5]/td[2]/h4/a";
    public static final String Wiki_Name_in_TagFilterWikisList_FourthTopCell="//div[@id='list']/div[2]/table/tbody/tr[7]/td[2]/h4/a";
    public static final String Wiki_Name_in_TagFilterWikisList_FifthTopCell="//div[@id='list']/div[2]/table/tbody/tr[9]/td[2]/h4/a";

    //Objects on the Create Wiki lightbox
    public static final String AddGroups_Link="link=Add Groups";
    public static final String Mandatory_Field_Indicator="class=lotusFormRequired";
    public static final String Wiki_Name_Field="title";
    public static final String Start_A_Wiki_Tags_Field="createWiki_TaggerTypeAhead";
    public static final String Members_Field="css=input[id$='_userTextbox']";
    public static final String Groups_Field="css=input[id$='_groupTextbox']";
    public static final String MembershipRolesUsersDropdown="css=select[id='qkrMemRoleSel']";
    public static final String MembershipRolesGroupsDropdown="css=select[id='qkrGroupRoleSel']";
    public static final String Description_Field="id=createWiki_description";
    public static final String Tags_Field="xpath=//*[contains(@id,'lconn_share0_widget_TagTypeAhead_')]";
    public static final String Add_Members_OK_Button="css=input.lotusFormButton";
    public static final String Description_field_on_EditWiki="id=editWiki_description";
    
    public static final String Tool_Tips_For_WikiName_Field="//table[@id='find']/tbody/tr[2]/td[2]/a/img";
    public static final String Tool_Tips_For_Members_Field="//table[@id='find']/tbody/tr[7]/td[2]/a/img";
    public static final String Tool_Tips_For_Groups_Field="//table[@id='find']/tbody/tr[10]/td[2]/a/img";
    public static final String Tool_Tips_For_Description_Field="//table[@id='find']/tbody/tr[19]/td[2]/a/img";
    public static final String Tool_Tips_For_Tags_Field="//table[@id='find']/tbody/tr[16]/td[2]/a/img";

    public static final String Create_Wiki_Navigation="//div[@id='lotusSidenav']/div/div/div/a";
    public static final String Default_Timeout_Value = "60000";
    public static final String Tag_Options_dropdownList="xpath=//*[contains(@id,'_popup')]";
	public static final String Create_Wiki_Members_Dropdown1="xpath=id('find')/tbody/tr[4]/td[2]/table/tbody/tr/td[1]/select";
	public static final String Create_Wiki_Members_Dropdown2="xpath=id('find')/tbody/tr[4]/td[2]/table/tbody/tr/td[2]/select";
	public static final String Create_Wiki_Members_Field="xpath=//*[contains(@id,'quickr_share_wiki_widget_MembershipOptions_')]";
	public static final String All_AuthenticatedUsers_as_EditorsGroup_Link="//table[@id='find']/tbody/tr[3]/td[2]/ul/li[2]/a";
	public static final String All_AnonymousUsers_as_ReadersGroup_Link="//table[@id='find']/tbody/tr[3]/td[2]/ul/li[1]/a";
	
	public static final String AllUsers_RadioButton="css=input[id$='_publicViewingAllowed']";
    public static final String WikiMembersOnly_RadioButton="css=input[id$='_publicViewingDisabled']";
    public static final String AllLoggedInUsers_RadioButton="css=input[id$='_authenticatedEditingAllowed')]";
    public static final String WikiEditorsAndOwnersOnly_RadioButton="css=input[id$='_authenticatedEditingDisabled']";
    
    public static final String Add_Members_Dropdown="css=[id='qkrMemRoleSel']";
    public static final String Start_A_Wiki_Groups_Dropdown="xpath=id('lotusContent')/div/form/table/tbody/tr[8]/td[2]/table/tbody/tr/td[1]/select";
    
    public static final String Add_Groups_Dropdown="css=[id='qkrGroupRoleSel']";
    
  
    public static final String Wiki_Member_Typeahead="css=input[id^=lconn_wikis_widget_MembershipOptions_][id$=userTextbox].wikiMembers";

    //Tooltip text on Create Wiki lightbox
    public static final String Name_Field_Tooltip_Text="Wiki titles can have up to 252 characters (fewer in some languages). Use any characters except these: \\ / : * ? < > | [ ] and \".";
    public static final String Add_Members_And_Groups_Field_Tooltip_Text="Select an access level: Readers can read and recommend pages. Editors can read, edit, comment, create, and recommend pages. Owners can read, edit, comment, create, recommend, and delete pages, tag the wiki, and change the wiki description. Then start typing a name, and then click the name when it displays.";
    public static final String Tags_Field_Tooltip_Text="Tags are keywords you can add to wikis to make them easier to find later. Tags can have up to 100 characters (fewer in some languages). Type tags separate by a space.";
    public static final String Description_Field_Tooltip_Text="Type a brief description of the wiki that will display in wiki lists.";
    
    //Objects for Versions UI & Functionality
    public static final String Versions_Top_Info_Number = "css=table[class='lotusTable lotusClear'] tr:nth-child(1) td:nth-child(1)";
    public static final String Versions_Complete_Info_Body = "css=table[class='lotusTable lotusClear']";
    public static final String Versions_Current_Version_Text = "Current Version";
	public static final String Versions_Compare_Text = "Compare version";
	public static final String Versions_Comparison_Dropdown1 = "css=div[class='lotusActions'] [dojoattachpoint='compareFromNode']";
	public static final String Versions_Comparison_Dropdown2 = "css=div[class='lotusActions'] [dojoattachpoint='compareToNode']";
	public static final String Versions_Comparison_Page_Dropdown1 = "css=td[class='qkrVersionInfoBox'] [aria-label='First version']";
	public static final String Versions_Comparison_Page_Dropdown2 = "css=td[class='qkrVersionInfoBox'] [aria-label='Second version']";
	public static final String Versions_Delete_Dropdown = "css=div[class='lotusActions'] [dojoattachpoint='deleteFromNode']";
	public static final String Versions_To_Text = "to:";
	public static final String Versions_Show_Comparison_Link = "link=Show comparison";
	public static final String Versions_Top_Version_Item_Number = "xpath=id('pageVersions')/div[2]/table/tbody/tr[1]/td[1]";
	public static final String Versions_Inactive_Version_View_Link = "xpath=id('pageVersions')/div[2]/table/tbody/tr[1]/td[4]/div";
	public static final String Versions_Deletion_Text = "Delete all versions prior to version";
	public static final String Versions_Deletion_Section = "xpath=id('pageVersions')/div[5]";
	public static final String Delete_Link = "link=Delete";
	public static final String Versions_Top_Pagination_Section = "css=div[id='pageVersions'] div[dojoattachpoint='topPageNode']";
	public static final String Versions_Bottom_Pagination_Section = "css=div[id='pageVersions'] div[dojoattachpoint='bottomPageNode']";
	public static final String Versions_Page2 = "css=ul[class='lotusInlinelist'] li:nth-child(2)";
	public static final String Versions_Page1 = "css=ul[class='lotusInlinelist'] li:nth-child(1)";
	public static final String Versions_Active_Page = "css=ul[class='lotusInlinelist'] a[href]";
	public static final String Versions_View_Textfield = "css=div[id='wikiContentDiv']";
	public static final String Versions_View_Textfield_Title = "css=[id=wikiPageHeader]";
	public static final String Versions_View_Details_Field = "css=div[class='qkrVersionInfo']";
	public static final String Versions_View_First_Link = "css=ul[class='lotusInlinelist lotusLinks qkrVersionActions'] li[class='lotusFirst']";
	public static final String Versions_Comparison_Page_Restore_Link = "xpath=id('pageVersions')/div[2]/table/tbody/tr[2]/td[5]/a";
	public static final String Versions_View_CompareToMostRecent_Link = "link=Compare to most recent";
	public static final String Versions_View_CompareToMostRecent = "Compare to most recent";
	public static final String Versions_View_ViewMostRecent_Link = "link=View most recent";
	public static final String Versions_Comparison_Page_Heading = "Wiki text comparison";
	public static final String Versions_Comparison_Page_Version_Section1 = "css=td[class='qkrVersionInfoBox']:first-child";
	public static final String Versions_Comparison_Page_Version_Section2 = "css=td[class='qkrVersionInfoBox']:last-child";
	public static final String Versions_Comparison_Page_Refresh_Section = "css=td[class='qkrVert']";
	public static final String Versions_Comparison_Page_Refresh = "Refresh";
	public static final String Versions_Comparison_Page_Refresh_Icon = "css=td[class='qkrVert'] *[alt='Refresh']";
	public static final String Versions_Comparison_Page_Deleted_Text = "Deleted";
	public static final String Versions_Comparison_Page_NewChanged_Text = "New/Changed";
	public static final String Versions_Comparison_Page_Compare_Textfield = "css=div[class='qkrWikiPreview']";
	public static final String Versions_View_Link = "link=View";
	public static final String Versions_Restore_Link = "link=Restore";
	public static final String Versions_Page_View_Link_to_ViewSecondMostRecent_Version = "xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr[2]/td[4]/a";
	public static final String Versions_Page_View_Link_to_ViewThirdMostRecent_Version = "xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr[3]/td[4]/a";
	public static final String Versions_Page_View_Link_to_ViewFourthMostRecent_Version = "xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr[4]/td[4]/a";
	public static final String Versions_Page_Restore_Link_to_RestoreSecondMostRecent_Version = "xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr[2]/td[5]/a";
	public static final String Versions_Page_Restore_Link_to_RestoreThirdMostRecent_Version = "xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr[3]/td[5]/a";
	public static final String Versions_Page_Restore_Link_to_RestoreFourthdMostRecent_Version = "xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr[4]/td[5]/a";
	public static final String Restore_Button= "xpath=//*[contains(@id,'quickr_widget_Dialog_')]/div[2]/div/form/div[2]/input";
	public static final String Delete_Prior_Versions_Text= "Delete";
	public static final String Delete_Prior_Versions_Link= "xpath=id('pageVersions')/div[5]/a";
	public static final String Restore_Versions_Text= "Restore";
	
	//Objects for 'My Wikis' tab
	public static final String Pagination_10 = "xpath=id('list')/div[3]/div[1]/ul/li[1]/a";
	public static final String Pagination_25 = "xpath=id('list')/div[3]/div[1]/ul/li[3]/a";
	public static final String Pagination_50 = "xpath=id('list')/div[3]/div[1]/ul/li[5]/a";
	public static final String All_Tags_Heading = "All Tags";
	public static final String Wikis_I_Am_Member_Of_Filter_Text = "Wikis I Am Member Of";

	//Rename wiki titles - test scenarios
	public static final String OwnerCanRenameWikis_Test1 = "1";
	public static final String OwnerCanRenameWikis_Test2 = " 2";
	public static final String OwnerCanRenameWikis_Test3 = "@~,.;'#-=!ï¿½$%^&()_+{}";
	public static final String [] OwnerCanRenameWikis_Test4 = new String []{"\\","/",":","*","?","\"","<",">","|","[","]"};
	public static final String OwnerCanRenameWikis_Test5 = "    5";
	public static final String OwnerCanRenameWikis_Test5b = " 5";
	
	//When page hasn't been created yet
	public static final String NotCreatedTextOnCreatePage = "has not been created yet";
	public static final String DescriptiveTextOnCreatePage = "You have followed a link to or entered the address of a page that has not been created yet.";
	public static final String TakeMeBackOnCreatePage = "link=No thanks - take me back";
	
	//Navigation on CKEditor
	public static final String FckEditor_Save_And_Close = "link=Save and Close";
	public static final String Editor_Title_Field = "id=name";
	
	//From QuickrWikiGenericMethods Objects
	public static final String My_Wikis_Tab= "link=My Wikis";
	public static final String CreateANewWikiTabTitle= "link=Create a New Wiki";
	public static final String WikiExistsWarningMessageField="class=lotusFormError";
	public static final String WikiHomePageTitleField="css=*[id='wikiPageHeader']";
	public static final String WikiHomePageTitleFieldWithDrafts="xpath=id('lotusContent')/div[1]/div[3]/h1";
	
	public static final String NoTagsIndicator="None";
	public static final String NoRecommendationsIndicator="No recommendations";
	
	/*public static final List<String> PAGETITLES = 
		Arrays.asList("Wikis"
				, "Create a New Wiki"
				, "All Wikis - Wikis"
				, "Public Wikis"
				, "My Wikis");
	
	public static final List<String> InvalidChars = 
		Arrays.asList("Wikis"
				, "Create a New Wiki"
				, "All Wikis - Wikis"
				, "Public Wikis"
				, "My Wikis"); */
	
	//Just for running the build file
	public static final String  Get_Page_Title= "xpath=//div[@id='lotusContent']/div[1]/div[2]/h1"; 
	
	//objects on the unauthorised Access Login page
	public static final String ErrorMessageHeading="//div[@id='lotusFrame']/div/div/div/h1";
	public static final String LinktotheLoginPage="link=Log in now";
	public static final String BacktoWikis="link=Back to Wikis";
	public static final String BacktoPage="link=Back to Page";
	public static final String ReturnToApplication="link=Return to Application";

	public static final String No_Access_Text1="You do not have access to this page";
	public static final String No_Access_Text2="You do not have permission to view this page";
	public static final String Cannot_Display_Text="That page cannot be displayed.";
	
	//Objects added for the NAV/Move this page testcases
	public static final String Nav_Tree_Dec = "//div[@class='lotusNavTree']";
	public static final String NavTreeArrow = "css=.dijitTreeExpandoOpened:nth-child(1)";
	
	//Move Page declarations
	public static final String MoveThisPageLightBox = "css=div[class='dijitDialog dijitContentPane']";
	public static final String FindParentPageTab = "link=Find Parent Page";
	public static final String ReorderPeerPageTab = "link=Reorder Peer Pages";
	public static final String EnterPageNumberTextField = "//*[@id='quickr_share_wiki_action_MoveWikiPage_1mysearchbox']";
	public static final String MovePageBrowseButton = "link=Browse...";
	public static final String MovePageSortByPrevious = "link=Previous";
	public static final String MovePageSortByNext = "link=Next";
	public static final String MovePageSortByName = "link=Name";
	public static final String MakeThisATopLevelPage = "css=input[id$='_maketoplevel_box']";
	public static final String ReOrderPeerPageUp = "//class=qkrSprite qkrSprite-arrowUp";
	public static final String ReOrderPeerPageDown = "//class=qkrSprite qkrSprite-arrowDown";
	public static final String MovePageUp = "css=img[class='qkrSprite qkrSprite-arrowUp']";
	public static final String MovePageDown = "css=img[class='qkrSprite qkrSprite-arrowDown']";
	
	public static final String MoveButton = "css=input[value='Move']";
	public static final String MovePageIntoListFirstItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(1)";
	public static final String MovePageIntoListSecondItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(2)";
	public static final String MovePageIntoListThirdItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(3)";
	public static final String MovePageIntoListFourthItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(4)";
	public static final String MovePageIntoListFifthItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(5)";
	public static final String MovePageIntoListSixthItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(6)";
	public static final String MovePageIntoListSeventhItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(7)";
	public static final String MovePageIntoListEightItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(8)";
	public static final String MovePageIntoListNinethItem = "css=div[class='reorderDiv qkrFormFieldRow qkrMovePageList'] *[class='qkrThin'] li:nth-child(9)";
	public static final String NavSelectedItem = "css=div[class='dijitTreeNodeSelected dijitTreeRowSelected dijitTreeRow lotusSelected']";
	
	//Objects used for verifications of objects/pages existing
	public static final String HomepageTitle = "//div[@id='lotusContent']/div[1]/div[2]/h1";
	public static final String AboutPageTitle = "pageDetails";
	public static final String ChildPageExists = "//*[@id='childList']/li[1]/a";
	public static final String SecondChildPageExists = "//*[@id='childList']/li[2]/a";
	public static final String NoChildPageExists = "css=ul[id='childList'] div";
	
	//Drag the Navigation pane
	public static final String NavResize = "class=lotusResizable";
	
	//Textual items for checking subviews
	public static final String Subscribe_To_Comments_Text = "Subscribe to these comments";
	public static final String Subscribe_To_Versions_Text = "Subscribe to these page versions";
	public static final String Subscribe_To_Attachments_Text = "Subscribe to these attachments";
	
	public static final String About_This_File_Text = "About this file";
	public static final String Child_Pages_Text = "Child pages (";
	public static final String Hierarchy_Text = "Hierarchy";
	
	public static final String CreateAPageAfterDeletion = "css=input[value='Create A Page']";
	
	//xpath for the expected error message
	public static final String InvalidCharErrorMessage = "//div[@id='lotusHeader']/table/tbody/tr[1]/td";
	
	

	
	//Added objects for next LC Release
	/**Adding Members*/
	public static final String SearchDir = "css=li[dojoattachpoint=searchButton].dijitMenuItem";
	public static final String SelectUser = "css=li[id^=lconn_wikis_widget_MembershipOptions_][id*=selectUser_popup]";
	
	
	public static final String fullSearchLink = "Person not listed? Use full search...";
	public static final String fullGroupSearchIdentifier = "css=*[id$='_selectGroup_popup'] *[id$='selectGroup_popup_searchDir']";
	public static final String selectedGroupIdentifier = "css=*[id$='_selectGroup_popup'] li:nth-child(2)";
	public static final String Groups_Textfield_Typeahead="css=*[id$='_selectGroup_popup']";
	public static final String fullUserSearchIdentifier = "css=*[id$='_selectUser_popup'] *[dojoattachpoint='searchButton']";
	public static final String selectedUserIdentifier = "css=*[id$='_selectUser_popup'] li:nth-child(2)";
	public static final String Members_Textfield_Typeahead="css=*[id$='_selectUser_popup']";
	
	//UI Messages
	public static final String UI_Message = "css=div[class='qkrWideInner']";
	
	//
	public static final String CKEditor_iFrame_Wikis = "css=iframe[title^='Rich text editor']";
	
	
}
