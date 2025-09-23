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

dojo.provide("lconn.homepage.widgets.profiles.MyColleagues");

dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.core.common.Scroller");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");
dojo.require("lconn.core.util._XSLCache");

dojo.require("dijit.form.ComboBox");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.Button");
dojo.require("dojox.data.dom");
dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("dojox.date.posix");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

dojo.declare("lconn.homepage.widgets.profiles.MyColleagues",lconn.homepage.core.base._BodyWidget,
{
	templatePath: dojo.moduleUrl("lconn.homepage", "widgets/profiles/templates/myColleagues.html"),
	_resourceBundle: null,
	tagLabel:null,
	nameLabel:null,
	searchByLabel:null,
	_activitesURLARRAY:null,
	_dogearURLARRAY:null,
	_blogsURLARRAY:null,
	_updatesPosition:null,
	_scroller: null,
	scrollerNode: null,
	_colleaguesLocation:null,
	noColleaguesTitle:null,
	noColleaguesMsg:null,
	goToYourProfilePage:null,
	canHazAct: false,
	canHazBlog: false,
	canHazDog: false,
	titleNode: null,
	loading: "",
	userid: null,
	// The userid of the person whose blog updates you're opening when you 
	// click 'x Blog Updates'
	blogsPopupUserid: null,
	
	xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
		xslStrings: {"profiles.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/profiles/profiles.xsl")},
			"friending.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/profiles/friending.xsl")},
			"../profiles.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/profiles/profiles.xsl")},
			"../friending.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/profiles/friending.xsl")}
		} 		
	})),
	
	postCreate: function() {	
		lconn.homepage.widgets.profiles.MyColleagues.superclass.postCreate.apply(this);
			
		this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();	
		
		this.userid = this._iContext.getUserProfile().getItemValue("userid");		
		
		this._searchURLRoot = this._feedUrls.searchFds;
		this.getErrorHandler().hideError();
		this.loadingNode.style.display = "";
		this.colleaguesLink.setAttribute('href',this._feedUrls.pendingFriendURL);
		
		this._createModeMenu();
		
		this.canHazAct = lconn.homepage.global.services["activities"]["ssl_enabled"] || lconn.homepage.global.services["activities"]["enabled"] ;
		this.canHazBlog = lconn.homepage.global.services["blogs"]["ssl_enabled"] || lconn.homepage.global.services["blogs"]["enabled"] ;
		this.canHazDog = lconn.homepage.global.services["dogear"]["ssl_enabled"] || lconn.homepage.global.services["dogear"]["enabled"] ;
		
		this.myColleaguesNode.style.display = "none";
		this._initScroller(this._resourceBundle.PROFILES_PAGING_STATUS);
		this.myColleaguesNode.innerHTML="";
		this.xsltUrl = this._parameters.xsltUrl;
		this.remoteUrl = this._feedUrls.friendingFds + "userid=" + this.userid +
							"&ps=" + this._getAmountOfEntriesToDisplay();
		this.retrieveAtomAndUpdate();
	},
	
	_getAmountOfEntriesToDisplay: function(){
		var numberEntries = this._optionSet.getItemValue("numberColleaguesEntries");			
		
		var newView = (numberEntries == "" || numberEntries == null) ? this.DEFAULT_AMOUNT : parseInt(numberEntries);
		
		if (newView == "NaN")
			newView = this.DEFAULT_AMOUNT;
		else	
			newView = (newView > 0 && newView < this.MAX_AMOUNT) ? newView : this.DEFAULT_AMOUNT;
			
		return newView;
	},
	
	handleAsyncRequest: function(data, evt) {
		this._displayScroller();
		this.noColleaguesNode.style.display="none";		
		var html="";
		var numColRequests=0;
		var curDateMil = new Date();
		curDateMil.setDate(curDateMil.getDate()-7);
		var xslDoc, xslParams;
		
		try{
			xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
	        xslParams =  [['choice', 'friends'],
								['dateRef',''],
								['photoURL',this._feedUrls.profilePicture],
								['blogURL',this._feedUrls.blogFds],
								['actURL',this._feedUrls.commonActivitiesFds],
								['dogURL',this._feedUrls.dogearFds],
								['tStamp',this._getRecentMillisecondFromOptions()],
								['lang', djConfig.locale],
								['langBlogs', djConfig.locale.replace('-', '_')],
	        					['profilesURL', this._feedUrls.profileServer]];
	        	
	        if(xslDoc)
	        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
			else
				html = this.getXsltSupport().getXsltResult(data,xslParams,this.xsltUrl);
	        
		}catch(e){hp_console_debug(e);}

			
		if(html=="") {
			this.noColleagues();
		} else {
			this.myColleaguesNode.style.display="";
		}
		try{
			xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
			xslParams = [['choice','pending']];
			
	        if(xslDoc)
	        	numColRequests = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
			else
				numColRequests = this.getXsltSupport().getXsltResult(data,xslParams,this.xsltUrl);}catch(e){hp_console_debug(e);
		}
				
		if(numColRequests!=0) {
			var colReqString="";
			if(numColRequests==1) {
				colReqString=dojo.string.substitute(this._resourceBundle.PROFILES_NEW_COLLEAGUE_REQUEST, [numColRequests]);
			} else {
				colReqString=dojo.string.substitute(this._resourceBundle.PROFILES_NEW_COLLEAGUE_REQUESTS, [numColRequests]);
			}
			this.colleagueRequests.innerHTML=colReqString;
			this.colleagueRequestContainer.style.display="";
		} 
		this.myColleaguesNode.innerHTML=html;
		this._updatesPosition=0;
		if(this.canHazAct) {
			this._activitiesURLARRAY=dojo.query(".newActivities",this.myColleaguesNode);
		}
		if(this.canHazDog) {
			this._dogearURLARRAY=dojo.query(".newDogear",this.myColleaguesNode);
		}
		if(this.canHazBlog) {
			this._blogsURLARRAY=dojo.query(".newBlogs",this.myColleaguesNode);
		}
		this.findColleaguesUpdates();
		try{
			SemTagSvc.parseDom(null, this.myColleaguesNode);
		} catch(e) {}				
		this.loadingNode.style.display="none";		
		this._scroller.getModel().appendContent(this.myColleaguesNode.childNodes);
		this.update();
		this._scroller.setTotal(this._getTotalNumberEntries(data));
		this._scroller.getModel().onNextComplete();
		// Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.myColleaguesNode);
	},
	findColleaguesUpdates:function() {
		var urls = [];
		var canGo=false;
		if(this.canHazAct) {
			if(this._updatesPosition>=this._activitiesURLARRAY.length) {

			} else {
				urls[0] = this._activitiesURLARRAY[this._updatesPosition].getAttribute('location');
				canGo=true;
			}
		}
		if(this.canHazDog) {
			if(this._updatesPosition>=this._dogearURLARRAY.length) {

			} else {
				if(this.canHazAct) {
					urls[1] = this._dogearURLARRAY[this._updatesPosition].getAttribute('location');
				} else {
					urls[0] = this._dogearURLARRAY[this._updatesPosition].getAttribute('location');
				}
				canGo=true;
			}
		}
		if(this.canHazBlog) {
			if(this._updatesPosition>=this._blogsURLARRAY.length) {

			} else {
				if(this.canHazAct) {
					if(this.canHazDog) {
						urls[2]=  this._blogsURLARRAY[this._updatesPosition].getAttribute('location');
					} else {
						urls[1]=  this._blogsURLARRAY[this._updatesPosition].getAttribute('location');
					}
				} else {
					if(this.canHazDog) {
						urls[1]=  this._blogsURLARRAY[this._updatesPosition].getAttribute('location');
					} else {
						urls[0]=  this._blogsURLARRAY[this._updatesPosition].getAttribute('location');
					}
				}
				canGo=true;
			}
		}
		if(canGo) {
			var ioSupport = new lconn.homepage.core.base._IOSupport(this._iContext);
		
			dojo.connect(ioSupport , "multipleFeedHandler", this, "handleColleaguesUpdates");
			dojo.connect(ioSupport, "handleError",this,"multipleError");
			try{ioSupport.retrieveMultipleFeeds(urls,false);}catch(e){hp_console_debug("Error"+e);}
		}
	},
	multipleError:function(data,evt) {
		hp_console_debug("error with multple request model");
	},
	handleColleaguesUpdates:function(mFR) {
		var urls = [];
		var results = [];	
		var xslParams, xslDoc, xslResult;
		if(this.canHazAct) {
			urls[0] = this._activitiesURLARRAY[this._updatesPosition].getAttribute('location');
			var actFR = mFR.item(urls[0]);
			if(actFR.status==0) {
				results[0]=0;
			} else {
				xslResult = "";
		        
				xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
		        xslParams = [['choice', 'numActUpdates'],['userid',this._activitiesURLARRAY[this._updatesPosition].getAttribute('userid')]];
		        	
		        if(xslDoc)
		        	xslResult = lconn.core.xslt.transform(actFR.data, xslDoc, null, xslParams, true);
				else
					xslResult = this.getXsltSupport().getXsltResult(actFR.data,xslParams,this.xsltUrl);
		        
				results[0]=parseInt(xslResult);
			}
		}
		if(this.canHazDog) {
			if(this.canHazAct) {
				urls[1] = this._dogearURLARRAY[this._updatesPosition].getAttribute('location');
				var dogFR = mFR.item(urls[1]);
			} else {
				urls[0] = this._dogearURLARRAY[this._updatesPosition].getAttribute('location');
				var dogFR = mFR.item(urls[0]);
			}
			if(dogFR.status==0) {
				results[1]=0;
			} else {
				xslResult = "";
		        
				xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
		        xslParams = [['choice', 'numDogUpdates'],['userid',this._dogearURLARRAY[this._updatesPosition].getAttribute('userid')]];
		        	
		        if(xslDoc)
		        	xslResult = lconn.core.xslt.transform(dogFR.data, xslDoc, null, xslParams, true);
				else
					xslResult = this.getXsltSupport().getXsltResult(dogFR.data,xslParams,this.xsltUrl);

				results[1]=parseInt(xslResult);
			}
		}
		if(this.canHazBlog) {
			if(this.canHazAct) {
				if(this.canHazDog) {
					urls[2]=  this._blogsURLARRAY[this._updatesPosition].getAttribute('location');
					var blogFR = mFR.item(urls[2]);
				} else {
					urls[1]=  this._blogsURLARRAY[this._updatesPosition].getAttribute('location');
					var blogFR = mFR.item(urls[1]);
				}
			} else {
				if(this.canHazDog) {
					urls[1]=  this._blogsURLARRAY[this._updatesPosition].getAttribute('location');
					var blogFR = mFR.item(urls[1]);
				} else {
					urls[0]=  this._blogsURLARRAY[this._updatesPosition].getAttribute('location');
					var blogFR = mFR.item(urls[0]);
				}
			}		
			if(blogFR.status==0) {
				results[2]=0;
			} else {
				var htmlN="";
		        
				xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
		        xslParams = [['choice', 'numBlogUpdates'],['userid',this._blogsURLARRAY[this._updatesPosition].getAttribute('userid')]];
		        	
		        if(xslDoc)
		        	htmlN = lconn.core.xslt.transform(blogFR.data, xslDoc, null, xslParams, true);
				else
					htmlN = this.getXsltSupport().getXsltResult(blogFR.data,xslParams,this.xsltUrl);
				
				
				var nodeHolder=document.createElement("div");
				nodeHolder.innerHTML=htmlN;
				var nodeCount=dojo.query(".blogNodes",nodeHolder);
				results[2]=nodeCount.length;
			}
		}
		
		this.insertUpdates(this._updatesPosition,results);
		this._updatesPosition++;
		this.findColleaguesUpdates();		
	},
	fetchMore:function() {
		var Url = this.remoteUrl+"&page="+this.pageNumber;		
		var io = this.getIoSupport(this._iContext);
		dojo.connect(io, "handleAsyncRequest", this, "appendColleagues");		
		io.retrieveAtomAndUpdate(Url, true);	
	},
	appendColleagues: function(data,evt) {
		var curDateMil = new Date();
		
		curDateMil.setDate(curDateMil.getDate()-7);
				
		try{
			var html;
			
			xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
	        xslParams =  [['choice', 'friends'],
							['dateRef',''],
							['photoURL',this._feedUrls.profilePicture],
							['blogURL',this._feedUrls.blogFds],
							['actURL',this._feedUrls.commonActivitiesFds],
							['dogURL',this._feedUrls.dogearFds],
							['tStamp',this._getRecentMillisecondFromOptions()],
							['lang', djConfig.locale],
							['langBlogs', djConfig.locale.replace('-', '_')],
							['profilesURL', this._feedUrls.profileServer]];
	        	
	        if(xslDoc)
	        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
			else
				html = this.getXsltSupport().getXsltResult(data, xslParams , this.xsltUrl);

		 } catch(E) {hp_console_debug(E);}
		if(html!='') {
			this.pageNumber++;
			this.colleaguesBoardingNode.innerHTML=html;
			for(var i=0;i<this.colleaguesBoardingNode.childNodes.length;i++) {
				var node = this.colleaguesBoardingNode.childNodes[i];
				if(node.nodeType==1) {
					node.style.display='none';						
				}
			}					
				
			try {
				SemTagSvc.parseDom(null, this.colleaguesBoardingNode);
			} catch (e) {
			}			
			this._scroller.getModel().appendContent(this.colleaguesBoardingNode.childNodes);
			this._scroller.getModel().onNextComplete();
				
			while(this.colleaguesBoardingNode.firstChild) {					
				this.myColleaguesNode.appendChild(this.colleaguesBoardingNode.firstChild);
			}
			if(this.canHazAct) {
				this._activitiesURLARRAY=dojo.query(".newActivities",this.myColleaguesNode);
			}
			if(this.canHazDog) {
				this._dogearURLARRAY=dojo.query(".newDogear",this.myColleaguesNode);
			}
			if(this.canHazBlog) {
				this._blogsURLARRAY=dojo.query(".newBlogs",this.myColleaguesNode);
			}			
			this._scroller.setTotal(this._getTotalNumberEntries(data));
			this._scroller.getModel().fetchCompleted();
			this._scroller.getModel().incPage();
			this.findColleaguesUpdates();
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.myColleaguesNode);
		}
	},
	update: function(){			
		// init number of entried to show
		var newView = this._getAmountOfEntriesToDisplay();				
		this._scroller.getModel().resetView(newView, newView);	
	},
	commonActivities:function(evt) {
		this.popup.showPopup(this._resourceBundle.ACTIVITIES_TITLE_POPUP_RECENT);
		var Url = evt.target.getAttribute('location');		
		var io = this.getIoSupport(this._iContext);
		dojo.connect(io, "handleAsyncRequest", this, "showActivities");		
		io.retrieveAtomAndUpdate(Url, true);			
	},
	showActivities:function(data,evt) {
		var html=""
		try{
			var xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
	        var xslParams =  [['choice', 'newActivities'],['tags',this._resourceBundle.COMMON_TAGS]];
	        	
	        if(xslDoc)
	        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
			else
				html = this.getXsltSupport().getXsltResult(data, xslParams ,this.xsltUrl);

			}catch(E){hp_console_debug(E);}
		html=this.htmlify(html);
		var pop = this.popup.setContent(html);
		var dateNodes = dojo.query(".dateSpan",pop);
		for(var i=0;i<dateNodes.length;i++) {
			try {this.getDateFormatterSupport().formatDate(dateNodes[i]);} catch (E) {}
		}
		try {
			SemTagSvc.parseDom(null, pop);				
		} catch(e) {}
		// Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
	},
	commonDogear: function(evt) {
		this.popup.showPopup(this._resourceBundle.BOOKMARKS_TITLE_POPUP_RECENT);
		var Url = evt.target.getAttribute('location');
		var io = this.getIoSupport(this._iContext);
		dojo.connect(io, "handleAsyncRequest", this, "showDogear");		
		io.retrieveAtomAndUpdate(Url, true);
	},
	showDogear: function(data,evt) {
		var html=""
		try {
			var xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
	        var xslParams =  [['choice', 'newDogear'],['tags',this._resourceBundle.COMMON_TAGS]];
	        	
	        if(xslDoc)
	        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
			else
				html = this.getXsltSupport().getXsltResult(data, xslParams,this.xsltUrl);
			} catch (E){hp_console_debug(E);}
		html=this.htmlify(html);
		var pop = this.popup.setContent(html);
		var dateNodes = dojo.query(".dateSpan",pop);
		for(var i=0;i<dateNodes.length;i++) {
			this.getDateFormatterSupport().formatDate(dateNodes[i]);
		}
		try {
			SemTagSvc.parseDom(null, pop);				
		} catch(e) {}
		// Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
	},
	commonBlogs: function(evt) {
		this.popup.showPopup(this._resourceBundle.BLOGS_TITLE_POPUP_RECENT);

		// Save the userid of the person's blog updates you're opening
		this.blogsPopupUserid = evt.target.getAttribute('userid');
		
		var Url = evt.target.getAttribute('location');		
		var io = this.getIoSupport(this._iContext);
		dojo.connect(io, "handleAsyncRequest", this, "showBlogs");		
		io.retrieveAtomAndUpdate(Url, true);
	},
	showBlogs: function(data,evt) {
		var html ="";
		try{
			var xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
	        var xslParams =  [['choice', 'newBlogs'],
							['userid',this.blogsPopupUserid],
							['photoURL',this._feedUrls.profilePicture],
							['blogURL',this._feedUrls.blogFds]];
	        	
	        if(xslDoc)
	        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
			else
				html= this.getXsltSupport().getXsltResult(data, xslParams, this.xsltUrl);
		}catch(E) {hp_console_debug(E);}

			
			
		html=this.htmlify(html);
		var pop = this.popup.setContent(html);
		var dateNodes = dojo.query(".dateSpan",pop);
		for(var i=0;i<dateNodes.length;i++) {
			try {this.getDateFormatterSupport().formatDate(dateNodes[i]);} catch (E) {}
		}
		try {
			SemTagSvc.parseDom(null, pop);				
		} catch(e) {}
		// Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
	},
	noColleagues :function() {
		this._hideScroller();
		this.myColleaguesNode.style.display = "none";
		this.noColleaguesNode.style.display = "";
		this.widgetFooter.style.display = "none";
	},
	handleError: function(data, evt){		   
		this.loadingNode.style.display = "none";
		this.noColleaguesNode.style.display="";
		this._hideScroller();
		var handler = this.getErrorHandler();
		handler.setErrorNode(this.errorNode);
					
		handler.displayError({
			exceptionToDisplay: data
		});			
	},
	postMixInProperties: function(){			
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
		this.goToYourProfilePage=this._resourceBundle.PROFILES_GO_TO_COLLEAGUES;
		this.noColleaguesMsg=this._resourceBundle.PROFILES_NO_COLLEAGUES_MESSAGE;
		this.noColleaguesTitle=this._resourceBundle.PROFILES_NO_COLLEAGUES;
		this._colleaguesLocation=this._feedUrls.pendingFriendURL;
		this.tagLabel = this._resourceBundle.PROFILES_OPTION_TAG;
		this.nameLabel = this._resourceBundle.PROFILES_OPTION_NAME;
		this.searchByLabel=this._resourceBundle.PROFILES_SEARCH_BY;
		this.loading=this._resourceBundle.COMMON_LOADING;
	},
	
	insertUpdates:function(entryNumber,results) {
		if(typeof(this.myColleaguesNode.childNodes[entryNumber])!="undefined") {
			if(this.canHazAct) {
				var act=this._activitiesURLARRAY[entryNumber];
				if(act!=null) {			
					if(results[0]>0) {
						act.className+=act.className? ' lotusAction':'lotusAction';
						act.innerHTML=dojo.string.substitute(this._resourceBundle.PROFILES_ACT_UPDATES_NUMBER, [results[0].toString()]);
						dojo.connect(act,"onclick",this,"commonActivities");
					} else {
						act.style.color="#000";
						act.innerHTML=this._resourceBundle.PROFILES_NO_ACT_UPDATES;
						dojo.addClass(act, "lotusBtnDisabled");
						dojo.attr(act, "aria-disabled", "true");
					}
				}
			}
			if(this.canHazDog) {
				var dog=this._dogearURLARRAY[entryNumber];			
				if(dog!=null) {
					if(results[1]>0) {
						dog.className+=dog.className? ' lotusAction':'lotusAction';
						dog.innerHTML=dojo.string.substitute(this._resourceBundle.PROFILES_DOGEAR_UPDATES_NUMBER, [results[1].toString()]);
						dojo.connect(dog,"onclick",this,"commonDogear");
					} else {
						dog.style.color="#000";
						dog.innerHTML=this._resourceBundle.PROFILES_NO_DOGEAR_UPDATES;
						dojo.addClass(dog, "lotusBtnDisabled");
						dojo.attr(dog, "aria-disabled", "true");
					}
				}
			}
			if(this.canHazBlog) {
				var blog=this._blogsURLARRAY[entryNumber];
				if(blog!=null) {
					if(results[2]>0) {
						blog.className+=blog.className? ' lotusAction':'lotusAction';
						blog.innerHTML=dojo.string.substitute(this._resourceBundle.PROFILES_BLOGS_UPDATES_NUMBER, [results[2].toString()]);
						dojo.connect(blog,"onclick",this,"commonBlogs");
					} else {
						blog.style.color="#000";
						blog.innerHTML=this._resourceBundle.PROFILES_NO_BLOGS_UPDATES;
						dojo.addClass(blog, "lotusBtnDisabled");
						dojo.attr(blog, "aria-disabled", "true");
					}
				}
			}
		}
	},
	htmlify: function(stringin) {
			var htmlout = stringin.replace(/&lt;/g,"<");
			htmlout = htmlout.replace(/&gt;/g,">");
			htmlout=htmlout.replace(/&amp;/g,"&");
			return htmlout;
	},
	
	_getRecentMillisecondFromOptions: function(){			
			var optionIndex = this._optionSet.getItemValue("recent");			
			
			var numberOfDayToRemove = 0;
			
			// show responses posted within the 7 last days
			// format the date 7 days ago as YEARMONTHDAY (ie: 20071106) to pass it to the XSL sheet
			var dateRef = new Date();			
									
			switch (parseInt(optionIndex)){
				case 1:
					// last day
					numberOfDayToRemove = 1;	
				 	break;
				case 2:
					// last 3 days
					numberOfDayToRemove = 3;	
				 	break;
				case 3:
					// last week
					numberOfDayToRemove = 7;	
				 	break;
				case 4:
					// last 2 weeks
					numberOfDayToRemove = 14;	
				 	break;
				case 5:
					// last 2 weeks
					numberOfDayToRemove = 31;	
				 	break;
				default:
					hp_console_debug("should not get here");
			}
						
			dateRef.setDate(dateRef.getDate()-numberOfDayToRemove);				
			// TODO: see if requirements implies to set hour to midnight			
			return dateRef.getTime();			
	},
	MAX_AMOUNT: 25,
	DEFAULT_AMOUNT: 3,
	pageNumber:2
	
});

