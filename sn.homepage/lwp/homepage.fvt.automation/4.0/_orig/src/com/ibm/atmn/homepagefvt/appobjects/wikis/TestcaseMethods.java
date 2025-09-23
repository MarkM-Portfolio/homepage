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

package com.ibm.atmn.homepagefvt.appobjects.wikis;


import com.ibm.atmn.homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.homepagefvt.tasks.wikis.FVT_WikisMethods;
import com.thoughtworks.selenium.*;
import java.awt.event.*;

import org.junit.*;


@SuppressWarnings("unused")
public class TestcaseMethods extends FVT_WikisMethods{
	
	/*****************************
	 * Methods used in PageIndex *
	 *****************************/
	public void ValidatePageNotCreatedYet() throws Exception {
		Assert.assertTrue("FAIL: Some descriptive text isn't correct", sel.isTextPresent(WikisObjects.NotCreatedTextOnCreatePage));
		Assert.assertTrue("FAIL: Some descriptive text isn't correct", sel.isTextPresent(WikisObjects.DescriptiveTextOnCreatePage));
		Assert.assertTrue("FAIL: problem with 'Create This Page' button", sel.isElementPresent(WikisObjects.Create_This_Page_Button));
		Assert.assertTrue("FAIL: problem with 'take me back' link", sel.isElementPresent(WikisObjects.TakeMeBackOnCreatePage));				
	}
	
	//Increase pages visitation value
	public void VisitPageToIncrementPageVisits(String PageCell, int IncrementalVisits) throws Exception {    	
		//Method to visit pages in the selected wiki a specified number of times
		for(int i=1; i<=IncrementalVisits; i++){
			clickLink("link=" + PageCell);

			//Open Index page
			clickLink(WikisObjects.Index_Link);

			//Validate that Pages Menu is expanded
			CheckPagesSectionUI();
			
			//Open Pages I Edited
			clickLink(WikisObjects.IEdited_Link);
		}
	}
	
	//Verify Pages section in page view
	public void CheckPagesSectionUI() throws Exception{
		Assert.assertTrue("FAIL: Pages Expand/Collapse arrow is missing", sel.isElementPresent(WikisObjects.Pages_Expand_Collapse_Arrow));
		
		String PagesTitle = sel.getText(WikisObjects.Pages_Section_Title);
		Assert.assertEquals("FAIL: Pages title isn't correct", PagesTitle, "Pages");
			
		if (ValidateAllPagesSectionIsCollapsed()){
			clickLink(WikisObjects.Pages_Expand_Collapse_Arrow);
		}
		
		//Verify that all specified links appear in All Pages section
		Assert.assertTrue("FAIL: Go to Page... link is missing", sel.isVisible(WikisObjects.Go_To_Page_Link));
		Assert.assertTrue("FAIL: I Edited link is missing", sel.isVisible(WikisObjects.IEdited_Link));
		Assert.assertTrue("FAIL: Edited by... link is missing", sel.isVisible(WikisObjects.EditedBy_Link));
	}
	
	public void CheckTagsSection() throws Exception {
		Assert.assertTrue("FAIL: Tags Expand/Collapse arrow is missing", sel.isElementPresent(WikisObjects.Tags_Expand_Collapse_Arrow));
		
		String TagsTitle = sel.getText(WikisObjects.Page_View_Tags_Title);
		Assert.assertEquals("FAIL: Tags title isn't correct", TagsTitle, "Tags");

		if (ValidateTagsSectionIsCollapsed())
			clickLink(WikisObjects.Tags_Expand_Collapse_Arrow);

		if (!sel.isTextPresent("No tags yet"))
			this.CheckTagsLinks();
	}
	
	public void CheckDateSection() throws Exception {
		Assert.assertTrue("FAIL: Tags Expand/Collapse arrow is missing", sel.isElementPresent(WikisObjects.Date_Expand_Collapse_Arrow));
		
		String DateTitle = sel.getText(WikisObjects.Date_Section_Title);
		Assert.assertEquals("FAIL: Date title isn't correct", DateTitle, "Date");
		
		if (ValidateDateSectionIsCollapsed())
			clickLink(WikisObjects.Date_Expand_Collapse_Arrow);
		
		String TodayLink = sel.getText(WikisObjects.Date_Links_Today);
		Assert.assertEquals("FAIL: today link isn't correct", TodayLink, "Today");
		
		String LastWeekLink = sel.getText(WikisObjects.Date_Links_Last_Week);
		Assert.assertEquals("FAIL: last week link isn't correct", LastWeekLink, "Last week");
		
		String LastMonthLink = sel.getText(WikisObjects.Date_Links_Last_30_Days);
		Assert.assertEquals("FAIL: last month link isn't correct", LastMonthLink, "Last 30 days");
		
		String LastYearLink = sel.getText(WikisObjects.Date_Links_Last_Year);
		Assert.assertEquals("FAIL: last year link isn't correct", LastYearLink, "Last year");
	}

	public void CheckMembersSection() throws Exception {
		Assert.assertTrue("FAIL: Members Expand/Collapse arrow is missing", sel.isElementPresent(WikisObjects.Members_Expand_Collapse_Arrow));
		
		String MembersTitle = sel.getText(WikisObjects.Members_Section_Title);
		Assert.assertEquals("FAIL: Members title isn't correct", MembersTitle, "Members");
		
		if (ValidateMembersSectionIsCollapsed())
			clickLink(WikisObjects.Members_Expand_Collapse_Arrow);
	}
	
	public boolean ValidateTagsSectionIsCollapsed() throws Exception {
		
		boolean PopularTagsCollapseState = true;
		
		if (sel.isElementPresent(WikisObjects.Tags_Expanded_Arrow))
				PopularTagsCollapseState = false;
		
		return PopularTagsCollapseState;
	}
	
	public boolean ValidateDateSectionIsCollapsed() throws Exception {
		
		boolean DateCollapseState = false;
		
		if (!sel.isTextPresent(WikisObjects.Date_Links_Last_Year))
			DateCollapseState = true;
		
		return DateCollapseState;
	}
	
	public boolean ValidateAllPagesSectionIsExpanded() throws Exception {
		boolean AllPagesExpandState = false;
		Assert.assertTrue("FAIL: Go to Page... link is missing", sel.isVisible(WikisObjects.Go_To_Page_Link));
		Assert.assertTrue("FAIL: I Edited link is missing", sel.isVisible(WikisObjects.IEdited_Link));
		Assert.assertTrue("FAIL: Edited by... link is missing", sel.isVisible(WikisObjects.EditedBy_Link));
		
		AllPagesExpandState = true;

		return AllPagesExpandState;
	}
	
	public boolean ValidateAllPagesSectionIsCollapsed() throws Exception {
		
		boolean AllPagesCollapseState = false;
		
		if(!sel.isVisible(WikisObjects.Go_To_Page_Link))
			AllPagesCollapseState = true;
		
		return AllPagesCollapseState;
	}
	
	public boolean ValidateMembersSectionIsExpanded() throws Exception {
		boolean MembersExpandState = false;
		int index;
		
		Assert.assertTrue("FAIL: View all link is missing", sel.isElementPresent(WikisObjects.View_All_Link));

		//Calling ElementCount method to parse the numeric value of the link
		int CurrentNumberOfMembers = FVT_WikisMethods.ElementCount(sel.getText(WikisObjects.View_All_Link));
		
		for (index=1; index <= CurrentNumberOfMembers; index++)
			Assert.assertTrue("FAIL: Member profile is missing", sel.isElementPresent("xpath=//*[contains(@id,'lconn_share_widget_MembersSummary_')]/div[1]/div["+ index +"]/a/img"));
		
		index++;
		
		//Assert that the number of profiles in Members section matches the number in the link
		Assert.assertTrue("FAIL: Member profiles don't match the number shown in the link", !sel.isElementPresent("xpath=//*[contains(@id,'lconn_share_widget_MembersSummary_')]/div[1]/div["+ index +"]/a/img"));
		MembersExpandState = true;

		return MembersExpandState;	
	}
	
	public boolean ValidateMembersSectionIsCollapsed() throws Exception {
		
		boolean MembersCollapseState = false;
		
		if(!sel.isElementPresent(WikisObjects.View_All_Link))
			MembersCollapseState = true;
		
		return MembersCollapseState;
	}
	
	/**Verify correct user from dropdown*/
	public void SelectCorrectUserNameFromEditedByDropdown(String UserName) throws Exception {    
		String DropdownText = "";

		if (!UserName.contains("Amy"))
			UserName = "Amy " + UserName;
		DropdownText = sel.getText(WikisObjects.Edited_By_Search_Dropdown);
		Assert.assertTrue("FAIL: Member names dropdown list doesn't contain correct user", DropdownText.contains(UserName));
	
		sel.clickAt(WikisObjects.Edited_By_Search_First_Item, UserName);
		Thread.sleep(5000);
	}
	
	public void SelectPageThroughPagesIndex(String pageXPath)throws Exception{
		//Select a specific page in Pages Index page
		String pageName = sel.getText(pageXPath);
		clickLink("link=" + pageName);
	}
	
	public void ActivateTypeAheadForPageSearch(String BrowserType, String ValueForTypeAhead) throws Exception {
		//Verify that the type-ahead text is present
		if(BrowserType.equals("Netscape")){
			sel.typeKeys(WikisObjects.Search_Page_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
		else if (BrowserType.equals("Microsoft Internet Explorer")){
		   sel.type(WikisObjects.Search_Page_Field, ValueForTypeAhead);
		   sel.typeKeys(WikisObjects.Search_Page_Field, ValueForTypeAhead); 
		   Thread.sleep(2000);
		}
	}
	
	public void ActivateAllPagesSearch(String BrowserType) throws Exception {
		if (BrowserType.equals("Microsoft Internet Explorer")){
			sel.focus(WikisObjects.Search_Page_Button);
			clickLink(WikisObjects.Search_Page_Button);
		}
		else
			clickLink(WikisObjects.Search_Page_Button);
	}

	public void SelectCreateThisPage() throws Exception {
		Assert.assertTrue("FAIL: Problem encountered with Create this page screen", sel.isElementPresent(WikisObjects.Create_This_Page_Button));
		clickLink(WikisObjects.Create_This_Page_Button);
	}
	
	public void SelectPageSearch() throws Exception {
		Assert.assertTrue("FAIL: Page Search field is missing", sel.isElementPresent(WikisObjects.Search_Page_Field));
		clickLink(WikisObjects.Search_Page_Field);
	}
	
	
	public void ValidateNewTitleInEditMode(String NewTitle) throws Exception {
		for (int second = 0;; second++){
			if (second >= 30) Assert.fail("TIMEOUT ERROR: CKEditor menu bar has failed to load within 30 seconds");
			  try{
			    if (sel.isVisible("class=cke_toolbar"));
					break;
			  }catch (Exception e) {}
			  Thread.sleep(1000);
		}
		
		Assert.assertTrue("FAIL: Editable page title field isn't visible", sel.isElementPresent(WikisObjects.New_Page_Title_Textfield));
		String EditTitle = sel.getValue(WikisObjects.New_Page_Title_Textfield);
		
		Assert.assertTrue("FAIL: New title doesn't appear in edit title textfield", EditTitle.equals(NewTitle));
	}
	
	public void ValidateTypeAheadDropdown() throws Exception {
		Assert.assertTrue("FAIL: Page Search popup is missing", sel.isElementPresent(WikisObjects.Page_Search_popup));
	}
	
	public void CheckTypeAheadEntries(String TypeAheadValue) throws Exception {
		if (TypeAheadValue.equals("PagesSearch")){
			/*Assert.assertTrue("FAIL: First peer page in type ahead field is missing", Data.New_Page_To_Verify_Page_Search1.equals(sel.getText("xpath=//li[@id='pagenavsearchbox_popup0']")));
			Assert.assertTrue("FAIL: Second peer page in type ahead field is missing", Data.New_Page_To_Verify_Page_Search2.equals(sel.getText("xpath=//li[@id='pagenavsearchbox_popup1']")));
			Assert.assertTrue("FAIL: First child page in type ahead field is missing", Data.New_Page_To_Verify_Page_Search3.equals(sel.getText("xpath=//li[@id='pagenavsearchbox_popup2']")));
			Assert.assertTrue("FAIL: First child page in type ahead field is missing", Data.New_Page_To_Verify_Page_Search4.equals(sel.getText("xpath=//li[@id='pagenavsearchbox_popup3']")));*/
		}
		else if (TypeAheadValue.equals("Welc")){
			Assert.assertTrue("FAIL: First peer page in type ahead field is missing", WikisObjects.Welcome_Homepage.equals(sel.getText("xpath=//li[@id='pagenavsearchbox_popup0']")));
			Assert.assertTrue("FAIL: Only 1 page should appear in typeahead dropdown", !sel.isElementPresent("xpath=//li[@id='pagenavsearchbox_popup1']"));	
		}
		else
			Assert.fail("FAIL: Problem encountered with Page Search type ahead");
	}
	
	public void SelectItemFromPageSearchTypeAheadDropdown(String TypeAheadTitle, String ItemID) throws Exception {
		Assert.assertTrue("FAIL: typeahead item doesn't exist", sel.isElementPresent(ItemID));
		Assert.assertEquals("FAIL: typeahead item isn't as expected", TypeAheadTitle, sel.getText(ItemID));
		sel.clickAt(ItemID,TypeAheadTitle);
		Thread.sleep(2000);
	}
	
	public void VerifyItemFromPageSearchTypeAheadDropdownOpens(String TypeAheadTitle) throws Exception {
		Assert.assertTrue("FAIL: Title isn't visible", sel.isTextPresent(TypeAheadTitle));
		Assert.assertTrue("FAIL: Wiki homepage title isn't visible", sel.isElementPresent(WikisObjects.WikiHomePageTitleField));
		Assert.assertTrue("FAIL: Type ahead title doesn't match wiki homepage title", TypeAheadTitle.equals(sel.getText(WikisObjects.WikiHomePageTitleField)));
	}
	
	public void SelectTakeMeBack() throws Exception {
		clickLink(WikisObjects.TakeMeBackOnCreatePage);
	}
	
	public void SortByName() throws Exception{
		//Click sort by Name option
		sel.click(WikisObjects.Sortby_Name_Option);
		Thread.sleep(2000);
	}
	
	public void SortByUpdated() throws Exception{
		//Click sort by Updated option
		sel.click(WikisObjects.Sortby_Updated_Option);
		Thread.sleep(2000);
	}
	
	public void SortByComments() throws Exception{
		//Method to click Sort by Comments option
		sel.click(WikisObjects.Sortby_Comments_Option);
		Thread.sleep(2000);
	}
	
	public void SortByRecommendations() throws Exception{
		//Method to click Sort by Recommendations option
		sel.click(WikisObjects.Sortby_Recommendations_Option);
		Thread.sleep(2000);
	}
	
	public void SortByVisits()throws Exception{
		//Method to click Sort by Visits option
		sel.click(WikisObjects.Sortby_Visits_Option);
		Thread.sleep(2000);
	}
	
	public void SortByCreated()throws Exception{
		//Method to click Sort by Created option
		sel.click(WikisObjects.Sortby_Created_Option);
		Thread.sleep(2000);
	}
	
	public void SortBySize()throws Exception{
		//Method to click Sort by Size option
		sel.click(WikisObjects.Sortby_Size_Option);
		Thread.sleep(2000);
	}
	
	public void SortByPageViews()throws Exception{
		//Method to click Sort by Page Views option
		sel.click(WikisObjects.Sortby_Page_Views_Option);
		Thread.sleep(2000);
	}
		
	/******************************
	 * Methods used in Membership *
	 ******************************/

	//Verify all UI on Members page
	public void VerifyMembersPageUI(String Role) throws Exception {    
		//Verify navigation links on Members Page
		Assert.assertTrue("FAIL: Next Page Link at the Top of Members List is not Present", sel.isElementPresent(WikisObjects.Top_Next_Page_Link_InActive));
		Assert.assertTrue("FAIL: Previous Page Link at the Top of Members List is not Present", sel.isElementPresent(WikisObjects.Top_Previous_Page_Link_InActive));
		Assert.assertTrue("FAIL: Next Page Link at the Bottom of Members List is not Present", sel.isElementPresent(WikisObjects.Bottom_Next_Page_Link_InActive));
		Assert.assertTrue("FAIL: Previous Page Link at the Bottom of Members List is not Present", sel.isElementPresent(WikisObjects.Bottom_Previous_Page_Link_InActive));
		Assert.assertTrue("FAIL: Page Display Choice Option is not displayed.", sel.isElementPresent(WikisObjects.PageDisplay_Choice_Option));
		
		if (sel.isElementPresent(WikisObjects.PressedListIcon))
			Assert.assertTrue("FAIL: Table Display Option is missing", sel.isElementPresent(WikisObjects.UnPressedTableIcon));
		else
			Assert.assertTrue("FAIL: List Display Option is missing", sel.isElementPresent(WikisObjects.UnPressedListIcon));
		
		Assert.assertTrue("FAIL: Id for 10 Members per Page is missing", sel.isElementPresent(WikisObjects.PageID_Showing_16Members_perpage));
		Assert.assertTrue("FAIL: Link for 25 Members per Page is missing", sel.isElementPresent(WikisObjects.PageID_Showing_32Members_perpage));
		Assert.assertTrue("FAIL: Link for 50 Members per Page is missing", sel.isElementPresent(WikisObjects.PageID_Showing_64Members_perpage));
		
		//Verify Member Role section
		Assert.assertTrue("FAIL: Member Role heading is missing", sel.isTextPresent(WikisObjects.Member_Role_Heading));
		Assert.assertTrue("FAIL: Member Role Expand and Collapse Menu is missing", sel.isElementPresent(WikisObjects.Members_Role_Expand_Collapse_Arrow));
		Assert.assertTrue("FAIL: Tooltip for Member Role Expand and Collapse Menu is missing", sel.isElementPresent(WikisObjects.Tooltip_For_MemberRole_Field));
		Assert.assertTrue("FAIL: Link to filter members by reader is missing", sel.isElementPresent(WikisObjects.Reader_Filter_Link));
		Assert.assertTrue("FAIL: Link to filter members by editor is missing", sel.isElementPresent(WikisObjects.Editor_Filter_Link));
		Assert.assertTrue("FAIL: Link to filter members by manager is missing", sel.isElementPresent(WikisObjects.Owner_Filter_Link));
		
		//Verify Member Kind section
		Assert.assertTrue("FAIL: Member Kind Heading is missing", sel.isTextPresent(WikisObjects.Member_Kind_Heading));
		Assert.assertTrue("FAIL: Member Kind Expand and Collapse Menu is missing", sel.isElementPresent(WikisObjects.Members_Kind_Expand_Collapse_Arrow));
		Assert.assertTrue("FAIL: Tooltip for Member Kind Expand and Collapse Menu is missing", sel.isElementPresent(WikisObjects.Tooltip_For_MemberKind_Field));
		Assert.assertTrue("FAIL: Link to Filter Members by Person is missing", sel.isElementPresent(WikisObjects.Person_Filter_Link));
		Assert.assertTrue("FAIL: Link to Filter Members by Groups is missing", sel.isElementPresent(WikisObjects.Group_Filter_Link));
	
		if (Role.equals(Data.Owner_Role)){
			Assert.assertTrue("FAIL: Add Members Button is missing", sel.isElementPresent(WikisObjects.Add_Members_Button));
			Assert.assertTrue("FAIL: Remove Members Button is missing", sel.isElementPresent(WikisObjects.Remove_Members_Button));
			Assert.assertTrue("FAIL: Manage Access Button is missing", sel.isElementPresent(WikisObjects.Manage_Access_Button));
		}
		else {
			Assert.assertFalse("FAIL: Add Members Button shouldn't appear for user with less than owner access", sel.isElementPresent(WikisObjects.Add_Members_Button));
			Assert.assertFalse("FAIL: Remove Members Button shouldn't appear for user with less than owner access", sel.isElementPresent(WikisObjects.Remove_Members_Button));
			Assert.assertFalse("FAIL: Manage Access Button shouldn't appear for user with less than owner access", sel.isElementPresent(WikisObjects.Manage_Access_Button));
			Assert.assertFalse("FAIL: Edit Link  shouldn't appear for user with less than owner access", sel.isElementPresent(WikisObjects.Edit_Link));
		}
	}
	
	public void verifyAddMembersDialogUI() throws Exception {    
		//Verify the UI of Add Members Light Box
		Assert.assertTrue("FAIL: Members field is missing", sel.isElementPresent(WikisObjects.Members_Field));
		Assert.assertTrue("FAIL: OK button is missing", sel.isElementPresent(WikisObjects.OK_Button));
		Assert.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
		
		//Click on Add Groups Link to open the Drop down and the Groups Field to Add Groups
		clickLink(WikisObjects.AddGroups_Link);
		
		Assert.assertTrue("FAIL: Wiki_Membership_Roles_dropdown list for Users is missing", sel.isElementPresent(WikisObjects.Add_Members_Dropdown));
		String MembershipRolesforUsers_array[] = sel.getSelectOptions(WikisObjects.Add_Members_Dropdown);
		Assert.assertTrue("FAIL: Some Membership roles for Users are missing", 3 == MembershipRolesforUsers_array.length);
		
		Assert.assertTrue("FAIL: First membership role isn't Owner for Users", Data.Owner_Role.equals(MembershipRolesforUsers_array[0]));
		Assert.assertTrue("FAIL: Second membership role isn't Editor for Users", Data.Editor_Role.equals(MembershipRolesforUsers_array[1]));
		Assert.assertTrue("FAIL: Third membership role isn't Reader for Users", Data.Reader_Role.equals(MembershipRolesforUsers_array[2]));
		
		Assert.assertTrue("FAIL: Wiki_Membership_Roles_dropdown list for Groups is missing", sel.isElementPresent(WikisObjects.Add_Groups_Dropdown));
		String MembershipRolesforGroups_array[] = sel.getSelectOptions(WikisObjects.Add_Groups_Dropdown);
		Assert.assertTrue("FAIL: Some Membership roles for Groups are missing", 3 == MembershipRolesforGroups_array.length);
		
		Assert.assertTrue("FAIL: First Membership Role isn't Owner for Groups", Data.Owner_Role.equals(MembershipRolesforGroups_array[0]));
		Assert.assertTrue("FAIL: Second Membership Role isn't Editor for Groups", Data.Editor_Role.equals(MembershipRolesforGroups_array[1]));
		Assert.assertTrue("FAIL: Third Membership Role isn't Reader for Groups", Data.Reader_Role.equals(MembershipRolesforGroups_array[2]));		
	}
	
	public int getNumberOfUsersDisplayedOnMembersPage() throws Exception {    
		//Verify the number of users displayed on Members page
				
		//Click on the link in the Members footer to show 64 members per page
		if (sel.isElementPresent(WikisObjects.PageID_Showing_64Members_perpage))
			clickLink(WikisObjects.PageID_Showing_64Members_perpage);
		
		//return the number of members on that page
		String noOfUsersText = sel.getText(WikisObjects.Number_of_Members);
		String noOfUsers = "";
		   
		//Loop to extract the number of users from the page text
		for (int y = 0;y < noOfUsersText.length();y++){
			if (noOfUsersText.charAt(y)=='f'){
				noOfUsers = noOfUsersText.substring(y + 2, noOfUsersText.length());
			  }
		}
	   
		int noOfUsers1 = Integer.parseInt(noOfUsers);
		return noOfUsers1;
		
		//Old code
		/*while(!(sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr[" +index1+ "]/td[" +index2+ "]/table/tbody/tr/td[2]/h4").equals(""))){
			index2++;
			
			if (index2 > 4){
				index1++;
				index2 = 1;
			}
			noOfUsers++;
		}
		return noOfUsers;*/ 					
	}
	
	/**Change the role for specified user*/
	public void editUserPrivilegeOnMembersPage(String UserFullName, String NewRole) throws Exception {    
		//Change the User Role to the Target role. 
		String userText = "";
		int index1 = 1, index2;
		boolean memberNotFound = true;
		
		clickLink(WikisObjects.PageID_Showing_64Members_perpage);
		
		//Select user from Members
		while(memberNotFound){
			index2 = 1;
			while((sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/h4"))&&(memberNotFound)){
				userText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/h4");
				
				if(userText.contains(UserFullName)){
					clickLink("xpath=id('list')/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/div[2]/ul/li/a");
					memberNotFound = false;
				}
				index2++;
			}
			index1++;
		}

		setNewRoleOnEditMembersLightBox(NewRole);
	}
	
	/**Remove a specified member*/
	public void removeUserFromMembersList(String UserFullName) throws Exception {    
		//Remove a user from Members list in Table View
		int index1 = 1, index2 = 1, innerIndex = 1;;
		
		if (sel.isElementPresent(WikisObjects.PageID_Showing_64Members_perpage))
			clickLink(WikisObjects.PageID_Showing_64Members_perpage);

		//while(sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/h4")){
		while (index1 < 3){
			String UsernameText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/h4");

			if(UsernameText.contains(UserFullName)){
				//Select checkbox for Member to be removed
				clickLink("xpath=id('list')/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[1]/input");	
				
				//Click Remove Members button
				clickLink(WikisObjects.Remove_Members_Button);
					
				//Verify resulting dialog UI
				this.VerifyRemoveMemberConfirmation();
					
				//Confirm Member removal
				clickLink(WikisObjects.OK_Button);
			}
			index2++;
			
			if (index2 > 4){
				index1++;
				index2 = 1;
			}
		}
	}
	
	/**Verify Remove Members confirmation dialog*/
	public void VerifyRemoveMemberConfirmation() throws Exception {
		Assert.assertTrue("FAIL: Remove Members title is incorrect", sel.isTextPresent("Remove Members"));
		Assert.assertTrue("FAIL: Remove Members text content is incorrect", sel.isTextPresent("Are you sure you want to remove selected members"));
		Assert.assertTrue("FAIL: OK button is missing", sel.isElementPresent(WikisObjects.OK_Button));
		Assert.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}
	
	/**Set new role on Members page*/
	public void setNewRoleOnEditMembersLightBox(String NewRole) throws Exception {    
		//Set the New Role on Edit Member Light Box
		if(NewRole.equals("Owner"))
			clickLink(WikisObjects.Owner_Role_Option_onEditMemberLightBox);
			
		else if(NewRole.equals("Editor"))
			clickLink(WikisObjects.Editor_Role_Option_onEditMemberLightBox);

		else if(NewRole.equals("Reader"))
			clickLink(WikisObjects.Reader_Role_Option_onEditMemberLightBox);
			
		clickLink(WikisObjects.Save_Button);
	}
	
	/**Verify that role has been correctly changed for specified user*/
	public boolean verifyNewRoleAssignedToUserOnMembersPage(String UserFullName, String NewRole) throws Exception {    
		//Verify that the new role that we've assigned on Members page is applied
		int index1 = 1, index2 = 1;
		boolean correctRole = false;

		if (sel.isElementPresent(WikisObjects.PageID_Showing_64Members_perpage))
			clickLink(WikisObjects.PageID_Showing_64Members_perpage);
		
		//while((sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/h4"))&&
		//!(sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/h4").equals("")))
		//while(sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/h4"))
		while (index1 < 3)
		{
			String UsernameText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/h4");
			//System.out.println(UsernameText);
			
			if((UsernameText.contains(UserFullName))||(UsernameText.equals(UserFullName))){
				String UserTypeText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/div[1]");
				//System.out.println(UserTypeText);
				Assert.assertTrue("FAIL: New Role appears incorrectly on Members page", UserTypeText.equals(NewRole));
				
				//Click Edit for specific member
				clickLink("xpath=//div[@id='list']/div[2]/table/tbody/tr["+index1+"]/td["+index2+"]/table/tbody/tr/td[2]/div[2]/ul/li/a");
					
				correctRole = this.VerifyRadioButtonIsCheckedForGivenRole(NewRole);
			}	
			
			index2++;
			
			if (index2 > 4){
				index1++;
				index2 = 1;
			}	
		}
		return correctRole;
	}
	
	/**Verify correct radio button is selected after role change*/
	public boolean VerifyRadioButtonIsCheckedForGivenRole(String Role) throws Exception {
		//Verify whether the Radio Button for the given role is checked
		boolean radioSelected = false;
		
		if((Role.equals("Owner"))&&(sel.isChecked(WikisObjects.Owner_Role_Option_onEditMemberLightBox)))
			radioSelected = true;
		else if((Role.equals("Editor"))&&(sel.isChecked(WikisObjects.Editor_Role_Option_onEditMemberLightBox)))
			radioSelected = true;
		else if((Role.equals("Reader"))&&(sel.isChecked(WikisObjects.Reader_Role_Option_onEditMemberLightBox)))
			radioSelected = true;
		
		clickLink(WikisObjects.Save_Button);
		return radioSelected;	
	}
	
	/****************************
	 * Methods used in Versions *
	 * **************************/
	
	//Open Version view through specified 'View' link
	public void OpenVersionView(int VersionsVisible) throws Exception {
		String ActivePage = "";
			
		if (sel.isElementPresent(WikisObjects.Versions_Active_Page))
			ActivePage = sel.getText(WikisObjects.Versions_Active_Page);
		
		if (VersionsVisible > 10){
			Assert.assertTrue("FAIL: pagination for versions is missing",(sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))&
					(sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));	
			
			if (ActivePage.equals("2"))
				clickLink("link=" + ActivePage);
			
			VersionsVisible = VersionsVisible - 10;
			
			Assert.assertTrue("FAIL: Version view link isn't visible", sel.isElementPresent("xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr["+VersionsVisible+"]/td[4]/a"));
			clickLink("xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr["+VersionsVisible+"]/td[4]/a");
		}
		
		else if ((VersionsVisible <= 10) & (VersionsVisible > 1)){
			Assert.assertFalse("FAIL: pagination for versions shouldn't appear for less than 10 versions",(sel.isVisible(WikisObjects.Versions_Top_Pagination_Section))&&
					(sel.isVisible(WikisObjects.Versions_Bottom_Pagination_Section)));	
			
			
			Assert.assertTrue("FAIL: Version view link isn't visible", sel.isElementPresent("xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr["+VersionsVisible+"]/td[4]/a"));
			clickLink("xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr["+VersionsVisible+"]/td[4]/a");
		}
		else
			Assert.fail("FAIL: Version view link is inactive because there is only 1 version");
		
	}
	
	//Verify that Versions page appears as expected
	public void ValidateVersionsPage(int VersionsValue, String Role) throws Exception {
		if (sel.isElementPresent(WikisObjects.Versions_Active_Page)){
			String ActivePage = sel.getText(WikisObjects.Versions_Active_Page);
		
			if ((VersionsValue > 10) & (ActivePage.equals("2"))){
				Assert.assertTrue("FAIL: Pagination for versions is missing", (sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))&
					(sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));	
		
				clickLink("link=" + ActivePage);

				VersionsValue = VersionsValue - 10;
			}
		}

		Assert.assertTrue("FAIL: Versions Information is missing", sel.isElementPresent(WikisObjects.Versions_Complete_Info_Body));

		ValidateVersionLinks(VersionsValue, Role);
		ValidateVersionComparisonUI(VersionsValue);
		ValidateVersionDeletionUI(VersionsValue, Role);
		
		Assert.assertTrue("FAIL: Subscribe to pages link is missing", sel.isElementPresent(WikisObjects.Subscribe_To_This_Page_Link));
		Assert.assertTrue("FAIL: Subscribe to page versions link is missing", sel.isElementPresent(WikisObjects.Subscribe_To_These_Page_Versions_Link));
	}
	
	//Validate links in Versions inline tab
	public void ValidateVersionLinks(int verValue, String UserRole) throws Exception {
		
		if (verValue == 1){
			Assert.assertTrue("FAIL: View link shouldn't be visible", !sel.isElementPresent(WikisObjects.Versions_View_Link));
			Assert.assertTrue("FAIL: Restore link shouldn't be visible", !sel.isElementPresent(WikisObjects.Versions_Restore_Link));
			Assert.assertTrue("FAIL: Current Version text is missing", sel.isTextPresent(WikisObjects.Versions_Current_Version_Text));
		}	
		else {
			Assert.assertTrue("FAIL: View link isn't visible", sel.isElementPresent(WikisObjects.Versions_View_Link));
		
			if ((UserRole.equals(Data.Creator_Role))||(UserRole.equals(Data.Owner_Role))||(UserRole.equals(Data.Editor_Role)))
				Assert.assertTrue("FAIL: Restore link isn't visible", sel.isElementPresent(WikisObjects.Versions_Restore_Link));	
		
			else 
				Assert.assertTrue("FAIL: Restore link shouldn't be visible", !sel.isElementPresent(WikisObjects.Versions_Restore_Link));
		}
	}
	
	//Return the number of versions added by counting content
	public int NumberOfVersionInfoContent() throws Exception{
		int index=1;
		int VersionContentCount = 0;
		
		while (sel.isElementPresent("css=table[class='lotusTable lotusClear'] tr:nth-child("+index+")")){
			if (sel.isElementPresent(WikisObjects.Versions_Active_Page)){ 
				String ActivePage = sel.getText(WikisObjects.Versions_Active_Page);
				
				if ((ActivePage.equals("2"))&(index >= 10)){
					clickLink("link=" + ActivePage);
					index = 0;
				}
			}
			
			VersionContentCount++;
			index++;
		}
		
		return VersionContentCount;	
	}
	
	//Match heading versions number to content count
	public void MatchVersionsHeadingValueToContent() throws Exception {
		int Versions_Value = ValidateVersionsNumericValue();
		int Versions_Visible = 0;
		int index = 1;
		
		if (sel.isElementPresent(WikisObjects.Versions_Active_Page)){
			String ActivePage = sel.getText(WikisObjects.Versions_Active_Page);
		
			//Check to see if the first page of versions is active, if so, add 10 to variable & click Page 2
			if ((Versions_Value > 10) && (ActivePage.equals("2"))){
				Assert.assertTrue("FAIL: Versions pagination is missing",(sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))&
						(sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));	
		
						clickLink("link=" + ActivePage);
						Versions_Visible = Versions_Visible + 10;
			}
			//Check to see if the second page of versions is active, if so, add 10 to variable
			else if ((Versions_Value > 10) && (ActivePage.equals("1"))){
				Assert.assertTrue("FAIL: Versions pagination is missing",(sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))&
						(sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));	
					Versions_Visible = Versions_Visible + 10;
			}
		}
		
		while (sel.isElementPresent("css=table[class='lotusTable lotusClear'] tr:nth-child("+index+")")){			
			Assert.assertTrue("FAIL: Versions aren't visible in page", sel.isElementPresent("css=table[class='lotusTable lotusClear'] tr:nth-child("+index+")"));
			index++;
			Versions_Visible++;
		}
		
		System.out.println("There is/are " + Versions_Value + " versions indicated by the Versions heading");
		System.out.println("There is/are " + Versions_Visible + " versions visible in the Versions page");

		Assert.assertTrue("FAIL: Versions indicated aren't visible", Versions_Value == Versions_Visible);	
	}
	
	//Verify Version View UI
	public void ValidateVersionViewUI(String NewContentText) throws Exception {
		String ViewText = "";
		
		Assert.assertTrue ("FAIL: Details field in Version View UI is missing", sel.isElementPresent(WikisObjects.Versions_View_Details_Field));
		
		if (sel.isVisible(WikisObjects.Versions_View_First_Link)){
			String LinkText = sel.getText(WikisObjects.Versions_View_First_Link);
			if (!LinkText.equals(WikisObjects.Versions_View_CompareToMostRecent))
				Assert.assertTrue ("FAIL: Restore link in Version View UI is missing", LinkText.equals(WikisObjects.Restore_Versions_Text));
		}
			
		Assert.assertTrue ("FAIL: Compare to most recent link in Version View UI is missing", sel.isElementPresent(WikisObjects.Versions_View_CompareToMostRecent_Link));
		Assert.assertTrue ("FAIL: View most recent link in Version View UI is missing", sel.isElementPresent(WikisObjects.Versions_View_ViewMostRecent_Link));
		
		Assert.assertTrue("FAIL: added content is missing", sel.isElementPresent(WikisObjects.Versions_View_Textfield));	
		ViewText = sel.getText(WikisObjects.Versions_View_Textfield);
		Assert.assertTrue("FAIL: text in view doesn't match text in first version", ViewText.contains(NewContentText));	
	}
	
	//Verify Version title is correct
	public void ValidateVersionViewTitle(String pageTitle, int versionValue) throws Exception {	
		Assert.assertTrue("FAIL: Versions View title is incorrect", sel.isTextPresent(pageTitle + " [" + versionValue + "] : Versions"));	
	}
	
	//Return version number of particular content
	public int getSelectedVersionNumber(int versionCount) throws Exception {	
		int versionNumber = Integer.parseInt(sel.getText("xpath=id('pageVersions')/div[2]/table/tbody/tr["+ versionCount +"]/td[1]"));
	
		return versionNumber;
	}
	
	//Click 'Show Comparison' link
	public void SelectShowComparison() throws Exception{
		Assert.assertTrue("FAIL: Show Comparison link isn't visible", sel.isElementPresent(WikisObjects.Versions_Show_Comparison_Link));
		clickLink(WikisObjects.Versions_Show_Comparison_Link);
	}
	
	//Make selections in comparison dropdowns
	public void MakeComparisonDropdownSelections(String SelectFrmDD1, String SelectFrmDD2) throws Exception{
		sel.select(WikisObjects.Versions_Comparison_Dropdown1, SelectFrmDD1);
		sel.select(WikisObjects.Versions_Comparison_Dropdown2, SelectFrmDD2);		
	}
	
	//Verify that the View link has been changed to 'Current Version'
	public void ValidateVersionViewLinkChange(int index) throws Exception{
		String TextStatus = sel.getText("xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr["+index+"]/td[4]");
		Assert.assertTrue("FAIL: View link should have changed to 'Current Version'", TextStatus.equals(WikisObjects.Versions_Current_Version_Text));
	}

	//Get last item in comparison dropdown #1
	public String GetOptionFromCompareDropdown1(String Position) throws Exception {	
		String Selection1a = "";
		String Dropdown1_Options[] = sel.getSelectOptions(WikisObjects.Versions_Comparison_Dropdown1);
		if (Position.equals("Last"))
			Selection1a = Dropdown1_Options[Dropdown1_Options.length - 1];
		else
			Selection1a = Dropdown1_Options[Dropdown1_Options.length - 2];
		
		return Selection1a;
	}
	
	//Get last item in comparison dropdown #2
	public String GetOptionFromCompareDropdown2(String Position) throws Exception {	
		String Selection2a = "";
		String Dropdown2_Options[] = sel.getSelectOptions(WikisObjects.Versions_Comparison_Dropdown2);
		if (Position.equals("Last"))
			Selection2a = Dropdown2_Options[Dropdown2_Options.length - 1];
		else
			Selection2a = Dropdown2_Options[Dropdown2_Options.length - 2];
		
		return Selection2a;
	}

	//Verify Version Comparison page appears as expected
	public void ValidateVersionComparisonPage(String WikiTitle, String ValueFromDropdown1, String ValueFromDropdown2) throws Exception{
		//Assert that comparison elements exists in Versions page
		String ComparisonPageTitle = sel.getText(WikisObjects.WikiHomePageTitleField);

		Assert.assertTrue("FAIL: Title text isn't correct", ComparisonPageTitle.equals(WikiTitle + " : Version Comparison"));
		
		Assert.assertTrue("FAIL: Heading doesn't exist", sel.isTextPresent(WikisObjects.Versions_Comparison_Page_Heading));
		Assert.assertTrue("FAIL: Comparison section 1 is missing", sel.isElementPresent(WikisObjects.Versions_Comparison_Page_Version_Section1));
		Assert.assertTrue("FAIL: Comparison section 2 is missing", sel.isElementPresent(WikisObjects.Versions_Comparison_Page_Version_Section2));
		Assert.assertTrue("FAIL: Refresh link is missing", sel.isElementPresent(WikisObjects.Versions_Comparison_Page_Refresh_Section));
		Assert.assertTrue("FAIL: Refresh icon is missing", sel.isElementPresent(WikisObjects.Versions_Comparison_Page_Refresh_Icon));
		Assert.assertTrue("FAIL: Comparison Deleted text is missing", sel.isTextPresent(WikisObjects.Versions_Comparison_Page_Deleted_Text));
		Assert.assertTrue("FAIL: Comparison New/Changed text is missing", sel.isTextPresent(WikisObjects.Versions_Comparison_Page_NewChanged_Text));
		Assert.assertTrue("FAIL: Comparison textfield is missing", sel.isElementPresent(WikisObjects.Versions_Comparison_Page_Compare_Textfield));
	
		String x = sel.getValue(WikisObjects.Versions_Comparison_Page_Dropdown1);
		String y = sel.getValue(WikisObjects.Versions_Comparison_Page_Dropdown2);
		
		Assert.assertTrue("FAIL: Item in Version Comparison page dropdown 1 is incorrect", x.equals(ValueFromDropdown1));
		Assert.assertTrue("FAIL: Item in Version Comparison page dropdown 2 is incorrect", y.equals(ValueFromDropdown2));
	}
	
	//Click Restore link for specified version
	public void RestorePriorVersions(String RestoreNumber) throws Exception {
		if(sel.isElementPresent("xpath=id('pageVersions')/div[2]/table/tbody/tr["+RestoreNumber+"]/td[5]/a")){
			sel.clickAt("xpath=id('pageVersions')/div[2]/table/tbody/tr["+RestoreNumber+"]/td[5]/a", WikisObjects.Restore_Versions_Text);
			Thread.sleep(2000);
		}
		else
			Assert.fail("FAIL: cannot locate the version specified");
	}
	
	//Verify version hasn't been restored
	public void ValidateVersionsNotRestored(String RestoredVersion) throws Exception {	
		Assert.assertTrue("FAIL: version restored text appears after cancel", !sel.isTextPresent("Restored from version " +RestoredVersion));
	}
	
	//Verify version has been restored
	public void ValidateVersionsAreRestored(String RestoredVersion) throws Exception {	
		Thread.sleep(2000);
		Assert.assertTrue("FAIL: version restored text doesn't appear", sel.isTextPresent("Restored from version " +RestoredVersion));
	}
	
	//Return position within listing of specified version content
	public String getVersionPosition(int RestoreVersionNumber) throws Exception {	
		int versionCount = NumberOfVersionInfoContent() + 1;
		
		return Integer.toString(versionCount - RestoreVersionNumber);
	}
	
	//Return Number value for specified version content
	public String GetRestoreNumberDetails(String VersionPosition) throws Exception {	
		String RestoreNumberDetails = sel.getText("xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr["+VersionPosition+"]/td[1]");
		return RestoreNumberDetails;
	}
	
	//Return Date value for specified version content
	public String GetDateDetails(String VersionPosition) throws Exception {	
		String DateDetails = sel.getText("xpath=id('pageVersions')/div[2]/table/tbody/tr["+VersionPosition+"]/td[2]");
		return DateDetails;
	}
	
	//Return Username value for specified version content
	public String GetUsernameDetails(String VersionPosition) throws Exception {	
		String UsernameDetails = extractUsernamefromText(sel.getText("xpath=//div[@id='pageVersions']/div[2]/table/tbody/tr["+VersionPosition+"]/td[3]/a"));
		return UsernameDetails;
	}
	
	//Verify resulting Restore dialog
	public void ValidateVersionRestorePopup(String RestoreVersionNumber, String DateDetails, String UsernameDetails) throws Exception {
		//Validate Restore popup
		Assert.assertTrue("FAIL: Restore confirmation text in popup isn't correct", sel.isTextPresent("You are about to replace the current version of this page with version "+RestoreVersionNumber+", which was created "+DateDetails+" by "+UsernameDetails+". Do you want to proceed?"));
		String Restore_Header = sel.getText(WikisObjects.Lightbox_Header);
		
		Assert.assertTrue("FAIL: Restore Confirmation header is incorrect", Restore_Header.contains("Confirm"));
		Assert.assertTrue("FAIL: Restore button is missing", sel.isElementPresent(WikisObjects.OK_Button));
		Assert.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));	
	}

	//Use method to extract username from text result
	public String extractUsernamefromText(String TextToBeParsed) throws Exception {
		StringBuffer ParsedUsernameInText = new StringBuffer();

		for (int index=0; index < TextToBeParsed.length(); index++){
			if (TextToBeParsed.charAt(index)=='A') {
				while(index < TextToBeParsed.length()){
					ParsedUsernameInText.append(TextToBeParsed.charAt(index));
					index++;
				}
			}
		}
		return ParsedUsernameInText.toString();	
	}
	
	
	//Select options in Delete versions dropdown
	public void MakeDeleteDropdownSelections(String SelectFrmDD1) throws Exception{
		sel.select(WikisObjects.Versions_Delete_Dropdown, SelectFrmDD1);		
		Thread.sleep(2000);
	}
	
	//Delete previous versions
	public void DeletePriorVersions() throws Exception {
		sel.clickAt(WikisObjects.Delete_Prior_Versions_Link, WikisObjects.Delete_Prior_Versions_Text);
		Thread.sleep(2000);
	}
	
	//Verify Deletion popup UI
	public void ValidateVersionDeletionPopup(String VersionNumber) throws Exception {
		Assert.assertTrue("FAIL: confirmation popup heading is incorrect", sel.isTextPresent("Confirm"));
		Assert.assertTrue("FAIL: confirmation popup is missing", sel.isTextPresent("Are you sure you want to delete all versions prior to "+VersionNumber+"?"));
	}
	
	//Ensure version hasn't been removed
	public void ValidateVersionsStillVisible(int VersionsVisible) throws Exception {	
		if (sel.isElementPresent(WikisObjects.Versions_Active_Page)){
			String ActivePage = sel.getText(WikisObjects.Versions_Active_Page);
		
			if ((VersionsVisible > 10)& (ActivePage.equals("2"))){
				Assert.assertTrue("FAIL: pagination for versions is missing",(sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))&
					(sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));	
			
				clickLink("link=" + ActivePage);
				VersionsVisible = VersionsVisible - 10;
			}
		}
		Assert.assertTrue("FAIL: Version has been incorrectly removed", sel.isElementPresent("xpath=id('pageVersions')/div[2]/table/tbody/tr["+VersionsVisible+"]/td[1]"));
	}
	
	//Ensure version has been deleted
	public void ValidateVersionsAreRemoved(int VersionsVisible) throws Exception {	
		if (sel.isElementPresent(WikisObjects.Versions_Active_Page)){
			String ActivePage = sel.getText(WikisObjects.Versions_Active_Page);
		
			if ((VersionsVisible > 10)&&(ActivePage.equals("2"))){
				Assert.assertTrue("FAIL: pagination for versions is missing",( sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))&
					(sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));	
			
				clickLink("link=" + ActivePage);
				VersionsVisible = VersionsVisible - 10;
			}
		}
		Assert.assertTrue("FAIL: Version hasn't been removed", !sel.isElementPresent("xpath=id('pageVersions')/div[2]/table/tbody/tr["+VersionsVisible+"]/td[1]"));
	}
	
	//Verify Version Deletion UI in Versions page
	public void ValidateVersionDeletionUI(int versionValue, String UserRole) throws Exception {
		if ((UserRole.equals(Data.Owner_Role))&&(versionValue > 1)){
			Assert.assertTrue("FAIL: deletion text isn't visible", sel.isTextPresent(WikisObjects.Versions_Deletion_Text));
			Assert.assertTrue("FAIL: deletion dropdown isn't visible", sel.isElementPresent(WikisObjects.Versions_Delete_Dropdown));	
			Assert.assertTrue("FAIL: Delete link isn't visible", sel.isElementPresent(WikisObjects.Delete_Link));
		}
		
		else {
			Assert.assertTrue("FAIL: deletion section shouldn't be visible for user with less than Editor access", !sel.isVisible(WikisObjects.Versions_Deletion_Section));
			Assert.assertTrue("FAIL: deletion dropdown shouldn't be visible for user with less than Editor access", !sel.isVisible(WikisObjects.Versions_Delete_Dropdown));	
			Assert.assertTrue("FAIL: Delete link shouldn't be visible for user with less than Editor access", !sel.isVisible(WikisObjects.Delete_Link));
		}		
	}
	
	//Verify Version comparison UI in Versions page
	public void ValidateVersionComparisonUI(int NumberOfVersions) throws Exception{
		
		if (NumberOfVersions > 1){
			//Assert that comparison elements exists in Versions page
			Assert.assertTrue("FAIL: Some comparison text is missing", sel.isTextPresent(WikisObjects.Versions_Compare_Text));
			Assert.assertTrue("FAIL: First comparison dropdown is missing", sel.isVisible(WikisObjects.Versions_Comparison_Dropdown1));
			Assert.assertTrue("FAIL: Some comparison text is missing", sel.isTextPresent(WikisObjects.Versions_To_Text));
			Assert.assertTrue("FAIL: Second comparison dropdown is missing", sel.isVisible(WikisObjects.Versions_Comparison_Dropdown2));
			Assert.assertTrue("FAIL: Show Comparison link is missing", sel.isVisible(WikisObjects.Versions_Show_Comparison_Link));
		}
		else{
			Assert.assertTrue("FAIL: First comparison dropdown shouldn't appear", !sel.isVisible(WikisObjects.Versions_Comparison_Dropdown1));
			Assert.assertTrue("FAIL: Second comparison dropdown shouldn't appear", !sel.isVisible(WikisObjects.Versions_Comparison_Dropdown2));
			Assert.assertTrue("FAIL: Show Comparison link shouldn't appear", !sel.isVisible(WikisObjects.Versions_Show_Comparison_Link));
		}
	}
	
	/**************************************
	 * Methods used in Page Functionality *
	 **************************************/
	
	//Ensure that a new page has being added successfully to the Nav tree
	public void VerifyPageAddedToNavTree (String NewPageExists) throws InterruptedException{
		Assert.assertTrue("FAIL: " +NewPageExists+ " hasn't been created successfully", sel.isTextPresent(NewPageExists));
	}

	
	
	//Create a new page on the navigation using right-click menu
	public void CreateNewPageAfterAllPreviousPagesWereDeleted (String WikiName) throws Exception{
		clickLink(WikisObjects.Create_A_Page_Button);	
		sel.type(WikisObjects.New_Page_Title_Textfield, WikiName);
		Thread.sleep(500);
		clickLink(WikisObjects.Save_and_Close_Link);
		
		if (sel.isTextPresent(WikisObjects.Duplicate_Page_Warning))
			clickLink(WikisObjects.Cancel_Link);
		
		this.VerifyPageAddedToNavTree(WikiName);
	}
	
	//Create a random number of root pages on the navigation through Page Actions
	public void CreateRandomNumberOfChildPages(int NumberOfPage) throws Exception{
		String ItemText = "";
		
		for (int i = 1; i <= NumberOfPage; i++){
			if (sel.isElementPresent(WikisObjects.Page_Actions_Button)){
				clickLink(WikisObjects.Page_Actions_Button);
			
				if (sel.isVisible(WikisObjects.Menu_Item_1)){
					ItemText = sel.getText(WikisObjects.Menu_Item_1);
					Assert.assertTrue("FAIL: The first item isn't 'Create Child'", ItemText.contains("Create Child"));	
				
					clickLink(WikisObjects.Menu_Item_1);
				
					//Enter the page name and save and close
					EnterNewPageNameSaveAndClose("Child Page" + i);
				}
			}

			else if (sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)){	
				clickLink(WikisObjects.Page_Actions_Button_For_Editors);
			
				if (sel.isVisible(WikisObjects.Menu_Item_1)){
					ItemText = sel.getText(WikisObjects.Menu_Item_1);
					Assert.assertTrue("FAIL: The first item isn't 'Create Child'", ItemText.contains("Create Child"));	
					
					clickLink(WikisObjects.Menu_Item_1);
					
					//Enter the page name and save and close
					EnterNewPageNameSaveAndClose("Child Page" + i);
				}
			}
			VerifyPageAddedToNavTree("Child Page" + i);
			Thread.sleep(3000);
		}
	}
	
	//Create a random number of root pages on the navigation through New Page link
	public void CreateRandomNumberOfRootPages(int NumberOfPage) throws Exception{
		for (int i = 1; i <= NumberOfPage; i++) {
			//Verify that the New Page is visible and then click on this, if not then refresh the browser, wait 5 seconds and then try again.
			if (sel.isVisible(WikisObjects.Create_New_Page_Link))
				clickLink(WikisObjects.Create_New_Page_Link);
			
			else{
				sel.refresh();
				sel.waitForPageToLoad("10000");
				clickLink(WikisObjects.Create_New_Page_Link);
			}
			//Enter the page name and save and close
			EnterNewPageNameSaveAndClose("Root Page" + i);
			
			VerifyPageAddedToNavTree("Root Page" + i);
			Thread.sleep(3000);
		}
	}
	
	//Verify that the child page has now being moved 
	public void VerifyPageHasMovedOnTheNav(String VerifyPageHasMovedMessage) throws InterruptedException{
		sel.click("link=" + VerifyPageHasMovedMessage);
		Thread.sleep(2000);
		
		sel.click(WikisObjects.About_Tab);
		Thread.sleep(2000);
		
		String TestForChildPage = sel.getText(WikisObjects.NoChildPageExists);
		Assert.assertEquals("No children exist", TestForChildPage);	
	}
	
	//Make the selected page a top level page
	public void MakeTopLevelPage() throws Exception {
		//Now click on the 'Make this a top level page' checkbox and click the Move button
		clickLink(WikisObjects.MakeThisATopLevelPage);
		clickLink(WikisObjects.OK_Button);

		System.out.println("PASS: The Child Page has being moved to the root successfully.");
	}
	
	//Move the selected page from root to child
	public void MoveRootPageToChildPage(int SelectPageInList) throws Exception {
		clickLink("css=.lotusForm");

		clickLink(WikisObjects.MovePageBrowseButton);

		clickLink(WikisObjects.MovePageSortByName);

		if (sel.isElementPresent("css=.lotusStream .lotusTable td h4:contains("+SelectPageInList+")"))	
			clickLink("css=.lotusStream .lotusTable td h4:contains("+SelectPageInList+")");	
		else{
			clickLink(WikisObjects.MovePageSortByNext);
			clickLink("css=.lotusStream .lotusTable td h4:contains"+(SelectPageInList));
		}
		clickLink(WikisObjects.MoveButton);
		
		System.out.println("PASS: The Root page has now being changed back to being a child page again.");
	}
	
	/**Validate page position in the navigation tree*/
	public void ValidatePagePositionInNav(String PageName, String PageInReorder) throws Exception{
		Assert.assertTrue("FAIL: Page at specified nav position doesn't correspond to page expected",
				("* " + PageName).equals(sel.getText(PageInReorder)));
	}
	
	//Method to open Move Page option in Page Actions
	public void SelectMovePageFromPageActions() throws Exception {
		String ItemText = "";
		
		if (sel.isElementPresent(WikisObjects.Page_Actions_Button)){
			clickLink(WikisObjects.Page_Actions_Button);
		}

		else if (sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)){	
			clickLink(WikisObjects.Page_Actions_Button_For_Editors);
		}
		
		if (sel.isElementPresent(WikisObjects.Menu_Item_4)){
			ItemText = sel.getText(WikisObjects.Menu_Item_4);
			Assert.assertTrue("FAIL: The fourth item isn't 'Move Page'", ItemText.contains("Move Page"));	
			
			clickLink(WikisObjects.Menu_Item_4);
		}
	}
	
	/******************************************
	 * Methods used in Comments Functionality *
	 ******************************************/
	
	//Validate Comments
	public int ValidateComments(String IdentifierToBeParsed) throws Exception {
		String TextToBeParsed = sel.getText(IdentifierToBeParsed);

		//Calling ElementCount method to parse the numeric value of the link
		int CurrentNumberOfComments = ElementCount(TextToBeParsed);
		System.out.println ("PASS: Comments link shows there is/are "+CurrentNumberOfComments+ " comment(s) for this page");
		
		return CurrentNumberOfComments;
	}
	
	//Verify that comment has been added
	public void ValidateNewlyAddedComment(String New_Comment, int index) throws Exception {

		if ((index > 20)&(index <= 40))
			index = index - 20;
		
		else if ((index > 40)&(index <= 60))
			index = index - 40;
		
		else if (index > 60)
			index = index - 60;
		
		Assert.assertTrue("FAIL: new comment text is missing", sel.isTextPresent(New_Comment));
		Assert.assertTrue("FAIL: new field for comment is missing", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div["+index+"]/div[2]"));
		Assert.assertTrue("FAIL: Edit link is missing for comment", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div["+index+"]/div[2]/div[2]/div/ul/li/a"));
		Assert.assertTrue("FAIL: Delete icon is missing for comment", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div["+index+"]/div[2]/div[2]/a/img"));
	}
	
	//Validate added comment by count
	public void ValidateCommentsAreAdded(int PreviousCommentsTotal, int NewCommentCount) throws Exception {
		//Verify whether the comments page link is showing the correct number of comments
		Assert.assertTrue("FAIL: on comments being added", NewCommentCount == (PreviousCommentsTotal + 1));
	}
	
	//Verify edited comment
	public void ValidateEditedComment(String EditedComment, int index) throws Exception {
		
		if ((index > 20)&(index <= 40))
			index = index - 20;
		
		else if ((index > 40)&(index <= 60))
			index = index - 40;
		
		else if (index > 60)
			index = index - 60;
		
		String NewComment = sel.getText("xpath=id('wikiPageComments')/div[2]/div["+index+"]/div[2]/div[2]/p");
		Assert.assertTrue("FAIL: Comment hasn't been edited correctly", NewComment.equals(EditedComment));
		Assert.assertTrue("FAIL: Edited comment indicator is missing", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div["+index+"]/span"));
		String CommentIndicator = sel.getText("xpath=id('wikiPageComments')/div[2]/div["+index+"]/span");
		Assert.assertTrue("FAIL: Edited comment indicator is missing", CommentIndicator.contains("Edited today at "));	
	}
	
	/*****************************************
	 * Methods used in tagging Functionality *
	 *****************************************/
	
	//Validate tag widget with single tag
	public void verifyTagWidget(String tag) throws Exception {
		//Validate that Pages Menu is expanded
		CheckTagsSection();
		
		if (sel.isElementPresent(WikisObjects.Link_for_Tag_List)){
			clickLink(WikisObjects.Link_for_Tag_List);
		
			Assert.assertTrue("FAIL: Tag(s) are missing from Tags list", sel.isElementPresent("link=" + tag));
		}
		
		if (sel.isElementPresent(WikisObjects.Link_for_Tag_Cloud)){
			clickLink(WikisObjects.Link_for_Tag_Cloud);
		
			Assert.assertTrue("FAIL: Tag(s) are missing from Cloud list", sel.isElementPresent("link=" + tag));
		}	
	}
	
	//Validate tag widget with single tag
	public void verifyTagWidget(String [] tags, int [] tagCount) throws Exception {
		//Validate that Pages Menu is expanded
		CheckTagsSection();
		
		if (sel.isElementPresent(WikisObjects.Link_for_Tag_List)){
			clickLink(WikisObjects.Link_for_Tag_List);
		
			for (int i = 0; i < tags.length; i++)
				Assert.assertTrue("FAIL: Tag(s) are missing from Tags list", sel.isElementPresent("css=ul[id='lconnTagWidget_tagList'] [title='Filter by the tag "+tags[i]+" with count "+tagCount[i]+"']"));
		}
		
		if (sel.isElementPresent(WikisObjects.Link_for_Tag_Cloud)){
			clickLink(WikisObjects.Link_for_Tag_Cloud);
			
			for (int i = 0; i < tags.length; i++)	
				Assert.assertTrue("FAIL: Tag(s) are missing from Cloud list", sel.isElementPresent("css=div[class='lotusTagCloud'] [title='Filter by the tag "+tags[i]+" with count "+tagCount[i]+"']"));
		}	
	}
	
	/*************************************************
	 * Methods used in Recommendations Functionality *
	 *************************************************/
	
	//Recommend a page
	public void RecommendCurrentPage()throws Exception{
		//Recommend the current page
		if (sel.isElementPresent(WikisObjects.Recommendations_Info_Alternate)){
			clickLink(WikisObjects.Recommendations_Info_Alternate);
		}
		else
			clickLink(WikisObjects.Recommendations_Info);
		
		//Force page refresh to remove recommendation popup
		//RefreshPage();
	}
	
	//Validate recommendation icon
	public void verifyRecommendationIconIsUpdated(int pageRecommends) throws Exception {    
		int index = 1;
		String recommendsValueText = "";
		
		while (index <= 5){
			if(sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + index + "]/td[4]/a")){
				recommendsValueText = sel.getText("xpath=id('list')/div[2]/table/tbody/tr[" + index + "]/td[4]/a");

				Assert.assertTrue("FAIL: Recommendation Icon in Pages Index hasn't been updated", recommendsValueText.contains(Integer.toString(pageRecommends)));
			}
		
			//this.verifyRecommendationUpdatesToIcons(pageRecommends, index);
			index = index + 2;
		}
	}
	
	//Quick test to recommend & unrecommend a page
	public void verifyRemovalOfRecommendation(int pageRecommends) throws Exception {    	
		//Verify that recommendation UI is correct before a user has recommended the page
		verifyRecommendationUI(pageRecommends, true);
		
		//Recommend the current page
		RecommendCurrentPage();
		
		//Decrement page recommendation counter
		pageRecommends--;
		
		//Verify that recommendation UI is correct before a user has recommended the page
		verifyRecommendationUI(pageRecommends, false);
		
		//Renew previous page recommendation scenario
		RecommendCurrentPage();
	}
}

