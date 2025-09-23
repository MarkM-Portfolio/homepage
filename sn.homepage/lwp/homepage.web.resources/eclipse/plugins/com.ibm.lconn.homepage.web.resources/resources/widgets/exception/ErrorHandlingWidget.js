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

dojo.provide("lconn.homepage.widgets.exception.ErrorHandlingWidget");

dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("dojox.date.posix");
dojo.require("dojox.data.dom");

/* Require this for its NLS files */
dojo.provide("lconn.homepage.river.watchlist.WatchlistTabPanel");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

dojo.declare(
	// widget name and class
	"lconn.homepage.widgets.exception.ErrorHandlingWidget",
	
	// superclass
	[dijit._Widget, dijit._Templated],

	// properties and methods
	{
		// summary: Dojo widget responsible for displaying a common error view in the widget
		//			Other widgets can pick up the node of HTML of this widget to display it		
			
		templatePath: dojo.moduleUrl("lconn.homepage", "widgets/exception/templates/errorHandlingWidget.html"),
		
		postMixInProperties: function(){			
			this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
			
			this.header = this._resourceBundle.ERROR_TITLE;
			this.body = this._resourceBundle.ERROR_BODY;
			this.errorMore = this._resourceBundle.ERROR_MORE;
			this.errorHide = this._resourceBundle.ERROR_HIDE;
			// Didn't make it in time for translation
			this.errorImgAlt = this._resourceBundle.ERROR_ICON;
			
			hp_console_debug("More Txt = " + this.errorMore);
			hp_console_debug("Hide Txt = " + this.errorHide);
			
			this.moreDetails = this.errorMore;
			
			hp_console_debug("Setting txt = " + this.moreDetails);
			
			this._extractExceptionDisplayableInfo();					
		},
		
		_extractExceptionDisplayableInfo: function(){
			// try to get a more detailed message
			// we are not guarranted that all the properies are available in the passed exception obj
			// as one can throw any object or primitive type in JS
			// at worst, we call toString which should work on any object and primitive
			
			if (this.exceptionToDisplay != null){
				var e = this.exceptionToDisplay;			
				var br = "<br/>";
				var message = this._resourceBundle.EXCEPTION_CAUGHT + br;
				if (e.name)
					message += this._resourceBundle.EXCEPTION_NAME + e.name + br;
				if (e.message)
					message += this._resourceBundle.EXCEPTION_MESSAGE + e.message + br;	
				if (e.fileName)
					message += this._resourceBundle.EXCEPTION_FILE_NAME + e.fileName + br;
				if (e.lineNumber)
					message += this._resourceBundle.EXCEPTION_LINE_NUMBER + e.lineNumber + br;
				if (e.stack)
					message += this._resourceBundle.EXCEPTION_STACK_TRACE + e.stack + br;
			
				message += this._resourceBundle.EXCEPTION_TO_STRING + e.toString();					
			
				this.errorText = message;	
			}			
		},
		
		toggleDetails: function(evt){
			if(this._isShowingDetails)
				this.errorTextNode.style.display = "none";	
			else
				this.errorTextNode.style.display = "";		
	
			hp_console_debug("toggle!!!!!!!!!!!!!!!");
			
			if(this.moreDetails==this.errorMore)
				this.moreDetails=this.errorHide;
			else
				this.moreDetails=this.errorMore;				
			
			this.showHideTextNode.innerHTML = this.moreDetails;
			
			hp_console_debug("Updating txt = " + this.moreDetails);
			
			this._isShowingDetails = !this._isShowingDetails;			
			dojo.stopEvent(evt);
		},
		
		errorText: "",
		exceptionToDisplay: null,
		moreDetails: "",
		header: "",
		body: "",
		sideIcon: "",
		root: null,
		errorTextNode: null,
		showHideTextNode: null,
		_isShowingDetails: false,
		_resourceBundle: null
			
	}	
);
