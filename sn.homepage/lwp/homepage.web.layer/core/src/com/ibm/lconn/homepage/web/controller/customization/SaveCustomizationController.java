/*******************************************************************************
 * HCL Confidential
 * OCO Source Materials
 *
 * Copyright HCL Technologies Limited 2010, 2021
 *
 * The source code for this program is not published or otherwise divested of 
 * its trade secrets, irrespective of what has been deposited with the U.S. 
 * Copyright Office.
 *******************************************************************************/

package com.ibm.lconn.homepage.web.controller.customization;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.utils.HomepageEventConstants;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

/**
 * Handle requests to save the customization attribute / widget prefs
 * 
 * @author Vincent
 * 
 */
@Controller
@RequestMapping(value = { "/web" })
public class SaveCustomizationController extends AbstractCustomizationController {

	private static final long serialVersionUID = 6164226067721174046L;

	private final static String CLASS_NAME = SaveCustomizationController.class.getName();

	// private static final String CUSTOMIZATION_PARAM_NAME = "data";
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	// request parameter
	private String data;
	
	@Autowired
	private ITabServices tabServices;
	
	public void init(HttpServletRequest request) {
        // TODO Auto-generated method stub

        setData(null);

        if (request.getParameter("data") != null) {
        	setData(request.getParameter("data"));
        }

        super.init(request);

    }

	public String executeInternal(HttpServletRequest request, String FId) throws WebException {
		
		init(request);
		
		if (FId != null) {
        	setFId(FId);
        }
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "executeInternal");

		String userInternalId = getPersonInternalId();
		saveCustomization(userInternalId);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "executeInternal");

		return null;
	}

	/**
	 * Read the customization param from the request and store them in the DB
	 * 
	 * @param userInternalId
	 * @param request
	 * @throws ServletException
	 */
	private void saveCustomization(String userInternalId) throws WebException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "saveCustomization", userInternalId);

		String param = getData();

		String widgetInstanceId = getFId();

		if (widgetInstanceId != null) {
			WidgetInstance widgetInstance;
			try {
				widgetInstance = getWidgetServices().getWidgetInstance(
						widgetInstanceId);

				if (widgetInstance != null) {
					// check if the widget instance belongs to the authenticated
					// user
					// throw a security exception if this is not the case
					doWidgetOwnershipCheck(widgetInstance.getWidgetInstanceId());
					
					getWidgetServices().changeWidgetSetting(
							widgetInstance.getWidgetInstanceId(), param);
					
					audit(widgetInstance.getWidgetId(), param);
				} else {
					String msg = getWebResourceBundle().getString(
							"error.userpref.get.widgetprefs", userInternalId,
							widgetInstanceId);
					if (logger.isLoggable(SEVERE)) {
						logger.logp(SEVERE, CLASS_NAME, "saveCustomization",
								msg);
					}
					throw new WebException(msg);
				}
			} catch (ServiceException e) {
				String msg = getWebResourceBundle().getString(
						"error.userpref.change.widget.setting", param);
				WebException t = new WebException(msg, e);
				if (logger.isLoggable(SEVERE)) {
					logger
							.logp(SEVERE, CLASS_NAME, "SaveCustomization", msg,
									t);
				}
				throw t;
			}
		} else {
			String msg = getWebResourceBundle().getString(
					"error.userpref.widgetid", userInternalId);
			WebException t = new WebException(msg);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "SaveCustomization", msg, t);
			}
			throw t;
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "saveCustomization");
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	private void audit(String widgetId, String param) throws WebException, ServiceException{
		
		if(getEventUtils().isRequiredPost(IEventUtils.USER_WIDGET_EDIT, Type.UPDATE)){
			
			Event event = getEventUtils().createEvent(IEventUtils.USER_WIDGET_EDIT, Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), true);
			Widget widget = getWidgetServices().getWidget(widgetId, false);
			populateWidgetEvent(widget, event);
			event.getProperties().put(HomepageEventConstants.CUSTOMIZATION, param);
			getEventUtils().sendPost(event);
		}
	}

	public ITabServices getTabServices() {
		return tabServices;
	}

	public void setTabServices(ITabServices tabServices) {
		this.tabServices = tabServices;
	}
}
