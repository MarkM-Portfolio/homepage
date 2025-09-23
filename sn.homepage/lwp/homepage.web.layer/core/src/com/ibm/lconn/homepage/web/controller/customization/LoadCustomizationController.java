/*******************************************************************************
 * HCL Confidential
 * OCO Source Materials
 *
 * Copyright HCL Technologies Limited 2010, 2021
 *
 * The source code for this program is not published or otherwise divested of 
 * its trade secrets, irrespective of what has been deposited with the U.S. 
 * Copyright Office.
 *******************************************************************************/

package com.ibm.lconn.homepage.web.controller.customization;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.WebException;

@Controller
@RequestMapping(value = { "/web" })
public class LoadCustomizationController extends AbstractCustomizationController {

	private static final long serialVersionUID = 4511724607707677413L;

	private final static String CLASS_NAME = LoadCustomizationController.class.getName();

	private final static Logger logger = Logger.getLogger(CLASS_NAME);

	// content sent to the client
	private InputStream resultContent;
	
	public void init(HttpServletRequest request) {
        // TODO Auto-generated method stub

        super.init(request);

    }

	public String executeInternal(HttpServletRequest request, String FId) throws WebException {
		
		init(request);
		
		if (FId != null) {
        	setFId(FId);
        }
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "executeInternal");

		String userInternalId = getPersonInternalId();
		System.out.println("\n\n\n\n LoadCustomizationController executeInternal userInternalId = " + userInternalId);
		
		
		try {
			loadCustomization(userInternalId);
		} catch (IOException e) {
			throw new WebException(e);
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "executeInternal");

		return null;
	}

	private void loadCustomization(String userInternalId) throws IOException,
			WebException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "loadCustomization", userInternalId);

		String widgetId = getFId();
		String tabInstanceId = getTabInstanceId();
		
		System.out.println("\n\n\n\n LoadCustomizationController loadCustomization userInternalId = " + userInternalId);
		System.out.println("\n\n\n\n LoadCustomizationController loadCustomization widgetId/FId = " + widgetId);
		System.out.println("\n\n\n\n LoadCustomizationController loadCustomization tabInstanceId = " + tabInstanceId);

		if ((widgetId != null) && (tabInstanceId != null)) {

			List<WidgetInstance> widgetInstances;
			try {
				widgetInstances = getWidgetServices().getWidgetInstances(widgetId, tabInstanceId);
				
				System.out.println("\n\n\n\n LoadCustomizationController loadCustomization widgetInstances = " + widgetInstances);

				if ((widgetInstances != null) && (tabInstanceId != null)) {
					// StringBuilder buffer = new StringBuilder()

					// TODO: handle multiple widget instance case here
					String settings = widgetInstances.get(0).getWidgetSetting();
					
					System.out.println("\n\n\n\n LoadCustomizationController loadCustomization settings = " + settings);
					
					if (settings == null)
						settings = "";

					// check if the widget instance belongs to the authenticated
					// user
					// throw a security exception if this is not the case
					doWidgetOwnershipCheck(widgetInstances.get(0).getWidgetInstanceId());

					resultContent = new ByteArrayInputStream(settings.getBytes("UTF-8"));
					
					System.out.println("\n\n\n\n LoadCustomizationController loadCustomization should success");

					// writer.append(settings);
					// writer.close();
				} else {
					String msg = getWebResourceBundle().getString(
							"error.userpref.get.widgetprefs", userInternalId,
							widgetId, tabInstanceId);
					if (logger.isLoggable(SEVERE)) {
						logger.logp(SEVERE, CLASS_NAME, "loadCustomization",
								msg);
					}
					// do not throw an exception in this case. This can happen
					// the
					// first time that a widget is put onto the page if it is a
					// new widget
				}
			} catch (ServiceException e) {
				String msg = getWebResourceBundle().getString(
						"error.userpref.load.widget.setting", userInternalId,
						widgetId);
				WebException t = new WebException(msg);
				if (logger.isLoggable(SEVERE)) {
					logger.logp(SEVERE, CLASS_NAME, "handle", msg, t);
				}
				throw t;
			}

		} else {
			String msg = getWebResourceBundle().getString(
					"error.userpref.widgetid", userInternalId);
			WebException t = new WebException(msg);
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "handle", msg, t);
			}
			throw t;
		}
	}

	public InputStream getResultContent() {
		return resultContent;
	}

	public void setResultContent(InputStream resultContent) {
		this.resultContent = resultContent;
	}
}
