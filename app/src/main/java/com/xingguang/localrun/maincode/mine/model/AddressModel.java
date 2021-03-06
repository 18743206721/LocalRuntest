package com.xingguang.localrun.maincode.mine.model;


import java.io.Serializable;
import java.util.List;

public class AddressModel implements Serializable {

    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"address_id":"8","user_id":"4","consignee":"吴青松","mobile":"17743142373","province":"1955","city":"1956","area":"1966","address":"金色华尔兹大厦2栋2门1204室","location":"125.289217,43.898283","is_default":"1"}]
     */

    private int status;
    private String msg;
    private List<AddressModel.DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<AddressModel.DataBean> getData() {
        return data;
    }

    public void setData(List<AddressModel.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address_id : 8
         * user_id : 4
         * consignee : 吴青松
         * mobile : 17743142373
         * province : 1955
         * city : 1956
         * area : 1966
         * address : 金色华尔兹大厦2栋2门1204室
         * location : 125.289217,43.898283
         * is_default : 1
         */

        private String address_id;
        private String user_id;
        private String consignee;
        private String mobile;
        private String province;
        private String city;
        private String area;
        private String address;
        private String location;
        private String is_default;
        private boolean isChecked;

        public DataBean(){

        }
        public DataBean(boolean isCheched) {
            this.isChecked = isCheched;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }
}
