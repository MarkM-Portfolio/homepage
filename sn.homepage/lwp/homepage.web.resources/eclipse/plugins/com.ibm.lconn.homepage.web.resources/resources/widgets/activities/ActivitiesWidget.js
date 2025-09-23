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

dojo.provide("lconn.homepage.widgets.activities.ActivitiesWidget");

dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.core.common.ScrollerModel");
dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.homepage.widgets.activities.ActivitiesCalendar");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");

dojo.require("dijit.Dialog");

dojo.require("dojo.string");
dojo.require("dojo.date.locale");
dojo.require("lconn.core.util._XSLCache");

//dojo.require("dojox.date.posix");

dojo.requireLocalization("lconn.homepage", "widgetstrings");


dojo.require("lconn.core.globalization.BidiDateUtil");

dojo.declare(// widget name and class
"lconn.homepage.widgets.activities.ActivitiesWidget", // superclass
 lconn.homepage.core.base._BodyWidget, // properties and methods
{
    // summary: Dojo widget responsible for rendering the body of the Dashboard Activities widget
    // description: See design doc in the req database	
    
    templatePath: dojo.moduleUrl("lconn.homepage", "widgets/activities/templates/activitiesWidget.html"),
    
	xslCache : new (dojo.declare("", [lconn.core.util._XSLCache], {
		xslStrings: {"activities.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/activities/activities.xsl")},
			"publicActivities.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/activities/publicActivities.xsl")},
			"../activities.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/activities/activities.xsl")},
			"../publicActivities.xsl": {templatePath : dojo.moduleUrl("lconn.homepage", "widgets/activities/publicActivities.xsl")}} 		
	})),

    postCreate: function(){
        // summary: post create initialization		
        
        lconn.homepage.widgets.activities.ActivitiesWidget.superclass.postCreate.apply(this);
        
        this.userid = this._iContext.getUserProfile().getItemValue("userid");
        
        // array of handlers returned by dojo.subscribe. Used to unsubscribe properly in destroy()
        this._subHandler = [];
        
        this.popupNewResponsesNode.id = dijit.getUniqueId();
        this.popupHighPriorityNode.id = dijit.getUniqueId();
        this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();
        
        
     // subscribe to event published when the user clicks a day of the calendar marked with a todo task
        this._subHandler.push(dojo.subscribe(lconn.homepage.events.activitiesCalendar.DAY_FLAG, this, "showTodoPopup"));
        
        this._createModeMenu();
                
        // init and insert calendar into DOM

        this._calendarWidget = new lconn.homepage.widgets.activities.ActivitiesCalendar({});
        this._calendarWidget.setParent(this);
        
        dojo.place(this._calendarWidget.domNode, this.calendarNode, 0);
        
        // init today's date and year (displayed on the right end of the widget)			
        this.displayDates();
        
        this._currentTodoPopup = this.popupTodoNode;
        
        this.isActivitiesResponsePopulated = false;
        
        this.update();
    },
    
    postMixInProperties: function(){
    
        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
        
        this.noActivitiesTitle = this._resourceBundle.ACTIVITIES_NO_ACTIVITIES_TITLE;
        this.noActivitiesBodyExplaination1 = this._resourceBundle.ACTIVITIES_NO_ACTIVITIES_BODY_EXPLAINATION_1;
        this.noActivitiesBodyExplaination2 = this._resourceBundle.ACTIVITIES_NO_ACTIVITIES_BODY_EXPLAINATION_2;
        this.noHighPriorityTitle = this._resourceBundle.ACTIVITIES_NO_HIGH_PRIORITY_TITLE;
        this.noHighPriorityBodyExplaination1 = this._resourceBundle.ACTIVITIES_NO_HIGH_PRIORITY_BODY_EXPLAINATION_1;
        this.noHighPriorityBodyExplaination2 = this._resourceBundle.ACTIVITIES_NO_HIGH_PRIORITY_BODY_EXPLAINATION_2;
        this.linkActivities = this._parameters.mappingRemoteUrl;
        this.linkActivitiesMessage = this._resourceBundle.ACTIVITIES_LINK_ACTIVITIES_MESSAGE;
        this.activitiesImg = dojo.moduleUrl("lconn.homepage.widgets", "images/welcomeLogo/Activities64.png") + "?etag=" + ibmConfig.versionStamp;
        
        this.openActNewWindowStr = dojo.string.substitute(this._resourceBundle.ARIA_OPEN_IN_NEW_WINDOW_GENERIC, [this._resourceBundle.ARIA_ACTIVITY]);
        this.openHighPrioActNewWindowStr = dojo.string.substitute(this._resourceBundle.ARIA_OPEN_IN_NEW_WINDOW_GENERIC, [this._resourceBundle.ARIA_HIGH_PRIO_ACTIVITY]);
        this.openTodoNewWindowStr = dojo.string.substitute(this._resourceBundle.ARIA_OPEN_IN_NEW_WINDOW_GENERIC, [this._resourceBundle.ARIA_TODO]);
        this.openNewWindowStr = dojo.string.substitute(this._resourceBundle.ARIA_OPEN_IN_NEW_WINDOW_GENERIC, [""]);
    },
    
    displayDates: function(){
        // summary: Format today's date and year and insert it in the approproate DOM nodes
        // description:  Today's date is formatted as follows: abbreviated day week + day number + abbreviated month
        //        Year is the full year (ie: 1999, not 99)
        //	
        
        var today = new Date();
        var day = dojo.date.locale.format(today, {
            formatLength: "full",
            selector: "date"
        });
        
        //dojox.date.posix.strftime(today, "%a %e %b");
		//bidi support
		if(!lconn.core.globalization.BidiDateUtil.isGregorian())    
			day = lconn.core.globalization.BidiDateUtil.formatBidiDate(today, {selector: "date",  formatLength: "full"});
        
		this.dayNode.innerHTML = day;
        
        //this.yearNode.innerHTML = today.getFullYear();			
    },
    
    handleAsyncRequestEverything: function(/* XML Document */data,/* Dojo Ajax event*/ evt){
        // summary: callback method called after /everything Ajax response is received (after retrieveAtomAndUpdate()) 
        // description:  responsible for triggering the methods rendering the parts of the widget according to the Ajax response:
        //            - high priorities link + popup content
        // 		      - new responses link + popup content			//		      
        //	          - my activities link + popup content
        //       The rendering of the widget is done in an hidden node (containerNode). LoadingNode is displayed while rendering (spinner image)
        //       The containerNode is shown only after rendering of the different parts
        
        
        // this.data used by updateXXX() methods
        
        // TODO: remove this method and move high priority and activities	
        
        this.data = data;
        
        
        this.updateHighPriority();
        this.updateNewResponses();
        //this.updateCalendar();
        this.updateActivities();
        
        // display content, hide loading image
        this.loadingNode.style.display = "none";
        this.containerNode.style.display = "";
    },
    
    handleAsyncRequestResponses: function(/* XML Document */data,/* Dojo Ajax event*/ evt){
        this.data = data;
        
        this.updateNewResponses();
        
        // display content, hide loading image
        this.loadingNode.style.display = "none";
        this.containerNode.style.display = "";
    },
    
    handleAsyncRequestTodos: function(/* XML Doc*/data){
        this.updateCalendar(data);
    },
    
    _extractToDos: function(strResp){
        // summary: extract the todos from the str returned by the xslt sheet
        // description: we need a regex rather than eval()uating the string for security reasons
        //	related to JSON
        
        var todos = [];
        
        // extract todo chucks
        var regex = /{\"title\":\"([^\"]*)\",\"dueDate\":\"([^\"]*)\",\"link\":\"([^\"]*)\",\"assignedTo\":\"([^\"]*)\"}/g;
        
        var extractFct = dojo.hitch(this, "_extract", todos);
        strResp.replace(regex, extractFct);
        
        return todos;
    },
    
    _extract: function(todosArray){
        // summary: Process chucks for for _extractToDos()
        
        var todoObj = {
            "title": arguments[2],
            "dueDate": arguments[3],
            "link": arguments[4],
            "assignedTo": arguments[5]
        };
        todosArray.push(todoObj);
    },
    
    updateCalendar: function(data){
        // summary: Update the calendar with todos according to XML feed and XSL sheet
        // description: This method get a JSON string from the XSL sheet and update the calendar accordingly
        //       The XSL sheet returns a JSON string containing the todos	as follows:
        //       [{					
        //		     "title": "some title",
        //		     "dueDate": "ISO datetimestamp",
        //		     "link": ""	
        //	      }, ...]	
        
        var strResp = "";
		var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
        var xslParams = [
                         ['choice', 'todos'],
                         ['openActNewWindowStr', this.openActNewWindowStr],
                         ['openHighPrioActNewWindowStr', this.openHighPrioActNewWindowStr],
                         ['openTodoNewWindowStr', this.openTodoNewWindowStr],
                         ['openNewWindowStr', this.openNewWindowStr]
                        ];
        	
        if(xslDoc)
        	strResp = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
		else
			strResp = this.getXsltSupport().getXsltResult(data, xslParams, this._parameters.xsltUrl);
        
        this._activitiesToDo = this._extractToDos(strResp);
        
        var now = new Date();
        
        // update calendar widget with todos
        for (var i = 0; i < this._activitiesToDo.length; i++) {
            var dateStr = this._activitiesToDo[i].dueDate;
            // no due date
            if (dateStr != "") {
                this._activitiesToDo[i].dueDate = dojo.date.stamp.fromISOString(dateStr);
                
                var compareDate = dojo.date.compare(this._activitiesToDo[i].dueDate, now, "date");
                
                if (compareDate < 0) {
                    // overdue task
                    this._calendarWidget.addFlagDay(this._activitiesToDo[i].dueDate, "dayFlagOverdueTask", ">");
                }
                else 
                    if (compareDate == 0) {
                        // today task
                        this._calendarWidget.addFlagDay(this._activitiesToDo[i].dueDate, "dayFlagTodayTask", "#");
                    }
                    else {
                        // task in the future
                        if (this._activitiesToDo[i].assignedTo == this.userid) {
                            // personal task
                            this._calendarWidget.addFlagDay(this._activitiesToDo[i].dueDate, "dayFlagPersonalTask", "*");
                        }
                        else {
                            // not assigned (shared/team) task		
                            this._calendarWidget.addFlagDay(this._activitiesToDo[i].dueDate, "dayFlagTeamTask", "+");
                        }
                    }
            }
        }
        // display flag dates
        this._calendarWidget.refresh();
    },
    
    
    updateActivities: function(){
        var activitiesString = this._resourceBundle.ACTIVITIES;
        this.activitiesNode.innerHTML = activitiesString;
        
    },
    
    updateHighPriority: function(){
        // set localized title
        var newEntriesString = this._resourceBundle.ACTIVITIES_NEW_ENTRIES;
        this.highPriorityNode.innerHTML = newEntriesString;
    },
    
    _removeAllHtmlTagsInContainer: function(/* DOM Node */containerNode, /* string */ CSSMarker){
        // summary: Search for all nodes with CSSMarker as CSS class inside containerNode and 
        //		remove HTML tags in the innerHTML of these nodes
        
        var nodes = dojo.query("." + CSSMarker, containerNode);
        for (var i = 0; i < nodes.length; i++) {
            this.removeHTMLTags(nodes[i]);
        }
    },
    
    _formatDatesInContainer: function(/* DOM Node */containerNode, /* string */ CSSMarker){
        // summary: Search for all nodes with CSSMarker as CSS class inside containerNode and 
        //		format dates in the innerHTML of these nodes
        // description: innerHTML of the nodes should only contain a datestamp 
        
        var nodes = dojo.query("." + CSSMarker, containerNode);
        for (var i = 0; i < nodes.length; i++) {
            this.getDateFormatterSupport().formatDate(nodes[i]);
        }
    },
    
    updateNewResponses: function(){
        // summary: Set the "new responses" string and initialize the new responses popup with data
        // description: Get the "My Responses" string from the resource bundle and place it in the appropriate DOM node
        //       Get the HTML of the popup from the XSL sheet and do the following processings: 
        //			- format the the dates according to common Connections guideline + i18n
        //			- escape the HTML tags
        //			- insert localized titles
        //			- compute the number of new responses
        //		The formated HTML code is stored in the DOM (this.popupNewResponsesNode) and ready to be displayed (see displayNewResponses())     
        
        var html = "";
        
		var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
        var xslParams = [
                         ['choice', 'responses'],
                         ['openActNewWindowStr', this.openActNewWindowStr],
                         ['openHighPrioActNewWindowStr', this.openHighPrioActNewWindowStr],
                         ['openTodoNewWindowStr', this.openTodoNewWindowStr],
                         ['openNewWindowStr', this.openNewWindowStr]
                        ];
        	
        if(xslDoc)
        	html = lconn.core.xslt.transform(this.data, xslDoc, null, xslParams, true);
		else
			html = this.getXsltSupport().getXsltResult(this.data, xslParams, this._parameters.xsltUrl);
        
        // put the html in an hidden temp node for processing
        this.tempNode.innerHTML = html;
        
        // compute number of response			
        var numberResponses = dojo.query(".entry", this.tempNode).length;
        
        var innerNode;
        
        if (numberResponses > 0) {
            // escape html from nodes marked with the class "activities-escape-html"
            this._removeAllHtmlTagsInContainer(this.tempNode, "activities-escape-html");
            
            // format dates
            this._formatDatesInContainer(this.tempNode, "activities-date");
            
            // store result in hidden node. Ready to be displayed in a popup
            this.popupNewResponsesNode.innerHTML = this.tempNode.innerHTML;
            
            innerNode = dojo.doc.createElement("a");
            // SPR DMCE7DHJX4
            dojo.addClass(innerNode, "lotusAction");
            innerNode.setAttribute("role","button");
            innerNode.setAttribute("href", "#");
            dojo.connect(innerNode, "onclick", this, "displayResponses");
        }
        else {
            // no responses , no link
            innerNode = dojo.doc.createElement("span");
        }
        
        // set link in main activities widget
        var newResponsesString = dojo.string.substitute(this._resourceBundle.ACTIVITIES_NEW_RESPONSES, [numberResponses]);
        innerNode.innerHTML = newResponsesString;
        
        this.newResponsesNode.appendChild(innerNode);
    },
    
    displayActivities: function(){
        // summary: Fetch XML for my activities popup and call method to show the popup
        // description: First time this method is called:
        //            - fetch XML of the recent activities 
        //			  - call _showActivitiesPopop()
        //        Next times: directly call _showActivitiesPopop(). Don't fetch again the same feed (optimizations)
        //        The XML DOM document is not cached. The HTML of the popup is cached to optimized further
        this.popup.showPopup(this._resourceBundle.ACTIVITIES_TITLE_POPUP_ACTIVITIES);
        if (this._activitiesListXMLCache || this._activitiesHtmlCache) {
            this._showActivitiesPopop(this._activitiesListXMLCache);
        }
        else {
            var io = this.getIoSupport(this._iContext);
            dojo.connect(io, "handleAsyncRequest", this, "_showActivitiesPopop");
            io.retrieveAtomAndUpdate(this._feedUrls.activities, true);
        }
    },
    
    _showActivitiesPopop: function(/* XML Document */data,/* Dojo Ajax event*/ evt){
        // summary: Show "my activities" popup
        // description: First time: get the HTML of the popup from the XSL sheet and do the following processings: 
        //			- format the the dates according to common Connections guideline + i18n
        //			- escape the HTML tags
        //			- insert localized title
        //		
        //      The result is stored in this._activitiesHtmlCache and shown in a popup
        //		Next times: this._activitiesHtmlCache is used directly without re-producing the HTML code     
        
        if (this._activitiesHtmlCache == null) {
            var activitiesHTML;
            
    		var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
            var xslParams = [
                             ['choice', 'activities'],
                             ['openActNewWindowStr', this.openActNewWindowStr],
                             ['openHighPrioActNewWindowStr', this.openHighPrioActNewWindowStr],
                             ['openTodoNewWindowStr', this.openTodoNewWindowStr],
                             ['openNewWindowStr', this.openNewWindowStr]
                            ];
            	
            if(xslDoc)
            	activitiesHTML = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
    		else
    			activitiesHTML = this.getXsltSupport().getXsltResult(data, xslParams, this._parameters.xsltUrl);


            if (activitiesHTML != "") {
                // display activities			
                this.tempNode.innerHTML = activitiesHTML;
                
                var nodes = dojo.query(".activities-escape-html", this.tempNode);
                for (var i = 0; i < nodes.length; i++) {
                    this.removeHTMLTags(nodes[i]);
                }
                
                nodes = dojo.query(".activities-date", this.tempNode);
                for (var i = 0; i < nodes.length; i++) {
                    this.getDateFormatterSupport().formatDate(nodes[i]);
                }
                
                nodes = dojo.query(".activities-icon", this.tempNode);
                for (var i = 0; i < nodes.length; i++) {
                    var node = nodes[i];
                    node.setAttribute("alt", this._resourceBundle.ACTIVITIES_ALT);
                    node.setAttribute("title", this._resourceBundle.ACTIVITIES_ALT);
                }
                
                //this.removeHTMLTags(this.tempNode);
                this._activitiesHtmlCache = this.tempNode.innerHTML;
                
            }
            else {
            	var ariadescribedby ='';
            	var noActivitiesNodeClone = dojo.query(this.noActivitiesNode)[0].cloneNode(true);
            	dojo.query("div > div#noActivitiesDesc", noActivitiesNodeClone).forEach(function(item, i){
    				item.id=item.id+"_Dialog";	
    				ariadescribedby=item.id;
    			});
            	dojo.query("div > span", noActivitiesNodeClone).forEach(function(item, i){
            		item.setAttribute("aria-describedby",ariadescribedby);			
    			});
                this._activitiesHtmlCache = noActivitiesNodeClone.innerHTML;
            }
        }
        var node = this.popup.setContent(this._activitiesHtmlCache);
        
        try {
            SemTagSvc.parseDom(null, node);
        } 
        catch (e) {
        	hp_console_debug("SemTag error");
        }
        // Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(node);
    },
    
    removeHTMLTags: function(/* DOM Node*/node){
        // summary: Remove the HTML tags (and any content included inside) from node.innerHTML			
        var str = node.innerHTML;
        
        // remove all tags
        str = str.replace(/&gt;/g, ">");
        str = str.replace(/&lt;[a-zA-Z\/][^>]*>/g, "");
        
        // replace &amp; with &
        str = str.replace(/&amp;/g, "&");
        
        node.innerHTML = str;
    },
    
    _showHighPriorityPopup: function(/* XML Document */data,/* Dojo Ajax event*/ evt){
        // summary: Initialize the high priority popup with data
        // description: see _showActivitiesPopup() description. Same process.
        
        if (this._highPriorityHtmlCache == null) {
            var highPriorityHTML;
            
    		var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
            var xslParams = [
                             ['choice', 'highPriorityReponses'],
                             ['openActNewWindowStr', this.openActNewWindowStr],
                             ['openHighPrioActNewWindowStr', this.openHighPrioActNewWindowStr],
                             ['openTodoNewWindowStr', this.openTodoNewWindowStr],
                             ['openNewWindowStr', this.openNewWindowStr]
                            ];
            	
            if(xslDoc)
            	highPriorityHTML = lconn.core.xslt.transform(data, xslDoc, null, xslParams, true);
    		else
    			highPriorityHTML = this.getXsltSupport().getXsltResult(data, xslParams, this._parameters.xsltUrl);
            	
            
            if (highPriorityHTML != "") {
                // display activities			
                this.tempNode.innerHTML = highPriorityHTML;
                
                var nodes = dojo.query(".activities-escape-html", this.tempNode);
                for (var i = 0; i < nodes.length; i++) {
                    this.removeHTMLTags(nodes[i]);
                }
                
                nodes = dojo.query(".activities-date", this.tempNode);
                for (var i = 0; i < nodes.length; i++) {
                    this.getDateFormatterSupport().formatDate(nodes[i]);
                }
                
                nodes = dojo.query(".activities-icon", this.tempNode);
                for (var i = 0; i < nodes.length; i++) {
                    var node = nodes[i];
                    node.setAttribute("alt", this._resourceBundle.ACTIVITIES_ALT);
                    node.setAttribute("title", this._resourceBundle.ACTIVITIES_ALT);
                }
                
                //this.removeHTMLTags(this.tempNode);
                this._highPriorityHtmlCache = this.tempNode.innerHTML;
                
            }
            else {
            	var ariadescribedby ='';
            	var noHighPriorityNodeClone = dojo.query(this.noHighPriorityNode)[0].cloneNode(true);
            	dojo.query("div > div#noHighPriorityDesc", noHighPriorityNodeClone).forEach(function(item, i){
    				item.id=item.id+"_Dialog";	
    				ariadescribedby=item.id;
    			});
            	dojo.query("div > span", noHighPriorityNodeClone).forEach(function(item, i){
            		item.setAttribute("aria-describedby",ariadescribedby);			
    			});
            	
                this._highPriorityHtmlCache = noHighPriorityNodeClone.innerHTML;
            }
        }
        var node = this.popup.setContent(this._highPriorityHtmlCache);
        
        try {
            SemTagSvc.parseDom(null, node);
        } 
        catch (e) {
        	hp_console_debug("SemTag error");
        }
        // Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(node);
    },
    
    showTodoPopup: function(/* Widget */parent, /* Date */ date,/* Event */ e){
        // summary: Format a show a popup with a list of task todos for a given date
        // description: This method is called when the user clicks a day in the calendar marked with a todo			
        
        // build html of the popup from this._activitiesToDo
        
        // SPR PMAN7QQEBK - Need to check the target of the dojo.publish
        if ((parent != null) && (parent == this)) {
        
            var html = "<div class='notelist'>";
            
            for (var i = 0; i < this._activitiesToDo.length; i++) {
                var todo = this._activitiesToDo[i];
                
                // only add todo tasks with due dates for now
                if (todo.dueDate != "") {
                    if (dojo.date.compare(date, todo.dueDate, "date") == 0) 
                        html += this._buildPopupHtml(todo);
                }
            }
            
            html += "</div>";
            this.popupTodoNode.innerHTML = html;
            // Bidi support
	      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.popupTodoNode);
	      	this.toDoPopup = new dijit.TooltipDialog({"class": "activitiesCalPopup", role: "dialog", "aria-label": this._resourceBundle.ARIA_TODO}, this.popupTodoNode);
            var popup = this.toDoPopup;
            
            dijit.popup.open({
                parent: this,
                popup: popup,
                around: e.target,
                onExecute: function(){
                },
                onCancel: function(){
                    dijit.popup.close(popup);
                },
                onClose: function(){
                    popup.destroy();
                    e.target.focus();
                }
            });
            
            dojo.addClass(this.toDoPopup.domNode, "popup");

            this.toDoPopup.focus();
            dojo.connect(popup, "_onBlur", this, "_onCalendarBlur");
            
            dojo.stopEvent(e);
        }
    },
    
    _onCalendarBlur: function(evt){
        dijit.popup.close(this.toDoPopup);
    },
    
    _populateActivitiesResponse: function(){
		var xslDoc = this.xslCache.getXslDoc(this._parameters.xsltUrl);
        var xslParams = [
                         ['choice', 'activities-list'],
                         ['openActNewWindowStr', this.openActNewWindowStr],
                         ['openHighPrioActNewWindowStr', this.openHighPrioActNewWindowStr],
                         ['openTodoNewWindowStr', this.openTodoNewWindowStr],
                         ['openNewWindowStr', this.openNewWindowStr]
                        ];
        	
        if(xslDoc)
        	this.tempNode.innerHTML = lconn.core.xslt.transform(this._activitiesListXMLCache, xslDoc, null, xslParams, true);
		else
			this.tempNode.innerHTML = this.getXsltSupport().getXsltResult(this._activitiesListXMLCache, xslParams, this._parameters.xsltUrl);
        	
        
        var activitiesToPopulate = dojo.query(".activities-activity-details", this.popupNewResponsesNode);
        
        for (var i = 0; i < activitiesToPopulate.length; i++) {
            var activityNode = activitiesToPopulate[i];
            var id = activityNode.id;
            
            var activityTemplate = dojo.query("." + id, this.tempNode)[0];
            
			if (activityTemplate != null) {
				activityNode.innerHTML = activityTemplate.innerHTML;
				
				var nodes = dojo.query(".activities-link", activityNode);
				for (var j = 0; j < nodes.length; j++) {
					var node = nodes[j];
					var title = this.openActNewWindowStr;
					var prio = node.getAttribute("act_priority");
					if ( prio == "High" ) {
						title = this.openHighPrioActNewWindowStr;
					}
					node.setAttribute("alt", title);
					node.setAttribute("title", title);
				}
			}
        }
        
        this.isActivitiesResponsePopulated = true;
    },
    
    _showResponsesPopup: function(data, evt){
        this._activitiesListXMLCache = data;
        
        if (!this.isActivitiesResponsePopulated) 
            this._populateActivitiesResponse();
        
        this.popup.showPopup(this._resourceBundle.ACTIVITIES_TITLE_POPUP_RESPONSES);
        var node = this.popup.setContent(this.popupNewResponsesNode.innerHTML);
        
        try {
            SemTagSvc.parseDom(null, node);
        } 
        catch (e) {
        	hp_console_debug("SemTag error");
        }
        // Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(node);
    },
    
    displayResponses: function(/* Event*/e){
        // summary: Show new responses popup (content of this.popupNewResponsesNode.innerHTML)
        //   Need to fetch list of activities first				
        
        if (this._activitiesListXMLCache != null) {
            this._showResponsesPopup(this._activitiesListXMLCache);
        }
        else {
            var io = this.getIoSupport(this._iContext);
            dojo.connect(io, "handleAsyncRequest", this, "_showResponsesPopup");
            
            var url = this._feedUrls.activities + "?ps=100";
            io.retrieveAtomAndUpdate(url, true);
        }
    },
    
    displayHighPriority: function(/* Event */e){
        // summary: Show high priorities popup (content of this.popupHighPriorityNode.innerHTML)	
        
        this.popup.showPopup(this._resourceBundle.ACTIVITIES_TITLE_POPUP_ENTRIES);
        if (this._highPriorityHtmlCache) {
            this._showHighPriorityPopup();
            return;
        }
        
        var io = this.getIoSupport(this._iContext);
        dojo.connect(io, "handleAsyncRequest", this, "_showHighPriorityPopup");
        
        var url = this._feedUrls.defaultURL + "?since=" + this._getRecentMillisecondFromOptions() + "&ps=100";
        
        io.retrieveAtomAndUpdate(url, true);
    },
    
    // partially from dojo 0.4 (dojo.string.summary)
    summarize: function(str, length){
        if (!length || str.length <= length) {
            return str;
        }
        else 
            return str.substring(0, length).replace(/\.+$/, "") + "...";
    },
    
    createShortSummary: function(node){
        node.innerHTML = this.summarize(node.innerHTML, 50);
    },
    
    
    _buildPopupHtml: function(/* Todo Obj, see description*/todoObj){
        // summary: build html code of a todo item from a todo Obj
        // description: a todo obj is as follows:
        //			{					
        //		     "title": "some title",
        //		     "dueDate": "ISO datetimestamp",
        //		     "link": "string"	
        //	        }
        // return: html code of a todo item
    	
    	var linkTitle = dojo.string.substitute(this._resourceBundle.ARIA_OPEN_IN_NEW_WINDOW_GENERIC, [this._resourceBundle.ARIA_TODO]);
        
        var html = "<div class='notes'>";
        html += "<div class='note'>";
        html += "<table role='presentation'><tr><td>";
        html += "<div class='icon'><img class='lconnSprite lconnSprite-iconToDo16' alt='' src='" + djConfig.blankGif + "'></div>";
        html += "</td><td style='white-space: nowrap'>";
        html += "<a target='_blank' class='notetext bidiAware' " + 
        			"title='" + linkTitle + "' aria-label='" + todoObj.title + "' " + 
        			"href='" + todoObj.link + "'>" + this.summarize(todoObj.title, 35) + "</a>";
        html += "</td></tr></table>";
        html += "</div>";
        
        return html;
    },
    
    destroy: function(){
        // summary: called when the widget is destroyed
        // description: mostly clean-up code: hide popup if needed and unsubscribe to events
        
        dijit.popup.close(this.popup);
        this.destroyPopupNodes();
        
        for (var i = 0; i < this._subHandler.length; i++) 
            dojo.unsubscribe(this._subHandler[i]);
        
        lconn.homepage.widgets.activities.ActivitiesWidget.superclass.destroy.apply(this);
    },
    
    destroyPopupNodes: function(){
        // summary: destroy popups properly 
        // description: MenuPopup moves the popup nodes in the DOM tree, outside the widget div container
        //       Therefore, destroy() does not destroy correctly those nodes, we have to do that manually 
        
        //this.popupTodoNode.parentNode.removeChild(this.popupTodoNode);
		
		// SPR AVEI7S8PRY
		// turns out that templated nodes are already destroyed by _Widget parent at this stage,
		// so no need to do it ourself
        //this.popupNewResponsesNode.parentNode.removeChild(this.popupNewResponsesNode);
       // this.popupHighPriorityNode.parentNode.removeChild(this.popupHighPriorityNode);
    },
    
    displayError: function(messageObj){
        // summary: overriden to hide loadingnode		   
        this.loadingNode.style.display = "none";
        
        var handler = this.getErrorHandler();
        handler.setErrorNode(this.errorNode);
        handler.displayError(messageObj);
    },
    
    _getRecentMillisecondFromOptions: function(){
        var optionIndex = this._optionSet.getItemValue("recent");
        
        var numberOfDayToRemove = 0;
        
        // show responses posted within the 7 last days
        // format the date 7 days ago as YEARMONTHDAY (ie: 20071106) to pass it to the XSL sheet
        var dateRef = new Date();
        
        switch (parseInt(optionIndex)) {
            case 1:
                // last day
                numberOfDayToRemove = 1;
                break;
            case 2:
                // last 3 days
                numberOfDayToRemove = 3;
                break;
            case 3:
                // last week
                numberOfDayToRemove = 7;
                break;
            case 4:
                // last 2 weeks
                numberOfDayToRemove = 14;
                break;
            case 5:
                // last 2 weeks
                numberOfDayToRemove = 31;
                break;
            default:
            	hp_console_debug("should not get here");
        }
        
        dateRef.setDate(dateRef.getDate() - numberOfDayToRemove);
        // TODO: see if requirements implies to set hour to midnight			
        return dateRef.getTime();
    },
    
    update: function(){
        // set parameters from catalog
        
        this.remoteUrls = [];
        this.remoteUrls[0] = this._feedUrls.responses + this.userid + "&since=" + this._getRecentMillisecondFromOptions() + "&ps=100";
        this.remoteUrls[1] = this._feedUrls.todos + "?assignedToUserid=" + this.userid + "&includeUnassigned=yes&completed=no" + "&ps=100";
        this.retrieveMultipleFeeds();
    },
    
    multipleFeedHandler: function(feedResultMap){
        var everythingMap = feedResultMap.item(this.remoteUrls[0]);
        var todoMap = feedResultMap.item(this.remoteUrls[1]);
        
        if (everythingMap.status == 0) {
            this.displayError({
                exceptionToDisplay: everythingMap.data
            });
            return;
        }
        
        if (todoMap.status == 0) {
            this.displayError({
                exceptionToDisplay: todoMap.data
            });
            return;
        }
        
        this.handleAsyncRequestResponses(everythingMap.data, null);
        this.handleAsyncRequestTodos(todoMap.data, null);
        
        this.updateActivities();
        this.updateHighPriority();
        
    },
    
    // private members
    
    // _activitiesToDo: Array
    // 		Array of todos in the following format:
    //      [{					
    //		     "title": "some title",
    //		     "dueDate": "ISO datetimestamp",
    //		     "link": ""	
    //	     }, ...]
    _activitiesToDo: null,
    
    // _calendarWidget: Widget object  	
    _calendarWidget: null,
    
    // _currentTodoPopup: DOM node
    //		Node shown in a popup. Should contain a todo list for a given day
    _currentTodoPopup: null,
    
    // _resourceBundle: JSON object
    //		Contains localized strings for the current browser locale
    _resourceBundle: null,
    
    // _activitiesHtmlCache: DOM node
    //		Node shown in a popup. Should contain the list of activities
    _activitiesHtmlCache: null,
    
    // _highPriorityHtmlCache: DOM node
    //		Node shown in a popup. Should contain the list of entries in high priority nodes
    _highPriorityHtmlCache: null,
    
    // _activitiesListXMLCache: XML Document
    //		Should contain the list of activites for recent responses
    _activitiesListXMLCache: null,
    
    // _subHandler: Array
    //		Contains handlers returned by dojo.subscribe. Used to unsubscribe easily
    _subHandler: null,
    
    // recentMs: long
    //		Set the lower limit in ms since 1970 for the "recent" (passed as "since" param to the feeds)
    recentMs: null,
    
    // _optionSet: ItemSet
    //		Reference to the options itemset. Passed by the iWidget wrapper on creation
    _optionSet: null,
    
    fakeWidget: null,
    
    isActivitiesResponsePopulated: null,
    
    
    // attached nodes (see template file)		
    
    calendarNode: null,
    dayNode: null,
    popupTodoNode: null,
    toDoPopup: null,
    newResponsesNode: null,
    noticesNode: null,
    highPriorityNode: null,
    popupNewResponsesNode: null,
    popupHighPriorityNode: null,
    loadingNode: null,
    noticesIconNode: null,
    activitiesNode: null,
    noActivitiesNode: null,
    noHighPriorityNode: null,
    noActivitiesTitle: null,
    noActivitiesBodyExplaination1: null,
    noActivitiesBodyExplaination2: null,
    noHighPriorityTitle: null,
    noHighPriorityBodyExplaination1: null,
    noHighPriorityBodyExplaination2: null,
    linkActivities: null,
    linkActivitiesMessage: null,
    welcomeImage: null,
    activitiesImg: null,
    _iContext: null,
    userid: null
});
