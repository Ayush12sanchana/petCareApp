package com.example.petapp002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterPetOwnerActivity extends AppCompatActivity {

    public Button petOwnerSaveBtn;
    public EditText fNameTxt, lNameTxt, emailTxt, tpTxt, passwordxt, rePasswordxt;
    public String fName;
    public String lName;
    public String email;
    public String tp;
    public String password;
    public String rePassword;
    public FirebaseAuth mFirebaseAuth;
    public FirebaseFirestore mfirebaseFirestore;
    public String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet_owner);

        mfirebaseFirestore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        petOwnerSaveBtn = findViewById(R.id.pet_owner_save_btn);
        fNameTxt = findViewById(R.id.user_first_name_txt);
        lNameTxt = findViewById(R.id.user_last_name_txt);
        emailTxt = findViewById(R.id.user_email_txt);
        tpTxt = findViewById(R.id.user_mobile_number_txt);
        passwordxt = findViewById(R.id.user_password_txt);
        rePasswordxt = findViewById(R.id.user_password_confirm_txt);

        fName = fNameTxt.getText().toString();
        lName = lNameTxt.getText().toString();
        email = emailTxt.getText().toString();
        tp = tpTxt.getText().toString();
        password = passwordxt.getText().toString();
        rePassword = rePasswordxt.getText().toString();


        petOwnerSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(fNameTxt.getText().toString())){
                    fNameTxt.setError("name required");
                    fNameTxt.requestFocus();
                }
                else if(TextUtils.isEmpty(lNameTxt.getText().toString())){
                    lNameTxt.setError("age required");
                    lNameTxt.requestFocus();
                }
                else if(TextUtils.isEmpty(emailTxt.getText().toString())){
                    emailTxt.setError("age required");
                    emailTxt.requestFocus();
                }
                else if(TextUtils.isEmpty(tpTxt.getText().toString())){
                    tpTxt.setError("age required");
                    tpTxt.requestFocus();
                }
                else if(TextUtils.isEmpty(passwordxt.getText().toString())){
                    passwordxt.setError("age required");
                    passwordxt.requestFocus();
                }
                else if(TextUtils.isEmpty(rePasswordxt.getText().toString())){
                    rePasswordxt.setError("age required");
                    rePasswordxt.requestFocus();
                }

                else if(!(fNameTxt.getText().toString().isEmpty() && lNameTxt.getText().toString().isEmpty() && emailTxt.getText().toString().isEmpty() && tpTxt.getText().toString().isEmpty() && passwordxt.getText().toString().isEmpty() && rePasswordxt.getText().toString().isEmpty())) {


                    if(password.equals(rePassword)) {



                        mFirebaseAuth.createUserWithEmailAndPassword(emailTxt.getText().toString(), passwordxt.getText().toString()).addOnCompleteListener(RegisterPetOwnerActivity.this, new OnCompleteListener<AuthResult>() {

                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        userId = mFirebaseAuth.getCurrentUser().getUid();

                                        DocumentReference documentReference = mfirebaseFirestore.collection("users").document("PetOwners").collection("users").document(userId);
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("Uid", userId);
                                        user.put("Fname", fNameTxt.getText().toString());
                                        user.put("Lname", lNameTxt.getText().toString());
                                        user.put("email", emailTxt.getText().toString());
                                        user.put("tp", tpTxt.getText().toString());
                                        user.put("password", passwordxt.getText().toString());
                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                Toast.makeText(RegisterPetOwnerActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                                //Bundle bundle = new Bundle();
                                                //bundle.putString("Uid", userId);

                                                //Intent intent = new Intent(registrationtwo.this, login01.class);
                                                //intent.putExtras(bundle);
                                                //startActivity(intent);

                                                Intent intent = new Intent(RegisterPetOwnerActivity.this, LoginActivity.class);
                                                startActivity(intent);

                                            }
                                        });


                                    }
                                    });

                    }

                }




            }
        });
    }
}
