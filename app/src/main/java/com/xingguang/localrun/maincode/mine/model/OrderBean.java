package com.xingguang.localrun.maincode.mine.model;

import java.util.List;

/**
 * 创建日期：2018/7/1
 * 描述:订单实体类
 * 作者:LiuYu
 */
public class OrderBean {
    /**
     * status : 1
     * msg : SUCCESS
     * data : {"list":[{"shop_id":"1","shop_name":"青松的店铺","order_id":"9","order_sn":"201806301739251455","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"9","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"shop_id":"1","shop_name":"青松的店铺","order_id":"8","order_sn":"201806301723111638","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"8","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"shop_id":"1","shop_name":"青松的店铺","order_id":"7","order_sn":"201806301721061795","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"7","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"shop_id":"1","shop_name":"青松的店铺","order_id":"6","order_sn":"201806301714023060","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"6","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"shop_id":"1","shop_name":"青松的店铺","order_id":"3","order_sn":"201806301552579029","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"3","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]}],"page":1}
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
         * list : [{"shop_id":"1","shop_name":"青松的店铺","order_id":"9","order_sn":"201806301739251455","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"9","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"shop_id":"1","shop_name":"青松的店铺","order_id":"8","order_sn":"201806301723111638","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"8","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"shop_id":"1","shop_name":"青松的店铺","order_id":"7","order_sn":"201806301721061795","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"7","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"shop_id":"1","shop_name":"青松的店铺","order_id":"6","order_sn":"201806301714023060","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"6","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]},{"shop_id":"1","shop_name":"青松的店铺","order_id":"3","order_sn":"201806301552579029","order_amount":"0.02","shipping_price":"0.01","order_status":"0","pay_status":"0","shipping_status":"0","order_type":1,"status_describe":"待支付","goods":[{"rec_id":"3","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]}]
         * page : 1
         */

        private int page;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * shop_id : 1
             * shop_name : 青松的店铺
             * order_id : 9
             * order_sn : 201806301739251455
             * order_amount : 0.02
             * shipping_price : 0.01
             * order_status : 0
             * pay_status : 0
             * shipping_status : 0
             * order_type : 1
             * status_describe : 待支付
             * goods : [{"rec_id":"9","goods_id":"5","goods_name":"可口可乐","spec_key_name":"容量:1L","goods_num":"1","is_comment":"0","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg"}]
             */

            private String shop_id;
            private String shop_name;
            private String order_id;
            private String order_sn;
            private String order_amount;
            private String shipping_price;
            private String order_status;
            private String pay_status;
            private String shipping_status;
            private int order_type;
            private String status_describe;
            private List<GoodsBean> goods;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getShipping_price() {
                return shipping_price;
            }

            public void setShipping_price(String shipping_price) {
                this.shipping_price = shipping_price;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getPay_status() {
                return pay_status;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public String getShipping_status() {
                return shipping_status;
            }

            public void setShipping_status(String shipping_status) {
                this.shipping_status = shipping_status;
            }

            public int getOrder_type() {
                return order_type;
            }

            public void setOrder_type(int order_type) {
                this.order_type = order_type;
            }

            public String getStatus_describe() {
                return status_describe;
            }

            public void setStatus_describe(String status_describe) {
                this.status_describe = status_describe;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * rec_id : 9
                 * goods_id : 5
                 * goods_name : 可口可乐
                 * spec_key_name : 容量:1L
                 * goods_num : 1
                 * is_comment : 0
                 * original_img : /Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg
                 */

                private String rec_id;
                private String goods_id;
                private String goods_name;
                private String spec_key_name;
                private String goods_num;
                private String is_comment;
                private String original_img;

                public String getRec_id() {
                    return rec_id;
                }

                public void setRec_id(String rec_id) {
                    this.rec_id = rec_id;
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

                public String getSpec_key_name() {
                    return spec_key_name;
                }

                public void setSpec_key_name(String spec_key_name) {
                    this.spec_key_name = spec_key_name;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }

                public String getIs_comment() {
                    return is_comment;
                }

                public void setIs_comment(String is_comment) {
                    this.is_comment = is_comment;
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
}
