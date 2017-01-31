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

import com.example.root.mickle.Communicator;
import com.example.root.mickle.R;
import com.example.root.mickle.db.Db_Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Create_budget extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner acc_Spinner;
    EditText txtName;
    EditText txtAmount;
    Button btnCreateBudget;
    Communicator communicator;
    Db_Helper db_helper;

    private List<String> dynamicListItems;

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

    public Create_budget() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //db_helper = new Db_Helper(getActivity());
        db_helper = new Db_Helper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_budget, container, false);

        //
        acc_Spinner = (Spinner) v.findViewById(R.id.acc_Spinner);
        txtName = (EditText) v.findViewById(R.id.txtName);
        txtAmount = (EditText) v.findViewById(R.id.txtAmount);
        btnCreateBudget = (Button) v.findViewById(R.id.btnCreateBudget);

        dynamicListItems = new ArrayList<String>();

        // Get a set of entries
        Set set =  db_helper.getAccountData().entrySet();

        // Get an iterator
        Iterator i = set.iterator();
        // Get each element
        while(i.hasNext()) {
            dynamicListItems.add(i.next().toString());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dynamicListItems);

        acc_Spinner.setAdapter(adapter);
        acc_Spinner.setOnItemSelectedListener(this);

        btnCreateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Create_budget.this.txtName.getText().toString();
                String amount = Create_budget.this.txtAmount.getText().toString();
                float num = Float.parseFloat(amount); // Convert string to float
                db_helper.insertBudget(name, num, "CASH");
            }
        });
        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

