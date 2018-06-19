package com.xingguang.localrun.maincode.mine.model;

import java.util.List;

/**
 * 创建日期：2018/6/19
 * 描述:收藏列表
 * 作者:LiuYu
 */
public class MyCollectionBean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"collect_id":"1","user_id":"4","goods_id":"18","add_time":"0","goods_name":"可口可乐","shop_id":"1","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","collect_count":"2"}]
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
         * collect_id : 1
         * user_id : 4
         * goods_id : 18
         * add_time : 0
         * goods_name : 可口可乐
         * shop_id : 1
         * shop_price : 2.50
         * original_img : /Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg
         * collect_count : 2
         */

        private String collect_id;
        private String user_id;
        private String goods_id;
        private String add_time;
        private String goods_name;
        private String shop_id;
        private String shop_price;
        private String original_img;
        private String collect_count;

        public String getCollect_id() {
            return collect_id;
        }

        public void setCollect_id(String collect_id) {
            this.collect_id = collect_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
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

        public String getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(String collect_count) {
            this.collect_count = collect_count;
        }
    }
}
