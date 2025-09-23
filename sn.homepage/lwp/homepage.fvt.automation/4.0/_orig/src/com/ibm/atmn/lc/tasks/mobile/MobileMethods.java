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

package com.ibm.atmn.lc.tasks.mobile;

import com.ibm.atmn.lc.appobjects.mobile.MobileData;
import com.ibm.atmn.lc.appobjects.mobile.MobileObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import org.testng.Assert;

public class MobileMethods extends CommonMethods {

	public void HomepageLauncher() throws Exception {

		clickLink(MobileObjects.HomepageLauncher);
	}

	public void VerifyMobileApp(String MobileApp, String AppPageTitle) throws Exception {

		//Click on the homepage launcher to see the full list of apps
		HomepageLauncher();

		//Click on the Updates app
		clickLink(MobileApp);

		//Verify the title of the page
		Assert.assertEquals(AppPageTitle, sel.getText(MobileObjects.PageTitle));

	}

	public void VerifyWikisApp(String MobileApp, String AppPageTitle) throws Exception {

		//Click on the homepage launcher to see the full list of apps
		HomepageLauncher();

		//Click on the Updates app
		clickLink(MobileApp);

		//Verify the title of the page
		Assert.assertEquals(AppPageTitle, sel.getText(MobileObjects.PageTitle));

		//Verify the the 3 option buttons exist
		sel.isElementPresent(MobileObjects.MobileWikisTag);
		sel.isElementPresent(MobileObjects.MobileWikisSort);
		sel.isElementPresent(MobileObjects.MobileWikisMenu);

		//Now verify the sort option
		clickLink(MobileObjects.MobileWikisSort);

		Assert.assertEquals(MobileData.SortOption1, sel.getText(MobileObjects.MobileWikisSortOption1));
		Assert.assertEquals(MobileData.SortOption2, sel.getText(MobileObjects.MobileWikisSortOption2));
		Assert.assertEquals(MobileData.SortOption3, sel.getText(MobileObjects.MobileWikisSortOption3));

		cautiousClick(MobileObjects.MobileWikisSort);

		//Now verify the menu options
		clickLink(MobileObjects.MobileWikisMenu);

		Assert.assertEquals(MobileData.MenuOption2, sel.getText(MobileObjects.MobileWikisMenuOption2));
		Assert.assertEquals(MobileData.MenuOption1, sel.getText(MobileObjects.MobileWikisMenuOption1));

		cautiousClick(MobileObjects.MobileWikisMenuOption1);

		Assert.assertEquals(MobileData.MenuOption1, sel.getText(MobileObjects.MobileWikisViewTitle));

		//now switch back to the public wikis view
		clickLink(MobileObjects.MobileWikisMenu);
		clickLink(MobileObjects.MobileWikisMenuOption2);

		Assert.assertEquals(MobileData.MenuOption2, sel.getText(MobileObjects.MobileWikisViewTitle));

	}

}
