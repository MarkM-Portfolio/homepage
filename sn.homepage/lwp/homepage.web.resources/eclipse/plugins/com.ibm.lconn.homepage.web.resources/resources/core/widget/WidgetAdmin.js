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

dojo.provide("lconn.homepage.core.widget.WidgetAdmin");
dojo.require("com.ibm.social.incontext.util.html");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare(// widget name and class
"lconn.homepage.core.widget.WidgetAdmin", null, // properties and methods
{
    _resourceBundle: null,
    
    contextRoot: null,
	
	multipleInstanceChecked: false, // State of multiple widget checkbox
	ssoChecked: false, // State of SSO checkbox
	_servIx: -1, // Service mapping option being edited.
    
    constructor: function(){
        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
        
    },
    
    /**
	 * Called when submitting Widget admin form
	 */
    validateEditAddForm: function(){
    	hp_console_debug('validateEditAddForm');
    	// remove all current errors first
    	dojo.query(".lotusFormErrorField").forEach(function(node){
    		dojo.removeAttr(node, "aria-invalid");
    		dojo.removeClass(node, "lotusFormErrorField")
    	});
    	
        var titleElement = dojo.byId('widgettitle');
        if (titleElement.value == "") {
            this.setAdminErrorMessage(this._resourceBundle.ERROR_EMPTY_NAME);
            this.setTitleInError();
            return false;
        }
        hp_console_debug('validating form');
       
        var urlElement = dojo.byId('widgeturl');        
        if (urlElement.value == '') {
        
            this.setAdminErrorMessage(this._resourceBundle.ERROR_EMPTY_URL);
            this.setUrlInError();
            return false;
        }
        else {//check if the url has a correct protocol or is relative
        	if(!this.validateUrlFormat(urlElement.value)){
                this.setAdminErrorMessage(this._resourceBundle.ERROR_PROTOCOL);
                this.setUrlInError();
                return false;        		
        	}
        	urlElement.value = com.ibm.social.incontext.util.html.encodeHtml(urlElement.value);
        }
        
        //check if the secure url is present that it has a correct protocol or is relative
        var urlElementSecure = dojo.byId('widgetsecureurl');
        if(urlElementSecure.value != '') {
        	if(!this.validateUrlFormat(urlElementSecure.value)){
                this.setAdminErrorMessage(this._resourceBundle.ERROR_PROTOCOL);
                this.setUrlInError();
                return false;        		
        	}
        }
                	
        hp_console_debug('checking tab');
        // Checks if the widget is linked to a tab if it's not an OS Gadget. The widget can be displayed into the update or widget panel.
        // At least one panel must be selected. It is possible display the widget in both the panel
		var isGadgetCtl = dojo.byId("isGadget");
		if (isGadgetCtl.checked == false) {
			var widgettab_widgetElement = dojo.byId('widgettab_widget');
			var widgettab_updateElement = dojo.byId('widgettab_update');
			if (widgettab_widgetElement.checked == false && widgettab_updateElement.checked == false) {
				this.setAdminErrorMessage(this._resourceBundle.ERROR_WIDGETTAB);
				this.setWidgetTabInError();
				return false;
			}
		}
		
		// Create hidden form elements for the Service->OAuth mappings
		var serviceMappingCtrl = dojo.byId("serviceMappings");
		var opts = dojo.query(">option", serviceMappingCtrl);
		var sectionDiv = dojo.byId("sectionMappingField");
		for (var i=0; i<opts.length; i++) {
			var input = dojo.create("input", {"name": "serviceMappings", "value": opts[i].text, "class": "display:none"});
			dojo.place(input, sectionDiv);
		}
        
        return true;
    },
    
    /**
     * validate that the widget url is acceptable protocol format
     */
    validateUrlFormat: function(url){
    	if(url.indexOf('http://') == 0 || url.indexOf('https://') == 0 || url.indexOf('/') == 0 || (url.search(/\$\{.*\}/) != -1) ){
    		return true;
    	}
    	else {
    		return false;
    	}    	    	
    },
    		
    submitEditForm: function(actionName){
        var checkPassed = false;
        //alert("submit Editor actionName: " + actionName);
        if (actionName == 'openAdminPage') {
            checkPassed = true;
        }
        else {
            checkPassed = this.validateEditAddForm();
        }
        if (checkPassed == true) {
            var formName = 'widgetForm';
            var formNode = dojo.byId(formName);
            if (actionName != undefined && actionName != null) {
                formNode.action = actionName + ".action";
            }
            dojo.byId(formName).submit();
        }
    },
    
    submitForm: function(formKey, actionName){
        var checkPassed = false;
        
        //alert('submit form: ' + formKey + " actionName: " + actionName);
        
        // check if widget is selected
        
        if (actionName == 'newOpenEditForm' || actionName === 'clearWidgetCache') {
            checkPassed = true;
        } else {
            checkPassed = this.checkSelected(formKey);
        }
        
        // check that it's not system widget
        if (checkPassed == true && actionName == 'removeWidgets') {
            var mySelect = dojo.byId(formKey + 'WidgetsSelect');
            var systemWidget = mySelect.options[mySelect.selectedIndex].getAttribute('system');
            
            if (systemWidget == 'true') {
                checkPassed = 'false';
                this.setAdminErrorMessage(this._resourceBundle.ERROR_SYSTEM_WIDGETS);
            }
        }
        
        if (checkPassed == true) {
            var formName = formKey + 'Form';
            var formNode = dojo.byId(formName);
            formNode.action = actionName + ".action";
            dojo.byId(formName).submit();
        }
    },
     
    widgetHomepageSpecificValidation: function(){
    },
    
    setAdminErrorMessage: function(message){
    	hp_console_debug('setAdminErrorMessage: ' + message);
        dojo.byId('adminErrorText').innerHTML = message;
        dojo.attr("adminError", "role", "alert");
        dojo.byId('adminError').style.display = 'block';
        window.scrollTo(0, 0);
    },
    
    checkSelected: function(formId){
        var itemToCheck = dojo.byId(formId + 'WidgetsSelect');
        if (itemToCheck.selectedIndex == -1) {
            this.setAdminErrorMessage(this._resourceBundle.ERROR_SELECT_WIDGET);
            return false;
        }
        else {
            return true;
        }
    },
    
    setTitleInError: function(){
    	this.invalidField("widgettitle");
    },
    
    setUrlInError: function(){        
        urlStatus = dojo.byId('statuswidgeturl').value;
        urlsecureStatus = dojo.byId('statuswidgetsecureurl').value;
        hp_console_debug('setUrlInError urlStatus: %s', urlStatus);
        hp_console_debug('setUrlInError urlsecureStatus: %s', urlsecureStatus);
        
        if (urlStatus == '' && urlsecureStatus == '') {
            this.invalidField('widgeturl');
            this.invalidField('widgetsecureurl');
        }
        else {
            if (!(urlStatus == 'ok' || urlStatus == '')) 
            	this.invalidField('widgeturl');
            
            
            if (!(urlsecureStatus == 'ok' || urlsecureStatus == '')) 
            	this.invalidField('widgetsecureurl');
        }
        
    },
    
    setIconUrlInError: function(){
        
        urliconStatus = dojo.byId('statuswidgeticonurl').value;
        urlsecureiconStatus = dojo.byId('statuswidgeticonsecureurl').value;
        
        hp_console_debug('setIconUrlInError urliconStatus: -%s-', urliconStatus);
        hp_console_debug('setIconUrlInError urlsecureiconStatus: -%s-', urlsecureiconStatus);
        
        if (urliconStatus != '' && urliconStatus != 'ok') {
        	hp_console_debug('setting errror: %s', urliconStatus);
        	this.invalidField('widgeticonurl');
        }
        
        if (urlsecureiconStatus != '' && urlsecureiconStatus != 'ok') {
        	hp_console_debug('setting errror: %s', urlsecureiconStatus);
        	this.invalidField('widgeticonsecureurl');
        }
    },
    
    
    setWidgetTabInError: function(){
    	this.invalidField('widgettab_widget');
    	this.invalidField('widgettab_update');
    },
	
	isGadgetClicked: function(ctl) {
		var wview = dojo.byId("widgetview");
		var wview_container = dojo.byId("widgetview_container");
		var gadgetSettings = dojo.byId("gadgetSettings");
		if (ctl.id == "isGadget" && ctl.checked == true) {
			wview.checked = false;
			wview.disabled = true;
			dojo.style(gadgetSettings, "display", "block");
			dojo.style(wview_container, "opacity", 0.5);
			this.hideHelpText(dojo.byId("widgettab_updateField"));
			this.hideHelpText(dojo.byId("widgettab_widgetField"));
			
		}
		else if(ctl.checked == true) { // ctl.id = "isWidget"
			wview.checked = this.multipleInstanceChecked;
			wview.disabled = false;
			dojo.style(gadgetSettings, "display", "none");
			dojo.style(wview_container, "opacity", 1.0);
			this.showHelpText(dojo.byId("widgettab_updateField"));
			this.showHelpText(dojo.byId("widgettab_widgetField"));
			
		}
	},
	
	hideHelpText: function(node) {
		var nd = dojo.query(".lotusMeta", node);
		this.hideNode(nd[0]);
		dojo.query("input", node).removeAttr("aria-describedby");
		
		// Remove '*' node
		nd = dojo.query(".lotusFormRequired", node);
		this.hideNode(nd[0]);
		dojo.query("input", node).removeAttr("aria-required");
	},
	
	showHelpText: function(node) {
		var nd = dojo.query(".lotusMeta", node);
		this.showNode(nd[0]);
		if(nd[0]) dojo.query("input", node).attr("aria-describedby", nd[0].id);
		
		// Show '*' node
		nd = dojo.query(".lotusFormRequired", node);
		this.showNode(nd[0]);
		dojo.query("input", node).attr("aria-required", "true");
	},
	
	hideNode: function(node) {
		if (node != null) {
			dojo.style(node, "visibility", "hidden");
		}
	},
	
	showNode: function(node) {
		if (node != null) {
			dojo.style(node, "visibility", "visible");
		}
	},
	
	
	
	isSecurityClicked: function(ctl) {
		var sso = dojo.byId("gadgetsso");
		var sso_container = dojo.byId("sso_container");
		if (ctl.id == "gadgetSecurityRestricted") {
			sso.disabled = true;
			sso.checked = false;
			dojo.style(sso_container, "opacity", 0.5);
		}
		else {// clt.id == "gadgetSecurityUnrestricted
			sso.checked = this.ssoChecked;
			sso.disabled = false;
			dojo.style(sso_container, "opacity", 1.0);
		}
	},
	
	isShareboxClicked: function(ctl) {
		ins = dojo.byId("gadgetinsert");
		if (ctl.checked == true) {
			ins.disabled = false;
		}
		else { // clt.checked == false
			ins.disabled = true;
		}
	},
	
	
	// Multiple widget checkbox clicked.  Remember its state.
	//
	multipleWidgetClicked: function(ctl) {
		this.multipleInstanceChecked = ctl.checked;
	},
	
	// SSO checkbox clicked.  Rember its state
	//
	ssoClicked: function(ctl) {
		this.ssoChecked = ctl.checked;
	},
	
		// Set up field states for Gadgets
	setFieldStates: function(isSystemGadget, divsToDisable) {
	
	// Set gadget clicked related settings
		this.isGadgetClicked(dojo.byId("isGadget"));
		var restricted = dojo.byId("gadgetSecurityRestricted");
		
		// Disable SSO if restricted is checked.
		if (restricted.checked == true) {
			var sso = dojo.byId("gadgetsso");
			this.ssoChecked = false;
			sso.checked = false;
			sso.disabled = true;
		}
		
		// Enable the insertion point for sharebox if checked
		//
		var inShareBox = dojo.byId("gadgetsharebox");
		var gadgetInsert = dojo.byId("gadgetinsert");
		this.simulateDisabledOptions(gadgetInsert);
		if (inShareBox.checked == true) {
			gadgetInsert.disabled = false;
		}
		
		this._selectFirstMapping();
		
		// If this is a system gadget, disable fields that user shouldn't be changing.
		//
		if (isSystemGadget) {
			for (var i=0; i<divsToDisable.length; i++) {
				this._disableFieldsInDiv(divsToDisable[i]);
			}
		}
	
	},
	
	//
	// Disable input fields in div 'divId'.
	//
	_disableFieldsInDiv: function(divId) {
		var inputs = dojo.query("input", divId);
		for (var i=0; i<inputs.length; i++) {
			inputs[i].disabled = true;
		}
	},
	
	
	// Simulate disabled sharebox insertion points
	// IE, Safari don't support "disabled" attribute on <option ...> tags!
	// Chrome shows disabled option as selectable.
	//
	simulateDisabledOptions: function(selection) {
			var styles = {"color":"#CCCCCC"};
			var children = selection.children;
			
			for(var i=0; i<children.length; i++) {
				if (children[i].disabled == true) {
					// Replace disabled options with an <optgroup> to simulate a disabled option
					//
					var node = dojo.create("optgroup", {"label": children[i].text});
					dojo.style(node, styles);
					dojo.place(node, children[i], "replace");
				}
			}
	},
	
	
	// Service mapping handlers.
	
	deleteServiceMapping: function() {
		var smCtrl = dojo.byId("serviceMappings");
		if (smCtrl != null) {
			var opts = smCtrl.options;
			for (var i=0; i<opts.length; i++) {
				if (opts[i].selected == true) {	
					smCtrl.options[i] = null;
					break;
				}
			}
		}
		this._selectFirstMapping();
	},
	
	addServiceMapping: function() {
		var newMappingDiv = dojo.byId("sectionMappingField");
		var serviceMappingsCtrl = dojo.byId("serviceMappings"); 
		var buttonDiv = dojo.byId("serviceMappingsFieldButtons");
		
		serviceMappingsCtrl.disabled = true;
		dojo.style(buttonDiv, "display", "none");
		dojo.style(newMappingDiv, "display", "block");
	},
	
	addServiceMappingComplete: function() {
		var smCtrl = dojo.byId("gadgetoauth");
		var serviceField = dojo.byId("serviceName");
		var serviceMappings = dojo.byId("serviceMappings");
		var selected = null;
		
		
		selected = this._getSelectedOption(smCtrl);
		
		if (this._servIx != -1) { // Got here from doing an Edit service mapping.  First delete the original mapping
			this.deleteServiceMapping();
			this._servIx = -1;
		}
		
		if (selected != null) {
			var service = serviceField.value;
			var text = service + "->" + selected.value;
			var newOpt = dojo.create("option", {value: text, label:text, innerHTML:text}, serviceMappings);
		}
		this.cancelServiceMappingComplete();
	},
	
	cancelServiceMappingComplete: function() {
		var smCtrl = dojo.byId("gadgetoauth");
		var newMappingDiv = dojo.byId("sectionMappingField");
		var serviceMappingsCtrl = dojo.byId("serviceMappings"); 
		var buttonDiv = dojo.byId("serviceMappingsFieldButtons");
		
		serviceMappingsCtrl.disabled = false;
		dojo.style(buttonDiv, "display", "block");
		dojo.style(newMappingDiv, "display", "none");
		
	},
	
	editServiceMapping: function() {
		var serviceNameCtrl = dojo.byId("serviceName");
		var oauthClientCtrl = dojo.byId("gadgetoauth");
		var serviceMappingCtrl = dojo.byId("serviceMappings");
		
		
		var service = this._getSelectedOption(serviceMappingCtrl);
		this._servIx = service.index;
		
		if (service != null) {
			// Populate controls for editing
			var serviceName = this._getServiceName(service.text);
			var oauthClient = this._getOauthClient(service.text);
			
			serviceNameCtrl.value = serviceName;
			oauthClientCtrl.value = oauthClient;
		
		}
		// Show result
		this.addServiceMapping();
	},

	_getSelectedOption: function(ctrl) {
		var selected = null;
		var opts = ctrl.options;
			for (var i=0; i<opts.length; i++) {
				if (opts[i].selected == true) {
					selected = opts[i];
					break;
				}
			}
		return(selected);
	},
	
	_getServiceName: function(service) {
		var arrowIx = service.indexOf("->");
		return(service.substr(0, arrowIx));
	},
	
	_getOauthClient: function(service) {
		var arrowIx = service.indexOf("->");
		return(service.substr(arrowIx + 2));
	},
	
	_selectFirstMapping: function() {
		var serviceMappingCtrl = dojo.byId("serviceMappings");
		var opts = serviceMappingCtrl.options;
		if (opts != null && opts.length > 0) {
			opts[0].selected = true;
		}
	},
	
	invalidField: function(fieldId){
		dojo.addClass(fieldId, "lotusFormErrorField");
		dojo.attr(fieldId, "aria-invalid", "true");
	}


});

lconn.homepage.core.widget.WidgetAdmin.submit = function(formKey, actionName){
    widgetAdmin.submitForm(formKey, actionName);
};
