package com.example.stilloa2_loginandregister;
/*
The following link was used to create the splash screen. Minimal modifications were made, barely
any were necessary. Thus I feel it is necessary to cite it:
https://abhiandroid.com/programming/splashscreen
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

//SplashActivity for displaying a splash screen.

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    //handler = handler for use in the transition to the main activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        },3000); //Show for 3 seconds before going to the MainActivity.
    }
}