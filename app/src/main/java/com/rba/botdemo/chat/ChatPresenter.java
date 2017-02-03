package com.rba.botdemo.chat;

import com.rba.botdemo.util.Util;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class ChatPresenter {

    private ChatView chatView;

    public ChatPresenter(ChatView chatView){
        this.chatView = chatView;
    }

    public boolean validMessage(String message){
        return Util.validMessage(message);
    }


}
