package com.fau.tunetrends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Track;
import model.TrackList;
import model.UserGroup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MenuScreen extends Activity {
	
	// This is the Adapter being used to display the list's data
    private ListView lv;
   

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_menu_screen);
		//List <Track> trackList = new ArrayList<Track>();
		TrackList trackList = UserGroup.getTrackList();
		//trackList.addSong(new Track("foo", "bar"));
		//trackList.addSong(new Track("foo2", "bar2"));
		
		List<Map<String, String>> song = new ArrayList<Map<String, String>>();
		for (int i = 0; i < trackList.countSongs(); i++)
		{
			Map<String, String> datum = new HashMap<String, String>(2);
			datum.put("title", trackList.getTrackList().get(i).getTitle());
			datum.put("artist",trackList.getTrackList().get(i).getArtist());
			song.add(datum);
		}
		/*
		for (Track t : trackList)
		{
			Map<String, String> datum = new HashMap<String, String>(2);
			datum.put("title", t.getTitle());
			datum.put("artist", t.getArtist());
			song.add(datum);
		}
		*/
		
		SimpleAdapter adapter = new SimpleAdapter(this, song,
                android.R.layout.simple_list_item_2,
                new String[] {"title", "artist"},
                new int[] {android.R.id.text1,
                           android.R.id.text2});
		
		lv = (ListView) findViewById(R.id.trackList);
        lv.setAdapter(adapter); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_screen, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.item2:
	        Intent intent = new Intent(this, AddSong.class);
	        this.startActivity(intent);
	        break;
		default:
	        return super.onOptionsItemSelected(item);
		}
		/*
		if (item.getItemId() == R.id.item2)
		{
			Intent i = new Intent(getApplication(), AddSong.class);
			//startActivity(i);
			//finish();
			
		}
		return (super.onOptionsItemSelected(item));*/
		return true;
	}

}
