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

/*
 * Takes a JSON String for the config parameter
 * 
 * example :
 * 
 *  { Tabs : [ { type: 'tab', title : 'i8ln.placeholder', url : 'url' }, {type: 'menu', title : 'i8ln.placeholder', url : 'default_url_if_its _first_tab_item', menuItems: [ { title: 'tab_menu_activities', url: '#', iconClass: 'lotusIconActivity lotusIconSprite lotusSprite extraPadding' }, { title: 'tab_menu_blogs', url: '#', iconClass: 'lotusIconBlogs lotusIconSprite lotusSprite extraPadding' }, { title: 'tab_menu_profiles', url: '#', iconClass: 'lotusIconProfiles lotusIconSprite lotusSprite extraPadding' } ] } ] }
 */

dojo.provide("lconn.homepage.dijit.tabs.TabsSupport");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");
dojo.require("lconn.homepage.dijit.tabs.Tab");
dojo.require("lconn.homepage.dijit.tabs.TabMenu");
dojo.require("lconn.homepage.dijit.tabs.TabContentPane");

dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.requireLocalization("lconn.homepage.dijit.tabs", "TabsSupport");

dojo.declare(
		"lconn.homepage.dijit.tabs.TabsSupport",
		[dijit._Widget, dijit._Templated, dijit._Container],
		{
			SELECTED_TAB_STYLE_NAME: "lotusSelected",
			TYPE_TAB: "tab",
			TYPE_MENU_TAB: "menu",
			
			templatePath: dojo.moduleUrl("lconn.homepage", "dijit/tabs/templates/TabsSupport.html"),
			config: null,
			items: null,
			tabContainerNode: null,
			tabHeaderContainer: null,
			tabContentPane: null,
			currentTabNode: null,
			_subscriptions: [],
			_resourceBundle: null,
			
			postCreate: function() {
				
				var me = this;
				me.tabContainerNode.appendChild(me.tabContentPane.domNode);
				me._resourceBundle = dojo.i18n.getLocalization("lconn.homepage.dijit.tabs", "TabsSupport");
				
				dojo.forEach(me.items, function(item, index) {
					
					hp_console_debug("tab item [" + me._resourceBundle[item.title] + " = " + item.url + "]");
					var tab=null;
					
					if(item.type == me.TYPE_TAB) {					
						
						hp_console_debug("adding tab [" + me._resourceBundle[item.title] + " = " + item.url + "]");
						tab = new lconn.homepage.dijit.tabs.Tab();
						tab.setTitle(me._resourceBundle[item.title]);
						tab.setUrl(item.url);
						
					} else if(item.type == me.TYPE_MENU_TAB) {
						
						hp_console_debug("adding tabmenu [" + me._resourceBundle[item.title] + " = " + item.url + "]");
						tab = new lconn.homepage.dijit.tabs.TabMenu();
						tab.setTitle(me._resourceBundle[item.title]);
						tab.setMenuItems(item.menuItems);
						
					}
					
					if(tab!=null) {
						me.tabHeaderContainer.appendChild(tab.domNode);
						
						if(index==0) {
							me.currentTabNode = tab.domNode;
							hp_console_debug("currentTabNode [" + me.currentTabNode + "]");
							me.updateTabMarkup(null, me.currentTabNode);
							me.tabContentPane.attr("href", item.url);
						}
					}
				});
				
				me._subscriptions.push(dojo.subscribe(lconn.homepage.events.tabs.TAB_CLICKED, dojo.hitch(me, "_onTabClicked")));
				me._subscriptions.push(dojo.subscribe(lconn.homepage.events.tabs.TAB_MENU_CLICKED, dojo.hitch(me, "_onTabMenuClicked")));
			},
			
			checkForPageSizeAndAttachToUrl : function() {
				if(this.tabContentPane.href) {
					var url = this.tabContentPane.href;
					hp_console_debug("checkForPageSizeAndAttachToUrl [" + url  + "]");
					var paramName = lconn.homepage.river.PageSizeController.PAGE_SIZE_PARAM.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
					var regexS = "[\\?&]"+paramName+"=([^&#]*)";
					var regex = new RegExp( regexS, "g" );
					var results = regex.exec(url);
					if( results != null ) {
						hp_console_debug(this.getSearchPartSeparator(url) + lconn.homepage.river.PageSizeController.PAGE_SIZE_PARAM + "=" + results[1]);
						return this.getSearchPartSeparator(url) + lconn.homepage.river.PageSizeController.PAGE_SIZE_PARAM + "=" + results[1];
					}
					else {
						hp_console_debug(this.getSearchPartSeparator(url) + lconn.homepage.river.PageSizeController.PAGE_SIZE_PARAM + "=" + lconn.homepage.river.PageSizeController.DEFAULT_PAGE_SIZE);
						return this.getSearchPartSeparator(url) + lconn.homepage.river.PageSizeController.PAGE_SIZE_PARAM + "=" + lconn.homepage.river.PageSizeController.DEFAULT_PAGE_SIZE;
					}
				}
			},
			
			getSearchPartSeparator: function(url) {
				var regex = new RegExp("\\?");
				var results = regex.exec(url);
				if(results == null)
					return "?";
				else
					return "&";
			},
			
			postMixInProperties: function() {
				hp_console_debug("postMixInProperties - config [" + this.config + "]");
				hp_console_debug(this.config.Tabs.length);
				this.items = this.config.Tabs;
				this.tabContentPane = new lconn.homepage.dijit.tabs.TabContentPane();
			},
			
			_onTabClicked: function(evt) {
				hp_console_debug("_onTabClicked [" + evt.currentTarget + "]");
				
				if(this.currentTabNode != evt.target.parentNode) {
					this.updateTabMarkup(this.currentTabNode, evt.target.parentNode);
					this.currentTabNode =  evt.target.parentNode;
					this.tabContentPane.attr("href", evt.currentTarget.getAttribute("url"));
				}				
			},
			
			_onTabMenuClicked: function(tabMenu, menuItem) {
				hp_console_debug("_onTabMenuClicked [" + menuItem + "]");
				hp_console_debug("_onTabMenuClicked [" + tabMenu.domNode + "]");
				
				if(this.currentTabNode != tabMenu.domNode) {
					this.updateTabMarkup(this.currentTabNode, tabMenu.domNode);
					this.currentTabNode =  tabMenu.domNode;
				}				
				
				this.tabContentPane.attr("href", menuItem.action);
			},
			
			updateTabMarkup: function(oldTabNode, newTabNode){
				if (newTabNode != null){
					dojo.addClass(newTabNode, this.SELECTED_TAB_STYLE_NAME);				
				}
							
				if (oldTabNode != null){
					dojo.removeClass(oldTabNode, this.SELECTED_TAB_STYLE_NAME);
				}
			},
			
			destroy: function(){
				hp_console_debug("TabsSupport - destroy");
				dojo.forEach(this._subscriptions, function(subscription){
					dojo.unsubscribe(subscription);
				});		
			}			
		}
);
