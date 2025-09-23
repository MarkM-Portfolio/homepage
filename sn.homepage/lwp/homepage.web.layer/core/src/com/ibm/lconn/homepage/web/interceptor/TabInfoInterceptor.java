/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.interceptor;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.AbstractTabInstanceAwareController;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;

/**
 * Interceptor responsible for injecting the current tabInstanceId in the Action (implementing TabInstanceAware). Two main use cases:
 * 
 * 1) If tabInstanceId was sent by the client (HTTP request param), we simply set this value.
 * 
 * 2) If tabInstanceId is not sent by the client, we create a new tab instance in the db and set the corresponding id in the action.
 * 
 * TODO: Need to refactor the logic of this. Was based on a filter
 * 
 * @author vincent
 * 
 */
public class TabInfoInterceptor extends AbstractHomepageInterceptor implements HandlerInterceptor {

    private static final long serialVersionUID = -7508554025953890738L;

    private final static String CLASS_NAME = TabInfoInterceptor.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private ITabServices tabServices;
    @Autowired
    private IUserServices userServices;

    // HTTP request parameter optionally set by the client
    protected static final String TAB_INSTANCE_ID = "tabInstanceId";

    protected void setWidgetTabAvailable(HttpServletRequest request) throws WebException, ServiceException {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setWidgetTabAvailable");
        }

        String tabName = TabType.WIDGET.getDBValue();

        if (logger.isLoggable(FINER)) {
            logger.logp(FINER, CLASS_NAME, "setWidgetTabAvailable", tabName);
        }

        boolean isWidgetTabEnabled = tabServices.isTabEnabled(tabName);
        request.getSession().setAttribute(GlobalSessionConstants.VIEW_ISWIDGETTABENABLED, isWidgetTabEnabled);

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setWidgetTabAvailable", String.valueOf(isWidgetTabEnabled));
        }
    }

    protected String setTabInstanceIdRequestParam(HttpServletRequest request) throws WebException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getTabInstanceIdRequestParam", request);
        }

        String tabInstanceId = null;

        // get internal user id first
        // important note: we are making the assumption that the interceptor is
        // registered AFTER the UserInfo interceptor in the stack
        String internalUserId = getInternalUserId(request);

        if (internalUserId == null) {
            String msg = getWebResourceBundle().getString("error.filter.get.userId", "");
            WebException ex = new WebException(msg);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "getTabInstanceIdRequestParam", msg, ex);
            }
            throw ex;
        }

        try {

            // check if request param already set
            String tabInstanceParam = request.getParameter(TAB_INSTANCE_ID);

            if (tabInstanceParam == null) {
                // param not set, try to locate the tab instance by tab name
                String tabType = getTabTypeParam(request);

                TabType type = TabType.valueOf(tabType.toUpperCase(Locale.ENGLISH));

                if (type != null) {
                    // create new tab instance if needed
                    try {
                        tabInstanceId = tabServices.getTabInstanceId(internalUserId, type);
                    } catch (ServiceException ex) {
                        // do MT fixup
                        this.userServices.fixupWidgetTabOrgIds(internalUserId, ApplicationContext.getOrganizationId());
                        tabInstanceId = tabServices.getTabInstanceId(internalUserId, type);
                    }

                    // action.setTabInstanceId(tabInstanceId);

                    // request.setAttribute(TAB_INSTANCE_ID, tabInstanceId);
                } else {
                    // TODO: i18n this string
                    String msg = "Invalid tabtype " + tabType;

                    WebException t = new WebException(msg);
                    if (logger.isLoggable(SEVERE)) {
                        logger.logp(SEVERE, CLASS_NAME, "getTabInstanceIdRequestParam", msg, t);
                    }
                    throw t;
                }
            } else {
                tabInstanceId = tabInstanceParam;

                // request.setAttribute(TAB_INSTANCE_ID, tabInstanceParam);
            }

            logger.logp(Level.FINEST, CLASS_NAME, "setTabInstanceIdRequestParam", "Setting tabInstanceId=" + tabInstanceId);

            request.getSession().setAttribute(GlobalSessionConstants.VIEW_TABINSTANCE_ID, tabInstanceId);

        } catch (ServiceException e) {
            String msg = getWebResourceBundle().getString("error.filter.get.tab.instance", internalUserId);
            WebException t = new WebException(msg, e);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "getTabInstanceIdRequestParam", msg, t);
            }
            throw t;
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "getTabInstanceIdRequestParam", tabInstanceId);
        }

        return tabInstanceId;
    }

    private String getInternalUserId(HttpServletRequest request) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getInternalUserId", new Object[] { request });
        }

        String internalUserId = null;

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            HttpSession session = httpRequest.getSession();
            internalUserId = (String) session.getAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID);
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "getInternalUserId", internalUserId);
        }

        return internalUserId;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "intercept");

        try {
            return interceptInternal(request, response, handler);
        } catch (Throwable ex) {

            if (logger.isLoggable(Level.SEVERE)) {
                String msg = getWebResourceBundle().getString("error.action.error.general");
                logger.log(Level.SEVERE, msg, ex);
            }

            return false;
        } finally {
            if (logger.isLoggable(FINER))
                logger.exiting(CLASS_NAME, "intercept");
        }

    }
    
    public boolean interceptInternal(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "interceptInternal");
        }

        // figure out whether we are intercepting a controller that extends TabInstanceAware
        boolean isTabInstanceAware = true;
        HandlerMethod hm;
        try {
            hm = (HandlerMethod) handler;
            
            Method method = hm.getMethod();
            // Sometimes Controller.class wont work and you have to use RestController.class just depends on what you use.
            if (AbstractTabInstanceAwareController.class.isAssignableFrom(method.getDeclaringClass())) {
                isTabInstanceAware = true;
            } else {
                isTabInstanceAware = false;
            }
            if (logger.isLoggable(FINER)) {
                logger.log(FINER, "interceptInternal - is tab instance aware? " + isTabInstanceAware);
            }
        } catch (ClassCastException e) {
        }
        
        if (isTabInstanceAware) {
            setTabInstanceIdRequestParam(request);
        }

        // We will let the abstract class catch the exception if there is one
        setWidgetTabAvailable(request);

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "interceptInternal");
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {
    }

    public ITabServices getTabServices() {
        return tabServices;
    }

    public void setTabServices(ITabServices tabSvc) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setTabSvc", tabSvc);
        }

        this.tabServices = tabSvc;

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setTabSvc");
        }
    }

    public void setUserServices(IUserServices userSvc) {
        this.userServices = userSvc;
    }

    public String getTabTypeParam(HttpServletRequest request) {
        String tabTypeParam = "UPDATE";
        ;
        if (StringUtils.contains(request.getRequestURL(), "widgets/")) {
            tabTypeParam = "WIDGET";
        }

        return tabTypeParam;
    }

}
