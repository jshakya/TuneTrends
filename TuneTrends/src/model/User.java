/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Mike
 * Class for the users
 */
public class User implements Serializable {

	private static final long serialVersionUID = 8182641119844029747L;
	/**
	 * Default constructor should not be used and will not create a valid user
	 */
	public User() {
	}
	
	/**
	 * Constructor to create a new user
	 * @param fName first name
	 * @param lName last name
	 * @param email email/username
	 * @param password password
	 */
	public User(String fName, String lName, String email, String password)
	{
		setFName(fName);
		setLName(lName);
		setEmail(email);
		setPassword(password);
		currentUser = this;
	}
	
	
	/**
	 * Submit a new rating for a track.
	 * @param track track to have its rating changed
	 * @param rating new rating
	 */
	public void submitSongRatings(Track track, int rating)
	{
		track.setRatingValue(rating);
	}
	
	/**
	 * MDR -- Accessor for first name
	 * @return first name
	 */
	public String getFName()
	{
		return this.fName;
	}
	
	/**
	 * MDR -- Mutator for first name
	 * @param fName new first name
	 */
	public void setFName(String fName)
	{
		this.fName = fName;
	}
	
	/**
	 * MDR -- Accessor for last name
	 * @return last name
	 */
	public String getLName()
	{
		return this.lName;
	}
	
	/**
	 * MDR -- Mutator for last name
	 * @param lName new last name
	 */
	public void setLName(String lName)
	{
		this.lName = lName;
	}
	
	/**
	 * MDR -- Accessor for group
	 * @return current group
	 */
	public UserGroup getGroup()
	{
		return this.group;
	}
	
	/**
	 * MDR -- Mutator for group
	 * @param group new group
	 */
	public void setGroup(UserGroup group)
	{
		this.group = group;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String fName;
	private String lName;
	private UserGroup group;
	public static User currentUser;
	private String email;
	private String password;

}
