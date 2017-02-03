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

public class OperationTypeDB extends DatabaseHandler{

    public OperationTypeDB(Context context) {
        super(context);
        getInstance(context);
    }

    public void addOperationType(SynchronizeResponse.DataBean.OperationTypeBean operationTypeBean){
        String sql = "INSERT INTO "+ ConstantDB.KEY_TABLE_OPERATION_TYPE +"("
                +ConstantDB.KEY_OPERATION_TYPE_ID+", "+ConstantDB.KEY_OPERATION_ID+", "
                +ConstantDB.KEY_OPERATION_DESCRIPTION+") VALUES (?,?, ?)";
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        statement.clearBindings();
        statement.bindString(1, String.valueOf(operationTypeBean.getOperation_type_id()));
        statement.bindString(2, operationTypeBean.getOperation_id());
        statement.bindString(3, operationTypeBean.getOperation_description());
        statement.execute();
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<SynchronizeResponse.DataBean.OperationTypeBean> getOperationType(){

        List<SynchronizeResponse.DataBean.OperationTypeBean> operationTypeList = new ArrayList<>();

        String selectQuery = "SELECT "+ConstantDB.KEY_OPERATION_TYPE_ID+", "+ConstantDB.KEY_OPERATION_ID+", "
                +ConstantDB.KEY_OPERATION_DESCRIPTION+" FROM "
                + ConstantDB.KEY_TABLE_OPERATION_TYPE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try{
            if (cursor.moveToFirst()) {
                do {
                    SynchronizeResponse.DataBean.OperationTypeBean operationTypeBean
                            = new SynchronizeResponse.DataBean.OperationTypeBean();
                    operationTypeBean.setOperation_type_id(cursor.getInt(0));
                    operationTypeBean.setOperation_id(cursor.getString(1));
                    operationTypeBean.setOperation_description(cursor.getString(1));
                    operationTypeList.add(operationTypeBean);
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }

        return operationTypeList;
    }

}