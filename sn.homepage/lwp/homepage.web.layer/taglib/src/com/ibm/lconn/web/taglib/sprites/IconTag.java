/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.web.taglib.sprites;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.commons.lang.StringEscapeUtils;

import com.ibm.lconn.core.url.LCUrlUtil;
import com.ibm.lconn.core.versionstamp.VersionStamp;
import com.ibm.ventura.internal.config.api.VenturaConfigurationProvider;
import com.ibm.ventura.internal.config.exception.VenturaConfigException;

/**
 * Map a source to an icon in the river of news, based on
 * riverOfNewsIcons.properties
 * 
 * Generalized for getting started page in 3.0
 * 
 * @author vincent
 * 
 */
public class IconTag extends AbstractHomepageTag {

	private static final String BLANK_PATH = "/nav/common/styles/images/blank.gif";
	private static final String DEFAULT_RESOURCE_BUNDLE_PREFIX = "jsp.river.alt.";

	private static final long serialVersionUID = -1099540960797520792L;

	private final static String CLASS_NAME = IconTag.class.getName();

	private static String DEFAULT_ALT = "jsp.river.alt.thirdParty";

	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private String source;
	private String resourceBundlePrefix;

	// we might want something more fine-grained to pick the icon than only the
	// source,
	// hence the eventName
	private String eventName;

	private String customMapping;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getCustomMapping() {
		return customMapping;
	}

	public void setCustomMapping(String customMapping) {
		this.customMapping = customMapping;
	}

	public String getResourceBundlePrefix() {
		
		String resourceBundlePrefix;
		
		if (this.resourceBundlePrefix != null) {
			resourceBundlePrefix = this.resourceBundlePrefix;
		} else {
			resourceBundlePrefix = DEFAULT_RESOURCE_BUNDLE_PREFIX;
		}
		
		return resourceBundlePrefix;
	}

	public void setResourceBundlePrefix(String resourceBundlePrefix) {
		this.resourceBundlePrefix = resourceBundlePrefix;
	}

	/**
	 * Get url from mapping and set the parent Sprite tag lib for handling
	 */
	@Override
	public int doStartTag() throws JspException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "doStartTag");

		String title = getLocalizedAlt();
		try {
			// ahh I miss JSF templates

			// sprite image
			// need to find a proper way to output this tag, but that's do the
			// job for now.

			StringWriter tagWritter = new StringWriter();

			tagWritter.write("<img class=\"");
			StringEscapeUtils.escapeHtml(tagWritter, getClassForSource());
			tagWritter.write("\" title=\"");
			StringEscapeUtils.escapeHtml(tagWritter, title);
			tagWritter.write("\" alt=\"");
			StringEscapeUtils.escapeHtml(tagWritter, title);
			tagWritter.write("\" src=\"" + getBlankGifUrl());
			tagWritter.write("\"></img>");

			StringBuffer buffer = tagWritter.getBuffer();

			// span needed for high contrast mode
			buffer.append("<span class='lotusAltText'>");
			buffer.append(getLocalizedHighContrastLabel(getSource(),
					pageContext.getRequest().getLocale()));
			buffer.append("</span>");

			pageContext.getOut().print(buffer.toString());
		} catch (IOException e) {
			throw new JspTagException(CLASS_NAME + ": " + e.getMessage());
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "doStartTag");

		return SKIP_BODY;
	}

	private String getLocalizedHighContrastLabel(String source, Locale locale) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getLocalizedHighContrastLabel",
					new Object[] { source, locale });

		String label = "";

		if (source != null) {
			// hacky stuff post string: we reuse the strings for the filter
			// label to get the component names
			String key = "jsp.river.filter." + source;
			List<String> keys = Collections.list(getJSPResourceBundle().getKeys());

			if (keys.contains(key)) {
				label = getJSPResourceBundle().getString(key, locale);
			}
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getLocalizedHighContrastLabel", label);

		return label;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

	private String getBlankGifUrl() {
		// Use the static URI to access the image. This will automatically
		// extend the cache expiration date for this image.
		String blankGifUrl = "";
		
		try {
			VenturaConfigurationProvider provider = VenturaConfigurationProvider.Factory.getInstance();
	        URL url = (pageContext.getRequest().isSecure()) ? provider.getSecureServiceURL("webresources") :
	        	provider.getServiceURL("webresources");
	        blankGifUrl = url.toString() + "/web/com.ibm.lconn.core.styles.oneui3/images/blank.gif?etag=" + VersionStamp.INSTANCE.getVersionStamp();
		} catch(VenturaConfigException e) {
			blankGifUrl = ((HttpServletRequest) pageContext.getRequest()).getContextPath()
							+ "/static/" + LCUrlUtil.toURLEncoded(VersionStamp.INSTANCE.getVersionStamp()) + BLANK_PATH;
		} 

		return blankGifUrl;
	}
	

	/**
	 * Obtain image class based on the property file
	 * 
	 * @return
	 */
	protected String getClassForSource() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getImageUrl");

		String className = "";

		if ((customMapping != null)
				&& SpriteIconListener.ICONS_MAPPING.containsKey(customMapping)) {
			// first look for the customMapping
			className = SpriteIconListener.ICONS_MAPPING
					.getProperty(customMapping);
		}

		if (!isClassFound(className) && eventName != null) {
			// then look for the eventName in the property file
			// making the assumption that there is no eventName equals to source
			// (not the case in 2.5)

			// we search for subsets of event names
			String potentialKey = getMatchKeyForEvent(eventName);

			if (potentialKey != null && !("".equals(potentialKey))) {
				className = SpriteIconListener.ICONS_MAPPING
						.getProperty(eventName);
			} else {
				className = "";
			}
		}

		if (!(isClassFound(className)) && (source != null)
				&& (SpriteIconListener.ICONS_MAPPING.containsKey(source))) {
			// not mapping for the eventName or customeMapping, fallback on the
			// source only
			className = SpriteIconListener.ICONS_MAPPING.getProperty(source);
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getClassForSource");

		return className;
	}

	protected boolean isClassFound(String className) {
		boolean classFound = (className != null && !"".equals(className));
		return classFound;
	}

	protected String getMatchKeyForEvent(String eventName) {
		String potentialKey = eventName;

		while (potentialKey != null
				&& !SpriteIconListener.ICONS_MAPPING.containsKey(potentialKey)) {

			try {
				potentialKey = potentialKey.substring(0, potentialKey
						.lastIndexOf("."));
			} catch (IndexOutOfBoundsException ex) {
				break;
			}
		}
		return potentialKey;
	}

	/**
	 * Obtain localized alt string for the icon depending on the source
	 * 
	 * @param source
	 * @return
	 */
	protected String getLocalizedAlt() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getLocalizedAlt");

		String localizedStr = null;
		// source is required, not risk of NPE

		String source = getSource();

		if (source != null) {
			String resourceKey = getResourceBundlePrefix() + source;

			try {
				localizedStr = getJSPResourceBundle().getString(resourceKey,
						pageContext.getRequest().getLocale());
			} catch (MissingResourceException mre) {
				// Not a server error, we fallback gracefully
				if (logger.isLoggable(Level.FINEST)) {
					logger.logp(Level.FINEST, CLASS_NAME, "getLocalizedAlt",
							"No icon alt text for resource key {0}",
							resourceKey);
				}
			}
		}

		// string not found. Fallback gracefully
		if (localizedStr == null || "".equals(localizedStr)) {
			getJSPResourceBundle().getString(DEFAULT_ALT,
					pageContext.getRequest().getLocale());
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getLocalizedAlt", localizedStr);

		return localizedStr;
	}
}
