package com.rba.botdemo.storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler mInstance;

    protected DatabaseHandler(Context context){
        super(context, ConstantDB.DATABASE_NAME, null, ConstantDB.DATABASE_VERSION);
    }

    protected void getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseHandler(context);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_OPERATION_TABLE= "CREATE TABLE " + ConstantDB.KEY_TABLE_OPERATION_TYPE + "("
                + ConstantDB.KEY_OPERATION_TYPE_ID + " TEXT, "
                + ConstantDB.KEY_OPERATION_ID+" TEXT, "
                + ConstantDB.KEY_OPERATION_DESCRIPTION+" TEXT)";

        String CREATE_PROPERTY_TABLE= "CREATE TABLE " + ConstantDB.KEY_TABLE_PROPERTY_TYPE + "("
                + ConstantDB.KEY_PROPERTY_TYPE_ID + " TEXT, "
                + ConstantDB.KEY_PROPERTY_ID+" TEXT, "
                + ConstantDB.KEY_PROPERTY_DESCRIPTION+" TEXT)";

        sqLiteDatabase.execSQL(CREATE_OPERATION_TABLE);
        sqLiteDatabase.execSQL(CREATE_PROPERTY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
