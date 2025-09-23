/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright  HCL Technologies Limited. 2008, 2021                   */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.mbean.admin.impl;

import static java.util.logging.Level.FINER;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import static org.springframework.util.ReflectionUtils.invokeMethod;
import javax.management.RuntimeMBeanException;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.services.mbean.admin.MBeanExporter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Facade Service which invokes particular Homepage services using service name
 * and method signature given.
 * 
 * Before doing this though it will process information which is general to all
 * Homepage MBeans.
 * 
 */
public class HomepageMBeanFacade implements HomepageMBeanFacadeMBean {
    private static String CLASS_NAME = HomepageMBeanFacade.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    // DI variables
    @Autowired
    private ResourceBundle serviceResourceBundle;
    @Autowired
    private MBeanExporter mbeanExporter;

    @Override
    public Object invoke(String service, String method, Object[] args, Class<?>[] argTypes) {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "invoke", new Object[] { service, method, argTypes, args });

        Object result = null;
        try {
            logger.info(serviceResourceBundle.getString("info.invocation.start", service, method));

            // create class and object of service given                      
            Object mbeanInstance = this.mbeanExporter.getBeansToExport().get(service);
            if (mbeanInstance == null)
                throw new ClassNotFoundException();

            Class<?> mbeanClass = mbeanInstance.getClass();            
            Method methodToBeCalled = mbeanClass.getMethod(method, argTypes);

            
            // invoke the given service and method correct parameters
            result = isEmpty(argTypes) || isEmpty(args) ? invokeMethod(methodToBeCalled,
                    mbeanInstance) : invokeMethod(methodToBeCalled, mbeanInstance, args);

        } catch (ClassNotFoundException e) {
        	if (logger.isLoggable(FINER)) {
				logger.logp(FINER, CLASS_NAME, method, e.getMessage(), e);
        	}
            throw new RuntimeMBeanException(new RuntimeException(e),
                    serviceResourceBundle.getString("error.service.not.exist", service));
        } catch (NoSuchMethodException e) {
        	if (logger.isLoggable(FINER)) {
				logger.logp(FINER, CLASS_NAME, method, e.getMessage(), e);
        	}
            throw new RuntimeMBeanException(new RuntimeException(e),
                    serviceResourceBundle.getString("error.method.not.found", service, method));
        } finally {
            logger.info(serviceResourceBundle
                    .getString("info.invocation.complete", service, method));
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "invoke", result);

        return result;
    }

    @Override
    public Object invoke(String service, String method, Object[] args, Class<?>[] argTypes,
            String orgId) {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "invoke", new Object[] { service, method, argTypes, args,
                    orgId });

        ApplicationContext.setOrganizationId(orgId);
        Object result = invoke(service, method, args, argTypes);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "invoke", result);

        return result;
    }

    public void setServiceResourceBundle(ResourceBundle serviceResourceBundle) {
        this.serviceResourceBundle = serviceResourceBundle;
    }
    
    public void setMbeanExporter(MBeanExporter mbeanExporter){
    	this.mbeanExporter = mbeanExporter;
    }
}
