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

dojo.provide("lconn.homepage.core.widget.iwExtension.HPItemSetHelper");

/**
 * Helper methods for working with Home Page widget item sets
 * @name lconn.homepage.core.widget.iwExtension.HPItemSetHelper
 * @class 
 */
dojo.declare("lconn.homepage.core.widget.iwExtension.HPItemSetHelper", null, {
	
	_defaultLocationSave: "/web/handleUP.action?Act=SaveCustomization&FId=",
    _defaultLocationLoad: "/web/handleUP.action?Act=LoadCustomization&FId=",
	_itemset: null,

	/**
	 * @param {Object} itemset - itemset to be used
	 * @name constructor
	 * @function
	 * @memberOf lconn.homepage.core.widget.iwExtension.HPItemSetHelper
	 * @constructor
	 */
	constructor: function(itemset) {
		this._itemset = itemset;
	},
	
	
	/**
	 * @param {String} widgetId
	 * @name load
	 * @function
	 * @memberOf lconn.homepage.core.widget.iwExtension.HPItemSetHelper
	 * @public
	 * Loads item set attributes from Homepage bean.
	 */
	load: function(widgetId) {
		// always get from the bean as we only persist the options on a widget basis
        if (lconn.homepage.global.beans[widgetId] != null) {
            var data = lconn.homepage.global.beans[widgetId];
            
            if (!dojo.isString(data))
                return;
            
            data = window.unescape(data);
            var options = data.split(",");
            
            dojo.forEach(options, function(option){
                if (option != "") {
                    var opt = option.split("=");
                    var name = opt[0];
                    var value = window.unescape(opt[1]);
                    this._itemset.setItemValue(name, value);
                }
            }, this);
			lconn.homepage.global.beans[widgetId] = null; // Don't load from bean next time.
        }
    },
	
    /**
	 * @param {String} widgetId
	 * @name save
	 * @function
	 * @memberOf lconn.homepage.core.widget.iwExtension.HPItemSetHelper
	 * @public
	 * Persists the widget's serialized item set definition by making call back to HomePage server
	 */
	save: function(widgetId) {
		this.post(widgetId, this.serializeOptions(this._itemset));
	},
	
	/**
	 * @param {String} widgetId 
	 * @param {String} postBody - serialized string of item set 
	 * @name post
	 * @function
	 * @memberOf lconn.homepage.core.widget.iwExtension.HPItemSetHelper
	 * Persists the widget's serialized item set definition by making call back to HomePage server
	 */
	post: function(widgetId, postBody) {
		if (widgetId != null) {
				lconn.homepage.global.beans[widgetId] = null;
		}
		
		var uri = this._fixHPURI(lconn.homepage.global.contextRoot + this._defaultLocationSave + widgetId);
		
		dojo.rawXhrPost({
			url: uri,
			postData: "data=" + postBody,
			handleAs: "json"
		});
	},
	
	/**
	 * @param {String} itemset - item set to be serialized
	 * @name serializeOptions
	 * @function
	 * @public
	 * @memberOf lconn.homepage.core.widget.iwExtension.HPItemSetHelper
	 * @return {String} - serailization of item set suitable for putting in Post body.
	 */
	serializeOptions: function(itemset) {
		var names = itemset.getAllNames();
		var serializedStr = "";
    
		if (names == null) 
			return serializedStr;
    
		for (var i = 0; i < names.length; i++) {
			var name = names[i];
			var value = itemset.getItemValue(name);
        
			if (value != null) {
				serializedStr += name + "=" + window.escape(window.escape(value)) + ","; // SPR VBUT7EUCZQ, we have to escape twice			
			}
		}
    
    // remove last comma
		serializedStr = serializedStr == "" ? serializedStr : serializedStr.substr(0, serializedStr.length - 1);
		return window.escape(serializedStr);
	},
	
	/**
	 * @param {String} uri
	 * @name _fixHPURI
	 * @function
	 * @memberOf lconn.homepage.core.widget.iwExtension.HPItemSetHelper
	 * @return {String} - rewritten URL handle for posting to HP server.
	 */	
	_fixHPURI: function(uri) {
		var resultURI = uri;
		
		if (this._isHPURI(uri)){
			var completeRoot = lconn.homepage.global.contextRoot + "/web/handleUP";
			resultURI = uri.replace(/.*handleUP/, completeRoot);
		}
		
		return resultURI;	
	
	},
	/**
	 * @param {String} uri
	 * @name _isHPURI
	 * @function
	 * @memberOf lconn.homepage.core.widget.iwExtension.HPItemSetHelper
	 * @return {Bool} - returns true iff uri is for posting item set to HomePage server
	 */	
	_isHPURI: function(uri) {
		// determine whether the uri target our save/load servlet by testing various string pattern 
		var result = false;
		
		if (dojo.isString(uri)){
			result = ((uri.indexOf("handleUP") != -1) 
					&& (uri.indexOf("FId=") != -1) 
					&& ((uri.indexOf("Act=SaveCustomization") != -1) || (uri.indexOf("Act=LoadCustomization") != -1)))
		}
		
		return result;
	}
});
