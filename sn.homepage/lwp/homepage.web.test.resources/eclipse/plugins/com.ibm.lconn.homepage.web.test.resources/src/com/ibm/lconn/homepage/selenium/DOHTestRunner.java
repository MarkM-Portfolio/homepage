/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.selenium;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.ibm.lconn.homepage.selenium.util.DOHTestUtil;
/**
 * Runs the selenium tests. E.g. opens browser and navigates
 * to DOH's runner.html on a Connections server.
 * @author Robert Campion
 */
public abstract class DOHTestRunner {
    
    protected void runDOHSeleniumTests(WebDriver driver, String runnerLocation, String reportDir, String browserName, int timeOut) {
		
    	driver.get(runnerLocation);
		
		// Had to use 'var testResult' as a workaround here for remote tests
		WebDriverWait myWait = new WebDriverWait(driver, timeOut);
        ExpectedCondition<Boolean> conditionToCheck = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver input) {
            	Object objValue = ((JavascriptExecutor) input).executeScript("return (typeof window.dohHtmlReport !== 'undefined');");
                return (objValue instanceof Boolean && ((Boolean)objValue));
            }
        };
        
        myWait.until(conditionToCheck);

		// Get the HTML report of the tests
		String testResultsHtml = (String) ((JavascriptExecutor) driver).executeScript("return window.dohHtmlReport;");
		try{
			// Create the test result file
			FileOutputStream out = new FileOutputStream(reportDir + "/JSUTResults" + browserName + ".html");
			PrintStream p = new PrintStream(out);
			
			// Print the results to it
			p.println(testResultsHtml);
			p.close();
			
			Reporter.log("<br /> <a href=\"./JSUTResults" + browserName + ".html\"> See detailed results </a> <br />");
		}catch (Exception e){
			System.err.println("Error occurred whilst writing to the test file");
		}
		
		// See if any tests failed
		Boolean testsFailed = (Boolean) ((JavascriptExecutor) driver).executeScript("return window.testsFailed;");
		DOHTestUtil.createFile(reportDir + "/JSUTResults.properties");
		if(testsFailed){
			DOHTestUtil.addProperty(reportDir + "/JSUTResults.properties", "dohFailed", "true");
		}
		
		// Create a simple log file with the results
		DOHTestUtil.createFile(reportDir + "/JSUTResults" + browserName + ".log");
		// Add information as to whether this browser passed or failed
		
		try {
			FileOutputStream out = new FileOutputStream(reportDir + "/JSUTResults" + browserName + ".log", true);
			PrintStream p = new PrintStream(out);
			
			// Print the results to 
			// TODO - Uncomment the line below and comment the line after it
			//p.println(browser.substring(1) + ": " + (testsFailed.equals("true")? "failed" : "passed"));
			p.println("something" + ": passed");
			p.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
