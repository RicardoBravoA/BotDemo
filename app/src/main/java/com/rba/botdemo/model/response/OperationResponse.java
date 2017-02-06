package com.rba.botdemo.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class OperationResponse extends ChatResponse {

    private List<OperationBean> operation;

    public List<OperationBean> getOperation() {
        return operation;
    }

    public void setOperation(List<OperationBean> operation) {
        this.operation = operation;
    }

    public static class OperationBean {
        /**
         * operation_type_id : 1
         * operation_id : venta
         * operation_description : Venta
         */

        private int operation_type_id;
        private String operation_id;
        private String operation_description;

        public int getOperation_type_id() {
            return operation_type_id;
        }

        public void setOperation_type_id(int operation_type_id) {
            this.operation_type_id = operation_type_id;
        }

        public String getOperation_id() {
            return operation_id;
        }

        public void setOperation_id(String operation_id) {
            this.operation_id = operation_id;
        }

        public String getOperation_description() {
            return operation_description;
        }

        public void setOperation_description(String operation_description) {
            this.operation_description = operation_description;
        }
    }
}
