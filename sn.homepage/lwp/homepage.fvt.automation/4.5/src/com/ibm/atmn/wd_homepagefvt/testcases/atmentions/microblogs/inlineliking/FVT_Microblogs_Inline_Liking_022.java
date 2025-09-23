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

package com.ibm.atmn.wd_homepagefvt.testcases.atmentions.microblogs.inlineliking;

import static org.testng.AssertJUnit.assertTrue;

import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;


public class FVT_Microblogs_Inline_Liking_022 extends FVT_AtMentionsMethods{

	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	User testUser5;
	User testUser6;
	
	@Test
	public void Inline_Liking_20_Message_Directed_In_Status_Update () throws Exception {
		 
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		testUser4 = userAllocator.getUser(this);
		testUser5 = userAllocator.getUser(this);
		testUser6 = userAllocator.getUser(this);
		 		
		System.out.println("INFO: Start of test Microblogs_Inline_Liking_022_Number_of_likes_Board_Message");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as user 1
		Login(testUser1);			
				
		//Search for User
		searchUser(testUser2);
	
		//Click on the Status updates
		driver.getFirstElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(FVT_NewsData.UpdateStatus);
		
		//Click post
		clickLink(FVT_AtMentionsObjects.Post);
		
		sleep(2000);
		
		//Like the status
		driver.getFirstElement("link=Like").click();
		
		//Logout
		LogoutAndClose();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
		
		//Like status with user2
		//Login
		Login(testUser2);
		
		//Like the status
		driver.getFirstElement("link=Like").click();
		
		//logout
		LogoutAndClose();
		
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Like status with user3
		//Login
		Login(testUser3);
		
		//Search for User
		searchUser(testUser2);
		
		//Like the status
		driver.getFirstElement("link=Like").click();
		
		//logout
		LogoutAndClose();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		
		//Like status with user4
		//Login
		Login(testUser4);
		
		//Search for User
		searchUser(testUser2);
		
		//Like the status
		driver.getFirstElement("link=Like").click();
		
		//logout
		LogoutAndClose();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		
		//Like status with user5
		//Login
		Login(testUser5);
		
		//Search for User
		searchUser(testUser2);
		
		//Like the status
		driver.getFirstElement("link=Like").click();

		//logout
		LogoutAndClose();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(testUser6);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Profiles");
		
		//Verify status has been liked 4 times
		//Check that Likes = 4
		assertTrue("FAIL: Number of likes not equal to '5'", driver.getFirstElement(FVT_NewsObjects.Likes).getText().compareToIgnoreCase("5") == 0);
		
		//logout
		LogoutAndClose();
		
		System.out.println("INFO: End of test Microblogs_Inline_Liking_022_Number_of_likes_Board_Message");	
		
	}
}
