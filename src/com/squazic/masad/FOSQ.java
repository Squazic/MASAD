package com.squazic.masad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FOSQ extends Activity {
	
	private ArrayList<Integer> fosq_l = new ArrayList<Integer>();
	private TextView q;
	private Button b0, b1, b2, b3, b4;
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fosq);
		
		q = (TextView)findViewById(R.id.fosqQ); 
		b0 = (Button)findViewById(R.id.fosq0); 
		b1 = (Button)findViewById(R.id.fosq1); 
		b2 = (Button)findViewById(R.id.fosq2); 
		b3 = (Button)findViewById(R.id.fosq3); 
		b4 = (Button)findViewById(R.id.fosq4); 
	} 
	/**
	 * FOSQ Button Selection
	 * @param v
	 */
	public void selectFosq0(View v) {
		fosq_l.add(0);
		changeQ();
	} 

	public void selectFosq1(View v) {
		fosq_l.add(1);
		changeQ();
	} 

	public void selectFosq2(View v) {
		fosq_l.add(2);
		changeQ();
	} 

	public void selectFosq3(View v) {
		fosq_l.add(3);
		changeQ();
	} 
	
	public void selectFosq4(View v) {
		fosq_l.add(4);
		changeQ();
	}
	
	public void changeQ() {
		if (fosq_l.size() == 1)
			q.setText(R.string.fosq_q2);
		else if (fosq_l.size() == 2)
			q.setText(R.string.fosq_q3);
		else if (fosq_l.size() == 3)
			q.setText(R.string.fosq_q4);
		else if (fosq_l.size() == 4)
			q.setText(R.string.fosq_q5);
		else if (fosq_l.size() == 5)
			q.setText(R.string.fosq_q6);
		else if (fosq_l.size() == 6)
			q.setText(R.string.fosq_q7);
		else if (fosq_l.size() == 7)
			q.setText(R.string.fosq_q8);
		else if (fosq_l.size() == 8)
			q.setText(R.string.fosq_q9);
		else if (fosq_l.size() == 9)
			q.setText(R.string.fosq_q10);
		else if (fosq_l.size() == 10)
			q.setText(R.string.fosq_q11);
		else if (fosq_l.size() == 11)
			q.setText(R.string.fosq_q12);
		else if (fosq_l.size() == 12)
			q.setText(R.string.fosq_q13);
		else if (fosq_l.size() == 13)
			q.setText(R.string.fosq_q14);
		else if (fosq_l.size() == 14)
			q.setText(R.string.fosq_q15);
		else if (fosq_l.size() == 15)
			q.setText(R.string.fosq_q16);
		else if (fosq_l.size() == 16)
			q.setText(R.string.fosq_q17);
		else if (fosq_l.size() == 17)
			q.setText(R.string.fosq_q18);
		else if (fosq_l.size() == 18)
			q.setText(R.string.fosq_q19);
		else if (fosq_l.size() == 19)
			q.setText(R.string.fosq_q20);
		else if (fosq_l.size() == 20)
			q.setText(R.string.fosq_q21);
		else if (fosq_l.size() == 21)
			q.setText(R.string.fosq_q22);
		else if (fosq_l.size() == 22)
			q.setText(R.string.fosq_q23);
		else if (fosq_l.size() == 23)
			q.setText(R.string.fosq_q24);
		else if (fosq_l.size() == 24)
			q.setText(R.string.fosq_q25);
		else if (fosq_l.size() == 25) {
			q.setText(R.string.fosq_q26);
			b0.setText(R.string.fosqBut0Alt);
			b1.setText(R.string.fosqBut1Alt);
			b2.setText(R.string.fosqBut2Alt);
			b3.setText(R.string.fosqBut3Alt);
			b4.setText(R.string.fosqBut4Alt);
		}
		else if (fosq_l.size() == 26){
			q.setText(R.string.fosq_q27);
			b0.setText(R.string.fosqBut0);
			b1.setText(R.string.fosqBut1);
			b2.setText(R.string.fosqBut2);
			b3.setText(R.string.fosqBut3);
			b4.setText(R.string.fosqBut4);
		}
		else if (fosq_l.size() == 27)
			q.setText(R.string.fosq_q28);
		else if (fosq_l.size() == 28)
			q.setText(R.string.fosq_q29);
		else if (fosq_l.size() == 29)
			q.setText(R.string.fosq_q30);
		else {
			String fosq = "";
			for (int i = 0; i < fosq_l.size(); i++) {
				fosq += "," + fosq_l.get(i);
			}
			String date = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date());
			byte[] content = ("FOSQ," + sum(fosq_l) + "," + date + fosq + "\n").getBytes();
			writeExternalStorage("patient_data.txt", content);
			System.out.println(fosq + sum(fosq_l));
			Intent nextScreen = new Intent(getApplicationContext(), CameraIntro.class);
			startActivity(nextScreen);
		}
	}
	
	public Integer sum(ArrayList<Integer> list) {
	     Integer sum = 0; 
	     for (Integer i:list)
	         sum = sum + i;
	     return sum;
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
	    		File file = new File(dir, "patient_data.txt");
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