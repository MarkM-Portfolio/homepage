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

package com.ibm.lconn.homepage.web.controller.metrics;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.SEVERE;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.lconn.homepage.model.Metric;
import com.ibm.lconn.homepage.model.ServerMetricsData;
import com.ibm.lconn.homepage.services.IServerMetricsServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.utils.IUIServiceUtils;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.controller.AbstractSessionAwareController;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.lconn.homepage.web.utils.INewsConfigUtil;

@Controller
@RequestMapping(value = "/web")
public class ServerMetricsController extends AbstractSessionAwareController {

    private static final long serialVersionUID = 7187244401915125382L;

    private final static String CLASS_NAME = ServerMetricsController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private IServerMetricsServices serverMetricsServices;

    @Autowired
    private IUIServiceUtils uiServicesUtils;

    @Autowired
    private ResourceBundle webResourceBundle;

    @Autowired
    private INewsConfigUtil newsConfigUtils;

    private List<ServerMetricsData> userMetrics;
    private List<ServerMetricsData> widgetMetrics;
    private List<ServerMetricsData> storyMetrics;
    private List<ServerMetricsData> nofificationMetrics;
    private List<ServerMetricsData> miscMetrics;

    private Date date;
    private int storyLifeTimeInDays;

    private List<List<ServerMetricsData>> renderMetricAsList;
    private List<List<ServerMetricsData>> renderMetricAsItem;

    private List<Metric> metricBlacklist;

    public ServerMetricsController() {
        // There are a bunch of metrics added by News to the DB.
        // Need to filter these out of list returned to the UI
        metricBlacklist = new ArrayList<Metric>(10);

        metricBlacklist.add(Metric.NO_PROFILES_BOARD_POSTS_TODAY);
        metricBlacklist.add(Metric.NO_PROFILES_BOARD_POSTS_TOTAL);
        metricBlacklist.add(Metric.NO_PROFILES_BOARD_POSTS_WITHCOMMENT);
        metricBlacklist.add(Metric.NO_PROFILES_BOARD_USERS_WITHPOST);

        metricBlacklist.add(Metric.NO_COMMUNITIES_BOARD_POSTS_WITHCOMMENT);
        metricBlacklist.add(Metric.NO_COMMUNITIES_BOARD_POSTS_TODAY);
        metricBlacklist.add(Metric.NO_COMMUNITIES_BOARD_POSTS_TOTAL);

        metricBlacklist.add(Metric.NO_ALL_BOARD_POSTS_WITHCOMMENT);
        metricBlacklist.add(Metric.NO_ALL_BOARD_POSTS_TODAY);
        metricBlacklist.add(Metric.NO_ALL_BOARD_POSTS_TOTAL);
    }

    public List<List<ServerMetricsData>> getRenderMetricAsItem() {
        return renderMetricAsItem;
    }

    public List<List<ServerMetricsData>> getRenderMetricAsList() {
        return renderMetricAsList;
    }

    public Date getDate() {
        return date;
    }

    public List<ServerMetricsData> getUserMetrics() {
        return userMetrics;
    }

    public List<ServerMetricsData> getWidgetMetrics() {
        return widgetMetrics;
    }

    public List<ServerMetricsData> getStoryMetrics() {
        return storyMetrics;
    }

    public List<ServerMetricsData> getNofificationMetrics() {
        return nofificationMetrics;
    }

    public List<ServerMetricsData> getMiscMetrics() {
        return miscMetrics;
    }

    @RequestMapping(value = "/servermetrics", method = RequestMethod.GET)
    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        boolean set = setServerMetricsData(request, response);
        setStoryLifeTimeInDays(newsConfigUtils.getStoryLifeTimeInDays());
        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "execute");
        addPagedAttributesToModel(model);
        if (set) {
            return ViewConstants.VIEW_HOMEPAGE_MAIN_METRICSPAGE;
        } else {
            return ViewConstants.VIEW_HOMEPAGE_MAIN_NOTSET;
        }
    }

    protected void addPagedAttributesToModel(Model model) {
        model.addAttribute("date", getDate());
        model.addAttribute("storyLifeTimeInDays", getStoryLifeTimeInDays());
        model.addAttribute("renderMetricAsList", getRenderMetricAsList());
        model.addAttribute("renderMetricAsItem", getRenderMetricAsItem());

        super.addPagedAttributesToModel(model);
    }

    private boolean setServerMetricsData(HttpServletRequest request, HttpServletResponse response) {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "setServerMetricsData");
        boolean metricsSet = true;
        Locale locale = this.getLocale(request);
        List<ServerMetricsData> metrics = null;
        renderMetricAsList = new ArrayList<List<ServerMetricsData>>();
        renderMetricAsItem = new ArrayList<List<ServerMetricsData>>();
        try {
            for (Metric metric : Metric.values()) {
                if (!metricBlacklist.contains(metric)) {
                    metrics = serverMetricsServices.getServerMetricsData(metric.getType());
                    if (metrics == null || metrics.size() == 0) {
                        metricsSet = false;
                        break;
                    } else {
                        if (metrics.get(0) != null) {
                            date = metrics.get(0).getRecordedOn();
                        }
                        if (metric.getStyle().equalsIgnoreCase("list")) {
                            localizeListStrings(metrics, locale);
                            // fix added for SPR #ZGZG8QG9GS
                            if (metrics.size() > 0) {// if no strings are found then dont add this metric
                                renderMetricAsList.add(metrics);
                            }
                        } else if (metric.getStyle().equalsIgnoreCase("trend")) {
                            renderMetricAsList.add(metrics);
                        } else {
                            renderMetricAsItem.add(metrics);
                        }
                    }
                }
            }

        } catch (ServiceException e) {
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "setServerMetricsData", "Error setting metrics in metrics action", e);
            }
            metricsSet = false;
            String error = webResourceBundle.getString("info.action.metrics", locale);
            if (logger.isLoggable(FINEST))
                logger.logp(FINEST, CLASS_NAME, "setServerMetricsData", error);
            // addActionError(error);
        }
        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "setServerMetricsData", metricsSet);
        return metricsSet;
    }

    private void localizeListStrings(List<ServerMetricsData> metrics, Locale locale) {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "localizeListStrings", new Object[] { metrics });
        for (ServerMetricsData datum : metrics) {
            if (datum.getType().equalsIgnoreCase("list")) {

                Map<String, Long> top5list = datum.getTop5list();
                if (top5list != null && top5list.size() > 0) {
                    Map<String, Long> localisedMap = new LinkedHashMap<String, Long>();
                    String localiziedKey = null;
                    for (String key : top5list.keySet()) {
                        long value = top5list.get(key);
                        localiziedKey = uiServicesUtils.getGlobalizedMsgFromCatalogUI(key, locale);
                        if (localiziedKey != null)
                            localisedMap.put(localiziedKey, value);
                    }
                    datum.setTop5list(localisedMap);
                } else {// the list is empty so we dont want to add it
                        // fix added for SPR #ZGZG8QG9GS
                    metrics.remove(datum);
                }

            }
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "localizeListStrings");
    }

    public int getStoryLifeTimeInDays() {
        return storyLifeTimeInDays;
    }

    public void setStoryLifeTimeInDays(int storyLifeTimeInDays) {
        this.storyLifeTimeInDays = storyLifeTimeInDays;
    }

}
