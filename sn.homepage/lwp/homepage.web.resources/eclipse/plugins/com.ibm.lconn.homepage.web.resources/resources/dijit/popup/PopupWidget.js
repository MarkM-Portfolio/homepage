/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2007, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.dijit.popup.PopupWidget");

dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("dijit.Dialog");

dojo.requireLocalization("lconn.homepage", "jsp");

dojo.declare(
	// widget name and class
	"lconn.homepage.dijit.popup.PopupWidget",
	
	// superclass
	[dijit._Widget, dijit._Templated],
	
	// properties and methods
	{
		_dlg: null,
		loading: "",
		dialogTitle: "",
		dialogData: null,
		cancelText: "",
		closeText: "",
		
		dialogTitleNode: null,
		dialogContentNode: null,
		loadingNode: null,
		loadingImageNode: null,
		
		_resourceBundle: null,
		
		templatePath: dojo.moduleUrl("lconn.homepage","dijit/popup/templates/popupWidget.html"),
		
		postCreate: function() {
			this.loadingImageNode.src=lconn.core.url.getServiceUrl(lconn.core.config.services.webresources)
										+ "/web/com.ibm.lconn.core.styles.oneui3/images/loading.gif?etag=" + ibmConfig.versionStamp;
			
			this._dlg = new dijit.Dialog({duration:1});
			
			// Hide the dialog button supplied by the Dijit dialog as we supply our own.
			dojo.addClass(this._dlg.closeButtonNode, "lotusHidden");
			
			this._dlg.hideA11y = function(evt) {
				if (evt.keyCode == dojo.keys.ENTER || evt.keyCode == dojo.keys.ESCAPE) {
					dojo.publish(lconn.homepage.events.popup.DESTROY, [this, "destroy"]);
				}
			};
		},
		
		postMixInProperties: function() {				
			this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "jsp");
			this.cancelText = this._resourceBundle["jsp.admin.edit.button.cancel"];
			this.closeText = this._resourceBundle["jsp.main.close"];
			this.loading=this._resourceBundle["jsp.main.iwidget.loading"];
		},
		
		showPopup: function(title) {	
			this.dialogTitle=title;
			this.dialogContentNode.innerHTML = "";
			var titleText = dojo.doc.createTextNode(this.dialogTitle);
			this.dialogTitleNode.appendChild(titleText);
			this._dlg.attr('content', this.domNode);
			this._dlg.show();		
		},
		
		setContent: function(content) {
			this.dialogData = content;
			this.dialogContentNode.innerHTML = this.dialogData;
			
			this.dialogContentNode.style.display="block";
			this.loadingNode.style.display="none";
			
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.dialogContentNode);
			return this._dlg.domNode;
		},
		position: function() {
			this._dlg.layout();		
		},
		
		closePopup: function() {
			hp_console_debug("popupWidget --> publish [" + lconn.homepage.events.popup.DESTROY + "]")
			dojo.publish(lconn.homepage.events.popup.DESTROY, [this]);
		},
		
		hidePopup:function() {
			this._dlg.hide();
		},
		
		getContextRoot: function(){
			return lconn.homepage.global.contextRoot;
		},
		
		getAbsoluteUrl: function(url){
			var contextRoot = this.getContextRoot();
			var absUrl = null; 
			
			if (contextRoot != null){
				// basic for 2.5
				absUrl = contextRoot + url;
			} else {
				absUrl = url;
			}
			
			return absUrl;
		}
	}
);
