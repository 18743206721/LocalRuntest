package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/27
 * 描述:商品集合
 * 作者:LiuYu
 */
public class SearchOneBean {

    /**
     * status : 1
     * msg : SUCCESS
     * data : {"type":1,"keyword":"可乐","sort":1,"data":[{"goods_id":"18","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"3"},{"goods_id":"17","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"2"},{"goods_id":"16","goods_name":"可口可乐","shop_price":"1.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"19","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"15","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"14","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"13","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"12","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"11","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"10","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"}]}
     */

    private int status;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * type : 1
         * keyword : 可乐
         * sort : 1
         * data : [{"goods_id":"18","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"3"},{"goods_id":"17","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"2"},{"goods_id":"16","goods_name":"可口可乐","shop_price":"1.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"19","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"15","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"14","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"13","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"12","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"11","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"},{"goods_id":"10","goods_name":"可口可乐","shop_price":"2.50","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","sale_num":"0"}]
         */

        private int type;
        private String keyword;
        private int sort;
        private List<DataBean> data;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * goods_id : 18
             * goods_name : 可口可乐
             * shop_price : 2.50
             * original_img : /Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg
             * sale_num : 3
             */

            private String goods_id;
            private String goods_name;
            private String shop_price;
            private String original_img;
            private String sale_num;

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

            public String getSale_num() {
                return sale_num;
            }

            public void setSale_num(String sale_num) {
                this.sale_num = sale_num;
            }
        }
    }
}
