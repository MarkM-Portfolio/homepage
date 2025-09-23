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

/**
 * 
 */
package com.ibm.atmn.wdbase.core.data;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 * 
 * @author Liam Walsh
 */
public class User {

	// Model a Communities user	
	/** The Email. */
	private String Email;

	/** The Password. */
	private String Password;

	/** The First name. */
	private String FirstName;

	/** The Last name. */
	private String LastName;

	/** The Uid. */
	private String Uid;

	/** The Language. */
	private String Language;

	/** The Phone. */
	private String Phone;

	/** The CN. */
	//private String CN;

	/** The Dept. */
	private String Dept;

	/** The Display name. */
	private String DisplayName;

	/** The Thread uid. */
	private Long ThreadUid;

	//User constructor
	/**
	 * Instantiates a new user.
	 * 
	 * @param ID
	 *            the unique id of the user
	 * @param Pword
	 *            the user password
	 * @param Email
	 *            the user email address
	 * @param FN
	 *            the user First name
	 * @param LN
	 *            the user Last name
	 * @param Lang
	 *            the user language
	 * @param DN
	 *            the user Display name
	 * @param ThId
	 *            the thread id which has locked the user
	 */
	public User(String ID, String Pword, String Email, String FN, String LN, String Lang, String DN, String ThId) {

		this.Uid = ID;
		this.Password = Pword;
		this.Email = Email;
		this.FirstName = FN;
		this.LastName = LN;
		this.Language = Lang;
		this.DisplayName = DN;
		this.ThreadUid = Long.parseLong(ThId);

	}

	//Override constructor
	/**
	 * Instantiates a new user.
	 */
	public User() {

	}

	//User methods 
	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {

		return Email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {

		Email = email;
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {

		return FirstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {

		FirstName = firstName;
	}

	/**
	 * Gets the last name.
	 * 
	 * @return the lastName
	 */
	public String getLastName() {

		return LastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {

		LastName = lastName;
	}

	/**
	 * Gets the uid.
	 * 
	 * @return the uid
	 */
	public String getUid() {

		return Uid;
	}

	/**
	 * Sets the uid.
	 * 
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {

		this.Uid = uid;
	}

	/**
	 * Gets the language.
	 * 
	 * @return the language
	 */
	public String getLanguage() {

		return Language;
	}

	/**
	 * Sets the language.
	 * 
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {

		Language = language;
	}

	/**
	 * Gets the phone.
	 * 
	 * @return the phone
	 */
	public String getPhone() {

		return Phone;
	}

	/**
	 * Sets the phone.
	 * 
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {

		Phone = phone;
	}

	/**
	 * Gets the dept.
	 * 
	 * @return the dept
	 */
	public String getDept() {

		return Dept;
	}

	/**
	 * Sets the dept.
	 * 
	 * @param dept
	 *            the dept to set
	 */
	public void setDept(String dept) {

		Dept = dept;
	}

	/**
	 * Gets the display name.
	 * 
	 * @return the displayName
	 */
	public String getDisplayName() {

		return DisplayName;
	}

	/**
	 * Sets the display name.
	 * 
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {

		DisplayName = displayName;
	}

	/**
	 * Sets the thread uid.
	 * 
	 * @param ThreadNum
	 *            The thread id to set
	 * @return the long
	 */
	public Long setThreadUID(Long ThreadNum) {

		return this.ThreadUid = ThreadNum;
	}

	/**
	 * Gets the thread uid.
	 * 
	 * @return the thread id
	 */
	public Long getThreadUID() {

		return this.ThreadUid;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {

		return Password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {

		Password = password;
	}

}
