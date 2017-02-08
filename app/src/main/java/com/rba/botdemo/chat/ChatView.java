package com.rba.botdemo.chat;

import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.PropertyBean;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface ChatView {

    void init();

    void onClickChatButton(ChatButtonEntity.ChatButtonBean chatButtonBean);

    void showOperationData(ChatResponse chatResponse);

    void showPropertyData(ChatResponse chatResponse);

    void showPropertyTypeData(ChatResponse chatResponse);

    void showMessageError(String error);

    void send();

    void onClickProperty(PropertyBean propertyBean);

    void clear();

}
