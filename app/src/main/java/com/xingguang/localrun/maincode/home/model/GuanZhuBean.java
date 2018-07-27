package com.xingguang.localrun.maincode.home.model;

/**
 * 创建日期：2018/7/26
 * 描述: 关注
 * 作者:LiuYu
 */
public class GuanZhuBean {


    /**
     * status : 0
     * msg : 您已关注该店铺
     * data : {"is_collected":1,"collect_id":"56"}
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
         * is_collected : 1
         * collect_id : 56
         */

        private int is_collected;
        private String collect_id;

        public int getIs_collected() {
            return is_collected;
        }

        public void setIs_collected(int is_collected) {
            this.is_collected = is_collected;
        }

        public String getCollect_id() {
            return collect_id;
        }

        public void setCollect_id(String collect_id) {
            this.collect_id = collect_id;
        }
    }
}
