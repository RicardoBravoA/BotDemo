package com.rba.botdemo.api;

import com.rba.botdemo.BuildConfig;
import com.rba.botdemo.model.response.SynchronizeResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface ApiService {

    @GET(BuildConfig.URL_SYNCHRONIZE)
    Observable<SynchronizeResponse> getSynchronize();

    @FormUrlEncoded
    @POST(BuildConfig.URL_MESSAGE)
    Observable<Object> postMessage(@FieldMap Map<String, String> data);

}
