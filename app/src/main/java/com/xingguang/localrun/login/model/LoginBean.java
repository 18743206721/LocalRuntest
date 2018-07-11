package com.xingguang.localrun.login.model;

/**
 * 创建日期：2018/6/12
 * 描述:
 * 作者:LiuYu
 */
public class LoginBean {


    /**
     * status : 1
     * msg : 请求成功
     * data : {"id":"16","mobile":"18743206721","nickname":"阿鲁特","avatar":"/Uploads/avatar/user1.jpg","token":"MDAwMDAwMDAwMH2KummA0KbctHeUYIWrrK60zsWsrol0bw","goods_collect":"0","shop_collect":"1","goods_visit":"11"}
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
         * id : 16
         * mobile : 18743206721
         * nickname : 阿鲁特
         * avatar : /Uploads/avatar/user1.jpg
         * token : MDAwMDAwMDAwMH2KummA0KbctHeUYIWrrK60zsWsrol0bw
         * goods_collect : 0
         * shop_collect : 1
         * goods_visit : 11
         */

        private String id;
        private String mobile;
        private String nickname;
        private String avatar;
        private String token;
        private String goods_collect;
        private String shop_collect;
        private String goods_visit;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

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
