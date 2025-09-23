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

dojo.provide("lconn.homepage.core.layout.ResizeEventManager");

dojo.require("lconn.homepage.core.constants.DojoEvents");
/**
 * Custom manager publishing lconn.homepage.core.layout.ResizeEventManager when layout nodes have changed size
 * Different from window.resize event:
 * - Fix bugs in IE6 where window.onresize is fired too often when body size is changed
 * - Do no publish events when window is resized
 * - Only publish events when resize vertically
 * 
 * 
 * Note: this routine uses the div "lotusTitleBar" as reference node!
 */

dojo.addOnLoad(function(){	
	var refNode = dojo.byId("lotusFrame");
	var cachedBox = dojo.marginBox(refNode);
	
	if (refNode == null){
		throw new Error("lconn.homepage.core.layout.ResizeEventManager has a strict dependency on div.lotusTitleBar which cannot be found on the page");
	}
	
	dojo.connect(window, "onresize", function(){
		var currentBox = dojo.marginBox(refNode);
						
		if (cachedBox.w != currentBox.w){			
			var isExpending = cachedBox.w < currentBox.w;
			dojo.publish(lconn.homepage.events.layout.RESIZE_WIDTH, [isExpending]);
			cachedBox = currentBox;
		} else {
		}
	});	
});
