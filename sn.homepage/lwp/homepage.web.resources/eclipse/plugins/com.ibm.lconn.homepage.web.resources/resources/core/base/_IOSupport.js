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

dojo.provide("lconn.homepage.core.base._IOSupport");

dojo.require("lconn.core.xslt");
dojo.require("com.ibm.oneui.util.proxy");
dojo.require("dojox.collections.Dictionary");

dojo.declare(
	// widget name and class
	"lconn.homepage.core.base._IOSupport",
	
	// superclass
	null,
	
	// properties and methods
	{
		// summary: Base/utility class regrouping common methods to IO/Ajax
		// description: This class provides an easy way to:
		//		- make a GET or POST request to any url (the url is rewritten to use the Ajax proxy if needed)
		//		- GET an ATOM feed
		//		- request several xml resources (feed) at one time in an asynchronous way. 
		//			A callback (multipleFeedHandler) is called when all the requests have returned
		//
		//	If an iContext is supplied to the constructor, this class uses io.rewriteURI as defined in the iWidget spec, 
		// 		otherwise the URI is rewritten using specific Homepage code bound to the built-in proxy
		//	Note that we do not use io.request directly but dojo.xhrGet and xhrPost which have the same bahavior 
		//		(request is just a convenience method according to the specs)
		
		constructor: function(iContext){
			this._iContext = iContext;
		},
				
		handleAsyncRequest: function(data, evt){
			// summary: Method called asynchronously when the Atom feed has been retireved successfully (400)
			//	dojo.connect to this method to call your callbacks 
  			;
		},
		
		handleError: function(data, evt){	
			// summary: Method called asynchronously when an error occured while retrieving the feed or while creating the XML doc
			//	dojo.connect to this method to call your callbacks 
  			;			
		},
		
		xhrGet: function(/* Object : see dojo.xhrGet doc */bindArgs){
			// summary: Rewrite the URL if a proxy is needed and call dojo.xhrGet with bindArgs
			// returns: Defered object returned by dojo.xhrGet
			
			if (this._iContext != null){
				// follow iWidget specs if iContext provided (not bound to our proxy)
				bindArgs.url = this._iContext.io.rewriteURI(bindArgs.url);
				return dojo.xhrGet(bindArgs);
			}
			else{
				bindArgs.url = com.ibm.oneui.util.proxy(bindArgs.url);
				return dojo.xhrGet(bindArgs);	
			}			
		},
		
		xhrPost: function(/* Object : see dojo.xhrPost doc */bindArgs){
			// summary: Rewrite the URL if a proxy is needed and call dojo.xhrPost with bindArgs
			// returns: Defered object returned by dojo.xhrPost
			
			if (this._iContext != null){
				// follow iWidget specs if iContext provided (not bound to our proxy)
				bindArgs.url = this._iContext.rewriteURI(bindArgs.url);
				return dojo.xhrPost(bindArgs);
			}
			else{
				bindArgs.url = com.ibm.oneui.util.proxy(bindArgs.url);
				return dojo.xhrPost(bindArgs);	
			}					
		},	
		
		retrieveAtomAndUpdate: function(/* String */ url, /* Boolean? */ forceRefresh){	
			// summary: Send a GET request to the url (proxied if needed), forcing a fresh requests (if the param is set)
			//	    and formating the XML independently of the mimetype
  			// description: - fresh request (browser side) by appending a fake param "forceRefresh"	
			//				- create an XML doc even if the mimetype is not set correctly to "text/xml" by the server
			//					This is a workaround for IE.
			//		dojo.connect to handleAsyncRequest (success) and handleError (failed) for the callback 	
			// returns: Defered object created by dojo.xhrGet
				
			var req = null;
				
			if(url != null)
			{
				if (forceRefresh){
					if (url.indexOf("?") != -1)
						url += "&forceRefresh=" + (new Date()).valueOf();	
					else
						url += "?forceRefresh=" + (new Date()).valueOf();		
				}		
				
				var bindArgs = {				
    				url: url,
					handleAs: "text",
					expectedContentType: "xml",
					timeout: this.TIMEOUT_MS
   				};	
				
				var req = this.xhrGet(bindArgs);
				// call workaround for IE before handleAsyncRequest			
				req.addCallback(this, "_formatXml");   	
				req.addErrback(this, "handleError");		
   			}
			
			return req;	
		},		
		
		_formatXml: function(data, evt){
			// summary: workaround for IE
			// descriptions: IE only creates an XML Document from the response if the content-type is "text/xml"
			// 	however, the content-type of the feeds is "application/xhtml+xml" so we have to get the response 
			// 	as text and create the XML doc manually			
			
			try{
				var xml = lconn.core.xslt.loadXmlString(data);
				this.handleAsyncRequest(xml, evt);
			}
			catch(e){				
				this.handleError(data, evt);			
			}
		},
		
		retrieveMultipleFeeds: function(/* String[] */urls, /* Boolean? */ forceRefresh){
			// summary: Send several GET requests each to the urls (proxied if needed), forcing a fresh requests 
			//		and formating the XML independently of the mimetype
  			// description: 
			//		multipleFeedHandler method is called when all the requests have completed	
			  			
			if((urls != null) && (dojo.isArray(urls)))
			{			
				this._multipleFeedResult = new dojox.collections.Dictionary();
				this._nbCompleted = 0;
				this._totalToComplete = urls.length;
			
				for(var i=0; i<urls.length; i++){
					var url = urls[i];
					
					if (forceRefresh){
						if (url.indexOf("?") != -1)
							url += "&forceRefresh=" + (new Date()).valueOf();	
						else
							url += "?forceRefresh=" + (new Date()).valueOf();
					}				
				
					var bindArgs = {				
    					url: url,
						handleAs: "text",
						timeout: this.TIMEOUT_MS					
   					};	
				
					var req = this.xhrGet(bindArgs);	
					req.addCallback(dojo.hitch(this, "_handlerSuccessMultiple", urls[i]));
					req.addErrback(dojo.hitch(this, "_handleErrorMultiple", urls[i]));					
				}				
   			}	
		},
		
		_handlerSuccessMultiple: function(url, data, evt){
			// summary: Internally used as a callback by retrieveMultipleFeeds							
			var xml = lconn.core.xslt.loadXmlString(data);						
			this._multipleFeedResult.add(url, {status: 1, data: xml});	
			
			this._checkMultipleCompleted();				
		},
		
		_handleErrorMultiple: function(url, data, evt){
			// summary: Internally used as a callback by retrieveMultipleFeeds			
			this._multipleFeedResult.add(url, {status: 0, data: data});	
			this._checkMultipleCompleted();				
		},
		
		_checkMultipleCompleted: function(){
			// summary: Internally used as a callback by retrieveMultipleFeeds			
			this._nbCompleted++;
			
			if (this._nbCompleted == this._totalToComplete)
				this.multipleFeedHandler(this._multipleFeedResult);
		},
		
		multipleFeedHandler: function(/* dojox.collections.Dictionary*/ multipleFeedResult){
			// summary: Callback method called when all the request have completed
			// description: - You can override this method in a subclass to add your code to handle the responses
			//				- You can also use dojo.connect on this method to invoke your own code
			//				- multipleFeedResult is a Dictionary (Map) of (url, resultObject}
			//					with resultObject having the following format: {status: 0, data: data}
			//						* status = 1 if the request is a success, 0 otherwise
			//						* data, xml document if the request is a success, exception object otherwise
			//
			// see also: retrieveMultipleFeeds
			;
		},		
		
		/*
		getInstance: function(args){
			if (lconn.homepage.core.base._IOSupport.prototype._instance == null)
				lconn.homepage.core.base._IOSupport.prototype._instance == new lconn.homepage._IOSupport(args);
			
			return lconn.homepage.core.base._IOSupport.prototype._instance;
		}, */
				
		// private members		
		_ajaxRequestsHandler: null,	
		_multipleFeedResult: null,	
		_nbCompleted: null,
		_totalToComplete: null,
		_iContext: null,
		
		// const
		TIMEOUT_MS: 30000 // 30 sec		
	}
);
