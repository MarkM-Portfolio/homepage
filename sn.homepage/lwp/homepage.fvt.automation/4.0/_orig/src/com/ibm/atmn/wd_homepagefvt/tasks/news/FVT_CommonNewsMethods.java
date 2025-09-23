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

package com.ibm.atmn.wd_homepagefvt.tasks.news;

import static org.testng.AssertJUnit.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.NewsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wdbase.core.data.User;

public class FVT_CommonNewsMethods extends CommonMethods{

	/**Click specified link*/
	/*public void clickLink(String Link) throws Exception{
		//Click the link provided
		selenium.click(Link);
		Thread.sleep(3500);
	}*/
	
	private String UpdateStatus;
	
	public FVT_CommonNewsMethods() {

		UpdateStatus = stamp(FVT_NewsData.UpdateStatus);

	}
	
	public void addBoardEntry() throws Exception{
		//Add an entry to the board
		driver.getSingleElement(NewsObjects.ProfilesBoard).type(FVT_ProfilesData.ProfilesBoardEntryNewsBVT);
		clickLink(NewsObjects.PostStatus);
	}
		
	public void navigateHomepage(String NewsStory, String NavOption, String component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		
		if (NavOption=="I'm following"){
			//Click on the Discover link
			clickLink("link=I'm following");
		}
		else if (NavOption=="Action Required"){
			//Click on the Discover link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Discover link
			clickLink("link=Saved");
			}
		else if (NavOption=="For me"){
			//Click on the Discover link
			clickLink("link=For me");
			}
		else if (NavOption=="Discover"){
		//Click on the Discover link
		clickLink("link=Discover");
		}
			
		
		//Filter by component from Drop-down
		filterBy(component);
	
		//Logout
		Logout();
		
		
				
	}
	
	public void verifyNewsStory_SameUser(String NewsStory, String NavOption, String component, boolean Visible) throws Exception{
	
		//Go to homepage
		clickLink(FVT_HomepageObjects.Home);
		
		
		
		if (NavOption=="All Updates"){
			//Click on the All Updates link
			clickLink("link=All Updates");
		}
		else if (NavOption=="Action Required"){
			//Click on the Action Required link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Saved link
			clickLink("link=Saved");
			}
		else if (NavOption=="My Notifications"){
			//Click on the My Notifications link
			clickLink("link=My Notifications");
			}
		else if (NavOption=="Discover"){
			//Click on the Discover link
			clickLink("link=Discover");
		}
		else if (NavOption=="I'm Following"){
			//Click on the I'm Following link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Status Updates"){
			//Click on the Status Updates link
			clickLink("link=Status Updates");
		}
			
		
		//Filter by component from Drop-down
		filterBy(component);
		
		Thread.sleep(2000);
		
		if(Visible==false){
			//Verify that the news story is there
			if(driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
			}
			//	Assert.assertFalse(sel.isTextPresent(NewsStory));	
			//}
		else{
			
			if(!driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
				//Verify that the news story is there
				//Assert.assertTrue(sel.isTextPresent(NewsStory));	
		}

		//Logout
		Logout();
		
		
				
	}
	
	public void verifyNewsStory(String NewsStory, String NavOption, String component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		
		if (NavOption=="All Updates"){
			//Click on the All Updates link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Action Required"){
			//Click on the Action Required link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Saved link
			clickLink("link=Saved");
			}
		else if (NavOption=="My Notifications"){
			//Click on the My Notifications link
			clickLink("link=My Notifications");
			}
		else if (NavOption=="Discover"){
			//Click on the Discover link
			clickLink("link=Discover");
		}
		else if (NavOption=="I'm Following"){
			//Click on the I'm Following link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Status Updates"){
			//Click on the Status Updates link
			clickLink("link=Status Updates");
		}
			
		
		//Filter by component from Drop-down
		filterBy(component);
		
		Thread.sleep(2000);
		
		System.out.println(NewsStory);
		
		if(Visible==false){
			//Verify that the news story is there
			if(driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
			}
			
		else{
			
			if(!driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
				
		}

		//Logout
		Logout();
		
		
				
	}
	
	public void verifyFirstNewsStory(String NewsStory, String NavOption, String component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		
		if (NavOption=="All Updates"){
			//Click on the All Updates link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Action Required"){
			//Click on the Action Required link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Saved link
			clickLink("link=Saved");
			}
		else if (NavOption=="My Notifications"){
			//Click on the My Notifications link
			clickLink("link=My Notifications");
			}
		else if (NavOption=="Discover"){
			//Click on the Discover link
			clickLink("link=Discover");
		}
		else if (NavOption=="I'm Following"){
			//Click on the I'm Following link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Status Updates"){
			//Click on the Status Updates link
			clickLink("link=Status Updates");
		}
			
		
		//Filter by component from Drop-down
		filterBy(component);
		
		Thread.sleep(2000);
		
		NewsStory = FVT_NewsObjects.FirstNewsItem+":contains('"+NewsStory+"')";
		
		if(Visible==false){
			//Verify that the news story is there
			if(driver.isElementPresent(NewsStory)){
				Assert.fail("fail");
			}
			}
			
		else{
			
			if(!driver.isElementPresent(NewsStory)){
				Assert.fail("fail");
			}
				
		}

		//Logout
		Logout();
		
		
				
	}
	
	
	public void verifyNewsStory_TwoFilters(String Username, String Userpass, String NewsStory, String NavOption, String FirstComponent,String SecondComponent, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(Username, Userpass);
		
		
		if (NavOption=="All Updates"){
			//Click on the All Updates link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Action Required"){
			//Click on the Action Required link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Saved link
			clickLink("link=Saved");
			}
		else if (NavOption=="My Notifications"){
			//Click on the My Notifications link
			clickLink("link=My Notifications");
			}
		else if (NavOption=="Discover"){
			//Click on the Discover link
			clickLink("link=Discover");
		}
		else if (NavOption=="I'm Following"){
			//Click on the I'm Following link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Status Updates"){
			//Click on the Status Updates link
			clickLink("link=Status Updates");
		}
			
		String[] Filters =  new String[]{FirstComponent, SecondComponent};
		
		List<String> ListFilters = Arrays.asList(Filters);
		
		Iterator itr= ListFilters.iterator();
		
		while(itr.hasNext()){
			
			filterBy((String) itr.next());
			Thread.sleep(2000);
						
			if(Visible==false){
				//Verify that the news story is there
				if(driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
				}
				
			else{
				
				if(!driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
					
			}
		}
	

		//Logout
		Logout();
		
		
				
	}	
	
	public void verifyNewsStory_ThreeFilters(String NewsStory, String NavOption, String FirstComponent,String SecondComponent,String ThirdComponent, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		
		if (NavOption=="All Updates"){
			//Click on the All Updates link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Action Required"){
			//Click on the Action Required link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Saved link
			clickLink("link=Saved");
			}
		else if (NavOption=="My Notifications"){
			//Click on the My Notifications link
			clickLink("link=My Notifications");
			}
		else if (NavOption=="Discover"){
			//Click on the Discover link
			clickLink("link=Discover");
		}
		else if (NavOption=="I'm Following"){
			//Click on the I'm Following link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Status Updates"){
			//Click on the Status Updates link
			clickLink("link=Status Updates");
		}
			
		String[] Filters =  new String[]{FirstComponent, SecondComponent, ThirdComponent};
		
		List<String> ListFilters = Arrays.asList(Filters);
		
		Iterator itr= ListFilters.iterator();
		
		while(itr.hasNext()){
			
			filterBy((String) itr.next());
			Thread.sleep(2000);
						
			if(Visible==false){
				//Verify that the news story is there
				if(driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
				}
				
			else{
				
				if(!driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
					
			}
		}
	

		//Logout
		Logout();
		
		
				
	}	

	
	
	public void verifyNewsStory(String Username, String Userpass, String NewsStory, String NavOption, String component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(Username, Userpass);
		
		if (NavOption=="All Updates"){
			//Click on the All Updates link
			clickLink("link=All Updates");
		}
		else if (NavOption=="Action Required"){
			//Click on the Action Required link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Saved link
			clickLink("link=Saved");
			}
		else if (NavOption=="My Notifications"){
			//Click on the My Notifications link
			clickLink("link=My Notifications");
			}
		else if (NavOption=="Discover"){
			//Click on the Discover link
			clickLink("link=Discover");
		}
		else if (NavOption=="I'm Following"){
			//Click on the I'm Following link
			clickLink("link=I'm Following");
		}
		else if (NavOption=="Status Updates"){
			//Click on the Status Updates link
			clickLink("link=Status Updates");
		}
			
		
		//Filter by component from Drop-down
		filterBy(component);
		
		Thread.sleep(2000);
		
		if(Visible==false){
			//Verify that the news story is there
			if(driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
			}
			//	Assert.assertFalse(sel.isTextPresent(NewsStory));	
			//}
		else{
			
			if(!driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
				//Verify that the news story is there
				//Assert.assertTrue(sel.isTextPresent(NewsStory));	
		}

		//Logout
		Logout();
		
		
	}
	
	public void verifyImFollowingNewsStory(String NewsStory, String component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		Thread.sleep(500);
		
		clickLink("link=I'm Following");
		
	
		String[] Filters =  new String[]{"Communities", component};
		
		List<String> ListFilters = Arrays.asList(Filters);
		
		Iterator itr= ListFilters.iterator();
		
		while(itr.hasNext()){
			
			filterBy((String) itr.next());
			
			Thread.sleep(2000);
						
			if(Visible==false){
				//Verify that the news story is there
				if(driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
				}
				
			else{
				
				if(!driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
					
			}
		}

		//Logout
		Logout();
		
		
				
	}
	
	public void verifyImFollowingNewsStory(String Username, String Userpass, String NewsStory, String component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(Username, Userpass);
		
		Thread.sleep(500);
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		String[] Filters =  new String[]{"Communities", component};
		
		List<String> ListFilters = Arrays.asList(Filters);
		
		Iterator itr= ListFilters.iterator();
		
		while(itr.hasNext()){
			
			filterBy((String) itr.next());
			
			Thread.sleep(2000);
						
			if(Visible==false){
				//Verify that the news story is there
				if(driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
				}
				
			else{
				
				if(!driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
					
			}
		}

		//Logout
		Logout();
		
		
				
	}
	
	public void verifyImFollowingTagsNewsStory(String NewsStory, String component, String Tagname, boolean Community, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		//Filter by Tags from Drop-down
		filterBy("Tags");
		
		String[] Filters =  new String[]{"All Tags", Tagname};
		
		List<String> ListFilters = Arrays.asList(Filters);
		
		Iterator itr= ListFilters.iterator();
		
		while(itr.hasNext()){
			
			filterByTag((String) itr.next());
			
			Thread.sleep(2000);
						
			if(Visible==false){
				//Verify that the news story is there
				if(driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
				}
				
			else{
				
				if(!driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
					
			}
		}
	
		
		//Filter by component from Drop-down
		filterBy(component);
		
		Thread.sleep(2000);
		
		if(Visible==false){
			//Verify that the news story is there
			if(driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
			}
			
		else{
			
			if(!driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
		}
		
		
		//If it is a community tags story check communities dropdown
		if(Community==true){
			
			
			//Filter by communities from Drop-down
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			if(Visible==false){
				//Verify that the news story is there
				if(driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
				}
				
			else{
				
				if(!driver.isTextPresent(NewsStory)){
					Assert.fail("fail");
				}
				
			}
		}

		//Logout
		Logout();
		
		
				
	}
	
	
	public void verifyMyNotificationsNewsStory(String UserName, String UserPass, String NewsStory, String FilterByFor, String Component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(UserName, UserPass);
			
		//Click on the My Notifications link
		clickLink("link=My Notifications");
	
		//Filter by component from Drop-down
		filterBy(Component);
		
		filterByFor(FilterByFor);
		
		Thread.sleep(3000);
				
		System.out.println(NewsStory);
		
		if(Visible==false){
			//Verify that the news story is there
			if(driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
			}
			
		else{
			
			if(!driver.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
				
		}

		//Logout
		Logout();
		
		
				
	}
	
	
	public void filterBy(String component) throws Exception{
	
		// if else statements that selects component from "filter by" drop-down menu
		if(component=="All"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterAll);
		}
		else if(component=="Status Updates"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterStatusUpdates);
		}
		else if(component=="Activities"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterActivities);	
		}
		else if(component=="Blogs"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterBlogs);
		}
		else if(component=="Bookmarks"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterBookmarks);
		}
		else if(component=="Communities"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterCommunities);
		}
		else if(component=="Files"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterFiles);
		}
		else if(component=="Forums"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterForums);
		}
		else if(component=="People"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterPeople);
		}
		else if(component=="Profiles"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterProfiles);
		}
		else if(component=="Tags"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterTags);
		}
		else if(component=="Wikis"){
			driver.getFirstElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterWikis);
		}
		
	}
		
	public void filterByTag(String Tagname) throws Exception{
			
			driver.getSingleElement(FVT_NewsObjects.FilterByTag).useAsDropdown().selectOptionByVisibleText(Tagname);
		
	}
	
	public void filterByFor(String FilterFor) throws Exception{
		
		driver.getSingleElement(FVT_NewsObjects.FilterByFor).useAsDropdown().selectOptionByVisibleText(FilterFor);
	
}
	
	public void filterBy_StatusUpdates(String component) throws Exception{
		
		// if else statements that selects component from "filter by" drop-down menu
		if(component=="All"){
			driver.getSingleElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.FilterAll);
		}
		else if(component=="People"){
			driver.getSingleElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.PeopleIFollow);
		}
		else if(component=="Communities"){
			driver.getSingleElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.CommunitiesIFollow);	
		}
		else if(component=="My Network"){
			driver.getSingleElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.MyNetwork);
		}
		else if(component=="My Network and People I Follow"){
			driver.getSingleElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.MyNetworkAndPeopleIFollow);
		}
		else if(component=="My Updates"){
			driver.getSingleElement(FVT_NewsObjects.FilterBy).useAsDropdown().selectOptionByVisibleText(FVT_NewsObjects.MyUpdates);
		}	
		
	}
	
	public String statusUpdate() throws Exception{
		
		
		//Click on the Status Updates link to open the Status Updates page
		clickLink(FVT_NewsObjects.StatusUpdates);

		//Enter and Save a status update
		driver.getSingleElement(FVT_NewsObjects.EnterStatusUpdate).type(UpdateStatus);

		//clickLink(Objects.SaveStatusUpdate);
		FormSaveButton(FVT_NewsObjects.PostStatus);
		
		Thread.sleep(1000);
		
		//Verify that the update posted corectly
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		clickLink(FVT_HomepageObjects.Refresh_StatusUpdates);
		
		return UpdateStatus;
			
	}
	
	
	public void verifyStatusUpdatesViews() throws Exception{
		

	assertTrue("FAIL: Status Updates link missing!", driver.isTextPresent("Status Updates"));
		
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Status Updates link missing!", driver.isTextPresent("All"));
		
		filterBy_StatusUpdates("People");
		
		assertTrue("FAIL: Status Updates link missing!", driver.isTextPresent("People I Follow"));
		
		filterBy_StatusUpdates("Communities");
		
		assertTrue("FAIL: Status Updates link missing!", driver.isTextPresent("Communities I Follow"));
		
		filterBy_StatusUpdates("My Network");
		
		assertTrue("FAIL: Status Updates link missing!", driver.isTextPresent("My Network"));
		
		filterBy_StatusUpdates("My Network and People");
		
		assertTrue("FAIL: Status Updates link missing!", driver.isTextPresent("My Network and People I Follow"));
		
		filterBy_StatusUpdates("My Updates");
		
		assertTrue("FAIL: Status Updates link missing!", driver.isTextPresent("My Updates"));
			
	}
	
	public void openStatusUpdates() throws Exception{
		

		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
			
	}
	
	public String statusUpdate(String StatusUpdate) throws Exception{
		

		//Enter and Save a status update
		driver.getSingleElement(FVT_NewsObjects.EnterStatusUpdate).type(StatusUpdate);

		//clickLink(Objects.SaveStatusUpdate);
		FormSaveButton(FVT_NewsObjects.PostStatus);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
	
		sleep(2000);
		
		clickLink(FVT_HomepageObjects.Refresh_StatusUpdates);
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		return StatusUpdate;
			
	}
	
	public String statusUpdate_FileAttached(String StatusUpdate, String FileName) throws Exception{
		
		
		//Click on the Status Updates link to open the Status Updates page
		//clickLink(FVT_NewsObjects.StatusUpdates);

		//Enter and Save a status update
		driver.getSingleElement(FVT_NewsObjects.EnterStatusUpdate).type(StatusUpdate);
		
		clickLink(FVT_NewsObjects.AttachAFile);
		
		clickLink(FVT_NewsObjects.MyComputer);
		
		//In File Upload dialog enter the name and path to the file to upload
		driver.getSingleElement(FVT_FilesObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileName));

		clickLink(FVT_NewsObjects.Ok);
		
		//clickLink(Objects.SaveStatusUpdate);
		FormSaveButton(FVT_NewsObjects.PostStatus);
		
		Thread.sleep(2000);
		
		clickLink(FVT_HomepageObjects.Refresh_StatusUpdates);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		return StatusUpdate;
			
	}
	
	public void statusUpdate_FourViews(String StatusUpdate) throws Exception{
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.AllUpdates);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		clickLink(FVT_NewsObjects.ActionRequired);
				
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));

		StatusUpdate = statusUpdate(StatusUpdate);
		
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		clickLink(FVT_NewsObjects.MyNotifications);
				
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));

		StatusUpdate = statusUpdate(StatusUpdate);
		
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		clickLink(FVT_NewsObjects.Discover);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		StatusUpdate = statusUpdate(StatusUpdate);
		
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
	
			
	}
	
	public String updateStatusCommunity(String StatusUpdate) throws Exception{
		

		//Enter and Save a status update
		driver.getSingleElement(FVT_NewsObjects.EnterStatusUpdateComm).type(StatusUpdate);

		//clickLink(Objects.SaveStatusUpdate);
		FormSaveButton(FVT_NewsObjects.PostStatus);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
	
		sleep(2000);
		
		//Verify that update appears in AS
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		return StatusUpdate;
			
	}
	
	public String CommentOnStatus_EE(String StatusUpdate) throws Exception {
		
		String StatusComment = FVT_NewsData.StatusComment;
		
		switchToEE();
		driver.getSingleElement(FVT_NewsObjects.EE_StatusComment).type(StatusComment);
		clickLink(FVT_NewsObjects.EE_SaveComment);
		switchFromEE();
		return StatusComment;

	}
	
	public String CommentOnStatus(String StatusUpdate) throws Exception {
		
		String StatusComment = FVT_NewsData.StatusComment;
		
		//Disable hover function 
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
			
		JavascriptExecutor js = (JavascriptExecutor) wd;
		
		js.executeScript("var as = dijit.byId('activityStream');var firstNewsItem = dojo.query('.lotusPost', as.newsFeedNode[0]);dojo.addClass(firstNewsItem, 'lotusPostHover');",(Object) null);
		
		Actions builder = new Actions(wd);
		
		String StatusUpdate_ = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		//assertTrue("Cant find status update element",driver.isElementPresent(StatusUpdate_));
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdate_).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.Comment).getBackingObject()).click().build().perform();
				
		//Enter and Save a status update
		
		driver.getFirstElement(FVT_NewsObjects.StatusComment).type(StatusComment);
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdate_).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.PostComment).getBackingObject()).click().build().perform();
		
		return StatusComment;
	

	}
	
	public String CommentOnStatus(String StatusUpdate,String StatusComment) throws Exception {
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
			
		//JavascriptExecutor js = (JavascriptExecutor) wd;
		
		//js.executeScript("var as = dijit.byId('activityStream');var firstNewsItem = dojo.query('.lotusPost', as.newsFeedNode[0]);dojo.addClass(firstNewsItem, 'lotusPostHover');",(Object) null);
		
		Actions builder = new Actions(wd);
		
		String StatusUpdate_ = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		//assertTrue("Cant find status update element",driver.isElementPresent(StatusUpdate_));
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdate_).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.Comment).getBackingObject()).click().build().perform();
		
		//builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdate_).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.Comment).getBackingObject()).click().moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.StatusComment).getBackingObject()).sendKeys(StatusComment).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.PostComment).getBackingObject()).click().build().perform();
		
		//Enter and Save a status update
		
		driver.getFirstElement(FVT_NewsObjects.StatusComment).type(StatusComment);
		
		//driver.getSingleElement(StatusUpdate_).leftMouseDown();
		
		//clickLink(FVT_NewsObjects.Comment);
		
		//driver.getFirstElement(FVT_NewsObjects.StatusComment).type(StatusComment);
		
		//driver.getSingleElement(StatusUpdate_).hover();
		
		//clickLink(FVT_NewsObjects.PostComment);
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdate_).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.PostComment).getBackingObject()).click().build().perform();

		//moveToElement((WebElement) driver.getSingleElement(FVT_NewsObjects.PostComment).getBackingObject()).click()
		
		return StatusComment;
	

	}
	
	
	public String CancelComment(String StatusUpdate) throws Exception {
		
		String StatusComment = FVT_NewsData.StatusComment;
		
		
		// Click on the Add a comment link for entry
		clickLink("link=Comment");
		
		//
		assertTrue("Fail: Link still there!!", driver.isElementPresent(FVT_NewsObjects.StatusComment));
		//
		assertTrue("Fail: Link still there!!", driver.isElementPresent(FVT_NewsObjects.PostComment));
		//
		assertTrue("Fail: Link still there!!", driver.isElementPresent(FVT_NewsObjects.CancelComment));
		
		//cautiousFocus(FVT_NewsObjects.StatusComment);
		//Thread.sleep(1000);
		//sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		//Thread.sleep(1000);
		//sel.keyPressNative(String.valueOf(KeyEvent.VK_F));
		//Thread.sleep(1000);
		//Enter and Save a status update
		driver.getSingleElement(FVT_NewsObjects.StatusComment).type(StatusComment);
		//cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate+"')");
		//cautiousFocus(FVT_NewsObjects.StatusComment);
		clickLink(FVT_NewsObjects.CancelComment);
		
		return StatusComment;
	

	}
	
	public void deleteComment(String StatusComment) throws Exception {
		
		
		// Delete comment
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		JavascriptExecutor js = (JavascriptExecutor) wd;
		
		js.executeScript("var as = dijit.byId('activityStream');var firstNewsItem = dojo.query('.lotusPost', as.newsFeedNode[0]);dojo.addClass(firstNewsItem, 'lotusPostHover');",(Object) null);
		
		Actions builder = new Actions(wd);
		
		String StatusComment2 = "css=div.lotusPostContent:contains('"+StatusComment+"')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusComment2).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteComment).getBackingObject()).click().build().perform();
		
		//builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteComment).getBackingObject()).click().build().perform();
		
		//clickLink(FVT_NewsObjects.DeleteComment);
		
		clickLink(FVT_NewsObjects.RemoveComment);

		

	}
	
	public void VerifyStatusUpdatedInDiscover() throws Exception{
		//Click on the Status Updates link to open the Status Updates page
		clickLink(NewsObjects.LeftNavDiscover);
		
		//Now verify that the update is displayed
		assertTrue("FAIL: the status update is not appearing as expected", driver.isTextPresent(CommonData.LDAP_FullUsername+"1"));
		assertTrue("FAIL: the status update is not appearing as expected", driver.isTextPresent(NewsData.UpdateStatus));
		
	}
	

	
	public void AddACommentToEntry() throws Exception {
		// Click on the Add a comment link for entry
		clickLink(NewsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		driver.getSingleElement(NewsObjects.BlogsCommentTextArea).type(NewsData.BlogsCommentText);

		// Submit comment
		clickLink(NewsObjects.BlogsCommentSubmit);

	}
	
	
	public void verifyActionRequiredBadgeNotVisible() throws Exception {


		//Go to All Updates
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is visible!",!driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());
		
		//Go to Status Updates
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is visible!",!driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());

		//Go to Saved
		clickLink(FVT_HomepageObjects.Saved);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is visible!",!driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());

		//Go to My Notifications
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is visible!",!driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());
		
		//Go to Discover
		clickLink(FVT_HomepageObjects.Discover);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is visible!",!driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());
	
		//Go to Action Required
		clickLink(FVT_NewsObjects.ActionRequired2);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is visible!",!driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());
		
		//Go to Getting Started
		clickLink(FVT_NewsObjects.GettingStarted);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is visible!",!driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());
		
		//Go to Widgets
		clickLink(FVT_NewsObjects.MyPage);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is visible!",!driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());

	}

	public void verifyActionRequiredBadgeVisible() throws Exception {

	
		//Go to Action Required
		clickLink(FVT_NewsObjects.ActionRequired2);
		
		//Check that action required badge is not displayed
		assertTrue("Action Required Badge is not visible!",driver.getSingleElement(FVT_NewsObjects.ActionRequiredBadge).isVisible());
		
		

	}
	
	public void switchToEE() throws Exception{
		//
		WebDriver wd = (WebDriver) driver.getBackingObject();
				
			wd.switchTo().frame(1);
		
		
	}
	
	public void switchFromEE() throws Exception{
		//
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		wd.switchTo().defaultContent();
		
	}
	
	public void Like() throws Exception{
		//
		clickLink(FVT_NewsObjects.Like);
	}
	
	public void Unlike() throws Exception{
		//
		clickLink(FVT_NewsObjects.Undo_Like);
	}
	
	public void SearchForUser(String User) throws Exception{
		/*
		 * Enter the Username in the search field and search for that user and 
		 * verify that the correct user is returned
		 */
		driver.getSingleElement(ProfilesObjects.ProfilesSearchForUser).type(User);
		clickLink(ProfilesObjects.ProfileSearch);
		
		if (driver.isElementPresent(ProfilesObjects.EditProfileHeader)){
			Thread.sleep(500);
		}
		else{
			Thread.sleep(3000);
		}
		assertTrue(driver.isTextPresent(User));
	}
	
public void FollowPerson( String User) throws Exception{
		
		//Search for user
		SearchForUser(User);
		
		//Select User
		clickLink(FVT_ProfilesObjects.SelectProfile);
		
		//Follow person
		
		if(driver.isElementPresent(FVT_ProfilesObjects.FollowLinkHidden)){
			return;
		}else{
			clickLink(FVT_ProfilesObjects.FollowPerson);
		}
		
	}

public boolean InviteToNetwork() throws Exception{
	//Invite person to network
	
	boolean Connected = false;
	
	//Select user's profile
	clickLink(FVT_ProfilesObjects.SelectProfile);
	Thread.sleep(500);
	//
	
	if(driver.isElementPresent(FVT_ProfilesObjects.Invite)){
	
	//driver.focus(FVT_ProfilesObjects.Invite);
	clickLink(FVT_ProfilesObjects.Invite);
	Thread.sleep(500);
	//
	clickLink(FVT_ProfilesObjects.SendInvite);
	Thread.sleep(500);
	}
	else
	{
		Reporter.log("Already in network.");
		Connected = true;
		
	}
	
	return Connected;

}


public void followTag(String Tagname) throws Exception{
		
	clickLink(FVT_HomepageObjects.ImFollowing);
	
	filterBy("Tags");
		
	clickLink(FVT_HomepageObjects.ManageTags);
	
	
	if (driver.isTextPresent(Tagname)){
		//Click done
		clickLink(FVT_HomepageObjects.DoneTagging);
		
	}else{
	
		//type in tag
		driver.getSingleElement(FVT_HomepageObjects.TagInputField).type(Tagname);
		
		//Add tag
		clickLink(FVT_HomepageObjects.AddTag);
		clickLink(FVT_HomepageObjects.AddTag);
		
		sleep(3000);
		
		String ExpectedValue = "Your new tag \""+Tagname+"\" was successfully added. You can now filter the feed with this tag.";
		
		assertTrue("FAIL: The tag was not added correctly", driver.isTextPresent(ExpectedValue));
		
		//Click done
		clickLink(FVT_HomepageObjects.DoneTagging);
	}
	
}


public String replaceNewsStory(String NewsStory,String FirstComponentName,String SecondComponentName, String Username) throws Exception{
	
	
	if (FirstComponentName != null){
		
		NewsStory = NewsStory.replaceAll("PLACEHOLDER", FirstComponentName);		
	
	}
		
	if (SecondComponentName != null){
		
		NewsStory = NewsStory.replaceAll("REPLACE_THIS", SecondComponentName);
		
	}
	
	if (Username != null){
		
		NewsStory = NewsStory.replaceAll("USER", Username);
		
	}
	
	return NewsStory;
	

}

		
}
