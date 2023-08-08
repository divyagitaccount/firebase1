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

// this SignUpActivity class is automatically created when you created layout
public class SignUpActivity extends AppCompatActivity {
    EditText name,email,password;            // declaration of EditText view - name, email and password
    Button registerButton;                  // declaration of Button view - registerButton
    TextView goToLoginPage;                // declaration of TextView - goToLoginPage

    private FirebaseAuth authentication;    // declare firebase

    @Override   // this onCreate method is automatically created when you are creating layout and under this method
    // all coding part is written.This is main method.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        authentication = FirebaseAuth.getInstance();  // initializing Firebase auth

        // getting all views by their id
        name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        registerButton = findViewById(R.id.signup1);
        goToLoginPage = findViewById(R.id.textView2);

        // now set on click listener on register button - OnClickListener means when you are clicking on register button what should be done is written here.

        registerButton.setOnClickListener(view -> {
            // get name,email,password and convert it into string

            String fullName = name.getText().toString();       // note: variable name should not be similar to layout name which we declared above.
            String emailId = email.getText().toString();
            String passwords = password.getText().toString();

            // check if name, email and password is empty, if so display a toast with message.
            if(TextUtils.isEmpty(fullName)){
                Toast.makeText(SignUpActivity.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(emailId)){
                Toast.makeText(SignUpActivity.this, "Please enter your email id", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(passwords)){
                Toast.makeText(SignUpActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            }
            else{
                // createUserWithEmailAndPassword is method which helps to create account with the help of email and password using firebase
                authentication.createUserWithEmailAndPassword(emailId,passwords).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){   // if task is success display message
                        Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_LONG).show(); // Toast is used to show message
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Signup Failed " , Toast.LENGTH_LONG).show();
                    }

                });
            }
        });

        // if user has already an account then if user click on goTOLoginPage text that is "Already have an account? sign in here" in UI
        // it will redirect to signIn page with the help of intent.Intent helps to go from one activity to another activity.
        goToLoginPage.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        });
    }
}