package com.fau.tunetrends;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Screen that allows users to change the rating for any songs.
 * Also displays the current rating value on screen
 * @author Mike
 * @author jshakya
 */
public class UpdateRating extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rating);
        //UI Elements
        final Button upBtn = (Button) findViewById(R.id.rateUpBtn);
        final Button downBtn = (Button) findViewById(R.id.rateDownBtn);
        final TextView ratingTxt = (TextView) findViewById(R.id.ratingValueTxt);
        final TextView titleTxt = (TextView) findViewById(R.id.titlePlaceholder);
        final TextView artistTxt = (TextView) findViewById(R.id.artistPlaceholder);

        int position = 0;

        //We're passed the position of the song in the tracklist, unloading it here
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = (int) extras.getLong("position");
        } else {
            Toast.makeText(getApplicationContext(), "error receiving track position", Toast.LENGTH_SHORT)
                    .show();
        }
        final int pos = position;
        // Setting the textView
        titleTxt.setText("Title : "
                + MainActivity.curUserGroup.getTrackList().get(pos).getTitle());
        artistTxt.setText("Artist : "
                + MainActivity.curUserGroup.getTrackList().get(pos).getArtist());

        ratingTxt.setText("RATINGS : "
                + String.valueOf(MainActivity.curUserGroup.getTrackList().getTrackList()
                .get(pos).getRatingValue()));
        upBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.curUserGroup.getTrackList().get(pos).upRateValue();
                ratingTxt.setText("RATINGS : "
                        + String.valueOf(MainActivity.curUserGroup.getTrackList()
                        .getTrackList().get(pos).getRatingValue()));
            }

        });
        downBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.curUserGroup.getTrackList().get(pos)
                        .downRateValue();
                ratingTxt.setText("RATINGS : "
                        + String.valueOf(MainActivity.curUserGroup.getTrackList()
                        .getTrackList().get(pos).getRatingValue()));
            }

        });

    }
}
