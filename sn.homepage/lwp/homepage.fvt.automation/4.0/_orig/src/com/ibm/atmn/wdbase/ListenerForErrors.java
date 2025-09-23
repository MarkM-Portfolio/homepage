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

package com.ibm.atmn.wdbase;

import org.testng.*;

public class ListenerForErrors extends TestListenerAdapter {

	//Take screen shot only for failed test case
	@Override
	public void onTestFailure(ITestResult tr) {

		//String methodName = tr.getMethod().getMethodName();
		//SetUpMethods.takeScreenshot(methodName);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {

		//ScreenShot();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {

		//ScreenShotOnPass();
	}

}
