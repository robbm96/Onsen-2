package com.example.onsen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText tName, tEmail, tPassword, tConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tName = findViewById(R.id.editTextName);
        tEmail = findViewById(R.id.editTextEmail);
        tPassword = findViewById(R.id.editTextPassword);
        tConfirmPassword = findViewById(R.id.editTextConfirmPassword);

    }

    public void signUp(View view){


        if(TextUtils.isEmpty(tName.getText()) && TextUtils.isEmpty(tEmail.getText()) &&
                TextUtils.isEmpty(tPassword.getText()) && TextUtils.isEmpty(tConfirmPassword.getText())){
            tName.setError( "Name is required" );
            tEmail.setError("Email is required");
            tPassword.setError("Password is required");
            tConfirmPassword.setError("Confirmed Password is required");
        }
        else if( TextUtils.isEmpty(tName.getText())){
            tName.setError( "Name is required" );
        }
        else if( TextUtils.isEmpty(tEmail.getText())) {
            tEmail.setError("Email is required");
        }
        else if( TextUtils.isEmpty(tPassword.getText())) {
            tPassword.setError("Password is required");
        }
        else if( TextUtils.isEmpty(tConfirmPassword.getText())) {
            tConfirmPassword.setError("Confirmed Password is required");
        }
        else if(!tPassword.getText().toString().equals(tConfirmPassword.getText().toString())) {
            tPassword.setError("Don't match");
            tConfirmPassword.setError("Don't match");
        }
        else{
            ParseUser user = new ParseUser();
            // Set the user's username and password, which can be obtained by a forms
            user.setUsername(tEmail.getText().toString().trim());
            user.setEmail(tEmail.getText().toString().trim());
            user.setPassword(tPassword.getText().toString().trim());

            user.put("name", tName.getText().toString().trim());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast toast = Toast.makeText(SignUpActivity.this, "Created account for " + tEmail.getText().toString() , Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 1350);
                        toast.show();
                        Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                    }
                }
            });


        }

    }


}
