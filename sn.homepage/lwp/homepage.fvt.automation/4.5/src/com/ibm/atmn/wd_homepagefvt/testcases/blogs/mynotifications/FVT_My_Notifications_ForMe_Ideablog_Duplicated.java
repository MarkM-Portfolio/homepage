/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.mynotifications;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;

/*
 * TTT ->  AS - MY NOTIFICATIONS - FOR ME - 00031 - ideablog.idea.duplicated - PUBLIC 
 * 
 * Author : Michael Tallant Murphy
 * Description: User1 starts a community , start a ideation blog , user 2 creates a entry in the ideation blog , user1 marks users 2 entry as a duplicate entry , user2 verifies that
 * my notification as received the update changes , user1 verifies that the changes are in I'm Following
 */

public class FVT_My_Notifications_ForMe_Ideablog_Duplicated extends
		FVT_BlogsMethods {

	private User testUser1 = null;
	private User testUser2 = null;
	private User testUser3 = null;
	private User testUser4 = null;
	private User testUser5 = null;
	private User testUser6 = null;
	private String DateTimeStamp = CommonMethods.genDateBasedRandVal();
	private String Blog = FVT_BlogsData.BlogsName1 + DateTimeStamp;
	private String Entry_Pub = FVT_BlogsData.IdeablogNewEntryEntry
			+ DateTimeStamp + "_Public";
	private String Entry_Mod = FVT_BlogsData.IdeablogNewEntryEntry
			+ DateTimeStamp + "_Moderate";
	private String Entry_Res = FVT_BlogsData.IdeablogNewEntryEntry
			+ DateTimeStamp + "_Restricted";
	private String publicCommunity = FVT_CommunitiesData.CommunityName
			+ "_Public_" + DateTimeStamp;
	private String moderateCommunity = FVT_CommunitiesData.CommunityName
			+ "_Moderate_" + DateTimeStamp;
	private String restrictedCommunity = DateTimeStamp
			+ FVT_CommunitiesData.CommunityName + "_Restricted_"
			+ DateTimeStamp;

	@Test
	public void Setup() {

		Reporter.log("Setup");
		// Extra users for Grid (Concurrency / avoiding deadlock between users
		// and nodes)
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		testUser4 = userAllocator.getUser(this);
		testUser5 = userAllocator.getUser(this);
		testUser6 = userAllocator.getUser(this);

		Reporter.log("User 1 -> " + testUser1.getDisplayName());
		Reporter.log("User 2 -> " + testUser2.getDisplayName());
		Reporter.log("User 3 -> " + testUser3.getDisplayName());
		Reporter.log("User 4 -> " + testUser4.getDisplayName());
		Reporter.log("User 5 -> " + testUser5.getDisplayName());
		Reporter.log("User 6 -> " + testUser6.getDisplayName());
		Reporter.log("Blog -> " + Blog);

		// Loging in as 2nd users for type-a-heads
		LoadComponent(CommonObjects.ComponentCommunities);
		Login(testUser2);
		LogoutAndClose();
		LoadComponent(CommonObjects.ComponentCommunities);
		Login(testUser4);
		LogoutAndClose();
		LoadComponent(CommonObjects.ComponentCommunities);
		Login(testUser6);
		LogoutAndClose();
	}

	@Test(dependsOnMethods = { "Setup" })
	public void startPublicCommunityWithIdeationBlog() throws Exception {

		Reporter.log("Start of Test :: createCommunityWithIdeationBlog");

		// Load Activities
		LoadComponent(CommonObjects.ComponentCommunities);
		Login(testUser1);

		// Create a Community
		driver.getFirstElement("css=span#createPlaceButton.lotusBtn a").click();
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityName).type(
				publicCommunity);
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityTag).type(
				FVT_CommunitiesData.CommunityTag);
		driver.getFirstElement(FVT_WikisObjects.Save_Button).click();

		assertTrue("FAIL :: Community title wasn't found ", driver
				.isTextPresent(publicCommunity));

		// Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		// Choose the 3 option - Customize
		driver.getSingleElement(CommunitiesObjects.Menu_Item_3).click();
		driver.getFirstElement(CommunitiesObjects.WidgetIdeationBlog).click();

		driver.getFirstElement(CommunitiesObjects.CloseWidgetSection).click();

		// Click on the Ideation Blog Nav bar Link
		driver.getFirstElement(CommunitiesObjects.LeftNav_IdeationBlog).click();
		driver.getFirstElement(FVT_BlogsObjects.NewIdea).click();

		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryTitle).type(
				Entry_Pub);
		typeNativeInCkEditor(FVT_BlogsData.BlogsDescription + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryPost).click();

		assertTrue("FAIL :: Ideation Blog wasn't found ", driver
				.isTextPresent(Entry_Pub));

		driver.getFirstElement("link=Members").click();
		driver.getFirstElement("css=a[id='memberAddButtonLink']").click();
		addingMemberUserNameTypeAhead(testUser2.getDisplayName());
		/*
		 * MembersTypeAhead(testUser2.getDisplayName(),
		 * "css=input[id='addMembersWidgetPeopleTypeAhead']");
		 * driver.getFirstElement
		 * ("css=li[id='inviteMembersWidgetPeopleTypeAhead_popup0']").click();
		 */
		driver.getFirstElement(FVT_BlogsObjects.AddMemberSaveBtn).click();

		Reporter.log("End of Test :: createCommunityWithIdeationBlog");

		LogoutAndClose();

	}

	@Test(dependsOnMethods = { "startPublicCommunityWithIdeationBlog" })
	public void startADuplicateIdea_Public() {

		Reporter.log("Start of Test :: startADuplicateIdea");

		// Load Activities
		LoadComponent(CommonObjects.ComponentBlogs);
		Login(testUser2);

		driver.getFirstElement(FVT_BlogsObjects.PublicBlogs).click();
		driver.getFirstElement("link=Blogs Listing").click();
		driver.getFirstElement("link=" + publicCommunity).click();

		driver.getFirstElement(FVT_BlogsObjects.NewIdea).click();

		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryTitle).type(
				Entry_Pub + "_startADuplicateIdea");
		typeNativeInCkEditor(FVT_BlogsData.BlogsDescription + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryPost).click();

		assertTrue("FAIL :: Ideation Blog wasn't found ", driver
				.isTextPresent(Entry_Pub + "_startADuplicateIdea"));

		LogoutAndClose();

		LoadComponent(CommonObjects.ComponentBlogs);
		Login(testUser1);

		driver.getFirstElement(FVT_BlogsObjects.PublicBlogs).click();
		driver.getFirstElement("link=Blogs Listing").click();
		driver.getFirstElement("link=" + publicCommunity).click();
		driver.getFirstElement("link=" + Entry_Pub + "_startADuplicateIdea")
				.click();

		// Click on more actions
		driver.getFirstElement("css=a[id='focusMoreActionsLink']").click();
		// Click on mark as duplicate
		driver.getFirstElement("css=tr[id='duplicateEntryLink']").click();
		// Enter the duplicate idea
		driver.getFirstElement("css=input[id='ideasAsString']").type(Entry_Pub);
		// Select the 1st item on the type ahead
		driver.getFirstElement("css=li[id='ideasAsString_popup0']").click();
		// Click on the save button
		driver.getFirstElement("css=input[id='dupIdeaSaveBtn']").click();
		// Click on the OK
		driver.getFirstElement("css=input[value='OK']").click();

		assertTrue("FAIL :: Idea wasn't marked as a Duplicated ", driver
				.isTextPresent("The idea has been marked as Duplicated."));

		LogoutAndClose();
	}

	@Test(dependsOnMethods = { "startADuplicateIdea_Public" })
	public void verifyDuplication_Public() throws Exception {

		//
		LoadComponent(CommonObjects.ComponentHomepage);
		Login(testUser2);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		String checkingFor = testUser1.getDisplayName() + " marked your "
				+ Entry_Pub + "_startADuplicateIdea idea in the "
				+ publicCommunity + " Ideation Blog as a duplicate of idea "
				+ Entry_Pub + ".";
		Reporter.log("Debug -> " + checkingFor);
		assertTrue("FAIL :: Duplication text isn't present", driver
				.isTextPresent(checkingFor));

		filterBy("Blogs");
		assertTrue(
				"FAIL :: Blogs filter applied, duplication text isn't present",
				driver.isTextPresent(checkingFor));

		LogoutAndClose();

		checkingFor = testUser1.getDisplayName() + " marked the " + Entry_Pub
				+ "_startADuplicateIdea idea in the " + publicCommunity
				+ " Ideation Blog as a duplicate of idea " + Entry_Pub + ".";

		LoadComponent(CommonObjects.ComponentHomepage);
		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.ImFollowing).click();

		Reporter.log("Debug -> " + checkingFor);
		assertTrue("FAIL :: Duplication text isn't present", driver
				.isTextPresent(checkingFor));

		filterBy("Blogs");
		assertTrue(
				"FAIL :: Blogs filter applied, duplication text isn't present",
				driver.isTextPresent(checkingFor));

		LogoutAndClose();
	}

	@Test(dependsOnMethods = { "Setup" })
	public void startModerateCommunityWithIdeationBlog() throws Exception {

		Reporter.log("Start of Test :: createCommunityWithIdeationBlog");

		// Load Activities
		LoadComponent(CommonObjects.ComponentCommunities);
		Login(testUser3);

		// Create a Community
		driver.getFirstElement("css=span#createPlaceButton.lotusBtn a").click();
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityName).type(
				moderateCommunity);
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityTag).type(
				FVT_CommunitiesData.CommunityTag);
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityAccessOption2)
				.click();
		driver.getFirstElement(FVT_WikisObjects.Save_Button).click();

		assertTrue("FAIL :: Community title wasn't found ", driver
				.isTextPresent(moderateCommunity));

		// Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		// Choose the 3 option - Customize
		driver.getSingleElement(CommunitiesObjects.Menu_Item_3).click();
		driver.getFirstElement(CommunitiesObjects.WidgetIdeationBlog).click();

		driver.getFirstElement(CommunitiesObjects.CloseWidgetSection).click();

		// Click on the Ideation Blog Nav bar Link
		driver.getFirstElement(CommunitiesObjects.LeftNav_IdeationBlog).click();
		driver.getFirstElement(FVT_BlogsObjects.NewIdea).click();

		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryTitle).type(
				Entry_Mod);
		typeNativeInCkEditor(FVT_BlogsData.BlogsDescription + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryPost).click();

		assertTrue("FAIL :: Ideation Blog wasn't found ", driver
				.isTextPresent(Entry_Mod));

		driver.getFirstElement("link=Members").click();
		driver.getFirstElement("css=a[id='memberAddButtonLink']").click();
		addingMemberUserNameTypeAhead(testUser2.getDisplayName());
		/*
		 * MembersTypeAhead(testUser2.getDisplayName(),
		 * "css=input[id='addMembersWidgetPeopleTypeAhead']");
		 * driver.getFirstElement
		 * ("css=li[id='inviteMembersWidgetPeopleTypeAhead_popup0']").click();
		 */
		driver.getFirstElement(FVT_BlogsObjects.AddMemberSaveBtn).click();

		Reporter.log("End of Test :: createCommunityWithIdeationBlog");

		LogoutAndClose();

	}

	@Test(dependsOnMethods = { "startModerateCommunityWithIdeationBlog" })
	public void startADuplicateIdea_Moderate() {

		Reporter.log("Start of Test :: startADuplicateIdea");

		// Load Activities
		LoadComponent(CommonObjects.ComponentBlogs);
		Login(testUser4);

		driver.getFirstElement(FVT_BlogsObjects.PublicBlogs).click();
		driver.getFirstElement("link=Blogs Listing").click();
		driver.getFirstElement("link=" + moderateCommunity).click();

		driver.getFirstElement(FVT_BlogsObjects.NewIdea).click();

		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryTitle).type(
				Entry_Mod + "_startADuplicateIdea");
		typeNativeInCkEditor(FVT_BlogsData.BlogsDescription + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryPost).click();

		assertTrue("FAIL :: Ideation Blog wasn't found ", driver
				.isTextPresent(Entry_Mod + "_startADuplicateIdea"));

		LogoutAndClose();

		LoadComponent(CommonObjects.ComponentBlogs);
		Login(testUser3);

		driver.getFirstElement(FVT_BlogsObjects.PublicBlogs).click();
		driver.getFirstElement("link=Blogs Listing").click();
		driver.getFirstElement("link=" + moderateCommunity).click();
		driver.getFirstElement("link=" + Entry_Mod + "_startADuplicateIdea")
				.click();

		// Click on more actions
		driver.getFirstElement("css=a[id='focusMoreActionsLink']").click();
		// Click on mark as duplicate
		driver.getFirstElement("css=tr[id='duplicateEntryLink']").click();
		// Enter the duplicate idea
		driver.getFirstElement("css=input[id='ideasAsString']").clear();
		driver.getFirstElement("css=input[id='ideasAsString']").type(Entry_Mod);
		// Select the 1st item on the type ahead
		driver.getFirstElement("css=li[id='ideasAsString_popup0']").click();
		// Click on the save button
		driver.getFirstElement("css=input[id='dupIdeaSaveBtn']").click();
		// Click on the OK
		driver.getFirstElement("css=input[value='OK']").click();

		assertTrue("FAIL :: Idea wasn't marked as a Duplicated ", driver
				.isTextPresent("The idea has been marked as Duplicated."));

		LogoutAndClose();
	}

	@Test(dependsOnMethods = { "startADuplicateIdea_Moderate" })
	public void verifyDuplication_Moderate() throws Exception {

		//
		LoadComponent(CommonObjects.ComponentHomepage);
		Login(testUser4);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		String checkingFor = testUser1.getDisplayName() + " marked your "
				+ Entry_Mod + "_startADuplicateIdea idea in the "
				+ moderateCommunity + " Ideation Blog as a duplicate of idea "
				+ Entry_Mod + ".";
		Reporter.log("Debug -> " + checkingFor);
		assertTrue("FAIL :: Duplication text isn't present", driver
				.isTextPresent(checkingFor));

		filterBy("Blogs");
		assertTrue(
				"FAIL :: Blogs filter applied, duplication text isn't present",
				driver.isTextPresent(checkingFor));

		LogoutAndClose();

		checkingFor = testUser1.getDisplayName() + " marked the " + Entry_Mod
				+ "_startADuplicateIdea idea in the " + moderateCommunity
				+ " Ideation Blog as a duplicate of idea " + Entry_Mod + ".";

		LoadComponent(CommonObjects.ComponentHomepage);
		Login(testUser3);

		driver.getFirstElement(FVT_HomepageObjects.ImFollowing).click();

		Reporter.log("Debug -> " + checkingFor);
		assertTrue("FAIL :: Duplication text isn't present", driver
				.isTextPresent(checkingFor));

		filterBy("Blogs");
		assertTrue(
				"FAIL :: Blogs filter applied, duplication text isn't present",
				driver.isTextPresent(checkingFor));

		LogoutAndClose();
	}

	@Test(dependsOnMethods = { "Setup" })
	public void startRestrictedCommunityWithIdeationBlog() throws Exception {

		Reporter.log("Start of Test :: createCommunityWithIdeationBlog");

		// Load Activities
		LoadComponent(CommonObjects.ComponentCommunities);
		Login(testUser5);

		// Create a Community
		driver.getFirstElement("css=span#createPlaceButton.lotusBtn a").click();
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityName).type(
				restrictedCommunity);
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityTag).type(
				FVT_CommunitiesData.CommunityTag);
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityAccessOption3)
				.click();
		driver.getFirstElement(FVT_WikisObjects.Save_Button).click();

		assertTrue("FAIL :: Community title wasn't found ", driver
				.isTextPresent(restrictedCommunity));

		// Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		// Choose the 3 option - Customize
		driver.getSingleElement(CommunitiesObjects.Menu_Item_3).click();
		driver.getFirstElement(CommunitiesObjects.WidgetIdeationBlog).click();

		driver.getFirstElement(CommunitiesObjects.CloseWidgetSection).click();

		// Click on the Ideation Blog Nav bar Link
		driver.getFirstElement(CommunitiesObjects.LeftNav_IdeationBlog).click();
		driver.getFirstElement(FVT_BlogsObjects.NewIdea).click();

		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryTitle).type(
				Entry_Res);
		typeNativeInCkEditor(FVT_BlogsData.BlogsDescription + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryPost).click();

		assertTrue("FAIL :: Ideation Blog wasn't found ", driver
				.isTextPresent(Entry_Res));

		driver.getFirstElement("link=Members").click();
		driver.getFirstElement("css=a[id='memberAddButtonLink']").click();
		addingMemberUserNameTypeAhead(testUser2.getDisplayName());
		/*
		 * MembersTypeAhead(testUser2.getDisplayName(),
		 * "css=input[id='addMembersWidgetPeopleTypeAhead']");
		 * driver.getFirstElement
		 * ("css=li[id='inviteMembersWidgetPeopleTypeAhead_popup0']").click();
		 */
		driver.getFirstElement(FVT_BlogsObjects.AddMemberSaveBtn).click();

		Reporter.log("End of Test :: createCommunityWithIdeationBlog");

		LogoutAndClose();

	}

	@Test(dependsOnMethods = { "startRestrictedCommunityWithIdeationBlog" })
	public void startADuplicateIdea_Restricted() {

		Reporter.log("Start of Test :: startADuplicateIdea");

		// Load Activities
		LoadComponent(CommonObjects.ComponentBlogs);
		Login(testUser6);

		driver.getFirstElement(FVT_BlogsObjects.MyBlogs).click();
		driver.getFirstElement("css=a:contains('" + DateTimeStamp + "')")
				.click();

		driver.getFirstElement(FVT_BlogsObjects.NewIdea).click();

		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryTitle).type(
				Entry_Res + "_startADuplicateIdea");
		typeNativeInCkEditor(FVT_BlogsData.BlogsDescription + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryPost).click();

		assertTrue("FAIL :: Ideation Blog wasn't found ", driver
				.isTextPresent(Entry_Res + "_startADuplicateIdea"));

		LogoutAndClose();

		LoadComponent(CommonObjects.ComponentBlogs);
		Login(testUser5);

		driver.getFirstElement(FVT_BlogsObjects.MyBlogs).click();
		driver.getFirstElement("css=a:contains('" + DateTimeStamp + "')")
				.click();
		// driver.getFirstElement("link="+restrictedCommunity).click();
		driver.getFirstElement("link=" + Entry_Res + "_startADuplicateIdea")
				.click();

		// Click on more actions
		driver.getFirstElement("css=a[id='focusMoreActionsLink']").click();
		// Click on mark as duplicate
		driver.getFirstElement("css=tr[id='duplicateEntryLink']").click();
		// Enter the duplicate idea
		driver.getFirstElement("css=input[id='ideasAsString']").clear();
		driver.getFirstElement("css=input[id='ideasAsString']").type(Entry_Res);
		// Select the 1st item on the type ahead
		driver.getFirstElement("css=li[id='ideasAsString_popup0']").click();
		// Click on the save button
		driver.getFirstElement("css=input[id='dupIdeaSaveBtn']").click();
		// Click on the OK
		driver.getFirstElement("css=input[value='OK']").click();

		assertTrue("FAIL :: Idea wasn't marked as a Duplicated ", driver
				.isTextPresent("The idea has been marked as Duplicated."));

		LogoutAndClose();
	}

	@Test(dependsOnMethods = { "startADuplicateIdea_Restricted" })
	public void verifyDuplication_Restricted() throws Exception {

		//
		LoadComponent(CommonObjects.ComponentHomepage);
		Login(testUser6);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		String checkingFor = testUser1.getDisplayName() + " marked your "
				+ Entry_Res + "_startADuplicateIdea idea in the "
				+ restrictedCommunity
				+ " Ideation Blog as a duplicate of idea " + Entry_Res + ".";
		Reporter.log("Debug -> " + checkingFor);
		assertTrue("FAIL :: Duplication text isn't present", driver
				.isTextPresent(checkingFor));

		filterBy("Blogs");
		assertTrue(
				"FAIL :: Blogs filter applied, duplication text isn't present",
				driver.isTextPresent(checkingFor));

		LogoutAndClose();

		checkingFor = testUser1.getDisplayName() + " marked the " + Entry_Res
				+ "_startADuplicateIdea idea in the " + restrictedCommunity
				+ " Ideation Blog as a duplicate of idea " + Entry_Res + ".";

		LoadComponent(CommonObjects.ComponentHomepage);
		Login(testUser5);

		driver.getFirstElement(FVT_HomepageObjects.ImFollowing).click();

		Reporter.log("Debug -> " + checkingFor);
		assertTrue("FAIL :: Duplication text isn't present", driver
				.isTextPresent(checkingFor));

		filterBy("Blogs");
		assertTrue(
				"FAIL :: Blogs filter applied, duplication text isn't present",
				driver.isTextPresent(checkingFor));

		LogoutAndClose();
	}

}
