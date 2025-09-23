/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.utils;

import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventChangeIndicator;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.web.WebException;

/**
 * 
 * @author vincent
 *
 */
public interface IEventUtils {
	
	// Home page user events
	public final static String USER_ENABLE_GETTING_STARTED = "homepage.gettingstarted.enabled";
	public final static String USER_DISABLE_GETTING_STARTED = "homepage.gettingstarted.disabled";
	public final static String USER_WIDGET_ADD = "homepage.widget.added";
	public final static String USER_WIDGET_REMOVE = "homepage.widget.removed";
	public final static String USER_WIDGET_EDIT = "homepage.widget.edited";
	public final static String USER_WIDGET_MOVE = "homepage.widget.moved";

	// admin events
	public final static String ADMIN_ADD_WIDGET = "homepage.admin.widget.added";
	public final static String ADMIN_EDIT_WIDGET = "homepage.admin.widget.edited";
	public final static String ADMIN_ENABLE_WIDGET = "homepage.admin.widget.enabled";
	public final static String ADMIN_DISABLE_WIDGET = "homepage.admin.widget.disabled";
	public final static String ADMIN_DELETE_WIDGET = "homepage.admin.widget.deleted";
	public final static String ADMIN_ENABLE_TEST_MODE = "homepage.admin.testmode.enabled";
	public final static String ADMIN_DISABLE_TEST_MODE = "homepage.admin.testmode.disabled";
	public final static String ADMIN_ENABLE_WIDGETS_TAB = "homepage.admin.widgetstab.enabled";
	public final static String ADMIN_DISABLE_WIDGETS_TAB = "homepage.admin.widgetstab.disabled";
	
	
	/**
	 * Indicates if the event is required before we do any work
	 * 
	 * @param eventName the event name
	 * @param type the event type
	 * 
	 * @return true if the event is required
	 */
	public boolean isRequiredPre(String eventName, Type type);
	
	/**
	 * Indicates if the event is required after we do any work
	 * 
	 * @param eventName the event name
	 * @param type the event type
	 * 
	 * @return true if the event is required
	 */	
	public boolean isRequiredPost(String eventName, Type type);
	
	/**
	 * Create a basic home page event object for the given event name
	 * 
	 * @param eventName
	 * @return
	 */
	public Event createEvent(String eventName, Type type, String personExternalId, 
			String personName, String personEmail, boolean setContainer);

	/**
	 * Sends the event at the SYNC_BEFORE_COMMIT invocation point
	 * 
	 * @param event the event
	 * 
	 * @return the change indicator
	 */
	public EventChangeIndicator sendPre(Event event) throws WebException;
	
	/**
	 * Sends the event at the necessary post invocation points
	 * 
	 * @param event the event
	 */
	public void sendPost(Event event) throws WebException;
	
}
