package com.rba.botdemo.chat;

import com.rba.botdemo.api.NetworkError;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.ErrorResponse;
import com.rba.botdemo.model.response.OperationResponse;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public interface ChatCallback {

    void onChatOperationResponse(OperationResponse operationResponse);

    void onChatPropertyResponse(ChatResponse.PropertyBean propertyResponse);

    void onChatPropertyTypeResponse(ChatResponse.PropertyTypeBean propertyTypeResponse);

    void onChatError(ErrorResponse errorResponse);

    void onChatFailure(NetworkError networkError);

}
