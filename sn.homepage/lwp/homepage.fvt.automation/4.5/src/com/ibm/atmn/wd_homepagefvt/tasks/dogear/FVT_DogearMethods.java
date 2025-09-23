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

package com.ibm.atmn.wd_homepagefvt.tasks.dogear;


import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.waffle.core.TestConfiguration.BrowserType;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearData;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.news.FVT_CommonNewsMethods;




import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class FVT_DogearMethods extends FVT_CommonNewsMethods {

	FormInputHandler typist;

	public void switchWindow() throws Exception {

		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Set set = new HashSet();

		set = wd.getWindowHandles();
		int size = set.size();
		
		Reporter.log("Number of window handlers " + size);
		
		Iterator it = set.iterator();
		while (it.hasNext()) {
		    // Get element
		    Object element = it.next();
		    Reporter.log("Set elements :: " + element.toString());
		    
		    
		    wd.switchTo().window(element.toString());
			Thread.sleep(500);
			String Title = wd.getTitle();
			Reporter.log("Window title : " + Title);
			
			if (Title.compareTo("Certificate Error: Navigation Blocked") == 0 ){
				 if (testConfig.browserIs(BrowserType.IE)) {
					 sleep(2000);
					 wd.navigate().to("javascript:document.getElementById('overridelink').click()");
					 
				 }
			}

		}
	}
	
	public String addPublicBookmark(String Title, String URL, String Tags, String Desc)throws Exception{
		//Open popup window with form
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		//Get original window handle
		String OriginalWindow = wd.getWindowHandle();
		
		//Click on the Start a bookmark button
		clickLink(FVT_DogearObjects.Nav_AddBookmark);
		
		//Switch to the bookmark form
		switchWindow();
		
		smartSleep(FVT_DogearObjects.Form_AddBookmark_Title);
		
		//Details for the bookmark
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Title).type(Title);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Url).type(URL);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Tags).type(Tags);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Description).type(Desc);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Radio_Public).click();
		//driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Save).click();
		driver.getFirstElement("css=input[id='submitBtn']").click();
		//clickLink(FVT_DogearObjects.Form_AddBookmark_Save);
		//sleep(1000);
		
		//Switch to original window
		wd.switchTo().window(OriginalWindow);
		
		return Title;
	}
	
	public String addPrivateBookmark(String Title1, String URL1, String Tags1, String Desc1)throws Exception{
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		//Get original window handle
		String OriginalWindow = wd.getWindowHandle();
		
		//Click on the Start a bookmark button
		clickLink(FVT_DogearObjects.Nav_AddBookmark);
		
		//Switch to the bookmark form
		switchWindow();
		
		//Details for the bookmark
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Title).type(Title1);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Url).type(URL1);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Tags).type(Tags1);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Description).type(Desc1);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Radio_Private).click();
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Save).click();
		
		//Switch to original window
		wd.switchTo().window(OriginalWindow);
		
		return Title1;
	}
	
	

	public void toggleAddToOwnWatchlist() {
		if (driver.isElementPresent(FVT_DogearObjects.MyBookmarks_Header) != true) {
			clickLink(FVT_DogearObjects.Nav_MyBookmarks);
		}

		if (driver.isElementPresent(FVT_DogearObjects.MyBookmarks_AddToWatchlist)) {
			clickLink(FVT_DogearObjects.MyBookmarks_AddToWatchlist);
		} else {
			clickLink(FVT_DogearObjects.MyBookmarks_RemoveFromWatchlist);
		}

	}

	public void toggleAddPersonToWatchlist(String personName) throws Exception {
		
		typist = new FormInputHandler(driver); 
		
		clickLink(FVT_DogearObjects.Nav_PublicBookmarks);
		
		clickLink(FVT_DogearObjects.StreamWindows_People_Search);
		
		typist.typeAndWait(FVT_DogearObjects.StreamWindows_Focus_People_Search,personName.replace(
				"ajones", "Amy Jones") ,null);
		driver.typeNative(Keys.DOWN);
		driver.typeNative(Keys.DOWN);
		
		sleep(1000);
		
		driver.typeNative(Keys.ENTER);
		driver.typeNative(Keys.ENTER);
		
		//driver.getSingleElement(FVT_DogearObjects.StreamWindows_Focus_People_Search).type("Hello");
		
		Thread.sleep(2000);
		
		if (driver.isElementPresent(FVT_DogearObjects.PublicBookmarks_AddToWatchlist) == true) {
			clickLink(FVT_DogearObjects.PublicBookmarks_AddToWatchlist);

		} else if (driver.isElementPresent(FVT_DogearObjects.PublicBookmarks_AddToWatchlist) != true){
			System.out.println("Already added to watchlist!");
		}

		
	}

	public void toggleAddTagToWatchlist(String tagName) throws Exception {
		
		typist = new FormInputHandler(driver); 
		
		clickLink(FVT_DogearObjects.Nav_PublicBookmarks);
		
		if (driver.isElementPresent(FVT_DogearObjects.StreamWindows_Focus_Tag_Search)) {
			clickLink(FVT_DogearObjects.StreamWindows_Focus_Tag_Search);
		}else{
			clickLink(FVT_DogearObjects.StreamWindows_Open_Tag_Search);
			clickLink(FVT_DogearObjects.StreamWindows_Focus_Tag_Search);

		}
			
		typist.typeAndWait(FVT_DogearObjects.StreamWindows_Type_Tag_Search,tagName ,null);
		driver.typeNative(Keys.DOWN);
		driver.typeNative(Keys.ENTER);
		
		
		
		Thread.sleep(2000);
		
		if (driver.isElementPresent(FVT_DogearObjects.PublicBookmarks_AddToWatchlist) == true) {
			clickLink(FVT_DogearObjects.PublicBookmarks_AddToWatchlist);
			} else if (driver.isElementPresent(FVT_DogearObjects.PublicBookmarks_AddToWatchlist) != true) {
			System.out.println("Already added to watchlist!");
		}

		
	}
	
	public void editBookmark() throws Exception {
	
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		//Get original window handle
		String OriginalWindow = wd.getWindowHandle();
		
		//
		driver.getFirstElement(FVT_DogearObjects.MoreButton).click();
		
		//
		driver.getFirstElement(FVT_DogearObjects.EditButton).click();
		
		//
		switchWindow();
		
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Description).clear();	
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Description).type(FVT_DogearData.EditedBookmarkDesc);
		driver.getSingleElement(FVT_DogearObjects.Form_AddBookmark_Save).click();
		
		//Switch to original window
		wd.switchTo().window(OriginalWindow);
		
		
	}
	

	public void deleteBookmark() {
		//driver.setSpeed("500");
		clickLink(FVT_DogearObjects.MyBookmarks_DeleteBookmark1);
		clickLink(FVT_DogearObjects.MyBookmarks_Delete);
		//String message = driver.getConfirmation();
		//Assert.assertTrue("FAIL: Delete confirmation message not as expected", message
		//		.endsWith("Are you sure you wish to delete it?"));
	}
	
	public void notifyUserAboutBookmark(String UserTypeAhead) {
		
		driver.getFirstElement(FVT_DogearObjects.Nav_BookmarksHome_ExpandDetails_Bookmark1).click();
		clickLink(FVT_DogearObjects.Notify_People);
		driver.getSingleElement(FVT_DogearObjects.Notify_Name).type(UserTypeAhead);
		
		//clickLink(FVT_DogearObjects.NotifyDropDownPerson1+":contains('"+UserTypeAhead+"')");
		if(driver.isElementPresent(FVT_DogearObjects.NotifyDropDownPerson1+":contains('"+UserTypeAhead+"'):nth(0)")){
			clickLink(FVT_DogearObjects.NotifyDropDownPerson1+":contains('"+UserTypeAhead+"'):nth(0)");
		} else { 
			clickLink("css=li#notifyEmails_popup_searchDir.dijitMenuItem");
			clickLink(FVT_DogearObjects.NotifyDropDownPerson1+":contains('"+UserTypeAhead+"'):nth(0)");
		}
		clickLink(FVT_DogearObjects.NotifyButton);
	}
	

	
	public void notifyUserAboutBrokenUrl(String UserTypeAhead) {
		
		driver.getFirstElement(FVT_DogearObjects.Nav_BookmarksHome_ExpandDetails_Bookmark1).click();
		
		clickLink(FVT_DogearObjects.MORE_ACTIONS);
		
		clickLink(FVT_DogearObjects.FLAG_BROKEN);
	
		clickLink(FVT_DogearObjects.BROKEN_SUBMIT);
	}
	
}
