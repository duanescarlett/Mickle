package com.example.root.mickle.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.mickle.Communicator;
import com.example.root.mickle.R;

public class Create_acc extends Fragment implements AdapterView.OnItemSelectedListener{

    EditText name;
    Spinner accType;
    EditText amount;
    Button btnSubmit;
    Communicator communicator;

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

    public Create_acc() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_create_acc, container, false);

        name = (EditText) v.findViewById(R.id.name);
        amount = (EditText) v.findViewById(R.id.amount);

        // Setup the spinner
        accType = (Spinner) v.findViewById(R.id.accType);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.account_type, android.R.layout.simple_list_item_checked);
        accType.setAdapter(adapter);
        accType.setOnItemSelectedListener(this);

        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String name = Create_acc.this.name.getText().toString();
                String amount = Create_acc.this.amount.getText().toString();
                String accType = Create_acc.this.accType.getSelectedItem().toString();

                float num = Float.parseFloat(amount); // Convert string to float

                // Create the account

                // Tell activity to kill fragment
                communicator.onDialogMessage("null", Create_acc.this);

            }

        });

        return v;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(getActivity(), "You Selected " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
