package com.example.petapp002.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.petapp002.R;


public class CreateSheduleFragment extends Fragment {

    RadioButton radioButton;
    Dialog myDialog;

    public CreateSheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myDialog= new Dialog(getActivity());


        View view = inflater.inflate(R.layout.fragment_create_shedule, container, false);

        radioButton= view.findViewById(R.id.radio_btn);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.create_shedule_detail_popup_window);
                myDialog.show();
            }
        });

        return view;
    }
}
