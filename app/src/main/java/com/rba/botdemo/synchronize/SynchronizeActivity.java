package com.rba.botdemo.synchronize;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.LinearLayout;

import com.rba.botdemo.R;
import com.rba.botdemo.base.BaseActivity;
import com.rba.botdemo.model.response.SynchronizeResponse;
import com.rba.botdemo.storage.database.OperationTypeDB;
import com.rba.botdemo.storage.database.PropertyTypeDB;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SynchronizeActivity extends BaseActivity implements SynchronizeView {

    private SynchronizePresenter synchronizePresenter;
    @Inject
    SynchronizeInteractor synchronizeInteractor;
    private OperationTypeDB operationTypeDB;
    private PropertyTypeDB propertyTypeDB;
    @BindView(R.id.linSynchronize) LinearLayout linSynchronize;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBotComponent().injectSynchronize(this);
        setContentView(R.layout.activity_synchronize);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        synchronizePresenter = new SynchronizePresenter(synchronizeInteractor, this);
        synchronizePresenter.loadSynchronize();
    }

    @Override
    public void addData(SynchronizeResponse synchronizeResponse) {
        operationTypeDB = new OperationTypeDB(this);
        propertyTypeDB = new PropertyTypeDB(this);

        for(SynchronizeResponse.DataBean.OperationTypeBean operationTypeBean
                : synchronizeResponse.getData().getOperation_type()){
            operationTypeDB.addOperationType(operationTypeBean);
        }

        for(SynchronizeResponse.DataBean.PropertyTypeBean propertyTypeBean
                : synchronizeResponse.getData().getProperty_type()){
            propertyTypeDB.addPropertyType(propertyTypeBean);
        }

    }

    @Override
    public void showMessageError(String message) {
        Snackbar.make(linSynchronize, message, Snackbar.LENGTH_SHORT).show();
    }


}
