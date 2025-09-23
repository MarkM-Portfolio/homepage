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

dojo.provide("lconn.homepage.core.factory.WidgetFactory");

dojo.require("lconn.homepage.core.common.DashboardPanel");
dojo.require("lconn.homepage.core.widget.IWidgetWrapper");
dojo.require("lconn.homepage.core.widget.sidebar.SideBarWidget");

dojo.declare("lconn.homepage.core.factory.WidgetFactory", null, {
	_availWidgets:null,

	// _instanceToWidgetMapping: Map
	// Mapping between the widget instance id and the widget id
	// ie: activitiesInstance1Id: activitiesId
	// activitiesInstance2Id: activitiesId
	_instanceToWidgetMapping: null,

	constructor: function(){
		// todo: avoid using a global variable for available widgets
		this._availWidgets = widgets;
		this._instanceToWidgetMapping = {};
	},

	_searchWidget: function(widgetId){
		for(var i=0; i<this._availWidgets.length; i++){
			if (this._availWidgets[i].id == widgetId)
				return this._availWidgets[i];
		}

		return null;
	},

	createWidgetInstanceById: function(widgetId, widgetInstanceId, widgetSetting, isSideBar){
		// summary: create an instance of lconn.homepage.core.common.DashboardPanel (wrapping
		// an iWidget) from a widget id
		// widgetInstanceId is the id of the new created DashboardPanel. MUST BE
		// UNIQUE ON THE PAGE
		var widget = this._searchWidget(widgetId);

		var panel = null;

		if (widget != null){

			// pay attention here: the id of the widgets is actually the widget
			// INSTANCE id stored in our DB
			// mapping between widget id and widget instance id can be done with
			// getWidgetId

			if ((typeof isSideBar == "undefined") || ((typeof isSideBar != "undefined") && (!isSideBar))){
				panel = new lconn.homepage.core.common.DashboardPanel(
					{
						id: widgetInstanceId,
						title: widget.title,
						// TODO: check why Liang put this here???
						thisWidgetID: widgetInstanceId + "_container",
						_layoutModel: layoutModel // global var
					});
			}
			else {
				panel = new lconn.homepage.core.widget.sidebar.SideBarWidget(
					{
					id: widgetInstanceId,
					title: widget.title,
					// TODO: check why Liang put this here???
					thisWidgetID: widgetInstanceId + "_container"
				});
			}

			var currentWidget = new lconn.homepage.core.widget.IWidgetWrapper(
				{
					widgetTitle: widget.title,
					widgetDef: widget.url, // Modified for Enabler
					creUrl: widget.url, // Used unmodified by CRE
					widgetId: widgetInstanceId,
					widgetType: widget.type,
					isSystem: widget.isSystem,
					widgetSetting: widgetSetting
				});
			panel.setBodyWidget(currentWidget);

			// add the mapping between widget instance id and widget id
			this._instanceToWidgetMapping[widgetInstanceId] = widgetId;
		}

		return panel;
	},

	getWidgetId: function(/* String */ widgetInstanceId){
		// sumarry: obtain the id of the widget corresponding the the passed
		// instance id

		return this._instanceToWidgetMapping[widgetInstanceId];
	}
});
