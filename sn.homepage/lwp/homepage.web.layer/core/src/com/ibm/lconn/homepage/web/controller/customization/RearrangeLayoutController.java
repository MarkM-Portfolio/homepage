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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.events.internal.Event;
import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.model.TabInstance;
import com.ibm.lconn.homepage.model.TabUI;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ISecurityServices;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.AbstractWidgetPageLayoutController;
import com.ibm.lconn.homepage.web.utils.HomepageEventConstants;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

/**
 * Handle requests to rearrange the layout of the page
 * 
 * @author Vincent (based existing 2.0 code from GetUserPref servlet)
 * 
 * 
 */
@Controller
@RequestMapping(value = { "/web" })
public class RearrangeLayoutController extends AbstractWidgetPageLayoutController {

    private static final long serialVersionUID = -7713587002972653899L;

    private final static String CLASS_NAME = RearrangeLayoutController.class.getName();

    private final static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private ITabServices tabServices;

    // private static final String POSITION = "Pos";
    private String pos;

    /**
     * 
     * @param userInternalId
     *            : userInternalId of current login user
     * @param pos
     * 
     * @return List of UserWidgetPref beans
     */
    private void updatePosFromString(String userInternalId, String pos) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "updatePosFromString", new Object[] { userInternalId, pos });

        // list of open widgets on the page according to query string
        // this is the most updated list
        TabUI tabBean = parsePositionString(userInternalId, pos);

        // security checks on the information coming from the JSON string
        // it throws a SecurityException if something goes wrong
        doSecurityChecks(userInternalId, tabBean);

        // list of open widgets on the page according to DB
        List<WidgetInstance> widgetsInDb = getWidgetsToUpdate(userInternalId, tabBean.getTabInstanceId());

        // VB: shouldn't the following be run in a transaction?

        // change position of existing and added widgets to the page
        changePositionOpenWidgets(userInternalId, tabBean);

        // remove widgets not on the page anymore
        deleteWidgetsNotOnPage(userInternalId, tabBean.getWidgetInstances(), widgetsInDb, tabBean.getTabInstanceId());

        updateNumberColumn(tabBean);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "updatePosFromString");
    }

    /**
     * Check that the information initially contained in the JSON string sent to the servet contains valid information
     * 
     * This is where we catch malicious users trying to modify the layout of the page of other users by crafting a JSON string manually
     * 
     * @throws SecurityException
     * @throws WebException
     */
    private void doSecurityChecks(String userInternalId, TabUI tabBean) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "doSecurityChecks", new Object[] { userInternalId, tabBean });

        doTabOwnershipCheck(userInternalId, tabBean);
        doWidgetOwnershipCheck(tabBean.getWidgetInstances());

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "doSecurityChecks");
    }

    private void doTabOwnershipCheck(String userInternalId, TabUI tabBean) throws SecurityException, WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "doTabOwnershipCheck", new Object[] { userInternalId, tabBean });

        ISecurityServices securitySvc = getSecurityServices();

        try {
            boolean owned = securitySvc.checkTabInstanceOwnership(userInternalId, tabBean.getTabInstanceId());

            if (!owned) {
                // an user is trying to modify a tab instance belonging to
                // another user...

                String msg = getWebResourceBundle().getString("error.security.tab.ownership", tabBean.getTabInstanceId(), userInternalId);

                SecurityException ex = new SecurityException(msg);
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "doTabOwnershipCheck", msg, ex);
                }
                throw ex;
            }
        } catch (ServiceException e) {
            String msg = getWebResourceBundle().getString("error.security.service.exception", tabBean.getTabInstanceId());
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "doTabOwnershipCheck", msg);
            }
            throw new WebException(msg, e);
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "doTabOwnershipCheck");
    }

    private void updateNumberColumn(TabUI tabBean) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "updateNumberColumn");

        // update column layout
        try {
            getTabServices().updateNumberColumn(tabBean.getTabInstanceId(), tabBean.getNumberColumns());
        } catch (ServiceException e) {
            String msg = getWebResourceBundle().getString("error.userpref.get.numberColumns", tabBean.getTabInstanceId());
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "updateNumberColumn", msg);
            }
            throw new WebException(msg);
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "updateNumberColumn");
    }

    private void deleteWidgetsNotOnPage(String userInternalId, List<WidgetInstance> widgetsOnPage, List<WidgetInstance> widgetsInDb, String tabId) throws WebException {
        {
            if (logger.isLoggable(FINER))
                logger.entering(CLASS_NAME, "deleteWidgetsNotOnPage", new Object[] { userInternalId, widgetsOnPage, widgetsInDb, tabId });

            // for each widget that was on the page, check if it was removed
            // obviously not the best algorithm (n2) but it will do it for now
            for (WidgetInstance widgetDb : widgetsInDb) {

                boolean isRemoved = true;

                // was the widget removed from the page by the user?
                for (WidgetInstance widgetPage : widgetsOnPage) {
                    if (widgetPage.getWidgetInstanceId().equals(widgetDb.getWidgetInstanceId())) {
                        isRemoved = false;
                        break;
                    }
                }

                if (isRemoved) {
                    WidgetInstance widgetInstance = null;
                    try {
                        widgetInstance = getWidgetServices().getWidgetInstance(widgetDb.getWidgetInstanceId());

                        if (widgetInstance != null) {

                            getWidgetServices().deleteWidgetInstance(widgetInstance.getWidgetInstanceId());

                            auditRemoved(widgetInstance.getWidgetId(), tabId);
                        } else {
                            String msg = getWebResourceBundle().getString("error.userpref.get.widgetprefs", userInternalId, widgetDb.getWidgetInstanceId());
                            if (logger.isLoggable(SEVERE)) {
                                logger.logp(SEVERE, CLASS_NAME, "deleteWidgetsNotOnPage", msg);
                            }
                            throw new WebException(msg);
                        }

                    } catch (ServiceException e) {
                        String error = getWebResourceBundle().getString("error.userpref.delete.update", userInternalId, widgetDb.getWidgetInstanceId());
                        WebException t = new WebException(error, e);
                        if (logger.isLoggable(SEVERE)) {
                            logger.logp(SEVERE, CLASS_NAME, "deleteWidgetsNotOnPage", error, t);
                        }
                        throw t;
                    }
                }
            }

            if (logger.isLoggable(FINER))
                logger.exiting(CLASS_NAME, "deleteWidgetsNotOnPage");
        }

    }

    @SuppressWarnings("unchecked")
    public TabUI parsePositionString(String userInternalId, String pos) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "parsePositionString", new Object[] { userInternalId, pos });

        TabUI tabUI = new TabUI();

        JSONObject jsonObj = null;
        try {
            jsonObj = JSONObject.parse(pos);
        } catch (IOException e) {
            handleParsingException(pos, e);
        }

        String tabId = (String) jsonObj.get("pageId");
        tabUI.setTabInstanceId(tabId);

        long columnNum = (Long) jsonObj.get("column");
        tabUI.setNumberColumns((int) columnNum);

        List<JSONObject> layout = (List<JSONObject>) jsonObj.get("layout");
        List<WidgetInstance> widgets = new ArrayList<WidgetInstance>();

        for (JSONObject container : layout) {
            String containerId = (String) container.get("containerId");

            int orderSeq = 0;

            List<Object> widgetJSONList = (List<Object>) container.get("widgets");

            for (Object widgetData : widgetJSONList) {
                String widgetId = null;
                String widgetInstanceId = null;
                String orgId = null;
                if (widgetData instanceof String) {
                    widgetInstanceId = (String) widgetData;
                } else if (widgetData instanceof JSONObject) {
                    JSONObject widgetDataJSON = (JSONObject) widgetData;
                    widgetId = (String) widgetDataJSON.get("widgetId");
                    widgetInstanceId = (String) widgetDataJSON.get("widgetInstanceId");
                    orgId = (String) widgetDataJSON.get("organizationId");
                } else {
                    handleParsingException(pos, null);
                }

                WidgetInstance widget = new WidgetInstance();
                widget.setWidgetId(widgetId); // can be null
                widget.setWidgetInstanceId(widgetInstanceId);
                widget.setContainer(containerId);
                widget.setOrderSequence(orderSeq);
                widget.setOrganizationId(orgId);

                widgets.add(widget);

                orderSeq++;
            }
        }

        tabUI.setWidgetInstances(widgets);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "parsePositionString", tabUI);

        return tabUI;
    }

    private void handleParsingException(String pos, Exception e) throws WebException {
        String msg = getWebResourceBundle().getString("error.json.parse", pos);
        WebException t = null;

        if (e != null)
            t = new WebException(msg, e);
        else
            t = new WebException(msg);

        if (logger.isLoggable(SEVERE)) {
            logger.logp(SEVERE, CLASS_NAME, "parsePositionString", msg, t);
        }
        throw t;
    }

    private void changePositionOpenWidgets(String userInternalId, TabUI tabUI) throws WebException {

        List<WidgetInstance> widgetsOnPage = tabUI.getWidgetInstances();

        for (WidgetInstance widgetOnPage : widgetsOnPage) {
            try {
                WidgetInstance widgetInDb = getWidgetServices().getWidgetInstance(widgetOnPage.getWidgetInstanceId().trim());

                if (widgetInDb != null) {
                    // change position of existing widget instance
                    int rowNum = widgetOnPage.getOrderSequence();
                    int colNum = Integer.parseInt(widgetOnPage.getContainer());
                    getWidgetServices().changePosition(widgetInDb.getWidgetInstanceId(), rowNum, colNum, tabUI.getTabInstanceId());

                    if (rowNum != widgetInDb.getOrderSequence() || colNum != Integer.parseInt(widgetInDb.getContainer())) {
                        auditMoved(widgetInDb.getWidgetId(), tabUI.getTabInstanceId(), rowNum, colNum);
                    }
                } else {
                    // the user adds a widget instance to the page

                    int orderNumber = widgetOnPage.getOrderSequence();
                    String containerId = widgetOnPage.getContainer();

                    // first check that the user did not manipulate the JSON
                    // string to add a widget not allowed on this tab
                    // this throws a security exception if check fails
                    doCheckWidgetAllowedOnTab(widgetOnPage.getWidgetId(), tabUI.getTabInstanceId(), userInternalId);

                    // Important note: we set the WidgetInstanceId here, which
                    // is the pk in the DB
                    // the widgetinstanceId is generated client-side to save on
                    // ajax request, which
                    // means that there is a very very small
                    // chance that the picked random id already exists in the
                    // DB. We let the insert fail here,
                    // in worse case, the new widget would no be persisted which
                    // is a small drawback compared to the performance gain of
                    // not used an ajax request
                    getWidgetServices().createWidgetInstance(widgetOnPage.getWidgetId().trim(), tabUI.getTabInstanceId(), orderNumber, containerId, true, widgetOnPage.getWidgetSetting(),
                            widgetOnPage.getWidgetInstanceId(), widgetOnPage.getOrganizationId());

                    auditAdded(widgetOnPage.getWidgetId(), tabUI.getTabInstanceId());
                }
            } catch (ServiceException ex) {
                String msg = getWebResourceBundle().getString("error.userpref.get.widgetprefs", userInternalId, widgetOnPage.getWidgetId().trim());
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "changePositionOpenWidgets", msg);
                }
                throw new WebException(msg, ex);

            }
        }
    }

    private void doCheckWidgetAllowedOnTab(String widgetId, String tabInstanceId, String userInternalId) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "doCheckWidgetAllowedOnTab", new Object[] { widgetId, tabInstanceId, userInternalId });

        ISecurityServices securitySvc = getSecurityServices();

        try {
            boolean allowed = securitySvc.checkWidgetAllowedOnWidgetTab(widgetId, tabInstanceId);

            if (!allowed) {
                // user probably crafted the JSON string to add a widget to the
                // page...

                String msg = getWebResourceBundle().getString("error.security.widget.notAllowedOnTab", widgetId, tabInstanceId, userInternalId);

                SecurityException ex = new SecurityException(msg);
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "doCheckWidgetAllowedOnTab", msg, ex);
                }
                throw ex;
            }
        } catch (ServiceException e) {
            String msg = getWebResourceBundle().getString("error.security.service.exception", tabInstanceId);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "doCheckWidgetAllowedOnTab", msg);
            }
            throw new WebException(msg, e);
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "doCheckWidgetAllowedOnTab");

    }

    private List<WidgetInstance> getWidgetsToUpdate(String userInternalId, String tabId) throws WebException {

        List<WidgetInstance> userWidgetPrefs;

        try {
            userWidgetPrefs = getWidgetServices().getWidgetsInstancesForTabInstance(tabId);

            ListIterator<WidgetInstance> it = userWidgetPrefs.listIterator();

            while (it.hasNext()) {
                WidgetInstance widgetInst = it.next();

                // only returns the open widgets
                // TODO: add SQL query instead
                int rowNum = widgetInst.getOrderSequence();
                // int colNum = widgetInst.getColNum();
                int colNum = Integer.parseInt(widgetInst.getContainer());

                if ((rowNum == 0) && (colNum == 0))
                    it.remove();
            }

        } catch (ServiceException e) {
            String msg = getWebResourceBundle().getString("error.widgetInfo.retrieve");
            WebException t = new WebException(msg, e);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "updatePosFromString", msg, t);
            }
            throw t;
        }

        return userWidgetPrefs;
    }

    public void init(HttpServletRequest request) {
        // TODO Auto-generated method stub

        setPos(null);

        if (request.getParameter("Pos") != null) {
            setPos(request.getParameter("Pos"));
        }

        super.init(request);

    }

    public String executeInternal(HttpServletRequest request) throws WebException {

        init(request);

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "executeInternal");
        }

        String userInternalId = getPersonInternalId();
        String pos = getPos();

        if (pos != null)
            updatePosFromString(userInternalId, pos);
        else {
            String msg = getWebResourceBundle().getString("error.userpref.position", userInternalId);
            WebException t = new WebException(msg);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "handle", msg, t);
            }
            throw t;
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "executeInternal");
        }

        return null;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setPos", pos);
        }

        this.pos = pos;

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setPos");
        }
    }

    public ITabServices getTabServices() {
        return tabServices;
    }

    public void setTabServices(ITabServices tabServices) {
        this.tabServices = tabServices;
    }

    private void auditAdded(String widgetId, String tabInstanceId) throws WebException, ServiceException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "auditAdded", new Object[] { widgetId, tabInstanceId });
        }

        if (getEventUtils().isRequiredPost(IEventUtils.USER_WIDGET_ADD, Type.CREATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.USER_WIDGET_ADD, Type.CREATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), true);

            Widget widget = getWidgetServices().getWidget(widgetId, true);
            populateWidgetEvent(widget, event);
            if (tabInstanceId != null) {
                TabInstance tab = getTabServices().getTabInstance(tabInstanceId);
                event.getProperties().put(HomepageEventConstants.TAB, tab.getTabId());
                event.getProperties().put(HomepageEventConstants.TAB_NAME, tab.getTabName());
            }
            getEventUtils().sendPost(event);

            if (logger.isLoggable(FINER)) {
                logger.exiting(CLASS_NAME, "auditAdded");
            }
        }
    }

    private void auditRemoved(String widgetId, String tabInstanceId) throws WebException, ServiceException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "auditRemoved", new Object[] { widgetId, tabInstanceId });
        }

        if (getEventUtils().isRequiredPost(IEventUtils.USER_WIDGET_REMOVE, Type.DELETE)) {
            Event event = getEventUtils().createEvent(IEventUtils.USER_WIDGET_REMOVE, Type.DELETE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), true);

            Widget widget = getWidgetServices().getWidget(widgetId, true);
            populateWidgetEvent(widget, event);
            if (tabInstanceId != null) {
                TabInstance tab = getTabServices().getTabInstance(tabInstanceId);
                event.getProperties().put(HomepageEventConstants.TAB, tab.getTabId());
                event.getProperties().put(HomepageEventConstants.TAB_NAME, tab.getTabName());
            }
            getEventUtils().sendPost(event);
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "auditRemoved");
        }
    }

    private void auditMoved(String widgetId, String tabInstanceId, int row, int col) throws WebException, ServiceException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "auditMoved", new Object[] { widgetId, tabInstanceId });
        }

        if (getEventUtils().isRequiredPost(IEventUtils.USER_WIDGET_MOVE, Type.UPDATE)) {
            Event event = getEventUtils().createEvent(IEventUtils.USER_WIDGET_MOVE, Type.UPDATE, getPersonExternalId(), getPersonDisplayName(), getPersonEmail(), true);

            Widget widget = getWidgetServices().getWidget(widgetId, true);
            populateWidgetEvent(widget, event);
            event.getProperties().put(HomepageEventConstants.ROW, Integer.toString(row));
            event.getProperties().put(HomepageEventConstants.COLUMN, Integer.toString(col));
            if (tabInstanceId != null) {
                TabInstance tab = getTabServices().getTabInstance(tabInstanceId);
                event.getProperties().put(HomepageEventConstants.TAB, tab.getTabId());
                event.getProperties().put(HomepageEventConstants.TAB_NAME, tab.getTabName());
            }
            getEventUtils().sendPost(event);
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "auditMoved");
        }
    }

}
