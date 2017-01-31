package com.example.root.mickle;

import android.app.Fragment;

/**
 * Created by root on 10/17/16.
 */

public interface Communicator {
    void onDialogMessage(String msg, Fragment targetFragment);
}
