package com.squazic.masad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Intro extends Activity {
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
	}
	 
	/**
	 * Begins program by calling Epworth
	 * @param v
	 */
	public void startProg(View v) {
		Intent nextScreen = new Intent(getApplicationContext(), EpworthIntro.class);
        startActivity(nextScreen);
	}
}