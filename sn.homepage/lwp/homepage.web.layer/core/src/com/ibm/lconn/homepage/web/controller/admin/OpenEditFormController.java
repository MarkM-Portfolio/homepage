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
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.ViewConstants;

@Controller
@RequestMapping(value = { "/admin" })
public class OpenEditFormController extends AbstractWidgetAdminController {

    private static final long serialVersionUID = 9000639918136261173L;

    private static String CLASS_NAME = OpenEditFormController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private WidgetFormFacadeSpring widgetFacade;

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        setupASConfig(request);

        Widget widget = null;

        if (getWidgetID() != null && !getWidgetID().equals("") && !getWidgetID().equals("null")) {
            try {
                widget = getWidgetServices().getWidgetNonLocalized(getWidgetID(), true);
            } catch (ServiceException ex) {
                String error = getWebResourceBundle().getString("error.admin.retrieve", getWidgetID());
                WebException e = new WebException(error, ex);
                if (logger.isLoggable(SEVERE))
                    logger.logp(SEVERE, CLASS_NAME, "editWidget", error, e);
                throw e;
            }
        }

        widgetFacade.setWidget(widget == null ? new Widget() : widget);

        setNonce(request, response);
        addPagedAttributesToModel(model, request);

        return ViewConstants.VIEW_HOMEPAGE_ADMIN_EDITFORM;
    }

    @RequestMapping(value = { "/openEditForm.action" }, method = RequestMethod.POST)
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "widgetID", required = false) String widgetId,
            @RequestParam(value = "X-Update-Nonce", required = false) String nonce) throws Exception {

        init(request);
        setWidgetID(widgetId);
        setNonce(nonce);
        getModel(request);

        setNonce(request, response);

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

    @RequestMapping(value = { "/newOpenEditForm.action" }, method = RequestMethod.POST)
    public String executeNewOpenEditForm(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "widgetID", required = false) String widgetId,
            @RequestParam(value = "X-Update-Nonce", required = false) String nonce) throws Exception {

        init(request);
        setWidgetID(widgetId);
        setNonce(nonce);
        getModel(request);

        setNonce(request, response);

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        addPagedAttributesToModel(model, request);

        return newOpenEditForm();

    }

    protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {

        super.addPagedAttributesToModel(model);

        model.addAttribute("welcomeNoteClass", widgetFacade.getEnabled());

        model.addAttribute("gadgetOauth", widgetFacade.getGadgetOauth());
        model.addAttribute("iconUrl", widgetFacade.getIconUrl());
        model.addAttribute("isBelongTabUpdateChecked", widgetFacade.getIsBelongTabUpdateChecked());
        model.addAttribute("isBelongTabWidgetChecked", widgetFacade.getIsBelongTabWidgetChecked());
        model.addAttribute("isDefaultOpenedChecked", widgetFacade.getIsDefaultOpenedChecked());
        model.addAttribute("isGadget", widgetFacade.getIsGadget());
        model.addAttribute("isGadgetInEEChecked", widgetFacade.getIsGadgetInEEChecked());
        model.addAttribute("isGadgetInShareboxChecked", widgetFacade.getIsGadgetInShareboxChecked());
        model.addAttribute("isGadgetTrusted", widgetFacade.getIsGadgetTrusted());
        model.addAttribute("isGadgetUrlPolicy", widgetFacade.getIsGadgetUrlPolicy());
        model.addAttribute("isHomepageSpecificChecked", widgetFacade.getIsHomepageSpecificChecked());
        model.addAttribute("isMarkedCachableChecked", widgetFacade.getIsMarkedCachableChecked());
        model.addAttribute("isMultipleInstanceAllowedChecked", widgetFacade.getIsMultipleInstanceAllowedChecked());
        model.addAttribute("isSSOChecked", widgetFacade.getIsSSOChecked());
        model.addAttribute("isSystemChecked", widgetFacade.getIsSystemChecked());
        model.addAttribute("orderAfterId", widgetFacade.getOrderAfterId());
        model.addAttribute("previewImage", widgetFacade.getPreviewImage());
        model.addAttribute("secureIconUrl", widgetFacade.getSecureIconUrl());
        model.addAttribute("secureUrl", widgetFacade.getSecureUrl());
        model.addAttribute("serviceMapEntries", widgetFacade.getServiceMapEntries());
        model.addAttribute("text", widgetFacade.getText());
        model.addAttribute("title", widgetFacade.getTitle());
        model.addAttribute("url", widgetFacade.getUrl());
        model.addAttribute("widget", widgetFacade.getWidget());
        model.addAttribute("widgetId", widgetFacade.getWidgetId());

        try {
            model.addAttribute("installedComponents", widgetFacade.getInstalledComponents());
            model.addAttribute("prereqCheck", widgetFacade.getPrereqCheck());
            model.addAttribute("shareGadgetsOptions", widgetFacade.getShareGadgetsOptions());
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String newOpenEditForm() {
        widgetFacade.setWidget(new Widget());
        return ViewConstants.VIEW_HOMEPAGE_ADMIN_EDITFORM;
    }

    public WidgetFormFacadeSpring getModel(HttpServletRequest request) {
        widgetFacade = new WidgetFormFacadeSpring();
        widgetFacade.setWidget(new Widget());
        widgetFacade.setInstalledComponents(getConfigurationService().getInstalledComponents());
        widgetFacade.setLocale(getLocale(request));
        widgetFacade.setWidgetServices(getWidgetServices());
        return widgetFacade;
    }

}
