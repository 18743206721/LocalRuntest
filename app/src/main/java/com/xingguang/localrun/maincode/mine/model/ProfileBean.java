package com.xingguang.localrun.maincode.mine.model;

/**
 * 创建日期：2018/7/11
 * 描述:
 * 作者:LiuYu
 */
public class ProfileBean{

    /**
     * status : 1
     * msg : 请求成功
     * data : {"goods_collect":"0","shop_collect":"1","goods_visit":"14"}
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
         * goods_collect : 0
         * shop_collect : 1
         * goods_visit : 14
         */

        private String goods_collect;
        private String shop_collect;
        private String goods_visit;

        public String getGoods_collect() {
            return goods_collect;
        }

        public void setGoods_collect(String goods_collect) {
            this.goods_collect = goods_collect;
        }

        public String getShop_collect() {
            return shop_collect;
        }

        public void setShop_collect(String shop_collect) {
            this.shop_collect = shop_collect;
        }

        public String getGoods_visit() {
            return goods_visit;
        }

        public void setGoods_visit(String goods_visit) {
            this.goods_visit = goods_visit;
        }
    }
}
