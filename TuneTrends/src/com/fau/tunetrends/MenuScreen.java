package com.fau.tunetrends;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.User;
import model.UserGroup;


/**
 * This is the screen that displays the songs in a list view. Users can click on the Menu button on their phone to access the add song screen.
 * Click on a row of the table to view the rating and rate the song up or down.
 * Uses Strategy pattern by inheriting from the Activity class
 *
 * @author Mike
 * @author jshakya
 */
public class MenuScreen extends Activity {

    List<Map<String, String>> song;
    String FILENAME = "data.dat";
    static User signedUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (signedUser == null)
            signedUser = (User) getIntent().getSerializableExtra("user");

        setContentView(R.layout.activity_menu_screen);

        MainActivity.curUserGroup.getTrackList().sortSongs();

        changeListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_screen, menu);
        return true;
    }

    @Override
    /**
     * loads tracklist on resume of app
     * precondition: none
     * postcondition: listview is updated with saved songs
     */
    protected void onResume() {

        super.onResume();
        setContentView(R.layout.activity_menu_screen);
        // List <Track> trackList = new ArrayList<Track>();
        MainActivity.curUserGroup.getTrackList().sortSongs();

        changeListView();

    }

    @Override
    /**
     * Performs the appropriate action based on which button in the menu is selected
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1: //Creates a demo list of songs
                MainActivity.curUserGroup.getTrackList().clear();
                MainActivity.curUserGroup.getTrackList().addSong("Happy", "Pharell Williams", 10);
                MainActivity.curUserGroup.getTrackList().addSong("Royals", "Lorde", 8);
                MainActivity.curUserGroup.getTrackList().addSong("Selfie", "Chain Smokers", 6);
                MainActivity.curUserGroup.getTrackList().addSong("All of me", "John Legend", 4);
                MainActivity.curUserGroup.getTrackList().addSong("Dark Horse ", "Katy Perry", 2);
                changeListView();
                break;
            case R.id.item2: //Moves to the Add Song class
                Intent intent = new Intent(this, AddSong.class);
                this.startActivity(intent);
                break;
            case R.id.item3: //Removes the top song
                MainActivity.curUserGroup.getTrackList().removeTopSong();
                changeListView();
                break;
            case R.id.item4: //Removes the bottom song
                MainActivity.curUserGroup.getTrackList().removeBottomSong();
                changeListView();
                break;
            case R.id.item5: //Displays the logged in user information for demonstration
                Toast.makeText(getApplicationContext(),
                        "User : " + signedUser.getEmail()
                                + "\nPassword : " + signedUser.getPassword()
                                + "\nFirst Name : " + signedUser.getFName()
                                + "\nLast Name : " + signedUser.getLName()

                        , Toast.LENGTH_LONG).show();
                changeListView();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * Method for updating the list of songs. Uses a SimpleAdapter to display the list
     * Could maybe consider making this a private method
     * precondition: curUserGroup must exist
     * postcondition: the list is loaded with the songs in tracklist
     */
    public void changeListView() {
        song = new ArrayList<Map<String, String>>();
        for (int i = 0; i < MainActivity.curUserGroup.getTrackList().countSongs(); i++) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("title", MainActivity.curUserGroup.getTrackList().get(i).getTitle());
            datum.put("artist", MainActivity.curUserGroup.getTrackList().get(i).getArtist());
            song.add(datum);
        }

        //Changes the hashmap to display track information in the list view
        SimpleAdapter adapter;
        adapter = new SimpleAdapter(this, song,
                android.R.layout.simple_list_item_2, new String[]{"title",
                "artist"}, new int[]{android.R.id.text1,
                android.R.id.text2});

        adapter.notifyDataSetChanged();
        ListView lv;
        lv = (ListView) findViewById(R.id.trackList);
        lv.setAdapter(adapter);
        lv.invalidateViews();

        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i = new Intent(getApplicationContext(),
                        UpdateRating.class);
                i.putExtra("position", id);
                startActivity(i);
            }
        });
    }

    @Override
    /**
     * Save file when instance is stopped, like when hitting back or leaving the screen.
     * precondition: none
     * postcondition: information is saved before closing
     */
    protected void onStop() {
        super.onStop();
        saveObject();
    }

    @Override
    /**
     * Save file when instance is destroyed
     * precondition: none
     * postcondition: information is saved before closing
     */
    protected void onDestroy() {
        super.onStop();
        saveObject();
    }

    /**
     * Saves the object to the specified filename
     * Precondition: curUserGroup must exist to prevent a crash
     * Postcondition: data.dat is created and app data is saved inside
     */
    public void saveObject() {
        try {
            FileOutputStream fos = null;
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(MainActivity.curUserGroup);
            fos.close();
        } catch (IOException e) {

        }
    }

    /**
     * Reads from the data.dat file to load the curUserGroup
     * precondition: data.dat must exist
     * postcondition: information in data.dat is saved in curUserGroup
     */
    public void loadObject() {
        try {
            Toast.makeText(getApplicationContext(), "Loading AppData",
                    Toast.LENGTH_LONG).show();
            FileInputStream fis = new FileInputStream(new File(getFilesDir(), FILENAME));
            ObjectInputStream in = new ObjectInputStream(fis);
            MainActivity.curUserGroup.setUserGroup((UserGroup) in.readObject());
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
