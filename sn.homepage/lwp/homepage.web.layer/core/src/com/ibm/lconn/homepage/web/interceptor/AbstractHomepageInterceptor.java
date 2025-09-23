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

package com.ibm.lconn.homepage.web.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.utils.ResourceBundle;

/**
 * Base class from which our custom interceptors derive from
 * 
 * @author vincent
 * 
 */
public abstract class AbstractHomepageInterceptor {

    private final static String CLASS_NAME = AbstractHomepageInterceptor.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);
    private static final long serialVersionUID = 1777155524914035186L;

    @Autowired
    protected ResourceBundle webResourceBundle;

    public ResourceBundle getWebResourceBundle() {
        return webResourceBundle;
    }

    public void setWebResourceBundle(ResourceBundle webResourceBundle) {
        this.webResourceBundle = webResourceBundle;
    }

    public Map<String, String> getSession(HttpServletRequest request) {
        Map<String, String> attributes = new HashMap<String, String>();

        if (request != null && request.getSession() != null) {
            Enumeration<?> e = request.getSession().getAttributeNames();

            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();

                // Get the value of the attribute
                String value = (String) request.getSession().getAttribute(name);

                attributes.put(name, value);
            }
        }

        return attributes;
    }
}
