package com.example.mbap_project_exercise_tracker_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity{

    private EditText editTxtEmail, editTxtPw, editTxtPN, editTxtAddress;
    private Button signUpUser;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTxtEmail = (EditText)findViewById(R.id.editTextEmail2);
        editTxtPw = (EditText)findViewById(R.id.editTextPassword2);
        editTxtPN = (EditText)findViewById(R.id.editTextPN);
        editTxtAddress = (EditText)findViewById(R.id.editTextAddress);
        signUpUser = (Button)findViewById(R.id.signupBtn2);

        mAuth = FirebaseAuth.getInstance();

        signUpUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTxtEmail.getText().toString();
                String password = editTxtPw.getText().toString();
                String phonenumber = editTxtPN.getText().toString();
                String address = editTxtAddress.getText().toString();

                if (email.isEmpty()) {
                    editTxtEmail.setError("Email is required!");
                    editTxtEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTxtEmail.setError("Email is invalid!");
                    editTxtEmail.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    editTxtPw.setError("Password is required!");
                    editTxtPw.requestFocus();
                    return;
                }
                if (phonenumber.isEmpty()) {
                    editTxtPN.setError("Phone number is required!");
                    editTxtPN.requestFocus();
                    return;
                }
                if (address.isEmpty()) {
                    editTxtAddress.setError("Address is required!");
                    editTxtAddress.requestFocus();
                    return;
                }
                if (email.isEmpty() && password.isEmpty() && phonenumber.isEmpty() && address.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();

                } else if (!(email.isEmpty() && password.isEmpty() && phonenumber.isEmpty() && address.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Sign Up Unsuccessful!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    };
    public void redirectToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
