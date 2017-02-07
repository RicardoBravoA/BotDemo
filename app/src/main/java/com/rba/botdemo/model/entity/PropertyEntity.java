package com.rba.botdemo.model.entity;

import com.rba.botdemo.model.response.PropertyBean;

import java.util.List;

/**
 * Created by Ricardo Bravo on 7/02/17.
 */

public class PropertyEntity {

    private List<PropertyBean> property;

    public List<PropertyBean> getProperty() {
        return property;
    }

    public void setProperty(List<PropertyBean> property) {
        this.property = property;
    }

}
