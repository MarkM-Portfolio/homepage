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

dojo.provide("lconn.homepage.as.filter.FilterState");

// TODO Remove. State no stored in a simple obj in view
/** Object used to store the state of the filter */
dojo.declare("lconn.homepage.as.filter.FilterState", 
	null,
	{
		// The current filter being used
		currentFilter: "",
		
		// Previous filter used 
		// TODO: Maybe just use history array for this
		previousFilter: "",
		
		getCurrentFilter: function(){
			return this.currentFilter;
		},
		
		setCurrentFilter: function(filter){
			this.previousFilter = this.currentFilter;
			
			this.currentFilter = filter;
		},
		
		getPreviousFilter: function(){
			return this.previousFilter;
		}
	}
);