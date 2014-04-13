package com.fau.tunetrends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Track;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MenuScreen extends Activity {
	
	// This is the Adapter being used to display the list's data
    private ListView lv;
   

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_screen);
		List <Track> trackList = new ArrayList<Track>();
		trackList.add(new Track("foo", "bar"));
		trackList.add(new Track("foo2", "bar2"));
		
		List<Map<String, String>> song = new ArrayList<Map<String, String>>();
		for (Track t : trackList)
		{
			Map<String, String> datum = new HashMap<String, String>(2);
			datum.put("title", t.getTitle());
			datum.put("artist", t.getArtist());
			song.add(datum);
		}
		
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

}
