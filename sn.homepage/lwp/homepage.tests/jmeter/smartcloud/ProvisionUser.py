#!/usr/bin/python
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

from Tkinter import *
from ttk import Frame, Button, Style,Entry,Radiobutton,Label,Combobox
import tkFileDialog
import tkMessageBox
import tkSimpleDialog
import sys
from SmartCloudRequest import SmartCloudRequest,SmartCloudUrls as urls
import re
import pickle
from urlparse import urlparse, parse_qs
import json
import requests
from BeautifulSoup import BeautifulSoup
import os

class AddOrganisation(object):

    def __init__(self, parent,hostname,adminUser,adminPwd,defOrgName):
        self.conn = SmartCloudRequest(hostname,adminUser,adminPwd)
        self.parent = parent
        self.hostname=hostname
        self.adminUser=adminUser
        self.adminPwd=adminPwd
        self.defOrgName=defOrgName
        self.datafile='myorgdata.pkl'
        self.users=[]
        self.__initUI__()

    def __initUI__(self):
        ws = self.parent.winfo_screenwidth()
        hs = self.parent.winfo_screenheight()
        w,h = 500 , 250
        x,y = (ws/2) - (w/2), (hs/2) - (h/2)

        modalWindow = Toplevel()
        modalWindow.geometry('%dx%d+%d+%d' % (w, h, x, y))
        modalWindow.resizable(0,0)
        modalWindow.title("Add PPA Organization")
        self.modalWindow=modalWindow
        self.frameDetails = Frame(modalWindow, height=40,relief=RAISED, borderwidth=0)
        self.frameButtons = Frame(modalWindow, height=40,relief=SUNKEN, borderwidth=0)

        self.lblSiteNumber = Label(self.frameDetails, text="Site Number")
        self.lblSiteNumber.grid(row=0,column=0,sticky=W)
        self.strSiteNumber=StringVar()
        self.txtSiteNumber = Entry(self.frameDetails,textvariable=self.strSiteNumber,width=30)
        self.txtSiteNumber.grid(row=0,column=1,sticky=W)
        self.txtSiteNumber.focus_set()

        self.lblIcNumber = Label(self.frameDetails, text="IC Number")
        self.lblIcNumber.grid(row=1,column=0,sticky=W)
        self.strIcNumber=StringVar()
        self.txtIcNumber = Entry(self.frameDetails,textvariable=self.strIcNumber,width=30)
        self.txtIcNumber.grid(row=1,column=1,sticky=W)

        self.lblOrgName = Label(self.frameDetails, text="New Org name")
        self.lblOrgName.grid(row=2,column=0,sticky=W)
        self.strOrgName=StringVar()
        self.txtOrgName= Entry(self.frameDetails,textvariable=self.strOrgName,width=30)
        self.txtOrgName.grid(row=2,column=1,sticky=W)
        self.strOrgName.set(self.defOrgName)
        self.lblFname= Label(self.frameDetails, text="Contact First Name")
        self.lblFname.grid(row=3,column=0,sticky=W)
        self.strFname=StringVar()
        self.txtFname= Entry(self.frameDetails,textvariable=self.strFname,width=30)
        self.txtFname.grid(row=3,column=1,sticky=W)

        self.lblLname= Label(self.frameDetails, text="Contact Last Name")
        self.lblLname.grid(row=4,column=0,sticky=W)
        self.strLname=StringVar()
        self.txtLname= Entry(self.frameDetails,textvariable=self.strLname,width=30)
        self.txtLname.grid(row=4,column=1,sticky=W)

        self.lblEmail= Label(self.frameDetails, text="Contact Email)")
        self.lblEmail.grid(row=5,column=0,sticky=W)
        self.strEmail=StringVar()
        self.txtEmail= Entry(self.frameDetails,textvariable=self.strEmail,width=30)
        self.txtEmail.grid(row=5,column=1,sticky=W)

        self.lblPhone= Label(self.frameDetails, text="Phone")
        self.lblPhone.grid(row=6,column=0,sticky=W)
        self.strPhone=StringVar()
        self.txtPhone= Entry(self.frameDetails,textvariable=self.strPhone,width=30)
        self.txtPhone.grid(row=6,column=1,sticky=W)

        self.lblPartNum= Label(self.frameDetails, text="Part Number")
        self.lblPartNum.grid(row=7,column=0,sticky=W)
        self.strPartNum=StringVar()
        self.cboPartNum = Combobox(self.frameDetails, textvariable=self.strPartNum, width=30)
        self.cboPartNum['values'] = ("D09TGLL","D09TFLL","D09THLL","D09TILL")
        self.cboPartNum.current(0)
        self.cboPartNum.grid(row=7,column=1,sticky=W)

        self.lblRegEmailSubject= Label(self.frameDetails, text="Reg Subject Pattern")
        self.lblRegEmailSubject.grid(row=8,column=0,sticky=W)
        self.strRegEmailSubject = StringVar()
        self.cboRegEmailSubject = Combobox(self.frameDetails, textvariable=self.strRegEmailSubject, width=40)
        self.cboRegEmailSubject['values'] = ("SmartCloud - Your purchase has been confirmed","Connections Cloud - Your purchase has been confirmed")
        self.cboRegEmailSubject.current(0)
        self.cboRegEmailSubject.grid(row=8,column=1,sticky=W)

        self.lblMailType = Label(self.frameDetails, text="Registration Mail type")
        self.lblMailType.grid(row=9,column=0,sticky=W)
        self.optionMailVar=IntVar()
        self.optMailinator = Radiobutton(self.frameDetails ,text="Mailinator", variable=self.optionMailVar, value=1)
        self.optMailinator.grid(row=9,column=1,sticky=W,padx=10)
        self.optBluebox = Radiobutton(self.frameDetails ,text="Bluebox", variable=self.optionMailVar, value=0)
        self.optBluebox.grid(row=9,column=1,sticky=W,padx=100)
        self.optionMailVar.set(0)

        self.btnAdd = Button(self.frameButtons, text="Add", command=self.__addOrg)
        self.btnAdd.grid(row=0,column=0,sticky=E)
        self.btnReg = Button(self.frameButtons, text="Register", command=self.__registerOrg)
        self.btnReg.grid(row=0,column=1,sticky=E)
        self.btnClose= Button(self.frameButtons, text="Close",command=self.__closeClick)
        self.btnClose.grid(row=0,column=2,sticky=E)

        self.frameDetails.pack(fill=X, expand=1)
        self.frameButtons.pack(fill=X, expand=1)

        self.__getFromFile()

        modalWindow.transient(self.parent)
        modalWindow.grab_set()
        self.parent.wait_window(modalWindow)


    def __addOrg(self):
        self.__data__()
        r=self.conn.get(urls.showNewPPAAccount)
        csrfToken=self.conn.getHiddenInputValue(r.text,"csrfToken")

        r=self.conn.get(urls.checkUserExistenceAcrossDataCentres + self.data["email"])

        payload = dict(SiteNumber=self.data["siteNum"],
                       autoAcceptTOU='Yes',
                       countryHiddenValue='US',
                       csrfToken=csrfToken,
                       customerId='',
                       dataCenter='US Data Center',
                       emailAddress=self.data["email"],
                       icNumber=self.data["icNum"],
                       origEmailId=self.data["email"],
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
        payload["customer.organization.firstContact.familyName"]=self.data["lname"],
        payload["customer.organization.firstContact.givenName"]=self.data["fname"],
        payload["customer.organization.firstContact.workPhone"]=self.data["phone"],
        payload["customer.organization.orgName"]=self.data["orgName"],

        r=self.conn.get(urls.addCustomerData,payload)
        r=self.conn.get(urls.showCreateNewOrder)

        payload = dict(csrfToken=csrfToken,
                       customerId='',
                       orderNumber='12345678',
                       renewSubscriptionId='null',
                       soldBy='Salesperson@mailinator.com')
        r=self.conn.get(urls.addOrderDetails,payload)

        payload = dict(csrfToken=csrfToken,
                       customerId='',
                       itemIdToEdit='',
                       renewSubscriptionId='null',
                       businessCode='PA',
                       orderType='NEW',
                       lineItem='1',
                       subscriptionId='',
                       partNumber=self.data["partNum"],
                       quantity='99',
                       termDurationLength='3',
                       termDurationUnits='Year%28s%29',
                       chargeType='PAID',
                       billingFrequency='Annual+Recurring+Charge+%28ARC%29',
                       renewalOption='Manual')
        r=self.conn.get(urls.addPartDetails,payload)

        r=self.conn.get(urls.showReviewDetails)

        payload = dict(csrfToken=csrfToken,
                       customerId='',
                       stateHiddenValue='',
                       countryHiddenValue='GB',
                       numberOfOrderItems='1')
        r=self.conn.get(urls.submitOrder,payload)
        print "add org complete, wait a few seconds and then register " + self.data["email"]
        reply = tkMessageBox.showinfo("Add Org Finished", "Finished adding " + self.data["orgName"], icon='info')

    def __registerOrg(self):
        self.__data__()

        if self.optionMailVar.get() is 1:
            mail = CheckMailinator(self.data["email"])
        else:
            mail = CheckBluebox(self.data["email"])

        regToken=mail.getRegistrationToken(self.strRegEmailSubject.get())

        if regToken == '-1':
            print "could not get token"
            return

        print "registration token from email is ", regToken

        payload = dict(confirmpassword='Pa88w0rd',
                       countryCode='GB',
                       emailaddress=self.data["email"],
                       fullname=self.data["fname"] + " " + self.data["lname"],
                       languageCode='en_US',
                       orgname=self.data["orgName"],
                       password='Pa88w0rd',
                       shouldValidate='true',
                       timeZoneCode='Africa/Bamako',
                       token=regToken)
        r=self.conn.post(urls.registerOrder,payload)
        print r.status_code
        print "registered " + self.data["orgName"] + " using token from " + self.data["email"]
        reply = tkMessageBox.showinfo("Reg Finished", "Finished registering " + self.data["orgName"], icon='info')

    def __closeClick(self):
        self.modalWindow.destroy()

    def __data__(self):
        self.data = dict (host=self.hostname,
                          siteNum=self.strSiteNumber.get(),
                          icNum=self.strIcNumber.get(),
                          orgName=self.strOrgName.get(),
                          adminUser =self.adminUser,
                          adminPwd=self.adminPwd,
                          fname=self.strFname.get(),
                          lname=self.strLname.get(),
                          email=self.strEmail.get(),
                          phone=self.strPhone.get(),
                          partNum=self.strPartNum.get(),
                          mailType=self.optionMailVar.get(),
                          regSubject=self.strRegEmailSubject.get())
        self.__saveToFile()
        return self.data

    def __saveToFile(self):
        try:
            fileout = open(self.datafile, 'wb')
            pickle.dump(self.data, fileout)
            fileout.close()
        except:
            pass

    def __getFromFile(self):
        try:
            fileout = open(self.datafile, 'rb')
            data = pickle.load(fileout)
            self.strSiteNumber.set(data["siteNum"])
            self.strIcNumber.set(data["icNum"])

            if not self.defOrgName:
                self.strOrgName.set(data["orgName"])

            self.strFname.set(data["fname"])
            self.strLname.set(data["lname"])
            self.strEmail.set(data["email"])
            self.strPhone.set(data["phone"])
            self.strPartNum.set(data["partNum"])
            self.optionMailVar.set(data["mailType"])
            self.strRegEmailSubject.set(data["regSubject"])
            fileout.close()
        except Exception, e:
            pass

class AddGuestUser(object):

    def __init__(self, parent,hostname):
        self.parent = parent
        self.hostname=hostname
        self.datafile='sc.guest.data.pkl'
        self.__initUI__()

    def __initUI__(self):
        ws = self.parent.winfo_screenwidth()
        hs = self.parent.winfo_screenheight()
        w,h = 400 , 220
        x,y = (ws/2) - (w/2), (hs/2) - (h/2)

        modalWindow = Toplevel()
        modalWindow.geometry('%dx%d+%d+%d' % (w, h, x, y))
        modalWindow.resizable(0,0)
        modalWindow.title("Add Guest User")
        self.modalWindow=modalWindow
        self.frameDetails = Frame(modalWindow, height=40,relief=RAISED, borderwidth=0)
        self.frameButtons = Frame(modalWindow, height=40,relief=SUNKEN, borderwidth=0)

        self.lblIntUsername = Label(self.frameDetails, text="Internal User")
        self.lblIntUsername.grid(row=0,column=0,sticky=W)
        self.strIntUsername=StringVar()
        self.txtIntUsername = Entry(self.frameDetails,textvariable=self.strIntUsername,width=30)
        self.txtIntUsername.grid(row=0,column=1,sticky=W)
        self.txtIntUsername.focus_set()

        self.lblIntPassword = Label(self.frameDetails, text="Internal User Password")
        self.lblIntPassword.grid(row=1,column=0,sticky=W)
        self.strIntPassword=StringVar()
        self.txtIntPassword= Entry(self.frameDetails,textvariable=self.strIntPassword,width=30)
        self.txtIntPassword.grid(row=1,column=1,sticky=W)

        self.lblExtFname = Label(self.frameDetails, text="External First Name")
        self.lblExtFname.grid(row=2,column=0,sticky=W)
        self.strExtFname=StringVar()
        self.txtExtFname= Entry(self.frameDetails,textvariable=self.strExtFname,width=30)
        self.txtExtFname.grid(row=2,column=1,sticky=W)

        self.lblExtLname= Label(self.frameDetails, text="External Last Name")
        self.lblExtLname.grid(row=3,column=0,sticky=W)
        self.strExtLname=StringVar()
        self.txtExtLname= Entry(self.frameDetails,textvariable=self.strExtLname,width=30)
        self.txtExtLname.grid(row=3,column=1,sticky=W)

        self.lblExtEmail= Label(self.frameDetails, text="External Email")
        self.lblExtEmail.grid(row=4,column=0,sticky=W)
        self.strExtEmail=StringVar()
        self.txtExtEmail= Entry(self.frameDetails,textvariable=self.strExtEmail,width=30)
        self.txtExtEmail.grid(row=4,column=1,sticky=W)

        self.lblRegEmailSubject= Label(self.frameDetails, text="Reg Subject Pattern")
        self.lblRegEmailSubject.grid(row=8,column=0,sticky=W)
        self.strRegEmailSubject = StringVar()
        self.cboRegEmailSubject = Combobox(self.frameDetails, textvariable=self.strRegEmailSubject, width=30)
        self.cboRegEmailSubject['values'] = ("You have been invited to join SmartCloud","You have been added to","Smart Cloud")
        self.cboRegEmailSubject.current(0)
        self.cboRegEmailSubject.grid(row=8,column=1,sticky=W)

        self.lblMailType = Label(self.frameDetails, text="Registration Mail type")
        self.lblMailType.grid(row=5,column=0,sticky=W)
        self.optionMailVar=IntVar()
        self.optMailinator = Radiobutton(self.frameDetails ,text="Mailinator", variable=self.optionMailVar, value=1)
        self.optMailinator.grid(row=5,column=1,sticky=W,padx=10)
        self.optBluebox = Radiobutton(self.frameDetails ,text="Bluebox", variable=self.optionMailVar, value=0)
        self.optBluebox.grid(row=5,column=1,sticky=W,padx=100)
        self.optionMailVar.set(0)

        # self.btnAdd = Button(self.frameButtons, text="Add", command=self.__addGuestUser)
        self.btnAdd = Button(self.frameButtons, text="Add", command=self.__addGuestUser)
        self.btnAdd.grid(row=0,column=0,sticky=E)
        self.btnReg = Button(self.frameButtons, text="Register", command=self.__registerGuestUser)
        self.btnReg.grid(row=0,column=1,sticky=E)
        self.btnListContacts = Button(self.frameButtons, text="List Contacts", command=self.__listContacts)
        self.btnListContacts .grid(row=0,column=2,sticky=E)
        self.btnClose= Button(self.frameButtons, text="Close",command=self.__closeClick)
        self.btnClose.grid(row=0,column=3,sticky=E)

        self.frameDetails.pack(fill=X, expand=1)
        self.frameButtons.pack(fill=X, expand=1)

        self.__getFromFile()

        modalWindow.transient(self.parent)
        modalWindow.grab_set()
        self.parent.wait_window(modalWindow)


    def __addGuestUser(self):
        self.__data__()
        conn = SmartCloudRequest(self.data["host"],self.data["internalUser"],self.data["internalPwd"])
        request=conn.get("/manage/subscribers/showInviteGuestDialog/input")
        csrfToken=conn.getHiddenInputValue(request.text, "csrfToken")
        print csrfToken

        payload = dict(customerId='',
                       csrfToken=csrfToken,
                       givenName=self.data["externalFname"],
                       familyName=self.data["externalLname"],
                       emailId=self.data["externalEmail"],
                       languageCode="en_US",
                       extidContent="A new account is ready for you.")

        reqAdd=conn.get("/manage/subscribers/inviteGuest",payload)
        reply = tkMessageBox.showinfo("Add Guest Finished", "Finished adding " + self.data["externalEmail"], icon='info')

    def __registerGuestUser(self):
        self.__data__()
        conn = SmartCloudRequest(self.data["host"],self.data["internalUser"],self.data["internalPwd"])
        if self.optionMailVar.get() is 1:
            mail = CheckMailinator(self.data["externalEmail"])
        else:
            mail = CheckBluebox(self.data["externalEmail"])

        regToken=mail.getRegistrationToken(self.strRegEmailSubject.get())

        if regToken == '-1':
            print "could not get token"
            return

        print "registration token from email is ", regToken

        payload = {'token':regToken,
                   'fullname':self.data["externalFname"] + "+" + self.data["externalLname"],
                   'emailaddress':self.data["externalEmail"],
                   'shouldValidate':'true',
                   'password':'Pa88w0rd',
                   'confirmpassword':'Pa88w0rd',
                   'countryCode':'IE',
                   'languageCode':'en_US',
                   'timeZoneCode':'Europe%2FDublin',
                   'accept_ToU':'on'}

        reqReg=conn.post("/manage/account/public/registration",payload)

        r=conn.post(urls.registerOrder,payload)
        print r.status_code
        print "registered " + self.data["externalEmail"]
        reply = tkMessageBox.showinfo("Reg Finished", "Finished registering " + self.data["externalEmail"], icon='info')

    def __listContacts(self):
        self.__data__()
        conn = SmartCloudRequest(self.data["host"],self.data["internalUser"],self.data["internalPwd"])

        payload = {'count':'24',
                    'startIndex':'0',
                    'sortOrder':'descending',
                    'filterOp':'contains',
                    'filterBy':'search',
                    'filterValue':'',
                    'type':'all',
                    'includeConnectToStatus':'true',
                    'includeConnectToOrg':'true'}

        req=conn.get("/mycontacts/api/contacts/",payload)
        data = json.loads(req.text)

        print req.text
        for entry in data["entry"]:
            print "Contact " + entry["primaryemail"] + " from orgId " + entry["orgId"]


    def __closeClick(self):
        self.modalWindow.destroy()

    def __data__(self):
        self.data = dict (host=self.hostname,
                          internalUser=self.strIntUsername.get(),
                          internalPwd=self.strIntPassword.get(),
                          externalFname=self.strExtFname.get(),
                          externalLname=self.strExtLname.get(),
                          externalEmail=self.strExtEmail.get(),
                          mailType=self.optionMailVar.get(),
                          regSubject=self.strRegEmailSubject.get())
        self.__saveToFile()
        return self.data

    def __saveToFile(self):
        try:
            fileout = open(self.datafile, 'wb')
            pickle.dump(self.data, fileout)
            fileout.close()
        except:
            pass

    def __getFromFile(self):
        self.strIntUsername.set("wdo1@mailinator.com")
        self.strIntPassword.set("Pa88w0rd")
        self.strExtFname.set("JC")
        self.strExtLname.set("Guest1")
        self.strExtEmail.set("jcguest1@mailinator.com")
        self.optionMailVar.set(1)

        try:
            if os.path.exists(self.datafile):
                fileout = open(self.datafile, 'rb')
                data = pickle.load(fileout)
                self.strIntUsername.set(data["internalUser"])
                self.strIntPassword.set(data["internalPwd"])
                self.strExtFname.set(data["externalFname"])
                self.strExtLname.set(data["externalLname"])
                self.strExtEmail.set(data["externalEmail"])
                self.optionMailVar.set(data["mailType"])
                self.strRegEmailSubject.set(data["regSubject"])
                fileout.close()

        except Exception as e:
            print e

class CheckMailinator(object):

    def __init__(self,userEmail):
        self.userEmail=userEmail
        self.mailinatorInbox='https://api.mailinator.com/api/inbox'
        self.mailinatorMessage='https://api.mailinator.com/api/email'
        self.mailinatorToken='0e09a9096e8340a7927e31d7b26fe220'
        payload = {'token': self.mailinatorToken, 'to': userEmail}
        reqInbox = requests.get(self.mailinatorInbox,params=payload)
        self.inboxJson=json.loads(reqInbox.text)


    def getRegistrationToken(self,subjectPattern):
        token="-1"
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

class CheckBluebox(object):

    def __init__(self,userEmail):
        self.userEmail=userEmail
        self.blueboxHost="bluebox.lotus.com"
        self.blueboxInbox = 'http://' + self.blueboxHost + '/bluebox/rest/json/inbox/' + userEmail + "/0/"
        self.blueboxMessage = 'http://' + self.blueboxHost + '/bluebox/rest/json/inbox/detail/'
        self.blueboxMessageLinks = "http://" + self.blueboxHost + "/rest/json/messageutils/" #[Uid]+/links
        reqInbox = requests.get(self.blueboxInbox)
        self.inboxJson=json.loads(reqInbox.text)

    def getRegistrationToken(self,subjectPattern):
        token="-1"
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

class UserInput(object):
    host='apps.basesandbox36.swg.usma.ibm.com'
    orgName="IBM Org"
    orgId='20000021'
    cloudSubscriptionId=""
    adminUser='bssadmin@us.ibm.com'
    adminPwd='Pa88w0rd'
    fname='Will'
    lname='Do1'
    email='wdo1@mailinator.com'
    inputfile=""
    single=1
    mailType=1
    mailRegSubject="Connections Cloud - You have been added to"

    def __init__(self):
        self.datafile='mydata.pkl'

    def printToStdOut(self):
        print 'host is ' + self.host + ", " \
                'orgName is ' + self.orgName + ", " \
                'orgId is ' + self.orgId + ", " \
                'cloudSubscriptionId is ' + self.cloudSubscriptionId + ", " \
                'adminUser is ' + self.adminUser + ", " \
                 'adminPwd is ' + self.adminPwd  + ", " \
                'fname is ' + self.fname  + ", " \
                'lname is ' + self.lname  + ", " \
                'email is ' + self.email

    def saveToDictionary(self):
        self.data = dict (host=UserInput.host,
                     orgName=UserInput.orgName,
                     orgId=UserInput.orgId,
                     cloudSubscriptionId =UserInput.cloudSubscriptionId,
                     adminUser =UserInput.adminUser,
                     adminPwd=UserInput.adminPwd,
                     fname=UserInput.fname,
                     lname=UserInput.lname,
                     email=UserInput.email,
                     inputfile=UserInput.inputfile,
                     single=UserInput.single,
                     mailRegSubject=UserInput.mailRegSubject,
                     mailType=UserInput.mailType)

    def saveToFile(self):
        self.saveToDictionary()
        fileout = open(self.datafile, 'wb')
        pickle.dump(self.data, fileout)
        fileout.close()
        self.getFromFile()

    def getFromFile(self):
        try:
            fileout = open(self.datafile, 'rb')
            self.data = pickle.load(fileout)
            UserInput.host=self.data["host"]
            UserInput.orgName=self.data["orgName"]
            UserInput.orgId=self.data["orgId"]
            UserInput.cloudSubscriptionId=self.data["cloudSubscriptionId"]
            UserInput.adminUser=self.data["adminUser"]
            UserInput.adminPwd=self.data["adminPwd"]
            UserInput.fname=self.data["fname"]
            UserInput.lname=self.data["lname"]
            UserInput.email=self.data["email"]
            UserInput.inputfile=self.data["inputfile"]
            UserInput.single=self.data["single"]
            UserInput.mailType=self.data["mailType"]
            UserInput.mailRegSubject=self.data["mailRegSubject"]
            fileout.close()
        except:
            pass

class ProvisionUser(Frame,UserInput):

    def __init__(self, parent,userInput):
        Frame.__init__(self, parent)
        self.parent = parent
        self.users={}
        self.__initUI__(userInput)

    def __initUI__(self,userInput):
        self.parent.title("Provision Smart Cloud User(s)")
        self.style = Style()
        self.style.theme_use("default")

        self.frameSep1= Frame(self,height=2, relief=SUNKEN, borderwidth=0)
        self.frameDetails = Frame(self, height=40,relief=RAISED, borderwidth=0)
        self.frameSep2= Frame(self,height=20, relief=SUNKEN, borderwidth=0)
        self.frameUserDetails = Frame(self, height=40,relief=RAISED, borderwidth=0)
        self.frameSep3= Frame(self,height=20, relief=SUNKEN, borderwidth=0)
        self.frameButtons = Frame(self, height=40,relief=RAISED, borderwidth=0)

        self.lblHost = Label(self.frameDetails, text="Smart Cloud Address")
        self.lblHost.grid(row=0,column=0,sticky=W)
        self.strHostname=StringVar()
        self.cboHost = Combobox(self.frameDetails, textvariable=self.strHostname, width=50)
        self.cboHost['values'] = ("apps.stmsandbox19.swg.usma.ibm.com",
                                            "apps.basesandbox09.swg.usma.ibm.com",
                                            "apps.basesandbox30.swg.usma.ibm.com",
                                            "apps.basesandbox36.swg.usma.ibm.com",
                                            "apps.acdev.swg.usma.ibm.com")
        self.cboHost.current(0)
        self.cboHost.grid(row=0,column=1,sticky=W)
        self.cboHost.bind('<<ComboboxSelected>>', self.selectHost)
        self.cboHost.focus_set()

        self.lblAdminUser  = Label(self.frameDetails, text="Admin User")
        self.lblAdminUser.grid(row=1, column=0,sticky=W)
        self.strAdminUser=StringVar()
        self.txtAdminUser = Entry(self.frameDetails,textvariable=self.strAdminUser,width=60)
        self.txtAdminUser.grid(row=1,column=1,sticky=W)
        self.lblAdminPwd = Label(self.frameDetails, text="Admin Password")
        self.lblAdminPwd.grid(row=2,column=0,sticky=W)
        self.strAdminPwd=StringVar()
        self.txtAdminPwd = Entry(self.frameDetails,textvariable=self.strAdminPwd,width=60)
        self.txtAdminPwd.grid(row=2,column=1,sticky=W)


        self.lblRegType = Label(self.frameUserDetails, text="Registration type")
        self.lblRegType.grid(row=0,column=0,sticky=W)
        self.optionVar=IntVar()
        self.optAddSingle = Radiobutton(self.frameUserDetails ,text="Single", variable=self.optionVar, value=1, command=self.optionClicked)
        self.optAddSingle.grid(row=0,column=1,sticky=W,padx=10)
        self.optAddFromFile = Radiobutton(self.frameUserDetails ,text="Multiple", variable=self.optionVar, value=0, command=self.optionClicked)
        self.optAddFromFile.grid(row=0,column=1,sticky=W,padx=100)
        self.optionVar.set(1)

        self.lblOrgName  = Label(self.frameUserDetails, text="Org Name")
        self.lblOrgName.grid(row=1,column=0,sticky=W)
        self.strOrgName=StringVar()
        self.cboOrgName = Combobox(self.frameUserDetails, textvariable=self.strOrgName, width=50)
        self.cboOrgName['values'] = ("IBM Org")
        self.cboOrgName.current(0)
        self.cboOrgName.grid(row=1,column=1,sticky=W)
        self.cboOrgName.bind('<<ComboboxSelected>>', self.selectOrg)

        self.btnGetOrgId= Button(self.frameUserDetails, text="Get OrgId",command=self.getOrgId)
        self.btnGetOrgId.grid(row=1,column=2,sticky=W)
        self.btnAddOrg= Button(self.frameUserDetails, text="+",command=self.addOrgClick,width=1)
        self.btnAddOrg.grid(row=1,column=2,sticky=W,padx=85)
        self.btnListOrgs= Button(self.frameUserDetails, text="?",command=self.listOrgs,width=1)
        self.btnListOrgs.grid(row=1,column=2,sticky=E,padx=105)

        self.lblOrgId  = Label(self.frameUserDetails, text="Org Id")
        self.lblOrgId.grid(row=2,column=0,sticky=W)
        self.strOrgId=StringVar()
        self.txtOrgId = Entry(self.frameUserDetails,textvariable=self.strOrgId,width=60)
        self.txtOrgId.grid(row=2,column=1,sticky=W)

        self.lblSubscriptionId  = Label(self.frameUserDetails, text="Subscription Id")
        self.lblSubscriptionId.grid(row=3,column=0,sticky=W)
        self.strSubscriptionId=StringVar()
        self.txtSubscriptionId= Entry(self.frameUserDetails,textvariable=self.strSubscriptionId,width=60)
        self.txtSubscriptionId.grid(row=3,column=1,sticky=W)
        self.btnSubscriptionId= Button(self.frameUserDetails, text="Get Subscrip Id",command=self.getSubscriptionId)
        self.btnSubscriptionId.grid(row=3,column=2,sticky=W)


        self.lblFname  = Label(self.frameUserDetails, text="First Name")
        self.lblFname.grid(row=4,column=0,sticky=W)
        self.strFname=StringVar()
        self.txtFname = Entry(self.frameUserDetails,textvariable=self.strFname,width=60)
        self.txtFname.grid(row=4,column=1,sticky=W)

        self.lblLname  = Label(self.frameUserDetails, text="Last Name")
        self.lblLname.grid(row=5,column=0,sticky=W)
        self.strLname=StringVar()
        self.txtLname = Entry(self.frameUserDetails,textvariable=self.strLname,width=60)
        self.txtLname.grid(row=5,column=1,sticky=W)

        self.lblEmail  = Label(self.frameUserDetails, text="Email")
        self.lblEmail.grid(row=6,column=0,sticky=W)
        self.strEmail=StringVar()
        self.txtEmail = Entry(self.frameUserDetails,textvariable=self.strEmail,width=60)
        self.txtEmail.grid(row=6,column=1,sticky=W)

        self.lblRegEmailSubject= Label(self.frameUserDetails, text="Reg Subject Pattern")
        self.lblRegEmailSubject.grid(row=7,column=0,sticky=W)
        self.lblRegEmailSubject.config(state='disabled')
        self.strRegEmailSubject = StringVar()
        self.cboRegEmailSubject = Combobox(self.frameUserDetails, textvariable=self.strRegEmailSubject, width=50)
        self.cboRegEmailSubject['values'] = ("SmartCloud - You have been added to","Connections Cloud - You have been added to","SmartCloud - Your account has been updated")
        self.cboRegEmailSubject.current(0)
        self.cboRegEmailSubject.grid(row=7,column=1,sticky=W)

        self.lblInputFile = Label(self.frameUserDetails, text="Input CSV File")
        self.lblInputFile.grid(row=8,column=0,sticky=W)
        self.lblInputFile.config(state='disabled')
        self.strInputFile=StringVar()
        self.txtInputFile = Entry(self.frameUserDetails, textvariable=self.strInputFile,width=60)
        self.txtInputFile.grid(row=8,column=1)
        self.txtInputFile.config(state='disabled')
        self.btnOpenFileDlg= Button(self.frameUserDetails, text="Browse",command=self.openFileDialog)
        self.btnOpenFileDlg.grid(row=8,column=2,sticky=W)
        self.btnOpenFileDlg.config(state='disabled')

        self.lblMailType = Label(self.frameUserDetails, text="Registration Mail type")
        self.lblMailType.grid(row=9,column=0,sticky=W)
        self.optionMailVar=IntVar()
        self.optMailinator = Radiobutton(self.frameUserDetails ,text="Mailinator", variable=self.optionMailVar, value=1)
        self.optMailinator.grid(row=9,column=1,sticky=W,padx=10)
        self.optBluebox = Radiobutton(self.frameUserDetails ,text="Bluebox", variable=self.optionMailVar, value=0)
        self.optBluebox.grid(row=9,column=1,sticky=W,padx=100)
        self.optionMailVar.set(0)

        self.btnAdd = Button(self.frameButtons, text="Add", command=self.addClick)
        self.btnAdd.grid(row=0,column=0,sticky=E)
        self.btnList = Button(self.frameButtons, text="List", command=self.printUsers)
        self.btnList.grid(row=0,column=1,sticky=E)
        self.btnReg = Button(self.frameButtons, text="Register", command=self.regClick)
        self.btnReg.grid(row=0,column=2,sticky=E)
        self.btnDelete = Button(self.frameButtons, text="Delete", command=self.delClick)
        self.btnDelete.grid(row=0,column=3,sticky=E)
        self.btnAddGuest = Button(self.frameButtons, text="Add Guest", command=self.addGuestClick)
        self.btnAddGuest.grid(row=0,column=4,sticky=E)
        self.btnGetUserEmail = Button(self.frameButtons, text="Get Email", command=self.getUserEmailClick)
        self.btnGetUserEmail.grid(row=0,column=5,sticky=E)
        self.btnClose= Button(self.frameButtons, text="Close",command=self.closeClick)
        self.btnClose.grid(row=0,column=6,sticky=E)

        self.frameSep1.pack(fill=X, padx=5, pady=5)
        self.frameDetails.pack(fill=X, expand=1)
        self.frameSep2.pack(fill=X, padx=5, pady=5)
        self.frameUserDetails.pack(fill=X, expand=1)
        self.frameSep3.pack(fill=X, padx=5, pady=5)
        self.frameButtons.pack(fill=X, expand=1)

        self.pack(fill=BOTH, expand=1)

        userInput=UserInput()
        userInput.getFromFile()
        self.strHostname.set(userInput.host)
        self.strAdminUser.set(userInput.adminUser)
        self.strAdminPwd.set(userInput.adminPwd)
        self.strOrgName.set(userInput.orgName)
        self.strSubscriptionId.set(userInput.cloudSubscriptionId)
        self.strOrgId.set(userInput.orgId)
        self.strFname.set(userInput.fname)
        self.strLname.set(userInput.lname)
        self.strEmail.set(userInput.email)
        self.strInputFile.set(userInput.inputfile)
        self.optionVar.set(userInput.single)
        self.optionMailVar.set(userInput.mailType)
        self.strRegEmailSubject.set(userInput.mailRegSubject)
        self.optionClicked()
        self.listOrgs()

    def selectHost(self,evt):
        self.listOrgs()

    def selectOrg(self,evt):
        self.getOrgId()

    def __setUserInput(self):
        UserInput.host=self.strHostname.get()
        UserInput.orgName=self.strOrgName.get()
        UserInput.orgId=self.strOrgId.get()
        UserInput.cloudSubscriptionId=self.strSubscriptionId.get()
        UserInput.adminUser=self.strAdminUser.get()
        UserInput.adminPwd=self.strAdminPwd.get()
        UserInput.fname=self.strFname.get()
        UserInput.lname=self.strLname.get()
        UserInput.email=self.strEmail.get()
        UserInput.inputfile=self.strInputFile.get()
        UserInput.single=self.optionVar.get()
        UserInput.mailType=self.optionMailVar.get()
        UserInput.mailRegSubject=self.strRegEmailSubject.get()
        userInput=UserInput()
        userInput.saveToFile()

    def getOrgId(self):
        self.strOrgId.set("...")
        conn=SmartCloudRequest(UserInput.host)
        id=conn.getOrgId(self.strOrgName.get())
        self.strOrgId.set(id)
        self.__setUserInput()

        if id == '-1':
            tkMessageBox.showinfo("Get Org ID", 'Could not get org id for:\n' + UserInput.orgName)
            self.strSubscriptionId.set("-1")
        else:
            self.getSubscriptionId()

    def listOrgs(self):
        try:
            self.__setUserInput()
            conn=SmartCloudRequest(UserInput.host,UserInput.adminUser,UserInput.adminPwd)
            if conn.isLoggedOn:
                req=conn.get('manage/account/csgList/input')
                soup=BeautifulSoup(req.text)
                linkdivs=soup.findAll('div',{'class':'link_outer'})
                regex='showUserAccounts.customerId=(.*?)\''
                list=[]
                for div in linkdivs:
                    link=div.find('a')
                    list.append(link.text)
                    ref=re.search(regex,link["onclick"])
                    print link.text,ref.group(1)

                self.cboOrgName['values']=list
            else:
                print "Not Logged On"
                print "Check that you have authenticated through Firewall"
                tkMessageBox.showerror("Failed login", 'Check that you have authenticated through Firewall\nOpen browser and try to connect to ' + UserInput.host)
        except Exception as e:
            print e

    def getSubscriptionId(self):
        self.strSubscriptionId.set("....")
        self.__setUserInput()
        conn=SmartCloudRequest(UserInput.host)
        id=conn.getCloudSubscriptionId(UserInput.orgId)
        self.strSubscriptionId.set(id)

        if id == '-1':
            tkMessageBox.showinfo("Get Subscription ID", 'Could not get subscription id for org:\n' + UserInput.orgId)

    def getUsersFromFile(self,file):
        users=[]
        try:
            with open(file) as fh:
                for line in fh:
                    line=line.strip() #strip new line
                    columns=line.split(",")
                    user = UserInput()
                    user.fname=columns[0]
                    user.lname=columns[1]
                    user.email=columns[2]
                    users.append(user)
        except IOError,e:
            print "Unable to open file " + file #Does not exist OR no read permissions

        return users

    def addOrgClick(self):
        addOrg=AddOrganisation(self,self.strHostname.get(),self.strAdminUser.get(),self.strAdminPwd.get(),self.strOrgName.get())

    def listUsers(self,conn):
        self.__setUserInput()

        payload = dict(pageNum='1',
                   pageSize='100',
                   queryField='NoQuery',
                   queryValue='',
                   queryValueText='',
                   sortAttribute='subscriber.person.displayName',
                   sortDirection='ASC')

        req=conn.post('manage/account/csgList/showUserAccounts?customerId=' + UserInput.orgId,payload)

        soup=BeautifulSoup(req.text)
        linkdivs=soup.findAll('div',{'class':'link_outer'})
        regex='editSubscriber.SubscriberId=(.*?)\''
        self.users={}
        for div in linkdivs:
            link=div.find('a')
            ref=re.search(regex,link["onclick"])
            # self.users.append(link.text)
            self.users[link.text]=ref.group(1)
            # print link.text,ref.group(1)



    def printUsers(self):
        conn=SmartCloudRequest(UserInput.host,UserInput.adminUser,UserInput.adminPwd)
        self.listUsers(conn)
        for user in self.users:
            print user, ' has id ', self.users[user]

        self.emailAddresses={}
        for user in self.users:
            emailAddress = self.getUserEmailAddress(conn,self.users[user])
            self.emailAddresses[user]= emailAddress

        for user in self.emailAddresses:
            print user, ' has email ', self.emailAddresses[user]

    def getUserEmailClick(self):
        self.__setUserInput()
        conn=SmartCloudRequest(UserInput.host,UserInput.adminUser,UserInput.adminPwd)
        id=tkSimpleDialog.askinteger("Enter User Id", "Enter User Id",initialvalue=20003993)
        print id
        emailAddress=self.getUserEmailAddress(conn,id)
        print emailAddress

    def getUserEmailAddress(self,conn,id):
        token='xx'

        payload = dict(start='1',
                       token=token,
                       entityId=id)

        req=conn.get('manage/user/updateUser/basic/showEdit=',payload)

        payload = dict(start='1',
                       token=token,
                       entityId=id)

        req=conn.get('manage/user/updateUser/collabSetup/showEdit',payload)
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

    def addClick(self):
        self.__setUserInput()
        userInput = UserInput()
        conn=SmartCloudRequest(UserInput.host,UserInput.adminUser,UserInput.adminPwd)
        if self.optionVar.get() is 1:
            self.addSingle(conn,userInput)
            reply = tkMessageBox.showinfo("Add Finished", "Finished adding user", icon='info')
        else:
            self.addMultiple(conn,userInput)

    def addSingle(self,conn,userInput):
        req=conn.get('manage/account/csgList/input')
        req=conn.get('manage/account/csgList/showUserAccounts?customerId=' + userInput.orgId)

        req=conn.get('manage/subscribers/addSubscriber?customerId=' + userInput.orgId)
        csrfToken=conn.getHiddenInputValue(req.text,"csrfToken")
        token=conn.getHiddenInputValue(req.text,"token")

        payload = dict(department='',
                       entityId='',
                       firstName=userInput.fname,
                       language='en_US',
                       lastName=userInput.lname,
                       roles='User,AppDeveloper,ContentValidator',
                       targetPage='',
                       token=token,
                       csrfToken=csrfToken,
                       visitedPage='1')

        req=conn.post('manage/user/addUser/basic/submit',payload)
        token=conn.getHiddenInputValue(req.text,"token")

        req=conn.get('manage/user/addUser/subscription/input?visitedPage=2&token=' +token)
        csrfToken=conn.getHiddenInputValue(req.text,"csrfToken")
        token=conn.getHiddenInputValue(req.text,"token")
        origEmailId=conn.getHiddenInputValue(req.text,"origEmailId")

        payload = dict(assignTo='',
                       collabSubscriptionId=userInput.cloudSubscriptionId,
                       csrfToken=csrfToken,
                       origEmailId=origEmailId,
                       subscriptionType='on',
                       targetPage='',
                       token=token,
                       visitedPage='2')
        req=conn.post('manage/user/addUser/subscription/submit',payload,None)
        csrfToken=conn.getHiddenInputValue(req.text,"csrfToken")
        token=conn.getHiddenInputValue(req.text,"token")

        payload = dict(csrfToken=csrfToken,
                       invitationText='A new account is ready for you.',
                       isCancelEmailChange='false',
                       primaryEmail=userInput.email,
                       targetPage='',
                       token=token,
                       visitedPage='3')
        req=conn.post('manage/user/addUser/collabSetup/submit',payload,None)
        ret = re.findall('The following user account was created',req.text)

        if len(ret) is not 0:
            print ret,userInput.email
            return True
        else:
            print "failed to add ",userInput.email
            return False

    def addGuestClick(self):
        addGuest=AddGuestUser(self,self.strHostname.get())

    def addMultiple(self,conn,userInput):
        users=self.getUsersFromFile(UserInput.inputfile)

        for user in users:
            userInput.fname = user.fname
            userInput.lname = user.lname
            userInput.email = user.email
            self.addSingle(conn,userInput)

        reply = tkMessageBox.showinfo("Add Finished", "Finished processing CSV file", icon='info')


    def regClick(self):
        self.__setUserInput()
        userInput=UserInput()
        conn=SmartCloudRequest(UserInput.host)

        if self.optionVar.get() is 1:
            self.regSingle(conn, userInput)
            reply = tkMessageBox.showinfo("Reg Finished", "Finished registering user", icon='info')
        else:
            self.regMultiple(conn, userInput)

    def regSingle(self,conn,userInput):
        if userInput.mailType is 1:
            mail = CheckMailinator(userInput.email)
        else:
            mail = CheckBluebox(userInput.email)

        regToken=mail.getRegistrationToken(userInput.mailRegSubject)

        if regToken == '-1':
            print "could not get token"
            return

        print "registration token from email is ", regToken

        payload = {'token': regToken,
                   'confirmpassword' : 'Pa88w0rd',
                   'password' : 'Pa88w0rd',
                   'countryCode' : 'IE',
                   'email_check' : 'on',
                   'emailaddress' : userInput.email,
                   'fullname' : userInput.fname +'%20' + userInput.lname,
                   'languageCode' : "en_US",
                   'orgname' : 'IBM%20Org',
                   'shouldValidate' : 'true',
                   'timeZoneCode' : 'Europe%2FDublin'}

        reqRegistration = conn.get('manage/account/public/registration',payload)
        ret = re.search("There was an error processing your registration",reqRegistration.text)
        ret1 = re.search("There was a problem with your registration",reqRegistration.text)

        if ret is not None or ret1 is not None:
            print 'could not register user ',userInput.email
            return
        else:
            print 'registered user ',userInput.email

    def regMultiple(self,conn,userInput):
        users=self.getUsersFromFile(UserInput.inputfile)

        for user in users:
            userInput.fname = user.fname
            userInput.lname = user.lname
            userInput.email = user.email
            self.regSingle(conn,userInput)

        reply = tkMessageBox.showinfo("Reg Finished", "Finished processing CSV file", icon='info')

    def delClick(self):
        self.__setUserInput()
        userInput=UserInput()
        conn=SmartCloudRequest(UserInput.host)

        if self.optionVar.get() is 1:
            self.delSingle(conn, userInput)
        else:
            self.delMultiple(conn, userInput)


    def delSingle(self,conn,userInput):
        ans=tkMessageBox.askquestion("Delete user?", "Do you really wish to delete user " +  userInput.email + " ?", icon='question')
        if ans == 'no':
            return
        else:
            ret=conn.deleteUser(userInput.orgId,userInput.email)
            if ret == 1:
                tkMessageBox.showinfo("Delete Finished", "Deleted user " + userInput.email, icon='info')
            else:
                tkMessageBox.showinfo("Delete Finished", "Did not delete user " + userInput.email, icon='info')

    def delMultiple(self,conn,userInput):
        ans=tkMessageBox.askquestion("Delete user?", "Do you really wish to delete users from csv file ?", icon='question')
        if ans == 'no':
            return
        else:
            users=self.getUsersFromFile(UserInput.inputfile)

            for user in users:
                ret=conn.deleteUser(userInput.orgId,user.email)

    def closeClick(self):
        self.parent.destroy()

    def optionClicked(self):
        if self.optionVar.get() is 1:
            self.btnOpenFileDlg.config(state='disabled')
            self.lblInputFile.config(state='disabled')
            self.txtInputFile.config(state='disabled')
            self.lblRegEmailSubject.config(state='normal')
            self.cboRegEmailSubject.config(state='normal')
            self.lblFname.config(state='normal')
            self.txtFname.config(state='normal')
            self.lblLname.config(state='normal')
            self.txtLname.config(state='normal')
            self.lblEmail.config(state='normal')
            self.txtEmail.config(state='normal')
            self.btnDelete.config(state='normal')
        else:
            self.btnOpenFileDlg.config(state='normal')
            self.lblInputFile.config(state='normal')
            self.txtInputFile.config(state='normal')
            self.lblRegEmailSubject.config(state='normal')
            self.cboRegEmailSubject.config(state='normal')
            self.lblFname.config(state='disabled')
            self.txtFname.config(state='disabled')
            self.lblLname.config(state='disabled')
            self.txtLname.config(state='disabled')
            self.lblEmail.config(state='disabled')
            self.txtEmail.config(state='disabled')
            self.btnDelete.config(state='normal')
        pass

    def openFileDialog(self):
        ftypes = [('CSV', '*.csv'), ('All files', '*')]
        dlg = tkFileDialog.Open(self, filetypes = ftypes)
        fl = dlg.show()

        if fl != '':
            self.strInputFile.set(fl)

def main():
    root = Tk()
    ws = root.winfo_screenwidth()
    hs = root.winfo_screenheight()
    w,h= 750, 370
    x,y = (ws/2) - (w/2),(hs/2) - (h/2)

    #responsible for setting the dimensions of the screen and where it is placed
    root.geometry('%dx%d+%d+%d' % (w, h, x, y))

    # this removes the maximize button
    root.resizable(0,0)

    app = ProvisionUser(root,UserInput)
    root.mainloop()

if __name__ == '__main__':
    sys.exit(main())