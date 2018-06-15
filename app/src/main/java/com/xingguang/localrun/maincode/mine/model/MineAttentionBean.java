package com.xingguang.localrun.maincode.mine.model;

import java.util.List;

/**
 * 创建日期：2018/6/14
 * 描述:关注的店铺列表实体类
 * 作者:LiuYu
 */
public class MineAttentionBean {

    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"collect_id":"1","user_id":"4","shop_id":"2","add_time":"1528969736","shop_name":"张三的小店"}]
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
         * shop_id : 2
         * add_time : 1528969736
         * shop_name : 张三的小店
         */

        private String collect_id;
        private String user_id;
        private String shop_id;
        private String add_time;
        private String shop_name;
        private String logo;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

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

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }
    }
}
