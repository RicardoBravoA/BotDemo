package com.rba.botdemo.chat;

import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.response.ChatResponse;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface ChatView {

    void init();

    void onClickChatButton(int pos, ChatButtonEntity chatButtonEntity);

    void showData(ChatResponse chatResponse);

    void showMessageError(String error);

    void clear();

}
