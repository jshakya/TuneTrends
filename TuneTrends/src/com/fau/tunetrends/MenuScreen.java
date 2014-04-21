package com.fau.tunetrends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.TrackList;
import model.UserGroup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MenuScreen extends Activity {

	// This is the Adapter being used to display the list's data
	private ListView lv;
	private SimpleAdapter adapter;
	List<Map<String, String>> song;
	TrackList trackList = UserGroup.getTrackList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu_screen);
		// List <Track> trackList = new ArrayList<Track>();

		trackList.sortSongs();

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
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.activity_menu_screen);
		// List <Track> trackList = new ArrayList<Track>();
		trackList.sortSongs();

		changeListView();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item2:
			Intent intent = new Intent(this, AddSong.class);
			this.startActivity(intent);
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
		for (int i = 0; i < trackList.countSongs(); i++) {
			Map<String, String> datum = new HashMap<String, String>(2);
			datum.put("title", trackList.getTrackList().get(i).getTitle());
			datum.put("artist", trackList.getTrackList().get(i).getArtist());
			song.add(datum);
		}
		/*
		 * for (Track t : trackList) { Map<String, String> datum = new
		 * HashMap<String, String>(2); datum.put("title", t.getTitle());
		 * datum.put("artist", t.getArtist()); song.add(datum); }
		 */

		adapter = new SimpleAdapter(this, song,
				android.R.layout.simple_list_item_2, new String[] { "title",
						"artist" }, new int[] { android.R.id.text1,
						android.R.id.text2 });

		adapter.notifyDataSetChanged();
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

}
