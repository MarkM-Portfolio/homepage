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

class log:
    @classmethod
    def info(self,msg):
     print msg

class CheckMailinator:

    def __init__(self,userEmail):
        try:
            log.info("checking email for " + userEmail)
            self.inboxJson={}
            self.userEmail=userEmail
            self.mailinatorToken='0e09a9096e8340a7927e31d7b26fe220'
            self.mailinatorInbox='https://api.mailinator.com/api/inbox?token=' + self.mailinatorToken + '&to=' + userEmail
            self.mailinatorMessage='https://api.mailinator.com/api/email?token=' + self.mailinatorToken + '&msgid='
            req = urllib2.urlopen(self.mailinatorInbox)
            data=req.read()
            self.inboxJson=json.loads(data)
        except Exception as e:
            log.info("ERR: " + repr(e) + " - " + e.message)
        finally:
            try:
                req.close()
            except Exception as e:
                print "ERR:: " + repr(e)
                log.info("ERR:: " + repr(e) + " - " + e.message)

    def __getMessageId(self,fromAddress=None,subjectPattern1=None,subjectPattern2=None):
        infoMsg="message for " + self.userEmail + " from " + fromAddress + " with matches for [" + subjectPattern1 + "]" + " [" + subjectPattern2 + "]"
        messages=[]

        try:
            for msg in self.inboxJson["messages"][::-1]:
                if msg["fromfull"] == fromAddress:
                    if re.search(subjectPattern1,msg["subject"]) is not None and re.search(subjectPattern2,msg["subject"]) is not None:
                        messages.append(msg)

            if (len(messages)==1):
                log.info("got " + infoMsg)
                return messages[0]["id"]
            elif (len(messages) > 1): #return newest email
                msgs=sorted(messages, key=lambda k: k['seconds_ago'],reverse=False)
                log.info("got newest " + infoMsg)
                return msgs[0]["id"]
            else:
                log.info("no " + infoMsg)
        except Exception as e:
            print "ERR# " + repr(e)
            log.info("ERR# " + repr(e) + " - " + e.message)

        return None

    def getMessage(self,fromAddress=None,subjectPattern1=None,subjectPattern2=None):
        msgId = self.__getMessageId(fromAddress,subjectPattern1,subjectPattern2)
        if msgId is None:
            return
        else:
            print "msgId returned : " + msgId + " [from:" + fromAddress + "]"

        try:
            url = self.mailinatorMessage + msgId
            req = urllib2.urlopen(url)
            raw=req.read()
            msgJson=json.loads(raw, strict=False)

            for part in msgJson["data"]["parts"]:
                contentType=part["headers"]["content-type"]
                body=part["body"]

                if "text/html" in contentType:
                    messageHtml=body

            self.messageContent=messageHtml
            return messageHtml
        except Exception as e:
            log.info("ERR; " + repr(e) + " - " + e.message)
        finally:
            try:
                req.close()
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

def saveMessage(messageText,filename):
    try:
        file = open(filename, "w")
        file.write(messageText.encode('utf8'))
        file.close()
        print filename + " saved"
    except Exception as e:
        print e

def main():
    mailBox = CheckMailinator(l_user)
    msg=mailBox.getMessage(l_from,l_mailSubject1,l_mailSubject2)
    if msg is None:
        print "no message to save"
    else:
        saveMessage(msg,l_saveFolder + "/" + l_mailFileName)


# l_saveFolder="${responses.dir}";
l_saveFolder="/tmp/saved_responses";
l_mailFileName = "email_intelli_j_run.html";
l_mailSubject1 = "replied to the ";
l_mailSubject2 = "144159 JMETER TEST FORUM TOPIC";
l_user = "wdo3@mailinator.com";
#l_from = "${user.mail.from}";
l_from = "no-reply@basesandbox30.swg.usma.ibm.com";
# l_csvMailBodyStringsToLookFor = "${user.mail.body.csv.strings}";

# main()


