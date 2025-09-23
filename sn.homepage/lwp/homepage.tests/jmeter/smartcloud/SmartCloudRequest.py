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

__author__ = 'decarey'
from BeautifulSoup import BeautifulSoup as Soup
import requests
import re
import os
import errno

class SmartCloudUrls():
    manageAccounts='manage/account/csgList/input'
    showNewPPAAccount="manage/account/csgList/showNewPPAAccount?orderType=orderType.PPA"
    showCreateNewAccount="manage/account/ppaFlowCustomer/showCreateNewAccount?customerId="
    checkUserExistenceAcrossDataCentres="manage/account/public/validateEmail/checkUserExistenceAcrossDataCentres?extid="
    addCustomerData="manage/account/ppaFlowCustomer/addCustomerData"
    showCreateNewOrder="manage/account/ppaOrder/showCreateNewOrder?customerId="
    addOrderDetails="manage/account/ppaOrder/addOrderDetails"
    showAddPartDetails="manage/account/ppaPart/showAddPartDetails?customerId="
    getResources="manage/account/getResources/input"
    showPartInfo="manage/account/ppaPart/showPartInfo"
    addPartDetails="manage/account/ppaPart/addPartDetails"
    showReviewDetails="manage/account/ppaReview/showReviewDetails?customerId="
    submitOrder="manage/account/ppaReview/submitOrder"
    registerOrder="manage/account/public/registration"
    search="manage/account/csgList/input"

    showUserAccounts="manage/account/csgList/showUserAccounts"
    manageSubscriptions="manage/account/subscriptions/input"


class SmartCloudRequest():
    def __init__(self,host,adminUser="bssadmin@us.ibm.com",adminPwd="Pa88w0rd",port="443",protocol="https"):
        # print "constructor"
        # print "using requests module version ", requests.__version__
        self.session = requests.Session()
        self.adminUser=adminUser
        self.adminPwd=adminPwd
        self.host=host
        self.port=port
        self.protocol=protocol
        self.saveFolder=os.path.dirname(os.path.realpath(__file__)) + "/requests"
        self.mkdir_p(self.saveFolder)
        self.deleteSavedRequests()
        self.requestIndex=0
        self.isLoggedOn=self.login(adminUser,adminPwd)

    def isLoggedOn(self):
        return self.isLoggedOn

    def __del__(self):
        # print 'deconstructor'
        self.session.close()

    def login(self,user,pwd):
        try:
            payload = dict(username=user, password=pwd)
            payload["login-form-type"]='pwd'
            payload["error-code"]=''
            print "logging on to " + self.host + " with " + user + " and password " + pwd + " ..."
            req=self.post('pkmslogin.form', payload)
            return True
        except Exception as e:
            print "Login error"
            print e
            return False

    def logout(self):
        return self.get("pkmslogout")

    def __getUrl(self,path):
        return self.protocol + '://' + self.host + ':' + self.port + '/' + path

    def get(self,path,params=None,cookies=None,save=True,printMsg=True):
        resourcePath=self.__getUrl(path)
        resp=self.session.get(resourcePath,params=params,cookies=cookies, verify=False,allow_redirects=True)
        if save:
            self.saveRequest("get_" + path,resp,printMsg)
        return resp

    def post(self,path, payload,cookies=None):
        resp=self.session.post(self.__getUrl(path), data=payload, cookies=cookies, verify=False, allow_redirects=True)
        self.saveRequest("post_" + path,resp)
        return resp

    def mkdir_p(self,path):
        try:
            if not os.path.exists(path):
                os.makedirs(path)
        except OSError as exc: # Python >2.5
            if exc.errno == errno.EEXIST and os.path.isdir(path):
                pass
            else: raise

    def saveRequest(self,name,request,printMsg=True):
        name=name.replace("/","_")
        if printMsg:
            print "saving " + name
        self.requestIndex += 1
        filename = self.saveFolder + "/" + str(self.requestIndex) + "_" + name + ".html"

        file = open(filename, "w")
        file.write(request.text.encode('utf8'))
        file.close()

    def deleteSavedRequests(self):
        print "deleting from " + self.saveFolder
        dirPath = self.saveFolder
        fileList = os.listdir(dirPath)
        for fileName in fileList:
            file = dirPath+"/"+fileName
            fileExtension = os.path.splitext(file)
            if os.path.isfile(file) and fileExtension[1][1:].lower() == "html":
                print "removing " + file
                os.remove(file)


    def getHiddenInputValue(self,content,name):
        soup = Soup(content)
        return (soup.find("input", {"type": "hidden", "name": name}))['value']

    def updateSessionHeaders(self,headers):
        self.session.headers.update(headers)

    def printSessionHeaders(self):
        for header in self.session.headers:
            print header,"=",self.session.headers.get(header)

    def printRequestHeaders(self,request):
        for header in request.headers:
            print header,"=",request.headers.get(header)

    def printSessionCookies(self):
        for cookie in self.session.cookies:
            print cookie.name,"=",cookie.value

    def printRequestCookies(self,request,name=None):
        if name is not None and len(request.cookies) > 0:
            print "cookies for ", name
        for cookie in request.cookies:
            print ">>\t", cookie.name,"=",cookie.value

    def getOrgId(self,queryName,queryField="manageaccount.tableheader.org",sortField="customer.organization.orgName"):
        try:
            payload = dict(pageNum='1',
                           pageSize='10',
                           queryField=queryField,
                           queryValue=queryName,
                           queryValueText=queryName,
                           sortAttribute=sortField,
                           sortDirection='ASC')
            r=self.post(SmartCloudUrls.search,payload)
            self.saveRequest("search",r)

            ret = re.search('customerId=(.*?).quot;',r.text)

            if ret is not None:
                id=ret.group(1)
                print "Org Id returned is " + id
                return id
            else:
                print 'no id found. return',queryName
                return "-1"
        except Exception as e:
            print e


    def getCloudSubscriptionId(self,customerId):
        payload = dict(customerId=customerId)
        self.get(SmartCloudUrls.showUserAccounts,payload)

        r=self.get(SmartCloudUrls.manageSubscriptions,payload)
        self.saveRequest("getsubscriptionid",r)
        ret = re.search('id":"(.*?)",',r.text.replace('\\x22', '"'))

        if ret is not None:
            id=ret.group(1)
            print "Cloud subscription id returned is", id
            return id
        else:
            print 'no subscription id found customerId ' + customerId + ', returning.'
            return "-1"

    def getUserId(self,orgId,userEmail):
        url_userSearch='manage/subscribers/companyList/input'

        req=self.get('manage/account/csgList/input')
        req=self.get('manage/account/csgList/showUserAccounts?customerId=' + orgId)

        payload = {'pageNum' : '1',
                   'pageSize' :'50',
                   'queryField' : 'custsubscribers.byEmail',
                   'queryValue' : userEmail,
                   'queryValueText' : userEmail,
                   'sortAttribute' : 'subscriber.person.displayName',
                   'sortDirection' : 'ASC'}

        reqSearch = self.post(url_userSearch,payload)
        ret = re.search('entityId=(.*?).quot;',reqSearch.text)

        if ret is not None:
            userId=ret.group(1)
            return userId
        else:
            print 'no user id found. return',userEmail
            return "-1"

    def deleteUser(self,orgId,userEmail):
        url_userDeleteDlg='/manage/subscribers/showDeleteDialog/input?entityId='
        url_userDelete = 'manage/subscribers/deleteSubscriber'

        userId = self.getUserId(orgId,userEmail)

        if not userId:
            return

        reqDeleteDlg = self.get(url_userDeleteDlg + userId)
        csrfToken=self.getHiddenInputValue(reqDeleteDlg.text ,"csrfToken")
        origEmailId=self.getHiddenInputValue(reqDeleteDlg.text ,"origEmailId")

        payload = {'entityId' : userId,
                   'csrfToken' : csrfToken,
                   'origEmailId' : origEmailId,
                   'assignTo' : ''}

        reqDelete = self.post(url_userDelete,payload,None)
        ret = re.search('was deleted',reqDelete.text)

        if ret is not None:
            print 'user deleted ',origEmailId
            return 1
        else:
            print 'not deleted',origEmailId
            return 0