/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2016                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

var originalValues = {};

function onFooterLoad() {
	
	var elementTofocus;
	var leftNavElement = document.getElementById("connections_admin");
	//default focus value
	if(leftNavElement && leftNavElement.firstChild){
		elementToFocus = leftNavElement.firstChild;
	}
	if(focusTracker){ //focusTracker is a global variable initialized in orgadmin.jsp to track focus after changes saved
		elementToFocus = document.getElementById(focusTracker);
		}
	if(elementToFocus){
		elementToFocus.focus();
	}
	showAlerts();
}

function switchToEditIfSpacebar(name, event) {
	if (event.keyCode==32||event.keyCode==13||event.charCode==32||event.charCode==13)
		switchToEdit(name);
}

function submitWithTrackFocus(name){
	var tracker = document.createElement("input");
	tracker.value = name + '_change';
	tracker.type = 'hidden';
	tracker.name = 'focusTracker';
	document.getElementById(name + '_form').insertBefore(tracker,document.getElementById(name + '_form').childNodes[1]);
	document.getElementById(name + '_form').submit();
}

function switchToEdit(name) {
	var value = document.getElementById(name+'_value').value;
	originalValues[name] = value;
	
	document.getElementById(name+'_show').style.display = 'none';
	document.getElementById(name+'_show').setAttribute('aria-hidden', 'true');
	
	document.getElementById(name+'_edit').style.display = 'block';
	document.getElementById(name+'_edit').setAttribute('aria-hidden', 'false');
	
	document.getElementById(name+'_value').focus();
}

function switchToShow(name) {
	
	document.getElementById(name+'_edit').style.display = 'none';
	document.getElementById(name+'_edit').setAttribute('aria-hidden', 'true');
	
	document.getElementById(name+'_show').style.display = 'block';
	document.getElementById(name+'_show').setAttribute('aria-hidden', 'false');
	if(originalValues[name]){
		document.getElementById(name+'_value').value = originalValues[name];
	}
	
	document.getElementById(name+'_change').focus();
}

function showAlerts() {
	
	// we need to re-add the alert items for the reader to pick up on them
	alertElements = document.getElementsByClassName("lotusAlert");
	for(var i = 0; i < alertElements.length; i++)
	{
		var alertElement = alertElements.item(i);
		var newAlertElement = alertElement.cloneNode(true);
		newAlertElement.style.display = 'block';
		newAlertElement.setAttribute('role', 'alert');
		newAlertElement.setAttribute('aria-hidden', 'false');
		
		// get the parent
		var parent = alertElement.parentElement;
		parent.removeChild(alertElement);
		parent.appendChild(newAlertElement);
	}
}

