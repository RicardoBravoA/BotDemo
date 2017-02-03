package com.rba.botdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rba.botdemo.api.NetworkModule;
import com.rba.botdemo.di.BotComponent;
import com.rba.botdemo.di.DaggerBotComponent;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class BaseActivity extends AppCompatActivity {

    private BotComponent botComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        botComponent = DaggerBotComponent.builder().networkModule(new NetworkModule()).build();
    }

    public BotComponent getBotComponent() {
        return botComponent;
    }

}
