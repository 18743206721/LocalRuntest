package com.xingguang.localrun.maincode.classify.model;

import java.util.List;

/**
 * 创建日期：2018/6/26
 * 描述:
 * 作者:LiuYu
 */
public class ClassifBean {

    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"id":"2","shop_name":"张三的小店","logo":"/Public/logo.png","goods":[{"goods_id":"29","goods_name":"世界杯T恤","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","shop_price":"26.00"},{"goods_id":"27","goods_name":"世界杯T恤","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","shop_price":"26.00"},{"goods_id":"24","goods_name":"世界杯T恤","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","shop_price":"26.00"}]}]
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
         * id : 2
         * shop_name : 张三的小店
         * logo : /Public/logo.png
         * goods : [{"goods_id":"29","goods_name":"世界杯T恤","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","shop_price":"26.00"},{"goods_id":"27","goods_name":"世界杯T恤","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","shop_price":"26.00"},{"goods_id":"24","goods_name":"世界杯T恤","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","shop_price":"26.00"}]
         */

        private String id;
        private String shop_name;
        private String logo;
        private List<GoodsBean> goods;

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

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_id : 29
             * goods_name : 世界杯T恤
             * original_img : /Uploads/Picture/2018-05-07/5aefe1542de60.jpg
             * shop_price : 26.00
             */

            private String goods_id;
            private String goods_name;
            private String original_img;
            private String shop_price;

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

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }
        }
    }
}
