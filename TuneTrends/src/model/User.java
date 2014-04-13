/**
 * 
 */
package model;

/**
 * @author Mike
 * Class for the users
 */
public class User {

	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String fName, String lName, UserGroup group)
	{
		setFName(fName);
		setLName(lName);
		setGroup(group);
		currentUser = this;
	}
	
	/**
	 * Might be a duplicate with the constructor? Or maybe do server calls here
	 */
	public void register()
	{
		
	}
	
	/**
	 * Method should submit to the list, since we already have a track
	 * @param track the track to be submitted to the list
	 */
	public void submitSong (Track track)
	{
		
	}
	
	/**
	 * Submit a new rating for a track. I feel like this should return the track though
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
	
	private String fName;
	private String lName;
	private UserGroup group;
	public static User currentUser;
	

}
