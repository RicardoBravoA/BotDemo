package com.rba.botdemo.model.response;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class ChatResponse {

    /**
     * _meta : {"status":"success","code":"200"}
     * type : property
     * message : {"response_1":"Mostrar propiedades","response_2":"","response_type_id":4}
     */

    private MetaBean _meta;
    private String type;
    private MessageBean message;

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
}
