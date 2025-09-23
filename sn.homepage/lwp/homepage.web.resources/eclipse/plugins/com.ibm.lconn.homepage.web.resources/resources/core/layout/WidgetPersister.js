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

dojo.provide("lconn.homepage.core.layout.WidgetPersister");

dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.declare("lconn.homepage.core.layout.WidgetPersister", null, {	
	// summary: component responsible for sending the requests to persist the layout server-side
	
	constructor: function(/* lconn.homepage.core.layoutWidgetLayoutModel */layoutModel, /* String */ pathPersistServlet){		
		if ((layoutModel != null) && (pathPersistServlet != null)){
			this._layoutModel = layoutModel;
			this._pathPersistServlet = pathPersistServlet;
			
			dojo.subscribe(lconn.homepage.events.layout.UPDATED, this, "saveLayout");				
		}
	},
	
	saveLayout: function(){
		//var persistStr = this._layoutModel.generatePersistanceString();
		
		var layoutJsonObj = this._layoutModel.generateLayoutObject();
		
		if (layoutJsonObj != null){
			var params = {};
			params['Act'] = 'ChangePos';
			params['Pos'] = dojo.toJson(layoutJsonObj);
				
			var url = this._pathPersistServlet; // needed for the xhrPost params closure	
			dojo.xhrPost({
				url: url,
				content: params,
				handleAs: "json"
			});
		}			
	},
	
	_layoutModel: null,
	_pathPersistServlet: null	
});
