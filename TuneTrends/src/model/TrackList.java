package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class houses the list of the tracks for the user and respective methods like 
 * add and remove the songs.
 * @author jebinshakya
 *
 */
public class TrackList {
	
	public TrackList ()
	{
		trackList = new ArrayList<Track>();
	}
	
	/**
	 * This adds the track to the list
	 * @param track to be added to the list
	 */
	public void addSong(Track track)
	{
		this.trackList.add(track);
	}
	
	/**
	 * This adds the newly created Track to the List 
	 * @param trackName Name of the Track
	 * @param artistName Name of the Artist
	 */
	public void addSong(String trackName, String artistName){
		Track track = new Track(trackName, artistName);
		this.trackList.add(track);
	}
	
	/**
	 * This returns the TrackList of the usergroup if needed
	 * NO IMPLEMENTATIONS YET		
	 * @return
	 */
	public ArrayList<Track> getTrackList() {
		return this.trackList;
	}
	
	/**
	 * This removes the top song of the list
	 */
	public void removeTopSong(){
		this.trackList.remove(0);
	}
	
	/**
	 * This removes the bottom song of the list
	 */
	public void removeBottomSong(){
		this.trackList.remove(this.trackList.size() -1);
	}
	
	/**
	 * This returns the number of the songs in the list
	 * @return
	 */
	public int countSongs(){
		return this.trackList.size();
	}
	
	/**
	 * This sorts the songs in the list according to the ratingValues
	 * @param another
	 * @return
	 */
	public void sortSongs() {
		Collections.sort(trackList, new trackComp());
	}
	

//	public void setTrackList(ArrayList<Track> trackList) {
//		TrackList = trackList;
//	}
	
	private ArrayList<Track> trackList;
}

/**
 * This is the comparator class to compare the Tracks within the TrackList 
 * @author jebinshakya
 */
class trackComp implements Comparator<Track>{
   @Override
   public int compare(Track t1, Track t2) {
       if(t1.getRatingValue() < t2.getRatingValue()){
           return 1;
       } else {
           return -1;
       }
   }
}
