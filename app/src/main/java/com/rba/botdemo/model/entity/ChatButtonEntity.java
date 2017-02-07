package com.rba.botdemo.model.entity;

import java.util.List;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class ChatButtonEntity {

    private List<ChatButtonBean> chatButtonBeen;

    public List<ChatButtonBean> getChatButtonBeen() {
        return chatButtonBeen;
    }

    public void setChatButtonBeen(List<ChatButtonBean> chatButtonBeen) {
        this.chatButtonBeen = chatButtonBeen;
    }

    public static class ChatButtonBean {

        String type;
        String id;
        String description;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }




}
