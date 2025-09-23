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

package com.ibm.lconn.homepage.web.servlet;

import static java.util.logging.Level.FINER;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.lconn.homepage.model.Component;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.ServiceException;

public class SearchProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 3353766753308479025L;
	
	private final static String CLASS_NAME = SearchProxyServlet.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Autowired
	private IConfigurationService configurationService;
	
	
	protected void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	protected IConfigurationService getConfigurationService() {
		return configurationService;
	}
	
	public void init() throws ServletException {
		
		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.entering(CLASS_NAME, "init");
		
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		setConfigurationService((IConfigurationService)context.getBean("configurationService"));
		
		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.exiting(CLASS_NAME, "init");
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "doGet", new Object[] { req, resp });
		}
		String proxyUrl = "";
		
		if(getConfigurationService()==null) {
			resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		} else {
			try {
				if(getConfigurationService().isComponentInstalled(Component.SEARCH.getName())) {
					String searchUrl = getConfigurationService().getComponentUrl(Component.SEARCH.getName(), req.isSecure());
					String uri = req.getRequestURI().replaceFirst(req.getContextPath(), "");
					String queryString = req.getQueryString()==null ? "" : req.getQueryString();
					
					if(!queryString.equalsIgnoreCase(""))
						proxyUrl = searchUrl + uri + "?" + req.getQueryString();
					else
						proxyUrl = searchUrl + uri;
					
					processProxyUrl(proxyUrl, req, resp);
					
				} else {
					resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
				}
				
			} catch (ServiceException e) {
				resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
			} catch (ProxyException pe) {
				resp.sendError(pe.getErrorCode(), pe.getMessage());
			}
			
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "doGet", proxyUrl);
		}
	}
	
	private void processProxyUrl(String url, HttpServletRequest req, HttpServletResponse resp) throws ProxyException{
		URLConnection con = null;
	
		try {
			URL proxyUrl = new URL(url);			
			con = proxyUrl.openConnection();
			con.setDoInput(true);
			con.setUseCaches(false);
			copyRequestHeaders(req, con, false);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        String contentType = con.getContentType();
    
        if(contentType != null)
            resp.setContentType(contentType);
        else
        	contentType = "";
        
        String contentEncoding = con.getContentEncoding();
        
        rewriteHeaders(con, resp);
     
        if(contentEncoding == null)
        	contentEncoding = "";
        
        Object obj2;
        
        BufferedOutputStream bufferedoutputstream;
        try
        {
            if(contentEncoding.indexOf("gzip") >= 0)
                obj2 = new GZIPInputStream(con.getInputStream());
            else
                obj2 = new BufferedInputStream(con.getInputStream());
            bufferedoutputstream = new BufferedOutputStream(resp.getOutputStream());
            int i;
            while((i = ((InputStream) (obj2)).read()) >= 0) 
                bufferedoutputstream.write(i);
        }
        catch(Exception ex) {
        	throw new ProxyException(getResponseCode(con), generateExceptionMessage(url, ex));
        }
        try
        {
            if(obj2 != null)
                ((InputStream) (obj2)).close();
            if(bufferedoutputstream != null)
            {
                bufferedoutputstream.flush();
                bufferedoutputstream.close();
            }
        }
        catch(Exception ex) {
        	throw new ProxyException(getResponseCode(con), generateExceptionMessage(url, ex));
        }
		
	}

	protected URL getURL(HttpServletRequest request) throws IOException {
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if (queryString != null && queryString.length() > 0) {
			url = url + "?" + queryString;
		}
		return new URL(url);
	}
	
	protected void copyRequestHeaders(HttpServletRequest request, URLConnection con, boolean isPost) throws IOException {
		Enumeration en = request.getHeaderNames();
		while(en.hasMoreElements()) {
			String key = (String)en.nextElement();
			// Workaround for Java.0 bug
			// Java.0 requires HTTP request header names as CamelCase even RFC2616 defines them case insensitive
			StringBuffer buf = new StringBuffer();
			String[] work = key.split("-");
			for(int i=0; i<work.length; i++) {
				buf.append(work[i].substring(0,1).toUpperCase()+work[i].substring(1));
				if(i<work.length-1)
					buf.append("-");
			}
			key = buf.toString();
			String value = null;
			if(key.equalsIgnoreCase("host")) {
				value = getURL(request).getHost();
			} else {
				value = request.getHeader(key);
			}
			if(key.equalsIgnoreCase("content-length")&&	!isPost) {
				continue;
			}
			con.setRequestProperty(key, value);
		}
	}
	
	private void rewriteHeaders(URLConnection urlconnection, HttpServletResponse httpservletresponse) {
        Map<String, List<String>> map = urlconnection.getHeaderFields();
        if(map != null)
        {
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                String s = iterator.next();
               
                if(s != null && !s.equals("Content-Type") && !checkForContentTypeGzipHeader(urlconnection, s))
                {
                    List<String> list =map.get(s);
                    String s1 = "";
                    for(Iterator<String> iterator1 = list.iterator(); iterator1.hasNext();)
                    {
                        if(s1.length() > 0)
                            s1 = (new StringBuilder()).append(s1).append(",").toString();
                        s1 = (new StringBuilder()).append(s1).append(iterator1.next()).toString();
                    }

                    httpservletresponse.setHeader(s, s1);
                }
            } while(true);
        }
    }
	
	/**
	 * Check the header to see if it is content-encoding and return true if it 
	 * is gzip
	 * @param urlconnection
	 * @return
	 */
	public boolean checkForContentTypeGzipHeader(URLConnection urlconnection, String header){
		boolean result = false;
        if(header != null && header.equalsIgnoreCase("Content-Encoding")){
        	String encoding = urlconnection.getContentEncoding();
        	if(encoding != null && encoding.indexOf("gzip") >= 0){
        		result = true;
        	}
        }
        return result;
	}
	
	protected int getResponseCode(Object con) {
		
		if(con instanceof HttpURLConnection) {
			try {
				return ((HttpURLConnection) con).getResponseCode();
			} catch (IOException e) {
			}
		}
		
		return -1;
	}
	 protected String generateExceptionMessage(String url, Exception exception) {
		 StringBuilder sb = new StringBuilder();
		 return sb.append("Request:").append(url).append("\nException: ").append(exception.getClass().getName()).append(":").append(exception.getMessage()).toString();
	 }
}
