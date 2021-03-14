package com.example.stilloa2_loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
NOTE: I chose to mame my project able to save data over multiple instances, and as such there
are multiple cases of File IO being used in this project. I am aware the best way of doing this
would be a database, but due to the close proximity of the due date combined with my not knowing
how to set up an SQLite database in Java, or Firebase, I decided to use text files instead. Note
that these files are internal to the device being used and are (unfortunately) in a protected folder
in order to to prevent users from accidentally deleting said files.

One of the only few oversights is that data for EditTexts before hitting a button on the login
or registration activities is lost on orientation change.

Tested on a Pixel 2 and a Pixel C.
 */

//MainActivity for navigation to registration or login.

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> emails = new ArrayList<String>();
    public static ArrayList<String> passwords = new ArrayList<String>();
    public static ArrayList<String> info = new ArrayList<String>();
    /*
    emails = list of emails of registered users.
    passwords = list of passwords of registered users.
    info = list of other miscellaneous info of registered users.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File emailFile = new File(getApplicationContext().getFilesDir().getPath() + "/emails.txt");
        File passwordFile = new File(getApplicationContext().getFilesDir().getPath() + "/passwords.txt");
        File infoFile = new File(getApplicationContext().getFilesDir().getPath() + "/info.txt");
        /*
        emailFile = file containing user emails.
        passwordFile = file containing user passwords.
        infoFile = file containing miscellaneous user info.
         */

        try {
            if (emailFile.createNewFile())
                System.out.println("File created.");
            else
                System.out.println("File exists.");
            if (passwordFile.createNewFile())
                System.out.println("File created.");
            else
                System.out.println("File exists.");
            if (infoFile.createNewFile())
                System.out.println("File created.");
            else
                System.out.println("File exists.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //For each file, create it if it does not exist.

        try{
            Scanner emailScanner = new Scanner(emailFile);
            Scanner passwordScanner = new Scanner(passwordFile);
            Scanner infoScanner = new Scanner(infoFile);

            while (emailScanner.hasNextLine()) {
                emails.add(emailScanner.nextLine());
            }

            while (passwordScanner.hasNextLine()) {
                passwords.add(passwordScanner.nextLine());
            }

            while (infoScanner.hasNextLine()) {
                info.add(infoScanner.nextLine());
            }

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        //For each file, read each line into its respective array.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void l_onClick(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    //Method for clicking the login button.

    public void r_onClick(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    //Method for clicking the register button.
}