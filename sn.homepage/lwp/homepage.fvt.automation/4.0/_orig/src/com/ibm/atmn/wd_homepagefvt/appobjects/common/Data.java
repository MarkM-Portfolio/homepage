/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.appobjects.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Properties;

import com.ibm.atmn.homepagefvt.appobjects.files.FilesObjects;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;


@SuppressWarnings("unused")
public class Data extends Initialize{
	
    private static final String BUNDLE = "data.properties";
    private static final Properties properties;
    static
    {
      properties = new Properties();
      try
      {
        properties.load(FilesObjects.class.getResourceAsStream(BUNDLE));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    
    //Generate a random number from date & time
	public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }
	
	public static final String Files_Public_Files = "link=Public Files";
	
}    
