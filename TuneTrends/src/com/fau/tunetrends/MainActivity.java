package com.fau.tunetrends;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView emailInput;
	TextView passwordInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
				// TODO Auto-generated method stub
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
}
