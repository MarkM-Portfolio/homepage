/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper");

dojo.require("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAhead");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare(
	// class
	"lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper",
	// superclass
	[dijit._Widget, dijit._Templated, dijit._Container], 
	{
		// summary: Wrapper around people and tag type ahead widgets
		// Warning: specific to watchlist tab. Probably overkill for other use-cases.
    
	    _imgRoot: null,
	    _resourceBundle: null,
		typeAheadNode: null,
		addTitle: '',
	    
	    // ref to wrapped widget
	    _typeAheadInst: null,
	    
	    // args passed to type ahead widgets upon construction. See wiki http://w3.tap.ibm.com/w3ki/display/conndev/Connections+Type-Ahead+Widget
	    typeAheadArgs: null,
		
		btnNode: null,
		
		CSS_ENABLED_BTN: "lotusBtnSpecial",
		CSS_DISABLED_BTN: "lotusBtnDisabled",
		
		_isBtnEnabled: false,
		
		templatePath: dojo.moduleUrl("lconn.homepage", "ui/dijit/typeahead/tags/templates/TagsTypeAheadWrapper.html"),
	    
	    postMixInProperties: function(){
	    	hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> postMixInProperties");
	    	hp_console_debug(this._imgSrc);
	        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
	        this.addTitle = this._resourceBundle.GENERIC_ADD;
	    },
		
		onAdd: function(evt){
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> onAdd");
			// called when user hits the "add" button		
			if (this._isBtnEnabled) {
				this.onAddTagSubscription();
				this._resetInputBox();
			}
		},
		doAddByEnter: function(evt){
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> doAddByEnter");
			var key = evt.charOrCode;
			if(key==dojo.keys.ENTER){
				this.onAdd(evt);
			}
		},
		onAddTagSubscription: function(){
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> onAddTagSubscription");
			// override or dojo.connect() for processing request to add a subscription
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> onAddTagSubscription [" + this._typeAheadInst.value + "]");
			dojo.publish(lconn.homepage.events.ui.addTag.EVENT_ID, [this._typeAheadInst.value]);
		},
		
		_resetInputBox: function(){
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> _resetInputBox");
			//dojo.attr(this._typeAheadInst, "value", "");			
			this._disableBtn();
			this._typeAheadInst.clearText();
		},
		
		_onCheckTypeAhead: function(){
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> _onCheckTypeAhead");
			if (this._typeAheadInst.isInputValid()){
				this._enableBtn();
			} else {
				this._disableBtn();
			}		
		},
		
		_enableBtn: function(){
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> _enableBtn");
			dojo.addClass(this.btnNode, this.CSS_ENABLED_BTN);
			dojo.removeClass(this.btnNode, this.CSS_DISABLED_BTN);		
			
			this._isBtnEnabled = true;
			// TODO: ARIA role		
			dijit.setWaiState(this.btnNode, "disabled", false);
		},
		
		_disableBtn: function(){
			hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> _disableBtn");
			dojo.removeClass(this.btnNode, this.CSS_ENABLED_BTN);
			dojo.addClass(this.btnNode, this.CSS_DISABLED_BTN);	
			
			this._isBtnEnabled = false;	
			
			dijit.setWaiState(this.btnNode, "disabled", true);
			// TODO: ARIA role
		},
	    
	    postCreate: function(){		
	    	hp_console_debug("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper --> postCreate");
	        if (this.typeAheadArgs != null) {
	                	
	            var args = this.typeAheadArgs;
	            hp_console_debug(args);
	            
	            this._typeAheadInst = new lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAhead(args, this.typeAheadNode);
	            dojo.style(this._typeAheadInst.textbox, { paddingTop: "4px", paddingBottom: "4px" } );
	            
				// listen to type ahead key press	
	            dojo.connect(this._typeAheadInst.textbox, "onkeypress", this, "doAddByEnter");
				dojo.connect(this._typeAheadInst.textbox, "onkeyup", this, "_onCheckTypeAhead");
				dojo.connect(this._typeAheadInst, "_doSelect", this, "_onCheckTypeAhead");
	        }
	    }
	}	
);


