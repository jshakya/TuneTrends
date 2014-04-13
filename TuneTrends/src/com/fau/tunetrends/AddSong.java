package com.fau.tunetrends;

import model.Track;
import model.User;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSong extends Activity {
	
	Button addSong = (Button) findViewById(R.id.addSong);
	EditText title = (EditText) findViewById(R.id.title);
	EditText artist = (EditText) findViewById(R.id.artist);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_song);
		
		addSong.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Track track = new Track (title.getText().toString(), artist.getText().toString());
				User currentUser = new User(); //How Do I get the current user????
				currentUser.getGroup().getTrackList().addSong(track);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_song, menu);
		return true;
	}

}
