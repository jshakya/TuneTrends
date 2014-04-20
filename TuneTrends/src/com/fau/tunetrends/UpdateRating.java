package com.fau.tunetrends;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class UpdateRating extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_rating);
		int position = 0;
		
		final Bundle extras = getIntent().getExtras();
		if (extras != null)
		{
			position = (int) extras.getLong("position");
			Toast.makeText(getApplicationContext(),
					String.valueOf(position), Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(getApplicationContext(),
					"error", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_rating, menu);
		return true;
	}

}
