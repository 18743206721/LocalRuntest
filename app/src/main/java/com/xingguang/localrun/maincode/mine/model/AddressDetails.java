package com.xingguang.localrun.maincode.mine.model;

/**
 * 创建日期：2018/6/14
 * 描述:
 * 作者:LiuYu
 */
public class AddressDetails {


    /**
     * status : 1
     * msg : SUCCESS
     * data : {"address_id":"9","user_id":"6","consignee":"刘宇","mobile":"18743206721","province":"1955","city":"1956","area":"1957","address":"纳吉布","location":"125.728620,44.521785","is_default":"1"}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address_id : 9
         * user_id : 6
         * consignee : 刘宇
         * mobile : 18743206721
         * province : 1955
         * city : 1956
         * area : 1957
         * address : 纳吉布
         * location : 125.728620,44.521785
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
