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


import java.util.Random;

import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;
import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.waffle.extensions.user.User;


public class FVT_GlobalSharebox extends FVT_ProfilesMethods {
	
	User testUser1 = userAllocator.getUser();
	User testUser2 = userAllocator.getUser();	
	
	private static String statusUpdate = "";
	
	//Create Array of Users
	private static String [][] users = new String [][]{ {"Combo .@2", "Pr0bl3m()* Combo .@2","Combo .@2"},
													    //{"C{fvt}Bracket", "Curly{fvt} Bracket","bracket"},
														{"Us&r", "Ampers&nd R&D Us&r","Us&r"},
													    //{"p%Sign%", "%Percent% %Sign%","%Sign%"},
														{"Face :-D", "Smiley :-) Face :-D","face :-D"},
														{"GÂççèÑtêë[Ú]üsêër", "GriffiÑ ÂççèÑtêë[Ú]üsêër <GriffiÑ ÂççèÑtêë[Ú]üsêër","GriffiÑ ÂççèÑtêë[Ú]üsêër"},
													    //{"OUser", "A'postrophe OUser","OUser"},
														{"ìíîïðñòóôõöøùúûüýþÿ", "ÂÃÄÀÁÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞßàáâãäåæçèéêë ìíîïðñòóôõöøùúûüýþÿ","ìíîïðñòóôõöøùúûüýþÿ"}};
	
	
	@Test
	public void FVT_GlobalSharebox_PeoplePicker_020 () throws Exception {
		
		System.out.println("INFO: Start of Global Sharebox 020");
		
		//Load Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(testUser1);		
		
		//Click on global sharebox
		clickLink(FVT_NewsObjects.Share);
		
		// Needed to switch frame focus to share-box frame
		switchToEE();
		
		//Select profile from dropdown menu
		driver.getSingleElement(FVT_NewsObjects.GlobalSharebox_DropdownMenu).useAsDropdown().selectOptionByVisibleText("a Person's profile");
		
		//Type users name
		driver.getFirstElement(FVT_NewsObjects.PeoplePicker).type(testUser2.getDisplayName());
		
		//Verify that name appears in type ahead
		assertTrue("Fail - User name did not appear in type ahead", driver.isTextPresent(testUser2.getEmail()));
		
		sleep(1000);
		
		//type users name and select from people picker dropdown
		selectUser_peoplePicker(testUser2);
		
		//create status update message
		statusUpdate = FVT_NewsData.UpdateStatus;
		
		//Enter message
		driver.getFirstElement(FVT_NewsObjects.PeoplePickerStatus).type(statusUpdate);
		
		//Click on post button
		clickLink(FVT_NewsObjects.PeoplePickerPost);
		
		//Switch focus from EE
		switchFromEE();
		
		//verify message posted successfully
		assertTrue("Fail - message not successfully posted", isTextPresent("The message was successfully posted"));
		
		//Logout
		LogoutAndClose();
		
		//Load Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(testUser2);
		
		//Click on ImFollowing
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		// verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate,"Status Updates", "People", null);
		
		//Click on Status Updates
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		assertTrue("Fail - NewsStory not visible", isTextPresent(testUser1.getDisplayName()+" "+statusUpdate));
		
		//Click on Notifications and verify under different views
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterByFor("For Me");
		
		// verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate,"Profiles", null, null);
		
		
		//Click on discover and verify under different views
		clickLink(FVT_HomepageObjects.Discover);
		
		// verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate,"Status Updates", "Profiles", null);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of FVT Global Sharebox test 020");

		
	} 
	
	@Test (dependsOnMethods = { "FVT_GlobalSharebox_PeoplePicker_020" })
	public void FVT_GlobalSharebox_Profile_Activity_NewsStream_021 () throws Exception {
		
		System.out.println("INFO: Start of GlobalSharebox_Profile_Activity_NewsStream_021 ");
		
		//Load Component
		LoadComponent(CommonObjects.ComponentProfiles);
		
		//Login
		Login(testUser2);
		
		// verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate,"Status Updates", "Profiles", null);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of GlobalSharebox_Profile_Activity_NewsStream_021 ");
		
	} 
	
	@Test (dependsOnMethods = { "FVT_GlobalSharebox_Profile_Activity_NewsStream_021" })
	public void FVT_GlobalSharebox_PeoplePicker_Special_Characters_023 () throws Exception {
		
		
		System.out.println("INFO: Start of Global Sharebox PeoplePicker_023_Special_Characters");
		
		//Random User Generator
		Random r = new Random();
		int getUser2 = r.nextInt(4);
		
		// Get values for user 2
		String User2Login = users[getUser2][0], User2Name = users[getUser2][1], User2Password = users[getUser2][2];
		
		//Load Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(testUser1);		

		//Click on global sharebox
		driver.getFirstElement(FVT_NewsObjects.Share).click();
		
		// Needed to switch frame focus to share-box frame
		switchToEE();
		
		//Select profile from dropdown menu
		driver.getSingleElement(FVT_NewsObjects.GlobalSharebox_DropdownMenu).useAsDropdown().selectOptionByVisibleText("a Person's profile");
		
		//Type users name
		driver.getFirstElement(FVT_NewsObjects.PeoplePicker).type(User2Name);
		
		sleep(1000);
		
		//type users name and select from people picker dropdown
		selectUser_peoplePicker(User2Name);
		
		//create status update message
		statusUpdate = FVT_NewsData.UpdateStatus;
		
		//Enter message
		driver.getFirstElement(FVT_NewsObjects.PeoplePickerStatus).type(statusUpdate);
		
		//Click on post button
		clickLink(FVT_NewsObjects.PeoplePickerPost);
		
		//Switch focus from EE
		switchFromEE();
		
		//verify message posted successfully
		assertTrue("Fail - message not successfully posted", isTextPresent("The message was successfully posted"));
		
		//Logout
		LogoutAndClose();
		
		//Load Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(User2Login, User2Password);
		
		//Click on ImFollowing
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		// verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate,"Status Updates", "People", null);
		
		//Click on Status Updates and verify under different views
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		assertTrue("Fail - NewsStory not visible", isTextPresent(testUser1.getDisplayName()+" "+statusUpdate));
		
		//Click on Notifications
		clickLink(FVT_HomepageObjects.MyNotifications);

		filterByFor("For Me");
		
		// verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate, "Profiles", null, null);
		
		//Click on discover
		clickLink(FVT_HomepageObjects.Discover);
		
		// verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate,"Status Updates", "Profiles", null);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Global Sharebox PeoplePicker_023_Special_Characters");

		
	} 
	
	@Test (dependsOnMethods = { "FVT_GlobalSharebox_PeoplePicker_Special_Characters_023" })
	public void FVT_GlobalSharebox_PeoplePicker_Special_Characters_024() throws Exception {
		
		System.out.println("INFO: Start of Global Sharebox PeoplePicker_024_Special_Characters");
		
		//Random User Generator
		Random r = new Random();
		int getUser1 = r.nextInt(4);
		
		// Get values for user 2
		String User1Login = users[getUser1][0], User1Name = users[getUser1][1], User1Password = users[getUser1][2];
		
		//Load Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(User1Login, User1Password);		

		//Click on global sharebox
		driver.getFirstElement(FVT_NewsObjects.Share).click();

		// Needed to switch frame focus to share-box frame
		switchToEE();
		
		//Select profile from dropdown menu
		driver.getSingleElement(FVT_NewsObjects.GlobalSharebox_DropdownMenu).useAsDropdown().selectOptionByVisibleText("a Person's profile");
		
		//Type users name
		driver.getFirstElement(FVT_NewsObjects.PeoplePicker).type(testUser2.getDisplayName());
		
		sleep(1000);
		
		//type users name and select from people picker dropdown
		selectUser_peoplePicker(testUser2.getDisplayName());
		
		//create status update message
		statusUpdate = FVT_NewsData.UpdateStatus;
		
		//Enter message
		driver.getFirstElement(FVT_NewsObjects.PeoplePickerStatus).type(statusUpdate);
		
		//Click on post button
		clickLink(FVT_NewsObjects.PeoplePickerPost);
		
		sleep(500);
		
		//Switch focus from EE
		switchFromEE();
		
		//verify message posted successfully
		assertTrue("Fail - message not successfully posted", isTextPresent("The message was successfully posted"));
		
		//Logout
		LogoutAndClose();
		
		//Load Component
		LoadComponent(CommonObjects.ComponentProfiles);
		
		//Login
		Login(testUser2);
		
		//Click on ImFollowing 
		System.out.println(User1Name+" "+statusUpdate);
		
		//verify under different views
		multiFilter(User1Login+" "+statusUpdate,"Status Updates", "Profiles", null);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Global Sharebox PeoplePicker_024_Special_Characters");

		
	}
	
	@Test
	public void FVT_GlobalSharebox_PeoplePicker_025 () throws Exception {
		
		System.out.println("INFO: Start of Global Sharebox 025");
		
		//Load Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(testUser1);		
		
		//Click on global sharebox
		clickLink(FVT_NewsObjects.Share);
		
		// Needed to switch frame focus to share-box frame
		switchToEE();
		
		//Select profile from dropdown menu
		driver.getSingleElement(FVT_NewsObjects.GlobalSharebox_DropdownMenu).useAsDropdown().selectOptionByVisibleText("a Person's profile");
		
		//Type users name
		driver.getFirstElement(FVT_NewsObjects.PeoplePicker).type(testUser2.getDisplayName());
		
		//Verify that name appears in type ahead
		assertTrue("Fail - User name did not appear in type ahead", driver.isTextPresent(testUser2.getEmail()));
		
		sleep(1000);
		
		//type users name and select from people picker dropdown
		selectUser_peoplePicker(testUser2);
		
		//create status update message
		statusUpdate = testUser1.getDisplayName()+" ~`!@#$%^&*()-=[];',./?><"+":|}{"+"+"+"_" + CommonMethods.genDateBasedRandVal();
		
		//Enter message
		driver.getFirstElement(FVT_NewsObjects.PeoplePickerStatus).type(statusUpdate);
		
		//Click on post button
		clickLink(FVT_NewsObjects.PeoplePickerPost);
		
		//Switch focus from EE
		switchFromEE();
		
		//verify message posted successfully
		assertTrue("Fail - message not successfully posted", isTextPresent("The message was successfully posted"));
		
		//Logout
		LogoutAndClose();
		
		//Load Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(testUser2);
		
		//Click on ImFollowing		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		//Verify under different views
		multiFilter(statusUpdate,"Status Updates", "People", null);
		
		//Click on Status Updates and verify under different views
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		//Verify under different views
		assertTrue("Fail - NewsStory not visible", isTextPresent(testUser1.getDisplayName()+" "+statusUpdate));
		
		//Click on Notifications and verify under different views
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterByFor("For Me");
		
		//Verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate, "Profiles", null, null);
		
		//Click on discover and verify under different views
		clickLink(FVT_HomepageObjects.Discover);
		
		//Verify under different views
		multiFilter(testUser1.getDisplayName()+" "+statusUpdate, "Status Updates", "Profiles", null);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of FVT Global Sharebox test 025");

		
	} 

}
