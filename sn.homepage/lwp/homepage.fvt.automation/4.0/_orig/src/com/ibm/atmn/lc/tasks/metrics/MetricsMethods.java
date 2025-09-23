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

package com.ibm.atmn.lc.tasks.metrics;

import com.ibm.atmn.lc.appobjects.metrics.MetricsObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import org.testng.Assert;

public class MetricsMethods extends CommonMethods {

	public void MetricViews(String Viewname, String ViewHeaderName) throws Exception {

		//Click on the Connections link in the tree and expand
		if (sel.isElementPresent(MetricsObjects.MetricsConnectionsTree)) {
			clickLink(MetricsObjects.MetricsConnectionsTree);
		}
		else if (sel.isElementPresent(MetricsObjects.MetricsConnectionsTreeExpanded)) {
			clickLink(MetricsObjects.MetricsConnectionsTreeExpanded);
		}

		//Choose a view to open
		clickLink(Viewname);

		//Verify the view
		Assert.assertEquals(ViewHeaderName, sel.getText(MetricsObjects.MetricsViewHeader));

	}

}
