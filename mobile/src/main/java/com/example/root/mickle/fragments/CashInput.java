package com.example.root.mickle.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.mickle.Communicator;
import com.example.root.mickle.R;

/**
 * Created by root on 10/16/16.
 */

public class CashInput extends Fragment {

    Button no, yes;
    Communicator communicator;
    EditText dollar_input;

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
        View view = inflater.inflate(R.layout.cash_input, null);
        no = (Button) view.findViewById(R.id.btnNo);
        yes = (Button) view.findViewById(R.id.btnYes);
        dollar_input = (EditText) view.findViewById(R.id.dollar_input);

        no.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                communicator.onDialogMessage("no", getTargetFragment());
                //Toast.makeText(getActivity(), "No button was clicked", Toast.LENGTH_LONG).show();
            }

        });

        yes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String s = dollar_input.getText().toString();
                communicator.onDialogMessage(s, CashInput.this);
                //Toast.makeText(getActivity(), "Yes button was clicked", Toast.LENGTH_LONG).show();
            }

        });

        return view;

    }

}
