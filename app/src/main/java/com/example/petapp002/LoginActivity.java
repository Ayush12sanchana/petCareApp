package com.example.petapp002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    public EditText email, password;
    public Button saveBtn;
    public FirebaseAuth mFirebaseAuth;
    public FirebaseAuth.AuthStateListener mAuthStateListener;
    public String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email_login_txt);
        password = findViewById(R.id.password_login_txt);
        saveBtn = findViewById(R.id.user_login_btn);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){

                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(email.getText().toString().isEmpty()){
                    email.setError("Please enter email id");
                    email.requestFocus();
                }
                else  if(password.getText().toString().isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.getText().toString().isEmpty() && password.getText().toString().isEmpty())) {


                    mFirebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                                if (mFirebaseUser != null) {

                                    userID = mFirebaseUser.getUid();

                                    Bundle bundle = new Bundle();
                                    bundle.putString("uid", userID);


                                    Intent intent = new Intent(LoginActivity.this, PetOwnerMainMenuActivity.class);
                                    //intent.putExtras(bundle);
                                    startActivity(intent);

                                }


                            }
                        }
                    });
                }
////////////////////////////
            }
        });

    }
}
