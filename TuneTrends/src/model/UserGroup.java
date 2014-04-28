package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Collection of users in an ArrayList. Implements serializable to save/load information
 * @author Mike
 * @author jshakya
 */
public class UserGroup extends ArrayList<User> implements Serializable {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = 8260713332729190808L;

    /**
     * creates the tracklist during initialization
     */
    public UserGroup() {
        this.trackList = new TrackList();
    }

    /**
     * returns the current tracklist. If null, creates a new one.
     * @return tracklist
     */
    public TrackList getTrackList() {
        if (this.trackList == null) {
            trackList = new TrackList();
            /*Remove after Successful Serialization*/
            trackList.addSong("Song2", "Artist 2", 2);
            trackList.addSong("Song5", "Artist 5", 5);
            trackList.addSong("Song3", "Artist 3", 3);
            /*Sample Initialization*/
        }
        return trackList;
    }

    /**
     * get group code. Not using in this version
     * @return
     */
    public int getGroupCode() {
        return this.groupCode;
    }

    /**
     * Set group code. Not using in this version
     * @param groupCode
     */
    public void setGroupCode(int groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * Adds a user to the usergroup
     * @param u
     */
    public void addUser(User u) {
        this.add(u);
    }

    /**
     * It loads the UserGroup from a file and then uses it
     *
     * @param temp
     */
    public void setUserGroup(UserGroup temp) {
        this.clear();
        for (User u : temp) {
            this.add(u);
        }
        this.trackList = temp.trackList;
    }

    private TrackList trackList;
    private int groupCode;
}
