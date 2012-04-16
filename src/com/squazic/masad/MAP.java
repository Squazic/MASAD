package com.squazic.masad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;

public class MAP extends Activity {
	
	private Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, 
					spinner7, spinner8, spinner9, spinner10, spinner11, spinner12, spinner13;
	private String dt = "";
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
	       
	    Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	    	dt = extras.getString("dt");
	    }
	 
		addItemsOnSpinner();
	}
	
	// add items into spinner dynamically
	public void addItemsOnSpinner() {
		spinner1 = (Spinner) findViewById(R.id.mapSpin1);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.map_array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner1.setAdapter(adapter);
		
		spinner2 = (Spinner) findViewById(R.id.mapSpin2);
	    spinner2.setAdapter(adapter);
		
		spinner3 = (Spinner) findViewById(R.id.mapSpin3);
	    spinner3.setAdapter(adapter);
		
		spinner4 = (Spinner) findViewById(R.id.mapSpin4);
	    spinner4.setAdapter(adapter);
		
		spinner5 = (Spinner) findViewById(R.id.mapSpin5);
	    spinner5.setAdapter(adapter);
		
		spinner6 = (Spinner) findViewById(R.id.mapSpin6);
	    spinner6.setAdapter(adapter);
		
		spinner7 = (Spinner) findViewById(R.id.mapSpin7);
	    spinner7.setAdapter(adapter);
		
		spinner8 = (Spinner) findViewById(R.id.mapSpin8);
	    spinner8.setAdapter(adapter);
		
		spinner9 = (Spinner) findViewById(R.id.mapSpin9);
	    spinner9.setAdapter(adapter);
		
		spinner10 = (Spinner) findViewById(R.id.mapSpin10);
	    spinner10.setAdapter(adapter);
		
		spinner11 = (Spinner) findViewById(R.id.mapSpin11);
	    spinner11.setAdapter(adapter);
		
		spinner12 = (Spinner) findViewById(R.id.mapSpin12);
	    spinner12.setAdapter(adapter);
		
		spinner13 = (Spinner) findViewById(R.id.mapSpin13);
	    spinner13.setAdapter(adapter);
	}
	 
	// Gets the selected dropdown list value
	public void evalMap(View v) {
	 
		String date = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date());
		int map_score = spinner1.getSelectedItemPosition() + spinner2.getSelectedItemPosition() +
				spinner3.getSelectedItemPosition() + spinner4.getSelectedItemPosition() + 
				spinner5.getSelectedItemPosition() + spinner6.getSelectedItemPosition() + 
				spinner7.getSelectedItemPosition() + spinner8.getSelectedItemPosition() + 
				spinner9.getSelectedItemPosition() + spinner10.getSelectedItemPosition() + 
				spinner11.getSelectedItemPosition() + spinner12.getSelectedItemPosition() + 
				spinner13.getSelectedItemPosition();
		String str = spinner1.getSelectedItemPosition() + "," + spinner2.getSelectedItemPosition() + "," +
						spinner3.getSelectedItemPosition() + "," + spinner4.getSelectedItemPosition() + "," + 
						spinner5.getSelectedItemPosition() + "," + spinner6.getSelectedItemPosition() + "," + 
						spinner7.getSelectedItemPosition() + "," + spinner8.getSelectedItemPosition() + "," + 
						spinner9.getSelectedItemPosition() + "," + spinner10.getSelectedItemPosition() + "," + 
						spinner11.getSelectedItemPosition() + "," + spinner12.getSelectedItemPosition() + "," + 
						spinner13.getSelectedItemPosition();
		  
		byte[] content = ("MAP," + map_score + "," + date + "," + str + "\n").getBytes();
		String qFile = dt + ".txt";
		writeExternalStorage(qFile, content);
		Intent nextScreen = new Intent(getApplicationContext(), FOSQIntro.class);
		nextScreen.putExtra("dt", dt);
        startActivity(nextScreen);
	}
	
	/**
	 * Writes content to external storage with append
	 * 
	 * @param filename - the name of the file to create
	 * @param content - the content to write
	 */
	public void writeExternalStorage(String filename, byte[] content) {
		String packageName = getPackageName();
		File externalPath = Environment.getExternalStorageDirectory();
		File dir = new File(externalPath.getAbsolutePath() + "/" + packageName);		
	    try {
	    	if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
	    		dir.mkdirs();
	    		File file = new File(dir, filename);
	    		FileOutputStream out = new FileOutputStream(file, true);
	    		out.write(content);
	    	}
	    	else {
	    		 FileOutputStream fos = openFileOutput(filename, Context.MODE_APPEND);
	    		 System.out.println(getFilesDir().getAbsolutePath());
	    		 fos.write(content);
	    		 fos.close();
	    	}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}