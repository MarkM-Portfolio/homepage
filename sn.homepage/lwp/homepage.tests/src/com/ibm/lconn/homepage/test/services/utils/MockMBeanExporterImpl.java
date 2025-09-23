/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2012, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services.utils;

import static java.util.logging.Level.FINER;

import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.logging.Logger;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.services.mbean.admin.MBeanExporter;
import com.ibm.lconn.homepage.services.utils.WASAdminService;

/**
 * Mock class used instead of MBeanExporterImpl in the test environment.
 * 
 * The MBeanExporterImpl class uses WebSphere resources that are unavailable
 * at test time to obtain the MBeanServer. This class uses the ManagementFactory
 * to obtain an MBeanServer.
 * 
 * @author Jim Antill
 *
 */
public class MockMBeanExporterImpl implements MBeanExporter {

	private final static String CLASS_NAME = MockMBeanExporterImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private Map<String, Object> beansToExport;
	@Autowired
	private WASAdminService wasAdminService;
	
	public void init() throws Exception{
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "init");
		if(beansToExport!=null){
			for(Map.Entry<String, Object> entry:beansToExport.entrySet()){
				exportBean(entry.getKey(),entry.getValue());
			}
		}
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "init");
	}
	
	public void destroy() throws Exception{
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "destroy");
		if(beansToExport!=null){
			for(Map.Entry<String, Object> entry:beansToExport.entrySet()){
				unregisterMBean(entry.getKey());
			}
		}
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "destroy");
	}

	public void exportBean(String shortBeanName, Object beanObject) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "exportBean","shortBeanName:"+shortBeanName);
		ObjectName mbeanName=buildMbeanName(shortBeanName);
		MBeanServer mbeanServer=retriveMBeanServer();
        if (mbeanServer.isRegistered(mbeanName))
        {
        	mbeanServer.unregisterMBean(mbeanName);
        }
        
        mbeanServer.registerMBean(beanObject, mbeanName);
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "exportBean");
	}

	private void unregisterMBean(String shortBeanName) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "unregisterMBean","shortBeanName:"+shortBeanName);
		ObjectName mbeanName=buildMbeanName(shortBeanName);
		MBeanServer mbeanServer=retriveMBeanServer();
        if (mbeanServer.isRegistered(mbeanName))
        {
        	mbeanServer.unregisterMBean(mbeanName);
        }
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "unregisterMBean");
	}

    
    private MBeanServer retriveMBeanServer()  {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "retriveMBeanServer");
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "retriveMBeanServer");
        return mbs;
    }

	private ObjectName buildMbeanName(String shortBeanName) throws Exception {
		StringBuffer mbeanNameSB = new StringBuffer();
		mbeanNameSB.append(wasAdminService.getDefaultDomain());
		mbeanNameSB.append(":name=" + shortBeanName);
		mbeanNameSB.append(",cell=" + wasAdminService.getCellName());
		mbeanNameSB.append(",type=LotusConnections,node=" + wasAdminService.getNodeName());
		mbeanNameSB.append(",process=" + wasAdminService.getProcessName());
		return new ObjectName(mbeanNameSB.toString());
	}

	public void setBeansToExport(Map<String, Object> beansToExport) {
		this.beansToExport = beansToExport;
	}

	public void setWasAdminService(WASAdminService wasAdminService) {
		this.wasAdminService = wasAdminService;
	}
	
	public Map<String, Object> getBeansToExport(){
		return this.beansToExport;
	}
}