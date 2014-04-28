package com.fau.tunetrends;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.User;

/**
 * The register screen. Allows new user to register for use of the app, with name, email and password.
 * Use of strategy pattern through inheriting Activity
 *
 * @author Mike
 * @author jshakya
 */
public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //These are the UI elements from the XML
        Button registerBtn = (Button) findViewById(R.id.registerUserBtn);
        final EditText fName = (EditText) findViewById(R.id.fName);
        final EditText lName = (EditText) findViewById(R.id.lName);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);

        //Anonymous on click listener for the register button
        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String fNameString = fName.getText().toString();
                String lNameString = lName.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                if (userExists(emailString)) {
                    Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_LONG).show();
                } else {
                    User registeredUser = new User(fNameString, lNameString, emailString, passwordString);
                    MainActivity.curUserGroup.addUser(registeredUser);
                    saveObject();
                    finish();
                }
            }
        });

    }

    /**
     * Checks if the username has already been used
     * precondition: curUserGroup exists
     * postcondition: true or false value returned
     * @param tempEmail the username entered in the textfield
     * @return true if the user is found, false if not
     */
    private Boolean userExists(String tempEmail) {
        for (User temp : MainActivity.curUserGroup) {
            if (tempEmail.equals(temp.getEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the object to the specified filename
     * Precondition: curUserGroup must exist to prevent a crash
     * Postcondition: data.dat is created and app data is saved inside
     */
    public void saveObject() {
        try {
            FileOutputStream fos = null;
            fos = openFileOutput("data.dat", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(MainActivity.curUserGroup);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
