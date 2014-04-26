package com.fau.tunetrends;

import model.User;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		Button registerBtn = (Button) findViewById(R.id.registerUserBtn);
		final EditText fName = (EditText) findViewById(R.id.fName);
		final EditText lName = (EditText) findViewById(R.id.lName);
		final EditText email = (EditText) findViewById(R.id.email);
		final EditText password = (EditText) findViewById(R.id.password);

		registerBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String fNameString = fName.getText().toString();
				String lNameString = lName.getText().toString();
				String emailString = email.getText().toString();
				String passwordString = password.getText().toString();

				User registeredUser = new User(fNameString, lNameString, emailString, passwordString);
				// Get values from fields

				// Create new User

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
