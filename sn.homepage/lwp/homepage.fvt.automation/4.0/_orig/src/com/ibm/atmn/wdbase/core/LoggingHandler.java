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

import java.io.File;

public interface LoggingHandler {

	public enum LogLevel {
		TRACE, DEBUG, INFO, WARN, ERROR, OFF
	};

	void setLogLevel(LogLevel level);

	String getLogLevel();

	void setLogFile(String file);

	String getLogFileName();

	File getLogFile();

	void logTrace(String msg);

	void logDebug(String msg);

	void logInfo(String msg);

	void logWarn(String msg);

	void logError(String msg);
}
