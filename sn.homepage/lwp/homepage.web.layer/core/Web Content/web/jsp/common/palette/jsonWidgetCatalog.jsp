<%@page session="true" contentType="application/json; charset=UTF-8"
	buffer="128kb"%>

<%--wscExemptBegin--%>
<%-- ***************************************************************** --%>
<%--                                                                   --%>
<%-- IBM Confidential                                                  --%>
<%--                                                                   --%>
<%-- OCO Source Materials                                              --%>
<%--                                                                   --%>
<%-- Copyright IBM Corp. 2008, 2015                                    --%>
<%--                                                                   --%>
<%-- The source code for this program is not published or otherwise    --%>
<%-- divested of its trade secrets, irrespective of what has been      --%>
<%-- deposited with the U.S. Copyright Office.                         --%>
<%--                                                                   --%>
<%-- ***************************************************************** --%>
<%--wscExemptEnd--%>

<%-- JSP fragment responsible for outputting a JSON string consumed by the palette client-side component
	 Refer to: https://w3.tap.ibm.com/w3ki/display/conndev/Widget+palette+in+2.5 --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jscript"	uri="http://www.ibm.com/dogear/tags/jscript" %>
<%@ taglib prefix="lc-ui" uri="http://www.ibm.com/lconn/tags/coreuiutil" %>
<lc-ui:serviceLink serviceName="webresources" var="urlWebResources" />

{ categories: 
	[ 	
		<%-- Last minutes change to display "all" widget. --%>
		<%-- We build a pseudo category with All the widgets --%>
		{
			id: "All",
			name: <jscript:string><fmt:message key="jsp.palette.category.all"/></jscript:string>,
			widgets: [
				<c:forEach items="${availWidgets}" var="widget" varStatus="widgetStatus">
				{ 
						id: "all_${widget.widgetId}",
						widgetId: "${widget.widgetId}",
						name: <jscript:string>${widget.title}</jscript:string>,
						desc: <jscript:string>${widget.text}</jscript:string>,
						xmlDefUrl: "${widget.url}",	
						multipleInstance: ${widget.multipleInstanceAllowed},					
						widgetType: "primary",
						<c:choose>
							<c:when test="${widget.iconUrl != null && widget.iconUrl != ''}">
								iconUrl: "${widget.iconUrl}"	
							</c:when>
							<c:otherwise>
								iconUrl: "${urlWebResources}/web/com.ibm.lconn.core.styles/images/iconThirdParty16.png"	
							</c:otherwise>						
						</c:choose>
					}
					<c:if test="${!widgetStatus.last}">
						,
					</c:if>		
				</c:forEach>
			]
		}		
		<%-- build the "real" list of categories --%>
		<c:forEach items="${categoriesTree}" var="category" varStatus="catStatus">
		,
		{
			id: "${category.id}",
			name: <jscript:string>${category.name}</jscript:string>,
			widgets: [
				<c:forEach items="${category.widgets}" var="widget" varStatus="widgetStatus">
					{ 
						id: "${widget.widgetId}",
						widgetId: "${widget.widgetId}",
						name: <jscript:string>${widget.title}</jscript:string>,
						desc: <jscript:string>${widget.text}</jscript:string>,
						xmlDefUrl: "${widget.url}",	
						multipleInstance: ${widget.multipleInstanceAllowed},					
						widgetType: "primary",
						<c:choose>
							<c:when test="${widget.iconUrl != null && widget.iconUrl != ''}">
								iconUrl: "${widget.iconUrl}"	
							</c:when>
							<c:otherwise>
								iconUrl: "${urlWebResources}/web/com.ibm.lconn.core.styles/images/iconThirdParty16.png"	
							</c:otherwise>						
						</c:choose>
					}				
					<c:if test="${!widgetStatus.last}">
						,
					</c:if>
				</c:forEach>
			]
		} 
		</c:forEach>		
	]
}
