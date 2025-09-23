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



dojo.provide("lconn.homepage.dijit.admin.WidgetTabConfiguration");

dojo.require("com.ibm.oneui.util.proxy");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare(
		// widget name and class
		"lconn.homepage.dijit.admin.WidgetTabConfiguration",
		// superclass
		[dijit._Widget, dijit._Templated, dijit._Container],
		// properties and methods
		{
			
			widgetTabContainer: null,
			msgNode: null,
			selectNode: null,
			_resourceBundle: null,
			_ajaxRequestsHandler: null,
			
			requestUrl: "",
						
			msgText: "",
			enabledText: "",
			disabledText: "",
			saveText: "",
			
			templatePath: dojo.moduleUrl("lconn.homepage", "dijit/admin/templates/widgetTabConfiguration.html"),
			
			postCreate: function() {
				hp_console_debug("postCreate");
				this.getStatus();
			},
			
			postMixInProperties: function() {
				hp_console_debug("postMixInProperties");
				this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
				
				this.msgText = this._resourceBundle.WIDGET_TAB_CONFIG_MSG;
				this.enabledText = this._resourceBundle.WIDGET_TAB_CONFIG_ENABLED;
				this.disabledText = this._resourceBundle.WIDGET_TAB_CONFIG_DISABLED;
				this.saveText = this._resourceBundle.WIDGET_TAB_CONFIG_SAVE;
			},
			
			postStatus: function(evt) {
				var dropdownIndex = this.selectNode.selectedIndex;
				var dropdownValue =this.selectNode[dropdownIndex].value;
				var me = this;
				hp_console_debug("postStatus [" + dropdownValue + "]");
				
				hp_console_debug("requestUrl [" + me.requestUrl + "]");
				var args = { url:  me.requestUrl + "?value=" + dropdownValue + "&forceRefresh=" + (new Date()).valueOf(),
							load: dojo.hitch(me, "handleGet"),
							handleAs: "json"
						   };
				
				args.url = com.ibm.oneui.util.proxy(args.url);
				
				var xhrObj = dojo.xhrGet(args);
				
			},
			
			getStatus: function() {
				var me = this;

				hp_console_debug("getStatus");
				hp_console_debug("requestUrl [" + me.requestUrl + "]");
				var args = { url:  me.requestUrl + "?forceRefresh=" + (new Date()).valueOf(),
							load: dojo.hitch(me, "handleGet"),
							handleAs: "json",
							headers: {"X-Update-Nonce": "true"}
						   };
				
				args.url = com.ibm.oneui.util.proxy(args.url);
				
				var xhrObj = dojo.xhrGet(args);
				
			},
			
			handleGet: function(data, ioArgs) {
				hp_console_debug("handleGet - Widget Tab enabled [" + data.enabled + "]");
				if(!data.enabled) {
					this.selectNode[1].selected = "1";					
				}
				
				this.selectNode.disabled = false;
			}
		}
);
