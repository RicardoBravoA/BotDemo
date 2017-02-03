package com.rba.botdemo.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class BaseActivity extends AppCompatActivity {

    private ChartComponent chartComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chartComponent = DaggerChartComponent.builder().networkModule(new NetworkModule()).build();
    }

    public ChartComponent getChartComponent() {
        return chartComponent;
    }

}
