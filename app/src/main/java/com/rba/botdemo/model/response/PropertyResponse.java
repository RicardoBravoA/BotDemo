package com.rba.botdemo.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class PropertyResponse extends ChatResponse {

    private List<PropertyBean> property;

    public List<PropertyBean> getProperty() {
        return property;
    }

    public void setProperty(List<PropertyBean> property) {
        this.property = property;
    }

    public static class PropertyBean {
        /**
         * property_id : 5
         * image : http://cde.urbania.pe/elements/34517/proyectos/2587/galery/2587-15066442.jpg
         * title : Proyecto Gran La Mar
         * price : 70,850
         * money_type : US$
         * url : http://urbania.pe/ficha-proyecto/proyecto-gran-la-mar-lima-pueblo-libre-bluhouse-asesores-2587
         * operation_type_id : 1
         * property_type_id : 2
         */

        private int property_id;
        private String image;
        private String title;
        private String price;
        private String money_type;
        private String url;
        private int operation_type_id;
        private int property_type_id;

        public int getProperty_id() {
            return property_id;
        }

        public void setProperty_id(int property_id) {
            this.property_id = property_id;
        }

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getOperation_type_id() {
            return operation_type_id;
        }

        public void setOperation_type_id(int operation_type_id) {
            this.operation_type_id = operation_type_id;
        }

        public int getProperty_type_id() {
            return property_type_id;
        }

        public void setProperty_type_id(int property_type_id) {
            this.property_type_id = property_type_id;
        }
    }
}
