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

dojo.provide("lconn.homepage.core.widget.iwExtension.WidgetDescriptorStore");

dojo.declare(
	// widget name and class
	"lconn.homepage.core.widget.iwExtension.WidgetDescriptorStore",
	
	// superclass
	null,

	// properties and methods
	{
		// summary: Component storing the iWidget XML definition client-side. 
		//		Populated by fragments present on the page and called by our custom enabler extentions on widget initialization					
		//		Supposed to be used as a singleton (lconn.homepage.iwExtension.widgetStore global variable)
		
		_descriptors: null,
		
		putDescriptor: function(/* String */ key, /* String */ xmlDescriptor){
			// summary: add an xml fragment for a given key in the store
			//		if xmlDescriptor is null or undefined, the corresponding xml fragement is set to an empty string
			
			xmlDescriptor = xmlDescriptor == null ? "" : xmlDescriptor;
			
			if (key != null){
				this._descriptors[key] = xmlDescriptor;
			}			
		},
		
		removeDescriptor: function(/* String */ key){
			// summary: set empty string the corresponding descritor
			//		does NOT destroy the corresponding descriptor
			
			this.putDescriptor(key);
		},
		
		destroyDescriptor: function(/* String */ key){
			// summary: destroy the corresponding descritor
			
			delete this._descriptors[key];
		},
		
		get: function(/* String */ widgetId){
			return this._descriptors[widgetId];
		},
		
		constructor: function(){			
			if (lconn.homepage.core.widget.iwExtension.widgetStore != null)
				throw new Error("lconn.homepage.core.widget.iwExtension.widgetStore already instantiated (it's a singleton!)");
			
			this._descriptors = {};
		}	
	}	
);

lconn.homepage.core.widget.iwExtension.widgetStore = new lconn.homepage.core.widget.iwExtension.WidgetDescriptorStore();
