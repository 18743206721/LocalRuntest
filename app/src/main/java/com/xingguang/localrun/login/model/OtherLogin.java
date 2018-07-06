package com.xingguang.localrun.login.model;

/**
 * 创建日期：2018/7/6
 * 描述:
 * 作者:LiuYu
 */
public class OtherLogin {


    /**
     * status : 1
     * msg : 请求成功
     * data : {"id":"4","mobile":"13146633658","password":"MDAwMDAwMDAwMH2Kqq1_qoyW","nickname":"张三","avatar":"/Uploads/avatar/user1.jpg","openid":null,"qq":null,"sina":null,"status":"1","last_login_time":"1527575145","last_login_ip":"127.0.0.1","reg_time":"1527571601","token":"MDAwMDAwMDAwMH560GmA0HzesoeCYoabsLG0zrdn"}
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
         * id : 4
         * mobile : 13146633658
         * password : MDAwMDAwMDAwMH2Kqq1_qoyW
         * nickname : 张三
         * avatar : /Uploads/avatar/user1.jpg
         * openid : null
         * qq : null
         * sina : null
         * status : 1
         * last_login_time : 1527575145
         * last_login_ip : 127.0.0.1
         * reg_time : 1527571601
         * token : MDAwMDAwMDAwMH560GmA0HzesoeCYoabsLG0zrdn
         */

        private String id;
        private String mobile;
        private String password;
        private String nickname;
        private String avatar;
        private Object openid;
        private Object qq;
        private Object sina;
        private String status;
        private String last_login_time;
        private String last_login_ip;
        private String reg_time;
        private String token;

        private String uniqid;
        private String user_id;
        private String third_type;

        public String getUniqid() {
            return uniqid;
        }

        public void setUniqid(String uniqid) {
            this.uniqid = uniqid;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getThird_type() {
            return third_type;
        }

        public void setThird_type(String third_type) {
            this.third_type = third_type;
        }

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public Object getSina() {
            return sina;
        }

        public void setSina(Object sina) {
            this.sina = sina;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
