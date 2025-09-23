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

dojo.provide("lconn.homepage.core.widget.iwExtension.ItemSet");

dojo.require("com.ibm.mm.enabler.iw.ItemSet");
dojo.require("lconn.homepage.core.widget.iwExtension.HPItemSetHelper");

// add _save and _load extensions to ItemSet to provide persistence. This is specific to Homepage

com.ibm.mm.enabler.iw.DefaultItemSetImpl.prototype._helper = new lconn.homepage.core.widget.iwExtension.HPItemSetHelper(this);

com.ibm.mm.enabler.iw.DefaultItemSetImpl.prototype._serializeOptions = function() {
	return(this._helper.serializeOptions());
};

com.ibm.mm.enabler.iw.DefaultItemSetImpl.prototype._save = function(uri, callbackFn){
    var widgetId = this.parent;
    if (widgetId != null) {
        lconn.homepage.global.beans[widgetId] = null;
    }
	
	uri = this._fixHPURI(uri);
    
    dojo.rawXhrPost({
        url: uri,
        postData: "data=" + this._serializeOptions(),
		handleAs: "json"
    });
};

// **** functions for backward compatibility ****

com.ibm.mm.enabler.iw.DefaultItemSetImpl.prototype._isHPURI = function(uri){
	// determine whether the uri target our save/load servlet by testing various string pattern 
	var result = false;
	
	if (dojo.isString(uri)){
		result = ((uri.indexOf("handleUP") != -1) 
				&& (uri.indexOf("FId=") != -1) 
				&& ((uri.indexOf("Act=SaveCustomization") != -1) || (uri.indexOf("Act=LoadCustomization") != -1)))
	}
	
	return result;
}

com.ibm.mm.enabler.iw.DefaultItemSetImpl.prototype._fixHPURI = function(uri){
	// rewrite handleUP url to include full URL path 
	// (causing problem for very specific cases for relative URIs in the updates page)
	
	var resultURI = uri;
	
	if (this._isHPURI(uri)){
		var completeRoot = lconn.homepage.global.contextRoot + "/web/handleUP";
		resultURI = uri.replace(/.*handleUP/, completeRoot);
	}
	
	return resultURI;	
}

// ****

com.ibm.mm.enabler.iw.DefaultItemSetImpl.prototype._load = function(uri, callbackFn){
	// always get from the bean as we only persist the options on a widget basis	
	if (this._isHPURI(uri)) {
		this._helper.load(this.parent);
		
		if (callbackFn != null && dojo.isFunction(callbackFn)){
			callbackFn();
		}					
	}
	else {
		// original behavior
		var sync = (callbackFn == undefined) ? true : false;
		
		var req = dojo.xhrGet({
			url: uri,
			sync: sync,
			handleAs: "text"
		});
		
		req.addCallback(dojo.hitch(this, "_unserializeOptions", callbackFn));
	}     
};

com.ibm.mm.enabler.iw.DefaultItemSetImpl.prototype._unserializeOptions = function(callbackFn, data, evt){
    if (!dojo.isString(data)) 
        return;
    
    data = window.unescape(data);
    var options = data.split(",");
    
    for (var i = 0; i < options.length; i++) {
        if (options[i] != "") {
            var option = options[i].split("=");
            var name = option[0];
            var value = window.unescape(option[1]);
            this.setItemValue(name, value);
        }
    }
    
    if (callbackFn != null) 
        callbackFn(); // should we pass anything to the callbackFn?
};

// Wrapper around our custom ItemSet to support the save() method defined in ManagedItemSet interface in HP
dojo.declare("com.ibm.mm.enabler.iw.HPAttributesItemSet", [com.ibm.mm.enabler.iw.ManagedItemSet], {

	//TODO: check legacy
    _defaultLocationSave: "/web/handleUP.action?Act=SaveCustomization&FId=",
    _defaultLocationLoad: "/web/handleUP.action?Act=LoadCustomization&FId=",
    _widgetId: null,
    _internalSet: null,
    _helper: null,
    
    constructor: function(itemSet, parent){
        if (itemSet == null) 
            throw new Error("itemSet is null");
        
		if (typeof(parent) == "undefined") {
			this._widgetId = itemSet.parent;
			}
		else {
			this._widgetId = parent;
		}
        this._internalSet = itemSet;
        this._helper = new lconn.homepage.core.widget.iwExtension.HPItemSetHelper(this);
		//this._load();
    },
    
    setItemValue: function(){
        this._internalSet.setItemValue.apply(this._internalSet, arguments);
    },
    
    getItemValue: function(){
        return this._internalSet.getItemValue.apply(this._internalSet, arguments);
    },
    
    getAllNames: function(){
        return this._internalSet.getAllNames.apply(this._internalSet, arguments);
    },
    
    removeItem: function(){
        this._internalSet.getAllNames.apply(this._internalSet, arguments);
    },
    
    clone: function(){
        return new com.ibm.mm.enabler.iw.HPAttributesItemSet(this._widgetId, this._internalSet.clone());
    },
    
    isReadOnly: function(){
        return this._internalSet.isReadOnly.apply(this._internalSet, arguments);
    },
    
    getItemSetDescription: function(){
        return this._internalSet.getItemSetDescription.apply(this._internalSet, arguments);
    },
    
    save: function(){
		if (typeof(this._internalSet._save) == "undefined") { // Using CRE, no itemset overrides
			this._helper.save(this._widgetId);
		}
		else {
			this._internalSet._save(lconn.homepage.global.contextRoot + this._defaultLocationSave + this._widgetId);
		}
    },
	
	_internal: function(){
		return this._internalSet;
	},
    
    _load: function(){
        // always get from the bean as we only persist the options on a widget basis
        if (lconn.homepage.global.beans[this._widgetId] != null) {
            var data = lconn.homepage.global.beans[this._widgetId];
            
            if (!dojo.isString(data))
                return;
            
            data = window.unescape(data);
            var options = data.split(",");
            
            dojo.forEach(options, function(option){
                if (option != "") {
                    var opt = option.split("=");
                    var name = opt[0];
                    var value = window.unescape(opt[1]);
                    this._internalSet.setItemValue(name, value);
                }
            }, this);
        }
    }
});
