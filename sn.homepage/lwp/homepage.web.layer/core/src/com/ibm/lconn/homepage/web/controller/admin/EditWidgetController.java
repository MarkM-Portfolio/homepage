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

import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

@Controller
@RequestMapping(value = { "/admin" })
public class EditWidgetController extends NewWidgetController {

    private static final long serialVersionUID = -1268760277741460424L;

    private static String CLASS_NAME = EditWidgetController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @RequestMapping(value = { "/editWidget.action" }, method = RequestMethod.POST)
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

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

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        if (!isCSRFSafe(request, response)) {
            return ViewConstants.VIEW_ERROR;
        }

        setupASConfig(request);

        Widget retrievedWidget;
        boolean systemWidget = false;
        try {
            try {
                retrievedWidget = getWidgetServices().getWidgetNonLocalized(getWidgetFacade().getWidgetId(), true);
                systemWidget = retrievedWidget.getIsSystem();
            } catch (ServiceException ex) {
                String error = getWebResourceBundle().getString("error.admin.retrieve", getWidgetFacade().getWidgetId());
                WebException e = new WebException(error, ex);
                if (logger.isLoggable(SEVERE))
                    logger.logp(SEVERE, CLASS_NAME, "editWidget", error, e);
                throw e;
            }

            Widget editedWidget = getWidgetFacade().getWidget();
            // isGadget field is disabled
            editedWidget.setIsGadget(retrievedWidget.isGadget());

            try {
                if (systemWidget) {
                    // For the system widgets, all the fields are disabled in the form,
                    // except the title
                    // consequently, the request do no contain the different parameters
                    // of a widget
                    // We have to use the fields from the DB and just update the title
                    // field
                    retrievedWidget.setTitle(editedWidget.getTitle());
                    retrievedWidget.setDefaultOpened(editedWidget.isDefaultOpened());
                    retrievedWidget.setMultipleInstanceAllowed(editedWidget.isMultipleInstanceAllowed());

                    // working on the DB widget itself in the rest of the method
                    editedWidget = retrievedWidget;

                }
                // Update it through service layer
                getWidgetServices().updateWidget(editedWidget);

                // conditionally handle update
                if (editedWidget.getBelongTabValue(TabType.SHAREDIALOG)) {
                    getWidgetServices().updateWidgetShareOrder(editedWidget.getWidgetId(), getWidgetFacade().getOrderAfterId());
                }

                getWidgetFacade().bindServiceNames(editedWidget.getWidgetId());

            } catch (ServiceException ex) {
                String error = getWebResourceBundle().getString("error.admin.update", editedWidget);
                WebException e = new WebException(error, ex);
                if (logger.isLoggable(SEVERE))
                    logger.logp(SEVERE, CLASS_NAME, "execute", error, e);
                throw e;
            }

            audit(editedWidget);
        } catch (WebException e) {
            this.setErrorMessageKey("jsp.admin.error.editwidget");
        }

        setNonce(request, response);
        addPagedAttributesToModel(model, request);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "execute");

        return ViewConstants.VIEW_HOMEPAGE_ADMIN_EDITFORM;
    }

    private void audit(Widget widget) throws WebException {
        if (getEventUtils().isRequiredPost(IEventUtils.ADMIN_EDIT_WIDGET, Type.UPDATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.ADMIN_EDIT_WIDGET, Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), false);

            populateWidgetAdminEvent(widget, event, true);
            getEventUtils().sendPost(event);
        }
    }

}
