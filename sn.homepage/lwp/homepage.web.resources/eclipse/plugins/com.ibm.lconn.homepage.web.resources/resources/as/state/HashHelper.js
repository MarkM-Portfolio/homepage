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

dojo.provide("lconn.homepage.as.state.HashHelper");

/**
 * Helps with the management of the hash value state.
 * @author Robert Campion
 * 
 * @deprecated Use StateHelper instead.
 */

dojo.declare("lconn.homepage.as.state.HashHelper", null,
{
	// Array for storing all the hash values
	hashValues: null,
	
	constructor: function(){
		// Init the hash values
		this.hashValues = [];
	},
	
	/**
	 * Set the hash value for the view.
	 * @param value
	 */
	setHashView: function(value){
		this.hashValues[0] = value;
	},
	
	/**
	 * Set the hash first filter.
	 * @param value
	 */
	setHashFirstFilter: function(value){
		this.hashValues[1] = value;
	},
	
	/**
	 * Set the hash second filter.
	 * @param value
	 */
	setHashSecondFilter: function(value){
		this.hashValues[2] = value;
	},
	
	/**
	 * Set the rollup hash value.
	 * @param value
	 */
	setHashRollup: function(value){
		this.hashValues[3] = value;
	},
	
	/**
	 * Set the hash value at a certain index.
	 * @param value (String)
	 * @param index (Int)
	 */
	setHash: function(value, index){
		this.hashValues[index] = value;
	},
	
	/**
	 * Clear the secondary filter hash value.
	 */
	clearSecondFilter: function(){
		this.hashValues[2] = "";
	},
	
	/**
	 * Clear the rollup hash value.
	 */
	clearRollup: function(){
		this.hashValues[3] = "";
	},
	
	/**
	 * Get the current hash value
	 * @returns (String)
	 */
	getSavedHashValue: function(){
		// Join all the hash values using "/"
		var hashValue = this.hashValues.join("/");
		hashValue = this.removeTrailingSlashes(hashValue);
		hashValue = this.removeDoubleSlashes(hashValue);
		
		return hashValue;
	},
	
	/**
	 * Update the URL hash value.
	 */
	updateHashValue: function(){
		dojo.hash(this.getSavedHashValue());
	},
	
	/**
	 * Does the saved hash value we have equal the current
	 * hash fragment in the URL.
	 * @returns - true: they're equal
	 * 			  false: they're not equal
	 */
	savedEqualsFragment: function(){
		var currentHash = dojo.hash();
		var savedHash = this.getSavedHashValue();
		
		return (currentHash == savedHash);
	},
	
	/**
	 * Remove the trailing slashes from a string.
	 * @param string
	 * @returns (String)
	 */
	removeTrailingSlashes: function(string){
		// While there is a trailing "/"
		while(string.charAt(string.length-1) === "/"){
			// Remove it
			string = string.substring(0, string.length-1);
		}
		
		return string;
	},

	/**
	 * Remove all double slashes ("//") and replace them with
	 * singular ones ("/")
	 * @param string
	 * @returns (String)
	 */
	removeDoubleSlashes: function(string){
		return string.replace(/\/\//g, "/");
	}
});