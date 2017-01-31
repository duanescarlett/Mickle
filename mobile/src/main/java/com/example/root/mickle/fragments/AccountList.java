package com.example.root.mickle.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.root.mickle.Communicator;
import com.example.root.mickle.MainActivity;
import com.example.root.mickle.R;

/*
 * Created by root on 10/25/16.
 */

public class AccountList extends Fragment {

    Communicator communicator;
    Button createBtn;
    Toolbar toolbar;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.account_list, null);
        toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        createBtn = (Button) view.findViewById(R.id.btnCreateAcc);

        createBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // Distroy the account list frag
                getFragmentManager().beginTransaction().remove(AccountList.this).commit();

                // Create the form for a new account
                Create_acc frag = new Create_acc();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.my_layout, frag, "Account creator");
                transaction.commit();
            }

        });

        return view;

    }



}
