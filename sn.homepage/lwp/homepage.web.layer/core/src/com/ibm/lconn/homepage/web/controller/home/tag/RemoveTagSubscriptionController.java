/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.controller.home.tag;

import static java.util.logging.Level.FINER;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.core.web.exception.AuthorizationException;
import com.ibm.lconn.core.web.secutil.CSRFHelper;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.lconn.homepage.web.controller.home.AbstractNewsController;

@Controller
@RequestMapping(value = { "/web" })
public class RemoveTagSubscriptionController extends AbstractNewsController {

    private static final long serialVersionUID = 7855669118678971974L;
    private final static String CLASS_NAME = RemoveTagSubscriptionController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private JSONObject jsonObject;
    private String tagId;
    private String tag;

    @RequestMapping(value = "/doRemoveTagSubscription.action", method = RequestMethod.PUT)
    public String removeTagSubscriptionJSON(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        init(request);

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "removeTagSubscriptionJSON");

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
                logger.exiting(CLASS_NAME, "removeTagSubscriptionJSON");
        }
    }

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setTag(request.getParameter("tag"));
        setTagId(request.getParameter("tagId"));
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "executeInternal");
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("X-FBA-CHECK-SKIP", "true");
        // Anti-CSRF by CSRFHelper
        try {
            CSRFHelper.isSafe(request, response);
        } catch (AuthorizationException se) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.logp(Level.FINEST, CLASS_NAME, "executeInternal", "CSRF attack, the request no X-Update-Nonce in header");
            }
            out.print(se.getMessage());
            out.flush();
            out.close();

            return null;
        }

        String personId = getPersonExternalId();
        Locale locale = getLocale(request);

        getNewsService().removeTagSubscription(personId, getTagId(), locale, getTag());
        setJsonObject(getNewsService().getTagSubscriptions(personId, locale));

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "executeInternal");
        }

        out.print(getJsonObject());
        out.flush();
        out.close();
        addPagedAttributesToModel(model, request);
        return null;
    }

    protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {
        model.addAttribute("isPerson", com.ibm.lconn.core.web.auth.LCRestSecurityHelper.isUserInRole(request, "person"));

        super.addPagedAttributesToModel(model);
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
