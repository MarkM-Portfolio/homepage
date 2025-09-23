/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential */
/*                                                                   */
/* OCO Source Materials */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013 */
/*                                                                   */
/* The source code for this program is not published or otherwise */
/* divested of its trade secrets, irrespective of what has been */
/* deposited with the U.S. Copyright Office. */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.appobjects.forums;

import com.ibm.atmn.lc.appobjects.common.CommonObjects;

public class ForumsObjects extends CommonObjects {

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
	public static String Reply_to_topic = "link=Reply to this Topic";
	public static String First_Topic_Number_of_Replies = "//div[@id='contentArea']/div/div/div[4]/table/tbody/tr[2]/td[3]";

}
