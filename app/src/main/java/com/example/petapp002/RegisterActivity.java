package com.example.petapp002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class RegisterActivity extends AppCompatActivity {

    public LinearLayout petOwnerBtn;
    public RelativeLayout backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        petOwnerBtn = findViewById(R.id.petowner);
        backBtn = findViewById(R.id.back_btn);

        //PetOwner Button Functions
        petOwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this, RegisterPetOwnerActivity.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this, LoginMenuActivity.class);
                startActivity(intent);
            }
        });

    }
}
