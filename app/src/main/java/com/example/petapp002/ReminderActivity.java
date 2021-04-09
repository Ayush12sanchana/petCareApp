package com.example.petapp002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.KeyguardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.petapp002.Fragment.AllSheduleFragment;
import com.example.petapp002.Fragment.CreateSheduleFragment;

public class ReminderActivity extends AppCompatActivity {

    Button allSheduleButton, createSheduleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        allSheduleButton = findViewById(R.id.all_shedule);
        createSheduleButton = findViewById(R.id.create_chedule);

        allSheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new AllSheduleFragment());
            }

        });

        createSheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new CreateSheduleFragment());
            }

        });

    }

    private void setFragment(Fragment fragment) {

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.shedule_fragment,fragment);
        fragmentTransaction.commit();
    }

}
