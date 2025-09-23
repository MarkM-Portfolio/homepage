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

dojo.provide("lconn.homepage.core.base._DateFormatter");

dojo.require("lconn.homepage.utils.DateFormater");

dojo.declare(
	// widget name and class
	"lconn.homepage.core.base._DateFormatter",
	
	// superclass
	null,
	
	// properties and methods
	{
		// summary: Utility class to translate an ISO timestamp to a formatted human readable date
		// description: Wrapper around lconn.homepage.utils.DateFormater to format a date contained in a DOM node
		//		You may use lconn.homepage.utils.DateFormater directly to format a timestamp in a string
		
		formatDate: function(/* DOM Node */ node){				
			// summary: Replace the ISO timestamp in the node (innerHTML) with an human readable date
			// description: Only works if the innerHTML only contain the timestamp string
			
			hp_console_debug("lconn.homepage.core.base._DateFormatter - formatDate");
	
			try {			
				var s = node.innerHTML.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
				var formattedDate = this.getDateFormater().formatDateTime(s);
				if(formattedDate != undefined) {
					node.innerHTML = formattedDate;
				}
			}
			catch(e) {
				hp_console_debug("DateFormater - formatDate exception [" + e + "]");
			}
	
		},
		
		formatDateOnly: function(/* DOM Node */ node){				
			// summary: Replace the ISO timestamp in the node (innerHTML) with an human readable date
			// description: Only works if the innerHTML only contain the timestamp string
			
			hp_console_debug("lconn.homepage.core.base._DateFormatter - formatDateOnly");
	
			try {
				var s = node.innerHTML.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
				var formattedDate = this.getDateFormater().formatDate(s);
				if(formattedDate != undefined) {
					node.innerHTML = formattedDate;
				}
			}
			catch(e) {
				hp_console_debug("DateFormater - formatDateOnly exception [" + e + "]");
			}
	
		},
		
		formatTimeOnly: function(/* DOM Node */ node){				
			// summary: Replace the ISO timestamp in the node (innerHTML) with an human readable date
			// description: Only works if the innerHTML only contain the timestamp string
			
			hp_console_debug("lconn.homepage.core.base._DateFormatter - formatTimeOnly");
	
			try {
				var s = node.innerHTML.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
				var formattedTime = this.getDateFormater().formatTime(s);
				if(formattedTime != undefined) {
					node.innerHTML = formattedTime;
				}
			}
			catch(e) {
				hp_console_debug("DateFormater - formatTimeOnly exception [" + e + "]");
			}
	
		},
		
		getDateFormater: function(){
			// summary: return a DateFormater instance
			// returns: lconn.homepage.utils.DateFormater
			if(this._formater==null)
				this._formater = new lconn.homepage.utils.DateFormater();
				
			return this._formater;
		},
		
		// private
		_formater: null		
	}
);
