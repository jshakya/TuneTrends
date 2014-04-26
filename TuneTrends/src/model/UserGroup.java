package model;

import java.io.Serializable;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.ArrayList;

//import android.content.Context;

public class UserGroup extends ArrayList<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8260713332729190808L;
	
	public UserGroup() {
		this.trackList = new TrackList();
	}
	
	public TrackList getTrackList()
	{
		if (this.trackList == null)
		{
			trackList = new TrackList();
			/*Remove after Successful Serialization*/
			trackList.addSong("Song2", "Artist 2", 2);
			trackList.addSong("Song5", "Artist 5", 5);
			trackList.addSong("Song3", "Artist 3", 3);
			/*Sample Initialization*/
		}
		return trackList;
	}
	
	public int getGroupCode(){
		return this.groupCode;
	}
	public void setGroupCode(int groupCode){
		this.groupCode = groupCode;
	}
	
	public void addUser(User u)
	{
		this.add(u);
	}
	/*
	public ArrayList <User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(ArrayList <User> listUsers) {
		this.listUsers = listUsers;
	}
	*/
	
	private TrackList trackList;
	private int groupCode;
	//private ArrayList <User> listUsers;
}
