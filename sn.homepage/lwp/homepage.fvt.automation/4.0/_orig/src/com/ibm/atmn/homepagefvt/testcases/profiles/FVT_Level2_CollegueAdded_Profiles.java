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

package com.ibm.atmn.homepagefvt.testcases.profiles;




import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;



public class FVT_Level2_CollegueAdded_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	
	
	@Test
	public void testCollegueAdded_Accepted () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testCollegueAdded_Accepted");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Search for user
		SearchForUser(CommonData.IC_LDAP_Username_Fullname451);
		
		//Invite User To You network
		InviteToNetwork();		
		
		//Logout of profiles
		Logout();	
		
		//Log in as other user
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Accept Invite
		clickLink("link=My Network");
		
		clickLink("link=Invitations");
		
		clickLink(FVT_ProfilesObjects.AcceptInvite);
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testCollegueAdded_Accepted");
	}
	
	@Test (dependsOnMethods = { "testCollegueAdded_Accepted" })
	public void testCollegueAdded_Rejected () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testCollegueAdded_Rejected");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Search for user
		SearchForUser(CommonData.IC_LDAP_Username_Fullname451);
		
		//Invite User To You network
		InviteToNetwork();		
		
		//Logout of profiles
		Logout();	
		
		//Log in as other user
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Reject Invite
		clickLink("link=My Network");
		
		clickLink("link=Invitations");
		
		clickLink(FVT_ProfilesObjects.RejectInvite);
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testCollegueAdded_Rejected");
	}
	
	
}
