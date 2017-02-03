package com.rba.botdemo.model.response;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class ErrorResponse {

    /**
     * _meta : {"status":"error","code":"101"}
     */

    private MetaBean _meta;

    public MetaBean get_meta() {
        return _meta;
    }

    public void set_meta(MetaBean _meta) {
        this._meta = _meta;
    }

    public static class MetaBean {
        /**
         * status : error
         * code : 101
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
}