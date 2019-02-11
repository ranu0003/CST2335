package com.example.myapplication;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {

    ImageButton mImageButton;
    EditText text;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileactivity);

        Intent login = getIntent();
        String msg=login.getStringExtra("defaultEmail");
        text=findViewById(R.id.email);
        text.setText(msg);


        mImageButton = (ImageButton) findViewById(R.id.profileImageButton);


         mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        Log.e(ACTIVITY_NAME,"in function: onCreate()");
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.e(ACTIVITY_NAME,"in function: onStart()");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.e(ACTIVITY_NAME,"in function: onResume()");


    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.e(ACTIVITY_NAME,"in function: onPause()");

    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.e(ACTIVITY_NAME,"in function: onStop()");

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e(ACTIVITY_NAME,"in function: onDestroy()");

    }
}