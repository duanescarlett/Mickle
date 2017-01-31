package com.example.root.mickle.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.root.mickle.Overhead;

import java.util.HashMap;

import static com.example.root.mickle.db.Db_Contract.Db_Vars.Account_Table;
import static com.example.root.mickle.db.Db_Contract.Db_Vars.Budget_Table;
import static com.example.root.mickle.db.Db_Contract.Db_Vars.DATABASE_NAME;
import static com.example.root.mickle.db.Db_Contract.Db_Vars.Liability_Table;
import static com.example.root.mickle.db.Db_Contract.Db_Vars.Profile_Table;
import static com.example.root.mickle.db.Db_Contract.Db_Vars.UID;

public class Db_Helper{
    private Core db;
    public SQLiteDatabase sqLiteDatabase;
    private Context context;

    public Db_Helper(Context context){
        this.context = context;
        Overhead.message(this.context, "Databases Intialized");
        db = new Core(this.context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    public long insertAccount(String name, String type, float amount){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Amount", amount);
        contentValues.put("Account", type);
        long result = sqLiteDatabase.insert(Account_Table, null, contentValues);
        return result;
    }

    public long insertProfile(String name, float amount){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Income", amount);
        long id = sqLiteDatabase.insert(Profile_Table, null, contentValues);
        return id;
    }

    public long insertBudget(String name, float amount, String acc_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Amount", amount);
        contentValues.put("Account_id", acc_id);
        long result = sqLiteDatabase.insert(Budget_Table, null, contentValues);
        return result;
    }

    public long insertLiability(String name, float amount, String acc_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Amount", amount);
        contentValues.put("Profile_id", acc_id);
        long result = sqLiteDatabase.insert(Budget_Table, null, contentValues);
        return result;
    }

    public HashMap getAccountData(){

        final HashMap<Integer,String> stack = new HashMap<>();

        final Thread AccThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] coloumns = {UID, "Name", "Amount", "Account"};
                Cursor cursor = sqLiteDatabase.query(Account_Table, coloumns, null, null, null, null, null);
                int n = 0;

                while (cursor.moveToNext()){
                    int index0 = cursor.getColumnIndex(UID);
                    int index1 = cursor.getColumnIndex("Name");
                    int index2 = cursor.getColumnIndex("Amount");
                    int index3 = cursor.getColumnIndex("Account");

                    int cid = cursor.getInt(index0);
                    String name = cursor.getString(index1);
                    Float amount = cursor.getFloat(index2);
                    String account = cursor.getString(index3);

                    stack.put(n, Integer.toString(cid));
                    stack.put(n, name);
                    stack.put(n, Float.toString(amount));
                    stack.put(n, account);
                    //Overhead.message(this.context, stack[n][n]);
                    n++;
                }
            }
        });
        AccThread.start();

        return stack;

    }

    public String getProfileData() {

        final String[] stack = new String[1];

        final Thread AccThread = new Thread(new Runnable() {

            @Override
            public void run() {
                String[] coloumns = {UID, "Name", "Income"};
                Cursor cursor = sqLiteDatabase.query(Profile_Table, coloumns, null, null, null, null, null);
                StringBuffer buffer = new StringBuffer();

                int n = 0;

                while (cursor.moveToNext()){
                    int index0 = cursor.getColumnIndex(UID);
                    int index1 = cursor.getColumnIndex("Name");
                    int index2 = cursor.getColumnIndex("Income");

                    int cid = cursor.getInt(index0);
                    String name = cursor.getString(index1);
                    Float income = cursor.getFloat(index2);

                    stack[0] = Integer.toString(cid);
                    stack[0] += " " + name;
                    stack[0] += " " + Float.toString(income);
                    //Overhead.message(this.context, stack[0]);
                    n++;
                }
            }
        });
        AccThread.start();

        return stack[0];

    }
}

class Core extends SQLiteOpenHelper {

    // Account Table
    public String acc_name = "Name";
    public String acc_amount = "Amount";
    public String acc_account = "Account";

    // Profile Table
    public String pro_name = "Name";
    public String pro_income = "Income";

    // Budget Table
    public String bud_name = "Name";
    public String bud_amount = "Amount";
    public String bud_acc = "Account_id";

    // Liability Table
    public String lia_name = "Name";
    public String lia_amount = "Amount";
    public String lia_profileId = "Profile_id";

    private Context context;
    private SQLiteDatabase dbthis;

    public Core(Context context) {
        super(context, DATABASE_NAME, null, 6);
        dbthis = this.getWritableDatabase();
        this.context = context;

    }

    @Override
    public void onCreate(final SQLiteDatabase db) {

        Thread process = new Thread(new Runnable() {
            @Override
            public void run() {
                db.execSQL("CREATE TABLE " + Profile_Table + " ("+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+pro_name+ " TEXT, "+pro_income+" FLOAT);");
                db.execSQL("CREATE TABLE " + Account_Table + " ("+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+acc_name+ " TEXT, "+acc_amount+" FLOAT, "+acc_account+" TEXT);");
                db.execSQL("CREATE TABLE " + Budget_Table + " ("+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+bud_name+ " TEXT, "+bud_amount+" FLOAT, "+bud_acc+" TEXT);");
                db.execSQL("CREATE TABLE " + Liability_Table + " ("+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+lia_name+ " TEXT, "+lia_amount+" FLOAT, "+lia_profileId+" INTEGER);");
                Overhead.message(context, "I think the tables have been created");
            }
        });

        process.start();

    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, int oldVersion, int newVersion) {

        Thread process = new Thread(new Runnable() {
            @Override
            public void run() {
                db.execSQL("DROP TABLE IF EXISTS " + Profile_Table);
                db.execSQL("DROP TABLE IF EXISTS " + Account_Table);
                db.execSQL("DROP TABLE IF EXISTS " + Budget_Table);
                db.execSQL("DROP TABLE IF EXISTS " + Liability_Table);
                onCreate(dbthis);
            }
        });

        process.start();

    }

}
