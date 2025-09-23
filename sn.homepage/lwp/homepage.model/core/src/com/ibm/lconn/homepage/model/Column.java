/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.model;

/**
 * Matching between the contain ids as used on the front end and predefined columns
 * @author Vincent
 *
 */
public enum Column {
	COL_1("1"), COL_2("2"), COL_3("3");
	
	private String id;
	
	private Column(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
}
