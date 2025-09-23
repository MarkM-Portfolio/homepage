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

@Controller
@RequestMapping(value = { "/admin" })
public class RemoveWidgetsController extends AbstractWidgetAdminController {

    private static final long serialVersionUID = 2714894124831281085L;

    private static String CLASS_NAME = RemoveWidgetsController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private void removeWidgets() throws WebException {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "removeWidgets");
        // Returns either only one or all available widgets
        if (getWidgetID() != null) {
            Widget widget;
            try {
                widget = getWidgetServices().getWidget(getWidgetID(), true);
                getWidgetServices().removeWidget(getWidgetID());
                audit(widget);
            } catch (ServiceException ex) {
                String error = getWebResourceBundle().getString("error.admin.remove", getWidgetID());
                WebException e = new WebException(error, ex);
                if (logger.isLoggable(SEVERE))
                    logger.logp(SEVERE, CLASS_NAME, "removeWidgets", error, e);
                throw e;
            }
        } else {
            String error = getWebResourceBundle().getString("error.admin.null.widget");
            WebException e = new WebException(error);
            if (logger.isLoggable(SEVERE))
                logger.logp(SEVERE, CLASS_NAME, "removeWidgets", error, e);
            throw e;
        }
    }

    @RequestMapping(value = { "/removeWidgets.action" }, method = RequestMethod.POST)
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "disabled", required = false) boolean disabled,
            @RequestParam(value = "widgetID", required = false) String widgetId, @RequestParam(value = "X-Update-Nonce", required = false) String nonce) throws Exception {

        init(request);
        setWidgetID(widgetId);
        setNonce(nonce);

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

        removeWidgets();
        setupASConfig(request);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "execute", ViewConstants.VIEW_HOMEPAGE_ADMIN_ADMINPAGE);

        addPagedAttributesToModel(model, request);

        return ViewConstants.VIEW_HOMEPAGE_ADMIN_ADMINPAGE;
    }

    private void audit(Widget widget) throws WebException, ServiceException {
        if (getEventUtils().isRequiredPost(IEventUtils.ADMIN_DELETE_WIDGET, Type.DELETE)) {
            Event event = getEventUtils().createEvent(IEventUtils.ADMIN_DELETE_WIDGET, Type.DELETE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), false);

            populateWidgetAdminEvent(widget, event, false);
            getEventUtils().sendPost(event);
        }
    }

}
