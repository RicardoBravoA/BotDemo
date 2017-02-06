package com.rba.botdemo.chat;

import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.response.OperationResponse;
import com.rba.botdemo.model.response.PropertyResponse;
import com.rba.botdemo.model.response.PropertyTypeResponse;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface ChatView {

    void init();

    void onClickChatButton(int pos, ChatButtonEntity chatButtonEntity);

    void showOperationData(OperationResponse operationResponse);

    void showPropertyData(PropertyResponse propertyResponse);

    void showPropertyTypeData(PropertyTypeResponse propertyTypeResponse);

    void showMessageError(String error);

    void clear();

}
