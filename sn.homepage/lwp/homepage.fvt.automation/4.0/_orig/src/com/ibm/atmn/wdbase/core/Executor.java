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

package com.ibm.atmn.wdbase.core;

import java.util.List;

/**
 * Primary Interface for interacting with your chosen web automation tool for controlling the browser. The following
 * list identifies the key terms found in this interface.
 * <p/>
 * <ul>
 * <li>{@link Executor} The browser itself, its windows, and the current page</li>
 * <li>{@link Element} An element of the current page</li>
 * </ul>
 * <p/>
 * First method in a session that should be invoked is {@link #load(String)}, which is used to open a browser and load a
 * new web page. Last method that should be invoked in a session is {@link #quit} to close the browser.
 * {@link #getSingleElement(String)} is used to find an {@link Element} to examine or act on.
 * <p/>
 * Implementation will vary by test tool and browser, depending on capabilities.
 * <p/>
 * WebDriver: uses {@link org.openqa.selenium.WebDriver}
 */
public interface Executor {

	public enum ExecutionSpeed {
		SLOW, FAST
	};

	ExecutionSpeed getExecutionMode();

	Object getBackingObject();

	//Navigation

	/**
	 * Opens new browser window and navigates to url. Returns once load is complete.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#get(String)}
	 * 
	 * @param url
	 *            The URL to load. e.g. http://w3.ibm.com/connections/activities/login.jsp
	 */
	void load(String url);

	/**
	 * Get a string representing the current URL that the browser is looking at.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#getCurrentUrl()}
	 * 
	 * @return The URL of the page currently loaded in the browser
	 */
	String getCurrentUrl();

	// General properties

	/**
	 * The inner text of the title element of the current page.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#getTitle()}
	 * 
	 * @return The title with leading and trailing whitespace stripped, or an empty string if title empty/not found
	 */
	String getTitle();

	/**
	 * Find all elements within the current page that match the given selector.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#findElements(By)}
	 * 
	 * @param selector
	 * @return A list of all {@link Element}s matching this selector. Empty list means no elements found.
	 */
	List<Element> getElements(String selector);

	/**
	 * Find the {@link Element} within the current page that matches the given selector.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#findElements(By)}
	 * <p/>
	 * This should not be used unless you want to strictly enforce that a selector return exactly one element. for
	 * better performance, use use {@link #getFirstElement(String)} instead. This should not be used to look for
	 * non-present elements, use {@link #getElements(String)} and assert that list is empty. This should not be used to
	 * get first matching element, use {@link #getFirstElement(String)} instead.
	 * 
	 * @param selector
	 * @return The matching {@link Element} on the current page.
	 * @throws ElementNotFoundException
	 *             If no matching elements are found
	 * @throws LooseSelectorException
	 *             If more than one matching element is found
	 */
	Element getSingleElement(String Selector);

	/**
	 * Find the first {@link Element} within the current page that matches the given selector.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#findElement(By)}
	 * <p/>
	 * This should not be used unless only the first matching element found is required. This should not be used to look
	 * for non-present elements, use {@link #getElements(String)} and assert that list is empty.
	 * 
	 * @param selector
	 * @return The matching {@link Element} on the current page.
	 * @throws ElementNotFoundException
	 *             If no matching elements are found
	 */
	Element getFirstElement(String selector);

	// Misc

	/**
	 * Get the HTML source of the page.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#getPageSource()} NOTE: See delegate javadoc for important
	 * warnings on possible browser variation of returned string.
	 * <p/>
	 * 
	 * @return The source of the current page (not necessarily the current page source!)
	 */
	String getPageSource();

	//TODO: javadoc
	String getBodyText();

	//TODO: javadoc
	boolean isElementPresent(String selector);

	//TODO: javadoc
	boolean isTextPresent(String text);

	/**
	 * Close the current window.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#close()}
	 * <p/>
	 * Do not use to close the last window, use {@link #quit()} instead.
	 */
	void close();

	/**
	 * Exits browser, closing all open windows. Next invocation on Executor, if any, should be {@link #load(String)}.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.WebDriver#quit()}
	 */
	void quit();

	/**
	 * Attempts to save a screenshot of the current page to the report output folder for this suite. Success will depend
	 * on tool and bowser. A link to the screenshot will be added to TestNG Reporter for the current suite.
	 */
	void saveScreenshot();

	/**
	 * Use this if you are attempting to type without a standard {@literal <input>} element. This method will attempt
	 * simulate key presses that may have the desired effect. The correct element focus must be achieved before calling
	 * this method. Window focus may also be required.
	 * <p/>
	 * WebDriver: uses {@link org.openqa.selenium.interactions.Actions#sendKeys(CharSequence...)}
	 * 
	 * @param text
	 */
	void typeNative(CharSequence... text);

	/**
	 * Return a set of window handles which can be used to iterate over all open windows of this webdriver instance by
	 * passing them to {@link #switchTo().window(String)}
	 * 
	 * @return A set of window handles which can be used to iterate over all open windows.
	 */
	//TODO: Set<String> getWindowHandles();

	/**
	 * Return an opaque handle to this window that uniquely identifies it within this driver instance. This can be used
	 * to switch to this window at a later date
	 */
	//TODO: String getWindowHandle();

	/**
	 * Send future commands to a different frame or window.
	 * 
	 * @return A TargetLocator which can be used to select a frame or window
	 * @see org.openqa.selenium.WebDriver.TargetLocator
	 */
	//TargetLocator is not an intuitive name since 'target' is vague. WindowAndIframeSwitcher ?
	//TODO: TargetLocator switchTo();

	/**
	 * An abstraction allowing the driver to access the browser's history and to navigate to a given URL.
	 * 
	 * @return A {@link org.openqa.selenium.WebDriver.Navigation} that allows the selection of what to do next
	 */
	//TODO: Navigation navigate();

	/**
	 * Gets the Option interface
	 * 
	 * @return An option interface
	 * @see org.openqa.selenium.WebDriver.Options
	 */
	//ExecutorOptions
	//TODO: Options manage();

}
