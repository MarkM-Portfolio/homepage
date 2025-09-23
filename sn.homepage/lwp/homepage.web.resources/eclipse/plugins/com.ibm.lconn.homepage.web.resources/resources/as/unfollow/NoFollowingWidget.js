/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.as.unfollow.NoFollowingWidget");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

dojo.declare("lconn.homepage.as.unfollow.NoFollowingWidget", 
[dijit._Widget, dijit._Templated], 
{
    strings: null,
    
    templatePath: dojo.moduleUrl("lconn.homepage", "as/unfollow/templates/noFollowingWidget.html"),
    
    /** Called when the information div is clicked - expands/collapses information */
    infoClicked : function(){
    	as_console_debug("lconn.homepage.as.unfollow.NoFollowingWidget - infoClicked");
    	// Toggle twisty classes
    	dojo.toggleClass(this.infoTwisty, "lotusTwistyClosed");
    	dojo.toggleClass(this.infoTwisty, "lotusTwistyOpen");

	   	// Edit the title text to reflect the arrow icon
    	if(dojo.hasClass(this.infoTwisty, "lotusTwistyOpen")){
    		this.infoTwisty.title = this.strings.unfollowCollapseText;
    		this.infoTwisty.setAttribute("aria-expanded", "true");
    		this.infoSection.setAttribute("aria-hidden", "false");
    		
    		// Refresh the innerHTML so that JAWS reads out the text
    		var currentHTML = this.infoSection.innerHTML;
    		this.infoSection.innerHTML = currentHTML;
    	}else{
    		this.infoTwisty.title = this.strings.unfollowExpandText;
    		this.infoTwisty.setAttribute("aria-expanded", "false");
    		this.infoSection.setAttribute("aria-hidden", "true");
    	}
    	
    	dojo.toggleClass(this.infoSection, "lotusHidden");
    }
});
