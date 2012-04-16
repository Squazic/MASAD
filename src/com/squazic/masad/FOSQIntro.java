package com.squazic.masad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FOSQIntro extends Activity {
	
	private String dt;
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fosq_intro);
	       
	    Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	    	dt = extras.getString("dt");
	    }
	}
	 
	/**
	 * Begins program by calling Epworth
	 * @param v
	 */
	public void beginFOSQ(View v) {
		Intent nextScreen = new Intent(getApplicationContext(), FOSQ.class);
		nextScreen.putExtra("dt", dt);
        startActivity(nextScreen);
	}
}