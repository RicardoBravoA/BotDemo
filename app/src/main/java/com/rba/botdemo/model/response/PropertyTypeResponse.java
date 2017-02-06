package com.rba.botdemo.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class PropertyTypeResponse extends ChatResponse {

    private List<PropertyBean> property;

    public List<PropertyBean> getProperty() {
        return property;
    }

    public void setProperty(List<PropertyBean> property) {
        this.property = property;
    }

    public static class PropertyBean {
        /**
         * property_type_id : 1
         * property_id : casa
         * property_description : Casa
         */

        private int property_type_id;
        private String property_id;
        private String property_description;

        public int getProperty_type_id() {
            return property_type_id;
        }

        public void setProperty_type_id(int property_type_id) {
            this.property_type_id = property_type_id;
        }

        public String getProperty_id() {
            return property_id;
        }

        public void setProperty_id(String property_id) {
            this.property_id = property_id;
        }

        public String getProperty_description() {
            return property_description;
        }

        public void setProperty_description(String property_description) {
            this.property_description = property_description;
        }
    }
}
