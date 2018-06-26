package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/26
 * 描述:商品规格
 * 作者:LiuYu
 */
public class SpecBean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"item_id":"3","goods_id":"5","key":"6","key_name":"容量:1L","price":"6.00","store_count":"0","spec_img":null},{"item_id":"1","goods_id":"5","key":"4","key_name":"容量:125ml","price":"1.50","store_count":"0","spec_img":null}]
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
         * item_id : 3
         * goods_id : 5
         * key : 6
         * key_name : 容量:1L
         * price : 6.00
         * store_count : 0
         * spec_img : null
         */

        private String item_id;
        private String goods_id;
        private String key;
        private String key_name;
        private String price;
        private String store_count;
        private Object spec_img;
        private String isClick;

        public String getIsClick() {
            return isClick;
        }

        public void setIsClick(String isClick) {
            this.isClick = isClick;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey_name() {
            return key_name;
        }

        public void setKey_name(String key_name) {
            this.key_name = key_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }

        public Object getSpec_img() {
            return spec_img;
        }

        public void setSpec_img(Object spec_img) {
            this.spec_img = spec_img;
        }
    }
}
