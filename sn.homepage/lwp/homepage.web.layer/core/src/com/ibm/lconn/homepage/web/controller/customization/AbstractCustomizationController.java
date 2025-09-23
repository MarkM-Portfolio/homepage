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

import java.util.logging.Logger;

import com.ibm.lconn.homepage.web.controller.AbstractWidgetPageLayoutController;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

/**
 * Common stuff to Load and Save customization actions
 * 
 * @author vincent
 * 
 */
public abstract class AbstractCustomizationController extends AbstractWidgetPageLayoutController {
	private static final long serialVersionUID = 4358785678891971188L;

	private final static String CLASS_NAME = AbstractCustomizationController.class.getName();

	private final static Logger logger = Logger.getLogger(CLASS_NAME);

	//private IEventUtils eventUtils;
	
	// that was the name of the widget instance id parameter
	// TODO: change name to something more obvious...
	private String FId;

	public String getFId() {
		return FId;
	}

	public void setFId(String fid) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "setFid", fid);

		FId = fid;

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "setFid");
	}

//	public IEventUtils getEventUtils() {
//		return eventUtils;
//	}
//
//	public void setEventUtils(IEventUtils eventUtils) {
//		this.eventUtils = eventUtils;
//	}
}
