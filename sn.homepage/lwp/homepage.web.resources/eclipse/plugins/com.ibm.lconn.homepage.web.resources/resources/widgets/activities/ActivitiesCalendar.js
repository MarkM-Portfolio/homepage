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

dojo.provide("lconn.homepage.widgets.activities.ActivitiesCalendar");

dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.core.DateUtil");
dojo.require("dijit.Calendar");
dojo.require("dojo.cldr.supplemental");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

dojo.require("dojox.date.islamic.Date");
dojo.require("dojox.date.islamic.locale");
dojo.require("dojox.date.islamic");
dojo.require("dojox.date.hebrew");
dojo.require("dojox.date.hebrew.locale");
dojo.require("dojox.date.hebrew.Date");
dojo.require("lconn.core.globalization.api");

dojo.declare(// widget name and class
"lconn.homepage.widgets.activities.ActivitiesCalendar", // superclass
dijit.Calendar, // properties and methods
{
    // summary: A calendar that implements markable/flagged days that can be clicked on. Adds other improvements as well to dojo calendar 
    // description: This class extends the dojo standard _Calendar widget. 
    //        It adds a mechanism to highlight given days (add_flagDays()). 
    //		  When the user clicks a highlighted day, the event "/activitiesCalendar/dayFlag" is published 
    //		  with the date clicked as first parameter.
    //
    //		  The cell of the marked day use the "dayFlag" CSS class. Define this CSS class in the page 
    //		  using this widget to see the highlighted days
    //
    //		  Note: For optimization reasons, the marked days are NOT displayed until you call refresh() 
    //				or you pass true to the second parameters of add/remove_flagDays
    //    
    //		  Beside the highlight days, this class also display the appropriate year in the month scroller box
    // 
    // example: See Activities widget		
	constructor: function(args){
		//bidi support
			if(lconn.core.globalization.api.getCalendar() == lconn.core.globalization.config.CALENDAR.HIJRI)
				args.datePackage = "dojox.date.islamic";
			else if(lconn.core.globalization.api.getCalendar() == lconn.core.globalization.config.CALENDAR.HEBREW)
				args.datePackage = "dojox.date.hebrew";
			if(lconn.core.globalization.api.getCalendar() != lconn.core.globalization.config.CALENDAR.GREGORIAN){
				this.dateModule = args.datePackage ? dojo.getObject(args.datePackage, false) : date;
				this.dateClassObj = this.dateModule.Date || Date;
				this.dateLocaleModule = args.datePackage ? dojo.getObject(args.datePackage+".locale", false) : locale;
				}
			this.inherited(arguments);	
	}, 
	
    postCreate: function(){
        // summary: post create initialization	
    	this.inherited(arguments);
    	
    	this._handlers = [];	
    },
    
    postMixInProperties: function(){
        //lconn.homepage.widgets.activities.ActivitiesCalendar.superclass.postMixInProperties.apply(this, arguments);
        
        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
    },

    _initialPopulateGrid: function(){
            // summary:
            //      Fills in the calendar grid with each day (1-31).
            //      Call this on creation, when moving to a new month.
            // tags:
            //      private

            var month = new this.dateClassObj(this.currentFocus);
            month.setDate(1);

            var firstDay = month.getDay(),
                daysInMonth = this.dateModule.getDaysInMonth(month),
                daysInPreviousMonth = this.dateModule.getDaysInMonth(this.dateModule.add(month, "month", -1)),
                today = new this.dateClassObj(),
                dayOffset = dojo.cldr.supplemental.getFirstDayOfWeek(this.lang);
            if(dayOffset > firstDay){
                dayOffset -= 7;
            }

            // If they didn't provide a summary, change the default summary to match with the new month
            if(!this.summary){
                var monthNames = this.dateLocaleModule.getNames('months', 'wide', 'standAlone', this.lang, month)
                this.gridNode.setAttribute("summary", monthNames[month.getMonth()]);
            }

            // Mapping from date (as specified by number returned from Date.valueOf()) to corresponding <td>
            this._date2cell = {};

            // Iterate through dates in the calendar and fill in date numbers and style info
            dojo.forEach(this.dateCells, function(template, idx){
                var i = idx + dayOffset;
                var date = new this.dateClassObj(month),
                    number, clazz = "dijitCalendar", adj = 0;

                if(i < firstDay){
                    number = daysInPreviousMonth - firstDay + i + 1;
                    adj = -1;
                    clazz += "Previous";
                }else if(i >= (firstDay + daysInMonth)){
                    number = i - firstDay - daysInMonth + 1;
                    adj = 1;
                    clazz += "Next";
                }else{
                    number = i - firstDay + 1;
                    clazz += "Current";
                }

                if(adj){
                    date = this.dateModule.add(date, "month", adj);
                }
                date.setDate(number);

                if(!this.dateModule.compare(date, today, "date")){
                    clazz = "dijitCalendarCurrentDate " + clazz;
                }

                if(this.isDisabledDate(date, this.lang)){
                    clazz = "dijitCalendarDisabledDate " + clazz;
                    template.setAttribute("aria-disabled", "true");
                }else{
                    clazz = "dijitCalendarEnabledDate " + clazz;
                    template.removeAttribute("aria-disabled");
                    template.setAttribute("aria-selected", "false");
                }

                var clazz2 = this.getClassForDate(date, this.lang);
                if(clazz2){
                    clazz = clazz2 + " " + clazz;
                }

                template.className = clazz + "Month dijitCalendarDateTemplate";

                // Each cell has an associated integer value representing it's date
                var dateVal = date.valueOf();
                this._date2cell[dateVal] = template;
                template.dijitDateValue = dateVal;

                // Set Date string (ex: "13").
                this._setText(this.dateLabels[idx], date.getDateLocalized ? date.getDateLocalized(this.lang) : date.getDate());
            }, this);
    

    
    },
    
    _populateGrid: function(){
        // summary: Overriden method. Adds mechanism to render the highlighted days + years in month box
        this._initialPopulateGrid();
            
        // highlight specified dates (typically todos)	
        // calendar days boxes	
        var nodes = dojo.query(".dijitCalendarEnabledDate", this.domNode);
        var dateDay = new Date();
        
        if (this._flagDays==null){
        	this._flagDays=[];
        }
        
        // go through all the display days and check if it should be highlighted		
        for (var i = 0; i < nodes.length; i++) {
            // .dijitDateValue is set by parent dojo class for each day
            // set a date object from this value
            dateDay.setTime(nodes[i].dijitDateValue);
            
            // is it a flagged day?
            // TODO: optimize (no need to check all the date, the current month should be enough
            for (var j = 0; j < this._flagDays.length; j++) {
                if (dojo.date.compare(dateDay, this._flagDays[j].date, "date") == 0) {
                    // "mark" the highlighted day						
                    nodes[i].className += " " + this._flagDays[j].style + " " + "dayFlag";
                    
                    if (this._flagDays[j].a11yChar != null) {
                        var spanDate = dojo.query(".dijitCalendarDateLabel", nodes[i])[0];
                        spanDate.innerHTML = this._flagDays[j].a11yChar + spanDate.innerHTML;
                        
                        if (dojo.isIE) {
                            spanDate.setAttribute("tabIndex", "0");
                        }
                        else {
                            spanDate.setAttribute("tabindex", "0");
                        }
                        
                        spanDate.setAttribute("role", "button");
                        spanDate.setAttribute("aria-hidden", "false");
                        
                        var dateStr = lconn.core.DateUtil.getLocalizedDate(dateDay);
                        //spanDate.setAttribute("aria-label", dateStr);
                        
                        var titleStr = dojo.string.substitute(this._resourceBundle.ACTIVITIES_ARIA_TODOS_DUE, [dateStr]);
                        
                        var parentNode = spanDate.parentNode;
                        
                        if (dojo.hasClass(parentNode, "dayFlagOverdueTask")) {
                        	titleStr = dojo.string.substitute(this._resourceBundle.ACTIVITIES_ARIA_OVERDUE_TODOS_DUE, [dateStr]);
                        } else if (dojo.hasClass(parentNode, "dayFlagTodayTask")) {
                        	titleStr = this._resourceBundle.ACTIVITIES_ARIA_TODOS_TODAY;
                        }
                        spanDate.setAttribute("aria-label", titleStr);
                        
                        this._handlers.push(dojo.connect(spanDate, "onkeypress", this, "_onDayKeyPress"));
                    }
                    
                    break;
                }
            }
        }

    },    

    refresh: function(){
        // summary: refresh the calendar view. Should be called after adding or removing flagged days
        this._populateGrid();
    },
    
    addFlagDay: function(/* Date */date, /* String */ cssStyle, /* String?*/ a11yChar,/* Boolean? */ refresh){
        // summary: Add a highlighted day to the calendar
        // description: After adding a day, you should call refresh() to see it
        //		  If refresh (2nd args) is true, add_flagDays call refresh() automatically			
        this._flagDays.push({
            date: date,
            style: cssStyle,
            a11yChar: a11yChar
        });
        if (refresh) 
            this.refresh();
    },

    _onDayKeyPress: function(/* Event */evt){
        if (evt.keyCode == dojo.keys.ENTER) {
            this._openMenuPopop(evt);
        }
    },
    
    _onDayClick: function(/* Event */evt){
        // summary: Overriden method. Adds mechanism to handle click on an highlighted day 
        this._openMenuPopop(evt);
    },
    
    _openMenuPopop: function(/* Event */evt){
        // get clicked day box	
        var evtTarget = evt.target;
        while (!evtTarget.dijitDateValue) {
            evtTarget = evtTarget.parentNode;
        }
        
        // day with flag mark (a todo)?
        if (dojo.hasClass(evtTarget, "dayFlag")) {
            // publish an event with the date of the day			
            var date = new Date();
            date.setTime(evtTarget.dijitDateValue);
            
            dojo.publish(lconn.homepage.events.activitiesCalendar.DAY_FLAG, [this._parent, date, evt]);
        }
    },
    
    setParent: function(parent){
        this._parent = parent;
    },
    
    
    //removeFlagDay: function(/* Date */date, /* Boolean? */refresh){	
    // summary: Remove a highlighted day from the calendar
    // description: After removing a day, you should call refresh() to the changes
    //		  If refresh (2nd args) is true, add_flagDays call refresh() automatically		
    
    // TODO: use dojo.date.compare instead
    //		this._flagDays.pop(date);			
    
    //		if (refresh)
    //			this.refresh();
    //	},		
    
    
    templateString: null, // set to null to force to read templatePath instead (templateString is set by parent class)
    templatePath: dojo.moduleUrl("lconn.homepage", "widgets/activities/templates/activitesCalendar.html"),
    
    // private
    
    // _flagDays: Array of Date		
    _flagDays: null,
    
    // _resourceBundle: For i18n
    _resourceBundle: null,
    
    // connect handlers
    _handlers: null,
    
    _parent: null
});
