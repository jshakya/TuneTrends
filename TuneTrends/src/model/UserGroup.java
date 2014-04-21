package model;

import java.util.ArrayList;

public class UserGroup {

	public UserGroup() {
		// TODO Auto-generated constructor stub
		UserGroup.tracklist = new TrackList();
	}
	
	public static TrackList getTrackList()
	{
		if (tracklist == null)
		{
			tracklist = new TrackList();
			/*Remove after Successful Serialization*/
			tracklist.addSong("Song2", "Artist 2", 2);
			tracklist.addSong("Song5", "Artist 5", 5);
			tracklist.addSong("Song3", "Artist 3", 3);
			/*Sample Initialization*/
		}
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
