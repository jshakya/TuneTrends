package model;

import java.io.Serializable;

/**
 * Class for songs/tracks. Title/Artists should not change once created.
 *
 * @author Mike, Jebin
 */
public class Track implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6205619219993159193L;

    /**
     * MDR -- Auto generated constructor. I just set the title/artist to blank strings here. Should consider removing.
     */
    public Track() {
        setTitle(new String());
        setArtist(new String());
        //this.ratingValue = 0;
        setRatingValue(0);
    }

    /**
     * MDR -- Use this constructor to create a new Track. Rating Value will be set to zero
     *
     * @param title  the name of the song
     * @param artist the name of the artist
     */
    public Track(String title, String artist) {
        setTitle(title);
        setArtist(artist);
        setRatingValue(0);
    }

    /**
     * JBN -- Use this constructor to create a new Track. Rating Value will be set to value provided
     *
     * @param title  the name of the song
     * @param artist the name of the artist
     */
    public Track(String title, String artist, int ratingValue) {
        setTitle(title);
        setArtist(artist);
        setRatingValue(ratingValue);
    }

    /**
     * MDR -- Accessor for the song title
     *
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * MDR -- Mutator for the song title
     *
     * @param title new title string
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * MDR -- Accessor for the song artist
     *
     * @return artist
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * MDR -- Mutator for the song artist
     *
     * @param artist new artist string
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * MDR -- Accessor for the rating value
     *
     * @return the rating value
     */
    public int getRatingValue() {
        return this.ratingValue;
    }

    /**
     * MDR -- Mutator for the song rating
     *
     * @param ratingValue new rating value
     */
    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    /**
     * Up rate the ratings
     */
    public void upRateValue() {
        this.ratingValue++;
    }

    /**
     * Down rate the ratings
     */
    public void downRateValue() {
        if (this.ratingValue > 0)
            this.ratingValue--;
    }

    private String title;
    private String artist;
    private int ratingValue;
}
