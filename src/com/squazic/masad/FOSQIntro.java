package com.squazic.masad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FOSQIntro extends Activity {
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fosq_intro);
	}
	 
	/**
	 * Begins program by calling Epworth
	 * @param v
	 */
	public void beginFOSQ(View v) {
		Intent nextScreen = new Intent(getApplicationContext(), FOSQ.class);
        startActivity(nextScreen);
	}
}