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

import static java.util.logging.Level.SEVERE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.ibm.icu.text.Normalizer;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.AbstractSessionAwareController;
import com.ibm.lconn.homepage.web.utils.IEventUtils;
import com.ibm.lconn.homepage.web.utils.IUrlNormalizer;
import com.ibm.lconn.homepage.web.utils.WidgetEventConstants;

public class AbstractWidgetAdminController extends AbstractSessionAwareController {

    private static final long serialVersionUID = 5637260690749696808L;

    @Autowired
    private ServletContext context;

    @Autowired
    private IUserServices userServices;

    @Autowired
    private IWidgetServices widgetServices;

    @Autowired
    private IEventUtils eventUtils;

    @Autowired
    private IConfigurationService configurationService;

    @Autowired
    private IUrlNormalizer urlNormalizer;

    private String widgetID;

    @Autowired
    private ResourceBundle webResourceBundle;

    private Map<String, Widget> enabledWidgetsMap = null;

    private Map<String, Widget> disabledWidgetsMap = null;

    private String errorMessageKey;

    private static String CLASS_NAME = AbstractWidgetAdminController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    public void init(HttpServletRequest request) {
        // TODO Auto-generated method stub

        super.init(request);

        enabledWidgetsMap = null;
        disabledWidgetsMap = null;
        errorMessageKey = null;
        widgetID = null;

    }

    protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("isPerson", com.ibm.lconn.core.web.auth.LCRestSecurityHelper.isUserInRole(request, "person"));
            model.addAttribute("disabledWidgets", getDisabledWidgets(request));
            model.addAttribute("enabledWidgets", getEnabledWidgets(request));
            model.addAttribute("testModelChecked", getTestModelChecked());
            model.addAttribute("errorMessageKey", getErrorMessageKey());
        } catch (WebException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("widgetId", getWidgetID());

        super.addPagedAttributesToModel(model);
    }

    private static final Comparator<Widget> widgetNameComparator = new Comparator<Widget>() {
        public int compare(Widget lhsWidget, Widget rhsWidget) {
            final String lhs = StringUtils.defaultString(lhsWidget.getTitle());
            final String rhs = StringUtils.defaultString(rhsWidget.getTitle());

            return Normalizer.compare(lhs, rhs, Normalizer.COMPARE_IGNORE_CASE);
        }
    };

    public Collection<Widget> getDisabledWidgets(HttpServletRequest request) throws WebException {
        final List<Widget> disabledWidgets = new ArrayList<Widget>();

        if (disabledWidgetsMap == null)
            try {
                disabledWidgetsMap = widgetServices.getDisabledWidgetsForAdmin(getLocale(request));
            } catch (ServiceException ex) {
                String error = getWebResourceBundle().getString("error.admin.enable");
                WebException e = new WebException(error, ex);
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "getDisabledWidgets", error, e);
                }
                throw e;
            }

        if (disabledWidgetsMap != null) {
            disabledWidgets.addAll(disabledWidgetsMap.values());
        }

        Collections.sort(disabledWidgets, widgetNameComparator);

        return disabledWidgets;
    }

    public Collection<Widget> getEnabledWidgets(HttpServletRequest request) throws WebException {

        final List<Widget> enabledWidgets = new ArrayList<Widget>();

        if (enabledWidgetsMap == null)
            try {
                enabledWidgetsMap = widgetServices.getEnabledWidgetsForAdminPage(getLocale(request));
            } catch (ServiceException ex) {
                String error = getWebResourceBundle().getString("error.admin.enable");
                WebException e = new WebException(error, ex);
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "getEnabledWidgets", error, e);
                }
                throw e;
            }

        if (enabledWidgetsMap != null) {
            enabledWidgets.addAll(enabledWidgetsMap.values());
        }

        Collections.sort(enabledWidgets, widgetNameComparator);

        return enabledWidgets;
    }

    public void setServletContext(ServletContext context) {
        this.setContext(context);
    }

    public void setWidgetServices(IWidgetServices widgetServices) {
        this.widgetServices = widgetServices;
    }

    public IWidgetServices getWidgetServices() {
        return widgetServices;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public ServletContext getContext() {
        return context;
    }

    public void setUrlNormalizer(IUrlNormalizer normalizer) {
        this.urlNormalizer = normalizer;
    }

    public IUrlNormalizer getUrlNormalizer() {
        return urlNormalizer;
    }

    public void setWebResourceBundle(ResourceBundle resourceBundle) {
        this.webResourceBundle = resourceBundle;
    }

    public IEventUtils getEventUtils() {
        return eventUtils;
    }

    public void setEventUtils(IEventUtils eventUtils) {
        this.eventUtils = eventUtils;
    }

    public ResourceBundle getWebResourceBundle() {
        return webResourceBundle;
    }

    public void setWidgetID(String widgetID) {
        this.widgetID = widgetID;
    }

    public String getWidgetID() {
        return widgetID;
    }

    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    public IUserServices getUserServices() {
        return userServices;
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public String getTestModelChecked() throws WebException {
        String userInternalId = getPersonInternalId();
        boolean checked = false;
        try {
            checked = getUserServices().isAdminTestModeActive(userInternalId);
        } catch (ServiceException ex) {
            String error = getWebResourceBundle().getString("error.admin.testmode", userInternalId);
            WebException e = new WebException(error, ex);
            if (logger.isLoggable(SEVERE))
                logger.logp(SEVERE, CLASS_NAME, "testModelChecked", error, e);
            throw e;
        }
        return checked ? "checked" : "";
    }

    protected void populateWidgetAdminEvent(Widget widget, Event event, boolean full) {

        // Set properties that are always required

        Map<String, String> props = new HashMap<String, String>();
        if (widget.getWidgetId() != null) {
            props.put(WidgetEventConstants.ID, widget.getWidgetId());
        }
        if (widget.getTitle() != null) {
            props.put(WidgetEventConstants.TITLE, widget.getTitle());
        }
        props.put(WidgetEventConstants.ENABLED, Boolean.toString(widget.isEnabled()));

        if (full) {
            // Add all widget details
            if (widget.getText() != null) {
                props.put(WidgetEventConstants.DESCRIPTION, widget.getText());
            }
            if (widget.getUrl() != null) {
                props.put(WidgetEventConstants.URL, widget.getUrl());
            }

            if (widget.getSecureUrl() != null) {
                props.put(WidgetEventConstants.SECURE_URL, widget.getSecureUrl());
            }
            if (widget.getIconUrl() != null) {
                props.put(WidgetEventConstants.ICON_URL, widget.getIconUrl());
            }
            if (widget.getSecureIconUrl() != null) {
                props.put(WidgetEventConstants.SECURE_ICON_URL, widget.getSecureIconUrl());
            }
            props.put(WidgetEventConstants.USE_CONNECTIONS_TAGS, Boolean.toString(widget.isHomepageSpecific()));
            props.put(WidgetEventConstants.CACHE_DESCRIPTOR, Boolean.toString(widget.getIsMarkedCachable()));
            props.put(WidgetEventConstants.DISPLAY_TAB_WIDGETS, Boolean.toString(widget.isBelongTabWidget()));
            props.put(WidgetEventConstants.DISPLAY_TAB_UPDATES, Boolean.toString(widget.isBelongTabUpdate()));
            props.put(WidgetEventConstants.OPEN_BY_DEFAULT, Boolean.toString(widget.isDefaultOpened()));
            props.put(WidgetEventConstants.MULTIPLE, Boolean.toString(widget.isMultipleInstanceAllowed()));

            // Add prereqs as a comma-delimited list
            Collection<String> prereqs = widget.getPrereqs();
            if (prereqs != null && prereqs.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                Iterator<String> iter = prereqs.iterator();
                while (iter.hasNext()) {
                    String prereq = iter.next();
                    buffer.append(prereq);
                    if (iter.hasNext()) {
                        buffer.append(",");
                    }
                }
                props.put(WidgetEventConstants.PREREQS, buffer.toString());
            }

        }
        event.setProperties(props);
    }

    public void setErrorMessageKey(String m) {
        this.errorMessageKey = m;
    }

    public String getErrorMessageKey() {
        return this.errorMessageKey;
    }

}
