package com.rba.botdemo.model.entity;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class ChatButtonEntity {

    String id;
    String description;

    public ChatButtonEntity(String id, String description) {
        this.id = id;
        this.description = description;
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
