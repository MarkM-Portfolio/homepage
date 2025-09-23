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

package com.ibm.lconn.homepage.web.controller.admin.restful;

import java.io.PrintWriter;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.AbstractSessionAwareController;
import com.ibm.lconn.homepage.web.utils.IEventUtils;
import com.ibm.lconn.homepage.web.controller.ViewConstants;

@Controller
@RequestMapping(value = { "/admin" })
public class WidgetTabController extends AbstractSessionAwareController {

	private static final long serialVersionUID = -9156493559628621842L;
	private final static String CLASS_NAME = WidgetTabController.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private JSONObject json;
    @Autowired
	private ITabServices tabServices;
    @Autowired
	private IEventUtils eventUtils;
	private String value;
	
	public String executeInternal(
        Model model, 
        HttpServletRequest request, 
        HttpServletResponse response, 
        String forceRefresh,
        String value
    ) throws Exception {

		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "execute");

		JSONObject widgetTabConfig = new JSONObject();
		
		if (logger.isLoggable(FINER))
			logger.logp(FINEST, CLASS_NAME, "execute", "getValue [" + value + "]");
		
		if ((value==null ? "" : value).compareToIgnoreCase("") != 0) {
			
			if(Boolean.parseBoolean(value)) {
				if (logger.isLoggable(FINER))
					logger.logp(FINEST, CLASS_NAME, "execute", "enableTab [" + TabType.WIDGET.getDBValue() + "]");
				getTabServices().enableTab(TabType.WIDGET.getDBValue());
				auditEnabled();
			} else {
				if (logger.isLoggable(FINER))
					logger.logp(FINEST, CLASS_NAME, "execute", "disableTab [" + TabType.WIDGET.getDBValue() + "]");
				getTabServices().disableTab(TabType.WIDGET.getDBValue());
				auditDisabled();
			}
		}
		
		widgetTabConfig.put("enabled", getTabServices().isTabEnabled(TabType.WIDGET.getDBValue()));		
		setJson(widgetTabConfig);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "execute");

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(widgetTabConfig);
        out.flush();
        out.close();

        return null;
	}
    
	@RequestMapping(value = { "/rest/widgetTab.action" }, method = RequestMethod.GET)
    public String execute(
    		Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		@RequestParam(value = "forceRefresh", required = false) String forceRefresh,
    		@RequestParam(value = "value", required = false) String value 
    ) throws Exception {

        init(request);

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        try {
            return executeInternal(model, request, response, forceRefresh, value);
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
	
	private void auditEnabled() throws WebException, ServiceException {
        
		if(getEventUtils().isRequiredPost(IEventUtils.ADMIN_ENABLE_WIDGETS_TAB, Type.UPDATE)){
			Event event = getEventUtils().createEvent(IEventUtils.ADMIN_ENABLE_WIDGETS_TAB,	Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), false);
			getEventUtils().sendPost(event);
		}
	}
	
	private void auditDisabled() throws WebException, ServiceException {

		if(getEventUtils().isRequiredPost(IEventUtils.ADMIN_DISABLE_WIDGETS_TAB, Type.UPDATE)){
			Event event = getEventUtils().createEvent(IEventUtils.ADMIN_DISABLE_WIDGETS_TAB,Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), false);
			getEventUtils().sendPost(event);
		}
	}

	public void setJson(JSONObject jsonObject) {
		this.json = jsonObject;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setTabServices(ITabServices tabServices) {
		this.tabServices = tabServices;
	}

	public ITabServices getTabServices() {
		return tabServices;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public IEventUtils getEventUtils() {
		return eventUtils;
	}

	public void setEventUtils(IEventUtils eventUtils) {
		this.eventUtils = eventUtils;
	}
}
