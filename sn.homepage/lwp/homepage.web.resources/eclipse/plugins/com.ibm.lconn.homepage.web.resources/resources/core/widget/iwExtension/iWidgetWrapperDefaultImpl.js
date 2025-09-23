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

dojo.provide("lconn.homepage.core.widget.iwExtension.iWidgetWrapperDefaultImpl");

dojo.require("lconn.homepage.core.widget.iwExtension.ItemSet");

dojo.require("com.ibm.mm.enabler.iw");

// add dynamically the ability to have a custom loading view for an iWidget, as the default implementation is hardcoded

/*
 com.ibm.mm.enabler.iWidgetWrapperDefaultImpl.prototype._setLoading = function(){
 this.rootElement.setAttribute("widgetStatus", "loading");
 var tempDiv = document.createElement("div");
 tempDiv.className = this.ns + "content";
 
 var html = ibmConfig.loadingHTML;
 
 tempDiv.innerHTML = html;
 this.rootElement.appendChild(tempDiv);
 };*/
com.ibm.mm.enabler.iWidgetInstanceStandard.prototype._constructor = function(/*RootElement*/widgetSpan,/*String*/ id){
    this.rootElement = widgetSpan;
    this.id = id;
    this.ns = widgetSpan.className.substr(0, 3);
    
    var nodes = [];
    className = this.ns + "Definition";
    com.ibm.mm.enabler.iw.utils.findElementByAttribute("class", className, this.rootElement, nodes, false);
    
    var node = nodes[0];
    var url = node.getAttribute("href");
    if (typeof(url) != "undefined" && url !== null) {
        this.widgetXMLUrl = url;
    }
};

dojo.declare("lconn.homepage.core.widget.iwExtension.widgetLoadService", com.ibm.mm.enabler.iw.services.widgetLoadService, {

    handleLoad: function(data, xhr){
    	hp_console_debug("extended widgetLoadService");
        // bind our substribute tags mechanism
        
        var fakeXhr = {};
        fakeXhr.responseText = this.substituteTags(xhr.responseText);
        this.inherited("handleLoad", arguments, [data, fakeXhr]);
    },
    
    substituteTags: function(xmlDescriptor){
        for (var tag in lconn.homepage.core.widget.iwExtension.widgetLoadService.VARS) {
            var regex = new RegExp("\{" + tag + "\}", "gi");
            xmlDescriptor = xmlDescriptor.replace(regex, lconn.homepage.core.widget.iwExtension.widgetLoadService.VARS[tag]);
        }
        
        return xmlDescriptor;
    }
});

lconn.homepage.core.widget.iwExtension.widgetLoadService.VARS = (function(){

	var result = {};
	
	try {
		var lookupField;		
		if (!lconn.homepage.global.isSecure) {
			lookupField = "url";
		}
		else {
			lookupField = "secureUrl";
		}
		
		for (var service in lconn.homepage.global.services) {
			result[service] = lconn.homepage.global.services[service][lookupField];
		}		
		
		// add the version field
		result.version = lconn.homepage.global.build;
		
	} catch (e){
		hp_console_debug("Exception while init lconn.homepage.core.widget.iwExtension.widgetLoadService.VARS " + e);
	}
    
    return result;
}());

com.ibm.mm.enabler.iWidgetWrapperDefaultImpl.prototype.loadWidgetDefinition = function(){
    var widgetSpan = this.rootElement;
    if (this.loaded) {
        return false;
    }
    
    this._setLoading();
    var url = this.getWidgetInstance().widgetXMLUrl;
    var iWidgetService = new lconn.homepage.core.widget.iwExtension.widgetLoadService();
    
    var store = lconn.homepage.core.widget.iwExtension.widgetStore;
    var widgetDefXml = store.get(factory.getWidgetId(this.id));
    
    if (widgetDefXml != null && widgetDefXml != "") {
        var fakeXhrObj = {
            responseText: widgetDefXml
        };
        
        iWidgetService.widgetId = this.id;
        
        // need to process this asynchronously (as the original bahavior in MuM based on XHR)
        setTimeout(dojo.hitch(iWidgetService, "handleLoad", null, fakeXhrObj), 1);
        //iWidgetService.handleLoad(null, fakeXhrObj);        
    }
    else 
        if (this.getWidgetInstance().widgetXMLUrl !== null) {
            iWidgetService.getWidgetXML(this.getWidgetInstance().widgetXMLUrl, this.id);
        }
        else {
            return false;
        }
    com.ibm.mm.enabler.debug.entry("iWidget.loadWidgetDefinition", true);
    return true;
};

/* fixing a bug in MuM code */

com.ibm.mm.enabler.iWidgetWrapperDefaultImpl.prototype.getWidgetItemSet = function(name){
    if (typeof(this.widgetItemSets) == "undefined" || this.widgetItemSets == null) {
        this._loadItemSets();
    }
    var rtnVal = this.widgetItemSets[name];
    
    /* bug fix: setting parent to this. It's null in original MuM code */
    var parent = this;
    
    if (typeof rtnVal == "undefined") {
        rtnVal = new com.ibm.mm.enabler.iw.DefaultItemSetImpl(parent, name);
        this.widgetItemSets[name] = rtnVal;
    }
    return rtnVal;
};


com.ibm.mm.enabler.iWidgetWrapperDefaultImpl.prototype._loadWidgetDefItemSets = function(){
    com.ibm.mm.enabler.debug.entry("iWidget._loadWidgetDefItemSets");
    if (typeof(this.widgetDef) != "undefined") {
        var names = this.widgetDef.getAllItemSetNames();
        for (var i = 0; i < names.length; i++) {
            var name = names[i];
            var itemSetWrapper = this.widgetDef.getItemSet(name);
            // fix here
            var parent = this;
            var anItemSet = new com.ibm.mm.enabler.iw.DefaultItemSetImpl(parent, itemSetWrapper.name, itemSetWrapper.onItemSetChanged, null, itemSetWrapper.isPrivate);
            var items = itemSetWrapper.items;
            for (var j in items) {
                var anItem = items[j];
                anItemSet.setItemValue(anItem.id, anItem.value, anItem.isReadOnly);
            }
            this.widgetItemSets[name] = anItemSet;
        }
    }
    com.ibm.mm.enabler.debug.exit("iWidget._loadWidgetDefItemSets");
};

// overriden to bind HP persistence mechanism to the iWidget spec attribute itemSet
com.ibm.mm.enabler.iWidgetWrapperDefaultImpl.prototype.getWidgetAttributes = function(){
    if (this.widgetAttributes == null) {
        this.widgetAttributes = new com.ibm.mm.enabler.iw.HPAttributesItemSet(this.getWidgetItemSet(iwConstants.ATTRIBUTES));
        this._loadWidgetAttributes();
        this.widgetAttributes._load();
    }
    
    return this.widgetAttributes;
}


// overriden to include attribute item set in the list returned (ignored in mum implementation as they handle it differently)
com.ibm.mm.enabler.iw.standardWidgetDefinition.prototype.getAllItemSetNames = function(name){

    var names = new Array();
    if (typeof(this.widgetDef.itemSetsArr) == "undefined" || this.widgetDef.itemSetsArr == null) {
        return names;
    }
    var i = 0;
    for (itemName in this.widgetDef.itemSetsArr) {
        var itemSetWrapper = this.widgetDef.itemSetsArr[itemName];
        // here we diverge from MuM
        if (typeof(itemSetWrapper) != "undefined" /*&& itemName != iwConstants.ATTRIBUTES*/) {
            names[i] = itemSetWrapper.name;
            i++;
        }
    }
    return names;
};
