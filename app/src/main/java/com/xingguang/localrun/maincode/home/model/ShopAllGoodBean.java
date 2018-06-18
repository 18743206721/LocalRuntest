package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/18
 * 描述:店铺全部宝贝
 * 作者:LiuYu
 */
public class ShopAllGoodBean {
    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"goods_id":"9","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"goods_id":"8","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"goods_id":"7","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"goods_id":"6","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"goods_id":"5","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"goods_id":"4","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goods_id : 9
         * goods_name : 可口可乐
         * shop_price : 2.50
         * original_img : /Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg
         */

        private String goods_id;
        private String goods_name;
        private String shop_price;
        private String original_img;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }
    }
}
