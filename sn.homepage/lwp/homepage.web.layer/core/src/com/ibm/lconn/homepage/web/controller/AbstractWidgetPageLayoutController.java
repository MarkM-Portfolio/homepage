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

package com.ibm.lconn.homepage.web.controller;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.SEVERE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.homepage.model.Column;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ISecurityServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.utils.IEventUtils;
import com.ibm.lconn.homepage.web.utils.IUrlNormalizer;
import com.ibm.lconn.homepage.web.utils.WidgetEventConstants;

public abstract class AbstractWidgetPageLayoutController extends AbstractWidgetController {

    private static final String SC_ST_MEETINGS_WIDGET_ID = "1520aa1-c2fa-48ef-be05-8dee630c0054";
    private static final long serialVersionUID = 363921239109493038L;
    private final static String CLASS_NAME = AbstractWidgetPageLayoutController.class.getName();
    private final static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private ISecurityServices securityServices;

    @Autowired
    private IUrlNormalizer urlNormalizer;

    @Autowired
    private IEventUtils eventUtils;

    private String columnNumber;

    private Map<String, String> widgetPreferences;

    private List<Widget> availWidgets;
    private List<WidgetInstance> widgetInstances;

    // review layout information here. Need something more generic
    private List<WidgetInstance> firstColWidgets;
    private List<WidgetInstance> secondColWidgets;
    private List<WidgetInstance> thirdColWidgets;

    public ISecurityServices getSecurityServices() {
        return securityServices;
    }

    public void setSecurityServices(ISecurityServices securityServices) {
        this.securityServices = securityServices;
    }

    public List<Widget> getAvailWidgets() {
        return availWidgets;
    }

    public void setAvailWidgets(List<Widget> availWidgets) {
        this.availWidgets = availWidgets;
    }

    public String getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = String.valueOf(columnNumber);
    }

    public List<WidgetInstance> getWidgetInstances() {
        return widgetInstances;
    }

    public void setWidgetInstances(List<WidgetInstance> widgetInstances) {
        this.widgetInstances = widgetInstances;
    }

    public List<WidgetInstance> getFirstColWidgets() {
        return firstColWidgets;
    }

    public void setFirstColWidgets(List<WidgetInstance> firstColWidgets) {
        this.firstColWidgets = firstColWidgets;
    }

    public List<WidgetInstance> getSecondColWidgets() {
        return secondColWidgets;
    }

    public void setSecondColWidgets(List<WidgetInstance> secondColWidgets) {
        this.secondColWidgets = secondColWidgets;
    }

    public List<WidgetInstance> getThirdColWidgets() {
        return thirdColWidgets;
    }

    public void setThirdColWidgets(List<WidgetInstance> thirdColWidgets) {
        this.thirdColWidgets = thirdColWidgets;
    }

    public Map<String, String> getWidgetPreferences() {
        return widgetPreferences;
    }

    public void setWidgetPreferences(Map<String, String> widgetPreferences) {
        this.widgetPreferences = widgetPreferences;
    }

    protected void addPagedAttributesToModel(Model model) {
        // set pagination values
        model.addAttribute("availWidgets", getAvailWidgets());
        model.addAttribute("columnNumber", getColumnNumber());
        model.addAttribute("widgetInstances", getWidgetInstances());
        model.addAttribute("firstColWidgets", getFirstColWidgets());
        model.addAttribute("secondColWidgets", getSecondColWidgets());
        model.addAttribute("thirdColWidgets", getThirdColWidgets());
        model.addAttribute("widgetPreferences", getWidgetPreferences());

        super.addPagedAttributesToModel(model);
    }

    /**
     * Set the list of widgets instances for the current user, ready to be processed in the JSPs
     * 
     * @throws ServletException
     */
    protected void setupWidgetInstances(HttpServletRequest request) throws WebException {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setupWidgetInstances");
        }

        List<WidgetInstance> widgetInstances = null;
        List<Widget> availWidgets = null;
        try {
            // Obtain the user widget persistence information for the view
            widgetInstances = getWidgetServices().getWidgetInstancesByTabInstanceId(getTabInstanceId());

            // Obtain the list of available widgets. These are the widgets
            // in the widget catalog
            availWidgets = getWidgetServices().getAvailableWidgetsByTabInstanceId(getTabInstanceId(), getLocale(request), isUserInAdminRole() && isShowDisabledWidgets());

            // Filter the widgets, removing disabled ones
            widgetInstances = removeDisabledWidgets(widgetInstances, availWidgets);

            // Create a default set of widget instances in the DB if there is
            // not any
            // widget open
            // This happens in 2 cases:
            // - first login
            // - the user has closed all the widgets on the page
            if (widgetInstances == null || widgetInstances.isEmpty()) {
                if (logger.isLoggable(FINEST)) {
                    logger.logp(FINEST, CLASS_NAME, "setupWidgetInstances", "Initialise Widget Instances");
                }
                widgetInstances = getWidgetServices().createDefaultWidgetInstancesSetForTabInstanceId(getTabInstanceId(), isShowDisabledWidgets());
            }

            urlNormalizer.translateUrls(availWidgets, request.isSecure());

        } catch (ServiceException ex) {
            String error = getWebResourceBundle().getString("error.userpref.createPersisBean", getPersonInternalId());
            WebException e = new WebException(error, ex);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "handle", error, e);
            }
            throw e;
        }

        // Parse the widget persistence information and create the list of
        // widgets in column one and two which will be passed to the JSP to
        // use
        setWidgetInstances(widgetInstances);
        setAvailWidgets(availWidgets);

        List<WidgetInstance> firstColWidgets = sortBeansByColNum(widgetInstances, Column.COL_1.getId());
        List<WidgetInstance> secondColWidgets = sortBeansByColNum(widgetInstances, Column.COL_2.getId());
        List<WidgetInstance> thirdColWidgets = sortBeansByColNum(widgetInstances, Column.COL_3.getId());

        setFirstColWidgets(firstColWidgets);
        setSecondColWidgets(secondColWidgets);
        setThirdColWidgets(thirdColWidgets);

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setupWidgetInstances");
        }
    }

    /**
     * TODO: The way this handled in the existing code needs complete refactoring
     * 
     * @param widgetInstances
     * @param availWidgets
     * @return
     */
    protected List<WidgetInstance> removeDisabledWidgets(List<WidgetInstance> widgetInstances, List<Widget> availWidgets) {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "removeDisabledWidgets", new Object[] { widgetInstances, availWidgets });
        }

        List<WidgetInstance> newList = new ArrayList<WidgetInstance>();
        if (widgetInstances == null)
            return newList;

        for (WidgetInstance bean : widgetInstances) {
            boolean isEnabled = false;
            for (Widget widget : availWidgets) {
                if (bean.getWidgetId().equalsIgnoreCase(widget.getWidgetId())) {
                    if (logger.isLoggable(FINEST)) {
                        logger.logp(FINEST, CLASS_NAME, "removeDisabledWidgets", "Widget {0} is available", widget.getWidgetId());
                    }
                    newList.add(bean);
                    isEnabled = true;
                    break;
                }
            }

            // If the widget not enabled, and is currently open on the users
            // page...
            if (!isEnabled && bean.isToggled()) {
                if (logger.isLoggable(FINEST)) {
                    logger.logp(FINEST, CLASS_NAME, "removeDisabledWidgets", "Widget {0} is currently open on page, but not available.", bean.getWidgetId());
                }

                // TODO see if anything needs to happen here - apparantly not.
            }
        }
        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "removeDisabledWidgets", newList);
        }
        return newList;
    }

    /**
     * TODO: The way this handled in the existing code needs complete refactoring
     * 
     * @param widgetInstances
     * @param availWidgets
     * @return
     */
    protected List<WidgetInstance> sortBeansByColNum(List<WidgetInstance> widgetInstances, String containerId) {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "sortBeansByColNum", new Object[] { widgetInstances, containerId });

        List<WidgetInstance> sameColBeans = new ArrayList<WidgetInstance>();

        for (WidgetInstance widgetInstance : widgetInstances) {
            if (widgetInstance.getContainer().equals(containerId)) {
                sameColBeans.add(widgetInstance);
            }
        }

        Collections.sort(sameColBeans, new Comparator<WidgetInstance>() {
            public int compare(WidgetInstance o1, WidgetInstance o2) {
                /**
                 * Begin SC hack for smartcloud ST meetings widget top - enforce by ordering Note full solution here is to provide column on WIDGET table with order weighting updatable through the
                 * widget admin page
                 **/
                if (o1.getWidgetId().equals(SC_ST_MEETINGS_WIDGET_ID)) {
                    return -1;
                } else if (o2.getWidgetId().equals(SC_ST_MEETINGS_WIDGET_ID)) {
                    return 1;
                }
                return o1.getOrderSequence() - o2.getOrderSequence();
            }
        });

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "sortBeansByColNum", sameColBeans);

        return sameColBeans;
    }

    /**
     * Check whether the user has ownership on a given widget instance
     * 
     * Used to do various security checks in subclasses
     * 
     * @param userInternalId
     * @param widgetInstanceId
     * @throws SecurityException
     * @throws ServletException
     */
    protected void doWidgetOwnershipCheck(String widgetInstanceId) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "doWidgetOwnershipCheck", new Object[] { getPersonInternalId(), widgetInstanceId });

        // check that the widget instance exists
        WidgetInstance instance = null;
        try {
            instance = getWidgetServices().getWidgetInstance(widgetInstanceId);
        } catch (ServiceException e) {
            // TODO Log
            throw new WebException(e);
        }
        if (instance != null) {

            boolean owned;
            try {
                owned = getSecurityServices().checkWidgetInstanceOwnership(getPersonInternalId(), widgetInstanceId);
            } catch (ServiceException e) {
                // TODO Log
                throw new WebException(e);
            }

            if (!owned) {
                // an user is trying to modify a widget instance belonging
                // to
                // another user...

                String msg = getWebResourceBundle().getString("error.security.widgetInstance.ownership", widgetInstanceId, getPersonInternalId());

                WebException ex = new WebException(msg);
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "doWidgetOwnershipCheck", msg, ex);
                }
                throw ex;
            }
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "doWidgetOwnershipCheck");
    }

    /**
     * Check whether the user has ownership on a given list of widget instances
     * 
     * Used to do various security checks in subclasses
     * 
     * @param userInternalId
     * @param widgets
     * @throws SecurityException
     * @throws ServletException
     */
    protected void doWidgetOwnershipCheck(List<WidgetInstance> widgets) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "doWidgetOwnershipCheck", new Object[] { getPersonInternalId(), widgets });

        for (WidgetInstance widget : widgets) {
            doWidgetOwnershipCheck(widget.getWidgetInstanceId());
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "doWidgetOwnershipCheck");
    }

    /**
     * Prepare the widget preferences (user settings) for the widgets open on the page and set the widgetPreferences property
     * 
     * TODO: Need to be refactored with the widget instance retrieval for the layout
     * 
     * @throws WebException
     */
    protected void prepareWidgetPreferences() throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "prepareWidgetPreferences");

        Map<String, String> descriptors = new HashMap<String, String>();

        List<WidgetInstance> widgetInstances = null;
        try {

            widgetInstances = getWidgetServices().getWidgetInstancesByTabInstanceId(getTabInstanceId());

            if (widgetInstances != null) {
                for (WidgetInstance widgetInstance : widgetInstances) {
                    descriptors.put(widgetInstance.getWidgetInstanceId(), widgetInstance.getWidgetSetting());
                }
            }

            setWidgetPreferences(descriptors);

        } catch (ServiceException e) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.logp(Level.SEVERE, CLASS_NAME, "prepareCachedDescriptors", e.getMessage(), e);
            }

            throw new WebException(e);
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "prepareWidgetPreferences");
    }

    public IUrlNormalizer getUrlNormalizer() {
        return urlNormalizer;
    }

    public void setUrlNormalizer(IUrlNormalizer urlNormalizer) {
        this.urlNormalizer = urlNormalizer;
    }

    public IEventUtils getEventUtils() {
        return eventUtils;
    }

    public void setEventUtils(IEventUtils eventUtils) {
        this.eventUtils = eventUtils;
    }

    protected void populateWidgetEvent(Widget widget, Event event) {

        // Set properties that are always required

        Map<String, String> props = event.getProperties();
        if (widget.getWidgetId() != null) {
            props.put(WidgetEventConstants.ID, widget.getWidgetId());
        }
        if (widget.getTitle() != null) {
            props.put(WidgetEventConstants.TITLE, widget.getTitle());
        }

    }

}
