/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.testcases.mediagallery;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.lc.appobjects.mediagallery.MediaGalleryData;
import com.ibm.atmn.lc.appobjects.mediagallery.MediaGalleryObjects;
import com.ibm.atmn.lc.tasks.mediagallery.MediaGalleryMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_MediaGallery extends MediaGalleryMethods {

	/*
	 * This is a functional test for the Media Gallery feature of IBM Connections Created By: Conor Pelly Date:
	 * 09/30/2011
	 */

	public BVT_Level2_MediaGallery() {

	}

	//Log into News and then logout
	public void MediaGallery() throws Exception {

	}

	//Log into News and then logout
	@Test
	public void testMediaGallery_UploadVideo() throws Exception {

		/*
		 * Upload a video to the Media Gallery Verify that the video has been added
		 */

		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 1");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a video from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadVideo, "Test.mp4");

		//Click on the View All link
		clickLink(MediaGalleryObjects.MediaGalleryViewAllLink);

		Assert.assertEquals(MediaGalleryData.MediaGalleryViewCountOneUpload, sel.getText(MediaGalleryObjects.MediaGalleryViewCount));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 1");
	}

	@Test
	public void testMediaGallery_UploadPhoto() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Verify that the photo was uploaded
		 */

		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadPhoto, "Desert.jpg");

		//Click on the View All link
		clickLink(MediaGalleryObjects.MediaGalleryViewAllLink);

		Assert.assertEquals(MediaGalleryData.MediaGalleryViewCountOneUpload, sel.getText(MediaGalleryObjects.MediaGalleryViewCount));

		//826 KB (859 KB including versions and thumbnails)
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 2");
	}

	@Test
	public void testMediaGallery_UploadPhoto_AddComments() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Add a comment to the uploaded photo Verify that the comment has been
		 * added successfully
		 */

		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadPhoto, "Desert.jpg");

		//Click on the View All link
		clickLink(MediaGalleryObjects.MediaGalleryViewAllLink);

		//Ensure that the photo is present
		String sPhotoName = sel.getText(MediaGalleryObjects.SinglePhotoUpload);
		if (sPhotoName.contains("Desert.jpg")) {
			clickLink(MediaGalleryObjects.SinglePhotoUpload);
		}
		else {
			System.out.println("\nTEST NOTICE: Uploaded photo not found. Link is : " + sPhotoName);
			Assert.fail("Uploaded photo not found. Link is : " + sPhotoName);
		}

		//Click Comments tab. There should be no comments in place. Ensure that comments tab
		// is selected. Using sel click as if in Comments tab page will not change
		sel.click(MediaGalleryObjects.CommentsTab + " (0)");

		//Add a comment
		System.out.println("\nTEST STEP: Adding Comment to uploaded photo.");
		AddComments("Comment on jpg");

		//Verify the comments has been added
		Assert.assertEquals("Comment on jpg", sel.getText(MediaGalleryObjects.AddedComment));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 3");

	}

	@Test
	public void testMediaGallery_UploadPhoto_EditComments() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Add a comment to the uploaded photo Edit the comment Verify the comment
		 * has been edited
		 */

		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 4");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadPhoto, "Desert.jpg");

		//Click on the View All link
		clickLink(MediaGalleryObjects.MediaGalleryViewAllLink);

		//Ensure that the photo is present
		String sPhotoName = sel.getText(MediaGalleryObjects.SinglePhotoUpload);
		if (sPhotoName.contains("Desert.jpg")) {
			clickLink(MediaGalleryObjects.SinglePhotoUpload);
		}
		else {
			System.out.println("\nTEST NOTICE: Uploaded photo not found. Link is : " + sPhotoName);
			Assert.fail("Uploaded photo not found. Link is : " + sPhotoName);
		}

		//Click Comments tab. There should be no comments in place
		sel.click(MediaGalleryObjects.CommentsTab + " (0)");

		//Add a comment
		AddComments("Comment on jpg");

		//Edit the comment
		System.out.println("\nTEST STEP: Edit Comment on uploaded photo.");
		EditComments("Updated Comments ");

		//Verify the comments has been edited
		Assert.assertEquals("Updated Comments Comment on jpg", sel.getText(MediaGalleryObjects.AddedComment));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 4");

	}

	@Test
	public void testMediaGallery_UploadPhoto_DeleteComments() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Add a comment to the uploaded photo Delete the comment Verify the comment
		 * has been deleted
		 */
		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 5");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadPhoto, "Desert.jpg");

		//Click on the View All link
		clickLink(MediaGalleryObjects.MediaGalleryViewAllLink);

		//Ensure that the photo is present
		if (sel.getText(MediaGalleryObjects.SinglePhotoUpload).contains("Desert.jpg")) {
			clickLink(MediaGalleryObjects.SinglePhotoUpload);
		}
		else {
			System.out.println("\nTEST NOTICE: Uploaded photo not found.");
			Assert.fail("Uploaded photo not found.");
		}

		//Click Comments tab. There should be no comments in place
		sel.click(MediaGalleryObjects.CommentsTab + " (0)");

		//Add a comment
		AddComments("Comment on jpg");

		//Delete the comment
		System.out.println("\nTEST STEP: Delete Comment on uploaded photo.");
		DeleteComment();

		//Verify the comment has been deleted
		Assert.assertFalse(sel.isElementPresent(MediaGalleryObjects.AddedComment), "Comment should be deleted.");

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 5");

	}

	@Test
	public void testMediaGallery_UploadPhoto_Recommendation() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Recommend the photo Verify that the photo is now recommended
		 */
		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 6");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadPhoto, "Desert.jpg");

		//Click on the View All link
		clickLink(MediaGalleryObjects.MediaGalleryViewAllLink);

		//Ensure that the photo is available
		if (sel.getText(MediaGalleryObjects.SinglePhotoUpload).contains("Desert.jpg")) {
			clickLink(MediaGalleryObjects.SinglePhotoUpload);
		}
		else {
			System.out.println("\nTEST NOTICE: Uploaded photo not found.");
		}

		//Recommend the photo
		System.out.println("\nTEST STEP: Recommend the uploaded photo.");
		RecommendMediaGalleryItem();

		//Verify that the photo has been recommended
		Assert.assertEquals("You have recommended", sel.getText(MediaGalleryObjects.RecommendMediaGalleryFile));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 6");

	}

	@Test
	public void testtestMediaGallery_UploadPhoto_EditProperties() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Edit the Properties of the photo Verify that the photo properties are
		 * updated
		 */

		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 7");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadPhoto, "Desert.jpg");

		//Click on the View All link
		clickLink(MediaGalleryObjects.MediaGalleryViewAllLink);

		//Open the uploaded photo
		clickLink(MediaGalleryObjects.SinglePhotoUpload);

		//Click Edit Properties button
		//Edit Tag, Description, Metadata and Save the changes
		System.out.println("\nTEST STEP: Edit the uploaded photo properties.");
		EditMediaGalleryItemProperties();

		//Verify the updated properties
		VerifyMediaGalleryEditProperties();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 7");

	}

	@Test
	public void testMediaGallery_UploadPhoto_AboutThisFile() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Select the About this File tab Verify that the About this File data is
		 * correct
		 */

		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 8");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadPhoto, "Desert.jpg");

		//Click on the View All link
		clickLink(MediaGalleryObjects.MediaGalleryViewAllLink);

		//Open the uploaded photo		
		clickLink(MediaGalleryObjects.SinglePhotoUpload);

		//Open the About this File tab
		clickLink(MediaGalleryObjects.AboutThisFileTab);

		//Verify About the File information
		String[] keywords = { "Created:", "Any update:", "Size:", "826 KB", "Downloads:", "0", CommonData.LDAP_FullUsername };

		for (String s : keywords) {
			System.out.println("\nValue of s : " + s);
			Assert.assertTrue(sel.isTextPresent(s), "Expected : " + s);
		}

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 8");

	}

	@Test
	public void MediaGallery_UploadPhoto_DownloadFile() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Download the photo
		 */

	}

	@Test
	public void MediaGallery_UploadPhoto_ReplaceFile() throws Exception {

	}

	@Test
	public void testMediaGalleryFromLeftNavLink() throws Exception {

		/*
		 * Upload a photo and video to the Media Gallery using the link
		 * 
		 * Verify that the photo and video were uploaded
		 */
		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 9");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Add a photo
		UploadFileFromMediaGallery(MediaGalleryObjects.MediaGalleryUploadPhotoButton, "Desert.jpg");

		//Add a video
		UploadFileFromMediaGallery(MediaGalleryObjects.MediaGalleryUploadVideoButton, "Test.mp4");

		Assert.assertEquals(MediaGalleryData.MediaGalleryViewCount, sel.getText(MediaGalleryObjects.MediaGalleryViewCount));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 9");
	}

	@Test
	public void testMediaGallery_Delete_Photo() throws Exception {

		/*
		 * Upload a photo to the Media Gallery Delete the photo Verify that the photo is deleted
		 */

		//Setup
		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 10");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadPhoto, "Desert.jpg");

		if (sel.isTextPresent("Upload successful")) {
			System.out.println("\nTEST::The photo was uploaded successfully.");
		}
		else {
			System.out.println("\nTEST::The photo was not uploaded successfully.");
		}

		//Delete the uploaded photo
		DeleteMediaGalleryFile("Desert.jpg");

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 10");
	}

	@Test
	public void testMediaGallery_Delete_Video() throws Exception {

		/*
		 * Upload a video to the Media Gallery Delete the video Verify that the video is deleted
		 */
		//Setup
		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 12");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Upload a photo from the widget
		UploadFileFromMediaGalleryWidget(MediaGalleryObjects.MediaGalleryUploadVideo, "Test.mp4");

		//Delete the uplodaded video
		DeleteMediaGalleryFile("test.mp4");

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 12");
	}

	@Test
	public void MediaGallery_Delete_MediaGallery() throws Exception {

		/*
		 * Create a new Media Gallery
		 */

		//Setup
		System.out.println("INFO: Start of MediaGallery Level 2 BVT Test 11");

		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Create a community for this test
		CreateCommunity(CommunitiesObjects.CommunityName, MediaGalleryData.CommunityNameForMediaGallery + genDateBasedRandVal(), CommunitiesObjects.CommunityTag,
				MediaGalleryData.CommunityTagForMediaGallery + genDateBasedRandVal());

		//Add Media Gallery
		AddMediaGalleryWidgetToCommunity(CommunitiesObjects.Menu_Item_3);

		//Remove the media gallery

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of MediaGallery Level 2 BVT Test 11");
	}

}
