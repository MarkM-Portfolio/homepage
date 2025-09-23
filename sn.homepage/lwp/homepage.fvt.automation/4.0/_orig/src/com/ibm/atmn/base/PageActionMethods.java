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

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class PageActionMethods extends SetUpMethods {

	public PageActionMethods() {

	}

	/** Click specified link */
	public void clickLink(String link) {

		//Click the link provided
		clickAndWait(link, getPageChangeHandler());
	}

	/** Click specified link */
	public void clickLink(String link, PageChangeHandler pageWaiter) {

		//Click the link provided
		clickAndWait(link, pageWaiter);
	}

	public void cautiousClick(String selector) {

		cautiousClick(selector, 20);
	}

	public void cautiousClick(String selector, int repetitions) {

		try {
			sel.click(selector);
		} catch (Exception e) {
			System.out.println("WARNING: Element click exception caught: " + e);
			if (repetitions > 0) {
				sleep(500);
				cautiousClick(selector, repetitions - 1);
			}
			else {
				Assert.fail("FAIL/ERROR: Invalid action, missing element or bad selector: " + selector);
			}
		}
	}

	public void cautiousFocus(String selector) {

		cautiousFocus(selector, 20);
	}

	public void cautiousFocus(String selector, int repetitions) {

		try {
			sel.focus(selector);
		} catch (Exception e) {
			System.out.println("WARNING: Element focus exception caught: " + e);
			if (repetitions > 0) {
				sleep(500);
				cautiousFocus(selector, repetitions - 1);
			}
			else {
				Assert.fail("FAIL/ERROR: Invalid action, missing element or bad selector: " + selector);
			}
		}
	}

	/*
	 * TODO: cautiousEval and cautiousRunScript should return the same way as the selenium methods. This requires a
	 * change in PageChangeHandler to handle javascript failure and the absense of dojo.
	 */
	public String cautiousGetEval(String script) {

		return cautiousGetEval(script, 20);
	}

	public String cautiousGetEval(String script, int repetitions) {

		try {
			return sel.getEval(script);
		} catch (Exception e) {
			System.out.println("WARNING: getEval exception caught: " + e);
			if (repetitions > 0) {
				sleep(500);
				return cautiousGetEval(script, repetitions - 1);
			}
			else {
				return null;
			}
		}
	}

	public boolean cautiousRunScript(String script) {

		return cautiousRunScript(script, 20);
	}

	public boolean cautiousRunScript(String script, int repetitions) {

		try {
			sel.runScript(script);
			return true;
		} catch (Exception e) {
			System.out.println("WARNING: runScript exception caught: " + e);
			if (repetitions > 0) {
				sleep(500);
				return cautiousRunScript(script, repetitions - 1);
			}
			else {
				return false;
			}
		}
	}

	private void clickAndWait(String element, PageChangeHandler pageWaiter) {

		cautiousClick(element);
		boolean result = pageWaiter.andWait();
		if (result) {
			return;
		}
		else {
			Assert.fail("Click did not result in any detectable change to page contact.");
		}
	}

	public void mouseOverAndWait(String selector) {

		mouseOverAndWait(selector, getPageChangeHandler());
	}

	public void mouseOverAndWait(String selector, PageChangeHandler pageWaiter) {

		sel.mouseOver(selector);
		pageWaiter.andWait();
	}

	public void clickAtAndWait(String selector, String coordinates) {

		clickAtAndWait(selector, coordinates, getPageChangeHandler());
	}

	public void clickAtAndWait(String selector, String coordinates, PageChangeHandler pageWaiter) {

		sel.clickAt(selector, coordinates);
		pageWaiter.andWait();
	}

	public PageChangeHandler getPageChangeHandler() {

		return new PageChangeHandler(this);
	}

	public void typeNative(String text) {

		FormInputHandler typist = getFormInputHandler();
		typist.type(null, text);
	}

	public FormInputHandler getFormInputHandler() {

		return new FormInputHandler(this);
	}

	public NativeTyper getNativeTyper() {

		return new NativeTyper(this);
	}

	public void assertAllTextPresent(ArrayList<String> assertList) {

		for (String text : assertList) {
			AssertJUnit.assertTrue("FAIL: assertList text '" + text + "' not found", sel.isTextPresent(text));
		}
	}

	public void assertAllTextNotPresent(ArrayList<String> assertList) {

		for (String text : assertList) {
			AssertJUnit.assertFalse("FAIL: Illegal text found: '" + text + "'", sel.isTextPresent(text));
		}
	}

	public void assertAllTextPresentWithinElement(String locator, ArrayList<String> assertList) {

		String innerHTML = sel.getEval("this.page().findElement(\"" + locator + "\").innerHTML;");

		for (String text : assertList) {
			AssertJUnit.assertTrue("FAIL: assertList text '" + text + "' not found", innerHTML.indexOf(text) >= 0);
		}
	}
}
