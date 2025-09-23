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

package com.ibm.atmn.wd_homepagefvt.testcases.profiles.discover;



import static org.testng.AssertJUnit.*;


import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;
import com.ibm.atmn.waffle.extensions.user.User;


public class FVT_Discover_BoardMessage_Likes extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	
	private static String BoardMessage = "";
	
	@Test
	public void testUser1CreateBoardMessageAndLike() throws Exception {

		System.out.println("INFO: Start of News Level 2 testUser1CreateBoardMessageAndLike");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
		Login(testUser1);
		
		//Add a board message
		BoardMessage = AddBoardEntry_OtherWall(testUser2.getDisplayName());

		//Verify that the update posted corectly
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		assertTrue("FAIL: Board Message not appearing in Activty Stream", driver.isTextPresent(BoardMessage));
		
		//click on status update to display EE
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+BoardMessage+")").click();
		
		//verify that EE id displayed
		assertTrue("FAIL: EE not visible", driver.isElementPresent(FVT_NewsObjects.EE_Header));
	
		//Swtich to Embedded Experience
		switchToEE();
		
		//Click on the Like button
		Like();
		
		//Check that Likes = 1
		assertTrue("FAIL: Number of likes not equal to '1'", driver.getSingleElement(FVT_NewsObjects.Likes).getText().compareToIgnoreCase("1") == 0);
		
		//Swtich from Embedded Experience
		switchFromEE();
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testUser1CreateBoardMessageAndLike");
	}

	@Test
	public void testUser2Like() throws Exception {

		System.out.println("INFO: Start of News Level 2 testUser2Like");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		//testUser2 = userAllocator.getUser();
		Login(testUser2);
		
		//Click on status update to display EE
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+BoardMessage+")").click();
		
		//verify that EE id displayed
		assertTrue("FAIL: EE not visible", driver.isElementPresent(FVT_NewsObjects.EE_Header));
		
		//Swtich to Embedded Experience
		switchToEE();
		
		//Click on the Like button
		Like();
		
		//Check that Likes = 1
		assertTrue("FAIL: Number of likes not equal to '2'", driver.getSingleElement(FVT_NewsObjects.Likes).getText().compareToIgnoreCase("2") == 0);
		
		//Swtich from Embedded Experience
		switchFromEE();
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testUser2Like");
	}
	
	
	
	@Test
	public void testRemoveLike() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRemoveLike");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		testUser3 = userAllocator.getUser();
		testUser4 = userAllocator.getUser();
		Login(testUser3);
		
		//Add a board message
		BoardMessage = AddBoardEntry_OtherWall(testUser4.getDisplayName());

		//Verify that the update posted corectly
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Click on status update to display EE
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+BoardMessage+")").click();
		
		//verify that EE id displayed
		assertTrue("FAIL: EE not visible", driver.isElementPresent(FVT_NewsObjects.EE_Header));
		
		//Swtich to Embedded Experience
		switchToEE();
		
		//Click on the Like button
		Like();
		
		//Check that Likes = 1
		assertTrue("FAIL: Number of likes not equal to '1'", driver.getSingleElement(FVT_NewsObjects.Likes).getText().compareToIgnoreCase("1") == 0);
		
		//Swtich from Embedded Experience
		switchFromEE();
		
		clickLink(FVT_NewsObjects.EE_Close);
		
		//Click on status update to display EE
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+BoardMessage+")").click();
		
		//Swtich to Embedded Experience
		switchToEE();
		
		//Unlike status update
		Unlike();
	
		//Check that no likies are showing
		assertTrue("FAIL: There is still likes!",driver.isElementPresent(FVT_NewsObjects.No_Likes));

		//Swtich from Embedded Experience
		switchFromEE();
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testRemoveLike");
	}
	
	
	
}
