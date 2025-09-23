/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
dojo.provide("lconn.homepage.jsp.as.test.TempleteEntries");

/**
 * Templete Entries  for the Activity Stream Post Test.
 * @author Tao Shang
 */
window.mbStatusTemplate = {
		"content": " Test Microblogin Message ",
		"objectType": "note"
}
window.testASEntries = {
		
    //Simple Entry
	"Simple Entry" : {
			

		    "published": "",
		    "url": "",
	
			"provider": {
				"id": "http://www.ibm.com/xmlns/prod/sn",
				"displayName": "IBM Connections - News Service",
				"url": ""
			},
		    "generator": {
		        "image": {
		            "url": "http://www.linkedin.com/favicon.ico"
		        },
		        "id": "linkedin",
		        "displayName": "linkedin",
		        "url": "http://www.linkedin.com"
		    },
		    "actor": {
		        "id": "",
		        "displayName": ""
		    },
			"content": "",
		    "title": "You have created a simple entry",
		  
		    "updated": "",
		    "object": {
		        "summary": "Post from LinkedIn simple event",
		        "objectType": "note",
		        "id": "",
		        "displayName": "Test Note",
		        "url": "http://www.ibm.com/"
		      },
		
		    "verb": "post"
		
	},
	"Bump entry, with image" : {
		 "published": "",
		    "url": "",
	
		    "provider": {
				"id": "http://www.ibm.com/xmlns/prod/sn",
				"displayName": "IBM Connections - News Service",
				"url": ""
			},
		    "generator": {
		        "image": {
		            "url": "http://www.myspace.com/favicon.ico"
		        },
		        "id": "myspace",
		        "displayName": "Myspace",
		        "url": "http://www.myspace.com"
		    },
		    "actor": {
		        "id": "",
		        "displayName": ""
		    },
			"content": "",
		    "title": "You have created an entry with image from AS Post",
		    "id": "",
		    "updated": "",
		     "object": {
				"summary": "A relatively unimportant notice",
				"objectType": "file",
				"image": {"url": "http://www.ibm.com/smarterplanet/global/images/us__en_us__general__decade_of_smart_icon__170x110.jpg"},
				"id": "",
				"displayName": "IBMPicture.gif",
				"url": "http://www.ibm.com/"
			},
		    "verb": "post"
	},
	"Entry with tags" : {

			"published": "",
		    "url": "",
		
		    "provider": {
				"id": "http://www.ibm.com/xmlns/prod/sn",
				"displayName": "IBM Connections - News Service",
				"url": ""
			},
		    "generator": {
		        "image": {
		            "url": "http://www.myspace.com/favicon.ico"
		        },
		        "id": "myspace",
		        "displayName": "Myspace",
		        "url": "http://www.myspace.com"
		    },
		    "actor": {
		        "id": "",
		        "displayName": ""
		    },
			"content": "",
		    "title": "<b>You</b> have created an entry with tag [<i>tagtest</i>] from AS Post",
		    "id": "",
		    "updated": "",
			"icon" : { "url" : "http://www.ibm.com/favicon.ico"},
		    "object":{
				 "tags" : [ { "displayName" : "tagtest",
                  "objectType" : "tag"
                } ],
				"summary":"see a tag ",
				"replies":{
				   "totalItems":0
				},
				
				
				"objectType":"note",
				"author":{
				   "objectType":"person",
				   "id":"",
				   "displayName":""
				},
				"id":"",
				"displayName":"",
				"url" : "http://www.myspace.com"
			
			 },
		    "verb": "post"
		},
		"Entry with Link": {
			"published" : "",
			"url" : "",
			"provider" : {
				"id" : "http://www.ibm.com/xmlns/prod/sn",
				"displayName" : "IBM Connections - News Service",
				"url" : lconn.core.url.getServiceUrl(lconn.core.config.services.news).uri
			},
			"generator" : {
				"image" : {
					"url" : lconn.core.url.getServiceUrl(lconn.core.config.services.homepage).uri +"/nav/common/images/iconProfiles16.png"
				},
				"id" : "",
				"displayName" : "Test Website",
				"url" : lconn.core.url.getServiceUrl(lconn.core.config.services.profiles).uri
			},
			"actor" : {
				"objectType" : "person",
				"id" : "",
				"displayName" : ""
			},

			"openSocial" : {
				"embed" : {
					"gadget" : lconn.core.url.getServiceUrl(lconn.core.config.services.webresources).uri+ "/web/com.ibm.social.ee/StatusUpdate.xml",
					"context" : {
						"summary" : "status updates test ",
						"connectionsContentUrl" : "",
						"published" : "",
						"isPublic" : "true",
						"eventType" : "profiles.status.updated",
						"actor" : {
							"id" :userid,
							"displayName" : lconn.homepage.userName
						},
						"iconUrl" : lconn.core.url.getServiceUrl(lconn.core.config.services.homepage).uri + "/nav/common/images/iconProfiles16.png",
						"numLikes" : "0",
						"title" : lconn.homepage.userName + " test",
						"numComments" : "0",
						"updated" : "false",
						"id" : "",
						"itemUrl" : "",
						"eventTitle" : lconn.homepage.userName +" status updates "
					}
				}
			},
			"title" : "Test post profils status",
			"content" : "",
			"id" : "",
			"updated" : "",
			"object" : {
				"summary" : "status updates ",
				"objectType" : "note",
				"author" : {
					"objectType" : "person",
					"id" : "",
					"displayName" : ""
				},
				"id" : "",
				"displayName" : "",
				"published" : "",
				"url" : ""
			},
			"verb" : "post"
		},
			
		"Minimal" : {
			"actor" : {
				"id" : userid,
				"displayName" : lconn.homepage.userName 
			},
			"title" : "Sample Minimal content (supplied in title)",
			"generator" : {
				"id" : ""
			},
			"object" : {
				
				"id" : ""
			}
		},
		"Custom Content" : {
			"actor" : {
				"id" : userid,
				"displayName" : lconn.homepage.userName 
			},
			"title" : "",
			"generator" : {
				"id" : ""
			},
			"object" : {
				
				"id" : ""
			}
		}
		
		
	// etc, etc
};