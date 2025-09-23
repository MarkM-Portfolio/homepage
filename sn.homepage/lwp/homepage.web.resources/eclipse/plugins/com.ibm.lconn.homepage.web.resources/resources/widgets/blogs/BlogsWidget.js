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


dojo.provide("lconn.homepage.widgets.blogs.BlogsWidget");

dojo.require("lconn.homepage.core.common.Scroller");
dojo.require("lconn.homepage.widgets.activities.ActivitiesCalendar");
dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");
dojo.require("lconn.core.util._XSLCache");


dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("dojox.date.posix");
dojo.require("dojox.data.dom");
dojo.require("dojox.html.entities");

dojo.requireLocalization("lconn.homepage", "widgetstrings");
dojo.requireLocalization("lconn.homepage", "jsp");

dojo.declare("lconn.homepage.widgets.blogs.BlogsWidget",lconn.homepage.core.base._BodyWidget, {
		// Resource bundles to be loaded
		_resourceBundleWs: null,
		_resourceBundleJsp: null,
		// Nodes that are tables and show the entry info
		childNodes: null,
		// nodes
		content: null,		
		blogWidgetTitle: '',
		_optionSet: null,
		_iContext: null,
		_scroller: null,
		data: null,		
		scrollerNode: null,				
		noBlogEntry1: "",
		noBlogEntry2: "",
		noBlogEntry3: "",
		loading: "",
		userid: null,
		entriesPerPage: 3,
		templatePath: dojo.moduleUrl("lconn.homepage", "widgets/blogs/templates/blogsWidget.html"),
		
		xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
			xslStrings: {"blogs.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/blogs/blogs.xsl")}} 		
		})),
		
		pageNumber:0,
		postCreate: function(){
			lconn.homepage.widgets.blogs.BlogsWidget.superclass.postCreate.apply(this);
						
			this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();
			
			this.userid = this._iContext.getUserProfile().getItemValue("userid");		
			
			this.blogsCentralLoading.style.display="";
			
			// Adding icons to display the raccomandation and comments ranking
			this.entriesPerPage = this._optionSet.getItemValue("numberRecentBlogEntries");
			this.remoteUrl = this._feedUrls.defaultURL + 
								"&lang=" + dojo.locale.replace('-', '_') + 
								"&ps=" + this._getAmountOfEntriesToDisplay();									
			this.blogLink.setAttribute("href", this._parameters.mappingRemoteUrl);
		},		
		
		loadLatestBlogEntriesMode: function(){
			this.pageNumber=0;
			this.retrieveAtomAndUpdate();			
		},	
			
		postMixInProperties: function(){			
			this._resourceBundleJsp = dojo.i18n.getLocalization("lconn.homepage", "jsp");
			this._resourceBundleWs = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
			this.blogWidgetTitle = this._resourceBundleWs.BLOGS_TITLE_LATEST_ENTRIES;
			this.noBlogEntry1 = this._resourceBundleWs.BLOGS_NBE1;				
			this.noBlogEntry2 = this._resourceBundleWs.BLOGS_NBE2;
			this.noBlogEntry3 = this._resourceBundleWs.BLOGS_NBE3;
			this.loading=this._resourceBundleWs.COMMON_LOADING;
		},
		
	    _applyXSLT: function(data){
	    	var html = "";					
	       
			var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
			var xslParams = [['more', this._resourceBundleWs.COMMON_MORE],
							['tags', this._resourceBundleWs.COMMON_TAGS],
							['userid', this.userid],
							['noLikeAlt', this._resourceBundleWs.BLOGS_NO_LIKE_ALT],
							['likeAlt', this._resourceBundleWs.BLOGS_LIKE_ALT],
							['fullLikeAlt', this._resourceBundleWs.BLOGS_FULL_LIKE_ALT],
							['noCommentsAlt', this._resourceBundleWs.COMMON_NO_COMMENTS_ALT],
							['commentsAlt', this._resourceBundleWs.COMMON_COMMENTS_ALT],
							['blankGifPath', this._blankGif],
			                ['ariaTag', this._resourceBundleWs.ARIA_TAG],
			                ['entryTitle', this._resourceBundleJsp["jsp.common.service.name.blogs"]]];
        	
			if(xslDoc)
				html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
			else
				html = this.getXsltSupport().getXsltResult(data, xslParams, this._parameters.xsltUrl);
			
			return html;
	    },
        
		handleAsyncRequest: function(data, evt){
			if(data!=null) {			
				var html = this._applyXSLT(data);				
		        
				if(html!= "") {		
					this._initScroller(this._resourceBundleWs.BLOGS_PAGING_STATUS);								
					this.update();	
					
					var total = this._getTotalNumberEntries(data);
					
					if (total != -1)
						this._scroller.setTotal(total);
						
					this.setContent(html);
					this.welcomeNode.style.display="none";
					this.blogsCentralLoading.style.display="none";
					this.blogsCentralContainer.style.display="";
				} else {
					this.blogsCentralLoading.style.display="none";
					this.welcomeNode.style.display="";
				}						
			}
		},
		
		fetchMore: function() {
			var Url = this.remoteUrl+"&page="+(this.pageNumber+1);		
			var io = this.getIoSupport(this._iContext);
			dojo.connect(io, "handleAsyncRequest", this, "updateBlogs");		
			io.retrieveAtomAndUpdate(Url, true);
		},
		
		updateBlogs: function(data,evt){
			if(data!=null) {
				var html = this._applyXSLT(data);				
		        		
				if(html!= "") {	
					this.pageNumber++;								
					//this.update();					
				
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
					try {
						SemTagSvc.parseDom(null, this.boardingNode);	
						dojo.parser.parse(this.boardingNode);
					} catch(e) {}	
					for(var i=0;i<this.boardingNode.childNodes.length;i++) {
						//skip over text nodes, IE9 issue
						if(this.boardingNode.childNodes[i].nodeType != 3)
							this.boardingNode.childNodes[i].style.display='none';						
					}										
					
					var total = this._getTotalNumberEntries(data);
					if (total != -1)
						this._scroller.setTotal(total);
						
					this._scroller.getModel().appendContent(this.boardingNode.childNodes);
					this._scroller.getModel().onNextComplete();
					this._scroller.getModel().fetchCompleted();
					this._scroller.getModel().incPage();
					
					while(this.boardingNode.firstChild) {					
						this.blogsCentralNode.appendChild(this.boardingNode.firstChild);
					}
				}
				// Bidi support
		      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.blogsCentralNode);
			}
		},
		
		setContent: function(html){
			if(html=="") {
				this.welcomeNode.display="none";
				return;
			} else {
				
				this.blogsCentralNode.innerHTML = html;

				try {
					SemTagSvc.parseDom(null, this.blogsCentralNode);
					dojo.parser.parse(this.blogsCentralNode);
				} catch(e) {}		
			}			
			this.formatNodes();			
			this.addListners();			
			this._scroller.getModel().appendContent(this.blogsCentralNode.childNodes);			
			this._scroller.getModel().refreshView();
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.blogsCentralNode);
		},
		
		addListners: function(){			
			var nodes = dojo.query(".showContent", this.blogsCentralNode);
			for(var i=0; i<nodes.length; i++){
				dojo.connect(nodes[i],"onclick",this, "displayEntry");
			}
		},
		
		formatNodes: function() {			
			// to clean up the html code
			var nodes = dojo.query(".content", this.blogsCentralNode);
			for(var i=0; i<nodes.length; i++) {
				this.removeHTMLTags(nodes[i]);
			}

			// To format the date
			var nodes = dojo.query(".published", this.blogsCentralNode);
			for(var i=0; i<nodes.length; i++){
				this.getDateFormatterSupport().formatDate(nodes[i]);
			}

			var nodes = dojo.query(".picture", this.blogsCentralNode);
			for(var i=0; i<nodes.length; i++){
				var retUserid = nodes[i].getAttribute("userid");				
				nodes[i].src = this._feedUrls.photoSvc + "?userid=" + retUserid + "&lastMod=" + 1;
				nodes[i].setAttribute("width",35);				
				nodes[i].setAttribute("height",35);
			}
		},
		
		//adding internationalization stuff
		internationalization: function(){			
			var nodes = dojo.query(".published", this.blogsCentralNode);
			for(var i=0; i<nodes.length; i++){
				var date = nodes[i].innerHTML;
				var newDate = dojo.string.substitute(this._resourceBundleWs.PUBLISHED, [date]);
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
			// Get the grandparent node (div.entryBody)
			var parent = target.parentNode.parentNode;		
			var popupContainerNode = dojo.query(".bodyHiddenContent", parent)[0].cloneNode(true);
			var ariaforchange ='';
			dojo.query("div[data-nodeforchange]", popupContainerNode).forEach(function(item, i){
				item.id=item.id+"_Dialog";
				ariaforchange_div = item.id;
				
				var htmlout = item.innerHTML;
				
				htmlout = htmlout.replace(/&lt;scr/g,"scr");
				htmlout = htmlout.replace(/&lt;/g,"<");
				htmlout = htmlout.replace(/&gt;/g,">");
				htmlout=htmlout.replace(/&amp;/g,"&");
				
				item.innerHTML = htmlout;
			});
			dojo.query("span[data-nodeforchange]", popupContainerNode).forEach(function(item, i){
				item.id=item.id+"_Dialog";
				ariaforchange_span = item.id;
			});
			dojo.query("a[data-ariaforchange]", popupContainerNode).forEach(function(item, i){
				item.setAttribute("aria-describedby",ariaforchange_div+" "+ariaforchange_span);
			});

			dojo.query("a[href_bc_]",popupContainerNode).forEach(function(item,i){
				dojo.setAttr(item, "href",dojo.getAttr(item,"href_bc_"));
				dojo.removeAttr(item,"href_bc_");
			});
	
			var html = popupContainerNode.innerHTML;
			
			
			this.popup.showPopup(this._resourceBundleWs.BLOGS_DIALOG_TITLE);
			var pop = this.popup.setContent(html);
			
			// This node already has anchor links that have been changed to
			// person card. You must remove the 'undefined' and 'undefinedid'
			// before parsing again. Otherwise, the links don't become person cards
			dojo.query(".fn.lotusPerson.hasHover", pop).forEach(function(node){
				node.removeAttribute("icbizcard_idx");
				node.removeAttribute("icbizcard_ref");
				dojo.removeClass(node, "hasHover");
			});
			
			try {
				SemTagSvc.parseDom(null, pop);				
			} catch(e) {}
			dojo.stopEvent(evt);
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
		},
		
		htmlify: function(stringin) {
			var htmlout = stringin.replace(/&lt;scr/g,"scr");
			htmlout = htmlout.replace(/&lt;/g,"<");
			htmlout = htmlout.replace(/&gt;/g,">");
			htmlout=htmlout.replace(/&amp;/g,"&");
			return htmlout;
		},
		
		_getAmountOfEntriesToDisplay: function(){
			var numberEntries = this._optionSet.getItemValue("numberRecentBlogEntries");			
			
			var newView = (numberEntries == "" || numberEntries == null) ? this.DEFAULT_AMOUNT : parseInt(numberEntries);
			
			if (newView == "NaN")
				newView = this.DEFAULT_AMOUNT;
			else	
				newView = (newView > 0 && newView < this.MAX_AMOUNT) ? newView : this.DEFAULT_AMOUNT;
				
			return newView;
		},
		
		
		update: function(){			
			// init number of entried to show
			var newView = this._getAmountOfEntriesToDisplay()
			//if more entries needed to load them again;				
			if(this.entriesPerPage < newView) {
				this.remoteUrl = this._feedUrls.defaultURL + 
									"&lang=" + dojo.locale.replace('-', '_') + 
									"&ps=" + newView;
				this.entriesPerPage = newView;
				this.loadLatestBlogEntriesMode();
			//if show less entries just hidden the extra ones
			} else if(this.entriesPerPage > newView) {
				this.entriesPerPage = newView;
				this._scroller.getModel().resetView(newView, newView);	
				this._scroller.getModel().setAlertThreshold(newView);
			}
			
		},
		
		handleError: function(data, evt){
			this.displayError({
				exceptionToDisplay: data
			});
		},
		
		displayError: function(messageObj){
			// summary: overriden to hide blogs sepcific nodes		   
			this.blogsCentralLoading.style.display = "none";
			this.blogsCentralContainer.style.display = "none";
			
			var handler = this.getErrorHandler();
			handler.setErrorNode(this.errorNode);
			handler.displayError(messageObj);
		},		
		
		MAX_AMOUNT: 25,
		DEFAULT_AMOUNT: 3		
	}	
);
