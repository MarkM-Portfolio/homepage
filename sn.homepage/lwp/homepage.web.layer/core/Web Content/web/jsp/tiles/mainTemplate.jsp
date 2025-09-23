<%-- ***************************************************************** --%>
<%--                                                                   --%>
<%-- HCL Confidential                                                  --%>
<%--                                                                   --%>
<%-- OCO Source Materials                                              --%>
<%--                                                                   --%>
<%-- Copyright HCL Technologies Limited. 2008, 2021                    --%>
<%--                                                                   --%>
<%-- The source code for this program is not published or otherwise    --%>
<%-- divested of its trade secrets, irrespective of what has been      --%>
<%-- deposited with the U.S. Copyright Office.                         --%>
<%--                                                                   --%>
<%-- ***************************************************************** --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%-- Templates used for main tabs (updates and widget pages) --%>

<%@page session="true" contentType="text/html; charset=UTF-8"
	buffer="128kb"%>
	
<jsp:include page="../common/homepageAuth.jspf"/>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="bidi" uri="http://www.ibm.com/lconn/tags/bidiutil"%>

<c:if test="${isPerson}">
<%@page import="java.util.ResourceBundle"%>

<bidi:direction />

<c:set var="dir" value="ltr"/>

<c:if test="${bidi}">
	<c:set var="dir" value="rtl"/>
</c:if>

<%
String localeIdBCP = request.getLocale().toString();
localeIdBCP = localeIdBCP.toLowerCase();
localeIdBCP = localeIdBCP.replace("_","-");
%>

<html xmlns="http://www.w3.org/1999/xhtml"
	dir="${dir}"
	lang="<%=localeIdBCP%>"
	xml:lang="<%=localeIdBCP%>">
<head>

<script type="text/javascript">
	(function() {
		// Set up the hp_console_debug logger.
		var s = window.location.search;
		var enableLogging = s && ( 
				s.indexOf("debug=dojo") != -1 || 
				s.indexOf("debug=true") != -1 ||
				s.indexOf("debug-hp") );
		if ( enableLogging ) {
			
			// backup version for IE & Safari
			var backupVer = function() {
				var msg = "", i;
				for ( i = 0; i < arguments.length; i++ ) {
					msg += arguments[i] + " ";
				}
				window.console.log(msg);
			};
	
			if ( window.console && window.console.debug ) {
				try {
					// Safari do not like assigning console.debug to something
					// Will throw exception
					window.hp_console_debug = window.console.debug;
					window.hp_console_debug("Create hp_console_debug()");
				} catch ( e ) {
					// fall back to more basic version
					window.hp_console_debug = backupVer;
				}
			} else { // ie does not have console.debug
				window.hp_console_debug = backupVer;
			}
		} else {
			window.hp_console_debug = function() {};
		}
	})();
</script>


<tiles:insertAttribute name="headSection" />

</head>

<%-- Optional CSS class that can be added on body elm. Required by OneUI CSS (about page)--%>
<tiles:importAttribute name="CSSBodyClass" toName="cssClass" ignore="true" />

<body class="lotusui lotusui30dojo lotusui30_body lotusui30_fonts lotusui30 ${cssClass} lotusSpritesOn">
<div id="lotusFrame" class="lotusui30_layout lotusFrame">

<div role="banner">
<tiles:insertAttribute name="header" /> 
</div>

<tiles:insertAttribute name="main" />
<tiles:insertAttribute name="footer" />
</div>
</body>
</html>
</c:if>
