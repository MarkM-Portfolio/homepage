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

dojo.provide("lconn.homepage.core.base.baseDashboardIWidget");

dojo.require("dojox.data.dom");
dojo.require("lconn.homepage.utils.xslt");
dojo.require("lconn.homepage.core.base.userPrefUI");
dojo.require("dojo.fx");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

// Interface for iWidgets using support specific to the Homepage (custom mode handling, _save/_load, etc).
dojo.declare("lconn.homepage.core.base.baseDashboardIWidget", null, {

	_editHelper: null,
 
    constructor: function(){
        this._buttonResourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
		this._editHelper = new lconn.homepage.core.base.userPrefUI();
    },
    
    onLoad: function(){
        this._domID = "_" + this.iContext.widgetId + "_";
        
        var optionSet = this._getUserPrefItemSet();
        
        if (optionSet) {
            // note: postOnLoad is called by the callback			
            //	optionSet._load("handleUP?Act=LoadCustomization&FId=" + this.iContext.widgetId, dojo.hitch(this, "_initOptionView"));		
            this._initOptionView();
        }
        
        this.postOnLoad();
    },
    
    postOnLoad: function(){
        // override with code that should be executed after onLoad and that require the option itemset to be set
    },
    
    _initOptionView: function(){
        this.divOptionContainer = dojo.doc.createElement("div");
        dojo.addClass(this.divOptionContainer, "lotusHidden lotusEntry lotusClear");
        //this.divOptionContainer.style.display = "none";
        dojo.place(this.divOptionContainer, this.getWidgetDomNode("root"), 0);
        
        var optNode = this.divOptionContainer;
        /*
        this._toggler = new dojo.fx.Toggler({
            node: optNode,
            showDuration: 200,
            hideDuration: 200,
            showFunc: dojo.fx.wipeIn,
            hideFunc: dojo.fx.wipeOut
        });
        */
    },
    
    getMaxUrl: function(){
        return this.getDefaultParam("mappingRemoteUrl");
    },
    
    _loadResourceBundle: function(){
        hp_console_debug("override _loadResourceBundle to set this._resourceBundle!");
    },
    
    getResourceBundle: function(){
        this._loadResourceBundle();
        return this._resourceBundle;
    },
    
    getWidgetDomNode: function(nodeId){
        return this.iContext.getElementById(this._domID + nodeId);
    },
    
    getOption: function(name){
    
        var userPref = this._getUserPrefItemSet();
        var option = null;
        
        if (userPref != null) {
            option = option.getItemValue(name);
        }
        
        return option;
    },
    
    getOptionSet: function(){
        return this._getUserPrefItemSet();
    },
    
    getDefaultParam: function(name){
        return this.iContext.getItemSet("defaultParams").getItemValue(name);
    },
    
    getFeedUrl: function(name){
        return this.iContext.getItemSet("feeds").getItemValue(name);
    },
    
    _getSupportedModes: function(){
	var modes = null;
	if (this.iContext._mm != null) {
		modes = this.iContext._mm.getSupportedModes();
		}
	else {
		var temp = this.iContext.widget._widgetObject.widgetMetadata.supportedModes;  // TBD figure out a way to use handle object to get modes.
		if ((temp != null) && (typeof(temp) != "undefined")) {
			modes = temp.split(" ");
		}
	}
        return modes;
    },
    
    onModeChangedHomepage: function(evt){
    	// In some circumstances, in some browsers, focus jumps to top of page
    	// when we switch mode in widget. If we are switching, set keepfocus to true
        this._loadMode(evt.payload, true); // true == keepfocus
        this._persistMode(evt.payload);
    },
    
    focusModeSwitchButton: function() {
    	dojo.query('.dijitDownArrowButton', this._currentView.domNode)[0].focus();
    },
    
    _persistMode: function(modeId){
        if (modeId == null) 
            return;
        
        var optionSet = this._getUserPrefItemSet();
        
        if (optionSet) {
            optionSet.setItemValue("modePersist", modeId);
            this._saveOptions(optionSet);
        }
    },
    
    _loadMode: function(modeId, keepfocus){
        // introspection to call the appropriate function to handle the mode loading
        this._currentModeName = modeId;
        var fctName = "load" + modeId + "Mode";
        
        if ((this[fctName] != null) && (this[fctName] instanceof Function)) 
            this[fctName](keepfocus);
    },
    
    getModeName: function(modeId){
        // override this method to define mapping between modeId and modeName
        
        // modeName is the modeId is no mapping specificed
        var result = modeId;
        
        // by default, tried to get an ItemSet called "modes" to do the mapping
        var modesItemSet = this.iContext.getItemSet("modes");
        
        if (modesItemSet != null) {
            result = modesItemSet.getItemValue(modeId);
        }
        
        // i18n if needed
        result = this._translateString(result);
        
        return result;
    },
    
    getSupportedModesMapping: function(){
        var mapping = {};
        
        var modeIds = this._getSupportedModes();
        
        if (modeIds != null) {
            for (var i = 0; i < modeIds.length; i++) {
                var modeId = modeIds[i];
                mapping[modeId] = this.getModeName(modeId);
            }
        }
        
        return mapping;
    },
    
    _generateOptionView: function(){
        if (this._cacheOptionHtml != null) 
            return this._cacheOptionHtml;
        
        var metaDataNode = this.getWidgetDomNode("options_metadata");
        
        if (metaDataNode != null) {
            var xmlStr = metaDataNode.innerHTML;
            xmlStr = xmlStr.replace("<?xml:namespace prefix = hp />", "");
            xmlStr = xmlStr.replace("<?xml:namespace prefix = \"hp\" />", "");
            this._cacheOptionHtml = this._editHelper.generateOptionView(xmlStr);
            return this._cacheOptionHtml;
        }
        else {
            return null;
        }
    },
    
    onEdit: function(){
        if (!this._editing) 
            this.loadOptionsMode();
        else 
            this.hideOptionsMode();
    },
    
    onRefresh: function(){
        // default behaviour but not the most performant: reload completly current mode
        // override in subclasses to implement a more fine grained refresh		
        if (this._currentModeName && this._currentModeName != "") 
            this._loadMode(this._currentModeName);
    },
    
    _getUserPrefItemSet: function(){
        // "options" is used for backward compatibility with 2.0
        var userPrefSet = this.iContext.getItemSet("options");
        if (userPrefSet.getAllNames() == null) {
            // this is the standard iWidget way supported from 2.5)
            userPrefSet = this.iContext.getItemSet("attributes");
        }
		else { // CRE is being used.  Load HP specific settings into item set
			userPrefSet = this.iContext.getItemSet("attributes");
			var node = this.iContext.getRootElement();
			var p = node.id; // Use the fact that the node containing the Widget has an id that lets us use it to index into the lconn.homepage.global.beans array
			var HPItemSet = new lconn.homepage.core.widget.iwExtension.HPItemSetHelper(userPrefSet);
			HPItemSet.load(p);
		}
        
        return userPrefSet;
    },
    
    loadOptionsMode: function(){
        var html = this._generateOptionView();
        
        if (html != null) {
            this.divOptionContainer.innerHTML = html;
            
            this._optionForm = dojo.query(".form-option", this.divOptionContainer)[0];
            
            this._i18nCaptions(this._optionForm);
            
            this._itemSetToForm(this._optionForm, this._getUserPrefItemSet());
            
			this._editHelper.attachActionsToButtons(this.divOptionContainer, this, "hideOptionsMode", "onSaveOptions", this._buttonResourceBundle["CANCEL_BUTTON"], this._buttonResourceBundle["SAVE_BUTTON"]);
            
            //this._toggler.show();
            dojo.removeClass(this.divOptionContainer, "lotusHidden");
            this._editing = true;
        }
    },
    
    onSaveOptions: function(){
        if (this._optionForm != null) {
            var optionSet = this._getUserPrefItemSet();
            
            var optionsNames = optionSet.getAllNames();
            
            for (var i = 0; i < optionsNames.length; i++) {
                var name = optionsNames[i];
                var queryName = "[name='" + name + "']";
                
                var formElm = dojo.query(queryName, this._optionForm);
                
                if ((formElm != null) && (typeof(formElm[0]) != "undefined")) {
                    var elm = formElm[0];
                    
                    if (elm.type == "checkbox") {
                        if (elm.checked) {
                            optionSet.setItemValue(name, "true");
                        }
                        else {
                            optionSet.setItemValue(name, "false");
                        }
                    }
                    else {
                        optionSet.setItemValue(name, elm.value);
                    }
                }
            }
            this.hideOptionsMode();
            this.onSaved();
        }
    },
    
    onSaved: function(){
		  this._saveOptions(this._getUserPrefItemSet());
    },
    
    onHelp: function(){
        // default behavior: call global function openHelpWindow with help url defined in defaultParams itemset
        
        if (!openHelpWindow) 
            return;
        
        var url = this.getDefaultParam("helpUrl");
        
        if (url && url != "") {
            openHelpWindow(url);
        }
    },
    
    _saveOptions: function(userPrefSet){
		if (userPrefSet != null) {
			if (userPrefSet.save != null) {
				userPrefSet.save();
			}
			// CRE is being used
			// No save method - must be using CRE.  Do save directly.
			else {
			// TBD - find a better way to get the widget id
				var parent = this.iContext._widgetObject.domId;
				var HPItemSet = new lconn.homepage.core.widget.iwExtension.HPItemSetHelper(userPrefSet);
				HPItemSet.save(parent);
			}
		}
    },
    
    _itemSetToForm: function(formNode, itemSet){
        var iq = "input, select, textarea";
        var formElms = dojo.query(iq, formNode);
        for (var i = 0; i < formElms.length; i++) {
            var node = formElms[i];
            
            // [type!=file][type!=submit][type!=image][type!=reset][type!=button]
            if ((node.type == "button") || (node.type == "submit") || (node.type == "image") ||
            (node.type == "reset") ||
            (node.type == "file")) {
                // skip
                continue;
            }
            
            var itemValue = itemSet.getItemValue(node.name);
            itemValue = this._translateString(itemValue);
            itemSet.setItemValue(node.name, itemValue);
            
            if (node.type == "checkbox") {
                node.checked = itemValue == "true" ? true : false;
            }
            else 
                if (node.type == "select-one") {
                    for (var j = 0; j < node.options.length; j++) {
                        var option = node.options[j];
                        if (option.value == itemValue) {
                            node.selectedIndex = j;
                            break;
                        }
                    }
                }
                else {
                    node.value = itemValue;
                }
        }
    },
    
    loadPersistedMode: function(){
        var optionSet = this._getUserPrefItemSet();
        if (optionSet) {
            var modeId = optionSet.getItemValue("modePersist");
            
            if (modeId != null) {
                this._loadMode(modeId);
                return true;
            }
        }
        return false;
    },
    
    _i18nCaptions: function(formNode){
        var html = formNode.innerHTML;
        html = this._translateString(html);
        formNode.innerHTML = html;
    },
    
    _translateString: function(str){
        if (str != null) {
            var regex = /{([^}]*)}/g;
            var translateFct = dojo.hitch(this, "_translate");
            return str.replace(regex, translateFct);
        }
        else {
            return str;
        }
    },
    
    _translate: function(){
        var bundle = this.getResourceBundle();
        if (bundle != null) 
            return bundle[arguments[1]];
        else 
            return null;
    },
    
    hideOptionsMode: function(){
        //this._toggler.hide();
        dojo.addClass(this.divOptionContainer, "lotusHidden");
        this._editing = false;
    },
    
    _optionForm: null,
    _domId: null,
    _editing: false,
    _toggler: null,
    _cacheOptionHtml: null,
    divOptionContainer: null,
    _resourceBundle: null,
    _buttonResourceBundle: null,
    _currentModeName: null
});
