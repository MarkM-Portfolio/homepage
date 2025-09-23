/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.utils.impl;

import static java.util.logging.Level.FINER;

import java.util.HashSet;
import java.util.logging.Logger;

import com.ibm.lconn.events.internal.ContainerDetails;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventChangeIndicator;
import com.ibm.lconn.events.internal.Person;
import com.ibm.lconn.events.internal.EventConstants.InvocationPoint;
import com.ibm.lconn.events.internal.EventConstants.Scope;
import com.ibm.lconn.events.internal.EventConstants.Source;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.events.internal.impl.Events;
import com.ibm.lconn.events.internal.object.DefaultEventFactory;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.utils.IEventUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Utils to create and send events to NR. Injected via Spring to avoid tight
 * coupling with Homepage actions, hense the abscence of static methods (there
 * might be a better way to handle this)
 * 
 * @author vincent
 * 
 */
public class EventUtils implements IEventUtils {

	private final static String CLASS_NAME = EventUtils.class.getName();

	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	@Autowired
	protected ResourceBundle webResourceBundle;

	public Event createEvent(String eventName, Type type, String personExternalId,
			String personName, String personEmail, boolean setContainer) {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "createEventRecord", 
					new Object[] {eventName, personExternalId, personName, personEmail});
		}
		
		Event event = DefaultEventFactory.createEvent(
				Source.HOMEPAGE, type, Scope.PRIVATE, eventName);
		
		// Add actor
		Person actor = DefaultEventFactory.createPerson(personExternalId, personName, personEmail);
		event.setActor(actor);
		
		// Add container
		if(setContainer) {
			ContainerDetails cDetails = DefaultEventFactory.createContainerDetails(personExternalId, personName, null);
			event.setContainerDetails(cDetails);
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "createEventRecord", event);
		}
		return event;
	}
	
	public boolean isRequiredPre(String eventName, Type type) {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "isRequiredPre", 
					new Object[] {eventName});
		}
		
		boolean result = Events.isEventRequired(InvocationPoint.SYNC_BEFORE_COMMIT,
					Source.HOMEPAGE, type, eventName, false);	
	
	
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "isRequiredPre", result);
		}
		return result;
	}
	
	public boolean isRequiredPost(String eventName, Type type) {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "isRequiredPost", 
					new Object[] {eventName});
		}
		
		boolean result = Events.isEventRequired(InvocationPoint.SYNC_AFTER_COMMIT,
					Source.HOMEPAGE, type, eventName, false) || 
					Events.isEventRequired(InvocationPoint.ASYNC,
							Source.HOMEPAGE, Type.CREATE, eventName, false);	
	
	
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "isRequiredPost", result);
		}
		return result;
	}
	
	public EventChangeIndicator sendPre(Event event) throws WebException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "sendPre", 
					new Object[] {event});
		}
		
		EventChangeIndicator result = null;
		try{
			result = Events.invokeSyncBeforeCommit(event);
		} catch (Exception e) {
			String msg = webResourceBundle.getString("error.event.pre.invoke", event.getName());
			throw new WebException(msg, e);
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "sendPre", result);
		}
		return result;
	}
	
	public void sendPost(Event event) throws WebException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "sendPost", 
					new Object[] {event});
		}
		
		try{
			if(Events.isEventRequired(InvocationPoint.SYNC_AFTER_COMMIT, 
					event.getSource(), event.getType(), event.getName(), false)) {
				Events.invokeSyncAfterCommit(event);
			}
			if(Events.isEventRequired(InvocationPoint.ASYNC, 
					event.getSource(), event.getType(), event.getName(), false)) {
				Events.invokeAsync(event, false);
			}
		} catch (Exception e) {
			String msg = webResourceBundle.getString("error.event.post.invoke", event.getName());
			throw new WebException(msg, e);
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "sendPost");
		}
	}
	
	public void attachTagToEvent(Event event, String tag) {
		HashSet<String> eventTag = new HashSet<String>();
		eventTag.add(tag);
		
		event.getContentData().setTags(eventTag);
	}
	
	public ResourceBundle getWebResourceBundle() {
		return webResourceBundle;
	}

	public void setWebResourceBundle(ResourceBundle webResourceBundle) {
		this.webResourceBundle = webResourceBundle;
	}
}
