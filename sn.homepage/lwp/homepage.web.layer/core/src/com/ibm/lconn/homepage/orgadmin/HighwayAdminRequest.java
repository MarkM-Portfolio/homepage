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

package com.ibm.lconn.homepage.orgadmin;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.connections.highway.client.api.HighwayAdminClient;
import com.ibm.connections.highway.client.api.HighwaySetup;
import com.ibm.connections.highway.common.api.HighwayException;
import com.ibm.connections.highway.common.api.HighwayUserSessionInfo;
import com.ibm.connections.highway.gatekeeper.HighwayGatekeeperImpl;
import com.ibm.lconn.core.gatekeeper.LCGatekeeper;
import com.ibm.lconn.core.gatekeeper.LCGatekeeperException;
import com.ibm.lconn.core.gatekeeper.LCSupportedFeature;
import com.ibm.lconn.core.platform.ICPlatform;
import com.ibm.lconn.core.web.util.Base64Util;
import com.ibm.lconn.core.web.util.CookieHelper;

public class HighwayAdminRequest {
	
	private static String CLASS_NAME = HighwayAdminRequest.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	protected static final String UPDATE_NONCE_ATTRIBUTE="X-Update-Nonce";
	
	protected static final String C2SETTING_SERVER = "c2.search.scoring.service";
	protected static final String C2SETTING_TEST_USERIDS = "c2.supported.test.userids";
	
	protected static final String C2SETTING_EXPORT_REDIS_HOST = "c2.export.redis.host";
	protected static final String C2SETTING_EXPORT_REDIS_PORT = "c2.export.redis.port";
	protected static final String C2SETTING_EXPORT_REDIS_PASSWORD = "c2.export.redis.pass";
	protected static final String C2SETTING_EXPORT_REDIS_CLUSTER_SET = "c2.export.redis.cluster.set";
	protected static final String C2SETTING_EXPORT_REDIS_SENTINEL_SET = "c2.export.redis.sentinel.set";
	protected static final String C2SETTING_EXPORT_REDIS_SENTINEL_MASTER = "c2.export.redis.sentinel.master";
	protected static final String C2SETTING_EXPORT_REDIS_THREADS = "c2.export.redis.threads";
	
	protected static final String C2SETTING_EXPORT_KAFKA_HOST = "c2.export.kafka.host";
	protected static final String C2SETTING_EXPORT_KAFKA_PORT = "c2.export.kafka.port";
	protected static final String C2SETTING_EXPORT_KAFKA_THREADS = "c2.export.kafka.threads";
	
	protected static final String BASE64_PREFIX = "~@64";
	
	private ResourceBundle bundle = null;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	public HighwayAdminRequest(ServletRequest request, ServletResponse response) {
		this.request = (HttpServletRequest)request;
		this.response = (HttpServletResponse)response;
		bundle = ResourceBundle.getBundle("com.ibm.lconn.homepage.orgadmin.orgadmin", request.getLocale());
	}
	
	public Collection<String> getHighwaySettingsToShow() {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getHighwaySettingsToShow", new Object[] {});
		}

		String showString = getResource("orgadmin.show.values");
		String[] names = showString.split(",", 10);
		
		ArrayList<String> list = new ArrayList<String>();
		for (String name : names) {
			name = name.trim();
			String gkFlag = getOptionalResource(name+".gatekeeper");
			boolean show = isGatekeeperEnabled(gkFlag);
			
			if (logger.isLoggable(FINER)) {
				logger.log(FINER, "Setting :" + name + " - " + gkFlag + "["+show+"]");
			}

			if (show)
				list.add(name);
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "getHighwaySettingsToShow", list);
		}
		return list;
	}
	
	public SettingDetails getHighwaySettingDetails(String name) {

		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getHighwaySettingDetails", name);
		}

		SettingDetails details = null;
		try {
			HighwayUserSessionInfo userSession = HighwaySetup.createUserInfoFromRequest(request);
			details=getDetails(userSession, name, request.getLocale());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (HighwayException e) {
			e.printStackTrace();
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "getHighwaySettingDetails", details.toString());
		}
		return details;
	}
	
	public SettingDetails updateHighwaySettingDetails() {

		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "updateHighwaySettingDetails", new Object[] {request.getParameter("settingName")});
		}
		
		// minimal parameters
		String postName = request.getParameter("settingName");
		String postValue = request.getParameter("settingValue");
		String postOrg = request.getParameter("organizationId");
		if ((postName==null) || (postOrg==null))
			return null;

		if (logger.isLoggable(FINER)) {
			logger.log(FINER, postName + "[org:"+postOrg+"] - " + postValue);
		}
	
		// get current details
		HighwayUserSessionInfo userSession = null; 
		SettingDetails details = null;
		try {
			userSession = HighwaySetup.createUserInfoFromRequest(request);
			details=getDetails(userSession, postName, request.getLocale());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (HighwayException e) {
			e.printStackTrace();
		}
		
		// validate the setting name and contents are valid
		String oldValue = details.getValue();
		boolean valid = isValidSettingForPost(postName);
		if (valid) {
			details.setValue(postValue);
			IValidator validator = getValidator(details, request.getLocale());
			if (validator != null)
				valid = validator.validate(details); // status & message will be set up in validate
		}
		
		// validate the nonce
		if (valid)
			valid = isValidNonce();
		
		// and attempt to update
		if (valid) {
			if (logger.isLoggable(FINER)) {
				logger.log(FINER, "set value ["+details.getValue()+"] " + (details.isGatekeeperSetting() ? "Gatekeeper" : "Highway"));
			}
			if (details.isGatekeeperSetting()) {
				HighwayGatekeeperImpl gatekeeperImpl = new HighwayGatekeeperImpl();
				boolean value = Boolean.parseBoolean(details.getValue());
				try {
					gatekeeperImpl.setEnabled(LCSupportedFeature.valueOf(details.getName()), userSession.getOrgId(), value);
				} catch (LCGatekeeperException e) {
					e.printStackTrace();
					valid=false;
				}
			} else {
				HighwayAdminClient hc = HighwaySetup.getHighwayAdminClient("homepage");
				try {
					valid = hc.setSetting(userSession, userSession.getOrgId(), postName, null, details.getValue());
				} catch (HighwayException e) {
					e.printStackTrace();
					valid = false;
				} 
			}
			
			if (!valid) {
				addErrorToDetails("unable.to.set.value", details, null);
			}
		}
		
		// reset if not updated, otherwise escape for display purposes
		if (!valid)
			details.setValue(oldValue);
		else
			details.setValue(escapeContentString(details.getValue()));
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "updateHighwaySettingDetails", details.getValue());
		}
		return details;
	}
	
	public boolean updateC2Setting() throws IOException {

		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "updateC2Setting", new Object[] {request.getParameter("settingName")});
		}

		// minimal parameters
		boolean valid =true;
		String postName = request.getParameter("settingName");
		String postType = request.getParameter("settingType");
		String postValue = request.getParameter("settingValue");
		if ((postName==null)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "NO_SETTING_NAME");
			return false;
		}
		
		// predefined settings allowed only
		if (postName.equalsIgnoreCase(C2SETTING_SERVER)) {
			updateSystemHighwaySetting(C2SETTING_SERVER, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_TEST_USERIDS)) {
			updateSystemHighwaySetting(C2SETTING_TEST_USERIDS, postValue, true);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_REDIS_HOST)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_REDIS_HOST, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_REDIS_PORT)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_REDIS_PORT, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_REDIS_PASSWORD)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_REDIS_PASSWORD, postValue, false, true);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_REDIS_CLUSTER_SET)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_REDIS_CLUSTER_SET, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_REDIS_SENTINEL_SET)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_REDIS_SENTINEL_SET, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_REDIS_SENTINEL_MASTER)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_REDIS_SENTINEL_MASTER, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_REDIS_THREADS)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_REDIS_THREADS, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_KAFKA_HOST)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_KAFKA_HOST, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_KAFKA_PORT)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_KAFKA_PORT, postValue, false);
		} else if (postName.equalsIgnoreCase(C2SETTING_EXPORT_KAFKA_THREADS)) {
			updateSystemHighwaySetting(C2SETTING_EXPORT_KAFKA_THREADS, postValue, false);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "INVALID_SETTING_NAME");
			valid = false;
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "updateC2Setting", valid);
		}
		
		return valid;
	}
	
	private boolean updateSystemHighwaySetting(String name, String value, boolean isFile) throws IOException  {
		return updateSystemHighwaySetting(name,value,isFile,false);
	}
	
	private boolean updateSystemHighwaySetting(String name, String value, boolean isFile,boolean isPassword) throws IOException {

		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "updateSystemHighwaySetting", new Object[] {request.getParameter("settingName")});
		}
		
		if (isPassword) {
			value=BASE64_PREFIX+Base64Util.encodeBase64(value);
		}
	
		HighwayUserSessionInfo userSession = HighwaySetup.getAdminUserSessionInfo(); 
		HighwayAdminClient hc = HighwaySetup.getHighwayAdminClient("homepage");
		boolean valid = false;
		try {
			if (isFile)
				valid = hc.setTextFileSetting(userSession, ICPlatform.ORG_DEFAULT, name, null, value);
			else
				valid = hc.setSetting(userSession, ICPlatform.ORG_DEFAULT, name, null, value);
		} catch (HighwayException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			e.printStackTrace();
			valid = false;
		} 
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "updateSystemHighwaySetting", valid);
		}
		return valid;
	}
	
	public String setNonce() {
		String nonce = UUID.randomUUID().toString();
	    HttpSession session = request.getSession(false);
	    session.setAttribute(UPDATE_NONCE_ATTRIBUTE, nonce);			
		CookieHelper.addCookie(request, response, UPDATE_NONCE_ATTRIBUTE, nonce);		
		return nonce;
	}
	
	public String getNonce() {
	    HttpSession session = request.getSession(false);
	    String nonce = (String)session.getAttribute(UPDATE_NONCE_ATTRIBUTE);			
		return nonce;
	}

	private SettingDetails getDetails(HighwayUserSessionInfo userSession, String name, Locale locale) throws HighwayException {

		SettingDetails details=new SettingDetails();
		details.setName(name);
		
		// look for the rest from the properties file
		details.setTitle(getOptionalResource(name+".title"));
		details.setDescription(getOptionalResource(name+".description"));
		details.setType(getOptionalResource(name+".type"));
		details.setGatekeeper(getOptionalResource(name+".gatekeeper"));
		details.setPrompt(getOptionalResource(name+".prompt"));
		details.setWarning(getOptionalResource(name+".warning"));
		details.setValidation(getOptionalResource(name+".validation"));
		
		// now add the value, escaping as appropriate
		if (details.isGatekeeperSetting()) {
			try {
				boolean isEnabled = LCGatekeeper.isEnabled(LCSupportedFeature.valueOf(details.getName()), request);
				String value = Boolean.toString(isEnabled);
				details.setValue(value);
			} catch (LCGatekeeperException e) {
				e.printStackTrace();
			}
		} else {
			HighwayAdminClient hc = HighwaySetup.getHighwayAdminClient("homepage");
			Object value = hc.getSetting(userSession, name);
			details.setValue( (value==null) ? "" : escapeContentString(value.toString()) );
		}
		
		return details;
	}
	
	/**
	 * Makes HTML content safe - not sufficient for any text that can be used within an attribute
	 * @param s
	 * @return
	 */
	private String escapeContentString(String s) {
		return s.replace("\"","&quot;").replace("<","&lt;").replace(">","&gt;").replace("&","&amp;");
	}
	
	private boolean isValidSettingForPost(String name) {
		HashSet<String> list = new HashSet<String>();
		list.addAll(getHighwaySettingsToShow());
		boolean valid = list.contains(name);
		if (!valid)
			logger.log(Level.SEVERE, "Attempt to update invalid setting : " + name);
		return valid;
	}
	
	private boolean isValidNonce() {
		String nonce = request.getParameter("nonce");
		boolean valid = (nonce!=null) && nonce.equals(getNonce());
		if (!valid)
			logger.log(Level.SEVERE, "Attempt to use an invalid nonce.");
		return valid;
	}
	
	private boolean isGatekeeperEnabled(String flagName) {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "isGatekeeperEnabled", new Object[] {flagName});
		}
		
		LCSupportedFeature feature = (flagName == null) ? null : LCSupportedFeature.valueOf(flagName);
		boolean valid = true; // no such flag means it has already been enabled fulltime
		
		if (feature != null) {
			try {
				valid = LCGatekeeper.isEnabled(feature, ICPlatform.getCurrentOrgId());
			} catch (LCGatekeeperException e) {
				e.printStackTrace();
				valid=false;
			}
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "isGatekeeperEnabled", Boolean.toString(valid));
		}
			
		return valid;
	}
	
	private IValidator getValidator(SettingDetails details, Locale locale) {
		if (details.getType() != null) {
			if (details.getType().equals(SettingDetails.TYPE_INT)) {
				return new ValidatorForInt(this);
			} else 	if (details.getType().equals(SettingDetails.TYPE_TEXT)) {
				return new ValidatorForText(this);
			} else 	if (details.getType().equals(SettingDetails.TYPE_LONG)) {
				return new ValidatorForLong(this);
 			} else 	if (details.getType().equals(SettingDetails.TYPE_BOOL)) {
				return new ValidatorForBool();
			} else 	if (details.getType().equals(SettingDetails.TYPE_GATEKEEPER)) {
				return new ValidatorForBool();
			}
		}
		return null;
	}
	
	public void addErrorToDetails(String errorname, SettingDetails details, String context) {
		String message = getResource(errorname);
		if (context != null)
			message += " ["+context+"]";
		details.setMessage(message);
		details.setStatus(SettingDetails.STATUS_ERROR);
	}
	
	public String getResource(String name) {
		return bundle.getString(name);
	}
	
	public String getOptionalResource(String name) {
		String s=null;
		try {
			s = bundle.getString(name);
		} catch (Exception e) {
			;
		}
		return s;
	}

}
