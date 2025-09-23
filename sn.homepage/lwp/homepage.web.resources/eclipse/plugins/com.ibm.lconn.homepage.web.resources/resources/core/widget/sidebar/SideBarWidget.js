/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2016                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.core.widget.sidebar.SideBarWidget");

dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.homepage.core.widget.WidgetGadgetPanel");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");
dojo.require("dijit.Menu");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare(// widget name and class
"lconn.homepage.core.widget.sidebar.SideBarWidget", // superclass
 [dijit._Widget, dijit._Templated, dijit._Container, lconn.homepage.core.widget.WidgetGadgetPanel], // properties and methods
{
    // summary: Generic container with title for the widgets in the side bar of the update page
    // description: Designed in such a way the widget content can be anything from static HTML to an iWidget
    
    // title: String
    //	Displayed in the title header bar
    title: "",
    
    // titleBar: DOMNode
    //	Dojo attach point
    titleBar: null,
    
    // _resourceBundle: map
    //		i18n bundle
    _resourceBundle: null,
    _resourceBundlePanel: null,
    
    _bodyWidget: null,
    
    // array keep references to items (options, help, refresh)
    _optionalItems: null,
    
    // _menu: dijit.Menu
    //	Pre-built menu object shown when user clicks action icon in title bar
    _menu: null,
    
    // _topic: Array
    //		Keep track of the subscribtions
    _topics: null,
    
    _mustUpdateMenu: true,
    
    _isIWidgetLoaded: null,
    _makeGadgetSubscriptionsSubscription: null,
    
    id: null,
    thisWidgetID: null,
	_errorMsg: "",
    
    templatePath: dojo.moduleUrl("lconn.homepage", "core/widget/sidebar/templates/sideBarWidget.html"),
    
    postMixInProperties: function(){
        this.inherited("postMixInProperties", arguments);
        
        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
        
        this._topics = [];
        this._topics.push(dojo.subscribe(lconn.homepage.events.init.COMPLETED, this, "_updateMenu"));
        this._topics.push(dojo.subscribe(lconn.homepage.events.layout.UPDATED, this, "_updateMenu"));        
        this._topics.push(dojo.subscribe(lconn.homepage.events.widget.LOADED, this, "_updateMenu"));
		this._errorMsg = this._resourceBundle.ERROR;
		this._errorAlt = this._resourceBundle.ERROR_ALT;
		
		this._canCustomize = layoutModel ? layoutModel.canCustomize() : true;
    },
    
    postCreate: function(){
        this.domNode.id = this.thisWidgetID;
        this._isIWidgetLoaded = false;
        dojo.removeAttr(this.domNode, "title");
        this._makeGadgetSubscriptionsSubscription = dojo.subscribe(lconn.homepage.events.widget.LOADED, this, "makeGadgetSubscriptions");
    },
    
    makeGadgetSubscriptions : function() {
    	var gadgetNode = dojo.query("iframe", this.containerNode)[0];
    	if(gadgetNode) {
    		var gadgetDiv = gadgetNode.parentElement;
			var gadgetDivId = dojo.attr(gadgetDiv, "id");
			
			var topic = com.ibm.lconn.gadget.container.Topics.getSiteTopic(gadgetDivId, com.ibm.lconn.gadget.container.Topics.GadgetWindow.SITE_TOPIC_SET_TITLE);
			this.subscribe(topic, "onWidgetTitleUpdated");
			
			// don't subscribe to width or height events, never resize the sidebar.
			
	    	dojo.unsubscribe(this._makeGadgetSubscriptionsSubscription); // once we've done our job, don't get called again.
    	}
    },
    
    onWidgetTitleUpdated: function(title){
        this.titleBar.innerHTML=title;
    },
    
    onToggleContent: function(evt){
        // summary: Hide/display the body of the widget
        if (dojo.hasClass(this.containerNode, "lotusHidden")) {
            // hide container + change twisty arrow image (CSS Sprite)
            dojo.removeClass(this.containerNode, "lotusHidden");
            dojo.removeClass(evt.currentTarget, "lotusTwistyClosed");
            dojo.addClass(evt.currentTarget, "lotusTwistyOpen");
        }
        else {
            dojo.addClass(this.containerNode, "lotusHidden");
            dojo.removeClass(evt.currentTarget, "lotusTwistyOpen");
            dojo.addClass(evt.currentTarget, "lotusTwistyClosed");
        }
        
        dojo.stopEvent(evt);
    },
    
    openMenu: function(evt){
        // summary: callback on the action menu icon
        
        if (this._mustUpdateMenu == true) {
            this._setupMenus();
            this._mustUpdateMenu = false;
        }
        
        var menu = dijit.byId(this._menu.id);
        evt = dojo.fixEvent(evt);
        dijit.popup.open({
            popup: menu,
            around: evt.target,
            orient: {
                'BL': 'TL',
                'BR': 'TR',
                'TL': 'BL',
                'TR': 'BR'
            },
            onExecute: function(){
            },
            onCancel: dojo.hitch(this, function(){
                dijit.popup.close(menu);
                this.reFocus();
            })
        });
        
        menu.focus();
        dojo.connect(menu, "_onBlur", dojo.hitch(this, function(){
            dijit.popup.close(menu);
            this.reFocus();
        }));
        dojo.stopEvent(evt);
    },
    
    _buildMenu: function(){
        // summary: build and pre-render drop down menu object displayed when the user clicks 
        //			the action icon in the title bar
        
        if (this._menu == null) {
            this._menu = new dijit.Menu();
            
            //this._initMenuItems();
            
            //this._menu.domNode.style.display = "none";
            //dojo.body().appendChild(this._menu.domNode);
            dojo.connect(this._menu, "onItemClick", this, "_onMenuItemClicked");
        }
    },
    
    _setupMenus: function(){
        // summary: init drop down menu items
        
        this._buildMenu();
        var menu = this._menu;
        
        // remove all items first
        var children = menu.getChildren();
        dojo.forEach(children, function(item){
            menu.removeChild(item);
        });
        
        if ( this._canCustomize ) {
	        if (this._canMoveUp()) {
	            menu.addChild(this._buildMenuItem(this._resourceBundle.MOVE_UP, "moveUp", "homepageSprite homepageSprite-widget-moveUp"));
	        }
	        
	        if (this._canMoveDown()) {
	            menu.addChild(this._buildMenuItem(this._resourceBundle.MOVE_DOWN, "moveDown", "homepageSprite homepageSprite-widget-moveDown"));
	        }
        }
        
        var completeMenu = dojo.hitch(this, function() {
        	if ( this._canCustomize ) {
        		menu.addChild(this._buildMenuItem(this._resourceBundle.CLOSE, "close", "homepageSprite homepageSprite-widget-close"));
        	}
        });
        
        // To get the supported actions requires async call to the container
        // It will return a promise, which will later be resolved with value
		if (this._bodyWidget.getSupportedActionsAsync != null) {
			this._bodyWidget.getSupportedActionsAsync().then(
				// callback for when promise is resolved
				// add the supported actions to menu as appropriate
				dojo.hitch(this, function(supportedActions) {
					if ( supportedActions ) {
						if (this._contains(supportedActions, "edit")) {
							menu.addChild(this._buildMenuItem(this._resourceBundle.EDIT, "edit", "homepageSprite homepageSprite-widget-edit"));
						}
						
						if (this._contains(supportedActions, "help")) {
							menu.addChild(this._buildMenuItem(this._resourceBundle.HELP, "help", "homepageSprite homepageSprite-widget-help"));
						}
						
						if (this._contains(supportedActions, "onRefreshNeeded") || this._contains(supportedActions, "refresh")){
							menu.addChild(this._buildMenuItem(this._resourceBundle.REFRESH, "reload", "homepageSprite homepageSprite-widget-refresh"));
						}		                
					}
					completeMenu();
				}),
				// callback for when promise is rejected
				// need to add final item to the menu
				dojo.hitch(this, function() {
					completeMenu();
				})
			);
		}
    },
	
    _canMoveUp: function(){
        var pos = layoutModel.getWidgetPosition(this.id);
        return pos.getRow() > 0;
    },
    
    _canMoveDown: function(){
        var pos = layoutModel.getWidgetPosition(this.id);
        
        // is it the last widget (from top to bottom)?			
        pos.incRow();
        return layoutModel.isWidgetAtPos(pos);
    },
    
    _onMenuItemClicked: function(/* dijit.MenuItem*/obj){
        if (obj.disabled == true) {
            return;
        }
        switch (obj.action) {
            case "edit":
                this.onEdit();
                break;
            case "reload":
                this.onReload()
                break;
            case "help":
                this.onHelp();
                break;
            case "close":
                this.onClose();
                break;
            case "moveUp":
                this.onMoveUpClick();
                break;
            case "moveDown":
                this.onMoveDownClick();
                break;
            default:
            	hp_console_debug("unknown action " + obj.action);
        }
        dijit.popup.close(this._menu);
        this.reFocus();
    },
    
    onEdit: function(){
        if ((this._bodyWidget != null) && (this._bodyWidget.edit != null)) {
            this._bodyWidget.edit(dojo.hitch(this, this.reFocus));
        }
    },
    
    onHelp: function(){
        if ((this._bodyWidget != null) && (this._bodyWidget.edit != null)) {
            this._bodyWidget.help();
        }
    },
    
    onReload: function(){
        if ((this._bodyWidget != null) && (this._bodyWidget.refresh != null)) {
            this._bodyWidget.refresh();
        }
    },
    
    onClose: function(evt){
        dojo.forEach(this._topics, function(topic){
            dojo.unsubscribe(topic);
        });
        
        dojo.publish(lconn.homepage.events.widget.DESTROY, [this, "destroy"]);
        
        if (this._bodyWidget) 
            this._bodyWidget.destroy();
        
        this.destroy();
        
        dojo.publish(lconn.homepage.events.palette.RECHECK);
    },
    
    onMoveUpClick: function(evt){
        moveInCol(this, true);
        this.onReload();
    },
    
    onMoveDownClick: function(evt){
        moveInCol(this, false);
        this.onReload();
    },
    
    
    setBodyWidget: function(/* _Widget */widget){
        // todo: remove existing children, if any
        this._bodyWidget = widget;
        this.addChild(widget);
    },
    
    reFocus: function(){
        if(this.actionMenuOpener){
        	this.actionMenuOpener.focus();
        }
    },
    
    startup: function(){
    
        if (this._bodyWidget != null) {
            this._bodyWidget.startup();
        }
    }
    
});
