<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

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

<%
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%@ page errorPage="/web/jsp/common/errorPage.jsp"%><%@page session="true"
	contentType="text/html; charset=UTF-8" buffer="128kb"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="bidi" uri="http://www.ibm.com/lconn/tags/bidiutil"%>
<%@ taglib prefix="lc-ui" uri="http://www.ibm.com/lconn/tags/coreuiutil"%>

<%@page import="java.util.ResourceBundle"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="<%= request.getLocale().getLanguage() %>" xml:lang="<%= request.getLocale().getLanguage() %>">
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="private,max-age=0" />
<lc-ui:favicon appname="homepage" />

<title><fmt:message key="jsp.homepage.title" /></title>

<jsp:include page='../common/defaultCSS.jspf' flush="false" />

<jsp:include page='init/bootStrapJsWidgetPage.jspf' flush="false" />

<!--  replace -->
<jsp:include page='../common/iWidget/xmlFormater.jspf' flush="false" />


<jsp:include page='../common/customHeaders.jspf' flush="false" />

</head>
<body class="lotusui lotusSpritesOn">

<jsp:include page='../common/printDebugMode.jspf' flush="false" />

<div id="lotusFrame" class="lotusFrame">

<jsp:include page='../common/pageHeader.jspf' flush="false" />
<jsp:include page='../common/menuBar.jspf' flush="false" />

<div id="lotusTitleBar" class="lotusTitleBar"><span style="position: absolute;">
	<a name="tabNavigation" id="tabNavigation">&nbsp;</a> </span>
	<div class="lotusRightCorner">
		<div class="lotusInner">
		<%
			request.setAttribute("selected", "widgets");
		%>
			<jsp:include page='../common/tabs.jspf' flush="false" />
			<jsp:include page='../common/search.jspf' flush="true" />
		</div>
	</div>
</div>

<jsp:include page="../common/placeBar.jspf">
	<fmt:message key="jsp.homepage.lotusBar.widgets" var="lotusBarWidgets" />
	<jsp:param name="placeName" value="${lotusBarWidgets}"/>
</jsp:include>

<div id="lotusMain" class="lotusMain myPage"><span style="position: absolute;"> <a
	name="mainContent" id="maincontent">&nbsp;</a> </span>

<jsp:include
	page="../common/palette/palette.jspf" flush="true"></jsp:include> 
	
<div id="lotusContent" class="lotusContent" style="padding:5px 0 20px 5px;">
<table id="dndtable" class="lotusWidgetTable"
	summary="<fmt:message key="jsp.main.summary.table.layout" />">
	<%-- Displaying the main content for the home page --%>
	<tr>
		<td id="dnd" class="lotus2Col">
		<span style="position: absolute;"> <a name="firstColumn"
			id="firstColumn">&nbsp;</a> </span></td>
		<td id="dnd2" class="lotus2Col">
		<span style="position: absolute;"> <a name="secondColumn"
			id="secondColumn">&nbsp;</a> </span></td>
	</tr>
</table>
<%-- end content --%></div>

<%-- end main --%></div>
<jsp:include page='../common/footer.jspf' flush="false" />
</div>
</body>
</html>
