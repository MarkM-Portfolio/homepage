/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.caching;

import java.io.Serializable;

public interface IGenericCachingService <K extends Serializable, V extends Serializable> {

	public V retrieveCache(K key);
	public void addCache(K key, V value, boolean canOverride);
	public void removeCache(K key);
	public void clearCache();
}
