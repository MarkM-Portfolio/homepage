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

package com.ibm.atmn.wdbase.core.log;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ibm.atmn.wdbase.core.LoggingHandler;

public class BaseLogger implements LoggingHandler {

	private String logFileName;
	private String log4jConfigFile = "test_config/log4j.properties";

	private File logFile;
	private Logger logger;

	public BaseLogger() {

		this("base");
	}

	public BaseLogger(String name) {

		logger = Logger.getLogger(name);
		PropertyConfigurator.configure(log4jConfigFile);
		logger.setLevel(Level.TRACE);
	}

	public void setLogLevel(LogLevel level) {

		switch (level) {
		case TRACE:
			logger.setLevel(Level.TRACE);
			break;
		case DEBUG:
			logger.setLevel(Level.DEBUG);
			break;
		case INFO:
			logger.setLevel(Level.INFO);
			break;
		case WARN:
			logger.setLevel(Level.WARN);
			break;
		case ERROR:
			logger.setLevel(Level.ERROR);
			break;
		case OFF:
			logger.setLevel(Level.OFF);
			break;
		}
	}

	public void setLogLevel(String level) {

		if (level.equalsIgnoreCase("trace")) {
			setLogLevel(LogLevel.TRACE);
		}
		else if (level.equalsIgnoreCase("debug")) {
			setLogLevel(LogLevel.DEBUG);
		}
		else if (level.equalsIgnoreCase("info")) {
			setLogLevel(LogLevel.INFO);
		}
		else if (level.equalsIgnoreCase("warn")) {
			setLogLevel(LogLevel.WARN);
		}
		else if (level.equalsIgnoreCase("error")) {
			setLogLevel(LogLevel.ERROR);
		}
		else if (level.equalsIgnoreCase("off")) {
			setLogLevel(LogLevel.OFF);
		}
	}

	public String getLogLevel() {

		return logger.getLevel().toString();
	}

	public void setLogFile(String file) {

		logFileName = file;
	}

	public String getLogFileName() {

		return logFileName;
	}

	public File getLogFile() {

		return logFile;
	}

	public void logTrace(String msg) {

		logger.trace(msg);
	}

	public void logDebug(String msg) {

		logger.debug(msg);
	}

	public void logInfo(String msg) {

		logger.info(msg);
	}

	public void logWarn(String msg) {

		logger.warn(msg);
	}

	public void logError(String msg) {

		logger.error(msg);
	}
}
