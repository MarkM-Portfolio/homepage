/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.config.gettingstarted;


public class GettingStartedUrl {
	private String url;
	private String target = "_blank";
	
	public GettingStartedUrl(String url, String target) {
		this.url = url;
		if ( target != null ) {
			this.target = target;
		}
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String dump() {
		return "GettingStartedUrl = { url: " + url + ", target: " + target + "}";
	}
}
