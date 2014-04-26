package com.fau.tunetrends;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.security.acl.LastOwnerException;

import model.Track;
//import model.TrackList;
//import model.User;
//import model.UserGroup;
import android.os.Bundle;
import android.app.Activity;
//import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSong extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        final Button addSong = (Button) findViewById(R.id.addSong);
        final EditText title = (EditText) findViewById(R.id.title);
        final EditText artist = (EditText) findViewById(R.id.artist);

        addSong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (title.getText().length() != 0)
                    if (artist.getText().length() != 0) {
//					Track track = new Track(title.getText().toString(), artist
//							.getText().toString());
                        MenuScreen.curUser.getTrackList().addSong(new Track(title.getText().toString(), artist
                                .getText().toString()));

                        Intent i = new Intent(getApplicationContext(),
                                MenuScreen.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Fields empty",
                                Toast.LENGTH_LONG).show();
                    }
                else {
                    Toast.makeText(getApplicationContext(), "Fields empty",
                            Toast.LENGTH_LONG).show();
                }
                onDestroy();
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
