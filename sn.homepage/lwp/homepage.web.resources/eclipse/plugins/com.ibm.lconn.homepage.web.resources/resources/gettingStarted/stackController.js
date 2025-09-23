/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.gettingStarted.stackController");

dojo.require("dijit.layout.ContentPane");

dojo.require("lconn.core.aria.TabPanel");

dojo.declare(// widget name and class
"lconn.homepage.gettingStarted.stackController", // superclass
 [dijit._Widget, dijit._Container, dijit._Templated], // properties and methods
{
    templateString: "<span wairole='tablist' role='tablist' aria-labelledby='${labelby}'></span>",
    
    stackContainerId: "",
    _stackContainerDijit: null,
    
    labelby: "",
    
    // The type of page that called this stackController, e.g. share, explore
    type: "",
    
    demoUrls: {
    	homepage: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/homepage45/homepage_demo.html",
    	activities: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/activities45/activities_demo.html",
    	blogs: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/blogs40/blogs_demo.html",
    	bookmarks: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/bookmarks40/bookmarks_demo.html",
    	communities: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/communities40/communities_demo.html",
    	files: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/files40/files_demo.html",
    	forums: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/forums45/forums_demo.html",
    	profiles: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/profiles40/profiles_demo.html",
    	wikis: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/wikis40/wikis_demo.html",
    	libraries: "http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/libraries45/libraries_demo.html"
    },
    
    // dijit for the rendering of the "button"/ action link / etc that the user click to show a container
    _dijitSelectorClass: "lconn.homepage.gettingStarted.actionLink",
    
    //_containerNode: null,
    _childRefContainerNodes: {},
    
    postMixInProperties: function() {
    },
    
    postCreate: function(){
        // Listen to notifications from StackContainer
        this.subscribe(this.stackContainerId + "-startup", "onStartup");
        this.subscribe(this.containerId + "-addChild", "onAddChild");
    },
    
    onStartup: function(/*Object*/info){
        // summary:
        //		Called after StackContainer has finished initializing
        // tags:
        //		private       
        
        this._stackContainerDijit = dijit.byId(this.stackContainerId);
        dojo.forEach(info.children, this.onAddChild, this);

        if (this.type == "explore") {
        	var firstLink = dojo.query(".gettingStartedActionLink", this.domNode)[0];
        	dojo.removeClass(firstLink, "gettingStartedActionLink");
        	dojo.addClass(firstLink, "gettingStartedActionLinkSelected");
        }
        
        // Run this through the TabPanel aria helper
        new lconn.core.aria.TabPanel(this.id);
    },
    
    onAddChild: function(child){
        this._childRefContainerNodes[child.id] = child;
        
        var dijitObj = this._buildDijitSelector(child.id);
        
        if (dijitObj != null) {
            this.domNode.appendChild(dijitObj.domNode);
        }
    },
    
    getChildren: function(){
		return this.containerNode ? dojo.query("> [widgetId]", this.containerNode).map(dijit.byNode) : []; // Widget[]
	},	
    
    _buildDijitSelector: function(associatedContainerId){
        var dijitClass = dojo.getObject(this._dijitSelectorClass);
        
        var refNode = dojo.doc.createElement("span");
        var label = this._childRefContainerNodes[associatedContainerId].title;
        
        var node = dojo.byId(associatedContainerId);
        var componentName = node.attributes["component"];
		
		if (componentName != null){
			componentName = componentName.value;
		}
		
        var dijitObj = null;
        
        if ((typeof label != "undefined") && (label != "")) {	
        	var ariaDescription = associatedContainerId + "_description";
        
        	dijitObj = new dijitClass({
                label: label,
                componentName: componentName,
                ariaDescription: ariaDescription
            });
            
            dojo.connect(dijitObj, "onClick", dojo.hitch(this, "onSelectorClick", dijitObj, associatedContainerId));
        }
        
        return dijitObj;
    },
    
    onSelectorClick: function(dijitObj, associatedContainerId){
    	hp_console_debug("onSelectorClick");
    	hp_console_debug(dijitObj);
    	hp_console_debug(associatedContainerId);
        
        // Check if this is the share stackController
        var shareComponentContainer = dojo.query(".shareComponentContainer")[0];
        if (shareComponentContainer && dojo.style(shareComponentContainer, "verticalAlign") == "middle") {
            dojo.style(shareComponentContainer, "verticalAlign", "top");
        }
        
        // If this is the explore page, we need to update the demo images
        // and links to videos
        if (this.type == "explore") {
            // Expecting something like "blogs-pane" here
            var srcApp = associatedContainerId.substring(0, associatedContainerId.length - 5);
            var app = srcApp;
            hp_console_debug("app: %o", app);
            
            // TODO: hardcoded fix as there is too much relying on "dogear" string to change atm
            if (app == "dogear") {
                app = "bookmarks";
            }
        }
        
        dojo.query(".gettingStartedActionLinkSelected", this.domNode).forEach(function(node, index, nodelist) {
        	dojo.removeClass(node, "gettingStartedActionLinkSelected");
        	dojo.addClass(node, "gettingStartedActionLink");
        	dojo.attr(dojo.query("a", node)[0], "aria-selected", "false");
        });
        
        dojo.addClass(dijitObj.domNode, "gettingStartedActionLinkSelected");
        dojo.attr(dojo.query("a", dijitObj.domNode)[0], "aria-selected", "true");
        
        this._stackContainerDijit.selectChild(this._childRefContainerNodes[associatedContainerId]);
    }
});

dojo.provide("lconn.homepage.gettingStarted.actionLink");

dojo.declare("lconn.homepage.gettingStarted.actionLink", [dijit._Widget, dijit._Templated], {
    templateString: "<span class='gettingStartedActionLink'>" +
    		"<a id='${id}_link' href='#' role='tab' aria-selected='false' dojoAttachPoint='anchorLink' dojoAttachEvent='onclick: onClick'>" +
    		"${label}</a><p></p></span>",
    
    label: "",
    iconSprite: "",
    componentName: "",
	altText: "",
	_blankGif: "",
	hideSpanClass: "",
	ariaDescription: "",
	
	postMixInProperties: function() {	
		this._blankGif = djConfig.blankGif;
		this._setSprite();
		
		if(!this.componentName){
			this.hideSpanClass = "lotusHidden";
		}
	},
    
    postCreate: function(){
       
    },
    
    _setSprite: function(){
        if (typeof (this.componentName) != "undefined" && this.componentName != "") {
			// no capitalize in dojo?
            var capitilizedName = this.componentName.charAt(0).toUpperCase() + this.componentName.substring(1, this.componentName.length);
            
            this.iconSprite = "lconnSprite-icon" + capitilizedName + "16";            
        }
    },
    
    onClick: function(){
    	hp_console_debug("onClick");
    }
});
