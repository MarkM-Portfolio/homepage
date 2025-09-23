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

dojo.provide("lconn.homepage.widgets.profiles.MyProfile");

dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.core.common.ScrollerModel");
dojo.require("lconn.core.util._XSLCache");

dojo.require("dijit.form.ComboBox");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.Button");
dojo.require("dojox.data.dom");
dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("dojox.date.posix");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

dojo.declare("lconn.homepage.widgets.profiles.MyProfile",
		lconn.homepage.core.base._BodyWidget,
		{
	templatePath: dojo.moduleUrl("lconn.homepage", "widgets/profiles/templates/myProfile.html"),
	_resourceBundle: null,
	tagLabel:null,
	nameLabel:null,
	searchByLabel:null,
	_ownerClass:null,
	titleNode: null,
	_colleaguesLocation:null,
	
	loading: "",
	userid: null,
	
	xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
		xslStrings: {"profiles.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/profiles/profiles.xsl")},
			"friending.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/profiles/friending.xsl")},
			"../profiles.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/profiles/profiles.xsl")},
			"../friending.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/profiles/friending.xsl")}
		} 		
	})),
	
	postCreate: function() {
			lconn.homepage.widgets.profiles.MyProfile.superclass.postCreate.apply(this);
		
			this.userid = this._iContext.getUserProfile().getItemValue("userid");		

			this.getErrorHandler().hideError();
			this.loadingNode.style.display = "";
			this.myProfileNode.style.display="none";
			
			this._createModeMenu();
			
			this.xsltUrl = this._parameters.xsltUrl;
			this.remoteUrl=this._feedUrls.myProfileFds + "userid=" + this.userid;
			this.retrieveAtomAndUpdate();
			var io = this.getIoSupport(this._iContext);
			dojo.connect(io, "handleAsyncRequest", this, "handleColleagueRequests");
			io.retrieveAtomAndUpdate(this._feedUrls.friendingFds+"userid="+this.userid, true);
	},
	handleAsyncRequest: function(data, evt) {
		if(data==null){
		}else{
			var html="";
			try{
				var xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
		        var xslParams =  [['choice', 'userProfile'], 
									['edit', this._resourceBundle.PROFILES_EDIT],
									['profile', this._resourceBundle.PROFILES_PROFILE],
									['email', this._resourceBundle.PROFILES_EMAIL],
									['telOffice', this._resourceBundle.PROFILES_TELOFFICE],
									['imageProfilesAlt', this._resourceBundle.PROFILES_IMG_PROFILES_ALT]];
		        	
		        if(xslDoc)
		        	html = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
				else
					html = this.getXsltSupport().getXsltResult(data, xslParams, this.xsltUrl);
			} catch(e) {hp_console_debug(e);}
			this.loadingNode.style.display="none";
			this.myProfileNode.innerHTML=html;
			this.myProfileNode.style.display="";
			try {
				SemTagSvc.parseDom(null,this.myProfileNode);
			}
			catch(e) {
				hp_console_debug(e);
			}
			// Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.myProfileNode);
		}
	},
	handleColleagueRequests:function(data,evt) {
		if(data==null) {
			// Hide the link so that JAWS won't read it
			dojo.addClass(this.colleagueRequests, "lotusHidden");
		} else {
			var numColRequests=0;
			try{
				var xslDoc = this.xslCache.getXslDoc(this.xsltUrl);
		        var xslParams =  [['choice','pending']];
		        	
		        if(xslDoc)
		        	numColRequests = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
				else
					numColRequests = this.getXsltSupport().getXsltResult(data, xslParams, this.xsltUrl);
			}catch(e){hp_console_debug(e);}
			
			if(numColRequests!=0) {
				var colReqString="";
				if(numColRequests==1) {
					colReqString=dojo.string.substitute(this._resourceBundle.PROFILES_NEW_COLLEAGUE_REQUEST, [numColRequests]);
				} else {
					colReqString=dojo.string.substitute(this._resourceBundle.PROFILES_NEW_COLLEAGUE_REQUESTS, [numColRequests]);
				}
				this.colleagueRequests.innerHTML=colReqString;
			}else{
				// Hide the link so that JAWS won't read it
				dojo.addClass(this.colleagueRequests, "lotusHidden");
			}
		}
	},
	handleError: function(data, evt){
		// summary: overriden to hide loadingnode		   
		this.loadingNode.style.display = "none";
		
		//lconn.homepage.PublicActivitiesWidget.superclass.displayError.apply(this, arguments);
		var handler = this.getErrorHandler();
		handler.setErrorNode(this.errorNode);
					
		handler.displayError({
			exceptionToDisplay: data
		});			
	},
	postMixInProperties: function(){			
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
		this._colleaguesLocation=this._feedUrls.pendingFriendURL;
		this.tagLabel = this._resourceBundle.PROFILES_TAG;
		this.nameLabel = this._resourceBundle.PROFILES_NAME;
		this.searchByLabel=this._resourceBundle.PROFILES_SEARCH_BY;
		this.loading=this._resourceBundle.COMMON_LOADING;
	}
});
