/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.core.base.userPrefUI");
dojo.require("dojox.data.dom");
dojo.require("com.ibm.lconn.gadget.util.userPrefHelper");
dojo.requireLocalization("lconn.homepage", "jsp");

/**
 * Helper code for generating edit form for Gadgets
 * @name lconn.homepage.core.base.userPrefUI
 * @extends lconn.homepage.core.base.userPref
 * @class lconn.homepage.core.base.userPrefUI
 */

dojo.declare("lconn.homepage.core.base.userPrefUI", [com.ibm.lconn.gadget.util.userPrefHelper], {

	strings: dojo.i18n.getLocalization("lconn.homepage", "jsp"), // Resource strings

/**
 * Takes HemePage tags and returns transformed HTML
 * 
 * @memberOf lconn.homepage.core.base.userPrefUI
 * @name generateOptionView
 * @param xmlStr(String) - String of Homepage tags
 * @function
 * @public
 */
 	generateOptionView: function(xmlStr){
        var html = null;
		
		xmlStr = xmlStr.replace("<?xml:namespace prefix = hp />", "");
		var xmlOptions = dojox.data.dom.createDocument(xmlStr);
		
		var xsltPath = dojo.moduleUrl("lconn.homepage.core.base", "options.xsl");
		xsltPath += "?version=" + lconn.homepage.global.build;
		html = lconn.core.xslt.transformDocument(xmlOptions, xsltPath.toString());
		return(html);
            
    },
	
    /**
     * Attaches actions to the Cancel and Save buttons
     * 
     * @memberOf lconn.homepage.core.base.userPrefUI
     * @name generateOptionView
     * @param node - HTML root node to search for save and cancel buttons
     * @param obj {Object} - dojo object with saveAction and cancelAction
     * @param cancelAction {method} - cancel method on 'obj'
     * @param saveAction {method} - save method on 'obj'
     * @param cancelString {String} - Cancel string to show on cancel button.
     * @param saveString {String} - Save string to show on save button.
     * @function
     * @public
     */
	
	attachActionsToButtons: function(node, obj, cancelAction, saveAction, cancelString, saveString) {
		var saveButton = dojo.query(".save-button", node)[0];
		dojo.connect(saveButton, "onclick", obj, saveAction);
		saveButton.value = saveString;
		var cancelButton = dojo.query(".cancel-button", node)[0];
		dojo.connect(cancelButton, "onclick", obj, cancelAction);
		cancelButton.value = cancelString;
	},
	
	/**
     * Converts form fields to UserPref format
     * 
     * @memberOf lconn.homepage.core.base.userPrefUI
     * @name UserPrefFromForm
     * @param form - pulls out userPrefs from form
     * @function
     * @return - returns userPref object
     * @public
     */
	//
	// Converts form fields to UserPref format
	//
	UserPrefFromForm: function(form) {
		var result = {};
		var nodes = dojo.query("select", form);
		for (var i=0; i<nodes.length; i++) {
			var n = nodes[i];
			result[n.name] = n.value;
		}
		nodes = dojo.query("input[type='string']", form);
		for (i=0; i<nodes.length; i++) {
			var n = nodes[i];
			result[n.name] = n.value;
		}
		nodes = dojo.query("input[type='checkbox']", form);
		for (i=0; i<nodes.length; i++) {
			var n = nodes[i];
			if (n.checked) {
				result[n.name] = "true";
			}
			else {
				result[n.name] = "false";
			}
		}
		return(result);
	},
	
	 /**
     * Generates markup for editing userPrefs
     * 
     * @memberOf lconn.homepage.core.base.userPrefUI
     * @name userPrefsToMarkup
     * @param userPrefs {Object} - userPrefs to show
     * @param userPrefDefaults {Object} - default settings
     * @param widgetId - used to create unique div id
     * @return - node with edit form.
     * @function
     * @public
     */
	// Generates markup for editing userPrefs
	// 
	// returns node.
	userPrefsToMarkup: function(userPrefs, userPrefDefaults, widgetId) {
		var root = dojo.create("div", {"id": this._editFormId(widgetId), "class": "lotusEntry lotusClear"});
		var editFormHtml = this.generateOptionView(this._userPrefsToHPTags(userPrefs));
		// Select default option choices
		var node = dojo.place(editFormHtml, root);
		this._setDefaultPrefs(root, userPrefDefaults);
		this._addListDirections(root, userPrefs);
		return(root);
	},
	
	// Adds user directions for list fields
	_addListDirections: function(node, userPrefs) {
		for (var name in userPrefs) {
			if (userPrefs.hasOwnProperty(name)) {
				if (userPrefs[name].dataType.toUpperCase() == "LIST") {
					// Find list node
					var n = dojo.query("[name=" + name + "]", node)
					if (n != null && n.length > 0) {
						var listHelp = this.strings['jsp.widget.listHelp'];
						dojo.place('<span class="lotusMeta">' + listHelp + '</span>', n[0], "after");
					}
				}
			}
		}
	},
	
	// Sets input field default values.
	//
	_setDefaultPrefs: function(node, userPrefDefaults) {
		for (var name in userPrefDefaults) {
			if (userPrefDefaults.hasOwnProperty(name)) {
				var defValue = userPrefDefaults[name];
				
				var n = dojo.query("[name=" + name + "]", node);
				if (n != null && n.length > 0) {
					var field = n[0];
					switch(field.type) {
						case "text": {
							field.value = defValue;
							break;
						}
						
						case "checkbox": {
							if (defValue == "true") {
								field.checked = true;
							}
							break;
						}
						
						case "select-one": {
							var optNode = dojo.query("option[value=" + defValue +"]", field);
							if (optNode != null && optNode.length > 0) {
								optNode[0].selected = true;
							}
							break;
						}
						
						default: 
							break;
						
					}
				}
			}
		}
	},
	
	//
	// Transforms userPrefs to <hp: ..> xml island.  Returns island as string
	//
	_userPrefsToHPTags: function(userPrefs) {
		var result = '<hp:options xmlns:hp="http://www.ibm.com/homepage">';
		
		for (var i in userPrefs) {
			if (userPrefs.hasOwnProperty(i)) {
				var up = userPrefs[i];
				switch(up.dataType.toUpperCase())
				{
					case "ENUM": {
						result += this._userPrefEnumToHP(up); 
						break;
					}
					case "BOOL": {
						result += this._userPrefBoolToHP(up);
						break;
					}
					case "STRING":
					case "LIST": {
						result += this._userPrefStringToHP(up);
						break;
					}
					default:
						break;
				}
			}
		}
		
		result += "</hp:options>";
		return(result);
	},
	
	_userPrefBoolToHP: function(up) {
		var result = "";
		var required = "";
		if (up.required == true) {
			required = 'hp:required="true"';
		}
		var result = '<hp:option hp:caption="' + up.displayName + '" hp:type="checkbox" hp:id="' + up.name + '" ' + required + '/>';
		return(result);
	},
	
	_userPrefStringToHP: function(up) {
		var result = "";
		var required = "";
		if (up.required == true) {
			required = 'hp:required="true"';
		}
		var result = '<hp:option hp:caption="' + up.displayName + '" hp:type="string" hp:id="' + up.name + '" ' + required + '/>';
		return(result);
	},
	
	
	_userPrefEnumToHP: function(up) {
		var vals = up.orderedEnumValues;
		var required = "";
		if (up.required == true) {
			required = 'hp:required="true"';
		}
		var result = '<hp:option hp:caption="' + up.displayName + '" hp:type="select" hp:id="' + up.name + '" ' + required + '>';
		for (var i = 0; i<vals.length; i++) {
			result += '<hp:choice hp:value="' + vals[i].displayValue + '" hp:id="' + vals[i].value + '"></hp:choice>';
		}
		
		result += "</hp:option>";
		return(result);
	},
	
	_editFormId: function(widgetId) {
		return(widgetId + "_editForm");
	}
});
