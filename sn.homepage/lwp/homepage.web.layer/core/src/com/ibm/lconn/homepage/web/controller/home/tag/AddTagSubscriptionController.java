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
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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
public class AddTagSubscriptionController extends AbstractNewsController {

    private static final long serialVersionUID = -2003048212297649514L;
    private final static String CLASS_NAME = AddTagSubscriptionController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    Pattern tagSplitter = Pattern.compile("[,\\s]+");

    private JSONObject jsonObject;
    private String tag;

    @RequestMapping(value = "/doAddTagSubscription.action", method = RequestMethod.POST)
    public String addTagSubscriptionJSON(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        init(request);

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "addTagSubscriptionJSON");

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
                logger.exiting(CLASS_NAME, "addTagSubscriptionJSON");
        }

    }

    public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setTag(request.getParameter("tag"));
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "addTagSubscriptionJSON");
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
                logger.logp(Level.FINEST, CLASS_NAME, "addTagSubscriptionJSON", "CSRF attack, the request no X-Update-Nonce in header");
            }
            out.print(se.getMessage());
            out.flush();
            out.close();

            return null;
        }

        String personId = getPersonExternalId();
        Locale locale = getLocale(request);

        String tag = getTag();
        if (logger.isLoggable(Level.FINEST)) {
            logger.logp(Level.FINEST, CLASS_NAME, "addTagSubscriptionJSON", "Handling tag add for: {0}", tag);
        }
        // Split by space and comma
        String[] tags = tagSplitter.split(tag);
        if (logger.isLoggable(Level.FINEST)) {
            logger.logp(Level.FINEST, CLASS_NAME, "addTagSubscriptionJSON", "Tags: {0}", Arrays.toString(tags));
        }
        for (String t : tags) {
            String theTag = t.trim();
            if (!theTag.isEmpty()) {
                if (logger.isLoggable(Level.FINEST)) {
                    logger.logp(Level.FINEST, CLASS_NAME, "addTagSubscriptionJSON", "Adding tag: {0}", theTag);
                }
                getNewsService().addTagSubscription(personId, theTag, locale);
            }
        }
        setJsonObject(getNewsService().getTagSubscriptions(personId, locale));

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "addTagSubscriptionJSON");
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

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
