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

package com.ibm.atmn.lc.tasks.widgets;

import com.ibm.atmn.lc.appobjects.widgets.WidgetsObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import org.testng.Assert;

public class WidgetsMethods extends CommonMethods {

	/** Click specified link */
	/*
	 * public void clickLink(String Link) throws Exception{ //Click the link provided sel.click(Link);
	 * Thread.sleep(3000); }
	 */

	/** Click At item */
	public void clickAtItem(String Link1, String Link2) throws Exception {

		//Click at the item provided
		sel.clickAt(Link1, Link2);
		Thread.sleep(3000);
	}

	public void CustomizeCommunity() throws Exception {

		//Click on the Communities Action button
		clickLink(WidgetsObjects.Community_Actions_Button);

		//Choose the 3 option - Customize
		sel.click(WidgetsObjects.Menu_Item_3);
		Thread.sleep(3000);

		//Verify that there are 8 widgets
		//Assert.assertTrue(sel.isTextPresent("1-8 widgets of 8"));
	}

	public void AddWigdetsToCommunity() throws Exception {

		sel.click(WidgetsObjects.WidgetBlog);
		CheckForAddingWidget();

		sel.click(WidgetsObjects.WidgetIdeationBlog);
		CheckForAddingWidget();

		sel.click(WidgetsObjects.WidgetActivities);
		CheckForAddingWidget();

		clickLink(WidgetsObjects.WidgetEvent);
		CheckForAddingWidget();

		sel.click(WidgetsObjects.WidgetFeeds);
		CheckForAddingWidget();

		sel.click(WidgetsObjects.WidgetMediaGallery);
		CheckForAddingWidget();

		sel.click(WidgetsObjects.WidgetSubCommunities);
		CheckForAddingWidget();

		sel.click(WidgetsObjects.WidgetWikis);
		CheckForAddingWidget();

		sel.click(WidgetsObjects.WidgetSectionClose);

	}

	public void VerifyWidgets() throws Exception {

		//To check that all widgets are present
		Assert.assertTrue(sel.isTextPresent("Feeds"));
		Assert.assertTrue(sel.isTextPresent("Subcommunities"));
		Assert.assertTrue(sel.isTextPresent("Wiki"));
		Assert.assertTrue(sel.isTextPresent("Activities"));
		Assert.assertTrue(sel.isTextPresent("Ideation Blog"));
		Assert.assertTrue(sel.isTextPresent("Blog"));
		Assert.assertTrue(sel.isTextPresent("Files"));
		Assert.assertTrue(sel.isTextPresent("Bookmarks"));
		Assert.assertTrue(sel.isTextPresent("Forums"));
		Assert.assertTrue(sel.isTextPresent("Calendar"));
	}

	public void CheckForAddingWidget() throws Exception {

		/*
		 * Method to check if the Adding Widget text is present and if it is not, sleep for 1000 millisecond and then
		 * check again if the text is not present then the script continues
		 */
		int i = 0;
		while (i < 20) {
			if (sel.isTextPresent("Adding Widget")) {
				Thread.sleep(1000);
				i = i + 1;
			}
			break;
		}
	}
}
