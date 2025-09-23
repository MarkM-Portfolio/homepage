/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.interceptor;

import static java.util.logging.Level.FINER;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Basic interceptor adding {@link FBAResponseHeaderSetter} as pre-result listener
 * 
 * @author vincent
 * 
 */
public class FBAResponseHeaderInterceptor extends HandlerInterceptorAdapter {

    private static final long serialVersionUID = 2494353180933231719L;

    private final static String CLASS_NAME = FBAResponseHeaderInterceptor.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    // Important: header name must match
    // lconn.homepage.core.auth.HTML_RESPONSE_HEADER in
    // formBaseUtility.js
    private static final String HTTP_RESPONSE_HEADER = "X-FBA-CHECK-SKIP";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) throws Exception {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setFBAResponseHeader", response);
        }

        if (!response.isCommitted()) {
            // FBA: we set a custom header here and check for the presence of
            // this header client-side
            response.addHeader(HTTP_RESPONSE_HEADER, "true");
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setFBAResponseHeader");
        }

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {
    }

}
