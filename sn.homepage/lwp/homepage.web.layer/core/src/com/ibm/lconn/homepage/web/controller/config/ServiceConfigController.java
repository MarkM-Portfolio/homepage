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

package com.ibm.lconn.homepage.web.controller.config;

import static java.util.logging.Level.FINER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Feed;
import org.apache.commons.lang3.StringUtils;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.core.web.serviceconfigs.ServiceConfigsApiImpl;
import com.ibm.lconn.homepage.web.controller.AbstractSessionAwareController;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;

@Controller
@RequestMapping(value = { "/" })
public class ServiceConfigController extends AbstractSessionAwareController {

	private static final long serialVersionUID = -3461894269907576387L;

	private final static String CLASS_NAME = ServiceConfigController.class.getName();

	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private Feed feed;
	
	private static final String SERVICE_NAME="homepage";
	
	@RequestMapping(value = { "/serviceconfig" }, method = RequestMethod.GET)
	public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "execute");
		
		ServiceConfigsApiImpl api = new ServiceConfigsApiImpl();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		api.writeFeedServiceConfigs(SERVICE_NAME, pw);
		
		
		Document<Feed> doc = Abdera.getNewParser().parse(generateInputStream(sw.getBuffer().toString()));
		setFeed(doc.getRoot());
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "execute");
		
        response.setContentType("application/atom+xml; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(getFeed());
        out.flush();
        out.close();

        return null;
	}
	
	@RequestMapping(value = { "/config/startview" }, method = { RequestMethod.GET })
	public String getConfiguredStartView(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	    JSONObject jsonObject = getConfiguredStartViewJSONResponse(request);

		response.setStatus(200);
		return writeJSONResponse(jsonObject, response);
	}

	@RequestMapping(value = { "/config/startview" }, method = { RequestMethod.PUT }, consumes = "application/json")
	public String changeConfiguredStartView(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = true) String id) throws Exception {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "changeConfiguredStartView");
        }
	    
        JSONObject jsonObject = new JSONObject();

        if (!getAllowedStartViews().contains(id)) {
			// 400 failed
			jsonObject.put("error", "provided id is not allowed");
			response.setStatus(400);
		} else {
			// set/update cookies ConnectionsHomepageState, ConnectionsHomepageActivityStreamState according to id
			jsonObject = getConfiguredStartViewJSONResponse(request);
			
			String hpCookieVal = "updates";
			String hpASCookieVal = isOrientEnabled() ? "topUpdates/topUpdates/all": "myStream/updates/all";
			
			if (StringUtils.equals(id, "myPage")) {
				hpCookieVal = "widgets";
				hpASCookieVal = "";
				
			} else if (StringUtils.equals(id, "topUpdates")) {
				hpCookieVal = "updates";
				hpASCookieVal = "topUpdates/topUpdates/all";
				
			} else if (StringUtils.equals(id, "latestUpdates")) {
				hpCookieVal = "updates";
				hpASCookieVal = "myStream/updates/all";
				
			} else if (StringUtils.equals(id, "discover")) {
				hpCookieVal = "updates";
				hpASCookieVal = "discover/all";
				
			}      
	        
	        if (logger.isLoggable(FINER)) {
	        	logger.finer("set ConnectionsHomepageState to '" + hpCookieVal + "', ConnectionsHomepageActivityStreamState to '" + hpASCookieVal + "'");
	        }
			
	        jsonObject.put("configuredView", id);
			setHomepageStateCookie(hpCookieVal, request, response);
			setHomepageASStateCookie(hpASCookieVal, request, response);
			response.setStatus(200);
		}        
        
        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "changeConfiguredStartView");
        }

		return writeJSONResponse(jsonObject, response);
	}
	
	protected String writeJSONResponse(JSONObject jsonObject, HttpServletResponse response) throws Exception {
		return writeResponse(jsonObject, "application/json", response);
	}
	
	protected String writeResponse(Object object, String contentType, HttpServletResponse response) throws Exception {
		if (response == null || object == null) {
			return null;
		}
		
	    PrintWriter out = response.getWriter();
        response.setContentType(contentType);
        response.setCharacterEncoding("UTF-8");
        out.print(object);
        out.flush();
        out.close();
        
        return null;
	}
	
	public Feed getFeed() throws Exception {
		return feed;
	}
	
	protected void setFeed(Feed feed) {
		this.feed = feed;
	}
	
	private InputStream generateInputStream(String s) {
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "generateInputStream", s);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "generateInputStream");
		
		return bais;
	}

}
