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

dojo.provide("lconn.homepage.core.common.DashboardDropDownButton");

dojo.require("dijit.form.Button");
dojo.require("dijit.form.DropDownButton");

dojo.declare("lconn.homepage.core.common.DashboardDropDownButton", dijit.form.DropDownButton, 
{
	// summary: Extend dijit dropdownbutton to include our own icon and for accessibility

	buttonIconUrl: "",
	title: "",
	describedby: "",
	
	postCreate : function(){
		this.inherited(arguments);
		dojo.addClass(this.containerNode, "lotusAccess");
		
		dojo.create("img", {className : "lconnSprite lconnSprite-iconDropDown5x3",
			src : this.buttonIconUrl,
			alt : this.title,
		},
		this.iconNode);
		
		dojo.removeClass(this.iconNode, "dijitNoIcon");
	}
	
});

