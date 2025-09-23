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

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.ibm.lconn.homepage.web.GlobalSessionConstants;

public abstract class AbstractTabInstanceAwareController extends AbstractHomepageController {

    private static final long serialVersionUID = 4035058490718820662L;

    private final static String CLASS_NAME = AbstractTabInstanceAwareController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    /* Set by TabInstanceInterceptorBase */
    private String tabInstanceId;

    public void setTabInstanceId(String tabInstanceId) {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "setTabInstanceId", tabInstanceId);

        this.tabInstanceId = tabInstanceId;

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "setTabInstanceId");
    }

    public void init(HttpServletRequest request) {
        // TODO Auto-generated method stub

        tabInstanceId = (String) request.getSession().getAttribute(GlobalSessionConstants.VIEW_TABINSTANCE_ID);

        super.init(request);
    }

    protected void addPagedAttributesToModel(Model model) {
        model.addAttribute("tabInstanceId", getTabInstanceId());

        super.addPagedAttributesToModel(model);
    }

    public String getTabInstanceId() {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getTabInstanceId");

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getTabInstanceId", tabInstanceId);

        return tabInstanceId;
    }

}
