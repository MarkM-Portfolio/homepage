<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.ibm.lconn.homepage.orgadmin.HighwayAdminRequest"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@page import="java.net.URL"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.ibm.lconn.homepage.orgadmin.CloudPathUtils"%>
<%-- ***************************************************************** --%>
<%--                                                                   --%>
<%-- IBM Confidential                                                  --%>
<%--                                                                   --%>
<%-- OCO Source Materials                                              --%>
<%--                                                                   --%>
<%-- Copyright IBM Corp. 2007, 2016                                    --%>
<%--                                                                   --%>
<%-- The source code for this program is not published or otherwise    --%>
<%-- divested of its trade secrets, irrespective of what has been      --%>
<%-- deposited with the U.S. Copyright Office.                         --%>
<%--                                                                   --%>
<%-- ***************************************************************** --%>

<%
	String stylesheetURL = CloudPathUtils.getCommonCSSURL();
	String navbarURL = CloudPathUtils.getBannerURL(request);
	String leftNavURL = CloudPathUtils.getAdminLeftNavURL();
	String footerURL = CloudPathUtils.getFooterURL();
	
	// and get some strings
	HighwayAdminRequest adminRequest = new HighwayAdminRequest(request,response);
	String sAdmin = adminRequest.getResource("ui.title.admin");
	String sConnAdmin = adminRequest.getResource("ui.title.conn.admin");
	String sConnAdminDesc = adminRequest.getResource("ui.title.conn.admin.desc");
	String sMainNav = adminRequest.getResource("ui.title.navigation");
	String sMainContent = adminRequest.getResource("ui.title.content");
	String sNotOrgadmin = adminRequest.getResource("ui.message.not.orgadmin");

	boolean isOrgAdmin = request.isUserInRole("org-admin");
	if (!isOrgAdmin) {
%>
	<b><%=sNotOrgadmin %></b>
<%
	} else {
%>

<head>
	<title><%=sConnAdmin %></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" href="orgadmin.css" rel="stylesheet" />
	<link type="text/css" href="<%=stylesheetURL%>" rel="stylesheet" />
	<script type="text/javascript" src="orgadmin.js"></script>
	<script type="text/javascript">
          function setDir() {
              if (typeof(jsonData.bannernext.lang)) {
                  var lang = jsonData.bannernext.lang.substring(0,2);
                  if (lang == "ar" || lang == "he" || lang == "iw") {
                       document.dir = "rtl";
                       document.body.dir = "rtl";

                       var link = document.createElement("link");
                       link.href = "orgadminRTL.css";
                       link.type = "text/css";
                       link.rel = "stylesheet";
                       document.getElementsByTagName("head")[0].appendChild(link);
                  }
              }
          }
          // focusTracker used in orgAdmin.js in order to maintain focus on highway element after changes saved.
          var focusTracker;
          <%
          String focusTrackerVal = request.getParameter("focusTracker");
          if(focusTrackerVal !=null){
    	   %>
 		   focusTracker = "<%=focusTrackerVal %>";    
  		  <%};%>
	</script>
</head>

<body class="tundra lotusliveui lotusliveui_gecko" dir="ltr" onload="setDir()">
	
	<div class="lotusFrame">

		<script src="<%=navbarURL.toString()%>" type="text/javascript"></script>

		<div class="lotusTitleBar" aria-labelledby="adminTitleBarLabel" role="banner"><div class="lotusWrapper"><div class="lotusInner">
			<div class="lotusTitleBarContent"> 
				<h2 id="adminTitleBarLabel" class="lotusHeading"><%=sAdmin %></h2>
			</div>
		</div></div></div>		

		<div class="lotusMain">
			<div class="lotusColLeft" aria-label="<%=sMainNav %>" role="navigation">
				<script type="text/javascript" src="<%=leftNavURL %>"></script>
			</div>

			<div class="lotusContent" aria-label="<%=sMainContent %>" role="main">
				<div class="lotusHeader">		
					<h1 class="lotusHeading"><%=sConnAdmin %></h1>
					<div class="lotusDetails"><%=sConnAdminDesc %></div>
				</div>

				<jsp:include page="settings.jspf" />
			</div>		
		</div>			

		<div class="lotusFooter" role="contentinfo">
			<script src="<%=footerURL%>" language="javascript"  onload="onFooterLoad()"></script>
		</div>
	</div>
		
</body>

<%
	}
%>
	
</html>

