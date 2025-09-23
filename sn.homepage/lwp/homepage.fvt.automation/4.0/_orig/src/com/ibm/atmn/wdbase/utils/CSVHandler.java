/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wdbase.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibm.atmn.wdbase.core.data.User;

/**
 * The Class CSVHandler.
 */
public class CSVHandler {

	/** The file name. */
	String fileName;

	/** The Separator. */
	String Separator;
	
	/** Column position of elements */
	private Integer UidIndex;
	private Integer PwordIndex;
	private Integer FnameIndex;
	private Integer LnameIndex;
	private Integer EmailIndex;
	private Integer DnameIndex;
	private Integer LangIndex;
	
	/**
	 * @return the uidIndex
	 */
	private Integer getUidIndex() {
		return UidIndex;
	}


	/**
	 * @param uidIndex the uidIndex to set
	 */
	private void setUidIndex() {		
		try {
			UidIndex = GetHeaderIndex("uid");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the uidIndex
	 */
	private Integer getLangIndex() {
		return LangIndex;
	}


	/**
	 * @param uidIndex the uidIndex to set
	 */
	private void setLangIndex() {		
		try {
			LangIndex = GetHeaderIndex("language");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the pwordIndex
	 */
	private Integer getPwordIndex() {
		return PwordIndex;
	}


	/**
	 * @param pwordIndex the pwordIndex to set
	 */
	private void setPwordIndex() {
		try {
			PwordIndex = GetHeaderIndex("password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @return the fnameIndex
	 */
	private Integer getFnameIndex() {
		return FnameIndex;
	}


	/**
	 * @param fnameIndex the fnameIndex to set
	 */
	private void setFnameIndex() {
		try {
			FnameIndex = GetHeaderIndex("first name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @return the lnameIndex
	 */
	private Integer getLnameIndex() {
		return LnameIndex;
	}


	/**
	 * @param lnameIndex the lnameIndex to set
	 */
	private void setLnameIndex() {
		try {
			LnameIndex = GetHeaderIndex("last name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @return the emailIndex
	 */
	private Integer getEmailIndex() {
		return EmailIndex;
	}


	/**
	 * @param emailIndex the emailIndex to set
	 */
	private void setEmailIndex() {
		try {
			EmailIndex = GetHeaderIndex("email");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @return the dnameIndex
	 */
	private Integer getDnameIndex() {
		return DnameIndex;
	}


	/**
	 * @param dnameIndex the dnameIndex to set
	 */
	private void setDnameIndex() {
		try {
			DnameIndex = GetHeaderIndex("Display name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Instantiates a new CSV handler.
	 * 
	 * @param FileName
	 *            the file name of the csv being parsed
	 */
	public CSVHandler(String FileName) {

		this.fileName = FileName;
		this.Separator = ",";
		this.setUidIndex();
		this.setDnameIndex();
		this.setLnameIndex();
		this.setFnameIndex();
		this.setEmailIndex();
		this.setPwordIndex();
		this.setLangIndex();

	}


	/**
	 * Read file split.
	 * 
	 * @param headerInclude
	 *            Whether to include the first line of the CSV as a header
	 * @return the hash map containing the User objects
	 * @throws Exception
	 *             the exception thrown
	 */
	public HashMap<Integer, User> ReadFileSplit(Boolean headerInclude) throws Exception {

		HashMap<Integer, User> map = new HashMap<Integer, User>();
		String Uid, Password, Email, FN, LN, Lang, Dn;

		try {

			BufferedReader br = new BufferedReader(new FileReader(fileName));
			//create a temp array list				
			ArrayList<Object> al = new ArrayList<Object>();
			String[] all;
			int lineNumber = 0;

			while ((fileName = br.readLine()) != null) {
				// TODO if the header is included get the ids of the columns to dynamically
				// assign the contents to the user constructor

				// Increment line number as hashmap key							
				all = fileName.split(",");

				// add elements to the array list
				for (int i = 0; i < all.length; i++) {
					al.add(all[i]);
				}

				Uid = al.get(getUidIndex()).toString();
				Password = al.get(getPwordIndex()).toString();
				Email = al.get(getEmailIndex()).toString();
				FN = al.get(getFnameIndex()).toString();
				LN = al.get(getLnameIndex()).toString();
				Lang = al.get(getLangIndex()).toString();
				Dn = al.get(getDnameIndex()).toString();

				//add a new user object to the map
				map.put(lineNumber, new User(Uid, Password, Email, FN, LN, Lang, Dn, "0"));

				//clear the arraylist
				al.clear();
				lineNumber++;
				//set the thread id
				//map.get(lineNumber).setThreadUID(Thread.currentThread().getId());
			}

			// TODO handle the header test here
			if (headerInclude == true) ;
			{
				map.remove(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}
	
	private Integer GetHeaderIndex(String Column) throws Exception{
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			//read the first line of the csv. this must contain the headers
			String Headers = br.readLine();
			String[] SplitHeaders = Headers.split(Separator);
			br.close();
			
			//return the index of the Column
			for (int i = 0; i < SplitHeaders.length; i++)
		      {
		       if (Column.equals(SplitHeaders[i].toString()))
		        {
		         return(i);
		        }
		      }
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
		return(-1);//didn't find what I was looking for
	}

	//mutators and access
	/**
	 * Sets the file name.
	 * 
	 * @param newFileName
	 *            the new file name
	 */
	public void setFileName(String newFileName) {

		this.fileName = newFileName;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName() {

		return fileName;
	}

}

