package model;

public class UserGroup {

	public UserGroup() {
		// TODO Auto-generated constructor stub
	}
	
	public TrackList getTrackList()
	{
		return this.tracklist;
	}
	
	public int getGroupCode(){
		return this.groupCode;
	}
	public void setGroupCode(int groupCode){
		this.groupCode = groupCode;
	}
	private TrackList tracklist;
	private int groupCode;

}
