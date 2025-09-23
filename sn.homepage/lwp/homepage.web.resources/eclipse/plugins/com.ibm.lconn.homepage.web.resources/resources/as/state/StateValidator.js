/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.as.state.StateValidator");

dojo.require("com.ibm.social.as.util.configNormalizer");

/**
 * Builds validation list and validates potential activity stream page states.
 * @author Robert Campion
 */

dojo.declare("lconn.homepage.as.state.StateValidator", [com.ibm.social.as.util.configNormalizer],
{
	// Property map containing all the valid states
	validStateMap: null,
	
	constructor: function(){
		// Init the state map
		this.validStateMap = {};
		
		// Build the valid state map
		this.buildValidStateMap();
	},
	
	/**
	 * Constructs the valid state map based on the AS config object
	 */
	buildValidStateMap: function(){
		var configObject = activityStreamConfig;
		
		this.addAllOptionsToMap("", configObject);
	},
	
	addAllOptionsToMap: function(prevHash, option){
		if ( option.filters ) {
			if(prevHash) {
				prevHash = prevHash + "/";
			}
			// Get its filters
			var filters = option.filters.options;
			// For every filter
			for(var f in filters){
				this.addAllOptionsToMap(prevHash +  f, filters[f]);
			}
		} else {
			this.validStateMap[prevHash] = true;
		}
	},
	
	/**
	 * Check if the state is valid.
	 * @param state
	 * @returns
	 */
	isStateValid: function(state){
		return this.validStateMap[state];
	}
});