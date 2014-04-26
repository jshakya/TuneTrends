package com.fau.tunetrends;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.TrackList;
import model.UserGroup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView emailInput;
    TextView passwordInput;

    static UserGroup curUserGroup;// = new UserGroup();
    String FILENAME = "data.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadFile();

        Button submitBtn = (Button) findViewById(R.id.submitBtn);
        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        // Validating Password
        emailInput = (TextView) findViewById(R.id.emailInput);
        passwordInput = (TextView) findViewById(R.id.passwordInput);
        // final Boolean flag;

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // JBN Remove if part to remove authorization
                if (checkCredentials()) {
                    Intent i = new Intent(getApplicationContext(),
                            MenuScreen.class);
                    startActivity(i);
                    // finish();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
                // finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private Boolean checkCredentials() {
        if (emailInput.getText().toString().equals("admin")
                && passwordInput.getText().toString().equals("pass"))
            return true;
        else
            Toast.makeText(
                    getApplicationContext(),
                    "Access Denied\n" + emailInput.getText().toString()
                            + passwordInput.getText().toString(),
                    Toast.LENGTH_LONG).show();
        return false;
    }

    public void saveFile() {
        try {
            FileOutputStream fos = null;
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(curUserGroup.getTrackList());
            fos.close();
        } catch (IOException e) {

        }
    }

    public void loadFile() {
        try {
            Toast.makeText(getApplicationContext(), "Loading App",
                    Toast.LENGTH_LONG).show();
//			File file = new File(getFilesDir(), FILENAME);
            FileInputStream fis = new FileInputStream(new File(getFilesDir(), FILENAME));
            ObjectInputStream in = new ObjectInputStream(fis);
            curUserGroup.getTrackList().setTrackList((TrackList) in.readObject());
            fis.close();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
