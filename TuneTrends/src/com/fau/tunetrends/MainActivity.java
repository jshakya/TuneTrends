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

/**
 * This is the login screen. Contains input fields for the username and password.
 * Contains two buttons for login and register.
 * @author Mike
 * @author jshakya
 */
public class MainActivity extends Activity {

    TextView emailInput;
    TextView passwordInput;

    static UserGroup curUserGroup;
    String FILENAME = "data.dat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (curUserGroup == null) {
            curUserGroup = new UserGroup();
            this.loadFile();
        }
        this.saveFile();

        Button submitBtn = (Button) findViewById(R.id.submitBtn);
        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        // Validating Password
        emailInput = (TextView) findViewById(R.id.emailInput);
        passwordInput = (TextView) findViewById(R.id.passwordInput);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (isUserInDB() != null) {
                    Intent i = new Intent(getApplicationContext(),
                            MenuScreen.class);
                    i.putExtra("user", isUserInDB());
                    MenuScreen.signedUser = null;
                    startActivity(i);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

    }

    /**
     * Save file when instance is destroyed
     * precondition: none
     * postcondition: information is saved before closing
     */
    protected void onDestroy() {
        super.onDestroy();
        saveFile();
    }

    /**
     * Save file when instance is stopped, like when hitting back or leaving the screen.
     * precondition: none
     * postcondition: information is saved before closing
     */
    protected void onStop() {
        super.onStop();
        saveFile();
    }

    /**
     * Authentication function returning a user to be used in Menu Screen
     * precondition: curUserGroup must be initialized else it will crash
     * postcondition: returns the matching user
     * @return A user in the database matching email and password, a new user, or null if not found.
     */
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

    /**
     * Saves the object to the specified filename
     * Precondition: curUserGroup must exist to prevent a crash
     * Postcondition: data.dat is created and app data is saved inside
     */
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

    /**
     * Reads from the data.dat file to load the curUserGroup
     * precondition: data.dat must exist
     * postcondition: information in data.dat is saved in curUserGroup
     */
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
