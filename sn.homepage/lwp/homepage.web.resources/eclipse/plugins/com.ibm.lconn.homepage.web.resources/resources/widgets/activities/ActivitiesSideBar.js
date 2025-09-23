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

dojo.provide("lconn.homepage.widgets.activities.ActivitiesSideBar");

dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.core.common.ScrollerModel");
dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.homepage.widgets.activities.ActivitiesCalendar");
dojo.require("lconn.homepage.dijit.popup.PopupWidgetFactory");

dojo.require("dijit.Dialog");

dojo.require("dojo.string");
dojo.require("dojo.date.locale");

dojo.requireLocalization("lconn.homepage", "widgetstrings");


dojo.declare(// widget name and class
"lconn.homepage.widgets.activities.ActivitiesSideBar", // superclass
lconn.homepage.core.base._BodyWidget, // properties and methods
{
    // summary: Dojo widget responsible for rendering the body of the Dashboard Activities widget
    // description: See design doc in the req database	
    
    templatePath: dojo.moduleUrl("lconn.homepage", "widgets/activities/templates/activitiesSideBar.html"),
    
    postCreate: function(){
        // summary: post create initialization		
        
        lconn.homepage.widgets.activities.ActivitiesSideBar.superclass.postCreate.apply(this);
        
        // array of handlers returned by dojo.subscribe. Used to unsubscribe properly in destroy()
        this._subHandler = [];
        
		this.userid = this._iContext.getUserProfile().getItemValue("userid");
		
        this.popupTodoNode.id = dijit.getUniqueId();
        this.popupTodoNode2.id = dijit.getUniqueId();
        
        this.linkActivitiesNode.href = this.linkActivities;
        
        this.popup = lconn.homepage.dijit.popup.PopupWidgetFactory.prototype.getInstance();
        
        // init images
        this.loadingImageNode.src = lconn.core.url.getServiceUrl(lconn.core.config.services.webresources)
		 + "/web/com.ibm.lconn.core.styles.oneui3/images/loading.gif?etag=" + ibmConfig.versionStamp;
      					        
        // subscribe to event published when the user clicks a day of the calendar marked with a todo task
        this._subHandler.push(dojo.subscribe(lconn.homepage.events.activitiesCalendar.DAY_FLAG, this, "showTodoPopup"));
        
        // init and insert calendar into DOM        
        this._calendarWidget = new lconn.homepage.widgets.activities.ActivitiesCalendar({});
        this._calendarWidget.setParent(this);
        
        dojo.place(this._calendarWidget.domNode, this.calendarNode, 0);      
        
        this._currentTodoPopup = this.popupTodoNode;
        
        this.isActivitiesResponsePopulated = false;
        
        // SPR TSOE7TKJST
		if (dojo.config.locale.indexOf("ja") != -1) {
			dojo.style(this.linkActivitiesNode, "fontSize", "0.85em");
		}
        
        this.update();
    },
    
    postMixInProperties: function(){
        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
        
        this.linkActivities = this._parameters.mappingRemoteUrl;
        this.linkActivitiesMessage = this._resourceBundle.ACTIVITIES_LINK_ACTIVITIES_MESSAGE;
        this.activitiesImg = dojo.moduleUrl("lconn.homepage.widgets", "images/welcomeLogo/Activities64.png") + "?etag=" + ibmConfig.versionStamp;	
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
        
        this.dayNode.innerHTML = day;
        
        //this.yearNode.innerHTML = today.getFullYear();			
    },
    
    handleAsyncRequestTodos: function(/* XML Doc*/data){
        this.updateCalendar(data);
        this.loadingNode.style.display = "none";
        this.containerNode.style.display = "";
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
        strResp = this.getXsltSupport().getXsltResult(data, [['choice', 'todos']], this._parameters.xsltUrl);
        
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
    
    _removeAllHtmlTagsInContainer: function(/* DOM Node */containerNode, /* string */ CSSMarker){
        // summary: Search for all nodes with CSSMarker as CSS class inside containerNode and 
        //		remove HTML tags in the innerHTML of these nodes
        
        var nodes = dojo.query("." + CSSMarker, containerNode);
        for (var i = 0; i < nodes.length; i++) {
            this.removeHTMLTags(nodes[i]);
        }
    },
    
    removeHTMLTags: function(/* DOM Node*/node){
        // summary: Remove the HTML tags (and any content included inside) from node.innerHTML			
        var str = node.innerHTML;
        
        // remove all tags
        str = str.replace(/&gt;/g, ">");
        str = str.replace(/&lt;[a-zA-Z\/][^>]*>/g, "");
        
        str = str.replace(/&amp;[^;]*;/g, "");
        str = str.replace(/&[^;]*;/g, "");
        
        node.innerHTML = str;
    },
    
    showTodoPopup: function(/* Widget*/parent, /* Date */ date,/* Event */ e){
        // summary: Format a show a popup with a list of task todos for a given date
        // description: This method is called when the user clicks a day in the calendar marked with a todo			
        
        // SPR PMAN7QQEBK - Need to check the target of the dojo.publish
        if ((parent != null) && (parent == this)) {
            // build html of the popup from this._activitiesToDo
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
            this.toDoPopup = new dijit.TooltipDialog({}, this.popupTodoNode);
            var popup = this.toDoPopup;
            
            dijit.popup.open({
                parent: this,
                popup: popup,
                around: e.target,
                orient: {
                    'BL': 'TR'
                },
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
            dojo.connect(popup, "_onBlur", this, "_onCalendarBlur");
            dojo.stopEvent(e);
        }
    },
    
    _onCalendarBlur: function(evt){
        dijit.popup.close(this.toDoPopup);
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
        
        var html = "<div class='notes'>";
        html += "<div class='note'>";
        html += "<div class='icon'><img src='" + dojo.moduleUrl("lconn.homepage.widgets", "images/icon_note.gif") + "'></div>";
        html += "<a target='_blank' class='notetext bidiAware' href='" + todoObj.link + "'>" + this.summarize(todoObj.title, 35) + "</a>";
        html += "</div>";
        
        return html;
    },
    
    destroy: function(){
        // summary: called when the widget is destroyed
        // description: mostly clean-up code: hide popup if needed and unsubscribe to events			
        
        for (var i = 0; i < this._subHandler.length; i++) 
            dojo.unsubscribe(this._subHandler[i]);
        
        lconn.homepage.widgets.activities.ActivitiesSideBar.superclass.destroy.apply(this);
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
        this.remoteUrls[0] = this._feedUrls.todos + "?assignedToUserid=" + this.userid + "&includeUnassigned=yes&completed=no" + "&ps=100";
        this.retrieveMultipleFeeds();
    },
    
    multipleFeedHandler: function(feedResultMap){
        //var everythingMap = feedResultMap.item(this.remoteUrls[0]);	
        var todoMap = feedResultMap.item(this.remoteUrls[0]);
        
        /*
         if (everythingMap.status == 0){
         this.displayError({
         exceptionToDisplay: everythingMap.data
         });
         return;
         }*/
        if (todoMap.status == 0) {
            this.displayError({
                exceptionToDisplay: todoMap.data
            });
            return;
        }
        
        //this.handleAsyncRequestResponses(everythingMap.data, null);						
        this.handleAsyncRequestTodos(todoMap.data, null);
        
        //this.updateActivities();
        //this.updateHighPriority();
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
    loadingNode: null,
    loadingImageNode: null,
    noticesIconNode: null,
    activitiesIconNode: null,
    linkActivitiesNode: null,
    linkActivities: null,
    linkActivitiesMessage: null,
    welcomeImage: null,
    activitiesImg: null,
    _iContext: null,
	userid: null
});
