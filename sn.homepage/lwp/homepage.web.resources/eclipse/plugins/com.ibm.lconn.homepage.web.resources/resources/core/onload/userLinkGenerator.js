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

dojo.provide("lconn.homepage.core.onload.userLinkGenerator");
dojo.require("com.ibm.lconn.layout.people");

/**
 * Use the common people api's to generate the User link
 * 
 * Will check within span.vcard for span.x-lconn-username
 * and span.x-lconn-userid elements and insert the link
 * where necessary. Any vcard with a link already will be
 * skipped, assuming already rendered
 * 
 */
lconn.homepage.core.onload.userLinkGenerator = {
	
	//event fired by SemTagSvc.parseDom
	tagChanged:"/com/ibm/mashups/livetext/livetextchanged",
	
	linkifyUser: function() {
		try {				
			dojo.addOnLoad(dojo.hitch(this, "updateProfileLinks"));
		}
		catch(err) {}
	},
	
	/**
	 * Override the SemTagSvc.parseDom function and provide our own function
	 * which will linkify profiles correctly before firing the event
	 * to continue business card processing.
	 */	
	updateProfileLinks: function(){
		SemTagSvc.parseDom = dojo.hitch(this, function(some, containerNode){
			dojo.query("span.vcard", containerNode).forEach(dojo.hitch(this, function(personCardLink){
				this.linkifyProfilesInFragment(personCardLink);
			}));
			//now call publish the event to kick start business card processing
			dojo.publish(this.tagChanged, [containerNode]);
		});
		
	},
	
	/**
	 * Take a person card link fragment span.vcard
	 * check that no anchor tags already exist, we only process
	 * empty ones where span.x-lconn-username and span.x-lconn-userid
	 * are present and call the people api to render.
	 */
	linkifyProfilesInFragment: function(personCardLink) {						
		var linknode = dojo.query("a", personCardLink)[0];
		 //if an anchor exists then dont process.
		if(!linknode) {				
			userIdNode = dojo.query(".x-lconn-userid", personCardLink)[0];
			userNameNode = dojo.query(".x-lconn-username", personCardLink)[0];
			
			if (userNameNode){
				var person={userid:userIdNode.innerHTML,name:userNameNode.innerHTML};
				person.name=dojox.html.entities.decode(person.name);
				var renderedProfileLink = com.ibm.lconn.layout.people.createLink(person);
				if ( renderedProfileLink ) {
					//if a link has been returned add, otherwise style plain text bold
					dojo.attr(renderedProfileLink, 'target', '_blank');
					var profileLink = dojo.query("a", renderedProfileLink)[0];
					dojo.place(renderedProfileLink, personCardLink, "first");
				} else {
					dojo.removeClass(userNameNode,"lotusHidden");
					dojo.addClass(userNameNode, "lotusPerson lotusBold");
				}
			}
							
		}
	}
};