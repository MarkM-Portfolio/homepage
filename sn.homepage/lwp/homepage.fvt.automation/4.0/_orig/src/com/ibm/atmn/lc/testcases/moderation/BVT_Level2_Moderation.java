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

package com.ibm.atmn.lc.testcases.moderation;

import org.testng.annotations.Listeners;
import com.ibm.atmn.base.SetUpMethods;
import com.ibm.atmn.base.User;
import com.ibm.atmn.base.UserAllocation;
import com.ibm.atmn.lc.tasks.moderation.ModerationMethods;
import org.testng.annotations.Test;
import org.testng.Reporter;
import org.testng.Assert;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesData;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.lc.appobjects.moderation.ModerationObjects;


@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Moderation extends ModerationMethods  {

	// Setup the users required
	private User testAdmin;
	private User testUser1;
	private User testUser2;
	private User testModerator;
	
	@Test(groups = { "moderation", "level-twos", "moderation-level-twos" }, dependsOnGroups = {})
	public void testModerationLevel2() throws Exception {

		System.out.println("INFO: Start of Moderation BVT (Level 2)");
		Reporter.log("INFO: Start of Moderation BVT (Level 2)");

		UserAllocation allocator = UserAllocation.getInstance();
		testAdmin = allocator.getAdminUser();
		testUser1 = allocator.getUser();
		testUser2 = allocator.getUser();
		testModerator = allocator.getModUser();
		
		System.out.println(testAdmin.getUid());
		System.out.println(testUser1.getUid());
		System.out.println(testUser2.getUid());
		System.out.println(testModerator.getUid());
		
		try {
			// Load the moderation app to ensure it is enabled.
			
			// Login to Communities
			// Load the component
			LoadComponent(CommonObjects.ComponentCommunities);

			//Login as a user
			Login(testUser1.getUid(), testUser1.getPassword());
					
			// Login based on User instance and config option
			//Login(testUser1,Login.Type);
			
			//Now Get the DateTime
			String DateTimeStamp = SetUpMethods.genDateBasedRandVal();
			String TestcaseCommunityName = "Moderation BVT Community" + DateTimeStamp;
			
			//Create a public community
			CreateNewCommunity(TestcaseCommunityName, CommunitiesData.CommunityHandle + DateTimeStamp, CommunitiesObjects.CommunityAccessOption1, "Members",
					CommunitiesObjects.CommunityMembersTypeAhead, CommunitiesObjects.selectedUserIdentifier, testUser2.getLastName(),
					CommunitiesObjects.fullUserSearchIdentifier);		
			
			// Add the Ideation and Blog widgets
			AddWidgetToCommunity(CommunitiesObjects.WidgetIdeationBlog);
			AddWidgetToCommunity(CommunitiesObjects.WidgetBlog);
			
			// Log out the user
			Logout();
			
			System.out.println("INFO: Second user logging in.");
			if (sel.isElementPresent(CommonObjects.Login_Link)) {
				sel.click(CommonObjects.Login_Link);
			}
			Login(testUser2.getUid(), testUser2.getPassword());
			
			// Join the community
			if (sel.isTextPresent(TestcaseCommunityName)){
				clickLink("link=" + TestcaseCommunityName);
			}else{
				System.out.println("Could not open community. Refreshing page.");
				sel.refresh();
				if (sel.isTextPresent(TestcaseCommunityName) == false) {
					Assert.fail("Community not present. exiting test. Expected Community :: " + TestcaseCommunityName);
				}else{
					clickLink("link=" + TestcaseCommunityName);	
				}
			}
			
			if (sel.isElementPresent(CommunitiesObjects.JoinACommunity)){			
				clickLink(CommunitiesObjects.JoinACommunity);
			}else{
				System.out.println("Join a community button not present. exiting test");
				Assert.fail("Join a community button not present. exiting test");
			}
			
			// check for text :: Welcome to the community, thank you for joining
			if (sel.isTextPresent("thank you for joining")){
				System.out.println("You have joined the community");
			}
			
			// Create Blog Entry
			String NewBlogEntry = "NewBlogEntry "  + DateTimeStamp;
			CreateANewBlogEntry(NewBlogEntry);
			
			// Verify that it has been submitted for approval
			if (sel.isTextPresent("Your entry has been submitted to moderator for approval.")){
				System.out.println("Entry submitted for approval");
				
			}else{
				System.out.println("Entry not submitted for approval");
				Assert.fail("Entry not submitted for approval");			
			}
			
			// Create idea blog
			CreateANewIdea("Brand New Idea" + DateTimeStamp);
			
			// Verify that it has been submitted for approval
			if (sel.isTextPresent("Your content has been submitted to moderator for approval")){
				System.out.println("Idea submitted for approval");
			}else{
				System.out.println("Idea not submitted for approval");
				Assert.fail("Idea not submitted for approval");
			}
			
			
			// Create forum topic
			String topicRandomNumber = genDateBasedRandVal();
			String part1TopicTitle = "Moderation Level 2 BVT " + topicRandomNumber;
			CreateAForumTopic(part1TopicTitle);
			
			// Verify that it has been submitted for approval
			if (sel.isTextPresent("This topic is awaiting review by the moderator.")){
				System.out.println("Topic submitted for approval");
			}else{
				System.out.println("Topic not submitted for approval");
				Assert.fail("Topic not submitted for approval");
			}
			
			
			// share a file
			String randomName = genDateBasedRandVal();

			//Upload community-owned file
			uploadFileToModeratedCommunity(randomName, ".jpg", "Desert.jpg");
			
			// Verify that it has been submitted for approval
			if (sel.isTextPresent("submitted for review and will be available when approved")){
				System.out.println("File submitted for approval");
			}else{
				System.out.println("File not submitted for approval");
				Assert.fail("File not submitted for approval");
			}
					
			// verify each has been created.
			
			Logout();
			
			// Login with the community owner again to moderation
			System.out.println("INFO: First user logging in.");
			if (sel.isElementPresent(CommonObjects.Login_Link)) {
				sel.click(CommonObjects.Login_Link);
			}
			Login(testUser1.getUid(), testUser1.getPassword());
			
			// Verify that the items created by the second user are available in content approval
			ModerateMyCommunity(TestcaseCommunityName);
			
			//sel.click(ModerationObjects.ContentApproval_Test);
			String[] all = sel.getAllLinks();
			System.out.println(all);
			for (String x:all){
				System.out.println(x.toString());
			
				//sel.getElementIndex(x);
				
			}
			
			// Open Content Approval
			sel.click(ModerationObjects.ContentApproval);
			
			// Open Files - Verify file uploaded is present
			//sel.click(ModerationObjects.Moderation_Files);
			//Thread.sleep(1000);
			//clickLink(ModerationObjects.Moderation_Files_Content);
			//Thread.sleep(1000);
			
			// Check for the file and authors text : element and text
			
			
			// Open Forums - Verify Topic is present
			sel.click(ModerationObjects.Moderation_Forums);
			Thread.sleep(1000);
			sel.click(ModerationObjects.Moderation_Forums_Posts);
			Thread.sleep(1000);
			
						
			// Open Blog - Verify Entry and New Idea are present
			sel.click(ModerationObjects.Moderation_Blogs);
			Thread.sleep(1000);			
			sel.click(ModerationObjects.Moderation_Blogs_Entries);
			Thread.sleep(1000);
			
			// Logout
			Logout();
			
			// Login with the moderator
			LoadComponent(CommonObjects.ComponentModeration);

			//Login as a moderator
			System.out.println("INFO: Moderator logging in.");
			Thread.sleep(10000);
			Login(testModerator.getUid(), testModerator.getPassword());
			
			// Validate the content approval
			
			// Search for the items in the approval view
			
			// Logout
			Logout();
			
			System.out.println("INFO: End of Moderation BVT (Level 2)");
			Reporter.log("INFO: End of Moderation BVT (Level 2)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
		
	}
	
}
