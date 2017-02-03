package com.rba.botdemo.api;

import com.rba.botdemo.BuildConfig;
import com.rba.botdemo.model.response.SynchronizeResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface ApiService {

    @GET(BuildConfig.URL_SYNCHRONIZE)
    Observable<SynchronizeResponse> getSynchronize();

}
