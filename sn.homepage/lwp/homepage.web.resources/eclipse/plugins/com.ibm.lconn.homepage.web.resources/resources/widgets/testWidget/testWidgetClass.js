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

dojo.provide("testWidgetClass");

dojo.declare("testWidgetClass", null,{

	onLoad: function(){
		console.info("test widget onLoad()");
		var profile = this.iContext.getUserProfile();
	},

	jsAlert: function(){
		console.debug("test widget jsAlert()");
	},

	onView: function(){
		console.debug("test widget onView()");

		var displayDiv = this.iContext.getElementById("displayDiv");
		var profile = this.iContext.getUserProfile();

		displayDiv.innerHTML = profile.getItemValue("email") + "<br/>";
		displayDiv.innerHTML += profile.getItemValue("displayName") + "<br/>";
		displayDiv.innerHTML += profile.getItemValue("userid") + "<br/>";
	},

	onEdit:function() {
		console.log("Entering testWidgetClass.onEdit()");
		if (!this._editing) {
			this.iContext.iEvents.fireEvent("onModeChanged", "", "{'newMode':'edit'}");
			this._editing = true;
		} else {
			this._editing = false;
			this.cancel();
		}
		console.log("Exiting testWidgetClass.onEdit()");
	},

	cancel: function() {
		console.debug("test widget cancel()");
		this.iContext.iEvents.fireEvent("onModeChanged", "", "{'newMode':'view'}");
	},

	dumpAttributes: function(){
		console.debug("test widget dumpAttributes()");
		var itemset = this.iContext.getItemSet("attributes", true)
        console.debug("attribute1 set to " + itemset.getItemValue("attribute1"));
        console.debug("attribute2 set to " + itemset.getItemValue("attribute2"));
        console.debug("attribute3 set to " + itemset.getItemValue("attribute3"));

		var div = this.iContext.getElementById("displayDiv");

		displayDiv.innerHTML = "attribute1 set to " + itemset.getItemValue("attribute1") + "<br/>";
		displayDiv.innerHTML += "attribute2 set to " + itemset.getItemValue("attribute2") + "<br/>";
		displayDiv.innerHTML += "attribute3 set to " + itemset.getItemValue("attribute3") + "<br/>";
	},

	saveAttributes:function(){
		console.log("Entering testWidgetClass.saveAttributes()");

		var attr1 = this.iContext.getElementById("txtAttr1").value;
		var attr2 = this.iContext.getElementById("txtAttr2").value;
		var attr3 = this.iContext.getElementById("txtAttr3").value;

		this.iContext.getiWidgetAttributes().setItemValue("attribute1",attr1);
		this.iContext.getiWidgetAttributes().setItemValue("attribute2",attr2);
		this.iContext.getiWidgetAttributes().setItemValue("attribute3",attr3);

		this.iContext.getiWidgetAttributes().save();

		console.log("Exiting testWidgetClass.saveAttributes()");
	}

});
