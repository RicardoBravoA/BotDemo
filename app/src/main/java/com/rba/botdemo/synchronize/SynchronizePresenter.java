package com.rba.botdemo.synchronize;

import com.rba.botdemo.api.NetworkError;
import com.rba.botdemo.model.response.ErrorResponse;
import com.rba.botdemo.model.response.SynchronizeResponse;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class SynchronizePresenter {

    private SynchronizeInteractor synchronizeInteractor;
    private SynchronizeView synchronizeView;
    private CompositeSubscription subscription;

    public SynchronizePresenter(SynchronizeInteractor synchronizeInteractor, SynchronizeView synchronizeView){
        this.synchronizeInteractor = synchronizeInteractor;
        this.synchronizeView = synchronizeView;
    }

    public void loadSynchronize() {
        this.subscription = new CompositeSubscription();

        Subscription subscription = synchronizeInteractor.getSynchronize(new SynchronizeCallback() {
            @Override
            public void onSynchronizeResponse(SynchronizeResponse synchronizeResponse) {
                synchronizeView.addData(synchronizeResponse);
            }

            @Override
            public void onSynchronizeError(ErrorResponse errorResponse) {
                synchronizeView.showMessageError(errorResponse.get_meta().getStatus());
            }

            @Override
            public void onSynchronizeFailure(NetworkError networkError) {
                synchronizeView.showMessageError(networkError.getMessage());
            }
        });

        this.subscription.add(subscription);

    }


    public void cancel() {
        subscription.unsubscribe();
    }

}
