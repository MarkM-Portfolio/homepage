/* ***************************************************************** */
/*                                                                                                                                    */
/* IBM Confidential                                                                                                         */
/*                                                                                                                                    */
/* OCO Source Materials                                                                                                */
/*                                                                                                                                    */
/* Copyright IBM Corp. 2006, 2015                                    */
/*                                                                                                                                    */
/* The source code for this program is not published or otherwise                               */
/* divested of its trade secrets, irrespective of what has been                                    */
/* deposited with the U.S. Copyright Office.                                                                 */
/*                                                                                                                                    */
/* ***************************************************************** */

// Mainly from MashupMaker enabler
// Modified to add Safari support

dojo.provide("lconn.homepage.utils.xslt");

dojo.require("dojox.collections.Dictionary");

//---------------------------------------------------------------------- xml related utility methods
/*
 * loadXml(sUrl) - returns oDomDoc. parses xml from the url into DOM document object.
 * loadXmlString() - returns oDomDoc. parses xml from the contents of the string into DOM document object.
 * loadXsl()
 * transform()
 */
lconn.homepage.utils.xslt.ie = {};
lconn.homepage.utils.xslt.gecko = {};

lconn.homepage.utils.xslt.cache = new dojox.collections.Dictionary();

lconn.homepage.utils.xslt.getXmlHttpRequest = function() {
	var oXml = null;
	if (typeof ActiveXObject != "undefined") {
		oXml = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		oXml = new XMLHttpRequest();
	}
  return oXml;
}

lconn.homepage.utils.xslt.loadXml = function(sUrl) {
	if (typeof ActiveXObject != "undefined") 
		return lconn.homepage.utils.xslt.ie.loadXml(sUrl);
	else 
		return lconn.homepage.utils.xslt.gecko.loadXml(sUrl);
}

lconn.homepage.utils.xslt.loadXmlString = function(sXml) {
	if (typeof ActiveXObject != "undefined") 
		return lconn.homepage.utils.xslt.ie.loadXmlString(sXml);
	else 
		return lconn.homepage.utils.xslt.gecko.loadXmlString(sXml);
}

lconn.homepage.utils.xslt.loadXsl = function(sUrl) {	
	var cache = lconn.homepage.utils.xslt.cache;
	var xslDoc = null;
	
	if (cache.contains(sUrl)){
		xslDoc = cache.item(sUrl);		
	}
	else{
	
		if (typeof ActiveXObject != "undefined") 
			xslDoc = lconn.homepage.utils.xslt.ie.loadXsl(sUrl);
		else 
			xslDoc = lconn.homepage.utils.xslt.gecko.loadXsl(sUrl);
			
		cache.add(sUrl, xslDoc);	
	}
	
	return xslDoc;	
}

lconn.homepage.utils.xslt.transform = function (xml, xsl, sXslMode, aXslParams, bReturnString)
{
   //ibm.enabler.debug.entry( "transform", [xml,xsl,sXslMode,aXslParams,bReturnString] );  
   if (typeof ActiveXObject != "undefined") {
    return lconn.homepage.utils.xslt.ie.transform(xml, xsl, sXslMode, aXslParams,bReturnString);
  }
  else 
    return lconn.homepage.utils.xslt.gecko.transform(xml, xsl, sXslMode, aXslParams,bReturnString);
}

// added
lconn.homepage.utils.xslt.transformDocument = function(/*XMLDocument*/xmlData, xsltUrl, params){
   var xslDoc = lconn.homepage.utils.xslt.loadXsl(xsltUrl);
   if (xslDoc.documentElement == null) 
   {
     //dojomum.debug("widgetLoadService:transformDocument xslDoc is null");
     return null;
   }
   var results = lconn.homepage.utils.xslt.transform(xmlData, xslDoc, null, params, true);
    	           
   //dojomum.debug("widgetLoadService:transformDocument return is "+results);           
   return results;
}

lconn.homepage.utils.xslt.transformAndUpdate = function (/*HTMLElement*/nodeToUpdate, /*XMLDocument*/xml, /*XMLDocument*/xsl, /*String?*/sXslMode, /*Map*/aXslParams) {
	//ibm.enabler.debug.entry( "transformAndUpdate", [ nodeToUpdate, xml, xsl, sXslMode, aXslParams ]);
	if ( typeof ActiveXObject != "undefined" ) {
		var results = lconn.homepage.utils.xslt.ie.transform(xml, xsl, sXslMode, aXslParams, true );
		//Don't really want to use innerHTML here, but seems to be the only way IE will 
		//take the update.
		//ibm.enabler.debug.text( "XSLT result: " + results );
		nodeToUpdate.innerHTML += results;
	}
	else {
		results = lconn.homepage.utils.xslt.gecko.transform(xml,xsl,sXslMode,aXslParams, false);
		//ibm.enabler.debug.text( "XSLT result: " + ( new XMLSerializer() ).serializeToString( results ), "lconn.homepage.utils.xslt.transformAndUpdate" );
		
		var toAppend = results.documentElement;
			
		if ( results.documentElement.tagName == "transformiix:result" ) {
			toAppend = results.documentElement.childNodes;
			dojomum.dom.copyChildren( results.documentElement, nodeToUpdate, true );
			/*var length = toAppend.length
			
			ibm.enabler.debug.text( "there are " + toAppend.length + " items to add." );
			
			for ( var c = 0; c < length; c++ ) {
				ibm.enabler.debug.text( "Appending: " + ( new XMLSerializer() ).serializeToString( toAppend[c] ), "lconn.homepage.utils.xslt.transformAndUpdate" );
				ibm.enabler.debug.text( "toAppend = " + toAppend[c] + "; c=" + c + "; length=" + length );
				nodeToUpdate.appendChild( toAppend[c] );
			}*/
		}
		else {
			//ibm.enabler.debug.text( "Appending2: " + ( new XMLSerializer() ).serializeToString( toAppend ), "lconn.homepage.utils.xslt.transformAndUpdate" );
			nodeToUpdate.appendChild( toAppend );
		}
	}
	
	//ibm.enabler.debug.exit( "transformAndUpdate" );
}

//---------------------------------------------------------------------- IE xml related utility methods

lconn.homepage.utils.xslt.ie.loadXml = function(sUrl) {
	var oXmlDoc = new ActiveXObject("MSXML2.DOMDocument");
	oXmlDoc.async=0;
	oXmlDoc.resolveExternals = 0;
  	if(!oXmlDoc.load(sUrl))
  	{
  		//Callers should catch this and can substitute their own error message
  		throw new Error("Error loading xml file " + sUrl);
  	}
	return oXmlDoc;

}

lconn.homepage.utils.xslt.ie.loadXmlString = function(sXml) {
	var oXmlDoc = new ActiveXObject("MSXML2.DOMDocument");
	oXmlDoc.async=0;
	oXmlDoc.resolveExternals = 0;
  	if(!oXmlDoc.loadXML(sXml))
  	{
  	    //Callers should catch this and can substitute their own error message
  		throw new Error("Error loading xml string " + sXml); 
  	}
	return oXmlDoc;
}

lconn.homepage.utils.xslt.ie.loadXsl = function(sUrl) {
	//we need to use MSXML2.FreeThreadedDOMDocument interface in order to support 
	//mode and parameters in XSL transformation.
	var oXslDoc = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
	oXslDoc.async=0;
	oXslDoc.resolveExternals = 0;
  	if(!oXslDoc.load(sUrl))
  	{
  		//Callers should catch this and can substitute their own error message
  		throw new Error("Error loading xsl file " + sUrl);
  	}	
	return oXslDoc;
}

lconn.homepage.utils.xslt.ie.transform = function(xmlDoc, xsl, sXslMode, aXslParams,bReturnString) {
	var oXml = xmlDoc;
	var oXsl = xsl;

     try {		
		if(!oXsl.documentElement) oXsl = this.loadXsl(xsl);
	 }
	 catch(e) {
		var sMsg = e.message;
		throw new Error(""+sMsg, ""+sMsg);
	 }
	//create the xsl processor and apply the transformation
	var oXslt = new ActiveXObject("Msxml2.XSLTemplate");
	oXslt.stylesheet = oXsl;
	var oXslProc = oXslt.createProcessor();
	oXslProc.input = oXml;
	
	//set paramaters if any
	if(aXslParams) {
		for(var p in aXslParams) {
			oXslProc.addParameter(aXslParams[p][0], aXslParams[p][1]);
		}
	}
	if (sXslMode) oXslProc.addParameter("mode", sXslMode);
	
	if (bReturnString) {
		if(!oXslProc.transform()) {
			//Callers should catch this and can substitute their own error message
  			throw new Error("Error transforming xml doc " + oXml); 
  		}
  		return oXslProc.output;
	} else {
		var oHtmlDoc = new ActiveXObject("MSXML2.DOMDocument");
		oHtmlDoc.async = 0;
		oHtmlDoc.validateOnParse = 1;
		oXml.transformNodeToObject(oXsl,oHtmlDoc);
		return oHtmlDoc;	
	}
}

//---------------------------------------------------------------------- GECKO xml related utility methods

lconn.homepage.utils.xslt.gecko.loadXml = function(sUrl) {
    //var oXmlResponse = NG.ServerRequest.postRequest(sUrl);
//    if (oXmlResponse) return xmlLoadString(oXmlResponse.responseText);
//    else return null;
}

lconn.homepage.utils.xslt.gecko.loadXmlString = function(sXml) {
    var parser = new DOMParser();
    try { oXmlDoc = parser.parseFromString(sXml, "text/xml"); }
    catch (exc) {
	    //Callers should catch this and can substitute their own error message
        //alert("error loading xml");
  		throw new Error("Error loading xml string " + sXml); 
    }
	return oXmlDoc;
}

lconn.homepage.utils.xslt.gecko.loadXsl = function(sUrl) {
	//This is done through createDocument because of anchor(#) we have in portal url. 
	//Do not change the code without testing the case.
	
	var oDomDoc = document.implementation.createDocument('','',null); 
	oDomDoc.async = false; // this is the important part
	
	if (dojo.isSafari){
		oDomDoc.load = function(url){
			var req = dojo.xhrGet({
				sync : true,
				url: url,
				handleAs: "xml",
				load: function(data, transport){
					oDomDoc = data; 
				}
			});
		} 
	}	
	
	oDomDoc.load(sUrl);	
	return oDomDoc;
}

lconn.homepage.utils.xslt.gecko.transform = function(xmlDoc, xsl, sXslMode, aXslParams,bReturnString) {
	try {
	  var xslDoc = xsl;
      if(!xslDoc.documentElement) 
      {
    	  hp_console_debug("xslDoc is not a Document, loading it...");
          xslDoc = this.loadXsl(xsl);
      }  
	  	        
      var proc = new XSLTProcessor();
      proc.importStylesheet(xslDoc); 
	   
      //set parameters if any
      if(aXslParams) {
	  	for(var p in aXslParams)  {
		    proc.setParameter(null, aXslParams[p][0], aXslParams[p][1]);
	    }
      }
      if (sXslMode) proc.setParameter(null, "mode", sXslMode);
    
      var resultDoc = proc.transformToFragment(xmlDoc, document);
      if (!bReturnString) {
      	return resultDoc;
      }
	  var serializer = new XMLSerializer();
	  resultStr = serializer.serializeToString(resultDoc);
	
    } 
    catch (exc)
    {
        //alert("error transforming document: "+exc)
		//Callers should catch this and can substitute their own error message
  		throw new Error("Error transforming xml doc " + exc);
	}
    return resultStr;  
}

/* This method sets the content of a layer within the HTML page
 * to the result of transforming the xml parameter by the xsl
 * parameter. The xml and xsl parameters may be of any form
 * supported by the transformXml() method. The layer parameter
 * may be either a DOM object or the name of a DOM object that
 * can be found using the findObject() method.
 */

lconn.homepage.utils.xslt.setLayerContentByXml = function (layer, xml, xsl, xslparam,bReturnString) {
    var result = lconn.homepage.utils.xslt.transform(xml,xsl,null,xslparam,bReturnString);
    if (layer.innerHTML) layer.innerHTML = result;
    else {
        var obj = document.getElementById(layer);
        obj.innerHTML=result;
    }
}
