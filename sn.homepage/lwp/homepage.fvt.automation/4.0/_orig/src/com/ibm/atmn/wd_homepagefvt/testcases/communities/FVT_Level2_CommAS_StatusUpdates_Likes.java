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

package com.ibm.atmn.wd_homepagefvt.testcases.communities;



import static org.testng.AssertJUnit.*;


import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.wdbase.core.data.User;


public class FVT_Level2_CommAS_StatusUpdates_Likes extends FVT_CommunitiesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	
	private static String StatusUpdate = "";
	private static String PublicCommunity = "";
	private static String PublicCommunity2 = "";
	
	@Test
	public void testUser1UpdateStatusAndLike() throws Exception {

		System.out.println("INFO: Start of News Level 2 testUser1UpdateStatusAndLike");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
		Login(testUser1);
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, testUser2.getDisplayName(), FVT_CommunitiesObjects.fullUserSearchIdentifier);

		clickLink("link=Recent Updates");
		
		//Add a status update
		StatusUpdate = StatusUpdate();
		
		//Verify that the update posted corectly
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//click on status update to display EE
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+StatusUpdate+")").click();
		
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

		System.out.println("INFO: End of News Level 2 testUser1UpdateStatusAndLike");
	}

	@Test
	public void testUser2Like() throws Exception {

		System.out.println("INFO: Start of News Level 2 testUser2Like");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		//testUser2 = userAllocator.getUser();
		Login(testUser2);
		
		clickLink("link=I'm a Member");
		
		clickLink("link="+ PublicCommunity);
		
		clickLink("link=Recent Updates");
		
		//Click on status update to display EE
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+StatusUpdate+")").click();
		
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
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		testUser3 = userAllocator.getUser();
		testUser4 = userAllocator.getUser();
		Login(testUser3);
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		PublicCommunity2 = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, testUser4.getDisplayName(), FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		clickLink("link=Recent Updates");
		
		//Add a status update
		StatusUpdate = StatusUpdate();

		//Verify that the update posted corectly
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Click on status update to display EE
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+StatusUpdate+")").click();
		
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
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+StatusUpdate+")").click();
		
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
