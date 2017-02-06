package com.rba.botdemo.chat;

import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.response.ChatResponse;

import java.util.List;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface ChatView {

    void init();

    void onClickChatButton(ChatButtonEntity chatButtonEntity);

    void showOperationData(ChatResponse chatResponse);

    void showPropertyData(ChatResponse chatResponse);

    void showPropertyTypeData(ChatResponse chatResponse);

    void showMessageError(String error);

    void send();

    void clear();

}
