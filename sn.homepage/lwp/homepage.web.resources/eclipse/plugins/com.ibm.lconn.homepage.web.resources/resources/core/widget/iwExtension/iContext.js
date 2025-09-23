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

dojo.provide("lconn.homepage.core.widget.iwExtension.iContext");

dojo.require("com.ibm.mm.enabler.iw");

dojo.declare("lconn.homepage.core.widget.iwExtension.UserProfile", com.ibm.mm.enabler.iw.UserProfile, {
    // overriden
    getItemValue: function(/* string */name){
        var value = null;
        
        if (name != null) {
            value = this.user[name];
            if (typeof value == "undefined") 
                value = null;
        }
        else {
            value = null;
        }
        
        return value;
    }
});

// we clone the global string here to avoid setting references that could
	// be modified inavertly by a 3rd party widget
    // 3rd party widgets could still modify this itemSet due to the unsecure nature
	// of javascript but they would have to be implemented specifically to do so (ie: not inavertly)
com.ibm.mm.enabler.iw.iContextImpl._cachedUserProfileStr = {
        "email": dojo.clone(user),
        "userId": dojo.clone(userid),
        "userid": dojo.clone(userid),
        "displayName": dojo.clone(lconn.homepage.userName),
        "cn": dojo.clone(lconn.homepage.userName)
    };

com.ibm.mm.enabler.iw.iContextImpl.prototype.getUserProfile = function(){    
    var itemSet = new lconn.homepage.core.widget.iwExtension.UserProfile(null, com.ibm.mm.enabler.iw.iContextImpl._cachedUserProfileStr);   
    return itemSet;
}
