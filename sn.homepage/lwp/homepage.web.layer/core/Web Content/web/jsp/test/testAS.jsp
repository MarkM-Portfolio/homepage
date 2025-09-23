<%-- ***************************************************************** --%>
<%--                                                                   --%>
<%-- IBM Confidential                                                  --%>
<%--                                                                   --%>
<%-- OCO Source Materials                                              --%>
<%--                                                                   --%>
<%-- Copyright IBM Corp. 2012, 2015                                    --%>
<%--                                                                   --%>
<%-- The source code for this program is not published or otherwise    --%>
<%-- divested of its trade secrets, irrespective of what has been      --%>
<%-- deposited with the U.S. Copyright Office.                         --%>
<%--                                                                   --%>
<%-- ***************************************************************** --%>
<%@ taglib prefix="lc-ui" uri="http://www.ibm.com/lconn/tags/coreuiutil" %> 
<script type="text/javascript">
	dojo.registerModulePath("lconn.homepage.jsp.as.test",lconn.core.url.getServiceUrl(lconn.core.config.services.homepage).uri + "/web/jsp/test");
	dojo.require("lconn.homepage.jsp.as.test.TempleteEntries");
</script>
<lc-ui:dojo include="lconn.homepage.tests.modules.EntryPostScript">
	djConfig.staticRoot="<lc-cache:uri template='{staticRoot}' />";
</lc-ui:dojo>

<div class="lotusMain" style="padding-top:20px">
<div class="lotusContent">
<div class="lotusHeader"> <h1>Activity Stream Entry Post Test</h1>
</div>

<div class="lotusTable">
<form id="inlineForm" class="lotusForm2"  action="javascript:;" aria-live="assertive">
<div class="lotusFormBody">
	<div class="lotusFormField">
		<label for="user"><h3>Current User : </h3></label>
		<span id="user"></span>
	</div>
	
	<div>
		<fieldset class="lotusFieldset" role="radiogroup" style="padding:15px;padding-top:0px">
			<legend><h3>Post API Selection</h3></legend>
			<input type="radio" id="postTypeAS" name="postType" class="lotusCheckbox" checked="checked"  /><label class="lotusCheckbox" for="postTypeAS">Acitivity Stream API</label>
			<input type="radio" id="postTypeMB" name="postType" class="lotusCheckbox" style="margin-left:15px" /><label class="lotusCheckbox" for="postTypeMB">Microbloging API</label>
		</fieldset>
	</div>
	<div id="asDiv">
		<div class="lotusFormField">
			<label for="entryset"><h3>Entry Set : </h3></label>
			<div class="lotusFieldWrapper"><select id="entryset"></select></div>
		</div>
		
		<div id="customHtmlField" class="lotusHidden" style="padding-bottom: 20px;">
			<label>Custom HTML content (placed in content attribute)</label>
			<textarea class="lotusText" style="width: 60%" id="customHtml" rows="5"></textarea>
		</div>
	
		<div class="lotusFormField">
			<!--<textarea id="postData" class="lotusText"  rows=20 cols=150 style="width:800px" ></textarea>-->
			<div id="entryEditBox"></div>
		</div>
	</div>
	<div id="mbDiv" class="lotusHidden">
		<div class="lotusFormField">
			<label for="entryset"><h3>Profile Status : </h3></label>
			<div class="lotusFieldWrapper">
				<textarea class="lotusText" id="mbMessage" name="mbMessage" rows="5" cols="50" style="width:500px"></textarea>
			</div>
		</div>
		
	</div>
	<div class="lotusFieldWrapper">
		<input class="lotusCheckbox" type="checkbox" id="toSave" name="toSave" />
		<label class="lotusCheckbox" for="toSave">Saved</label>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="lotusCheckbox" type="checkbox" id="toAction" name="toAction" />
		<label class="lotusCheckbox" for="toAction">Action Required</label>
	</div>
	
	<div class="lotusFormField">
		<label for="postTimes">Post Times : </label>
		<input class="lotusText" type="text" id="postTimes" value="1" aria-required="true"  style="width: 50px"/>
	</div>
	
	<div class="lotusFormField">
		<input id="postBtn" type="button" value="Post" class="lotusBtn"/>
	</div>
	
	<div class="lotusMessageBody">
		<div id="loderbody" class="lotusHidden"><img id="loadingImg" class="loading" />Loading
		</div>
		<span id="message"></span>
	</div>
</div>
</form>
</div>
</div>

