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



dojo.provide("lconn.homepage.widgets.files.FilesWidget");

dojo.require("lconn.core.utilities");
dojo.require("lconn.homepage.core.common.Scroller");
dojo.require("lconn.homepage.widgets.activities.ActivitiesCalendar");
dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");
dojo.require("lconn.core.util._XSLCache");


dojo.require("dojo.string");
dojo.require("dojo.date.locale");

dojo.require("dojo.number");
dojo.require("dojox.date.posix");
dojo.require("dojox.data.dom");
dojo.require("dojo._base.html");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

dojo.declare("lconn.homepage.widgets.files.FilesWidget",lconn.homepage.core.base._BodyWidget, {
		_resourceBundle: null,
		// Nodes that are tables and show the entry info
		childNodes: null,
		// nodes
		content: null,		
		filesWidgetTitle: '',
		_optionSet: null,
		_iContext: null,
		_scroller: null,
		data: null,		
		scrollerNode: null,				
		noFilesEntry1: "",
		noFilesEntry2: "",
		noFilesEntry3: "",
		loading: "",
		templatePath: dojo.moduleUrl("lconn.homepage", "widgets/files/templates/filesWidget.html"),
		
		xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
			xslStrings: {"files.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/files/files.xsl")}} 		
		})),

		pageNumber:1,
		userid: null,
		postCreate: function(){
			lconn.homepage.widgets.files.FilesWidget.superclass.postCreate.apply(this);
				
			this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();
			
			this.userid = this._iContext.getUserProfile().getItemValue("userid");		
			
			this.filesCentralLoading.style.display="";
	
			this.filesUploadLink.setAttribute("href", this._parameters.uploadURL);


			this.remoteUrl = this._feedUrls.defaultURL;									

			this.filesLink.setAttribute("href", this._parameters.mappingRemoteUrl);	
			
			// Only show the "Follow this link below to add files to your share" string
			// when we're in MyFiles mode.
			if(this._modes == "MyFiles"){
				this.noFilesEntry2Point.innerHTML = this.noFilesEntry2;
			}
		},		
		
		loadLatestFilesEntriesMode: function(){
			this.pageNumber=1;
			this.retrieveAtomAndUpdate();			
		},	
			
		postMixInProperties: function(){			
			
			var title = this._modes.toUpperCase() + "_TITLE";
			
			this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
			this.noFilesEntry1 = this._resourceBundle.FILES_NBE1;				
			this.noFilesEntry2 = this._resourceBundle.FILES_NBE2;
			this.noFilesEntry3 = this._resourceBundle.FILES_NBE3;
			this.loading=this._resourceBundle.COMMON_LOADING;
			this.uploadLinkText=this._resourceBundle.FILES_UPLOAD_LINK;
		},
		
		handleAsyncRequest: function(data, evt){
			if(data!=null) {			
				var html = "";					
				var isLTR = "";
				if(dojo._isBodyLtr()){
					isLTR = "true";
				}
				else{
					 isLTR = "false";
				}
				
				var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
				var xslParams = [['more', this._resourceBundle.COMMON_MORE],
									['tags', this._resourceBundle.COMMON_TAGS],
//									['userid', this.userid],
									['noRecommendAlt', this._resourceBundle.FILES_NO_RECOMMEND_ALT],
									['recommendAlt', this._resourceBundle.FILES_RECOMMEND_ALT],
									['fullRecommendAlt', this._resourceBundle.FILES_FULL_RECOMMEND_ALT],
									['noCommentsAlt', this._resourceBundle.FILES_NO_COMMENTS_ALT],
									['commentsAlt', this._resourceBundle.FILES_COMMENTS_ALT],
									['downloadAlt',this._resourceBundle.FILES_DOWNLOAD_ALT],
									['downloadText',this._resourceBundle.FILES_DOWNLOAD_TEXT],											
									['addedText', this._resourceBundle.FILES_ADDED],
									['modifiedText', this._resourceBundle.FILES_MODIFIED],
									['publicIconAlt', this._resourceBundle.FILES_PUBLIC],
									['privateIconAlt', this._resourceBundle.FILES_PRIVATE],
									['sharedIconAlt', this._resourceBundle.FILES_SHARED_WITH],
									['downloadIconAlt', this._resourceBundle.FILES_DOWNLOAD_ICON_ALT],
									['blankGifPath', this._blankGif],
									['filesMode', this._modes],
									['isLTR', isLTR]];
				if(xslDoc)
					html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
				else
					html = this.getXsltSupport().getXsltResult(data, xslParams,  this._parameters.xsltUrl);
				
				if(html!= "") {		
					this._initScroller(this._resourceBundle.FILES_PAGING_STATUS);								
					this.update();
					this.tempNode.innerHTML = html;
					this.formatDownloadAria(this.tempNode);

					var totalNode = dojo.query(".totalEntries", this.tempNode);
					var total = totalNode[0].innerHTML;
					dojo.query(".totalEntries", this.tempNode).orphan();
					html = this.tempNode.innerHTML;
					this.tempNode.innerHTML="";

					this.setContent(html);
					if(total == 0){
						this.filesCentralLoading.style.display="none";
						this.welcomeNode.style.display="";
					}
					else{						
						if (total != -1)
							this._scroller.setTotal(total);
						this.welcomeNode.style.display="none";
						this.filesCentralLoading.style.display="none";
						this.filesCentralContainer.style.display="";
						this.filesCentralNode.style.display="";
					}
				} else {
					this.filesCentralLoading.style.display="none";
					this.welcomeNode.style.display="";
				}						
			}
		},
		
		setupWidget: function(opts){
			this._hideScroller();		
			this.getErrorHandler().hideError();
			this.filesCentralLoading.style.display="";
			this.welcomeNode.style.display="none";
			this.filesCentralContainer.style.display="none";
			this.filesWidgetTitle.innerHTML=opts.title;
			
			this._initScroller(this._resourceBundle.FILES_PAGING_STATUS);
			
			this.pageNumber = 1;
			
			this.remoteUrl = opts.url;
			this.retrieveAtomAndUpdate();	
		},
		
		SharedFiles: function(title) {
			this.setupWidget({
				title: title,
				url: this._feedUrls.sharedFilesURL + "?lang=" + dojo.locale +
						"&ps=" + this._getAmountOfEntriesToDisplay()
			});
		},
		
		MyFiles: function(title) {
			this.setupWidget({
				title: title,
				url: this._feedUrls.myFilesURL + "?lang=" + dojo.locale +
						"&ps=" + this._getAmountOfEntriesToDisplay()
			});
	
		},		
		
		/** No longer in use by the looks of things */
    	PopularFiles: function(title) {
			this._hideScroller();		
			this.getErrorHandler().hideError();
			this.filesCentralLoading.style.display="";
			this.welcomeNode.style.display="none";
			this.filesCentralContainer.style.display="none";
			this.filesWidgetTitle.innerHTML=title;
			
			this._initScroller(this._resourceBundle.FILES_PAGING_STATUS);
			this.pageNumber =1;

			this.remoteUrl = this._feedUrls.popularURL + "&lang=" + dojo.locale+"&page="+this.pageNumber;//hard coded at present
			this.retrieveAtomAndUpdate();				
			this.remoteUrl = this._feedUrls.popularURL;//reset for next fetch
		},
		
		_hideScroller: function(){
			this.scrollerNode.style.display = "none";
		},
		
		fetchMore: function() {
			this.pageNumber++;
			var Url = this.remoteUrl+"&page="+this.pageNumber;		
			var io = this.getIoSupport(this._iContext);
			dojo.connect(io, "handleAsyncRequest", this, "updateFiles");		
			io.retrieveAtomAndUpdate(Url, true);
			
		},
		
		updateFiles: function(data,evt){
			if(data!=null) {
				var html = "";					
				var isLTR = "";
				if(dojo._isBodyLtr()){
					isLTR = "true";
				}
				else{
					 isLTR = "false";
				}
				var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
				var xslParams = [['more', this._resourceBundle.COMMON_MORE],
									['tags', this._resourceBundle.COMMON_TAGS],
									//['userid', this.userid],
									['noRecommendAlt', this._resourceBundle.FILES_NO_RECOMMEND_ALT],
									['recommendAlt', this._resourceBundle.FILES_RECOMMEND_ALT],
									['fullRecommendAlt', this._resourceBundle.FILES_FULL_RECOMMEND_ALT],
									['noCommentsAlt', this._resourceBundle.FILES_NO_COMMENTS_ALT],
									['commentsAlt', this._resourceBundle.FILES_COMMENTS_ALT],
									['downloadText',this._resourceBundle.FILES_DOWNLOAD_TEXT],											
									['downloadAlt',this._resourceBundle.FILES_DOWNLOAD_ALT],										
									['addedText', this._resourceBundle.FILES_ADDED],
									['modifiedText', this._resourceBundle.FILES_MODIFIED],
									['publicIconAlt', this._resourceBundle.FILES_PUBLIC],
									['privateIconAlt', this._resourceBundle.FILES_PRIVATE],
									['sharedIconAlt', this._resourceBundle.FILES_SHARED_WITH],
									['downloadIconAlt', this._resourceBundle.FILES_DOWNLOAD_ICON_ALT],
									['blankGifPath', this._blankGif],
									['filesMode', this._modes],
									['isLTR', isLTR]];
				if(xslDoc)
					html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
				else
					html = this.getXsltSupport().getXsltResult(data, xslParams, this._parameters.xsltUrl);

		
				if(html!= "") {	
								
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

					var nodes = dojo.query(".fileDate", this.boardingNode);
					for(var i=0; i<nodes.length; i++){
						var modified = dojo.query(".modified", nodes[i]);
						var created =  dojo.query(".created", nodes[i]);
						var modDate = modified[0].innerHTML instanceof Date ? modified[0].innerHTML : dojo.date.stamp.fromISOString(modified[0].innerHTML);
						var createDate = created[0].innerHTML instanceof Date ? created[0].innerHTML : dojo.date.stamp.fromISOString(created[0].innerHTML);
						var result = dojo.date.compare(createDate, modDate);
						if(result == 0){
							created[0].style.display = "";
							this.getDateFormatterSupport().formatDate(created[0]);
							var newDate = created[0].innerHTML;
							newDate = (this._modes != "SharedFiles") ? this._resourceBundle.FILES_ADDED + newDate : this._resourceBundle.FILES_SHARED  + newDate ;
							created[0].innerHTML = newDate;

						}
						else if(result >0){
							created[0].style.display= "";
							this.getDateFormatterSupport().formatDate(created[0]);
							var newDate = created[0].innerHTML;
							newDate = (this._modes != "SharedFiles") ? this._resourceBundle.FILES_ADDED + newDate : this._resourceBundle.FILES_SHARED  + newDate ;							
//							newDate = this._resourceBundle.FILES_ADDED  + newDate;
							created[0].innerHTML = newDate;

						}
						else{
							if(createDate == null){
								created[0].style.display="";
							}
							modified[0].style.display= "";
							this.getDateFormatterSupport().formatDate(modified[0]);
							var newDate = modified[0].innerHTML;
							newDate = (this._modes != "SharedFiles") ? this._resourceBundle.FILES_ADDED + newDate : this._resourceBundle.FILES_MODIFIED  + newDate ;
//							newDate = this._resourceBundle.FILES_MODIFIED + newDate;
							modified[0].innerHTML = newDate;
						}
				
					}
					
					this.formatDownloadAria(this.boardingNode);
					hp_console_debug("updateFiles [quickrIcon]");
					var nodes = dojo.query(".quickrIcon", this.boardingNode);
					var iconURL	= this._feedUrls.imageURL;
					
					for(var i=0; i<nodes.length; i++){
						hp_console_debug("lconn.homepage.widgets.files.FilesWidget updateFiles - getFileIconClassName: %o", lconn.core.utilities.getFileIconClassName(nodes[i].alt, 32));
						nodes[i].className = lconn.core.utilities.getFileIconClassName(nodes[i].alt, 32);
					}		

					for(var i=0;i<this.boardingNode.childNodes.length;i++) {
						//skip over text nodes, IE9 issue
						if(this.boardingNode.childNodes[i].nodeType != 3){
							this.boardingNode.childNodes[i].style.display='none';	
						}
					}										
					if (total != -1)
						this._scroller.setTotal(total);
					this._scroller.getModel().appendContent(this.boardingNode.childNodes);
					this._scroller.getModel().onNextComplete();
					this._scroller.getModel().fetchCompleted();
					this._scroller.getModel().incPage();
					
					var len = 0;
					 for(len = this.boardingNode.childNodes.length; len>0; len--) {					
						this.filesCentralNode.appendChild(this.boardingNode.firstChild);
					}
					try {
						SemTagSvc.parseDom(null, this.filesCentralNode);
						dojo.parser.parse(this.filesCentralNode);
					} catch(e) {}
					
					// Bidi support
			      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.filesCentralNode);
				}
			}
		},
		
		setContent: function(html){
			if(html=="") {
				this.welcomeNode.display="none";
				return;
			} else {
				
				this.filesCentralNode.innerHTML = html;

				try {
					SemTagSvc.parseDom(null, this.filesCentralNode);		
					dojo.parser.parse(this.filesCentralNode);
				} catch(e) {}		
			}			
			this.formatNodes();			
			this.addListners();
			this.addQuickrIcons();
			this.formatFileSize();
			this.formatDownloadLink();			
			this._scroller.getModel().appendContent(this.filesCentralNode.childNodes);			
			this._scroller.getModel().refreshView();
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.filesCentralNode);
		},
		
		addListners: function(){			
			var nodes = dojo.query(".showContent", this.filesCentralNode);
			for(var i=0; i<nodes.length; i++){
				dojo.connect(nodes[i],"onclick",this, "displayEntry");
			}
		},
		
		formatNodes: function() {			
			// to clean up the html code
			var nodes = dojo.query(".content", this.filesCentralNode);
			for(var i=0; i<nodes.length; i++) {
				this.removeHTMLTags(nodes[i]);
			}

			var nodes = dojo.query(".fileDate", this.filesCentralNode);
			for(var i=0; i<nodes.length; i++){
				var modified =null;
				var created = null;
				modified = dojo.query(".modified", nodes[i]);
				created =  dojo.query(".created", nodes[i]);
				var modDate = modified[0].innerHTML instanceof Date ? modified[0].innerHTML : dojo.date.stamp.fromISOString(modified[0].innerHTML);
				var createDate = created[0].innerHTML instanceof Date ? created[0].innerHTML : dojo.date.stamp.fromISOString(created[0].innerHTML);
				var result = dojo.date.compare(createDate, modDate);
				if(result == 0){
					created[0].style.display = "";
					this.getDateFormatterSupport().formatDate(created[0]);
					var newDate = created[0].innerHTML;
					newDate = (this._modes != "SharedFiles") ? this._resourceBundle.FILES_ADDED + newDate : this._resourceBundle.FILES_SHARED  + newDate ;
//					newDate = this._resourceBundle.FILES_ADDED + newDate;
					created[0].innerHTML = newDate;

				}
				else if(result >0){
					created[0].style.display= "";
					this.getDateFormatterSupport().formatDate(created[0]);
					var newDate = created[0].innerHTML;
					newDate = (this._modes != "SharedFiles") ? this._resourceBundle.FILES_ADDED + newDate : this._resourceBundle.FILES_SHARED  + newDate ;
	//				newDate = this._resourceBundle.FILES_ADDED  + newDate;
					created[0].innerHTML = newDate;

				}
				else{
					if(createDate == null){
						created[0].style.display= "";
					}
					modified[0].style.display= "";
					this.getDateFormatterSupport().formatDate(modified[0]);
					var newDate = modified[0].innerHTML;
					newDate = (this._modes != "SharedFiles") ? this._resourceBundle.FILES_ADDED + newDate : this._resourceBundle.FILES_MODIFIED  + newDate ;
					//newDate = this._resourceBundle.FILES_MODIFIED + newDate;
					modified[0].innerHTML = newDate;
				}
				
			}

		},
		
		//adding internationalization stuff
		internationalization: function(){			
			var nodes = dojo.query(".published", this.filesCentralNode);
			for(var i=0; i<nodes.length; i++){
				var date = nodes[i].innerHTML;
				var newDate = dojo.string.substitute(this._resourceBundle.PUBLISHED, [date]);
				 nodes[i].innerHTML = newDate;
			}
		},
		//attach quickr icons to the 
		addQuickrIcons: function(){
			hp_console_debug("addQuickrIcons []");	
			var iconURL	= this._feedUrls.imageURL;
			var nodes = dojo.query(".quickrIcon", this.filesCentralNode);
			for(var i=0; i<nodes.length; i++){
				hp_console_debug("lconn.homepage.widgets.files.FilesWidget addQuickrIcons - getFileIconClassName: %o", lconn.core.utilities.getFileIconClassName(nodes[i].alt, 32));
				nodes[i].className = lconn.core.utilities.getFileIconClassName(nodes[i].alt, 32);
			}		
		},
		formatFileSize: function(){
			var nodes = dojo.query(".fileSize", this.filesCentralNode);
			for(var i =0; i<nodes.length; i++){
				if(this._modes == "SharedFiles"){
					nodes[i].innerHTML = "";
				}
				else{
					nodes[i].innerHTML = this.calculateFileSize(nodes[i].innerHTML);
				}
//				nodes[i].style.display = "";
			}
		
		
		},	
		formatDownloadLink: function(){
			var nodes = dojo.query(".entryBody", this.boardingNode);
			for(var i =0; i<nodes.length; i++){
				var downloadText = nodes[i].childNodes[1].name;
				var size = nodes[i].childNodes[1].innerHTML;
				var filelink = nodes[i].childNodes[1].href;
				var slashIndex = filelink.lastIndexOf('/');
				var questionIndex = filelink.lastIndexOf('?');
				var newFilename = filelink.substring(slashIndex+1,questionIndex);
				if(this._modes == "SharedFiles"){
					size = "";
				}
//				nodes[i].childNodes[0].firstChild.innerHTML += size;
				nodes[i].childNodes[0].firstChild.title = dojo.string.substitute(downloadText, {filename: newFilename, filesize:size});
			}			
		},			
		calculateFileSize: function(/*Number in bytes*/ size){
			var i = 0;
			var sizeLetters = new Array(this._resourceBundle.FILES_KB,this._resourceBundle.FILES_MB,this._resourceBundle.FILES_GB);
			if(size < 1024){
				if(size != 0){
					//size = dojo.number.format((size / 1024),{places:1});
					size = dojo.string.substitute(this._resourceBundle.FILES_B, [size]);
				}
				else{
					size = dojo.string.substitute(this._resourceBundle.FILES_B, [0]);
				}
			}
			else{
				while(size >=1.0){
					size = size/1024;
					i++;				
				}
				var sizeString = sizeLetters[i-2];
				size = dojo.number.format((size * 1024),{places:1});
				size = dojo.string.substitute(sizeString, [size]);
				
			}
			size = "("+ size+")";

			return size;
			
		
		
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
			
			this.popup.showPopup(this._resourceBundle.FILES_DIALOG_TITLE);
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
			var numberEntries = this._optionSet.getItemValue("numberRecentFilesEntries");			
			
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
			//this._scroller.getModel().setAlertThreshold(newView);
		},
		
		handleError: function(data, evt){
			this.displayError({
				exceptionToDisplay: data
			});
		},
		
		displayError: function(messageObj){
			// summary: overriden to hide blogs sepcific nodes		   
			this.filesCentralLoading.style.display = "none";
			this.filesCentralContainer.style.display = "none";
			
			var handler = this.getErrorHandler();
			handler.setErrorNode(this.errorNode);
			handler.displayError(messageObj);
		},		

		formatDownloadAria: function(parentNode) {
			var self = this;
			
			dojo.query(".filesDownloadIcon", parentNode).forEach(function(node) {
				dojo.attr(node, "aria-label", dojo.string.substitute(self._resourceBundle.FILES_DOWNLOAD_ICON_ARIA, [node.alt]));
			});
		},

		MAX_AMOUNT: 25,
		DEFAULT_AMOUNT: 3		
	}	
);
