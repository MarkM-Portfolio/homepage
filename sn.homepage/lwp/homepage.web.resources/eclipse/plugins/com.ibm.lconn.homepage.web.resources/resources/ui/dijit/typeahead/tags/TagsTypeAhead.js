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

dojo.provide("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAhead");

dojo.require("lconn.core.TypeAhead");

dojo.declare(
	// class
	"lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAhead", 
	// superclass
	[lconn.core.TypeAhead], 
	{
		//		multipleValues: bool ** OVERRIDE
        //      Sets whether or not this type-ahead should support multiple values.
        //      If true, then typing a token (usually comma) will cause the type-ahead
        //      to reset and begin searching for a new name. 
        multipleValues: false,
        
		isInputValid: function(){
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAhead --> isInputValid");
			// valid if at least one char in the input box					
			return (this.textbox.value != null && this.textbox.value != "" && dojo.trim(this.textbox.value) != "");
		},
		
		clearText: function() {
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAhead --> clearText");
			this.textbox.value="";
		}
	}
);


