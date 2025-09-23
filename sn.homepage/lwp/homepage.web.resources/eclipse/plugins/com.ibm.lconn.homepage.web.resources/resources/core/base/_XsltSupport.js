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

dojo.provide("lconn.homepage.core.base._XsltSupport");

dojo.require("lconn.core.xslt");

dojo.declare(// widget name and class
"lconn.homepage.core.base._XsltSupport", // superclass
null, // properties and methods
{
    // summary: Base/utility class regrouping common methods to XSLT parsing
    // description:
    //	Wrapper wround lconn.core.xslt.transformDocument
    //	Note: based around the singleton design pattern, call getInstance() rather than using "new"	
    
    constructor: function(iContext){
        this._iContext = iContext;
    },
    
    getXsltResult: function(/* XML doc */xmlData, /* Array of params */ params,/* String: xslt url */ xslt){
        // summary: Wrapper wround lconn.core.xslt.transformDocument
        // returns: string resulting from the transformation or null if error	
        
        // proxify the URL for PMR 62405,227,000 before calling lconn.core.xslt
        
        var xsltUrl = xslt;
        
        if (this._iContext != null) {
            xsltUrl = this._iContext.io.rewriteURI(xsltUrl);
        }
        
        return lconn.core.xslt.transformDocument(xmlData, xsltUrl, params);
    },
    
    getInstance: function(args){
        if (lconn.dboard._XsltSupport.prototype._instance == null) 
            lconn.dboard._XsltSupport.prototype._instance == new lconn.dboard._XsltSupport(args);
        
        return lconn.dboard._XsltSupport.prototype._instance;
    },
    
    _xsltProcessor: null,
    xsltUrl: null,
    _instance: null,
    _iContext: null

});
