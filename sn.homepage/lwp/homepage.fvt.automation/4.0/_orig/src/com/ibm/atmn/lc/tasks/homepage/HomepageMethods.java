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

package com.ibm.atmn.lc.tasks.homepage;

// import com.ibm.automation.bvt.setup.common.Initialize;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.homepage.HomepageObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import java.awt.event.*;
import org.testng.Assert;

public class HomepageMethods extends CommonMethods {

	public void CheckWidgetsIsAdded() throws Exception {

		//check to see if the widgets are added already
		if (sel.isElementPresent(HomepageObjects.ActivitiesWidget)) {
			//If the element is present then remove
			RemoveWidget();
		}
		if (sel.isElementPresent(HomepageObjects.BookmarksWidget)) {
			//If the element is present then remove
			RemoveWidget();
		}
		if (sel.isElementPresent(HomepageObjects.CommunitiesWidget)) {
			//If the element is present then remove
			RemoveWidget();
		}
		if (sel.isElementPresent(HomepageObjects.BlogsWidget)) {
			//If the element is present then remove
			RemoveWidget();
		}
		if (sel.isElementPresent(HomepageObjects.ProfilesWidget)) {
			//If the element is present then remove
			RemoveWidget();
		}
		if (sel.isElementPresent(HomepageObjects.LatestWikisWidget)) {
			//If the element is present then remove
			RemoveWidget();
		}
		if (sel.isElementPresent(HomepageObjects.MyFilesWidget)) {
			//If the element is present then remove
			RemoveWidget();
		}

	}

	public void RemoveWidget() throws Exception {

		//Remove any existing widgets from the widget view in homepage	
		try {
			if (sel.isElementPresent(HomepageObjects.ClickForActions)) {
				sel.click(HomepageObjects.ClickForActions);
				Thread.sleep(1000);
				if (sel.isElementPresent(HomepageObjects.ClickForActionsOptionRemove)) {
					sel.click(HomepageObjects.ClickForActionsOptionRemove);
					Thread.sleep(500);
				}
				else {
					sel.click(HomepageObjects.ClickForActionsOption5);
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {

		}
		Thread.sleep(1000);

	}

	public void RemoveWidgets() throws Exception {

		//Remove any existing widgets from the widget view in homepage	
		for (int widgets = 0;; widgets++) {
			if (widgets >= 3) ;
			try {
				if (sel.isElementPresent(HomepageObjects.ClickForActions)) {
					sel.click(HomepageObjects.ClickForActions);
					Thread.sleep(1000);
					if (sel.isElementPresent(HomepageObjects.ClickForActionsOptionRemove)) {
						sel.click(HomepageObjects.ClickForActionsOptionRemove);
						Thread.sleep(500);
					}
					else {
						sel.click(HomepageObjects.ClickForActionsOption5);
						Thread.sleep(500);
					}
				}//break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public void OpenHelp(String WidgetName, String ComponentClickForActions) throws Exception {

		//From the menu dropdown for each component choose Help	
		if (sel.isElementPresent(ComponentClickForActions)) {
			sel.click(ComponentClickForActions);
			Thread.sleep(500);

			if (ComponentClickForActions.contains("Communities")) {
				sel.click(HomepageObjects.ClickForActionsOption);
			}
			else if (ComponentClickForActions.contains("Blogs")) {
				sel.click(HomepageObjects.ClickForActionsOption);
			}
			else if (ComponentClickForActions.contains("Profiles")) {
				sel.click(HomepageObjects.ClickForActionsOption);
			}
			else if (ComponentClickForActions.contains("Bookmarks")) {
				sel.click(HomepageObjects.ClickForActionsOption);
			}
			else {
				sel.click(HomepageObjects.ClickForActionsOption);
			}
			Thread.sleep(1000);
		}
	}

	public void VerifyHelp(String ComponentLink, String HomepageTitle, String WidgetNameVerified) throws Exception {

		if (sel.isElementPresent(HomepageObjects.HelpFrame)) {
			sel.selectWindow(HomepageObjects.HelpFrame);
			Thread.sleep(500);
		}
		else {
			Thread.sleep(4000);
			sel.selectWindow(HomepageObjects.HelpFrame);
			Thread.sleep(500);
		}
		Assert.assertEquals(WidgetNameVerified, sel.getText(HomepageObjects.UsingWidgetText));
		Thread.sleep(500);
		sel.selectWindow("null");
		Thread.sleep(1000);
	}

	public void WidgetsCustomize(String WidgetToAdd, String AddWidgetToTest) throws Exception {

		//Click on the Customize button in the Widgets tab
		//clickLink(Objects.WidgetsCustomize);
		if (sel.isElementPresent(HomepageObjects.WidgetsCustomize)) {
			sel.click(HomepageObjects.WidgetsCustomize);
			Thread.sleep(2000);
		}
		//Choose which component to show widgets for
		if (sel.isElementPresent(WidgetToAdd)) {
			sel.click(WidgetToAdd);
			Thread.sleep(2000);
		}
		//Add the widget
		if (sel.isElementPresent(AddWidgetToTest)) {
			sel.click(AddWidgetToTest);
			Thread.sleep(2000);
		}
	}

	public void MoveBetweenTabs(String User_Name, String Password) throws Exception {

		/*
		 * Method to switch between tabs in a browser
		 */
		sel.keyDownNative(String.valueOf(KeyEvent.VK_CONTROL));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_CONTROL));
		Thread.sleep(1000);
		//Check to see if you are required to login again
		if (sel.isElementPresent(CommonObjects.USERNAME_FIELD)) {
			sel.type(CommonObjects.USERNAME_FIELD, User_Name);
			Thread.sleep(500);
			sel.type(CommonObjects.Password_FIELD, Password);
			Thread.sleep(500);
			sel.click(CommonObjects.Login_Button);
			Thread.sleep(3000);
		}
	}

	public void VerifyComponentName(String ComponentName) throws Exception {

		Assert.assertTrue(sel.isTextPresent(ComponentName));
		Thread.sleep(500);
	}

}