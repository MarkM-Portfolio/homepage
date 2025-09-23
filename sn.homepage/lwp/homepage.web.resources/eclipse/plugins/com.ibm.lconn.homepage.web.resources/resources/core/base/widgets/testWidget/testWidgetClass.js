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

dojo.provide("testWidgetClass");

dojo.declare("testWidgetClass", null,{
	
	onLoad: function(){	
		hp_console_debug("testWidgetClass");
		var profile = this.iContext.getUserProfile();
		hp_console_debug(profile);
	},

	onview: function(){
		hp_console_debug("onview");
		
		var userPrefsDiv = this.iContext.getElementById("userPrefs");
		var profile = this.iContext.getUserProfile();
		
		userPrefsDiv.innerHTML = profile.getItemValue("email") + "<br/>";
		userPrefsDiv.innerHTML += profile.getItemValue("cn") + "<br/>";
		userPrefsDiv.innerHTML += profile.getItemValue("displayName") + "<br/>";
		userPrefsDiv.innerHTML += profile.getItemValue("userid") + "<br/>";		
	},
	
	dumpAttributes: function(){
		hp_console_debug("show attritues");
		var attr = this.iContext.getiWidgetAttributes();
		hp_console_debug(attr.getAllNames());
	},
	
	saveAttributes: function(){
		var attr = this.iContext.getiWidgetAttributes();
		attr.save();
	}	
	
});
