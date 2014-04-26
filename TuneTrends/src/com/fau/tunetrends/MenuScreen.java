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

import model.TrackList;
import model.UserGroup;

public class MenuScreen extends Activity {

    List<Map<String, String>> song;
    static UserGroup curUser;// = new UserGroup();
    String FILENAME = "data.dat";
    //TrackList trackList = curUser.getTrackList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_screen);
        // List <Track> trackList = new ArrayList<Track>();

        if (curUser == null) {
            curUser = new UserGroup();
            loadObject();
        }
        //loadObject();

        curUser.getTrackList().sortSongs();

        changeListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_screen, menu);
        return true;
    }

    @Override
    protected void onResume() {

        super.onResume();
        setContentView(R.layout.activity_menu_screen);
        // List <Track> trackList = new ArrayList<Track>();
        curUser.getTrackList().sortSongs();

        changeListView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                curUser.getTrackList().clear();
                curUser.getTrackList().addSong("Happy", "Pharell Williams", 10);
                curUser.getTrackList().addSong("Royals", "Lorde", 8);
                curUser.getTrackList().addSong("Selfie", "Chain Smokers", 6);
                curUser.getTrackList().addSong("All of me", "John Legend", 4);
                curUser.getTrackList().addSong("Dark Horse ", "Katy Perry", 2);
                changeListView();
                break;
            case R.id.item2:
                Intent intent = new Intent(this, AddSong.class);
                this.startActivity(intent);
                break;
            case R.id.item3:
                curUser.getTrackList().removeTopSong();
                changeListView();
                break;
            case R.id.item4:
                curUser.getTrackList().removeBottomSong();
                changeListView();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        /*
         * if (item.getItemId() == R.id.item2) { Intent i = new
		 * Intent(getApplication(), AddSong.class); //startActivity(i);
		 * //finish();
		 * 
		 * } return (super.onOptionsItemSelected(item));
		 */
        return true;
    }

    public void changeListView() {
        song = new ArrayList<Map<String, String>>();
        for (int i = 0; i < curUser.getTrackList().countSongs(); i++) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("title", curUser.getTrackList().get(i).getTitle());
            datum.put("artist", curUser.getTrackList().get(i).getArtist());
            song.add(datum);
        }
        /*
         * for (Track t : trackList) { Map<String, String> datum = new
		 * HashMap<String, String>(2); datum.put("title", t.getTitle());
		 * datum.put("artist", t.getArtist()); song.add(datum); }
		 */

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
                // When clicked, show a toast with the TextView text
                // String number = String.valueOf(id);
                // Toast.makeText(getApplicationContext(),
                // number, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),
                        UpdateRating.class);
                i.putExtra("position", id);
                startActivity(i);
                // finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "Saving App", Toast.LENGTH_LONG)
                .show();

        saveObject();
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "Destroy App", Toast.LENGTH_LONG)
                .show();
        saveObject();


    }

    public void saveObject() {
        try {
            FileOutputStream fos = null;
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(curUser.getTrackList());
            fos.close();
        } catch (IOException e) {

        }
    }

    public void loadObject() {
        try {
            Toast.makeText(getApplicationContext(), "Loading App",
                    Toast.LENGTH_LONG).show();
//			File file = new File(getFilesDir(), FILENAME);
            FileInputStream fis = new FileInputStream(new File(getFilesDir(), FILENAME));
            ObjectInputStream in = new ObjectInputStream(fis);
            curUser.getTrackList().setTrackList((TrackList) in.readObject());
            fis.close();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
