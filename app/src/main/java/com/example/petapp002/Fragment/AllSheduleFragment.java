package com.example.petapp002.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.petapp002.R;

public class AllSheduleFragment extends Fragment {

    Dialog myDialog;
    LinearLayout shedule;



    public AllSheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myDialog= new Dialog(getActivity());
        View view = inflater.inflate(R.layout.fragment_all_shedule, container, false);

        shedule= view.findViewById(R.id.relative01);
        shedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.shedule_detail_popup_window);
                myDialog.show();

            }
        });



        return view;


    }


}
