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

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.controller.AbstractWebController;
import com.ibm.lconn.homepage.web.controller.ViewConstants;

/**
 * Legacy action that map the former handleUP in 2.0/2.5 to new customization actions in the same package
 * 
 * @author vincent
 * 
 */
@Controller
@RequestMapping(value = { "/web" })
public class LegacyCustomizationController extends AbstractWebController {

    private static final long serialVersionUID = -1112149535340775089L;

    private final static String CLASS_NAME = LegacyCustomizationController.class.getName();

    private final static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private RearrangeLayoutController rearrangeLayoutController;
    @Autowired
    private LoadCustomizationController loadCustomizationController;
    @Autowired
    private SaveCustomizationController saveCustomizationController;

    // Mapping between the former "Act" request param and new Struts action
    private final static Map<String, String> actParamToStrutsAction;

    static {
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("ChangePos".toUpperCase(Locale.US), "doRearrangeLayout");
        mapping.put("SaveCustomization".toUpperCase(Locale.US), "doSaveCustomization");
        mapping.put("LoadCustomization".toUpperCase(Locale.US), "doLoadCustomization");

        actParamToStrutsAction = Collections.unmodifiableMap(mapping);
    }

    // request param
    private String act;

    // used by the Result object to invoke the action (see
    // struts-customization.xml)
    private String actionName;

    // These are not used within this action but subsequent actions
    // (such as SaveCustomizationAction) make use of them. Added here
    // to stop exceptions in the logs.
    private String FId;
    private String data;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "setAct", act);

        this.act = act;

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "setAct");
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getFId() {
        return FId;
    }

    public void setFId(String fid) {
        FId = fid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void init(HttpServletRequest request) {
        // TODO Auto-generated method stub

        // setAct(null);
        // setData(null);

        if (request.getParameter("Act") != null) {
            setAct(request.getParameter("Act"));
        }
        
        if (request.getParameter("Pos") != null) {
        	setData(request.getParameter("Pos"));
        }
        
        if (request.getParameter("data") != null) {
        	setData(request.getParameter("data"));
        }

        super.init(request);

    }

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "executeInternal");

        init(request);

        String action = getAct();

        if (action != null) {
            action = action.toUpperCase(Locale.US);

            if (actParamToStrutsAction.containsKey(action)) {
                setActionName(actParamToStrutsAction.get(action));

                if (getActionName() == "doRearrangeLayout") {
                    rearrangeLayoutController.executeInternal(request);
                } else if (getActionName() == "doSaveCustomization") {
                	saveCustomizationController.executeInternal(request, getFId());
                } else if (getActionName() == "doLoadCustomization") {
                	loadCustomizationController.executeInternal(request, getFId());
                } else {

                }
            }
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "executeInternal");

        return null;

    }

    @RequestMapping(value = { "/handleUP.action" }, method = RequestMethod.POST)
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "Act", required = false) String Act, @RequestParam(value = "FId", required = false) String FId) throws Exception {

        init(request);
        
        if (FId != null) {
        	setFId(FId);
        }

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        try {
            return executeInternal(model, request, response);
        } catch (ServiceException se) {
            logger.log(Level.SEVERE, se.getMessage(), se);
            response.sendError(400, se.getErrorCode());
            return ViewConstants.VIEW_ERROR;
        } catch (WebException we) {
            logger.log(Level.SEVERE, we.getMessage(), we);
            response.sendError(400, we.getMessage());
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

}
