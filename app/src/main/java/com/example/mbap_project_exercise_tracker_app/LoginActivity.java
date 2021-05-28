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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editTxtEmail, editTxtPw;
    private Button loginUser;

    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTxtEmail = (EditText)findViewById(R.id.editTextEmail);
        editTxtPw = (EditText)findViewById(R.id.editTextPassword);
        loginUser = (Button)findViewById(R.id.loginBtn2);
        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser = mAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "Logged In!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                }

            }
        };
        loginUser.setOnClickListener(v -> {
            String email = editTxtEmail.getText().toString();
            String password = editTxtPw.getText().toString();

            if (email.isEmpty()) {
                editTxtEmail.setError("Email is required!");
                editTxtEmail.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTxtEmail.setError("Email is invalid!");
                editTxtEmail.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                editTxtPw.setError("Password is required!");
                editTxtPw.requestFocus();
                return;
            }
            if (email.isEmpty() && password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
            } else if (!(email.isEmpty() && password.isEmpty())) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Unsuccessful. Please Try Logging In Again", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            } ;
        });
    }

    public void redirectToSignUp(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}

