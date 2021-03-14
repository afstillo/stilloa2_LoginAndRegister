package com.example.stilloa2_loginandregister;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

//RegistrationActivity for registering new users.

public class RegisterActivity extends AppCompatActivity {
    EditText r_firstName, r_lastName, r_birthday, r_email, r_password, r_confirmPassword;
    TextView r_errorMessage;
    String firstName, lastName, birthday, email, password, confirmPassword, errors;
    /*
    r_firstName = EditText for entering the first name of the user.
    r_lastName = EditText for entering the last name of the user.
    r_birthday = EditText for entering the birthday of the user.
    r_email = EditText for entering the email of the user.
    r_password = EditText for entering the password of the user.
    r_confirmPassword = EditText for re-entering the user's password.
    r_errorMessage = Initially empty TextView for displaying any data errors the user makes.
    firstName = first name of user.
    lastName = last name of user.
    birthday = birthday of user.
    email = email of user.
    password = password of user.
    confirmPassword = second entry of password by user.
    errors = error message to be displayed should the user make any.
     */

    /*
    LIST OF CONSTRAINTS:
    No empty fields allowed.
    firstName is between 3 and 30 characters long.
    birthday is in MM/DD/YYYY format.
    email must be a valid email.
    email must not already be registered.
    password and confirmPassword must be equal.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        r_firstName = findViewById(R.id.r_firstname);
        r_lastName = findViewById(R.id.r_lastname);
        r_birthday = findViewById(R.id.r_date_of_birth);
        r_email = findViewById(R.id.r_email);
        r_password = findViewById(R.id.r_password);
        r_confirmPassword = findViewById(R.id.r_password_confirm);
        r_errorMessage = findViewById(R.id.r_error_message);
        //assign each variable with its respective element.
    }

    public void onClick(View V) throws IOException {
        errors = "Errors:\n";
        //Errors will be output if any errors are present.
        if (r_firstName.getText().toString().equals("") || r_lastName.getText().toString().equals("")
                || r_birthday.getText().toString().equals("") || r_email.getText().
                toString().equals("") || r_password.getText().toString().equals("") ||
                r_confirmPassword.getText().toString().equals("")){
            errors += "Empty fields detected";
        }
        //check for any empty fields

        firstName = r_firstName.getText().toString().trim();
        lastName = r_lastName.getText().toString().trim();
        birthday = r_birthday.getText().toString();
        email = r_email.getText().toString().trim();
        password = r_password.getText().toString();
        confirmPassword = r_confirmPassword.getText().toString();
        //Set each variable equal to its corresponding text field.

        if (!firstName.equals("") && firstName.length() > 30 || firstName.length() < 3) {
            if (!errors.equals("Errors:\n")) {
                errors += ",\n";
            }
            errors += "First name is invalid";
        }
        //Ensure first name is no less than three characters and no more than 30 characters.

        /*
        NOTE: Regarding the following code, there are simpler ways of accompishing this, but I felt
        this way was the best according to the ease and restrictiveness of input. By using regex
        to split the field into individual variables based on a delimiter, each field as well as
        format of input can be easily checked. Most of this code was reused from an assignment
        in CSIT-231 (Systems Programming) translated from C into Java.
         */

        String[] birthday_fields = birthday.split("/");
        //Split the string into 3 fields based around a delimiter.


        /*
        The following three if else statements either deal with one of the fields within the birthday
        entry being left blank, or more of the '/' character than intended being within the birthday
        input that would cause the app to crash if evaluated.

        Could potentially be simplified by using regex instead.
         */
        if (birthday.charAt(0) == '/'|| birthday.charAt(birthday.length() -1 ) == '/')
        {
            if (!errors.equals("Errors:\n")){
                errors+= ",\n";
            }
            errors+="Date is not in MM/DD/YYYY format";
        }
        //Check if the first or last character is a /

        else if (birthday.charAt(birthday.indexOf('/') + 1) == '/'){
            if (!errors.equals("Errors:\n")){
                errors+= ",\n";
            }
            errors+="Date is not in MM/DD/YYYY format";
        }
        //Ensure that the character after the first / is not also a / (i.e. 5//7/1998)

        else if (birthday.charAt(birthday.indexOf('/', (birthday.indexOf('/') + 1)) + 1) == '/')
        {
            if (!errors.equals("Errors:\n")){
                errors+= ",\n";
            }
            errors+="Date is not in MM/DD/YYYY format";
        }
        //Ensure the character after the second / is not also a /.

        else if (birthday_fields.length != 3 && !birthday.equals("")){
            if (!errors.equals("Errors:\n")){
                errors+= ",\n";
            }
            errors+="Date is not in MM/DD/YYYY format";
        }
        //If there are not three fields, notify the user that the date must be in MM/DD/YYYY format.

        else if (!birthday.equals("")){
            if (Integer.parseInt(birthday_fields[0]) > 12 ||
                    Integer.parseInt(birthday_fields[0]) < 1){
                if (!errors.equals("Errors:\n")){
                    errors+= ",\n";
                }
                errors+="Invalid Date";
            }
            //Check for the month field.

            switch(Integer.parseInt(birthday_fields[0])){
                case 1:
                    if ((Integer.parseInt(birthday_fields[1]) > 31 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors+= ",\n";
                        errors+="Invalid Date";
                    }
                case 2:
                    int days;
                    if (Integer.parseInt(birthday_fields[2])%4==0)
                        days = 29;
                    else
                        days=28;
                    if ((Integer.parseInt(birthday_fields[1]) > days || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 3:
                    if ((Integer.parseInt(birthday_fields[1]) > 31 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 4:
                    if ((Integer.parseInt(birthday_fields[1]) > 30 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 5:
                    if ((Integer.parseInt(birthday_fields[1]) > 31 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 6:
                    if ((Integer.parseInt(birthday_fields[1]) > 30 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 7:
                    if ((Integer.parseInt(birthday_fields[1]) > 31 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 8:
                    if ((Integer.parseInt(birthday_fields[1]) > 31 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 9:
                    if ((Integer.parseInt(birthday_fields[1]) > 30 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 10:
                    if ((Integer.parseInt(birthday_fields[1]) > 31 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 11:
                    if ((Integer.parseInt(birthday_fields[1]) > 30 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
                case 12:
                    if ((Integer.parseInt(birthday_fields[1]) > 31 || Integer.parseInt(birthday_fields[1]) < 1)
                            && !errors.equals("Errors:\n") && !errors.contains("Invalid Date")){
                        errors += ",\n";
                        errors += "Invalid Date";
                    }
            }//Lengthy switch statement for the day field. Includes logic for leap years.
        }

        if (!email.equals("") && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!errors.equals("Errors:\n")){
                errors+= ",\n";
            }
            errors+="Email is invalid";
        }
        /*
        Check if the email is valid. The following link was referenced for the if statement:
        https://stackoverflow.com/questions/22348212/android-check-if-an-email-address-is-valid-or-not
         */

        if (MainActivity.emails.contains(email)){
            if (!errors.equals("Errors:\n")){
                errors+= ",\n";
            }
            errors+="Email is already registered";
        }
        //Check if the email is already registered.

        if (!password.equals(confirmPassword)){
            if (!errors.equals("Errors:\n")){
                errors+= ",\n";
            }
            errors+="Passwords are not equal";
        }
        //Check that password and confirmPassword are equal.

        if (errors.equals("Errors:\n")){
            MainActivity.emails.add(email);
            MainActivity.passwords.add(password);
            MainActivity.info.add(firstName);
            MainActivity.info.add(lastName);
            MainActivity.info.add(birthday);
            //Add each value to its respective ArrayList

            FileOutputStream emailWriter = new FileOutputStream(getApplicationContext().getFilesDir().getPath() + "/emails.txt", true);
            emailWriter.write((email + "\n").getBytes());
            emailWriter.close();

            FileOutputStream passwordWriter = new FileOutputStream(getApplicationContext().getFilesDir().getPath() + "/passwords.txt", true);
            passwordWriter.write((password + "\n").getBytes());
            passwordWriter.close();

            FileOutputStream infoWriter = new FileOutputStream(getApplicationContext().getFilesDir().getPath() + "/info.txt", true);
            infoWriter.write((firstName + "\n").getBytes());
            infoWriter.write((lastName + "\n").getBytes());
            infoWriter.write((birthday + "\n").getBytes());
            infoWriter.close();
            //Write each new value to its respective file.

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show();
            finish();
            //Notify the user and close the activity.
        }
        else{
            errors+=".";
            r_errorMessage.setText(errors);
        }
        //If no errors are present, register the user. Otherwise, display the errors.
    }//Method for clicking register.
}
