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
package com.ibm.lconn.homepage.services.helper;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;


/**
 * Used by index.jsp and redirectAction.jsp to determine if the widgets page
 * should be displayed instead of updates.
 * Re: PMR# 33750,000,738
 *
 * @author BrianOG
 */
public class DefaultHomepageTabHelper {
    private static final String CLASS_NAME = DefaultHomepageTabHelper.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    private static boolean isSet = false;
    private static boolean isWidgetsDefault = false;
    private static boolean isGettingStartedBypassed = false;

    private final static String PROPERTY_DEFAULT_WIDGETS = "homepage.default.widgets";
    private final static String PROPERTY_BYPASS_GETTING_STARTED = "homepage.gettingstarted.bypass";

    private static void getPropertiesFromConfig() {
        if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.entering(CLASS_NAME, "getPropertiesFromConfig");
        }

        if (!isSet) { // once is enough to read per jvm.
            if (LOGGER.isLoggable(Level.FINER)) {
                LOGGER.logp(Level.FINER, CLASS_NAME, "isWidgetsDefault", "Reading from config");
                LOGGER.logp(Level.FINER, CLASS_NAME, "isGettingStartedBypassed", "Reading from config");
            }

            VenturaConfigurationHelper configHelper = VenturaConfigurationHelper.Factory.getInstance();

            if (configHelper != null) {
                Properties genProps = configHelper.getGenericProperites();

                String sWidgetsDefault = genProps.getProperty(PROPERTY_DEFAULT_WIDGETS, "false");
                String sGettingStartedBypassed = genProps.getProperty(PROPERTY_BYPASS_GETTING_STARTED, "false");

                if (LOGGER.isLoggable(Level.FINER)) {
                    LOGGER.logp(Level.FINER, CLASS_NAME, "isWidgetsDefault", "Property value: " + sWidgetsDefault);
                    LOGGER.logp(Level.FINER, CLASS_NAME, "isGettingStartedBypassed", "Property value: " + sGettingStartedBypassed);
                }


                isWidgetsDefault = Boolean.parseBoolean(sWidgetsDefault);
                isGettingStartedBypassed = Boolean.parseBoolean(sGettingStartedBypassed);

                isSet = true;
            }
        }

        if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.exiting(CLASS_NAME, "isWidgetsDefault");
        }
    }

    public static boolean isWidgetsDefault() {
        if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.entering(CLASS_NAME, "isWidgetsDefault");
        }

        if (!isSet) getPropertiesFromConfig();

        if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.exiting(CLASS_NAME, "isWidgetsDefault", new Boolean(isWidgetsDefault));
        }

        return isWidgetsDefault;
    }

    public static boolean isGettingStartedBypassed() {
        if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.entering(CLASS_NAME, "isGettingStartedBypassed");
        }

        if (!isSet) getPropertiesFromConfig();

        if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.exiting(CLASS_NAME, "isGettingStartedBypassed", new Boolean(isGettingStartedBypassed));
        }

        return isGettingStartedBypassed;
    }
}
