# ***************************************************************** 
#                                                                   
# IBM Confidential                                                  
#                                                                   
# OCO Source Materials                                              
#                                                                   
# Copyright IBM Corp. 2008, 2013                                    
#                                                                   
# The source code for this program is not published or otherwise    
# divested of its trade secrets, irrespective of what has been      
# deposited with the U.S. Copyright Office.                         
#                                                                   
# ***************************************************************** 

#  This is:  bvt_communities_createCommunity.py
#
#  Jython Script 
# AUTHOR:           liamwals@ie.ibm.com
# INITIAL RELEASE:  LC3.6
# TEST TARGETS:     Run the SearchService indexNow command

import sys, java
sys.path.append('C:\\windows\\system32\\python26.zip')
sys.path.append('C:\\Python26\\DLLs')
sys.path.append('C:\\Python26\\Lib')
sys.path.append('C:\\Python26\\Lib\\plat-win')
sys.path.append('C:\\Python26\\Lib\\lib-tk')
sys.path.append('C:\\Python26\\')
sys.path.append('C:\\Python26\\Lib\\site-packages')
sys.path.append('C:\\Python26\\Lib\\')
sys.path.append('C:\\MyThinclient\lcadminScripts\\')
sys.path.append('C:\\MyThinclient\lcadminScripts\\common\\')
sys.path.append('C:\\MyThinclient\lcadminScripts\\communities\\')
import traceback

from types import *
from java.util import Vector
from java.lang import Boolean
from java.util import Hashtable
from java.text import DateFormat
from java.util import Date
from java.lang import RuntimeException
from java import io
import os

sys.path.append('C:\\MyThinclient\\optionalLibraries\\jython\\Lib\\')

#print sys.path

execfile('C:\\MyThinclient\\optionalLibraries\\jython\\Lib\\searchAdmin.py') # only do this once

def IndexIt():
   #print ""
   #print "--------------------------------------------------------------------------------"
   #print " BVT Test Script for fetchAllComm MBean command."
   #print "--------------------------------------------------------------------------------"
   
   currentDate = Date()
   print currentDate
   print ""

   print "indexNow"
   
   indexCommCmd1 = "index1 = SearchService.indexNow('communities')"   
   #print "Now fetching all communities using the command  " + fetchCommCmd1   
   exec indexCommCmd1
   
   if index1.isEmpty():
       print 'FAIL FAIL ---- on indexing all communities.'
   
   print ""
   return

#=========================================================
print "INDEX_START"
#-------------------------------------------------
# Main
#-------------------------------------------------
try:
    IndexIt()
except:
      print '*****************************************************************'
      print 'FAIL FAIL FAIL FAIL FAIL FAIL FAIL FAIL FAIL FAIL FAIL FAIL FAIL'
      print '*****************************************************************'
else:
      print '*****************************************************************'
      print 'PASS PASS PASS PASS PASS PASS PASS PASS PASS PASS PASS PASS PASS'
      print '*****************************************************************'      
      print ""
      
print "INDEX_END"