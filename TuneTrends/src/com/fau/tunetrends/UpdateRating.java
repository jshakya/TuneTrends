package com.fau.tunetrends;

import model.UserGroup;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateRating extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_rating);
		final Button upBtn= (Button) findViewById(R.id.rateUpBtn);
		final Button downBtn = (Button) findViewById(R.id.rateDownBtn);
		final TextView ratingTxt = (TextView) findViewById(R.id.ratingValueTxt);
		
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
		final int pos = position;
		//Setting the textView
		ratingTxt.setText(String.valueOf(UserGroup.getTrackList().getTrackList().get(pos).getRatingValue()));
		upBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UserGroup.getTrackList().getTrackList().get(pos).upRateValue();
				ratingTxt.setText(String.valueOf(UserGroup.getTrackList().getTrackList().get(pos).getRatingValue()));
			}
		
		});
		downBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UserGroup.getTrackList().getTrackList().get(pos).downRateValue();
				ratingTxt.setText(String.valueOf(UserGroup.getTrackList().getTrackList().get(pos).getRatingValue()));
			}
		
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_rating, menu);
		return true;
	}

}
