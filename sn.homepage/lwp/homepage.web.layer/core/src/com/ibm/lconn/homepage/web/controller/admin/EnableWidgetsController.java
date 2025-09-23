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

package com.ibm.lconn.homepage.web.controller.admin;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.lconn.homepage.web.utils.IEventUtils;
import com.ibm.lconn.homepage.web.utils.WidgetEventConstants;

@Controller
@RequestMapping(value = { "/admin" })
public class EnableWidgetsController extends AbstractWidgetAdminController {

    private static final long serialVersionUID = -7959687880265596920L;

    private static String CLASS_NAME = EnableWidgetsController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private void enableWidgets() throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "enableWidgets");
        // Returns either only one or all available widgets
        if (getWidgetID() != null) {
            try {
                Widget widget = getWidgetServices().getWidget(getWidgetID(), true);
                // transaction not needed here. An enabled widget still
                // work even if caching the descriptor failed.
                getWidgetServices().enableWidget(getWidgetID());
                audit(widget);
            } catch (ServiceException ex) {
                String error = getWebResourceBundle().getString("error.admin.enable", getWidgetID());
                WebException e = new WebException(error, ex);
                if (logger.isLoggable(SEVERE))
                    logger.logp(SEVERE, CLASS_NAME, "enableWidgets", error, e);

                this.setErrorMessageKey("jsp.admin.error.enablewidget");
            } catch (WebException ex) {
                String error = getWebResourceBundle().getString("error.admin.enable", getWidgetID());
                WebException e = new WebException(error, ex);
                if (logger.isLoggable(SEVERE))
                    logger.logp(SEVERE, CLASS_NAME, "enableWidgets", error, e);

                this.setErrorMessageKey("jsp.admin.error.enablewidget");
            }
        } else {
            String error = getWebResourceBundle().getString("error.admin.null.widget");
            WebException e = new WebException(error);
            if (logger.isLoggable(SEVERE))
                logger.logp(SEVERE, CLASS_NAME, "enableWidgets", error, e);

            this.setErrorMessageKey("jsp.admin.error.enablewidget");
        }
    }
    
    @RequestMapping(value= {"/enableWidgets.action"},method = RequestMethod.GET)
    public String getEnableWidgets(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	init(request);
        setWidgetID(null);
        setNonce(null);
        return execute(model, request, response); 	
    }

    @RequestMapping(value = { "/enableWidgets.action" }, method = RequestMethod.POST)
    public String postEnableWidgets(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "widgetID", required = false) String widgetId,
            @RequestParam(value = "X-Update-Nonce", required = false) String nonce) throws Exception {
        init(request);
        setWidgetID(widgetId);
        setNonce(nonce);

       return execute(model, request, response);

    }
    
     
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
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

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        enableWidgets();
        setupASConfig(request);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "execute", ViewConstants.VIEW_HOMEPAGE_ADMIN_ADMINPAGE);

        addPagedAttributesToModel(model, request);

        return ViewConstants.VIEW_HOMEPAGE_ADMIN_ADMINPAGE;
    }

    private void audit(Widget widget) throws WebException {
        if (getEventUtils().isRequiredPost(IEventUtils.ADMIN_ENABLE_WIDGET, Type.UPDATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.ADMIN_ENABLE_WIDGET, Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), false);

            populateWidgetAdminEvent(widget, event, false);
            event.getProperties().put(WidgetEventConstants.ENABLED, Boolean.TRUE.toString());
            getEventUtils().sendPost(event);
        }
    }

}
