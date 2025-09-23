/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.controller.activitystream;

import static java.util.logging.Level.FINER;

import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.homepage.services.activitystream.config.IConfigService;
import com.ibm.lconn.homepage.web.controller.AbstractSessionAwareController;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.UserInfo;

/**
 * Spring controller that returns the Activity Stream configuration object in JSON format.
 * 
 * @author Piyush Jain
 */
@Controller
@RequestMapping(value = "/web")
public class ActivityStreamConfigController extends AbstractSessionAwareController {

    private static final long serialVersionUID = 3587065299316204802L;
    private final static String CLASS_NAME = ActivityStreamConfigController.class.getName();

    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private IConfigService activityStreamConfigService;

    private JSONObject jsonObject;

    @RequestMapping(value = "/getActivityStreamConfig", method = RequestMethod.GET)
    public String getActivityStreamConfigJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
        init(request);
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getActivityStreamConfigJSON");
        }

        UserInfo user = getUserInfo();
        // Get the config object from the service and set it to the local jsonObject
        setJsonObject(getActivityStreamConfigService().getConfigObject(getLocale(request), user));

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "getActivityStreamConfigJSON");
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(getJsonObject());
        out.flush();
        out.close();

        return null;

    }

    public IConfigService getActivityStreamConfigService() {
        return activityStreamConfigService;
    }

    public void setActivityStreamConfigService(IConfigService activityStreamConfigService) {
        this.activityStreamConfigService = activityStreamConfigService;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

}
