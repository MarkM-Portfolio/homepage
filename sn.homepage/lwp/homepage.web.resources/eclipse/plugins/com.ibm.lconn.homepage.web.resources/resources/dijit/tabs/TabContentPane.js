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

dojo.provide("lconn.homepage.dijit.tabs.TabContentPane");

dojo.require("lconn.homepage.core.base._DateFormatter");
dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("dijit.layout.ContentPane");

dojo.requireLocalization("lconn.homepage.dijit.tabs", "TabContentPane");

dojo.declare(
		"lconn.homepage.dijit.tabs.TabContentPane",
		dijit.layout.ContentPane,
		{
			// loadingMessage: String
			// Overriden property from parent to display OneUI loading
			// icon	
			loadingMessage: "<div style='text-align:center;'><img alt='LoadingTest' src='nav/common/styles/images/loading.gif'/></div>",
			subscriptions: [],
			_resourceBundle: null,
			_dateFormatter: null,
			
			postCreate : function() {				
				this._dateFormatter = new lconn.homepage.core.base._DateFormatter();
				this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage.dijit.tabs", "TabContentPane");
				this.subscriptions.push(dojo.subscribe(lconn.homepage.events.tabs.PAGE_EVENT, dojo.hitch(this, "_onPageEvent")));				
			},
			
			onLoad : function() {
				if (!this.mustAppendContent) {
					hp_console_debug("TabContentPane [onLoad]");
					this._setupToggles();
				}

				this.inherited("onLoad", arguments);
				
			},
			
			appendContent : function(html) {
				hp_console_debug("TabContentPane [appendContent]");
				this._setupToggles();
			},
			
			_setupToggles: function() {
				
				hp_console_debug("TabContentPane [_setupToggles]");
				var me = this;
				var detailNodes = dojo.query(".river-details", this.domNode);
				
				dojo.forEach(detailNodes, function(node) {
					
					var titleNode = dojo.query(".river-story-title", node.parentNode);

					if (titleNode.length > 0) {
						var id = titleNode[0].parentNode.getAttribute("uuid");
						hp_console_debug("TabContentPane - uuid = " + id);
						dojo.connect(titleNode[0], "onclick", dojo.hitch(me, "onToggleClick"));
					}
				});
				
				hp_console_debug("***************** localise dates");
				var me = this;
				dojo.query(".localize-date", me.domNode).forEach(
															function(node) {
																hp_console_debug("dateNode [" + node + "]");
																me._dateFormatter.formatDate(node);
															});
				hp_console_debug("***************** localise dates completed");
				this.parseSemanticTag(this.domNode);
				hp_console_debug("TabContentPane [_setupToggles] completed");
			},
			
			parseSemanticTag : function(/* DOMNode */container) {
				try{
					hp_console_debug("TabContentPane.parseSemanticTag");
					SemTagSvc.parseDom(null, container);
				} catch(e){
					hp_console_debug("TabContentPane.parseSemanticTag exception [" + e + "]");
					// swallow
				}
			},
			
			_updateUrlWithParameter : function (url, paramName, paramValue) {
				hp_console_debug("_updateUrlWithParameter [" + url + ", " + paramName + ", " + paramValue + "]");
				
				if(paramName==lconn.homepage.river.PageSizeController.PAGE_SIZE_PARAM) {
					if(url.indexOf("?") != -1) {
						url = url.substr(0, url.indexOf("?")) + "?" + paramName + "=" + paramValue;						
					} else {
						url = url + "?" + paramName + "=" + paramValue;
					}
				}
				else {
					paramName = paramName.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
					var regexS = paramName+"=([^&#]*)";
					var regex = new RegExp( regexS, "g" );
					var results = regex.exec(url);
					if( results == null )
						url = url + this.getSearchPartSeparator(url) + paramName + "=" + paramValue;
					else {
						url = url.substr(0, regex.lastIndex - results[1].length) + paramValue + url.substr(regex.lastIndex, url.length);
					}
				}
				hp_console_debug("TabContentPane - _updateUrlWithParameter [" + url + "]");
				return url;
			},
			
			getSearchPartSeparator: function(url) {
				var regex = new RegExp("\\?");
				var results = regex.exec(url);
				if(results == null)
					return "?";
				else
					return "&";
			},
			
			_onPageEvent : function(params) {
				var url = this.href;
				hp_console_debug("TabContentPane - onPageEvent - parameter count [" + params.length + "]");
				for (i = 0; i < params.length; i++) {
					hp_console_debug("TabContentPane - onPageEvent " + params[i][0] + "="	+ params[i][1]);
					url = this._updateUrlWithParameter(url,  params[i][0],  params[i][1]);
				}
				hp_console_debug("TabContentPane - onPageEvent with URL [" + url + "]");
				this.attr("href", url);
			},
			
			onToggleClick: function(/* Event */ evt){						
				// user clicked the header bar and not a link inside the header bar
				hp_console_debug("TabContentPane [onToggleClick]");
				if ((evt.target.tagName != "A") || (dojo.hasClass(evt.target, "toggle-action"))){
					this.toggle(evt.currentTarget);
					
					dojo.stopEvent(evt);
				}
			},

			toggle: function(/* DOMNode */ node) {
				
				var id = node.parentNode.getAttribute("uuid");		
				hp_console_debug("id [ " + id  + "]");
				var toggleNode = dojo.query(".toggle-action", node)[0];
				hp_console_debug("toggleNode [ " + toggleNode  + "]");
				var detailNode = dojo.byId(id);
				hp_console_debug("detailNode [ " + detailNode  + "]");
				
				var toggler = new dojo.fx.Toggler( {
					node :detailNode,
					showDuration :200,
					hideDuration :200,
					showFunc :dojo.fx.wipeIn,
					hideFunc :dojo.fx.wipeOut
				});
				
				if (dojo.hasClass(toggleNode, "showing")) {
					hp_console_debug("hiding");
					dojo.removeClass(detailNode.parentNode, "lotusEntryExpended");
					dojo.removeClass(toggleNode, "showing");
					toggler.hide();
					toggleNode.innerHTML = this._resourceBundle["toggle_more"];
					
				} else {
					hp_console_debug("showing");
					dojo.addClass(toggleNode, "showing");
					dojo.addClass(detailNode.parentNode, "lotusEntryExpended");
					toggler.show();				
					toggleNode.innerHTML = this._resourceBundle["toggle_hide"];
				}
						
			},
			
			destroy: function(){
				hp_console_debug("TabContentPane - destroy");
				dojo.forEach(this.subscriptions, function(subscription){
					dojo.unsubscribe(subscription);
				});		
			}
			
		}
);
