package com.rba.botdemo.chat;

import com.rba.botdemo.model.entity.ChatButtonEntity;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public interface ChatView {

    void init();

    void onClickChatButton(ChatButtonEntity chatButtonEntity);

}
