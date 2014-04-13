package com.fau.tunetrends;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;

public class MenuScreen extends ListActivity {
	
	// This is the Adapter being used to display the list's data
    SimpleCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_screen, menu);
		return true;
	}

}
