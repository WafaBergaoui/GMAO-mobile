package com.example.gmaomobile.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String TAG = Database.class.getSimpleName();

    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 2;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_USERS_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";


    public static final String INTERVENTION_TABLE = "interventions";
    public static final String COLUMN_interventions_ID = "INTERVENTIONS_ID";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_EQUIPMENT = "equipment";
    public static final String COLUMN_DESCRIPTION = "description";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT);";

    public static final String CREATE_TABLE_INTERVENTIONS = "CREATE TABLE " + INTERVENTION_TABLE + "("
            + COLUMN_interventions_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TITLE + " varchar(20),"
            + COLUMN_PRIORITY + " varchr(20),"
            + COLUMN_DATE + " DATE,"
            + COLUMN_EQUIPMENT + " varchar(30),"
            + COLUMN_DESCRIPTION + " varchar(100));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_INTERVENTIONS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INTERVENTION_TABLE);
        onCreate(db);
    }

    /**
     * Storing user details in database
     */
    public void addUser(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }


    public boolean getUser(String email, String pass) {

        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'" + email + "'" + " and " + COLUMN_PASS + " = " + "'" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }


    public void addIntervention(String title, String priority, String date, String equipment, String description) {

        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_PRIORITY, priority);
        values.put(COLUMN_DATE, String.valueOf(date));
        values.put(COLUMN_EQUIPMENT, equipment);
        values.put(COLUMN_DESCRIPTION, description);

        db.insert("interventions", null, values);
        db.close();

    }



    public ArrayList<Interventions> getAllIntervention(){

        SQLiteDatabase sd = this.getReadableDatabase();
        ArrayList<Interventions> interventionList = new ArrayList<Interventions>();
        Cursor c = sd.rawQuery("select * from " + INTERVENTION_TABLE, null);

        if (c.getCount() > 0) {
            for (int i = 0; i < c.getCount(); i++) {
                c.moveToNext();
                Interventions intervention = new Interventions();
                intervention.setId(Integer.parseInt(c.getString(0)));
                intervention.setTitle(c.getString(1));
                intervention.setPriority(c.getString(2));
                intervention.setDate(c.getString(3));
                intervention.setEquipment(c.getString(4));
                intervention.setDescription(c.getString(5));
                interventionList.add(intervention);
            }
        }
        c.close();
        sd.close();
        return interventionList;

    }


    public int updateInterventions(String title, String priority, String date, String equipment, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Interventions interventions = new Interventions();

        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_PRIORITY, priority);
        values.put(COLUMN_DATE, String.valueOf(date));
        values.put(COLUMN_EQUIPMENT, equipment);
        values.put(COLUMN_DESCRIPTION, description);

        return db.update(INTERVENTION_TABLE , values, COLUMN_interventions_ID + " = ?",
                new String[]{String.valueOf(interventions.getId())});
    }

    public void deleteInterventions(Interventions interventions) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(INTERVENTION_TABLE,  COLUMN_interventions_ID+ " = ?",
                new String[] { String.valueOf(interventions.getId()) });
        db.close();
    }


}
