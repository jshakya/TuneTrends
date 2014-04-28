package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class houses the list of the tracks for the user and respective methods like
 * add and remove the songs.
 *
 * @author jebinshakya
 */
public class TrackList extends ArrayList<Track> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4580108724538741771L;


    /**
     * clears out existing values in TrackList
     * precondition: none
     * postcondition: tracklist is empty
     */
    public TrackList() {
        this.clear();
    }

    /**
     * This adds the track to the list
     * precondition: track needs to be initialized
     * postcondition: the track is added to the end of the list
     * @param track to be added to the list
     */
    public void addSong(Track track) {
        this.add(track);
    }

    /**
     * This adds the newly created Track to end of the List
     * precondition: none
     * postcondition: new track is created and the track is added to the end of the list
     * @param trackName  Name of the Track
     * @param artistName Name of the Artist
     */
    public void addSong(String trackName, String artistName) {
        this.add(new Track(trackName, artistName));
    }

    /**
     * This adds the newly created Track to end of the List
     * precondition: none
     * postcondition: new track is created and the track is added to the end of the list
     * @param trackName  Name of the Track
     * @param artistName Name of the Artist
     */
    public void addSong(String trackName, String artistName, int ratingValue) {
        this.add(new Track(trackName, artistName, ratingValue));
    }

    /**
     * This returns the TrackList of the usergroup
     * precondition: tracklist should be initialized
     * postcondition: tracklist is returned
     *
     * @return this
     */
    public TrackList getTrackList() {
        return this;
    }

    /**
     * sets the tracklist to the contents of the passed tracklist, especially while loading from a file
     * precondition: none
     * postcondition: tracklist is created from temp
     * @param temp
     */
    public void setTrackList(TrackList temp) {
        this.clear();
        for (Track track : temp) {
            this.add(track);
        }
    }

    /**
     * This removes the top song of the list
     * precondition: tracklist should not be empty
     * postcondition: the top track is removed
     */
    public void removeTopSong() {
        this.remove(0);
    }

    /**
     * This removes the bottom song of the list
     * precondition: tracklist should not be empty
     * postcondition: the bottom track is removed
     */
    public void removeBottomSong() {
        this.remove(this.size() - 1);
    }

    /**
     * This returns the number of the songs in the list
     * precondition: none
     * postcondition: returns the size of the list
     * @return this.size
     */
    public int countSongs() {
        return this.size();
    }

    /**
     * This sorts the songs in the list according to the ratingValues
     * precondition: none
     * postcondition: the list will be sorted in order of highest rating to lowest rating
     */
    public void sortSongs() {
        Collections.sort(this, new trackComp());
    }
}

/**
 * This is the comparator class to compare the Tracks within the TrackList
 *
 * @author jshakya
 */
class trackComp implements Comparator<Track> {
    @Override
    public int compare(Track t1, Track t2) {
        if (t1.getRatingValue() < t2.getRatingValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
