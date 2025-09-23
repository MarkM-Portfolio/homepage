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

package com.ibm.atmn.homepagefvt.appobjects.activities;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;

public class FVT_ActivitiesObjects extends CommonObjects {

	// Start an activity
	public static String Start_An_Activity = "link=Start an Activity";
	public static String Start_An_Activity_InputText_Name = "css=input#lconn_act_ActivityForm_0_titleInput";
	public static String Start_An_Activity_InputText_Tags = "css=input#lconn_act_ActivityForm_0tagz";
	//member type and roles
	public static String Start_An_Activity_InputSelect_MemberType = "css=div#lconn_act_MemberPicker_0.AddMembers select:nth(0)";
	public static String Start_An_Activity_InputSelect_MemberRole = "css=div#lconn_act_MemberPicker_0.AddMembers select:nth(1)";	
	//Member name typeAhead text field
	public static String Start_An_Activity_InputText_Members_PeopleTypeAhead = "css=input#lconn_core_PeopleTypeAhead_0";
	public static String Start_An_Activity_InputText_Members_CommunityTypeAhead = "css=input#lconn_core_PeopleTypeAhead_1";
	public static String Start_An_Activity_InputText_Members_GroupsTypeAhead = "css=input#lconn_core_AddGroups_0PeopleTypeAhead";
	//typeahead popup
	public static String Start_An_Activity_Members_TypeaheadPromptList = "css=#lconn_core_PeopleTypeAhead_0_popup";
	public static String usersActivitySearchIdentifier = Start_An_Activity_Members_TypeaheadPromptList + "_searchDir";
	public static String selectedActivityUserIdentifier = "css=*[id$='lconn_core_PeopleTypeAhead_0_popup'] li:nth-child(2)";
	//Goals
	public static String Start_An_Activity_Textarea_Activity_Goals = "css=textarea#lconn_act_ActivityForm_0_descriptionInput";
	//Browse Groups
	public static String Browse_Groups = "link=Browse Groups";
	//Due Date
	public static String Start_An_Activity_InputText_DueDate = "id=lconn_act_ActivityForm_0duedate";
	
	//EDIT Activity
	public static String ActivityHome_EditActivity_TopActivity = "css=a:contains(Edit):nth(0)";
	public static String Edit_Activity_Dialogue_SaveButton = "css=div[class='lotusDialogWrapper dijitDialog dijitContentPane']:last input.lotusFormButton";
	public static String ActivitiesHome_DeleteActivity_TopActivity = "css=a:contains(Delete):nth(0)";
	public static String Delete_Activity_Dialogue_OkButton = "css=input[value='OK']";
	public static String Edit_An_Activity_Textarea_Activity_Goals = "css=textarea#lconn_act_ActivityForm_2_descriptionInput.lotusText";
	public static String Edit_An_Activity_Title = "css=input#lconn_act_ActivityForm_0_titleInput.lotusText";
	
	
	// Start an Entry/ToDo
	public static String New_Entry = "css=span.lotusBtn a:contains('Add Entry')";
	public static String New_Entry_InputText_Title = "id=lconn_act_EntryForm_0title";
	public static String New_Entry_InputText_Tags = "id=lconn_act_EntryForm_0tags";
	//Add bookmark
	public static String New_Entry_Add_Bookmark = "link=Add Bookmark";
	//A first Bookmark
	public static String New_Entry_Bookmark1_Row = "css=div[id^='lconn_act_BookmarkField']:nth(0)";
	public static String New_Entry_Bookmark1_Lable = New_Entry_Bookmark1_Row + "-fieldLabel";
	public static String New_Entry_Bookmark1_Lable_InputText_LableName = New_Entry_Bookmark1_Row + " fieldset div.fieldHeader span:nth(0) input";
	public static String New_Entry_Bookmark1_InputText_LinkDescription = New_Entry_Bookmark1_Row + " fieldset div.fieldData span:nth(0) span input[class^='lotusText bookmarkTitle']";
	public static String New_Entry_Bookmark1_InputText_LinkURL = New_Entry_Bookmark1_Row + " fieldset div.fieldData span:nth(0) input[class^='bookmarkUrl']";	
	//A second Bookmark
	public static String New_Entry_Bookmark2_Row = "css=div[id^='lconn_act_BookmarkField']:nth(1)";
	public static String New_Entry_Bookmark2_Lable = New_Entry_Bookmark2_Row + "-fieldLabel";
	public static String New_Entry_Bookmark2_Lable_InputText_LableName = New_Entry_Bookmark2_Row + " fieldset div.fieldHeader span:nth(0) input";
	public static String New_Entry_Bookmark2_InputText_LinkDescription = New_Entry_Bookmark2_Row + " fieldset div.fieldData span:nth(0) span input[class^='lotusText bookmarkTitle']";
	public static String New_Entry_Bookmark2_InputText_LinkURL = New_Entry_Bookmark2_Row + " fieldset div.fieldData span:nth(0) input[class^='bookmarkUrl']";	
	//Add Custom Fields
	public static String New_Entry_AddCustomFieldsMenu = "css=a:contains('Add Custom Fields')";
	public static String New_Entry_AddCustomFields_Date = "css=#dijit_MenuItem_2_text";
	//A Date
	public static String New_Entry_Date_Row = "css=div[id^='lconn_act_DateField']:nth(0)";
	public static String New_Entry_Date_Lable = New_Entry_Date_Row + "-fieldLabel";
	public static String New_Entry_Date_Lable_InputText_LableName = New_Entry_Date_Row + " fieldset div.fieldHeader span:nth(0) input";
	public static String New_Entry_Date_InputText_Date = New_Entry_Date_Row + " fieldset div.fieldData span:nth(0) input[id^='lconn_act_DateTextBox_']";
	//checkboxes
	public static String New_Entry_Notify_Checkbox_Notify = "id=lconn_act_EntryForm_0notifyCheck";
	public static String New_Entry_Notify_Checkbox_NotifyAll = "css=input[id^=lconn_act_NotifySection_][id$=notifyAll]:nth(0)";

	// Main Navigation
	public static String Activities_Tab = "link=Activities";
	public static String To_Do_List_Tab = "link=To Do List";
	public static String Activity_Templates_Tab = "link=Activity Templates";
	public static String Recent_Updates_Tab = "link=Recent Updates";
	public static String ActivityOutline_More_ExpandDescription = "link=... [more]";
	public static String ActivityHome_ExpandActivityDetails_TopActivity = "css=td[class^=lotusAlignRight]:nth(0) a";
	public static String Activities_LeftNav_Trash = "css=a>span:contains(Trash)";
	public static String Members = "link=Members";
	public static String ActivityOutline = "link=Activity Outline";
	
	//ToDo
	public static String AddToDo = "css=span.lotusBtn a:contains('Add To Do Item')";
	public static String ToDoMoreOptions = "css=a span[dojoattachpoint='moreOptionsText_AP']";
	public static String ToDo_InputText_Title = "css=input[id^=lconn_act_ToDoForm_][id$=title]:nth(0)";
	public static String MarkPrivateToDo = "css=input#lconn_act_ToDoForm_0privateCheck";
	public static String ExpandToDo = "//h4/span";
	public static String MarkToDoCompleted = "//div[2]/input";
	
	public static String ToDoItems = "css=a:contains('To Do Items')";
	public static String ToDo_Assign_Person = "css=a:contains('Choose a person')";
	public static String ToDo_Assign_FilterBox = "//fieldset/div/fieldset/div/input";
	public static String ToDo_Assign_CheckBox = "//fieldset/div[2]/div/input";
	public static String ToDoItemMore = "link=... [more]";
	
	//Add Section
	public static String AddSection = "css=a.lotusAction:contains('Add Section')";
	public static String Section_InputText_Title = "css=input[id^=lconn_act_SectionForm_][id$=sectionInputTitle]:nth(0)";
	
	//For File Upload
	public static String AddFileLink = "css=div.fieldData span a:contains('Add File')";
	public static String AddFileOptionAttachFile = "css=tbody[class='dijitReset'] tr:contains('Attach File')";
	public static String AttachmentField = "css=span input.fieldFileNode";
	
	//Activity Entry
	public static String MarkPrivate = "css=input#lconn_act_EntryForm_0privateCheck";
	
	//Change Access
	public static String ChangeAccess = "//div[@id='actMembershipList']/div[2]/div[2]/div[7]/div[3]/a";
	public static String CheckPublic = "//div[@id='lconn_act_PublicAccessSelector_0']/div[4]/input";
	public static String SaveAccessChange = "css=div.lotusDialogFooter > span > input.lotusFormButton";

	//Add Comment
	public static String ExpandActivityEntry = "//div[2]/h4";
	public static String AddComment = "css=li.lotusFirst a:contains('Add Comment')";
	public static String MarkPrivateComment = "css=input#lconn_act_ReplyForm_0privateCheck";

	
	//Follow
	public static String FollowActivity = "link=Follow this Activity";
	
	
}