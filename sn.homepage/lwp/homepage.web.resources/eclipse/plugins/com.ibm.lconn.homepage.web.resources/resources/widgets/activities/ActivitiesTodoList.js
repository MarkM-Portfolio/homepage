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


dojo.provide("lconn.homepage.widgets.activities.ActivitiesTodoList");

dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("dojo.string");
dojo.require("dojo.date.stamp");
dojo.require("dojox.html.entities");

dojo.requireLocalization("lconn.homepage", "widgetstrings");

/**
 * @author Yi Ming Huang (huangyim@cn.ibm.com)
 */
dojo.declare("lconn.homepage.widgets.activities.ActivitiesTodoList", lconn.homepage.core.base._BodyWidget, {

	// Max displayed
	MAX: 10,
    _resourceBundle: null,
    templatePath: dojo.moduleUrl("lconn.homepage", "widgets/activities/templates/activitiesTodoList.html"),
    
    postCreate: function(){
    	this.showLoading();
        this.update();        
    },
    
    postMixInProperties: function(){
        this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
        this.loading = this._resourceBundle.COMMON_LOADING;
        this.loadingImg = lconn.core.url.getServiceUrl(lconn.core.config.services.webresources)
		 + "/web/com.ibm.lconn.core.styles.oneui3/images/loading.gif?etag=" + ibmConfig.versionStamp;
        this.activitiesTodoLink = this._parameters.todoUrl;
        this.blankGif = djConfig.blankGif;
        this.todayMidnight = new Date();
		this.todayMidnight.setHours(0,0,0,0);
    },
    
    // show the loading indicator
    showLoading: function() {
    	dojo.addClass(this.welcomeNode, "lotusHidden");
    	dojo.addClass(this.activitiesTodoListCentralContainerNode, "lotusHidden");
    	dojo.addClass(this.errorNode, "lotusHidden");
    	dojo.removeClass(this.activitiesTodoListCentralLoading, "lotusHidden");
    },
    
    // show the error information
    showError: function() {
    	dojo.addClass(this.activitiesTodoListCentralLoading, "lotusHidden");
    	dojo.addClass(this.welcomeNode, "lotusHidden");
    	dojo.addClass(this.activitiesTodoListCentralContainerNode, "lotusHidden");
    	dojo.removeClass(this.errorNode, "lotusHidden");
    },
    
    // show the welcome pane - when there is no todo items
    showWelcome: function() {
    	dojo.addClass(this.activitiesTodoListCentralLoading, "lotusHidden");
    	dojo.addClass(this.activitiesTodoListCentralContainerNode, "lotusHidden");
    	dojo.addClass(this.errorNode, "lotusHidden");
    	dojo.removeClass(this.welcomeNode, "lotusHidden");
    },
    
    // show the todo list
    showTodoList: function() {
    	dojo.addClass(this.activitiesTodoListCentralLoading, "lotusHidden");
    	dojo.addClass(this.welcomeNode, "lotusHidden");
    	dojo.addClass(this.errorNode, "lotusHidden");
    	dojo.removeClass(this.activitiesTodoListCentralContainerNode, "lotusHidden");
    },
    
    /**
     * Success callback for the xhr to get todo feed
     * 1) Parse the XML feed to todo list objects
     * 2) Layout the todo list on UI
     * @see lconn.homepage.core.base._BodyWidget.handleAsyncRequest
     * @param data {Document} todo feed document
     * @param evt {object} event object
     */
    handleAsyncRequest: function(data, evt) {
       this.todoItems = this._doc2items(data);
       var len = this.MAX > this.todoItems.length ? this.todoItems.length : this.MAX;
       if (len > 0) {
    	   var table = dojo.create("table", {className: "lotusTable toDoTable"});
    	   for (var i=0; i<len; i++) {
    		   var todo = this.todoItems[i];
    		   this._createTodoItemDom(table, i, todo);
    	   }
    	   this.activitiesTodoListCentralNode.appendChild(table);
    	   
    	   // parse the vcard livetext
    	   if (SemTagSvc) {
    		   SemTagSvc.parseDom(null, this.activitiesTodoListCentralNode);
    	   }
    	   
    	   this.showTodoList();
       } else {
    	   this.showWelcome();
       }
       
      // Bidi support
   	  lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(this.activitiesTodoListCentralNode);
    },
    
    /**
     * Check to see if due date is earlier or equal to today
     * @param date {Date} due date
     * @returns {Boolean} true: earlier or equal
     */
    _earlierThanToday: function(date) {
    	var today = new Date();
		today.setHours(23,59,59,0);
		if (date.getTime() < today.getTime())
			return true;
		else return false;
    },
    
    /**
     * Create a table row in the todo table
     * @param table {DOM.table} todo table
     * @param row {int} row index
     * @param todo {object} todo object
     */
    _createTodoItemDom: function(table, row, todo) {
    	var tr = table.insertRow(row);
    	if (row == 0) dojo.addClass(tr, "lotusFirst");
    	var td = tr.insertCell(0);
    	var tickDiv = dojo.create("div",{className:"lconnHomepage-todoWidgetIcon"},td, "first");
    	var tickImg = dojo.create("img",{className: "lconnTickIcon", src: this.blankGif},tickDiv);
    	var toDoDiv = dojo.create("div",{className: "lconnHomepage-todoWidgetContent"},td);
    	var titleLink = dojo.create("a", {className: "linkToDo bidiAware", href: todo.htmlLink}, toDoDiv);
    	
    	/* Create a text node for the link contents. This prevents any active HTML in the script title from being run 
    	   e.g prevent script injection attack. The title is provided by the user rather than Connections so could
    	   contain <script> elements if an attack is being performed. */
    	dojo.place(document.createTextNode(todo.title), titleLink, "only");
    	
    	var personSpan;
    	var metaInner1;
    	if(todo.author.userid == userid){
    		personSpan = this._createPersonSpan(this._resourceBundle.ACTIVITIES_YOU, todo.author.userid);
    		metaInner1 = dojo.string.substitute(this._resourceBundle.ACTIVITIES_CREATED_THIS, [personSpan]);
    	} else {
    		personSpan = this._createPersonSpan(todo.author.name, todo.author.userid);
    		metaInner1 = dojo.string.substitute(this._resourceBundle.ACTIVITIES_ASSIGN_TO_YOU, [personSpan]);
    	}
    	
    	dojo.create("div", {className: "lotusMeta toDoText", innerHTML: metaInner1}, toDoDiv);
    	
    	if (todo.duedate) {
    		var duedate = dojo.date.stamp.fromISOString(todo.duedate);
        	var formattedDD = lconn.core.DateUtil.toString(duedate, false);
        	var duedateDiv = dojo.create("div", {className: "lotusMeta"}, toDoDiv);
        	var dueTextSpan = dojo.create("span",{className: "toDoText", innerHTML: this._resourceBundle.ACTIVITIES_DUE_DATE}, duedateDiv, "first");
        	var dueDateSpan = dojo.create("span",{className: "toDoText", innerHTML: formattedDD}, duedateDiv);
        	if (this._earlierThanToday(duedate)){
    			dojo.addClass(dueDateSpan, "homepageOverdueTodo");
        	}
    	}
    },
    
    /**
     * Create the person vcard HTML markup
     * @param name {string} person name
     * @param uuid {string} person userid
     * @returns {string.html} vcard HTML markup
     */
    _createPersonSpan: function(name, uuid) {
    	
    	var encodedUser = dojox.html.entities.encode(name);
    	
		var html =  
		'<span class="vcard personToDoLink">' +
			'<span class="x-lconn-username lotusHidden">' +  encodedUser + '</span>' +
			'<span class="x-lconn-userid lotusHidden">' + uuid + '</span>' +
		'</span>';
		return html;
	},
    
	/**
	 * Failure callback for the xhr to get todo feed
	 * @see lconn.homepage.core.base._BodyWidget.handleError
	 */
	handleError: function(){
		this.showError();
	},
    
	/*
	 * Fire the request to get todo feed
	 */
    update: function(){
        this.remoteUrl = this._feedUrls.defaultURL + "&assignedToUserid=" + lconn.homepage.global.getUserId();
        this.retrieveAtomAndUpdate();
    },
    
    /**
     * Filter used by _doc2itmes -- filter out the todo that has NO due date or OVERDUE
     * @param todo {object} todo object
     * @returns {boolean} true=filter it, false=leave it
     */
    _duedateFilter: function(todo) {
    	if (!todo.duedate)
    		return true;
    	var duedate = dojo.date.stamp.fromISOString(todo.duedate);
    	return this.todayMidnight.getTime() > duedate.getTime();
    },
    
    /**
     * Parse the todo feed XML to object array
     * @param doc {Document} todo feed XML
     * @param filter {Function} filter function
     * @returns {Array} todo list
     */
    _doc2items: function(doc, filter) {
        var items = new Array();
        var self = this;
        dojo.query("entry", doc).forEach(function(entry) {
              var authorNode = self._node(entry, "author");
              var author = {
                    name: self._value(self._node(authorNode, "name")),
                    email: self._value(self._node(authorNode, "email")),
                    userid: self._value(self._node(authorNode, "snx:userid"))
              };
              
              var assignedTo = null;
              /**	Not used for now
              var assignedToNode = self._node(entry, "snx:assignedto");
              if (assignedToNode) {
                    assignedTo = {
                          name: self._value(assignedToNode, "name"),
                          email: self._value(assignedToNode),
                          userid: self._value(assignedToNode, "snx:userid")
                    };
              }
              */
              var linkNode = dojo.query("link[type='text/html']", entry)[0];
              var todo = {
                    id: self._value(self._node(entry, "id")),
                    title: self._value(self._node(entry, "title")),
                    duedate: self._value(self._node(entry, "snx:duedate")),
                    content: self._value(self._node(entry, "content")),
                    htmlLink: self._value(linkNode, "href"),
                    author: author,
                    assignedTo: assignedTo                    
              };
              // if fiter is not defined, or if it returns false
              if (!filter || !filter(todo)) {
                    items.push(todo);
              }
        });
        return items;
      },
      
      /**
       * Get child node named by tag
       * @param doc {DOM} parent node
       * @param tag {string} node tag
       * @returns {DOM} child node named by tag
       */
      _node: function(doc, tag) {
    	  return doc.getElementsByTagName(tag)[0];
      },

      /**
       * Get the node inner text or attribute value
       * @param doc {DOM} node
       * @param attr {string} attribute of the node
       * @returns {string} the node inner text or attribute value
       */
      _value: function(doc, attr) {
        if (!doc) {
              return null;
        }
        // if attribute is undefined get the inner text, otherwise get the attribute
        if (attr == undefined) {
              return doc.textContent || doc.text;
          } else {
              return doc.attributes.getNamedItem(attr).nodeValue;
          }
      }
    
});
