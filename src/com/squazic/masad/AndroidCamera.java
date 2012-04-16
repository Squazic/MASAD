package com.squazic.masad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
//import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
//import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
//import android.provider.MediaStore.Images.Media;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
//import android.widget.Toast;

public class AndroidCamera extends Activity implements SurfaceHolder.Callback{

 Camera camera;
 SurfaceView surfaceView;
 SurfaceHolder surfaceHolder;
 boolean previewing = false;
 LayoutInflater controlInflater = null;
 int pics;
 
 Button buttonTakePicture;
 
 final int RESULT_SAVEIMAGE = 0;
 private String dt;
 
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.camera);
       
       Bundle extras = getIntent().getExtras();
       if (extras != null) {
    	   pics = extras.getInt("pics");
    	   dt = extras.getString("dt");
       }
       
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      
       getWindow().setFormat(PixelFormat.UNKNOWN);
       surfaceView = (SurfaceView)findViewById(R.id.camerapreview);
       surfaceHolder = surfaceView.getHolder();
       surfaceHolder.addCallback(this);
       surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
      
       controlInflater = LayoutInflater.from(getBaseContext());
       View viewControl = controlInflater.inflate(R.layout.camera_button, null);
       LayoutParams layoutParamsControl
        = new LayoutParams(LayoutParams.FILL_PARENT,
        LayoutParams.FILL_PARENT);
       this.addContentView(viewControl, layoutParamsControl);
      
       buttonTakePicture = (Button)findViewById(R.id.takepicture);
       buttonTakePicture.setOnClickListener(new Button.OnClickListener(){

   @Override
   public void onClick(View arg0) {
    // TODO Auto-generated method stub
    camera.takePicture(myShutterCallback,
      myPictureCallback_RAW, myPictureCallback_JPG);
   }});
      
       LinearLayout layoutBackground = (LinearLayout)findViewById(R.id.background);
       layoutBackground.setOnClickListener(new LinearLayout.OnClickListener(){

   @Override
   public void onClick(View arg0) {
    // TODO Auto-generated method stub

    buttonTakePicture.setEnabled(false);
    camera.autoFocus(myAutoFocusCallback);
   }});
   }
  
   AutoFocusCallback myAutoFocusCallback = new AutoFocusCallback(){

  @Override
  public void onAutoFocus(boolean arg0, Camera arg1) {
   // TODO Auto-generated method stub
   buttonTakePicture.setEnabled(true);
  }};
  
   ShutterCallback myShutterCallback = new ShutterCallback(){

  @Override
  public void onShutter() {
   // TODO Auto-generated method stub
   
  }};
  
 PictureCallback myPictureCallback_RAW = new PictureCallback(){

  @Override
  public void onPictureTaken(byte[] arg0, Camera arg1) {
   // TODO Auto-generated method stub
   
  }};
  
 PictureCallback myPictureCallback_JPG = new PictureCallback(){

  @Override
  public void onPictureTaken(byte[] arg0, Camera arg1) {
   // TODO Auto-generated method stub
   /*Bitmap bitmapPicture
    = BitmapFactory.decodeByteArray(arg0, 0, arg0.length); */
   
   //Uri uriTarget = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());

   OutputStream imageFileOS;
   try {

	    File externalPath = Environment.getExternalStorageDirectory();
	    File dir = new File(externalPath.getAbsolutePath() + "/" + getPackageName());
	    File file;
	    if (pics == 2){
	    	file = new File(dir, dt + "-1.jpg");
	    }
	    else {
	    	file = new File(dir, dt + "-2.jpg");
	    }
	    
	    imageFileOS = new FileOutputStream(file);
	    
    //imageFileOS = getContentResolver().openOutputStream(uriTarget);
    imageFileOS.write(arg0);
    imageFileOS.flush();
    imageFileOS.close();
    
    /*Toast.makeText(AndroidCamera.this,
      "Image saved: " + uriTarget.toString(),
      Toast.LENGTH_LONG).show();*/
    if (pics == 2){
    	Intent nextScreen = new Intent(getApplicationContext(), AndroidCamera.class);
    	nextScreen.putExtra("pics", 1);
		nextScreen.putExtra("dt", dt);
    	startActivity(nextScreen);
    	finish();
    }
    else {
    	Intent nextScreen = new Intent(getApplicationContext(), RecordAudio.class);
		nextScreen.putExtra("dt", dt);
    	startActivity(nextScreen);
    }
    
   } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }

   camera.startPreview();
  }};

 @Override
 public void surfaceChanged(SurfaceHolder holder, int format, int width,
   int height) {
  // TODO Auto-generated method stub
  if(previewing){
   camera.stopPreview();
   previewing = false;
  }
  
  if (camera != null){
   try {
    camera.setPreviewDisplay(surfaceHolder);
    camera.startPreview();
    previewing = true;
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }
 }

 @Override
 public void surfaceCreated(SurfaceHolder holder) {
  // TODO Auto-generated method stub
  camera = Camera.open();
 }

 @Override
 public void surfaceDestroyed(SurfaceHolder holder) {
  // TODO Auto-generated method stub
  camera.stopPreview();
  camera.release();
  camera = null;
  previewing = false;
 }
}