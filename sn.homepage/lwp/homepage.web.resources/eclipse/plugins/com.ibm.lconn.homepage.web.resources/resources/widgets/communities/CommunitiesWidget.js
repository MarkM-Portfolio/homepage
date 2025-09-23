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


dojo.provide("lconn.homepage.widgets.communities.CommunitiesWidget");

dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.core.base._IOSupport");
dojo.require("lconn.homepage.core.common.Scroller");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");
dojo.require("lconn.core.util._XSLCache");

dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("dojox.date.posix");
dojo.require("dojox.data.dom");
dojo.require("lconn.core.xslt");

dojo.requireLocalization("lconn.homepage", "widgetstrings");
dojo.requireLocalization("lconn.homepage", "jsp");

dojo.declare("lconn.homepage.widgets.communities.CommunitiesWidget", lconn.homepage.core.base._BodyWidget, {
    templatePath: dojo.moduleUrl("lconn.homepage", "widgets/communities/templates/communitiesWidget.html"),
    isContainer: true,
    communityTitle: "",
    noCommunityTitle: "",
    noCommMsg1: "",
    noCommMsg2: "",
    goToYourCommPage: "",
    loading: "",
    userid: null,
	entriesPerPage: 3,
    publicCount:-1,
	xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
		xslStrings: {"communities.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/communities/communities.xsl")},
			"../communities.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/communities/communities.xsl")}} 		
	})),
	
	
    postCreate: function(){
        lconn.homepage.widgets.communities.CommunitiesWidget.superclass.postCreate.apply(this);
        
        this.userid = this._iContext.getUserProfile().getItemValue("userid");
        
        this.communityLink.href = this._parameters.mappingRemoteUrl;
        this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();
        
        this.entriesPerPage = this._getAmountOfEntriesToDisplay();
        
        this._createModeMenu();
    },
    
    _hideTitleNode: function(title) {
		if(title=="")
			this.titleNode.style.display="none";
		else
			this.titleNode.style.display="";
	},
	
	/**
	 * Setups each of the bookmark widgets
	 * @param opts - Object {title: "", url: ""}
	 */ 
	setupWidget: function(opts){
		this.getErrorHandler().hideError();
        this.communityWelcome.style.display = "none";
        this.communityLoading.style.display = "";
        this._displayScroller();
        
        this._initScroller(this._resourceBundleWs.COMMUNITIES_PAGING_STATUS);
        this.update();
        this.reset();

        this.remoteUrl = opts.url;
        if (this.titleNode.innerHTML != "") 
            this.communityTitle.innerHTML = opts.title;
        
        this._hideTitleNode(opts.title);
        
        this.retrieveAtomAndUpdate();
    },

    updatePublicCount: function(data,evt) {
        try{
            var xml = lconn.core.xslt.loadXmlString(data);
            this.publicCount = this._getTotalNumberEntries(xml);
        }
        catch(e){
            console.error("Problem getting Public Community count.");
        }
    },

    MyCommunity: function(title){
        this.publicCount = -1;
        this.setupWidget({
            title: title,
            url: this._feedUrls.defaultURL + "&ps=" + this._getAmountOfEntriesToDisplay()
        });
    },
    
    PublicCommunity: function(title){
        this.publicCount = -1;

        if (this._feedUrls.publicCountURL){
            this._IOSupport = new lconn.homepage.core.base._IOSupport(this._iContext);
            var url = this._feedUrls.publicCountURL + "&forceRefresh=" + (new Date()).valueOf();
            var bindArgs = {
                url: url,
                handleAs: "text",
                expectedContentType: "xml"
            };
            var req = this._IOSupport.xhrGet(bindArgs);
            req.addCallback(this, "updatePublicCount");
        }

        this.setupWidget({
            title: title,
            url: this._feedUrls.publicURL + "&ps=" + this._getAmountOfEntriesToDisplay()
        });
    },
    
    WelcomeMode: function(){
        this.getErrorHandler().hideError();
        
        this._hideScroller();
        this.commNode.innerHTML = "";
        //SPR #DMCE86WQYX        this.titleNode.style.display="none";
        this.commNode.style.display = "none";
        this.communityWelcome.style.display = "";
    },
    
    reset: function(){
        this.commNode.innerHTML = "";
        // reset scroller aswell?
    },
    
    postMixInProperties: function(){
        this._resourceBundleJsp = dojo.i18n.getLocalization("lconn.homepage", "jsp");
        this._resourceBundleWs = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
        this.communityTitle = this._resourceBundleWs.COMMUNITIES_TITLE;
        this.noCommunityTitle = this._resourceBundleWs.COMMUNITIES_NO_COMMUNITY_TITLE;
        this.noCommMsg1 = this._resourceBundleWs.COMMUNITIES_NO_COMMUNITY_MSG_1;
        this.noCommMsg2 = this._resourceBundleWs.COMMUNITIES_NO_COMMUNITY_MSG_2;
        this.goToYourCommPage = this._resourceBundleWs.COMMUNITIES_GO_TO_COMMUNITIES;
        this.loading = this._resourceBundleWs.COMMON_LOADING;
    },
    
    fetchMore: function(pageIndex){
        this.updateContents(pageIndex);
    },
    
    updateContents: function(pageIndex){
        var io = this.getIoSupport(this._iContext);
        
        var url = this.remoteUrl + "&page=" + (pageIndex + 1);
        dojo.connect(io, "handleAsyncRequest", this, "appendNew");
        dojo.connect(io, "handleError", this, "handleError");
        io.retrieveAtomAndUpdate(url, true);
    },
    
    appendNew: function(data, evt){
        this._data = data;
        this.appendCommunityTicker();
    },
    
    handleAsyncRequest: function(data, evt){
        this.communityWelcome.style.display = "none";
        this._data = data;
        this.communityLoading.style.display = "none";
        this.updateCommunityTicker();
    },
    
    _applyXSLT: function(){
        var html = "";        
        
        var forumsEnabled = ((typeof lconn.homepage.global.services.forums != "undefined") && (lconn.homepage.global.services.forums.enabled == true || lconn.homepage.global.services.forums.ssl_enabled == true));
        
        try {
    		var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
            var xslParams = [['feed', this._resourceBundleWs.COMMUNITIES_FEEDS], 
                             ['tags', this._resourceBundleWs.COMMON_TAGS], 
                             ['bookmark', this._resourceBundleWs.COMMUNITIES_BOOKMARKS], 
                             ['forum', this._resourceBundleWs.COMMUNITIES_FORUM], 
                             ['logoAlt', this._resourceBundleWs.COMMUNITIES_LOGO_ALT], 
                             ['forumsEnabled', forumsEnabled],
                             ['commTitle', this._resourceBundleJsp["jsp.common.service.name.communities"]]];
            	
            if(xslDoc)
            	html = lconn.core.xslt.transform(this._data, xslDoc, null, xslParams, true);
    		else
                html = this.getXsltSupport().getXsltResult(this._data, xslParams , this._parameters.xsltUrl);        	
        } 
        catch (e) {
        	hp_console_debug(e);
        }
        return html;
    },
    
    updateCommunityTicker: function(){
        var html = this._applyXSLT();
        
        if (html != "") {
            this._setContent(html);
            this.updateCommunityTickerTotalCount();
        }
        else {
            this.WelcomeMode();
        }
    },
    
    appendCommunityTicker: function(){
        var html = this._applyXSLT();
        if (html != "") {
            this._appendContent(html);
            this._scroller.getModel().fetchCompleted();
            this._scroller.getModel().incPage();
            this.updateCommunityTickerTotalCount();
        }
        else {
            this._scroller.getModel().unregisterFetchMoreCallback();
        }
    },

    updateCommunityTickerTotalCount: function(){
        if (this.publicCount !== -1) {
            this._scroller.setTotal(this.publicCount);
        } else {
            this._scroller.setTotal(this._getTotalNumberEntries(this._data));
        }
    },
    
    _formatHtml: function(html){
        this.boardingNode.innerHTML = html;
        var feedNodes = dojo.query(".communityFeedLink", this.boardingNode);
        for (var i = 0; i < feedNodes.length; i++) {
            dojo.connect(feedNodes[i], 'onclick', this, "fetchFeeds");
        }
        var bookNodes = dojo.query(".communityBookmarkLink", this.boardingNode);
        for (var i = 0; i < bookNodes.length; i++) {
            dojo.connect(bookNodes[i], 'onclick', this, "fetchBookmarks");
        }
        var forumNodes = dojo.query(".communityForumLink", this.boardingNode);
        for (var i = 0; i < forumNodes.length; i++) {
            dojo.connect(forumNodes[i], 'onclick', this, "fetchForums");
        }
        var dateNodes = dojo.query(".dateSpan", this.boardingNode);
        for (var i = 0; i < dateNodes.length; i++) {
            this.getDateFormatterSupport().formatDate(dateNodes[i]);
        }
        
        this._scroller.getModel().appendContent(this.boardingNode.childNodes);
        this._scroller.getModel().onNextComplete();
        
        while (this.boardingNode.firstChild) {
            this.commNode.appendChild(this.boardingNode.firstChild);
        }
        
        try {
            SemTagSvc.parseDom(null, this.commNode);
            dojo.parser.parse(this.commNode);
        } 
        catch (e) {
        }
        
        this.commNode.style.display = "";
        // Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.commNode);
    },
    
    _setContent: function(html){
        this._formatHtml(html);
    },
    
    _appendContent: function(html){
        this._formatHtml(html);
    },
    fetchFeeds: function(evt){
        var url = evt.target.getAttribute('location');
        var title = evt.target.getAttribute('title');
        this.popup.showPopup(title);
        var io = this.getIoSupport(this._iContext);
        dojo.connect(io, "handleAsyncRequest", this, "showFeeds");
        io.retrieveAtomAndUpdate(url, true);
    },
    showFeeds: function(data){
        var xmlData = data;
        var xsltUrl = this.getXsltUrl("xslt/communityFeeds.xsl");
        var feedImg = dojo.moduleUrl("lconn.dboard", "images/iconFeed.gif");
        var html = "";
        html = this.getXsltSupport().getXsltResult(data, 
        		[['nofeeds', this._resourceBundleWs.COMMUNITIES_NO_FEEDS_TITLE], 
        		 ['nodescription1', this._resourceBundleWs.COMMUNITIES_NO_FEEDS_EXPLANATION1], 
        		 ['nodescription2', this._resourceBundleWs.COMMUNITIES_NO_FEEDS_EXPLANATION2], 
        		 ['tags', this._resourceBundleWs.COMMON_TAGS],
        		 ['blankGif', this._blankGif],
                 ['ariaTag', this._resourceBundleWs.ARIA_TAG],
                 ['ariaTagDescription', this._resourceBundleWs.ARIA_TAG_DESCRIPTION],
                 ['commFeedsTitle', this._resourceBundleJsp["jsp.common.service.name.communities"]]], xsltUrl);
        
        html = this.htmlify(html);
        var pop = this.popup.setContent(html);
        var dateNodes = dojo.query(".dateSpan", pop);
        var imgFeedNodes = dojo.query(".feedLinkImage", pop);
        for (var i = 0; i < dateNodes.length; i++) {
            try {
                this.getDateFormatterSupport().formatDate(dateNodes[i]);
            } 
            catch (E) {
            }
        }
        
        for (var i = 0; i < imgFeedNodes.length; i++) {
            imgFeedNodes[i].setAttribute("src", feedImg);
            imgFeedNodes[i].setAttribute("alt", this._resourceBundleWs.COMMUNITIES_FEED_ALT_IMG);
            imgFeedNodes[i].setAttribute("title", this._resourceBundleWs.COMMUNITIES_FEED_ALT_IMG);
        }
        
        try {
            SemTagSvc.parseDom(null, pop);
        } 
        catch (e) {
        }
        // Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
    },
    fetchForums: function(evt){
        var url = evt.target.getAttribute('location');
        var title = evt.target.getAttribute('title');
        this.popup.showPopup(title);
        var io = this.getIoSupport(this._iContext);
        dojo.connect(io, "handleAsyncRequest", this, "showForums");
        io.retrieveAtomAndUpdate(url, true);
    },
    showForums: function(data){
        var xmlData = data;
        var xsltUrl = this.getXsltUrl("xslt/communityForums.xsl");
        var html = "";
        try {
            html = this.getXsltSupport().getXsltResult(data, 
            		[['nofeeds', this._resourceBundleWs.COMMUNITIES_NO_FORUMS_TITLE], 
            		 ['nodescription1', this._resourceBundleWs.COMMUNITIES_NO_FORUMS_EXPLANATION1], 
            		 ['nodescription2', this._resourceBundleWs.COMMUNITIES_NO_FORUMS_EXPLANATION2], 
            		 ['tags', this._resourceBundleWs.COMMON_TAGS],
            		 ['blankGif', this._blankGif],
                     ['ariaTag', this._resourceBundleWs.ARIA_TAG],
                     ['ariaTagDescription', this._resourceBundleWs.ARIA_TAG_DESCRIPTION],
                     ['commForumsTitle', this._resourceBundleJsp["jsp.common.service.name.forums"]]], xsltUrl);
        } 
        catch (e) {
        	hp_console_debug(e);
        }
        
        html = this.htmlify(html);
        var pop = this.popup.setContent(html);
        var dateNodes = dojo.query(".dateSpan", pop);
        for (var i = 0; i < dateNodes.length; i++) {
            try {
                this.getDateFormatterSupport().formatDate(dateNodes[i]);
            } 
            catch (E) {
            }
        }
        try {
            SemTagSvc.parseDom(null, pop);
        } 
        catch (e) {
        }
        // Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
    },
    fetchBookmarks: function(evt){
        var url = evt.target.getAttribute('location');
        var title = evt.target.getAttribute('title');
        this.popup.showPopup(title);
        
        var io = this.getIoSupport(this._iContext);
        dojo.connect(io, "handleAsyncRequest", this, "showBookmarks");
        
        io.retrieveAtomAndUpdate(url, true);
    },
    
    showBookmarks: function(data){
        var xmlData = data;
        var xsltUrl = this.getXsltUrl("xslt/communityBookmarks.xsl");
        var html = "";
        html = this.getXsltSupport().getXsltResult(data, 
        		[['nobookmarks', this._resourceBundleWs.COMMUNITIES_NO_BOOKMARKS_TITLE], 
        		 ['nodescription1', this._resourceBundleWs.COMMUNITIES_NO_BOOKMARKS_EXPLANATION1], 
        		 ['nodescription2', this._resourceBundleWs.COMMUNITIES_NO_BOOKMARKS_EXPLANATION2], 
        		 ['tags', this._resourceBundleWs.COMMON_TAGS],
        		 ['blankGif', this._blankGif],
                 ['ariaTag', this._resourceBundleWs.ARIA_TAG],
                 ['ariaTagDescription', this._resourceBundleWs.ARIA_TAG_DESCRIPTION],
                 ['commBookMarkTitle', this._resourceBundleJsp["jsp.common.service.name.dogear"]]], xsltUrl);
        html = this.htmlify(html);
        var pop = this.popup.setContent(html);
        var dateNodes = dojo.query(".dateSpan", pop);
        for (var i = 0; i < dateNodes.length; i++) {
            try {
                this.getDateFormatterSupport().formatDate(dateNodes[i]);
            } 
            catch (E) {
            }
        }
        try {
            SemTagSvc.parseDom(null, pop);
        } 
        catch (e) {
        }
        // Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(pop);
    },
    
    closeInfo: function(){
        this.communityInformation.style.display = 'none';
    },
    
    _getAmountOfEntriesToDisplay: function(){
        var numberEntries = this._optionSet.getItemValue("communitiesNumberEntries");
        
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
        // summary: overriden to hide nodes   
        this.communityLoading.style.display = "none";
        this._hideScroller();
        this.commNode.style.display = "none";
        this.communityWelcome.style.display = "none";
        
        var handler = this.getErrorHandler();
        handler.setErrorNode(this.errorNode);
        handler.displayError(messageObj);
    },
    htmlify: function(stringin){
        var htmlout = stringin.replace(/&lt;/g, "<");
        htmlout = htmlout.replace(/&gt;/g, ">");
        htmlout = htmlout.replace(/&amp;/g, "&");
        return htmlout;
    },
    getXsltUrl: function(xslFileLocation){
    	var xsltUrl = dojo.moduleUrl("lconn.homepage.widgets.communities", xslFileLocation);
    	//Make the url absolute so that the proxy isn't used to retrieve it (SPR #BZJN87CAHR)
        return this.makeUrlAbsolute(xsltUrl.path || xsltUrl);
    },
    makeUrlAbsolute: function(url){
    	if (url.indexOf("/") == 0){
    		return window.location.protocol + "//" + window.location.host + url;
    	}else{
    		return url;
    	}
    },
    
    _childNodes: null,
    _scroller: null,
    // nodes
    scrollerNode: null,
    content: null,
    _commTickerWidget: null,
    _data: null,
    _resourceBundleWs: null,
    _resourceBundleJsp: null,
    titleNode: null,
    
    // CONST
    DEFAULT_AMOUNT: 3,
    MAX_AMOUNT: 25
});
