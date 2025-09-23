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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.servlet.ProxyException;

public abstract class AbstractWebController {

    private static final long serialVersionUID = -8641910999454926803L;
    private final static String CLASS_NAME = AbstractWebController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private Map<String, Object> sessionAttributes;

    public void init(HttpServletRequest request) {
        // TODO Auto-generated method stub

        sessionAttributes = new HashMap<String, Object>();

        sessionAttributes.put(GlobalSessionConstants.USER_INFO_ORGID, request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_ORGID));
        sessionAttributes.put(GlobalSessionConstants.USER_INFO_EXTERNAL_ID, request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_EXTERNAL_ID));
        sessionAttributes.put(GlobalSessionConstants.USER_INFO_NAME, request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_NAME));
        sessionAttributes.put(GlobalSessionConstants.USER_INFO_INTERNAL_ID, request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID));
        sessionAttributes.put(GlobalSessionConstants.USER_BOARD_ENABLED, request.getSession().getAttribute(GlobalSessionConstants.USER_BOARD_ENABLED));
        sessionAttributes.put(GlobalSessionConstants.USER_INFO_LANG, request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_LANG));
        sessionAttributes.put(GlobalSessionConstants.USER_INFO_TIMESTAMP, request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_TIMESTAMP));
        sessionAttributes.put(GlobalSessionConstants.USER_IS_EXTERNAL, request.getSession().getAttribute(GlobalSessionConstants.USER_IS_EXTERNAL));
        sessionAttributes.put(GlobalSessionConstants.USER_INFO_EMAIL, request.getSession().getAttribute(GlobalSessionConstants.USER_INFO_EMAIL));

        sessionAttributes.put(GlobalSessionConstants.VIEW_ISWIDGETTABENABLED, request.getSession().getAttribute(GlobalSessionConstants.VIEW_ISWIDGETTABENABLED));

    }

    public Map<String, Object> getSession() {
        return sessionAttributes;
    }

    protected void addPagedAttributesToModel(Model model) {
        model.addAttribute("session", getSession());
    }
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
    	  if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
              throw e;
          String view = ViewConstants.VIEW_ERROR;
          
          int responseCode = 400;
        
          if (e instanceof WebException) {
        	      responseCode = 500;
                 
          } else if (e instanceof ProxyException) {
              responseCode = 407;
            

          } else if (e instanceof ServiceException) {
              responseCode = 500;

          } else if (e instanceof RuntimeException) {
              responseCode = 500;
          } 
          response.setStatus(responseCode);

          // Otherwise setup and send the user to a default error-view.
          ModelAndView mav = new ModelAndView();
          mav.addObject("messageStatusCode", responseCode);
          mav.addObject("exception", e);
          mav.setViewName(view);
          return mav;
    }

}
