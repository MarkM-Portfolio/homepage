/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.web.taglib.jsp;

import static java.util.logging.Level.FINER;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ibm.lconn.homepage.services.config.gettingstarted.GettingStartedBeanHelper;
import com.ibm.lconn.homepage.services.helper.DefaultHomepageTabHelper;
import com.ibm.lconn.homepage.services.helper.GettingStartedPreferenceHelper;
import com.ibm.lconn.homepage.utils.UrlDecoder;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;

public class HomepagePageState extends TagSupport {

	private static final long serialVersionUID = -2063590570573788511L;
	private static String CLASS_NAME = HomepagePageState.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	// Mappings of page names to actual URLs
	private static Map<String, String> pageUrls;
	
	static{
		pageUrls = new HashMap<String, String>();
		
		pageUrls.put("gettingStarted", "/web/gettingStarted/");
		pageUrls.put("updates", "/web/updates/");
		pageUrls.put("widgets", "/web/widgets/");
	}
	
	public HomepagePageState(){		
	}

	@Override
	public int doStartTag() throws JspException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "doStartTag");
		ApplicationContext.setOrganizationId();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		String homepageState = getCookieValue(request.getCookies(), "ConnectionsHomepageState");
		
		// And it isn't null or empty
		if(homepageState != null && homepageState.length() > 0){
			// Set the page URL based off of it
			setPageUrl(getPageUrl(transformPage(request, homepageState)));
			return SKIP_BODY;
		}

        if(DefaultHomepageTabHelper.isGettingStartedBypassed()){
            if(DefaultHomepageTabHelper.isWidgetsDefault()){
                setPageUrl(getPageUrl("widgets"));
            } else {
                setPageUrl(getPageUrl("updates"));
            }
        }else if(isGettingStartedDisplayEnabled(request) && !isGettingStartedUrlMode() ){
			// If the getting started page is enabled by the user
			setPageUrl(getPageUrl("gettingStarted"));
		}else if(DefaultHomepageTabHelper.isWidgetsDefault()){
			// Else if the widgets page is default
			setPageUrl(getPageUrl("widgets"));
		}else{
			// Otherwise, default to the updates page
			setPageUrl(getPageUrl("updates"));
		}
        ApplicationContext.unsetOrganizationId();
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}
	
	private String getPageUrl(String page){
		// Get the available page URL
		String pageUrl = (String) pageUrls.get(page);
		
		// If it doesn't exist
		if(pageUrl == null){
			// Default to the "updates" page
			pageUrl = (String) pageUrls.get("updates");
		}
		
		return pageUrl;
	}
	
	private void setPageUrl(String pageUrl){
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		if(pageUrl.equals((String) pageUrls.get("updates"))){
			String asState = getCookieValue(request.getCookies(), "ConnectionsHomepageActivityStreamState");
			
			if(asState != null && asState.length() > 0){
				pageUrl += "#" + asState;
			} else {
				pageUrl += "#imFollowing/all";
			}
		}
		
		// Set the request attribute for this page URL
		request.setAttribute("ConnectionsHomepageState", pageUrl);
	}
	
	/**
	 * Logic for transforming the page result under certain circumstances.
	 * @param request
	 * @param page
	 * @return
	 */
	private String transformPage(HttpServletRequest request, String page){
		// If we're going to getting started but the checkbox is checked
		if(page.equals("gettingStarted") && (DefaultHomepageTabHelper.isGettingStartedBypassed() || !isGettingStartedDisplayEnabled(request))){
			// Ignore and redirect to the updates page instead
			return "updates";
		}
		
		return page;
	}
	
	/**
	 * Is the getting started page enabled (i.e. checkbox not checked).
	 * @param request
	 * @return true if it is, false otherwise
	 */
	private boolean isGettingStartedDisplayEnabled(HttpServletRequest request){
		return GettingStartedPreferenceHelper.isGettingStartedDisplayEnabled(
				(String)request.getSession().getAttribute("user.info.internal.id"),
				request.getRemoteUser());
	}
	
	private boolean isGettingStartedUrlMode() {
		return GettingStartedBeanHelper.isUrlMode();
	}
	
	private String getCookieValue(Cookie[] cookies, String cookieName){
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				
				// Compare it to the cookie name passed
				if(cookie.getName().equals(cookieName)){
					// If found, return the value
					return UrlDecoder.decode(cookie.getValue());
				}
			}
		}
		
		return null;
	}
}