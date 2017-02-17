package com.example.root.mickle.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.root.mickle.Communicator;
import com.example.root.mickle.R;
import com.example.root.mickle.models.Profile;

public class Create_user extends Fragment {

    EditText name;
    EditText income;
    Button btnSubmit;
    Communicator communicator;

    Profile profile_Tb;

    public Create_user() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Activity a;

        // This is the new way to handle onAttach
        if(context instanceof Activity){
            a = (Activity) context;
            communicator = (Communicator) a;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);
        name = (EditText) view.findViewById(R.id.name);
        income = (EditText) view.findViewById(R.id.income);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s = name.getText().toString();
                String t = income.getText().toString();

                float num = Float.parseFloat(t);

                // Create profile
                Create_user.this.profile_Tb = new Profile(s, num);

                // Return to main activity

            }
        });

        return view;

    }

}
