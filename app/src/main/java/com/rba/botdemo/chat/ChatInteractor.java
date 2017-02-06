package com.rba.botdemo.chat;

import android.util.Log;

import com.google.gson.Gson;
import com.rba.botdemo.api.ApiService;
import com.rba.botdemo.api.ErrorUtil;
import com.rba.botdemo.api.NetworkError;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.ErrorResponse;
import com.rba.botdemo.model.response.OperationResponse;
import com.rba.botdemo.model.response.PropertyResponse;
import com.rba.botdemo.model.response.PropertyTypeResponse;
import com.rba.botdemo.util.Constant;

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
                .onErrorResumeNext(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        Log.i("z- onErrorResumeNext", throwable.getMessage());
                        return Observable.error(throwable);
                    }
                }).subscribe(new Subscriber<Object>() {
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
                    public void onNext(Object o) {
                        ChatResponse chatResponse = new Gson().fromJson(new Gson().toJson(o), ChatResponse.class);
                        Log.i("z- type", chatResponse.getType());

                        if(chatResponse.getType().equals(Constant.TAG_OPERATION)){
                            OperationResponse operationResponse
                                    = new Gson().fromJson(
                                    new Gson().toJson(o), OperationResponse.class);
                            Log.i("z- data", new Gson().toJson(operationResponse));
                            callback.onChatOperationResponse(operationResponse);
                        } else if(chatResponse.getType().equals(Constant.TAG_PROPERTY_TYPE)){
                            PropertyTypeResponse propertyTypeResponse
                                    = new Gson().fromJson(
                                    new Gson().toJson(o), PropertyTypeResponse.class);
                            Log.i("z- data", new Gson().toJson(propertyTypeResponse));
                            callback.onChatPropertyTypeResponse(propertyTypeResponse);
                        } else if(chatResponse.getType().equals(Constant.TAG_PROPERTY)){
                            PropertyResponse propertyResponse
                                    = new Gson().fromJson(
                                    new Gson().toJson(o), PropertyResponse.class);
                            Log.i("z- data", new Gson().toJson(propertyResponse));
                            callback.onChatPropertyResponse(propertyResponse);
                        }

                        /*
                        if(o instanceof ChatResponse){
                            ChatResponse chatResponse = (ChatResponse) o;
                            Log.i("z- chatResponse", new Gson().toJson(chatResponse));
                        }else if(o instanceof PropertyTypeResponse){
                            PropertyTypeResponse propertyTypeResponse = (PropertyTypeResponse) o;
                            Log.i("z- propertyTypeResponse", new Gson().toJson(propertyTypeResponse));
                        }else if(o instanceof PropertyResponse){
                            PropertyResponse propertyResponse = (PropertyResponse) o;
                            Log.i("z- chatResponse", new Gson().toJson(propertyResponse));
                        }
                        */

                    }
                });

    }

}
