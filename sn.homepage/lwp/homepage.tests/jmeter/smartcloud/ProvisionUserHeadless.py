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

from SmartCloudRequest import SmartCloudRequest,SmartCloudUrls as urls
from BeautifulSoup import BeautifulSoup
import re
import requests
from urlparse import urlparse, parse_qs
import json
import time
import sys
import string
import random

class CheckMailinator():

    def __init__(self,userEmail):
        self.userEmail=userEmail
        self.mailinatorInbox='https://api.mailinator.com/api/inbox'
        self.mailinatorMessage='https://api.mailinator.com/api/email'
        self.mailinatorToken='0e09a9096e8340a7927e31d7b26fe220'
        payload = {'token': self.mailinatorToken, 'to': userEmail}
        reqInbox = requests.get(self.mailinatorInbox,params=payload)
        if reqInbox.status_code == 400:
            print "No inbox found for ",userEmail
            self.inboxJson=dict(messages=[])
            self.inbox=False
        else:
            self.inboxJson=json.loads(reqInbox.text)
            self.inbox=True


    def getRegistrationToken(self,subjectPattern):
        token="-1"
        if self.inbox == False:
            return token
        print "mailinator: " + self.userEmail + " : looking for subject " + subjectPattern
        for msg in self.inboxJson["messages"][::-1]:
            if re.search(subjectPattern,msg["subject"]) is not None:
                msgId=msg["id"]
                payload = {'token': self.mailinatorToken, 'msgid': msgId}
                req = requests.get(self.mailinatorMessage,params=payload)
                data = json.loads(req.text)
                body_html = data["data"]["parts"][1]["body"]
                soup = BeautifulSoup(body_html)
                emailRegAddress = soup.findAll('a')[0].get('href')
                parsed = urlparse(emailRegAddress)
                params = parse_qs(parsed.query)
                token=params["token"][0]
                break

        return token

class CheckBluebox():

    def __init__(self,userEmail):
        self.userEmail=userEmail
        self.blueboxHost="bluebox.lotus.com"
        self.blueboxInbox = 'http://' + self.blueboxHost + '/bluebox/rest/json/inbox/' + userEmail + "/0/"
        self.blueboxMessage = 'http://' + self.blueboxHost + '/bluebox/rest/json/inbox/detail/'
        self.blueboxMessageLinks = "http://" + self.blueboxHost + "/rest/json/messageutils/" #[Uid]+/links
        reqInbox = requests.get(self.blueboxInbox)
        self.inboxJson=json.loads(reqInbox.text)
        if len(self.inboxJson) == 0:
            print "empty inbox found for ",userEmail
            self.inbox=False
        else:
            self.inbox=True

    def getRegistrationToken(self,subjectPattern):
        token="-1"
        if self.inbox == False:
            return token

        print "bluebox: " + self.userEmail + " : looking for subject " + subjectPattern
        for msg in self.inboxJson[::-1]:
            if re.search(subjectPattern,msg["Subject"]) is not None:
                msgId=msg["Uid"]
                req = requests.get(self.blueboxMessage + msgId)
                # req = requests.get(self.blueboxMessageLinks + msgId + "/links")
                data = json.loads(req.text)
                body_html = data["HtmlBody"]
                soup = BeautifulSoup(body_html)
                emailRegAddress = soup.findAll('a')[0].get('href')
                print emailRegAddress
                parsed = urlparse(emailRegAddress)
                params = parse_qs(parsed.query)
                token=params["token"][0]
                break

        return token

class ProvisionUser():

    def __init__(self, host,user,pwd):
        self.sc=SmartCloudRequest(host,user,pwd)
        self.listOrgs()
        self.users={}

    def listOrgs(self,orgName=""):
        try:
            self.orgs={}
            if self.sc.isLoggedOn:
                payload = dict(pageNum='1',
                                pageSize='100',
                                queryField='manageaccount.tableheader.org',
                                queryValue=orgName,
                                queryValueText=orgName + '%',
                                sortAttribute='customer.organization.orgName',
                                sortDirection='ASC')
                req=self.sc.post('manage/account/csgList/input',payload)
                soup=BeautifulSoup(req.text)
                linkdivs=soup.findAll('div',{'class':'link_outer'})
                regex='showUserAccounts.customerId=(.*?)\''
                list=[]
                for div in linkdivs:
                    link=div.find('a')
                    list.append(link.text)
                    ref=re.search(regex,link["onclick"])
                    self.orgs[link.text]=ref.group(1)
                print "Orgs :: ", self.orgs
            else:
                print "Not Logged On"
                print "Check that you have authenticated through Firewall"
        except Exception as e:
            print e

    def listUsers(self,orgName):
        self.users={}
        if not self.hasOrg(orgName):
            print orgName + " does not exist, returning from listUsers"
            return False

        orgId=self.orgs[orgName]

        payload = dict(pageNum='1',
                       pageSize='100',
                       queryField='NoQuery',
                       queryValue='',
                       queryValueText='',
                       sortAttribute='subscriber.person.displayName',
                       sortDirection='ASC')

        req=self.sc.post('manage/account/csgList/showUserAccounts?customerId=' + orgId,payload)

        soup=BeautifulSoup(req.text)
        linkdivs=soup.findAll('div',{'class':'link_outer'})
        regex='editSubscriber.SubscriberId=(.*?)\''
        for div in linkdivs:
            link=div.find('a')
            ref=re.search(regex,link["onclick"])
            self.users[link.text]=ref.group(1)
        print "Users for  " + orgName + " :: ", self.users
        return orgId

    def printUsers(self,orgName):
        self.listUsers(orgName)
        for user in self.users:
            print user, ' has id ', self.users[user]

        for user in self.users:
            emailAddress = self.getUserEmailAddress(self.users[user])
            print user, ' has email ', emailAddress


    def getUserEmailAddress(self,id):
       token='xx'
       payload = dict(start='1',
                       token=token,
                       entityId=id)

       req=self.sc.get('manage/user/updateUser/basic/showEdit=',payload,None,True,False)

       payload = dict(start='1',
                       token=token,
                       entityId=id)

       req=self.sc.get('manage/user/updateUser/collabSetup/showEdit',payload,None,True,False)
       soup = BeautifulSoup(req.text)
       emailAddress = soup.find(id="existingEmail")
       if emailAddress:
          return emailAddress.text
       else:
          emailAddress = soup.find(id="primaryEmailId")
          if emailAddress:
              return emailAddress.get('value')
          else:
              return "email not found"


    def getSubscriptionId(self,orgId):
        return self.sc.getCloudSubscriptionId(orgId)

    def hasOrg(self,orgName):
        self.listOrgs(orgName)

        if orgName in self.orgs:
            return True
        else:
            return False

    def hasUser(self,userName):
        if userName in self.users:
            return True
        else:
            return False

    def addOrg(self,orgName,fname,lname,email):
        if self.hasOrg(orgName):
            print orgName + ' already exists, returning from addOrg'
            return False

        r=self.sc.get(urls.showNewPPAAccount)
        csrfToken=self.sc.getHiddenInputValue(r.text,"csrfToken")
        r=self.sc.get(urls.checkUserExistenceAcrossDataCentres + email)

        payload = dict(SiteNumber="siteNum12",
                       autoAcceptTOU='Yes',
                       countryHiddenValue='US',
                       csrfToken=csrfToken,
                       customerId='',
                       dataCenter='US Data Center',
                       emailAddress=email,
                       icNumber="icNum12",
                       origEmailId=email,
                       renewSubscriptionId='null',
                       salesOrganization='0412',
                       stateHiddenValue='')
        payload["address.addressLine1"]='1 Norfolk Way'
        payload["address.addressLine2"]='Up the road'
        payload["address.city"]='Norwich'
        payload["address.countryCode"]='GB'
        payload["address.postalCode"]=''
        payload["address.state"]=''
        payload["customer.customerType"]='ENTERPRISE',
        payload["customer.organization.federationTypeAsString"]='NON_FEDERATED',
        payload["customer.organization.firstContact.familyName"]=lname,
        payload["customer.organization.firstContact.givenName"]=fname,
        payload["customer.organization.firstContact.workPhone"]="12345678",
        payload["customer.organization.orgName"]=orgName,

        r=self.sc.get(urls.addCustomerData,payload)
        r=self.sc.get(urls.showCreateNewOrder)

        payload = dict(csrfToken=csrfToken,
                       customerId='',
                       orderNumber='12345678',
                       renewSubscriptionId='null',
                       soldBy='Salesperson@mailinator.com')
        r=self.sc.get(urls.addOrderDetails,payload)

        payload = dict(csrfToken=csrfToken,
                       customerId='',
                       itemIdToEdit='',
                       renewSubscriptionId='null',
                       businessCode='PA',
                       orderType='NEW',
                       lineItem='1',
                       subscriptionId='',
                       partNumber="D09TILL",
                       quantity='99',
                       termDurationLength='3',
                       termDurationUnits='Year%28s%29',
                       chargeType='PAID',
                       billingFrequency='Annual+Recurring+Charge+%28ARC%29',
                       renewalOption='Manual')
        r=self.sc.get(urls.addPartDetails,payload)

        r=self.sc.get(urls.showReviewDetails)

        payload = dict(csrfToken=csrfToken,
                       customerId='',
                       stateHiddenValue='',
                       countryHiddenValue='GB',
                       numberOfOrderItems='1')
        r=self.sc.get(urls.submitOrder,payload)
        self.listOrgs()

    def register(self,orgName,fname,lname,email,pattern):
        time.sleep(10)

        if email.find("mailinator") != -1:
            mail = CheckMailinator(email)
        else:
            mail = CheckBluebox(email)

        if mail.inbox == False:
            print "no inbox - can not register, returning..."
            return False

        regToken=mail.getRegistrationToken(pattern)

        if regToken == '-1':
            print "could not get token"
            return False

        print "registration token from email is ", regToken

        payload = dict(confirmpassword='Pa88w0rd',
                       countryCode='GB',
                       emailaddress=email,
                       fullname=fname + " " + lname,
                       languageCode='en_US',
                       orgname=orgName,
                       password='Pa88w0rd',
                       shouldValidate='true',
                       timeZoneCode='Africa/Bamako',
                       token=regToken)
        r=self.sc.post(urls.registerOrder,payload)
        print r.status_code," - registered using token from ",email

    def addUser(self,orgName,fname,lname,email):
        orgId=self.listUsers(orgName)

        if orgId is False:
            print 'orgName does not exist, returning from addUser'
            return False

        if self.hasUser(fname + ' ' + lname):
            print fname + ' ' + lname + ' already exists, returning from addUser'
            return False

        req=self.sc.get('manage/account/csgList/input')
        req=self.sc.get('manage/account/csgList/showUserAccounts?customerId=' + orgId)

        req=self.sc.get('manage/subscribers/addSubscriber?customerId=' + orgId)
        csrfToken=self.sc.getHiddenInputValue(req.text,"csrfToken")
        token=self.sc.getHiddenInputValue(req.text,"token")

        payload = dict(department='',
                       entityId='',
                       firstName=fname,
                       language='en_US',
                       lastName=lname,
                       roles='User,AppDeveloper,ContentValidator',
                       targetPage='',
                       token=token,
                       csrfToken=csrfToken,
                       visitedPage='1')

        req=self.sc.post('manage/user/addUser/basic/submit',payload)
        token=self.sc.getHiddenInputValue(req.text,"token")

        req=self.sc.get('manage/user/addUser/subscription/input?visitedPage=2&token=' +token)
        csrfToken=self.sc.getHiddenInputValue(req.text,"csrfToken")
        token=self.sc.getHiddenInputValue(req.text,"token")
        origEmailId=self.sc.getHiddenInputValue(req.text,"origEmailId")

        cloudSubscriptionId=self.getSubscriptionId(orgId)

        payload = dict(assignTo='',
                       collabSubscriptionId=cloudSubscriptionId,
                       csrfToken=csrfToken,
                       origEmailId=origEmailId,
                       subscriptionType='on',
                       targetPage='',
                       token=token,
                       visitedPage='2')
        req=self.sc.post('manage/user/addUser/subscription/submit',payload,None)
        csrfToken=self.sc.getHiddenInputValue(req.text,"csrfToken")
        token=self.sc.getHiddenInputValue(req.text,"token")

        payload = dict(csrfToken=csrfToken,
                       invitationText='A new account is ready for you.',
                       isCancelEmailChange='false',
                       primaryEmail=email,
                       targetPage='',
                       token=token,
                       visitedPage='3')
        req=self.sc.post('manage/user/addUser/collabSetup/submit',payload,None)
        ret = re.findall('The following user account was created',req.text)

        if len(ret) is not 0:
            print ret,email
            self.listUsers(orgName)
            return True
        else:
            print "failed to add ",email
            return False

########################################################################################################################
data={}

if len(sys.argv) == 5:
    data["host"]=sys.argv[1]
    data["user"]=sys.argv[2]
    data["password"]=sys.argv[3]
    data["orgName"]=sys.argv[4].split(",")
    provision=ProvisionUser(data["host"],data["user"],data["password"])
    for org in data["orgName"]:
        print "looking for users for org ", org
        provision.printUsers(org)
    sys.exit()

if len(sys.argv) !=3 :
    print "script requires property file argument"
    sys.exit(-1)

data["propertyFile"]=sys.argv[1]
registerUsersOnly=sys.argv[2] == 'true'

try:
    fh=open(data["propertyFile"],"r")
    for line in fh:
        prop=line.strip("\n").split("=")
        data[prop[0]]=prop[1]
except Exception as err:
    print err
    sys.exit(-1)

print data

provision=ProvisionUser(data["server.name"],data["sc.user.admin.name"],data["sc.user.admin.pwd"])

if not registerUsersOnly:
    provision.addOrg(data["sc.org.name"],'Will','DoeAdmin', "Admin_" + data["user_1"])
    provision.addOrg(data["sc.org.ext.name"],'Will','DoeAdmin',"Admin_" + data["user_5"])

    if provision.hasOrg(data["sc.org.name"]):
        provision.register(data["sc.org.name"],'Will','DoeAdmin',"Admin_" + data["user_1"],data["email.add.org.reg.subj.pattern"])
    else:
        print "adding internal org, returning because there is no org",data["sc.org.name"]

    if provision.hasOrg(data["sc.org.ext.name"]):
        provision.register(data["sc.org.ext.name"],'Will','DoeAdmin',"Admin_" + data["user_5"],data["email.add.org.reg.subj.pattern"])
    else:
        print "registering external org, returning because there is no org",data["sc.org.ext.name"]


rndstr=''.join(random.choice(string.ascii_lowercase + string.digits) for _ in range(5))

if provision.hasOrg(data["sc.org.name"]):
    if not registerUsersOnly:
        provision.addUser(data["sc.org.name"],'Will',rndstr + "1",data["user_1"])
        provision.addUser(data["sc.org.name"],'Will',rndstr + "2",data["user_2"])
        provision.addUser(data["sc.org.name"],'Will',rndstr + "3",data["user_3"])

    provision.register(data["sc.org.name"],'Will',rndstr + "1",data["user_1"],data["email.add.user.reg.subj.pattern"])
    provision.register(data["sc.org.name"],'Will',rndstr + "2",data["user_2"],data["email.add.user.reg.subj.pattern"])
    provision.register(data["sc.org.name"],'Will',rndstr + "3",data["user_3"],data["email.add.user.reg.subj.pattern"])
else:
    print "adding internal users, returning because there is no org",data["sc.org.name"]

if provision.hasOrg(data["sc.org.ext.name"]):
    if not registerUsersOnly:
        provision.addUser(data["sc.org.ext.name"],'Bill',rndstr + "ext1",data["user_5"])
    provision.register(data["sc.org.ext.name"],'Bill',rndstr + "ext1",data["user_5"],data["email.add.user.reg.subj.pattern"])
else:
    print "adding external user, returning because there is no org",data["sc.org.ext.name"]

print "FINISHED"