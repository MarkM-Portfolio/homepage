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

dojo.provide("lconn.homepage.widgets.bookmarks.BookmarkWidget");

dojo.require("lconn.homepage.core.base._BodyWidget");

dojo.require("dojo.parser");
dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("dojox.date.posix");
dojo.require("dojox.data.dom");
dojo.require("lconn.homepage.core.common.Scroller");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");
dojo.require("lconn.core.util._XSLCache");


dojo.requireLocalization("lconn.homepage", "widgetstrings");
dojo.requireLocalization("lconn.homepage", "jsp");

dojo.declare(
	// widget name and class
	"lconn.homepage.widgets.bookmarks.BookmarkWidget",
	
	// superclass
	lconn.homepage.core.base._BodyWidget,

	// properties and methods
	{
		templatePath: dojo.moduleUrl("lconn.homepage", "widgets/bookmarks/templates/bookmarkWidget.html"),
		dogTitle: "",
		noBookmarks1:"",
		noBookmarks2:"",
		noBookmarks3:"",
		loading: "",
		userid: null,
		

		xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
			xslStrings: {"dogear.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/bookmarks/dogear.xsl")},
				"../dogear.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/bookmarks/dogear.xsl")}} 		
		})),
		
		postCreate: function(){			
			lconn.homepage.widgets.bookmarks.BookmarkWidget.superclass.postCreate.apply(this);	
			
			this.userid = this._iContext.getUserProfile().getItemValue("userid");	
			// TBD CRE doesn't return userid correctly.  Set to empty for now
			if (this.userid == null) {
				this.userid = "";
			}
			
			// init images
			this.bookmarkLink.href=this._parameters.mappingRemoteUrl;					
									
			this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();

			// Get the common resource path that we need to resolve URLs to the Add Bookmark dialog in the XSLT.
			this._bookmarkletPath = (lconn.homepage.global.isSecure) ? 
									 lconn.homepage.global.services.bookmarklet.secureUrl :
									 lconn.homepage.global.services.bookmarklet.url;
										
			this._createModeMenu();
		},
		
		/**
		 * Setups each of the bookmark widgets
		 * @param opts - Object {title: "", url: ""}
		 */ 
		setupWidget: function(opts){
			this._hideScroller();		
			this.getErrorHandler().hideError();
			this.dogearBookmarksNode.innerHTML = "";
			if(this.titleNode.innerHTML != "")				
				this.dogTitleNode.innerHTML = opts.title;
			
			this._hideTitleNode(opts.title);
			
			this._initScroller(this._resourceBundleWs.BOOKMARKS_PAGING_STATUS);
			this.update();
			
			this.remoteUrl = opts.url;
			this.retrieveAtomAndUpdate();
		},
		
		RecentBookmarks:function(title) {
			this.setupWidget({
				title: title,
				url: this._feedUrls.defaultURL + 
						"?ps=" + this._getAmountOfEntriesToDisplay() + 
						"&lang=" + dojo.locale
			});
		},
		
		WatchList:function(title) {
			this.setupWidget({
				title: title,
				url: this._feedUrls.watchURL + 
						"?ps=" + this._getAmountOfEntriesToDisplay() +
						"&userid=" + this.userid + "&lang=" + dojo.locale
			});
		},
		
		PopularBookmarks:function(title){
			this.setupWidget({
				title: title,
				url: this._feedUrls.popularURL +
						"?ps=" + this._getAmountOfEntriesToDisplay() +
						"&lang=" + dojo.locale
			});
		},
		
		MyBookmarks:function(title) {
			this.setupWidget({
				title: title,
				url: this._feedUrls.myBookURL + 
						"?ps=" + this._getAmountOfEntriesToDisplay() +
						"&userid="+this.userid + "&lang=" + dojo.locale
			});
		},
		
		WelcomeMode:function() {
			this.getErrorHandler().hideError();
			//SPR #DMCE86WQYX this.titleNode.style.display="none";
			this.dogearBookmarksNode.style.display="none";	
			this._hideScroller();		
			this.welcomeNode.style.display="";
		},
		
		_hideTitleNode: function(title) {
			if(title=="")
				this.titleNode.style.display="none";
			else
				this.titleNode.style.display="";
		},
		
		fetchMore: function(pageIndex){
			this.updateContents(pageIndex);
		},
		
		postMixInProperties: function(){
			this._resourceBundleJsp = dojo.i18n.getLocalization("lconn.homepage", "jsp");
			this._resourceBundleWs = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
			this.dogTitle = this._resourceBundleWs.BOOKMARKS_TITLE;
			this.noBookmarks1 = this._resourceBundleWs.BOOKMARKS_NB1;
			this.noBookmarks2 = this._resourceBundleWs.BOOKMARKS_NB2;
			this.noBookmarks3 = this._resourceBundleWs.BOOKMARKS_NB3;
			this.loading=this._resourceBundleWs.COMMON_LOADING;			
		},
		
		_applyXslt: function(data){				
	        var html = "";
	        
			var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
	        var xslParams = [['copy',this._resourceBundleWs.BOOKMARKS_COPY],
	    					 ['userid',this.userid],
	    					 ['details',this._resourceBundleWs.BOOKMARKS_DETAILS],
	    					 ['tags',this._resourceBundleWs.COMMON_TAGS],
	    					 ['edit',this._resourceBundleWs.BOOKMARKS_EDIT],
	    					 ['dogearTitle',this._resourceBundleJsp["jsp.common.service.name.dogear"]]];
	        	
	        if(xslDoc)
	        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
			else
				html = this.getXsltSupport().getXsltResult(data, xslParams, this._parameters.xsltUrl);			
						
			if(html=="") 
				return -1;
			
			this.boardingNode.innerHTML = html;
			
			for(var i=0;i<this.boardingNode.childNodes.length;i++) {
				var node = this.boardingNode.childNodes[i];
				if(node.nodeType==1) {
					var dateNode = dojo.query(".dogAtomDate", node);			
					for(var j=0; j<dateNode.length; j++){
						this.getDateFormatterSupport().formatDate(dateNode[j]);
					}
					var detailNode = dojo.query(".dogDetails", node);
					for(var j=0; j<detailNode.length; j++){
						dojo.connect(detailNode[j],"onclick",this,"fetchDetails");
					}
					node.style.display='none';						
				}
			}					
				
			this._scroller.getModel().appendContent(this.boardingNode.childNodes);
			this._scroller.getModel().onNextComplete();
				
			while(this.boardingNode.firstChild) {					
				this.dogearBookmarksNode.appendChild(this.boardingNode.firstChild);
			}			
			
			try {
				SemTagSvc.parseDom(null, this.dogearBookmarksNode);
			} catch (e) {
			}	
			
			dojo.parser.parse(this.dogearBookmarksNode);
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.dogearBookmarksNode);									
		},
		
		handleAsyncRequest: function(data, evt){			
			this.dogearLoadingNode.style.display = "none";
			var result = this._applyXslt(data);					
			
			if (result == -1){
				this.WelcomeMode();
			}
			else{
				this.welcomeNode.style.display="none";
				
				this._scroller.setTotal(this._getTotalNumberEntries(data));
				
				this._scroller.getModel().refreshView();
				this.dogearBookmarksNode.style.display = "";
				this._displayScroller();				
			}			
		},	
		
		appendNew: function(data,evt) {					
			var xmlData = data;
			var result = this._applyXslt(xmlData);
			
			if (result == -1){								
				this._scroller.getModel().unregisterFetchMoreCallback();
			}
			else {					
				this._scroller.getModel().fetchCompleted();
				this._scroller.getModel().incPage();
				this._scroller.getModel().refreshView();					
			}			
		},			
		
		updateContents: function(pageIndex) {
			// Make sure the remoteUrl is initialized
			if(this.remoteUrl){
				pageIndex = parseInt(pageIndex);	
				pageIndex++;				
			
				var url = this.remoteUrl + "&page=" + pageIndex;
			
				var io = this.getIoSupport(this._iContext);
				dojo.connect(io, "handleAsyncRequest", this, "appendNew");					
			
				io.retrieveAtomAndUpdate(url, true);
			}			
		},	
		
		fetchDetails: function(evt) {			
			var url = evt.target.getAttribute('location');
			var title = evt.target.getAttribute('title');
			this.popup.showPopup(title);
			
			var io = this.getIoSupport(this._iContext);
			dojo.connect(io, "handleAsyncRequest", this, "displayDetails");					
			
			io.retrieveAtomAndUpdate(url, true);			
		},
		
		displayDetails: function(data) {
			var xmlData = data;
			var xsltUrl = dojo.moduleUrl("lconn.homepage.widgets.bookmarks","xslt/bookmarkDetails.xsl");
			var html="";
			html = lconn.core.xslt.transformDocument(xmlData, xsltUrl.toString(),
														[['copy',this._resourceBundleWs.BOOKMARKS_COPY],
														 ['userid',this.userid],
														 ['details',this._resourceBundleWs.BOOKMARKS_DETAILS],
														 ['tags',this._resourceBundleWs.COMMON_TAGS],
														 ['edit',this._resourceBundleWs.BOOKMARKS_EDIT],
														 ['bookmarkletPath', this._bookmarkletPath],
														 ['ariaTag', this._resourceBundleWs.ARIA_TAG],
														 ['ariaTagDescription', this._resourceBundleWs.ARIA_TAG_DESCRIPTION],
								    					 ['openNewWindowStr', this._resourceBundleWs.BOOKMARKS_ARIA_OPENS_NEW_WINDOW]]);
						
			if(html == ""){
				html = "<span tabindex='0' aria-label='" + this._resourceBundleWs.BOOKMARKS_ERROR_DETAILS + "'>" + this._resourceBundleWs.BOOKMARKS_ERROR_DETAILS + "</span>";
				this.popup.setContent(html);				
			}else{
				var pop = this.popup.setContent(html);
				var dateNodes = dojo.query(".dogAtomDate",pop);
				for(var i=0;i<dateNodes.length;i++) {
					try {this.getDateFormatterSupport().formatDate(dateNodes[i]);} catch (E) {}
				}
				var cNodes = dojo.query(".contentNode",pop);
				for(var i=0;i<cNodes.length;i++) {
					var htmlout = cNodes[i].innerHTML;
					htmlout = htmlout.replace(/&lt;/g,"<");
					htmlout = htmlout.replace(/&gt;/g,">");
					htmlout=htmlout.replace(/&amp;/g,"&");
					cNodes[i].innerHTML=htmlout;
				}
				try {
					SemTagSvc.parseDom(null, pop);				
				} catch(e) {}
				// Bidi support
		      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
			}
		},
		
		_getAmountOfEntriesToDisplay: function(){
			var numberEntries = this._optionSet.getItemValue("recentBookmarksNumberEntries");			
			
			var newView = (numberEntries == "" || numberEntries == null) ? this.DEFAULT_AMOUNT : parseInt(numberEntries);
			
			if (newView == "NaN")
				newView = this.DEFAULT_AMOUNT;
			else	
				newView = (newView > 0 && newView < this.MAX_AMOUNT) ? newView : this.DEFAULT_AMOUNT;
				
			return newView;
		},
		
		update: function(){
			// init number of entried to show
			var newView = this._getAmountOfEntriesToDisplay();				
			this._scroller.getModel().resetView(newView, newView);	
		},
		
		handleError: function(data, evt){
			this.displayError({
				exceptionToDisplay: data
			});
		},
		
		displayError: function(messageObj){
			// summary: overriden to hide blogs sepcific nodes		   
			this.dogearLoadingNode.style.display = "none";			
			this.dogearBookmarksNode.style.display = "none";			
			this.welcomeNode.style.display = "none";
			this.dogearLoadingNode.style.display = "none";	
			
			this._hideScroller();					
			
			var handler = this.getErrorHandler();
			handler.setErrorNode(this.errorNode);
			handler.displayError(messageObj);
		},
		
		_childNodes: null,
		_page: null,
		_total: null,
		_position: 0,
		_amount: null,
		_resourceBundleWs: null,
		_resourceBundleJsp: null,
		_tags: null,
		_iContext: null,
		_optionSet: null,
		_scroller: null,
		_bookmarkletPath: "",

		titleNode: null,
		
		// nodes
		scrollerNode: null,
		
		// CONST
		DEFAULT_AMOUNT : 3,
		MAX_AMOUNT: 25
		
	}
	
);
