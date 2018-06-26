package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/21
 * 描述:商品详情
 * 作者:LiuYu
 */
public class GoodsDetailsBean {

    /**
     * status : 1
     * msg : SUCCESS
     * data : {"goods_id":"4","cat_id1":"5","cat_id2":"7","shop_id":"1","goods_sn":"No00010000004","goods_name":"可口可乐","store_count":"50","click_count":"0","sale_num":"0","shop_price":"2.50","market_price":"2.50","goods_remark":"可口可乐","goods_content":"","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","is_on_sale":"1","on_time":"1525671377","sort":"50","is_recommend":"0","is_new":"0","is_hot":"0","last_update":"0","goods_type":"0","spec_type":"0","goods_images":[{"img_id":"6","goods_id":"4","image_url":"/Uploads/Picture/2018-05-07/5aefe5cc9b460.jpg"},{"img_id":"5","goods_id":"4","image_url":"/Uploads/Picture/2018-05-07/5aefe5cc87028.jpg"},{"img_id":"4","goods_id":"4","image_url":"/Uploads/Picture/2018-05-07/5aefe5cc72bf0.jpg"}],"is_collected":0,"collect_id":0}
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
         * goods_id : 4
         * cat_id1 : 5
         * cat_id2 : 7
         * shop_id : 1
         * goods_sn : No00010000004
         * goods_name : 可口可乐
         * store_count : 50
         * click_count : 0
         * sale_num : 0
         * shop_price : 2.50
         * market_price : 2.50
         * goods_remark : 可口可乐
         * goods_content :
         * original_img : /Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg
         * is_on_sale : 1
         * on_time : 1525671377
         * sort : 50
         * is_recommend : 0
         * is_new : 0
         * is_hot : 0
         * last_update : 0
         * goods_type : 0
         * spec_type : 0
         * goods_images : [{"img_id":"6","goods_id":"4","image_url":"/Uploads/Picture/2018-05-07/5aefe5cc9b460.jpg"},{"img_id":"5","goods_id":"4","image_url":"/Uploads/Picture/2018-05-07/5aefe5cc87028.jpg"},{"img_id":"4","goods_id":"4","image_url":"/Uploads/Picture/2018-05-07/5aefe5cc72bf0.jpg"}]
         * is_collected : 0
         * collect_id : 0
         */

        private String goods_id;
        private String cat_id1;
        private String cat_id2;
        private String shop_id;
        private String goods_sn;
        private String goods_name;
        private String store_count;
        private String click_count;
        private String sale_num;
        private String shop_price;
        private String market_price;
        private String goods_remark;
        private String goods_content;
        private String original_img;
        private String is_on_sale;
        private String on_time;
        private String sort;
        private String is_recommend;
        private String is_new;
        private String is_hot;
        private String last_update;
        private String goods_type;
        private String spec_type;
        private int is_collected;
        private int collect_id;
        private List<GoodsImagesBean> goods_images;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getCat_id1() {
            return cat_id1;
        }

        public void setCat_id1(String cat_id1) {
            this.cat_id1 = cat_id1;
        }

        public String getCat_id2() {
            return cat_id2;
        }

        public void setCat_id2(String cat_id2) {
            this.cat_id2 = cat_id2;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }

        public String getClick_count() {
            return click_count;
        }

        public void setClick_count(String click_count) {
            this.click_count = click_count;
        }

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getGoods_remark() {
            return goods_remark;
        }

        public void setGoods_remark(String goods_remark) {
            this.goods_remark = goods_remark;
        }

        public String getGoods_content() {
            return goods_content;
        }

        public void setGoods_content(String goods_content) {
            this.goods_content = goods_content;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public String getIs_on_sale() {
            return is_on_sale;
        }

        public void setIs_on_sale(String is_on_sale) {
            this.is_on_sale = is_on_sale;
        }

        public String getOn_time() {
            return on_time;
        }

        public void setOn_time(String on_time) {
            this.on_time = on_time;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(String is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getSpec_type() {
            return spec_type;
        }

        public void setSpec_type(String spec_type) {
            this.spec_type = spec_type;
        }

        public int getIs_collected() {
            return is_collected;
        }

        public void setIs_collected(int is_collected) {
            this.is_collected = is_collected;
        }

        public int getCollect_id() {
            return collect_id;
        }

        public void setCollect_id(int collect_id) {
            this.collect_id = collect_id;
        }

        public List<GoodsImagesBean> getGoods_images() {
            return goods_images;
        }

        public void setGoods_images(List<GoodsImagesBean> goods_images) {
            this.goods_images = goods_images;
        }

        public static class GoodsImagesBean {
            /**
             * img_id : 6
             * goods_id : 4
             * image_url : /Uploads/Picture/2018-05-07/5aefe5cc9b460.jpg
             */

            private String img_id;
            private String goods_id;
            private String image_url;

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }
    }
}
