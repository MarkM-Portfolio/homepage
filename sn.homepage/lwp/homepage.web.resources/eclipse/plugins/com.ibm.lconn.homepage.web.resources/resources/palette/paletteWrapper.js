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

dojo.provide("lconn.homepage.palette.paletteWrapper");

dojo.require("lconn.core.paletteOneUI.Palette");
dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.homepage.palette.ChangeLayoutAddContentPane");
dojo.require("dojox.uuid.generateTimeBasedUuid");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");
dojo.require("dojo.fx");
dojo.require("dojox.fx.style");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare(// widget name and class
"lconn.homepage.palette.paletteWrapper", // superclass
 [dijit._Widget, dijit._Templated, dijit._Container], // properties and methods
{
    // summary: Wrap the new widget palette in a container
    //		Mainly used to encapulate palette event listeners to avoid to pollute the global page scope

    // paletteNode: DOMNode
    //		Template Dojo attach point
    paletteNode: null,

    // _topics: Array [handler]
    //		Used internally to keep track of dojo.subscribe handlers
    _topics: [],

    // SHOW_PALETTE_EVENT: Const String
    //		Name of the event listened to when the palette should show up
    TOGGLE_PALETTE_EVENT: "/lconn/dboard/palette/togglePaletteEvent",

    // _palette: lconn.core.palette.Palette
    //		Used internally to keep track of the palette reference
    _palette: null,
    _changeLayoutView: null,
    _layoutMenu: null,

    // _isShowing: Boolean
    //		Used internally to keep track of the palette status
    _isInit: false,

    // jsonUrl: String
    //		url passed to the palette
    jsonUrl: "",

    // CHANGE_LAYOUT_ID: String
    //		id of the change layout panel
    CHANGE_LAYOUT_ID: "changeLayoutId",
    TWO_COL_ID: "2Col",
    THREE_COL_ID: "3Col",

    _isChangeLayoutInit: false,

    imageContextRoot: "",

    _resourceBundle: null,

    _isPaletteOpen: false,

    hpPhotoImgLeft: "",

    hpPhotoImgTop: "",

  // array used to keep track of the widgets button that should still be displayed right after
  //	adding click the button only (SPR THSE7SFRZK)
  _justAddedWidgets: null,

    templatePath: dojo.moduleUrl("lconn.homepage", "palette/templates/PaletteWrapper.html"),

    postCreate: function(){
        this.inherited("postCreate", arguments);

    this._justAddedWidgets = [];

        // register to event published by the palette
        this._topics.push(dojo.subscribe(this.TOGGLE_PALETTE_EVENT, dojo.hitch(this, "togglePalette")));
        this._topics.push(dojo.subscribe(lconn.core.paletteOneUI.Palette.prototype.CLOSE_PALETTE_EVENT, dojo.hitch(this, "hidePalette")));
  },

    postMixInProperties: function(){
        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
    },

    togglePalette: function()
    {
    	var isHikari = lconn.core.theme.getCurrentThemeId() == 'hikari' ? true : false;
    	
        if(this._isPaletteOpen)
        {
        	if(isHikari && dijit.byId('palette_modal_id').open)
        	{
        		this.hidePalette();
        	}
        	else
        	{
        		this.showPalette();
        	}
        }
        else
        {
            this.showPalette();
        }
    },

    showAuxPaletteLinks: function(){
      this.toggleAuxPaletteLinks(true);
    },

    hideAuxPaletteLinks: function(){
      this.toggleAuxPaletteLinks(false);
    },

    toggleAuxPaletteLinks: function(/*Boolean*/ show) {
      //toggle the before/after palette links from tabindex
      var beforePaletteLink = dojo.byId("beforePaletteLink");
      var afterPaletteLink = dojo.byId("afterPaletteLink");

      if(beforePaletteLink && afterPaletteLink){
        dojo.attr(beforePaletteLink, "tabindex", show?"0":"-1");
        dojo.attr(afterPaletteLink, "tabindex", show?"0":"-1");
      }
    },

    showPalette: function(){
    	var isHikari 			= lconn.core.theme.getCurrentThemeId() == 'hikari' ? true : false;
    	var modal_id 			= 'palette_modal_id';
    	var palette_wrapper_id 	= 'paletteWrapper';
        this.hideAuxPaletteLinks();

        // summary: Show the palette. On first invoke, it creates the palette widget object
        if (!this._isInit && this.jsonUrl != "")
        {
            this._createPaletteWidget();
            this.paletteNode.innerHTML = "";
            this.paletteNode.appendChild(this._palette.domNode);
            this._palette.populatePalette(true);
            if(isHikari)
            {
        		var app_palette_modal = new dijit.Dialog({
    				style	: 'display:block;'	,
    				id		: modal_id			,
    				content	: dojo.byId(palette_wrapper_id)
        		});

        		app_palette_modal.show();
        		this.setTabFocus("widgetAddId");
            }

            this._isInit = true;
            if (dojo.isIE) {
              // Fix float element's width can't render correctly in IE by dojo.fx
              this.domNode.style.width = "100%";
                if (dojo.isIE == 6) {
                    this.domNode.style.zoom = 1;
                }
            }
         }
        else
        {
        	if(isHikari)
        	{
        		dijit.byId(modal_id).show();
        		this.setTabFocus("widgetAddId");
        	}
        	else
        	{
                // just refresh the palette display
                dojo.publish(lconn.core.paletteOneUI.Palette.prototype.RECHECK_CAN_ADD_WIDGET_HANDLER_EVENT);
        	}
         }

        // needed for wipeOut
        this.domNode.style.display = "none";
        dojo.removeClass(this.domNode, "lotusHidden");

        this._isPaletteOpen = true;
        dojo.global.isPaletteOpen = true; //set global for setSticky in ASSideNav.js

        var anims = [];
        var userphotoNode = dojo.byId("hpUserPhotoImg") != null ? dojo.byId("hpUserPhotoImg") : dojo.byId("hpplaceholder_top");

        //for the hikari theme there is no need to move the user image
        if (lconn.core.theme.getCurrentThemeId() != "hikari") {
          anims.push(
            dojox.fx.addClass(userphotoNode, 'moveUserImage')
          )

          dojo.addClass(dojo.byId("lotusFrame"), "paletteExpanded");
        }

        if(isHikari)
        {
            anims.push(dojo.fx.wipeIn({
                node: this.domNode,
                duration: 300,
                onEnd: function()
                {
                	dojo.hitch(this, "makeAddContentShelfVisible");
                	dijit.byId(modal_id).resize();
                }.bind(this)
            }));
        }
        else
        {
            anims.push(dojo.fx.wipeIn({
                node: this.domNode,
                duration: 300,
                onEnd: dojo.hitch(this, "makeAddContentShelfVisible")
            }));
        }

        dojo.fx.combine(anims).play();
    },

	// Set focus on tab
	setTabFocus: function(tabid) {
		var curTab = dojo.byId(tabid);
		if (curTab != null) {

			setTimeout(function() {
				dojo.attr(curTab, {"aria-selected":"true","tabindex":"0"});
				curTab.focus();
			}, 1000);
		}
    },

    hidePalette: function(){
        // summary: Hide the palette. Does not destroy the palette widget object
        this._justAddedWidgets = [];
        this.makeAddContentShelfInvisible();

        var anims = [];
        anims.push(dojo.fx.wipeOut({
            node: this.domNode,
            duration: 300
        }));

        dojo.connect(anims, "onEnd", function(){
            dojo.publish(lconn.core.paletteOneUI.Palette.prototype.RESET_COUNTER_EVENT);
            dojo.byId("paletteLink").focus();
        });

        //for the hikari theme there is no need to move the user image
        if (lconn.core.theme.getCurrentThemeId() != "hikari") {
            var userPhotoNode = dojo.byId("hpUserPhotoImg") != null ? dojo.byId("hpUserPhotoImg") : dojo.byId("hpplaceholder_top");

            anims.push(dojo.fx.slideTo({
                node: userPhotoNode,
                duration: 300,
                top: "0",
                left: "0",
                units: "px"
            }));

          dojo.removeClass(dojo.byId("lotusFrame"), "paletteExpanded");
        }

        dojo.fx.combine(anims).play();

        this._isPaletteOpen = false;
        dojo.global.isPaletteOpen = false; //set global for setSticky in ASSideNav.js

        this.showAuxPaletteLinks();
        dojo.byId("paletteLink").focus();
    },

    /**
     * Make the add content's shelf node visible. Only needed for IE
     * because otherwise, the shelf doesn't work with the wipeIn/Out.
     */
    makeAddContentShelfVisible: function(){
      // Only use for IE
      if(dojo.isIE){
        // Make the shelf visible
        dojo.style(this._palette._contentArea.shelfNode, {
          visibility: "visible"
        });
      }
    },

    /**
     * Make the add content's shelf node invisible. Only needed for IE
     * because otherwise, the shelf doesn't work with the wipeIn/Out.
     */
    makeAddContentShelfInvisible: function(){
      // Only use for IE
      if(dojo.isIE){
        // Make the shelf invisible
        dojo.style(this._palette._contentArea.shelfNode, {
          visibility: "hidden"
        });
      }
    },

    _buttonStatusHandler: function(item){
        // summary: handler passed to the palette to update the state of the buttons

        var enabled = false;

        if (item.widgetType == "layout") {
            // layout mode
            enabled = this._checkCanSwitchLayout(item);
        }
        else {
            // add content (widgets) mode
            enabled = this._checkCanAddWidget(item);
        }

        return enabled;
    },

  _buttonVisibilityHandler: function(item){
    // rafinement for THSE7SFRZK. Not the most efficient implementation (call checkCanAddWidget twice)
    //	but not noticiable for end-users

    var isVisible = false;

    var canAdd = this._checkCanAddWidget(item);

    if (!canAdd){
      // widget already open on the page and cannot be added twice
      // need to determine whether the button should still be displayed (right after the user clicked it)

      isVisible = dojo.some(this._justAddedWidgets, function(widgetId){
        return widgetId == item.widgetId[0];
      }, this);

    } else {
      isVisible = true;
    }

    return isVisible;
  },

    _checkCanSwitchLayout: function(item){
        var numCol = layoutModel.getNumberOfColumns();
        var result = false;

        if ((item.id == this.TWO_COL_ID) && (numCol != 2)) {
            result = true;
        }
        else
            if ((item.id == this.THREE_COL_ID) && (numCol != 3)) {
                result = true;
            }
            else {
                result = false;
            }

        return result;
    },

    _checkCanAddWidget: function(item){
        var result = item.multipleInstance[0];

        if (result == false) {
            var widgets = dojo.query("div.homepage-widget[widgetId]");
            var referenceId = item.widgetId[0];

            var result = !dojo.some(widgets, function(widget){
                var instanceId = dojo.attr(widget, "widgetid");
                var widgetId = factory.getWidgetId(instanceId);
                return (widgetId == referenceId);
            });
        }

        return result;
    },

    _changeLayout: function(item){
        if (item.id == this.TWO_COL_ID) {
          myPageSupport.removeThirdColumn();
        }
        else
            if (item.id == this.THREE_COL_ID) {
              myPageSupport.addThirdColumn();
            }

        dojo.publish(lconn.core.paletteOneUI.Palette.prototype.RECHECK_CAN_ADD_WIDGET_HANDLER_EVENT);
    },

    _paletteButtonListener: function(item){
        if (item.widgetType == "layout") {
            this._changeLayout(item);
        }
        else {
            this._addWidgetToPageListener(item);
        }
    },

    _addWidgetToPageListener: function(item){
        // sumarry: Listener function invoked when user clicks the "add to widget" button

        // unique id for an instance of a widget
        // using the timebased version here as it is more "random" than "generateRandomUuid".
        // however, remember this is client-side code and based on the client clock

    this._justAddedWidgets.push(item.widgetId);

        var randomWidgetInstanceId = dojox.uuid.generateTimeBasedUuid();

        // replace - with x as iWidget framework uses the id to create variable on the global scope based on the id ('-' is evaluated)
        randomWidgetInstanceId = randomWidgetInstanceId.replace(/-/g, 'x');

        var container;
        var isUpdatePage = false;

        // using global variable here. Would benefit from changing that
        var pageType = layoutModel.getPageType();
        var widget = factory.createWidgetInstanceById(item.widgetId.toString(), randomWidgetInstanceId, pageType != layoutModel.WIDGET_TAB);

        insertWidget(widget, false);
        dojo.publish(lconn.homepage.events.dnd.DROP, [null, null, widget.root]);

        dojo.publish(lconn.core.paletteOneUI.Palette.prototype.RECHECK_CAN_ADD_WIDGET_HANDLER_EVENT);
        this._palette.incCounter();

        if (typeof this._palette.enableItemCounter != "undefined") {
            this._palette.enableItemCounter();
        }
    },

    _createPaletteWidget: function(){
        if (this._palette == null) {
            //dojo.require("lconn.homepage.palette.PaletteLayer");
            var that = this;
            this._palette = new lconn.core.paletteOneUI.Palette({
                imageContextRoot: that.imageContextRoot
            });
            this._palette.setJsonSourceUrl(encodeURI(this.jsonUrl));
            this._palette.registerCanAddWidgetFct(dojo.hitch(this, "_buttonStatusHandler"));
      this._palette.registerIsVisibleButton(dojo.hitch(this, "_buttonVisibilityHandler"));
      // Make the add content shelf invisible
      this.makeAddContentShelfInvisible();

            if (layoutModel.getPageType() == layoutModel.WIDGET_TAB) {
                //this._createChangeLayoutDropDown();
                this._buildChangeLayoutView();
                dojo.connect(this._palette, "_switchToTab", this, "onSwitch");
            }

            dojo.subscribe(lconn.core.paletteOneUI.Palette.prototype.ADD_WIDGET_EVENT, dojo.hitch(this, "_paletteButtonListener"));
        }
    },

    onSwitch: function(id, evt){
        // summary: invoked when the user selects a tab in the palette

        if (id == this.CHANGE_LAYOUT_ID) {
            // change layout view
            if (this._isChangeLayoutInit == false) {
                this.contentAreaChangeLayout.populatePalette(false);
            }

            // just check for disableItemCounter because of timing issues when delivering sn.infra
            if (typeof this._palette.disableItemCounter != null) {
                this._palette.disableItemCounter();
            }
        }
        else {
            // add Content view
            dojo.publish(lconn.core.paletteOneUI.Palette.prototype.RECHECK_CAN_ADD_WIDGET_HANDLER_EVENT);
            dojo.publish(lconn.core.paletteOneUI.Palette.prototype.RESET_COUNTER_EVENT);
        }
    },

    _buildChangeLayoutView: function(){
        if (this._palette != null) {
            this.contentAreaChangeLayout = new lconn.homepage.palette.ChangeLayoutAddContentPane({
                imageContextRoot: this.imageContextRoot
            });
            this._palette.addTabPane("changeLayoutId", this._resourceBundle.CHANGE_LAYOUT, this.contentAreaChangeLayout, false);
            this.contentAreaChangeLayout.setJsonData(this._getChangeLayoutJson());
            this.contentAreaChangeLayout.registerCanAddWidgetFct(dojo.hitch(this, "_buttonStatusHandler"));
            //contentArea.populatePalette(false);
        }
    },

    _getChangeLayoutJson: function(){
        // build the change layout view
        // reuse some of the stuffs implemented for widget view, hence the mention to "widgets" here
        // we need to re-design the json string to use names more generic
        return {
            categories: [{
                name: "",
                id: "all",
                widgets: [{
                    id: this.TWO_COL_ID,
                    name: this._resourceBundle.TWO_COL,
                    widgetId: "2col", /* not used but mandatory */
                    iconUrl: dojo.moduleUrl("lconn.homepage.palette", "images/2column.png"),
                    desc: this._resourceBundle.TWO_COL_DESC,
                    multipleInstance: false, /* not used but mandatory */
                    widgetType: "layout"
                }, {
                    id: this.THREE_COL_ID,
                    name: this._resourceBundle.THREE_COL,
                    widgetId: "3col",
                    iconUrl: dojo.moduleUrl("lconn.homepage.palette", "images/3column.png"),
                    desc: this._resourceBundle.THREE_COL_DESC,
                    multipleInstance: false,
                    widgetType: "layout"
                }]
            }]
        };
    },

    destroy: function(){
        // sumarry: overriden. Clean up handlers
        dojo.forEach(this._topics, function(topic){
            dojo.unsubscribe(topic);
        });
    }
});