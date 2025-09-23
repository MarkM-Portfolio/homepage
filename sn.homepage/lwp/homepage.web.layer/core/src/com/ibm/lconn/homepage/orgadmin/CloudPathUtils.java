/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2016                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.orgadmin;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.lconn.core.web.mt.TenantLookupFilter;
import com.ibm.lconn.core.web.util.CookieHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;

public class CloudPathUtils {
	
	private static String SHARED_SETTINGS_CLASS = "com.ibm.config.shared.SharedSettings";
	private static String METHOD_GET_INSTANCE = "getInstance";
	private static String METHOD_GET_COMPONENT_VERSION = "getComponentVersion";
	private static String METHOD_GET_SHARED_SERVICE_URL = "getSharedServiceURL";
	private static String METHOD_GET_NAVBAR_URL = "getNavBarURL";
	private static String METHOD_GET_SERVER = "getServer";
	private static String METHOD_GET_FOOTER_URL = "getFooterURL";
	
	private static String SERVICE_ADMINNAV = "adminnav-bssui";
	
	private static String CONNECTIONS_ADMIN_NAME = "connections_admin"; // TODO : Update when we have a real URL
	private static String OFFERING_ADMIN_NAME = "admin";
	
	private static String COMPONENT_NAME_THEMING = "theming";
	private static String COMPONENT_NAME_BSSUI = "bssui";
	
	private static String S2S_HEADER = "S2SToken";
	
	private static boolean loaded = false;
	private static String adminNavbarURL = null;
	private static String adminLeftNavURL = null;
	private static String bssServerURL = null;
	private static String footerURL = null;
	private static String commonCSSURL = null;
	
	private static void load() {
		
		// only load once
		if (loaded)
			return;
		loaded = true;
		
		// class is only found on cloud
		ClassLoader classLoader = CloudPathUtils.class.getClassLoader();
		Class<?> sharedSettingClass = null;
		try {
			sharedSettingClass = classLoader.loadClass(SHARED_SETTINGS_CLASS);
		} catch (Exception e) {;} // this is valid if running on prem
		if (sharedSettingClass == null)
			return;
		
		// from here on out anything is an error
		String themingVersion = null;
		String bssuiVersion = null;
		try
		{
			// get the instance
			Method instanceMethod = sharedSettingClass.getMethod(METHOD_GET_INSTANCE);
			Object instanceObject = instanceMethod.invoke(null);
			
			Method serviceURLMethod = sharedSettingClass.getMethod(METHOD_GET_SHARED_SERVICE_URL, String.class);
			adminLeftNavURL = (String)serviceURLMethod.invoke(instanceObject, SERVICE_ADMINNAV);
			adminLeftNavURL += '/' + CONNECTIONS_ADMIN_NAME;

			Method versionMethod = sharedSettingClass.getMethod(METHOD_GET_COMPONENT_VERSION, String.class);
			themingVersion = (String)versionMethod.invoke(instanceObject, COMPONENT_NAME_THEMING);
			bssuiVersion = (String)versionMethod.invoke(instanceObject, COMPONENT_NAME_BSSUI);
			
			Method method = sharedSettingClass.getMethod(METHOD_GET_NAVBAR_URL, String.class);
			adminNavbarURL = (String)method.invoke(instanceObject, OFFERING_ADMIN_NAME);
			
			method = sharedSettingClass.getMethod(METHOD_GET_SERVER, String.class);
			bssServerURL = (String)method.invoke(instanceObject, "bssui");
			
			method = sharedSettingClass.getMethod(METHOD_GET_FOOTER_URL, String.class);
			footerURL = (String)method.invoke(instanceObject, OFFERING_ADMIN_NAME);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		commonCSSURL = bssServerURL+"/theming/css/"+themingVersion+"/smartcloud/defaultTheme/common.css";

	}

	public static String getAdminNavbarURL() {
		load();
		return adminNavbarURL;
	}

	public static String getBssServerURL() {
		load();
		return bssServerURL;
	}

	public static String getFooterURL() {
		load();
		return footerURL;
	}

	public static String getCommonCSSURL() {
		load();
		return commonCSSURL;
	}

	/**
	 * @return the adminLeftNavURL
	 */
	public static String getAdminLeftNavURL() {
		load();
		return adminLeftNavURL;
	}
	
	/**
	 * Include the token on the banner request
	 * @param request
	 * @return
	 */
	public static String getBannerURL(HttpServletRequest request) {
		
		load();
		
		// This has all been copied from the main management app to make sure we use the token
		String token="";
		Cookie[] resultCookies = request.getCookies();
		if (resultCookies != null) {		
			for (Cookie cookie: resultCookies) {
				if (cookie.getName().equals("token")) {
					token = cookie.getValue();
					break;
				}
			}
		}
		
		String paramString = "?pageType=admin&token=" + URLEncoder.encode(token);
		return adminNavbarURL+paramString;
	}
	
	/**
	 * Determine if the calling code is a server to server request
	 * @throws IOException 
	 */
	public static boolean isS2SRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean isS2S = false;
		
		// must contain a remote user
		String remoteUser = request.getRemoteUser();
		if (remoteUser==null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "UNABLE_TO_READ_REMOTE_USER");
			return false;
		}
		
		// check for cloud/onprem (this can be slow this is a rarely called mechanism)
		VenturaConfigurationHelper configHelper =  VenturaConfigurationHelper.Factory.getInstance();
		Properties properties = (configHelper == null) ? null : configHelper.getGenericProperties();
		if (properties==null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "UNABLE_TO_READ_CONFIGURATION");
			return false;
		}
		
		// now determine if it's a privileged request
		isS2S = request.isUserInRole("admin");
		
		// and return
		if (!isS2S)
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		
		return isS2S;
	}

	
}
