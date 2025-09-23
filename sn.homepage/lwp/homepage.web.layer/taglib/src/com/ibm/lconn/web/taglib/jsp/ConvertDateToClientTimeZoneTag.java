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

package com.ibm.lconn.web.taglib.jsp;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ConvertDateToClientTimeZoneTag  extends TagSupport {

	private static final long serialVersionUID = 113150967999881598L;
	private static String CLASS_NAME = BuildNumberTag.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private Date value;
	private String pattern;
	private String clientTZOffSet;
	
	private Date getNewCalendarDate(Calendar cal) throws ParseException {
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);
		
		String sdate = String.format("%02d/%02d/%02d %02d:%02d:%02d", day, month+1, year, hour, minutes, seconds);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.parse(sdate);
	}
	
	private String getConvertedDate() throws ParseException {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		cal.setTime(getValue());
		cal.add(Calendar.MINUTE, JspFunctions.getTimezoneOffsetAsMinutes(getClientTZOffSet()));
		
		Date convertedDate = getNewCalendarDate(cal);
		DateFormat df =DateFormat.getDateInstance(DateFormat.FULL);
		
		if(getPattern()!=null) {
			((SimpleDateFormat) df).applyPattern(getPattern());
		}
		
		return df.format(convertedDate);
	}
	
	@Override
	public int doStartTag() throws JspException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "doStartTag");		
		
		try {
			String res = getConvertedDate();
			pageContext.getOut().print(res);
		} catch (IOException e) {
			if (logger.isLoggable(Level.SEVERE)) {
				logger.logp(Level.SEVERE, CLASS_NAME, "doStartTag",
						"Exception", e);
			}
		} catch (ParseException e) {
			if (logger.isLoggable(Level.SEVERE)) {
				logger.logp(Level.SEVERE, CLASS_NAME, "doStartTag",
						"Exception", e);
			}
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "doStartTag");
		
		return SKIP_BODY;
	}
	
	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getClientTZOffSet() {
		return clientTZOffSet;
	}

	public void setClientTZOffSet(String offSet) {
		this.clientTZOffSet = offSet;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}


}
