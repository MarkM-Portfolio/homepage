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

package com.ibm.lconn.homepage.web.utils.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.HttpsURL;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetCategory;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.WebException;
import com.ibm.lconn.homepage.web.utils.IUrlNormalizer;

public class UrlNormalizerImpl implements IUrlNormalizer, ApplicationContextAware {

    private static String CLASS_NAME = UrlNormalizerImpl.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private final static String CONTEXT_ROOT_TAG = "${HOMEPAGE_CONTEXT_ROOT}";
    private final static String COMMON_ROOT_TAG = "${COMMON_CONTEXT_ROOT}";
    private final static String WEB_RESOURCES_TAG = "{webresources}";

    private final static String WIDGET_INFO_SERVLET = "/web/getWidgetDescriptor.action";

    @Autowired
    @Qualifier("webResourceBundle")
    private ResourceBundle serviceResourceBundle;
    @Autowired
    private IConfigurationService configurationService;
    private String hpContextRoot;
    private String hpContextRootSsl;

    private String commonContextRoot;
    private String commonContextRootSsl;

    @Autowired
    private WebApplicationContext applicationContext;

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setServiceResourceBundle(ResourceBundle serviceResourceBundle) {
        this.serviceResourceBundle = serviceResourceBundle;
    }

    public ResourceBundle getServiceResourceBundle() {
        return serviceResourceBundle;
    }

    public void normalizeWidget(Widget widget) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "normalizeWidget", new Object[] { widget });

        boolean isFilepath = false;

        URI uri = normalizeWidgetUrl(widget.getUrl());
        String secureUrl = widget.getSecureUrl();
        URI secureUri = null;
        if (secureUrl != null && !secureUrl.equals("")) {
            secureUri = normalizeWidgetUrl(widget.getSecureUrl(), true);
        } else {
            if (logger.isLoggable(Level.FINEST)) {
                logger.logp(Level.FINEST, CLASS_NAME, "normalizeWidget", "Secure URL not set. Setting to non-secure URL");
            }
            secureUri = uri;
        }

        if (!(uri instanceof HttpURL))
            isFilepath = true;

        try {
            widget.setUrl(uri.getURI());
            widget.setSecureUrl(secureUri.getURI());
        } catch (URIException e) {
            throw new WebException(e);
        }
        widget.setFileSytemUrl(isFilepath);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "normalizeWidget");
    }

    /**
     * Method to normalize the WidgetURL. Invokes the method that will return an unsecure URL.
     * 
     * @param dbWidgetUrl
     *            URL of the widget.
     * @return URI for the widget.
     * @throws WebException
     */
    protected URI normalizeWidgetUrl(String dbWidgetUrl) throws WebException {
        return this.normalizeWidgetUrl(dbWidgetUrl, false);
    }

    /**
     * Method to normalize the Widget URL. If placeholders are found they are replaced with the application root they correspond to.
     * 
     * @param dbWidgetUrl
     *            Widget URL - could contain placeholders
     * @param secure
     *            True if the secure URL is required.
     * @return URI of the widget.
     * @throws WebException
     */
    protected URI normalizeWidgetUrl(String dbWidgetUrl, boolean secure) throws WebException {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "normalizeWidgetUrl", new Object[] { dbWidgetUrl, secure });

        URI widgetUrl = null;
        ServletContext context = applicationContext.getServletContext();

        if (dbWidgetUrl.startsWith("http://")) {
            try {
                widgetUrl = new HttpURL(dbWidgetUrl);
            } catch (URIException e) {
                throw new WebException(e);
            }
        } else if (dbWidgetUrl.startsWith("https://")) {
            try {
                widgetUrl = new HttpsURL(dbWidgetUrl);
            } catch (URIException e) {
                throw new WebException(e);
            }
        } else if (dbWidgetUrl.contains("://")) {

            String msg = getServiceResourceBundle().getString("error.widgetInfo.update.notSupportedProtocol", dbWidgetUrl);
            WebException t = new WebException(msg);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "normalizeWidgetUrl", msg, t);
            }
            throw t;
        } else {
            // relative URL
            try {
                if (dbWidgetUrl.startsWith("/")) {
                    // outside the homepage project
                    widgetUrl = new HttpURL(new HttpURL(hpContextRoot), new HttpURL(dbWidgetUrl));
                } else {
                    // relative to the Homepage project ==> keep it relative
                    // widgetUrl = new HttpURL(dbUrl);
                    String replacedUrl = this.replacePathPlaceHolders(dbWidgetUrl, secure, false);

                    // Only add in the real path if we haven't replace a placeholder for the path.
                    if (replacedUrl.compareTo(dbWidgetUrl) == 0) {
                        widgetUrl = new URI(dbWidgetUrl);
                    } else {
                        // Use the appropriate routine to get the URL. Returning an HttpURL causes the
                        // service that obtains the cache to treat this as a URL rather than a file.
                        if (replacedUrl.startsWith("http://")) {
                            widgetUrl = new HttpURL(replacedUrl);
                        } else {
                            widgetUrl = new HttpsURL(replacedUrl);
                        }
                    }
                }
            } catch (URIException e) {
                throw new WebException(e);
            }
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "normalizeWidgetUrl", widgetUrl);

        return widgetUrl;
    }

    /**
     * Determine runtime (request) transformation needed to the widget urls (icon url and cached descriptors)
     * 
     * 
     * @param widget
     * @return
     * @throws WebException
     */
    public void translateUrls(Widget widget, boolean isSecure) throws WebException {

        if (widget.isHomepageSpecific()) {
            widget.setUrl(replacePathPlaceHolders(widget.getUrl(), isSecure, false));
        } /*
           * else { // standard iWidget descriptor: fetch it directly widget.setUrl(getUrl(widget.getSecureUrl(), widget.getUrl(), isSecure)); }
           */

        String iconUrl = getUrl(widget.getSecureIconUrl(), widget.getIconUrl(), isSecure);

        // Replace context root tag for icons
        if (iconUrl != null) {
            widget.setIconUrl(replacePathPlaceHolders(iconUrl, isSecure, false));
        } else {
            widget.setIconUrl(null);
        }
    }

    /**
     * Replace path holders in the URL. The method caters for URLs that will be accessed using HTTP(s) and also for URLs accessed from the file system.
     * 
     * @param url
     *            URL to look for placeholders in.
     * @param isSecure
     *            True if secure url is being used.
     * @param inFileSystem
     *            True if the url will be used to look in the filesystem.
     * @return URL with any placeholders replaced.
     */
    private String replacePathPlaceHolders(String url, boolean isSecure, boolean inFileSystem) throws WebException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "replacePathPlaceHolders", new Object[] { url, isSecure, inFileSystem });
        }

        try {
            if (url.contains(CONTEXT_ROOT_TAG)) {
                if (inFileSystem) {
                    url = url.replace(CONTEXT_ROOT_TAG, this.getUrlPath(getHomepageContextRoot(isSecure)));
                } else {
                    url = url.replace(CONTEXT_ROOT_TAG, getHomepageContextRoot(isSecure));
                }
            } else if (url.contains(COMMON_ROOT_TAG)) {
                if (inFileSystem) {
                    url = url.replace(COMMON_ROOT_TAG, this.getUrlPath(getCommonContextRoot(isSecure)));
                } else {
                    url = url.replace(COMMON_ROOT_TAG, getCommonContextRoot(isSecure));
                }
            } else if (url.contains(WEB_RESOURCES_TAG)) {
                if (inFileSystem) {
                    url = url.replace(WEB_RESOURCES_TAG, this.getUrlPath(getCommonContextRoot(isSecure)));
                } else {
                    url = url.replace(WEB_RESOURCES_TAG, getCommonContextRoot(isSecure));
                }
            }
        } catch (URIException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "replacePathPlaceHolders", ex.getMessage());
        }
        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "replacePathPlaceHolders", url);
        }

        return url;
    }

    /**
     * Gets the path of a URL
     * 
     * @param url
     *            The url to get the path from
     * @return Path of the URL
     */
    private String getUrlPath(String url) throws URIException {
        return new URI(url).getPath();
    }

    /**
     * @see translateUrls
     * @param widgets
     * @throws WebException
     */
    public void translateUrls(List<Widget> widgets, boolean isSecure) throws WebException {
        for (Widget widget : widgets) {
            translateUrls(widget, isSecure);
        }
    }

    /**
     * @see translateUrls
     * @param widgets
     * @throws WebException
     */
    public void translateUrls(Map<String, WidgetCategory> widgets, boolean isSecure) throws WebException {
        for (WidgetCategory widgetCategory : widgets.values()) {
            translateUrls(widgetCategory.getWidgets(), isSecure);
        }
    }

    /**
     * Return the secureUrl or url to use in the UI depending on whether the page was accessed over HTTPs Used for XML descriptors and icon urls
     */
    private String getUrl(String secureUrl, String url, boolean isSecure) {
        String resultUrl;

        if (isSecure) {
            if (secureUrl != null && secureUrl.trim().length() > 0) {
                resultUrl = secureUrl;
            } else {
                resultUrl = url;
            }
        } else {
            resultUrl = url;
        }

        return resultUrl;
    }

    private String getHomepageContextRoot(boolean isSecure) throws WebException {
        String url = null;
        try {
            url = isSecure ? getConfigurationService().getComponentUrl("homepage", true) : getConfigurationService().getComponentUrl("homepage", false);
        } catch (ServiceException se) {
            throw new WebException(se);
        }
        return url;
    }

    private String getCommonContextRoot(boolean isSecure) throws WebException {
        String url = null;
        try {
            url = isSecure ? getConfigurationService().getComponentUrl("webresources", true) : getConfigurationService().getComponentUrl("webresources", false);
        } catch (ServiceException se) {
            throw new WebException(se);
        }
        return url;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = (WebApplicationContext) context;
    }
}
