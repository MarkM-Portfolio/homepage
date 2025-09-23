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

package com.ibm.atmn.wd_homepagefvt.testcases.dogear;





import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.dogear.FVT_DogearMethods;


public class FVT_Test_Dogear extends FVT_DogearMethods{
	/*
	 * This is a functional test for the News Component of IBM Connections
	 * Created By: Conor Pelly
	 * Date: 07/05/2011
	 */

	//Log into News and then logout
	
	


	//NOTE: Only the ajones[number](Amy Jones[number]) class of users are valid here without further changes.
	private static String testUser1Username = CommonData.IC_LDAP_Username111;
	private static String testUser3Username = CommonData.IC_LDAP_Username333;
	private static String testUser3Password = CommonData.IC_LDAP_Password333;
	
	
	
	
	public void testAddPersonAndTagToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddPersonAndTagToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones333)
		Login(testUser3Username, testUser3Password);

		//Add person to watchlist
		toggleAddPersonToWatchlist(testUser1Username);
		
		//Add tag to watchlist 
		toggleAddTagToWatchlist("fvttesttag");
		
		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory_JO("FVTTestTag", "Bookmarks", "Public");
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddPersonAndTagToWatchlist Test");

	}

}