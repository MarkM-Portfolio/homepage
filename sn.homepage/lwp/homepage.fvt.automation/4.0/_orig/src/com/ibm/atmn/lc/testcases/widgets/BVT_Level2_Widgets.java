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

package com.ibm.atmn.lc.testcases.widgets;

import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.widgets.WidgetsData;
import com.ibm.atmn.lc.appobjects.widgets.WidgetsObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;
import com.ibm.atmn.lc.tasks.widgets.WidgetsMethods;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

/*
 * This is a functional test for the Widgets Component of IBM Connections Created By: Conor Pelly Date: 07/05/2011
 */

// Log into communities and then add widgets - logout.
@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Widgets extends WidgetsMethods {

	/*
	 * This is a functional test for the Widgets Component of IBM Connections Created By: Conor Pelly Date: 11/02/2011
	 */

	//Now Get the DateTime
	String DateTimeStamp = CommonMethods.genDateBasedRandVal();

	//Log into communities and then add widgets - logout.
	@Test
	public void addWidgets() throws Exception {

		System.out.println("INFO: Start of Widgets BVT_Level_2 Test");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//assertTrue("Fail: Communities is not open", selenium.isTextPresent("My Communities"));

		//Create a community
		CreateCommunity(WidgetsObjects.CommunityName, WidgetsData.NameOfCommunity + genDateBasedRandVal(), WidgetsObjects.CommunityTag, WidgetsData.TagForCommunity);

		//Customize community
		CustomizeCommunity();

		//Add the widgets to the community
		AddWigdetsToCommunity();

		//Verify that the widgets are displayed in the Community
		VerifyWidgets();

		//Check for any error messages
		CheckForErrorsOnPage();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Widgets BVT_Level_2 Test");
	}

	//Log into communities and then add widgets - logout.
	@Test(dependsOnMethods = { "addWidgets" })
	public void calendar() throws Exception {

		System.out.println("INFO: Start of Calendar Test");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Load the community just created
		clickLink("link=" + WidgetsData.NameOfCommunity + DateTimeStamp);

		//Click on the Events link in the nav
		cautiousClick("//a[contains(text(),'Calendar')]");

		//Verify the calendar view
		Assert.assertTrue(sel.isElementPresent("css=a[title='Create a new event on selected day']"));
		Assert.assertTrue(sel.isElementPresent("css=#listTab"));
		Assert.assertTrue(sel.isElementPresent("css=#gridTab"));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Calendar Test");
	}
}
