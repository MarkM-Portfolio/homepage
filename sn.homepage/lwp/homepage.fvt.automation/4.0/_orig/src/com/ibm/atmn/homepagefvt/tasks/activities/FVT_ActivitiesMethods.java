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

package com.ibm.atmn.homepagefvt.tasks.activities;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;


import org.junit.Assert;


import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.homepagefvt.appobjects.activities.FVT_ActivitiesObjects;


public class FVT_ActivitiesMethods extends FVT_CommunitiesMethods {
	
	

	/** Select a member from activated Add member textfield typeahead dropdown */
	public void selectMemberFromDropdown(String userName) {
		if (userName.contains("Amy Jones"))
			userName = userName.replace("Amy Jones", "ajones");

		cautiousFocus(FVT_ActivitiesObjects.Start_An_Activity_Members_TypeaheadPromptList);
		sel.clickAt(FVT_ActivitiesObjects.usersActivitySearchIdentifier, "1,1");
		clickAtAndWait(FVT_ActivitiesObjects.selectedActivityUserIdentifier, "1,1");
	}
	
	public void UploadFileToActivity(String FileUploadName){
		sel.selectWindow("null");
		
		//Open "File Upload" dialog
		sel.focus(FVT_ActivitiesObjects.AddFileLink);
		clickLink(FVT_ActivitiesObjects.AddFileLink);
		sel.focus(FVT_ActivitiesObjects.AddFileOptionAttachFile);
		clickLink(FVT_ActivitiesObjects.AddFileOptionAttachFile);
		sel.focus(FVT_ActivitiesObjects.AttachmentField);
		sleep(500);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		sleep(3000);
		
		//get the path and file name of the file to upload
		if (CommonObjects.TestOS.contains("Windows")) {
			typeNative(CommonData.WindowsFileLocation+FileUploadName);
			sleep(500);
		}else{
			typeNative(CommonData.LinuxMacFileLocation+FileUploadName);
			sleep(500);
		}
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sleep(1000);
		
		
	}

	public String addEntryToActivity(FormInputHandler typist) {
		clickLink(FVT_ActivitiesObjects.New_Entry);

		//start of form
		String entryRandomNumber = genDateBasedRandVal();
		String part1EntryTitle = FVT_ActivitiesData.Start_A_Entry_InputText_Title_Data + entryRandomNumber;

		typist.type(FVT_ActivitiesObjects.New_Entry_InputText_Title, part1EntryTitle);
		typist.type(FVT_ActivitiesObjects.New_Entry_InputText_Tags, FVT_ActivitiesData.Start_A_Entry_InputText_Tags_Data
				+ entryRandomNumber, "tags");

		//upload file
		/*
		 * upload file goes here sel.click("link=Add File "); sel.click("id=dijit_MenuItem_3_text");
		 * sel.click("name=fields.new0.file");
		 */
		UploadFileToActivity("Desert.jpg");

		//Slow Down
		sel.setSpeed("500");
		
		//Add bookmark
		sel.click(FVT_ActivitiesObjects.New_Entry_Add_Bookmark);
		sel.selectWindow("null");
		typist.type(FVT_ActivitiesObjects.New_Entry_Bookmark1_Lable_InputText_LableName, entryRandomNumber + "ActivitiesHome");
		typist.type(FVT_ActivitiesObjects.New_Entry_Bookmark1_InputText_LinkDescription, "ActHome " + entryRandomNumber);
		typist.type(FVT_ActivitiesObjects.New_Entry_Bookmark1_InputText_LinkURL, FVT_ActivitiesObjects.TestURL + "activities", null);

		//Add Second bookmark
		sel.click(FVT_ActivitiesObjects.New_Entry_Add_Bookmark);
		sel.selectWindow("null");
		typist.type(FVT_ActivitiesObjects.New_Entry_Bookmark2_Lable_InputText_LableName, "Link Name " + entryRandomNumber);
		typist.type(FVT_ActivitiesObjects.New_Entry_Bookmark2_InputText_LinkDescription, "Link " + entryRandomNumber);
		typist.type(FVT_ActivitiesObjects.New_Entry_Bookmark2_InputText_LinkURL, "ftp://dog-earURL.com", null);

		//Add date
		sel.click(FVT_ActivitiesObjects.New_Entry_AddCustomFieldsMenu);
		sel.click(FVT_ActivitiesObjects.New_Entry_AddCustomFields_Date);
		sel.selectWindow("null");
		typist.type(FVT_ActivitiesObjects.New_Entry_Date_Lable_InputText_LableName, entryRandomNumber + " Date 1");
		typist.pickADojoDate(FVT_ActivitiesObjects.New_Entry_Date_InputText_Date);
		
		//Entry text
		String description = FVT_ActivitiesData.CK_Editor_Textarea_RichText_Data + entryRandomNumber;
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);

		//Notification
		sel.click(FVT_ActivitiesObjects.New_Entry_Notify_Checkbox_Notify);
		sel.click(FVT_ActivitiesObjects.New_Entry_Notify_Checkbox_NotifyAll);
		
		//Return to default speed
		sel.setSpeed("0");

		//Save
		clickLink(FVT_ActivitiesObjects.SaveButton2);

		return part1EntryTitle;
	}

	public String addEntryToActivity_Basic(String ActivityName, FormInputHandler typist, boolean accessPrivate) {

		//
		clickLink(FVT_ActivitiesObjects.New_Entry);

		//start of form
		String entryRandomNumber = genDateBasedRandVal();
		//String part1EntryTitle = FVT_ActivitiesData.Start_A_Entry_InputText_Title_Data + entryRandomNumber;

		typist.type(FVT_ActivitiesObjects.New_Entry_InputText_Title, ActivityName);
		typist.type(FVT_ActivitiesObjects.New_Entry_InputText_Tags, FVT_ActivitiesData.Start_A_Entry_InputText_Tags_Data
				+ entryRandomNumber, "tags");
		
		//Entry text
		String description = FVT_ActivitiesData.CK_Editor_Textarea_RichText_Data + entryRandomNumber;
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);

		if(accessPrivate==true){
		clickLink(FVT_ActivitiesObjects.MarkPrivate);
		}
		//Save
		clickLink(FVT_ActivitiesObjects.SaveButton);

		return ActivityName;
	}
	
	public String addToDoToActivity(String ToDoTitle,FormInputHandler typist, boolean accessPrivate, boolean assignToDo) {

		//start of form
		String entryRandomNumber = genDateBasedRandVal();
		//String ToDoTitle = FVT_ActivitiesData.ToDo_InputText_Title_Data + entryRandomNumber;
		
		//Add to-do entry
		sleep(1000);
		sel.focus(FVT_ActivitiesObjects.AddToDo);
		sleep(200);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sleep(1000);
		
		cautiousClick(FVT_ActivitiesObjects.ToDoMoreOptions);
		
		//Title
		typist.type(FVT_ActivitiesObjects.ToDo_InputText_Title, ToDoTitle);
		
		//Assign Members
		if(assignToDo==true){
			
			clickLink(FVT_ActivitiesObjects.ToDo_Assign_Person);
			sel.focus(FVT_ActivitiesObjects.ToDo_Assign_FilterBox);
			typist.type(FVT_ActivitiesObjects.ToDo_Assign_FilterBox, "");
			typist.type(FVT_ActivitiesObjects.ToDo_Assign_FilterBox, "Amy Jones450");
			clickLink(FVT_ActivitiesObjects.ToDo_Assign_CheckBox);
			}
		
		
		//Description
		String description = FVT_ActivitiesData.ToDo_InputText_Description_Data + entryRandomNumber;
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);
		
		
		//Mark To Do Private
		if(accessPrivate==true){
			sel.focus(FVT_ActivitiesObjects.MarkPrivateToDo);
			clickLink(FVT_ActivitiesObjects.MarkPrivateToDo);
			}
		
		//Save To Do
		clickLink(FVT_ActivitiesObjects.SaveButton);
		//clickLink(CommonObjects.CancelLink);
		

		return ToDoTitle;
	}
	
	public void addCommentToActivityEntry(FormInputHandler typist, boolean accessPrivate) {
		
		//
		clickLink(FVT_ActivitiesObjects.AddComment);

		
		//Entry text
		String entryRandomNumber = genDateBasedRandVal();
		String description = FVT_ActivitiesData.CK_Editor_Textarea_RichText_CommentData + entryRandomNumber;
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);

		if(accessPrivate==true){
		clickLink(FVT_ActivitiesObjects.MarkPrivateComment);
		}
		//Save
		clickLink(FVT_ActivitiesObjects.SaveButton);

	}
	
	public String startAnActivity(String ActivityName, FormInputHandler typist, String personAsOwner) {

		clickLink(FVT_ActivitiesObjects.Start_An_Activity);

		//Fill form to create activity
		String activityRandomNumber = genDateBasedRandVal();

		//Activity Name
		sel.selectWindow("null");
		typist.type(FVT_ActivitiesObjects.Start_An_Activity_InputText_Name, ActivityName);

		//Tags
		sel.selectWindow("null");
		typist.type(FVT_ActivitiesObjects.Start_An_Activity_InputText_Tags, FVT_ActivitiesData.Start_An_Activity_InputText_Tags_Data + activityRandomNumber, "tags");

		//select members
		sel.select(FVT_ActivitiesObjects.Start_An_Activity_InputSelect_MemberType, "Person");
		sel.select(FVT_ActivitiesObjects.Start_An_Activity_InputSelect_MemberRole, "Owner");
		//activate typeahead
		sel.focus(FVT_ActivitiesObjects.Start_An_Activity_InputText_Members_PeopleTypeAhead);
		typist.typeAndWait(FVT_ActivitiesObjects.Start_An_Activity_InputText_Members_PeopleTypeAhead, personAsOwner.replace(
				"ajones", "Amy Jones"), null);
		//select member from prompt or search and select
		selectMemberFromDropdown(personAsOwner);
		//check member added and name appears
		Assert.assertTrue(sel.isTextPresent(personAsOwner.replace("ajones", "Amy Jones")));

		//type activity description ('goals')
		sel.selectWindow("null");
		typist.type(FVT_ActivitiesObjects.Start_An_Activity_Textarea_Activity_Goals,
				FVT_ActivitiesData.Start_An_Activity_Textarea_Description_Data + ActivityName);

		//Enter due date
		sel.selectWindow("null");
		typist.pickADojoDate(FVT_ActivitiesObjects.Start_An_Activity_InputText_DueDate);

		//Save activity
		clickLink(CommonObjects.SaveButton);

		return ActivityName;
	}

	public void changeActivityAccess() {
		
		
		//Click on Members in the left Navigation pane
		clickLink(FVT_ActivitiesObjects.Members);

		//Click on the change access button
		clickLink(FVT_ActivitiesObjects.ChangeAccess);
		
		//Focus on the public radio button and click
		sel.focus(FVT_ActivitiesObjects.CheckPublic);
		cautiousClick(FVT_ActivitiesObjects.CheckPublic);
		
		
		//Save access changes
		clickLink(FVT_ActivitiesObjects.SaveAccessChange);
		
		//Go to activity outline
		clickLink(FVT_ActivitiesObjects.ActivityOutline);
	}	

	
	public void followActivity(String ActivityName) {
		
		
		LoadComponent(CommonObjects.ComponentActivities);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Activities is not open", sel.isTextPresent("Activities"));
		
		//Go back to activities list
		clickLink(FVT_ActivitiesObjects.Activities_Tab);
		
		//Go back to the main page of the activity created above
		clickLink("link=" + ActivityName);
		
		//Follow
		clickLink(FVT_ActivitiesObjects.FollowActivity);
		
		//Logout
		Logout();
	}	
	
}