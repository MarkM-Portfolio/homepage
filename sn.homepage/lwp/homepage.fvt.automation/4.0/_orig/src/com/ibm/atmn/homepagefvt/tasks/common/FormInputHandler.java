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

package com.ibm.atmn.homepagefvt.tasks.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;

public class FormInputHandler {

	private ArrayList<String> assertList = new ArrayList<String>();

	public FormInputHandler() {

	}

	public void type(String selector, String text) {
		type(selector, text, "sentence");
	}

	public void type(String selector, String text, String textType) {
		type(selector, text, textType, "type");
	}

	public void type(String selector, String text, String textType, String typingStrategy) {
		type(selector, text, textType, typingStrategy, null);
	}

	//textType == null means do not add to assert list. 
	//typingStrategy == null mean use default strategy (unless selector is null, in which case nativeType will be used).
	//fireStrategy == null means do not use a fireStrategy (e.g. use for nativeType)
	//if a selector is not applicable (e.g. nativeType), use selector = null. If selector is null, nativeType will be used. This applies whether typingStrategy is provided or not.
	public void type(String selector, String text, String textType, String typingStrategy, String fireStrategy) {

		if (selector == null)
			typingStrategy = "typeNative";
		if (text == null)
			text = "null";
		if (textType == null)
			;
		if (typingStrategy == null)
			typingStrategy = "";
		if (fireStrategy == null)
			fireStrategy = "";

		if (typingStrategy.equalsIgnoreCase("typeKeys")) {
			cautiousType(selector, text, typingStrategy);
		} else if (typingStrategy.equalsIgnoreCase("typeNative")) {
			typeNative(text);
		} else {
			cautiousType(selector, text, "type");
		}

		if (fireStrategy.equalsIgnoreCase("event")) {
			fireByEvent(selector);
		} else if (fireStrategy.equalsIgnoreCase("typeKeysBackspace")) {
			fireByTypeKeysBackspace(selector);
		} else {
			//do nothing
		}

		//test if string should be stored
		if (textType != null) {
			//parse and add string to list for assert check
			if (textType.equalsIgnoreCase("tags")) {
				addTagsToAssertList(text);
				//} else if (textType.equalsIgnoreCase("date")) {
				//addDateToAssertList(text);
			} else {
				addBlobToAssertList(text);
			}
		}
	}

	private void cautiousType(String selector, String text, String typingStrategy) {

		PageChangeActionHandler pageWaiter = CommonMethods.getPageChangeActionHandler();
		try {
			if (typingStrategy.equalsIgnoreCase("typeKeys")) {
				CommonMethods.selenium.typeKeys(selector, text);
			} else {
				CommonMethods.selenium.type(selector, text);
			}
		} catch (Exception e) {
			System.out.println("WARNING: 'type' exception caught: " + e);
			if (pageWaiter.waitForNumberOfPageChanges(1)) {
				cautiousType(selector, text, typingStrategy);
			}
		}
	}

	private void typeNative(String text) {

		NativeTyper typist = new NativeTyper();
		typist.nativeType(text);

	}

	public void typeAndWait(String selector, String text) {
		typeAndWait(selector, text, "sentence");
	}

	public void typeAndWait(String selector, String text, String textType) {
		typeAndWait(selector, text, textType, "type", "typeKeysBackspace");
	}

	public void typeAndWait(String selector, String text, String textType, String typingStrategy) {
		typeAndWait(selector, text, textType, typingStrategy, "typekeysBackspace");
	}

	public void typeAndWait(String selector, String text, String textType, String typingStrategy, String fireStrategy,
			PageChangeActionHandler... handlers) {

		PageChangeActionHandler pageWaiter;
		//retrieve Handler
		pageWaiter = CommonMethods.getPageChangeActionHandler(handlers);
		//type the text
		type(selector, text, textType, typingStrategy, fireStrategy);
		//wait for page change
		pageWaiter.andWait();

	}

	public void addBlobToAssertList(String text) {
		assertList.add(text);
	}

	public void addTagsToAssertList(String text) {
		String[] tags = text.split(" ");
		for (String tag : tags) {
			addBlobToAssertList(tag.toLowerCase());
		}
	}

	public void addDateToAssertList(String text) {

		//Parse date string
		SimpleDateFormat dmy = new SimpleDateFormat("dd MMMM yyyy");
		SimpleDateFormat mdy = new SimpleDateFormat("MMMM dd, yyyy");
		boolean mdyIsFormat = true;
		Date date;
		dmy.setLenient(false);
		mdy.setLenient(false);
		try {
			date = mdy.parse(text);
		} catch (ParseException e) {
			mdyIsFormat = false;
			try {
				date = dmy.parse(text);
			} catch (ParseException e1) {
				System.out.println("ERROR: This date could not be parsed: " + text + ". It will not be verified. \n"
						+ e1);
				return;
			}
		}

		//Format date
		SimpleDateFormat yearOnly = new SimpleDateFormat("yyyy");
		SimpleDateFormat withoutYear;
		SimpleDateFormat withYear;
		if (mdyIsFormat) {
			withoutYear = new SimpleDateFormat("MMM d");
			withYear = new SimpleDateFormat("MMM d, yyyy");
		} else {
			withoutYear = new SimpleDateFormat("d MMM");
			withYear = new SimpleDateFormat("d MMM yyyy");
		}

		//get current year	
		String currentYear = CommonMethods.selenium.getEval("var d = new Date(); d.getFullYear()");

		//Add appropriate string to assert list (if date in current year, the year will not be displayed)
		if (currentYear.equalsIgnoreCase(yearOnly.format(date))) {
			addBlobToAssertList(withoutYear.format(date));
		} else {
			addBlobToAssertList(withYear.format(date));
		}

	}

	public String popLastFromAssertList() {
		return assertList.remove(assertList.size() - 1);
	}

	public void dumpList() {
		assertList = new ArrayList<String>();
	}

	public ArrayList<String> getListCopy() {
		ArrayList<String> temp = new ArrayList<String>();
		for (String text : assertList) {
			temp.add(text);
		}
		return temp;
	}

	public ArrayList<String> getAssertList() {

		return assertList;
	}

	public void pickADate(String locator) {
		pickADate(locator, true);
	}

	//Selects a date from a subset of dates in the next 18 months
	public void pickADate(String locator, boolean addToAssertList) {
		//activate dropdown datepicker
		CommonMethods.selenium.click(locator);

		//Generate random future month and date of month
		int monthsForward = ((int) (Math.floor(Math.random() * 15))) + 1;
		int dayOfMonth = ((int) (Math.floor(Math.random() * 28))) + 1;

		//Select month
		while (monthsForward > 0) {
			CommonMethods.selenium.clickAt(CommonObjects.Date_Picker_NextMonth, "2,2");
			monthsForward--;
		}

		//Select date
		CommonMethods.selenium.click(CommonObjects.Date_Picker_CurrentMonth_Dates + ":nth(" + (dayOfMonth - 1)
				+ ")>span");

		//test if string should be stored
		if (addToAssertList != false) {
			//get string, parse it, and add to list for assert check
			addDateToAssertList(CommonMethods.selenium.getValue(locator));
		}
	}

	private void fireByEvent(String selector, String... events) {
		String[] eventList;
		//confirm list of events to fire
		if (events.length < 1) {
			eventList = new String[] { "keydown", "keyup", "keypress", "change", "blur" };
		} else {
			eventList = events;
		}
		for (String event : eventList) {
			CommonMethods.selenium.fireEvent(selector, event);
			CommonMethods.sleep(100);
		}
	}

	private void fireByTypeKeysBackspace(String selector) {

		CommonMethods.selenium.typeKeys(selector, " ");
		CommonMethods.selenium.typeKeys(selector, "\b");
	}
}
