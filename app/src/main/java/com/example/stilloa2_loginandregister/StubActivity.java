package com.example.stilloa2_loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//StubActivity as a placeholder for the larger application.

public class StubActivity extends AppCompatActivity {
    TextView s_welcome, s_info;
    Button s_logout;
    int indexReceived;
    String text;

    /*
    s_welcome = TextView for welcoming the user.
    s_info = TextView for displaying user info.
    s_logout = Button for returning to the MainActivity.
    indexReceived = Index received from the login activity.
    text = String to be displayed in both TextViews. Exists only for concatenation purposes.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stub);

        s_welcome = findViewById(R.id.success);
        s_info = findViewById(R.id.info);
        s_logout = findViewById(R.id.back);
        //assign each variable with its respective element.

        Intent intent = getIntent();
        indexReceived = intent.getIntExtra("INDEX", 0);
        //Load the index from the previous activity.

        text = ("Welcome,\n" + MainActivity.info.get(3 * indexReceived) + " " +
                MainActivity.info.get((3 * indexReceived) + 1) + ".");
        s_welcome.setText(text);

        text = ("Email: " + MainActivity.emails.get(indexReceived) + "\nPassword: " +
                MainActivity.passwords.get(indexReceived) + "\nBirthday: " +
                MainActivity.info.get((3 * indexReceived) + 2));
        s_info.setText(text);
        //Using the index, get data from the ArrayLists in the MainActivity and display it to the user.
    }

    public void onClick(View v ){
        finish();
    }//Method for clicking logout.
}