package com.rba.botdemo.synchronize;

import android.util.Log;

import com.google.gson.Gson;
import com.rba.botdemo.api.ApiService;
import com.rba.botdemo.api.ErrorUtil;
import com.rba.botdemo.api.NetworkError;
import com.rba.botdemo.model.response.ErrorResponse;
import com.rba.botdemo.model.response.SynchronizeResponse;

import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class SynchronizeInteractor {

    private ApiService apiService;

    public SynchronizeInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    public Subscription getSynchronize(final SynchronizeCallback callback){

        return  apiService.getSynchronize()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends SynchronizeResponse>>() {
                    @Override
                    public Observable<? extends SynchronizeResponse> call(Throwable throwable) {
                        Log.i("z- onErrorResumeNext", throwable.getMessage());
                        return Observable.error(throwable);
                    }
                }).subscribe(new Subscriber<SynchronizeResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            Response response = exception.response();
                            ErrorResponse errorResponse = ErrorUtil.parseError(response);
                            callback.onSynchronizeError(errorResponse);
                        } else {
                            callback.onSynchronizeFailure(new NetworkError(e));
                        }
                    }

                    @Override
                    public void onNext(SynchronizeResponse synchronizeResponse) {
                        Log.i("z- onNext", new Gson().toJson(synchronizeResponse));
                        callback.onSynchronizeResponse(synchronizeResponse);
                    }
                });

    }

}
