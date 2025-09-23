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
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.ibm.lconn.homepage.model.Component;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

public abstract class AbstractHomepageController extends AbstractSessionAwareController {

    private static final long serialVersionUID = -3445154516452250947L;

    @Autowired
    private IConfigurationService configurationService;
    @Autowired
    private ResourceBundle webResourceBundle;
    @Autowired
    private IEventUtils eventUtils;

    /* Generic Homepage Action request parameters */
    private Boolean firstTimeLogin;

    private String semanticTagURL;
    private String semanticTagSSLURL;
    private String profileURL;
    private String profileSSLURL;

    private String forceRefresh;

    protected void addPagedAttributesToModel(Model model) {
        super.addPagedAttributesToModel(model);
    }

    public ResourceBundle getWebResourceBundle() {
        return webResourceBundle;
    }

    public void setWebResourceBundle(ResourceBundle webResourceBundle) {
        this.webResourceBundle = webResourceBundle;
    }

    protected IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public String getSemanticTagURL() {
        return semanticTagURL;
    }

    public void setSemanticTagURL(String semanticTagURL) {
        this.semanticTagURL = semanticTagURL;
    }

    public String getSemanticTagSSLURL() {
        return semanticTagSSLURL;
    }

    public void setSemanticTagSSLURL(String semanticTagSSLURL) {
        this.semanticTagSSLURL = semanticTagSSLURL;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getProfileSSLURL() {
        return profileSSLURL;
    }

    public void setProfileSSLURL(String profileSSLURL) {
        this.profileSSLURL = profileSSLURL;
    }

    public Boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    public void setFirstTimeLogin(boolean firstTimeLogin) {
        this.firstTimeLogin = Boolean.valueOf(firstTimeLogin);
    }

    protected void setupJavalinAttributes() throws Exception {
        // TODO: Vincent- do we need to set both SSL and non SSL. What about using getPrincipalProxy().isRequestSecure() here?
        setSemanticTagURL(getConfigurationService().getComponentUrl(Component.PERSON_TAG.getName(), false));
        setSemanticTagSSLURL(getConfigurationService().getComponentUrl(Component.PERSON_TAG.getName(), true));
        setProfileURL(getConfigurationService().getComponentUrl(Component.PROFILES.getName(), false));
        setProfileSSLURL(getConfigurationService().getComponentUrl(Component.PROFILES.getName(), true));
    }

    public void setForceRefresh(String forceRefresh) {
        this.forceRefresh = forceRefresh;
    }

    public String getForceRefresh() {
        return forceRefresh;
    }

    public IEventUtils getEventUtils() {
        return eventUtils;
    }

    public void setEventUtils(IEventUtils eventUtils) {
        this.eventUtils = eventUtils;
    }

}
