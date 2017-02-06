package com.rba.botdemo.chat;

import android.util.Log;

import com.google.gson.Gson;
import com.rba.botdemo.api.ApiService;
import com.rba.botdemo.api.ErrorUtil;
import com.rba.botdemo.api.NetworkError;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.ErrorResponse;

import java.util.Map;

import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class ChatInteractor {

    private ApiService apiService;

    public ChatInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    public Subscription sendMessage(Map<String, String> data, final ChatCallback callback){

        return  apiService.postMessage(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ChatResponse>>() {
                    @Override
                    public Observable<? extends ChatResponse> call(Throwable throwable) {
                        Log.i("z- onErrorResumeNext", throwable.getMessage());
                        return Observable.error(throwable);
                    }
                }).subscribe(new Subscriber<ChatResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            Response response = exception.response();
                            ErrorResponse errorResponse = ErrorUtil.parseError(response);
                            callback.onChatError(errorResponse);
                        } else {
                            callback.onChatFailure(new NetworkError(e));
                        }
                    }

                    @Override
                    public void onNext(ChatResponse chatResponse) {
                        Log.i("z- onNext", new Gson().toJson(chatResponse));
                        callback.onChatResponse(chatResponse);
                    }
                });

    }

}
