package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/7/5
 * 描述:商品评价
 * 作者:LiuYu
 */
public class PingJiaBean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"comment_id":"12","shop_id":"1","goods_id":"19","order_id":"17","user_id":"5","username":"156****5672","content":"qss","score":"3","img":["/Uploads/comment/2018-07-05/5b3d862a29fdb","/Uploads/comment/2018-07-05/5b3d862a2b6a5","/Uploads/comment/2018-07-05/5b3d862a2c28b"],"ip_address":"119.53.105.129","is_show":"1","add_time":"1530758698","avatar":"/Uploads/avatar/2018-06-14/5b21f7b1a20c3"},{"comment_id":"8","shop_id":"1","goods_id":"19","order_id":"17","user_id":"6","username":"156****5672","content":"p","score":"3","img":["/Uploads/comment/2018-07-05/5b3d7c6f1cb9b"],"ip_address":"119.53.105.129","is_show":"1","add_time":"1530756207","avatar":"/Uploads/avatar/2018-06-19/5b28ef8114f38.png"},{"comment_id":"7","shop_id":"1","goods_id":"19","order_id":"12","user_id":"5","username":"156****5672","content":"宝贝不错","score":"2","img":["/Uploads/comment/2018-07-03/5b3b05cb19176.png"],"ip_address":"119.53.105.129","is_show":"1","add_time":"1530594763","avatar":"/Uploads/avatar/2018-06-14/5b21f7b1a20c3"},{"comment_id":"6","shop_id":"1","goods_id":"19","order_id":"10","user_id":"5","username":"156****5672","content":"宝贝不错","score":"2","img":[""],"ip_address":"119.53.108.194","is_show":"1","add_time":"1530594513","avatar":"/Uploads/avatar/2018-06-14/5b21f7b1a20c3"},{"comment_id":"5","shop_id":"1","goods_id":"19","order_id":"11","user_id":"5","username":"156****5672","content":"宝贝不错","score":"2","img":[""],"ip_address":"119.53.105.129","is_show":"1","add_time":"1530592641","avatar":"/Uploads/avatar/2018-06-14/5b21f7b1a20c3"},{"comment_id":"4","shop_id":"1","goods_id":"19","order_id":"14","user_id":"5","username":"156****5672","content":"宝贝不错","score":"3","img":[""],"ip_address":"119.53.105.129","is_show":"1","add_time":"1530592622","avatar":"/Uploads/avatar/2018-06-14/5b21f7b1a20c3"},{"comment_id":"3","shop_id":"1","goods_id":"19","order_id":"13","user_id":"5","username":"156****5672","content":"宝贝不错","score":"3","img":[""],"ip_address":"119.53.108.194","is_show":"1","add_time":"1530586559","avatar":"/Uploads/avatar/2018-06-14/5b21f7b1a20c3"}]
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
         * comment_id : 12
         * shop_id : 1
         * goods_id : 19
         * order_id : 17
         * user_id : 5
         * username : 156****5672
         * content : qss
         * score : 3
         * img : ["/Uploads/comment/2018-07-05/5b3d862a29fdb","/Uploads/comment/2018-07-05/5b3d862a2b6a5","/Uploads/comment/2018-07-05/5b3d862a2c28b"]
         * ip_address : 119.53.105.129
         * is_show : 1
         * add_time : 1530758698
         * avatar : /Uploads/avatar/2018-06-14/5b21f7b1a20c3
         */

        private String comment_id;
        private String shop_id;
        private String goods_id;
        private String order_id;
        private String user_id;
        private String username;
        private String content;
        private String score;
        private String ip_address;
        private String is_show;
        private String add_time;
        private String avatar;
        private List<String> img;

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getIp_address() {
            return ip_address;
        }

        public void setIp_address(String ip_address) {
            this.ip_address = ip_address;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }
    }
}
