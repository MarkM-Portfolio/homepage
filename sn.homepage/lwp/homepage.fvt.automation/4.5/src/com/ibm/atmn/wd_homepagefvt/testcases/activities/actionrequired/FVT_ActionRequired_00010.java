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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.actionrequired;



import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
import com.ibm.atmn.waffle.extensions.user.User;

public class FVT_ActionRequired_00010 extends FVT_ActivitiesMethods {

	FormInputHandler typist;

	
	private User testUser1;

	@Test 
	public void testVerifyBadgeNotPresent() throws Exception {
			
			
			System.out.println("INFO: Start of Action Required FVT_Level_2 testVerifyBadgeNotPresent");
		
			// Users allocated within the testcase as there is a dependency 
			testUser1 = userAllocator.getUser(this);
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
				
			//Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			//Check that badge is not visible in all views
			verifyActionRequiredBadgeNotVisible();
			
			//Logout
			Logout();
			
			System.out.println("INFO: End of Action Required FVT_Level_2 testVerifyBadgeNotPresent");
			
		}

}