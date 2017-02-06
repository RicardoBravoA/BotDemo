package com.rba.botdemo.chat;

import com.rba.botdemo.api.NetworkError;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.ErrorResponse;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public interface ChatCallback {

    void onChatOperationResponse(ChatResponse chatResponse);

    void onChatPropertyResponse(ChatResponse chatResponse);

    void onChatPropertyTypeResponse(ChatResponse chatResponse);

    void onChatError(ErrorResponse errorResponse);

    void onChatFailure(NetworkError networkError);

}
