package com.rba.botdemo.chat;

import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.OperationResponse;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface ChatView {

    void init();

    void onClickChatButton(ChatButtonEntity chatButtonEntity);

    void showOperationData(OperationResponse operationResponse);

    void showPropertyData(ChatResponse.PropertyBean propertyResponse);

    void showPropertyTypeData(ChatResponse.PropertyTypeBean propertyTypeResponse);

    void showMessageError(String error);

    void send();

    void clear();

}
