package com.rba.botdemo.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class SynchronizeResponse {


    /**
     * data : {"operation_type":[{"operation_type_id":1,"operation_id":"venta","operation_description":"Venta"},{"operation_type_id":2,"operation_id":"alquiler","operation_description":"Alquiler"}],"property_type":[{"property_type_id":1,"property_id":"casa","property_description":"Casa"},{"property_type_id":2,"property_id":"departamento","property_description":"Departamento"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<OperationTypeBean> operation_type;
        private List<PropertyTypeBean> property_type;

        public List<OperationTypeBean> getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(List<OperationTypeBean> operation_type) {
            this.operation_type = operation_type;
        }

        public List<PropertyTypeBean> getProperty_type() {
            return property_type;
        }

        public void setProperty_type(List<PropertyTypeBean> property_type) {
            this.property_type = property_type;
        }

        public static class OperationTypeBean {
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

        public static class PropertyTypeBean {
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
}
