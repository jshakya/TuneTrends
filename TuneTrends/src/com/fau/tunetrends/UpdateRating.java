package com.fau.tunetrends;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;

//import model.UserGroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import android.content.Context;

public class UpdateRating extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rating);
        final Button upBtn = (Button) findViewById(R.id.rateUpBtn);
        final Button downBtn = (Button) findViewById(R.id.rateDownBtn);
        final TextView ratingTxt = (TextView) findViewById(R.id.ratingValueTxt);
        final TextView titleTxt = (TextView) findViewById(R.id.titlePlaceholder);
        final TextView artistTxt = (TextView) findViewById(R.id.artistPlaceholder);

        int position = 0;

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = (int) extras.getLong("position");
            Toast.makeText(getApplicationContext(), String.valueOf(position),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT)
                    .show();
        }
        final int pos = position;
        // Setting the textView
        titleTxt.setText("Title : "
                + MenuScreen.curUserGroup.getTrackList().get(pos).getTitle());
        artistTxt.setText("Artist : "
                + MenuScreen.curUserGroup.getTrackList().get(pos).getArtist());

        ratingTxt.setText("RATINGS : "
                + String.valueOf(MenuScreen.curUserGroup.getTrackList().getTrackList()
                .get(pos).getRatingValue()));
        upBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MenuScreen.curUserGroup.getTrackList().get(pos).upRateValue();
                ratingTxt.setText("RATINGS : "
                        + String.valueOf(MenuScreen.curUserGroup.getTrackList()
                        .getTrackList().get(pos).getRatingValue()));
            }

        });
        downBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MenuScreen.curUserGroup.getTrackList().get(pos)
                        .downRateValue();
                ratingTxt.setText("RATINGS : "
                        + String.valueOf(MenuScreen.curUserGroup.getTrackList()
                        .getTrackList().get(pos).getRatingValue()));
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
