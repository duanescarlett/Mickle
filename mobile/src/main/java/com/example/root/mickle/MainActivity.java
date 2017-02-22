package com.example.root.mickle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.root.mickle.fragments.AccountList;
import com.example.root.mickle.fragments.BudgetList;
import com.example.root.mickle.fragments.CashInput;
import com.example.root.mickle.fragments.Create_acc;
import com.example.root.mickle.fragments.Create_user;
import com.example.root.mickle.models.Profile;

public class MainActivity extends AppCompatActivity implements Communicator {

    private Toolbar toolbar;
    private CashInput cashInput;
    private Profile profileTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Enter a deposit to an account", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Run a query to see if the user exists
        //checkUserExistence(db);

    }

    public void addUser(){
        Create_user frag = new Create_user();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.my_layout, frag, "New user creation");
        transaction.commit();
    }

    public void checkUserExistence(){

//        if(data == "")
//            addUser();
//        else
//            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }
        return true;
    }

    public void showDialog(View v){
        cashInput = new CashInput();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.my_layout, cashInput, "Cash Input");
        transaction.commit();
    }

    public void showAccList(View v){
        AccountList frag = new AccountList();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.my_layout, frag, "Account List");
        transaction.commit();
    }

    public void showBudList(View v){
        BudgetList frag = new BudgetList();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.my_layout, frag, "Budget List");
        transaction.commit();
    }

    public void createAccFrag(View v){
        Create_acc frag = new Create_acc();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.my_layout, frag, "Account creator");
        transaction.commit();
    }

    @Override
    public void onDialogMessage(String msg, Fragment c) {
        getFragmentManager().beginTransaction().remove(c).commit();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
}