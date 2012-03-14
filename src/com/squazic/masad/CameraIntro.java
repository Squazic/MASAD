package com.squazic.masad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CameraIntro extends Activity {
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_intro);
	}
	 
	/**
	 * Begins program by calling Epworth
	 * @param v
	 */
	public void beginCamera(View v) {
		Intent nextScreen = new Intent(getApplicationContext(), AndroidCamera.class);
		nextScreen.putExtra("pics", 2);
        startActivity(nextScreen);
	}
}