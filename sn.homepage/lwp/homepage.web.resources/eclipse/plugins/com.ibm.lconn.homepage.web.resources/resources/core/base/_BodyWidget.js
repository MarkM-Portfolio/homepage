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

dojo.provide("lconn.homepage.core.base._BodyWidget");

dojo.require("lconn.homepage.core.base._DateFormatter");
dojo.require("lconn.homepage.core.base._ErrorHandler");
dojo.require("lconn.homepage.core.base._IOSupport");
dojo.require("lconn.homepage.core.base._IWidgetSupport");
dojo.require("lconn.homepage.core.base._XsltSupport");

// base class that contains some common functionalities of the dashboard widgets
dojo.declare(
	// widget name and class
	"lconn.homepage.core.base._BodyWidget",
	
	// superclass
	[dijit._Widget, dijit._Templated, dijit._Container],
	
	{		
		// modes
		_modes: null,
		
		_loadingImagePath: "",
		
		// An array of locales that require a different line height
		largeCharsLocales: ["zh-cn","zh-tw","ko"],
		
		constructor: function() {
			this._loadingImagePath = lconn.core.url.getServiceUrl(lconn.core.config.services.webresources)
			 + "/web/com.ibm.lconn.core.styles.oneui3/images/loading.gif?etag=" + ibmConfig.versionStamp;
		},
		
		postCreate: function() {
			// If a largeCharsLangs is used, we need to adjust line heights
			// of the titleNode
			if(this.titleNode && this.largeCharsLocales.join().indexOf(dojo.locale) != -1){
				dojo.addClass(this.titleNode, "largeChars");
			} 
		},
		
		retrieveAtomAndUpdate: function(forceRefresh){			
			this._ioSupport = new lconn.homepage.core.base._IOSupport(this._iContext);
			
			// add listeners
			dojo.connect(this._ioSupport, "handleAsyncRequest", this, "handleAsyncRequest");
			dojo.connect(this._ioSupport, "handleError", this, "handleError");		
			
			forceRefresh = forceRefresh == null ? true : forceRefresh; // true by default if nothing specified	
			
			this._ioSupport.retrieveAtomAndUpdate(this.remoteUrl, forceRefresh);
		},
		
		retrieveMultipleFeeds: function(){			
			this._ioSupport = new lconn.homepage.core.base._IOSupport(this._iContext);
			
			// add listener		
			dojo.connect(this._ioSupport, "multipleFeedHandler", this, "multipleFeedHandler");			
			
			this._ioSupport.retrieveMultipleFeeds(this.remoteUrls, true);			
		},		
		
		multipleFeedHandler: function(){
			hp_console_debug("retrieveMultipleFeeds called but multipleFeedHandler not overriden");
		},
		
		handleAsyncRequest: function(data, evt){
			hp_console_debug("retrieveAtomAndUpdate called but handleAsyncRequest not overriden");
		},		
				
		handleError: function(data, evt){
			this.getErrorHandler().displayError({
				exceptionToDisplay: data	
			});
		},
		
		getIoSupport: function(iContext){
			// always returns a new instance (to handle callback correctly)
			return new lconn.homepage.core.base._IOSupport(iContext);
		},
		
		getErrorHandler: function(){
			if (this._errorHandlerSupport == null){
				this._errorHandlerSupport = new lconn.homepage.core.base._ErrorHandler();
				this._errorHandlerSupport.setErrorNode(this.errorNode);			
			}			
			return this._errorHandlerSupport;
		},	
		
		getXsltSupport: function(){
			if (this._xsltSupport == null)
				this._xsltSupport = new lconn.homepage.core.base._XsltSupport(this._iContext);
						
			return this._xsltSupport;
		},		
		
		getDateFormatterSupport: function(){
			if(this._dateFormatterSupport==null)
				this._dateFormatterSupport = new lconn.homepage.core.base._DateFormatter();
									
			return this._dateFormatterSupport;
		},		
		
		getIWidgetSupport: function(){
			if(this._iWidgetSupport==null){
				this._iWidgetSupport = new lconn.homepage.core.base._IWidgetSupport();				
			}
									
			return this._iWidgetSupport;
		},
		
		getMaxUrl: function(){
			if (this._parameters != null)
				return this._parameters.mappingRemoteUrl;
			else return null;
		},	
		
		getSupportedActions: function(){
			if (this._parameters != null)
				return this._parameters.supportedActions;
			else return null;
		},
		
		getContextRoot: function(){
			return lconn.homepage.global.contextRoot;
		},
		
		getAbsoluteUrl: function(url){
			var contextRoot = this.getContextRoot();
			var absUrl = null; 
			
			if (contextRoot != null){
				// basic for 2.5
				absUrl = contextRoot + url;
			} else {
				absUrl = url;
			}
			
			return absUrl;
		},
		
		/** Get the total number of entry items from a feed document */
		_getTotalNumberEntries: function(/* XML Doc */data){
	        var total = -1;
	        
	        try{
	        	var namespaceUrl="http://a9.com/-/spec/opensearch/1.1/";
	        	
	            if (dojo.isIE){
	            	/* Handle the XML for IE using namespaces. */
	            	data.setProperty("SelectionLanguage","XPath");
	            	data.setProperty("SelectionNamespaces","xmlns:wd='" + namespaceUrl + "'");
	                total = data.selectNodes("//wd:totalResults")[0].childNodes[0].nodeValue;
	            }
	            else{
	                total = data.documentElement.getElementsByTagNameNS(namespaceUrl, 
	                								"totalResults")[0].childNodes[0].nodeValue;
	            }
	        } 
	        catch (ignoreException){
	            ;
	        }
	        
	        return total;
	    },
	    
	    /** Build a dropdown menu for the modes available to this widget.
	     *  For example, the Communities widget has two modes, Public and My.
	     *  NOTE: your subclass must declare 'menuModeButtonNode' and 'titleNode'
	     *  nodes for this function to work correctly. */
	    _createModeMenu: function(){
	    	//Build menu, if modes is null or the number of modes is equal to 1 then the menu will not be created		
			var iWidgetSupport = this.getIWidgetSupport();			
			iWidgetSupport.setIContext(this._iContext);
			
			// Get number of modes;
			var numModes = 0;
			for (var i in this._modes) {
				numModes++;
			}
			// Don't create menu if there's only one mode
			//		
			iWidgetSupport.setModes(this._modes);
			iWidgetSupport.setMenuNode(this.menuModeButtonNode);
			iWidgetSupport.setDescribedbyId(this.id + "_ViewDropDown");

			if (numModes <= 1 || !iWidgetSupport._buildModeMenu()) {
				this.titleNode.innerHTML = "";
				dojo.attr(this.titleNode.parentElement, "role", "presentation");
				dojo.attr(this.titleNode, "role", "presentation");
			}
	    },
		
		
		/** Initialize the page scroller */
		_initScroller: function(scrollerTmplString){
	        this._scroller = new lconn.homepage.core.common.Scroller();
	        
	        this._scroller.setProperties({
				 templateString: scrollerTmplString,
				 view: this._getAmountOfEntriesToDisplay(),
				 fetchCallbackObj: this,
				 fetchCallbackFunc: "fetchMore",
				 domNode: this.scrollerNode
			});	
	    },
	    
	    /** Display the scroller */
	    _displayScroller: function(){
	    	dojo.removeClass(this.scrollerNode, "lotusHidden");
	    },
	    
	    /** Hide the scroller */
	    _hideScroller: function(){
	    	dojo.addClass(this.scrollerNode, "lotusHidden");
	    },
		
		// new private members
		_dateFormatterSupport: null,
		_errorHandlerSupport: null,
		_ioSupport: null,
		_iWidgetSupport: null,
		_iContext: null,
		_xsltSupport: null,
		remoteUrl: null,
		remoteUrls: null		
	}
);
