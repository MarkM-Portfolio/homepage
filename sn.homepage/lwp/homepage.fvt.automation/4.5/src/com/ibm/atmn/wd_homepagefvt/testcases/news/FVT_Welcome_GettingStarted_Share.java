/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.news;

import static org.testng.AssertJUnit.assertTrue;

import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;

public class FVT_Welcome_GettingStarted_Share extends FVT_AtMentionsMethods{

	User testUser1;
	
	@Test
	public void Welcome_GettingStarted_Share_020_021_022 () throws Exception {
		 
		testUser1 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of Welcome_GettingStarted_Share_020_021_022");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(testUser1);
		
		//Click share button
		clickLink("css=div#gettingStart_tablist_dijit_layout_ContentPane_1.dijitTabContent");
		
		//Click Request a document link
		clickLink("css=a#lconn_homepage_gettingStarted_actionLink_6_link");
		
		//verify text is present
		assertTrue("Fail - Text not present ",driver.isTextPresent("Use a library to store, organize, collaborate on, and share community files."));
		
		//Click on go to communities link
		clickLink("css=div#dijit_layout_ContentPane_1.dijitContentPane div.lotusContent table.lotusLayout tbody tr td.shareComponentContainer div#suggestionTextContainerId.dijitContainer div#library-pane.dijitContentPane div.gettingStartedComponentLinks table tbody tr td div.shareComponentInfoValue a");
		
		//Verify user lands on community page
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Welcome_GettingStarted_Share_020_021_022");	
		
	}
	
		@Test (dependsOnMethods = { "Welcome_GettingStarted_Share_020_021_022" })
		public void Welcome_GettingStarted_Explore_024_025 () throws Exception {

			
			System.out.println("INFO: Start of Welcome_GettingStarted_Explore_024_025");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentHomepage);
			
			//Login
			Login(testUser1);
			
			//Click share button
			clickLink("css=div#gettingStart_tablist_dijit_layout_ContentPane_2.dijitTabContent");
			
			//Click Request a document link
			clickLink("css=a#lconn_homepage_gettingStarted_actionLink_4_link");
			
			assertTrue("Fail - Communities Demo not present ",driver.isTextPresent("View Communities Demo"));
			
			//verify text is present
			assertTrue("Fail - Library Demo not present ",driver.isTextPresent("View Library Demo"));
			
			
			//Check here that correct video opens - currently videos not running correctly in 4.5
			
			//Logout
			LogoutAndClose();
			
			System.out.println("INFO: End of Welcome_GettingStarted_Explore_024_025");	
			
			
		}	
}