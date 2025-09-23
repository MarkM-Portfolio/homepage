@REM ***************************************************************** 
@REM                                                                   
@REM IBM Confidential                                                  
@REM                                                                   
@REM OCO Source Materials                                              
@REM                                                                   
@REM Copyright IBM Corp. 2011, 2013                                    
@REM                                                                   
@REM The source code for this program is not published or otherwise    
@REM divested of its trade secrets, irrespective of what has been      
@REM deposited with the U.S. Copyright Office.                         
@REM                                                                   
@REM ***************************************************************** 

CALL bld clean

rmdir /s /q build\homepage.web.layer

CALL bld -DcrConvert.1to3=false -Denable.pseudo.translation=false

time /t

