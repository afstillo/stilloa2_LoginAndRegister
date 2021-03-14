package com.example.stilloa2_loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//LoginActivity for logging existing users in.

public class LoginActivity extends AppCompatActivity {
    EditText email_field, password_field;
    Button login;
    TextView error;
    int index;
    String email, password;
    /*
    email_field = EditText for email entry.
    password_field = EditText for password entry
    login = Button to attempt login.
    error = Initially empty TextView for displaying errors in entries.
    index = index of user data in the emails and passwords ArrayList in the MainActivity.
    Also used to find the data in the info ArrayList through multiples of 3.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_field = findViewById(R.id.email);
        password_field = findViewById(R.id.password);
        login = findViewById(R.id.login1);
        error = findViewById(R.id.l_error_message);
        //matching each variable with its respective element.
    }

    public void onClick(View V){
        /*
        NOTE: there are multiple instances in this method that use return as a means of stopping
        execution. There are most likely better ways of doing this, but this is what I settled on
        for this submission.
         */
        if (email_field.getText().toString().equals("") ||
                password_field.getText().toString().equals("")){
            error.setText(R.string.empty_fields);
            return;
        }
        else{
            email = email_field.getText().toString();
            password = password_field.getText().toString();
        }
        //Check for any empty fields, notify the user and stop execution if detected.

        if(MainActivity.emails.contains(email))
        {
            index = MainActivity.emails.indexOf(email);
        }
        else{
            error.setText(R.string.username_not_found);
            return;
        }
        //Check if the email is registered. If not, notify the user and stop execution.

        if (!password.equals(MainActivity.passwords.get(index))){
            error.setText(R.string.incorrect_password);
            return;
        }
        else{
            Intent intent = new Intent(LoginActivity.this, StubActivity.class);
            intent.putExtra("INDEX", index);
            startActivity(intent);
            finish();
        }
        /*
        Check if the password entered matches that of the user. Notify the user and stop execution
        if so. If not, start the stub of the larger application passing the index of the user.
         */
    }//Method for clicking login.


}
