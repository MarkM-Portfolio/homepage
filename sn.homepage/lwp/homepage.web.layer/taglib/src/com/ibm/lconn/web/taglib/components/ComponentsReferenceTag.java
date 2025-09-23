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

package com.ibm.lconn.web.taglib.components;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ibm.json.java.JSONObject;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;

public class ComponentsReferenceTag extends TagSupport {

	private static final long serialVersionUID = 1055222551284521294L;
	private final static String CLASS_NAME = ComponentsReferenceTag.class
			.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private String jsVarName;
	private String jsonCache;

	public String getJsVarName() {
		return jsVarName;
	}

	public void setJsVarName(String jsVarName) {
		this.jsVarName = jsVarName;
	}

	@Override
	public int doStartTag() throws JspException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "doStartTag");

		JspWriter out = pageContext.getOut();

		try {
			out.write(generateJsCode());
		} catch (IOException e) {
			// post string freeze, just re-throw the exception without logging.
			throw new JspException(e);
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "doStartTag");

		return SKIP_BODY;
	}

	/**
	 * Obtain a JSON representation of all the components configured in
	 * LotusConnections-Config.xml This method handles reverse-proxy
	 * configuration (via VenturaConfigurationHelper)
	 * 
	 * @return
	 */
	private String getComponentsConfigAsJson() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getComponentsConfigAsJson");

		if (jsonCache == null) {
			VenturaConfigurationHelper vch = VenturaConfigurationHelper.Factory
					.getInstance();
			JSONObject componentsJson = new JSONObject();

			Collection<String> componentNames = vch.getInstalledComponents();

			for (String componentName : componentNames) {
				ComponentEntry component = vch
						.getComponentConfig(componentName);
				if (component != null) {
					JSONObject componentJson = new JSONObject();

					componentJson.put("enabled", component.isUrlEnabled());
					componentJson.put("ssl_enabled", component.isUrlEnabled());

					if ((component.isUrlEnabled()) && (component.getServiceUrl() != null)) {
						componentJson.put("url", component.getServiceUrl().toExternalForm());
					}

					if ((component.isSecureUrlEnabled()) && (component.getSecureServiceUrl() != null)) {
						componentJson.put("secureUrl", component
								.getSecureServiceUrl().toExternalForm());
					}

					componentsJson.put(component.getName(), componentJson);
				}
			}

			jsonCache = componentsJson.toString();
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getComponentsConfigAsJson", jsonCache);

		return jsonCache;
	}
	
	private String generateJsCode(){
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getComponentsConfigAsJson");
		
		StringBuffer jsCode = new StringBuffer();
		String result;
		
		// append jsVarName if any passed
		if ((jsVarName != null) && !("".equals(jsVarName))){
			jsCode.append(jsVarName);
			jsCode.append("=");
		}
		
		jsCode.append(getComponentsConfigAsJson());		
		
		result = jsCode.toString();
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getComponentsConfigAsJson", result);
		
		return result;
	}
}
