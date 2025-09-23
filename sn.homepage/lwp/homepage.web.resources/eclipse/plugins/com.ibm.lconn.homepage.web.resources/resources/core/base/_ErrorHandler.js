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

dojo.provide("lconn.homepage.core.base._ErrorHandler");
dojo.require("lconn.homepage.widgets.exception.ErrorHandlingWidget");

dojo.declare(
	// widget name and class
	"lconn.homepage.core.base._ErrorHandler",
	
	// superclass
	null,
	
	// properties and methods
	{
		// summary: Utility class to display an error panel in a DOM node place holder
		// description: Typically used to display an error view in a widget from a Javascript 
		//		exception object
		
		
		displayError: function(messageObj){
			// summary: display a default error view in errorNode
			// description: set innerHTML of errorNode with an formatted error message
			// 		-messageObj, map overriding the default error message to display
			// 		Typically:
			//		  {
			// 			exceptionToDisplay: exceptionObj
			//		  }		
			//	Note that this method DOES NOT hide any other node in your widget, you have
			//	to do this operation manually
							
			if (this.errorNode != null){
				var errorWidget = new lconn.homepage.widgets.exception.ErrorHandlingWidget(messageObj);				
				
				this.errorNode.innerHTML = "";
				this.errorNode.appendChild(errorWidget.root);
				this.errorNode.style.display = "";				
			}			
		},
		
		hideError: function(){
			// summary: hide error panel
			if (this.errorNode != null){
				this.errorNode.style.display = "none";
				this.errorNode.innerHTML = "";					
			}			
		},
		
		setErrorNode: function(/* DOM Node */ errorNodePlaceHolder){
			// summary: set place holder node used by displayError() to display the error panel
			this.errorNode = errorNodePlaceHolder;
		},
		
		errorNode: null		
	}
);
