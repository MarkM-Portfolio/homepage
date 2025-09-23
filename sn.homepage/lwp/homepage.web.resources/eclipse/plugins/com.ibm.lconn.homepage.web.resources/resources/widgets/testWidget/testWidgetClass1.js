/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2015                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("testWidgetClass1");

dojo.declare("testWidgetClass1", lconn.homepage.core.base.baseDashboardIWidget,{
//dojo.declare("testWidgetClass", null,{

	onLoad: function(){
		console.info("test widget onLoad()");
	},

	onView: function(){
		console.debug("test widget onView()");
		var contentDiv = this.iContext.getElementById("content");
		var profile = this.iContext.getUserProfile();

		contentDiv.innerHTML = profile.getItemValue("email") + "<br/>";
		contentDiv.innerHTML += profile.getItemValue("displayName") + "<br/>";
		contentDiv.innerHTML += profile.getItemValue("userid") + "<br/>";
	},

	fireEditEvent:function() {
			this.iContext.iEvents.fireEvent("onModeChanged", "", "{'newMode':'edit'}");
	},

	onEdit:function() {
		console.log("Entering testWidgetClass.onEdit()");

		if (!this._editing) {
			this.iContext.iEvents.fireEvent("onModeChanged", "", "{'newMode':'edit'}");
			this._editing = true;

			iWidgetAttributes= this.iContext.getiWidgetAttributes();
			att1=iWidgetAttributes.getItemValue("attribute1");
			att2=iWidgetAttributes.getItemValue("attribute2");
			att3=iWidgetAttributes.getItemValue("attribute3");
			var txtAttr1 = this.iContext.getElementById("txtAttr1");
			var txtAttr2 = this.iContext.getElementById("txtAttr2");
			var txtAttr3 = this.iContext.getElementById("txtAttr3");

			txtAttr1.value=att1;
			txtAttr2.value=att2;
			txtAttr3.value=att3;
			txtAttr1.focus();

			this.dumpAttributes();
		} else {
			this._editing = false;
			this.cancel();
		}
		console.log("Exiting testWidgetClass.onEdit()");
	},

	cancel: function() {
		console.debug("enter widget cancel()");
		this._editing = false;
		this.iContext.iEvents.fireEvent("onModeChanged", "", "{'newMode':'view'}");
	},

	saveAttributes:function(){
		console.log("enter testWidgetClass.changeAttributes()");
		var attr1 = this.iContext.getElementById("txtAttr1").value;
		var attr2 = this.iContext.getElementById("txtAttr2").value;
		var attr3 = this.iContext.getElementById("txtAttr3").value;
		this.iContext.getiWidgetAttributes().setItemValue("attribute1",attr1);
		this.iContext.getiWidgetAttributes().setItemValue("attribute2",attr2);
		this.iContext.getiWidgetAttributes().setItemValue("attribute3",attr3);
		iWidgetAttributes.save(dojo.hitch(this,this.savedCallback));
		this.dumpAttributes();
	},

	savedCallback:function(name,isSaved){
		console.debug(name + " saved :" + isSaved);
	},

	dumpAttributes: function(){
		var contentDiv = this.iContext.getElementById("content");

		iWidgetAttributes = this.iContext.getiWidgetAttributes();

		att1=iWidgetAttributes.getItemValue("attribute1");
		att2=iWidgetAttributes.getItemValue("attribute2");
		att3=iWidgetAttributes.getItemValue("attribute3");

		contentDiv.innerHTML = "attr1 :: " + att1 + "<br/>";
		contentDiv.innerHTML += "attr2 :: " + att2 + "<br/>";
		contentDiv.innerHTML += "attr3 :: " + att3 + "<br/>";
	},

	itemSetChanged: function(){
		console.debug("itemSetChanged");
	}
});
