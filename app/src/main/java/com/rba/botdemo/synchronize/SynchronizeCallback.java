package com.rba.botdemo.synchronize;

import android.accounts.NetworkErrorException;

import com.rba.botdemo.model.response.ErrorResponse;
import com.rba.botdemo.model.response.SynchronizeResponse;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface SynchronizeCallback {

    void onSynchronizeResponse(SynchronizeResponse synchronizeResponse);

    void onSynchronizeError(ErrorResponse errorResponse);

    void onSynchronizeFailure(NetworkErrorException networkError);


}
