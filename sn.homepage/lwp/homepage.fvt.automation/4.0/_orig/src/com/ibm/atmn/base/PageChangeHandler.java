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

package com.ibm.atmn.base;

import java.io.ByteArrayInputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

public class PageChangeHandler {

	private int timeoutInMillis = 30000;
	private int interval = 0;
	private int numberOfChanges = 1;
	private int minimumCopyCount = 3;
	private float beta = 0.2f;
	private int alpha = 2200;
	private String initialSnapshot = "";
	private long startTime = System.currentTimeMillis();
	private long timeToFirstChange;
	private long deadline = startTime + timeoutInMillis;

	private PageActionMethods pageActionHandler;

	public PageChangeHandler(PageActionMethods pageActionHandler) {

		this.pageActionHandler = pageActionHandler;
		this.initialSnapshot = getHtmlSnapshot();
	}

	public PageChangeHandler(PageActionMethods pageActionHandler, int timeoutInMillis, int interval, int alpha, float beta, int numberOfChanges, String intitalSnapshot) {

		this(pageActionHandler);
		this.timeoutInMillis = timeoutInMillis;
		this.interval = interval;
		this.alpha = alpha;
		this.beta = beta;
		this.numberOfChanges = numberOfChanges;

	}

	public boolean andWait() {

		//set up
		boolean pageChanged = false;
		boolean pageStable = false;

		//wait for changes	
		pageChanged = waitForNumberOfPageChanges(numberOfChanges);

		//if changed
		if (pageChanged == true) {
			pageStable = waitForPageToStopChanging();
		}

		//Check results
		return checkResults(pageStable);

	}

	private boolean areOfEquallength(String one, String two) {

		if (one.length() == two.length()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean checkResults(boolean pageStable) {

		//Check results
		String latestSnapshot = getHtmlSnapshot();
		//Test for Success: page changed and consecutive snapshots were equal OR timeout occurred but page has changed
		if (pageStable == true || (getStringChecksum(initialSnapshot) != getStringChecksum(latestSnapshot)) || initialSnapshot.equals(latestSnapshot) == false) {

			return true;

		}
		else { //timeout occurred and no change
			return false;
		}
	}

	private String getHtmlSnapshot() {

		String source = "";

		try {
			source = pageActionHandler.sel.getHtmlSource();
		} catch (NullPointerException n) {
			StackTraceElement[] stack = n.getStackTrace();
			for (StackTraceElement trace : stack) {
				System.out.println(trace);
			}
		} catch (Exception e) {
			System.out.println("WARNING: getHtmlSource Exception was caught: " + e);
		}
		return source;
	}

	private long getStringChecksum(String text) {

		byte buffer[] = text.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		CheckedInputStream cis = new CheckedInputStream(bais, new Adler32());
		long value = cis.getChecksum().getValue();

		return value;

	}

	private boolean monitorForPageChanges(int numberOfChanges, int interval, long deadline, String compareAgainstSnapshot) {

		int changeCount = 0;
		String latestSnapshot = "";
		while (System.currentTimeMillis() < deadline && changeCount < numberOfChanges) {
			SetUpMethods.sleep(interval);
			latestSnapshot = getHtmlSnapshot();

			//Test for changes. Ignore empty or null returns (not expected behaviour).
			if (latestSnapshot != null && latestSnapshot != ""
					&& (areOfEquallength(compareAgainstSnapshot, latestSnapshot) != true || compareAgainstSnapshot.equals(latestSnapshot) != true)) {
				changeCount++;
				compareAgainstSnapshot = latestSnapshot;
			}
			if (changeCount == 1) {
				timeToFirstChange = System.currentTimeMillis() - startTime;
			}
		}

		if (changeCount == numberOfChanges) {
			return true;
		}
		else {
			return false;
		}

	}

	private boolean monitorForPageCopies(int interval, long deadline, long timeToFirstChange) {

		//set up
		int copyCount = 0;
		int requiredStableTime;
		requiredStableTime = alpha + ((int) (timeToFirstChange * beta));
		long timeAtLastReset = System.currentTimeMillis();
		String compareAgainstSnapshot = getHtmlSnapshot();
		String latestSnapshot = "";

		for (int i = 0; System.currentTimeMillis() < deadline && ((System.currentTimeMillis() - timeAtLastReset) < requiredStableTime || copyCount < minimumCopyCount); i++) {

			SetUpMethods.sleep(interval);
			latestSnapshot = getHtmlSnapshot();

			//Test if the latest snapshot is the same as the one before.
			if (latestSnapshot != null && latestSnapshot != "" && areOfEquallength(compareAgainstSnapshot, latestSnapshot) == true) {
				copyCount++;
			}
			else { //A change occurred, reset copy counter and timeAtLastReset
				copyCount = 0;
				timeAtLastReset = System.currentTimeMillis();
			}
			compareAgainstSnapshot = latestSnapshot;
		}

		if ((System.currentTimeMillis() - timeAtLastReset) >= requiredStableTime && copyCount >= minimumCopyCount) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean waitForNumberOfPageChanges(int numberOfChanges) {

		boolean pageChanged = false;

		pageChanged = monitorForPageChanges(numberOfChanges, interval, deadline, initialSnapshot);

		return pageChanged;
	}

	public boolean waitForPageToStopChanging() {

		boolean pageStable = false;

		if (timeToFirstChange < 500) {
			//System.out.println("\nTEST NOTICE: Page change detected too soon. Waiting for copies...");
			pageStable = monitorForPageCopies(interval, deadline, timeToFirstChange);
		}

		long go = 0;
		long stop = 0;
		String dojoPageLoadFired = "";
		boolean success = pageActionHandler.cautiousRunScript("dojo.dojoPageLoadFired = 'false';");
		if (success) {
			go = System.currentTimeMillis();
			pageActionHandler.cautiousRunScript("dojo.addOnLoad(function(){ dojo.dojoPageLoadFired = 'true'; });");

			//ActionHandler.sel.waitForCondition("selenium.browserbot.getCurrentWindow().dojo.dojoPageLoadFired == true", "30000");
			while (dojoPageLoadFired.equals("true") != true && System.currentTimeMillis() < deadline) {
				dojoPageLoadFired = pageActionHandler.cautiousGetEval("selenium.browserbot.getCurrentWindow().dojo.dojoPageLoadFired");
			}
			stop = System.currentTimeMillis();
		}

		if (!success || (stop - go) < 100) {
			//System.out.println("TEST NOTICE: JavaScript failure or addOnLoad fired too soon. Waiting for copies...");
			pageStable = monitorForPageCopies(interval, deadline, timeToFirstChange);
		}

		//TESTING PRINTS
		/*
		 * System.out.println("AddOnLoad returned in: " + (stop - go) + "milliseconds.");
		 * System.out.println("TimeToFirstChange: " + timeToFirstChange); System.out.println("Total time: " +
		 * (System.currentTimeMillis() - startTime));
		 */

		if (dojoPageLoadFired.equals("true") || pageStable == true) {
			return true;
		}
		else {
			return false;
		}
	}

}