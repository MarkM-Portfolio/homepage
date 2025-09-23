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
 * 
 * 
 * { type: 'menu', title : 'i8ln.placeholder', url : 'default_url_if_its _first_tab_item', menuItems: [ { title: 'tab_menu_activities', url: '#', iconClass: 'lotusIconActivity lotusIconSprite lotusSprite extraPadding' }, { title: 'tab_menu_blogs', url: '#', iconClass: 'lotusIconBlogs lotusIconSprite lotusSprite extraPadding' }, { title: 'tab_menu_profiles', url: '#', iconClass: 'lotusIconProfiles lotusIconSprite lotusSprite extraPadding' } ] }
 * 
 * 
 */

dojo.provide("lconn.homepage.dijit.tabs.TabMenu");

dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");

dojo.requireLocalization("lconn.homepage.dijit.tabs", "TabMenu");

dojo.declare(
		"lconn.homepage.dijit.tabs.TabMenu",
		[dijit._Widget, dijit._Templated, dijit._Container],
		{
			templatePath: dojo.moduleUrl("lconn.homepage", "dijit/tabs/templates/TabMenu.html"),
			title: "",
			url: "",
			_menu: null,
			menuItems: null,
			_connections:[],
			
			postCreate: function() {
				this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage.dijit.tabs", "TabMenu");
				this._menu = new dijit.Menu();
			},
			
			postMixInProperties: function() {
				
			},
			
			setTitle: function(title) {
				this.title=title;
				this.buildRendering();
			},
			
			setUrl: function(url) {
				this.url=url;
				this.buildRendering();
			},
			
			setMenuItems: function(menuItems) {
				this.menuItems = menuItems;
				this._buildMenu();
			},			
			
			onMoreClicked: function(evt){
				menuUtility.openMenu(evt, this._menu.id);
				dojo.stopEvent(evt);			
			},
			
			_onMenuItemClicked: function(/* dijit.MenuItem*/ menuItem){
				hp_console_debug("TabMenu - _onMenuItemClicked");
				dojo.publish(lconn.homepage.events.tabs.TAB_MENU_CLICKED, [this, menuItem]);
				this._menu.domNode.style.display="none";
			},
			
			_buildMenu: function(){
				hp_console_debug("TabMenu - _buildMenu");
				var me = this;
				if(me.menuItems!=null) {								
					
					me._menu = new dijit.Menu();
					
					dojo.forEach(me.menuItems, function(menuItem) {
						
						hp_console_debug("adding menuItem [" + me._resourceBundle[menuItem.title] + ", " + menuItem.iconClass + "]");
						var item = new dijit.MenuItem({label: me._resourceBundle[menuItem.title], iconClass: menuItem.iconClass});
						item.action = menuItem.url;
						me._menu.addChild(item);
					});
					me._connections.push(dojo.connect(me._menu, "onItemClick", me, "_onMenuItemClicked"));
					me._menu.domNode.style.display = "none";
					dojo.body().appendChild(me._menu.domNode);
				}			
			},
			
			destroy: function() {
				hp_console_debug("TabMenu - destroy");
				dojo.forEach(this._connections, function(connection){
					dojo.disconnect(connection);
				});	
			}
		}
);

