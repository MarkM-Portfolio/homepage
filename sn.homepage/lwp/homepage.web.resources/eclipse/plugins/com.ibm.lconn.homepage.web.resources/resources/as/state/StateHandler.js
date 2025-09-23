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

dojo.provide("lconn.homepage.as.state.StateHandler");

dojo.require("com.ibm.social.as.listener.Registrar");
dojo.require("lconn.homepage.as.state.StateValidator");
dojo.require("lconn.homepage.as.state.StateTranslator");
dojo.require("com.ibm.social.as.constants.events");

dojo.require("dojo.hash");
dojo.require("dojo.cookie");

/**
 * Handler that looks after the state of the page/activity stream. Reads in
 * either the hash value or cookie value relating to current state. Basically,
 * it listens for the AS to load, and on load it gets the correct state, and
 * drives that back into the AS, forcing the stream to refresh. If the AS is
 * updated internally, an event is fired which this StateHandler reads, and then
 * uses to update the hash and cookie value (thus storing the state for the
 * user). If the hash value is manually changed, this informs the AS of it. All
 * communication with the AS is handled through either dojo events or RPC
 * services (if we're a gadget)
 * 
 * @author Robert Campion
 */

dojo.declare("lconn.homepage.as.state.StateHandler", 
[com.ibm.social.as.listener.Registrar],
{
	// Object which validates the state
	stateValidator: null,
	
	// Helper for translating states from 3.0 to new ones
	stateTranslator: null,
	
	// The default hash state to use
	defaultHashState: "myStream/imFollowing/all",
	
	myStream : "myStream/",
	
	// The current state of the page
	currentStateObj: null,
	
	// Boolean indicating that we should ignore the hash change
	ignoreHashChange: false,
	
	// The value of the cookie
	cookieStateValue: "ConnectionsHomepageActivityStreamState",
	
	// The value of the SC cookie
	cookieSmartCloudStateValue: "bhhashval",
	
	// Event to be called to change the AS state.
	updateStateEvent: com.ibm.social.as.constants.events.UPDATESTATE,
	
	// Event fired when the AS updates its view.
	stateUpdatedEvent: com.ibm.social.as.constants.events.STATEUPDATED,
	
	// Called when the activity stream gadget/widget loads.
	activityStreamLoadedEvent: com.ibm.social.as.constants.events.ACTIVITYSTREAMLOADED,
	
	constructor: function(){
		// Setup the validator
		this.stateValidator = new lconn.homepage.as.state.StateValidator();
		
		// Listen out for when the AS is loaded
		this.register(this.activityStreamLoadedEvent, dojo.hitch(this, "onLoad"));
	},
	
	/**
	 * Call as soon as the activity stream gadget/widget is loaded. It essentially
	 * tells the AS what state it should be in, based on the browser hash/cookie.
	 */
	onLoad: function(){
		// Listen out for state changes coming from the AS.
		abstractHelper.register(this.stateUpdatedEvent, dojo.hitch(this, "onStateUpdated"));

		// Get the current page state
		var stateHashValue = this.getValidStateHashValue();
		// Save the state
		this.saveState(stateHashValue);
		// Update the activity stream's state based on this state value
		this.updateState(stateHashValue);
		
		// Set timeout is used here so that we don't make duplicate requests on page load
		// when coming from a state where there is no hash (e.g. after login). 
		// We set the timeout to 200 ensuring that the initial onhashchange event has
		// fired before subscribing to this event. The timeout does not affect the loading
		// of the AS page. NB bookmarking links in the AS will not work until the event
		// is subscribed to.
		setTimeout(dojo.hitch(this, function(){
			// Subscribe to future hash changes
			this.subscribe("/dojo/hashchange", dojo.hitch(this, "hashValueChanged"));
		}), 200);
	},
	
	/**
	 * Called when the Activity Stream has updated its state.
	 * @param stateArr {Array} The state of the AS.
	 */
	onStateUpdated: function(stateArr){
		// Save the stateObj locally for future use.
		this.currentStateObj = stateArr;
		
		// Get the state in hash form.
		var stateHashValue = stateArr.join("/");
		
		// Is this hash state different from the existing one?
		// (without this check we may be make multiple XHR calls)
		if(stateHashValue != dojo.hash()){
			// We want to ignore this hash change.
			this.activateIgnoreHashFlag();
			// Save the state.
			this.saveState(stateHashValue);
		}
	},
	
	/**
	 * Called when the hash value has changed
	 * @param newState
	 */
	hashValueChanged: function(newState){
		// If the ignore hash change flag is set
		if(this.ignoreHashChange){
			// Set it to false for the next time
			this.ignoreHashChange = false;
			// Return - don't act on this hashchange
			return;
		}
		// Update the AS's state
		this.updateState(newState);
	},
	
	/**
	 * Save the state in the browser, using hash and cookie.
	 * @param stateHashValue
	 */
	saveState: function(stateHashValue){
		
		// Update the hash
		dojo.hash(stateHashValue);

		// this method commented out for consistent behaviour - we never save filter settings at any level
		// Update the cookie
//		dojo.cookie(this.cookieStateValue, stateHashValue, {
//			path: lconn.homepage.global.contextRoot
//		});
	},
	
	/**
	 * Update the state of the AS. It does this by calling an event/service
	 * and passing the state object.
	 * @param stateHashValue
	 */
	updateState: function(stateHashValue){
		// Split the hash value and update the config object
		var stateArray = stateHashValue.split("/");
		
		// Publish a side navigation event change, 
		// which also will update the AS view.
		abstractHelper.call(this.updateStateEvent, [stateArray]);
	},
	
	/**
	 * Turns the ignore hash change flag on by setting it to true.
	 */
	activateIgnoreHashFlag: function(){
		this.ignoreHashChange = true;
	},
	
	/**
	 * Get a valid state hash value. Will always return a valid has state,
	 * even if the one in the URL or cookie is invalid.
	 * @returns {String} a valid state hash value.
	 */
	getValidStateHashValue: function(){
		// Get the current page state
		var stateValue = this.getStateHashValue();
		var translator = this.getTranslator();
		
		if(!this.stateValidator.isStateValid(stateValue)){			
			if(translator.checkForLegacyMyStreamState(stateValue)){
				stateValue = this.myStream.concat(stateValue);
			}else{
				// State isn't valid, it may be coming from 3.0.
				// Attempt to translate it.
				// Only init this translator on demand.
				stateValue = translator.translateState(stateValue);
			}
			
			// If the state is still not valid
			if(!this.stateValidator.isStateValid(stateValue)){
				// Use the default one
				stateValue = this.defaultHashState;
			}
		}
		
		return stateValue;
	},
	
	/**
	 * Get the current page state value. Checks the URL hash first, and if it isn't
	 * present, it uses the state cookie.
	 * @returns (String) state value (if available)
	 */
	getStateHashValue: function(){
		// Get the current location.hash value
		var stateValue = dojo.hash();
		
		// If no hash is there
		if(!stateValue){
			// Check the cookie
			stateValue = dojo.cookie(this.cookieStateValue);
			if(!stateValue){
				stateValue = dojo.cookie(this.cookieSmartCloudStateValue);
				if(stateValue){
					stateValue = stateValue.replace("#", "");	
				}
				
			}
		}
		
		// Return the state value if it's available, otherwise, just
		// return an empty string (dojo.cookie may return 'undefined')
		return stateValue ? stateValue : "";
	},
	
	/**
	 * Get the state translator. Creates one if needs be.
	 */
	getTranslator: function(){
		if(!this.stateTranslator){
			this.stateTranslator = new lconn.homepage.as.state.StateTranslator();
		}
		
		return this.stateTranslator;
	},
	
	/**
	 * @returns {Object} the current state in object form.
	 */
	getCurrentStateObj: function(){
		return this.currentStateObj;
	}
});