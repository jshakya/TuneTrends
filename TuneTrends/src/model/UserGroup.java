package model;

import java.util.ArrayList;

public class UserGroup {

	public UserGroup() {
		// TODO Auto-generated constructor stub
		UserGroup.tracklist = new TrackList();
	}
	
	public static TrackList getTrackList()
	{
		return tracklist;
	}
	
	public int getGroupCode(){
		return this.groupCode;
	}
	public void setGroupCode(int groupCode){
		this.groupCode = groupCode;
	}
	public ArrayList <User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(ArrayList <User> listUsers) {
		this.listUsers = listUsers;
	}
	
	private static TrackList tracklist;
	private int groupCode;
	private ArrayList <User> listUsers;
}
