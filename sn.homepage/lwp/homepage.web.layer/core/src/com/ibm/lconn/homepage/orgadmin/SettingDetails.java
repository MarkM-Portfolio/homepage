/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2016                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.orgadmin;

public class SettingDetails {

	public static String TYPE_BOOL="bool";
	public static String TYPE_INT="integer";
	public static String TYPE_TEXT="text";
	public static String TYPE_LONG="long";
	public static String TYPE_GATEKEEPER="gatekeeper";
	
	public static String STATUS_GOOD="good";
	public static String STATUS_WARNING="warning";
	public static String STATUS_ERROR="error";
	
	public static int INPUT_TEXTBOX = 0;
	public static int INPUT_CHECKBOX = 1;
	
	private String name="";
	private String title=null;
	private String description=null;
	private String gatekeeper=null;
	private String prompt=null;
	private String warning=null;
	private String type=null;
	private String validation=null;
	private String value=null;
	private String status=null; // error | warning | good
	private String message=null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getGatekeeper() {
		return gatekeeper;
	}
	public void setGatekeeper(String gatekeeper) {
		this.gatekeeper = gatekeeper;
	}
	public boolean useCheckbox() {
		if (type != null) {
			if (type.equalsIgnoreCase(TYPE_BOOL) || type.equalsIgnoreCase(TYPE_GATEKEEPER)) {
				return true;
			}
		}
		return false;
	}
	public boolean isGatekeeperSetting() {
		return ((type!=null) && (type.equals(SettingDetails.TYPE_GATEKEEPER)));
	}
	public String toString() {
		return "{" + toString("name",name) +toString("type",type)+toString("title",title)+toString("description",description)+toString("prompt",prompt)+
				toString("validation",validation)+toString("status",status)+toString("gatekeeper",gatekeeper)+toString("value",value +"}");
	}
	public String toString(String name, String value) {
		return name + ":\""+value+"\",";
	}
}