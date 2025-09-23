/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.dao.aop;

import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.stereotype.Repository;

/**
 * Pointcut to get advice applied to all methods of DAO
 * 
 * @author BrianOG
 */
public class HomepageDaoObjectMatcher extends AnnotationMatchingPointcut {
	public HomepageDaoObjectMatcher() {
		super(Repository.class,true);
	}

}
