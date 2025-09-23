# ***************************************************************** 
#                                                                   
# IBM Confidential                                                  
#                                                                   
# OCO Source Materials                                              
#                                                                   
# Copyright IBM Corp. 2007, 2015                                    
#                                                                   
# The source code for this program is not published or otherwise    
# divested of its trade secrets, irrespective of what has been      
# deposited with the U.S. Copyright Office.                         
#                                                                   
# ***************************************************************** 

import sys, java
import os
from java.lang.reflect import Array     
from java.lang import Class    
from java.lang import Object  
from java.lang import String                                            
from java.util import UUID
import exceptions
import lotusConnectionsCommonAdmin
from com.ibm.ws.scripting import ScriptingException
from java.lang import Exception
lineSeparator = java.lang.System.getProperty('line.separator')
initializeOk = 1


domainName = AdminControl.getDefaultDomain()
cellNameM = AdminControl.getCell()

#  Check for do not ask for service/node
try:
    batchMode
except NameError:
    batchMode = 0

#	Set default global oraganisation ID	
try:
	orgIdGlobal
except NameError:
	orgIdGlobal = "00000000-0000-0000-0000-000000000000"

try:
    serviceNodeNameHomepage
except NameError:
    serviceNodeNameHomepage = None

if (serviceNodeNameHomepage != None):
	bAskForNodeHomepage = 0
else:
	bAskForNodeHomepage = not batchMode
	
#--------------------------------------------------------------	
def connectToService(service):
	global ServiceName
	if (bAskForNodeHomepage or (serviceNodeNameHomepage == None)):
		svcs=AdminControl.queryNames("*:name=" +service+ ",type=LotusConnections,*").split(lineSeparator)
	else:
		svcs=AdminControl.queryNames("*:name=" +service+ ",type=LotusConnections,node=" + serviceNodeNameHomepage  + ",*").split(lineSeparator)
  
	if len(svcs[0]) == 0:
		initializeOk = 0
		print "No " +service+ " found"
		return
	else:
		if len(svcs)==1:
			retry = 0
			j = 0
		else:  
			i=1
			for s in svcs:
				print "%i: %s" % (i, s)
				i=i+1
			retry=1
	while retry==1:
		if (bAskForNodeHomepage): 
			print "Which service do you want to connect to?"
			response = sys.stdin.readline()
			print ""
		else:
			response = "1"
		try:
			j = int(response) - 1
			tt = svcs[j]
			retry=0
		except:
			print "Invalid selection, specify an index number between 1 and %i." % len(svcs)
		
    
	nodeServer = svcs[j][svcs[j].find("node="):]

	ServiceName = domainName+':name=' +service+ ',type=LotusConnections,cell='+cellNameM+','+nodeServer
  	print "Connecting to : " + ServiceName
  	return ServiceName
	
	#-----------------------------------

def chooseServer():
	global HomepagePersonServiceName
	global HomepageMBeanFacadeName
	global HomepageMetricsServiceName
	global initializeOk
	
	HomepagePersonServiceName = connectToService('HomepagePersonService')
	HomepageMetricsServiceName = connectToService('HomepageMetricsService')
	HomepageMBeanFacadeName = connectToService('HomepageMBeanFacade')
	
	#-----------------------------------
  
chooseServer()
	
#--------------------------------------------------------------
# setOrgId("id of org")
#--------------------------------------------------------------
def setOrgId(orgId):
	global orgIdGlobal
	orgIdGlobal = orgId
	return "Assigned global organization ID: " + orgIdGlobal   

#-----------------------------------------------------------------
# HomepageMetricsServiceMBean  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------
class HomepageMetricsServiceMBean:	
  def fetchMetrics(self, orgId='_default_'):
    parms = []
    signature = []
    svcMsg = HomepageMBeanFacade.invoke('HomepageMetricsService', 'fetchMetrics', parms, signature, orgId)
    print svcMsg
    return
  
  def fetchMetric(self, metricName, orgId='_default_'):
    parms = [metricName]
    signature = ['java.lang.String']
    svcMsg = HomepageMBeanFacade.invoke('HomepageMetricsService', 'fetchMetric', parms, signature, orgId)
    print svcMsg
    return
   
  def saveMetricToFile(self, absFileName, sampleCount, fieldKey, orgId='_default_'):
    parms = [absFileName, sampleCount, fieldKey]
    signature = ['java.lang.String', 'java.lang.Integer', 'java.lang.String']
    svcMsg = HomepageMBeanFacade.invoke('HomepageMetricsService', 'saveMetricToFile', parms, signature, orgId)
    print svcMsg
    return

  def saveMetricsToFile(self, absFileName, sampleCount, fieldKeys, orgId='_default_'):
    parms = [absFileName, sampleCount, fieldKeys]
    signature = ['java.lang.String', 'java.lang.Integer', 'java.util.ArrayList']
    svcMsg = HomepageMBeanFacade.invoke('HomepageMetricsService', 'saveMetricsToFile', parms, signature, orgId)
    print svcMsg
    return

  def fetchMetricsInternalTest(self, orgId='_default_'):
    parms = []
    signature = []
    svcMsg = HomepageMBeanFacade.invoke('HomepageMetricsService', 'fetchMetrics', parms, signature, orgId)
    print svcMsg
    return

#-----------------------------------------------------------------
# HomepageSearchServiceMBean  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------
class HomepageSearchServiceMBean:	
  
  def deleteIndex(self):
    print "The HomepageSearchService.deleteIndex method is deprecated.Please use the SearchService.deleteIndex method instead."	
    return
    
  def indexNow(self, services):
    print "The HomepageSearchService.indexNow method is deprecated.Please use the SearchService.indexNow method instead."
    return 
    
  def refreshTasks(self):
    print "The HomepageSearchService.refreshTasks method is deprecated.Please use the SearchService.refreshTasks method instead."
    return 

#-----------------------------------------------------------------
# HomepagePersonServiceMBean  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------
class HomepagePersonServiceMBean:	
  
  def updateAllMemberIds(self):
    print "The HomepagePersonService.updateAllMemberIds method is deprecated."
    return 
    
  def updateBatchMemberId(self, repository):
    print "The HomepagePersonService.updateBatchMemberId method is deprecated."
    return 
    
  def updateMemberId(self, email):
    print "The HomepagePersonService.updateMemberId method is deprecated."
    return 
    
  def populateLoginNames(self):
    print "The HomepagePersonService.populateLoginNames method is deprecated."
    return 
  
  def updateBatchMemberIdInternalTest(self, repository):
    print "The HomepagePersonService.updateBatchMemberIdInternalTest method is deprecated."
    return 


  def resetWelcomeFlagAllMembers(self, orgId='_default_'):
    parms = []
    signature = []
    svcMsg = HomepageMBeanFacade.invoke('HomepagePersonService', 'resetWelcomeFlagAllMembers', parms, signature, orgId)
    print svcMsg
    return
    
  def resetWelcomeFlagBatchMembersByEmail(self, repository, orgId='_default_'):
    parms = [repository]
    signature = ['java.lang.String']
    svcMsg = HomepageMBeanFacade.invoke('HomepagePersonService', 'resetWelcomeFlagBatchMembersByEmail', parms, signature, orgId)
    print svcMsg
    return

  def resetWelcomeFlagBatchMembersByLoginName(self, repository, orgId='_default_'):
    parms = [repository]
    signature = ['java.lang.String']
    svcMsg = HomepageMBeanFacade.invoke('HomepagePersonService', 'resetWelcomeFlagBatchMembersByLoginName', parms, signature, orgId)
    print svcMsg
    return
    
  def resetWelcomeFlagMemberByEmail(self, email, orgId='_default_'):
    parms = [email]
    signature = ['java.lang.String']
    svcMsg = HomepageMBeanFacade.invoke('HomepagePersonService', 'resetWelcomeFlagMemberByEmail', parms, signature, orgId)
    print svcMsg
    return

  def resetWelcomeFlagMemberByLoginName(self, loginName, orgId='_default_'):
    parms = [loginName]
    signature = ['java.lang.String']
    svcMsg = HomepageMBeanFacade.invoke('HomepagePersonService', 'resetWelcomeFlagMemberByLoginName', parms, signature, orgId)
    print svcMsg
    return

#-----------------------------------------------------------------
# HomepageMemberServiceMBean  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------
class HomepageMemberServiceMBean(lotusConnectionsCommonAdmin.ComponentMemberServiceMBeanBase):
    def __init__(self):
        return
        
    def  syncAllMemberExtIds(self, *args):
        print "The HomepageMemberService.syncAllMemberExtIds method is deprecated. Please use the NewsMemberService.syncAllMemberExtIds method instead."

    def  syncMemberExtIdByLogin(self, *args):
        print "The HomepageMemberService.syncMemberExtIdByLogin method is deprecated. Please use the NewsMemberService.syncMemberExtIdByLogin method instead."

    def  syncBatchMemberExtIdsByLogin(self, *args):
        print "The HomepageMemberService.syncBatchMemberExtIdsByLogin method is deprecated. Please use the NewsMemberService.syncBatchMemberExtIdsByLogin method instead."

    def  syncMemberExtIdByEmail(self, *args):
        print "The HomepageMemberService.syncMemberExtIdByEmail method is deprecated. Please use the NewsMemberService.syncMemberExtIdByEmail method instead."

    def  syncBatchMemberExtIdsByEmail(self, *args):
        print "The HomepageMemberService.syncBatchMemberExtIdsByEmail method is deprecated. Please use the NewsMemberService.syncBatchMemberExtIdsByEmail method instead."

#-----------------------------------------------------------------
# HomepageMigrationServiceMBean  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------
class HomepageMigrationServiceMBean:

	def populateLoginNames(self):
	    print "The HomepageMigrationService.populateLoginNames method is deprecated."
	    return 

#-----------------------------------------------------------------
# HomepageInternalTestClass  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------
class HomepageInternalTestClass:
  def InternalTestAll (self):
    print " "
    #
    metrics=HomepageMetricsService.fetchMetricsInternalTest()
    print "metrics: "
    print metrics
    print " "
    #
    HomepagePersonService.updateBatchMemberIdInternalTest("bogusRepository")
    print "Should get message: CLFRQ0219E: File [bogusRepository] does not exist"
    print " "

#-----------------------------------------------------------------
# HomepageCellConfig  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------

gHomepageCellArgs = None   #Global configuration arguments -- workingDirectory, cellName, nodeName, etc.

class HomepageCellConfigArgs:
	def __init__(self, workingDirectory, cellName, nodeName = None, serverName = None):
		self.workingDirectory = workingDirectory
		assert (workingDirectory), "Missing required argument: workingDirectory must be specified"
		self.cellName = cellName
		assert (cellName), "Missing required argument: cellName must be specified"
		self.nodeName = nodeName
		self.serverName = serverName	
		
	def getRepositoryPath(self):
		#<cell_name>[/nodes/<node_name>[/servers/<server_name>]]
		retVal = self.cellName
				
		if(self.nodeName and len(self.nodeName) > 0):
			retVal = retVal + "/nodes/" + self.nodeName
					
		if(self.serverName and len(self.serverName) > 0):
			retVal = retVal + "/servers/" + self.serverName
					
		return retVal
	
		
  	def print(self):
		print "\tworkingDirectory:", self.workingDirectory
		print "\tcellName:", self.cellName
		print "\tnodeName:", self.nodeName
		print "\tserverName:", self.serverName

#-----------------------------------------------------------------
# HomepageCellConfig  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------
class HomepageCellConfigService:	
	def set_config_args_from_list(self, args):	
		wd = None; cn = None; nn = None; sn = None	
	
		#assert len(args) >= 2, "Missing required arguments: working_directory and cellName must be specified"
		idx = 0
		for s in args:
			if (idx == 0): 
				wd = s
				exists = File(wd).exists()
				if(not exists):
					raise "Working directory does not exist", wd	
			elif (idx == 1):
				cn = s
			elif (idx == 2):
				nn = s
			elif (idx == 3):
				sn = s
			idx = idx + 1
			
		global gHomepageCellArgs
		gHomepageCellArgs = HomepageCellConfigArgs(wd, cn, nn, sn)
		
	def set_config_args(workingDirectory, cellName, nodeName = None, serverName = None):
		global gHomepageCellArgs
		gHomepageCellArgs = HomepageCellConfigArgs(workingDirectory, cellName, nodeName, serverName)
		
	
	def validate_args(self, args):
		global gHomepageCellArgs
		if ( ( (not args) or len(args) < 2)  and (gHomepageCellArgs != None)): 
			print "Using configuration arguments :"; gHomepageCellArgs.print()
		else:
			self.set_config_args_from_list(args)
			
	
	#--------------------------------------------------------------
	# checkOutConfig
	#
	# Arguments
	#     checkOutConfig(working_dir, cellName, nodeName = None , serverName = None)
	#--------------------------------------------------------------
	def checkOutConfig(self, *args):
	    print "The HomepageCellConfig.checkOutCellConfig method is deprecated.Please use the SearchCellConfig.checkOutConfig method instead."


	#--------------------------------------------------------------
	# check_out_connections_proxy_config
	#
	# Arguments
	#     check_out_connections_proxy_config(working_dir, cellName, nodeName = None , serverName = None)
	#--------------------------------------------------------------
	def checkOutProxyConfig(self, *args):
	    try:
	      self.validate_args(args)
	      obj = AdminConfig.extract("cells/" + gHomepageCellArgs.getRepositoryPath() + "/LotusConnections-config/proxy-homepage-config.tpl", gHomepageCellArgs.workingDirectory + "/proxy-homepage-config.tpl")
	      obj = AdminConfig.extract("cells/" + gHomepageCellArgs.cellName + "/LotusConnections-config/proxy-config.xsd", gHomepageCellArgs.workingDirectory + "/proxy-config.xsd")
	      print "Homepage proxy configuration file successfully checked out"	      
	    except:
	       	c, i, tb = sys.exc_info()	    
		print "Exception -", c, i


	#--------------------------------------------------------------
	# checkOutGettingstartedConfig
	#
	# Arguments
	#     checkOutGettingstartedConfig(working_dir, cellName, nodeName = None , serverName = None)
	#--------------------------------------------------------------
	def checkOutGettingstartedConfig(self, *args):
	    try:
	      self.validate_args(args)
	      obj = AdminConfig.extract("cells/" + gHomepageCellArgs.getRepositoryPath() + "/LotusConnections-config/gettingstarted-config.xml", gHomepageCellArgs.workingDirectory + "/gettingstarted-config.xml")
	      obj = AdminConfig.extract("cells/" + gHomepageCellArgs.cellName + "/LotusConnections-config/gettingstarted-config.xsd", gHomepageCellArgs.workingDirectory + "/gettingstarted-config.xsd")
	      print "Homepage Getting Started page configuration file successfully checked out"	      
	    except:
	       	c, i, tb = sys.exc_info()	    
		print "Exception -", c, i


	
	#--------------------------------------------------------------
	# checkInConfig
	#
	# Arguments
	#     checkInConfig(working_dir, cellName, nodeName = None , serverName = None)
	#--------------------------------------------------------------
	def  checkInConfig(self, *args):
	
	    print "The HomepageCellConfig.checkInCellConfig method is deprecated.Please use the SearchCellConfig.checkInConfig method instead."
		
	#--------------------------------------------------------------
	# check_in_connections_proxy_config
	#
	# Arguments
	#     check_out_connections_config(working_dir, cellName, nodeName = None , serverName = None)
	#--------------------------------------------------------------
	def  checkInProxyConfig(self, *args):
	
	   try:
	      self.validate_args(args)
	      configDoc = "cells/" + gHomepageCellArgs.getRepositoryPath() + "/LotusConnections-config/proxy-homepage-config.tpl"
	      newDoc = gHomepageCellArgs.workingDirectory + "/proxy-homepage-config.tpl"
	      validator=HomepageConfigFileValidator(newDoc)
	      validator.validateDoc()

	      if (AdminConfig.existsDocument(configDoc)):
	        digest = AdminConfig.extract(configDoc, gHomepageCellArgs.workingDirectory + "/proxy-homepage-config.tpl_ORIG")
	        AdminConfig.checkin(configDoc, newDoc, digest)
	      else:
	        AdminConfig.createDocument(configDoc, newDoc)

	      print "Homepage proxy configuration file successfully checked in"	      	      
	   except:
	       	c, i, tb = sys.exc_info()	    
		print "Exception -", c, i


	#--------------------------------------------------------------
	# checkInGettingstartedConfig
	#
	# Arguments
	#     checkInGettingstartedConfig(working_dir, cellName, nodeName = None , serverName = None)
	#--------------------------------------------------------------
	def  checkInGettingstartedConfig(self, *args):
	
	   try:
	      self.validate_args(args)
	      configDoc = "cells/" + gHomepageCellArgs.getRepositoryPath() + "/LotusConnections-config/gettingstarted-config.xml"
	      newDoc = gHomepageCellArgs.workingDirectory + "/gettingstarted-config.xml"
	      validator=HomepageConfigFileValidator(newDoc)
	      validator.validateDoc()

	      if (AdminConfig.existsDocument(configDoc)):
	        digest = AdminConfig.extract(configDoc, gHomepageCellArgs.workingDirectory + "/gettingstarted-config.xml_ORIG")
	        AdminConfig.checkin(configDoc, newDoc, digest)
	      else:
	        AdminConfig.createDocument(configDoc, newDoc)

	      print "Homepage Getting Started page configuration file successfully checked in"	      	      
	   except:
	       	c, i, tb = sys.exc_info()	    
		print "Exception -", c, i


	def listTasks(self):
			   
	    print "The HomepageCellConfig.listTasks method is deprecated.Please use the SearchCellConfig.listTasks method instead."
	
	def addTask(self, name, schedule, startbySchedule, services):

	    print "The HomepageCellConfig.addTask method is deprecated.Please use the SearchCellConfig.addTask method instead."
		
	def deleteTask(self, name):

	    print "The HomepageCellConfig.deleteTask method is deprecated.Please use the SearchCellConfig.deleteTask method instead."
		
		
	def setSeedlistPageSize(self, size):

	    print "The HomepageCellConfig.setSeedlistPageSize Method is deprecated.Please use the SearchCellConfig..setSeedlistPageSize Method method instead."

#-----------------------------------------------------------------
# HomepageConfigFileValidator  Class
#-----------------------------------------------------------------
#
#  The purpose of this script is to provide validation for an xml file against 
#  collocated schema file(s)
#	
#  
#  
#
#-----------------------------------------------------------------
import sys, java
import os
from java.lang import System
import exceptions
from com.ibm.ws.scripting import ScriptingException


from java.io import File;
from java.io import FileInputStream;
from java.io import FileNotFoundException;
from java.io import IOException;
from java.io import InputStream;

from javax.xml.parsers import ParserConfigurationException;
from javax.xml.parsers import SAXParser;
from javax.xml.parsers import SAXParserFactory;

from org.xml.sax import Attributes;
from org.xml.sax import EntityResolver;
from org.xml.sax import InputSource;
from org.xml.sax import SAXException;
from org.xml.sax import SAXParseException;
from org.xml.sax import XMLReader;
from org.xml.sax.helpers import DefaultHandler;


class HomepageConfigFileValidator:
	
	def __init__(self, filename):
		self.fileName = filename
		self.valid = 0
		self.debugEnabled = 0
		self.xmlReader = None
		self.sp = None
	
	def logDebug(self, msg):
		print msg
	
	def log(self, msg):
		print msg
	
	def logError(self, msg, e):
		print("Error: " + msg);
		print("Exception: " + e.getMessage());

	
	def validateDoc(self):
		if (self.debugEnabled):
			self.logDebug("validateDoc entry");
	        try:
	            spf = SAXParserFactory.newInstance();
	            spf.setNamespaceAware(1);
	            spf.setValidating(1);
	            spf.setFeature("http://xml.org/sax/features/validation", 1);
	            spf.setFeature("http://apache.org/xml/features/validation/schema", 1);
	            spf.setFeature("http://apache.org/xml/features/validation/schema-full-checking", 1);
	           
	            self.sp = spf.newSAXParser();
	            self.sp.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		
	            self.xmlReader = self.sp.getXMLReader();
	
	            defaultHandler = HomepageDefaultContentHandler(self.debugEnabled);
	            	            	            	            
	            self.xmlReader.setEntityResolver(HomepageSchemaLoader());
	            
	            self.xmlReader.setContentHandler(defaultHandler);

	            self.xmlReader.setErrorHandler(defaultHandler);
	            
	            self.xmlReader.parse(self.fileName);
	            
	            self.log(self.fileName + " is valid");
	                        
	        except SAXException:
	        	raise 
	            
	        except ParserConfigurationException:
			raise

	        if (self.debugEnabled):
			self.logDebug("validateDoc exit");
	

class HomepageDefaultContentHandler (DefaultHandler):
	
	def __init__(self, debugEnabled):
		self.debugEnabled=debugEnabled
	
	def warning(self, SAXParserException_e):
		print SAXParserException_e
		

        def error(self, SAXParseException_e):
        	print "Exception - " , SAXParseException_e
	     	raise SAXParseException_e;


        def fatalError(self, SAXParseException_e):
         	print "Exception - " , SAXParseException_e
           	raise SAXParseException_e;

        
        def startElement(self, s1, s2, s3, a):
        	if (self.debugEnabled):
        		print s1, s2, s3, a
        	

class HomepageSchemaLoader(EntityResolver):
	def __init__(self):
		self.FILE_SCHEME = "file://";
	
	def resolveEntity(self, publicId, systemId):
		
		schemaFileName = systemId[self.FILE_SCHEME.__len__() :  ]
		
		print "Loading schema file for validation:" , schemaFileName
		stream = FileInputStream(schemaFileName)
		
		retVal = InputSource(stream)
		
		return retVal
		
#-----------------------------------------------------------------
# HomepageConfigFileReaderUpdater  Class
#-----------------------------------------------------------------
#
#  The purpose of this script is to provide a class for reading updating the node attributes
#  and values of an xml.
#	
#  
#  
#
#-----------------------------------------------------------------

from java.io import FileInputStream;
from java.io import FileNotFoundException;
from java.io import FileWriter;
from java.io import IOException;
from java.io import InputStream;

from javax.xml.parsers import DocumentBuilder;
from javax.xml.parsers import DocumentBuilderFactory;
from javax.xml.parsers import ParserConfigurationException;
from javax.xml.transform import OutputKeys;
from javax.xml.transform import Transformer;
from javax.xml.transform import TransformerException;
from javax.xml.transform import TransformerFactory;
from javax.xml.transform import TransformerFactoryConfigurationError;
from javax.xml.transform.dom import DOMSource;
from javax.xml.transform.stream import StreamResult;

from org.apache.xpath import CachedXPathAPI;
from org.w3c.dom import Document;
from org.w3c.dom import Element;
from org.w3c.dom import Node;
from org.w3c.dom import NodeList;
from org.xml.sax import SAXException;


class HomepageConfigFileReaderUpdater:
	
	def __init__(self, filename):
		self.fileName = filename;
		self.xpath = CachedXPathAPI()
		self.debugEnabled = 0
		self.dbfactory = DocumentBuilderFactory.newInstance();
		self.dbfactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
							   "http://www.w3.org/2001/XMLSchema");
		self.dbfactory.setIgnoringElementContentWhitespace(1);

		try:
			documentBuilder = self.dbfactory.newDocumentBuilder();
		
			configIS = FileInputStream(self.fileName);

			self.doc = documentBuilder.parse(configIS);

		except ParserConfigurationException, e:
			print e
			raise e
			
		except  FileNotFoundException, e:
			print e
			raise e

		except SAXException, e:
			print e
			raise e
			
		except IOException, e:
			print e
			raise e
	
	def saveDoc(self): 
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		fw =FileWriter(self.fileName);
		result = StreamResult(fw);
		source = DOMSource(self.doc);
		transformer.transform(source, result);
		fw.close();
		
	
	def getNodeProperty(self, xpathStr, attribute):
		config = self.doc.getDocumentElement();
		nl = self.xpath.selectNodeList(config, xpathStr);
		if(nl.getLength() > 0):
			node = nl.item(0);
			attNode = node.getAttributes().getNamedItem(attribute);
			return attNode.getNodeValue()
		
		
	def updateNodeProperty(self, xpathStr, attribute, value):
		config = self.doc.getDocumentElement();
		nl = self.xpath.selectNodeList(config, xpathStr);
		if(nl.getLength() > 0):
			node = nl.item(0);
			attNode = node.getAttributes().getNamedItem(attribute);
			msg = "Changing " + attribute + " from " + attNode.getNodeValue() + " to " + value
			self.log(msg)
			attNode.setNodeValue(value);
		
		return 1;
	
		

			
	def updateNodeValue(self, xpathStr, value):
		config = self.doc.getDocumentElement();
		nl = self.xpath.selectNodeList(config, xpathStr);
		if(nl.getLength() > 0):
			node = nl.item(0);
			parent = node.getParentNode();
			nodeName = None;
			if (parent):
				nodeName = parent.getLocalName();
				
			if(not nodeName):
				nodeName = node.getLocalName()
			else:
				nodeName = nodeName + "." + node.getLocalName()

			node = node.getFirstChild();
			nodeVal = node.getNodeValue();
			msg = "Changing " + nodeName + " from " + nodeVal + " to " + value
			self.log(msg)
			node.setNodeValue(value);
		
		return 1

	
	def logDebug(self, msg):
		print msg
	
	def log(self, msg):
		print msg
	
	def logError(self, msg, exception):
		print "Error: ", msg 
		print "Exception: ", exception
		
#-----------------------------------------------------------------
# HomepageMBeanFacadeMBean  Class
#-----------------------------------------------------------------
#
#	
#  
#  
#
#-----------------------------------------------------------------
class HomepageMBeanFacadeMBean:		

#-----------------------------------------------------------------
# Invokes JMX 'service' 'method' by signature passing along with request context variables (like orgId, sessionId etc.)
# Emulates AdminControl.invoke_jmx(...) on the server side
#-----------------------------------------------------------------			
    def invoke(self, service, method, params, signature, orgId):
        global AdminControl
        import  javax.management  as  mgmt
        objName =  mgmt.ObjectName(HomepageMBeanFacadeName)
	
        if (orgId == '_default_'):
            orgId = orgIdGlobal
	
        inputArgs = Array.newInstance(Object,len(params))
        signatureClasses  = Array.newInstance(Class,len(signature))

        # init method arguments
        for i in range(len(params)):
            inputArgs[i] = params[i];
	
        # init method arguments classes
        for i in range(len(signature)):
            signatureClasses[i] = Class.forName(signature[i]);
	
        parms = [service, method, inputArgs, signatureClasses, orgId]
        signature = ['java.lang.String', 'java.lang.String', '[Ljava.lang.Object;', '[Ljava.lang.Class;', 'java.lang.String']
	
        result = AdminControl.invoke_jmx(objName, 'invoke', parms, signature)
        return result	
        
#-----------------------------------------------------------------

global HomepageMetricsService
global HomepagePersonService
global HomepageMBeanFacade
global HomepageCellConfig

#---Deprecated but used for print commannds to inform the user of the deprecation
global HomepageSearchService
global HomepageMemberService
global HomepageMigrationService

HomepageMetricsService =  HomepageMetricsServiceMBean()
HomepagePersonService = HomepagePersonServiceMBean()
HomepageMBeanFacade = HomepageMBeanFacadeMBean()
HomepageCellConfig = HomepageCellConfigService()

#---Deprecated but used for print commannds to inform the user of the deprecation
HomepageSearchService = HomepageSearchServiceMBean()
HomepageMemberService = HomepageMemberServiceMBean()
HomepageMigrationService = HomepageMigrationServiceMBean()

hits = HomepageInternalTestClass()

if (initializeOk == 1):
	print "HomePage Administration initialized"
