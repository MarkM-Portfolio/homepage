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

package com.ibm.lconn.homepage.services.caching.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibm.lconn.homepage.services.caching.IGenericCachingService;

public abstract class GenericCachingServiceImpl<K extends Serializable, V extends Serializable> implements IGenericCachingService<K, V> {

	Map <K, V> cache;
	
	public GenericCachingServiceImpl(Class<K> keyType, Class<V> valueType) {
		cache = new HashMap<K, V>();
	}
	
	public V retrieveCache(K key) {
		return cache.get(key);
	}
	
	public void addCache(K key, V value, boolean canOverride) {
		if(!cache.containsKey(key) || (cache.containsKey(key) && canOverride)) {
			cache.put(key, value);		
		}			
	}

	public void removeCache(K key) {
		cache.remove(key);		
	}
	
	public void clearCache() {
		cache.clear();
	}
	
	protected Set<K> getCacheKeySet() {
		return cache.keySet();
	}
	
	protected List<K> getCacheKeyList() {
		List<K> cacheKeyList = new ArrayList<K>();
		Iterator<K> iter = getCacheKeySet().iterator();
		while(iter.hasNext()) {
			cacheKeyList.add(iter.next());
		}
		return cacheKeyList;
	}
}
