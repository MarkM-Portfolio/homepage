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

package com.ibm.atmn.wd_homepagefvt.appobjects.forums;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;

public class FVT_ForumsObjects extends CommonObjects {

	// Start a forum
	public static String Start_A_Forum = "link=Start a Forum";
	public static String Start_A_Forum_InputText_Name = "css=input#lconn_forums_ForumForm_0_name";
	public static String Start_A_Forum_InputText_Tags = "css=input#lconn_forums_ForumForm_0_tag";
	public static String Start_A_Forum_Textarea_Description = "css=textarea#lconn_forums_ForumForm_0_description";

	// Start a Topic
	public static String Start_A_Topic = "link=Start a Topic";
	public static String Start_A_Topic_InputText_Title = "css=input#lconn_forums_PostForm_0_postTitle";
	public static String Start_A_Topic_InputCheckbox_MarkAsQuestion = "css=input#lconn_forums_PostForm_0_postQestionMark";
	public static String Start_A_Topic_InputText_Tags = "css=input#lconn_forums_PostForm_0_postTag";
	public static String Start_A_Topic_InputButton_ConfirmationDialogueOk = "css=div.lotusDialogFooter input.lotusFormButton";

	
	
	// Main Navigation
	public static String My_Forums_Tab = "link=My Forums";
	public static String Public_Forums_Tab = "link=Public Forums";

	// Forum Page
	public static String Reply_to_topic = "link=Reply to Topic";
	public static String First_Topic_Number_of_Replies = "//div[@id='contentArea']/div/div/div[4]/table/tbody/tr[2]/td[3]";

	// Update Forum
	public static String Forum_MoreActions = "css=a:contains('Forum Actions')";
	public static String Edit_Forum = "css=tr#dijit_MenuItem_1.dijitReset td#dijit_MenuItem_1_text.dijitReset";
	public static String Edit_Topic = "link=Edit";
	public static String Edit_Reply = "//div[3]/ul/li/a";
	public static String Edit_Reply2 = "css=ul[class='lotusCommentList'] a:contains('Edit')";
	
	//Community Forums
	public static String Forums_Tab = "css=li#dfForumsTab a";
	public static String TopicReply = "link=Reply";
	
	//public static String ForumsTopTab = "link=Forums";
	public static String ForumsTopTab = "css=ul.lotusTabs > li > a:contains('Forums')";
	
	//Follow forum
	public static String Follow = "css=a#forumFollowMenu";
	public static String FollowForum = "css=td#dijit_MenuItem_0_text.dijitReset";
	
	
}
