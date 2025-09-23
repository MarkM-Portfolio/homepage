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

dojo.provide("simple.widget");
dojo.declare("simple.widget", [], {

    onLoad: function() {
        console.info("simple.widget.onLoad()");
    },

    onview: function() {
     	console.info("simple.widget.view()");
    },

    cancel: function() {
    console.info("simple.widget.cancel()");
    this._onEdit=false;
    this.iContext.iEvents.fireEvent("onModeChanged", "", "{'newMode':'view'}");
    },

    onEdit: function() {
    console.info("simple.widget.onedit()");
      if (!this._onEdit) {
        this.iContext.iEvents.fireEvent("onModeChanged", "", "{'newMode':'edit'}");
        this._onEdit=true;
      } else {
        this.iContext.iEvents.fireEvent("onModeChanged", "", "{'newMode':'view'}");
        this._onEdit=false;
      }
    }
});

