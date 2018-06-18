package com.xingguang.localrun.maincode.home.model;

/**
 * 创建日期：2018/6/18
 * 描述:店铺简介
 * 作者:LiuYu
 */
public class ShopJianjieBean {
    /**
     * status : 1
     * msg : SUCCESS
     * data : {"id":"1","shop_name":"青松的店铺","logo":"/Uploads/Picture/2018-06-14/5b2202d850ea3.png","licence":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","join_time":"2018年05月07日","status":"1","percent":0}
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
         * id : 1
         * shop_name : 青松的店铺
         * logo : /Uploads/Picture/2018-06-14/5b2202d850ea3.png
         * licence : /Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg
         * join_time : 2018年05月07日
         * status : 1
         * percent : 0
         */

        private String id;
        private String shop_name;
        private String logo;
        private String licence;
        private String join_time;
        private String status;
        private int percent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLicence() {
            return licence;
        }

        public void setLicence(String licence) {
            this.licence = licence;
        }

        public String getJoin_time() {
            return join_time;
        }

        public void setJoin_time(String join_time) {
            this.join_time = join_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }
}
