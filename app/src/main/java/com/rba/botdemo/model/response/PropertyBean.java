package com.rba.botdemo.model.response;

/**
 * Created by Ricardo Bravo on 7/02/17.
 */

public class PropertyBean {
    /**
     * property_id : 2
     * image : http://cde.urbania.pe/elements/123410/proyectos/2138/galery/2138-11411084.jpg
     * title : Proyecto QUEBRADA DEL MAR
     * price : 222,000
     * money_type : US$
     * url : http://urbania.pe/ficha-proyecto/proyecto-quebrada-del-mar-lima-asia-armas-doomo-inmobiliaria-2138
     * operation_type_id : venta
     * property_type_id : casa
     */

    private int property_id;
    private String image;
    private String title;
    private String price;
    private String money_type;
    private String url;
    private String operationType;
    private String propertyType;

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

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}