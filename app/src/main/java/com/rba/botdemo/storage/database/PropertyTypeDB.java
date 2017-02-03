package com.rba.botdemo.storage.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.rba.botdemo.model.response.SynchronizeResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class PropertyTypeDB extends DatabaseHandler{

    public PropertyTypeDB(Context context) {
        super(context);
        getInstance(context);
    }

    public void addPropertyType(SynchronizeResponse.DataBean.PropertyTypeBean propertyTypeBean){
        String sql = "INSERT INTO "+ ConstantDB.KEY_TABLE_PROPERTY_TYPE +"("
                +ConstantDB.KEY_PROPERTY_TYPE_ID+", "+ConstantDB.KEY_PROPERTY_ID+", "
                +ConstantDB.KEY_PROPERTY_DESCRIPTION+") VALUES (?,?, ?)";
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        statement.clearBindings();
        statement.bindString(1, String.valueOf(propertyTypeBean.getProperty_type_id()));
        statement.bindString(2, propertyTypeBean.getProperty_id());
        statement.bindString(3, propertyTypeBean.getProperty_description());
        statement.execute();
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<SynchronizeResponse.DataBean.PropertyTypeBean> getPropertyType(){

        List<SynchronizeResponse.DataBean.PropertyTypeBean> propertyTypeList = new ArrayList<>();

        String selectQuery = "SELECT "+ConstantDB.KEY_PROPERTY_TYPE_ID+", "+ConstantDB.KEY_PROPERTY_ID+", "
                +ConstantDB.KEY_PROPERTY_DESCRIPTION+" FROM "
                + ConstantDB.KEY_TABLE_PROPERTY_TYPE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try{
            if (cursor.moveToFirst()) {
                do {
                    SynchronizeResponse.DataBean.PropertyTypeBean propertyTypeBean
                            = new SynchronizeResponse.DataBean.PropertyTypeBean();
                    propertyTypeBean.setProperty_type_id(cursor.getInt(0));
                    propertyTypeBean.setProperty_id(cursor.getString(1));
                    propertyTypeBean.setProperty_description(cursor.getString(1));
                    propertyTypeList.add(propertyTypeBean);
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }

        return propertyTypeList;
    }

}