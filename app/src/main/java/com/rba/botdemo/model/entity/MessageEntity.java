package com.rba.botdemo.model.entity;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class MessageEntity {

    int type;
    String message;

    public MessageEntity() {
    }

    public MessageEntity(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
