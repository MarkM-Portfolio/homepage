/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010,2021                     */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.controller.admin;

import static java.util.logging.Level.FINER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.commons.lang.StringEscapeUtils;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.core.services.cre.remote.widget.model.ProxyPolicy;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetPolicyFlag;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetTypeEnum;
import com.ibm.lconn.core.services.shindig.remote.model.OAuth2ClientRemote;
import com.ibm.lconn.core.services.shindig.remote.model.OAuth2GadgetBindingRemote;
import com.ibm.lconn.core.services.shindig.remote.spi.OAuth2ConsumerServiceRemote;
import com.ibm.lconn.core.tkrproxysvc.service.LCRemoteServiceFactory;
import com.ibm.lconn.homepage.model.Category;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.tk.rproxysvc.service.TKRemoteServiceDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

public class WidgetFormFacadeSpring {
	
	private final static String CLASS_NAME = WidgetFormFacadeSpring.class.getName();
	private final static Logger logger = Logger.getLogger(CLASS_NAME);

	private OAuth2ConsumerServiceRemote _service = null;
	@Autowired
	private IWidgetServices widgetServices;
	private Map<String,String> oauthBindings = new HashMap<String, String>(); // key = service name, value = oauth client name
	
	static {
		LCRemoteServiceFactory.setComponentName("homepage");
	}	

	private Widget widget;
	private Collection<String> installedComponents;
	private Locale locale = null;
	private String orderAfterId = null;

	public void setWidget(Widget widget) {
		this.widget = widget;
		//This mean a new widget has been put in
		if (widget.getWidgetId() == null) {
			// 3rd party for any new widget in 2.5
			widget.setCategoryName(Category.OTHER.name());
			// A new widget created by the admin can be just primary.
			widget.setType(WidgetTypeEnum.PRIMARY.getDBValue());
			widget.setIsSystem(false);
			widget.setPrereqs(new ArrayList<String>());
		}
	}

	public Widget getWidget() {
		return widget;
	}

	public void setWidgetId(String widgetId) {
		if (widgetId != null)
			widget.setWidgetId(widgetId);
		if (logger.isLoggable(FINER)) {
			logger.info("setWidgetId():  " + widgetId);
		}
	}

	public String getWidgetId() {
		return widget.getWidgetId() == null ? "" : widget.getWidgetId();
	}

	public void setTitle(String title) {
		if (title != null) {
			if (logger.isLoggable(FINER)) {
				logger.info("title:  " + title);
			}
			widget.setTitle(StringEscapeUtils.escapeHtml(title));
		}
	}

	public String getTitle() {
		return widget.getTitle();
	}

	public void setUrl(String url) {
		widget.setUrl(url);
	}

	public String getUrl() {
		return widget.getUrl();
	}

	public void setPreviewImage(String previewImage) {
		widget.setPreviewImage(previewImage);
	}

	public String getPreviewImage() {
		return widget.getPreviewImage();
	}

	public void setIconUrl(String iconUrl) {
		widget.setIconUrl(iconUrl);
	}

	public String getIconUrl() {
		return widget.getIconUrl();
	}

	public void setEnabled(String isEnabled) {
		if (getWidgetId() == null) // During the widget creation the id is null
			// so by default is not enabled
			widget.setEnabled(false);
		else
			widget.setEnabled(Boolean.parseBoolean(isEnabled));
	}

	public String getEnabled() {
		return String.valueOf(widget.isEnabled());
	}


	public void setDefaultOpened(String isDefaultOpened) {
		widget.setDefaultOpened(Boolean.parseBoolean(isDefaultOpened));
	}

	public void setHomepageSpecific(String isHomepageSpecific) {
		widget.setIsHomepageSpecific(Boolean.parseBoolean(isHomepageSpecific));
	}
	
	public void setMarkedCachable(String isMarkedCachable) {
		widget.setIsMarkedCachable(Boolean.parseBoolean(isMarkedCachable));
	}

	public void setMultipleInstanceAllowed(String isMultipleInstanceAllowed) {
		widget.setMultipleInstanceAllowed(Boolean.parseBoolean(isMultipleInstanceAllowed));
	}

	public void setBelongTabWidget(String isBelongTabWidget) {
		widget.setBelongTabWidget(Boolean.parseBoolean(isBelongTabWidget));
	}

	public void setBelongTabUpdate(String isBelongTabUpdate) {
		widget.setBelongTabUpdate(Boolean.parseBoolean(isBelongTabUpdate));
	}
	
	public String getIsMarkedCachableChecked() {
		return widget.isCachable() ? "checked" : "";
	}
	
	public String getIsDefaultOpenedChecked() {
		return widget.isDefaultOpened() ? "checked" : "";
	}
	public String getIsBelongTabUpdateChecked(){
		return widget.isBelongTabUpdate() ? "checked" : "";
	}

	public String getIsBelongTabWidgetChecked() {
		return widget.isBelongTabWidget() ? "checked" : "";
	}

	public String getIsMultipleInstanceAllowedChecked() {
		return widget.isMultipleInstanceAllowed() ? "checked" : "";
	}

	public String getIsHomepageSpecificChecked(){
		return widget.isHomepageSpecific() ? "checked" : "";
	}
	
	public void setSystem(boolean isSystem) {
		widget.setIsSystem(isSystem);
	}

	public String getIsSystemChecked() {
		return widget.getIsSystem()? "disabled" : "";
	}

	public void setPrereqList(String[] prereqsArray) {
		if (prereqsArray != null) {
			// Add prereqs
			Collection<String> prereqsCollection = new ArrayList<String>();
			for (String prereq : prereqsArray) {
				prereqsCollection.add(prereq.trim());
			}
			widget.setPrereqs(prereqsCollection);
		}
	}

	public void setText(String text) {
		if (text != null) {
			widget.setText(StringEscapeUtils.escapeHtml(text));
		}
	}

	public String getText() {
		return widget.getText();
	}

	public Collection<Entry<String, String>> getPrereqCheck() throws ServiceException {
		HashMap<String, String> prereqCheck = new HashMap<String, String>(7);
		Collection<String> installedComponets = getInstalledComponents();
		for(String prereqCheckName : installedComponets){
			if (widget.getPrereqs() != null) {
				if (widget.getPrereqs().contains(prereqCheckName)){
					prereqCheck.put(prereqCheckName, "checked");
				}else{
					prereqCheck.put(prereqCheckName, "");
				}
			}else{
				prereqCheck.put(prereqCheckName, "");
			}
			
		}
		return prereqCheck.entrySet();
	}

	public void setInstalledComponents(Collection<String> installedComponents) {
		this.installedComponents = installedComponents;
	}
	
	public Collection<String> getInstalledComponents() throws ServiceException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getInstalledComponents");
		}
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getInstalledComponents", installedComponents);
		}
		return installedComponents;
	}

	public void setSecureIconUrl(String secureIconUrl) {
		widget.setSecureIconUrl(secureIconUrl);
	}

	public String getSecureIconUrl() {
		return widget.getSecureIconUrl();
	}

	public void setSecureUrl(String secureUrl) {
		widget.setSecureUrl(secureUrl);
	}

	public String getSecureUrl() {
		return widget.getSecureUrl();
	}
	
	public void setIsGadget(String isGadget) {
		widget.setIsGadget(Boolean.parseBoolean(isGadget));
	}

	public String getIsGadget(){
		String result = widget.isGadget() ? "true" : "false";
		return(result);
	}
	
	// Gadget specific settings
	public String getIsGadgetTrusted() {
		boolean result = widget.getPolicyFlagSet().getFlag(WidgetPolicyFlag.GADGET_TRUSTED);
		return(Boolean.toString(result));
	}
	
	public void setIsGadgetTrusted(String isTrusted) {
		boolean b = Boolean.parseBoolean(isTrusted);
		widget.getPolicyFlagSet().setFlag(WidgetPolicyFlag.GADGET_TRUSTED, b);
	}
	
	// Gadget specific settings
	public String getIsSSOChecked() {
		boolean result = widget.getPolicyFlagSet().getFlag(WidgetPolicyFlag.GADGET_SSO);
		return result ? "checked" : "";
	}

	public void setIsGadgetSSO(String isSSO) {
		boolean b = Boolean.parseBoolean(isSSO);
		widget.getPolicyFlagSet().setFlag(WidgetPolicyFlag.GADGET_SSO, b);
	}
	
	public String getIsGadgetInShareboxChecked() {
		boolean result =  widget.getBelongTabValue(TabType.SHAREDIALOG);
		return result ? "checked" : "";
	}
	
	public void setIsGadgetInSharebox(String isInSharebox) {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "setIsGadgetInSharebox");
		}
		boolean b = Boolean.parseBoolean(isInSharebox);
		boolean belong = widget.getBelongTabValue(TabType.SHAREDIALOG);
		if (b != belong) {
			widget.setBelongTabValue(TabType.SHAREDIALOG, b);	
			if (logger.isLoggable(FINER)) {
				logger.info("setIsGadgetInSharebox is set:  " + b);
				logger.info("b:  " + b + ", belong:  " + belong);
			}
		}
		
		
	}
	
	public String getIsGadgetInEEChecked() {
		boolean result = widget.getBelongTabValue(TabType.EMBEDXP);
		return result ? "checked" : "";
	}
	
	public void setIsGadgetInEE(String isInEE) {
		boolean b = Boolean.parseBoolean(isInEE);
		widget.setBelongTabValue(TabType.EMBEDXP, b);
	}
		
	//
	// Returns string version of one of widgetExtendedSettings.ProxyPolicy.intranet_access, ...external_only, ...custom
	//
	public String getIsGadgetUrlPolicy() {
		String policyString = "custom";
		ProxyPolicy result= widget.getProxyPolicy();
		if (result == ProxyPolicy.intranet_access) {
			policyString = "intranet";
		} else if (result == ProxyPolicy.external_only) {
			policyString = "external_only";
		}
		return(policyString);
	}
	
	//
	// takes one of widgetExtendedSettings.ProxyPolicy.intranet_access, ...external_only, ...custom
	//
	public void setIsGadgetUrlPolicy(String policy) {
		ProxyPolicy p = ProxyPolicy.custom;
		if (policy.equals("intranet")) {
				p = ProxyPolicy.intranet_access;
				
		} else if (policy.equals("external_only")) {
			p = ProxyPolicy.external_only;
		}
		widget.setProxyPolicy(p);
	}
	
	/**
	 * Returns collection of share gadgets hashmap entries
	 * key is the gadget name
	 * value is "selected" if this is the insertion point, "" if not
	 * @throws ServiceException 
	*/
	public Collection<ShareGadget> getShareGadgetsOptions() throws ServiceException {
		final boolean FINER_P = logger.isLoggable(FINER);

		if (FINER_P) {
			logger.entering(CLASS_NAME, "getShareGadgetsOptions");
		}

		final List<ShareGadget> sgCtrls = new ArrayList<ShareGadget>(); // Array of control beans used to populate selection options
		final String thisWid = this.getWidgetId();
		
		ShareGadget prevSg = null;
		
		for (Widget w :  widgetServices.getOrderedShareboxWidgets(locale, true)) {
			if (FINER_P) {
				logger.info("getShareGadgetsOptions widget ID:  " + w.getWidgetId() + ", thisWid: " + thisWid);
			}
			
			if (w.getWidgetId().compareTo(thisWid) == 0) {//This widget is in the Sharebox list.  Don't show it.
				// Set previous widget as "insert after" point.
				if (prevSg != null) {
					prevSg.putIsSelected(true);
				}
			} else {
				final ShareGadget sg = new ShareGadget();
				sg.putName(w.getTitle());
				sg.putId(w.getWidgetId());
				if (w.getIsSystem()) {
					// If previous gadget is a system gadget, disable it so can't insert in middle of system gadgets
					if (prevSg != null) {
						prevSg.putIsDisabled(true);
					}
				}
				sgCtrls.add(sg);
				prevSg = sg;
			}
		}
		
		if (FINER_P) {
			logger.info("getShareGadgetsOptions returning: " + sgCtrls);
			logger.exiting(CLASS_NAME, "getShareGadgetsOptions");
		}
		
		return sgCtrls;
	}
	
	/**
	 * Set insertion point in Sharebox
	 */
	public void setShareGadgetInsertionPoint(String gadgetId) throws WidgetServiceException, ServiceException {
		this.orderAfterId = gadgetId;
	}
	
	public String getShareGadgetInsertionPoint(String gadgetId) {
		return orderAfterId;
	}

	/**
	 * Get List of service map entries
	 */
	public Collection<Entry<String, String>> getServiceMapEntries() {
		final boolean FINER_P = logger.isLoggable(FINER);
		int count = 0;
		
		if (FINER_P) {
			logger.entering(CLASS_NAME, "getServiceMapEntries");
		}
		
		Map<String,String> m = new HashMap<String, String>();
		OAuth2ConsumerServiceRemote oauth2 = getOAuth2ConsumerServiceRemote();
		String wid = this.widget.getWidgetId();
		if (FINER_P) {
			logger.finer("wid:  " + wid);
		}
		if (wid != null) {
			count = oauth2.countGadgetBinding(wid, null);	

			if (FINER_P) {
				logger.finer("count:  " + count);
			}
			List<OAuth2GadgetBindingRemote> Bindings = oauth2.browseGadgetBinding(wid, null, count, 1); // Get list of client mappings
			for (OAuth2GadgetBindingRemote b : Bindings) {
				if (FINER_P) {
					logger.finer("service:  " + b.getServiceName() + ", client:  " + b.getClientName());
				}
				m.put(b.getServiceName(), b.getClientName());
			}
		}
		return(m.entrySet());
	}
	
	/**
	 * Sets list of service map entries as entered in the form
	 */
	public void setServiceMappings(String mappings[]) {
		final boolean FINER_P = logger.isLoggable(FINER);
		final String separator = "->";
		
		for (int i=0; i<mappings.length; i++){
			if (FINER_P) {
				logger.finer("setServiceMappings():mappings[" + i + "]=" + mappings[i]);
			}
			// Binding is a string like serviceName->oauthClientName.  Tease out the two names and add to binding map
			if(mappings[i].contains(separator) == true) {
				String[] vals = mappings[i].split(separator);
				if (FINER_P) {
					logger.finer("Creating service mapping.  Service name:  " + vals[0] + ", client name:  " + vals[1]);
				}
				this.oauthBindings.put(vals[0], vals[1]);
			}
		}
	}
	
	/**
	 * Return Collection of strings to be used by HTML select tag
	 */
	public Collection<String> getGadgetOauth() {
		final boolean FINER_P = logger.isLoggable(FINER);
		
		if (FINER_P) {
			logger.entering(CLASS_NAME, "getGadgetOauth");
		}

		final OAuth2ConsumerServiceRemote oauthRemote = getOAuth2ConsumerServiceRemote();
		final Collection<String> oauthClients = new ArrayList<String>();
		
		final int count = oauthRemote.countClient(null);
		
		if (FINER_P) {
			logger.finer("count:  " + count);
		}
		
		for (OAuth2ClientRemote client : oauthRemote.browseClient(null, count, 1)) {
			if (FINER_P) {
				logger.finer("client name:  " + client.getClientName());
			}
			oauthClients.add(client.getClientName());
		}

		if (FINER_P) {
			logger.exiting(CLASS_NAME, "getGadgetOauth");
		}
		
		return(oauthClients);
	}
	
	/**
	 * Bind service names to oauth2 clients
	 * @param wId - id of widget to do bindings on
	 */
	void bindServiceNames(String wId) {
		final boolean FINER_P = logger.isLoggable(FINER);
		OAuth2ConsumerServiceRemote oauth2 = getOAuth2ConsumerServiceRemote();
		String wid = this.widget.getWidgetId();
		
		// First delete all the current service name mappings.
		int count = oauth2.countGadgetBinding(wid, null);
		if (FINER_P) {
			logger.finer("count:  " + count);
		}
		List<OAuth2GadgetBindingRemote> Bindings = oauth2.browseGadgetBinding(wid, null, count, 1); // Get list of client mappings
		for (OAuth2GadgetBindingRemote b : Bindings) {
			if (FINER_P) {
				logger.finer("DELETING service:  " + b.getServiceName() + ", client:  " + b.getClientName());
			}
			oauth2.unbindGadget(wid, b.getServiceName());
		}
		
		// Then add the new bindings.
		for (Map.Entry<String, String> b : this.oauthBindings.entrySet()) {
			String service = b.getKey();
			String client = b.getValue();
			if (FINER_P) {
				logger.finer("bindServiceName:  service:  " + service + ", client:  " + client);
			}
			oauth2.bindGadget(wId, service, client, false); 
		}
	}
	
	
	/**
	 * @returns handle to OAuth2ConsumerServiceRemote interface
	 */
	private OAuth2ConsumerServiceRemote getOAuth2ConsumerServiceRemote() {
		try {
			if (logger.isLoggable(FINER)) {
				logger.entering(CLASS_NAME, "getOAuth2ConsumerServiceRemote");
			}
		if (this._service == null) {
			TKRemoteServiceDescriptor<OAuth2ConsumerServiceRemote> descriptor = new TKRemoteServiceDescriptor<OAuth2ConsumerServiceRemote>(
					OAuth2ConsumerServiceRemote.class, "news");
			this._service = LCRemoteServiceFactory.getInstance(descriptor);
		}
		} catch(Throwable e) {
			logger.info("ERROR:  " + e);
			return(null);
		}
		if (logger.isLoggable(FINER)) {
			logger.info("this._service:  " + this._service);	
		}
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "getOAuth2ConsumerServiceRemote");
		}
		return this._service;
	}

	/**
	 * @return the locale
	 */
	public final Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public final void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * @param widgetServices the widgetServices to set
	 */
	public final void setWidgetServices(IWidgetServices widgetServices) {
		this.widgetServices = widgetServices;
	}

	/**
	 * Inner class to represent a shared gadget entry in Selection control. 
	 * 
	 *
	 */
	public class ShareGadget {
		private String name = null;
		private String id = null;
		private boolean selected = false;
		private boolean disabled = false;
		
		public void putName(String name) {
			this.name = name;
		}
		public String getName() {
			return(this.name);
		}
		public void putId(String id) {
			this.id = id;
		}
		public String getId() {
			return(this.id);
		}
		public void putIsSelected(boolean b) {
			this.selected = b;
		}
		public String getIsSelected() {
			String result = "";
				if (this.selected) {
					result = "selected";
				}
			return(result);
		}
		public void putIsDisabled(boolean b) {
			this.disabled = b;
		}
		public String getIsDisabled() {
			String result = "";
			if (this.disabled) {
				result = "disabled";
			}
			return(result);
		}
	}
	
	/**
	 * @return the orderAfterId
	 */
	public final String getOrderAfterId() {
		return orderAfterId;
	}

	/**
	 * @param orderAfterId the orderAfterId to set
	 */
	public final void setOrderAfterId(String orderAfterId) {
		this.orderAfterId = orderAfterId;
	}
	
}

