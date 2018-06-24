package com.xingguang.localrun.maincode.shop.model;

import java.util.List;

/**
 * 创建日期：2018/5/22
 * 描述:购物车商品信息
 * 作者:LiuYu
 */
public class GoodInfo {

    /**
     * status : 1
     * msg : SUCCESS
     * data : {"cartList":[{"id":"1","user_id":"4","session_id":"","goods_id":"19","goods_sn":"No00010000019","goods_name":"可口可乐","market_price":"2.50","goods_price":"6.00","member_goods_price":"6.00","goods_num":"2","spec_key":"6_12","spec_key_name":"容量:1L 口味:甜橙","selected":"1","add_time":"1529039954","goods_fee":12,"cut_fee":12,"total_fee":12,"goods":{"goods_id":"19","cat_id1":"5","cat_id2":"7","shop_id":"1","goods_sn":"No00010000019","goods_name":"可口可乐","store_count":"99","click_count":"3","sale_num":"0","shop_price":"2.50","market_price":"2.50","goods_remark":"可口可乐","goods_content":"&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe652e9278.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe6530afc8.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe65326548.jpg&quot; alt=&quot;&quot; /&gt;","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","is_on_sale":"1","on_time":"1525671525","sort":"50","is_recommend":"1","is_new":"0","is_hot":"0","last_update":"1528680556","goods_type":"0","spec_type":"3"}},{"id":"2","user_id":"4","session_id":"","goods_id":"18","goods_sn":"No00010000018","goods_name":"可口可乐","market_price":"2.50","goods_price":"2.50","member_goods_price":"2.50","goods_num":"1","spec_key":"","spec_key_name":"","selected":"1","add_time":"1529039964","goods_fee":2.5,"cut_fee":2.5,"total_fee":2.5,"goods":{"goods_id":"18","cat_id1":"5","cat_id2":"7","shop_id":"1","goods_sn":"No00010000018","goods_name":"可口可乐","store_count":"50","click_count":"13","sale_num":"3","shop_price":"2.50","market_price":"2.50","goods_remark":"可口可乐","goods_content":"&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe652e9278.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe6530afc8.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe65326548.jpg&quot; alt=&quot;&quot; /&gt;","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","is_on_sale":"1","on_time":"1525671525","sort":"50","is_recommend":"0","is_new":"0","is_hot":"0","last_update":"1525755892","goods_type":"0","spec_type":"0"}},{"id":"3","user_id":"4","session_id":"","goods_id":"23","goods_sn":"No00020000023","goods_name":"世界杯T恤","market_price":"30.00","goods_price":"26.00","member_goods_price":"26.00","goods_num":"1","spec_key":"","spec_key_name":"","selected":"1","add_time":"1529735369","goods_fee":26,"cut_fee":26,"total_fee":26,"goods":{"goods_id":"23","cat_id1":"3","cat_id2":"8","shop_id":"2","goods_sn":"No00020000023","goods_name":"世界杯T恤","store_count":"180","click_count":"0","sale_num":"0","shop_price":"26.00","market_price":"30.00","goods_remark":"阿斯达斯的","goods_content":"阿斯打扫打扫大岁大多电风扇的份上","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","is_on_sale":"1","on_time":"1529371602","sort":"50","is_recommend":"0","is_new":"0","is_hot":"0","last_update":"1529371645","goods_type":"0","spec_type":"4"}}],"cartPriceInfo":{"total_fee":40.5,"goods_fee":40.5,"goods_num":4}}
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
         * cartList : [{"id":"1","user_id":"4","session_id":"","goods_id":"19","goods_sn":"No00010000019","goods_name":"可口可乐","market_price":"2.50","goods_price":"6.00","member_goods_price":"6.00","goods_num":"2","spec_key":"6_12","spec_key_name":"容量:1L 口味:甜橙","selected":"1","add_time":"1529039954","goods_fee":12,"cut_fee":12,"total_fee":12,"goods":{"goods_id":"19","cat_id1":"5","cat_id2":"7","shop_id":"1","goods_sn":"No00010000019","goods_name":"可口可乐","store_count":"99","click_count":"3","sale_num":"0","shop_price":"2.50","market_price":"2.50","goods_remark":"可口可乐","goods_content":"&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe652e9278.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe6530afc8.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe65326548.jpg&quot; alt=&quot;&quot; /&gt;","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","is_on_sale":"1","on_time":"1525671525","sort":"50","is_recommend":"1","is_new":"0","is_hot":"0","last_update":"1528680556","goods_type":"0","spec_type":"3"}},{"id":"2","user_id":"4","session_id":"","goods_id":"18","goods_sn":"No00010000018","goods_name":"可口可乐","market_price":"2.50","goods_price":"2.50","member_goods_price":"2.50","goods_num":"1","spec_key":"","spec_key_name":"","selected":"1","add_time":"1529039964","goods_fee":2.5,"cut_fee":2.5,"total_fee":2.5,"goods":{"goods_id":"18","cat_id1":"5","cat_id2":"7","shop_id":"1","goods_sn":"No00010000018","goods_name":"可口可乐","store_count":"50","click_count":"13","sale_num":"3","shop_price":"2.50","market_price":"2.50","goods_remark":"可口可乐","goods_content":"&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe652e9278.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe6530afc8.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe65326548.jpg&quot; alt=&quot;&quot; /&gt;","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","is_on_sale":"1","on_time":"1525671525","sort":"50","is_recommend":"0","is_new":"0","is_hot":"0","last_update":"1525755892","goods_type":"0","spec_type":"0"}},{"id":"3","user_id":"4","session_id":"","goods_id":"23","goods_sn":"No00020000023","goods_name":"世界杯T恤","market_price":"30.00","goods_price":"26.00","member_goods_price":"26.00","goods_num":"1","spec_key":"","spec_key_name":"","selected":"1","add_time":"1529735369","goods_fee":26,"cut_fee":26,"total_fee":26,"goods":{"goods_id":"23","cat_id1":"3","cat_id2":"8","shop_id":"2","goods_sn":"No00020000023","goods_name":"世界杯T恤","store_count":"180","click_count":"0","sale_num":"0","shop_price":"26.00","market_price":"30.00","goods_remark":"阿斯达斯的","goods_content":"阿斯打扫打扫大岁大多电风扇的份上","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","is_on_sale":"1","on_time":"1529371602","sort":"50","is_recommend":"0","is_new":"0","is_hot":"0","last_update":"1529371645","goods_type":"0","spec_type":"4"}}]
         * cartPriceInfo : {"total_fee":40.5,"goods_fee":40.5,"goods_num":4}
         */

        private CartPriceInfoBean cartPriceInfo;
        private List<CartListBean> cartList;

        public CartPriceInfoBean getCartPriceInfo() {
            return cartPriceInfo;
        }

        public void setCartPriceInfo(CartPriceInfoBean cartPriceInfo) {
            this.cartPriceInfo = cartPriceInfo;
        }

        public List<CartListBean> getCartList() {
            return cartList;
        }

        public void setCartList(List<CartListBean> cartList) {
            this.cartList = cartList;
        }

        public static class CartPriceInfoBean {
            /**
             * total_fee : 40.5
             * goods_fee : 40.5
             * goods_num : 4
             */

            private double total_fee;
            private double goods_fee;
            private int goods_num;

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }

            public double getGoods_fee() {
                return goods_fee;
            }

            public void setGoods_fee(double goods_fee) {
                this.goods_fee = goods_fee;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }
        }

        public static class CartListBean {
            /**
             * id : 1
             * user_id : 4
             * session_id :
             * goods_id : 19
             * goods_sn : No00010000019
             * goods_name : 可口可乐
             * market_price : 2.50
             * goods_price : 6.00
             * member_goods_price : 6.00
             * goods_num : 2
             * spec_key : 6_12
             * spec_key_name : 容量:1L 口味:甜橙
             * selected : 1
             * add_time : 1529039954
             * goods_fee : 12
             * cut_fee : 12
             * total_fee : 12
             * goods : {"goods_id":"19","cat_id1":"5","cat_id2":"7","shop_id":"1","goods_sn":"No00010000019","goods_name":"可口可乐","store_count":"99","click_count":"3","sale_num":"0","shop_price":"2.50","market_price":"2.50","goods_remark":"可口可乐","goods_content":"&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe652e9278.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe6530afc8.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe65326548.jpg&quot; alt=&quot;&quot; /&gt;","original_img":"/Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg","is_on_sale":"1","on_time":"1525671525","sort":"50","is_recommend":"1","is_new":"0","is_hot":"0","last_update":"1528680556","goods_type":"0","spec_type":"3"}
             */

            private String id;
            private String user_id;
            private String session_id;
            private String goods_id;
            private String goods_sn;
            private String goods_name;
            private String market_price;
            private String goods_price;
            private String member_goods_price;
            private String goods_num;
            private String spec_key;
            private String spec_key_name;
            private String selected;
            private String add_time;
            private double goods_fee;
            private double cut_fee;
            private double total_fee;
            private boolean isChoose;
            private GoodsBean goods;

            public boolean isChoose() {
                return isChoose;
            }

            public void setChoose(boolean choose) {
                isChoose = choose;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getSession_id() {
                return session_id;
            }

            public void setSession_id(String session_id) {
                this.session_id = session_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getMember_goods_price() {
                return member_goods_price;
            }

            public void setMember_goods_price(String member_goods_price) {
                this.member_goods_price = member_goods_price;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getSpec_key() {
                return spec_key;
            }

            public void setSpec_key(String spec_key) {
                this.spec_key = spec_key;
            }

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public double getGoods_fee() {
                return goods_fee;
            }

            public void setGoods_fee(double goods_fee) {
                this.goods_fee = goods_fee;
            }

            public double getCut_fee() {
                return cut_fee;
            }

            public void setCut_fee(double cut_fee) {
                this.cut_fee = cut_fee;
            }

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(int total_fee) {
                this.total_fee = total_fee;
            }

            public GoodsBean getGoods() {
                return goods;
            }

            public void setGoods(GoodsBean goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * goods_id : 19
                 * cat_id1 : 5
                 * cat_id2 : 7
                 * shop_id : 1
                 * goods_sn : No00010000019
                 * goods_name : 可口可乐
                 * store_count : 99
                 * click_count : 3
                 * sale_num : 0
                 * shop_price : 2.50
                 * market_price : 2.50
                 * goods_remark : 可口可乐
                 * goods_content : &lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe652e9278.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe6530afc8.jpg&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/Uploads/Editor/2018-05-07/5aefe65326548.jpg&quot; alt=&quot;&quot; /&gt;
                 * original_img : /Uploads/Picture/2018-05-07/5aefe0d3543a8.jpg
                 * is_on_sale : 1
                 * on_time : 1525671525
                 * sort : 50
                 * is_recommend : 1
                 * is_new : 0
                 * is_hot : 0
                 * last_update : 1528680556
                 * goods_type : 0
                 * spec_type : 3
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
            }
        }
    }
}
