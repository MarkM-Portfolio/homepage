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

dojo.provide("lconn.homepage.widgets.activities.PublicActivitiesWidget");

dojo.require("lconn.homepage.core.common.Scroller");
dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

dojo.declare(
	// widget name and class
	"lconn.homepage.widgets.activities.PublicActivitiesWidget",
	// superclass
	lconn.homepage.core.base._BodyWidget,
	
	// properties and methods
	{		
		// summary: Dojo widget responsible for rendering the body of the public Activities widget
		// description: See design doc in the req database	
	
		templatePath: dojo.moduleUrl("lconn.homepage", "widgets/activities/templates/publicActivitiesWidget.html"),
		userid: null,

		xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
			xslStrings: {"../publicActivities.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/activities/publicActivities.xsl")}} 		
		})),		
		
		postCreate: function(){	
			// summary: post create initialization
							
			lconn.homepage.widgets.activities.PublicActivitiesWidget.superclass.postCreate.apply(this);
			
			this.userid = this._iContext.getUserProfile().getItemValue("userid");		
			
			this.loadingNode.style.display = "";
			this.remoteUrl = this._feedUrls.publicActivities + "&ps=" + this._getAmountOfEntriesToDisplay();
			this.activityLink.href = this._parameters.mappingRemoteUrl;
			this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();			
			
			this._createModeMenu();
			
			this._initScroller(this._resourceBundle.ACTIVITIES_PAGING_STATUS);
			this.update();
			
			this.retrieveAtomAndUpdate();						
		},	
		
		fetchMore: function(pageIndex){
			pageIndex = parseInt(pageIndex);	
			pageIndex++;				
			
			var url = this.remoteUrl + "&page=" + pageIndex;
			
			var io = this.getIoSupport(this._iContext);
			dojo.connect(io, "handleAsyncRequest", this, "appendNew");					
			
			io.retrieveAtomAndUpdate(url, true);
		},
		
		appendNew: function(data){
			var xmlData = data;
			var result = this._applyXslt(xmlData);
			
			if (result == -1){								
				this._scroller.getModel().unregisterFetchMoreCallback();
			}
			else {					
				this._scroller.getModel().fetchCompleted();
				this._scroller.getModel().incPage();					
			}
		},
		
		_applyXslt: function(data){				
			var html = "";
			var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);

			if ( xslDoc ) {
				html = lconn.core.xslt.transform(data, xslDoc, null, null, true);
			} else {
				html = this.getXsltSupport().getXsltResult(data, null, this._parameters.xsltUrl);
			}
			
			if (html != ""){											
				this.boardingNode.innerHTML = html;
				
				// format dates
				this._formatDatesInContainer(this.boardingNode, "activities-date");
				
				// commented out for SPR DAMC7QBGZX
				//this.removeHTMLTags(this.boardingNode);	
							
				for(var i=0;i<this.boardingNode.childNodes.length;i++) {
					if ( this.boardingNode.childNodes[i].style ) {
						this.boardingNode.childNodes[i].style.display='none';						
					}
				}
				
				this._scroller.getModel().appendContent(this.boardingNode.childNodes);
				this._scroller.getModel().onNextComplete();
				
				while(this.boardingNode.firstChild) {					
					this.contentNode.appendChild(this.boardingNode.firstChild);
				}	
				
				try {
					SemTagSvc.parseDom(null, this.contentNode);
					dojo.parser.parse(this.contentNode);
				} catch (e) {
				}	
				// Bidi support
		      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.contentNode);
			}
			else{
				return -1;
			}								
		},				
		
		postMixInProperties: function(){
			lconn.homepage.widgets.activities.PublicActivitiesWidget.superclass.postMixInProperties.apply(this);			
			this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");			
		},
		
		handleAsyncRequest: function(data, evt) {			
			this.loadingNode.style.display="none";
			this.containerNode.style.display="";
			
			var result = this._applyXslt(data);
			
			if (result == -1){
				this._showWelcomeView();
				this._hideScroller();
			}
			else{
				this._showNormalView();				
				this._scroller.setTotal(this._getTotalNumberEntries(data));				
				this._scroller.getModel().refreshView();
				this._displayScroller();				
			}			
		},
		
		removeHTMLTags: function(/* DOM Node*/ node){
			// summary: Remove the HTML tags (and any content included inside) from node.innerHTML			
			var str = node.innerHTML;		
						
			// remove all tags
			str = str.replace(/&gt;/g,">");
			str = str.replace(/&lt;[a-zA-Z\/][^>]*>/g,"");
						
			str = str.replace(/&amp;[^;]*;/g,"");
			str = str.replace(/&[^;]*;/g,"");	
			
			node.innerHTML = str;
		},
				
		_formatDatesInContainer: function(/* DOM Node */ containerNode, /* string */ CSSMarker){
			// summary: Search for all nodes with CSSMarker as CSS class inside containerNode and 
			//		format dates in the innerHTML of these nodes
			// description: innerHTML of the nodes should only contain a datestamp 
			
			var nodes = dojo.query("." + CSSMarker, containerNode);				
			for(var i=0; i<nodes.length; i++){
				this.getDateFormatterSupport().formatDate(nodes[i]);
			}
		},
				
		_showNormalView: function(){
			this.activityPane.style.display="";
			this._displayScroller();
			this.welcomeNode.style.display="none";			
		},
		
		_showWelcomeView: function(){
			this.activityPane.style.display="none";
			this._displayScroller();
			this.welcomeNode.style.display="";			
		},
		
		_getAmountOfEntriesToDisplay: function(){
			var numberEntries = this._optionSet.getItemValue("publicNumberEntries");			
			
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
			// summary: overriden to hide loadingnode		   
			this.loadingNode.style.display = "none";
			this._hideScroller();	
			
			//lconn.homepage.PublicActivitiesWidget.superclass.displayError.apply(this, arguments);
			var handler = this.getErrorHandler();
			handler.setErrorNode(this.errorNode);
						
			handler.displayError({
				exceptionToDisplay: data
			});			
		},
		
		_resourceBundle: null,	
		
		_childNodes: null,
		_scroller: null,
		// nodes
		scrollerNode: null,
		loadingNode: null,
		contentNode: null,
		welcomeNode: null,
		activityLink: null,	
		activityPane: null,
		boardingNode: null,		
		_data:null,		
		_resourceBundle: null,
		_optionSet: null,
		_scrollHandler: null,
		titleNode: null,
		
		// CONST
		DEFAULT_AMOUNT : 3,
		MAX_AMOUNT: 25
	}
);
