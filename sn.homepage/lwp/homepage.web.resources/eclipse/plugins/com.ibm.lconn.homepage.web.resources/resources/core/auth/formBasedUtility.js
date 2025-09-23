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

dojo.require("com.ibm.ajax.auth");
dojo.require("dojo.cookie");
dojo.require("lconn.core.auth.whiteListHelper");
dojo.provide("lconn.homepage.core.auth.formBasedUtility");

// handler registered by the customer
// there can be only one handler as defined in LotusConnections-Config.xml
// Deprecated in 2.5.1
// com.ibm.ajax.auth._customerHandler = null;

// ensure the lconn.homepage.core.auth is correctly created
dojo.getObject("lconn.homepage.core.auth", true);


lconn.homepage.core.auth.HTML_RESPONSE_HEADER = "X-FBA-CHECK-SKIP";

// list of (parts of) URLs that should return HTML fragment over Ajax in normal case
// response MUST include the header lconn.homepage.core.auth.HTML_RESPONSE_HEADER
// TODO: could we get this list from struts.xml?
lconn.homepage.core.auth.HTML_RESP_URLS = ['web/doFetchTagSubscriptions.action',
                                           'web/doAddTagSubscription.action',
                                           'web/doRemoveTagSubscription.action',
                                           'web/shareStepPage.action',
                                           'web/exploreStepPage.action'];

lconn.homepage.core.auth.formBasedUtility = {
    // summary: HP utility encapsulating the logic for detecting the form based
    // auth challenge on Ajax requests
    // Vincent
    
    // _contextRoot: String
    _contextRoot: null,
    
    // REDIRECT_PATH: String
    REDIRECT_PATH: "/web/authredirect.action",
    
    // COOKIE_NAME: String
    COOKIE_NAME: "HomepageRedirect",
    
    isCustomAuthEnabled: false,
    _customerHandler: null,
    _whiteListHelper: null,
    
    init: function(contextRoot, /* lconn.core.auth.whiteListHelper */ whiteListHelper){
        this._whiteListHelper = whiteListHelper;
        this._contextRoot = contextRoot;
        this._overrideXhrCalls();
    },
    
    HPCustomAuthHandlerHTMLFragments: function(auth, response, ioArgs){
        // summary: some specific auth detections handler for HTML fragments
        // sent over Ajax in river of news
        
        // check for FBA for river of news and notifications HTML fragments
        var url = ioArgs.url;
        
        if (this._isHtmlRespUrl(url)) {
            if (ioArgs.xhr.getResponseHeader(lconn.homepage.core.auth.HTML_RESPONSE_HEADER) == null ||
            (ioArgs.xhr.getResponseHeader(lconn.homepage.core.auth.HTML_RESPONSE_HEADER) != "true")) {
                // unexpected response ==> login form or error page
                if (ioArgs.xhr.status != 500) {
                    // login form
                    return true;
                }
                else {
                    // error page
                    return false;
                }
            }
        }
    },
    
    _isHtmlRespUrl: function(urlToCheck){
        return dojo.some(lconn.homepage.core.auth.HTML_RESP_URLS, function(url){
            return urlToCheck.indexOf(url) != -1;
        });
    },
    
    _registerAuthenticationHandler: function(){
        // sumarry: register default configuration handler or customer registered
        // handler
        
        var auth = com.ibm.ajax.auth;
        var useCustomerAuthHandler = false;
        
        // register default authentication handler
        var url = this._contextRoot + this.REDIRECT_PATH;
        var handler = {
            // just put the context root, which is form based auth protected
            url: url,
            authenticationRequired: function(response, ioArgs, onauthenticated){
                var racp = window.location.href.replace(/,/g, "%2C");
                // dojo.cookie seems to be encoding the url, causing a redirect error so using document.cookie
                // dojo.cookie(that.COOKIE_NAME, racp, {path: "/"});                
                document.cookie = "HomepageRedirect=" + racp + "; path=/;Secure";
                location.href = this.url;
            },
            onSuccess: function(response, ioArgs){
            	hp_console_debug("Successfully loaded");
            }
        }
        // check based on both content and reponse status
        // auth.setDefaultAuthenticationTests(false, true, true);
        auth.setAuthenticationHandler(dojo.hitch(handler, handler.authenticationRequired));
    },
    
    _overrideXhrCalls: function(){
        // summary: Dynamically override the dojo.xhr* calls to invoke the form
        // based auth mechanism
        var auth = com.ibm.ajax.auth;
        
        var that = this;
        // this._setupCustomAuth();
        
        this._registerAuthenticationHandler();
        
        // add our custom HP detection handler
        auth.addAuthenticationCheck(dojo.hitch(this, "HPCustomAuthHandlerHTMLFragments"));
        
        originaldojoxhr = dojo.xhr;
        
        var whiteListHelper = this._whiteListHelper;
        dojo.xhr = function(){
            // only handle FBA for whitelisted urls
            var ioArgs = arguments[1];
            if (whiteListHelper.isWhiteListedURL(ioArgs.url)) {
            
                try {
                    ioArgs[1] = auth.prepareSecure(ioArgs);
                } 
                catch (e) {
                	hp_console_debug("exception in overriden dojo.xhr (form-based auth): %o", e);
                }
            }
            
            return originaldojoxhr(arguments[0], arguments[1], arguments[2]);
        };
    }
};
