/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
dojo.provide("lconn.homepage.as.tagmanager.TagManager");
/**
 * Widget include the components for add tags and remove tags
 * @author Rui Qi Shi
 */

dojo.require("lconn.core.TypeAheadDataStore");
dojo.require("lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper");
dojo.require("lconn.homepage.ui.constants.UIEvents");
dojo.require("com.ibm.social.as.constants.events");
dojo.require("com.ibm.social.as.util.ConfigObjectUtil");
dojo.require("lconn.homepage.as.tagmanager.TagRemoveItem");
dojo.require("lconn.homepage.as.message.UIMessage");
dojo.require("lconn.core.url");
dojo.require("com.ibm.oneui.util.proxy");
dojo.require("com.ibm.social.incontext.util.html");


dojo.requireLocalization("lconn.homepage", "activitystream");

dojo.declare("lconn.homepage.as.tagmanager.TagManager", [dijit._Widget, dijit._Templated],
{	// The context root
	contextRootPath : lconn.homepage.global.contextRoot,
    // The API url for fetch, add, remove tags subscription 
	FETCH_TAG_SUBSCRIPTIONS_URL: lconn.homepage.global.contextRoot + '/web/doFetchTagSubscriptions.action',
	ADD_TAG_SUBSCRIPTIONS_URL: lconn.homepage.global.contextRoot + '/web/doAddTagSubscription.action',
	REMOVE_TAG_SUBSCRIPTIONS_URL: lconn.homepage.global.contextRoot + '/web/doRemoveTagSubscription.action',
	// Stings bundle for i18n
	_resourceBundle: null,
	// The typeAhead dom Node for add tags component.
	tagsTypeAheadNode: null,
	// The typeAhead widget Node for add tags component.
	tagsTypeAheadWidget: null,
	// The domNode for remove tags component.
	tagsRemoveNode: null,
	// The domNode for div containing remove tags component.
	removeTagsComboNode: null,
	// The widget for remove tags component.
	tagsRemoveWidget: null,
	// The array of already exist tags
	alreadyExistingTags:[],
	//Create message event name
	createMessageEventName: "lconn.homepage.activitystream.event.UIMessage.create",
	//Clear message event name
	clearMessageEventName: "lconn.homepage.activitystream.event.UIMessage.clear",
	// Update UI after unfollow event name
	unfollowTagEventName:'lconn.homepage.activitystream.event.tagfilter.unfollowTag',
	
	templatePath: dojo.moduleUrl("lconn.homepage", "as/tagmanager/templates/tagManager.html"),
	
	tagsMap: null,
	
	displayedTags : null,
	/**
	 * Called before the widget is rendered.
	 * - Set up strings and create message factory
	 */
	postMixInProperties: function() {
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
		this.messageFactory = new lconn.homepage.as.message.UIMessage();
		this.displayedTags = [];
	},
	/**
	 * Called after the widget is rendered in the UI.
	 */
	postCreate: function(){
		this.getTagSubscriptions();
		this._setupAddTagsTypeAhead();
		this.subscribe(this.unfollowTagEventName,dojo.hitch(this,"unfollowTags"));
	},
	
	updateTags: function(tags){
		
		// don't be fancy, just remove all and re-add
		com.ibm.social.as.util.ConfigObjectUtil.forEachRealOptionKey(this.tagsMap, function(tagKey){
			delete this.tagsMap[tagKey];
		}, this);
		
		dojo.forEach(tags, function(item){
			this.tagsMap[item.text] = {
					label:item.text,
					params: {
						filterBy:"tag",
						filterOp:"equals",
						filterValue:item.text
					}
			};
		}, this);
		
		// trigger a filter refresh by just refreshing the stream
		dojo.publish(com.ibm.social.as.constants.events.UPDATEFILTERS, []);
	},
	/**
	 * Build the add tags component
	 */
	_setupAddTagsTypeAhead: function(){
		as_console_debug("lconn.homepage.as.tagmanager.TagManager - _setupAddTagsTypeAhead");
		if(this.tagsTypeAheadWidget == null) {
			as_console_debug("lconn.homepage.as.tagmanager.TagManager --> _setupAddTagsTypeAhead - init tagsTypeAhead");
	        var searchRoot = lconn.core.url.getServiceUrl(lconn.core.config.services.search).uri;
	        var url = '';
	        if (searchRoot != null) {
	            url = com.ibm.oneui.util.proxy(searchRoot + "/json/mytag");
	        }
	        var tagsStore = new lconn.core.TypeAheadDataStore({
	            url: encodeURI(url),
	            queryParam: "tag"
	        });
	        var args = {
					id: "tagTypeAhead",
		            minChars: 2,
		            searchDelay: 400,
		            multipleValues: true,
		            store: tagsStore,
		            'class': 'inputField',
		            token: " ",
		            hintText: this._resourceBundle.addATag
		    };
	        this.tagsTypeAheadWidget = new lconn.homepage.ui.dijit.typeahead.tags.TagsTypeAheadWrapper({ typeAheadArgs: args }, this.tagsTypeAheadNode);
			dojo.connect(this.tagsTypeAheadWidget, "onAddTagSubscription", dojo.hitch(this,function(){
				as_console_debug("lconn.homepage.ui.perspectives.TagsPerspective --> tagsTypeAhead - onAddTagSubscription");
				this.addTagSubscription(this.tagsTypeAheadWidget._typeAheadInst.attr('value'));
			}));
		}
	},
	/**
	 * Build remove tags component UI
	 * @param tagList {Array} the tag objects should be rendered
	 */
	renderRemoveItems: function(tagList){
		as_console_debug("lconn.homepage.as.tagmanager.TagManager - renderRemoveItems");
		dojo.forEach(tagList, function(tag){
			// only render new tags
			if(dojo.indexOf(this.displayedTags, tag.text) == -1) {
				this.displayedTags.push(tag.text);
				var tmpItem = new lconn.homepage.as.tagmanager.TagRemoveItem(
						{tagItem:tag,
						removeUrl:this.REMOVE_TAG_SUBSCRIPTIONS_URL,
						onRemoveCallback:dojo.hitch(this,"onRemoveCallback"),
						statusMessageNode:this.statusMessageNode
						});
				this.tagsRemoveNode.appendChild(tmpItem.domNode);
			}
		}, this);
	},
	
	/**
	 * Hide the tags component
	 * @param tagList {Array} the tag objects should be rendered
	 */
	hideTagsComponent: function(tagMap){
		as_console_debug("lconn.homepage.as.tagmanager.TagManager - hideTagsComponent");
		
		if(com.ibm.social.as.util.ConfigObjectUtil.getNumRealOptions(tagMap)>0){
			dojo.removeClass(this.removeTagsComboNode, "lotusHidden");
		}else{
			dojo.addClass(this.removeTagsComboNode, "lotusHidden");
		}
	},
	/**
	 * Callback to hide tags component when last tag removed
	 * @param 
	 */
	onRemoveCallback: function(newItems){
		as_console_debug("lconn.homepage.as.tagmanager.TagManager - onRemoveCallback");
		this.updateTags(newItems);
		this.hideTagsComponent(this.tagsMap);
		this.displayedTags = dojo.map(newItems, function(item){return item.text;});
	},
	/**
	/**
	 * Update the tagManager UI and tag filter UI after unfollow tags
	 */
	unfollowTags: function(){
		as_console_debug("lconn.homepage.as.tagmanager.TagManager - unfollowTags");
		//refresh the feed
		this.getTagSubscriptions();
		//clear existed tags in UI
		dojo.empty(this.tagsRemoveNode);
	},
	/**
	 * Get the followed tags 
	 */
	getTagSubscriptions: function() {
		as_console_debug("lconn.homepage.as.tagmanager.TagManager--> getTagSubscriptions -url [" + this.FETCH_TAG_SUBSCRIPTIONS_URL + "]");
		var args = { 
			url:  this.FETCH_TAG_SUBSCRIPTIONS_URL,
			load: dojo.hitch(this, "handleTagSubscriptions"),
			handleAs: "json",
			sync:true,
			preventCache:true
		};

		activityStreamAbstractHelper.xhrGet(args);
	},
	/**
	 * Follow tags
	 * @param tag {String} tags string
	 */
	addTagSubscription: function(tag) {
		// Reset the existing tags array
		this.alreadyExistingTags = [];
		var lTag = tag.toLocaleLowerCase();
		as_console_debug("lconn.homepage.as.tagmanager.TagManager --> getTagSubscriptions -url [" + this.ADD_TAG_SUBSCRIPTIONS_URL + "]");
		lTag = this.removeExistingTags(this.removeRepeatingTags(this.normalizeTags(lTag)));
		var args = {
			url:  this.ADD_TAG_SUBSCRIPTIONS_URL,
			content: {
				tag: lTag
			},
			headers: {"X-Update-Nonce": "true"},
			timeout: 10000,
			error: dojo.hitch(this, "handleTagAddTimeOut"),
			load: dojo.hitch(this, "handleTagSubscriptions"),
			handleAs: "json",
			preventCache:true
		};
		// If there's no tags to add
		if(!lTag){
			// Just call the fetch URL (not an add). An add request without
			// any tags will fail.
			args.url = this.FETCH_TAG_SUBSCRIPTIONS_URL;
		}
		activityStreamAbstractHelper.xhr("post",args);
		// Disable the input area when adding a tag, enable in handleTagSubscriptions() if it succeed 
		this.tagsTypeAheadWidget._typeAheadInst.attr('disabled', true);
	},
	/**
	 * The handle function for XHR call
	 * @param json{Json} return data from the xhr call
	 * @param ioArgs{Object} pass the xhr parameters 
	 */
	handleTagSubscriptions: function(json, ioArgs) {
		as_console_debug("lconn.homepage.as.tagmanager.TagManager --> handleTagSubscriptions [" + json + "]");
		
		if(json.error) {
			//TODO: error message
			console.error("add tag subscriptions error");
		}
		else {
			this.renderRemoveItems(json.items);
			this.updateTags(json.items);
			this.hideTagsComponent(this.tagsMap);
			// The ioArgs.args.content exist when it is the callback for addTagSubscription
			if(ioArgs.args.content){
				// Enable the typeAhrad input 
				this.tagsTypeAheadWidget._typeAheadInst.attr('disabled', false);
				// fix for ie:lose focus when disable the input 
				this.tagsTypeAheadWidget._typeAheadInst.focus();
				var tagsAdded = ioArgs.args.content.tag;
				var tagsAddedArray = tagsAdded.split(",");
				if(tagsAddedArray.length>0&&tagsAddedArray[0]!=""){
					var addedString = tagsAddedArray.join(", ");
					if(addedString){
						addedString = com.ibm.social.incontext.util.html.encodeHtml(addedString);
					}
					var confirmText = '';
					if(tagsAddedArray.length==1){
						confirmText = dojo.string.substitute(this._resourceBundle.tagFollowedConfirm, [addedString]);
						dojo.publish(this.createMessageEventName,[confirmText,"SUCCESS",this.statusMessageNode]);
					}else{
						confirmText = dojo.string.substitute(this._resourceBundle.tagsFollowedConfirm, [addedString]);
						dojo.publish(this.createMessageEventName,[confirmText,"SUCCESS",this.statusMessageNode]);
					}
				}else if(this.alreadyExistingTags.length>0){
					var existedTags = this.alreadyExistingTags.join(", ");
					if(existedTags){
						existedTags = com.ibm.social.incontext.util.html.encodeHtml(existedTags);
					}
					var warningText ='';
					if(this.alreadyExistingTags.length==1){
						warningText = dojo.string.substitute(this._resourceBundle.tagExistedWarn, [existedTags]);
						dojo.publish(this.createMessageEventName,[warningText,"WARNING",this.statusMessageNode]);
					}else{
						warningText = dojo.string.substitute(this._resourceBundle.tagsExistedWarn, [existedTags]);
						dojo.publish(this.createMessageEventName,[warningText,"WARNING",this.statusMessageNode]);
					}
				}
			}
		}
		
		if(this.tagsTypeAheadWidget){
			this.tagsTypeAheadWidget._resetInputBox();
		}
	},	
	/**
	 * The handle function for XHR time out
	 */
	handleTagAddTimeOut: function(error, ioargs){
		as_console_debug("lconn.homepage.as.tagmanager.TagManager --> handleTagAddTimeOut");
		// Enable the typeAhrad input 
		this.tagsTypeAheadWidget._typeAheadInst.attr('disabled', false);
		// fix for ie:lose focus when disable the input
		this.tagsTypeAheadWidget._typeAheadInst.focus();
		var errorText = this._resourceBundle.timeOutText;
		dojo.publish(this.createMessageEventName,[errorText,"ERROR",this.statusMessageNode]);
	},
	/**
	 * Normalize the tags into the right style
	 * @param inTags{String} no normalized tag string
	 */
	normalizeTags : function (inTags){
		as_console_debug("lconn.homepage.as.tagmanager.TagManager - normalizeTags - inTags:", inTags);
		var st = dojo.trim(inTags).replace(/\s+/g," ").split(",");
		// Trim each tag to remove spaces
		for ( var i = 0; i < st.length; i++ ){
			st[i] = dojo.trim(st[i]);
		}
		st = st.join().split(" ");

		var sb = "";
		var first = true;
		for ( var i = 0; i < st.length; i++ ){
			if(!first){
				sb = sb + ",";
			}else{
				first=false;
			}
			var tag = dojo.trim(st[i]);

			tag = tag.replace(/'/g, "");
			tag = tag.replace(/"/g, "");
			sb = sb + tag;
		}
		if(sb.charAt(0) == ","){
			sb = sb.substring(1, sb.length);
		}
		if(sb.charAt(sb.length-1) == ","){
			sb = sb.substring(0, sb.length-1);
		}
		sb = sb.replace(/,/g,",");
		
		as_console_debug("lconn.homepage.as.tagmanager.TagManager - normalizeTags - sb:", sb);
		return sb;
	},
	/**
	 * Remove any existing tags. Should be used before trying to add tags because
	 * adding duplicate tags for a user throws an error.
	 * @param tags - string for normalized tags
	 * @returns - string without tags already existing
	 */
	removeExistingTags: function(tagsArray){
		
		// Remove any possible spaces
		for(var i = 0; i < tagsArray.length; i++){
			tagsArray[i] = dojo.string.trim(tagsArray[i]);
		}
		
		// Check through the current tags store
		var tagStoreItems = this.tagsMap;
		
		// For each item in the store
		for(var key in tagStoreItems){
			var tagStoreItemText = tagStoreItems[key].label;
			
			// And for each tag being added
			for(var j = 0; j < tagsArray.length; j++){
				var addTag = tagsArray[j];
				
				// If the tag being added is already present in the store
				if(addTag == tagStoreItemText){
					// Remove it from the tags being added and save it for
					// later (for the confirmation message)
					this.alreadyExistingTags = this.alreadyExistingTags.concat(tagsArray.splice(j, 1));
				}
			}
		}
		
		// Join the array back into a string and return it
		return tagsArray.join(",");
	},
	/**
	 * Remove any repeating tags. 
	 */
	removeRepeatingTags: function(tags){
		var tagsArray = tags.split(",");
		var n = [];
		for(var i = 0; i < tagsArray.length; i++){
			if (dojo.indexOf(n,tagsArray[i]) == -1) {
				n.push(tagsArray[i]);
			}
		}
		return n;
	},
	/**
	 * Clear the status message
	 */
	clearMessage: function(){
		dojo.publish(this.clearMessageEventName);
	}
});