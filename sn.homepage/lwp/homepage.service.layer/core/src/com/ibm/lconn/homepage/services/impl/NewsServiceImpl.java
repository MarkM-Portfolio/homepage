/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.rmi.RemoteException;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.lconn.homepage.model.SourceType;
import com.ibm.lconn.homepage.services.INewsService;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.utils.NewsBeanFactory;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import com.ibm.lconn.news.ejb.client.NewsStoryEJBBean;
import com.ibm.lconn.news.model.INewsStoryRequestBean;
import com.ibm.lconn.news.model.INewsStoryRequestBean.StoryType;
import com.ibm.lconn.news.model.IWatchlistLeafBean;
import com.ibm.lconn.news.model.IWatchlistNodeBean;
import com.ibm.lconn.news.model.IWatchlistTreeBean;
import com.ibm.lconn.news.model.impl.NewsStoryRequestBean;
import com.ibm.websphere.ce.cm.DuplicateKeyException;

public class NewsServiceImpl implements INewsService {

	private final static String CLASS_NAME = NewsServiceImpl.class.getName();	
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	@Autowired
	private NewsBeanFactory newsBeanFactory;
	
	@Autowired
	private ResourceBundle jspResourceBundle;
	
	private NewsStoryEJBBean getNewStoryEJBBean() {
		return newsBeanFactory.getNewsEjbInstance();
	}
	
	private final static String duplicateKeyErrorCode = "111";

	
	protected INewsStoryRequestBean generateNewsStoryRequestBean(StoryType storyType,
																 Locale locale,
																 int numberStories,
																 boolean isSecure,
																 String readerId) {

		INewsStoryRequestBean request = new NewsStoryRequestBean(storyType, locale);
		request.setPageSize(numberStories);
		request.setReaderId(readerId);
		request.setLocale(locale);
		request.setSecureUrl(isSecure);
		
		return request;
	}
	
	protected INewsStoryRequestBean generateNewsStoryRequestBean(StoryType storyType,
			 													 Locale locale,
			 													 int numberStories,
			 													 boolean isSecure,
			 													 String readerId,
			 													 String source) {
		
		INewsStoryRequestBean request = generateNewsStoryRequestBean(storyType, locale, numberStories, isSecure, readerId);
		if(source!=null)
			request.setSource(source);
		return request;
	}
	
	private JSONObject generateTagsJson(IWatchlistTreeBean bean, Locale locale) {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, 
							"generateTagsJson",
							new Object[] { bean, locale });
		}
		
		JSONObject json = new JSONObject();
		JSONArray items = new JSONArray();

		/*
		JSONObject all = new JSONObject();
		all.put("text", getJspResourceBundle().getString("jsp.palette.category.all", locale));
		all.put("value", "ALL");
		items.add(all);
		*/
		
		Map<String, IWatchlistNodeBean> nodes = bean.getWatchlistNodes();
		
		for(IWatchlistNodeBean node : nodes.values()) {
			
			if(logger.isLoggable(Level.FINEST)) {
				logger.logp(Level.FINEST, CLASS_NAME, "generateTagsJson", ToStringBuilder.reflectionToString(node,
		                ToStringStyle.MULTI_LINE_STYLE));
			}
			
			if(node.getNodeSource().equalsIgnoreCase(SourceType.TAGS.getName())) {
				for(IWatchlistLeafBean leaf : node.getWatchlistLeaves()) {
					
					JSONObject item = new JSONObject();
					item.put("text", StringEscapeUtils.escapeHtml(leaf.getContainerName()));
					item.put("value", leaf.getContainerId());
					
					items.add(item);
					
					if(logger.isLoggable(Level.FINEST)) {
						logger.logp(Level.FINEST, CLASS_NAME, "generateTagsJson", ToStringBuilder.reflectionToString(leaf,
								ToStringStyle.MULTI_LINE_STYLE));
					}
				}
			}
		}
		json.put("identifier", "value");
		json.put("label", "text");
		json.put("items", items);
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, 
							"generateTagsJson",
							new Object[] { json });
		}
		
		return json;
	}
	
	public JSONObject getTagSubscriptions(String personId, Locale locale) throws ServiceException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, 
							"getTagSubscriptions",
							new Object[] { personId, locale });
		}
		
		if(getNewStoryEJBBean() == null) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "getTagSubscriptions", "error handling request for user [" + personId +"] - news ejb bean is null");
			}
			throw generateServiceException(locale);
		}
		
		try {
			IWatchlistTreeBean bean = getNewStoryEJBBean().getWatchlistTree(personId, locale, ApplicationContext.getOrganizationId());
			return generateTagsJson(bean, locale);
		} catch (RemoteException e) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "getTagSubscriptions", "error handling request for user [" + personId +"]", e);
			}
			throw generateServiceException(locale);
		}
		
		finally {
			if (logger.isLoggable(FINER))
				logger.exiting(CLASS_NAME, "getTagSubscriptions");
		}
	}

	public void addTagSubscription(String personId, String tag, Locale locale) throws ServiceException {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, 
							"addTagSubscription",
							new Object[] { personId, tag });
		}
		
		if(getNewStoryEJBBean() == null) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "addTagSubscription", "error handling request for user [" + personId +"] - news ejb bean is null");
			}
			throw generateServiceException(locale);
		}
		
		try {
			
			getNewStoryEJBBean().addTagSubscription(personId, tag, ApplicationContext.getOrganizationId());
		} catch (RemoteException e) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "addTagSubscription", "error handling request for user [" + personId +"]", e);
			}
			try{
				if(e.getCause().getCause().getCause().getCause() instanceof DuplicateKeyException){
					throw generateServiceException(locale, duplicateKeyErrorCode);
				}
			}catch(NullPointerException npe){
				logger.logp(SEVERE, CLASS_NAME, "addTagSubscription", "null pointer expection whilst checking for duplicate tags");
			}
			
			throw generateServiceException(locale);
		}
		finally {
			if (logger.isLoggable(FINER))
				logger.exiting(CLASS_NAME, "addTagSubscription");
		}
	
	}

	public void removeTagSubscription(String personId, String tagId, Locale locale,String tag) throws ServiceException {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, 
							"removeTagSubscription",
							new Object[] { personId, tagId, tag });
		}
		
		if(getNewStoryEJBBean() == null) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "removeTagSubscription", "error handling request for user [" + personId +"] - news ejb bean is null");
			}
			throw generateServiceException(locale);
		}
		
		try {
			getNewStoryEJBBean().removeTagSubscription(personId, SourceType.TAGS.getName(), tagId, tag, ApplicationContext.getOrganizationId());
		} catch (RemoteException e) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "removeTagSubscription", "error handling request for user [" + personId +"]", e);
			}
			throw generateServiceException(locale);
		}
		finally {
			if (logger.isLoggable(FINER))
				logger.exiting(CLASS_NAME, "removeTagSubscription");
		}
		
	}

	public void setJspResourceBundle(ResourceBundle jspResourceBundle) {
		this.jspResourceBundle = jspResourceBundle;
	}

	public ResourceBundle getJspResourceBundle() {
		return jspResourceBundle;
	}

	private ServiceException generateServiceException(Locale locale) {
		return new ServiceException(getJspResourceBundle().getString("jsp.river.error.error",  locale));		
	}
	
	private ServiceException generateServiceException(Locale locale, String errorCode) {
		return new ServiceException(getJspResourceBundle().getString("jsp.river.error.error",  locale), errorCode);		
	}

	public NewsBeanFactory getNewsBeanFactory() {
		return newsBeanFactory;
	}

	public void setNewsBeanFactory(NewsBeanFactory newsBeanFactory) throws ServiceException {
		this.newsBeanFactory = newsBeanFactory;
	}
}
