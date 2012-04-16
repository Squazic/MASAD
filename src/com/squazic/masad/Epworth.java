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
//import android.widget.Toast;

public class Epworth extends Activity {
	
	private Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8;
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.epworth);
	 
		addItemsOnSpinner();
	}
	
	// add items into spinner dynamically
	public void addItemsOnSpinner() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.epworth_array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner1.setAdapter(adapter);
		
		spinner2 = (Spinner) findViewById(R.id.spinner2);
	    spinner2.setAdapter(adapter);
		
		spinner3 = (Spinner) findViewById(R.id.spinner3);
	    spinner3.setAdapter(adapter);
		
		spinner4 = (Spinner) findViewById(R.id.spinner4);
	    spinner4.setAdapter(adapter);
		
		spinner5 = (Spinner) findViewById(R.id.spinner5);
	    spinner5.setAdapter(adapter);
		
		spinner6 = (Spinner) findViewById(R.id.spinner6);
	    spinner6.setAdapter(adapter);
		
		spinner7 = (Spinner) findViewById(R.id.spinner7);
	    spinner7.setAdapter(adapter);
		
		spinner8 = (Spinner) findViewById(R.id.spinner8);
	    spinner8.setAdapter(adapter);
	}
	 /*
	public void addListenerOnSpinnerItemSelection() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}*/
	 
	// Gets the selected dropdown list value
	public void evalScore(View v) {
	 
		//String diagnosis;
		String date = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date());
		int epworth_score = spinner1.getSelectedItemPosition() + spinner2.getSelectedItemPosition() + 
				spinner3.getSelectedItemPosition() + spinner4.getSelectedItemPosition() + 
				spinner5.getSelectedItemPosition() + spinner6.getSelectedItemPosition() + 
				spinner7.getSelectedItemPosition() + spinner8.getSelectedItemPosition();
		String ep_str = spinner1.getSelectedItemPosition() + "," + spinner2.getSelectedItemPosition() +  "," + 
				spinner3.getSelectedItemPosition() +  "," + spinner4.getSelectedItemPosition() +  "," + 
				spinner5.getSelectedItemPosition() +  "," + spinner6.getSelectedItemPosition() +  "," + 
				spinner7.getSelectedItemPosition() +  "," + spinner8.getSelectedItemPosition();
		/*
		if (epworth_score > 10) {
			diagnosis = this.getString(R.string.risk);
		}
		else {
			diagnosis = this.getString(R.string.healthy);
		}
		Toast.makeText(Epworth.this,
				"Your Epworth Score is: " + epworth_score + "\n" + diagnosis,
				Toast.LENGTH_LONG).show();*/
		byte[] content = ("Epworth," + epworth_score + "," + date + "," + ep_str + "\n").getBytes();
		String dt = android.text.format.DateFormat.format("yyyy-MM-dd-hh-mm", new java.util.Date()).toString();
		String qFile = dt + ".txt";
		writeExternalStorage(qFile, content);
		
		Intent nextScreen = new Intent(getApplicationContext(), MAP.class);
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