package com.rba.botdemo.model.response;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class PropertyResponse {

    String image;
    String title;
    String price;
    String money_type;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }
}
