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

dojo.provide("lconn.homepage.palette.ChangeLayoutButton");

dojo.require("lconn.core.paletteOneUI.WidgetButton");

dojo.declare(// widget name and class
"lconn.homepage.palette.ChangeLayoutButton", // superclass
 lconn.core.paletteOneUI.WidgetButton, // properties and methods
{
    // summary: Override WidgetButton to always hide "Added to your page" and "+" button
    
    templateString: null,    
    templatePath: dojo.moduleUrl("lconn.homepage", "palette/templates/ChangeLayoutButton.html"),
        
    disableButton: function(){
        if (this._isEnabled) {
            dojo.disconnect(this._onClickHandler);
            this._onClickHandler = null;
            
            //dojo.removeClass(this.addedWidgetLabelNode, "lotusHidden");
            dojo.addClass(this.titleContainerNode, "lotusAdded");
            // cannot use lotusHidden on this node because of lotusIcon override
            this.plusIconNode.style.display = "none";
            
            dojo.addClass(this.titleNode, "lotusHidden");
            dojo.removeClass(this.titleNodeAdded, "lotusHidden");
            dijit.focus(this.titleNodeAdded);
            this.titleNodeSpan.innerHTML = this.title;
            
            dojo.addClass(this.domNode, "lotusPaletteDisabledBtn");
            
            this._isEnabled = false;
        }
    },
    
    enableButton: function(){
        if (!this._isEnabled) {
            if (this._onClickHandler == null) {
                this._onClickHandler = dojo.connect(this.domNode, "onclick", this, "onClick");
            }
            // never display the "+" icon
            //this.plusIconNode.style.display = "";
            
            dojo.addClass(this.titleNodeAdded, "lotusHidden");
            dojo.removeClass(this.titleNode, "lotusHidden");
            this.titleNode.innerHTML = this.title;
            
            dojo.removeClass(this.titleContainerNode, "lotusAdded");
            
            dojo.removeClass(this.domNode, "lotusPaletteDisabledBtn");
            
            this._isEnabled = true;
        }
    },
    
    _buildIconNode: function(/* String */imgUrl, /* String */ altText){
        // summary: build span/img nodes used to display the widget icon
        
        var spanNode = dojo.doc.createElement("span");
        var imgNode = dojo.doc.createElement("img");
        
		// no alt/title text. Img is just a decoration not carrying info.
        imgNode.src = imgUrl;
        imgNode.alt = "";
        imgNode.title = "";
        
        spanNode.appendChild(imgNode);
        
        if(dojo.isIE == 6){
	        // Although setTimeout is set to run at 0 here, it actually waits 
	        // until the browser finishes some other tasks before executing.
        	// Problem was that image wasn't loaded in time before rendering.
        	// This fix is only required for IE6.
			setTimeout(dojo.hitch(this, function(){
				this.widgetIconNode.appendChild(spanNode);
			}), 0);
        }else{
        	// Just add normally
        	this.widgetIconNode.appendChild(spanNode);
        }
    },
    
    _updateTitle: function(){
        if (this.widgetItem != null) {
            this.title = this.widgetItem.name[0];
            this.titleNode.innerHTML = this.widgetItem.name[0];
        }
        this.updateLabel();
    },
    
    _updateDescription: function(){
        if ((this.widgetItem != null) && (this.widgetItem.desc != null)) {
            dojo.attr(this.domNode, "title", this.widgetItem.desc[0]);
            dojo.attr(this.domNode, "alt", this.widgetItem.desc[0]);
        }
    },
    updateLabel: function(){
    	this.layoutAddedDescNode.innerHTML = this._resourceBundle.WIDGET_THIS_LAYOUT;
    	dijit.setWaiState(this.titleNodeAdded, "label",dojo.string.substitute(this._resourceBundle.WIDGET_LAYOUT_ADDED,[this.title]));
    },
    
    onClick: function(evt){
        if (!this._addingWidget) {
            this._addingWidget = true;
            try {
                if (this.widgetItem != null) {
                    dojo.publish(this.ADD_WIDGET_EVENT, [this.widgetItem]);
                }
            } 
            catch (e) {
                // ignore
            }
            finally {
                // show that the widget has been added, without disabling the button for now	
                //dojo.removeClass(this.addedWidgetLabelNode, "lotusHidden");
                //dojo.addClass(this.titleContainerNode, "lotusAdded");
                this._addingWidget = false;
            }
        }
    }
});
