package com.xingguang.localrun.maincode.shop.model;

import com.google.gson.annotations.SerializedName;

/**
 * 创建日期：2018/6/30
 * 描述:
 * 作者:LiuYu
 */
public class WeixinBean {


    /**
     * status : 1
     * msg :
     * data : {"appid":"wxbe852bea37d724f9","noncestr":"a4b039d6a739b867ffdefd9f66207dc8","package":"Sign=WXPay","partnerid":"1505077811","prepayid":"wx30145142959401a2317513693743182519","timestamp":1530341502,"sign":"2DD52A9DAB95D30B1A3660694C3CEC0D","order_sn":"201806301451414446","sn_type":"parent_sn","appsecret":"aaaaaaaabbbbbbbb1111111122222222"}
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
         * appid : wxbe852bea37d724f9
         * noncestr : a4b039d6a739b867ffdefd9f66207dc8
         * package : Sign=WXPay
         * partnerid : 1505077811
         * prepayid : wx30145142959401a2317513693743182519
         * timestamp : 1530341502
         * sign : 2DD52A9DAB95D30B1A3660694C3CEC0D
         * order_sn : 201806301451414446
         * sn_type : parent_sn
         * appsecret : aaaaaaaabbbbbbbb1111111122222222
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;
        private String order_sn;
        private String sn_type;
        private String appsecret;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getSn_type() {
            return sn_type;
        }

        public void setSn_type(String sn_type) {
            this.sn_type = sn_type;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }
    }
}
