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

package com.ibm.lconn.homepage.web.controller.home.following;

import static java.util.logging.Level.FINER;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONArtifact;
import com.ibm.json.java.JSONObject;
import com.ibm.lconn.following.internal.NamedResource;
import com.ibm.lconn.homepage.web.controller.AbstractHomepageController;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import com.ibm.lconn.news.activitystreams.service.util.ActivityStreamsIdPrefix;
import com.ibm.lconn.news.ejb.client.NewsStoryEJBBean;
import com.ibm.lconn.news.ejb.client.NewsStoryEJBUtil;
import com.ibm.lconn.news.ejb.exceptions.NewsEJBConsumerException;

@Controller
@RequestMapping(value = "/web")
public class FetchFollowEvidenceController extends AbstractHomepageController {

    private final static String CLASS_NAME = FetchFollowEvidenceController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private String storyId;
    private JSONArtifact json;
    private NewsStoryEJBBean newsStoryBean;

    public JSONArtifact getJsonObject() {
        return json;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public NewsStoryEJBBean getNewsStoryBean() throws NewsEJBConsumerException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "getNewsStoryBean");

        if (newsStoryBean == null) {
            newsStoryBean = NewsStoryEJBUtil.getBean("homepage");
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "getNewsStoryBean", newsStoryBean);

        return newsStoryBean;
    }

    public void setNewsStoryBean(NewsStoryEJBBean newsStoryBean) {
        this.newsStoryBean = newsStoryBean;
    }

    @RequestMapping(value = "/doFetchFollowingData.action", method = RequestMethod.GET)
    public String fetchFollowingData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        init(request);
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "fetchFollowingData");
        }
        setStoryId(request.getParameter("storyId"));
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        setupJavalinAttributes();
        String personId = getPersonExternalId();
        Locale userLocale = getLocale(request);

        if (storyId != null && personId != null && userLocale != null) {
            List<NamedResource> resources = null;
            storyId = ActivityStreamsIdPrefix.removeUrnLsidFromString(storyId);
            try {
                resources = getNewsStoryBean().getStoryFollowableResourcesForPerson(storyId, personId, userLocale.getLanguage(), userLocale.getCountry(), ApplicationContext.getOrganizationId());
            } catch (NewsEJBConsumerException e) {
                if (logger.isLoggable(Level.SEVERE)) {
                    String msg = getWebResourceBundle().getString("error.createEJBHome.exception");
                    logger.logp(Level.SEVERE, CLASS_NAME, "fetchFollowingData", msg, e);
                    out.print(msg);
                    out.flush();
                    out.close();
                    return msg;
                }
            } catch (Exception e) {
                if (logger.isLoggable(Level.SEVERE)) {
                    logger.logp(Level.SEVERE, CLASS_NAME, "fetchFollowingData", "Exception obtaining followable resources", e);
                    out.print(e.getMessage());
                    out.flush();
                    out.close();
                    return e.getMessage();
                }
            }
            if (resources != null) {
                JSONArray entries = new JSONArray();
                for (NamedResource resource : resources) {
                    JSONObject resourceObj = new JSONObject();
                    resourceObj.put("title", resource.getName());
                    resourceObj.put("type", resource.getType().toString().toLowerCase());
                    resourceObj.put("resourceId", resource.getID());
                    entries.add(resourceObj);
                }
                json = entries;
            }
        }

        out.print(getJsonObject());

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "fetchFollowingData", json);
        }

        out.flush();
        out.close();
        return getJsonObject().toString();

    }

}
