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

dojo.provide("lconn.homepage.widgets.profiles.ProfileSearch");

dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.core.common.Scroller");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");

dojo.require("dijit.form.ComboBox");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.Button");
dojo.require("dojox.data.dom");
dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("dojox.date.posix");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

dojo.declare("lconn.homepage.widgets.profiles.ProfileSearch",lconn.homepage.core.base._BodyWidget,
{
	templatePath: dojo.moduleUrl("lconn.homepage", "widgets/profiles/templates/profileSearch.html"),
	_resourceBundle: null,
	tagLabel:null,
	nameLabel:null,
	searchByLabel:null,
	isSearching: false,
	_scroller: null,
	scrollerNode: null,
	_searchType:null,
	_searchValue:null,
	goToYourSearchPage:null,
	noResultsTitle:null,
	loading: "",
	userid: null,
	postCreate: function() {
			lconn.homepage.widgets.profiles.ProfileSearch.superclass.postCreate.apply(this);
		
			if(this._searchValue!=null) {
				this.isSearching=true;
			}
			
			this.userid = this._iContext.getUserProfile().getItemValue("userid");		
			
			this.searchButtonNode.src = dojo.moduleUrl("lconn.homepage.widgets","images/search2.gif");
			this.searchFooterImageNode.src = dojo.moduleUrl("lconn.homepage.widgets","images/profiles_16.gif");			
			this.getErrorHandler().hideError();
			this.profileTitleNode.innerHTML = this._resourceBundle.PROFILES_SEARCH;
			this.xsltUrl = this._parameters.xsltUrl;
			this.SearchLink.setAttribute('href',this._parameters.mappingRemoteUrl);
			
			this.noSearchResults.style.display="none";
			var iWidgetSupport = this.getIWidgetSupport();			
			iWidgetSupport.setIContext(this._iContext);
			iWidgetSupport.setModes(this._modes);
			iWidgetSupport.setMenuNode(this.menuModeButtonNode);
			iWidgetSupport.setAlwaysShowMenu(true);
			iWidgetSupport._buildModeMenu();
			if(this.isSearching==false) {
				this.noSearchPerformed.style.display="";
			} else {
				this.noSearchPerformed.style.display="none";
				this.loadingNode.style.display = "";
				this.textBoxNode.value=this._searchValue;
				this.searchComboNode.selectedIndex=this._searchType;
				this.searchProfilesPage();
			}
	},
	_initScroller: function(){
		this._scroller = new lconn.homepage.core.common.Scroller();			
		var view = this._getAmountOfEntriesToDisplay();				
		this._scroller.getModel().setInitParams(0, view, view);
		this._scroller.getModel().setAlertThreshold(view);
		
		this._scroller.setScrollingStatusTemplateString(this._resourceBundle.PROFILES_SEARCH_PAGING_STATUS);
		
		this.scrollerNode.innerHTML = "";
		this.scrollerNode.style.display = "";
		dojo.place(this._scroller.domNode, this.scrollerNode, "first");		
	},
	
	_getTotalNumberEntries: function(/* XML Doc */ data){
		hp_console_debug(data);
		var total = -1;
		
		try{
			if (dojo.isIE){	
				total = data.getElementsByTagName("opensearch:totalResults")[0].childNodes[0].nodeValue;
			}
			else {
				total = data.documentElement.getElementsByTagNameNS("http://a9.com/-/spec/opensearch/1.1/", "totalResults")[0].childNodes[0].nodeValue;
			}			
		}
		catch (ignoreException){
			;
		}	
			
		return total;		
	},
	
	_displayScroller: function(){
		this.scrollerNode.style.display = "";
	},
	
	_hideScroller: function(){
		this.scrollerNode.style.display = "none";
	},
	
	_getAmountOfEntriesToDisplay: function(){
		var numberEntries = this._optionSet.getItemValue("numberSearchResults");			
		
		var newView = (numberEntries == "" || numberEntries == null) ? this.DEFAULT_AMOUNT : parseInt(numberEntries);
		
		if (newView == "NaN")
			newView = this.DEFAULT_AMOUNT;
		else	
			newView = (newView > 0 && newView < this.MAX_AMOUNT) ? newView : this.DEFAULT_AMOUNT;
			
		return newView;
	},
	handleAsyncRequest: function(data, evt) {
		if(data==null){
			this.noResults();
		}else{
			var html="";
			try {
				html = this.getXsltSupport().getXsltResult(data, [['choice', 'searchResult']],this.xsltUrl);			
			} catch(e){
				hp_console_debug("Error: "+e);
			}
			if(html=="") {
				this.noResults();
			} else {
				
				this.noSearchResults.style.display="none";
				this.loadingNode.style.display="none";
				this.profileSearchNode.innerHTML=html;
				try{
					SemTagSvc.parseDom(null, this.profileSearchNode);
				} catch(e) {}				
				this.loadingNode.style.display="none";
				this.profileSearchNode.style.display="";
				this._scroller.getModel().appendContent(this.profileSearchNode.childNodes);
				this.update();
				this._scroller.getModel().setAlertThreshold(5);
				this._scroller.setTotal(this._getTotalNumberEntries(data));
				this._scroller.getModel().setFetchMoreCallback(this, "moreResults");
				this._scroller.getModel().refreshView();
				this._displayScroller();
				// Bidi support
		      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.profileSearchNode);
			}
		}
	},
	noResults:function() {
		this.loadingNode.style.display="none";
		this._hideScroller();
		this.noResultMessage.innerHTML=dojo.string.substitute(this._resourceBundle.PROFILES_NO_RESULT_MESSAGE,[this.textBoxNode.value]);
		this.noSearchResults.style.display="";
	},
	moreResults:function() {
		var Url = this.remoteUrl+"&page="+this.pageNumber;		
		var io = this.getIoSupport();
		dojo.connect(io, "handleAsyncRequest", this, "appendResults");		
		io.retrieveAtomAndUpdate(Url, true);	
	},
	appendResults: function(data,evt) {
		var html = this.getXsltSupport().getXsltResult(data, [['choice', 'searchResult']], this.xsltUrl);
		if(html!='') {
			this.pageNumber++;
			this.boardingNode.innerHTML=html;
			for(var i=0;i<this.boardingNode.childNodes.length;i++) {
				var node = this.boardingNode.childNodes[i];
				if(node.nodeType==1) {
					node.style.display='none';						
				}
			}					
				
			try {
				SemTagSvc.parseDom(null, this.boardingNode);
			} catch (e) {
			}			
			this._scroller.getModel().appendContent(this.boardingNode.childNodes);
				
			while(this.boardingNode.firstChild) {					
				this.profileSearchNode.appendChild(this.boardingNode.firstChild);
			}			
			this._scroller.setTotal(this._getTotalNumberEntries(data));
			this._scroller.getModel().fetchCompleted();
			this._scroller.getModel().incPage();
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.profileSearchNode);
		}		
	},
	update: function(){			
		// init number of entried to show
		var newView = this._getAmountOfEntriesToDisplay();				
		this._scroller.getModel().resetView(newView, newView);	
		this._scroller.getModel().setAlertThreshold(newView);	
	},
	postMixInProperties: function(){			
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
		this.goToYourSearchPage = this._resourceBundle.PROFILES_GO_TO_PROFILE_SEARCH;
		this.noResultsTitle =this._resourceBundle.PROFILES_NO_RESULT_TITLE;
		this.tagLabel = this._resourceBundle.PROFILES_TAG;
		this.nameLabel = this._resourceBundle.PROFILES_NAME;
		this.searchByLabel=this._resourceBundle.PROFILES_SEARCH_BY;
		this.loading=this._resourceBundle.COMMON_LOADING;
	},
	
	searchProfilesPage:function() {
		this.profileSearchNode.style.display="none";
		this._hideScroller();
		this._initScroller();
		switch(this.searchComboNode.value) {
			case 'Name':
				this.remoteUrl=this._feedUrls.searchFds+"name="+encodeURI(this.textBoxNode.value);
				break;
			case 'Tag':
				this.remoteUrl=this._feedUrls.searchFds+"profileTags="+encodeURI(this.textBoxNode.value);
				break;
		}
		this.noSearchPerformed.style.display="none";
		this.loadingNode.style.display="";
		this.retrieveAtomAndUpdate();
	},	
	MAX_AMOUNT: 25,
	DEFAULT_AMOUNT: 3,
	pageNumber:2
});
