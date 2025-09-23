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

package com.ibm.atmn.wdbase.core.webdriver;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.atmn.wdbase.core.TestManager;
import com.ibm.atmn.wdbase.core.TestConfiguration.BrowserType;
import com.ibm.atmn.wdbase.utils.FileIOHandler;
import com.ibm.atmn.wdbase.utils.Utils;

// TODO: Potentially shared object. Must be made IMMUTABLE if so. TBD...may cause difficulties as scope increases.
public class WebDriverElementLocator {

	private String sizzleSelector;
	private String elementType;
	private LocatorType locatorType;
	private By selectBy;

	private static final String sizzleJavascript = FileIOHandler.readFileToString(new File("resources/littlesizzle.js"));

	public enum LocatorType {
		XPATH, CSS, LINK, PARTIAL_LINK, ID, NAME, CLASS
	};

	public WebDriverElementLocator(String selector) {

		this.sizzleSelector = selector;
	}

	private By getSelectBy(WebDriverExecutor executor, String selector) {

		if (selector.startsWith("xpath=") || selector.startsWith("//") || selector.startsWith("./")) {
			selectBy = byXpath(selector);
		}
		else if (selector.startsWith("id=")) {
			locatorType = LocatorType.ID;
			selectBy = By.id(selector.replaceFirst("^\\s*id=\\s*", ""));
		}
		else if (selector.startsWith("link=")) {
			locatorType = LocatorType.LINK;
			selectBy = By.linkText(selector.replaceFirst("^\\s*link=\\s*", ""));
		}
		else if (selector.startsWith("name=")) {
			locatorType = LocatorType.NAME;
			selectBy = By.name(selector.replaceFirst("^\\s*name=\\s*", ""));
		}
		else if (selector.startsWith("class=")) {

			locatorType = LocatorType.CLASS;
			selectBy = By.className(selector.replaceFirst("^\\s*class=\\s*", ""));
		}
		else if (selector.startsWith("css=")) {

			selectBy = byCss(executor, selector);
		}
		else {
			throw new RuntimeException("selector could not be identified");
		}
		return selectBy;
	}

	private By byCss(WebDriverExecutor executor, String selector) {

		String selectorValue = selector.replaceFirst("^\\s*css=\\s*", "");

		if (selectorValue.contains(":nth(") && (!executor.getTestConfiguration().browserIs(BrowserType.IE))) {
			String[] parts = selectorValue.split(":nth\\(");
			selectorValue = parts[0];
			for (int i = 1; i < parts.length; i++) {
				String part = parts[i];
				String bit = part.substring(0, part.indexOf(")"));
				try {
					Integer index = Integer.parseInt(bit);
					index++;
					bit = String.valueOf(index);
				} catch (NumberFormatException e) {

				}
				selectorValue = selectorValue + ":nth-of-type(" + bit + part.substring(part.indexOf(")"));
				TestManager.logger.logTrace("desizzled: " + selectorValue);
			}
		}

		locatorType = LocatorType.CSS;
		return By.cssSelector(selectorValue);
	}

	private By byXpath(String selector) {

		String selectorValue = selector.replaceFirst("^\\s*xpath=\\s*", "");
		locatorType = LocatorType.XPATH;
		return By.xpath(selectorValue);
	}

	@SuppressWarnings("unchecked")
	public List<WebElement> locateAll(WebDriverExecutor executor) {

		TestManager.logger.logTrace("Attempting to locate all elements with locator " + sizzleSelector);
		if (sizzleSelector.contains(":contains(") && (!executor.getTestConfiguration().browserIs(BrowserType.IE))) {

			JavascriptExecutor javascriptContext = (JavascriptExecutor) executor.wd();
			javascriptContext.executeScript(sizzleJavascript);
			TestManager.logger.logTrace("Attempting to locate with contains " + getSelectBy(executor, sizzleSelector).toString().replace("By.selector: ", ""));
			List<WebElement> elements = null;
			for (int i = 0; (elements == null || elements.isEmpty()) && i < 40; i++) {
				try {
					elements = (List<WebElement>) javascriptContext.executeScript("return Sizzle(arguments[0])", getSelectBy(executor, sizzleSelector).toString().replace(
							"By.selector: ", ""));
				} catch (WebDriverException e) {
					TestManager.logger.logWarn("WebDriver javascript execution exception caught: " + e.getMessage());
				}
				Utils.milliSleep(500);
				javascriptContext.executeScript(sizzleJavascript);
			}
			return elements;
		}
		return executor.wd().findElements(getSelectBy(executor, sizzleSelector));
	}

	public List<WebElement> locateAll(WebDriverExecutor executor, int min, int max) {

		List<WebElement> elements = locateAll(executor);
		if (elements.size() > max) {
			throw new RuntimeException("Too many found: " + sizzleSelector + " : " + elements.size() + " elements found when max " + max + " expected.");
		}
		else if (elements.size() < min) {
			throw new RuntimeException("Too few found: " + sizzleSelector + " : " + elements.size() + " elements found when min " + min + " expected.");
		}
		return elements;
	}

	/**
	 * Attempts to locate the element on the currently loaded page
	 * 
	 * NOTE: Should be invoked before all methods in this class
	 * 
	 * @returns WebElement if found, throws exception otherwise.
	 */
	public WebElement locateFirst(WebDriverExecutor executor) {

		TestManager.logger.logTrace("Attempting to locate element with locator " + sizzleSelector);
		List<WebElement> elements = locateAll(executor);
		if (!elements.isEmpty()) {
			return elements.get(0);
		}
		else {
			throw new RuntimeException("elementType" + " element [(" + locatorType.toString() + ") " + sizzleSelector + "] does not exist");
		}
	}

	/**
	 * Checks whether the element exists on the page
	 */
	public boolean exists(WebDriverExecutor executor) {

		List<WebElement> exists;
		exists = locateAll(executor);

		if (exists.size() > 0) {
			TestManager.logger.logDebug(elementType + " element [(" + locatorType.toString() + ") " + sizzleSelector + "] exists");
			return true;
		}
		else {
			TestManager.logger.logDebug(elementType + " element [(" + locatorType.toString() + ") " + sizzleSelector + "] does not exist");
			return false;
		}
	}

	//TODO: Will this work?
	public WebElement waitForAppearance(WebDriverExecutor executor) {

		//TODO: add back the variables for timers, config to testConfig?
		WebElement element = (new WebDriverWait(executor.wd(), 30)).until(new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver wd) {

				return wd.findElement(selectBy);
			}

		});
		return element;
	}

}
