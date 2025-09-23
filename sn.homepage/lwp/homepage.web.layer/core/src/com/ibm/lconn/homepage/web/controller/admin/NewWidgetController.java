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

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
public class NewWidgetController extends AbstractWidgetAdminController {

    private static final long serialVersionUID = 9000639918136261173L;

    private static String CLASS_NAME = NewWidgetController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private WidgetFormFacadeSpring widgetFacade;

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        if (!isCSRFSafe(request, response)) {
            return null;
        }

        setupASConfig(request);

        Widget newWidget = getWidgetFacade().getWidget();
        newWidget.setWidgetId(null);
        // Add it through service layer
        try {
            final String widgetId = getWidgetServices().addWidget(newWidget);

            audit(newWidget);

            // conditionally handle update
            if (newWidget.getBelongTabValue(TabType.SHAREDIALOG)) {
                getWidgetServices().updateWidgetShareOrder(widgetId, getWidgetFacade().getOrderAfterId());
            }

            getWidgetFacade().bindServiceNames(widgetId);

        } catch (ServiceException ex) {
            String error = getWebResourceBundle().getString("error.admin.add", newWidget);
            WebException e = new WebException(error, ex);

            if (logger.isLoggable(SEVERE))
                logger.logp(SEVERE, CLASS_NAME, "saveWidget", error, e);

            this.setErrorMessageKey("jsp.admin.error.newwidget");
        } catch (WebException ex) {
            String error = getWebResourceBundle().getString("error.admin.add", newWidget);
            WebException e = new WebException(error, ex);

            this.setErrorMessageKey("jsp.admin.error.newwidget");

            if (logger.isLoggable(SEVERE))
                logger.logp(SEVERE, CLASS_NAME, "saveWidget", error, e);
        }
        addPagedAttributesToModel(model, request);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "execute");

        return ViewConstants.VIEW_HOMEPAGE_ADMIN_ADMINPAGE;
    }

    @RequestMapping(value = { "/newWidget.action" }, method = RequestMethod.POST)
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

    public void init(HttpServletRequest request) {
        super.init(request);
        getModel(request);

        if (request.getParameter("widgetId") != null) {
            getWidgetFacade().setWidgetId(request.getParameter("widgetId"));
        }

        if (request.getParameter("enabled") != null) {
            getWidgetFacade().setEnabled(request.getParameter("enabled"));
        }

        if (request.getParameter("statuswidgeturl") != null) {

        }

        if (request.getParameter("statuswidgetsecureurl") != null) {

        }

        if (request.getParameter("statuswidgeticonurl") != null) {

        }

        if (request.getParameter("statuswidgeticonsecureurl") != null) {

        }

        if (request.getParameter("isGadget") != null) {
            getWidgetFacade().setIsGadget(request.getParameter("isGadget"));
        }

        if (request.getParameter("isGadgetTrusted") != null) {
            getWidgetFacade().setIsGadgetTrusted(request.getParameter("isGadgetTrusted"));
        }

        if (request.getParameter("gadgetoauth") != null) {

        }

        if (request.getParameter("serviceTitle") != null) {

        }

        if (request.getParameter("title") != null) {
            getWidgetFacade().setTitle(request.getParameter("title"));
        }

        if (request.getParameter("text") != null) {
            getWidgetFacade().setText(request.getParameter("text"));
        }

        if (request.getParameter("url") != null) {
            getWidgetFacade().setUrl(request.getParameter("url"));
        }

        if (request.getParameter("secureUrl") != null) {
            getWidgetFacade().setSecureUrl(request.getParameter("secureUrl"));
        }

        if (request.getParameter("iconUrl") != null) {
            getWidgetFacade().setIconUrl(request.getParameter("iconUrl"));
        }

        if (request.getParameter("secureIconUrl") != null) {
            getWidgetFacade().setSecureIconUrl(request.getParameter("secureIconUrl"));
        }

        if (request.getParameter("belongTabWidget") != null) {
            getWidgetFacade().setBelongTabWidget(request.getParameter("belongTabWidget"));
        }

        if (request.getParameter("belongTabUpdate") != null) {
            getWidgetFacade().setBelongTabUpdate(request.getParameter("belongTabUpdate"));
        }

        if (request.getParameter("homepageSpecific") != null) {
            getWidgetFacade().setHomepageSpecific(request.getParameter("homepageSpecific"));
        }

        if (request.getParameter("defaultOpened") != null) {
            getWidgetFacade().setDefaultOpened(request.getParameter("defaultOpened"));
        }

        if (request.getParameter("multipleInstanceAllowed") != null) {
            getWidgetFacade().setMultipleInstanceAllowed(request.getParameter("multipleInstanceAllowed"));
        }

        if (request.getParameter("prereqList") != null) {
            String[] prereqList = request.getParameterValues("prereqList");

            for (int i = 0; i < prereqList.length; i++) {
                String prereqKey = prereqList[i];
                if (StringUtils.isNotEmpty(prereqKey)) {
                    try {
                        Iterator<Entry<String, String>> iter = getWidgetFacade().getPrereqCheck().iterator();

                        while (iter.hasNext()) {
                            Entry<String, String> prereq = iter.next();
                            if (StringUtils.equals(prereq.getKey(), prereqKey)) {
                                prereq.setValue("checked");
                                break;
                            }
                        }

                    } catch (ServiceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void audit(Widget widget) throws WebException {
        if (getEventUtils().isRequiredPost(IEventUtils.ADMIN_ADD_WIDGET, Type.CREATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.ADMIN_ADD_WIDGET, Type.CREATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), false);

            populateWidgetAdminEvent(widget, event, true);
            getEventUtils().sendPost(event);
        }
    }

    public WidgetFormFacadeSpring getModel(HttpServletRequest request) {
        widgetFacade = new WidgetFormFacadeSpring();
        widgetFacade.setWidget(new Widget());
        widgetFacade.setInstalledComponents(getConfigurationService().getInstalledComponents());
        widgetFacade.setLocale(getLocale(request));
        widgetFacade.setWidgetServices(getWidgetServices());
        return widgetFacade;
    }

    public void setWidgetFacade(WidgetFormFacadeSpring widgetFacade) {
        this.widgetFacade = widgetFacade;
    }

    public WidgetFormFacadeSpring getWidgetFacade() {
        return widgetFacade;
    }

}
