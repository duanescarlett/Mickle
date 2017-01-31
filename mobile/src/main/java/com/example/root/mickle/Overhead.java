package com.example.root.mickle;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by root on 11/24/16.
 */

public class Overhead {

    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
