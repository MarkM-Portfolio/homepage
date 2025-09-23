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

dojo.provide("lconn.homepage.core.common.DashboardPanel");

dojo.require("lconn.homepage.core.widget.WidgetGadgetPanel");
dojo.require("lconn.homepage.core.common.DashboardDropDownButton");
dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.require("com.ibm.lconn.gadget.container.Topics");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");
dojo.require("dijit.form.Button");

dojo.require("com.ibm.social.incontext.util.html");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare("lconn.homepage.core.common.DashboardPanel", [dijit._Widget, dijit._Templated, dijit._Container, lconn.homepage.core.widget.WidgetGadgetPanel], // properties and methods
{
    // parameters
    title: "",
    thisWidgetID: "",
    maxUrl: "",
    _bodyWidget: null,
    _layoutModel: null,
    
    _mainMenu: null,
    //_moveSubMenu: null,
    
    _iconActionsUrl: null,
    _menuTitle: "",
	_errorMsg: "",
    
	_makeGadgetSubscriptionsSubscription : null,
    
    maxWidth : null,
    
    // settings
    isContainer: true,
    templatePath: dojo.moduleUrl("lconn.homepage", "core/common/templates/dashboardPanel.html"),
    
    // DOM nodes
    body: null,
    arrow: null,
    iconImage: null,
    root: null,
    titleBar: null,
    closeIcon: null,
    maxIconNode: null,
    
    editNode: null,
    refreshNode: null,
    helpNode: null,
    moveWidgetNode: null,
    _mustUpdateMenu: null,
    
    _resourceBundle: null,
    
    UP: 0,
    DOWN: 1,
    RIGHT: 2,
    LEFT: 3,
    
    postCreate: function(){
        this.root.id = this.thisWidgetID;
        
        this._mustUpdateMenu = true;
        
		this.setMaxUrl(this.maxUrl);

		this.subscribe(lconn.homepage.events.widget.LOADED, "onWidgetParamUpdated");
        
        // remove title from root node (added automatically by dojo but cause issues)
        this.root.removeAttribute("title");
        
        this.title = com.ibm.social.incontext.util.html.decodeHtml(this.title);
        if (dojo.isIE) {
        	this.maxNode.innerText=this.title;
        } else {
        	this.maxNode.textContent=this.title;
        } 
                
        this.subscribe(lconn.homepage.events.layout.UPDATED, "_updateMenu");
        this.subscribe(lconn.homepage.events.init.COMPLETED, "_updateMenu");
        this.subscribe(lconn.homepage.events.widget.LOADED, "_updateMenu");
        this._makeGadgetSubscriptionsSubscription = dojo.subscribe(lconn.homepage.events.widget.LOADED, this, "makeGadgetSubscriptions");
    },
    
    makeGadgetSubscriptions : function() {
    	var gadgetNode = dojo.query("iframe", this.containerNode)[0];
    	if(gadgetNode) {
    		var gadgetDiv = gadgetNode.parentElement;
			var gadgetDivId = dojo.attr(gadgetDiv, "id");
			
			var topic = com.ibm.lconn.gadget.container.Topics.getSiteTopic(gadgetDivId, com.ibm.lconn.gadget.container.Topics.GadgetWindow.SITE_TOPIC_SET_TITLE);
			this.subscribe(topic, "onWidgetTitleUpdated");
			
			this.maxWidth = dojo.position(this.containerNode)['w'] + "px"; // do not allow the gadget to grow beyond our initial width
			topic = com.ibm.lconn.gadget.container.Topics.getSiteTopic(gadgetDivId, com.ibm.lconn.gadget.container.Topics.GadgetWindow.AFTER_ADJUST_WIDTH);
			this.subscribe(topic, "onWidthUpdated");
			
			this.maxHeight = "600px"; // hard code max-height of 600px, UI recommendation is not to go beyond 400.
			topic = com.ibm.lconn.gadget.container.Topics.getSiteTopic(gadgetDivId, com.ibm.lconn.gadget.container.Topics.GadgetWindow.AFTER_ADJUST_HEIGHT);
			this.subscribe(topic, "onHeightUpdated");
			
	    	dojo.unsubscribe(this._makeGadgetSubscriptionsSubscription); // once we've done our job, don't get called again.
    	}
    },
    
    onWidthUpdated : function() {
		dojo.style(dojo.query("iframe", this.containerNode)[0], "maxWidth", this.maxWidth);
    },
    
    onHeightUpdated : function() {
		dojo.style(dojo.query("iframe", this.containerNode)[0], "maxHeight", this.maxHeight);
    },
    
    postMixInProperties: function(){
        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
        this._menuTitle = this._resourceBundle.MENU_TITLE;
        this._iconActionsUrl = this._blankGif;
		this._errorMsg = this._resourceBundle.ERROR;
		this._errorAlt = this._resourceBundle.ERROR_ALT;
    },
    
    onWidgetParamUpdated: function(widgetObj){
        if (widgetObj == this._bodyWidget) {
            // update max url link
            if (this._bodyWidget.getMaxUrlAsync != null) {
            	this._bodyWidget.getMaxUrlAsync().then(dojo.hitch(this, function(res) {
            		this.setMaxUrl(res);
            	}));
            } 
        }
    },
    
    onWidgetTitleUpdated: function(title){
        this.maxNode.innerHTML=title;
    },
    
    setMaxUrl: function(maxUrl){
        if (maxUrl) {
            this.maxUrl = maxUrl;
            this.maxNode.href = this.maxUrl;
            this.maxNode.target = "_blank";
            dojo.removeClass(this.maxNode, "homepageUnclickable");
        }
    },
    
    setBodyWidget: function(widget){
        this._bodyWidget = widget;
        
        if (this._bodyWidget.getMaxUrlAsync != null) {
        	this._bodyWidget.getMaxUrlAsync().then(dojo.hitch(this, function(res) {
        		this.setMaxUrl(res);
        	}));
        }
        
        this.addChild(widget);
    },
    
    getBodyWidget: function(){
        return this._bodyWidget;
    },
    
    startup: function(){
        if (this._bodyWidget != null && this._bodyWidget.startup) 
            this._bodyWidget.startup();
    },
    
    _setupMenus: function(){
    	hp_console_debug("_setupMenus");
        if (this._mainMenu == null) {
        	hp_console_debug("build _mainMenu");
            this._mainMenu = new dijit.Menu();
            dojo.connect(this._mainMenu, "onItemClick", dojo.hitch(this, "_onMainMenuItemClicked"));
        }
        
        this._initMenuItems();
    },
    
    _clearMenu: function(menu){
    	hp_console_debug("_clearMenu [" + menu + "]");
        var children = menu.getChildren();
        dojo.forEach(children, function(item){
            menu.removeChild(item);
        });
    },
    
    _initMenuItems: function(){
    	hp_console_debug("_initMenuItems");
        
        this._clearMenu(this._mainMenu);
        
        var menuItems = this._buildMoveWidgetMenuItems();
     		 
		for (var i = 0; i < menuItems.length; i++) {
			this._mainMenu.addChild(menuItems[i]);
		}

		// function to add final items to menu and start the menu
		// used whether supportedActions are available or not
		var completeAndStartMenu = dojo.hitch(this, function() {
			if ( this._layoutModel.canCustomize() ) {
				this._mainMenu.addChild(this._buildMenuItem(this._resourceBundle.CLOSE, "close", "homepageSprite homepageSprite-widget-close"));
			}
			
			this._mainMenu.startup();
			hp_console_debug("_initMenuItems COMPLETED");
		});
		
		// Getting supported actions requires an async call to the container.
		// getSupportedActionsAsync will return a promise,
		// which may not be resolved immediately.
		if (this._bodyWidget.getSupportedActionsAsync != null) {
			this._bodyWidget.getSupportedActionsAsync().then(
				// callback for when promise is resolved
				// work add supported actions items as required, then start menu
				dojo.hitch(this, function(supportedActions) {
					if ( supportedActions ) {
						if (this._contains(supportedActions, "edit")) {
							this._mainMenu.addChild(this._buildMenuItem(this._resourceBundle.EDIT, "edit", "homepageSprite homepageSprite-widget-edit"));
						}
						
						if (this._contains(supportedActions, "help")) {
							this._mainMenu.addChild(this._buildMenuItem(this._resourceBundle.HELP, "help", "homepageSprite homepageSprite-widget-help"));
						}
						
						if (this._contains(supportedActions, "onRefreshNeeded") || this._contains(supportedActions, "refresh")) {
							this._mainMenu.addChild(this._buildMenuItem(this._resourceBundle.REFRESH, "refresh", "homepageSprite homepageSprite-widget-refresh"));
						}
					}
					
					// add final item to menu and start it
					completeAndStartMenu();
				}),
				// callback for when promise is rejected
				// need to add final item to menu and start it
				dojo.hitch(this, function() {
					completeAndStartMenu();
				})
			);
		}		
    },
         
    _buildMoveWidgetMenuItems: function(){
        // summary: returns an array of MenuItem with the available target location depending
        //	 on the current layout of the page
        
        var menuItems = [];
        
        if (this._layoutModel != null && this._layoutModel.canCustomize() ) {
            var currentPos = this._layoutModel.getWidgetPosition(this.id);
            
            if (currentPos == null) 
                return [];
            
            
            var changeColLabel = currentPos.getColumn() == 0 ? this._resourceBundle.MOVE_RIGHT : this._resourceBundle.MOVE_LEFT;
            var action = currentPos.getColumn() == 0 ? this.RIGHT : this.LEFT;
            var styleClass = currentPos.getColumn() == 0 ? "homepageSprite homepageSprite-widget-moveRight" : "homepageSprite homepageSprite-widget-moveLeft";
            
            menuItems.push(this._buildMenuItem(changeColLabel, action, styleClass));
            
            if (this.domNode.parentNode.id == "dnd2" && (this._layoutModel.getNumberOfColumns() == 3 || dojo.byId("dnd3") != null)) {
                menuItems.push(this._buildMenuItem(this._resourceBundle.MOVE_RIGHT, this.RIGHT, "homepageSprite homepageSprite-widget-moveRight"));
            }
            
            var cursor = currentPos.clone();
            cursor.y++; // next row				
            if (this._layoutModel.isWidgetAtPos(cursor)) {
                menuItems.push(this._buildMenuItem(this._resourceBundle.MOVE_DOWN, this.DOWN, "homepageSprite homepageSprite-widget-moveDown"));
            }
            
            // previous row
            cursor.y -= 2;
            
            if (this._layoutModel.isWidgetAtPos(cursor)) {
                menuItems.push(this._buildMenuItem(this._resourceBundle.MOVE_UP, this.UP, "homepageSprite homepageSprite-widget-MoveUp"));
            }
        }
        
        return menuItems;
    },
    
    openMenu: function(evt){
        // summary: callback on the action menu icon
        
        if (this._mustUpdateMenu == true) {
            this._setupMenus();
            this._mustUpdateMenu = false;
        }
        
        var menu = dijit.byId(this._mainMenu.id);
        evt = dojo.fixEvent(evt);
        dijit.popup.open({
            popup: menu,
            around: evt.target,
            orient: dojo._isBodyLtr() ? {'BL': 'TL', 'BR': 'TR', 'TL': 'BL', 'TR': 'BR'} : {'BR':'TR', 'BL':'TL', 'TR':'BR', 'TL':'BL'},
            onExecute: function(){
            },
            onCancel: dojo.hitch(this, function(){
                try {
                    dijit.popup.close(menu);
                    if(this.actionMenuOpener){
                    	this.actionMenuOpener.focus();
                    }
                } 
                catch (e) {
                }
            })
        });
        
        menu.focus();
        dojo.connect(menu, "_onBlur", dojo.hitch(this, function(){
            dijit.popup.close(menu);
            if(this.actionMenuOpener){
               	this.actionMenuOpener.focus();
            }
        }));
        
        dojo.stopEvent(evt);
    },
    
    _onMainMenuItemClicked: function(/* dijit.MenuItem*/obj){
        switch (obj.action) {
            case "edit":
                this.edit();
                break;
            case "refresh":
                this.refresh()
                break;
            case "close":
                this.close();
                break;
            case "help":
                this.help();
                break;
            case this.UP:
                moveInCol(this, true);
                break;
            case this.DOWN:
                moveInCol(this, false);
                break;
            case this.RIGHT:
                moveRight(this);
                break;
            case this.LEFT:
                moveLeft(this);
                break;
            default:
            	hp_console_debug("unknown action " + obj.action);
        }
        
        dijit.popup.close(this._mainMenu);
        if(this.actionMenuOpener){
        	this.actionMenuOpener.focus();
        }
    },
    
    refresh: function(){
        if ((this._bodyWidget != null) && (this._bodyWidget.refresh != null)) {
            this._bodyWidget.refresh();
        }
    },
    
    edit: function(){
        if ((this._bodyWidget != null) && (this._bodyWidget.edit != null)) {
            this._bodyWidget.edit();
        }
    },
    
    help: function(){
        if ((this._bodyWidget != null) && (this._bodyWidget.edit != null)) {
            this._bodyWidget.help();
        }
    },
    
    close: function(){
        dojo.publish(lconn.homepage.events.widget.DESTROY, [this, "destroy"]);
        
        if (this._bodyWidget) 
            this._bodyWidget.destroy();
        
        lconn.homepage.core.common.DashboardPanel.superclass.destroy.apply(this);
        
        dojo.publish(lconn.homepage.events.palette.RECHECK);
    }
});
