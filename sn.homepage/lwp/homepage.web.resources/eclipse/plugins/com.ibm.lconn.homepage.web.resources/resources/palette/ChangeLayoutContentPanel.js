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

dojo.provide("lconn.homepage.palette.ChangeLayoutContentPanel");

dojo.require("lconn.core.paletteOneUI.PaletteContentPanel");
dojo.require("lconn.homepage.palette.ChangeLayoutButton");


// We just need to change one CSS class in the template (lotusLargeWidgets) to make the button look larger

dojo.declare(// widget name and class
"lconn.homepage.palette.ChangeLayoutContentPanel", // superclass
 [lconn.core.paletteOneUI.PaletteContentPanel], // properties and methods
{    
	templateString: null,
    templatePath: dojo.moduleUrl("lconn.homepage", "palette/templates/ChangeLayoutContentPanel.html"),
	
	_togglePagingButtons: function(){
		// Overriden to always hide the pager. It does not make sense in the content of switch buttons
		dojo.addClass(this.pagerNode, "lotusHidden");		
	},
	
	_addPaletteButton: function(widgetItem, enabled){
		// summary: add a button representing a widget to the content area
		
		var imageRoot = this.imageContextRoot;
        var button = new lconn.homepage.palette.ChangeLayoutButton({
            widgetItem: widgetItem,
            imageContextRoot: imageRoot,
			initialStatus: enabled
        });
        this.addChild(button);
	}
});
