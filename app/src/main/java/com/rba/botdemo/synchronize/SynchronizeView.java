package com.rba.botdemo.synchronize;

import com.rba.botdemo.model.response.SynchronizeResponse;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface SynchronizeView {

    void init();

    void addData(SynchronizeResponse synchronizeResponse);

    void showMessageError(String message);

}
