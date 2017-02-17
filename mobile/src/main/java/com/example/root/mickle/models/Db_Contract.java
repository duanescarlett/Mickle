package com.example.root.mickle.models;

import android.provider.BaseColumns;

/**
 * Created by root on 10/21/16.
 */

public final class Db_Contract {

    private Db_Contract(){}

    public static class Db_Vars implements BaseColumns {
        protected static final String DATABASE_NAME = "User.db";
        protected static final String Profile_Table = "profile_table";
        protected static final String Account_Table = "account_table";
        protected static final String Budget_Table = "budget_table";
        protected static final String Liability_Table = "liability_table";
        protected static final String UID = "_id";
    }

}
