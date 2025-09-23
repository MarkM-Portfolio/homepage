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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetCacheInvalidatorRemote;
import com.ibm.lconn.core.tkrproxysvc.service.LCRemoteServiceFactory;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.tk.rproxysvc.service.TKRemoteServiceDescriptor;

@Controller
@RequestMapping(value = { "/admin" })
public class ClearWidgetCacheController extends AbstractWidgetAdminController {

    private static final long serialVersionUID = 2714894124831281085L;

    private static String CLASS_NAME = ClearWidgetCacheController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @RequestMapping(value = { "/clearWidgetCache.action" }, method = RequestMethod.POST)
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

        final boolean error = clearWidgetCache();

        if (error) {
            setErrorMessageKey("jsp.admin.error.refreshcache");
        }

        setupASConfig(request);
        addPagedAttributesToModel(model, request);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "execute");
        return ViewConstants.VIEW_HOMEPAGE_ADMIN_ADMINPAGE;
    }

    /**
     * Return true if error
     * 
     * @return
     */
    private boolean clearWidgetCache() {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "clearWidgetCache");

        boolean retval = false;
        try {
            Holder.getWidgetCacheInvalidatorRemote().clearCachedWidgets();
        } catch (Exception e) {
            logger.logp(Level.SEVERE, CLASS_NAME, "clearWidgetCache", e.getMessage(), e);
            retval = true;
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "clearWidgetCache", true);

        return retval;
    }

    private static class Holder {
        private static WidgetCacheInvalidatorRemote widgetCacheInvalidatorRemote = null;

        public static WidgetCacheInvalidatorRemote getWidgetCacheInvalidatorRemote() {
            if (widgetCacheInvalidatorRemote == null) {
                TKRemoteServiceDescriptor<WidgetCacheInvalidatorRemote> descriptor = new TKRemoteServiceDescriptor<WidgetCacheInvalidatorRemote>(WidgetCacheInvalidatorRemote.class, "opensocial");
                widgetCacheInvalidatorRemote = LCRemoteServiceFactory.getInstance(descriptor, WidgetCacheInvalidatorRemote.class.getClassLoader());
            }
            return widgetCacheInvalidatorRemote;
        }
    }

}
