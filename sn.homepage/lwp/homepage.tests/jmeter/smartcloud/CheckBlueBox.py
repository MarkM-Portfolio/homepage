# ***************************************************************** 
#                                                                   
# IBM Confidential                                                  
#                                                                   
# OCO Source Materials                                              
#                                                                   
# Copyright IBM Corp. 2015                                          
#                                                                   
# The source code for this program is not published or otherwise    
# divested of its trade secrets, irrespective of what has been      
# deposited with the U.S. Copyright Office.                         
#                                                                   
# ***************************************************************** 

import urllib2
import json
import re
import traceback
import socket

class log:
    @classmethod
    def info(self,msg):
        print msg

class CheckBluebox:

    def __init__(self,userEmail):
        try:
            log.info("checking email for " + userEmail)
            self.inboxJson={}
            self.userEmail=userEmail
            self.blueboxHost="bluebox.lotus.com"
            self.blueboxInbox = 'http://' + self.blueboxHost + '/bluebox/rest/json/inbox/' + userEmail + "/0/"
            self.blueboxMessage = 'http://' + self.blueboxHost + '/bluebox/rest/json/inbox/detail/'
            log.info("-> getting ~" + self.blueboxInbox)
            req = urllib2.urlopen(self.blueboxInbox)
            data=req.read()
            self.inboxJson=json.loads(data, strict=False)
            print self.inboxJson
        except socket.timeout:
            log.info("ERR: Request Timed Out!")
        except Exception as e:
            log.info("ERR: " + repr(e) + " - " + e.message)
        finally:
            try:
                req.close()
            except UnboundLocalError:
                pass
            except Exception as e:
                log.info("ERR:: " + repr(e) + " - " + e.message)

    def __getMessageId(self,fromAddress=None,subjectPattern1=None,subjectPattern2=None):
        infoMsg="message for " + self.userEmail + " from " + fromAddress + " with matches for [" + subjectPattern1 + "]" + " [" + subjectPattern2 + "]"
        messages=[]

        try:
            for msg in self.inboxJson:
                if msg["Sender"][0].find(fromAddress) != -1:
                    if re.search(subjectPattern1,msg["Subject"]) is not None and re.search(subjectPattern2,msg["Subject"]) is not None:
                        messages.append(msg)

            if (len(messages)==1):
                log.info("got " + infoMsg)
                return messages[0]["Uid"]
            elif (len(messages) > 1): #return newest email
                #messages=messages[::-1]
                log.info("got newest " + infoMsg)
                return messages[0]["Uid"]
            else:
                log.info("no " + infoMsg)
        except KeyError:
            pass
        except Exception as e:
            log.info("ERR# " + repr(e) + " - " + e.message)

        return None


    def getMessage(self,fromAddress=None,subjectPattern1=None,subjectPattern2=None):
        msgId = self.__getMessageId(fromAddress,subjectPattern1,subjectPattern2)
        if msgId is None:
            return
        else:
            log.info("msgId returned : " + msgId + " [from:" + fromAddress + "]")

        try:
            url = self.blueboxMessage + msgId
            log.info("-> getting ~" + url)
            req1 = urllib2.urlopen(url)
            data=req1.read()
            msgJson=json.loads(data, strict=False)

            messagePlain=msgJson["TextBody"]
            messageHtml=msgJson["HtmlBody"]
            self.messageContent=messageHtml
            return messageHtml
            # return messagePlain
        except socket.timeout:
            log.info("ERR; Request Timed Out!")
        except Exception as e:
            log.info("ERR; " + repr(e) + " - " + e.message)
        finally:
            try:
                req1.close()
            except UnboundLocalError:
                pass
            except Exception as e:
                log.info("ERR;; " + repr(e) + " - " + e.message)

        return

    def testMessageForStrings(self, csvStrings):
        if (len(csvStrings) == 0):
            return True

        strings=csvStrings.split(",")
        for string in strings:
            log.info("looking for [" + string + "] in message")
            if not string.find(self.messageContent):
                log.info("can not find [" + string + "] in message")
                return False
            else:
                log.info("found [" + string + "] in message")
        return True

#end of class

# def saveMessage(messageText,filename):
#     try:
#         file = open(filename, "w")
#         file.write(messageText.encode('UTF-16'))
#         file.close()
#         print filename + " saved"
#     except Exception as e:
#         print e
#
# def main():
#     mailBox = CheckBluebox(l_user)
#     msg=mailBox.getMessage(l_from,l_mailSubject1,l_mailSubject2)
#     if msg is None:
#         print "no message to save"
#         return
#     else:
#         saveMessage(msg,l_saveFolder + "/" + l_mailFileName)
#         mailBox.testMessageForStrings(l_csvMailBodyStringsToLookFor)
#
#
# # l_saveFolder="${responses.dir}";
# l_saveFolder="/tmp/saved_responses";
# l_mailFileName = "email_intelli_j_run.html";
# l_mailSubject1 = "replied to the ";
# l_mailSubject2 = "144159 JMETER TEST FORUM TOPIC";
# l_user = "bdo1@bluebox.lotus.com";
# #l_from = "${user.mail.from}";
# l_from = "no-reply@basesandbox30.swg.usma.ibm.com";
# # l_csvMailBodyStringsToLookFor = "${user.mail.body.csv.strings}";
#
# # main()
