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

package com.ibm.atmn.base;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;

import com.ibm.atmn.base.User;

/**
 * The Class CSVHandler.
 */
public class CSVHandler {

	/** The file name. */
	String fileName;

	/** The Separator. */
	String Separator;

	/**
	 * Instantiates a new CSV handler.
	 * 
	 * @param FileName
	 *            the file name of the csv being parsed
	 */
	public CSVHandler(String FileName) {

		this.fileName = FileName;
		this.Separator = ",";

	}

	// TODO should return a hashmap to the calling function
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

				Uid = al.get(1).toString();
				Password = al.get(2).toString();
				Email = al.get(6).toString();
				FN = al.get(4).toString();
				LN = al.get(5).toString();
				Lang = al.get(7).toString();
				Dn = al.get(8).toString();

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
