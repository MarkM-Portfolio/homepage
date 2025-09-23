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

package com.ibm.atmn.wd_homepagefvt.tasks.activities;

import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.ibm.atmn.wdbase.FormInputHandler;
import com.ibm.atmn.wdbase.core.Element;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;




public class FVT_ActivitiesMethods extends FVT_CommunitiesMethods {
	
	

	/** Select a member from activated Add member textfield typeahead dropdown */
	public void selectMemberFromDropdown(String userName) {

		driver.getSingleElement(FVT_ActivitiesObjects.usersActivitySearchIdentifier).click();

		if (driver.isElementPresent(FVT_ActivitiesObjects.selectedActivityUserIdentifier)) {
			driver.getSingleElement(FVT_ActivitiesObjects.selectedActivityUserIdentifier).click();
			sleep(1000);
		}
		else {
			driver.getSingleElement(FVT_ActivitiesObjects.selectedActivityUserIdentifier).click();
			sleep(1000);
		}
	}
	
	public void UploadFileToActivity(String FileUploadName){
		
		
		//Open "File Upload" dialog
		
		clickLink(FVT_ActivitiesObjects.AddFileLink);
		
		clickLink(FVT_ActivitiesObjects.AddFileOptionAttachFile);
		
		driver.getSingleElement(FVT_ActivitiesObjects.AttachmentField).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
		
	}

	/*
	
	public void UploadFileToActivity(String FileUploadName){
		sel.selectWindow("null");
		
		//Open "File Upload" dialog
		//sel.focus(FVT_ActivitiesObjects.AddFileLink);
		clickLink(FVT_ActivitiesObjects.AddFileLink);
		//sel.focus(FVT_ActivitiesObjects.AddFileOptionAttachFile);
		clickLink(FVT_ActivitiesObjects.AddFileOptionAttachFile);
		//sel.focus(FVT_ActivitiesObjects.AttachmentField);
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
	
	*/
	
	public String addEntryToActivity(FormInputHandler typist) {
		
		typist = new FormInputHandler(driver);
		
		clickLink(FVT_ActivitiesObjects.New_Entry);

		//start of form
		String entryRandomNumber = genDateBasedRandVal();
		String part1EntryTitle = FVT_ActivitiesData.Start_A_Entry_InputText_Title_Data + entryRandomNumber;

		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_InputText_Title).type(part1EntryTitle);
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_InputText_Tags).type(FVT_ActivitiesData.Start_A_Entry_InputText_Tags_Data
				+ entryRandomNumber, "tags");

		//upload file
		/*
		 * upload file goes here sel.click("link=Add File "); sel.click("id=dijit_MenuItem_3_text");
		 * sel.click("name=fields.new0.file");
		 */
		UploadFileToActivity("Desert.jpg");
		
		
		//Add bookmark
		clickLink(FVT_ActivitiesObjects.New_Entry_Add_Bookmark);
	
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_Bookmark1_Lable_InputText_LableName).type(entryRandomNumber + "ActivitiesHome");
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_Bookmark1_InputText_LinkDescription).type("ActHome " + entryRandomNumber);
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_Bookmark1_InputText_LinkURL).type(FVT_ActivitiesObjects.TestURL + "activities", null);

		//Add Second bookmark
		clickLink(FVT_ActivitiesObjects.New_Entry_Add_Bookmark);
		
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_Bookmark2_Lable_InputText_LableName).type("Link Name " + entryRandomNumber);
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_Bookmark2_InputText_LinkDescription).type("Link " + entryRandomNumber);
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_Bookmark2_InputText_LinkURL).type("ftp://dog-earURL.com", null);

		//Add date
		clickLink(FVT_ActivitiesObjects.New_Entry_AddCustomFieldsMenu);
		clickLink(FVT_ActivitiesObjects.New_Entry_AddCustomFields_Date);
	
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_Date_Lable_InputText_LableName).type(entryRandomNumber + " Date 1");
		typist.pickADojoDate(FVT_ActivitiesObjects.New_Entry_Date_InputText_Date);
		
		//Entry text
		String description = FVT_ActivitiesData.CK_Editor_Textarea_RichText_Data + entryRandomNumber;
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);

		//Notification
		clickLink(FVT_ActivitiesObjects.New_Entry_Notify_Checkbox_Notify);
		clickLink(FVT_ActivitiesObjects.New_Entry_Notify_Checkbox_NotifyAll);
		
		//Return to default speed
		

		//Save
		clickLink(FVT_ActivitiesObjects.Save_Entry);

		return part1EntryTitle;
	}

	public String addEntryToActivity_Basic(String ActivityName, FormInputHandler typist, boolean accessPrivate) {

		typist = new FormInputHandler(driver);
		
		//
		clickLink(FVT_ActivitiesObjects.New_Entry);

		//start of form
		String entryRandomNumber = genDateBasedRandVal();
		//String part1EntryTitle = FVT_ActivitiesData.Start_A_Entry_InputText_Title_Data + entryRandomNumber;

		
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_InputText_Title).clear();
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_InputText_Title).type(ActivityName);
		driver.getSingleElement(FVT_ActivitiesObjects.New_Entry_InputText_Tags).type(FVT_ActivitiesData.Start_A_Entry_InputText_Tags_Data);
		
		//Entry text
		String description = FVT_ActivitiesData.CK_Editor_Textarea_RichText_Data + entryRandomNumber;
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);

		if(accessPrivate==true){
		clickLink(FVT_ActivitiesObjects.MarkPrivate);
		}
		
		//Save
		driver.getFirstElement(FVT_ActivitiesObjects.Save_Entry).click();

		return ActivityName;
	}
	
	public String addToDoToActivity(String ToDoTitle,FormInputHandler typist, boolean accessPrivate, boolean assignToDo) {

		typist = new FormInputHandler(driver);
		
		//start of form
		String entryRandomNumber = genDateBasedRandVal();
		//String ToDoTitle = FVT_ActivitiesData.ToDo_InputText_Title_Data + entryRandomNumber;
		
		//Add to-do entry
		clickLink(FVT_ActivitiesObjects.AddToDo);
		//sleep(1000);
		//sel.focus(FVT_ActivitiesObjects.AddToDo);
		//sleep(200);
		//sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		//sleep(1000);
		
		clickLink(FVT_ActivitiesObjects.ToDoMoreOptions);
		
		//Title
		driver.getSingleElement(FVT_ActivitiesObjects.ToDo_InputText_Title).clear();
		typist.type(FVT_ActivitiesObjects.ToDo_InputText_Title, ToDoTitle);
		
		typist.type(FVT_ActivitiesObjects.ToDo_InputText_Tag, FVT_ActivitiesData.ToDo_Tags_Data);
		
		//Assign Members
		if(assignToDo==true){
			
			clickLink(FVT_ActivitiesObjects.ToDo_Assign_Person);
			clickLink(FVT_ActivitiesObjects.ToDo_Assign_Person);
			driver.getSingleElement(FVT_ActivitiesObjects.ToDo_Assign_FilterBox).clear();
			driver.getSingleElement(FVT_ActivitiesObjects.ToDo_Assign_FilterBox).type("Amy Jones450");
			clickLink(FVT_ActivitiesObjects.ToDo_Assign_CheckBox);
			}
		
		
		//Description
		String description = FVT_ActivitiesData.ToDo_InputText_Description_Data + entryRandomNumber;
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);
		
		
		//Mark To Do Private
		if(accessPrivate==true){
			//sel.focus(FVT_ActivitiesObjects.MarkPrivateToDo);
			clickLink(FVT_ActivitiesObjects.MarkPrivateToDo);
			}
		
		//Save To Do
		clickLink(FVT_ActivitiesObjects.SaveButton);
		//clickLink(CommonObjects.CancelLink);
		

		return ToDoTitle;
	}
	
	public String addToDoToActivity(String ToDoTitle,FormInputHandler typist, boolean accessPrivate, boolean assignToDo, String Username) {

		typist = new FormInputHandler(driver);
		
		//start of form
		String entryRandomNumber = genDateBasedRandVal();
		//String ToDoTitle = FVT_ActivitiesData.ToDo_InputText_Title_Data + entryRandomNumber;
		
		//Add to-do entry
		clickLink(FVT_ActivitiesObjects.AddToDo);
		//sleep(1000);
		//sel.focus(FVT_ActivitiesObjects.AddToDo);
		//sleep(200);
		//sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		//sleep(1000);
		
		clickLink(FVT_ActivitiesObjects.ToDoMoreOptions);
		
		//Title
		driver.getSingleElement(FVT_ActivitiesObjects.ToDo_InputText_Title).clear();
		typist.type(FVT_ActivitiesObjects.ToDo_InputText_Title, ToDoTitle);
		
		//Assign Members
		if(assignToDo==true){
			
			clickLink(FVT_ActivitiesObjects.ToDo_Assign_Person);
			//sel.focus(FVT_ActivitiesObjects.ToDo_Assign_FilterBox);
			driver.getSingleElement(FVT_ActivitiesObjects.ToDo_Assign_FilterBox).clear();
			driver.getSingleElement(FVT_ActivitiesObjects.ToDo_Assign_FilterBox).type(Username);
			clickLink(FVT_ActivitiesObjects.ToDo_Assign_CheckBox);
			}
		
		
		//Description
		String description = FVT_ActivitiesData.ToDo_InputText_Description_Data + entryRandomNumber;
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);
		
		
		//Mark To Do Private
		if(accessPrivate==true){
			//sel.focus(FVT_ActivitiesObjects.MarkPrivateToDo);
			clickLink(FVT_ActivitiesObjects.MarkPrivateToDo);
			}
		
		//Save To Do
		clickLink(FVT_ActivitiesObjects.SaveButton);
		//clickLink(CommonObjects.CancelLink);
		

		return ToDoTitle;
	}
	
	public void addCommentToActivityEntry(FormInputHandler typist, boolean accessPrivate) {
		
		typist = new FormInputHandler(driver);
		
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
	
	public void editCommentToActivityEntry(FormInputHandler typist, boolean accessPrivate) {
		
		typist = new FormInputHandler(driver);
		
		//
		
		List<Element> list;
		list = driver.getElements("link=Edit");
		Integer last = list.size();

		list.get(last-(last-1)).click();
		//clickLink(FVT_ActivitiesObjects.EditComment);

		
		//Entry text
		String description = " - Edited";
		typeNativeInCkEditor(description);
		typist.addBlobToAssertList(description);

		if(accessPrivate==true){
		clickLink(FVT_ActivitiesObjects.MarkPrivateComment);
		}
		//Save
		clickLink(FVT_ActivitiesObjects.SaveButton);

	}
	
	public String startAnActivity(String ActivityName, FormInputHandler typist, String personAsOwner) {

		typist = new FormInputHandler(driver);
		
		clickLink(FVT_ActivitiesObjects.Start_An_Activity);

		//Activity Name
		//sel.selectWindow("null");
		driver.getSingleElement(FVT_ActivitiesObjects.Start_An_Activity_InputText_Name).type(ActivityName);

		//Tags
		driver.getSingleElement(FVT_ActivitiesObjects.Start_An_Activity_InputText_Tags).type(FVT_ActivitiesData.Start_An_Activity_InputText_Tags_Data);

		//select members	
		driver.getSingleElement(FVT_ActivitiesObjects.Start_An_Activity_InputSelect_MemberType).useAsDropdown().selectOptionByVisibleText("Person");
		driver.getSingleElement(FVT_ActivitiesObjects.Start_An_Activity_InputSelect_MemberRole).useAsDropdown().selectOptionByVisibleText("Owner");
		
		//activate typeahead
		typist.typeAndWait(FVT_ActivitiesObjects.Start_An_Activity_InputText_Members_PeopleTypeAhead, personAsOwner, null);
		//select member from prompt or search and select
		selectMemberFromDropdown(personAsOwner);
		//check member added and name appears
		//Assert.assertTrue(driver.isTextPresent(personAsOwner), "Person was not added as owner");

		//type activity description ('goals')
		//sel.selectWindow("null");
		driver.getSingleElement(FVT_ActivitiesObjects.Start_An_Activity_Textarea_Activity_Goals).type(
				FVT_ActivitiesData.Start_An_Activity_Textarea_Description_Data + ActivityName);

		//Enter due date
		//sel.selectWindow("null");
		typist.pickADojoDate(FVT_ActivitiesObjects.Start_An_Activity_InputText_DueDate);

		//Save activity
		clickLink(CommonObjects.SaveButton);

		return ActivityName;
	}

	public void changeActivityAccess(String Access) {
		
		
		//Click on Members in the left Navigation pane
		clickLink(FVT_ActivitiesObjects.Members);

		//Click on the change access button
		clickLink(FVT_ActivitiesObjects.ChangeAccess);
		
		//Focus on the public radio button and click
		//sel.focus(FVT_ActivitiesObjects.CheckPublic);
		if(Access=="Public"){
		clickLink(FVT_ActivitiesObjects.CheckPublic);
		}else if(Access=="Public Read"){
		clickLink(FVT_ActivitiesObjects.CheckPublicRead);
		}else{
		clickLink(FVT_ActivitiesObjects.CheckPrivate);
		}
		
		
		//Save access changes
		clickLink(FVT_ActivitiesObjects.SaveAccessChange);
		
		//Go to activity outline
		clickLink(FVT_ActivitiesObjects.ActivityOutline);
	}	

	public void changeActivityAccess() {
		
		
		//Click on Members in the left Navigation pane
		clickLink(FVT_ActivitiesObjects.Members);

		//Click on the change access button
		clickLink(FVT_ActivitiesObjects.ChangeAccess);
		
		//Focus on the public radio button and click
		//sel.focus(FVT_ActivitiesObjects.CheckPublic);
		clickLink(FVT_ActivitiesObjects.CheckPublic);
		
		//Save access changes
		clickLink(FVT_ActivitiesObjects.SaveAccessChange);
		
		//Go to activity outline
		clickLink(FVT_ActivitiesObjects.ActivityOutline);
	}
	
	public void addMember(FormInputHandler typist, String PersonAsMember) {
		
		typist = new FormInputHandler(driver);
		
		//Click on Members in the left Navigation pane
		clickLink(FVT_ActivitiesObjects.Members);

		//Click Add Members
		clickLink(FVT_ActivitiesObjects.ADD_MEMBERS);
		
		//select members	
		getActiveElement(FVT_ActivitiesObjects.ADD_MEMBER_TYPE).useAsDropdown().selectOptionByVisibleText("Person");
		getActiveElement(FVT_ActivitiesObjects.ADD_MEMBER_ROLE).useAsDropdown().selectOptionByVisibleText("Reader");
		
		/*
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		WebElement NewElement = wd.findElement(By.cssSelector("div div[id^='lconn_act_MemberPicker_'][class='AddMembers'] ul.lotusInlinelist li.lotusFirst select[title='Member Type']"));
		
		List<WebElement> DList = NewElement.findElements(By.cssSelector("[role='option'][value='0']"));
		
		String test = "";
		
		for (WebElement a:DList){
			test = a.getText();
			Reporter.log("Test: ");
			Reporter.log(test);
		
		}
		
		*/
		
		//activate typeahead
		typist.typeAndWait(FVT_ActivitiesObjects.Start_An_Activity_InputText_Members_PeopleTypeAhead, PersonAsMember, null);
		//select member from prompt or search and select
		selectMemberFromDropdown(PersonAsMember);
		
		clickLink(CommonObjects.SaveButton);
		
	}
	
	public void followActivity(String ActivityName) {
		
		
		LoadComponent(CommonObjects.ComponentActivities);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));
		
		//Go back to activities list
		clickLink(FVT_ActivitiesObjects.Activities_Tab);
		
		//Go back to the main page of the activity created above
		clickLink("link=" + ActivityName);
		
		//Follow
		clickLink(FVT_ActivitiesObjects.FollowActivity);
		
		//Logout
		Logout();
		
	}	
	
public void notifyAboutEntry() {
	
	//Open dialog to notify other people
	clickLink(FVT_ActivitiesObjects.MORE_ACTIONS);
	
	clickLink(FVT_ActivitiesObjects.NOTIFY_OTHER_PEOPLE);
	
	//Send Notification
	clickLink(FVT_ActivitiesObjects.NOTIFY_ALL_CHECK);
	
	clickLink(FVT_ActivitiesObjects.SEND_NOTIFICATIONS);
	
	//Check that notifications sent
	assertTrue("Fail: Notification not sent correctly", driver.isTextPresent("The notification has been sent!"));	

	
}	
	
	
}