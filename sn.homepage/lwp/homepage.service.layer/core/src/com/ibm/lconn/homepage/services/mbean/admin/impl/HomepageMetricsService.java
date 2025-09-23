/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright  HCL Technologies Limited. 2009, 2021                   */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.mbean.admin.impl;

import static java.util.logging.Level.FINER;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.homepage.services.ISystemMetrics;

/**
 * 
 * Services to retrieve metrics for the wsadmin. This is registred as an MBean.
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public class HomepageMetricsService implements HomepageMetricsServiceMBean {
	
	private static String CLASS_NAME = HomepageMetricsService.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
	private ISystemMetrics systemMetrics;
	
    static final String DATESSTR = "dates";
    static final String OKSTR = "ok";
    static final String ERRORSTR = "error";
    
    private static final String METRIC_TOPWIDGETS = "homepage.metric.topWidgets";
    
    private WidgetCatalogServiceRemote widgetCatalogService;
    
    public void setWidgetCatalogService(WidgetCatalogServiceRemote widgetCatalogService) {
    	this.widgetCatalogService = widgetCatalogService;
    }
    
    
	public Object fetchMetric(String metricName) {
        if(logger.isLoggable(Level.FINER))
        	logger.entering(CLASS_NAME, "fetchMetric", metricName);
        
        if (metricName == null || metricName.length() == 0)
            return null;

        String oneField[] = new String[] {metricName};

        if(logger.isLoggable(Level.FINEST)){
        	// KH: externalized string info.metrics.property.value
        	logger.logp(Level.FINEST, CLASS_NAME, "fetchMetric", "one field: {0}", oneField);
        }

        Map<String,Object> tempMap =  fetchMetricsFields(oneField);
        Object metric = (Object)tempMap.get( metricName);
        
        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "fetchMetric", metric);
        }
		
		return metric;
	}

	public Map<String,Object> fetchMetrics() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "fetchMetrics", new Object[] {});
		
		Map<String,Object> metrics = systemMetrics.fetchMetrics();

		// Resolve widget names for TopWidgets to be consistent with when it
		// is requested individually
		String topWidgets = (String)metrics.get(METRIC_TOPWIDGETS);
		if ( topWidgets != null && !topWidgets.isEmpty()) {
			topWidgets = resolveWidgetNames(topWidgets);
			metrics.put(METRIC_TOPWIDGETS, topWidgets);
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "fetchMetrics", metrics);
		
		return metrics;
	}

	public Map<String,Object> fetchMetricsFields(String[] metricNameArr) {
		
        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "fetchMetricsFields", metricNameArr);
        }
        if (metricNameArr == null)
            return null;

        Map<String,Object> htAll = systemMetrics.fetchMetrics();
        Map<String,Object> htRet = new HashMap<String,Object>();
        
        if(logger.isLoggable(Level.FINEST)){
        	// KH: externalized string info.metrics.property.value
        	logger.logp(Level.FINEST, CLASS_NAME, "fetchMetricsFields", "htAll: {0}", htAll);
        }

        // look up the specified metrics
        for (int ix = 0; ix < metricNameArr.length; ix++)  {
            if(logger.isLoggable(Level.FINEST)) {
            	// KH: externalized string info.metrics.property.value
            	logger.logp(Level.FINEST, CLASS_NAME, "fetchMetricsFields", "metricNameArr[ix]: {0}", metricNameArr[ix]);
            }
            
            if (metricNameArr[ix] == null || metricNameArr[ix].length() == 0)
            		continue;

            if(logger.isLoggable(Level.FINEST)){
             	// KH: externalized string info.metrics.property.value
             	logger.logp(Level.FINEST, CLASS_NAME, "fetchMetricsFields", "(Long)htAll.get( metricNameArr[ix]): {0}", htAll.get( metricNameArr[ix]));
            }
         
            Object valueObj = htAll.get( metricNameArr[ix]);
            if(metricNameArr[ix].equalsIgnoreCase(METRIC_TOPWIDGETS)){
            	valueObj = resolveWidgetNames((String)valueObj);
            }
            if (valueObj != null)
            	htRet.put(metricNameArr[ix], valueObj);
            
        }
        
        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "fetchMetricsFields", htRet);
        }
        return htRet;
	}
	
	/**
	 * Get the widgets from remote service so we can resolve names
	 * @return map of widget text (name in db) to widget title (localised name)
	 */
	private Map<String, String> getWidgetNames() {
		if ( logger.isLoggable(Level.FINER) ) {
			logger.entering(CLASS_NAME, "getWidgetNames");
		}
		
		Map<String, String> widgetNames = new HashMap<String, String>();
		List<Widget> widgets = null;
		
		try {
			if ( this.widgetCatalogService != null ) {
				widgets = this.widgetCatalogService.getAllWidgets(Locale.getDefault());
				for ( Widget widget : widgets ) {
					Widget widgetNonLocalised = this.widgetCatalogService.getWidgetNonLocalized(widget.getWidgetId(), true);
					if ( widgetNonLocalised != null ) {
						widgetNames.put(widgetNonLocalised.getTitle(), widget.getTitle());
					}
				}
			}
		} catch ( Exception e ) {
			if ( logger.isLoggable(Level.FINER) ) {
				logger.logp(Level.FINER, CLASS_NAME, "getWidgetCatalogService", "Error getting widgets to resolve names for HP metrics.", e);
				// if we fail to get the remote service, or the widgets, we just fall back to widget name keys
			}
		}
		
		if ( logger.isLoggable(Level.FINER) ) {
			logger.exiting(CLASS_NAME, "getWidgetNames", widgetNames);
		}
		
		return widgetNames;
	}
	
	// 
	/**
	 * Where possible, replace widget name keys with actual widget names
	 * @param initialStr the value we got from the database
	 * @return value with widget names resolved where possble
	 */
	private String resolveWidgetNames(String initialStr) {
		if ( logger.isLoggable(FINER) ) {
			logger.entering(CLASS_NAME, "resolveWidgetNames", initialStr);
		}
		
		Map<String, String> widgetNames = getWidgetNames();
		
		StringBuffer newStr = new StringBuffer();
		
		String[] widgetMetrics = initialStr.split(";");
		for ( String widgetMetric : widgetMetrics ) {
			widgetMetric = widgetMetric.trim();
			if ( widgetMetric != null && !widgetMetric.isEmpty() && widgetMetric.contains(":")) {
				String[] vals = widgetMetric.split(":");

				if (vals.length > 1) {
					String name = widgetNames.get(vals[0]);
					if (name == null || name.isEmpty()) {
						name = vals[0];
					}

					newStr.append(name);
					newStr.append(":");
					newStr.append(vals[1]);
					newStr.append(";");
				}
			}
		}
		
		if ( logger.isLoggable(FINER) ) {
			logger.entering(CLASS_NAME, "resolveWidgetNames", newStr.toString());
		}
		
		return newStr.toString();
	}

	public String saveMetricToFile(String absoluteFilename,	Integer sampleCount, String fieldKeyArg) {
        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "saveMetricToFile", new Object[]{absoluteFilename, sampleCount, fieldKeyArg});
        }
    	
        String separator = ",";  // was an arg
        
        if(logger.isLoggable(Level.FINEST)) {
        	// KH: externalized string info.metrics.separator
         	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricToFile", "Separator: {0}", separator);
        }

        ArrayList<String> fieldKeys = new ArrayList<String>();
        String retStr = OKSTR;

        toEnd:        
        do {

        // check for reset timeout
        if (fieldKeyArg.equals("metrics.cache.timeout.in.minutes"))
        {
            if(logger.isLoggable(Level.FINEST)){
            	// KH: externalized string info.metrics.setting.timeout
             	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricToFile", "Setting timeout: ", separator);
            }

            int newCacheTimeout = sampleCount.intValue();

            if (systemMetrics.setCacheTimeoutMinutes( newCacheTimeout))
            	// KH: externalized string info.metrics.ok.cache.timeout
                retStr = OKSTR + ": cache timeout changed";
            else
            	// KH: externalized string error.metrics.cache.timeout
                retStr = ERRORSTR + ": cache timeout invalid (< 0 or > 1440 minutes)";

            break toEnd;
        }

        // check for all requested
        if (fieldKeyArg.equalsIgnoreCase("all"))
        {
            String[] appKeyArray = systemMetrics.getMetricKeyNameArray();
            for (int ix = 0; ix < appKeyArray.length; ix++)
            {
                fieldKeys.add(appKeyArray[ix]);
            }
            retStr = saveMetricsToFile( absoluteFilename, sampleCount, fieldKeys);
            break toEnd;  // not strictly necessary
        }
        else
        {
            fieldKeys.add( fieldKeyArg);
            retStr = saveMetricsToFile( absoluteFilename, sampleCount, fieldKeys);
            break toEnd;  // not strictly necessary
        }

        } while (false);
     
        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "saveMetricToFile", retStr);
        }
        return retStr;

	}

	 public java.lang.String saveMetricsToFile( String absoluteFilename, Integer sampleCount, ArrayList<String> fieldKeys) {
        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "saveMetricsToFile", new Object[]{absoluteFilename, sampleCount, fieldKeys});
        }
        String separator = ",";  // was an arg

        if(logger.isLoggable(Level.FINEST)){
         	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Separator: {0}", separator);
        }

        String retStr = OKSTR;

        Map<String,Vector<Object>> hTable = new Hashtable<String,Vector<Object>>();
        Object[] requestKeyArray = fieldKeys.toArray();
        String fieldKey = null;
        boolean bFileAlreadyExists = false;

        toEnd:        
        do {
            // verify that 'sampleCount' is reasonable
            if (sampleCount < 1 || sampleCount > 1000) {
                if(logger.isLoggable(Level.FINEST)){
                	// KH: externalized string error.metrics.bad.sample.count
                 	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Error: sampleCount bad");
                }
            	// KH: externalized string error.metrics.bad.sample.count
                retStr = ERRORSTR + ": sample count bad";
                break toEnd;
            }

            // verify separator is a single char (always true since separator is no longer an
            // argument.
            if (separator.length() != 1)  {
                if(logger.isLoggable(Level.FINEST)){
                	// KH: externalized string error.metrics.bad.separator
                 	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Error: separator bad");
                }
            	// KH: externalized string error.metrics.bad.separator
                retStr = ERRORSTR + ": separator bad, must be length 1";
                break toEnd;
            }

            // validate the keys
            String[] appKeyArray = systemMetrics.getMetricKeyNameArray();
            for (int iy = 0; iy < requestKeyArray.length; iy++) {
                // make sure the object is a string
                if (!(requestKeyArray[iy] instanceof String))  {
                	// KH: externalized string error.metrics.field.key
                    retStr = ERRORSTR + ": field key is not a String";
                    break toEnd;
                }
                fieldKey = (String)requestKeyArray[iy]; 

                // verify that fieldKey is a valid key
                boolean bFoundKey = false;

                for (int ix = 0; ix < appKeyArray.length; ix++) {
                    if (appKeyArray[ix].equals(fieldKey))
                    {
                        // fieldKey is ok
                        bFoundKey = true;
                        break;
                    }
                }
                                                              
                if (! bFoundKey) {
                    if(logger.isLoggable(Level.FINEST)) {
                    	// KH: externalized string error.metrics.field.key.notfound
                     	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Error: fieldKey bad: "+fieldKey);
                    }
                	// KH: externalized string error.metrics.field.key.notfound
                    retStr = ERRORSTR + ": fieldKey bad";
                    break toEnd;
                }
            }

            // 'msf' is MetricsSaveFile
            File msf = new File( absoluteFilename);

            if(logger.isLoggable(Level.FINEST)){
            	// KH: externalized string info.metrics.dir
             	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Test: does file (or dir) exist?");
            }

            if (msf.exists()) {
                if(logger.isLoggable(Level.FINEST)){
                	// KH: externalized string info.metrics.dir.ok
                 	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Ok: file (or dir) exists");
                }
                if (msf.isFile()) {
                    if(logger.isLoggable(Level.FINEST)) {
                    	// KH: externalized string info.metrics.file.ok
                     	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Ok, it is a file (not dir)");
                    }
                    if (msf.canRead() && msf.canWrite()) {
                        if(logger.isLoggable(Level.FINEST)) {
                        	// KH: externalized string info.metrics.file.ok.read
                         	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Ok, can read");
                        }
                        bFileAlreadyExists = true;
                    }
                    else  {
                        if(logger.isLoggable(Level.FINEST)) {
                        	// KH: externalized string error.metrics.file.read
                         	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Error: can't read/write file");
                        }	
                        // KH: externalized string error.metrics.file.read
                        retStr = ERRORSTR + ": can't read/write file";
                        break toEnd;
                    }
                }
                else  {
                    if(logger.isLoggable(Level.FINEST)){
                    	// KH: externalized string error.metrics.notfile
                     	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Error: given directory, not file");
                    }	
                	// KH: externalized string error.metrics.notfile
                    retStr = ERRORSTR + ": given directory, not file";
                    break toEnd;
                }
            }
            else  {
                if(logger.isLoggable(Level.FINEST)){
                	// KH: externalized string error.metrics.file.notexisting
                 	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "File (or dir) does NOT exist");
                }	
                try {
                    // create the file
                    if (msf.createNewFile()) {
                        if(logger.isLoggable(Level.FINEST)){
                        	// KH: externalized string info.metrics.file.success
                         	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "File created successfully");
                        }	

                        //retStr = "ok: file created";
                    }
                    else  {
                        if(logger.isLoggable(Level.FINEST)){
                        	// KH: externalized string error.metrics.file.create
                         	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Error: create failed");
                        }	
                    	
                    	// KH: externalized string error.metrics.file.create
                        retStr = ERRORSTR + ": create failed";
                        break toEnd;
                    }
                }
                catch (IOException e) {
                	// TODO - globalize error message after beta
                	// KH: externalized property error.metrics.statistics.file.creating
                	String message = "Error occurred creating Home Page statistics file";
                    if(logger.isLoggable(Level.SEVERE)){
                    	logger.logp(Level.SEVERE, CLASS_NAME, "saveMetricsToFile", message, e);
                    }
                    retStr = ERRORSTR + ": io error, create file failed";
                    break toEnd;
                }
            }

            if(logger.isLoggable(Level.FINEST)){
            	// KH: externalized string error.metrics.file.already.exists
             	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "bFileAlreadyExists: {0}", bFileAlreadyExists);
            }

            String[] metricNames = systemMetrics.getMetricKeyNameArray();
            Map<String,Object> metricMap = (Map<String,Object>)fetchMetricsFields(metricNames);
        // args are ok, now if file exists, i.e., not just created,
        // must load it (and thus validate it)
        if (bFileAlreadyExists)  {
            StringBuffer strBuf = new StringBuffer();
            hTable = parseFile( msf, separator, strBuf);
            if (hTable == null)  {
                retStr = strBuf.toString();
                break toEnd;
            }

            // get date vector
            Vector<Object> vect = (Vector<Object>)hTable.get(DATESSTR);
            // add the current date/time to the date vector
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            StringBuffer dateStr = new StringBuffer();
            sdf.format(new Date(), dateStr, new FieldPosition(0));
            
            if(logger.isLoggable(Level.FINEST)){
            	// KH; externalized string info.metrics.property.value
             	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "dateStr: {0}", dateStr);
            }
            vect.add(dateStr.toString()); 

            // make sure fieldKey is represented in file
            for (int iy = 0; iy < requestKeyArray.length; iy++)
            {
                fieldKey = (String)requestKeyArray[iy]; 

                if (! hTable.containsKey(fieldKey))
                {
                    // fieldKey not there yet
                    Vector<Object> vect1 = new Vector<Object>();
                    for (int ix = 0; ix < vect.size() - 1; ix++)
                    {
                        vect1.add( "0");
                    }
                    hTable.put( fieldKey, vect1);
                }
            }

            Set<String> keySet = hTable.keySet();
            String keyFrFile = null;
            Object newMetricValue = null;

            Iterator<String> it1 = keySet.iterator();
            for (; it1.hasNext();)
            {
                // get the next metric key (in file)
                keyFrFile = (String)it1.next();
                if (keyFrFile.equals(DATESSTR)) continue;  // skip date vector here
                
                if(logger.isLoggable(Level.FINEST)){
                	// KH; externalized string info.metrics.property.value
                 	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "keyFrFile: {0}", keyFrFile);
                }
                // get the vector associated with key
                vect = (Vector<Object>)hTable.get(keyFrFile);
                // add metric value
                newMetricValue = (Object)metricMap.get(keyFrFile);
                // check for key invalid.
                if (newMetricValue == null)
                {
                    retStr = ERRORSTR + ": bad key in file";
                    break toEnd;
                }
                vect.add( newMetricValue);
            }

            it1 = keySet.iterator();
            for (; it1.hasNext();)
            {
                keyFrFile = (String)it1.next();
                vect = (Vector<Object>)hTable.get(keyFrFile);

                do {  // loop until under sampleCount
                if (vect.size() > sampleCount)
                {
                    if(logger.isLoggable(Level.FINEST)){
                    	// KH; externalized string info.metrics.property.value
                     	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Removing: vect.size: {0} sampleCount: {1}", new Object[]{vect.size(), sampleCount});
                    }
                    vect.remove(0);
                }
                else
                    break;
                } while(true);
            }
        }
        else
        {
            // seed the hash table
            Vector<Object> vect = new Vector<Object>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            StringBuffer dateStr = new StringBuffer();
            sdf.format(new Date(), dateStr, new FieldPosition(0));
            
            if(logger.isLoggable(Level.FINEST)){
            	// KH; externalized string info.metrics.property.value
             	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "dateStr: {0}", dateStr);
            }
            vect.add(dateStr.toString()); 

            hTable.put( DATESSTR, vect);

            // add keys
            for (int iy = 0; iy < requestKeyArray.length; iy++)
            {
                fieldKey = (String)requestKeyArray[iy]; 

                vect = new Vector<Object>();
                vect.add( (Object)metricMap.get(fieldKey));
                hTable.put( fieldKey, vect);
            }

            if(logger.isLoggable(Level.FINEST)){
            	// KH; externalized string info.metrics.hash.seeded
             	logger.logp(Level.FINEST, CLASS_NAME, "saveMetricsToFile", "Hash table is seeded");
            }
        }

        String newMetrics = metricsToString( hTable, separator);

        // write to file
        BufferedWriter output = null;
        try {
            output = new BufferedWriter ( new FileWriter( msf)); 
            output.write( newMetrics);
           
        } catch (IOException e) {
        	// TODO - globalize error message after beta
        	// KH: externalized property error.metrics.statistics.file.writing
			String message = "Error occurred when writing Home Page statistics file";
			if(logger.isLoggable(Level.SEVERE)){
				logger.logp(Level.SEVERE, CLASS_NAME, "saveMetricsToFile", message, e);
			}
            retStr = "error: io error, write file failed 1";
            break toEnd;
        }
        finally {
            try {
                if (output != null) output.close();
            } catch (IOException e) {
            	// TODO - globalize error message after beta
            	// KH: externalized property error.metrics.statistics.file.closing
    			String message = "Error occurred when closing Home Page statistics file";
    			if(logger.isLoggable(Level.SEVERE)){
    				logger.logp(Level.SEVERE, CLASS_NAME, "saveMetricsToFile", message, e);
    			}

            	// KH: externalized property error.metrics.statistics.file.closing
                retStr = "error: io error, write file failed 2";
                break toEnd;
            }
        }

        } while (false);
         
        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "saveMetricsToFile", retStr);
        }
        return retStr;
	 }
	
	 /**
     * Parses the file specified assuming CSV( "Comma" Separated Values) format for
     * the statistics. Dates row does have to be there unless file is empty. Returns
     * a Hashtable of Vectors - each vector is a row in the file. Values
     * separated by separator character.
     * 
     * @param fname - filename to parse
     * @return Hashtable of Vectors
     */
    private Map<String,Vector<Object>> parseFile( File msf, String separator, StringBuffer retStrBuf) {
        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "parseFile", new Object[]{msf, separator, retStrBuf});
        }

        Map<String,Vector<Object>> hTable = new Hashtable<String,Vector<Object>>();
        Vector<Object> vector = null;
        BufferedReader input = null;

        try {       
            //File msf = new File( fname);
            
            input = new BufferedReader ( new FileReader( msf));
            String line = null;
            while( (line = input.readLine()) != null)
            {
                vector = new Vector<Object>();
                String[] xStrArr = line.split( separator);
                for(int ix = 1; ix < xStrArr.length; ix++)
                {
                    vector.add(xStrArr[ix]);
                }
                hTable.put(xStrArr[0], vector);
            }
        } catch (IOException e) {
        	// TODO - globalize error message after beta     (Which beta was that?)   
        	// KH: externalized property error.metrics.statistics.file.parsing
			String message = "Error occurred when parsing Home Page statistics file";
			if(logger.isLoggable(Level.SEVERE)){
				logger.logp(Level.SEVERE, CLASS_NAME, "parseFile", message, e);
			}
        	// KH: externalized property error.metrics.statistics.file.parsing
            retStrBuf.append( "error:  io error parsing file");
            hTable = null; 
        }
        finally {
            try {
                if (input!= null) {
                    input.close();
                }
            } catch (IOException e) {
            	// TODO - globalize error message after beta
            	// KH: externalized property error.metrics.statistics.file.closing
    			String message = "Error occurred when closing Home Page statistics file";
    			if(logger.isLoggable(Level.SEVERE)){
    				logger.logp(Level.SEVERE, CLASS_NAME, "parseFile", message, e);
    			}
                retStrBuf.append( "error:  io error parsing file");
                hTable = null; 
            }
        }

        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "parseFile", hTable);
        }
        
        return hTable;
    }
    
    /**
     * Puts given Hashtable in CSV (Comma Separated Values) format
     * 
     * @param h - Hashtable of Vectors of statistics
     * @return
     */
    public String metricsToString(Map<String,Vector<Object>> hTable, String separator) {
        if(logger.isLoggable(Level.FINER)){
        	logger.entering(CLASS_NAME, "metricsToString", new Object[]{hTable, separator});
        }

        StringBuffer sb = new StringBuffer( );
        Set<String> keys = (Set<String>)hTable.keySet();
        
        Object key;
        Vector<Object> vector;
        
        // write dates first
        sb.append( DATESSTR + separator);
        vector = (Vector<Object> ) hTable.get( DATESSTR);
        Enumeration<Object> enumr = vector.elements();
        for (; enumr.hasMoreElements();)
        {
            sb.append(enumr.nextElement() + separator);
        }

        sb.append( System.getProperty("line.separator"));
        
        //now write the metrics
        Iterator<String> iter = keys.iterator();
        for (; iter.hasNext();)
        {
            key = iter.next();

            if ( key.equals(DATESSTR)) continue;

            sb.append(key.toString() + separator);
            vector = (Vector<Object>) hTable.get(key);
            enumr = vector.elements();
            for (; enumr.hasMoreElements();)
            {
                sb.append(enumr.nextElement() + separator);
            }
            sb.append( System.getProperty("line.separator"));
        }
        
        String result = sb.toString();
        if(logger.isLoggable(Level.FINER)){
        	logger.exiting(CLASS_NAME, "metricsToString", result);
        }
        return result;
    }

	public ISystemMetrics getSystemMetrics() {
		return systemMetrics;
	}

	public void setSystemMetrics(ISystemMetrics systemMetrics) {
		this.systemMetrics = systemMetrics;
	}	
}
