package com.example.petapp002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class PetOwnerMainMenuActivity extends AppCompatActivity {

    public LinearLayout addPetButton, reminderBtn , myPetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner_main_menu);

        addPetButton = findViewById(R.id.add_pet_btn);
        reminderBtn = findViewById(R.id.reminder_btn);
        myPetBtn = findViewById(R.id.my_pet_btn);


        //MyPet Button Function
        myPetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PetOwnerMainMenuActivity.this, MyPetActivity.class);
                startActivity(intent);
            }
        });

        //Add Pet Button Function
        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PetOwnerMainMenuActivity.this, AddPetActivity.class);
                startActivity(intent);
            }
        });

        //Reminder Button Function
        reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PetOwnerMainMenuActivity.this, ReminderActivity.class);
                startActivity(intent);
            }
        });
    }
}
