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
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.lconn.following.ejb.client.FollowingEJBHelper;
import com.ibm.lconn.following.internal.FollowingException;
import com.ibm.lconn.following.internal.Resource;
import com.ibm.lconn.following.internal.Resource.ResourceType;
import com.ibm.lconn.following.internal.Resource.Source;
import com.ibm.lconn.following.internal.impl.ResourceImpl;
import com.ibm.lconn.homepage.web.controller.AbstractHomepageController;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;

@Controller
@RequestMapping(value = "/web")
public class UnfollowResourcesController extends AbstractHomepageController {

    private final static String CLASS_NAME = UnfollowResourcesController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);
    private static Map<ResourceType, Source> sourceMap = new TreeMap<ResourceType, Source>();

    static {
        sourceMap.put(ResourceType.ACTIVITY, Source.ACTIVITIES);
        sourceMap.put(ResourceType.BLOG, Source.BLOGS);
        sourceMap.put(ResourceType.COMMUNITY, Source.COMMUNITIES);
        sourceMap.put(ResourceType.FILE, Source.FILES);
        sourceMap.put(ResourceType.FILE_FOLDER, Source.FILES);
        sourceMap.put(ResourceType.FORUM, Source.FORUMS);
        sourceMap.put(ResourceType.FORUM_TOPIC, Source.FORUMS);
        sourceMap.put(ResourceType.PROFILE, Source.PROFILES);
        sourceMap.put(ResourceType.WIKI, Source.WIKIS);
        sourceMap.put(ResourceType.WIKI_PAGE, Source.WIKIS);
        sourceMap.put(ResourceType.TAG, Source.TAGS);
    }

    private JSONArray unfollowJson;

    private JSONObject jsonResult = new JSONObject();

    public JSONObject getJsonObject() {
        return jsonResult;
    }

    public void setServletRequest(HttpServletRequest request) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "setServletRequest");
        }

        try {
            unfollowJson = JSONArray.parse(request.getInputStream());
        } catch (Exception e) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.logp(Level.SEVERE, CLASS_NAME, "execute", "Exception parsine JSON resources array", e);
            }
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "setServletRequest", unfollowJson);
        }

    }

    @RequestMapping(value = "/doUnfollowResources.action", method = RequestMethod.POST)
    public String unfollowResources(HttpServletRequest request, HttpServletResponse response) throws Exception {
        init(request);
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "executeInternal");
        }
        setServletRequest(request);
        setupJavalinAttributes();
        String personId = getPersonExternalId();

        if (unfollowJson != null) {

            int processedItems = 0;

            for (Object elem : unfollowJson) {
                if (elem instanceof JSONObject) {
                    JSONObject jsonResource = (JSONObject) elem;
                    Resource unfollowResource = getResource(jsonResource);
                    if (unfollowResource != null) {
                        try {
                            FollowingEJBHelper.getInstance("homepage").unfollow(personId, unfollowResource);
                            processedItems++;
                        } catch (FollowingException e) {
                            if (logger.isLoggable(Level.SEVERE)) {
                                logger.logp(Level.SEVERE, CLASS_NAME, "execute", "Exception during resource unfollow", e);
                            }
                        }
                    }
                }
            }
            if (unfollowJson.size() == processedItems) {
                setSuccessJsonResult();
            } else {
                setFailJsonResult();
            }
        } else {
            setFailJsonResult();
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonResult);
        out.flush();
        out.close();

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "executeInternal", jsonResult);
        }
        return jsonResult.toString();
    }

    private void setSuccessJsonResult() {
        jsonResult.put("result", "success");
    }

    private void setFailJsonResult() {
        jsonResult.put("result", "fail");
    }

    private Resource getResource(JSONObject jsonResource) {
        Resource result = null;
        try {
            String containerId = (String) jsonResource.get("resourceId");
            ResourceType type = readEnumValue(ResourceType.class, (String) jsonResource.get("type"));
            if (containerId != null && type != null) {
                result = new ResourceImpl(getResourceSource(type), type, containerId);
                result.setOrganizationId(ApplicationContext.getOrganizationId());
            }
        } catch (Exception e) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.logp(Level.SEVERE, CLASS_NAME, "execute", "Exception fetching resource data from JSON", e);
            }
        }
        return result;
    }

    private static Source getResourceSource(ResourceType type) {
        return sourceMap.get(type);
    }

    private <T extends Enum<T>> T readEnumValue(Class<T> enumType, String enumValue) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "readEnumValue", new Object[] { enumType, enumValue });
        }
        T result = null;

        if (enumValue != null) {
            enumValue = (enumValue.trim().length() == 0) ? null : enumValue.toUpperCase();
        }
        if (enumValue != null) {
            try {
                result = Enum.valueOf(enumType, enumValue);
            } catch (Exception e) {
                logger.finer("Error when creating enum for " + enumValue);
            }
        }
        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "readEnumValue", new Object[] { result });
        }
        return result;
    }

}
