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

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;

import com.ibm.atmn.wdbase.core.Element;
import com.ibm.atmn.wdbase.core.TestManager;
import com.ibm.atmn.wdbase.core.Executor.ExecutionSpeed;
import com.ibm.atmn.wdbase.utils.Utils;

public class WebDriverElement implements Element {

	private long executionDelay = 2000;
	private WebElement element;
	private WebDriverExecutor executor;
	WebDriverElementLocator locator;

	public WebDriverElement(WebDriverExecutor executor, WebElement element, WebDriverElementLocator locator, long executionDelay) {

		this.executor = executor;
		this.element = element;
		this.executionDelay = executionDelay;
		this.locator = locator;
	}

	@Override
	public Object getBackingObject() {

		return element;
	}

	@Override
	public String getType() {

		return element.getTagName();
	}

	@Override
	public String getText() {

		String text = element.getText();
		TestManager.logger.logDebug("Returning text '" + text + "' from " + "elementType" + " element [(" + "locatorType.toString()" + ") " + locator + "]");
		return text;
	}

	@Override
	public String getAttribute(String attributeName) {

		String value = element.getAttribute(attributeName);
		TestManager.logger.logDebug("Returning value '" + value + "' for attribute '" + attributeName + "' of " + "elementType" + " element [(" + "locatorType.toString()" + ") "
				+ locator + "]");
		return value;
	}

	@Override
	public java.awt.Point getLocation() {

		return new Point(element.getLocation().getX(), element.getLocation().getY()); //TODO: is this the same as Locatable.getCoordinates()?
	}

	@Override
	public Dimension getSize() {

		return new Dimension(element.getSize().getWidth(), element.getSize().getHeight());
	}

	@Override
	public void click() {

		TestManager.logger.logDebug("Clicking on " + "elementType" + " element [(" + "locatorType.toString()" + ") " + locator + "]");
		element.click();

		if (executor.getExecutionMode() == ExecutionSpeed.SLOW) {
			Utils.milliSleep(executionDelay);
		}
	}

	/**
	 * Hovers the mouse over an element
	 */
	@Override
	public void hover() {

		TestManager.logger.logDebug("Hovering over " + "elementType" + " element [(" + "locatorType.toString()" + ") " + locator + "]");
		Locatable el = (Locatable) element;
		executor.getMouse().mouseMove(el.getCoordinates());

		if (executor.getExecutionMode() == ExecutionSpeed.SLOW) {
			Utils.milliSleep(executionDelay);
		}
	}

	@Override
	public void type(CharSequence... text) {

		TestManager.logger.logDebug("Typing text '" + text + "' into " + "elementType" + " element [(" + "locatorType.toString()" + ") " + locator + "]");
		element.sendKeys(text);

		if (executor.getExecutionMode() == ExecutionSpeed.SLOW) {
			Utils.milliSleep(executionDelay);
		}
	}

	@Override
	public void clear() {

		element.clear();

		if (executor.getExecutionMode() == ExecutionSpeed.SLOW) {
			Utils.milliSleep(executionDelay);
		}
	}

	@Override
	public boolean isVisible() {

		if (element.isDisplayed()) {
			TestManager.logger.logDebug("elementType" + " element [(" + "locatorType.toString()" + ") " + locator + "] is visible");
			return true;
		}
		else {
			TestManager.logger.logDebug("elementType" + " element [(" + "locatorType.toString()" + ") " + locator + "] is not visible");
			return false;
		}
	}

	@Override
	public void doubleClick() {

		TestManager.logger.logDebug("doubleClick on " + "elementType" + " element [(" + "locatorType.toString()" + ") " + locator + "]");
		Locatable el = (Locatable) element;
		executor.getMouse().doubleClick(el.getCoordinates());

		if (executor.getExecutionMode() == ExecutionSpeed.SLOW) {
			Utils.milliSleep(executionDelay);
		}
	}

	@Override
	public void typeFilePath(CharSequence path) {

		type(path);

	}

	@Override
	public void leftMouseDown() {

		Locatable el = (Locatable) element;
		executor.getMouse().mouseDown(el.getCoordinates());
	}

	@Override
	public void leftMouseUp() {

		Locatable el = (Locatable) element;
		executor.getMouse().mouseUp(el.getCoordinates());
	}

	@Override
	public void rightMouseClick() {

		//TODO:Does this right-click?
		Locatable el = (Locatable) element;
		executor.getMouse().contextClick(el.getCoordinates());
	}

	@Override
	public void rightMouseUp() {

		// TODO Are there right mouse down and up options?
		throw new RuntimeException("Method not implemented in WebDriverElement, use backing object.");
	}

	@Override
	public void setText(String text) {

		// TODO What is this for?
		throw new RuntimeException("Method not implemented in WebDriverElement, use backing object.");
	}

	@Override
	public List<Element> getElements(String selector) {

		// TODO Auto-generated method stub
		throw new RuntimeException("Method not implemented in WebDriverElement, use backing object.");
	}

	@Override
	public Element getSingleElement(String Selector) {

		// TODO Auto-generated method stub
		throw new RuntimeException("Method not implemented in WebDriverElement, use backing object.");
	}

	@Override
	public boolean isSelected() {

		return element.isSelected();
	}

	@Override
	public void click(boolean andWaitForPageToChange) {

		this.click(); //page waiting can probably be best handled in web driver using implicit waits?
	}

	@Override
	public void clickAt(int x, int y) {

		// TODO How to offset?

	}

	@Override
	public void submit() {

		// TODO This method is probably unreliable and too web-driver specific?
		throw new RuntimeException("Method not implemented in WebDriverElement, use backing object.");
	}

	@Override
	public SelectDropdown useAsDropdown() {

		return new SelectDropdownMenu();
	}

	private class SelectDropdownMenu implements SelectDropdown {

		private Select select;

		public SelectDropdownMenu() {

			select = new Select(element);
		}

		@Override
		public void deselectAll() {

			select.deselectAll();

		}

		@Override
		public void deselectByIndex(int index) {

			select.deselectByIndex(index);

		}

		@Override
		public void deselectByValue(String value) {

			select.deselectByValue(value);

		}

		@Override
		public void deselectByVisibleText(String text) {

			select.deselectByVisibleText(text);

		}

		@Override
		public List<Element> getAllSelectedOptions() {

			List<WebElement> wdElements = select.getAllSelectedOptions();
			List<Element> elements = new ArrayList<Element>();
			for (WebElement wdElement : wdElements) {
				elements.add(new WebDriverElement(executor, wdElement, null, 1500));
			}
			return elements;
		}

		@Override
		public List<Element> getOptions() {

			List<WebElement> wdElements = select.getOptions();
			List<Element> elements = new ArrayList<Element>();
			for (WebElement wdElement : wdElements) {
				elements.add(new WebDriverElement(executor, wdElement, null, 1500));
			}
			return elements;
		}

		@Override
		public boolean isMultiple() {

			return select.isMultiple();
		}

		@Override
		public void selectOptionByValue(String value) {

			select.selectByValue(value);

		}

		@Override
		public void selectOptionByVisibleText(String text) {

			select.selectByVisibleText(text);

		}
	}

	@Override
	public boolean isElementPresent(String selector) {

		// TODO This method is probably unreliable and too web-driver specific?
		throw new RuntimeException("Method not implemented in WebDriverElement, use backing object.");
	}

	@Override
	public boolean isTextPresent(String text) {

		// TODO This method is probably unreliable and too web-driver specific?
		throw new RuntimeException("Method not implemented in WebDriverElement, use backing object.");
	}
}
