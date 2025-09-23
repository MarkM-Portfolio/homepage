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

dojo.provide("lconn.homepage.palette.ChangeLayoutAddContentPane");
dojo.require("lconn.core.paletteOneUI.AddContentPane");
dojo.require("lconn.homepage.palette.ChangeLayoutContentPanel");

dojo.declare(
	// widget name and class
	"lconn.homepage.palette.ChangeLayoutAddContentPane",
	
	// superclass
	[lconn.core.paletteOneUI.AddContentPane],

	// properties and methods
	{		
		postCreate: function(){
			// Overriden to use ChangeLayoutContentPanel
			
			//this.inherited("postCreate", arguments);
			//lconn.core.paletteOneUI.AddContentPane.prototype.superclass.postCreate.apply(this, arguments);
			
			this._createLoadingNode();
			this._setLoading();
			
			// init and display info panel
			var imageRoot = this.imageContextRoot;
			
			// Change is here			
			this._contentArea = new lconn.homepage.palette.ChangeLayoutContentPanel({imageContextRoot: imageRoot});
			
			this.contentAreaNode.appendChild(this._contentArea.domNode);
			
			// utility to create datastore for the tree
			this._storeBuilder = new lconn.core.paletteOneUI.PaletteDataStoreBuilder();		
		
			this._registerDefaultCanAddWidgetFct();
			this._registerDefaultIsVisibleWidgetFct();
		}
	}	
);
