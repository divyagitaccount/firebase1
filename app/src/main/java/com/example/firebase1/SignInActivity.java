package com.example.firebase1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

// this SignInActivity class is automatically created when you created layout
public class SignInActivity extends AppCompatActivity {

    EditText email, password;      // declare EditText - email,password
    Button loginButton;          //  declare Button - loginButton
    TextView goToRegisterPage;   // declare TextView - goToRegisterPage

    private FirebaseAuth authentication;   // declare FirebaseAuth


    @Override
    // this onCreate method is automatically created when you are creating layout and under this method
    // all coding part is written.This is main method.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // getting all views by their id
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.signup);
        goToRegisterPage = findViewById(R.id.textView2);

        authentication = FirebaseAuth.getInstance();  // initializing Firebase auth

        // now set on click listener on login button - OnClickListener means when you are clicking on login button what should be done is written here.

        loginButton.setOnClickListener(view -> {
            // get name,email,password and convert it into string
            String emailId = email.getText().toString();             // note: variable name should not be similar to layout name which we declared above.
            String passwords = password.getText().toString();

            // check if  email and password is empty, if so display a toast with message.
            if (TextUtils.isEmpty(emailId)) {
                Toast.makeText(SignInActivity.this, "Please enter your email id", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(passwords)) {
                Toast.makeText(SignInActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            } else {
                // signInWithEmailAndPassword is method which helps to login into existing  account with the help of email and password using firebase
                authentication.signInWithEmailAndPassword(emailId, passwords).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {  // if task is success display message
                        Toast.makeText(SignInActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignInActivity.this, "Login Failed " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        // if user has not created an account then if user click on goToRegisterPage text that is "Don't have an account? sign up here" in UI
        // it will redirect to signUp page with the help of intent.Intent helps to go from one activity to another activity.

        goToRegisterPage.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }
}


