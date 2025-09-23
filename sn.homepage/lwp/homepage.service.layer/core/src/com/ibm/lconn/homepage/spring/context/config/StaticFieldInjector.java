/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.spring.context.config;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class StaticFieldInjector  implements BeanNameAware, BeanClassLoaderAware, InitializingBean, DisposableBean {

	private String staticField;

	private Object fieldValue;

	private String beanName;

	private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();


	public void afterPropertiesSet() throws Exception {
		if (staticField == null) {
			staticField = beanName;
		}

		setStaticFieldValue(fieldValue);
	}


	@SuppressWarnings("unchecked")
	private void setStaticFieldValue(Object value) throws ClassNotFoundException, LinkageError,
			IllegalAccessException, InvocationTargetException,
			NoSuchFieldException {
		// Try to parse static field into class and field.
		int lastDotIndex = this.staticField.lastIndexOf('.');
		if (lastDotIndex == -1 || lastDotIndex == this.staticField.length()) {
			throw new IllegalArgumentException(
					"staticField must be a fully qualified class plus filed name: " +
					"e.g. 'example.MyExampleClass.MY_EXAMPLE_FIELD'");
		}
		String className = this.staticField.substring(0, lastDotIndex);
		String fieldName = this.staticField.substring(lastDotIndex + 1);
			
		Class targetClass = ClassUtils.forName(className, this.beanClassLoader);
		Method targetSetter=findSetter(targetClass,fieldName);
		if(targetSetter!=null){
			targetSetter.invoke(null, value);
		}else{
			Field targetField = targetClass.getField(fieldName);
			targetField.set(null, value);
		}
	}


	public void destroy() throws Exception {
		fieldValue=null;
		setStaticFieldValue(null);
	}


	@SuppressWarnings("unchecked")
	private Method findSetter(Class targetClass, String fieldName) {
		String setterName="set";
		setterName+=fieldName.substring(0,1).toUpperCase();
		if(fieldName.length()>1){
			setterName+=fieldName.substring(1);
		}
		for(Method currentMethod:targetClass.getMethods()){
			if(currentMethod.getName().equals(setterName)){
				return currentMethod;
			}
		}
		return null;
	}


	public void setStaticField(String staticField) {
		this.staticField = StringUtils.trimAllWhitespace(staticField);
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public void setBeanName(String beanName) {
		this.beanName = StringUtils.trimAllWhitespace(BeanFactoryUtils.originalBeanName(beanName));
	}

	public void setBeanClassLoader(ClassLoader classLoader) {
		this.beanClassLoader = classLoader;
	}




}
