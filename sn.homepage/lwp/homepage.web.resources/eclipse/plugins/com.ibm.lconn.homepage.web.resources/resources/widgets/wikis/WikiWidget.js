/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2007, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */



dojo.provide("lconn.homepage.widgets.wikis.WikiWidget");

dojo.require("lconn.homepage.core.common.Scroller");
dojo.require("lconn.homepage.widgets.activities.ActivitiesCalendar");
dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");
dojo.require("lconn.core.util._XSLCache");

dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("dojox.date.posix");
dojo.require("dojox.data.dom");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

dojo.declare("lconn.homepage.widgets.wikis.WikiWidget",lconn.homepage.core.base._BodyWidget, {
		_resourceBundle: null,
		// Nodes that are tables and show the entry info
		childNodes: null,
		// nodes
		content: null,		
		wikiWidgetTitle: '',
		_optionSet: null,
		_iContext: null,
		_scroller: null,
		data: null,		
		scrollerNode: null,				
		noWikiEntry1: "",
		noWikiEntry2: "",
		noWikiEntry3: "",
		loading: "",
		entriesPerPage: 3,
		templatePath: dojo.moduleUrl("lconn.homepage", "widgets/wikis/templates/wikiWidget.html"),
		
		xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
			xslStrings: {"wiki.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/wikis/wiki.xsl")}} 		
		})),

		
		pageNumber:1,
		userid: null,
		postCreate: function(){		
			lconn.homepage.widgets.wikis.WikiWidget.superclass.postCreate.apply(this);
				
			this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();
			
			this.userid = this._iContext.getUserProfile().getItemValue("userid");		
			
			this.wikiCentralLoading.style.display="";
			
			this.entriesPerPage = this._getAmountOfEntriesToDisplay();
			
			this.remoteUrl = this._feedUrls.defaultURL;									
			this.wikiLink.setAttribute("href", this._parameters.mappingRemoteUrl);
														
		},		
		
		loadLatestWikiEntriesMode: function(){
			this.pageNumber=1;
			this.retrieveAtomAndUpdate();			
		},	
			
		postMixInProperties: function(){			
			
			var title = this._modes.toUpperCase() + "_TITLE";
			
			this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
			//this.wikiWidgetTitle = this._resourceBundle[title];
			this.noWikiEntry1 = this._resourceBundle.WIKIS_NBE1;				
			this.noWikiEntry2 = this._resourceBundle.WIKIS_NBE2;
			this.noWikiEntry3 = this._resourceBundle.WIKIS_NBE3;
			this.loading=this._resourceBundle.COMMON_LOADING;
		},
		
		handleAsyncRequest: function(data, evt){
			if(data!=null) {			
				var html = "";
				
				var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
		        var xslParams = [['more', this._resourceBundle.COMMON_MORE],
								['tags', this._resourceBundle.COMMON_TAGS],
							//	['userid', this.userid],
								['noRecommendAlt', this._resourceBundle.WIKIS_NO_RECOMMEND_ALT],
								['recommendAlt', this._resourceBundle.WIKIS_RECOMMEND_ALT],
								['fullRecommendAlt', this._resourceBundle.WIKIS_FULL_RECOMMEND_ALT],
								['noCommentsAlt', this._resourceBundle.WIKIS_NO_COMMENTS_ALT],
								['commentsAlt', this._resourceBundle.WIKIS_COMMENTS_ALT],
								['wikiMode', this._modes]];
	        	
		        if(xslDoc)
		        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
				else
					html = this.getXsltSupport().getXsltResult(data, xslParams, this._parameters.xsltUrl);


				if(html!= "") {		
					this._initScroller(this._resourceBundle.WIKIS_PAGING_STATUS);								
					this.update();
					this.tempNode.innerHTML = html;
					var totalNode = dojo.query(".totalEntries", this.tempNode);
					var total = totalNode[0].innerHTML;
					dojo.query(".totalEntries", this.tempNode).orphan();
					html = this.tempNode.innerHTML;
					this.tempNode.innerHTML="";		
	
					this.setContent(html);
					if(total == 0){
						this.wikiCentralLoading.style.display="none";
						this.welcomeNode.style.display="";
					}
					else{						
						if (total != -1)
							this._scroller.setTotal(total);
						this.welcomeNode.style.display="none";
						this.wikiCentralLoading.style.display="none";
						this.wikiCentralContainer.style.display="";
						this.wikiCentralNode.style.display="";					
					}	

				} else {
					this.wikiCentralLoading.style.display="none";
					this.welcomeNode.style.display="";
				}						
			}
		},
		
		setupWidgets: function(opts){
			if(this.titleNode.innerHTML != "")				
				this.wikiTitleNode.innerHTML = opts.title;
			
			this._hideScroller();		
			this.getErrorHandler().hideError();
			
			this.wikiCentralLoading.style.display="";
			this.welcomeNode.style.display="none";
			this.wikiCentralContainer.style.display="none";
			this.wikiWidgetTitle.innerHTML = opts.title;
			
			this.pageNumber = 1;
			this._initScroller(this._resourceBundle.WIKIS_PAGING_STATUS);
			
			this.remoteUrl = opts.url + "&lang=" + dojo.locale.replace('-', '_');
			
			this.retrieveAtomAndUpdate();
		},
		
		LatestWikis: function(title) {
			this.setupWidgets({
				title: title,
				url: this._feedUrls.latestURL + 
						"&ps=" + this._getAmountOfEntriesToDisplay()
			});
		},
		
		MyWikis: function(title) {
			this.setupWidgets({
				title: title,
				url: this._feedUrls.myWikiURL + 
						"&ps=" + this._getAmountOfEntriesToDisplay()
			});
		},		
		
    	PopularWikis: function(title) {
    		this.setupWidgets({
				title: title,
				url: this._feedUrls.popularURL + 
						"&ps=" + this._getAmountOfEntriesToDisplay()
			});
		},
		
		_hideScroller: function(){
			this.scrollerNode.style.display = "none";
		},
		
		fetchMore: function() {
			this.pageNumber++;
			var Url = this.remoteUrl+"&page="+this.pageNumber;		
			var io = this.getIoSupport(this._iContext);
			dojo.connect(io, "handleAsyncRequest", this, "updateWiki");		
			io.retrieveAtomAndUpdate(Url, true);
			
		},
		
		updateWiki: function(data,evt){
			if(data!=null) {
				var html = "";
				
				var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
		        var xslParams = [['more', this._resourceBundle.COMMON_MORE],
									['tags', this._resourceBundle.COMMON_TAGS],
									//['userid', this.userid],
									['noRecommendAlt', this._resourceBundle.WIKIS_NO_RECOMMEND_ALT],
									['recommendAlt', this._resourceBundle.WIKIS_RECOMMEND_ALT],
									['fullRecommendAlt', this._resourceBundle.WIKIS_FULL_RECOMMEND_ALT],
									['noCommentsAlt', this._resourceBundle.WIKIS_NO_COMMENTS_ALT],
									['commentsAlt', this._resourceBundle.WIKIS_COMMENTS_ALT],
									['wikiMode', this._modes]];
	        	
		        if(xslDoc)
		        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
				else
					html = this.getXsltSupport().getXsltResult(data, xslParams, this._parameters.xsltUrl);


		
				if(html!= "") {	
								
					//this.update();					
					this.tempNode.innerHTML = html;
					var totalNode = dojo.query(".totalEntries", this.tempNode);
					var total = totalNode[0].innerHTML;
					dojo.query(".totalEntries", this.tempNode).orphan();
					html = this.tempNode.innerHTML;
					this.tempNode.innerHTML="";	
					this.boardingNode.innerHTML=html;
					var nodes = dojo.query(".showContent", this.boardingNode);
					for(var i=0; i<nodes.length; i++){
						dojo.connect(nodes[i],"onclick",this, "displayEntry");
					}
					var nodes = dojo.query(".content", this.boardingNode);
					for(var i=0; i<nodes.length; i++) {
						this.removeHTMLTags(nodes[i]);
					}

					// To format the date
					var nodes = dojo.query(".published", this.boardingNode);
					for(var i=0; i<nodes.length; i++){
						this.getDateFormatterSupport().formatDate(nodes[i]);
					}

					var nodes = dojo.query(".picture", this.boardingNode);
					for(var i=0; i<nodes.length; i++){
						var retUserid = nodes[i].getAttribute("userid");				
						nodes[i].src = this._feedUrls.photoSvc + "?userid=" + retUserid + "&lastMod=" + 1;
						nodes[i].setAttribute("width",35);				
						nodes[i].setAttribute("height",35);	
					}
			var nodes = dojo.query(".tag", this.boardingNode);
			for (var i=0; i<nodes.length; i++){
				var title = nodes[i].title;
				var href = dojo.attr(nodes[i], "href");
				var tag = this.sanitizeTag(title);
				
				var titleTextNode = dojo.doc.createTextNode(title);	
				
				var lang = dojo.locale;
				if(lang.length >=2){
					lang = lang.substring(0,2);
				}
				if(this._modes != "LatestWikiEntries" && this._modes != "PopularWikis"){
					dojo.attr(nodes[i], "href", href +"lang=" + lang +"&tag=" + tag);
					nodes[i].innerHTML = "";
					nodes[i].appendChild(titleTextNode);
				}
				else{
					dojo.attr(nodes[i], "href", href +"lang=" + lang +"#/public?tag=" + tag);
					nodes[i].innerHTML = "";
					nodes[i].appendChild(titleTextNode);
				}
			}
				//	try {
				//		SemTagSvc.parseDom(null, this.boardingNode);				
				//	} catch(e) {}	
					for(var i=0;i<this.boardingNode.childNodes.length;i++) {
						//skip over text nodes, IE9 issue
						if(this.boardingNode.childNodes[i].nodeType != 3){
							this.boardingNode.childNodes[i].style.display='none';
						}						
					}										
					
					//var total = this._getTotalNumberEntries(data);
					if (total != -1)
						this._scroller.setTotal(total);
						
					this._scroller.getModel().appendContent(this.boardingNode.childNodes);		
					this._scroller.getModel().onNextComplete();	
					this._scroller.getModel().fetchCompleted();
					this._scroller.getModel().incPage();
					var len = 0;
					 for(len = this.boardingNode.childNodes.length; len>0; len--) {					
						this.wikiCentralNode.appendChild(this.boardingNode.firstChild);
					}
					try {
						SemTagSvc.parseDom(null, this.wikiCentralNode);
						dojo.parser.parse(this.wikiCentralNode);
					} catch(e) {}	
					// Bidi support
			      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.wikiCentralNode);
				}
			}
		},
		sanitizeTag: function(tag){
			var hashCode = "%23";
			var percentCode = "%25";
			var ampCode = "%26";
			var hash = "#";
			var percent = "%";
			var amp = "&amp;";
			var weeAmp = "&";
			var atCode = "%40";
			var dollarCode = "%24";
			tag = tag.replace(/%/g,percentCode);
			tag = tag.replace(/\$/g,dollarCode);

			tag = tag.replace(/#/g,hashCode);
			tag = tag.replace(/&amp;/g,ampCode);
			tag = tag.replace(/&/g,ampCode);
			tag = tag.replace(/@/g,atCode);

			return tag;
		},
		
		setContent: function(html){
			if(html=="") {
				this.welcomeNode.display="none";
				return;
			} else {
				
				this.wikiCentralNode.innerHTML = html;

				try {
					SemTagSvc.parseDom(null, this.wikiCentralNode);	
					dojo.parser.parse(this.wikiCentralNode);
				} catch(e) {}		
			}			
			this.formatNodes();			
			this.addListners();			
			this._scroller.getModel().appendContent(this.wikiCentralNode.childNodes);			
			this._scroller.getModel().onNextComplete();	
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.wikiCentralNode);
		},
		
		addListners: function(){			
			var nodes = dojo.query(".showContent", this.wikiCentralNode);
			for(var i=0; i<nodes.length; i++){
				dojo.connect(nodes[i],"onclick",this, "displayEntry");
			}
		},
		
		formatNodes: function() {			
			// to clean up the html code
			var nodes = dojo.query(".content", this.wikiCentralNode);
			for(var i=0; i<nodes.length; i++) {
				this.removeHTMLTags(nodes[i]);
			}

			// To format the date
			var nodes = dojo.query(".published", this.wikiCentralNode);
			for(var i=0; i<nodes.length; i++){
				this.getDateFormatterSupport().formatDate(nodes[i]);
			}

			var nodes = dojo.query(".picture", this.wikiCentralNode);
			for(var i=0; i<nodes.length; i++){
				var retUserid = nodes[i].getAttribute("userid");				
				nodes[i].src = this._feedUrls.photoSvc + "?userid=" + retUserid + "&lastMod=" + 1;
				nodes[i].setAttribute("width",35);				
				nodes[i].setAttribute("height",35);
			}
			var nodes = dojo.query(".tag", this.wikiCentralNode);
			for (var i=0; i<nodes.length; i++){
				var title = nodes[i].title;
				var href = dojo.attr(nodes[i], "href");
				var tag = this.sanitizeTag(title);
				var lang = dojo.locale;
				
				var titleTextNode = dojo.doc.createTextNode(title);	
				
				if(lang.length >=2){
					lang = lang.substring(0,2);
				}
				if(this._modes != "LatestWikiEntries" && this._modes != "PopularWikis"){
					dojo.attr(nodes[i], "href", href +"lang=" + lang +"&tag=" + tag);
					nodes[i].innerHTML = "";
					nodes[i].appendChild(titleTextNode);
				}
				else{
					dojo.attr(nodes[i], "href", href +"lang=" + lang +"#/public?tag=" + tag);
					nodes[i].innerHTML = "";
					nodes[i].appendChild(titleTextNode);
				}				
			}			
		},
		
		//adding internationalization stuff
		internationalization: function(){			
			var nodes = dojo.query(".published", this.wikiCentralNode);
			for(var i=0; i<nodes.length; i++){
				var date = nodes[i].innerHTML;
				var newDate = dojo.string.substitute(this._resourceBundle.PUBLISHED, [date]);
				 nodes[i].innerHTML = newDate;
			}
		},		
		
		removeHTMLTags: function(node){			
			var str = node.innerHTML;		
						
			// remove all tags
			str = str.replace(/&gt;/g,">");
			str = str.replace(/&lt;[a-zA-Z\/][^>]*>/g,"");
						
			str = str.replace(/&amp;[^;]*;/g,"");
			str = str.replace(/&[^;]*;/g,"");	
			
			node.innerHTML = str;
		},
		
		displayEntry: function(evt){
			var target = evt.target;
			var parent = target.parentNode;		
			
			var popupContainerNode = dojo.query(".bodyHiddenContent", parent);				
			var html = popupContainerNode[0].innerHTML;			
			var htmlToShow = this.htmlify(html);			
			
			this.popup.showPopup(this._resourceBundle.WIKIS_DIALOG_TITLE);
			var pop = this.popup.setContent(htmlToShow);
			try {
				SemTagSvc.parseDom(null, pop);				
			} catch(e) {}
			dojo.stopEvent(evt);7
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
		},
		
		htmlify: function(stringin) {
			var htmlout = stringin.replace(/&lt;/g,"<");
			htmlout = htmlout.replace(/&gt;/g,">");
			htmlout=htmlout.replace(/&amp;/g,"&");
			return htmlout;
		},
		
		_getAmountOfEntriesToDisplay: function(){
			var numberEntries = this._optionSet.getItemValue("numberRecentWikiEntries");			
			
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
			this.entriesPerPage = newView;
		},
		
		handleError: function(data, evt){
			this.displayError({
				exceptionToDisplay: data
			});
		},
		
		displayError: function(messageObj){
			// summary: overriden to hide blogs sepcific nodes		   
			this.wikiCentralLoading.style.display = "none";
			this.wikiCentralContainer.style.display = "none";
			
			var handler = this.getErrorHandler();
			handler.setErrorNode(this.errorNode);
			handler.displayError(messageObj);
		},		
		
		MAX_AMOUNT: 25,
		DEFAULT_AMOUNT: 3		
	}	
);
