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

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.lconn.homepage.web.utils.HomepageEventConstants;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

/**
 * Handle requests to get/save iWidget itemsets.
 * Used to provide custom persistence endpoint to CRE
 *
 * @author BrianOG
 *
 */
@Controller
@RequestMapping(value = { "/web" })
public class ItemSetPersistenceController extends AbstractCustomizationController {
	private static final long serialVersionUID = -8855850909390886688L;

	private final static String CLASS_NAME = ItemSetPersistenceController.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private static final String TYPE_ITEMSET = "itemSet";

	private enum ResponseTypes {
		Success,
		TypeNotSupported,
		WidgetNotFound,
		AccessDenied,
		MethodNotSupported,
		InvalidPostData,
		Error,
		Unknown
	}

	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;
	@Autowired
	private ITabServices tabServices;

	// Request URL params
	private String type;
	private String pageId;
	private String widgetId;
	private String itemSetId;

	private WidgetInstance widgetInstance;


	// Need access to request to get method & body
	public void setServletRequest(HttpServletRequest request) {
		this.httpRequest = request;
	}

	// Need access to response to set code & body
	public void setServletResponse(HttpServletResponse response) {
		this.httpResponse = response;
	}

	public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ( logger.isLoggable(FINER) ) {
			logger.entering(CLASS_NAME, "executeInternal");
			logger.logp(FINER, CLASS_NAME, "executeInternal", "type: " + type);
			logger.logp(FINER, CLASS_NAME, "executeInternal", "pageId: " + pageId);
			logger.logp(FINER, CLASS_NAME, "executeInternal", "widgetId: " + widgetId);
			logger.logp(FINER, CLASS_NAME, "executeInternal", "itemSetId: " + itemSetId);
		}

		if ( getType() == null || getType().isEmpty() || !getType().equals(TYPE_ITEMSET) ) {
			// we only support itemSet persistence
			doResponse(ResponseTypes.TypeNotSupported, "Only type=itemSet is currently supported by this API");
		} else if ( !retrieveWidgetInstance() ) {
			// widget instance id not found
			doResponse(ResponseTypes.WidgetNotFound, "Widget instance not found for id: " + widgetId);
		} else if ( !validateUser() ) {
			// current user does not own this widget
			doResponse(ResponseTypes.AccessDenied, "User does not own the specified widget instance: " + widgetId);
		} else {
			String method = httpRequest.getMethod();
			if ( logger.isLoggable(FINER) ) logger.logp(FINER, CLASS_NAME, "executeInternal", "method: " + method);

			if ( method.equalsIgnoreCase("GET") ) {

				response.setContentType("application/json");
				try {
					response.getWriter().write(doGet());
				} catch (IOException e) {
					if ( logger.isLoggable(FINER) ) logger.logp(FINER, CLASS_NAME, "doGet", "Error occured getting widget settings", e);
				}

			} else if ( method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("POST") ) {
				doPut();
			} else {
				doResponse(ResponseTypes.MethodNotSupported, "Only GET and PUT (POST) are currently supported by this API");
			}
		}

		if (logger.isLoggable(FINER)) logger.exiting(CLASS_NAME, "executeInternal");

		// Return NONE, we are handling the response manually
		return null;
	}

	@RequestMapping(value = { "/itemSetPersistence.action" }, method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    public String execute(
    		Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		@RequestParam(value = "type", required = false) String type,
    		@RequestParam(value = "pageId", required = false) String pageId,
    		@RequestParam(value = "widgetId", required = false) String widgetId,
    		@RequestParam(value = "itemSetId", required = false) String itemSetId
    ) throws Exception {

        init(request);

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        try {
            return executeInternal(model, request, response);
        } catch (ServiceException se) {
            logger.log(Level.SEVERE, se.getMessage(), se);
            response.sendError(400, se.getErrorCode());
            return ViewConstants.VIEW_ERROR;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, t.getMessage(), t);
            response.sendError(400, t.getMessage());
            return ViewConstants.VIEW_ERROR;
        } finally {
            if (logger.isLoggable(FINER))
                logger.exiting(CLASS_NAME, "execute");
        }

    }
	
	private String doGet() {
		String result = "";
		if ( logger.isLoggable(FINER) ) {
			logger.entering(CLASS_NAME, "doGet");
		}

		//result = widgetInstance.getWidgetSetting();

		if ( logger.isLoggable(FINER) ) {
			logger.exiting(CLASS_NAME, "doGet", result);
		}
		return result;
	}

	private String doPut() {
		String result = "";
		if ( logger.isLoggable(FINER) ) {
			logger.entering(CLASS_NAME, "doPost");
		}


		String body = getBody();
		if ( !validateBody(body) ) {
			doResponse(ResponseTypes.InvalidPostData, "Invalid POST data");
		} else {
			// lets go save the setting
			try {
				getWidgetServices().changeWidgetSetting(widgetInstance.getWidgetInstanceId(), body);
				audit(widgetInstance.getWidgetId(), body);
			} catch (ServiceException e) {
				doResponse(ResponseTypes.Error, "Error occured saving widget settings: " + e.getMessage());
				if ( logger.isLoggable(FINER) ) logger.logp(FINER, CLASS_NAME, "doPost", "Error occured saving widget settings", e);
			} catch (WebException e) {
				doResponse(ResponseTypes.Error, "Error occured saving widget settings: " + e.getMessage());
				if ( logger.isLoggable(FINER) ) logger.logp(FINER, CLASS_NAME, "doPost", "Error occured saving widget settings", e);
			}
			doResponse(ResponseTypes.Success, null);
		}

		if ( logger.isLoggable(FINER) ) {
			logger.exiting(CLASS_NAME, "doPost", result);
		}
		return result;
	}

	private void doResponse(ResponseTypes respType, String body) {
		int respCode = 0;
		if ( body == null ) body = "";

		switch ( respType ) {
			case Success:
				respCode = HttpServletResponse.SC_OK;
				break;

			case WidgetNotFound:
				respCode = HttpServletResponse.SC_NOT_FOUND; // 404
				break;

			case AccessDenied:
				respCode = HttpServletResponse.SC_FORBIDDEN; // 403
				break;

			case MethodNotSupported:
				respCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED; // 405
				break;

			case TypeNotSupported:
			case InvalidPostData:
			case Unknown:
			default:
				respCode = HttpServletResponse.SC_BAD_REQUEST; // 400
		}

		httpResponse.setStatus(respCode);
		try {
			httpResponse.getWriter().write(body);
		} catch (IOException e) {
			if ( logger.isLoggable(FINER) ) {
				logger.logp(FINER, CLASS_NAME, "handleError", "Error writing response body", e);
			}
		}
	}

	private boolean retrieveWidgetInstance() {
		if ( logger.isLoggable(FINER) ) logger.entering(CLASS_NAME, "retrieveWidgetInstance");

		boolean valid = false;

		try {
			widgetInstance = getWidgetServices().getWidgetInstance(widgetId);
			valid = widgetInstance != null;
		} catch (ServiceException e) {
			if ( logger.isLoggable(FINER) ) {
				logger.logp(FINER, CLASS_NAME, "retrieveWidgetInstance", "Error retrieving widget instance data", e);
			}
		}

		if ( logger.isLoggable(FINER) ) logger.exiting(CLASS_NAME, "retrieveWidgetInstance", valid);

		return valid;
	}

	// Verify that body contains the itemset we expect (attributes)
	private boolean validateBody(String body) {
		if ( logger.isLoggable(FINER) ) logger.entering(CLASS_NAME, "vaidateBody");

		boolean valid = false;

		if ( body != null ) {
			try {
				JSONObject json = JSONObject.parse(body);
				if ( ((String)json.get("name")).equals("attributes") && json.containsKey("items") ) {
					valid = true;
				}
			} catch (Exception e) {
				if ( logger.isLoggable(FINER) ) {
					logger.logp(FINER, CLASS_NAME, "doPost", "Error parsing body", e);
				}
			};
		}

		if ( logger.isLoggable(FINER) ) logger.exiting(CLASS_NAME, "validateBody", valid);

		return valid;
	}

	// Verify that current user owns the widget instance specified
	private boolean validateUser() {
		if ( logger.isLoggable(FINER) ) logger.entering(CLASS_NAME, "validateUser");

		boolean valid = true;

		try {
			doWidgetOwnershipCheck(widgetInstance.getWidgetInstanceId());
		} catch (WebException e) {
			valid = false;
		}


		if ( logger.isLoggable(FINER) ) logger.exiting(CLASS_NAME, "validateUser", valid);

		return valid;
	}

	private String getBody() {
		if ( logger.isLoggable(FINER) ) logger.entering(CLASS_NAME, "getBody");

		StringBuffer body = new StringBuffer();
		try {
			BufferedReader reader = httpRequest.getReader();
			String line;
			while ( (line = reader.readLine() ) != null ) {
				body.append(line);
			}
		} catch (IOException e) {
			if ( logger.isLoggable(FINER) ) {
				logger.logp(FINER, CLASS_NAME, "getBody", "Error reading response body", e);
			}
		}

		String result = body.length() > 0 ? body.toString() : null;

		if ( logger.isLoggable(FINER) ) logger.exiting(CLASS_NAME, "getBody", result);
		return result;
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

		String param = "";//getData();

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

	private void audit(String widgetId, String param) throws WebException, ServiceException{
		if(getEventUtils().isRequiredPost(IEventUtils.USER_WIDGET_EDIT, Type.UPDATE)){
			Event event = getEventUtils().createEvent(IEventUtils.USER_WIDGET_EDIT,
					Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), true);

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getWidgetId() {
		return widgetId;
	}

	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	public String getItemSetId() {
		return itemSetId;
	}

	public void setItemSetId(String itemSetId) {
		this.itemSetId = itemSetId;
	}
}
