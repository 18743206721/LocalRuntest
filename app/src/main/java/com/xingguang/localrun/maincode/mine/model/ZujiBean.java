package com.xingguang.localrun.maincode.mine.model;

import java.util.List;

/**
 * 创建日期：2018/6/14
 * 描述:
 * 作者:LiuYu
 */
public class ZujiBean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"date":"2018-06-13","goods":[{"visit_id":"7","goods_id":"9","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"visit_id":"8","goods_id":"10","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"visit_id":"9","goods_id":"15","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"date":"2018-06-12","goods":[{"visit_id":"5","goods_id":"7","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"visit_id":"6","goods_id":"8","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"date":"2018-06-11","goods":[{"visit_id":"1","goods_id":"18","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"visit_id":"2","goods_id":"4","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"visit_id":"3","goods_id":"5","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"visit_id":"4","goods_id":"6","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]}]
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
         * date : 2018-06-13
         * goods : [{"visit_id":"7","goods_id":"9","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"visit_id":"8","goods_id":"10","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"},{"visit_id":"9","goods_id":"15","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]
         */

        private String date;
        private List<GoodsBean> goods;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * visit_id : 7
             * goods_id : 9
             * goods_name : 可口可乐
             * shop_price : 2.50
             * original_img : /Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg
             */

            private String visit_id;
            private String goods_id;
            private String goods_name;
            private String shop_price;
            private String original_img;

            public String getVisit_id() {
                return visit_id;
            }

            public void setVisit_id(String visit_id) {
                this.visit_id = visit_id;
            }

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
}
