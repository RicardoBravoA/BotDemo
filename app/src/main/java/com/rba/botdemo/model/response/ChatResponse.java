package com.rba.botdemo.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class ChatResponse extends PropertyBean {

    /**
     * _meta : {"status":"success","code":"200"}
     * type : property
     * message : {"response_1":"Mostrar propiedades","response_2":"","response_type_id":4}
     */

    private MetaBean _meta;
    private String type;
    private MessageBean message;
    private List<PropertyBean> property;

    public MetaBean get_meta() {
        return _meta;
    }

    public void set_meta(MetaBean _meta) {
        this._meta = _meta;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public List<PropertyBean> getProperty() {
        return property;
    }

    public void setProperty(List<PropertyBean> property) {
        this.property = property;
    }

    public static class MetaBean {
        /**
         * status : success
         * code : 200
         */

        private String status;
        private String code;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class MessageBean {
        /**
         * response_1 : Mostrar propiedades
         * response_2 :
         * response_type_id : 4
         */

        private String response_1;
        private String response_2;
        private int response_type_id;

        public String getResponse_1() {
            return response_1;
        }

        public void setResponse_1(String response_1) {
            this.response_1 = response_1;
        }

        public String getResponse_2() {
            return response_2;
        }

        public void setResponse_2(String response_2) {
            this.response_2 = response_2;
        }

        public int getResponse_type_id() {
            return response_type_id;
        }

        public void setResponse_type_id(int response_type_id) {
            this.response_type_id = response_type_id;
        }
    }

    public List<PropertyTypeBean> getProperty_type() {
        return property_type;
    }

    private List<PropertyTypeBean> property_type;

    public static class PropertyTypeBean {
        /**
         * property_type : casa
         */

        private String property_type;

        public String getProperty_type() {
            return property_type;
        }

        public void setProperty_type(String property_type) {
            this.property_type = property_type;
        }
    }

    private List<OperationBean> operation;

    public List<OperationBean> getOperation() {
        return operation;
    }

    public void setOperation(List<OperationBean> operation) {
        this.operation = operation;
    }

    public static class OperationBean {
        /**
         * operation : venta
         */

        private String operation;

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }
    }

}
