/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 8955438897203160299L;
	
	private String errorCode;
	private boolean duplicateGadget = false;
	
	public ServiceException(Exception ex) {
		super(ex);
	}
	
	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(String msg, String errorCode) {
		super(msg);
		
		setErrorCode(errorCode);
	}

	public ServiceException(Throwable t) {
		super(t);
	}
	public ServiceException(String msg, Exception ex) {
		super(msg, ex);
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public final boolean isDuplicateGadget() {
		return duplicateGadget;
	}

	public final void setDuplicateGadget(boolean duplicateGadget) {
		this.duplicateGadget = duplicateGadget;
	}
}
