/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.config.gettingstarted;

/**
 * Caused when problem in reading getting started configuration file occurs
 *
 */
public class GettingStartedConfigException extends Exception {

	private static final long serialVersionUID = 8936802054389812613L;

    public GettingStartedConfigException(String message){
        super(message);
    }

    public GettingStartedConfigException(Throwable t){
        super(t);
    }

    public GettingStartedConfigException(String message, Throwable t){
        super(message, t);
    }
}
