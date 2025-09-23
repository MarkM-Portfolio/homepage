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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Keyboard;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ibm.atmn.wdbase.core.Element;
import com.ibm.atmn.wdbase.core.Executor;
import com.ibm.atmn.wdbase.core.TestConfiguration;

public class WebDriverExecutor implements Executor {

	private static class ThreadDriverHolder extends ThreadLocal<WebDriver> {

		public void setWebDriver(WebDriver driver) {

			super.set(driver);
		}

		public WebDriver getWebDriver() {

			return super.get();
		}

		public Mouse getDriverMouse() {

			return ((HasInputDevices) getWebDriver()).getMouse();
		}

		public Keyboard getDriverKeyboard() {

			return ((HasInputDevices) getWebDriver()).getKeyboard();
		}
	}

	private static ThreadDriverHolder driverHolder = new ThreadDriverHolder();;

	private TestConfiguration testConfig;

	public WebDriverExecutor(TestConfiguration testConfig) {

		setTestConfiguration(testConfig);
	}

	/**
	 * Gets a driver if it has been set. A {@link RuntimeException} is thrown if the driver has not been set.
	 * 
	 * @return A thread-local {@link WebDriver} instance
	 */
	WebDriver wd() {

		WebDriver driver = driverHolder.getWebDriver();
		if (driver != null) {
			return driver;
		}
		else {
			throw new RuntimeException("A WebDriver instance has not been set for this thread");
		}
	}

	private boolean driverIsSet() {

		return driverHolder.getWebDriver() != null ? true : false;
	}

	private void removeContextDriver() {

		driverHolder.remove();
	}

	private void setContextDriver() {

		driverHolder.setWebDriver(WebDriverSetup.createDriver(getTestConfiguration()));
	}

	Mouse getMouse() {

		return driverHolder.getDriverMouse();
	}

	Keyboard getKeyboard() {

		return driverHolder.getDriverKeyboard();
	}

	@Override
	public ExecutionSpeed getExecutionMode() {

		return ExecutionSpeed.SLOW;
	}

	@Override
	public void load(String url) {

		setContextDriver();
		//TODO: Configure implicit wait timeout (can be set only once?)
		wd().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wd().get(url);
	}

	@Override
	public void quit() {

		if (driverIsSet()) {
			wd().quit();
			removeContextDriver();
		}
	}

	@Override
	public void saveScreenshot() {

		// TODO Auto-generated method stub
		throw new RuntimeException("Method not implemented in WebDriver");

	}

	@Override
	public void close() {

		wd().close();
	}

	@Override
	public String getCurrentUrl() {

		return wd().getCurrentUrl();
	}

	@Override
	public List<Element> getElements(String selector) {

		WebDriverElementLocator locator = new WebDriverElementLocator(selector);
		List<WebElement> wdElements = locator.locateAll(this);
		List<Element> elements = new ArrayList<Element>();
		for (WebElement wdElement : wdElements) {
			elements.add(new WebDriverElement(this, wdElement, locator, 1500));
		}

		return elements;
	}

	@Override
	public String getPageSource() {

		return wd().getPageSource();
	}

	@Override
	public Element getSingleElement(String selector) {

		WebDriverElementLocator locator = new WebDriverElementLocator(selector);
		WebElement wdElement = locator.locateAll(this, 1, 1).get(0);
		Element element = new WebDriverElement(this, wdElement, locator, 1500);
		return element;
	}

	@Override
	public Element getFirstElement(String selector) {

		WebDriverElementLocator locator = new WebDriverElementLocator(selector);
		WebElement wdElement = locator.locateFirst(this);
		Element element = new WebDriverElement(this, wdElement, locator, 1500);
		return element;
	}

	@Override
	public String getTitle() {

		return wd().getTitle();
	}

	private void setTestConfiguration(TestConfiguration testConfig) {

		this.testConfig = testConfig;
	}

	TestConfiguration getTestConfiguration() {

		return this.testConfig;
	}

	@Override
	public Object getBackingObject() {

		return wd();
	}

	@Override
	public void typeNative(CharSequence... text) {

		Actions writer = new Actions(wd());
		writer.sendKeys(text).build().perform();

	}

	@Override
	public String getBodyText() {

		return this.getSingleElement("//body").getText();
	}

	@Override
	public boolean isElementPresent(String selector) {

		int elementCount = getElements(selector).size();
		if (elementCount > 1) {
			throw new RuntimeException("1 element only expected. " + elementCount + " elements returned.");
		}
		return elementCount == 1;
	}

	@Override
	public boolean isTextPresent(String text) {

		return getBodyText().contains(text);
	}

}