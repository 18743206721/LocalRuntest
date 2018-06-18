package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/18
 * 描述:店铺首页轮播图
 * 作者:LiuYu
 */
public class ShopBannerBean {
    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"id":"3","data_id":"0","image":"/Uploads/Picture/2018-06-14/5b2215d42a0c6.png"},{"id":"2","data_id":"19","image":"/Uploads/Picture/2018-06-14/5b2215d42a0c6.png"}]
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
         * id : 3
         * data_id : 0
         * image : /Uploads/Picture/2018-06-14/5b2215d42a0c6.png
         */

        private String id;
        private String data_id;
        private String image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getData_id() {
            return data_id;
        }

        public void setData_id(String data_id) {
            this.data_id = data_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
