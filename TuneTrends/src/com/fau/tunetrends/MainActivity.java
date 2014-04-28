package com.fau.tunetrends;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.User;
import model.UserGroup;

public class MainActivity extends Activity {

    TextView emailInput;
    TextView passwordInput;

    static UserGroup curUserGroup;// = new UserGroup();
    String FILENAME = "data.dat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //JEBIN GITTEST
        if (curUserGroup == null) {
            curUserGroup = new UserGroup();
            this.loadFile();
        }
        //constant saver
        this.saveFile();

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
                if (isUserInDB() != null) {
                    Intent i = new Intent(getApplicationContext(),
                            MenuScreen.class);
                    i.putExtra("user", isUserInDB());
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

    protected void onDestroy() {
        super.onDestroy();
        saveFile();
    }

    protected void onStop() {
        super.onStop();
        saveFile();
    }

    private User isUserInDB() {
        for (User temp : MainActivity.curUserGroup) {
            if (emailInput.getText().toString().equals(temp.getEmail()) && passwordInput.getText().toString().equals(temp.getPassword())) {
                return temp;
            }
        }

        if (emailInput.getText().toString().equals("admin")
                && passwordInput.getText().toString().equals("pass"))
            return new User("FirstAdmin", "LastAdmin", "admin", "pass");
        else
            Toast.makeText(
                    getApplicationContext(),
                    "Access Denied for user:\n" + emailInput.getText().toString(),
                    Toast.LENGTH_LONG).show();
        return null;
    }


    public void saveFile() {
        try {
            FileOutputStream fos = null;
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(curUserGroup);
            fos.close();
            Toast.makeText(getApplicationContext(), "Saving AppData", Toast.LENGTH_LONG)
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile() {
        try {

//			File file = new File(getFilesDir(), FILENAME);
            FileInputStream fis = new FileInputStream(new File(getFilesDir(), FILENAME));
            ObjectInputStream in = new ObjectInputStream(fis);
            curUserGroup.setUserGroup((UserGroup) in.readObject());
            fis.close();
            Toast.makeText(getApplicationContext(), "Loading AppData", Toast.LENGTH_LONG)
                    .show();
        } catch (IOException e) {
            //to check errors on serialization
            e.printStackTrace();
            saveFile();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
