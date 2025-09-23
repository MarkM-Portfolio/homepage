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

package com.ibm.atmn.lc.testcases.metrics;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.metrics.MetricsData;
import com.ibm.atmn.lc.appobjects.metrics.MetricsObjects;
import com.ibm.atmn.lc.tasks.metrics.MetricsMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Metrics extends MetricsMethods {

	/*
	 * This is a functional test for the Metrics Component of IBM Connections Created By: Conor Pelly Date: 09/20/2011
	 */

	//Log into Metrics and then logout
	public void LoadMetricsAndVerify() throws Exception {

		System.out.println("INFO: Start of Metrics Level 2 BVT Test 1");

		//Load the component
		LoadComponent(CommonObjects.ComponentMetrics);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Verify that the Metrics page is loaded
		assertTrue(sel.isElementPresent("link=Metrics"));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Metrics Level 2 BVT Test 1");
	}

	public void UnAuthorizedUserCannotLogin() throws Exception {

		System.out.println("INFO: Start of Metrics Level 2 BVT Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentMetrics);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Verify that the Metrics page is loaded
		assertTrue(sel.isTextPresent("Access to Metrics is Restricted"));

		System.out.println("INFO: End of Metrics Level 2 BVT Test 2");
	}

	@Test
	public void testPeopleView() throws Exception {

		System.out.println("INFO: Start of Metrics Level 2 BVT Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentMetrics);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Verify that the Metrics page is loaded
		assertTrue(sel.isElementPresent("link=Metrics"));

		//Open the People view and verify
		MetricViews(MetricsObjects.MetricsPeopleLink, MetricsData.PeopleView);

		//Open the Participation view and verify
		MetricViews(MetricsObjects.MetricsParticipationLink, MetricsData.ParticipationView);

		//Open the Content view and verify
		MetricViews(MetricsObjects.MetricsContentLink, MetricsData.ContentView);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Metrics Level 2 BVT Test 3");

	}

}
