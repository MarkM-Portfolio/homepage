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

package com.ibm.atmn.lc.tasks.activities;

import java.awt.event.KeyEvent;

import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.lc.appobjects.activities.ActivitiesData;
import com.ibm.atmn.lc.appobjects.activities.ActivitiesObjects;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import org.testng.Assert;

public class ActivitiesMethods extends CommonMethods {

	/** Select a member from activated Add member textfield typeahead dropdown */
	public void selectMemberFromDropdown(String userName) {

		if (userName.contains("Amy Jones")) userName = userName.replace("Amy Jones", "ajones");

		cautiousFocus(ActivitiesObjects.Start_An_Activity_Members_TypeaheadPromptList);
		sel.clickAt(ActivitiesObjects.usersActivitySearchIdentifier, "1,1");
		clickAtAndWait(ActivitiesObjects.selectedActivityUserIdentifier, "1,1");
	}

	public void UploadFileToActivity(String FileUploadName) {

		sel.selectWindow("null");

		//Open "File Upload" dialog
		sel.focus(ActivitiesObjects.AddFileLink);
		clickLink(ActivitiesObjects.AddFileLink);
		sel.focus(ActivitiesObjects.AddFileOptionAttachFile);
		clickLink(ActivitiesObjects.AddFileOptionAttachFile);
		sel.focus(ActivitiesObjects.AttachmentField);
		sleep(500);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		sleep(3000);

		//get the path and file name of the file to upload
		typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sleep(1000);

	}

	public String addEntryToActivity(FormInputHandler typist) {

		clickLink(ActivitiesObjects.New_Entry);

		//start of form
		String entryRandomNumber = genDateBasedRandVal();
		String part1EntryTitle = ActivitiesData.Start_A_Entry_InputText_Title_Data + entryRandomNumber;

		typist.type(ActivitiesObjects.New_Entry_InputText_Title, part1EntryTitle);
		typist.type(ActivitiesObjects.New_Entry_InputText_Tags, ActivitiesData.Start_A_Entry_InputText_Tags_Data + entryRandomNumber, "tags");

		/*
		 * upload file
		 */
		UploadFileToActivity("Desert.jpg");

		//Slow Down
		sel.setSpeed("1200");

		//Add bookmark
		sel.click(ActivitiesObjects.New_Entry_Add_Bookmark);
		typist.type(ActivitiesObjects.New_Entry_Bookmark1_Lable_InputText_LableName, entryRandomNumber + "ActivitiesHome");
		typist.type(ActivitiesObjects.New_Entry_Bookmark1_InputText_LinkDescription, "ActHome " + entryRandomNumber);
		typist.type(ActivitiesObjects.New_Entry_Bookmark1_InputText_LinkURL, sConfig.browserURL + "activities", null);

		//Add Second bookmark
		sel.click(ActivitiesObjects.New_Entry_Add_Bookmark);
		typist.type(ActivitiesObjects.New_Entry_Bookmark2_Lable_InputText_LableName, "Link Name " + entryRandomNumber);
		typist.type(ActivitiesObjects.New_Entry_Bookmark2_InputText_LinkDescription, "Link " + entryRandomNumber);
		typist.type(ActivitiesObjects.New_Entry_Bookmark2_InputText_LinkURL, "ftp://dog-earURL.com", null);

		//Add date
		sel.click(ActivitiesObjects.New_Entry_AddCustomFieldsMenu);
		sel.click(ActivitiesObjects.New_Entry_AddCustomFields_Date);
		typist.type(ActivitiesObjects.New_Entry_Date_Lable_InputText_LableName, entryRandomNumber + " Date 1");
		typist.pickADojoDate(ActivitiesObjects.New_Entry_Date_InputText_Date);

		//Entry text
		sel.selectWindow("null");
		sel.focus(ActivitiesObjects.New_Entry_AddCustomFieldsMenu);
		sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sleep(1000);
		String description = ActivitiesData.CK_Editor_Textarea_RichText_Data + entryRandomNumber;
		//Return to default speed
		sel.setSpeed("0");
		//typeNativeInCkEditor(description);
		typeNative(description);
		typist.addBlobToAssertList(description);

		//Notification
		sel.click(ActivitiesObjects.New_Entry_Notify_Checkbox_Notify);
		sel.click(ActivitiesObjects.New_Entry_Notify_Checkbox_NotifyAll);

		//Save	
		try {
			System.out.println("Clicking the Save button");
			if (sel.isElementPresent(CommonObjects.SaveButton3)) {
				System.out.println("Save button present");
				sel.focus(CommonObjects.SaveButton3);
				clickLink(CommonObjects.SaveButton3);
			}
			else {
				System.out.println("No Save button");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return part1EntryTitle;
	}

	public String startAnActivity(FormInputHandler typist, String personAsOwner) {

		clickLink(ActivitiesObjects.Start_An_Activity);

		//Fill form to create activity
		String activityRandomNumber = genDateBasedRandVal();
		String part1ActivityName = ActivitiesData.Start_An_Activity_InputText_Name_Data + activityRandomNumber;

		//Activity Name
		sel.selectWindow("null");
		typist.type(ActivitiesObjects.Start_An_Activity_InputText_Name, part1ActivityName);

		//Tags
		sel.selectWindow("null");
		typist.type(ActivitiesObjects.Start_An_Activity_InputText_Tags, ActivitiesData.Start_An_Activity_InputText_Tags_Data + activityRandomNumber, "tags");

		//select members
		sel.select(ActivitiesObjects.Start_An_Activity_InputSelect_MemberType, "Person");
		sel.select(ActivitiesObjects.Start_An_Activity_InputSelect_MemberRole, "Owner");
		//activate typeahead
		sel.focus(ActivitiesObjects.Start_An_Activity_InputText_Members_PeopleTypeAhead);
		typist.typeAndWait(ActivitiesObjects.Start_An_Activity_InputText_Members_PeopleTypeAhead, personAsOwner.replace("ajones", "Amy Jones"), null);
		//select member from prompt or search and select
		selectMemberFromDropdown(personAsOwner);
		//check member added and name appears
		Assert.assertTrue(sel.isTextPresent(personAsOwner.replace("ajones", "Amy Jones")));

		//type activity description ('goals')
		sel.selectWindow("null");
		typist.type(ActivitiesObjects.Start_An_Activity_Textarea_Activity_Goals, ActivitiesData.Start_An_Activity_Textarea_Description_Data + part1ActivityName);

		//Enter due date
		sel.selectWindow("null");
		typist.pickADojoDate(ActivitiesObjects.Start_An_Activity_InputText_DueDate);

		//Save activity
		clickLink(CommonObjects.SaveButton);

		return part1ActivityName;
	}

	public void ShiftTab() throws Exception {

		Thread.sleep(500);
		sel.keyDownNative(String.valueOf(KeyEvent.VK_SHIFT));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_SHIFT));
		Thread.sleep(1000);
	}

	public void TabAndEnter(String ObjectFocus, int NoTabs) throws Exception {

		sel.focus(ObjectFocus);
		sleep(500);
		try {
			int i = 0;
			while (i < NoTabs) {
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sleep(500);
				i++;
			}
			sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
			sleep(3500);
		} catch (Exception e) {
			sleep(3500);
		}
	}
}
