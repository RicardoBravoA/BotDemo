package com.rba.botdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rba.botdemo.api.NetworkModule;
import com.rba.botdemo.di.BotComponent;
import com.rba.botdemo.di.DaggerBotComponent;
import com.rba.botdemo.storage.database.OperationTypeDB;
import com.rba.botdemo.storage.database.PropertyTypeDB;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class BaseActivity extends AppCompatActivity {

    private BotComponent botComponent;
    public OperationTypeDB operationTypeDB;
    public PropertyTypeDB propertyTypeDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        botComponent = DaggerBotComponent.builder().networkModule(new NetworkModule()).build();
        operationTypeDB = new OperationTypeDB(this);
        propertyTypeDB = new PropertyTypeDB(this);
    }

    public BotComponent getBotComponent() {
        return botComponent;
    }

}
