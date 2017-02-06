package com.rba.botdemo.chat;

import com.rba.botdemo.api.NetworkError;
import com.rba.botdemo.model.response.ErrorResponse;
import com.rba.botdemo.model.response.OperationResponse;
import com.rba.botdemo.model.response.PropertyResponse;
import com.rba.botdemo.model.response.PropertyTypeResponse;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public interface ChatCallback {

    void onChatOperationResponse(OperationResponse operationResponse);

    void onChatPropertyResponse(PropertyResponse propertyResponse);

    void onChatPropertyTypeResponse(PropertyTypeResponse propertyTypeResponse);

    void onChatError(ErrorResponse errorResponse);

    void onChatFailure(NetworkError networkError);

}
