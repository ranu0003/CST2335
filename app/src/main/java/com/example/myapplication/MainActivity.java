package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.util.Log;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.ProfileActivity;

public class MainActivity extends Activity {

    EditText editText1;

    Button loginButton;

    Button login;

    String email;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab3);

        editText1 = findViewById(R.id.editText1);


        editText1 =(EditText) findViewById(R.id.editText1);
        login = (Button) findViewById(R.id.loginButton);

        prefs = getSharedPreferences("name", Context.MODE_PRIVATE);
        String userName = prefs.getString("defaultEmail","");
        editText1.setText(userName);


        loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getApplicationContext(),ProfileActivity.class);
                it.putExtra("defaultEmail",editText1.getText().toString());
                startActivity(it);
            }
        });

        login.setOnClickListener((v) ->{
            Intent it = new Intent(getApplicationContext(),ProfileActivity.class);
            it.putExtra("defaultEmail",editText1.getText().toString());
            startActivity(it);
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor edit = prefs.edit();

        email = editText1.getText().toString();

        edit.putString("defaultEmail",email);

        edit.apply();
    }
}