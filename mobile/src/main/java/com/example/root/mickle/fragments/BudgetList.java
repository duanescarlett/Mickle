package com.example.root.mickle.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.root.mickle.Communicator;
import com.example.root.mickle.R;

/**
 * Created by root on 10/27/16.
 */

public class BudgetList extends DialogFragment{

    Communicator communicator;
    Button btnCreateBudget;

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
        View view = inflater.inflate(R.layout.budget_list, null);
        setCancelable(false);

        btnCreateBudget = (Button) view.findViewById(R.id.btnCreateBudget);
        btnCreateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Distroy the account list frag
                getFragmentManager().beginTransaction().remove(BudgetList.this).commit();

                // Create the form for a new account
                Create_budget frag = new Create_budget();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.my_layout, frag, "Account creator");
                transaction.commit();
            }
        });

        return view;
    }
}
