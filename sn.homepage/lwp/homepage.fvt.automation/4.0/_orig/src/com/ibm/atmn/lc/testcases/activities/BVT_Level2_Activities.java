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

package com.ibm.atmn.lc.testcases.activities;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.base.User;
import com.ibm.atmn.base.UserAllocation;
import com.ibm.atmn.lc.appobjects.activities.ActivitiesData;
import com.ibm.atmn.lc.appobjects.activities.ActivitiesObjects;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.tasks.activities.ActivitiesMethods;

/*
 * TEST 1 Create an activity, entry, to-do:
 * 
 * Log in as ajones4.
 * 
 * Go to the About page. Examine page.
 * 
 * Go to the My Activities view.
 * 
 * Bring up the Start Activity dialog.
 * 
 * Enter a title
 * 
 * Enter some text in the description
 * 
 * Etner some tag
 * 
 * Enter a due date
 * 
 * Add ajones7 as owner.
 * 
 * Save the activity.
 * 
 * Create entry:
 * 
 * Bring up the new entry form.
 * 
 * Enter a title
 * 
 * Attach a file
 * 
 * Add a bookmark property
 * 
 * Enter a description
 * 
 * Add a link property
 * 
 * Check notify checkbox
 * 
 * Save the entry
 * 
 * Create a todo.
 * 
 * Add a new section.
 * 
 * TEST 2, edit activity, delete activity:
 * 
 * Login in as another member - ajones7. Verify the activity created is there.
 * 
 * Add a new entry.
 * 
 * Expand the entry created by ajones4. Verify ajones7 can see all the properties.
 * 
 * Edit the activity, change the description.
 * 
 * Delete the activity. Verify it's in Trash.
 */

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Activities extends ActivitiesMethods {

	private String part1ActivityName = "";
	private String part1EntryTitle = "";

	//NOTE: Only the ajones[number](Amy Jones[number]) class of users are valid here without further changes.
	private User testUser1;
	private User testUser2;

	private FormInputHandler typist = getFormInputHandler();
	private ArrayList<String> assertList;

	@Test(groups = { "activities", "level-twos", "activities-level-twos" }, dependsOnGroups = {})
	public void testActivitiesLevel2Part1() throws Exception {

		System.out.println("INFO: Start of Activities BVT (Level 2) Part 1");
		Reporter.log("INFO: Start of Activities BVT (Level 2) Part 1");

		UserAllocation allocator = UserAllocation.getInstance();
		testUser1 = allocator.getUser();
		testUser2 = allocator.getUser();

		// Load the component
		LoadComponent(CommonObjects.ComponentActivities);

		// Login as a user (ie. Amy Jones333)
		Login(testUser1.getEmail(), testUser1.getPassword());

		// Start Activities BVT (Level 2) Part 1

		//Examine 'About' Page
		Reporter.log("Examining About page");
		clickLink(CommonObjects.AboutPageLink);
		AssertJUnit.assertTrue(sel.isElementPresent(CommonObjects.ReleaseVersion));
		CheckForErrorsOnPage();

		//Back to activities home
		clickBannerLink(CommonObjects.Banner_Apps_Activities);

		//Start an activity
		part1ActivityName = startAnActivity(typist, testUser2.getUid());

		//Go back to activities list
		clickLink(ActivitiesObjects.Activities_Tab);

		//Expand top activity (activity created above)
		clickLink(ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);

		//Check all 'start activity' form entries were saved and are now displayed
		assertList = typist.getAssertList();
		assertAllTextPresent(assertList);
		typist.dumpList();

		//Go back to the main page of the activity created above
		clickLink("link=" + part1ActivityName);

		//expand activity description
		clickLink(ActivitiesObjects.ActivityOutline_More_ExpandDescription);

		//CheckForErrorsOnPage();

		//Create New entry for activity created above
		part1EntryTitle = addEntryToActivity(typist);

		//CheckForErrorsOnPage();

		//Add to-do entry
		sleep(1000);
		clickLink(ActivitiesObjects.AddToDo);
		typist.type(ActivitiesObjects.ToDo_InputText_Title, ActivitiesData.ToDo_InputText_Title_Data);
		//Save form
		TabAndEnter(ActivitiesObjects.ToDo_InputText_Title, 2);
		//Close form
		TabAndEnter(ActivitiesObjects.ToDo_InputText_Title, 3);
		//cautiousClick(ActivitiesObjects.SaveButton);
		//cautiousClick(CommonObjects.CancelLink);
		//clickLink(CommonObjects.SaveButton3);
		//clickLink(CommonObjects.CancelLink);

		//create new section
		sel.focus(ActivitiesObjects.AddSection);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sleep(1000);
		typist.type(ActivitiesObjects.Section_InputText_Title, ActivitiesData.Section_InputText_Title_Data);
		//save section
		TabAndEnter(ActivitiesObjects.Section_InputText_Title, 2);
		//Close form
		//TabAndEnter(ActivitiesObjects.Section_InputText_Title, 3);
		//sel.click(CommonObjects.SaveButton3);
		//clickLink(CommonObjects.CancelLink);

		//CheckForErrorsOnPage();

		//Need to wait for the new section to create before we look for all the text
		sleep(5000);

		//verify entry, to-do and section data created. Keep list for part 2
		assertList = typist.getAssertList();
		assertAllTextPresent(assertList);

		// Logout of Connections
		Logout();

		System.out.println("INFO: End of Activities BVT (Level 2) Part 1");
		Reporter.log("INFO: End of Activities BVT (Level 2) Part 1");
	}

	@Test(groups = { "activities", "level-twos", "activities-level-twos" }, dependsOnMethods = { "testActivitiesLevel2Part1" })
	public void testActivitiesLevel2Part2() throws Exception {

		System.out.println("INFO: Start of Activities BVT (Level 2) Part 2");

		// Load the component
		LoadComponent(CommonObjects.ComponentActivities);

		// Login as the user added as activity owner in test part 1 (ie. not Amy Jones333)
		Login(testUser2.getEmail(), testUser2.getPassword());

		// Start Activities BVT (Level 2) Part 2

		//expand last activity entered and go to activity outline
		sel.click(ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);
		clickLink("link=" + part1ActivityName);

		CheckForErrorsOnPage();

		//Add new entry
		addEntryToActivity(typist);

		//expand entry from part 1 (top entry)
		sel.focus("css=h4[id$=miniTitle]:contains(" + part1EntryTitle + ")");
		sel.click("css=h4[id$=miniTitle]:contains(" + part1EntryTitle + ")");

		//verify entry, to-do and section data
		assertList = typist.getAssertList();
		assertAllTextPresent(assertList);
		typist.dumpList();

		//Go back to activities list
		clickLink(ActivitiesObjects.Activities_Tab);

		//Expand top activity (activity created in part 1)
		clickLink(ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);
		//Click edit for top activity
		clickLink(ActivitiesObjects.ActivityHome_EditActivity_TopActivity);

		//Edit the activity description
		sel.focus(ActivitiesObjects.Start_An_Activity_Textarea_Activity_Goals);
		typist.type(ActivitiesObjects.Start_An_Activity_Textarea_Activity_Goals, "modified description for " + part1ActivityName);
		clickLink(ActivitiesObjects.Edit_Activity_Dialogue_SaveButton);

		//Expand top activity (activity created in part 1 and just modified)
		clickLink(ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);

		//Check modified activity description is saved
		assertList = typist.getAssertList();
		assertAllTextPresent(assertList);
		typist.dumpList();

		CheckForErrorsOnPage();

		//Delete Activity
		clickLink(ActivitiesObjects.ActivitiesHome_DeleteActivity_TopActivity);
		clickLink(ActivitiesObjects.Delete_Activity_Dialogue_OkButton);

		CheckForErrorsOnPage();

		//Go to trash folder and verify deleted activity is present
		clickLink(ActivitiesObjects.Activities_LeftNav_Trash);
		Assert.assertTrue(sel.isTextPresent(part1ActivityName), "Activity not visible in trash: " + part1ActivityName);

		CheckForErrorsOnPage();

	}
}
