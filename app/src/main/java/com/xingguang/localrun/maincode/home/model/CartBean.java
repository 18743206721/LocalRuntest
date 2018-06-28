package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/28
 * 描述:购物车结算bean
 * 作者:LiuYu
 */
public class CartBean {

    /**
     * status : 1
     * msg : SUCCESS
     * data : {"cartlist":[{"shop_id":"2","shop_name":"张三的小店","goods":[{"shop_id":"2","goods_id":"29","goods_name":"世界杯T恤","goods_num":"1","goods_price":"26.00","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","spec_key_name":"无"}]}],"goods_num":1,"address":{"address_id":"7","user_id":"5","consignee":"吴青","mobile":"17743142373","province":"1","city":"2","area":"3","address":"北京密云区城区","location":"116.840226,40.375518","distance":"777813","shipping_price":"20.00","is_default":"1","address2":"北京密云区城区北京密云区城区"},"total_amount":46,"shipping_price":"20.00"}
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
         * cartlist : [{"shop_id":"2","shop_name":"张三的小店","goods":[{"shop_id":"2","goods_id":"29","goods_name":"世界杯T恤","goods_num":"1","goods_price":"26.00","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","spec_key_name":"无"}]}]
         * goods_num : 1
         * address : {"address_id":"7","user_id":"5","consignee":"吴青","mobile":"17743142373","province":"1","city":"2","area":"3","address":"北京密云区城区","location":"116.840226,40.375518","distance":"777813","shipping_price":"20.00","is_default":"1","address2":"北京密云区城区北京密云区城区"}
         * total_amount : 46
         * shipping_price : 20.00
         */

        private int goods_num;
        private AddressBean address;
        private double total_amount;
        private String shipping_price;
        private List<CartlistBean> cartlist;

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(int total_amount) {
            this.total_amount = total_amount;
        }

        public String getShipping_price() {
            return shipping_price;
        }

        public void setShipping_price(String shipping_price) {
            this.shipping_price = shipping_price;
        }

        public List<CartlistBean> getCartlist() {
            return cartlist;
        }

        public void setCartlist(List<CartlistBean> cartlist) {
            this.cartlist = cartlist;
        }

        public static class AddressBean {
            /**
             * address_id : 7
             * user_id : 5
             * consignee : 吴青
             * mobile : 17743142373
             * province : 1
             * city : 2
             * area : 3
             * address : 北京密云区城区
             * location : 116.840226,40.375518
             * distance : 777813
             * shipping_price : 20.00
             * is_default : 1
             * address2 : 北京密云区城区北京密云区城区
             */

            private String address_id;
            private String user_id;
            private String consignee;
            private String mobile;
            private String province;
            private String city;
            private String area;
            private String address;
            private String location;
            private String distance;
            private String shipping_price;
            private String is_default;
            private String address2;

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getShipping_price() {
                return shipping_price;
            }

            public void setShipping_price(String shipping_price) {
                this.shipping_price = shipping_price;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }
        }

        public static class CartlistBean {
            /**
             * shop_id : 2
             * shop_name : 张三的小店
             * goods : [{"shop_id":"2","goods_id":"29","goods_name":"世界杯T恤","goods_num":"1","goods_price":"26.00","original_img":"/Uploads/Picture/2018-05-07/5aefe1542de60.jpg","spec_key_name":"无"}]
             */

            private String shop_id;
            private String shop_name;
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

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * shop_id : 2
                 * goods_id : 29
                 * goods_name : 世界杯T恤
                 * goods_num : 1
                 * goods_price : 26.00
                 * original_img : /Uploads/Picture/2018-05-07/5aefe1542de60.jpg
                 * spec_key_name : 无
                 */

                private String shop_id;
                private String goods_id;
                private String goods_name;
                private String goods_num;
                private String goods_price;
                private String original_img;
                private String spec_key_name;

                public String getShop_id() {
                    return shop_id;
                }

                public void setShop_id(String shop_id) {
                    this.shop_id = shop_id;
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

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getOriginal_img() {
                    return original_img;
                }

                public void setOriginal_img(String original_img) {
                    this.original_img = original_img;
                }

                public String getSpec_key_name() {
                    return spec_key_name;
                }

                public void setSpec_key_name(String spec_key_name) {
                    this.spec_key_name = spec_key_name;
                }
            }
        }
    }
}
