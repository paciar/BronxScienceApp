package com.example.android.bronxscienceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        This should house the log-in/sign-up features of the app, along with any global variables.
        For now, I'll replace it with an intent to start HomeActivity.
         */

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}