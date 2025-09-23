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

package com.ibm.atmn.lc.tasks.forums;

import com.ibm.atmn.lc.tasks.common.CommonMethods;

public class ForumsMethods extends CommonMethods {

	public void waitForElementToExist(String element, int maxAttempts) {

		int attempts = 0;
		while (sel.isElementPresent(element) != true && attempts < maxAttempts) {

			sleep(100);
			attempts++;
			System.out.println(attempts);

		}
	}

}